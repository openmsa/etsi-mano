package com.ubiqube.etsi.mano.grammar;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class AstBuilder {
	private final TreeBuilder treeBuilder;
	private final List<Node> nodes;

	public AstBuilder(final String filter) {
		treeBuilder = new TreeBuilder();
		if ((null != filter) && !filter.isEmpty()) {
			final EtsiLexer el = new EtsiLexer(CharStreams.fromString(filter));
			final CommonTokenStream tokens = new CommonTokenStream(el);
			final EtsiFilter parser = new EtsiFilter(tokens);
			parser.addParseListener(treeBuilder);
			parser.filterExpr();
			nodes = treeBuilder.getListNode();
		} else {
			nodes = new ArrayList<>();
		}
	}

	public TreeBuilder getTreeBuilder() {
		return treeBuilder;
	}

	public List<Node> getNodes() {
		return nodes;
	}

}
