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

package com.ubiqube.etsi.mano.vnfm.v261.controller.nslcm;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.common.v261.controller.lcm.LcmLinkable;
import com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfInstanceLinks;
import com.ubiqube.etsi.mano.controller.lcmgrant.VnfInstanceLcm;
import com.ubiqube.etsi.mano.dao.mano.CancelModeTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.json.MapperForView;
import com.ubiqube.etsi.mano.model.VnfInstantiate;
import com.ubiqube.etsi.mano.service.VnfInstanceService;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.CreateVnfRequest;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.InstantiateVnfRequest;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.TerminateVnfRequest;

import ma.glasnost.orika.MapperFacade;

@RestController
public class VnfLcmSol002Api implements VnfLcmSol002 {
	private static final Logger LOG = LoggerFactory.getLogger(VnfLcmSol002Api.class);
	@Nonnull
	private final LcmLinkable links = new Sol002LcmLinkable();
	private final VnfInstanceService vnfInstancesService;
	private final VnfInstanceLcm vnfInstanceLcm;
	private final MapperFacade mapper;

	public VnfLcmSol002Api(final VnfInstanceService _vnfInstancesRepository, final VnfInstanceLcm _vnfInstanceLcm, final MapperFacade _mapper) {
		vnfInstancesService = _vnfInstancesRepository;
		vnfInstanceLcm = _vnfInstanceLcm;
		mapper = _mapper;
		LOG.info("Starting Ns Instance SOL002 Controller.");
	}

	@Override
	public ResponseEntity<String> vnfInstancesGet(final Map<String, String> queryParameters) {
		final List<com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfInstance> result = vnfInstanceLcm.get(queryParameters).stream()
				.map(x -> {
					final com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfInstance v = mapper.map(x, com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfInstance.class);
					v.setLinks(links.getLinks(x.getId().toString()));
					return v;
				})
				.collect(Collectors.toList());
		final String exclude = queryParameters.get("exclude_fields");
		final String fields = queryParameters.get("fields");

		final ObjectMapper localMapper = MapperForView.getMapperForView(exclude, fields, null, null);
		try {
			return new ResponseEntity<>(localMapper.writeValueAsString(result), HttpStatus.OK);
		} catch (final JsonProcessingException e) {
			throw new GenericException(e);
		}
	}

	@Override
	public ResponseEntity<com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfInstance> vnfInstancesPost(final CreateVnfRequest createVnfRequest) {
		final VnfInstance vnfInstance = vnfInstanceLcm.post(createVnfRequest.getVnfdId(), createVnfRequest.getVnfInstanceName(), createVnfRequest.getVnfInstanceDescription());
		final com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfInstance inst = mapper.map(vnfInstance, com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfInstance.class);
		inst.setLinks(links.getLinks(vnfInstance.getId().toString()));
		return ResponseEntity.accepted().body(inst);
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdChangeExtConnPost(final String vnfInstanceId) {
		throw new GenericException("TODO");
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdChangeFlavourPost(final String vnfInstanceId) {
		throw new GenericException("TODO");
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdDelete(final String vnfInstanceId) {
		vnfInstanceLcm.delete(UUID.fromString(vnfInstanceId));
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfInstance> vnfInstancesVnfInstanceIdGet(final String vnfInstanceId) {
		final VnfInstance vnfInstanceDb = vnfInstancesService.findById(UUID.fromString(vnfInstanceId));
		final com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfInstance vnfInstance = mapper.map(vnfInstanceDb, com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfInstance.class);
		vnfInstance.setLinks(links.getLinks(vnfInstanceId));
		return new ResponseEntity<>(vnfInstance, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdHealPost(final String vnfInstanceId) {
		throw new GenericException("TODO");
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdInstantiatePost(final String vnfInstanceId, final InstantiateVnfRequest instantiateVnfRequest) {
		final VnfInstantiate req = mapper.map(instantiateVnfRequest, VnfInstantiate.class);
		final VnfBlueprint lcm = vnfInstanceLcm.instantiate(UUID.fromString(vnfInstanceId), req);
		final VnfInstanceLinks link = links.getLinks(lcm.getId().toString());
		return ResponseEntity.accepted().header("Location", link.getSelf().getHref()).build();
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdOperatePost(final String vnfInstanceId) {

		throw new GenericException("TODO");
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdPatch(final String vnfInstanceId) {
		throw new GenericException("TODO");
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdScalePost(final String vnfInstanceId) {
		throw new GenericException("TODO");
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdScaleToLevelPost(final String vnfInstanceId) {
		throw new GenericException("TODO");
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdTerminatePost(final String vnfInstanceId, final TerminateVnfRequest terminateVnfRequest) {
		final VnfBlueprint lcm = vnfInstanceLcm.terminate(UUID.fromString(vnfInstanceId), CancelModeTypeEnum.fromValue(terminateVnfRequest.toString()), terminateVnfRequest.getGracefulTerminationTimeout());
		final VnfInstanceLinks link = links.getLinks(lcm.getId().toString());
		return ResponseEntity.noContent().header("Location", link.getSelf().getHref()).build();
	}
}
