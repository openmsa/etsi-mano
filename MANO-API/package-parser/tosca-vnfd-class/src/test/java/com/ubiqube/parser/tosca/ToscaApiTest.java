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

import com.ubiqube.parser.tosca.ZipUtil.Entry;
import com.ubiqube.parser.tosca.api.ToscaApi;
import com.ubiqube.parser.tosca.convert.ConvertApi;
import com.ubiqube.parser.tosca.convert.SizeConverter;
import com.ubiqube.parser.tosca.scalar.Size;

import tosca.datatypes.nfv.VirtualNetworkInterfaceRequirements;
import tosca.groups.nfv.PlacementGroup;
import tosca.nodes.Compute;
import tosca.nodes.nfv.VduCp;
import tosca.nodes.nfv.VnfExtCp;
import tosca.nodes.nfv.VnfVirtualLink;
import tosca.nodes.nfv.vdu.VirtualBlockStorage;
import tosca.nodes.nfv.vdu.VirtualObjectStorage;
import tosca.policies.nfv.VduInstantiationLevels;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ToscaApiTest {
	private static final Logger LOG = LoggerFactory.getLogger(ToscaApiTest.class);

	private final ConvertApi conv = new ConvertApi();

	private final Map<String, String> parameters = new HashMap<>();

	public ToscaApiTest() {
		conv.register(Size.class.getCanonicalName(), new SizeConverter());
	}

	// @Test
	void testName() throws Exception {
		final ToscaParser tp = new ToscaParser("src/test/resources/web_mysql_tosca.yaml");
		final ToscaContext root = tp.getContext();
		final ToscaApi toscaApi = new ToscaApi();
		final List<Compute> res = toscaApi.getObjects(root, parameters, Compute.class);
		System.out.println("" + res);
	}

	@Test
	public void testUbiCsar() throws Exception {
		ZipUtil.makeToscaZip("/tmp/ubi-tosca.csar", Entry.of("ubi-tosca/Definitions/tosca_ubi.yaml", "Definitions/tosca_ubi.yaml"),
				Entry.of("ubi-tosca/TOSCA-Metadata/TOSCA.meta", "TOSCA-Metadata/TOSCA.meta"));
		final ToscaParser tp = new ToscaParser("/tmp/ubi-tosca.csar");
		final ToscaContext root = tp.getContext();
		final ToscaApi toscaApi = new ToscaApi();
		final List<tosca.nodes.nfv.vdu.Compute> res = toscaApi.getObjects(root, parameters, tosca.nodes.nfv.vdu.Compute.class);
		final List<VirtualBlockStorage> list = toscaApi.getObjects(root, parameters, VirtualBlockStorage.class);
		assertEquals(1, list.size());
		final List<VirtualObjectStorage> vos = toscaApi.getObjects(root, parameters, VirtualObjectStorage.class);
		assertEquals(1, vos.size());
		final List<VnfVirtualLink> listVl = toscaApi.getObjects(root, parameters, VnfVirtualLink.class);
		assertEquals(3, listVl.size());
		final List<VduCp> listVduCp = toscaApi.getObjects(root, parameters, VduCp.class);
		assertEquals(4, listVduCp.size());
		final List<PlacementGroup> listPg = toscaApi.getObjects(root, parameters, PlacementGroup.class);
		assertEquals(1, listPg.size());
		final List<VduInstantiationLevels> listvduIl = toscaApi.getObjects(root, parameters, VduInstantiationLevels.class);
		assertEquals(1, listvduIl.size());
		final List<VnfExtCp> listExtCp = toscaApi.getObjects(root, parameters, VnfExtCp.class);
		assertEquals(1, listExtCp.size());
		testVnfExtCp(listExtCp.get(0));
	}

	private static void testVnfExtCp(final VnfExtCp vnfExtCp) {
		assertEquals("ext01", vnfExtCp.getInternalName());
		final List<VirtualNetworkInterfaceRequirements> vnirs = vnfExtCp.getVirtualNetworkInterfaceRequirements();
		assertEquals(1, vnirs.size());
		final VirtualNetworkInterfaceRequirements vnir = vnirs.get(0);
		assertEquals("vl01", vnir.getName());
	}

	// @Test
	void testResolvValue() throws Exception {
		final ToscaParser tp = new ToscaParser("src/test/resources/web_mysql_tosca.yaml");
		final ToscaContext root = tp.getContext();
		final ToscaApi toscaApi = new ToscaApi();
		final List<NodeTemplate> res = toscaApi.getNodeMatching(root, parameters, Compute.class);
		assertEquals(2, res.size(), "Size of the list must be 2");
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
			methodInvoke(meth, cls, ret);
		} else {
			final Method writeMethod = x.getWriteMethod();
			methodInvoke(writeMethod, cls, convert(res, writeMethod.getParameterTypes()[0]));
		}
	}

	private Object convert(final Object res, final Class<?> parameterType) {
		if (res.getClass().equals(parameterType)) {
			return res;
		}
		LOG.debug("Converting: {} into {}", res.getClass(), parameterType.getName());
		return conv.map(parameterType.getName(), res);
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

	private static void methodInvoke(final Method method, final Object instance, final Object paramter) {
		try {
			method.invoke(instance, paramter);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new ParseException(e);
		}
	}
}
