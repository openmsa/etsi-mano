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
package com.ubiqube.etsi.mano.nfvo.service.rest;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.ubiqube.etsi.mano.controller.vnflcm.VnfInstanceLcm;
import com.ubiqube.etsi.mano.dao.mano.CancelModeTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.common.ApiVersionType;
import com.ubiqube.etsi.mano.dao.mano.config.Servers;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.dao.mano.vnfi.ChangeExtVnfConnRequest;
import com.ubiqube.etsi.mano.model.VnfInstantiate;
import com.ubiqube.etsi.mano.model.VnfOperateRequest;
import com.ubiqube.etsi.mano.model.VnfScaleRequest;
import com.ubiqube.etsi.mano.model.VnfScaleToLevelRequest;
import com.ubiqube.etsi.mano.service.HttpGateway;
import com.ubiqube.etsi.mano.service.ServerService;
import com.ubiqube.etsi.mano.service.VnfmService;
import com.ubiqube.etsi.mano.service.rest.ServerAdapter;

import ma.glasnost.orika.MapperFacade;

/**
 * This class Is preparing querying a VNFM using HHTP.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@ConditionalOnMissingBean(VnfmService.class)
@Service
public class NfvoVnfInstanceLcm implements VnfInstanceLcm {
	private final ServerService serverService;
	private final MapperFacade mapper;

	public NfvoVnfInstanceLcm(final ServerService serverService, final MapperFacade mapper) {
		this.serverService = serverService;
		this.mapper = mapper;
	}

	@Override
	public List<VnfInstance> get(final Servers servers, final MultiValueMap<String, String> requestParams) {
		final ServerAdapter server = serverService.findNearestServer();
		final URI uri = server.getUriFor(ApiVersionType.SOL003_VNFFM, "vnf_instances", Map.of());
		final HttpGateway httpGateway = server.httpGateway();
		final ParameterizedTypeReference<List<?>> clazz = httpGateway.getVnfInstanceListParam();
		final List<?> resp = server.rest().get(uri, clazz);
		return resp.stream().map(x -> mapper.map(x, VnfInstance.class)).toList();
	}

	@Override
	public VnfInstance post(final Servers servers, final String vnfdId, final String vnfInstanceName, final String vnfInstanceDescription) {
		final ServerAdapter server = serverService.findNearestServer();
		final HttpGateway httpGateway = server.httpGateway();
		final Object req = httpGateway.createVnfInstanceRequest(vnfdId, vnfInstanceName, vnfInstanceDescription);
		final URI uri = server.getUriFor(ApiVersionType.SOL002_VNFLCM, "vnf_instances", Map.of());
		final Class<?> clazz = httpGateway.getVnfInstanceClass();
		final var res = server.rest().post(uri, req, clazz);
		return mapper.map(res, VnfInstance.class);
	}

	@Override
	public void delete(final Servers servers, final UUID vnfInstanceId) {
		final ServerAdapter server = serverService.findNearestServer();
		final Map<String, Object> uriVariables = Map.of("id", vnfInstanceId);
		final URI uri = server.getUriFor(ApiVersionType.SOL002_VNFLCM, "vnf_instances/{id}", uriVariables);
		server.rest().delete(uri, null);
	}

	@Override
	public VnfBlueprint instantiate(final Servers servers, final UUID vnfInstanceId, final VnfInstantiate instantiateVnfRequest) {
		final ServerAdapter server = serverService.findNearestServer();
		final HttpGateway httpGateway = server.httpGateway();
		final var req = mapper.map(instantiateVnfRequest, httpGateway.getVnfInstanceInstantiateRequestClass());
		final Map<String, Object> uriVariables = Map.of("id", vnfInstanceId);
		final URI uri = server.getUriFor(ApiVersionType.SOL002_VNFLCM, "vnf_instances/{id}/instantiate", uriVariables);
		final Class<?> respClass = httpGateway.getVnfLcmOpOccs();
		final var resp = server.rest().post(uri, req, respClass);
		return mapper.map(resp, VnfBlueprint.class);
	}

	@Override
	public VnfBlueprint terminate(final Servers servers, final UUID vnfInstanceId, final CancelModeTypeEnum terminationType, final Integer gracefulTerminationTimeout) {
		final ServerAdapter server = serverService.findNearestServer();
		final HttpGateway httpGateway = server.httpGateway();
		final var req = httpGateway.createVnfInstanceTerminate(terminationType, gracefulTerminationTimeout);
		final Map<String, Object> uriVariables = Map.of("id", vnfInstanceId);
		final URI uri = server.getUriFor(ApiVersionType.SOL002_VNFLCM, "vnf_instances/{id}/terminate", uriVariables);
		final Class<?> respClass = httpGateway.getVnfLcmOpOccs();
		final var resp = server.rest().post(uri, req, respClass);
		return mapper.map(resp, VnfBlueprint.class);
	}

	@Override
	public VnfBlueprint scaleToLevel(final Servers servers, final UUID vnfInstanceId, final VnfScaleToLevelRequest scaleVnfToLevelRequest) {
		final ServerAdapter server = serverService.findNearestServer();
		final HttpGateway httpGateway = server.httpGateway();
		final Class<?> reqClass = httpGateway.getVnfInstanceScaleToLevelRequest();
		final Map<String, Object> uriVariables = Map.of("id", vnfInstanceId);
		final URI uri = server.getUriFor(ApiVersionType.SOL002_VNFLCM, "vnf_instances/{id}/scale_to_level", uriVariables);
		final var req = mapper.map(scaleVnfToLevelRequest, reqClass);
		final Class<?> respClass = httpGateway.getVnfLcmOpOccs();
		final var resp = server.rest().post(uri, req, respClass);
		return mapper.map(resp, VnfBlueprint.class);
	}

	@Override
	public VnfBlueprint scale(final Servers servers, final UUID vnfInstanceId, final VnfScaleRequest scaleVnfRequest) {
		final ServerAdapter server = serverService.findNearestServer();
		final HttpGateway httpGateway = server.httpGateway();
		final Class<?> reqClass = httpGateway.getVnfInstanceScaleRequest();
		final Map<String, Object> uriVariables = Map.of("id", vnfInstanceId);
		final URI uri = server.getUriFor(ApiVersionType.SOL002_VNFLCM, "vnf_instances/{id}/scale", uriVariables);
		final var req = mapper.map(scaleVnfRequest, reqClass);
		final Class<?> respClass = httpGateway.getVnfLcmOpOccs();
		final var resp = server.rest().post(uri, req, respClass);
		return mapper.map(resp, VnfBlueprint.class);
	}

	@Override
	public VnfBlueprint operate(final Servers servers, final UUID vnfInstanceId, final VnfOperateRequest operateVnfRequest) {
		final ServerAdapter server = serverService.findNearestServer();
		final HttpGateway httpGateway = server.httpGateway();
		final Class<?> reqClass = httpGateway.getVnfInstanceOperateRequest();
		final Map<String, Object> uriVariables = Map.of("id", vnfInstanceId);
		final URI uri = server.getUriFor(ApiVersionType.SOL002_VNFLCM, "vnf_instances/{id}/operate", uriVariables);
		final var req = mapper.map(operateVnfRequest, reqClass);
		final Class<?> respClass = httpGateway.getVnfLcmOpOccs();
		final var resp = server.rest().post(uri, req, respClass);
		return mapper.map(resp, VnfBlueprint.class);
	}

	@Override
	public VnfBlueprint vnfLcmOpOccsGet(final Servers servers, @NotNull final UUID id) {
		final ServerAdapter server = serverService.findNearestServer();
		final HttpGateway httpGateway = server.httpGateway();
		final Map<String, Object> uriVariables = Map.of("id", id);
		final URI uri = server.getUriFor(ApiVersionType.SOL002_VNFLCM, "vnf_lcm_op_occs/{id}", uriVariables);
		final Class<?> respClass = httpGateway.getVnfLcmOpOccs();
		final var resp = server.rest().post(uri, null, respClass);
		return mapper.map(resp, VnfBlueprint.class);
	}

	@Override
	public VnfBlueprint changeExtConn(final Servers servers, @NotNull final UUID vnfInstanceId, final ChangeExtVnfConnRequest cevcr) {
		final ServerAdapter server = serverService.findNearestServer();
		final HttpGateway httpGateway = server.httpGateway();
		final Class<?> reqClass = httpGateway.getVnfInstanceChangeExtConnRequest();
		final Map<String, Object> uriVariables = Map.of("id", vnfInstanceId);
		final URI uri = server.getUriFor(ApiVersionType.SOL002_VNFLCM, "vnf_instances/{id}/change_ext_conn", uriVariables);
		final var req = mapper.map(cevcr, reqClass);
		final Class<?> respClass = httpGateway.getVnfLcmOpOccs();
		final var resp = server.rest().post(uri, req, respClass);
		return mapper.map(resp, VnfBlueprint.class);
	}

	@Override
	public VnfInstance findById(final Servers servers, final String vnfInstance) {
		final ServerAdapter server = serverService.findNearestServer();
		final HttpGateway httpGateway = server.httpGateway();
		final Map<String, Object> uriVariables = Map.of("id", vnfInstance);
		final URI uri = server.getUriFor(ApiVersionType.SOL002_VNFLCM, "vnf_instances/{id}", uriVariables);
		final Class<?> respClass = httpGateway.getVnfInstanceClass();
		final var resp = server.rest().post(uri, null, respClass);
		return mapper.map(resp, VnfInstance.class);
	}

}
