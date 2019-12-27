package com.ubiqube.parser.tosca;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
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
		assertEquals(1, res.size());
		final NodeTemplate obj = res.get(0);
		final Map<String, Object> caps = (Map<String, Object>) obj.getCapabilities();
		handleMap(caps, Compute.class, null);
	}

	private void handleMap(final Map<String, Object> caps, final Class clazz, final Class generic) throws IntrospectionException {
		final BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
		final PropertyDescriptor[] props = beanInfo.getPropertyDescriptors();
		LOG.info("class=>{}[{}] --- [{}]", clazz.getName(), caps, Arrays.toString(props));
		if (clazz.isAssignableFrom(Map.class)) {
			LOG.debug("Handling map of {}", generic);
			return;
		}
		final Stream<PropertyDescriptor> stream = Arrays.stream(props);
		stream.forEach(x -> {
			final Object res = caps.get(camelCaseToUnderscore(x.getName()));
			if (null != res) {
				LOG.info("Property: {}={}", x.getName(), res);
				handleCaps(res, x);
			}
		});
	}

	private void handleCaps(final Object res, final PropertyDescriptor x) {
		if (res instanceof Map) {
			Map<String, Object> caps = (Map<String, Object>) res;
			if (null != caps.get("properties")) {
				caps = (Map<String, Object>) caps.get("properties");
			}
			LOG.debug("Recursing: {}", caps);
			try {
				final Method rm = x.getReadMethod();
				final Class zz = getReturnType(rm);
				if (rm.getReturnType().isAssignableFrom(Map.class)) {
					handleMap(caps, Map.class, zz);
				} else {
					handleMap(caps, zz, zz);
				}
			} catch (final IntrospectionException e) {
				throw new ParseException(e);
			}
		} else {
			LOG.error("Unkwon type: {} => {}", x.getName(), res.getClass().getName());
		}
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

	private static String fieldCamelCase(final String key) {
		final java.util.regex.Pattern p = java.util.regex.Pattern.compile("_(.)");
		final Matcher m = p.matcher(key);
		final StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, m.group(1).toUpperCase());
		}
		m.appendTail(sb);
		LOG.debug("CamelCase:  {}<=>{}", key, sb.toString());
		return sb.toString();
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
}
