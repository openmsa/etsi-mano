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
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.VnfInstanceSubscriptionFilter;

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
			} else if (propertyDescriptor.getPropertyType().isAssignableFrom(VnfInstanceSubscriptionFilter.class)) {
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
