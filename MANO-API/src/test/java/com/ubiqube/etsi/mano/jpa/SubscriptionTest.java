package com.ubiqube.etsi.mano.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ubiqube.etsi.mano.dao.mano.Subscription;

@DataJpaTest
public class SubscriptionTest {
	@Autowired
	private SubscriptionJpa subscriptionJpa;

	@Test
	void testName() throws Exception {
		final List<Subscription> res = subscriptionJpa.findEventAndVnfPkg(null, "");
		assertEquals(0, res.size());
	}
}
