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
package com.ubiqube.etsi.mano.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.time.OffsetDateTime;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import net.rakugakibox.spring.boot.orika.OrikaMapperFactoryConfigurer;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class TestHelper {
	private static final Logger LOG = LoggerFactory.getLogger(TestHelper.class);

	private final DefaultMapperFactory mapperFactory;
	private final PodamFactoryImpl podam;
	private final Set<Class<?>> complex = new HashSet<>();

	public TestHelper(final OrikaMapperFactoryConfigurer orikaMapperFactoryConfigurer) {
		mapperFactory = new DefaultMapperFactory.Builder().build();
		orikaMapperFactoryConfigurer.configure(mapperFactory);

		podam = new PodamFactoryImpl();
		podam.getStrategy().addOrReplaceTypeManufacturer(String.class, new UUIDManufacturer());
		complex.add(String.class);
		complex.add(UUID.class);
		complex.add(Long.class);
		complex.add(Integer.class);
		complex.add(Boolean.class);
		complex.add(OffsetDateTime.class);
	}

	protected void doTest(final Class<?> json, final Class<?> db, final Set<String> ignore) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IntrospectionException {
		final MapperFacade mapper = mapperFactory.getMapperFacade();
		final Object avc = podam.manufacturePojo(json);
		final Object avcDb = mapper.map(avc, db);
		final Object res = mapper.map(avcDb, json);
		final Deque<String> stack = new ArrayDeque<>();
		assertFullEqual(avc, res, ignore, stack);
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

	private String prettyStack(final Deque<String> stack) {
		return "\n" + stack.toString();
	}

	private void checknull(final Object avcDb) throws IntrospectionException, IllegalArgumentException, InvocationTargetException, IllegalAccessException {
		final BeanInfo beanInfo = Introspector.getBeanInfo(avcDb.getClass());
		final MethodDescriptor[] m = beanInfo.getMethodDescriptors();
		for (final MethodDescriptor methodDescriptor : m) {
			if (!methodDescriptor.getName().startsWith("get") || "getClass".equals(methodDescriptor.getName())) {
				continue;
			}
			LOG.debug(" + {}", methodDescriptor.getName());
			final Object r = methodDescriptor.getMethod().invoke(avcDb, null);
			if (null == r) {
				LOG.warn("  - {} is null", methodDescriptor.getName());
				continue;
			}
			if (r instanceof List) {
				final List l = (List) r;
				for (final Object obj : l) {
					if (isComplex(obj)) {
						LOG.warn("  + Looping: {}", r.getClass());
						checknull(obj);
					}
				}
				continue;
			}
			if (r instanceof Map) {
				continue;
			}
			if (r instanceof Set) {
				continue;
			}
			if (isComplex(r)) {
				LOG.warn("  + Looping: {}", r.getClass());
				checknull(r);
			}
		}
	}

	private boolean isComplex(final Object r) {
		if (r instanceof Enum) {
			return false;
		}
		return !complex.contains(r.getClass());
	}

}
