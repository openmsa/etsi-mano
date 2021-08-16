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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.AvailabilityZone;
import com.amazonaws.services.ec2.model.CapacityReservationInstancePlatform;
import com.amazonaws.services.ec2.model.CreateCapacityReservationRequest;
import com.amazonaws.services.ec2.model.DescribeAvailabilityZonesRequest;
import com.amazonaws.services.ec2.model.InstanceNetworkInterfaceSpecification;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.StartInstancesRequest;
import com.amazonaws.services.ec2.model.StopInstancesRequest;
import com.amazonaws.services.ec2.model.TerminateInstancesRequest;
import com.ubiqube.etsi.mano.dao.mano.GrantInformationExt;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.service.sys.ServerGroup;
import com.ubiqube.etsi.mano.service.vim.Dns;
import com.ubiqube.etsi.mano.service.vim.Network;
import com.ubiqube.etsi.mano.service.vim.PhysResources;
import com.ubiqube.etsi.mano.service.vim.ResourceQuota;
import com.ubiqube.etsi.mano.service.vim.Storage;
import com.ubiqube.etsi.mano.service.vim.Vim;
import com.ubiqube.etsi.mano.service.vim.VimCapability;
import com.ubiqube.etsi.mano.service.vim.mon.VimMonitoring;

public class AwsVim implements Vim {

	private static AmazonEC2 getEc2Client() {
		return AmazonEC2ClientBuilder.defaultClient();
	}

	private static AWSStaticCredentialsProvider getCredential(final VimConnectionInformation vci) {
		return new AWSStaticCredentialsProvider(new BasicAWSCredentials(vci.getAccessInfo().get("access_key_id"), vci.getAccessInfo().get("secret_key_id")));
	}

	@Override
	public void allocateResources(final VimConnectionInformation vci, final GrantInformationExt x) {
		final CreateCapacityReservationRequest createCapacityReservationRequest = new CreateCapacityReservationRequest()
				.withInstanceCount(1)
				.withInstancePlatform(CapacityReservationInstancePlatform.LinuxUNIX);
		// getConnection(vimConnectionInformation).createCapacityReservation(createCapacityReservationRequest).getCapacityReservation().;
	}

	@Override
	public void freeResources(final VimConnectionInformation vci, final GrantInformationExt x) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getType() {
		return "AWS";
	}

	@Override
	public String getOrCreateFlavor(final VimConnectionInformation vci, final String name, final int numVcpu, final long virtualMemorySize, final long disk) {
		// TODO Auto-generated method stub
		return "t1.micro";
	}

	@Override
	public String createCompute(final VimConnectionInformation vci, final String instanceName, final String flavorId, final String imageId, final List<String> networks, final List<String> storages, final String cloudInitData) {
		final Collection<InstanceNetworkInterfaceSpecification> ni = new ArrayList<>();
		final RunInstancesRequest runInstancesRequest = new RunInstancesRequest();
		runInstancesRequest.withInstanceType(flavorId)
				.withUserData(cloudInitData)
				.withNetworkInterfaces(ni)
				.withImageId(imageId)
				.withRequestCredentialsProvider(getCredential(vci));
		networks.forEach(x -> ni.add(new InstanceNetworkInterfaceSpecification().withSubnetId(x)));
		return getEc2Client().runInstances(runInstancesRequest).getReservation().getInstances().get(0).getInstanceId();
	}

	@Override
	public List<String> getZoneAvailableList(final VimConnectionInformation vci) {
		final DescribeAvailabilityZonesRequest req = new DescribeAvailabilityZonesRequest()
				.withRequestCredentialsProvider(getCredential(vci));
		return getEc2Client().describeAvailabilityZones(req).getAvailabilityZones().stream().map(AvailabilityZone::getZoneId).collect(Collectors.toList());
	}

	@Override
	public void deleteCompute(final VimConnectionInformation vci, final String resourceId) {
		final TerminateInstancesRequest terminateInstancesRequest = new TerminateInstancesRequest(Arrays.asList(resourceId))
				.withRequestCredentialsProvider(getCredential(vci));
		getEc2Client().terminateInstances(terminateInstancesRequest);
	}

	@Override
	public List<ServerGroup> getServerGroup(final VimConnectionInformation vci) {
		return new ArrayList<>();
	}

	@Override
	public void startServer(final VimConnectionInformation vci, final String resourceId) {
		final StartInstancesRequest req = new StartInstancesRequest()
				.withInstanceIds(resourceId)
				.withRequestCredentialsProvider(getCredential(vci));
		getEc2Client().startInstances(req);
	}

	@Override
	public void stopServer(final VimConnectionInformation vci, final String resourceId) {
		final StopInstancesRequest req = new StopInstancesRequest()
				.withInstanceIds(resourceId)
				.withRequestCredentialsProvider(getCredential(vci));
		getEc2Client().stopInstances(req);
	}

	@Override
	public ResourceQuota getQuota(final VimConnectionInformation vci) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VimCapability> getCaps(final VimConnectionInformation vci) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VimMonitoring getMonitoring(final VimConnectionInformation vci) {
		return new AwsMonitoring(getCredential(vci));
	}

	@Override
	public Network network(final VimConnectionInformation vci) {
		return new AwsNetwork(getEc2Client(), getCredential(vci));
	}

	@Override
	public Storage storage(final VimConnectionInformation vci) {
		return new AwsStorage(getEc2Client(), getCredential(vci));
	}

	@Override
	public Dns dns(final VimConnectionInformation vci) {
		return new AwsDns(getCredential(vci));
	}

	@Override
	public PhysResources getPhysicalResources(final VimConnectionInformation vimConnectionInformation) {
		// TODO Auto-generated method stub
		return null;
	}

}
