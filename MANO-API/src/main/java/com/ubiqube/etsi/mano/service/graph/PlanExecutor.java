package com.ubiqube.etsi.mano.service.graph;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jgrapht.ListenableGraph;
import org.springframework.stereotype.Service;

import com.github.dexecutor.core.DefaultDexecutor;
import com.github.dexecutor.core.DexecutorConfig;
import com.github.dexecutor.core.ExecutionConfig;
import com.github.dexecutor.core.task.ExecutionResults;
import com.github.dexecutor.core.task.TaskProvider;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.jpa.VnfLiveInstanceJpa;
import com.ubiqube.etsi.mano.service.VnfmInterface;
import com.ubiqube.etsi.mano.service.graph.nfvo.NsUnitOfWork;
import com.ubiqube.etsi.mano.service.graph.nfvo.UowNsTaskCreateProvider;
import com.ubiqube.etsi.mano.service.graph.nfvo.UowNsTaskDeleteProvider;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;
import com.ubiqube.etsi.mano.service.graph.vnfm.UowTaskCreateProvider;
import com.ubiqube.etsi.mano.service.graph.vnfm.UowTaskDeleteProvider;
import com.ubiqube.etsi.mano.service.vim.Vim;

@Service
public class PlanExecutor {

	private final VnfmInterface vnfm;

	private final VnfLiveInstanceJpa vnfLiveInstanceJpa;

	public PlanExecutor(final VnfmInterface _vnfm, final VnfLiveInstanceJpa _vnfLiveInstanceJpa) {
		vnfm = _vnfm;
		vnfLiveInstanceJpa = _vnfLiveInstanceJpa;
	}

	public ExecutionResults<UnitOfWork, String> execCreate(final ListenableGraph<UnitOfWork, ConnectivityEdge<UnitOfWork>> g, final VimConnectionInformation vimConnectionInformation, final Vim vim, final Map<String, String> baseContext) {
		return createExecutor(g, new UowTaskCreateProvider(vimConnectionInformation, vim, vnfLiveInstanceJpa, baseContext));
	}

	public ExecutionResults<UnitOfWork, String> execDelete(final ListenableGraph<UnitOfWork, ConnectivityEdge<UnitOfWork>> g, final VimConnectionInformation vimConnectionInformation, final Vim vim) {
		return createExecutor(g, new UowTaskDeleteProvider(vimConnectionInformation, vim, vnfLiveInstanceJpa));
	}

	private static <U> ExecutionResults<U, String> createExecutor(final ListenableGraph<U, ConnectivityEdge<U>> g, final TaskProvider<U, String> uowTaskProvider) {
		final ExecutorService executorService = Executors.newFixedThreadPool(20);
		final DexecutorConfig<U, String> config = new DexecutorConfig<>(executorService, uowTaskProvider);
		// What about config setExecutionListener.
		final DefaultDexecutor<U, String> executor = new DefaultDexecutor<>(config);
		g.edgeSet().forEach(x -> executor.addDependency(x.getSource(), x.getTarget()));

		return executor.execute(ExecutionConfig.TERMINATING);
	}

	public ExecutionResults<NsUnitOfWork, String> execCreateNs(final ListenableGraph<NsUnitOfWork, ConnectivityEdge<NsUnitOfWork>> g, final VimConnectionInformation vimConnectionInformation, final Vim vim, final Map<String, String> baseContext) {
		return createExecutor(g, new UowNsTaskCreateProvider(vimConnectionInformation, vim, null, vnfm, baseContext));
	}

	public ExecutionResults<NsUnitOfWork, String> execDeleteNs(final ListenableGraph<NsUnitOfWork, ConnectivityEdge<NsUnitOfWork>> g, final VimConnectionInformation vimConnectionInformation, final Vim vim) {
		return createExecutor(g, new UowNsTaskDeleteProvider(vimConnectionInformation, vim, null, vnfm));
	}

}
