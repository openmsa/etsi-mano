package com.ubiqube.etsi.mano.grammar;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

public class GrammarTest {

	@Test
	public void testValueIssue() {
		final AstBuilder astBuilder = new AstBuilder("id.eq=fce04624-6f92-42b1-bf50-437b682288a5");
		final List<Node> nodes = astBuilder.getNodes();
		final Node node = nodes.get(0);
		assertEquals("fce04624-6f92-42b1-bf50-437b682288a5", node.getValue());
	}

	@Test
	public void testMultiValueIssue() {
		final AstBuilder astBuilder = new AstBuilder("id.eq=fce04624-6f92-42b1-bf50-437b682288a5,OOOOOOO");
		final List<Node> nodes = astBuilder.getNodes();
		final Node node = nodes.get(0);
		assertEquals("fce04624-6f92-42b1-bf50-437b682288a5", node.getValue());
	}
}
