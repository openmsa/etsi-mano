package com.ubiqube.etsi.mano.service.graph;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jgrapht.ListenableGraph;

import com.github.dexecutor.core.DefaultDexecutor;
import com.github.dexecutor.core.DexecutorConfig;
import com.github.dexecutor.core.ExecutionConfig;
import com.github.dexecutor.core.support.ThreadPoolUtil;
import com.github.dexecutor.core.task.ExecutionResults;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.service.vim.Vim;

public class PlanExecutor {

	public ExecutionResults<UnitOfWork, String> exec(final ListenableGraph<UnitOfWork, ConnectivityEdge> g, final VimConnectionInformation vimConnectionInformation, final Vim vim) {
		final ExecutorService executorService = Executors.newFixedThreadPool(ThreadPoolUtil.ioIntesivePoolSize());
		final DexecutorConfig<UnitOfWork, String> config = new DexecutorConfig<>(executorService, new UowTaskProvider(vimConnectionInformation, vim));
		// config.setExecutionListener(listener);
		final DefaultDexecutor<UnitOfWork, String> executor = new DefaultDexecutor<>(config);
		g.edgeSet().forEach(x -> executor.addDependency(x.getSource(), x.getTarget()));

		return executor.execute(ExecutionConfig.TERMINATING);
	}
}
