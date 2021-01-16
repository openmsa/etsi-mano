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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.etsi.mano.dao.mano.NsAddressData;
import com.ubiqube.etsi.mano.dao.mano.NsSap;
import com.ubiqube.etsi.mano.dao.mano.NsVlProfile;
import com.ubiqube.etsi.mano.dao.mano.SecurityGroup;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsVirtualLink;
import com.ubiqube.etsi.mano.service.pkg.ToscaException;
import com.ubiqube.etsi.mano.service.pkg.bean.NsInformations;
import com.ubiqube.etsi.mano.service.pkg.bean.SecurityGroupAdapter;
import com.ubiqube.etsi.mano.service.pkg.ns.NsPackageProvider;
import com.ubiqube.etsi.mano.service.pkg.tosca.SizeConverter;
import com.ubiqube.etsi.mano.service.pkg.tosca.TimeConverter;
import com.ubiqube.parser.tosca.ToscaContext;
import com.ubiqube.parser.tosca.ToscaParser;
import com.ubiqube.parser.tosca.api.ToscaApi;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
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
public class ToscaNsPackageProvider implements NsPackageProvider {

	private static final Logger LOG = LoggerFactory.getLogger(ToscaNsPackageProvider.class);

	private final ToscaApi toscaApi;
	private final ToscaContext root;
	private final MapperFacade mapper;

	private final ToscaParser toscaParser;

	public ToscaNsPackageProvider(final byte[] data) {
		final File tempFile = fetchData(data);
		toscaParser = new ToscaParser(tempFile.getAbsolutePath());
		root = toscaParser.getContext();
		toscaApi = new ToscaApi();
		final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

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

		final ConverterFactory converterFactory = mapperFactory.getConverterFactory();
		converterFactory.registerConverter(new SizeConverter());
		converterFactory.registerConverter(new TimeConverter());
		mapper = mapperFactory.getMapperFacade();
	}

	private static File fetchData(final byte[] data) {
		File tempFile;
		try {
			tempFile = File.createTempFile("tosca", ".zip");
		} catch (final IOException e) {
			throw new ToscaException(e);
		}
		try (final OutputStream os = new FileOutputStream(tempFile)) {
			os.write(data);
		} catch (final IOException e) {
			throw new ToscaException(e);
		}
		return tempFile;
	}

	@Override
	public NsInformations getNsInformations(final Map<String, String> userData) {
		final List<NS> ns = toscaApi.getObjects(root, userData, tosca.nodes.nfv.NS.class);
		return mapper.map(ns.get(0), NsInformations.class);
	}

	@Override
	public Set<NsVirtualLink> getNsVirtualLink(final Map<String, String> userData) {
		final List<tosca.nodes.nfv.NsVirtualLink> nvl = toscaApi.getObjects(root, userData, tosca.nodes.nfv.NsVirtualLink.class);
		return nvl.stream().map(x -> mapper.map(x, NsVirtualLink.class)).collect(Collectors.toSet());
	}

	@Override
	public Set<NsSap> getNsSap(final Map<String, String> userData) {
		final List<Sap> saps = toscaApi.getObjects(root, userData, Sap.class);
		return saps.stream().map(x -> mapper.map(x, NsSap.class)).collect(Collectors.toSet());
	}

	@Override
	public Set<SecurityGroupAdapter> getSecurityGroups(final Map<String, String> userData) {
		final List<SecurityGroupRule> sgr = toscaApi.getObjects(root, userData, SecurityGroupRule.class);
		return sgr.stream().map(x -> new SecurityGroupAdapter(mapper.map(x, SecurityGroup.class), x.getTargets())).collect(Collectors.toSet());
	}

	@Override
	public Set<String> getNestedNsd(final Map<String, String> userData) {
		final List<NsTopology> sgr = toscaApi.getObjects(root, userData, NsTopology.class);
		return sgr.stream()
				.filter(x -> x.getNestedNsdInvariant() != null)
				.flatMap(x -> x.getNestedNsdInvariant().stream())
				.collect(Collectors.toSet());
	}

	@Override
	public Set<String> getVnfd(final Map<String, String> userData) {
		final List<NsTopology> sgr = toscaApi.getObjects(root, userData, NsTopology.class);
		return sgr.stream()
				.filter(x -> x.getVnfdInvariant() != null)
				.flatMap(x -> x.getVnfdInvariant().stream()).collect(Collectors.toSet());
	}

}
