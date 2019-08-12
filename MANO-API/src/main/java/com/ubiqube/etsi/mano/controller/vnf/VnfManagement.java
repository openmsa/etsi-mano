package com.ubiqube.etsi.mano.controller.vnf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.ubiqube.api.entities.repository.RepositoryElement;
import com.ubiqube.api.exception.ServiceException;
import com.ubiqube.api.interfaces.repository.RepositoryService;
import com.ubiqube.etsi.mano.exception.ConflictException;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.json.MapperForView;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackagesVnfPkgIdGetResponse;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo.OnboardingStateEnum;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;
import com.ubiqube.etsi.mano.utils.RangeHeader;
import com.ubiqube.etsi.mano.utils.ZipFileHandler;

@Service
public class VnfManagement {
	private static final String APPLICATION_ZIP = "application/zip";
	private static final Logger LOG = LoggerFactory.getLogger(VnfManagement.class);
	private static final String REPOSITORY_NVFO_DATAFILE_BASE_PATH = "Datafiles/NFVO/vnf_packages";

	private final VnfPackageRepository vnfPackageRepository;
	private final RepositoryService repositoryService;

	public VnfManagement(VnfPackageRepository _vnfPackageRepository, RepositoryService _repositoryService) {
		super();
		LOG.debug("Booting VNF SOL003 SOL005 Management.");
		vnfPackageRepository = _vnfPackageRepository;
		repositoryService = _repositoryService;
	}

	public VnfPkgInfo vnfPackagesVnfPkgIdGet(@Nonnull String vnfPkgId, @Nonnull Linkable links) {
		final VnfPkgInfo vnfPkgInfo = vnfPackageRepository.get(vnfPkgId);
		vnfPkgInfo.setLinks(links.getVnfLinks(vnfPkgId));
		final VnfPackagesVnfPkgIdGetResponse vnfPackagesVnfPkgIdGetResponse = new VnfPackagesVnfPkgIdGetResponse();
		vnfPackagesVnfPkgIdGetResponse.setVnfPkgInfo(vnfPkgInfo);
		return vnfPkgInfo;
	}

	public String vnfPackagesGet(@Nonnull Map<String, String> queryParameters, @Nonnull Linkable links) {
		final String filter = queryParameters.get("filter");

		final List<VnfPkgInfo> vnfPkginfos = vnfPackageRepository.query(filter);
		for (final VnfPkgInfo vnfPkgInfo : vnfPkginfos) {
			vnfPkgInfo.setLinks(links.getVnfLinks(vnfPkgInfo.getId()));
		}

		final String exclude = queryParameters.get("exclude_fields");
		final String fields = queryParameters.get("fields");

		final ObjectMapper mapperForQuery = MapperForView.getMapperForView(exclude, fields, null, null);

		try {
			return mapperForQuery.writeValueAsString(vnfPkginfos);
		} catch (final JsonProcessingException e) {
			throw new GenericException(e);
		}
	}

