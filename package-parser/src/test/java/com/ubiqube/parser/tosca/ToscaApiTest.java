package com.ubiqube.parser.tosca;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.parser.tosca.api.ToscaApi;

import tosca.nodes.Compute;

public class ToscaApiTest {
	private static final Logger LOG = LoggerFactory.getLogger(ToscaApiTest.class);
	private final ToscaParser tp = new ToscaParser();

	@Test
	void testName() throws Exception {
		final ToscaContext root = tp.parse("src/test/resources/web_mysql_tosca.yaml");
		final ToscaApi toscaApi = new ToscaApi();
		final List<Compute> res = toscaApi.getObjects(root, Compute.class);
		System.out.println("" + res);
	}

	@Test
	void testResolvValue() throws Exception {
		final ToscaContext root = tp.parse("src/test/resources/web_mysql_tosca.yaml");
		final ToscaApi toscaApi = new ToscaApi();
		final List<NodeTemplate> res = toscaApi.getNodeMatching(root, Compute.class);
		assertEquals(1, res.size(), "Size of the list must be 1");
		final NodeTemplate obj = res.get(0);
		final Map<String, Object> caps = (Map<String, Object>) obj.getCapabilities();
		final Object maps = handleMap(caps, Compute.class, null);
		LOG.debug("map={}", maps);
	}

	private Object handleMap(final Map<String, Object> caps, final Class clazz, final Class generic) {
		final Object cls = newInstance(clazz);
		BeanInfo beanInfo;
		try {
			beanInfo = Introspector.getBeanInfo(clazz);
		} catch (final IntrospectionException e) {
			throw new ParseException(e);
		}
		final PropertyDescriptor[] props = beanInfo.getPropertyDescriptors();
		LOG.info("class=>{}[{}] --- [{}]", clazz.getName(), caps, Arrays.toString(props));
		if (clazz.isAssignableFrom(Map.class)) {
			LOG.debug("Handling map of {}", generic);
			final Map map = (Map) cls;
			handleRealMap(map, generic, caps);
			// map.put(key, value)
		}
		final Stream<PropertyDescriptor> stream = Arrays.stream(props);
		stream.forEach(x -> {
			final Object res = caps.get(camelCaseToUnderscore(x.getName()));
			if (null != res) {
				LOG.info("Property: {}={}", x.getName(), res);
				handleCaps(res, x, cls);
			}
		});
		return cls;
	}

	private void handleRealMap(final Map map, final Class generic, final Map<String, Object> caps) {
		caps.forEach((x, y) -> {
			final Object res = handleMap((Map<String, Object>) y, generic, null);
			map.put(x, res);
		});
	}

	private void handleCaps(final Object res, final PropertyDescriptor x, final Object cls) {
		if (res instanceof Map) {
			Map<String, Object> caps = (Map<String, Object>) res;
			if (null != caps.get("properties")) {
				caps = (Map<String, Object>) caps.get("properties");
			}
			LOG.debug("Recursing: {}", caps);
			final Method rm = x.getReadMethod();
			final Class zz = getReturnType(rm);
			Object ret = null;
			if (rm.getReturnType().isAssignableFrom(Map.class)) {
				ret = handleMap(caps, Map.class, zz);
			} else {
				ret = handleMap(caps, zz, zz);
			}
			LOG.debug("return: {} for property: {}", ret, x.getName());
			final Method meth = x.getWriteMethod();
			try {
				meth.invoke(cls, ret);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				throw new ParseException(e);
			}
		} else {
			final Method writeMethod = x.getWriteMethod();
			try {
				writeMethod.invoke(cls, convert(res, writeMethod.getParameterTypes()[0]));
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				throw new ParseException(e);
			}
		}
	}

	private Object convert(final Object res, final Class<?> parameterType) {
		if (res.getClass().equals(parameterType)) {
			return res;
		}
		LOG.debug("Converting: {} into {}", res.getClass(), parameterType.getName());
		return null;
	}

	private static Class getReturnType(final Method readMethod) {
		LOG.debug("Return Type={}", readMethod.getReturnType());
		final Type returnTypes = readMethod.getGenericReturnType();
		if (returnTypes instanceof ParameterizedType) {
			final ParameterizedType rt = (ParameterizedType) returnTypes;
			final Type[] ata = rt.getActualTypeArguments();
			if (ata.length == 1) {
				return (Class) ata[0];
			}
			return (Class) ata[1];
		}
		return readMethod.getReturnType();
	}

	private static String camelCaseToUnderscore(final String key) {
		final Matcher m = Pattern.compile("(?<=[a-z])[A-Z]").matcher(key);

		final StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "_" + m.group().toLowerCase());
		}
		m.appendTail(sb);
		LOG.trace("Underscore:  {}<=>{}", key, sb.toString());
		return sb.toString();
	}

	private static Object newInstance(final Class<?> clazz) {
		try {
			if (clazz.isAssignableFrom(Map.class)) {
				return new HashMap();
			}
			return clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new ParseException(e);
		}
	}
}
