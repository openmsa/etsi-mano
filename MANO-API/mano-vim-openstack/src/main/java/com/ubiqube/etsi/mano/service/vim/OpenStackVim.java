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

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.validation.constraints.Null;

import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.api.client.IOSClientBuilder.V3;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.common.Extension;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.model.compute.AbsoluteLimit;
import org.openstack4j.model.compute.Action;
import org.openstack4j.model.compute.BlockDeviceMappingCreate;
import org.openstack4j.model.compute.Flavor;
import org.openstack4j.model.compute.Server;
import org.openstack4j.model.compute.builder.ServerCreateBuilder;
import org.openstack4j.model.compute.ext.AvailabilityZone;
import org.openstack4j.model.network.Agent;
import org.openstack4j.openstack.OSFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.GrantInformationExt;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.service.VimService;
import com.ubiqube.etsi.mano.service.vim.mon.VimMonitoring;

import ma.glasnost.orika.MapperFacade;

@Service
public class OpenStackVim implements Vim {
	private static final long GIGA = 1024 * 1024 * 1024L;

	private static final long MEGA = 1048576L;

	private static final Logger LOG = LoggerFactory.getLogger(OpenStackVim.class);

	private static final ThreadLocal<Map<String, OSClientV3>> sessions = new ThreadLocal<>();

	private final VimService vciJpa;

	private final MapperFacade mapper;

	public OpenStackVim(final VimService _vciJpa, final MapperFacade _mapper) {
		vciJpa = _vciJpa;
		mapper = _mapper;
		LOG.info("Booting Openstack VIM.\n" +
				"   ___  ___   __   _____ __  __ \n" +
				"  / _ \\/ __|__\\ \\ / /_ _|  \\/  |\n" +
				" | (_) \\__ \\___\\ V / | || |\\/| |\n" +
				"  \\___/|___/    \\_/ |___|_|  |_|\n");
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
			return authenticate(vim.orElseThrow(() -> new VimException("Unable to find Vim " + x)));
		});
	}

	@Override
	public void allocateResources(final VimConnectionInformation vimConnectionInformation, final GrantInformationExt grantInformation) {
		final OSClientV3 os = this.getClient(vimConnectionInformation);
		// XXX Do placement with blazar.
	}

	@Override
	public void freeResources(final VimConnectionInformation vimConnectionInformation, final GrantInformationExt grantInformation) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getType() {
		return "OPENSTACK_V3";
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
	public List<String> getZoneAvailableList(final VimConnectionInformation vimConnectionInformation) {
		final OSClientV3 os = this.getClient(vimConnectionInformation);
		final List<? extends AvailabilityZone> list = os.compute().zones().list();
		return list.stream().filter(x -> x.getZoneState().getAvailable())
				.map(AvailabilityZone::getZoneName)
				.collect(Collectors.toList());
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
	public List<ServerGroup> getServerGroup(final VimConnectionInformation vimConnectionInformation) {
		final OSClientV3 os = this.getClient(vimConnectionInformation);
		return os.compute().hostAggregates().list().stream().map(x -> new ServerGroup(x.getId(), x.getName(), x.getAvailabilityZone()))
				.collect(Collectors.toList());

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

	@Override
	public List<VimCapability> getCaps(final VimConnectionInformation vimConnectionInformation) {
		final ExecutorService tpe = Executors.newFixedThreadPool(5);
		final CompletionService<List<VimCapability>> completionService = new ExecutorCompletionService<>(tpe);
		final List<? extends Extension> exts;
		completionService.submit(getExtensions(vimConnectionInformation));
		completionService.submit(getAgents(vimConnectionInformation));
		int received = 0;
		final List<VimCapability> res = new ArrayList<>();
		res.add(VimCapability.REQUIRE_SUBNET_ALLOCATION);
		Exception ex = null;
		while (received < 2) {
			try {
				final Future<List<VimCapability>> resultFuture = completionService.take();
				res.addAll(resultFuture.get());
				received++;
			} catch (final InterruptedException e) {
				ex = e;
				LOG.info("", e);
				Thread.currentThread().interrupt();
				break;
			} catch (final ExecutionException e) {
				ex = e;
				break;
			}
		}
		tpe.shutdown();
		try {
			tpe.awaitTermination(5, TimeUnit.MINUTES);
		} catch (final InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new VimException(e);
		}
		if (null != ex) {
			throw new VimException(ex);
		}
		return res;
	}

	private Callable<List<VimCapability>> getExtensions(final VimConnectionInformation vimConnectionInformation) {
		return () -> {
			final OSClientV3 os = this.getClient(vimConnectionInformation);
			final List<? extends Extension> list = os.networking().network().listExtensions();
			return list.stream()
					.map(this::convertExtenstionToCaps)
					.filter(Objects::nonNull)
					.collect(Collectors.toList());
		};
	}

	private Callable<List<VimCapability>> getAgents(final VimConnectionInformation vimConnectionInformation) {
		return () -> {
			final OSClientV3 os = this.getClient(vimConnectionInformation);
			final List<? extends Agent> list = os.networking().agent().list();
			return list.stream()
					.map(this::convertAgentToCaps)
					.filter(Objects::nonNull)
					.collect(Collectors.toList());
		};
	}

	private VimCapability convertExtenstionToCaps(final Extension ext) {
		if ("dns-integration".equals(ext.getAlias())) {
			return VimCapability.HAVE_DNS;
		} else if ("availability_zone".equals(ext.getAlias())) {
			return VimCapability.HAVE_AVAILABILITY_ZONE;
		} else if ("bgp".equals(ext.getAlias())) {
			return VimCapability.HAVE_BGP;
		} else if ("net-mtu".equals(ext.getAlias())) {
			return VimCapability.HAVE_NET_MTU;
		} else if ("qos".equals(ext.getAlias())) {
			return VimCapability.HAVE_QOS;
		} else if ("router".equals(ext.getAlias())) {
			return VimCapability.HAVE_ROUTER;
		} else if ("vlan-transparent".equals(ext.getAlias())) {
			return VimCapability.HAVE_VLAN_TRANSPARENT;
		} else if ("extra_dhcp_opt".equals(ext.getAlias())) {
			return VimCapability.HAVE_DHCP;
		}
		return null;
	}

	private VimCapability convertAgentToCaps(final Agent agent) {
		if ("neutron-openvswitch-agent".equals(agent.getBinary())) {
			return VimCapability.HAVE_VXNET;
		}
		return null;
	}

	@Override
	public @Null VimMonitoring getMonitoring(final VimConnectionInformation vimConnectionInformation) {
		final OSClientV3 os = this.getClient(vimConnectionInformation);
		return new OpenstackMonitoring(os);
	}

	@Override
	public com.ubiqube.etsi.mano.service.vim.Network network(final VimConnectionInformation vimConnectionInformation) {
		final OSClientV3 os = this.getClient(vimConnectionInformation);
		return new OsNetwork(os, vimConnectionInformation);
	}

	@Override
	public Storage storage(final VimConnectionInformation vimConnectionInformation) {
		final OSClientV3 os = this.getClient(vimConnectionInformation);
		return new OsStorage(os, mapper);
	}

	@Override
	public Dns dns(final VimConnectionInformation vimConnectionInformation) {
		final OSClientV3 os = this.getClient(vimConnectionInformation);
		return new OsDns(os);
	}

}