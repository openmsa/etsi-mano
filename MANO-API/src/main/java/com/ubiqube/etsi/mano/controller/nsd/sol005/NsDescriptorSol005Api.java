package com.ubiqube.etsi.mano.controller.nsd.sol005;

import static com.ubiqube.etsi.mano.Constants.ensureDisabled;
import static com.ubiqube.etsi.mano.Constants.ensureIsOnboarded;
import static com.ubiqube.etsi.mano.Constants.ensureNotInUse;
import static com.ubiqube.etsi.mano.Constants.ensureNotOnboarded;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.factory.NsdFactories;
import com.ubiqube.etsi.mano.json.MapperForView;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo.NsdOnboardingStateEnum;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo.NsdOperationalStateEnum;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfoIdGetResponse;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfoLinks;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfoLinksSelf;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsPostQuery;
import com.ubiqube.etsi.mano.repository.NsdRepository;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;
import com.ubiqube.etsi.mano.service.Patcher;
import com.ubiqube.etsi.mano.utils.MimeType;
import com.ubiqube.etsi.mano.utils.RangeHeader;

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
@Profile({ "!VNFM" })
@RestController
@Api(value = "/sol005/nsd/v1/ns_descriptors")
public class NsDescriptorSol005Api implements NsDescriptorSol005 {

	private static final Logger LOG = LoggerFactory.getLogger(NsDescriptorSol005Api.class);

	private final NsdRepository nsdRepository;
	private final Patcher patcher;
	private final VnfPackageRepository vnfPackageRepository;

