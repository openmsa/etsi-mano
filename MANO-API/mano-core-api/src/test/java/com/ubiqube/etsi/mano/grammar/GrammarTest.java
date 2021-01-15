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

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;

import com.ubiqube.etsi.mano.grammar.Node.Operand;

public class GrammarTest {

	/**
	 * Simple value can be cinfused with STRING <=> AATTRIBUTES.
	 *
	 * @throws Exception
	 */
	@Test
	void testSimpleValue() throws Exception {
		final AstBuilder astBuilder = new AstBuilder("id.gt=aaa");
		final List<Node<String>> nodes = astBuilder.getNodes();
		assertEquals(1, nodes.size());
		assertNode(nodes.get(0), "id", Operand.GT, "aaa");
	}

	/**
	 * Value may be letter and digits.
	 */
	@Test
	public void testComplexValue() {
		final AstBuilder astBuilder = new AstBuilder("id.eq=fce04624-6f92-42b1-bf50-437b682288a5");
		final List<Node<String>> nodes = astBuilder.getNodes();
		assertEquals(1, nodes.size());
		assertNode(nodes.get(0), "id", Operand.EQ, "fce04624-6f92-42b1-bf50-437b682288a5");
	}

	/**
	 * This is a multivalue.
	 */
	@Disabled("code currently does handle multivalues")
	@Test
	public void testMultiValueIssue() {
		final AstBuilder astBuilder = new AstBuilder("id.eq=fce04624-6f92-42b1-bf50-437b682288a5,OOOOOOO");
		final List<Node<String>> nodes = astBuilder.getNodes();
		final Node node = nodes.get(0);
		final List<String> values = node.getValues();
		assertEquals("fce04624-6f92-42b1-bf50-437b682288a5", values.get(0));
		assertEquals("OOOOOOO", values.get(1));
	}

	/**
	 * Multiple Operator separated by '&' and 2 simple values.
	 */
	@Test
	public void testMultiOp() {
		final AstBuilder astBuilder = new AstBuilder("id.eq=string&vnfdVersion.gt=bad");
		final List<Node<String>> nodes = astBuilder.getNodes();
		assertEquals(2, nodes.size());
		assertNode(nodes.get(0), "id", Operand.EQ, "string");
		assertNode(nodes.get(1), "vnfdVersion", Operand.GT, "bad");
	}

	/**
	 * Sub object attribute and simple values.
	 */
	@Test
	public void testMultiAttr() {
		final AstBuilder astBuilder = new AstBuilder("id.my.bean.eq=string&vnfdVersion.gt=bad");
		final List<Node<String>> nodes = astBuilder.getNodes();
		assertEquals(2, nodes.size());
		assertNode(nodes.get(0), "id.my.bean", Operand.EQ, "string");
		assertNode(nodes.get(1), "vnfdVersion", Operand.GT, "bad");
	}

	/**
	 *
	 */
	@Test
	public void testMultiAttr2() {
		final AstBuilder astBuilder = new AstBuilder("id.my.bean.eq=stri556ng&my.long.vnfdVersion.gt=bad_ty");
		final List<Node<String>> nodes = astBuilder.getNodes();
		assertEquals(2, nodes.size());
		assertNode(nodes.get(0), "id.my.bean", Operand.EQ, "stri556ng");
		assertNode(nodes.get(1), "my.long.vnfdVersion", Operand.GT, "bad_ty");
	}

	private static void assertNode(final Node node, final String key, final Operand op, final String value) {
		assertEquals(key, node.getName());
		assertEquals(op, node.getOp());
		assertEquals(value, node.getValue());
	}
}
