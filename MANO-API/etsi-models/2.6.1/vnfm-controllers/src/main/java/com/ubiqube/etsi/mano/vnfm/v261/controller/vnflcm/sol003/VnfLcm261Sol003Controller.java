/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.ubiqube.etsi.mano.vnfm.v261.controller.vnflcm.sol003;

import static com.ubiqube.etsi.mano.Constants.getSafeUUID;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.security.RolesAllowed;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.common.v261.model.Link;
import com.ubiqube.etsi.mano.common.v261.model.nslcm.VirtualStorageResourceInfo;
import com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfExtCpInfo;
import com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfInstanceInstantiatedVnfInfo;
import com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfInstanceLinks;
import com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfVirtualLinkResourceInfo;
import com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfcResourceInfo;
import com.ubiqube.etsi.mano.controller.nslcm.VnfInstanceGenericFrontController;
import com.ubiqube.etsi.mano.dao.mano.CancelModeTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfLiveInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.service.VnfInstanceService;
import com.ubiqube.etsi.mano.service.VnfPackageService;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.ChangeExtVnfConnectivityRequest;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.CreateVnfRequest;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.InstantiateVnfRequest;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.OperateVnfRequest;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.ScaleVnfRequest;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.ScaleVnfToLevelRequest;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.TerminateVnfRequest;

import ma.glasnost.orika.MapperFacade;

@RolesAllowed({ "ROLE_NFVO" })
@RestController
public class VnfLcm261Sol003Controller implements VnfLcm261Sol003Api {
	private static final Logger LOG = LoggerFactory.getLogger(VnfLcm261Sol003Controller.class);
	// XXX Duplicate service.
	private final VnfInstanceService vnfInstancesService;

	private final VnfInstanceService vnfInstanceService;

	private final MapperFacade mapper;

	private final VnfPackageService vnfPackageService;

	private final VnfInstanceGenericFrontController frontController;

	public VnfLcm261Sol003Controller(final VnfInstanceService _vnfInstancesRepository, final MapperFacade _mapper, final VnfInstanceService _vnfInstanceService, final VnfPackageService _vnfPackageService, final VnfInstanceGenericFrontController _frontController) {
		vnfInstancesService = _vnfInstancesRepository;
		mapper = _mapper;
		vnfInstanceService = _vnfInstanceService;
		vnfPackageService = _vnfPackageService;
		frontController = _frontController;
		LOG.debug("Starting Ns Instance SOL003 Controller.");
	}

	@Override
	public ResponseEntity<String> vnfInstancesGet(@Nonnull @RequestParam final MultiValueMap<String, String> requestParams) {
		return frontController.search(requestParams, com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfInstance.class, null, VnfLcm261Sol003Controller::makeLinks);
	}

