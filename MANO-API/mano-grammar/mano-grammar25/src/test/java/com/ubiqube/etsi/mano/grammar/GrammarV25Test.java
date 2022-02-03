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

import com.ubiqube.etsi.mano.grammar.Node.Operand;
import com.ubiqube.etsi.mano.grammar.v25.Grammar25Service;

class GrammarV25Test {

	@SuppressWarnings("static-method")
	@Test
	void test() {
		final GrammarParser gp = new Grammar25Service();
		// (eq,weight,100);(neq,weight/aa,100);(in,weight/aa,100,55)
		final List<Node<String>> nodes = gp.parse("(eq,weight,100);(neq,weight/aa,100);(in,weight/aa,100,55)");
		assertEquals(3, nodes.size());
		assertNode(nodes.get(0), "weight", Operand.EQ, "100");
		assertNode(nodes.get(1), "weight.aa", Operand.NEQ, "100");
		// It's wrong.?
		final Node<String> node = nodes.get(2);
		assertEquals("weight.aa", node.getName());
		assertEquals(Operand.IN, node.getOp());
		final List<String> values = node.getValues();
		assertEquals("100", values.get(0));
		assertEquals("55", values.get(1));
	}

	private static void assertNode(final Node<String> node, final String key, final Operand op, final String value) {
		assertEquals(key, node.getName());
		assertEquals(op, node.getOp());
		assertEquals(value, node.getValue());
	}
}
