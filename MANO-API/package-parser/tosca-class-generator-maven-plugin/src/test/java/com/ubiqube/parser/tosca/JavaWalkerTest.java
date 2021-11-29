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

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.ubiqube.parser.tosca.generator.JavaWalker;
import com.ubiqube.parser.tosca.generator.ToscaWalker;

class JavaWalkerTest {

	@SuppressWarnings({ "unused", "static-method" })
	@Test
	void testName() throws Exception {
		final JavaWalker jw = new JavaWalker("target/tmp");
		final ToscaWalker tw = new ToscaWalker();
		tw.generate("src/test/resources/etsi_nfv_sol001_vnfd_types.yaml", jw);
		assertTrue(true);
	}
}
