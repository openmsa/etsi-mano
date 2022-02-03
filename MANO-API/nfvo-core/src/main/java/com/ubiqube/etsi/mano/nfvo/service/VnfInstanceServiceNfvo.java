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
package com.ubiqube.etsi.mano.nfvo.service;

import java.net.URI;
import java.util.Map;
import java.util.UUID;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.common.ApiVersionType;
import com.ubiqube.etsi.mano.service.HttpGateway;
import com.ubiqube.etsi.mano.service.ServerService;
import com.ubiqube.etsi.mano.service.VnfInstanceGatewayService;
import com.ubiqube.etsi.mano.service.VnfmService;
import com.ubiqube.etsi.mano.service.rest.ServerAdapter;

import ma.glasnost.orika.MapperFacade;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
@ConditionalOnMissingBean(VnfmService.class)
public class VnfInstanceServiceNfvo implements VnfInstanceGatewayService {

	private final ServerService serverService;

	private final MapperFacade mapper;

	public VnfInstanceServiceNfvo(final ServerService serverService, final MapperFacade mapper) {
		this.serverService = serverService;
		this.mapper = mapper;
	}

	@Override
	public VnfInstance findById(final UUID id) {
		final ServerAdapter server = serverService.findNearestServer();
		final URI uri = server.getUriFor(ApiVersionType.SOL003_VNFLCM, "vnf_instances/{id}", Map.of("id", id));
		final HttpGateway httpGateway = server.httpGateway();
		final Object resp = server.rest().get(uri, httpGateway.getVnfInstanceClass());
		return mapper.map(resp, VnfInstance.class);
	}

}
