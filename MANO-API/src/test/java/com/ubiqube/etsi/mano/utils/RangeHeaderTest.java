package com.ubiqube.etsi.mano.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RangeHeaderTest {
	@Test
	public void test01() {
		final String range = "bytes=200-1000";
		final RangeHeader rangeHeader = new RangeHeader(range);
		assertEquals(rangeHeader.getFrom(), 200L, "Test from.");
		assertEquals(rangeHeader.getTo(), Integer.decode("1000"), "Test to.");
	}

	@Test
	public void test02() {
		final String range = "bytes=200-";
		final RangeHeader rangeHeader = new RangeHeader(range);
		assertEquals(rangeHeader.getFrom(), 200L, "Test from.");
		assertEquals(rangeHeader.getTo(), null, "Test to.");
	}
}
