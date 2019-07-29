package com.ubiqube.etsi.mano.filter;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;

public class FilterTest {

	private static final Logger LOG = LoggerFactory.getLogger(FilterTest.class);
	private final Set<String> simpleTypes = new HashSet<>();

	public FilterTest() {
		simpleTypes.add("java.lang.String");
		simpleTypes.add("java.lang.Class");
		simpleTypes.add("java.lang.Integer");
		simpleTypes.add("int");
		simpleTypes.add("long");
		simpleTypes.add("float");
		simpleTypes.add("java.util.Date");
	}

	@Test
	public void testFilter() throws IntrospectionException {
		buildCache(VnfPkgInfo.class);
	}

	private Map<String, JsonBeanProperty> buildCache(Class clazz) throws IntrospectionException {
		final Map<String, JsonBeanProperty> properties = new HashMap<>();
		LOG.info("Building AST of a {}", clazz.getName());
		final BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
		final PropertyDescriptor[] propDescs = beanInfo.getPropertyDescriptors();

		for (final PropertyDescriptor propertyDescriptor : propDescs) {
			if ("class".equals(propertyDescriptor.getName())) {
				continue;
			}
			LOG.info("Handling property: {}", propertyDescriptor.getName());
			final Method writeMethod = propertyDescriptor.getReadMethod();
			final JsonProperty jsonProperty = writeMethod.getAnnotation(JsonProperty.class);
			String jsonName = propertyDescriptor.getName();
			if (null != jsonProperty) {
				jsonName = jsonProperty.value();
			}
			final JsonBeanProperty jsonBeanProperty = new JsonBeanProperty(propertyDescriptor, jsonName);
			final Class<?> propertyType = propertyDescriptor.getPropertyType();
			if (haveInnerType(propertyType)) {
				final Class clazzRet = extractInnerListType(propertyDescriptor);
				if (isComplex(clazzRet)) {
					buildCache(clazzRet);
				}
			} else if (isComplex(propertyType)) {
				buildCache(propertyType);
			}
			properties.put(jsonName, jsonBeanProperty);
		}
		LOG.info("AST of a {} is Done...", clazz.getName());
		return properties;
	}

	private boolean haveInnerType(Class clazz) {
		if (clazz.getName().contentEquals("java.util.List")) {
			return true;
		}
		if (simpleTypes.contains(clazz.getName())) {
			return false;
		}
		LOG.debug("not in List/Map => {}", clazz.getName());
		return false;
	}

	private Class extractInnerListType(PropertyDescriptor propertyDescriptor) {
		final Method method = propertyDescriptor.getReadMethod();
		final ParameterizedType returnType = (ParameterizedType) method.getGenericReturnType();
		final Type[] type = returnType.getActualTypeArguments();
		return (Class) type[0];
	}

	private boolean isComplex(Class<?> propertyType) {
		final String name = propertyType.getName();
		if (simpleTypes.contains(name)) {
			return false;
		} else if ("java.lang.Object".equals(name)) {
			LOG.warn("Could not handle {}, considering as a simple type.", name);
			return false;
		}
		LOG.debug("Complex: {}", propertyType.getName());
		return true;
	}
}
