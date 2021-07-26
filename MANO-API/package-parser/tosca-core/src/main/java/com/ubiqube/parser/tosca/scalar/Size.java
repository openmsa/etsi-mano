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

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.parser.tosca.ParseException;

public class Size {
	private static final Logger LOG = LoggerFactory.getLogger(Size.class);

	private static final Pattern SIZE_PATTERN = Pattern.compile("(?<size>[0-9]+)\\s*(?<unit>[a-zA-Z]+)");

	private final long lsize;

	private final String unit;

	public Size(final String value) {
		LOG.debug("Size value: {}", value);

		final Matcher m = SIZE_PATTERN.matcher(value);
		if (!m.find()) {
			throw new ParseException("Size scalr: Unable to find a match for: " + value);
		}
		lsize = Long.parseLong(m.group("size"));
		unit = m.group("unit");
	}

	public Size(final Long value) {
		lsize = value.longValue();
		unit = "b";
	}

	public String getToscaForm() {
		return lsize + " " + unit;
	}

	public BigDecimal getValue() {
		final BigDecimal mul = getMultiplier(unit);
		final BigDecimal sizeBd = new BigDecimal(lsize);
		return mul.multiply(sizeBd);
	}

	public BigDecimal getMultiplier(final String unit2) {
		switch (unit2.toLowerCase()) {
		case "b":
			return BigDecimal.ONE;
		// (1000 bytes)
		case "kb":
			return new BigDecimal("1000");
		// (1024 bytes)
		case "kiB":
			return new BigDecimal("1024");
		// (1000000 bytes)
		case "mb":
			return new BigDecimal("1000000");
		// (1048576 bytes)
		case "mib":
			return new BigDecimal("1048576");
		// (1000000000 bytes)
		case "gb":
			return new BigDecimal("1000000000");
		// (1073741824 bytes)
		case "gib":
			return new BigDecimal("1073741824");
		// 1000000000000 bytes)
		case "tb":
			return new BigDecimal("1000000000000");
		// 1099511627776
		case "tib":
			return new BigDecimal("1099511627776");
		default:
			throw new ParseException("Unknown scalar unit " + unit2);
		}
	}
}
