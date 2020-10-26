package com.ubiqube.etsi.mano.service.plan.contributors;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.ResourceTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.ScaleInfo;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.dao.mano.v2.Blueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.StorageTask;
import com.ubiqube.etsi.mano.dao.mano.v2.Task;
import com.ubiqube.etsi.mano.service.VnfPackageService;
import com.ubiqube.etsi.mano.service.graph.vnfm.StorageUow;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;
import com.ubiqube.etsi.mano.service.vim.node.Node;
import com.ubiqube.etsi.mano.service.vim.node.Storage;

@Service
public class StorageContributor implements PlanContributor {
	VnfPackageService VnfPackageService;

	@Override
	public Class<? extends Node> getContributionType() {
		return Storage.class;
	}

	@Override
	public List<Task> contribute(final VnfPackage vnfPackage, final Blueprint plan, final Set<ScaleInfo> scaling) {
		final List<Task> ret = new ArrayList<>();
		vnfPackage.getVnfCompute().forEach(x -> x.getStorages().forEach(y -> {
			final Task ct = findCompute(plan, x.getToscaName());
			final StorageTask task = new StorageTask();
			task.setToscaName(x.getToscaName() + "-" + y);
			task.setType(ResourceTypeEnum.STORAGE);
			task.setParentAlias(ct.getAlias());
			task.setVnfStorage(findVnfStorage(vnfPackage.getVnfStorage(), y));
			ret.add(task);
		}));
		return ret;
	}

	private static Task findCompute(final Blueprint plan, final String toscaName) {
		return plan.getTasks().stream().filter(x -> x.getToscaName().equals(toscaName)).findAny().orElseThrow();
	}

	private static VnfStorage findVnfStorage(final Set<VnfStorage> vnfStorage, final String toscaName) {
		return vnfStorage.stream()
				.filter(x -> x.getToscaName().equals(toscaName))
				.findAny()
				.orElseThrow();
	}

	@Override
	public List<UnitOfWork> convertTasksToExecNode(final Set<Task> tasks, final Blueprint plan) {
		return tasks.stream()
				.filter(x -> x instanceof StorageTask)
				.map(x -> (StorageTask) x)
				.map(x -> new StorageUow(x, x.getVnfStorage()))
				.collect(Collectors.toList());
	}

}
