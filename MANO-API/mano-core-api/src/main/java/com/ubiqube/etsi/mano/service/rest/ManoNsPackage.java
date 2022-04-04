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

import com.ubiqube.etsi.mano.dao.mano.NsdPackage;
import com.ubiqube.etsi.mano.dao.mano.common.ApiVersionType;
import com.ubiqube.etsi.mano.service.HttpGateway;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class ManoNsPackage {

	private final ManoClient client;

	public ManoNsPackage(final ManoClient manoClient) {
		this.client = manoClient;
		client.setQueryType(ApiVersionType.SOL005_NSD);
		client.setFragment("/ns_descriptors");
	}

	public List<NsdPackage> list() {
		return client.createQuery()
				.setInClassList(HttpGateway::getNsdPackageClassList)
				.setOutClass(NsdPackage.class)
				.getList();
	}

	public NsdPackage create(final Map<String, Object> userDefinedData) {
		return client.createQuery(httpGateway -> httpGateway.createNsdPackageRequest(userDefinedData))
				.setWireOutClass(HttpGateway::getNsdPackageClass)
				.setOutClass(NsdPackage.class)
				.post();
	}

}