	/**
	 * We should not reply a Response here.
	 *
	 * @param vnfPkgId
	 * @param artifactPath
	 * @param _accept
	 * @param rangeHeader
	 * @return
	 * @throws ServiceException
	 */
	public ResponseEntity<Resource> vnfPackagesVnfPkgIdArtifactsArtifactPathGet(@Nonnull String vnfPkgId, @Nonnull String artifactPath, @Nullable RangeHeader rangeHeader) throws ServiceException {
		getVnfPkgIndividualInfoOrCheckOnboardingStatus(vnfPkgId, true);

		final List<String> listvnfPckgFiles = repositoryService.doSearch(new StringBuilder().append(REPOSITORY_NVFO_DATAFILE_BASE_PATH).append("/").append(vnfPkgId).append("/").append(artifactPath.trim()).toString(), "");

		if (!listvnfPckgFiles.isEmpty()) {
			if (listvnfPckgFiles.size() > 1) {
				return getZipArchive(rangeHeader, listvnfPckgFiles);
			}
			final RepositoryElement repositoryElement = repositoryService.getElement(listvnfPckgFiles.get(0));
			final byte[] content = repositoryService.getRepositoryElementContent(repositoryElement);
			final InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(content));
			return ResponseEntity.ok()
					.contentType(MediaType.APPLICATION_OCTET_STREAM)
					.body(resource);
		}
		throw new NotFoundException(new StringBuilder("VNF package artifact not found for vnfPack with id: ")
				.append(vnfPkgId).append(" artifactPath: ").append(artifactPath).toString());
	}

	public ResponseEntity<Resource> vnfPackagesVnfPkgIdVnfdGet(@Nonnull String vnfPkgId, @Nullable String accept) {
		final List<String> listvnfPckgFiles = new LinkedList<>();

		getVnfPkgIndividualInfoOrCheckOnboardingStatus(vnfPkgId, true);

		// - Implement VNFD multi-files support
		final String uri = new StringBuilder().append(REPOSITORY_NVFO_DATAFILE_BASE_PATH).append("/").append(vnfPkgId).append("/").append("vnfd.json").toString();
		listvnfPckgFiles.add(uri);

		boolean isVnfd;
		try {
			isVnfd = repositoryService.exists(uri);
		} catch (final ServiceException e) {
			throw new GenericException(e);
		}

		if (isVnfd) {
			if (MediaType.TEXT_PLAIN_VALUE.equals(accept)) {
				final RepositoryElement repositoryElement = repositoryService.getElement(uri);
				final byte[] content = repositoryService.getRepositoryElementContent(repositoryElement);
				final InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(content));
				return ResponseEntity.ok(resource);
			} else if (APPLICATION_ZIP.equals(accept) && MediaType.TEXT_PLAIN_VALUE.equals(accept)) {
				return getZipArchive(null, listvnfPckgFiles);
			} else {
				final RepositoryElement repositoryElement = repositoryService.getElement(uri);
				final String content = new String(repositoryService.getRepositoryElementContent(repositoryElement), StandardCharsets.UTF_8);
				final byte[] yaml = conJsonToYaml(content);
				final InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(yaml));
				return ResponseEntity.ok()
						.contentType(MediaType.APPLICATION_OCTET_STREAM)
						.body(resource);
			}
		}
		throw new NotFoundException("VNFD not found for vnfPkg with id: " + vnfPkgId);
	}

	/**
	 * Method allows to archive VNF Package contents and artifacts.
	 *
	 * @param range
	 * @param listvnfPckgFiles
	 * @return
	 *
	 */
	private ResponseEntity<Resource> getZipArchive(RangeHeader rangeHeader, List<String> listvnfPckgFiles) {

		final ZipFileHandler zip = new ZipFileHandler(repositoryService, listvnfPckgFiles);
		ByteArrayOutputStream bos;

		try {
			if (rangeHeader == null) {
				bos = zip.getZipFile();
				final InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(bos.toByteArray()));
				final MediaType contentType = MediaType.APPLICATION_OCTET_STREAM;
				return ResponseEntity.ok().contentType(contentType).body(resource);

			}
			bos = zip.getByteRangeZipFile((int) rangeHeader.getFrom(), rangeHeader.getTo());
			final String contentRange = new StringBuilder().append("bytes").append(rangeHeader.getFrom()).append("-")
					.append(rangeHeader.getTo()).append("/").append(zip.zipFileByteArrayLength()).toString();
			final InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(bos.toByteArray()));
			final MediaType contentType = MediaType.APPLICATION_OCTET_STREAM;
			return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
					.header("Content-Range", contentRange)
					.contentType(contentType)
					.body(resource);
		} catch (final IOException e) {
			throw new GenericException(e);
		}
	}

	/**
	 * Map YAML file to JsonObject.
	 *
	 * @param yaml VNF Package Metadata from repository
	 * @return the JsonObject as a String
	 */
	private static String convertYamlToJson(final String yaml) {
		try {
			final ObjectMapper yamlReader = new ObjectMapper(new YAMLFactory());
			final Object obj = yamlReader.readValue(yaml, Object.class);
			final ObjectMapper jsonWriter = new ObjectMapper();
			return jsonWriter.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
		} catch (final IOException e) {
			throw new GenericException(e);
		}
	}

	private static byte[] conJsonToYaml(String json) {
		try {
			final ObjectMapper jsonReader = new ObjectMapper();
			final Object obj = jsonReader.readValue(json, Object.class);
			final ObjectMapper yamlWriter = new ObjectMapper(new YAMLFactory());
			return yamlWriter.writerWithDefaultPrettyPrinter().writeValueAsBytes(obj);
		} catch (final IOException e) {
			throw new GenericException(e);
		}
	}

	private VnfPkgInfo getVnfPkgIndividualInfoOrCheckOnboardingStatus(@Nonnull String vnfPkgId, boolean isCheckOnbordingStatus) {
		final VnfPkgInfo vnfPkgInfo = vnfPackageRepository.get(vnfPkgId);

		if (isCheckOnbordingStatus) {
			final String onboardingState = vnfPkgInfo.getOnboardingState();
			if (OnboardingStateEnum.PROCESSING.toString().equalsIgnoreCase(onboardingState)
					|| OnboardingStateEnum.UPLOADING.toString().equalsIgnoreCase(onboardingState.toUpperCase())) {
				throw new ConflictException("Conflict with the current VNF Package onbording state: " + onboardingState.toUpperCase());
			}
		} else {
			return vnfPkgInfo;
		}
		return vnfPkgInfo;
	}

	public ResponseEntity<Resource> vnfPackagesVnfPkgIdPackageContentGet(@Nonnull String _vnfPkgId, String _range) {
		getVnfPkgIndividualInfoOrCheckOnboardingStatus(_vnfPkgId, true);

		// List vnfd package from repository
		List<String> listvnfPckgFiles;
		try {
			listvnfPckgFiles = repositoryService.doSearch(new StringBuilder().append(REPOSITORY_NVFO_DATAFILE_BASE_PATH).append("/").append(_vnfPkgId).toString(), "");
		} catch (final ServiceException e) {
			throw new GenericException(e);
		}

		if (!listvnfPckgFiles.isEmpty()) {
			return getZipArchive(RangeHeader.fromValue(_range), listvnfPckgFiles);
		}
		throw new NotFoundException("VNF package content not found for vnfPkgId: " + _vnfPkgId);
	}

}
