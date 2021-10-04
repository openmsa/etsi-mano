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
package com.ubiqube.etsi.mano.vnfm.controller.vnflcm;

import static com.ubiqube.etsi.mano.Constants.VNFLCM_SEARCH_DEFAULT_EXCLUDE_FIELDS;
import static com.ubiqube.etsi.mano.Constants.VNFLCM_SEARCH_MANDATORY_FIELDS;
import static com.ubiqube.etsi.mano.Constants.ensureInstantiated;

import java.net.URI;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.ubiqube.etsi.mano.controller.vnflcm.VnfInstanceLcm;
import com.ubiqube.etsi.mano.dao.mano.CancelModeTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.dao.mano.vnfi.ChangeExtVnfConnRequest;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.model.VnfInstantiate;
import com.ubiqube.etsi.mano.model.VnfOperateRequest;
import com.ubiqube.etsi.mano.model.VnfScaleRequest;
import com.ubiqube.etsi.mano.model.VnfScaleToLevelRequest;
import com.ubiqube.etsi.mano.service.VnfPackageService;
import com.ubiqube.etsi.mano.vnfm.fc.vnflcm.VnfInstanceGenericFrontController;
import com.ubiqube.etsi.mano.vnfm.service.VnfInstanceService;
import com.ubiqube.etsi.mano.vnfm.service.VnfInstanceServiceVnfm;