	public NsDescriptorSol005Api(final NsdRepository _nsdRepository, final VnfPackageRepository _vnfPackageRepository, final Patcher _patcher) {
		nsdRepository = _nsdRepository;
		vnfPackageRepository = _vnfPackageRepository;
		patcher = _patcher;
		LOG.info("Starting NSD Management SOL005 Controller.");
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
	public ResponseEntity<String> nsDescriptorsGet(final String accept, final String filter, final String allFields, final String fields, final String excludeFields, final String excludeDefault) {
		final List<NsDescriptorsNsdInfo> nsds = nsdRepository.query(filter);
		final List<NsDescriptorsNsdInfoIdGetResponse> response = nsds.stream()
				.map(NsdFactories::createNsDescriptorsNsdInfoIdGetResponse)
				.collect(Collectors.toList());

		response.forEach(x -> x.getNsdInfo().setLinks(makeLinks(x.getNsdInfo().getId())));

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
	public ResponseEntity<Void> nsDescriptorsNsdInfoIdDelete(final String nsdInfoId) {
		final NsDescriptorsNsdInfo nsdInfo = nsdRepository.get(nsdInfoId);
		ensureDisabled(nsdInfo);
		ensureNotInUse(nsdInfo);

		nsdRepository.delete(nsdInfoId);
		// NsdDeletionNotification OSS/BSS
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
	public ResponseEntity<NsDescriptorsNsdInfo> nsDescriptorsNsdInfoIdGet(final String nsdInfoId, final String accept) {
		final NsDescriptorsNsdInfo nsdInfo = nsdRepository.get(nsdInfoId);

		final NsDescriptorsNsdInfoIdGetResponse nsDescriptorsNsdInfoIdGetResponse = new NsDescriptorsNsdInfoIdGetResponse();
		nsDescriptorsNsdInfoIdGetResponse.setNsdInfo(nsdInfo);
		nsdInfo.setLinks(makeLinks(nsdInfoId));
		return new ResponseEntity<>(nsdInfo, HttpStatus.OK);
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
	public ResponseEntity<Resource> nsDescriptorsNsdInfoIdNsdContentGet(final String nsdInfoId, final String accept, final String range) {
		final RangeHeader rangeHeader = RangeHeader.fromValue(range);
		final NsDescriptorsNsdInfo nsdInfo = nsdRepository.get(nsdInfoId);
		ensureIsOnboarded(nsdInfo);
		byte[] bytes;
		if (rangeHeader != null) {
			bytes = nsdRepository.getBinary(nsdInfoId, "nsd", rangeHeader.getFrom(), (long) rangeHeader.getTo());
			final InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(bytes));
			// Content-Range: bytes 0-1023/146515
			final String mime = MimeType.findMatch(bytes);
			return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
					.header("Content-Range", rangeHeader.getContentRange(bytes.length))
					.header("Content-Type", mime)
					.body(resource);
		}
		bytes = nsdRepository.getBinary(nsdInfoId, "nsd");
		final InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(bytes));
		final String mime = MimeType.findMatch(bytes);
		return ResponseEntity.ok()
				.header("Content-Type", mime)
				.body(resource);
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
	public ResponseEntity<Void> nsDescriptorsNsdInfoIdNsdContentPut(final String nsdInfoId, final String accept, final MultipartFile file) {
		final NsDescriptorsNsdInfo nsdInfo = nsdRepository.get(nsdInfoId);
		ensureNotOnboarded(nsdInfo);
		try {
			// Must be Async.
			nsdRepository.storeBinary(nsdInfoId, "nsd", file.getInputStream());
		} catch (final IOException e) {
			throw new GenericException(e);
		}

		nsdInfo.setNsdOnboardingState(NsdOnboardingStateEnum.ONBOARDED);
		nsdInfo.setNsdOperationalState(NsdOperationalStateEnum.ENABLED);
		nsdRepository.save(nsdInfo);
		nsdInfo.setLinks(makeLinks(nsdInfoId));
		// NsdOnBoardingNotification to OSS/BSS
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
	public ResponseEntity<NsDescriptorsNsdInfo> nsDescriptorsNsdInfoIdPatch(final String nsdInfoId, final String body, final String contentType) {
		final NsDescriptorsNsdInfo nsdPkgInfo = nsdRepository.get(nsdInfoId);
		patcher.patch(body, nsdPkgInfo);
		nsdRepository.save(nsdPkgInfo);
		nsdPkgInfo.setLinks(makeLinks(nsdInfoId));
		// NsdChangeNotification OSS/BSS
		return new ResponseEntity<>(nsdPkgInfo, HttpStatus.OK);
	}

	/**
	 * Create a new NS descriptor resource.
	 *
	 * The POST method is used to create a new NS descriptor resource or a new
	 * version of an on-boarded NS descriptor.
	 *
	 */
	@Override
	public ResponseEntity<NsDescriptorsNsdInfo> nsDescriptorsPost(final String accept, final String contentType, final NsDescriptorsPostQuery nsDescriptorsPostQuery) {
		final NsDescriptorsNsdInfo nsdDescriptor = NsdFactories.createNsDescriptorsNsdInfo();
		final Map<String, Object> userDefinedData = nsDescriptorsPostQuery.getCreateNsdInfoRequest().getUserDefinedData();
		nsdDescriptor.setUserDefinedData(userDefinedData);
		nsdDescriptor.setNsdName((String) userDefinedData.get("name"));
		final List<String> vnfPkgIds = (List<String>) userDefinedData.get("vnfPkgIds");
		// Verify if VNF Package exists.
		vnfPkgIds.stream().forEach(vnfPackageRepository::get);
		nsdDescriptor.setVnfPkgIds(vnfPkgIds);
		userDefinedData.remove("vnfPkgIds");

		final List<String> pnfPkgIds = (List<String>) userDefinedData.get("pnfPkgIds");
		// TODO: verify PNF ids.
		nsdDescriptor.setPnfdInfoIds(pnfPkgIds);
		nsdRepository.save(nsdDescriptor);

		if (null != userDefinedData.get("heat")) {
			nsdRepository.storeObject(nsdDescriptor.getId(), "nsd", userDefinedData.get("heat"));
			nsdDescriptor.setNsdOnboardingState(NsdOnboardingStateEnum.ONBOARDED);
			nsdDescriptor.setNsdOperationalState(NsdOperationalStateEnum.ENABLED);
			nsdRepository.save(nsdDescriptor);
		}
		nsdDescriptor.setLinks(makeLinks(nsdDescriptor.getId()));
		return new ResponseEntity<>(nsdDescriptor, HttpStatus.OK);
	}

	private static NsDescriptorsNsdInfoLinks makeLinks(@Nonnull final String id) {
		final NsDescriptorsNsdInfoLinks ret = new NsDescriptorsNsdInfoLinks();
		final NsDescriptorsNsdInfoLinksSelf nsdSelf = new NsDescriptorsNsdInfoLinksSelf();
		final String _self = linkTo(methodOn(NsDescriptorSol005.class).nsDescriptorsNsdInfoIdGet(id, "")).withSelfRel().getHref();
		nsdSelf.setHref(_self);
		ret.setSelf(nsdSelf);

		final String _nsdContent = linkTo(methodOn(NsDescriptorSol005.class).nsDescriptorsNsdInfoIdNsdContentGet(id, "", "")).withSelfRel().getHref();
		final NsDescriptorsNsdInfoLinksSelf nsdContent = new NsDescriptorsNsdInfoLinksSelf();
		nsdContent.setHref(_nsdContent);
		ret.setNsdContent(nsdContent);

		return ret;
	}

}
