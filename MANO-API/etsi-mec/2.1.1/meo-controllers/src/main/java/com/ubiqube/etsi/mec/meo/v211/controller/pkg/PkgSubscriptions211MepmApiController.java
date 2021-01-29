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
package com.ubiqube.etsi.mec.meo.v211.controller.pkg;

import javax.validation.Valid;

import org.springframework.hateoas.server.EntityLinks;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.ubiqube.etsi.mano.controller.SubscriptionAbstractApi;
import com.ubiqube.etsi.mano.controller.vnf.VnfSubscriptionManagement;
import com.ubiqube.etsi.mano.dao.mano.subs.SubscriptionType;
import com.ubiqube.etsi.mec.meo.v211.model.pkg.AppPkgSubscription;
import com.ubiqube.etsi.mec.meo.v211.model.pkg.AppPkgSubscriptionInfo;

import ma.glasnost.orika.MapperFacade;

@Controller
public class PkgSubscriptions211MepmApiController extends SubscriptionAbstractApi<AppPkgSubscriptionInfo> implements PkgSubscriptions211MepmApi {

	public PkgSubscriptions211MepmApiController(final VnfSubscriptionManagement _vnfSubscriptionManagement, final MapperFacade _mapper, final EntityLinks _entityLinks) {
		super(_vnfSubscriptionManagement, _mapper, _entityLinks, AppPkgSubscriptionInfo.class, SubscriptionType.MEOPKG);
	}

	@Override
	public ResponseEntity<AppPkgSubscriptionInfo> subscriptionsPost(@Valid final AppPkgSubscription body) {
		return subscriptionPost(body);
	}

}
