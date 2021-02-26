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
package com.ubiqube.parser.tosca.scalar;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ubiqube.parser.tosca.ParseException;

public class Frequency {
	private static final Pattern FREQUENCY_PATTERN = Pattern.compile("(?<freq>[0-9]+(\\.[0-9]+)?)\\s*(?<unit>[a-zA-Z]+)");

	private final double freq;

	private final String unit;

	public Frequency(final String value) {

		final Matcher m = FREQUENCY_PATTERN.matcher(value);
		if (!m.find()) {
			throw new ParseException("Size scalr: Unable to find a match for: " + value);
		}
		freq = Long.parseLong(m.group("freq"));
		unit = m.group("unit");
	}

	public String getToscaForm() {
		return freq + " " + unit;
	}

	public double getValue() {
		return freq * getMultiplier(unit);
	}

	public static double getMultiplier(final String unit2) {
		switch (unit2.toLowerCase(Locale.ROOT)) {
		case "hz":
			return 1D;
		case "khz": // (1000 bytes)
			return 1_000D;
		case "mhz":// (1024 bytes)
			return 1_000_000D;
		case "ghz": // (1000000 bytes)
			return 1_000_000_000D;
		default:
			throw new ParseException("Unknown scalar unit " + unit2);
		}
	}

}
