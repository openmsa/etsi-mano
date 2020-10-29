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
package com.ubiqube.etsi.mano.repository;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.LccnSubscriptionRequest;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.LifecycleChangeNotificationsFilter;

public class SubscriptionFilterTest {

	@Test
	void testName() throws Exception {
		final ObjectMapper desMapper = new ObjectMapper();
		final LccnSubscriptionRequest subscription = desMapper.readValue(new FileInputStream("src/test/resources/Sol003LcmSubscription-req.json"), LccnSubscriptionRequest.class);
		final LifecycleChangeNotificationsFilter filter = subscription.getFilter();
		getMapFilter(filter);
	}

	private void getMapFilter(final LifecycleChangeNotificationsFilter filter) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IntrospectionException {
		final Deque<String> stack = new LinkedList<>();
		getMapFilterInner(filter, stack);
	}

	private void getMapFilterInner(final Object filter, final Deque<String> stack) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		final BeanInfo bi = Introspector.getBeanInfo(filter.getClass());
		final PropertyDescriptor[] pd = bi.getPropertyDescriptors();
		for (final PropertyDescriptor propertyDescriptor : pd) {
			System.out.println(propertyDescriptor.getName() + " = " + propertyDescriptor.getPropertyType());
			if (propertyDescriptor.getPropertyType().isAssignableFrom(List.class)) {
				final Object value = propertyDescriptor.getReadMethod().invoke(filter);
				if (null != value) {
					handleList(propertyDescriptor, (List) value, stack);
				}
			} else if (propertyDescriptor.getPropertyType().isAssignableFrom(com.ubiqube.etsi.mano.vnfm.v261.model.VnfInstanceSubscriptionFilter.class)) {
				final Object value = propertyDescriptor.getReadMethod().invoke(filter);
				stack.push(propertyDescriptor.getName());
				getMapFilterInner(value, stack);
				stack.pop();
			} else {
				System.out.println(">>> " + buildKey(stack));
			}
		}
	}

	private void handleList(final PropertyDescriptor propertyDescriptor, final List list, final Deque<String> stack) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IntrospectionException {
		System.out.println(propertyDescriptor.getName() + " = " + list);
		int i = 0;
		for (final Object object : list) {
			stack.push(String.valueOf(i++));
			getMapFilterInner(object, stack);
			stack.pop();
		}
	}

	private String buildKey(final Deque<String> _stack) {
		final StringJoiner sj = new StringJoiner(".");
		_stack.descendingIterator().forEachRemaining(sj::add);
		return sj.toString();
	}
}
