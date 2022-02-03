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
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.NsLiveInstance;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.dao.mano.NsdPackageVnfPackage;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.config.ServerType;
import com.ubiqube.etsi.mano.dao.mano.config.Servers;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanOperationType;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanStatusType;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsBlueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsVnfTask;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.jpa.config.ServersJpa;
import com.ubiqube.etsi.mano.nfvo.jpa.NsLiveInstanceJpa;
import com.ubiqube.etsi.mano.nfvo.service.NsInstanceService;
import com.ubiqube.etsi.mano.nfvo.service.graph.NsBundleAdapter;
import com.ubiqube.etsi.mano.nfvo.service.plan.contributors.vt.NsVnfCreateVt;
import com.ubiqube.etsi.mano.nfvo.service.plan.contributors.vt.NsVnfInstantiateVt;
import com.ubiqube.etsi.mano.nfvo.service.plan.contributors.vt.NsVtBase;
import com.ubiqube.etsi.mano.orchestrator.nodes.Node;
import com.ubiqube.etsi.mano.orchestrator.nodes.nfvo.VnfInstantiateNode;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
@Transactional
public class VnfContributor extends AbstractNsContributor<NsVnfTask, NsVtBase<NsVnfTask>> {
	private final NsInstanceService nsInstanceService;
	private final NsLiveInstanceJpa nsLiveInstanceJpa;
	private final ServersJpa serversJpa;
	private final Random rand = new Random();

	public VnfContributor(final NsInstanceService nsInstanceService, final NsLiveInstanceJpa nsLiveInstanceJpa, final ServersJpa serversJpa) {
		this.nsInstanceService = nsInstanceService;
		this.nsLiveInstanceJpa = nsLiveInstanceJpa;
		this.serversJpa = serversJpa;
	}

	private static Set<String> getNetworks(final VnfPackage x) {
		return x.getVnfCompute().stream().flatMap(y -> y.getNetworks().stream()).collect(Collectors.toSet());
	}

	private List<NsVtBase<NsVnfTask>> doTerminatePlan(final NsdInstance instance) {
		final List<NsVtBase<NsVnfTask>> ret = new ArrayList<>();
		final List<NsLiveInstance> insts = nsLiveInstanceJpa.findByNsdInstanceAndClass(instance, NsVnfTask.class.getSimpleName());
		insts.stream().forEach(x -> {
			final NsVnfTask task = (NsVnfTask) x.getNsTask();
			final NsVnfTask nt = createDeleteTask(NsVnfTask::new, x);
			final Set<String> nets = getNetworks(task.getNsPackageVnfPackage().getVnfPackage());
			nt.setExternalNetworks(nets);
			nt.setVimResourceId(task.getVimResourceId());
			ret.add(new NsVnfCreateVt(nt));
			ret.add(new NsVnfInstantiateVt(nt));
		});
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
				.filter(x -> 0 == nsInstanceService.countLiveInstanceOfVnf(blueprint.getNsInstance(), x.getId()))
				.forEach(x -> {
					final NsVnfTask vnf = createTask(NsVnfTask::new);
					vnf.setChangeType(ChangeType.ADDED);
					final NsdPackageVnfPackage nsPackageVnfPackage = find(x, bundle.nsPackage().getVnfPkgIds());
					final Set<String> nets = getNetworks(x);
					vnf.setExternalNetworks(nets);
					vnf.setNsPackageVnfPackage(nsPackageVnfPackage);
					final Servers server = selectServer(x);
					vnf.setServer(server);
					vnf.setAlias(nsPackageVnfPackage.getToscaName());
					vnf.setToscaName(nsPackageVnfPackage.getToscaName());
					vnf.setFlavourId("flavour");
					vnf.setVnfdId(nsPackageVnfPackage.getVnfPackage().getVnfdId());
					ret.add(new NsVnfCreateVt(vnf));
					ret.add(new NsVnfInstantiateVt(vnf));
				});
		return ret;
	}

	private Servers selectServer(final VnfPackage vnfPackage) {
		final List<Servers> servers = serversJpa.findByServerTypeAndServerStatusIn(ServerType.VNFM, Arrays.asList(PlanStatusType.SUCCESS));
		if (servers.isEmpty()) {
			return null;
		}
		final Set<String> vnfmInfos = vnfPackage.getVnfmInfo();
		if (vnfmInfos.isEmpty()) {
			return null;
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
