package com.ubiqube.etsi.mano.nfvo.v261.controller.vnf;

import static com.ubiqube.etsi.mano.Constants.ensureDisabled;
import static com.ubiqube.etsi.mano.Constants.ensureNotInUse;
import static com.ubiqube.etsi.mano.Constants.ensureNotOnboarded;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ubiqube.etsi.mano.common.v261.controller.vnf.Linkable;
import com.ubiqube.etsi.mano.common.v261.model.vnf.VnfPkgInfo;
import com.ubiqube.etsi.mano.controller.vnf.VnfPackageManagement;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.nfvo.v261.VnfPackageFactory;
import com.ubiqube.etsi.mano.nfvo.v261.model.vnf.CreateVnfPkgInfoRequest;
import com.ubiqube.etsi.mano.nfvo.v261.model.vnf.UploadVnfPkgFromUriRequest;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;
import com.ubiqube.etsi.mano.service.Patcher;
import com.ubiqube.etsi.mano.service.event.ActionType;
import com.ubiqube.etsi.mano.service.event.EventManager;
import com.ubiqube.etsi.mano.service.event.NotificationEvent;
import com.ubiqube.etsi.mano.utils.SpringUtils;

import ma.glasnost.orika.MapperFacade;

/**
 * SOL005 - VNF Package Management Interface
 *
 * <p>
 * SOL005 - VNF Package Management Interface IMPORTANT: Please note that this
 * file might be not aligned to the current version of the ETSI Group
 * Specification it refers to and has not been approved by the ETSI NFV ISG. In
 * case of discrepancies the published ETSI Group Specification takes
 * precedence. Please report bugs to
 * https://forge.etsi.org/bugzilla/buglist.cgi?component=Nfv-Openapis
 *
 * NOTE: Normaly we should receive object in methods but Genson seems to be on
 * the classpath and is unable to unserialize objects. So we use a string2Object
 * to do So. Note same problems occurred when returning object some times genson
 * could be here and not Jackson, in this case you can use object2String.
 *
 */
@Profile({ "!VNFM" })
@RestController
public final class VnfPackageSol005Api implements VnfPackageSol005 {
	private static final Logger LOG = LoggerFactory.getLogger(VnfPackageSol005Api.class);
	@Nonnull
	private final Linkable links = new Sol005Linkable();
	private final VnfPackageManagement vnfManagement;
	private final VnfPackageRepository vnfPackageRepository;
	private final Patcher patcher;
	private final EventManager eventManager;
	private final MapperFacade mapper;

	public VnfPackageSol005Api(final VnfPackageManagement _vnfManagement, final Patcher _patcher, final VnfPackageRepository _vnfPackageRepository, final EventManager _eventManager, final MapperFacade _mapper) {
		vnfManagement = _vnfManagement;
		patcher = _patcher;
		vnfPackageRepository = _vnfPackageRepository;
		eventManager = _eventManager;
		mapper = _mapper;
		LOG.info("Starting VNF Package SOL005 Controller.");
	}

	@Override
	public ResponseEntity<String> vnfPackagesGet(final Map<String, String> requestParams) {
		final String resp = vnfManagement.vnfPackagesGet(requestParams);
		return ResponseEntity.ok(resp);
	}

	@Override
	public ResponseEntity<List<ResourceRegion>> vnfPackagesVnfPkgIdArtifactsArtifactPathGet(final String vnfPkgId, final HttpServletRequest request, final String accept, final String range) {
		final String artifactPath = SpringUtils.extractParams(request);
		return vnfManagement.vnfPackagesVnfPkgIdArtifactsArtifactPathGet(UUID.fromString(vnfPkgId), artifactPath, range);
	}

	@Override
	// TODO: Same as SOL003 ?
	public ResponseEntity<VnfPkgInfo> vnfPackagesVnfPkgIdGet(final String vnfPkgId, final String accept) {
		final VnfPkgInfo vnfPkgInfo = vnfManagement.vnfPackagesVnfPkgIdGet(UUID.fromString(vnfPkgId), VnfPkgInfo.class);
		vnfPkgInfo.setLinks(links.getVnfLinks(vnfPkgId));
		return ResponseEntity.ok(vnfPkgInfo);
	}

	@Override
	public ResponseEntity<List<ResourceRegion>> vnfPackagesVnfPkgIdPackageContentGet(final String vnfPkgId, final String accept, final String range) {
		return vnfManagement.vnfPackagesVnfPkgIdPackageContentGet(UUID.fromString(vnfPkgId), range);
	}

	@Override
	public ResponseEntity<Resource> vnfPackagesVnfPkgIdVnfdGet(final String vnfPkgId, final String accept) {
		return vnfManagement.vnfPackagesVnfPkgIdVnfdGet(UUID.fromString(vnfPkgId), accept);
	}

