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
package com.ubiqube.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

public class CartesianTest {

	@Test
	void testName() throws Exception {
		final List<String> a = List.of("a", "b");
		final List<String> b = List.of("1", "2", "3");
		final List<List<String>> res = product(a, b);
		System.out.println("" + res);
		assertNotNull(res);
	}

	private static List<List<String>> product(final List<String> a, final List<String> b) {
		return Optional.of(a.stream()
				.map(e1 -> Optional.of(b.stream().map(e2 -> Arrays.asList(e1, e2)).collect(Collectors.toList())).orElse(Collections.emptyList()))
				.flatMap(List::stream)
				.collect(Collectors.toList())).orElse(Collections.emptyList());
	}

}
