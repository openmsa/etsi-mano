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

import java.util.Map;
import java.util.concurrent.ExecutorService;

import org.jgrapht.ListenableGraph;
import org.springframework.stereotype.Service;

import com.github.dexecutor.core.DefaultDexecutor;
import com.github.dexecutor.core.DexecutorConfig;
import com.github.dexecutor.core.ExecutionConfig;
import com.github.dexecutor.core.task.ExecutionResults;
import com.github.dexecutor.core.task.TaskProvider;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.service.graph.nfvo.NsUnitOfWork;
import com.ubiqube.etsi.mano.service.graph.nfvo.UowNsTaskCreateProvider;
import com.ubiqube.etsi.mano.service.graph.nfvo.UowNsTaskDeleteProvider;
import com.ubiqube.etsi.mano.service.vim.ConnectivityEdge;
import com.ubiqube.etsi.mano.service.vim.Vim;
import com.ubiqube.etsi.mano.utils.SpringUtils;

@Service
public class NsPlanExecutor {
	public ExecutionResults<NsUnitOfWork, String> execCreateNs(final ListenableGraph<NsUnitOfWork, ConnectivityEdge<NsUnitOfWork>> g, final VimConnectionInformation vimConnectionInformation, final Vim vim, final Map<String, String> baseContext) {
		return createExecutor(g, new UowNsTaskCreateProvider(vimConnectionInformation, vim, null, null, baseContext));
	}

	public ExecutionResults<NsUnitOfWork, String> execDeleteNs(final ListenableGraph<NsUnitOfWork, ConnectivityEdge<NsUnitOfWork>> g, final VimConnectionInformation vimConnectionInformation, final Vim vim) {
		return createExecutor(g, new UowNsTaskDeleteProvider(vimConnectionInformation, vim, null, null));
	}

	private static <U> ExecutionResults<U, String> createExecutor(final ListenableGraph<U, ConnectivityEdge<U>> g, final TaskProvider<U, String> uowTaskProvider) {
		final ExecutorService executorService = SpringUtils.getFixedThreadPool(10, "executor");
		final DexecutorConfig<U, String> config = new DexecutorConfig<>(executorService, uowTaskProvider);
		// What about config setExecutionListener.
		final DefaultDexecutor<U, String> executor = new DefaultDexecutor<>(config);
		g.edgeSet().forEach(x -> executor.addDependency(x.getSource(), x.getTarget()));

		final ExecutionResults<U, String> res = executor.execute(ExecutionConfig.TERMINATING);
		executorService.shutdown();
		return res;
	}
}
