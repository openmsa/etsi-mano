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
package com.ubiqube.grammar;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.grammar.EtsiFilter;
import com.ubiqube.etsi.mano.grammar.EtsiLexer;
import com.ubiqube.etsi.mano.grammar.Node;
import com.ubiqube.etsi.mano.grammar.Node.Operand;
import com.ubiqube.etsi.mano.grammar.TreeBuilder;

public class FilterTest {

	@Test
	public void treeTest() {
		final ANTLRInputStream input = new ANTLRInputStream("weight.eq=100");
		final EtsiLexer lexer = new EtsiLexer(input);

		final CommonTokenStream tokens = new CommonTokenStream(lexer);
		final EtsiFilter parser = new EtsiFilter(tokens);
		final TreeBuilder treeBuilder = new TreeBuilder();
		parser.addParseListener(treeBuilder);
		parser.filterExpr();
		final List<Node> listNode = treeBuilder.getListNode();
		assertNotNull(listNode);
		assertEquals(1, listNode.size());
	}

	@Test
	void testMultiFilter() throws Exception {
		final ANTLRInputStream input = new ANTLRInputStream("weight.eq=100&color.neq=red");
		final EtsiLexer lexer = new EtsiLexer(input);

		final CommonTokenStream tokens = new CommonTokenStream(lexer);
		final EtsiFilter parser = new EtsiFilter(tokens);
		final TreeBuilder treeBuilder = new TreeBuilder();
		parser.addParseListener(treeBuilder);
		parser.filterExpr();
		final List<Node> listNode = treeBuilder.getListNode();
		assertNotNull(listNode);
		assertEquals(2, listNode.size());
	}

	@Test
	void testMultiDot() throws Exception {
		final ANTLRInputStream input = new ANTLRInputStream("color.of.my.bean.neq=red");
		final EtsiLexer lexer = new EtsiLexer(input);

		final CommonTokenStream tokens = new CommonTokenStream(lexer);
		final EtsiFilter parser = new EtsiFilter(tokens);
		final TreeBuilder treeBuilder = new TreeBuilder();
		parser.addParseListener(treeBuilder);
		parser.filterExpr();
		final List<Node> listNode = treeBuilder.getListNode();
		assertNotNull(listNode);
		assertEquals(1, listNode.size());
		final Node node = listNode.get(0);
		assertEquals(Operand.NEQ, node.getOp());
		assertEquals("red", node.getValue());
		assertEquals("color.of.my.bean", node.getName());
		System.out.println(treeBuilder.toString());
	}
}
