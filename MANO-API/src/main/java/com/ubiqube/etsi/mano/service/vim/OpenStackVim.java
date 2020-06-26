package com.ubiqube.etsi.mano.service.vim;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

import org.jgrapht.ListenableGraph;
import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.api.client.IOSClientBuilder.V3;
import org.openstack4j.api.storage.BlockVolumeService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.model.common.Payload;
import org.openstack4j.model.common.Payloads;
import org.openstack4j.model.compute.AbsoluteLimit;
import org.openstack4j.model.compute.Action;
import org.openstack4j.model.compute.BlockDeviceMappingCreate;
import org.openstack4j.model.compute.Flavor;
import org.openstack4j.model.compute.Image;
import org.openstack4j.model.compute.Server;
import org.openstack4j.model.compute.builder.ServerCreateBuilder;
import org.openstack4j.model.compute.ext.AvailabilityZone;
import org.openstack4j.model.image.ContainerFormat;
import org.openstack4j.model.image.DiskFormat;
import org.openstack4j.model.image.builder.ImageBuilder;
import org.openstack4j.model.network.AttachInterfaceType;
import org.openstack4j.model.network.IPVersionType;
import org.openstack4j.model.network.Network;
import org.openstack4j.model.network.NetworkType;
import org.openstack4j.model.network.Port;
import org.openstack4j.model.network.Router;
import org.openstack4j.model.network.Subnet;
import org.openstack4j.model.network.builder.NetworkBuilder;
import org.openstack4j.model.network.builder.SubnetBuilder;
import org.openstack4j.model.storage.block.Volume;
import org.openstack4j.model.storage.block.builder.VolumeBuilder;
import org.openstack4j.openstack.OSFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.etsi.mano.dao.mano.GrantInformation;
import com.ubiqube.etsi.mano.dao.mano.IpPool;
import com.ubiqube.etsi.mano.dao.mano.L2Data;
import com.ubiqube.etsi.mano.dao.mano.L3Data;
import com.ubiqube.etsi.mano.dao.mano.SoftwareImage;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VlProtocolData;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedVirtualLink;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.jpa.VimConnectionInformationJpa;
import com.ubiqube.etsi.mano.service.graph.ConnectivityEdge;
import com.ubiqube.etsi.mano.service.graph.vnfm.NoopUow;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;
import com.ubiqube.etsi.mano.service.graph.vnfm.VirtualLinkUow;

import ma.glasnost.orika.MapperFacade;

public class OpenStackVim implements Vim {
	private static final long GIGA = 1024 * 1024 * 1024L;

	private static final long MEGA = 1048576L;

	private static final Logger LOG = LoggerFactory.getLogger(OpenStackVim.class);

	private static final ThreadLocal<Map<String, OSClientV3>> sessions = new ThreadLocal<>();

	private final VimConnectionInformationJpa vciJpa;

	private final MapperFacade mapper;

	public OpenStackVim(final VimConnectionInformationJpa _vciJpa, final MapperFacade _mapper) {
		vciJpa = _vciJpa;
		mapper = _mapper;
	}

	/**
	 * @deprecated Will be removed since java 9.
	 */
	@Override
	@Deprecated
	protected void finalize() throws Throwable {
		sessions.remove();
		LOG.error("Finalize called.");
		super.finalize();
	}

	private static OSClientV3 authenticate(final VimConnectionInformation vci) {
		vci.getInterfaceInfo().get("endpoint");
		final V3 base = OSFactory.builderV3()
				.endpoint(vci.getInterfaceInfo().get("endpoint"));
		final Map<String, String> ai = vci.getAccessInfo();
		final String userDomain = ai.get("userDomain");
		if (null != userDomain) {
			final Identifier domainIdentifier = Identifier.byName(userDomain);
			base.credentials(ai.get("username"), ai.get("password"), domainIdentifier);
		} else {
			base.credentials(ai.get("username"), ai.get("password"));
		}

		final String project = ai.get("project");
		final String projectId = ai.get("projectId");
		if (null != project) {
			base.scopeToProject(Identifier.byName(project));
		} else if (null != projectId) {
			base.scopeToProject(Identifier.byId(projectId));
		}
		return base.authenticate();
	}