import ma.glasnost.orika.MapperFacade;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class VnfInstanceGenericFrontControllerImpl implements VnfInstanceGenericFrontController {
	private static final String LOCATION = "Location";

	private final VnfPackageService vnfPackageService;

	private final VnfInstanceLcm vnfInstanceLcm;

	// XXX Duplicate service.
	private final VnfInstanceService vnfInstancesService;

	private final MapperFacade mapper;

	private final VnfInstanceServiceVnfm vnfInstanceServiceVnfm;

	public VnfInstanceGenericFrontControllerImpl(final VnfPackageService vnfPackageService, final VnfInstanceLcm vnfInstanceLcm, final VnfInstanceService vnfInstancesService,
			final MapperFacade mapper, final VnfInstanceServiceVnfm vnfInstanceServiceVnfm) {
		super();
		this.vnfPackageService = vnfPackageService;
		this.vnfInstanceLcm = vnfInstanceLcm;
		this.vnfInstancesService = vnfInstancesService;
		this.mapper = mapper;
		this.vnfInstanceServiceVnfm = vnfInstanceServiceVnfm;
	}

	@Override
	public ResponseEntity<Void> terminate(final UUID vnfInstanceId, final CancelModeTypeEnum cancelMode, final int timeout, final Function<VnfBlueprint, String> getSelfLink) {
		final VnfBlueprint lcm = vnfInstanceLcm.terminate(vnfInstanceId, cancelMode, timeout);
		final String link = getSelfLink.apply(lcm);
		return ResponseEntity.accepted().header(LOCATION, link).build();
	}

	@Override
	public <U> ResponseEntity<Void> scaleToLevel(final UUID vnfInstanceId, final U body, final Function<VnfBlueprint, String> getSelfLink) {
		final VnfScaleToLevelRequest req = mapper.map(body, VnfScaleToLevelRequest.class);
		final VnfBlueprint lcm = vnfInstanceLcm.scaleToLevel(vnfInstanceId, req);
		final String link = getSelfLink.apply(lcm);
		return ResponseEntity.accepted().header(LOCATION, link).build();
	}

	@Override
	public <U> ResponseEntity<Void> scale(final UUID vnfInstanceId, final U body, final Function<VnfBlueprint, String> getSelfLink) {
		final VnfScaleRequest req = mapper.map(body, VnfScaleRequest.class);
		final VnfBlueprint lcm = vnfInstanceLcm.scale(vnfInstanceId, req);
		final String link = getSelfLink.apply(lcm);
		return ResponseEntity.accepted().header(LOCATION, link).build();
	}

	@Override
	public <U> ResponseEntity<Void> snapshot(final UUID vnfInstanceId, final U body) {
		final VnfInstance vnfInstance = vnfInstanceServiceVnfm.findById(vnfInstanceId);
		ensureInstantiated(vnfInstance);
		throw new GenericException("TODO");
	}

	@Override
	public <V> ResponseEntity<V> modify(final UUID vnfInstanceId, final String body, final String ifMatch, final Function<VnfInstance, String> getSelfLink) {
		VnfInstance vnfInstance = vnfInstanceServiceVnfm.findById(vnfInstanceId);
		vnfInstance = vnfInstancesService.vnfLcmPatch(vnfInstance, body, ifMatch);
		final String link = getSelfLink.apply(vnfInstance);
		return ResponseEntity.accepted().header(LOCATION, link).build();
	}

	@Override
	public <U> ResponseEntity<Void> operate(final UUID vnfInstanceId, final U body, final Function<VnfBlueprint, String> getSelfLink) {
		final VnfOperateRequest req = mapper.map(body, VnfOperateRequest.class);
		final VnfBlueprint lcm = vnfInstanceLcm.operate(vnfInstanceId, req);
		final String link = getSelfLink.apply(lcm);
		return ResponseEntity.accepted().header(LOCATION, link).build();
	}

	@Override
	public <U> ResponseEntity<Void> instantiate(final UUID vnfInstanceId, final U body, final Function<VnfBlueprint, String> getSelfLink) {
		final VnfInstantiate req = mapper.map(body, VnfInstantiate.class);
		final VnfBlueprint lcm = vnfInstanceLcm.instantiate(vnfInstanceId, req);
		final String link = getSelfLink.apply(lcm);
		return ResponseEntity.accepted().header(LOCATION, link).build();
	}

	@Override
	public ResponseEntity<Void> heal(final UUID vnfInstanceId, final String cause, final Map<String, String> hashMap) {
		final VnfInstance vnfInstance = vnfInstanceServiceVnfm.findById(vnfInstanceId);
		ensureInstantiated(vnfInstance);
		throw new GenericException("TODO");
	}

	@Override
	public <U> ResponseEntity<U> findById(final UUID vnfInstanceId, final Class<U> clazz, final Consumer<U> makeLink, final String instanceSelfLink) {
		final VnfInstance vnfInstanceDb = vnfInstanceServiceVnfm.findById(vnfInstanceId);
		final U vnfInstance = mapper.map(vnfInstanceDb, clazz);

		final VnfPackage vnfPackage = vnfPackageService.findById(vnfInstanceDb.getVnfPkg().getId());
		mapper.map(vnfPackage, vnfInstance);
		makeLink.accept(vnfInstance);
		return ResponseEntity.created(URI.create(instanceSelfLink)).body(vnfInstance);
	}

	@Override
	public ResponseEntity<Void> deleteById(final UUID vnfInstanceId) {
		vnfInstanceLcm.delete(vnfInstanceId);
		return ResponseEntity.noContent().build();
	}

	@Override
	public <U> ResponseEntity<Void> createSnapshot(final UUID vnfInstanceId, final U object, final Function<VnfBlueprint, String> getSelfLink) {
		final VnfInstance vnfInstance = vnfInstanceServiceVnfm.findById(vnfInstanceId);
		ensureInstantiated(vnfInstance);
		throw new GenericException("TODO");
	}

	@Override
	public <U> ResponseEntity<Void> changeVnfPkg(final UUID vnfInstanceId, final U object, final Function<VnfBlueprint, String> getSelfLink) {
		final VnfInstance vnfInstance = vnfInstanceServiceVnfm.findById(vnfInstanceId);
		ensureInstantiated(vnfInstance);
		throw new GenericException("TODO");
	}

	@Override
	public <U> ResponseEntity<Void> changeFlavour(final UUID vnfInstanceId, final U object, final Function<VnfBlueprint, String> getSelfLink) {
		final VnfInstance vnfInstance = vnfInstanceServiceVnfm.findById(vnfInstanceId);
		ensureInstantiated(vnfInstance);
		throw new GenericException("TODO");
	}

	@Override
	public <U> ResponseEntity<Void> changeExtConn(final UUID vnfInstanceId, final U object, final Function<VnfBlueprint, String> getSelfLink) {
		final VnfInstance vnfInstance = vnfInstanceServiceVnfm.findById(vnfInstanceId);
		ensureInstantiated(vnfInstance);
		final ChangeExtVnfConnRequest cevcr = mapper.map(object, ChangeExtVnfConnRequest.class);
		final VnfBlueprint lcm = vnfInstanceLcm.changeExtConn(vnfInstanceId, cevcr);
		final String link = getSelfLink.apply(lcm);
		return ResponseEntity.accepted().header(LOCATION, link).build();
	}

	@Override
	public <U> ResponseEntity<U> create(final String vnfdId, final String vnfInstanceName, final String vnfInstanceDescription, final Class<U> clazz, final Consumer<U> makeLink, final String selfLink) {
		final VnfInstance vnfInstance = vnfInstanceLcm.post(vnfdId, vnfInstanceName, vnfInstanceDescription);
		final U inst = mapper.map(vnfInstance, clazz);
		makeLink.accept(inst);
		return ResponseEntity.created(URI.create(selfLink)).body(inst);
	}

	@Override
	public <U> ResponseEntity<String> search(final MultiValueMap<String, String> requestParams, final Class<U> clazz, final String nextpageOpaqueMarker, final Consumer<U> makeLink) {
		return vnfInstancesService.search(requestParams, clazz, VNFLCM_SEARCH_DEFAULT_EXCLUDE_FIELDS, VNFLCM_SEARCH_MANDATORY_FIELDS, makeLink);
	}

}
