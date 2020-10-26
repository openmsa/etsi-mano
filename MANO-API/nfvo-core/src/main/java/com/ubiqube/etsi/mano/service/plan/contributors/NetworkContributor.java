package com.ubiqube.etsi.mano.service.plan.contributors;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.ResourceTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.ScaleInfo;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.VnfVl;
import com.ubiqube.etsi.mano.dao.mano.v2.Blueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.NetworkTask;
import com.ubiqube.etsi.mano.dao.mano.v2.Task;
import com.ubiqube.etsi.mano.service.BlueprintService;
import com.ubiqube.etsi.mano.service.VnfPackageService;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;
import com.ubiqube.etsi.mano.service.graph.vnfm.VirtualLinkUow;
import com.ubiqube.etsi.mano.service.vim.node.Network;
import com.ubiqube.etsi.mano.service.vim.node.Node;

@Service
public class NetworkContributor extends AbstractPlanContributor {
	private final BlueprintService planService;
	private final VnfPackageService vnfPackageService;

	public NetworkContributor(final BlueprintService _planService, final VnfPackageService _vnfPackageService) {
		planService = _planService;
		vnfPackageService = _vnfPackageService;
	}

	@Override
	public Class<? extends Node> getContributionType() {
		return Network.class;
	}

	@Override
	public List<Task> contribute(final VnfPackage vnfPackage, final Blueprint plan, final Set<ScaleInfo> scaling) {
		final ArrayList<Task> ret = new ArrayList<>();
		final Set<VnfVl> vls = vnfPackage.getVnfVl();
		for (final VnfVl vnfVl : vls) {
			final int num = planService.getNumberOfLiveVl(plan.getVnfInstance(), vnfVl);
			if (num == 0) {
				final NetworkTask networkTask = createTask(NetworkTask::new);
				networkTask.setAlias(vnfVl.getToscaName());
				networkTask.setToscaName(vnfVl.getToscaName());
				networkTask.setType(ResourceTypeEnum.VL);
				networkTask.setVnfVl(vnfVl);
				ret.add(networkTask);
			}
		}
		return ret;
	}

	@Override
	public List<UnitOfWork> convertTasksToExecNode(final Set<Task> tasks, final Blueprint plan) {
		final ArrayList<UnitOfWork> ret = new ArrayList<>();
		tasks.stream()
				.filter(x -> x instanceof NetworkTask)
				.forEach(x -> {
					final NetworkTask networkTask = (NetworkTask) x;
					final VnfVl vnfVl = vnfPackageService.findVirtualLnkById(networkTask.getVnfVl().getId()).orElseThrow();
					ret.add(new VirtualLinkUow(networkTask, vnfVl));
				});
		return ret;
	}

}