	private OSClientV3 getClient(final VimConnectionInformation vimConnectionInformation) {
		final Map<String, OSClientV3> sess = sessions.get();
		if (null == sess) {
			LOG.debug("Creation session cache for thread: {}", Thread.currentThread().getName());
			final HashMap<String, OSClientV3> newSess = new HashMap<>();
			final OSClientV3 osv3 = authenticate(vimConnectionInformation);
			newSess.put(vimConnectionInformation.getVimId(), osv3);
			sessions.set(newSess);
			return osv3;
		}
		return sess.computeIfAbsent(vimConnectionInformation.getVimId(), x -> {
			final Optional<VimConnectionInformation> vim = vciJpa.findById(vimConnectionInformation.getId());
			return authenticate(vim.orElseThrow(() -> new GenericException("Unable to find Vim " + x)));
		});
	}

	@Override
	public void allocateResources(final VimConnectionInformation vimConnectionInformation, final GrantInformation grantInformation) {
		final OSClientV3 os = this.getClient(vimConnectionInformation);
		// XXX Do placement with blazar.
	}

	@Override
	public void freeResources(final VimConnectionInformation vimConnectionInformation, final GrantInformation grantInformation) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getType() {
		return "OPENSTACK_V3";
	}

	@Override
	@Nonnull
	public VimImage getImagesInformations(final VimConnectionInformation vimConnectionInformation, final String name) {
		final OSClientV3 os = this.getClient(vimConnectionInformation);
		final List<? extends Image> images = os.compute().images().list();
		final Image image = images.stream().filter(x -> x.getName().equalsIgnoreCase(name)).findFirst().orElseThrow(() -> new NotFoundException("Image " + name + " Cannot be found on Vim."));
		return mapper.map(image, VimImage.class);
	}

	@Override
	public String createNetwork(final VimConnectionInformation vimConnectionInformation, final VlProtocolData vl, final String name) {
		final OSClientV3 os = this.getClient(vimConnectionInformation);

		final L2Data l2 = vl.getL2ProtocolData();
		final NetworkBuilder bNet = Builders.network().tenantId(vimConnectionInformation.getAccessInfo().get("projectId"));
		Optional.ofNullable(l2.getMtu()).ifPresent(x2 -> bNet.toString());
		bNet.name(name);
		Optional.ofNullable(l2.getNetworkType()).ifPresent(x2 -> bNet.networkType(NetworkType.valueOf(x2.toUpperCase())));
		final Network network = os.networking().network().create(bNet.adminStateUp(true).build());
		l2.getVlanTransparent();
		LOG.debug("Network created: {} = {}", network.getId(), network.getStatus());
		return network.getId();
	}

