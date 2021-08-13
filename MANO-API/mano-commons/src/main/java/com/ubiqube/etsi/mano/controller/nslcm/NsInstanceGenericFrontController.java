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
package com.ubiqube.etsi.mano.controller.nslcm;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.dao.mano.dto.CreateNsInstance;
import com.ubiqube.etsi.mano.dao.mano.dto.nsi.NsInstanceDto;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsBlueprint;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.model.NsInstantiate;

import ma.glasnost.orika.MapperFacade;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class NsInstanceGenericFrontController {
	private static final String LOCATION = "Location";

	private static final Logger LOG = LoggerFactory.getLogger(NsInstanceGenericFrontController.class);

	private static final Set<String> NSI_SEARCH_MANDATORY_FIELDS = new HashSet<>(Arrays.asList("id", "nsInstanceDescription", "nsdId", "nsdInfoId", "nsState", "nsInstanceName"));

	private static final String NSI_SEARCH_DEFAULT_EXCLUDE_FIELDS = "vnfInstances,pnfInfo,virtualLinkInfo,vnffgInfo,sapInfo,,nsScaleStatus,additionalAffinityOrAntiAffinityRules";

	private final MapperFacade mapper;

	private final NsInstanceControllerService nsInstanceControllerService;

	private final NsInstanceController nsLcmController;

	public NsInstanceGenericFrontController(final MapperFacade _mapper, final NsInstanceControllerService _nsInstanceControllerService, final NsInstanceController _nsLcmController) {
		mapper = _mapper;
		nsInstanceControllerService = _nsInstanceControllerService;
		nsLcmController = _nsLcmController;
		LOG.debug("Starting Ns Instance SOL005 Controller.");
	}

	public <U> ResponseEntity<String> search(final MultiValueMap<String, String> requestParams, final Class<U> clazz, @Valid final String nextpageOpaqueMarker, final Consumer<U> makeLink) {
		return nsInstanceControllerService.search(requestParams, clazz, NSI_SEARCH_DEFAULT_EXCLUDE_FIELDS, NSI_SEARCH_MANDATORY_FIELDS, makeLink);
	}

	public ResponseEntity<Void> delete(final String nsInstanceId) {
		final UUID nsInstanceUuid = UUID.fromString(nsInstanceId);
		nsLcmController.nsInstancesNsInstanceIdDelete(nsInstanceUuid);
		return ResponseEntity.noContent().build();
	}

	public <U> ResponseEntity<U> findById(final String nsInstanceId, final Class<U> clazz, final Consumer<U> makeLink) {
		final UUID nsInstanceUuid = UUID.fromString(nsInstanceId);
		final NsInstanceDto nsInstanceDb = nsLcmController.nsInstancesNsInstanceIdGet(nsInstanceUuid);
		final U nsInstance = mapper.map(nsInstanceDb, clazz);
		makeLink.accept(nsInstance);
		return new ResponseEntity<>(nsInstance, HttpStatus.OK);
	}

	public <U> ResponseEntity<U> heal(final String nsInstanceId, final Object request) {
		final UUID nsInstanceUuid = UUID.fromString(nsInstanceId);
		final NsInstanceDto nsInstanceDb = nsLcmController.nsInstancesNsInstanceIdGet(nsInstanceUuid);
		throw new GenericException("TODO");
	}

	public <U> ResponseEntity<U> instantiate(final String nsInstanceId, final Object request, final Function<NsBlueprint, String> getSelfLink) {
		final UUID nsInstanceUuid = UUID.fromString(nsInstanceId);
		final NsInstantiate nsInst = mapper.map(request, NsInstantiate.class);
		final NsBlueprint nsLcm = nsInstanceControllerService.instantiate(nsInstanceUuid, nsInst);
		final String link = getSelfLink.apply(nsLcm);
		return ResponseEntity.accepted().header(LOCATION, link).build();
	}

	public <U> ResponseEntity<U> scale(final String nsInstanceId, final Object request) {
		final UUID nsInstanceUuid = UUID.fromString(nsInstanceId);
		final NsdInstance nsInstanceDb = nsLcmController.nsInstancesNsInstanceIdScalePost(nsInstanceUuid);
		throw new GenericException("TODO");
	}

	public <U> ResponseEntity<U> terminate(final String nsInstanceId, final Object request, final Function<NsBlueprint, String> getSelfLink) {
		final UUID nsInstanceUuid = UUID.fromString(nsInstanceId);
		final NsBlueprint lcm = this.nsInstanceControllerService.terminate(nsInstanceUuid, OffsetDateTime.now());

		final String link = getSelfLink.apply(lcm);
		return ResponseEntity.accepted().header(LOCATION, link).build();
	}

	public <U> ResponseEntity<U> update(final String nsInstanceId, final Object request) {
		final UUID nsInstanceUuid = UUID.fromString(nsInstanceId);
		nsLcmController.nsInstancesNsInstanceIdUpdatePost(nsInstanceUuid);
		throw new GenericException("TODO");
	}

	public <U> ResponseEntity<U> create(final Object request, final Class<U> clazz, final Consumer<U> makeLink, final Function<U, String> getSelfLink) {
		final CreateNsInstance req = mapper.map(request, CreateNsInstance.class);
		if (req.getNsdId() == null) {
			throw new NotFoundException("NsdId field is empty.");
		}
		final NsdInstance nsInstance = nsInstanceControllerService.createNsd(req.getNsdId(), req.getNsName(), req.getNsDescription());
		final U nsInstanceWeb = mapper.map(nsInstance, clazz);
		makeLink.accept(nsInstanceWeb);
		final String location = getSelfLink.apply(nsInstanceWeb);
		return ResponseEntity.created(URI.create(location)).body(nsInstanceWeb);
	}
}
