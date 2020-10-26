package com.ubiqube.etsi.mano.service.plan.contributors;

import java.util.List;
import java.util.Set;

import com.ubiqube.etsi.mano.dao.mano.ScaleInfo;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.v2.Blueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.Task;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;
import com.ubiqube.etsi.mano.service.vim.node.Node;

public interface PlanContributor {

	Class<? extends Node> getContributionType();

	List<Task> contribute(VnfPackage vnfPackage, Blueprint plan, Set<ScaleInfo> scaling);

	List<UnitOfWork> convertTasksToExecNode(Set<Task> tasks, Blueprint plan);
}
