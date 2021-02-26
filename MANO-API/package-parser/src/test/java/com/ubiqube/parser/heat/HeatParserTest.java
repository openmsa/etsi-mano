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
package com.ubiqube.parser.heat;

import org.junit.jupiter.api.Test;

public class HeatParserTest {
	@Test
	void testName() throws Exception {
		final HeatParser tp = new HeatParser();
		final HeatRoot root = tp.parse("src/test/resources/hadoop_spark.yaml");
		System.out.println("" + root);
	}

	@Test
	void testName2() throws Exception {
		final HeatParser tp = new HeatParser();
		final HeatRoot root = tp.parse("src/test/resources/appserver.yaml");
		System.out.println("" + root);
	}
}
