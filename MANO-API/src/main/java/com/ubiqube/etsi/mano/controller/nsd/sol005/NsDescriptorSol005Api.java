package com.ubiqube.etsi.mano.controller.nsd.sol005;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Nonnull;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.api.entities.repository.RepositoryElement;
import com.ubiqube.api.exception.ServiceException;
import com.ubiqube.api.interfaces.repository.RepositoryService;
import com.ubiqube.etsi.mano.exception.ConflictException;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.factory.NsdFactories;
import com.ubiqube.etsi.mano.json.MapperForView;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo.NsdOnboardingStateEnum;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfoIdGetResponse;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfoIdPatchQuery;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfoLinks;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfoLinksSelf;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsPostQuery;
import com.ubiqube.etsi.mano.repository.NsdRepository;
import com.ubiqube.etsi.mano.utils.RangeHeader;
import com.ubiqube.etsi.mano.utils.ZipFileHandler;

import io.swagger.annotations.Api;

/**
 * SOL005 - NSD Management Interface
 *
 * <p>
 * SOL005 - NSD Management Interface IMPORTANT: Please note that this file might
 * be not aligned to the current version of the ETSI Group Specification it
 * refers to and has not been approved by the ETSI NFV ISG. In case of
 * discrepancies the published ETSI Group Specification takes precedence. Please
 * report bugs to
 * https://forge.etsi.org/bugzilla/buglist.cgi?component=Nfv-Openapis
 *
 */
@RestController
@Api(value = "/sol005/nsd/v1/ns_descriptors")
public class NsDescriptorSol005Api implements NsDescriptorSol005 {
	protected static final String NVFO_DATAFILE_BASE_PATH = "Datafiles/NFVO";
	private static final String REPOSITORY_NSD_BASE_PATH = NVFO_DATAFILE_BASE_PATH + "/nsd";

	private final NsdRepository nsdRepository;

	private final RepositoryService repositoryService;

	public NsDescriptorSol005Api(NsdRepository _nsdRepository, RepositoryService _repositoryService) {
		nsdRepository = _nsdRepository;
		repositoryService = _repositoryService;
	}

	/**
	 * Query information about multiple NS descriptor resources.
	 *
	 * \&quot;The GET method queries information about multiple NS descriptor
	 * resources. This method shall follow the provisions specified in the Tables
	 * 5.4.2.3.2-1 and 5.4.2.3.2-2 for URI query parameters, request and response
	 * data structures, and response codes.\&quot;
	 *
	 */
	@Override
	public ResponseEntity<String> nsDescriptorsGet(String accept, String filter, String allFields, String fields, String excludeFields, String excludeDefault) {
		final List<NsDescriptorsNsdInfoIdGetResponse> response = new ArrayList<>();
		final List<NsDescriptorsNsdInfo> nsds = nsdRepository.query(filter);
		for (final NsDescriptorsNsdInfo nsDescriptorsNsdInfo : nsds) {
			final NsDescriptorsNsdInfoIdGetResponse resp = new NsDescriptorsNsdInfoIdGetResponse();
			nsDescriptorsNsdInfo.setLinks(makeLinks(nsDescriptorsNsdInfo.getId()));
			resp.setNsdInfo(nsDescriptorsNsdInfo);
			response.add(resp);
		}

		final ObjectMapper mapper = MapperForView.getMapperForView(excludeFields, fields, null, null);
		try {
			return new ResponseEntity<>(mapper.writeValueAsString(response), HttpStatus.OK);
		} catch (final JsonProcessingException e) {
			throw new GenericException(e);
		}

	}

	/**
	 * Delete an individual NS descriptor resource.
	 *
	 * The DELETE method deletes an individual NS descriptor resource. An individual
	 * NS descriptor resource can only be deleted when there is no NS instance using
	 * it (i.e. usageState &#x3D; NOT_IN_USE) and has been disabled already (i.e.
	 * operationalState &#x3D; DISABLED). Otherwise, the DELETE method shall fail.
	 *
	 */
	@Override
	public ResponseEntity<Void> nsDescriptorsNsdInfoIdDelete(String nsdInfoId) {
		final NsDescriptorsNsdInfo nsdInfo = nsdRepository.get(nsdInfoId);
		if (!nsdInfo.getNsdOperationalState().equals("DISABLED") || nsdInfo.getNsdUsageState().equals("IN_USE")) {
			throw new ConflictException("Nsd in bad state " + nsdInfoId);
		}
		nsdRepository.delete(nsdInfoId);
		return ResponseEntity.noContent().build();
	}

