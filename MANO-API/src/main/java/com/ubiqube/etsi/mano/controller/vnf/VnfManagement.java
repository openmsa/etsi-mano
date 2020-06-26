package com.ubiqube.etsi.mano.controller.vnf;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.json.MapperForView;
import com.ubiqube.etsi.mano.nfvo.v261.model.vnf.VnfPkgInfo;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;
import com.ubiqube.etsi.mano.utils.MimeType;
import com.ubiqube.etsi.mano.utils.SpringUtil;

import ma.glasnost.orika.MapperFacade;

/**
 * This implementation cover VNFO + NFVM & VNFO only.
 *
 * @author ovi@ubiqube.com
 *
 */
@Profile({ "!VNFM" })
@Service
public class VnfManagement implements VnfPackageManagement {
	private static final String APPLICATION_ZIP = "application/zip";
	private static final Logger LOG = LoggerFactory.getLogger(VnfManagement.class);

	private final VnfPackageRepository vnfPackageRepository;
	private final MapperFacade mapper;

	public VnfManagement(final VnfPackageRepository _vnfPackageRepository, final MapperFacade _mapper) {
		super();
		vnfPackageRepository = _vnfPackageRepository;
		mapper = _mapper;
		LOG.info("Starting VNF Package Management For NFVO+VNFM or NFVO Only Management.");
	}

	@Override
	public <U> U vnfPackagesVnfPkgIdGet(final UUID vnfPkgId, final Class<U> u) {
		final VnfPackage vnfPackage = vnfPackageRepository.get(vnfPkgId);
		return mapper.map(vnfPackage, u);
	}

	@Override
	public String vnfPackagesGet(final Map<String, String> queryParameters) {
		final String filter = queryParameters.get("filter");

		final List<VnfPackage> vnfPackageInfos = vnfPackageRepository.query(filter);
		final List<VnfPkgInfo> vnfPkginfos = vnfPackageInfos.stream()
				.map(x -> mapper.map(x, VnfPkgInfo.class))
				.collect(Collectors.toList());

		// vnfPkginfos.forEach(x -> x.setLinks(links.getVnfLinks(x.getId())));

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
	 */
	@Override
	public ResponseEntity<List<ResourceRegion>> vnfPackagesVnfPkgIdArtifactsArtifactPathGet(final UUID vnfPkgId, final String artifactPath, final String rangeHeader) {
		final byte[] content = vnfPackageRepository.getBinary(vnfPkgId, "vnfd");

		final InputStream bis = new ByteArrayInputStream(content);
		final ZipInputStream zis = new ZipInputStream(bis);
		ZipEntry entry = null;
		try {
			while ((entry = zis.getNextEntry()) != null) {
				if (entry.isDirectory()) {
					continue;
				}
				if (entry.getName().equals(artifactPath)) {
					return handleArtifact(zis, rangeHeader);

				}
			}
		} catch (final IOException e) {
			throw new GenericException(e);
		}
		throw new NotFoundException(new StringBuilder("VNF package artifact not found for vnfPack with id: ")
				.append(vnfPkgId).append(" artifactPath: ").append(artifactPath).toString());
	}

	@Override
	public ResponseEntity<Resource> vnfPackagesVnfPkgIdVnfdGet(final UUID vnfPkgId, final String accept) {
		vnfPackageRepository.get(vnfPkgId);

		final byte[] content = vnfPackageRepository.getBinary(vnfPkgId, "vnfd");
		final String mime = MimeType.findMatch(content);
		final InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(content));
		final BodyBuilder bodyBuilder = ResponseEntity.ok();
		handleMimeType(bodyBuilder, mime);
		return bodyBuilder.body(resource);
	}

	@Override
	public ResponseEntity<List<ResourceRegion>> vnfPackagesVnfPkgIdPackageContentGet(final UUID _vnfPkgId, final String _range) {
		final byte[] bytes = vnfPackageRepository.getBinary(_vnfPkgId, "vnfd");
		return SpringUtil.handleBytes(bytes, _range);
	}

	private static ResponseEntity<List<ResourceRegion>> handleArtifact(final ZipInputStream zis, final String _range) throws IOException {
		final byte[] zcontent = StreamUtils.copyToByteArray(zis);
		return SpringUtil.handleBytes(zcontent, _range);
	}

	private static void handleMimeType(final BodyBuilder bodyBuilder, final String mime) {
		if (MediaType.APPLICATION_JSON_VALUE.contentEquals(mime)) {
			bodyBuilder.contentType(MediaType.APPLICATION_JSON);
		} else if (APPLICATION_ZIP.equals(mime)) {
			bodyBuilder.header("Content-Type", mime);
		} else {
			bodyBuilder.contentType(MediaType.APPLICATION_OCTET_STREAM);
		}
	}

}
