package com.ubiqube.etsi.mano.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class SubscriptionTest {
	@Autowired
	private SubscriptionJpa subscriptionJpa;

	@Test
	void testName() throws Exception {
		subscriptionJpa.findEventAndVnfPkg(null, "");
	}
}
