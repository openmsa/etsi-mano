package com.ubiqube.etsi.mano.graph;

import java.util.Set;

import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DefaultListenableGraph;
import org.jgrapht.graph.DirectedAcyclicGraph;
import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.service.graph.ConnectivityEdge;
import com.ubiqube.etsi.mano.service.graph.vnfm.EdgeListener;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;

public class GraphTest {
	private static <U> ListenableGraph<U, ConnectivityEdge<U>> createGraph() {
		final Class<ConnectivityEdge<U>> t = (Class<ConnectivityEdge<U>>) (Object) ConnectivityEdge.class;
		// Vertex everyThing
		final ListenableGraph<U, ConnectivityEdge<U>> g = new DefaultListenableGraph<>(new DirectedAcyclicGraph<>(t));
		g.addGraphListener(new EdgeListener<U>());
		return g;
	}

	@Test
	void testName() throws Exception {
		final ListenableGraph<UnitOfWork, ConnectivityEdge<UnitOfWork>> g = createGraph();
		g.addGraphListener(new EdgeListener<UnitOfWork>());
		final UnitOfWork vduA = new TestUnitOfWork("A");
		final UnitOfWork vduB = new TestUnitOfWork("B");
		final UnitOfWork vduC = new TestUnitOfWork("C");
		final UnitOfWork vduD = new TestUnitOfWork("D");
		final UnitOfWork vduE = new TestUnitOfWork("E");
		final UnitOfWork vduF = new TestUnitOfWork("F");
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
		final UnitOfWork root = new TestUnitOfWork("Start");
		g.addVertex(root);
		g.vertexSet().stream()
				.filter(key -> g.incomingEdgesOf(key).isEmpty())
				.forEach(key -> {
					if (key != root) {
						g.addEdge(root, key);
					}
				});
		// And end Node
		final UnitOfWork end = new TestUnitOfWork("End");
		g.addVertex(end);
		g.vertexSet().stream()
				.filter(key -> g.outgoingEdgesOf(key).isEmpty())
				.forEach(key -> {
					if (key != end) {
						g.addEdge(key, end);
					}
				});
		System.out.println("==========================");
		final Set<ConnectivityEdge<UnitOfWork>> edg = g.edgesOf(vduB);
		edg.forEach(x -> {
			System.out.println("Edge: " + x.getSource().getName() + " -> " + x.getTarget().getName());
		});
	}
}
