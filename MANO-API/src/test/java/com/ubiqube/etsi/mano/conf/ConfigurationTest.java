package com.ubiqube.etsi.mano.conf;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.ubiqube.service.TestConfigurations;

public class ConfigurationTest {

	@Test
	void testName() throws Exception {
		final TestConfigurations conf = new TestConfigurations();
		conf.set("key", "value");
		final String val = conf.build("key")
				.notNull()
				.build();
		assertEquals("value", val);
	}
}
