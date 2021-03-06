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
package com.ubiqube.etsi.mano.service.vim;

import java.util.Map;

import javax.annotation.Nonnull;

import com.ubiqube.etsi.mano.dao.mano.IpPool;
import com.ubiqube.etsi.mano.dao.mano.L3Data;
import com.ubiqube.etsi.mano.dao.mano.VlProtocolData;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public interface Network {
	String createNetwork(final VlProtocolData vl, String name, String dnsDomain, String qosPolicyId);

	String createSubnet(final L3Data l3ProtocolData, final IpPool ipAllocationPool, final String networkId);

	void deleteSubnet(final String resourceId);

	void deleteVirtualLink(String resourceId);

	String createRouter(final String name, final String internalNetworkId, final String externalNetworkId);

	void deleteRouter(String resourceId);

	@Nonnull
	Map<String, String> getPublicNetworks();

}
