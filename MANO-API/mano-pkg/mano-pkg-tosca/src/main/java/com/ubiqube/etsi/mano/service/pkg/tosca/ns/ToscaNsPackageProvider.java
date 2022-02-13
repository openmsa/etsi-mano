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

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.etsi.mano.dao.mano.NsAddressData;
import com.ubiqube.etsi.mano.dao.mano.NsSap;
import com.ubiqube.etsi.mano.dao.mano.NsVlProfile;
import com.ubiqube.etsi.mano.dao.mano.SecurityGroup;
import com.ubiqube.etsi.mano.dao.mano.VlProtocolData;
import com.ubiqube.etsi.mano.dao.mano.dto.NsNsd;
import com.ubiqube.etsi.mano.dao.mano.dto.NsVnf;
import com.ubiqube.etsi.mano.dao.mano.nsd.VnffgDescriptor;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsVirtualLink;
import com.ubiqube.etsi.mano.service.pkg.ToscaException;
import com.ubiqube.etsi.mano.service.pkg.bean.NsInformations;
import com.ubiqube.etsi.mano.service.pkg.bean.SecurityGroupAdapter;
import com.ubiqube.etsi.mano.service.pkg.bean.nsscaling.LevelMapping;
import com.ubiqube.etsi.mano.service.pkg.bean.nsscaling.NsScaling;
import com.ubiqube.etsi.mano.service.pkg.bean.nsscaling.RootLeaf;
import com.ubiqube.etsi.mano.service.pkg.bean.nsscaling.StepMapping;
import com.ubiqube.etsi.mano.service.pkg.bean.nsscaling.VlLevelMapping;
import com.ubiqube.etsi.mano.service.pkg.bean.nsscaling.VlStepMapping;
import com.ubiqube.etsi.mano.service.pkg.ns.NsPackageProvider;
import com.ubiqube.etsi.mano.service.pkg.tosca.AbstractPackageReader;
import com.ubiqube.parser.tosca.ParseException;

