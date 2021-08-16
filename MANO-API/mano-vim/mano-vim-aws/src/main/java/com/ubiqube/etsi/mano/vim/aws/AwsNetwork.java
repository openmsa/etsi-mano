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
package com.ubiqube.etsi.mano.vim.aws;

import java.util.Map;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.CreateSubnetRequest;
import com.amazonaws.services.ec2.model.CreateVpcRequest;
import com.amazonaws.services.ec2.model.DeleteSubnetRequest;
import com.amazonaws.services.ec2.model.DeleteVpcRequest;
import com.ubiqube.etsi.mano.dao.mano.IpPool;
import com.ubiqube.etsi.mano.dao.mano.L3Data;
import com.ubiqube.etsi.mano.dao.mano.VlProtocolData;
import com.ubiqube.etsi.mano.service.vim.Network;

public class AwsNetwork implements Network {
	private final AmazonEC2 client;
	private final AWSStaticCredentialsProvider credential;

	public AwsNetwork(final AmazonEC2 ec2Client, final AWSStaticCredentialsProvider credential) {
		super();
		this.client = ec2Client;
		this.credential = credential;
	}

	@Override
	public String createNetwork(final VlProtocolData vl, final String name, final String dnsDomain, final String qosPolicyId) {
		// CreateDefaultVpcRequest
		// CreateVpcRequest
		final CreateVpcRequest createVpcRequest = new CreateVpcRequest()
				.withCidrBlock(vl.getL3ProtocolData().getCidr())
				.withRequestCredentialsProvider(credential);

		return client.createVpc(createVpcRequest).getVpc().getVpcId();
	}

	@Override
	public String createSubnet(final L3Data l3ProtocolData, final IpPool ipAllocationPool, final String networkId) {
		// CreateDefaultSubnetRequest
		final CreateSubnetRequest req = new CreateSubnetRequest()
				.withVpcId(networkId)
				.withRequestCredentialsProvider(credential);
		if (l3ProtocolData.getIpVersion().equals("IPV4")) {
			req.withCidrBlock(l3ProtocolData.getCidr());
		} else {
			req.withIpv6CidrBlock(l3ProtocolData.getCidr());
		}
		return client.createSubnet(req).getSubnet().getSubnetId();
	}

	@Override
	public void deleteSubnet(final String resourceId) {
		final DeleteSubnetRequest req = new DeleteSubnetRequest()
				.withSubnetId(resourceId)
				.withRequestCredentialsProvider(credential);
		client.deleteSubnet(req);
	}

	@Override
	public void deleteVirtualLink(final String resourceId) {
		final DeleteVpcRequest req = new DeleteVpcRequest()
				.withVpcId(resourceId)
				.withRequestCredentialsProvider(credential);
		client.deleteVpc(req);
	}

	@Override
	public String createRouter(final String name, final String internalNetworkId, final String externalNetworkId) {
		// CreateInternetGatewayRequest
		// CreateLocalGatewayRouteRequest
		// CreateNatGatewayRequest
		return null;
	}

	@Override
	public void deleteRouter(final String resourceId) {
		// TODO Auto-generated method stub
		// client.deleteInternetGateway(deleteInternetGatewayRequest);
	}

	@Override
	public Map<String, String> getPublicNetworks() {
		// DescribeVpcsRequest
		return null;
	}

	@Override
	public String createPort(final String name, final String networkId, final String deviceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePort(final String uuid) {
		// TODO Auto-generated method stub

	}
}
