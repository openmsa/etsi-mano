package com.ubiqube.etsi.mano.service.graph;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jgrapht.ListenableGraph;
import org.springframework.stereotype.Service;

import com.github.dexecutor.core.DefaultDexecutor;
import com.github.dexecutor.core.DexecutorConfig;
import com.github.dexecutor.core.ExecutionConfig;
import com.github.dexecutor.core.support.ThreadPoolUtil;
import com.github.dexecutor.core.task.ExecutionResults;
import com.github.dexecutor.core.task.TaskProvider;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.jpa.VnfInstantiedBaseJpa;
import com.ubiqube.etsi.mano.service.vim.Vim;

@Service
public class PlanExecutor {

	private final VnfInstantiedBaseJpa vnfInstantiedBaseJpa;

	public PlanExecutor(final VnfInstantiedBaseJpa _vnfInstantiedBaseJpa) {
		vnfInstantiedBaseJpa = _vnfInstantiedBaseJpa;
	}

	public ExecutionResults<UnitOfWork, String> execCreate(final ListenableGraph<UnitOfWork, ConnectivityEdge> g, final VimConnectionInformation vimConnectionInformation, final Vim vim) {
		return createExecutor(g, new UowTaskCreateProvider(vimConnectionInformation, vim, vnfInstantiedBaseJpa));
	}

	public ExecutionResults<UnitOfWork, String> execDelete(final ListenableGraph<UnitOfWork, ConnectivityEdge> g, final VimConnectionInformation vimConnectionInformation, final Vim vim) {
		return createExecutor(g, new UowTaskDeleteProvider(vimConnectionInformation, vim, vnfInstantiedBaseJpa));
	}

	private static ExecutionResults<UnitOfWork, String> createExecutor(final ListenableGraph<UnitOfWork, ConnectivityEdge> g, final TaskProvider<UnitOfWork, String> uowTaskProvider) {
		final ExecutorService executorService = Executors.newFixedThreadPool(ThreadPoolUtil.ioIntesivePoolSize());
		final DexecutorConfig<UnitOfWork, String> config = new DexecutorConfig<>(executorService, uowTaskProvider);
		// What about config setExecutionListener.
		final DefaultDexecutor<UnitOfWork, String> executor = new DefaultDexecutor<>(config);
		g.edgeSet().forEach(x -> executor.addDependency(x.getSource(), x.getTarget()));

		return executor.execute(ExecutionConfig.TERMINATING);
	}
}
