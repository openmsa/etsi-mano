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
package com.ubiqube.etsi.mano.vnfm.service.plan.contributors.v2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import javax.annotation.Priority;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.ExtManagedVirtualLinkDataEntity;
import com.ubiqube.etsi.mano.dao.mano.ExtVirtualLinkDataEntity;
import com.ubiqube.etsi.mano.dao.mano.ResourceTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfLiveInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.VnfVl;
import com.ubiqube.etsi.mano.dao.mano.common.ListKeyPair;
import com.ubiqube.etsi.mano.dao.mano.v2.ExternalCpTask;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanOperationType;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.orchestrator.Bundle;
import com.ubiqube.etsi.mano.orchestrator.nodes.Node;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.VnfExtCp;
import com.ubiqube.etsi.mano.vnfm.jpa.VnfLiveInstanceJpa;
import com.ubiqube.etsi.mano.vnfm.service.graph.VnfBundleAdapter;
import com.ubiqube.etsi.mano.vnfm.service.plan.contributors.v2.vt.PortVt;
import com.ubiqube.etsi.mano.vnfm.service.plan.contributors.v2.vt.VnfExtCpVt;
import com.ubiqube.etsi.mano.vnfm.service.plan.contributors.v2.vt.VnfPreExtCp;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
@Priority(100)
public class VnfExtCpContributor extends AbstractContributorV2Base<ExternalCpTask, VnfExtCpVt> {

	private static final Logger LOG = LoggerFactory.getLogger(VnfExtCpContributor.class);

	private final VnfLiveInstanceJpa vnfLiveInstanceJpa;

	public VnfExtCpContributor(final VnfLiveInstanceJpa vnfLiveInstanceJpa) {
		super();
		this.vnfLiveInstanceJpa = vnfLiveInstanceJpa;
	}

	@Override
	public List<VnfExtCpVt> vnfContribute(final Bundle bundle, final VnfBlueprint plan) {
		if (plan.getOperation() == PlanOperationType.CHANGE_EXTERNAL_VNF_CONNECTIVITY) {
			return changeExtCp(bundle, plan);
		}
		if (plan.getOperation() == PlanOperationType.TERMINATE) {
			return doTerminatePlan(plan.getVnfInstance());
		}
		final List<VnfLiveInstance> instances = vnfLiveInstanceJpa.findByVnfInstanceIdAndClass(plan.getVnfInstance(), ExternalCpTask.class.getSimpleName());
		final VnfPackage vnfPackage = ((VnfBundleAdapter) bundle).vnfPackage();
		final List ret = new ArrayList<>();
		vnfPackage.getVnfExtCp().stream().forEach(vnfExtCp -> {
			if (!have(instances, vnfExtCp.getToscaName())) {
				handleExtCp(plan, vnfPackage, ret, vnfExtCp);
			} else {
				final Optional<VnfCompute> compute = vnfPackage.getVnfCompute().stream()
						.peek(y -> LOG.debug(" '{}' = '{}' => {}", y.getToscaName(), vnfExtCp.getInternalVirtualLink(), y.getToscaName().equals(vnfExtCp.getInternalVirtualLink())))
						.filter(y -> y.getToscaName().equals(vnfExtCp.getInternalVirtualLink()))
						.findFirst();
				compute.ifPresent(y -> {
					final ExternalCpTask task = createTask(ExternalCpTask::new);
					task.setToscaName(vnfExtCp.getToscaName());
					task.setAlias(y.getToscaName() + "-" + vnfExtCp.getToscaName());
					task.setChangeType(ChangeType.ADDED);
					task.setType(ResourceTypeEnum.LINKPORT);
					task.setVnfExtCp(vnfExtCp);
					task.setPort(true);
					ret.add(new PortVt(task));
				});
			}
		});
		return ret;
	}

