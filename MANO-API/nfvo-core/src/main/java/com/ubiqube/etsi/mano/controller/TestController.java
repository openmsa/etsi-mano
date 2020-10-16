package com.ubiqube.etsi.mano.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DefaultListenableGraph;
import org.jgrapht.graph.DirectedAcyclicGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.dexecutor.core.DefaultDexecutor;
import com.github.dexecutor.core.DexecutorConfig;
import com.github.dexecutor.core.ExecutionConfig;
import com.github.dexecutor.core.task.Task;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedCompute;
import com.ubiqube.etsi.mano.service.graph.PlanExecutor;
import com.ubiqube.etsi.mano.service.graph.vnfm.EdgeListener;
import com.ubiqube.etsi.mano.service.graph.vnfm.EndUow;
import com.ubiqube.etsi.mano.service.graph.vnfm.NoopUow;
import com.ubiqube.etsi.mano.service.graph.vnfm.StartUow;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;
import com.ubiqube.etsi.mano.service.graph.vnfm.UowTaskCreateProvider;
import com.ubiqube.etsi.mano.service.vim.ConnectivityEdge;
import com.ubiqube.etsi.mano.utils.SpringUtils;

@Controller
@RequestMapping("/exec")
public class TestController {

	private static final Logger LOG = LoggerFactory.getLogger(TestController.class);

	private final PlanExecutor planExecutor;

	public TestController(final PlanExecutor _planExecutor) {
		planExecutor = _planExecutor;
	}

	public void doExecutor() {
		final ListenableGraph<UnitOfWork, ConnectivityEdge<UnitOfWork>> g = createGraph();
		g.addGraphListener(new EdgeListener<UnitOfWork>());
		final UnitOfWork vduA = new NoopUow(new VnfInstantiatedCompute());
		final UnitOfWork vduB = new NoopUow(new VnfInstantiatedCompute());
		final UnitOfWork vduC = new NoopUow(new VnfInstantiatedCompute());
		final UnitOfWork vduD = new NoopUow(new VnfInstantiatedCompute());
		final UnitOfWork vduE = new NoopUow(new VnfInstantiatedCompute());
		final UnitOfWork vduF = new NoopUow(new VnfInstantiatedCompute());
		g.addVertex(vduA);
		g.addVertex(vduB);
		g.addVertex(vduC);
		g.addVertex(vduD);
		g.addVertex(vduE);
		g.addVertex(vduF);

		g.addEdge(vduA, vduB);
		g.addEdge(vduA, vduC);

		g.addEdge(vduB, vduD);
		g.addEdge(vduC, vduD);

		g.addEdge(vduE, vduC);
		g.addEdge(vduE, vduF);
		final UnitOfWork root = new StartUow(new VnfInstantiatedCompute());
		g.addVertex(root);
		g.vertexSet().stream()
				.filter(key -> g.incomingEdgesOf(key).isEmpty())
				.forEach(key -> {
					if (key != root) {
						g.addEdge(root, key);
					}
				});
		// And end Node
		final UnitOfWork end = new EndUow(new VnfInstantiatedCompute());
		g.addVertex(end);
		g.vertexSet().stream()
				.filter(key -> g.outgoingEdgesOf(key).isEmpty())
				.forEach(key -> {
					if (key != end) {
						g.addEdge(key, end);
					}
				});
		planExecutor.execCreate(g, null, null, new HashMap<>());
	}

	public void doExecutor2() {
		final ListenableGraph<UnitOfWork, ConnectivityEdge<UnitOfWork>> g = createGraph();
		LOG.warn("Running...................................................");
		g.addGraphListener(new EdgeListener<UnitOfWork>());
		final UnitOfWork vduA = new NoopUow(new VnfInstantiatedCompute());
		final UnitOfWork vduB = new NoopUow(new VnfInstantiatedCompute());
		g.addVertex(vduA);
		g.addVertex(vduB);
		g.addEdge(vduA, vduB);
		planExecutor.execCreate(g, null, null, new HashMap<>());
		LOG.warn("Running................................................... Done!");
	}

	/**
	 * Yes executor is cleaned after shutdown.
	 */
	public void doExecutor3() {
		final ExecutorService executorService = SpringUtils.getFixedThreadPool(10, "executor");
		final UnitOfWork vduA = new NoopUow(new VnfInstantiatedCompute());
		final UnitOfWork vduB = new NoopUow(new VnfInstantiatedCompute());
		final Map<String, String> map = new ConcurrentHashMap<>();
		executorService.submit(() -> {
			try {
				final Task<UnitOfWork, String> provider = new UowTaskCreateProvider(null, null, null, map).provideTask(vduA);
				provider.execute();
			} catch (final Exception e) {
				LOG.error("", e);
			}
		});
		executorService.submit(() -> {
			final Task<UnitOfWork, String> provider = new UowTaskCreateProvider(null, null, null, map).provideTask(vduB);
			provider.execute();
		});
		executorService.shutdown();
	}

	@GetMapping
	public void doExecutor4() {
		final UnitOfWork vduA = new NoopUow(new VnfInstantiatedCompute());
		final UnitOfWork vduB = new NoopUow(new VnfInstantiatedCompute());
		final ExecutorService executorService = SpringUtils.getFixedThreadPool(10, "executor");
		final UowTaskCreateProvider uowTaskProvider = new UowTaskCreateProvider(null, null, null, new ConcurrentHashMap<>());
		final DexecutorConfig<UnitOfWork, String> config = new DexecutorConfig<>(executorService, uowTaskProvider);
		// What about config setExecutionListener.
		final DefaultDexecutor<UnitOfWork, String> executor = new DefaultDexecutor<>(config);

		executor.addIndependent(vduA);
		executor.execute(ExecutionConfig.TERMINATING);
	}

	private static <U> ListenableGraph<U, ConnectivityEdge<U>> createGraph() {
		final Class<ConnectivityEdge<U>> t = (Class<ConnectivityEdge<U>>) (Object) ConnectivityEdge.class;
		// Vertex everyThing
		final ListenableGraph<U, ConnectivityEdge<U>> g = new DefaultListenableGraph<>(new DirectedAcyclicGraph<>(t));
		g.addGraphListener(new EdgeListener<U>());
		return g;
	}
}
