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
package com.ubiqube.etsi.mano.service.rest;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.ubiqube.etsi.mano.dao.mano.Subscription;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.common.ApiVersionType;
import com.ubiqube.etsi.mano.service.HttpGateway;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class ManoVnfPackage {
	private final ManoClient client;

	public ManoVnfPackage(final ManoClient manoClient, final UUID id) {
		this.client = manoClient;
		client.setObjectId(id);
		client.setQueryType(ApiVersionType.SOL003_VNFPKGM);
		client.setFragment("/vnf_packages");
	}

	public ManoVnfPackage(final ManoClient manoClient) {
		this(manoClient, null);
	}

	public List<VnfPackage> list() {
		return client.createQuery()
				.setInClassList(HttpGateway::getVnfPackageClassList)
				.setOutClass(VnfPackage.class)
				.getList();
	}

	public Subscription subscribe(final Subscription subscription) {
		client.setFragment("/subscriptions");
		return client.createQuery()
				.setWireInClass(HttpGateway::getPkgmSubscriptionRequest)
				.setWireOutClass(HttpGateway::getVnfPackageSubscriptionClass)
				.setOutClass(Subscription.class)
				.post(subscription);
	}

	public VnfPackage create(final Map<String, String> userDefinedData) {
		return client.createQuery(httpGateway -> httpGateway.createVnfPackageRequest(userDefinedData))
				.setWireOutClass(HttpGateway::getVnfPackageClass)
				.setOutClass(VnfPackage.class)
				.post();
	}

}
