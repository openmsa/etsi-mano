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
package com.ubiqube.etsi.mano.tf;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class LinkTest {

	@Test
	void testName() throws Exception {
		final List<PairsVl> ports = Arrays.asList(new PairsVl("A", "B"),
				new PairsVl("C", "D"),
				new PairsVl("E", "F"));
		for (int i = 0; i < ports.size() - 1; i++) {
			final PairsVl left = ports.get(i);
			final PairsVl right = ports.get(i + 1);
			System.out.println(" + " + left.right + " -> " + right.left);
		}
	}

	record PairsVl(String left, String right) {
		//
	}
}
