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

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.AttachInterfaceType;
import org.openstack4j.model.network.IPVersionType;
import org.openstack4j.model.network.Network;
import org.openstack4j.model.network.NetworkType;
import org.openstack4j.model.network.Port;
import org.openstack4j.model.network.Router;
import org.openstack4j.model.network.Subnet;
import org.openstack4j.model.network.builder.NetworkBuilder;
import org.openstack4j.model.network.builder.SubnetBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.etsi.mano.dao.mano.IpPool;
import com.ubiqube.etsi.mano.dao.mano.L2Data;
import com.ubiqube.etsi.mano.dao.mano.L3Data;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VlProtocolData;

public class OsNetwork implements com.ubiqube.etsi.mano.service.vim.Network {
	private static final Logger LOG = LoggerFactory.getLogger(OsNetwork.class);

	private final OSClientV3 os;

	private final VimConnectionInformation vimConnectionInformation;

	public OsNetwork(final OSClientV3 os, final VimConnectionInformation vimConnectionInformation) {
		this.os = os;
		this.vimConnectionInformation = vimConnectionInformation;
	}

	@Override
	public String createNetwork(final VlProtocolData vl, final String name, final String dnsDomain, final String qosPolicyId) {
		final L2Data l2 = vl.getL2ProtocolData();
		final NetworkBuilder bNet = Builders.network().tenantId(vimConnectionInformation.getAccessInfo().get("projectId"));
		bNet.name(name);
		Optional.ofNullable(l2.getMtu()).ifPresent(bNet::mtu);
		Optional.ofNullable(l2.getNetworkType()).ifPresent(x2 -> bNet.networkType(NetworkType.valueOf(x2.toUpperCase())));
		// Don't know how to use vlan_transarent.
		// Optional.ofNullable(l2.getVlanTransparent()).ifPresent(x ->
		// bNet.vlanTransparent(x.booleanValue()));
		Optional.ofNullable(dnsDomain).ifPresent(bNet::dnsDomain);
		Optional.ofNullable(qosPolicyId).ifPresent(bNet::qosPolicyId);
		final Network network = os.networking().network().create(bNet.adminStateUp(true).build());
		LOG.debug("Network created: {} = {}", network.getId(), network.getStatus());
		return network.getId();
	}

	@Override
	public String createSubnet(final L3Data l3ProtocolData, final IpPool ipAllocationPool, final String networkId) {
		final SubnetBuilder bSub = Builders.subnet()
				.name(l3ProtocolData.getL3Name())
				.addPool(ipAllocationPool.getStartIpAddress(), ipAllocationPool.getEndIpAddress())
				.cidr(l3ProtocolData.getCidr())
				.enableDHCP(l3ProtocolData.isDhcpEnabled())
				.gateway(l3ProtocolData.getGatewayIp())
				.tenantId(vimConnectionInformation.getAccessInfo().get("projectId"))
				.ipVersion(convertIpVersion(l3ProtocolData.getIpVersion()))
				.networkId(networkId);
		final Subnet res = os.networking().subnet().create(bSub.build());
		LOG.debug("SubNetwork created: {}", res.getId());
		return res.getId();
	}

	private static IPVersionType convertIpVersion(final String ipVersion) {
		if ("ipv6".equals(ipVersion)) {
			return IPVersionType.valueOf("V6");
		}
		return IPVersionType.valueOf("V4");
	}

	@Override
	public void deleteSubnet(final String resourceId) {
		checkResult(os.networking().subnet().delete(resourceId));
	}

	@Override
	public void deleteVirtualLink(final String resourceId) {
		checkResult(os.networking().network().delete(resourceId));
	}

	@Override
	public String createRouter(final String name, final String internalNetworkId, final String externalNetworkId) {
		final Network net = os.networking().network().get(internalNetworkId);
		// XXX get first one ?
		final String psubnetId = net.getNeutronSubnets().get(0).getId();
		final Router routerBuilder = Builders.router()
				.name(name)
				.externalGateway(externalNetworkId)
				.build();
		final Router router = os.networking().router().create(routerBuilder);
		os.networking().router().attachInterface(router.getId(), AttachInterfaceType.SUBNET, psubnetId);
		return router.getId();
	}

	@Override
	public void deleteRouter(final String resourceId) {
		final List<? extends Port> routerList = os.networking().port().list();
		routerList.stream()
				.filter(x -> x.getDeviceId().equals(resourceId))
				.filter(x -> !x.getDeviceOwner().equals("network:router_gateway"))
				.map(Port::getId)
				.forEach(x -> os.networking().router().detachInterface(resourceId, null, x));
		checkResult(os.networking().router().delete(resourceId));
	}

	@Override
	public Map<String, String> getPublicNetworks() {
		return os.networking().network().list().stream().filter(Network::isRouterExternal)
				.collect(Collectors.toMap(Network::getName, Network::getId));

	}

	private static void checkResult(final ActionResponse action) {
		if (!action.isSuccess() && (action.getCode() != 404)) {
			throw new VimException(action.getCode() + " " + action.getFault());
		}
	}
}
