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
package com.ubiqube.etsi.mano.service.pkg.tosca.ns;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.ubiqube.etsi.mano.dao.mano.NsAddressData;
import com.ubiqube.etsi.mano.dao.mano.NsSap;
import com.ubiqube.etsi.mano.dao.mano.NsVlProfile;
import com.ubiqube.etsi.mano.dao.mano.SecurityGroup;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsVirtualLink;
import com.ubiqube.etsi.mano.service.pkg.bean.NsInformations;
import com.ubiqube.etsi.mano.service.pkg.bean.SecurityGroupAdapter;
import com.ubiqube.etsi.mano.service.pkg.ns.NsPackageProvider;
import com.ubiqube.etsi.mano.service.pkg.tosca.AbstractPackageReader;

import ma.glasnost.orika.MapperFactory;
import tosca.datatypes.nfv.AddressData;
import tosca.nodes.nfv.NS;
import tosca.nodes.nfv.NsTopology;
import tosca.nodes.nfv.Sap;
import tosca.policies.nfv.SecurityGroupRule;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class ToscaNsPackageProvider extends AbstractPackageReader implements NsPackageProvider {

	public ToscaNsPackageProvider(final byte[] data) {
		super(data);
	}

	@Override
	protected void additionalMapping(final MapperFactory mapperFactory) {
		mapperFactory.classMap(tosca.nodes.nfv.NsVirtualLink.class, NsVirtualLink.class)
				.field("vlProfile", "nsVlProfile")
				.field("connectivityType", "vlConnectivityType")
				.byDefault()
				.register();
		mapperFactory.classMap(tosca.datatypes.nfv.NsVlProfile.class, NsVlProfile.class)
				.field("minBitrateRequirements.root", "linkBitrateRoot")
				.field("minBitrateRequirements.leaf", "linkBitrateLeaf")
				.field("maxBitrateRequirements.root", "maxBitrateRequirementsRoot")
				.field("maxBitrateRequirements.leaf", "maxBitrateRequirementsLeaf")
				.field("serviceAvailability.level", "serviceAvailability")
				.byDefault()
				.register();

		mapperFactory.classMap(AddressData.class, NsAddressData.class)
				.field("l2AddressData.macAddressAssignment", "macAddressAssignment")
				.field("l3AddressData.numberOfIpAddress", "numberOfIpAddress")
				.field("l3AddressData.ipAddressAssignment", "ipAddressAssignment")
				.field("l3AddressData.ipAddressType", "ipAddressType")
				.field("l3AddressData.floatingIpActivated", "floatingIpActivated")
				.byDefault()
				.register();
		mapperFactory.classMap(NS.class, NsInformations.class)
				.field("descriptorId", "nsdId")
				.field("invariantId", "nsdInvariantId")
				.field("nsProfile.minNumberOfInstances", "minNumberOfInstance")
				.field("nsProfile.maxNumberOfInstances", "maxNumberOfInstance")
				.field("nsProfile.nsInstantiationLevel", "instantiationLevel")
				.field("name", "nsdName")
				.field("flavourId", "flavorId")
				.field("designer", "nsdDesigner")
				.field("version", "nsdVersion")
				.byDefault()
				.register();
		mapperFactory.classMap(Sap.class, NsSap.class)
				.field("internalName", "toscaName")
				.byDefault()
				.register();
		mapperFactory.classMap(tosca.nodes.nfv.NsVirtualLink.class, NsVirtualLink.class)
				.field("internalName", "toscaName")
				.byDefault()
				.register();
	}

	@Override
	public NsInformations getNsInformations(final Map<String, String> userData) {
		final List<NsInformations> nss = getListOf(NS.class, NsInformations.class, userData);
		return nss.get(0);
	}

	@Override
	public Set<NsVirtualLink> getNsVirtualLink(final Map<String, String> userData) {
		return getSetOf(tosca.nodes.nfv.NsVirtualLink.class, NsVirtualLink.class, userData);
	}

	@Override
	public Set<NsSap> getNsSap(final Map<String, String> userData) {
		return getSetOf(Sap.class, NsSap.class, userData);
	}

	@Override
	public Set<SecurityGroupAdapter> getSecurityGroups(final Map<String, String> userData) {
		final List<SecurityGroupRule> sgr = getObjects(SecurityGroupRule.class, userData);
		return sgr.stream().map(x -> new SecurityGroupAdapter(getMapper().map(x, SecurityGroup.class), x.getTargets())).collect(Collectors.toSet());
	}

	@Override
	public Set<String> getNestedNsd(final Map<String, String> userData) {
		final List<NsTopology> sgr = getObjects(NsTopology.class, userData);
		return sgr.stream()
				.filter(x -> x.getNestedNsdInvariant() != null)
				.flatMap(x -> x.getNestedNsdInvariant().stream())
				.collect(Collectors.toSet());
	}

	@Override
	public Set<String> getVnfd(final Map<String, String> userData) {
		final List<NsTopology> sgr = getObjects(NsTopology.class, userData);
		return sgr.stream()
				.filter(x -> x.getVnfdInvariant() != null)
				.flatMap(x -> x.getVnfdInvariant().stream()).collect(Collectors.toSet());
	}

}