	@Override
	public ResponseEntity<com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfInstance> vnfInstancesPost(final CreateVnfRequest request) {
		return frontController.create(request.getVnfdId(), request.getVnfInstanceName(), request.getVnfInstanceDescription(),
				com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfInstance.class, VnfLcm261Sol003Controller::makeLinks, "");
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdChangeExtConnPost(final String vnfInstanceId, final ChangeExtVnfConnectivityRequest changeExtVnfConnectivityRequest) {
		return frontController.changeExtConn(getSafeUUID(vnfInstanceId), changeExtVnfConnectivityRequest);
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdChangeFlavourPost(final String vnfInstanceId) {
		return frontController.changeFlavour(getSafeUUID(vnfInstanceId), vnfInstanceId);
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdDelete(final String vnfInstanceId) {
		return frontController.deleteById(getSafeUUID(vnfInstanceId));
	}

	@Override
	public ResponseEntity<com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfInstance> vnfInstancesVnfInstanceIdGet(final String vnfInstanceId) {
		final VnfInstance vnfInstanceDb = vnfInstancesService.findById(UUID.fromString(vnfInstanceId));
		final com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfInstance vnfInstance = mapper.map(vnfInstanceDb, com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfInstance.class);

		final VnfPackage vnfPackage = vnfPackageService.findById(UUID.fromString(vnfInstance.getVnfPkgId()));
		mapper.map(vnfPackage, vnfInstance);
		vnfInstance.setId(vnfInstanceDb.getId().toString());
		final VnfInstanceInstantiatedVnfInfo instantiatedVnfInfo = vnfInstance.getInstantiatedVnfInfo();

		final List<VnfLiveInstance> liveCompute = vnfInstanceService.getLiveComputeInstanceOf(vnfInstanceDb);
		final List<VnfcResourceInfo> vnfcResourceInfo = mapper.mapAsList(liveCompute, VnfcResourceInfo.class);
		instantiatedVnfInfo.setVnfcResourceInfo(vnfcResourceInfo);

		final List<VnfLiveInstance> liveExtCp = vnfInstanceService.getLiveExtCpInstanceOf(vnfInstanceDb);
		final List<VnfExtCpInfo> extCpInfo = mapper.mapAsList(liveExtCp, VnfExtCpInfo.class);
		instantiatedVnfInfo.setExtCpInfo(extCpInfo);

		final List<VnfLiveInstance> liveStorage = vnfInstanceService.getLiveStorageInstanceOf(vnfInstanceDb);
		final List<VirtualStorageResourceInfo> virtualStorageResourceInfo = mapper.mapAsList(liveStorage, VirtualStorageResourceInfo.class);
		instantiatedVnfInfo.setVirtualStorageResourceInfo(virtualStorageResourceInfo);

		final List<VnfLiveInstance> liveVirtualLink = vnfInstanceService.getLiveVirtualLinkInstanceOf(vnfInstanceDb);
		final List<VnfVirtualLinkResourceInfo> virtualLinkResourceInfo = mapper.mapAsList(liveVirtualLink, VnfVirtualLinkResourceInfo.class);
		instantiatedVnfInfo.setVirtualLinkResourceInfo(virtualLinkResourceInfo);

		makeLinks(vnfInstance);
		return ResponseEntity.ok().eTag("" + vnfInstanceDb.getVersion()).body(vnfInstance);
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdHealPost(final String vnfInstanceId) {
		return frontController.heal(getSafeUUID(vnfInstanceId), null, new HashMap<>());
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdInstantiatePost(final String vnfInstanceId, final InstantiateVnfRequest instantiateVnfRequest) {
		return frontController.instantiate(getSafeUUID(vnfInstanceId), instantiateVnfRequest, VnfLcm261Sol003Controller::getLcmLink);
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdOperatePost(final String vnfInstanceId, final OperateVnfRequest operateVnfRequest) {
		return frontController.operate(getSafeUUID(vnfInstanceId), operateVnfRequest, VnfLcm261Sol003Controller::getLcmLink);
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdPatch(final String vnfInstanceId, final String body, final String ifMatch) throws URISyntaxException {
		return frontController.modify(getSafeUUID(vnfInstanceId), body, ifMatch, VnfLcm261Sol003Controller::getInstanceLink);
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdScalePost(final String vnfInstanceId, final ScaleVnfRequest scaleVnfRequest) {
		return frontController.scale(getSafeUUID(vnfInstanceId), scaleVnfRequest, VnfLcm261Sol003Controller::getLcmLink);
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdScaleToLevelPost(final String vnfInstanceId, final ScaleVnfToLevelRequest scaleVnfToLevelRequest) {
		return frontController.scaleToLevel(getSafeUUID(vnfInstanceId), scaleVnfToLevelRequest, VnfLcm261Sol003Controller::getLcmLink);
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdTerminatePost(final String vnfInstanceId, final TerminateVnfRequest terminateVnfRequest) {
		return frontController.terminate(getSafeUUID(vnfInstanceId), CancelModeTypeEnum.fromValue(terminateVnfRequest.getTerminationType().toString()),
				terminateVnfRequest.getGracefulTerminationTimeout(), VnfLcm261Sol003Controller::getLcmLink);
	}

	public static String getSelfLink(final String id) {
		return linkTo(methodOn(VnfLcmOpOccs261Sol003Api.class).vnfLcmOpOccsVnfLcmOpOccIdGet(id)).withSelfRel().getHref();
	}

	private static String getLcmLink(final VnfBlueprint vnfblueprint) {
		return linkTo(methodOn(VnfLcmOpOccs261Sol003Controller.class).vnfLcmOpOccsVnfLcmOpOccIdGet(vnfblueprint.getId().toString())).withSelfRel().getHref();
	}

	private static String getInstanceLink(final com.ubiqube.etsi.mano.dao.mano.VnfInstance vnfInstance) {
		return linkTo(methodOn(VnfLcm261Sol003Controller.class).vnfInstancesVnfInstanceIdGet(vnfInstance.getId().toString())).withSelfRel().getHref();
	}

	private static void makeLinks(@NotNull final com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfInstance vnfInstance) {
		final String id = vnfInstance.getId();
		final String hrefScaleToLevel = linkTo(methodOn(VnfLcm261Sol003Api.class).vnfInstancesVnfInstanceIdScaleToLevelPost(id, null)).withSelfRel().getHref();
		final String hrefScale = linkTo(methodOn(VnfLcm261Sol003Api.class).vnfInstancesVnfInstanceIdScalePost(id, null)).withSelfRel().getHref();
		final String hrefOperate = linkTo(methodOn(VnfLcm261Sol003Api.class).vnfInstancesVnfInstanceIdOperatePost(id, null)).withSelfRel().getHref();
		final String hrefInstanciate = linkTo(methodOn(VnfLcm261Sol003Api.class).vnfInstancesVnfInstanceIdInstantiatePost(id, null)).withSelfRel().getHref();
		final String hrefIndicators = "";
		final String hrefHeal = linkTo(methodOn(VnfLcm261Sol003Api.class).vnfInstancesVnfInstanceIdHealPost(id)).withSelfRel().getHref();
		final String hrefChangeFlavor = linkTo(methodOn(VnfLcm261Sol003Api.class).vnfInstancesVnfInstanceIdChangeFlavourPost(id)).withSelfRel().getHref();
		final String hrefChangeExtConn = linkTo(methodOn(VnfLcm261Sol003Api.class).vnfInstancesVnfInstanceIdChangeExtConnPost(id, null)).withSelfRel().getHref();
		final String hrefSelf = linkTo(methodOn(VnfLcm261Sol003Api.class).vnfInstancesVnfInstanceIdGet(id)).withSelfRel().getHref();
		final String hrefTerminate = linkTo(methodOn(VnfLcm261Sol003Api.class).vnfInstancesVnfInstanceIdTerminatePost(id, null)).withSelfRel().getHref();
		final VnfInstanceLinks vnfInstanceLinks = new VnfInstanceLinks();
		final Link self = new Link();
		self.setHref(hrefSelf);
		vnfInstanceLinks.self(self);

		final Link changeExtConn = new Link();
		changeExtConn.setHref(hrefChangeExtConn);
		vnfInstanceLinks.setChangeExtConn(changeExtConn);

		final Link changeFlavour = new Link();
		changeFlavour.setHref(hrefChangeFlavor);
		vnfInstanceLinks.setChangeFlavour(changeFlavour);

		final Link heal = new Link();
		heal.setHref(hrefHeal);
		vnfInstanceLinks.setHeal(heal);

		final Link indicators = new Link();
		indicators.setHref(hrefIndicators);
		vnfInstanceLinks.setIndicators(indicators);

		final Link instantiate = new Link();
		instantiate.setHref(hrefInstanciate);
		vnfInstanceLinks.setInstantiate(instantiate);

		final Link operate = new Link();
		operate.setHref(hrefOperate);
		vnfInstanceLinks.setOperate(operate);

		final Link scale = new Link();
		scale.setHref(hrefScale);
		vnfInstanceLinks.setScale(scale);

		final Link terminate = new Link();
		terminate.setHref(hrefTerminate);
		vnfInstanceLinks.setTerminate(terminate);

		final Link scaleToLevel = new Link();
		scaleToLevel.setHref(hrefScaleToLevel);
		vnfInstanceLinks.setScaleToLevel(scaleToLevel);
		vnfInstance.setLinks(vnfInstanceLinks);

	}
}
