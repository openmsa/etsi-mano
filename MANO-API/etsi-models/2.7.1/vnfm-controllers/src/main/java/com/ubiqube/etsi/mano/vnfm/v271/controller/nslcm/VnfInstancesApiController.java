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
package com.ubiqube.etsi.mano.vnfm.v271.controller.nslcm;

import static com.ubiqube.etsi.mano.Constants.VNF_SEARCH_DEFAULT_EXCLUDE_FIELDS;
import static com.ubiqube.etsi.mano.Constants.VNF_SEARCH_MANDATORY_FIELDS;
import static com.ubiqube.etsi.mano.Constants.ensureInstantiated;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

import javax.annotation.Nonnull;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;

import com.ubiqube.etsi.mano.controller.lcmgrant.VnfInstanceLcm;
import com.ubiqube.etsi.mano.dao.mano.CancelModeTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.VnfLiveInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.model.VnfInstantiate;
import com.ubiqube.etsi.mano.model.VnfOperateRequest;
import com.ubiqube.etsi.mano.model.VnfScaleRequest;
import com.ubiqube.etsi.mano.model.VnfScaleToLevelRequest;
import com.ubiqube.etsi.mano.model.v271.sol005.nslcm.ChangeExtVnfConnectivityRequest;
import com.ubiqube.etsi.mano.model.v271.sol005.nslcm.ChangeVnfFlavourRequest;
import com.ubiqube.etsi.mano.model.v271.sol005.nslcm.CreateVnfRequest;
import com.ubiqube.etsi.mano.model.v271.sol005.nslcm.HealVnfRequest;
import com.ubiqube.etsi.mano.model.v271.sol005.nslcm.InstantiateVnfRequest;
import com.ubiqube.etsi.mano.model.v271.sol005.nslcm.OperateVnfRequest;
import com.ubiqube.etsi.mano.model.v271.sol005.nslcm.ScaleVnfRequest;
import com.ubiqube.etsi.mano.model.v271.sol005.nslcm.ScaleVnfToLevelRequest;
import com.ubiqube.etsi.mano.model.v271.sol005.nslcm.TerminateVnfRequest;
import com.ubiqube.etsi.mano.model.v271.sol005.nslcm.VirtualStorageResourceInfo;
import com.ubiqube.etsi.mano.model.v271.sol005.nslcm.VnfExtCpInfo;
import com.ubiqube.etsi.mano.model.v271.sol005.nslcm.VnfInfoModificationRequest;
import com.ubiqube.etsi.mano.model.v271.sol005.nslcm.VnfInstance;
import com.ubiqube.etsi.mano.model.v271.sol005.nslcm.VnfInstanceInstantiatedVnfInfo;
import com.ubiqube.etsi.mano.model.v271.sol005.nslcm.VnfVirtualLinkResourceInfo;
import com.ubiqube.etsi.mano.model.v271.sol005.nslcm.VnfcResourceInfo;
import com.ubiqube.etsi.mano.service.ManoSearchResponseService;
import com.ubiqube.etsi.mano.service.VnfInstanceService;
import com.ubiqube.etsi.mano.service.VnfPackageService;

import ma.glasnost.orika.MapperFacade;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Controller
public class VnfInstancesApiController implements VnfInstancesApi {
	private static final String LOCATION = "Location";
	private static final Logger LOG = LoggerFactory.getLogger(VnfInstancesApiController.class);
	@Nonnull
	private final LcmLinkable links = new Sol003LcmLinkable();
	private final VnfInstanceService vnfInstancesService;
	private final VnfInstanceLcm vnfInstanceLcm;
	private final MapperFacade mapper;
	private final VnfInstanceService vnfInstanceService;
	private final VnfPackageService vnfPackageService;
	private final ManoSearchResponseService searchService;

	public VnfInstancesApiController(final VnfInstanceService vnfInstancesService, final VnfInstanceLcm vnfInstanceLcm, final MapperFacade mapper, final VnfInstanceService vnfInstanceService, final VnfPackageService vnfPackageService, final ManoSearchResponseService _searchService) {
		super();
		this.vnfInstancesService = vnfInstancesService;
		this.vnfInstanceLcm = vnfInstanceLcm;
		this.mapper = mapper;
		this.vnfInstanceService = vnfInstanceService;
		this.vnfPackageService = vnfPackageService;
		searchService = _searchService;
	}

	@Override
	public ResponseEntity<String> vnfInstancesGet(final MultiValueMap<String, String> requestParams) {
		final List<com.ubiqube.etsi.mano.dao.mano.VnfInstance> result = vnfInstanceLcm.get(requestParams);
		final Consumer<VnfInstance> setLink = x -> x.setLinks(links.getLinks(x.getId()));
		return searchService.search(requestParams, VnfInstance.class, VNF_SEARCH_DEFAULT_EXCLUDE_FIELDS, VNF_SEARCH_MANDATORY_FIELDS, result, VnfInstance.class, setLink);
	}

