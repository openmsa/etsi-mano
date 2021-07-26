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