	public String createSubnet(final VimConnectionInformation vimConnectionInformation, final L3Data l3ProtocolData, final IpPool ipAllocationPool, final String networkId) {
		final OSClientV3 os = this.getClient(vimConnectionInformation);
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

	private IPVersionType convertIpVersion(final String ipVersion) {
		if ("ipv6".equals(ipVersion)) {
			return IPVersionType.valueOf("V6");
		}
		return IPVersionType.valueOf("V4");
	}

	@Override
	public void refineExecutionPlan(final ListenableGraph<UnitOfWork, ConnectivityEdge<UnitOfWork>> g) {
		final List<UnitOfWork> vertexPool = new ArrayList<>();
		LOG.info("Openstack, modification of execution plan.");
		final ArrayList<UnitOfWork> vertexSet = new ArrayList<>(g.vertexSet());
		vertexSet.forEach(x -> {
			if (x instanceof VirtualLinkUow) {
				LOG.debug("Found VL: {}", x.getName());
				final VirtualLinkUow vlu = (VirtualLinkUow) x;
				final VnfInstantiatedCompute vnfInstantiedCompute = new VnfInstantiatedCompute();
				// XXX There is a big question here, we don"t have access to mano internal.
				final NoopUow noop = new NoopUow(vnfInstantiedCompute);
				g.addVertex(noop);

				final ArrayList<ConnectivityEdge<UnitOfWork>> out = new ArrayList<>(g.outgoingEdgesOf(x));
				g.removeAllEdges(new ArrayList<>(out));
				final VlProtocolData vnfVl = vlu.getVlProtocolData();
				vnfVl.getIpAllocationPools().forEach(y -> {
					final OsSubnetworkUow subUow = new OsSubnetworkUow(new VnfInstantiatedVirtualLink(), vnfVl, y, x.getToscaName());
					vertexPool.add(subUow);
					g.addVertex(subUow);
					g.addEdge(x, subUow);
					g.addEdge(subUow, noop);
					LOG.debug("Creating sub network : {}", subUow.getToscaName());
				});
				LOG.debug("Re-linking: {} <=> {}", noop.getName(), out);
				out.forEach(y -> g.addEdge(noop, y.getTarget()));
			}
		});

	}

	@Override
	public String createCompute(final VimConnectionInformation vimConnectionInformation, final String instanceName, final String flavorId, final String imageId, final List<String> networks, final List<String> storages, final String cloudInitData) {
		final OSClientV3 os = this.getClient(vimConnectionInformation);
		final ServerCreateBuilder bs = Builders.server();
		LOG.debug("Creating server flavor={}, image={}", flavorId, imageId);
		bs.image(imageId);
		bs.name(instanceName);
		bs.flavor(flavorId);
		if ((null != cloudInitData) && !cloudInitData.isEmpty()) {
			bs.userData(Base64.getEncoder().encodeToString(cloudInitData.getBytes()));
		}
		if (!networks.isEmpty()) {
			bs.networks(networks);
		}
		storages.forEach(x -> {
			final BlockDeviceMappingCreate blockDevice = Builders.blockDeviceMapping()
					.volumeId(x)
					.build();
			bs.blockDevice(blockDevice);
		});

		final Server res = os.compute().servers().boot(bs.build());
		return res.getId();
	}

	@Override
	public String createStorage(final VimConnectionInformation vimConnectionInformation, final VnfStorage vnfStorage, final String aliasName) {
		final OSClientV3 os = this.getClient(vimConnectionInformation);
		final Object imgName = vnfStorage.getSoftwareImage().getName();
		final Image image = os.compute().images().list().stream()
				.filter(x -> x.getName().equals(imgName) || x.getId().equals(imgName))
				.findFirst()
				.orElseThrow(() -> new NotFoundException("Image " + vnfStorage.getSoftwareImage().getName() + " not found"));
		final VolumeBuilder bv = Builders.volume();
		bv.size((int) (vnfStorage.getSize() / GIGA));
		bv.name(aliasName);
		bv.imageRef(image.getId());
		final Volume res = os.blockStorage().volumes().create(bv.build());
		waitForVolumeCompletion(os.blockStorage().volumes(), res);
		return res.getId();
	}

	private static void waitForVolumeCompletion(final BlockVolumeService volumes, final Volume volume) {
		Volume localVolume = volume;
		while ((localVolume.getStatus() == org.openstack4j.model.storage.block.Volume.Status.CREATING) || (localVolume.getStatus() == org.openstack4j.model.storage.block.Volume.Status.DOWNLOADING)) {
			LOG.info("Waiting for volume: {}", volume.getId());
			try {
				Thread.sleep(500);
			} catch (final InterruptedException e) {
				LOG.error("", e);
				Thread.currentThread().interrupt();
			}
			localVolume = volumes.get(volume.getId());
		}
		LOG.info("Volume {} Done with status: {}", localVolume.getId(), localVolume.getStatus());
	}

	@Override
	public Optional<SoftwareImage> getSwImageMatching(final VimConnectionInformation vimConnectionInformation, final SoftwareImage img) {
		final OSClientV3 os = this.getClient(vimConnectionInformation);
		// XXX: Checksum is not comparated, and checksum exist in os.image()
		final Optional<? extends Image> image = os.compute().images().list().stream()
				.filter(x -> x.getName().equals(img.getName()))
				.findFirst();
		if (image.isPresent()) {
			final SoftwareImage swImage = mapper.map(image.get(), SoftwareImage.class);
			return Optional.of(swImage);
		}
		return Optional.empty();
	}

	@Override
	public SoftwareImage uploadSoftwareImage(final VimConnectionInformation vimConnectionInformation, final SoftwareImage img) {
		final String imagePath = img.getImagePath();
		// XXX A little bit simple.
		if (imagePath.startsWith("http")) {
			try (Payload<URL> payload = Payloads.create(new URL(imagePath))) {
				return doUpload(vimConnectionInformation, img, payload);
			} catch (final IOException e) {
				throw new GenericException(e);
			}
		}
		try (Payload<File> payload = Payloads.create(new File(imagePath))) {
			return doUpload(vimConnectionInformation, img, payload);
		} catch (final IOException e) {
			throw new GenericException(e);
		}
	}

	private SoftwareImage doUpload(final VimConnectionInformation vimConnectionInformation, final SoftwareImage img, final Payload<?> payload) {
		final ImageBuilder bImg = Builders.image()
				.containerFormat(ContainerFormat.valueOf(img.getContainerFormat())).diskFormat(DiskFormat.valueOf(img.getDiskFormat()))
				.minDisk(img.getMinDisk())
				.minRam(img.getMinRam())
				.size(img.getSize());
		if (null != img.getChecksum()) {
			bImg.checksum(img.getChecksum().getHash());
		}
		final OSClientV3 os = this.getClient(vimConnectionInformation);
		final org.openstack4j.model.image.Image osImage = os.images().create(bImg.build(), payload);
		img.setProvider(img.getProvider());
		img.setVimId(osImage.getId());
		return img;
	}

	@Override
	public String getOrCreateFlavor(final VimConnectionInformation vimConnectionInformation, final String name, final int numVcpu, final long virtualMemorySize, final long disk) {
		final OSClientV3 os = this.getClient(vimConnectionInformation);
		LOG.debug("mem={} disk={}", (virtualMemorySize / MEGA), (disk / GIGA));
		final Optional<Flavor> matchingFlavor = os.compute().flavors().list()
				.stream()
				.filter(x -> x.getVcpus() == numVcpu)
				.filter(x -> x.getRam() == (virtualMemorySize / MEGA))
				.filter(x -> x.getDisk() == (disk / GIGA))
				.map(x -> (Flavor) x)
				.findFirst();
		// XXX We can't use name maybe use an UUID.
		return matchingFlavor.orElseGet(() -> os.compute()
				.flavors()
				.create(Builders.flavor()
						.disk((int) (disk / GIGA))
						.ram((int) (virtualMemorySize / MEGA))
						.vcpus(numVcpu)
						.isPublic(true)
						.name(name)
						.build()))
				.getId();
	}

	@Override
	public String createObjectStorage(final VimConnectionInformation vimConnectionInformation, final VnfStorage vnfStorage) {
		final OSClientV3 os = this.getClient(vimConnectionInformation);
		final ActionResponse res = os.objectStorage().containers().create(vnfStorage.getToscaName());
		LOG.debug("Object storage result success ? {}", res.isSuccess());
		return vnfStorage.getToscaName();
	}

	@Override
	public List<String> getZoneAvailableList(final VimConnectionInformation vimConnectionInformation) {
		final OSClientV3 os = this.getClient(vimConnectionInformation);
		final List<? extends AvailabilityZone> list = os.compute().zones().list();
		return list.stream().filter(x -> x.getZoneState().getAvailable())
				.map(AvailabilityZone::getZoneName)
				.collect(Collectors.toList());
	}

	@Override
	public String createRouter(final VimConnectionInformation vimConnectionInformation, final String name, final String internalNetworkId, final String externalNetworkId) {
		final OSClientV3 os = this.getClient(vimConnectionInformation);
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

	private static void checkResult(final ActionResponse action) {
		if (!action.isSuccess() && (action.getCode() != 404)) {
			throw new VimException(action.getCode() + " " + action.getFault());
		}
	}

	@Override
	public void deleteRouter(final VimConnectionInformation vimConnectionInformation, final String resourceId) {
		final OSClientV3 os = this.getClient(vimConnectionInformation);
		final List<? extends Port> routerList = os.networking().port().list();
		routerList.stream()
				.filter(x -> x.getDeviceId().equals(resourceId))
				.filter(x -> !x.getDeviceOwner().equals("network:router_gateway"))
				.map(Port::getId)
				.forEach(x -> os.networking().router().detachInterface(resourceId, null, x));
		checkResult(os.networking().router().delete(resourceId));
	}

	@Override
	public void deleteCompute(final VimConnectionInformation vimConnectionInformation, final String resourceId) {
		final OSClientV3 os = this.getClient(vimConnectionInformation);
		final ActionResponse res = os.compute().servers().delete(resourceId);
		if (409 == res.getCode()) {
			return;
		}
		int cnt = 100;
		while (cnt >= 0) {
			final Server res2 = os.compute().servers().get(resourceId);
			if (null == res2) {
				LOG.info("Compute resource {} released.", resourceId);
				return;
			}
			try {
				Thread.sleep(100);
			} catch (final InterruptedException e) {
				LOG.error("Interrupted", e);
				Thread.currentThread().interrupt();
			}
			cnt--;
		}
	}

	@Override
	public void deleteVirtualLink(final VimConnectionInformation vimConnectionInformation, final String resourceId) {
		final OSClientV3 os = this.getClient(vimConnectionInformation);
		checkResult(os.networking().network().delete(resourceId));
	}

	@Override
	public void deleteStorage(final VimConnectionInformation vimConnectionInformation, final String resourceId) {
		final OSClientV3 os = this.getClient(vimConnectionInformation);
		checkResult(os.blockStorage().volumes().delete(resourceId));
	}

	@Override
	public void deleteObjectStorage(final VimConnectionInformation vimConnectionInformation, final String resourceId) {
		final OSClientV3 os = this.getClient(vimConnectionInformation);
		checkResult(os.objectStorage().containers().delete(resourceId));
	}

	public void deleteSubnet(final VimConnectionInformation vimConnectionInformation, final String resourceId) {
		final OSClientV3 os = this.getClient(vimConnectionInformation);
		checkResult(os.networking().subnet().delete(resourceId));
	}

	@Override
	public List<ServerGroup> getServerGroup(final VimConnectionInformation vimConnectionInformation) {
		final OSClientV3 os = this.getClient(vimConnectionInformation);
		return os.compute().hostAggregates().list().stream().map(x -> new ServerGroup(x.getId(), x.getName(), x.getAvailabilityZone()))
				.collect(Collectors.toList());

	}

	@Override
	public Map<String, String> getPublicNetworks(final VimConnectionInformation vimConnectionInformation) {
		final OSClientV3 os = this.getClient(vimConnectionInformation);
		return os.networking().network().list().stream().filter(Network::isRouterExternal)
				.collect(Collectors.toMap(Network::getName, Network::getId));

	}

	@Override
	public void startServer(final VimConnectionInformation vimConnectionInformation, final String resourceId) {
		final OSClientV3 os = this.getClient(vimConnectionInformation);
		os.compute().servers().action(resourceId, Action.START);
	}

	@Override
	public void stopServer(final VimConnectionInformation vimConnectionInformation, final String resourceId) {
		final OSClientV3 os = this.getClient(vimConnectionInformation);
		os.compute().servers().action(resourceId, Action.STOP);
	}

	@Override
	public ResourceQuota getQuota(final VimConnectionInformation vimConnectionInformation) {
		final OSClientV3 os = this.getClient(vimConnectionInformation);
		final AbsoluteLimit usage = os.compute().quotaSets().limits().getAbsolute();
		final OsQuotas quotas = new OsQuotas();
		Optional.ofNullable(usage.getMaxSecurityGroups()).ifPresent(quotas::setSecurityGroupsMax);
		Optional.ofNullable(usage.getSecurityGroupRulesUsed()).ifPresent(quotas::setSecurityGroupsUsed);

		Optional.ofNullable(usage.getMaxTotalCores()).ifPresent(quotas::setVcpuMax);
		Optional.ofNullable(usage.getTotalCoresUsed()).ifPresent(quotas::setVcpuUsed);

		Optional.ofNullable(usage.getMaxTotalFloatingIps()).ifPresent(quotas::setFloatingIpMax);
		Optional.ofNullable(usage.getTotalFloatingIpsUsed()).ifPresent(quotas::setFloatingIpUsed);

		Optional.ofNullable(usage.getMaxTotalInstances()).ifPresent(quotas::setInstanceMax);
		Optional.ofNullable(usage.getTotalInstancesUsed()).ifPresent(quotas::setInstanceUsed);

		Optional.ofNullable(usage.getMaxTotalRAMSize()).ifPresent(x -> quotas.setRamMax(x * MEGA));
		Optional.ofNullable(usage.getTotalRAMUsed()).ifPresent(x -> quotas.setRamUsed(x * MEGA));

		Optional.ofNullable(usage.getMaxTotalKeypairs()).ifPresent(quotas::setKeyPairsMax);
		Optional.ofNullable(usage.getTotalKeyPairsUsed()).ifPresent(quotas::setKeyPairsUsed);
		return quotas;
	}
}