	@Override
	public ResponseEntity<VnfInstance> vnfInstancesPost(@Valid final CreateVnfRequest createVnfRequest) {
		final com.ubiqube.etsi.mano.dao.mano.VnfInstance vnfInstance = vnfInstanceLcm.post(createVnfRequest.getVnfdId(), createVnfRequest.getVnfInstanceName(), createVnfRequest.getVnfInstanceDescription());
		final VnfInstance inst = mapper.map(vnfInstance, VnfInstance.class);
		inst.setLinks(links.getLinks(vnfInstance.getId().toString()));
		return ResponseEntity.accepted().body(inst);
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdChangeExtConnPost(final String vnfInstanceId, @Valid final ChangeExtVnfConnectivityRequest changeExtVnfConnectivityRequest) {
		final com.ubiqube.etsi.mano.dao.mano.VnfInstance vnfInstance = vnfInstancesService.findById(UUID.fromString(vnfInstanceId));
		ensureInstantiated(vnfInstance);
		throw new GenericException("TODO");
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdChangeFlavourPost(final String vnfInstanceId, @Valid final ChangeVnfFlavourRequest changeVnfFlavourRequest) {
		final com.ubiqube.etsi.mano.dao.mano.VnfInstance vnfInstance = vnfInstancesService.findById(UUID.fromString(vnfInstanceId));
		ensureInstantiated(vnfInstance);
		throw new GenericException("TODO");
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdDelete(final String vnfInstanceId) {
		vnfInstanceLcm.delete(UUID.fromString(vnfInstanceId));
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<VnfInstance> vnfInstancesVnfInstanceIdGet(final String vnfInstanceId) {
		final com.ubiqube.etsi.mano.dao.mano.VnfInstance vnfInstanceDb = vnfInstancesService.findById(UUID.fromString(vnfInstanceId));
		final VnfInstance vnfInstance = mapper.map(vnfInstanceDb, VnfInstance.class);

		final VnfPackage vnfPackage = vnfPackageService.findByVnfdId(UUID.fromString(vnfInstance.getVnfdId()));
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

		vnfInstance.setLinks(links.getLinks(vnfInstanceId));
		return new ResponseEntity<>(vnfInstance, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdHealPost(final String vnfInstanceId, @Valid final HealVnfRequest healVnfRequest) {
		final com.ubiqube.etsi.mano.dao.mano.VnfInstance vnfInstance = vnfInstancesService.findById(UUID.fromString(vnfInstanceId));
		ensureInstantiated(vnfInstance);
		throw new GenericException("TODO");
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdInstantiatePost(final String vnfInstanceId, @Valid final InstantiateVnfRequest instantiateVnfRequest) {
		final VnfInstantiate req = mapper.map(instantiateVnfRequest, VnfInstantiate.class);
		final VnfBlueprint lcm = vnfInstanceLcm.instantiate(UUID.fromString(vnfInstanceId), req);
		final String link = VnfLcmOpOccsApiController.getSelfLink(lcm.getId().toString());
		return ResponseEntity.accepted().header(LOCATION, link).build();
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdOperatePost(final String vnfInstanceId, @Valid final OperateVnfRequest operateVnfRequest) {
		final VnfOperateRequest req = mapper.map(operateVnfRequest, VnfOperateRequest.class);
		final VnfBlueprint lcm = vnfInstanceLcm.operate(UUID.fromString(vnfInstanceId), req);
		final String link = VnfLcmOpOccsApiController.getSelfLink(lcm.getId().toString());
		return ResponseEntity.accepted().header(LOCATION, link).build();
	}

	@Override
	public ResponseEntity<VnfInstance> vnfInstancesVnfInstanceIdPatch(final String vnfInstanceId, @Valid final VnfInfoModificationRequest vnfInfoModificationRequest) {
		final com.ubiqube.etsi.mano.dao.mano.VnfInstance vnfInstance = vnfInstancesService.findById(UUID.fromString(vnfInstanceId));
		throw new GenericException("TODO");
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdScalePost(final String vnfInstanceId, @Valid final ScaleVnfRequest scaleVnfRequest) {
		final VnfScaleRequest req = mapper.map(scaleVnfRequest, VnfScaleRequest.class);
		final VnfBlueprint lcm = vnfInstanceLcm.scale(UUID.fromString(vnfInstanceId), req);
		final String link = VnfLcmOpOccsApiController.getSelfLink(lcm.getId().toString());
		return ResponseEntity.noContent().header(LOCATION, link).build();
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdScaleToLevelPost(final String vnfInstanceId, @Valid final ScaleVnfToLevelRequest scaleVnfToLevelRequest) {
		final VnfScaleToLevelRequest req = mapper.map(scaleVnfToLevelRequest, VnfScaleToLevelRequest.class);
		final VnfBlueprint lcm = vnfInstanceLcm.scaleToLevel(UUID.fromString(vnfInstanceId), req);
		final String link = VnfLcmOpOccsApiController.getSelfLink(lcm.getId().toString());
		return ResponseEntity.noContent().header(LOCATION, link).build();
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdTerminatePost(final String vnfInstanceId, @Valid final TerminateVnfRequest terminateVnfRequest) {
		final VnfBlueprint lcm = vnfInstanceLcm.terminate(UUID.fromString(vnfInstanceId), CancelModeTypeEnum.fromValue(terminateVnfRequest.toString()), terminateVnfRequest.getGracefulTerminationTimeout());
		final String link = VnfLcmOpOccsApiController.getSelfLink(lcm.getId().toString());
		return ResponseEntity.noContent().header(LOCATION, link).build();
	}

	public static String getSelfLink(final String id) {
		return linkTo(methodOn(VnfLcmOpOccsApi.class).vnfLcmOpOccsVnfLcmOpOccIdGet(id)).withSelfRel().getHref();
	}
}
