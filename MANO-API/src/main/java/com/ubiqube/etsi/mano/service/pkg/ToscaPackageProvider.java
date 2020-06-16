package com.ubiqube.etsi.mano.service.pkg;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.etsi.mano.dao.mano.AdditionalArtifact;
import com.ubiqube.etsi.mano.dao.mano.L3Data;
import com.ubiqube.etsi.mano.dao.mano.NsAddressData;
import com.ubiqube.etsi.mano.dao.mano.NsSap;
import com.ubiqube.etsi.mano.dao.mano.NsVirtualLink;
import com.ubiqube.etsi.mano.dao.mano.NsVlProfile;
import com.ubiqube.etsi.mano.dao.mano.ScalingAspect;
import com.ubiqube.etsi.mano.dao.mano.SecurityGroup;
import com.ubiqube.etsi.mano.dao.mano.SoftwareImage;
import com.ubiqube.etsi.mano.dao.mano.VlProtocolData;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfExtCp;
import com.ubiqube.etsi.mano.dao.mano.VnfLinkPort;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.dao.mano.VnfVl;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.service.event.ProviderData;
import com.ubiqube.parser.tosca.ToscaContext;
import com.ubiqube.parser.tosca.ToscaParser;
import com.ubiqube.parser.tosca.api.ArtefactInformations;
import com.ubiqube.parser.tosca.api.ToscaApi;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import tosca.datatypes.nfv.AddressData;
import tosca.datatypes.nfv.L3ProtocolData;
import tosca.datatypes.nfv.VirtualLinkProtocolData;
import tosca.nodes.nfv.NS;
import tosca.nodes.nfv.NsTopology;
import tosca.nodes.nfv.Sap;
import tosca.nodes.nfv.VNF;
import tosca.nodes.nfv.VduCp;
import tosca.nodes.nfv.VnfVirtualLink;
import tosca.nodes.nfv.vdu.Compute;
import tosca.nodes.nfv.vdu.VirtualBlockStorage;
import tosca.nodes.nfv.vdu.VirtualObjectStorage;
import tosca.policies.nfv.InstantiationLevels;
import tosca.policies.nfv.ScalingAspects;
import tosca.policies.nfv.SecurityGroupRule;
import tosca.policies.nfv.VduInitialDelta;
import tosca.policies.nfv.VduInstantiationLevels;
import tosca.policies.nfv.VduScalingAspectDeltas;

public class ToscaPackageProvider implements PackageProvider {

	private static final Logger LOG = LoggerFactory.getLogger(ToscaPackageProvider.class);

	private final ToscaApi toscaApi;
	private final ToscaContext root;
	private final MapperFacade mapper;

	private final ToscaParser toscaParser;

