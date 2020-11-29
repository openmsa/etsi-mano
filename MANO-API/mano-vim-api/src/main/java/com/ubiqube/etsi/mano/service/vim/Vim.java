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
import java.util.Set;

import javax.annotation.Nonnull;

import com.ubiqube.etsi.mano.dao.mano.GrantInformationExt;
import com.ubiqube.etsi.mano.dao.mano.IpPool;
import com.ubiqube.etsi.mano.dao.mano.L3Data;
import com.ubiqube.etsi.mano.dao.mano.SoftwareImage;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VlProtocolData;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public interface Vim {

	void allocateResources(VimConnectionInformation vimConnectionInformation, GrantInformationExt x);

	void freeResources(VimConnectionInformation vimConnectionInformation, GrantInformationExt x);

	String getType();

	@Nonnull
	VimImage getImagesInformations(VimConnectionInformation vimConnectionInformation, String name);

	String createNetwork(final VimConnectionInformation vimConnectionInformation, final VlProtocolData vl, String name, String dnsDomain, String qosPolicyId);

	String createSubnet(final VimConnectionInformation vimConnectionInformation, final L3Data l3ProtocolData, final IpPool ipAllocationPool, final String networkId);

	void deleteSubnet(final VimConnectionInformation vimConnectionInformation, final String resourceId);

	/**
	 * Add VIM custom Node inside the global dependency network.
	 *
	 * @param connectionStorage The link descriptor instance.
	 */
	void addNodeToPlans(VnfConnections connectionStorage);

	Optional<SoftwareImage> getSwImageMatching(VimConnectionInformation vimConnectionInformation, SoftwareImage img);

	SoftwareImage uploadSoftwareImage(VimConnectionInformation vimConnectionInformation, SoftwareImage img);

	@Nonnull
	String getOrCreateFlavor(VimConnectionInformation vimConnectionInformation, String name, int numVcpu, long virtualMemorySize, long disk);

	String createStorage(VimConnectionInformation vimConnectionInformation, VnfStorage vnfStorage, final String aliasName);

	String createCompute(VimConnectionInformation vimConnectionInformation, String instanceName, String flavorId, String imageId, List<String> networks, List<String> storages, String cloudInitData);

	String createObjectStorage(final VimConnectionInformation vimConnectionInformation, final VnfStorage vnfStorage);

	List<String> getZoneAvailableList(VimConnectionInformation vimConnectionInformation);

	void deleteCompute(VimConnectionInformation vimConnectionInformation, String resourceId);

	void deleteVirtualLink(VimConnectionInformation vimConnectionInformation, String resourceId);

	void deleteStorage(VimConnectionInformation vimConnectionInformation, String resourceId);

	void deleteObjectStorage(VimConnectionInformation vimConnectionInformation, String resourceId);

	List<ServerGroup> getServerGroup(final VimConnectionInformation vimConnectionInformation);

	String createRouter(final VimConnectionInformation vimConnectionInformation, final String name, final String internalNetworkId, final String externalNetworkId);

	void deleteRouter(VimConnectionInformation vimConnectionInformation, String resourceId);

	@Nonnull
	Map<String, String> getPublicNetworks(VimConnectionInformation vimConnectionInformation);

	void startServer(VimConnectionInformation vimConnectionInformation, String resourceId);

	void stopServer(VimConnectionInformation vimConnectionInformation, String resourceId);

	ResourceQuota getQuota(final VimConnectionInformation vimConnectionInformation);

	String createDnsZone(final VimConnectionInformation vimConnectionInformation, final String zoneName);

	void deleteDnsZone(VimConnectionInformation vimConnectionInformation, String resourceId);

	String createDnsRecordSet(final VimConnectionInformation vimConnectionInformation, final String zoneId, final String hostname, final String networkName);

	void deleteDnsRecordSet(final VimConnectionInformation vimConnectionInformation, final String resourceId, final String zoneId, final Set<String> ips);

	List<VimCapability> getCaps(final VimConnectionInformation vimConnectionInformation);
}
