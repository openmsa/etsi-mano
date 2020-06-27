package com.ubiqube.etsi.mano.nfvo.v261.controller.nsd;

import static com.ubiqube.etsi.mano.Constants.ensureDisabled;
import static com.ubiqube.etsi.mano.Constants.ensureIsOnboarded;
import static com.ubiqube.etsi.mano.Constants.ensureNotInUse;
import static com.ubiqube.etsi.mano.Constants.ensureNotOnboarded;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.dao.mano.NsdPackage;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.json.MapperForView;
import com.ubiqube.etsi.mano.nfvo.v261.NsdFactories;
import com.ubiqube.etsi.mano.nfvo.v261.model.Link;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005.CreateNsdInfoRequest;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005.NsdInfo;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005.NsdInfoLinks;
import com.ubiqube.etsi.mano.repository.NsdRepository;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;
import com.ubiqube.etsi.mano.service.Patcher;
import com.ubiqube.etsi.mano.service.event.ActionType;
import com.ubiqube.etsi.mano.service.event.EventManager;
import com.ubiqube.etsi.mano.utils.SpringUtil;

import io.swagger.annotations.Api;
import ma.glasnost.orika.MapperFacade;

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
	private final EventManager eventManager;
	private final MapperFacade mapper;

	public NsDescriptorSol005Api(final NsdRepository _nsdRepository, final VnfPackageRepository _vnfPackageRepository, final Patcher _patcher, final EventManager _eventManager, final MapperFacade _mapper) {
		nsdRepository = _nsdRepository;
		vnfPackageRepository = _vnfPackageRepository;
		patcher = _patcher;
		eventManager = _eventManager;
		mapper = _mapper;
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
		final List<NsdPackage> nsds = nsdRepository.query(filter);

		final List<NsdInfo> list = nsds.stream()
				.map(x -> mapper.map(x, NsdInfo.class))
				.collect(Collectors.toList());
		list.forEach(x -> x.setLinks(makeLinks(x.getId())));

		final ObjectMapper lmapper = MapperForView.getMapperForView(excludeFields, fields, null, null);
		try {
			return new ResponseEntity<>(lmapper.writeValueAsString(list), HttpStatus.OK);
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
		final UUID nsdInfoUuid = UUID.fromString(nsdInfoId);
		final NsdPackage nsdPackage = nsdRepository.get(nsdInfoUuid);
		ensureDisabled(nsdPackage);
		ensureNotInUse(nsdPackage);

		nsdRepository.delete(nsdInfoUuid);
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
	public ResponseEntity<NsdInfo> nsDescriptorsNsdInfoIdGet(final String nsdInfoId) {
		final NsdPackage nsdPackage = nsdRepository.get(UUID.fromString(nsdInfoId));
		final NsdInfo nsdInfo = mapper.map(nsdPackage, NsdInfo.class);
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
	public ResponseEntity<List<ResourceRegion>> nsDescriptorsNsdInfoIdNsdContentGet(final String nsdInfoId, final String accept, final String range) {
		final NsdPackage nsdInfo = nsdRepository.get(UUID.fromString(nsdInfoId));
		ensureIsOnboarded(nsdInfo);
		final byte[] bytes = nsdRepository.getBinary(UUID.fromString(nsdInfoId), "nsd");
		return SpringUtil.handleBytes(bytes, range);
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
		final NsdPackage nsdInfo = nsdRepository.get(UUID.fromString(nsdInfoId));
		ensureNotOnboarded(nsdInfo);
		try {
			nsdRepository.storeBinary(UUID.fromString(nsdInfoId), "nsd", file.getInputStream());
		} catch (final IOException e) {
			throw new GenericException(e);
		}

		eventManager.sendAction(ActionType.NSD_PKG_ONBOARD_FROM_BYTES, nsdInfo.getId(), new HashMap<>());
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
	public ResponseEntity<NsdInfo> nsDescriptorsNsdInfoIdPatch(final String nsdInfoId, final String body, final String contentType) {
		NsdPackage nsdPkgInfo = nsdRepository.get(UUID.fromString(nsdInfoId));
		patcher.patch(body, nsdPkgInfo);
		nsdPkgInfo = nsdRepository.save(nsdPkgInfo);
		final NsdInfo ret = mapper.map(nsdPkgInfo, NsdInfo.class);
		ret.setLinks(makeLinks(nsdInfoId));
		// NsdChangeNotification OSS/BSS
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}

	/**
	 * Create a new NS descriptor resource.
	 *
	 * The POST method is used to create a new NS descriptor resource or a new
	 * version of an on-boarded NS descriptor.
	 *
	 */
	@Override
	public ResponseEntity<NsdInfo> nsDescriptorsPost(final String contentType, final CreateNsdInfoRequest nsDescriptorsPostQuery) {
		NsdInfo nsdDescriptor = NsdFactories.createNsdInfo();
		final Map<String, String> userDefinedData = nsDescriptorsPostQuery.getUserDefinedData();
		nsdDescriptor.setUserDefinedData(userDefinedData);

		NsdPackage nsdPackage = mapper.map(nsdDescriptor, NsdPackage.class);
		nsdPackage = nsdRepository.save(nsdPackage);
		nsdDescriptor = mapper.map(nsdPackage, NsdInfo.class);
		nsdDescriptor.setLinks(makeLinks(nsdDescriptor.getId()));
		return new ResponseEntity<>(nsdDescriptor, HttpStatus.OK);
	}

	private static NsdInfoLinks makeLinks(@Nonnull final String id) {
		final NsdInfoLinks ret = new NsdInfoLinks();
		final Link nsdSelf = new Link();
		final String _self = linkTo(methodOn(NsDescriptorSol005.class).nsDescriptorsNsdInfoIdGet(id)).withSelfRel().getHref();
		nsdSelf.setHref(_self);
		ret.setSelf(nsdSelf);

		final String _nsdContent = linkTo(methodOn(NsDescriptorSol005.class).nsDescriptorsNsdInfoIdNsdContentGet(id, "", "")).withSelfRel().getHref();
		final Link nsdContent = new Link();
		nsdContent.setHref(_nsdContent);
		ret.setNsdContent(nsdContent);

		return ret;
	}

}
