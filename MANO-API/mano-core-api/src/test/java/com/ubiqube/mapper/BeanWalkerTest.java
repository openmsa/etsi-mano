/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.common.v261.model.vnf.PackageOperationalStateType;
import com.ubiqube.etsi.mano.common.v261.model.vnf.PkgmNotificationsFilter;
import com.ubiqube.etsi.mano.common.v261.model.vnf.PkgmNotificationsFilter.NotificationTypesEnum;
import com.ubiqube.etsi.mano.common.v261.model.vnf.PkgmNotificationsFilterVnfProductsFromProviders;
import com.ubiqube.etsi.mano.common.v261.model.vnf.PkgmSubscription;
import com.ubiqube.etsi.mano.common.v261.model.vnf.PkgmSubscriptionLinks;
import com.ubiqube.etsi.mano.common.v261.model.vnf.VnfPkgInfo;
import com.ubiqube.etsi.mano.dao.mano.FilterAttributes;
import com.ubiqube.etsi.mano.mapper.AttrHolder;
import com.ubiqube.etsi.mano.mapper.BeanWalker;
import com.ubiqube.etsi.mano.mapper.CollectNonNullListener;
import com.ubiqube.etsi.mano.mapper.SpelWriter;

import ma.glasnost.orika.impl.DefaultMapperFactory;

public class BeanWalkerTest {

	private final DefaultMapperFactory mapperFactory;

	public BeanWalkerTest() {
		mapperFactory = new DefaultMapperFactory.Builder().build();
	}

	@Test
	void testName() throws Exception {
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
		subsJson.setId(UUID.fromString("96feacab-2765-4927-9bf5-883f6b566f36").toString());
		final PkgmSubscriptionLinks links = new PkgmSubscriptionLinks();
		subsJson.setLinks(links);

		final BeanWalker bw = new BeanWalker();
		final CollectNonNullListener beanListener = new CollectNonNullListener();
		bw.walk(subsJson, beanListener);
		final List<AttrHolder> attrs = beanListener.getAttrs();
		final SpelWriter sw = new SpelWriter(mapperFactory.getMapperFacade());
		final List<FilterAttributes> swRes = sw.getFilterAttrs(attrs);

		assertEquals(4, swRes.size());
		FilterAttributes swElem = swRes.get(0);
		assertEquals("callbackUri", swElem.getAttribute());
		assertEquals("http://callbackUri/", swElem.getValue());
		swElem = swRes.get(1);
		assertEquals("filter.notificationTypes", swElem.getAttribute());
		assertEquals("VnfPackageChangeNotification", swElem.getValue());
		swElem = swRes.get(2);
		assertEquals("filter.vnfProductsFromProviders[0].operationalState[0]", swElem.getAttribute());
		assertEquals("DISABLED", swElem.getValue());
		swElem = swRes.get(3);
		assertEquals("id", swElem.getAttribute());
		assertEquals("96feacab-2765-4927-9bf5-883f6b566f36", swElem.getValue());
	}

	@Test
	void testHashMap() throws Exception {
		final VnfPkgInfo subsJson = new VnfPkgInfo();
		final Map<String, String> userDefinedData = new HashMap<>();
		userDefinedData.put("test", "value");
		subsJson.setUserDefinedData(userDefinedData);
		final BeanWalker bw = new BeanWalker();
		final CollectNonNullListener beanListener = new CollectNonNullListener();
		bw.walk(subsJson, beanListener);
		final List<AttrHolder> attrs = beanListener.getAttrs();
		final SpelWriter sw = new SpelWriter(mapperFactory.getMapperFacade());
		final List<FilterAttributes> swRes = sw.getFilterAttrs(attrs);
		assertEquals(1, swRes.size());
		final FilterAttributes sw0 = swRes.get(0);
		assertEquals("userDefinedData[test]", sw0.getAttribute());
	}
}
