package com.ubiqube.parser.tosca;

import java.util.Iterator;

import org.apache.commons.jxpath.JXPathContext;
import org.junit.jupiter.api.Test;

public class JxPathTest {
	private ToscaContext root = null;

	@Test
	void testName() throws Exception {
		final ToscaParser tp = new ToscaParser("src/test/resources/web_mysql_tosca.yaml");
		root = tp.getContext();
		final JXPathContext ctx = JXPathContext.newContext(root);
		final Object nodes = ctx.getValue("topologies/nodeTemplate//type");
		System.out.println(">> " + nodes);
		for (final Iterator<?> iter = ctx.iterate("topologies/nodeTemplate/*/type"); iter.hasNext();) {
			final Object node = iter.next();
			System.out.println(">>> " + node);
		}

	}
}
