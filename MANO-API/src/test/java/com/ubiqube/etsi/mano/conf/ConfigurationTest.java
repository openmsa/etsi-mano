package com.ubiqube.etsi.mano.conf;

import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.service.Configuration;

public class ConfigurationTest {

	@Test
	void testName() throws Exception {
		final Configuration conf = null;
		final String val = conf.build("")
				.notNull()
				.build();
	}
}
