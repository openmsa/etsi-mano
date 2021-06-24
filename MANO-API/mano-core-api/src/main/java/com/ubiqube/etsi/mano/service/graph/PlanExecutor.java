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
package com.ubiqube.etsi.mano.service.graph;

import java.util.concurrent.ExecutorService;
import java.util.function.Supplier;

import org.jgrapht.ListenableGraph;

import com.github.dexecutor.core.DefaultDexecutor;
import com.github.dexecutor.core.DexecutorConfig;
import com.github.dexecutor.core.ExecutionConfig;
import com.github.dexecutor.core.task.ExecutionResults;
import com.github.dexecutor.core.task.TaskProvider;
import com.ubiqube.etsi.mano.dao.mano.v2.Task;
import com.ubiqube.etsi.mano.orchestrator.nodes.ConnectivityEdge;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;
import com.ubiqube.etsi.mano.utils.SpringUtils;

public class PlanExecutor<U extends Task, P> {

	public ExecutionResults<UnitOfWork<U, P>, String> execCreate(final ListenableGraph<UnitOfWork<U, P>, ConnectivityEdge<UnitOfWork<U, P>>> g, final Supplier<TaskProvider<UnitOfWork<U, P>, String>> supplier) {
		return createExecutor(g, supplier.get());
	}

	public ExecutionResults<UnitOfWork<U, P>, String> execDelete(final ListenableGraph<UnitOfWork<U, P>, ConnectivityEdge<UnitOfWork<U, P>>> g, final Supplier<TaskProvider<UnitOfWork<U, P>, String>> supplier) {
		final ListenableGraph<UnitOfWork<U, P>, ConnectivityEdge<UnitOfWork<U, P>>> rev = GraphTools.revert(g);
		GraphTools.exportGraph(rev, "del-rev.dot");
		return createExecutor(rev, supplier.get());
	}

	private ExecutionResults<UnitOfWork<U, P>, String> createExecutor(final ListenableGraph<UnitOfWork<U, P>, ConnectivityEdge<UnitOfWork<U, P>>> g, final TaskProvider<UnitOfWork<U, P>, String> uowTaskProvider) {
		final ExecutorService executorService = SpringUtils.getFixedThreadPool(10, "executor");
		final DexecutorConfig<UnitOfWork<U, P>, String> config = new DexecutorConfig<>(executorService, uowTaskProvider);
		// What about config setExecutionListener.
		final DefaultDexecutor<UnitOfWork<U, P>, String> executor = new DefaultDexecutor<>(config);
		g.edgeSet().forEach(x -> executor.addDependency(x.getSource(), x.getTarget()));

		final ExecutionResults<UnitOfWork<U, P>, String> res = executor.execute(ExecutionConfig.TERMINATING);
		executorService.shutdown();
		return res;
	}
}
