package com.ubiqube.etsi.mano.grammar;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

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
		final List<Node> nodes = astBuilder.getNodes();
		assertEquals(1, nodes.size());
		assertNode(nodes.get(0), "id", Operand.GT, "aaa");
	}

	/**
	 * Value may be letter and digits.
	 */
	@Test
	public void testComplexValue() {
		final AstBuilder astBuilder = new AstBuilder("id.eq=fce04624-6f92-42b1-bf50-437b682288a5");
		final List<Node> nodes = astBuilder.getNodes();
		assertEquals(1, nodes.size());
		assertNode(nodes.get(0), "id", Operand.EQ, "fce04624-6f92-42b1-bf50-437b682288a5");
	}

	/**
	 * This is a multivalue, it will failed.
	 */
	@Test
	public void testMultiValueIssue() {
		final AstBuilder astBuilder = new AstBuilder("id.eq=fce04624-6f92-42b1-bf50-437b682288a5,OOOOOOO");
		final List<Node> nodes = astBuilder.getNodes();
		final Node node = nodes.get(0);
		assertEquals("fce04624-6f92-42b1-bf50-437b682288a5", node.getValue());
	}

	/**
	 * Multiple Operator separated by '&' and 2 simple values.
	 */
	@Test
	public void testMultiOp() {
		final AstBuilder astBuilder = new AstBuilder("id.eq=string&vnfdVersion.gt=bad");
		final List<Node> nodes = astBuilder.getNodes();
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
		final List<Node> nodes = astBuilder.getNodes();
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
		final List<Node> nodes = astBuilder.getNodes();
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