	/**
	 * Read information about an individual NS descriptor resource.
	 *
	 * \&quot;The GET method reads information about an individual NS descriptor.
	 * This method shall follow the provisions specified in GS NFV-SOL 005 Tables
	 * 5.4.3.3.2-1 and 5.4.3.3.2-2 of GS NFV-SOL 005 for URI query parameters,
	 * request and response data structures, and response codes.\&quot;
	 *
	 */
	@Override
	public ResponseEntity<NsDescriptorsNsdInfo> nsDescriptorsNsdInfoIdGet(String nsdInfoId, String accept) {
		final NsDescriptorsNsdInfo nsdIfno = nsdRepository.get(nsdInfoId);

		final NsDescriptorsNsdInfoIdGetResponse nsDescriptorsNsdInfoIdGetResponse = new NsDescriptorsNsdInfoIdGetResponse();
		nsDescriptorsNsdInfoIdGetResponse.setNsdInfo(nsdIfno);
		nsdIfno.setLinks(makeLinks(nsdInfoId));
		return new ResponseEntity<>(nsdIfno, HttpStatus.OK);
	}

	/**
	 * Fetch the content of a NSD.
	 *
	 * The GET method fetches the content of the NSD. The NSD can be implemented as
	 * a single file or as a collection of multiple files. If the NSD is implemented
	 * in the form of multiple files, a ZIP file embedding these files shall be
	 * returned. If the NSD is implemented as a single file, either that file or a
	 * ZIP file embedding that file shall be returned. The selection of the format
	 * is controlled by the \&quot;Accept\&quot; HTTP header passed in the GET
	 * request:• If the \&quot;Accept\&quot; header contains only
	 * \&quot;text/plain\&quot; and the NSD is implemented as a single file, the
	 * file shall be returned; otherwise, an error message shall be returned.• If
	 * the \&quot;Accept\&quot; header contains only \&quot;application/zip\&quot;,
	 * the single file or the multiple files that make up the NSD shall be returned
	 * embedded in a ZIP file.• If the \&quot;Accept\&quot; header contains both
	 * \&quot;text/plain\&quot; and \&quot;application/zip\&quot;, it is up to the
	 * NFVO to choose the format to return for a single-file NSD; for a multi-file
	 * NSD, a ZIP file shall be returned.NOTE: The structure of the NSD zip file is
	 * outside the scope of the present document.
	 *
	 * @return
	 *
	 */
	@Override
	public ResponseEntity<Resource> nsDescriptorsNsdInfoIdNsdContentGet(String nsdInfoId, String accept, String range) {
		try {
			final RangeHeader rangeHeader = RangeHeader.fromValue(range);
			final List<String> listvnfPckgFiles = repositoryService.doSearch(new StringBuilder().append(REPOSITORY_NSD_BASE_PATH).append("/").append(nsdInfoId).toString(), "");
			if (!listvnfPckgFiles.isEmpty()) {
				if (listvnfPckgFiles.size() > 1) {
					return getZipArchive(rangeHeader, listvnfPckgFiles);
				}
				final RepositoryElement repositoryElement = repositoryService.getElement(listvnfPckgFiles.get(0));
				final byte[] content = repositoryService.getRepositoryElementContent(repositoryElement);
				final InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(content));
				return ResponseEntity.ok()
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.body(resource);

			}
			throw new NotFoundException(new StringBuilder("VNF package artifact not found for vnfPack with id: ").append(nsdInfoId).toString());
		} catch (final ServiceException e) {
			throw new GenericException(e);
		}
	}

	/**
	 * Upload the content of a NSD.
	 *
	 * \&quot;The PUT method is used to upload the content of a NSD. The NSD to be
	 * uploaded can be implemented as a single file or as a collection of multiple
	 * files, as defined in clause 5.4.4.3.2 of GS NFV-SOL 005. If the NSD is
	 * implemented in the form of multiple files, a ZIP file embedding these files
	 * shall be uploaded. If the NSD is implemented as a single file, either that
	 * file or a ZIP file embedding that file shall be uploaded. The
	 * \&quot;Content-Type\&quot; HTTP header in the PUT request shall be set
	 * accordingly based on the format selection of the NSD. If the NSD to be
	 * uploaded is a text file, the \&quot;Content-Type\&quot; header is set to
	 * \&quot;text/plain\&quot;. If the NSD to be uploaded is a zip file, the
	 * \&quot;Content-Type\&quot; header is set to \&quot;application/zip\&quot;.
	 * This method shall follow the provisions specified in the Tables 5.4.4.3.3-1
	 * and 5.4.4.3.3-2 of GS-NFV-SOL 005 for URI query parameters, request and
	 * response data structures, and response codes.\&quot;
	 *
	 */
	@Override
	public ResponseEntity<Void> nsDescriptorsNsdInfoIdNsdContentPut(String nsdInfoId, String accept, final MultipartFile file) {
		try {
			nsdRepository.storeBinary(nsdInfoId, file.getInputStream(), "nsd");
		} catch (final IOException e) {
			throw new GenericException(e);
		}
		final NsDescriptorsNsdInfo nsdInfo = nsdRepository.get(nsdInfoId);
		nsdInfo.setNsdOnboardingState(NsdOnboardingStateEnum.ONBOARDED);
		nsdRepository.save(nsdInfo);
		nsdInfo.setLinks(makeLinks(nsdInfoId));
		return ResponseEntity.accepted().build();
	}

	/**
	 * Modify the operational state and/or the user defined data of an individual NS
	 * descriptor resource.
	 *
	 * The PATCH method modifies the operational state and/or user defined data of
	 * an individual NS descriptor resource. This method can be used to: 1) Enable a
	 * previously disabled individual NS descriptor resource, allowing again its use
	 * for instantiation of new network service with this descriptor. The usage
	 * state (i.e. \&quot;IN_USE/NOT_IN_USE\&quot;) shall not change as result. 2)
	 * Disable a previously enabled individual NS descriptor resource, preventing
	 * any further use for instantiation of new network service(s) with this
	 * descriptor. The usage state (i.e. \&quot;IN_USE/NOT_IN_USE\&quot;) shall not
	 * changes a result. 3) Modify the user defined data of an individual NS
	 * descriptor resource.
	 *
	 */
	@Override
	public ResponseEntity<List<Object>> nsDescriptorsNsdInfoIdPatch(String nsdInfoId, NsDescriptorsNsdInfoIdPatchQuery body, String contentType) {
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_IMPLEMENTED);
	}

	/**
	 * Create a new NS descriptor resource.
	 *
	 * The POST method is used to create a new NS descriptor resource or a new
	 * version of an on-boarded NS descriptor.
	 *
	 */
	@Override
	public ResponseEntity<NsDescriptorsNsdInfo> nsDescriptorsPost(String accept, String contentType, NsDescriptorsPostQuery nsDescriptorsPostQuery) {
		final String id = UUID.randomUUID().toString();

		final NsDescriptorsNsdInfo resp = NsdFactories.createNsDescriptorsNsdInfo(id);
		final Map<String, Object> userDefinedData = (Map<String, Object>) nsDescriptorsPostQuery.getCreateNsdInfoRequest().getUserDefinedData();
		resp.setUserDefinedData(userDefinedData);
		resp.setNsdName((String) userDefinedData.get("name"));
		final List<String> vnfPkgIds = (List<String>) userDefinedData.get("vnfPkgIds");
		resp.setVnfPkgIds(vnfPkgIds);

		nsdRepository.save(resp);
		return new ResponseEntity<>(resp, HttpStatus.OK);
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
				return ResponseEntity.ok()
						.contentType(MediaType.APPLICATION_OCTET_STREAM)
						.body(resource);

			}
			bos = zip.getByteRangeZipFile((int) rangeHeader.getFrom(), rangeHeader.getTo());
			final String contentRange = new StringBuilder().append("bytes ").append(rangeHeader.getFrom()).append("-")
					.append(rangeHeader.getTo()).append("/").append(zip.zipFileByteArrayLength()).toString();
			final InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(bos.toByteArray()));
			return ResponseEntity.ok()
					.contentType(MediaType.APPLICATION_OCTET_STREAM)
					.header("Range", contentRange)
					.body(resource);
		} catch (final IOException e) {
			throw new GenericException(e);
		}
	}

	private static NsDescriptorsNsdInfoLinks makeLinks(@Nonnull String id) {
		final NsDescriptorsNsdInfoLinks ret = new NsDescriptorsNsdInfoLinks();
		final NsDescriptorsNsdInfoLinksSelf nsdSelf = new NsDescriptorsNsdInfoLinksSelf();
		final String _self = linkTo(methodOn(NsDescriptorSol005Api.class).nsDescriptorsNsdInfoIdGet(id, "")).withSelfRel().getHref();
		nsdSelf.setHref(_self);

		final String _nsdContent = linkTo(methodOn(NsDescriptorSol005Api.class).nsDescriptorsNsdInfoIdNsdContentGet(id, "", "")).withSelfRel().getHref();
		final NsDescriptorsNsdInfoLinksSelf nsdContent = new NsDescriptorsNsdInfoLinksSelf();
		nsdContent.setHref(_nsdContent);
		ret.setNsdContent(nsdContent);

		return ret;
	}

}
