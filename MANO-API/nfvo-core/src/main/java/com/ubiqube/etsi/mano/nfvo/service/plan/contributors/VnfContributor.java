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
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.NsLiveInstance;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.dao.mano.NsdPackageVnfPackage;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
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
import com.ubiqube.etsi.mano.nfvo.factory.NsInstanceFactory;
import com.ubiqube.etsi.mano.nfvo.jpa.NsLiveInstanceJpa;
import com.ubiqube.etsi.mano.nfvo.service.NsInstanceService;
import com.ubiqube.etsi.mano.nfvo.service.graph.NsBundleAdapter;
import com.ubiqube.etsi.mano.nfvo.service.plan.contributors.vt.NsVnfVt;
import com.ubiqube.etsi.mano.orchestrator.nodes.Node;
import com.ubiqube.etsi.mano.orchestrator.nodes.nfvo.VnfNode;
import com.ubiqube.etsi.mano.service.VnfmInterface;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
@Transactional
public class VnfContributor extends AbstractNsContributor<NsVnfTask, NsVnfVt> {
	private final NsInstanceService nsInstanceService;
	private final VnfmInterface vnfm;
	private final NsLiveInstanceJpa nsLiveInstanceJpa;
	private final ServersJpa serversJpa;

	public VnfContributor(final NsInstanceService nsInstanceService, final VnfmInterface vnfm, final NsLiveInstanceJpa nsLiveInstanceJpa, final ServersJpa serversJpa) {
		this.nsInstanceService = nsInstanceService;
		this.vnfm = vnfm;
		this.nsLiveInstanceJpa = nsLiveInstanceJpa;
		this.serversJpa = serversJpa;
	}

	private static Set<String> getNetworks(final VnfPackage x) {
		return x.getVnfCompute().stream().flatMap(y -> y.getNetworks().stream()).collect(Collectors.toSet());
	}

	private List<NsVnfVt> doTerminatePlan(final NsdInstance instance) {
		final List<NsVnfVt> ret = new ArrayList<>();
		final List<NsLiveInstance> insts = nsLiveInstanceJpa.findByNsdInstanceAndClass(instance, NsVnfTask.class.getSimpleName());
		insts.stream().forEach(x -> {
			final NsVnfTask task = (NsVnfTask) x.getNsTask();
			final NsVnfTask nt = createDeleteTask(NsVnfTask::new, x);
			final Set<String> nets = getNetworks(task.getNsPackageVnfPackage().getVnfPackage());
			nt.setExternalNetworks(nets);
			nt.setVnfInstance(task.getVnfInstance());
			ret.add(new NsVnfVt(nt));
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
		return VnfNode.class;
	}

	@Override
	protected List<NsVnfVt> nsContribute(final NsBundleAdapter bundle, final NsBlueprint blueprint) {
		if (blueprint.getOperation() == PlanOperationType.TERMINATE) {
			return doTerminatePlan(blueprint.getInstance());
		}
		final Set<VnfPackage> vnfs = nsInstanceService.findVnfPackageByNsInstance(bundle.nsPackage());
		return vnfs.stream()
				.filter(x -> 0 == nsInstanceService.countLiveInstanceOfVnf(blueprint.getNsInstance(), x.getId()))
				.map(x -> {
					final NsVnfTask vnf = createTask(NsVnfTask::new);
					vnf.setChangeType(ChangeType.ADDED);
					final NsdPackageVnfPackage nsPackageVnfPackage = find(x, bundle.nsPackage().getVnfPkgIds());
					final Set<String> nets = getNetworks(x);
					vnf.setExternalNetworks(nets);
					vnf.setNsPackageVnfPackage(nsPackageVnfPackage);
					final Servers server = selectServer(x);
					vnf.setServer(server);
					final VnfInstance vnfmVnfInstance = vnfm.createVnfInstance(server, x, "VNF instance hold by: " + blueprint.getNsInstance().getId(), x.getId().toString());
					final VnfInstance vnfInstance = NsInstanceFactory.createNsInstancesNsInstanceVnfInstance(vnfmVnfInstance, x);
					vnfInstance.setNsInstance(blueprint.getNsInstance());
					vnf.setVnfInstance(vnfInstance.getId().toString());
					vnf.setAlias(nsPackageVnfPackage.getToscaName());
					vnf.setToscaName(nsPackageVnfPackage.getToscaName());

					return new NsVnfVt(vnf);
				}).toList();
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
		return available.get(0);
	}

}
