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

import java.util.UUID;

import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.common.ApiVersionType;
import com.ubiqube.etsi.mano.service.HttpGateway;

/**
 *
 * @author olivier.vignaud@gmail.com
 *
 */
public class ManoOnboardedVnfPackage {
	private final ManoClient client;

	public ManoOnboardedVnfPackage(final ManoClient manoClient, final UUID id) {
		this.client = manoClient;
		client.setQueryType(ApiVersionType.SOL003_VNFPKGM);
		client.setFragment("/onboarded_vnf_packages/{id}");
		client.setObjectId(id);
	}

	public VnfPackage find() {
		return client.createQuery()
				.setWireOutClass(HttpGateway::getVnfPackageClass)
				.setOutClass(VnfPackage.class)
				.getSingle();
	}

}
