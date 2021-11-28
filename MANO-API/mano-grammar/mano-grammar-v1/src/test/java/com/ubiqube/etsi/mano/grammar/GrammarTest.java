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
	void testComplexValue() {
		final List<Node<String>> nodes = grammarv1Service.parse("id.eq=fce04624-6f92-42b1-bf50-437b682288a5");
		assertEquals(1, nodes.size());
		assertNode(nodes.get(0), "id", Operand.EQ, "fce04624-6f92-42b1-bf50-437b682288a5");
	}

	/**
	 * This is a multivalue.
	 */
	@Disabled("code currently does handle multivalues")
	@Test
	void testMultiValueIssue() {
		final List<Node<String>> nodes = grammarv1Service.parse("id.eq=fce04624-6f92-42b1-bf50-437b682288a5,OOOOOOO");
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
		final List<Node<String>> nodes = grammarv1Service.parse("id.eq=string&vnfdVersion.gt=bad");
		assertEquals(2, nodes.size());
		assertNode(nodes.get(0), "id", Operand.EQ, "string");
		assertNode(nodes.get(1), "vnfdVersion", Operand.GT, "bad");
	}

	/**
	 * Sub object attribute and simple values.
	 */
	@Test
	public void testMultiAttr() {
		final List<Node<String>> nodes = grammarv1Service.parse("id.my.bean.eq=string&vnfdVersion.gt=bad");
		assertEquals(2, nodes.size());
		assertNode(nodes.get(0), "id.my.bean", Operand.EQ, "string");
		assertNode(nodes.get(1), "vnfdVersion", Operand.GT, "bad");
	}

	/**
	 *
	 */
	@Test
	void testMultiAttr2() {
		final List<Node<String>> nodes = grammarv1Service.parse("id.my.bean.eq=stri556ng&my.long.vnfdVersion.gt=bad_ty");
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
