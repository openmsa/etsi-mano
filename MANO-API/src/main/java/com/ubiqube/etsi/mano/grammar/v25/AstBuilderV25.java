package com.ubiqube.etsi.mano.grammar.v25;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import com.ubiqube.etsi.mano.grammar.Node;

public class AstBuilderV25 {
	private final TreeBuilderV25 treeBuilder;
	private final List<Node> nodes;

	public AstBuilderV25(final String filter) {
		treeBuilder = new TreeBuilderV25();
		if ((null != filter) && !filter.isEmpty()) {
			final EtsiLexerV25 el = new EtsiLexerV25(CharStreams.fromString(filter));
			final CommonTokenStream tokens = new CommonTokenStream(el);
			final EtsiFilterV25 parser = new EtsiFilterV25(tokens);
			parser.addParseListener(treeBuilder);
			parser.filterExpr();
			nodes = treeBuilder.getListNode();
		} else {
			nodes = new ArrayList<>();
		}
	}

	public TreeBuilderV25 getTreeBuilder() {
		return treeBuilder;
	}

	public List<Node> getNodes() {
		return nodes;
	}

}
