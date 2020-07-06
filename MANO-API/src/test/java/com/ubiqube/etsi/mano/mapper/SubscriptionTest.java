package com.ubiqube.etsi.mano.mapper;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.common.v261.model.vnf.PackageOperationalStateType;
import com.ubiqube.etsi.mano.common.v261.model.vnf.PkgmNotificationsFilter;
import com.ubiqube.etsi.mano.common.v261.model.vnf.PkgmNotificationsFilter.NotificationTypesEnum;
import com.ubiqube.etsi.mano.common.v261.model.vnf.PkgmNotificationsFilterVnfProductsFromProviders;
import com.ubiqube.etsi.mano.common.v261.model.vnf.PkgmSubscription;
import com.ubiqube.etsi.mano.common.v261.model.vnf.PkgmSubscriptionLinks;
import com.ubiqube.etsi.mano.common.v261.model.vnf.PkgmSubscriptionRequest;
import com.ubiqube.etsi.mano.config.OrikaConfiguration;
import com.ubiqube.etsi.mano.dao.mano.ApiTypesEnum;
import com.ubiqube.etsi.mano.dao.mano.AuthParamBasic;
import com.ubiqube.etsi.mano.dao.mano.AuthentificationInformations;
import com.ubiqube.etsi.mano.dao.mano.FilterAttributes;
import com.ubiqube.etsi.mano.dao.mano.Subscription;
import com.ubiqube.etsi.mano.dao.mano.SubscriptionQuery;
import com.ubiqube.etsi.mano.nfvo.v261.OrikaConfigurationNfvo261;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class SubscriptionTest {
	private final DefaultMapperFactory mapperFactory;

	public SubscriptionTest() {
		final OrikaConfiguration orikaConfiguration = new OrikaConfiguration();
		final OrikaConfigurationNfvo261 orikaNfvo = new OrikaConfigurationNfvo261();
		mapperFactory = new DefaultMapperFactory.Builder().build();
		orikaConfiguration.configure(mapperFactory);
		orikaNfvo.configure(mapperFactory);
	}

	@Test
	void testJsonToJpa() throws Exception {
		final MapperFacade mapper = mapperFactory.getMapperFacade();
		final PkgmSubscription subsJson = new PkgmSubscription();
		subsJson.setCallbackUri("http://callbackUri/");
		final PkgmNotificationsFilter filter = new PkgmNotificationsFilter();
		filter.setNotificationTypes(NotificationTypesEnum.VnfPackageChangeNotification);
		final List<PkgmNotificationsFilterVnfProductsFromProviders> vnfProductsFromProviders = new ArrayList<>();
		final PkgmNotificationsFilterVnfProductsFromProviders subProv = new PkgmNotificationsFilterVnfProductsFromProviders();
		subProv.addOperationalStateItem(PackageOperationalStateType.DISABLED);
		vnfProductsFromProviders.add(subProv);
		filter.setVnfProductsFromProviders(vnfProductsFromProviders);
		subsJson.setFilter(filter);
		subsJson.setId(UUID.randomUUID().toString());
		final PkgmSubscriptionLinks links = new PkgmSubscriptionLinks();
		subsJson.setLinks(links);
		final PkgmSubscriptionRequest so = new PkgmSubscriptionRequest();
		so.setFilter(filter);
		final Subscription subsDb = mapper.map(so, Subscription.class);
		final List<FilterAttributes> filters = subsDb.getFilters();
		assertEquals(2, filters.size()); // Should be 2
		checkFilter(filters.get(0), "notificationTypes", "VnfPackageChangeNotification");
		checkFilter(filters.get(1), "vnfProductsFromProviders[0].operationalState[0]", "DISABLED");
	}

	private static void checkFilter(final FilterAttributes filterAttributes, final String attr, final String value) {
		assertEquals(attr, filterAttributes.getAttribute());
		assertEquals(value, filterAttributes.getValue());
	}

	@Test
	void testDbToJson() throws Exception {
		final MapperFacade mapper = mapperFactory.getMapperFacade();
		final Subscription subsDb = new Subscription();
		subsDb.setApi(ApiTypesEnum.SOL005);
		final AuthentificationInformations authentificationInformations = new AuthentificationInformations();
		final AuthParamBasic authParamBasic = new AuthParamBasic();
		authParamBasic.setUserName("user");
		authParamBasic.setPassword("pass");
		authentificationInformations.setAuthParamBasic(authParamBasic);

		subsDb.setAuthentificationInformations(authentificationInformations);
		final UUID uuid = UUID.randomUUID();
		subsDb.setId(uuid);
		final SubscriptionQuery subscriptionQuery = new SubscriptionQuery();
		subscriptionQuery.setCallbackUri("http://callBackUri/?");
		final List<FilterAttributes> subscriptionFilter = new ArrayList<>();
		final FilterAttributes fa = new FilterAttributes();
		fa.setAttribute("vnfProductsFromProviders.0.operationalState.0");
		fa.setValue("DISABLED");
		subscriptionFilter.add(fa);
		// subscriptionQuery.setSubscriptionFilter(subscriptionFilter);
		subsDb.setFilters(subscriptionFilter);
		final PkgmSubscription subsJson = mapper.map(subsDb, PkgmSubscription.class);
		assertNotNull(subsJson.getId());
	}
}
