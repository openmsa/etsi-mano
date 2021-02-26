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
	private final long size;
	private final String unit;

	public Size(final String value) {
		LOG.debug("Size value: {}", value);
		final Pattern p = Pattern.compile("(?<size>[0-9]+)\\s*(?<unit>[a-zA-Z]+)");
		final Matcher m = p.matcher(value);
		if (!m.find()) {
			throw new ParseException("Size scalr: Unable to find a match for: " + value);
		}
		size = Long.parseLong(m.group("size"));
		unit = m.group("unit");
	}

	public Size(final Long value) {
		size = value.longValue();
		unit = "b";
	}

	public String getToscaForm() {
		return size + " " + unit;
	}

	public BigDecimal getValue() {
		final BigDecimal mul = getMultiplier(unit);
		final BigDecimal sizeBd = new BigDecimal(size);
		return mul.multiply(sizeBd);
	}

	public BigDecimal getMultiplier(final String unit2) {
		switch (unit2.toLowerCase()) {
		case "b":
			return BigDecimal.ONE;
		case "kb": // (1000 bytes)
			return new BigDecimal("1000");
		case "kiB":// (1024 bytes)
			return new BigDecimal("1024");
		case "mb": // (1000000 bytes)
			return new BigDecimal("1000000");
		case "mib": // (1048576 bytes)
			return new BigDecimal("1048576");
		case "gb": // (1000000000 bytes)
			return new BigDecimal("1000000000");
		case "gib": // (1073741824 bytes)
			return new BigDecimal("1073741824");
		case "tb": // 1000000000000 bytes)
			return new BigDecimal("1000000000000");
		case "tib": // 1099511627776
			return new BigDecimal("1099511627776");
		default:
			throw new ParseException("Unknown scalar unit " + unit2);
		}
	}
}
