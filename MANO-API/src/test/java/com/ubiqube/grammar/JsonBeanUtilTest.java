package com.ubiqube.grammar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.grammar.JsonBeanProperty;
import com.ubiqube.etsi.mano.grammar.JsonBeanUtil;
import com.ubiqube.etsi.mano.model.vnf.sol005.SubscriptionObject;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;

public class JsonBeanUtilTest {

	@Test
	void testVnfPkg() throws Exception {
		final JsonBeanUtil jsonBeanUtil = new JsonBeanUtil();
		final Object _object = new VnfPkgInfo();
		final Map<String, JsonBeanProperty> res = jsonBeanUtil.getProperties(_object);

		JsonBeanProperty element = res.get("softwareImages.name");
		assertNotNull(element, "Could not find softwareImages.name");
		List<JsonBeanProperty> accessors = element.getListAccessors();
		assertNotNull(accessors, "List of accessors is null.");
		assertEquals(2, accessors.size(), "List of accessors should be 2");
		assertEquals("softwareImages", accessors.get(0).getJsonName(), "Sowtware Image not found.");

		element = res.get("_links.self.href");
		assertNotNull(element, "Could not find _links.self.href");
		accessors = element.getListAccessors();
		assertNotNull(accessors, "List of accessors is null.");
		assertEquals(3, accessors.size(), "List of accessors should be 2");
	}

	@Test
	void testSubscription() throws Exception {
		final JsonBeanUtil jsonBeanUtil = new JsonBeanUtil();
		final Object _object = new SubscriptionObject();
		final Map<String, JsonBeanProperty> res = jsonBeanUtil.getProperties(_object);

		System.out.println("" + res);
		final JsonBeanProperty element = res.get("subscriptionsPkgmSubscription.filter.vnfProductsFromProviders.vnfProducts.versions.vnfdVersions");
		assertNotNull(element, "Could not find softwareImages.name");
		final List<JsonBeanProperty> accessors = element.getListAccessors();
		assertNotNull(accessors, "List of accessors is null.");
		assertEquals(6, accessors.size(), "List of accessors should be 2");
	}

	@Test
	void testCache() throws Exception {
		final JsonBeanUtil jsonBeanUtil = new JsonBeanUtil();
		final Object _object = new SubscriptionObject();
		final Map<String, JsonBeanProperty> res1 = jsonBeanUtil.getProperties(_object);
		final Map<String, JsonBeanProperty> res2 = jsonBeanUtil.getProperties(_object);

		assertEquals(res1, res2, "Result should be the same.");
	}
}