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
package com.ubiqube.etsi.mec.meo.service;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.GrantInterface;
import com.ubiqube.etsi.mano.dao.mano.GrantResponse;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.jpa.GrantsResponseJpa;
import com.ubiqube.etsi.mec.meo.event.MeoEventManager;
import com.ubiqube.etsi.mec.service.AppGrantManagement;

import ma.glasnost.orika.MapperFacade;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class MeoGrantManagement implements AppGrantManagement {

	private static final Logger LOG = LoggerFactory.getLogger(MeoGrantManagement.class);

	private final MapperFacade mapper;

	private final GrantsResponseJpa grantsResponseJpa;

	private final MeoEventManager eventManager;

	public MeoGrantManagement(final MapperFacade mapper, final GrantsResponseJpa grantsResponseJpa, final MeoEventManager eventManager) {
		super();
		this.mapper = mapper;
		this.grantsResponseJpa = grantsResponseJpa;
		this.eventManager = eventManager;
	}

	@Override
	public GrantResponse get(final UUID grantId) {
		final Optional<GrantResponse> grantOpt = grantsResponseJpa.findById(grantId);
		return grantOpt.orElseThrow(() -> new NotFoundException("Unable to find grant " + grantId));

	}

	@Override
	public GrantResponse post(final GrantInterface grantRequest) {
		final GrantResponse grants = mapper.map(grantRequest, GrantResponse.class);
		grants.setAvailable(Boolean.FALSE);
		final GrantResponse grantsDb = grantsResponseJpa.save(grants);
		LOG.debug("Sending grants {}", grantsDb.getId());
		eventManager.sendGrant(grantsDb.getId(), new HashMap<>());
		LOG.info("Grant request {} sent.", grantsDb.getId());
		return grantsDb;
	}

}
