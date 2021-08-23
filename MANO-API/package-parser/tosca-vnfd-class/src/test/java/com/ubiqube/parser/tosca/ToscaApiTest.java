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
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.UUID;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.parser.tosca.ZipUtil.Entry;
import com.ubiqube.parser.tosca.api.ToscaApi;
import com.ubiqube.parser.tosca.convert.ConvertApi;
import com.ubiqube.parser.tosca.convert.SizeConverter;
import com.ubiqube.parser.tosca.scalar.Frequency;
import com.ubiqube.parser.tosca.scalar.Size;
import com.ubiqube.parser.tosca.scalar.Time;

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
	private final Set<Class<?>> complex = new HashSet<>();

	public ToscaApiTest() {
		conv.register(Size.class.getCanonicalName(), new SizeConverter());
		complex.add(String.class);
		complex.add(UUID.class);
		complex.add(Long.class);
		complex.add(Integer.class);
		complex.add(Boolean.class);
		complex.add(OffsetDateTime.class);
		complex.add(Size.class);
		complex.add(Frequency.class);
		complex.add(Time.class);
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
		assertEquals(2, res.size());
		checknull(res.get(0));
		final List<VirtualBlockStorage> list = toscaApi.getObjects(root, parameters, VirtualBlockStorage.class);
		assertEquals(1, list.size());
		checknull(list.get(0));
		final List<VirtualObjectStorage> vos = toscaApi.getObjects(root, parameters, VirtualObjectStorage.class);
		assertEquals(1, vos.size());
		checknull(vos.get(0));
		final List<VnfVirtualLink> listVl = toscaApi.getObjects(root, parameters, VnfVirtualLink.class);
		assertEquals(3, listVl.size());
		checknull(listVl.get(0));
		final List<VduCp> listVduCp = toscaApi.getObjects(root, parameters, VduCp.class);
		assertEquals(4, listVduCp.size());
		checknull(listVduCp.get(0));
		final List<PlacementGroup> listPg = toscaApi.getObjects(root, parameters, PlacementGroup.class);
		assertEquals(1, listPg.size());
		checknull(listPg.get(0));
		final List<VduInstantiationLevels> listvduIl = toscaApi.getObjects(root, parameters, VduInstantiationLevels.class);
		assertEquals(1, listvduIl.size());
		// checknull(listvduIl.get(0));
		final List<VnfExtCp> listExtCp = toscaApi.getObjects(root, parameters, VnfExtCp.class);
		assertEquals(1, listExtCp.size());
		testVnfExtCp(listExtCp.get(0));
		checknull(listExtCp.get(0));
	}

	private static void testVnfExtCp(final VnfExtCp vnfExtCp) {
		assertEquals("ext01", vnfExtCp.getInternalName());
		final List<VirtualNetworkInterfaceRequirements> vnirs = vnfExtCp.getVirtualNetworkInterfaceRequirements();
		assertEquals(1, vnirs.size());
		final VirtualNetworkInterfaceRequirements vnir = vnirs.get(0);
		assertEquals("vl01", vnir.getName());
	}

	protected void assertFullEqual(final Object orig, final Object tgt, final Set<String> ignore, final Deque<String> stack) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		final BeanInfo beanInfo = Introspector.getBeanInfo(orig.getClass());
		final MethodDescriptor[] m = beanInfo.getMethodDescriptors();
		for (final MethodDescriptor methodDescriptor : m) {
			if (!methodDescriptor.getName().startsWith("get") || "getClass".equals(methodDescriptor.getName()) || ignore.contains(methodDescriptor.getName())) {
				continue;
			}
			LOG.debug(" + {}", methodDescriptor.getName());
			stack.push(methodDescriptor.getName());
			final Object src = methodDescriptor.getMethod().invoke(orig);
			final Object dst = methodDescriptor.getMethod().invoke(tgt);
			if (null == src) {
				LOG.warn("  - {} is null", methodDescriptor.getName());
				continue;
			}
			if (src instanceof List) {
				final List sl = (List) src;
				final List dl = (List) dst;
				assertNotNull(dl, "Target element is null for field: " + methodDescriptor.getName() + prettyStack(stack));
				assertEquals(sl.size(), dl.size(), "List are not equals " + methodDescriptor.getName() + prettyStack(stack));
				Collections.sort(sl, Comparator.comparing(Object::toString));
				Collections.sort(dl, Comparator.comparing(Object::toString));
				for (int i = 0; i < sl.size(); i++) {
					final Object so = sl.get(i);
					final Object dobj = dl.get(i);
					stack.push("[" + i + "]");
					if (isComplex(so)) {
						LOG.warn("  + Looping: {}", src.getClass());
						assertFullEqual(so, dobj, ignore, stack);
					} else {
						assertEquals(so, dobj, "List in " + methodDescriptor.getName() + ": is not equal at " + i + prettyStack(stack));
					}
					stack.pop();
				}
				stack.pop();
				continue;
			}
			if (src instanceof Map) {
				stack.pop();
				continue;
			}
			if (src instanceof Set) {
				stack.pop();
				continue;
			}
			if (isComplex(src)) {
				LOG.warn("  + Looping: {}", src.getClass());
				assertNotNull(dst, "Target element is null for field: " + methodDescriptor.getName() + prettyStack(stack));
				assertFullEqual(src, dst, ignore, stack);
			} else {
				assertEquals(src, dst, "Field " + methodDescriptor.getName() + ": must be equals." + prettyStack(stack));
			}
			stack.pop();
		}
	}

	private static String prettyStack(final Deque<String> stack) {
		return "\n" + stack.toString();
	}

	private void checknull(final Object avcDb) throws IntrospectionException, IllegalArgumentException, InvocationTargetException, IllegalAccessException {
		final List<String> err = new ArrayList<>();
		final List<String> ignore = new ArrayList<>();
		ignore.add("getInternalDescription");
		ignore.add("getInternalName");
		ignore.add("getArtifacts");
		checknullInternal(avcDb, ignore, err, new Stack<>());
		if (!err.isEmpty()) {
			final String str = err.stream().collect(Collectors.joining("\n"));
			LOG.error("Following errors have been found:\n" + str);
			throw new RuntimeException("Some errors:\n" + str);
		}
	}

	private void checknullInternal(final Object avcDb, final List<String> ignore, final List<String> err, final Stack<String> stack) throws IntrospectionException, IllegalArgumentException, InvocationTargetException, IllegalAccessException {
		final BeanInfo beanInfo = Introspector.getBeanInfo(avcDb.getClass());
		final MethodDescriptor[] m = beanInfo.getMethodDescriptors();
		for (final MethodDescriptor methodDescriptor : m) {
			if (!methodDescriptor.getName().startsWith("get") || "getClass".equals(methodDescriptor.getName())) {
				continue;
			}
			stack.push(methodDescriptor.getName());
			LOG.trace(" + {}", methodDescriptor.getName());
			final Object r = methodDescriptor.getMethod().invoke(avcDb, null);
			if ((null == r)) {
				if (!ignore.contains(methodDescriptor.getName())) {
					LOG.warn("  - {} is null", methodDescriptor.getName());
					err.add(buildError(stack));

				}
				stack.pop();
				continue;
			}
			if (r instanceof List) {
				int i = 0;
				final List l = (List) r;
				for (final Object obj : l) {
					if (isComplex(obj)) {
						LOG.warn("  + Looping: {}", r.getClass());
						stack.push("[" + i + "]");
						checknullInternal(obj, ignore, err, stack);
						stack.pop();
					}
					i++;
				}
				stack.pop();
				continue;
			}
			if (r instanceof Map) {
				final Map<Object, Object> mm = (Map) r;
				final Set<Map.Entry<Object, Object>> e = mm.entrySet();
				for (final Map.Entry me : e) {
					if (isComplex(me.getValue())) {
						LOG.warn("  + Looping: {}", r.getClass());
						stack.push("" + me.getKey());
						checknullInternal(me.getValue(), ignore, err, stack);
						stack.pop();
					}
				}
				stack.pop();
				continue;
			}
			if (r instanceof Set) {
				stack.pop();
				continue;
			}
			if (isComplex(r)) {
				LOG.warn("  + Looping: {}", r.getClass());
				checknullInternal(r, ignore, err, stack);
			}
			stack.pop();
		}
	}

	private static String buildError(final Stack<String> stack) {
		return stack.stream().collect(Collectors.joining(" -> "));
	}

	private boolean isComplex(final Object r) {
		if (r instanceof Enum) {
			return false;
		}
		return !complex.contains(r.getClass());
	}
}
