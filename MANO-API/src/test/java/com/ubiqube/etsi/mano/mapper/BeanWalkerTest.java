package com.ubiqube.etsi.mano.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.model.vnf.sol005.SubscriptionsPkgmSubscription;
import com.ubiqube.etsi.mano.model.vnf.sol005.SubscriptionsPkgmSubscriptionFilter;
import com.ubiqube.etsi.mano.model.vnf.sol005.SubscriptionsPkgmSubscriptionFilter.NotificationTypesEnum;
import com.ubiqube.etsi.mano.model.vnf.sol005.SubscriptionsPkgmSubscriptionFilterVnfProductsFromProviders;
import com.ubiqube.etsi.mano.model.vnf.sol005.SubscriptionsPkgmSubscriptionFilterVnfProductsFromProviders.OperationalStateEnum;
import com.ubiqube.etsi.mano.model.vnf.sol005.SubscriptionsPkgmSubscriptionLinks;

public class BeanWalkerTest {

	@Test
	void testName() throws Exception {
		final SubscriptionsPkgmSubscription subsJson = new SubscriptionsPkgmSubscription();
		subsJson.setCallbackUri("http://callbackUri/");
		final SubscriptionsPkgmSubscriptionFilter filter = new SubscriptionsPkgmSubscriptionFilter();
		filter.setNotificationTypes(NotificationTypesEnum.VNFPACKAGECHANGENOTIFICATION);
		final List<SubscriptionsPkgmSubscriptionFilterVnfProductsFromProviders> vnfProductsFromProviders = new ArrayList<>();
		final SubscriptionsPkgmSubscriptionFilterVnfProductsFromProviders subProv = new SubscriptionsPkgmSubscriptionFilterVnfProductsFromProviders();
		subProv.addOperationalStateItem(OperationalStateEnum.DISABLED);
		vnfProductsFromProviders.add(subProv);
		filter.setVnfProductsFromProviders(vnfProductsFromProviders);
		subsJson.setFilter(filter);
		subsJson.setId(UUID.randomUUID().toString());
		final SubscriptionsPkgmSubscriptionLinks links = new SubscriptionsPkgmSubscriptionLinks();
		subsJson.setLinks(links);

		final BeanWalker bw = new BeanWalker();
		final CollectNonNullListener beanListener = new CollectNonNullListener();

		bw.walk(subsJson, beanListener);

		final List<AttrHolder> attrs = beanListener.getAttrs();
		attrs.forEach(x -> System.out.println("" + x.getStack() + " = " + x.getValue()));

		System.out.println("" + subsJson);
	}
}