	/**
	 * Create a new individual VNF package resource. 9.5.2.4
	 *
	 * The POST method creates a new individual VNF package resource.
	 *
	 */
	@Override
	public ResponseEntity<VnfPkgInfo> vnfPackagesPost(final String accept, final String contentType, final CreateVnfPkgInfoRequest vnfPackagePostQuery) {
		final Map<String, String> userData = vnfPackagePostQuery.getUserDefinedData();
		VnfPackage vnfPackage = VnfPackageFactory.createVnfPkgInfo(userData);
		vnfPackage = vnfPackageRepository.save(vnfPackage);
		final VnfPkgInfo vnfPkgInfo = mapper.map(vnfPackage, VnfPkgInfo.class);
		vnfPkgInfo.setLinks(links.getVnfLinks(vnfPackage.getId().toString()));
		return new ResponseEntity<>(vnfPkgInfo, HttpStatus.CREATED);
	}

	/**
	 * Delete an individual VNF package.
	 *
	 * The DELETE method deletes an individual VNF Package resource.
	 *
	 */
	@Override
	public ResponseEntity<Void> vnfPackagesVnfPkgIdDelete(final String vnfPkgId) {
		final UUID vnfPkgUuid = UUID.fromString(vnfPkgId);
		final VnfPackage vnfPackage = vnfPackageRepository.get(vnfPkgUuid);
		ensureDisabled(vnfPackage);
		ensureNotInUse(vnfPackage);
		vnfPackageRepository.delete(vnfPkgUuid);
		return ResponseEntity.noContent().build();
	}

	/**
	 * Update information about an individual VNF package.
	 *
	 * \&quot;The PATCH method updates the information of a VNF package.\&quot;
	 * \&quot;This method shall follow the provisions specified in the Tables
	 * 9.4.3.3.4-1 and 9.4.3.3.4-2 for URI query parameters, request and response
	 * data structures, and response codes.\&quot;
	 *
	 */
	@Override
	public ResponseEntity<VnfPkgInfo> vnfPackagesVnfPkgIdPatch(final String vnfPkgId, final String body, final String contentType) {
		final UUID vnfPkgUuid = UUID.fromString(vnfPkgId);
		final VnfPackage vnfPackage = vnfPackageRepository.get(vnfPkgUuid);
		patcher.patch(body, vnfPackage);
		vnfPackageRepository.save(vnfPackage);

		final VnfPkgInfo vnfPkgInfo = mapper.map(vnfPackage, VnfPkgInfo.class);
		vnfPkgInfo.setLinks(links.getVnfLinks(vnfPkgUuid.toString()));
		// On change Notification
		eventManager.sendNotification(NotificationEvent.VNF_PKG_ONCHANGE, vnfPkgUuid);
		return new ResponseEntity<>(vnfPkgInfo, HttpStatus.OK);
	}

	/**
	 * Upload a VNF package by providing the content of the VNF package.
	 *
	 * The PUT method uploads the content of a VNF package. This method shall follow
	 * the provisions specified in the Tables 9.4.5.3.3-1 and 9.4.5.3.3-2 for URI
	 * query parameters, request and response data structures, and response codes.
	 *
	 */
	@Override
	public ResponseEntity<Void> vnfPackagesVnfPkgIdPackageContentPut(final String vnfPkgId, final String accept, final MultipartFile file) {
		final UUID vnfPkgUuid = UUID.fromString(vnfPkgId);
		final VnfPackage vnfPackage = vnfPackageRepository.get(vnfPkgUuid);
		ensureNotOnboarded(vnfPackage);
		final Map<String, Object> parameters = new HashMap<>();
		try {
			parameters.put("data", file.getBytes());
		} catch (final IOException e) {
			throw new GenericException(e);
		}
		eventManager.sendAction(ActionType.VNF_PKG_ONBOARD_FROM_BYTES, vnfPkgUuid, parameters);
		return ResponseEntity.accepted().build();
	}

	/**
	 * Upload a VNF package by providing the address information of the VNF package.
	 *
	 * The POST method provides the information for the NFVO to get the content of a
	 * VNF package. This method shall follow the provisions specified in the Tables
	 * 9.4.6.3.1-1 and 9.4.6.3.1-2 for URI query parameters, request and response
	 * data structures, and response codes.
	 *
	 */
	@Override
	public ResponseEntity<Void> vnfPackagesVnfPkgIdPackageContentUploadFromUriPost(final String accept, final String contentType, final String vnfPkgId, final UploadVnfPkgFromUriRequest contentUploadFromUriPostRequest) {
		final UUID vnfPkgUuid = UUID.fromString(vnfPkgId);
		final VnfPackage vnfPackage = vnfPackageRepository.get(vnfPkgUuid);
		ensureNotOnboarded(vnfPackage);

		final Map<String, String> uddList = contentUploadFromUriPostRequest.getUserDefinedData();
		final Map<String, Object> parameters = new HashMap<>();
		parameters.put("url", uddList.get("url"));
		eventManager.sendAction(ActionType.VNF_PKG_ONBOARD_FROM_URI, vnfPkgUuid, parameters);

		return ResponseEntity.noContent().build();
	}

}
