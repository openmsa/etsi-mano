package com.ubiqube.parser.tosca.scalar;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ubiqube.parser.tosca.ParseException;

public class Time {

	private final long time;
	private final String unit;

	public Time(final String value) {
		final Pattern p = Pattern.compile("(?<time>[0-9]+)\\s*(?<unit>d|h|m|s|ms|us|ns)");
		final Matcher m = p.matcher(value);
		if (!m.find()) {
			throw new ParseException("Size scalr: Unable to find a match for: " + value);
		}
		time = Long.parseLong(m.group("time"));
		unit = m.group("unit");
	}

	public String getToscaForm() {
		return time + " " + unit;
	}

	public BigDecimal getValue() {
		final BigDecimal mul = getMultiplier(unit);
		final BigDecimal sizeBd = new BigDecimal(time);
		return mul.multiply(sizeBd);
	}

	public BigDecimal getMultiplier(final String unit2) {
		switch (unit2.toLowerCase()) {
		case "d": // 3600000000000*24
			return new BigDecimal("86400000000000");
		case "h": // 60000000000*60
			return new BigDecimal("3600000000000");
		case "m":// 1000000000*60
			return new BigDecimal("60000000000");
		case "s":// 10^1
			return new BigDecimal("1000000000");
		case "ms": // 10^-3
			return new BigDecimal("1000000");
		case "us": // 10^-6
			return new BigDecimal("1000");
		case "ns": // 10^-9
			return new BigDecimal("1");
		default:
			throw new ParseException("Unknown scalar unit " + unit2);
		}
	}
}
