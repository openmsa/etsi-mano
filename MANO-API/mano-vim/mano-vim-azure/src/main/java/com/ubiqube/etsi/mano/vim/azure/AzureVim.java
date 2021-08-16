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
package com.ubiqube.etsi.mano.vim.azure;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.azure.resourcemanager.AzureResourceManager;
import com.azure.resourcemanager.compute.models.AvailabilitySet;
import com.azure.resourcemanager.compute.models.VirtualMachine;
import com.azure.resourcemanager.compute.models.VirtualMachineSizeTypes;
import com.ubiqube.etsi.mano.dao.mano.GrantInformationExt;
import com.ubiqube.etsi.mano.dao.mano.IpPool;
import com.ubiqube.etsi.mano.dao.mano.L3Data;
import com.ubiqube.etsi.mano.dao.mano.SoftwareImage;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VlProtocolData;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.service.vim.Dns;
import com.ubiqube.etsi.mano.service.vim.Network;
import com.ubiqube.etsi.mano.service.vim.ResourceQuota;
import com.ubiqube.etsi.mano.service.vim.ServerGroup;
import com.ubiqube.etsi.mano.service.vim.Storage;
import com.ubiqube.etsi.mano.service.vim.Vim;
import com.ubiqube.etsi.mano.service.vim.VimCapability;
import com.ubiqube.etsi.mano.service.vim.VimImage;
import com.ubiqube.etsi.mano.service.vim.mon.VimMonitoring;

public class AzureVim implements Vim {
	private AzureResourceManager getConnection(final VimConnectionInformation vimConnectionInformation) {
		return null;
	}

	@Override
	public void allocateResources(final VimConnectionInformation vimConnectionInformation, final GrantInformationExt x) {
		// TODO Auto-generated method stub

	}

	@Override
	public void freeResources(final VimConnectionInformation vimConnectionInformation, final GrantInformationExt x) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getType() {
		return "AZURE";
	}

	@Override
	public VimImage getImagesInformations(final VimConnectionInformation vimConnectionInformation, final String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createNetwork(final VimConnectionInformation vimConnectionInformation, final VlProtocolData vl, final String name, final String dnsDomain, final String qosPolicyId) {
		final String region;
		final String groupName;
		getConnection(vimConnectionInformation).networks()
				.define(name)
				.withRegion(region)
				.withExistingResourceGroup(groupName)
				.withAddressSpace(groupName)
				.defineSubnet("")
				.withAddressPrefix("")
				.attach()
				.withDnsServer(groupName)
				.create();
		return null;
	}

	@Override
	public String createSubnet(final VimConnectionInformation vimConnectionInformation, final L3Data l3ProtocolData, final IpPool ipAllocationPool, final String networkId) {
		// Nothing.
		return null;
	}

	@Override
	public void deleteSubnet(final VimConnectionInformation vimConnectionInformation, final String resourceId) {
		getConnection(vimConnectionInformation).networks().deleteById(resourceId);
	}

	@Override
	public Optional<SoftwareImage> getSwImageMatching(final VimConnectionInformation vimConnectionInformation, final SoftwareImage img) {
		// getConnection(vimConnectionInformation).virtualMachineImages().getImage(null, getType(), getType(), getType(), getType())
		return null;
	}

	@Override
	public SoftwareImage uploadSoftwareImage(final VimConnectionInformation vimConnectionInformation, final SoftwareImage img) {
		// TODO Auto-generated method stub
		// getConnection(vimConnectionInformation).galleryImages().
		return null;
	}

	@Override
	public String getOrCreateFlavor(final VimConnectionInformation vimConnectionInformation, final String name, final int numVcpu, final long virtualMemorySize, final long disk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createStorage(final VimConnectionInformation vimConnectionInformation, final VnfStorage vnfStorage, final String aliasName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createCompute(final VimConnectionInformation vimConnectionInformation, final String instanceName, final String flavorId, final String imageId, final List<String> networks, final List<String> storages, final String cloudInitData) {
		// TODO Auto-generated method stub
		final VirtualMachine resp = getConnection(vimConnectionInformation).virtualMachines()
				.define(instanceName)
				.withRegion(cloudInitData)
				.withNewResourceGroup()
				.withExistingPrimaryNetwork(null)
				.withSubnet(instanceName)
				.withPrimaryPrivateIPAddressDynamic()
				.withoutPrimaryPublicIPAddress()
				.withSpecializedLinuxCustomImage(imageId)
				.withComputerName(instanceName)
				.withSize(VirtualMachineSizeTypes.BASIC_A0)
				.create();
		return resp.id();
	}

	@Override
	public String createObjectStorage(final VimConnectionInformation vimConnectionInformation, final VnfStorage vnfStorage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getZoneAvailableList(final VimConnectionInformation vimConnectionInformation) {
		return getConnection(vimConnectionInformation).availabilitySets().list().stream().map(AvailabilitySet::id).collect(Collectors.toList());
	}

	@Override
	public void deleteCompute(final VimConnectionInformation vimConnectionInformation, final String resourceId) {
		getConnection(vimConnectionInformation).virtualMachines().deleteById(resourceId);
	}

	@Override
	public void deleteVirtualLink(final VimConnectionInformation vimConnectionInformation, final String resourceId) {
		getConnection(vimConnectionInformation).networks().deleteById(resourceId);
	}

	@Override
	public void deleteStorage(final VimConnectionInformation vimConnectionInformation, final String resourceId) {
		// TODO
	}

	@Override
	public void deleteObjectStorage(final VimConnectionInformation vimConnectionInformation, final String resourceId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ServerGroup> getServerGroup(final VimConnectionInformation vimConnectionInformation) {
		return new ArrayList<>();
	}

	@Override
	public String createRouter(final VimConnectionInformation vimConnectionInformation, final String name, final String internalNetworkId, final String externalNetworkId) {
		// TODO Auto-generated method stub
		getConnection(vimConnectionInformation).
		return null;
	}

	@Override
	public void deleteRouter(final VimConnectionInformation vimConnectionInformation, final String resourceId) {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<String, String> getPublicNetworks(final VimConnectionInformation vimConnectionInformation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void startServer(final VimConnectionInformation vimConnectionInformation, final String resourceId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stopServer(final VimConnectionInformation vimConnectionInformation, final String resourceId) {
		// TODO Auto-generated method stub

	}

	@Override
	public ResourceQuota getQuota(final VimConnectionInformation vimConnectionInformation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createDnsZone(final VimConnectionInformation vimConnectionInformation, final String zoneName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteDnsZone(final VimConnectionInformation vimConnectionInformation, final String resourceId) {
		// TODO Auto-generated method stub

	}

	@Override
	public String createDnsRecordSet(final VimConnectionInformation vimConnectionInformation, final String zoneId, final String hostname, final String networkName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteDnsRecordSet(final VimConnectionInformation vimConnectionInformation, final String resourceId, final String zoneId, final Set<String> ips) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<VimCapability> getCaps(final VimConnectionInformation vimConnectionInformation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @NotNull Network network(final VimConnectionInformation vimConnectionInformation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @NotNull Storage storage(final VimConnectionInformation vimConnectionInformation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @NotNull Dns dns(final VimConnectionInformation vimConnectionInformation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @NotNull VimMonitoring getMonitoring(final VimConnectionInformation vimConnectionInformation) {
		// TODO Auto-generated method stub
		return null;
	}

}
