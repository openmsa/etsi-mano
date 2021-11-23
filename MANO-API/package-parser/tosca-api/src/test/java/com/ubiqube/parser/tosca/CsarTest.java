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

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.util.List;

import com.ubiqube.parser.tosca.csar.CsarParser;

public class CsarTest {

	void testGetFiles() throws Exception {
		final CsarParser csar = new CsarParser(new File("src/test/resources/csar_elk.csar"));
		final List<?> list = csar.getFiles();
		assertNotNull(list);
	}
}