	public ToscaPackageProvider(final byte[] data) {
		final File tempFile = fetchData(data);
		toscaParser = new ToscaParser(tempFile.getAbsolutePath());
		root = toscaParser.getContext();
		toscaApi = new ToscaApi();
		final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(ProviderData.class, VNF.class)
				.field("vnfProvider", "provider")
				.field("vnfProductName", "productName")
				.field("vnfSoftwareVersion", "softwareVersion")
				.field("vnfdVersion", "descriptorVersion")
				.field("vnfdId", "descriptorId")
				.field("descriptorId", "descriptorId")
				.field("flavorId", "flavourId")
				.byDefault()
				.register();
		mapperFactory.classMap(ArtefactInformations.class, AdditionalArtifact.class)
				.field("path", "artifactPath")
				.field("checksum", "checksum.hash")
				.field("algorithm", "checksum.algorithm")
				.byDefault()
				.register();
		mapperFactory.classMap(VirtualBlockStorage.class, VnfStorage.class)
				.field("swImageData", "softwareImage")
				.field("virtualBlockStorageData.sizeOfStorage", "size")
				.field("internalName", "toscaName")
				.field("", "myField:{|setType('BLOCK')}")
				.byDefault()
				.register();
		mapperFactory.classMap(VirtualObjectStorage.class, VnfStorage.class)
				.field("virtualObjectStorageData.maxSizeOfStorage", "size")
				.field("internalName", "toscaName")
				.field("", "myField:{|setType('OBJECT')}")
				.byDefault()
				.register();
		mapperFactory.classMap(Compute.class, VnfCompute.class)
				.field("swImageData", "softwareImage")
				.field("internalName", "toscaName")
				.field("virtualStorageReq", "storages")
				.field("virtualCompute.virtualCpu.numVirtualCpu", "numVcpu")
				.field("virtualCompute.virtualCpu.cpuArchitecture", "cpuArchitecture")
				.field("virtualCompute.virtualMemory.virtualMemSize", "virtualMemorySize")
				.field("virtualCompute.virtualLocalStorage[0].sizeOfStorage", "diskSize")
				.byDefault()
				.register();
		mapperFactory.classMap(VduCp.class, VnfLinkPort.class)
				.field("virtualBindingReq", "virtualBinding")
				.field("virtualLinkReq", "virtualLink")
				.field("order", "interfaceOrder")
				.field("internalName", "toscaName")
				.byDefault()
				.register();
		mapperFactory.classMap(VnfVirtualLink.class, VnfVl.class)
				.field("internalName", "toscaName")
				.field("vlProfile", "vlProfileEntity")
				.byDefault()
				.register();
		mapperFactory.classMap(VirtualLinkProtocolData.class, VlProtocolData.class)
				.field("l3ProtocolData.ipAllocationPools", "ipAllocationPools")
				.byDefault()
				.register();
		mapperFactory.classMap(L3ProtocolData.class, L3Data.class)
				.field("name", "l3Name")
				.byDefault()
				.register();

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
		mapperFactory.classMap(tosca.nodes.nfv.VnfExtCp.class, VnfExtCp.class)
				.field("externalVirtualLinkReq", "externalVirtualLink")
				.field("internalVirtualLinkReq", "internalVirtualLink")
				.field("internalName", "toscaName")
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
			throw new GenericException(e);
		}
		try (final OutputStream os = new FileOutputStream(tempFile)) {
			os.write(data);
		} catch (final IOException e) {
			throw new GenericException(e);
		}
		return tempFile;
	}

	@Override
	public Set<SoftwareImage> getSoftwareImages(final Map<String, String> parameters) {
		final List<Compute> list = toscaApi.getObjects(root, parameters, Compute.class);
		LOG.debug("Found {} Compute node in TOSCA model", list.size());
		return list.stream()
				.map(x -> mapper.map(x.getSwImageData(), SoftwareImage.class))
				.collect(Collectors.toSet());
	}

	@Override
	public ProviderData getProviderPadata() {
		final List<VNF> vnfs = toscaApi.getObjects(root, new HashMap<String, String>(), VNF.class);
		if (vnfs.isEmpty()) {
			LOG.warn("No VNF node found in the package.");
			return new ProviderData();
		}
		return mapper.map(vnfs.get(0), ProviderData.class);
	}

	@Override
	public Set<AdditionalArtifact> getAdditionalArtefacts(final Map<String, String> parameters) {
		final List<ArtefactInformations> files = toscaParser.getFiles();
		return files.stream().map(x -> mapper.map(x, AdditionalArtifact.class)).collect(Collectors.toSet());
	}

	@Override
	public Set<VnfCompute> getVnfComputeNodes(final Map<String, String> parameters) {
		final List<Compute> list = toscaApi.getObjects(root, parameters, Compute.class);
		LOG.debug("Found {} Compute node in TOSCA model", list.size());
		return list.stream()
				.map(x -> mapper.map(x, VnfCompute.class))
				.collect(Collectors.toSet());
	}

	@Override
	public Set<VnfStorage> getVnfStorages(final Map<String, String> parameters) {
		final List<VirtualBlockStorage> list = toscaApi.getObjects(root, parameters, VirtualBlockStorage.class);
		LOG.debug("Found {} Block Storage node in TOSCA model", list.size());
		final Set<VnfStorage> res = list.stream()
				.map(x -> mapper.map(x, VnfStorage.class))
				.collect(Collectors.toSet());
		final List<VirtualObjectStorage> vos = toscaApi.getObjects(root, parameters, VirtualObjectStorage.class);
		LOG.debug("Found {} Object Storage node in TOSCA model", vos.size());
		final Set<VnfStorage> resVos = vos.stream()
				.map(x -> mapper.map(x, VnfStorage.class))
				.collect(Collectors.toSet());
		res.addAll(resVos);
		return res;
	}

	@Override
	public Set<VnfVl> getVnfVirtualLinks(final Map<String, String> parameters) {
		final List<VnfVirtualLink> list = toscaApi.getObjects(root, parameters, VnfVirtualLink.class);
		LOG.debug("Found {} Vl node in TOSCA model", list.size());
		return list.stream()
				.map(x -> mapper.map(x, VnfVl.class))
				.collect(Collectors.toSet());
	}

	@Override
	public Set<VnfLinkPort> getVnfVduCp(final Map<String, String> parameters) {
		final List<VduCp> list = toscaApi.getObjects(root, parameters, VduCp.class);
		LOG.debug("Found {} VduCp node in TOSCA model", list.size());
		return list.stream()
				.map(x -> mapper.map(x, VnfLinkPort.class))
				.collect(Collectors.toSet());
	}

	@Override
	public Set<VnfExtCp> getVnfExtCp(final Map<String, String> parameters) {
		final List<tosca.nodes.nfv.VnfExtCp> list = toscaApi.getObjects(root, parameters, tosca.nodes.nfv.VnfExtCp.class);
		LOG.debug("Found {} ExtCp node in TOSCA model", list.size());
		return list.stream()
				.map(x -> mapper.map(x, VnfExtCp.class))
				.collect(Collectors.toSet());
	}

	@Override
	public Set<ScalingAspect> getScalingAspects(final Map<String, String> parameters) {
		final List<ScalingAspects> list = toscaApi.getObjects(root, parameters, ScalingAspects.class);
		final Set<ScalingAspect> ret = new HashSet<>();
		for (final ScalingAspects scalingAspects : list) {
			final Map<String, tosca.datatypes.nfv.ScalingAspect> sa = scalingAspects.getAspects();
			final Set<ScalingAspect> tmp = sa.entrySet().stream().map(x -> {
				final ScalingAspect scaleRet = mapper.map(x.getValue(), ScalingAspect.class);
				scaleRet.setName(x.getKey());
				return scaleRet;
			}).collect(Collectors.toSet());
			ret.addAll(tmp);
		}
		return ret;
	}

	@Override
	public List<InstantiationLevels> getInstatiationLevels(final Map<String, String> parameters) {
		return toscaApi.getObjects(root, parameters, InstantiationLevels.class);
	}

	@Override
	public List<VduInstantiationLevels> getVduInstantiationLevels(final Map<String, String> parameters) {
		return toscaApi.getObjects(root, parameters, VduInstantiationLevels.class);
	}

	@Override
	public List<VduInitialDelta> getVduInitialDelta(final Map<String, String> parameters) {
		return toscaApi.getObjects(root, parameters, VduInitialDelta.class);
	}

	@Override
	public List<VduScalingAspectDeltas> getVduScalingAspectDeltas(final Map<String, String> parameters) {
		return toscaApi.getObjects(root, parameters, VduScalingAspectDeltas.class);
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
