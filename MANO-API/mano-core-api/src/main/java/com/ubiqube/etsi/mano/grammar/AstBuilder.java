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
package com.ubiqube.etsi.mano.grammar;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import com.ubiqube.etsi.mano.exception.BadRequestException;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class AstBuilder {
	private final TreeBuilder treeBuilder;
	private final List<Node<String>> nodes;

	public AstBuilder(final String filter) {
		treeBuilder = new TreeBuilder();
		if ((null != filter) && !filter.isEmpty()) {
			final EtsiLexer el = new EtsiLexer(CharStreams.fromString(filter));
			final CommonTokenStream tokens = new CommonTokenStream(el);
			final EtsiFilter parser = new EtsiFilter(tokens);
			parser.addParseListener(treeBuilder);
			parser.filterExpr();
			nodes = treeBuilder.getListNode();
			checkNodes();
		} else {
			nodes = new ArrayList<>();
		}
	}

	private void checkNodes() {
		nodes.forEach(x -> {
			if (null == x.getOp()) {
				throw new BadRequestException("Bad filter: " + x);
			}
		});
	}

	public TreeBuilder getTreeBuilder() {
		return treeBuilder;
	}

	public List<Node<String>> getNodes() {
		return nodes;
	}

}