import ma.glasnost.orika.MapperFactory;
import tosca.datatypes.nfv.AddressData;
import tosca.datatypes.nfv.LinkBitrateRequirements;
import tosca.datatypes.nfv.NsVirtualLinkProtocolData;
import tosca.groups.nfv.VNFFG;
import tosca.nodes.nfv.Forwarding;
import tosca.nodes.nfv.NFP;
import tosca.nodes.nfv.NS;
import tosca.nodes.nfv.NfpPosition;
import tosca.nodes.nfv.NfpPositionElement;
import tosca.nodes.nfv.Sap;
import tosca.nodes.nfv.VNF;
import tosca.policies.nfv.NfpRule;
import tosca.policies.nfv.NsAutoScale;
import tosca.policies.nfv.NsToLevelMapping;
import tosca.policies.nfv.SecurityGroupRule;
import tosca.policies.nfv.VirtualLinkToLevelMapping;
import tosca.policies.nfv.VnfToInstantiationLevelMapping;
import tosca.policies.nfv.VnfToLevelMapping;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class ToscaNsPackageProvider extends AbstractPackageReader implements NsPackageProvider {

	private static final Logger LOG = LoggerFactory.getLogger(ToscaNsPackageProvider.class);

	public ToscaNsPackageProvider(final InputStream data) {
		super(data);
	}

	@Override
	protected void additionalMapping(final MapperFactory mapperFactory) {
		mapperFactory.classMap(tosca.nodes.nfv.NsVirtualLink.class, NsVirtualLink.class)
				.field("internalName", "toscaName")
				.field("vlProfile", "nsVlProfile")
				.field("connectivityType", "vlConnectivityType")
				.byDefault()
				.register();
		mapperFactory.classMap(tosca.datatypes.nfv.NsVlProfile.class, NsVlProfile.class)
				.field("minBitrateRequirements.root", "linkBitrateRoot")
				.field("minBitrateRequirements.leaf", "linkBitrateLeaf")
				.field("maxBitrateRequirements.root", "maxBitrateRequirementsRoot")
				.field("maxBitrateRequirements.leaf", "maxBitrateRequirementsLeaf")
				.field("serviceAvailabilityLevel", "serviceAvailability")
				.field("virtualLinkProtocolData", "vlProtocolData")
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
		mapperFactory.classMap(NsVirtualLinkProtocolData.class, VlProtocolData.class)
				.field("l3ProtocolData.ipAllocationPools", "ipAllocationPools")
				.byDefault()
				.register();
	}

	@SuppressWarnings("null")
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
	public Set<NsNsd> getNestedNsd(final Map<String, String> userData) {
		final List<NS> sgr = getObjects(NS.class, userData);
		if (!sgr.isEmpty()) {
			sgr.remove(0);
		}
		return sgr.stream()
				.filter(x -> x.getDescriptorId() != null)
				.map(x -> new NsNsd(x.getInvariantId(), x.getInternalName(), x.getFlavourId(), x.getVirtualLinkReq()))
				.collect(Collectors.toSet());
	}

	@Override
	public Set<NsVnf> getVnfd(final Map<String, String> userData) {
		final List<VNF> sgr = getObjects(VNF.class, userData);
		return sgr.stream()
				.filter(x -> x.getDescriptorId() != null)
				.map(ToscaNsPackageProvider::map)
				.collect(Collectors.toSet());
	}

	private static NsVnf map(final VNF x) {
		final NsVnf o = new NsVnf();
		o.setVnfdId(x.getDescriptorId());
		o.setName(x.getInternalName());
		o.setFlavourId(x.getFlavourId());
		o.setVirtualLink(x.getVirtualLinkReq());
		o.setVirtualLink1(x.getVirtualLink1Req());
		o.setVirtualLink2(x.getVirtualLink2Req());
		o.setVirtualLink3(x.getVirtualLink3Req());
		o.setVirtualLink4(x.getVirtualLink4Req());
		o.setVirtualLink5(x.getVirtualLink5Req());
		o.setVirtualLink6(x.getVirtualLink6Req());
		o.setVirtualLink7(x.getVirtualLink7Req());
		o.setVirtualLink8(x.getVirtualLink8Req());
		o.setVirtualLink9(x.getVirtualLink9Req());
		o.setVirtualLink10(x.getVirtualLink10Req());
		return o;
	}

	/**
	 * VNFFGd -> 1..N, NfpPositionElement -> 0..N Nfpd.
	 */
	@Override
	public Set<VnffgDescriptor> getVnffg(final Map<String, String> userData) {
		// Port pair group ?
		getObjects(NfpPositionElement.class, userData);
		// NfpPosition link to NfpPositionElement
		final List<NfpPosition> nfpPosition = getObjects(NfpPosition.class, userData);
		// nfp = Link to NfpPosition
		final List<NFP> nfp = getObjects(NFP.class, userData);
		getObjects(Forwarding.class, userData);
		// nfp rule = Link to NFP . classifier, Target = NFP
		final List<NfpRule> nfpRule = getObjects(NfpRule.class, userData);
		// vnffg link to NFP, VNF, PNF, NS, NsVirtualLink, NfpPositionElement
		final List<VNFFG> vnffg = getObjects(VNFFG.class, userData);
		final List<VnffgDescriptor> rules = nfpRule.stream().map(x -> {
			final String nfpName = getNfpName(x);
			final NFP localNfp = findNfp(nfpName, nfp);
			localNfp.getNfpPositionReq();
			return new VnffgDescriptor();
		}).toList();
		return vnffg.stream().map(x -> {
			final NFP nfpd = findNfp(nfp, x.getMembers());
			final VnffgDescriptor vnffgd = new VnffgDescriptor();
			final List<NfpPosition> nfpPos = findNfpPosition(nfpd.getNfpPositionReq(), nfpPosition);
			LOG.trace("{}, {}", rules, nfpPos);
			return vnffgd;
		})
				.collect(Collectors.toSet());
	}

	private static NFP findNfp(final String nfpName, final List<NFP> nfp) {
		return nfp.stream().filter(x -> x.getInternalName().equals(nfpName)).findFirst().orElseThrow(() -> new ParseException("Could not find Nfp named: " + nfpName));
	}

	private static String getNfpName(final NfpRule rule) {
		final List<String> tgt = rule.getTargets();
		if (null == tgt || tgt.isEmpty()) {
			throw new ToscaException("Rule [" + rule.getInternalName() + "] must have a target.");
		}
		if (tgt.size() != 1) {
			throw new ToscaException("Rule [" + rule.getInternalName() + "] must have only one target.");
		}
		return tgt.get(0);
	}

	private static List<NfpPosition> findNfpPosition(final List<String> nfpPositionReq, final List<NfpPosition> nfpPosition) {
		return nfpPosition.stream().filter(x -> nfpPositionReq.contains(x.getInternalName())).toList();
	}

	private static NFP findNfp(final List<NFP> nfp, final List<String> members) {
		return nfp.stream().filter(x -> members.contains(x.getInternalName())).findFirst().orElseThrow();
	}

	@Override
	public boolean isAutoHealEnabled() {
		final List<NsAutoScale> autoScale = getObjects(NsAutoScale.class, Map.of());
		return !autoScale.isEmpty();
	}

	@Override
	public NsScaling getNsScaling(final Map<String, String> userData) {
		final NsScaling ret = new NsScaling();
		// Level
		final List<VnfToInstantiationLevelMapping> vnfLevel = getObjects(VnfToInstantiationLevelMapping.class, userData);
		ret.setVnfLevelMapping(vnfLevel.stream().map(x -> new LevelMapping(x.getInternalName(), x.getTargets(), x.getNumberOfInstances())).toList());
		final List<NsToLevelMapping> nsLevel = getObjects(NsToLevelMapping.class, userData);
		ret.setNsLevelMapping(nsLevel.stream().map(x -> new LevelMapping(x.getAspect(), x.getTargets(), x.getNumberOfInstances())).toList());
		final List<VirtualLinkToLevelMapping> vlLevel = getObjects(VirtualLinkToLevelMapping.class, userData);
		ret.setVlLevelMapping(vlLevel.stream().map(x -> new VlLevelMapping(x.getTargets(), map(x.getBitRateRequirements()))).toList());
		// Step
		final List<VnfToLevelMapping> vnfStep = getObjects(VnfToLevelMapping.class, userData);
		ret.setVnfStepMapping(vnfStep.stream().map(x -> new StepMapping(x.getAspect(), x.getTargets(), mapLevel(x.getNumberOfInstances()))).toList());
		final List<NsToLevelMapping> nsStep = getObjects(NsToLevelMapping.class, userData);
		ret.setNsStepMapping(nsStep.stream().map(x -> new StepMapping(x.getAspect(), x.getTargets(), mapLevel(x.getNumberOfInstances()))).toList());
		final List<VirtualLinkToLevelMapping> vlStep = getObjects(VirtualLinkToLevelMapping.class, userData);
		ret.setVlStepMapping(vlStep.stream().map(x -> new VlStepMapping(x.getAspect(), x.getTargets(), mapVlLevel(x.getBitRateRequirements()))).toList());
		return ret;
	}

	private static Map<Integer, RootLeaf> mapVlLevel(@NotNull final Map<String, LinkBitrateRequirements> bitRateRequirements) {
		return bitRateRequirements.entrySet().stream()
				.collect(Collectors.toMap(x -> Integer.valueOf(x.getKey()), x -> new RootLeaf(x.getValue().getRoot(), x.getValue().getLeaf())));
	}

	private static Map<Integer, Integer> mapLevel(final Map<String, Integer> map) {
		return map.entrySet().stream()
				.collect(Collectors.toMap(x -> Integer.valueOf(x.getKey()), Entry::getValue));
	}

	private static Map<String, RootLeaf> map(@NotNull final Map<String, LinkBitrateRequirements> bitRateRequirements) {
		return bitRateRequirements.entrySet().stream()
				.collect(Collectors.toMap(Entry::getKey, x -> new RootLeaf(x.getValue().getRoot(), x.getValue().getLeaf())));
	}

}
