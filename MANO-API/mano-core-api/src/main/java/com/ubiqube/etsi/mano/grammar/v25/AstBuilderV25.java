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
