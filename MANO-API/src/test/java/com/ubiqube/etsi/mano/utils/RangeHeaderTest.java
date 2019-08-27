package com.ubiqube.etsi.mano.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RangeHeaderTest {
	@Test
	public void test01() {
		final String range = "bytes=200-1000";
		final RangeHeader rangeHeader = new RangeHeader(range);
		assertEquals(rangeHeader.getFrom(), Integer.decode("200"), "Test from.");
		assertEquals(rangeHeader.getTo(), Integer.decode("1000"), "Test to.");
	}

	@Test
	public void test02() {
		final String range = "bytes=200-";
		final RangeHeader rangeHeader = new RangeHeader(range);
		assertEquals(rangeHeader.getFrom(), Integer.decode("200"), "Test from.");
		assertEquals(rangeHeader.getTo(), null, "Test to.");
	}

	@Test
	void test03() throws Exception {
		final String txt = "Range: bytes=200-1000";
		final RangeHeader range = RangeHeader.fromValue(txt);
		assertEquals(txt, range.toString());
		assertEquals("bytes 200-1000/10000", range.getContentRange(10000));
	}

	@Test
	void testNull() throws Exception {
		final RangeHeader range = RangeHeader.fromValue(null);
		assertEquals(null, range);
	}

	@Test
	public void testgetLastByte() {
		final String range = "bytes=-1";
		final RangeHeader rangeHeader = new RangeHeader(range);
		assertEquals(rangeHeader.getFrom(), null, "Test from.");
		assertEquals(rangeHeader.getTo(), Integer.decode("1"), "Test to.");
	}

}
