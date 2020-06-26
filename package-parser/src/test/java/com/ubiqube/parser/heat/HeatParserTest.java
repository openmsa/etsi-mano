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
