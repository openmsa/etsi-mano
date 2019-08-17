package com.ubiqube.etsi.mano.grammar;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.grammar.Node.Operand;
import com.ubiqube.etsi.mano.grammar.v25.AstBuilderV25;

public class GrammarV25Test {

	@Test
	public void test() {
		// (eq,weight,100);(neq,weight/aa,100);(in,weight/aa,100,55)
		final AstBuilderV25 astBuilder = new AstBuilderV25("(eq,weight,100);(neq,weight/aa,100);(in,weight/aa,100,55)");
		System.out.println("" + astBuilder.getNodes());
		final List<Node> nodes = astBuilder.getNodes();
		assertEquals(3, nodes.size());
		assertNode(nodes.get(0), "weight", Operand.EQ, "100");
		assertNode(nodes.get(1), "weight.aa", Operand.NEQ, "100");
		// TODO It's wrong.
		assertNode(nodes.get(2), "weight.aa", Operand.IN, "55");
	}

	private void assertNode(final Node node, final String key, final Operand op, final String value) {
		assertEquals(key, node.getName());
		assertEquals(op, node.getOp());
		assertEquals(value, node.getValue());
	}
}
