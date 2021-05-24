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
package com.ubiqube.etsi.mano.controller.lcmgrant;

import java.net.URI;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.GrantResponse;
import com.ubiqube.etsi.mano.dao.mano.dto.VnfGrantsRequest;

import ma.glasnost.orika.MapperFacade;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class LcmGrantsFrontController {
	private final GrantManagement grantManagement;
	private final MapperFacade mapper;

	public LcmGrantsFrontController(final GrantManagement _grantManagement, final MapperFacade _mapper) {
		grantManagement = _grantManagement;
		mapper = _mapper;
	}

	public <U> ResponseEntity<U> grantsGrantIdGet(final String grantId, final Class<U> clazz, final Consumer<U> makeLink) {
		final GrantResponse grants = grantManagement.get(UUID.fromString(grantId));
		if (!grants.getAvailable().equals(Boolean.TRUE)) {
			return ResponseEntity.accepted().build();
		}
		final U jsonGrant = mapper.map(grants, clazz);
		makeLink.accept(jsonGrant);
		return ResponseEntity.ok(jsonGrant);
	}

	public <U> ResponseEntity<U> grantsPost(@Valid final Object grantRequest, final Class<U> clazz, final Function<U, String> getSelfLink) {
		final VnfGrantsRequest obj = mapper.map(grantRequest, VnfGrantsRequest.class);
		final GrantResponse resp = grantManagement.post(obj);
		final U res = mapper.map(resp, clazz);
		final URI location = URI.create(getSelfLink.apply(res));
		return ResponseEntity.created(location).build();
	}

}
