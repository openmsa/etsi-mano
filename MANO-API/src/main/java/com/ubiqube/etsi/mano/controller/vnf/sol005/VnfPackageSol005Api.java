package com.ubiqube.etsi.mano.controller.vnf.sol005;

import static com.ubiqube.etsi.mano.Constants.ensureDisabled;
import static com.ubiqube.etsi.mano.Constants.ensureNotInUse;
import static com.ubiqube.etsi.mano.Constants.ensureNotOnboarded;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ubiqube.api.exception.ServiceException;
import com.ubiqube.api.interfaces.device.DeviceService;
import com.ubiqube.etsi.mano.controller.vnf.Linkable;
import com.ubiqube.etsi.mano.controller.vnf.VnfPackageManagement;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.exception.BadRequestException;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.factory.VnfPackageFactory;
import com.ubiqube.etsi.mano.model.vnf.PackageOnboardingStateType;
import com.ubiqube.etsi.mano.model.vnf.PackageOperationalStateType;
import com.ubiqube.etsi.mano.model.vnf.sol005.CreateVnfPkgInfoRequest;
import com.ubiqube.etsi.mano.model.vnf.sol005.UploadVnfPkgFromUriRequest;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;
import com.ubiqube.etsi.mano.service.ManufacturerModel;
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
	private final ManufacturerModel manufacturerModel;
	private final DeviceService deviceService;
	private final VnfPackageRepository vnfPackageRepository;
	private final Patcher patcher;
	private final EventManager eventManager;
	private final MapperFacade mapper;

	public VnfPackageSol005Api(final VnfPackageManagement _vnfManagement, final Patcher _patcher, final VnfPackageRepository _vnfPackageRepository, final ManufacturerModel _manufacturerModel, final DeviceService _deviceService, final EventManager _eventManager, final MapperFacade _mapper) {
		vnfManagement = _vnfManagement;
		manufacturerModel = _manufacturerModel;
		deviceService = _deviceService;
		patcher = _patcher;
		vnfPackageRepository = _vnfPackageRepository;
		eventManager = _eventManager;
		mapper = _mapper;
		LOG.info("Starting VNF Package SOL005 Controller.");
	}

	@Override
	public ResponseEntity<String> vnfPackagesGet(final Map<String, String> requestParams) throws ServiceException {
		final String resp = vnfManagement.vnfPackagesGet(requestParams, links);
		return ResponseEntity.ok(resp);
	}

	@Override
	public ResponseEntity<List<ResourceRegion>> vnfPackagesVnfPkgIdArtifactsArtifactPathGet(final String vnfPkgId, final HttpServletRequest request, final String accept, final String range) throws ServiceException {
		final String artifactPath = SpringUtils.extractParams(request);
		return vnfManagement.vnfPackagesVnfPkgIdArtifactsArtifactPathGet(vnfPkgId, artifactPath, range);
	}

	@Override
	// TODO: Same as SOL003 ?
	public ResponseEntity<VnfPkgInfo> vnfPackagesVnfPkgIdGet(final String vnfPkgId, final String accept) {
		final VnfPkgInfo vnfPkgInfo = vnfManagement.vnfPackagesVnfPkgIdGet(vnfPkgId, links);
		return ResponseEntity.ok(vnfPkgInfo);
	}

	@Override
	public ResponseEntity<List<ResourceRegion>> vnfPackagesVnfPkgIdPackageContentGet(final String vnfPkgId, final String accept, final String range) {
		return vnfManagement.vnfPackagesVnfPkgIdPackageContentGet(vnfPkgId, range);
	}

	@Override
	public ResponseEntity<Resource> vnfPackagesVnfPkgIdVnfdGet(final String vnfPkgId, final String accept) {
		return vnfManagement.vnfPackagesVnfPkgIdVnfdGet(vnfPkgId, accept);
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

		checkUserData(userData);
		final Object heatDoc = userData.remove("heat");
		vnfPackage = vnfPackageRepository.save(vnfPackage);
		final String vnfPkgId = vnfPackage.getId().toString();
		if (null != heatDoc) {
			vnfPackageRepository.storeObject(vnfPkgId, "vnfd", heatDoc);
			vnfPackage.setOnboardingState(PackageOnboardingStateType.ONBOARDED);
			vnfPackage.setOperationalState(PackageOperationalStateType.ENABLED);
			vnfPackageRepository.save(vnfPackage);
			eventManager.sendNotification(NotificationEvent.VNF_PKG_ONBOARDING, vnfPkgId);
		}
		final VnfPkgInfo vnfPkgInfo = mapper.map(vnfPackage, VnfPkgInfo.class);
		vnfPkgInfo.setLinks(links.getVnfLinks(vnfPkgId));

		return new ResponseEntity<>(vnfPkgInfo, HttpStatus.CREATED);
	}

	private void checkUserData(final Map<String, String> userData) {
		Assert.notNull(userData.get("customerId"), "customerId could not be null.");
		// Not in camel case ???
		Assert.notNull(userData.get("device_login"), "device_login could not be null.");
		Assert.notNull(userData.get("device_password"), "device_password could not be null.");
		final String vimId = userData.get("vimId");
		if (null == vimId) {
			throw new BadRequestException("vimId could not be null");
		}
		try {
			deviceService.getDeviceId(vimId);
		} catch (final ServiceException e) {
			throw new BadRequestException("vimId is not found in MSA.", e);
		}
		final String manufacturerId = userData.get("manufacturerId");
		Assert.notNull(manufacturerId, "manufacturerId could not be null.");
		final String modelId = userData.get("modelId");
		Assert.notNull(modelId, "modelId could not be null.");
		// Probably not the best place to do that.
		final String manufacturer = manufacturerModel.getManufacturerById(manufacturerId);
		final String model = manufacturerModel.getModelById(manufacturerId, modelId);
		userData.put("manufacturer", manufacturer);
		userData.put("model", model);
	}

	/**
	 * Delete an individual VNF package.
	 *
	 * The DELETE method deletes an individual VNF Package resource.
	 *
	 */
	@Override
	public ResponseEntity<Void> vnfPackagesVnfPkgIdDelete(final String vnfPkgId) {
		final VnfPackage vnfPackage = vnfPackageRepository.get(vnfPkgId);
		ensureDisabled(vnfPackage);
		ensureNotInUse(vnfPackage);
		vnfPackageRepository.delete(vnfPkgId);
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
		final VnfPackage vnfPackage = vnfPackageRepository.get(vnfPkgId);
		patcher.patch(body, vnfPackage);
		vnfPackageRepository.save(vnfPackage);

		final VnfPkgInfo vnfPkgInfo = mapper.map(vnfPackage, VnfPkgInfo.class);
		vnfPkgInfo.setLinks(links.getVnfLinks(vnfPkgId));
		// On change Notification
		eventManager.sendNotification(NotificationEvent.VNF_PKG_ONCHANGE, vnfPkgId);
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
		final VnfPackage vnfPackage = vnfPackageRepository.get(vnfPkgId);
		ensureNotOnboarded(vnfPackage);
		final Map<String, Object> parameters = new HashMap<>();
		try {
			parameters.put("data", file.getBytes());
		} catch (final IOException e) {
			throw new GenericException(e);
		}
		eventManager.sendAction(ActionType.VNF_PKG_ONBOARD_FROM_BYTES, vnfPkgId, parameters);
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
		final VnfPackage vnfPackage = vnfPackageRepository.get(vnfPkgId);
		ensureNotOnboarded(vnfPackage);

		final Map<String, Object> uddList = contentUploadFromUriPostRequest.getUserDefinedData();
		final Map<String, Object> parameters = new HashMap<>();
		parameters.put("url", uddList.get("url"));
		eventManager.sendAction(ActionType.VNF_PKG_ONBOARD_FROM_URI, vnfPkgId, parameters);

		return ResponseEntity.noContent().build();
	}

}
