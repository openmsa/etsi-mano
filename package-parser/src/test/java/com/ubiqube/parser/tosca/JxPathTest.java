package com.ubiqube.parser.tosca;

import java.util.Iterator;

import org.apache.commons.jxpath.JXPathContext;
import org.junit.jupiter.api.Test;

public class JxPathTest {
	private final ToscaParser tp = new ToscaParser();
	private ToscaContext root = null;

	@Test
	void testName() throws Exception {
		root = tp.parse("src/test/resources/web_mysql_tosca.yaml");
		final JXPathContext ctx = JXPathContext.newContext(root);
		final Object nodes = ctx.getValue("topologies/nodeTemplate//type");
		System.out.println(">> " + nodes);
		for (final Iterator<?> iter = ctx.iterate("topologies/nodeTemplate/*/type"); iter.hasNext();) {
			final Object node = iter.next();
			System.out.println(">>> " + node);
		}

	}
}
