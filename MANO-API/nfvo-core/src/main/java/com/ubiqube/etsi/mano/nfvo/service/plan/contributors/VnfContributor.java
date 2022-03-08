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
package com.ubiqube.etsi.mano.nfvo.service.plan.contributors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.NsLiveInstance;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.dao.mano.NsdPackageVnfPackage;
import com.ubiqube.etsi.mano.dao.mano.ResourceTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.common.ListKeyPair;
import com.ubiqube.etsi.mano.dao.mano.config.ServerType;
import com.ubiqube.etsi.mano.dao.mano.config.Servers;
import com.ubiqube.etsi.mano.dao.mano.nsd.CpPair;
import com.ubiqube.etsi.mano.dao.mano.nsd.VnffgDescriptor;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanOperationType;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanStatusType;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.ExternalPortRecord;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsBlueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsVnfTask;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.jpa.config.ServersJpa;
import com.ubiqube.etsi.mano.nfvo.jpa.NsLiveInstanceJpa;
import com.ubiqube.etsi.mano.nfvo.service.NsInstanceService;
import com.ubiqube.etsi.mano.nfvo.service.graph.NsBundleAdapter;
import com.ubiqube.etsi.mano.nfvo.service.plan.contributors.vt.NsVnfCreateVt;
import com.ubiqube.etsi.mano.orchestrator.nodes.Node;
import com.ubiqube.etsi.mano.orchestrator.nodes.nfvo.VnfInstantiateNode;
import com.ubiqube.etsi.mano.service.NsScaleStrategy;
import com.ubiqube.etsi.mano.service.graph.vt.NsVtBase;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
@Transactional
public class VnfContributor extends AbstractNsContributor<NsVnfTask, NsVtBase<NsVnfTask>> {
	private static final Logger LOG = LoggerFactory.getLogger(VnfContributor.class);

	private final NsInstanceService nsInstanceService;
	private final NsLiveInstanceJpa nsLiveInstanceJpa;
	private final ServersJpa serversJpa;
	private final NsScaleStrategy nsScaleStrategy;
	private final Random rand = new Random();

	public VnfContributor(final NsInstanceService nsInstanceService, final NsLiveInstanceJpa nsLiveInstanceJpa, final ServersJpa serversJpa, final NsScaleStrategy nsScaleStrategy) {
		this.nsInstanceService = nsInstanceService;
		this.nsLiveInstanceJpa = nsLiveInstanceJpa;
		this.serversJpa = serversJpa;
		this.nsScaleStrategy = nsScaleStrategy;
	}

	private static Set<ExternalPortRecord> getNetworks(final VnfPackage vnfPackage) {
		final Set<ExternalPortRecord> ret = new HashSet<>();
		final Set<ExternalPortRecord> n = vnfPackage.getVnfCompute().stream()
				.flatMap(y -> y.getNetworks().stream())
				.map(y -> new ExternalPortRecord(y, null))
				.collect(Collectors.toSet());
		ret.addAll(n);
		return ret;
	}

	private static List<CpPair> getForwarder(final Set<VnffgDescriptor> vnffg) {
		final Set<ExternalPortRecord> ret = new HashSet<>();
		vnffg.stream()
				.flatMap(x -> x.getNfpd().stream())
				.flatMap(x -> x.getInstancces().stream())
				.flatMap(x -> x.getPairs().stream()).forEach(x -> {
					if (null != x.getEgressVl()) {
						ret.add(new ExternalPortRecord(x.getEgressVl(), null));
					}
					if (null != x.getIngressVl()) {
						ret.add(new ExternalPortRecord(x.getIngressVl(), null));
					}
				});
		return List.of();
	}

	private List<NsVtBase<NsVnfTask>> doTerminatePlan(final NsdInstance instance) {
		final List<NsVtBase<NsVnfTask>> ret = new ArrayList<>();
		final List<NsLiveInstance> insts = nsLiveInstanceJpa.findByNsdInstanceAndClass(instance, NsVnfTask.class.getSimpleName());
		int i = 0;
		for (final NsLiveInstance nsLiveInstance : insts) {
			final NsVnfTask task = (NsVnfTask) nsLiveInstance.getNsTask();
			final NsVnfTask nt = createDeleteTask(NsVnfTask::new, nsLiveInstance);
			final Set<ExternalPortRecord> nets = task.getNsPackageVnfPackage().getVirtualLinks().stream()
					.filter(x -> x.getValue() != null)
					.map(x -> new ExternalPortRecord(x.getValue(), null))
					.collect(Collectors.toSet());
			nt.setExternalNetworks(nets);
			nt.setVimResourceId(nsLiveInstance.getResourceId());
			nt.setServer(task.getServer());
			nt.setAlias(getToscaName(insts.get(i).getNsTask().getToscaName(), i));
			nt.setToscaName(getToscaName(nsLiveInstance.getNsTask().getAlias(), i++));
			nt.setType(ResourceTypeEnum.VNF);
			nt.setNsdId(instance.getNsdInfo().getId());
			nt.setNsPackageVnfPackage(task.getNsPackageVnfPackage());
			ret.add(new NsVnfCreateVt(nt));
		}
		return ret;
	}

	private static NsdPackageVnfPackage find(final VnfPackage vnfPackage, final Set<NsdPackageVnfPackage> vnfPkgIds) {
		return vnfPkgIds.stream()
				.filter(x -> x.getVnfPackage().getId().compareTo(vnfPackage.getId()) == 0)
				.findFirst().orElseThrow(() -> new NotFoundException("VNF Package not found: " + vnfPackage.getId()));
	}

