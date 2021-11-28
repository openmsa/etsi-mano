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
/**
 * This copy of Woodstox XML processor is licensed under the
 * Apache (Software) License, version 2.0 ("the License").
 * See the License for details about distribution rights, and the
 * specific rights regarding derivate works.
 *
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/
 *
 * A copy is also included in the downloadable source code package
 * containing Woodstox, in file "ASL2.0", under the same directory
 * as this file.
 */
package com.ubiqube.etsi.mano.grammar.v25;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.springframework.stereotype.Service;

import com.mano.etsi.mano.grammar.v25.EtsiFilterV25;
import com.mano.etsi.mano.grammar.v25.EtsiLexerV25;
import com.ubiqube.etsi.mano.grammar.GrammarException;
import com.ubiqube.etsi.mano.grammar.GrammarParser;
import com.ubiqube.etsi.mano.grammar.Node;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class Grammar25Service implements GrammarParser {

	@SuppressWarnings("unchecked")
	@Override
	public List<Node<String>> parse(final String query) {
		List<Node<String>> nodes = new ArrayList<>();
		final TreeBuilderV25 treeBuilder = new TreeBuilderV25();
		if (null != query && !query.isEmpty()) {
			final EtsiLexerV25 el = new EtsiLexerV25(CharStreams.fromString(query));
			final CommonTokenStream tokens = new CommonTokenStream(el);
			final EtsiFilterV25 parser = new EtsiFilterV25(tokens);
			parser.addParseListener(treeBuilder);
			parser.filterExpr();
			nodes = treeBuilder.getListNode();
			checkNodes(nodes);
		}
		return nodes;
	}

	private static void checkNodes(final List<Node<String>> nodes) {
		nodes.forEach(x -> {
			if (null == x.getOp()) {
				throw new GrammarException("Bad filter: " + x);
			}
		});
	}

}
