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
package com.ubiqube.etsi.mec.mepm.v211.controller.lcm;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.ubiqube.etsi.mec.meo.v211.model.lcm.Body;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.InlineResponse201;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.SubscriptionLinkList;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.SubscriptionType;

/**
 * Broken.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Controller
public class LcmSubscriptionsMepm211ApiController implements LcmSubscriptionsMepm211Api {

	@Override
	public ResponseEntity<SubscriptionLinkList> appLcmSubscriptionsGET(@Valid final String subscriptionType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<InlineResponse201> appLcmSubscriptionsPOST(@Valid final Body body, @NotNull @Valid final SubscriptionType subscriptionType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> individualSubscriptionDELETE(final String subscriptionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<InlineResponse201> individualSubscriptionGET(final String subscriptionId, @NotNull @Valid final String subscriptionType) {
		// TODO Auto-generated method stub
		return null;
	}

}
