package com.ubiqube.etsi.mano.conf;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.service.PropertiesConfiguration;

public class ConfigurationTest {

	@Test
	void testName() throws Exception {
		final PropertiesConfiguration conf = new PropertiesConfiguration();
		conf.set("key", "value");
		final String val = conf.build("key")
				.notNull()
				.build();
		assertEquals("value", val);
	}
}
