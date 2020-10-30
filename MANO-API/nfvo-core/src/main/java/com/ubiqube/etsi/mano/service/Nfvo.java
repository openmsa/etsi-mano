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
package com.ubiqube.etsi.mano.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.controller.lcmgrant.GrantManagement;
import com.ubiqube.etsi.mano.dao.mano.GrantResponse;
import com.ubiqube.etsi.mano.dao.mano.dto.GrantsRequest;
import com.ubiqube.etsi.mano.exception.GenericException;

import ma.glasnost.orika.MapperFacade;

/**
 * Communication layer between VNFM -> NFVO from a NFVM point of view.
 *
 * @author ovi@ubiqube.com
 *
 */
@Service
public class Nfvo {

	private static final Logger LOG = LoggerFactory.getLogger(Nfvo.class);

	private final MapperFacade mapper;

	private final GrantManagement grantManagement;

	public Nfvo(final MapperFacade _mapper, final GrantManagement _grantManagement) {
		mapper = _mapper;
		grantManagement = _grantManagement;
	}

	public GrantResponse sendSyncGrantRequest(final GrantsRequest req) {
		// final GrantsRequest finalReq = mapper.map(req, GrantsRequest.class);
		return sendAndWaitGrantRequest(req);
	}

	private GrantResponse sendAndWaitGrantRequest(final GrantsRequest grantRequest) {
		final GrantResponse grants = grantManagement.post(grantRequest);
		return pollGrants(grants);
	}

	private GrantResponse pollGrants(final GrantResponse grants) {
		int counter = 50;
		while (counter > 0) {
			final GrantResponse grantOpt = grantManagement.get(grants.getId());
			if (Boolean.TRUE.equals(grantOpt.getAvailable())) {
				return grantOpt;
			}
			LOG.debug("Grant ID {} not ready.", grants.getId());
			counter--;
			try {
				Thread.sleep(5 * 1000L);
			} catch (final InterruptedException e) {
				Thread.currentThread().interrupt();
				throw new GenericException(e);
			}
		}
		throw new GenericException("Unable to get grant ID " + grants.getId());
	}
}
