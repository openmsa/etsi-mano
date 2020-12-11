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

import javax.annotation.security.RolesAllowed;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.LccnSubscriptionRequest;

@RolesAllowed({ "ROLE_NFVO" })
@RestController
public class VnfLcmSubscriptionsSol003Api implements VnfLcmSubscriptionsSol003 {

	@Override
	public ResponseEntity<Void> subscriptionsGet() {
		throw new GenericException("TODO");
	}

	@Override
	public ResponseEntity<Void> subscriptionsPost(final LccnSubscriptionRequest lccnSubscriptionRequest, final String contentType) {
		throw new GenericException("TODO");
	}

	@Override
	public ResponseEntity<Void> subscriptionsSubscriptionIdDelete(final String subscriptionId) {
		throw new GenericException("TODO");
	}

	@Override
	public ResponseEntity<Void> subscriptionsSubscriptionIdGet(final String subscriptionId) {
		throw new GenericException("TODO");
	}
}