	private static void handleExtCp(final VnfBlueprint plan, final VnfPackage vnfPackage, final List ret, final com.ubiqube.etsi.mano.dao.mano.VnfExtCp vnfExtCp) {
		final Optional<VnfVl> vl = vnfPackage.getVnfVl().stream()
				.filter(y -> y.getToscaName().equals(vnfExtCp.getInternalVirtualLink()))
				.findFirst();
		if (vl.isPresent()) {
			final ExternalCpTask task = addTask(vnfExtCp, vl.get().getToscaName());
			ret.add(new VnfExtCpVt(task));
		} else {
			final Optional<String> vlName = findVl(vnfPackage, vnfExtCp.getToscaName());
			if (vlName.isPresent()) {
				// Early VnfExCp, informations will came from InstantiateRequest.
				// XXX: This is not the correct class, it should be a ExtVirtualLinkInfoEntity
				// class.
				final Set<ExtManagedVirtualLinkDataEntity> extVl = plan.getParameters().getExtManagedVirtualLinks();
				final Optional<ExtManagedVirtualLinkDataEntity> ex = extVl.stream()
						.filter(z -> z.getVnfVirtualLinkDescId().equals(vlName.get()))
						.findFirst();
				if (ex.isPresent()) {
					//
					final ExternalCpTask t = addTask(vnfExtCp, ex.get().getVnfVirtualLinkDescId());
					t.setPreExtCpResourceId(ex.get().getResourceId());
					ret.add(new VnfPreExtCp(t));
				}
			}
		}
	}

	private static ExternalCpTask addTask(final com.ubiqube.etsi.mano.dao.mano.VnfExtCp vnfExtCp, final String toscaName) {
		final ExternalCpTask task = createTask(ExternalCpTask::new);
		task.setToscaName(vnfExtCp.getToscaName());
		task.setAlias(toscaName + "-" + vnfExtCp.getToscaName());
		task.setChangeType(ChangeType.ADDED);
		task.setType(ResourceTypeEnum.LINKPORT);
		task.setVnfExtCp(vnfExtCp);
		return task;
	}

	private static Optional<String> findVl(final VnfPackage vnfPackage, final String externalVirtualLink) {
		return vnfPackage.getVirtualLinks().stream()
				.filter(Objects::nonNull)
				.filter(x -> x.getValue().equals(externalVirtualLink))
				.map(VnfExtCpContributor::getVl)
				.findFirst();
	}

	private static String getVl(final ListKeyPair kp) {
		if (kp.getIdx() == 0) {
			return "virtual_link";
		}
		return "virtual_link_" + kp.getIdx();
	}

	private static boolean have(final List<VnfLiveInstance> instances, final String toscaName) {
		return instances.stream().anyMatch(x -> x.getTask().getToscaName().equals(toscaName));
	}

	private List doTerminatePlan(final VnfInstance vnfInstance) {
		final List<VnfLiveInstance> instances = vnfLiveInstanceJpa.findByVnfInstanceIdAndClass(vnfInstance, ExternalCpTask.class.getSimpleName());
		final List ret = new ArrayList<>();
		instances.stream().forEach(x -> ret.add(deleteVli(x)));
		return ret;
	}

	private static Object deleteVli(final VnfLiveInstance x) {
		final ExternalCpTask extCp = (ExternalCpTask) x.getTask();
		if (extCp.getPort() != null && extCp.getPort()) {
			final ExternalCpTask task = createDeleteTask(ExternalCpTask::new, x);
			task.setType(ResourceTypeEnum.LINKPORT);
			task.setVnfExtCp(((ExternalCpTask) x.getTask()).getVnfExtCp());
			task.setPort(true);
			return new PortVt(task);
		}
		final ExternalCpTask task = createDeleteTask(ExternalCpTask::new, x);
		task.setType(ResourceTypeEnum.LINKPORT);
		task.setVnfExtCp(((ExternalCpTask) x.getTask()).getVnfExtCp());
		return new VnfExtCpVt(task);
	}

	private List<VnfExtCpVt> changeExtCp(final Bundle bundle, final VnfBlueprint plan) {
		final List<VnfExtCpVt> ret = new ArrayList<>();
		final VnfInstance vnfInstance = plan.getInstance();
		final Set<ExtVirtualLinkDataEntity> evl = plan.getChangeExtVnfConnRequest().getExtVirtualLinks();
		evl.forEach(x -> x.getExtCps().forEach(y -> {
			final List<VnfLiveInstance> vli = vnfLiveInstanceJpa.findByTaskVnfInstanceAndToscaName(vnfInstance, y.getCpdId());
			deleteVli(vli.get(0));
		}));

		return ret;
	}

	@Override
	public Class<? extends Node> getNode() {
		return VnfExtCp.class;
	}

}