	@Override
	public Class<? extends Node> getNode() {
		return VnfInstantiateNode.class;
	}

	@Override
	protected List<NsVtBase<NsVnfTask>> nsContribute(final NsBundleAdapter bundle, final NsBlueprint blueprint) {
		if (blueprint.getOperation() == PlanOperationType.TERMINATE) {
			return doTerminatePlan(blueprint.getInstance());
		}
		final Set<VnfPackage> vnfs = nsInstanceService.findVnfPackageByNsInstance(bundle.nsPackage());
		final List<NsVtBase<NsVnfTask>> ret = new ArrayList<>();
		vnfs.stream()
				.forEach(x -> {
					final NsdPackageVnfPackage nsPackageVnfPackage = find(x, bundle.nsPackage().getVnfPkgIds());
					final int curr = nsInstanceService.countLiveInstanceOfVnf(blueprint.getNsInstance(), nsPackageVnfPackage.getToscaName() + "%");
					final int inst = nsScaleStrategy.getNumberOfInstances(nsPackageVnfPackage, blueprint);
					LOG.info("VNF curr: {} <=> inst: {}", curr, inst);
					if (curr > inst) {
						remove(curr - inst, blueprint.getInstance(), ret);
					} else if (curr < inst) {
						add(curr, inst, x, nsPackageVnfPackage, ret, bundle.nsPackage().getVnffgs(), bundle.nsPackage().getId());
					}
				});
		return ret;
	}

	private void remove(final int cnt, final NsdInstance instance, final List<NsVtBase<NsVnfTask>> ret) {
		final List<NsLiveInstance> insts = nsLiveInstanceJpa.findByNsdInstanceAndClass(instance, NsVnfTask.class.getSimpleName());
		for (int i = 0; i < cnt; i++) {
			final NsVnfTask task = (NsVnfTask) insts.get(i).getNsTask();
			final NsVnfTask nt = createDeleteTask(NsVnfTask::new, insts.get(i));
			final Set<ExternalPortRecord> nets = getNetworks(task.getNsPackageVnfPackage().getVnfPackage());
			nt.setExternalNetworks(nets);
			nt.setVimResourceId(insts.get(i).getResourceId());
			nt.setToscaName(getToscaName(insts.get(i).getNsTask().getToscaName(), i));
			nt.setAlias(getToscaName(insts.get(i).getNsTask().getToscaName(), i));
			nt.setServer(task.getServer());
			nt.setNsdId(instance.getNsdInfo().getId());
			ret.add(new NsVnfCreateVt(nt));
		}
	}

	private void add(final int curr, final int cnt, final VnfPackage vnfPkg, final NsdPackageVnfPackage nsPackageVnfPackage, final List<NsVtBase<NsVnfTask>> ret, final Set<VnffgDescriptor> vnffg, final UUID nsdId) {
		for (int i = curr; i < cnt; i++) {
			final String newName = getToscaName(nsPackageVnfPackage.getToscaName(), i);
			LOG.debug("VNF inst Creating: {}", newName);
			final NsVnfTask vnf = createTask(NsVnfTask::new);
			vnf.setChangeType(ChangeType.ADDED);
			final Set<ExternalPortRecord> nets = getNetworks(vnfPkg);
			nets.addAll(getVl(nsPackageVnfPackage));
			vnf.setExternalNetworks(nets);
			vnf.setNsPackageVnfPackage(nsPackageVnfPackage);
			final Servers server = selectServer(vnfPkg);
			vnf.setServer(server);
			vnf.setAlias(newName);
			vnf.setToscaName(newName);
			vnf.setFlavourId("flavour");
			vnf.setVnfdId(nsPackageVnfPackage.getVnfPackage().getVnfdId());
			vnf.setType(ResourceTypeEnum.VNF);
			vnf.setNsdId(nsdId);
			ret.add(new NsVnfCreateVt(vnf));
		}
	}

	private static Set<ExternalPortRecord> getVl(final NsdPackageVnfPackage nsPackageVnfPackage) {
		return nsPackageVnfPackage.getVirtualLinks().stream()
				.filter(x -> x.getValue() != null)
				.map(x -> new ExternalPortRecord(x.getValue(), getVlName(x)))
				.collect(Collectors.toSet());
	}

	private static String getVlName(final ListKeyPair x) {
		if (x.getIdx() == 0) {
			return "virtual_link";
		}
		return "virtual_link_" + x.getIdx();
	}

	private static String getToscaName(final String nsPackageVnfPackage, final int instanceNumber) {
		return nsPackageVnfPackage + "-" + String.format("%04d", instanceNumber);
	}

	private Servers selectServer(final VnfPackage vnfPackage) {
		final List<Servers> servers = serversJpa.findByServerTypeAndServerStatusIn(ServerType.VNFM, Arrays.asList(PlanStatusType.SUCCESS));
		if (servers.isEmpty()) {
			return null;
		}
		final Set<String> vnfmInfos = vnfPackage.getVnfmInfo();
		if (vnfmInfos.isEmpty()) {
			return servers.get(rand.nextInt(servers.size()));
		}
		final List<Servers> available = servers.stream()
				.filter(x -> {
					final List<String> s = x.getCapabilities().stream().filter(vnfmInfos::contains).toList();
					return !s.isEmpty();
				})
				.toList();
		if (available.isEmpty()) {
			throw new GenericException("No VNFM server found for the following requirements: " + vnfmInfos);
		}
		return available.get(rand.nextInt(available.size()));
	}

}
