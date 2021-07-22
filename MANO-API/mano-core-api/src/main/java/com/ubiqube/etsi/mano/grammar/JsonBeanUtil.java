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
package com.ubiqube.etsi.mano.grammar;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.StringJoiner;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.exception.GenericException;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class JsonBeanUtil {
	/** Logger instance. */
	private static final Logger LOG = LoggerFactory.getLogger(JsonBeanUtil.class);
	private final Set<String> simpleTypes = new HashSet<>();
	private static final Map<String, Map<String, JsonBeanProperty>> CACHE = new ConcurrentHashMap<>();

	public JsonBeanUtil() {
		simpleTypes.add("java.lang.String");
		simpleTypes.add("java.lang.Boolean");
		simpleTypes.add("java.lang.Class");
		simpleTypes.add("java.lang.Integer");
		simpleTypes.add("java.lang.Long");
		simpleTypes.add("boolean");
		simpleTypes.add("int");
		simpleTypes.add("long");
		simpleTypes.add("float");
		simpleTypes.add("java.util.Date");
		simpleTypes.add("java.time.OffsetDateTime");
	}

	public Map<String, JsonBeanProperty> getPropertiesFromClass(@Nonnull final Class<?> _object) {
		Map<String, JsonBeanProperty> cached = CACHE.get(_object.getName());
		if (cached != null) {
			return cached;
		}
		Map<String, JsonBeanProperty> res;
		try {
			res = buildCache(_object);
		} catch (final IntrospectionException e) {
			throw new GenericException(e);
		}
		cached = rebuildProperties(res);
		CACHE.put(_object.getName(), cached);
		return cached;
	}

	public Map<String, JsonBeanProperty> getProperties(@Nonnull final Object _object) {
		return getPropertiesFromClass(_object.getClass());
	}

	private Map<String, JsonBeanProperty> rebuildProperties(final Map<String, JsonBeanProperty> res) {
		final Map<String, JsonBeanProperty> ret = new HashMap<>();
		final Deque<String> stack = new LinkedList<>();
		final Deque<JsonBeanProperty> stackObject = new LinkedList<>();
		rebuildPropertiesInner(res, stack, stackObject, ret);
		return ret;
	}

	private void rebuildPropertiesInner(final Map<String, JsonBeanProperty> rawProps, final Deque<String> stackName, final Deque<JsonBeanProperty> stackObject, final Map<String, JsonBeanProperty> ret) {
		for (final Map.Entry<String, JsonBeanProperty> entry : rawProps.entrySet()) {
			final String key = entry.getKey();
			final JsonBeanProperty jsonBeanProperty = entry.getValue();
			final Map<String, JsonBeanProperty> right = jsonBeanProperty.getRight();
			stackName.push(key);
			stackObject.push(jsonBeanProperty);
			if (right != null) {
				rebuildPropertiesInner(right, stackName, stackObject, ret);
			}
			final String newKey = createKey(stackName);
			final List<JsonBeanProperty> listAccessor = getAccessorList(stackObject);
			jsonBeanProperty.setAccessorsList(listAccessor);
			ret.put(newKey, jsonBeanProperty);
			stackName.pop();
			stackObject.pop();
		}
	}

	private static List<JsonBeanProperty> getAccessorList(final Deque<JsonBeanProperty> stackObject) {
		final Queue<JsonBeanProperty> rev = Collections.asLifoQueue(stackObject);
		final List<JsonBeanProperty> listObject = new ArrayList<>(rev);
		Collections.reverse(listObject);
		return listObject;
	}

	private static String createKey(final Deque<String> stackName) {
		final StringJoiner sj = new StringJoiner(".");
		stackName.descendingIterator().forEachRemaining(sj::add);
		return sj.toString();
	}

	private Map<String, JsonBeanProperty> buildCache(final Class<?> clazz) throws IntrospectionException {
		final Map<String, JsonBeanProperty> properties = new HashMap<>();
		LOG.debug("Building AST of a {}", clazz.getName());
		final BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
		final PropertyDescriptor[] propDescs = beanInfo.getPropertyDescriptors();

		for (final PropertyDescriptor propertyDescriptor : propDescs) {
			if (isJavaInnerClass(propertyDescriptor.getName())) {
				continue;
			}
			LOG.debug("Handling property: {}", propertyDescriptor.getName());
			final String jsonName = getPropertyName(clazz, propertyDescriptor);
			final JsonBeanProperty jsonBeanProperty = new JsonBeanProperty(propertyDescriptor, jsonName);
			final Class<?> propertyType = propertyDescriptor.getPropertyType();
			if (isContainer(propertyType)) {
				final Class<?> clazzRet = extractInnerListType(propertyDescriptor);
				if (isComplex(clazzRet)) {
					final Map<String, JsonBeanProperty> res = buildCache(clazzRet);
					jsonBeanProperty.setRight(res);
				}
			} else if (isComplex(propertyType)) {
				final Map<String, JsonBeanProperty> res = buildCache(propertyType);
				jsonBeanProperty.setRight(res);
			}
			properties.put(jsonName, jsonBeanProperty);
		}
		LOG.debug("AST of a {} is Done...", clazz.getName());
		return properties;
	}

	private static String getPropertyName(final Class<?> clazz, final PropertyDescriptor propertyDescriptor) {
		return Optional.ofNullable(findNamedAnnotaion(propertyDescriptor, clazz))
				.map(JsonProperty::value)
				.orElse(propertyDescriptor.getName());
	}

	private static boolean isJavaInnerClass(final String name) {
		return "class".equals(name)
				|| "declaringClass".equals(name)
				|| "java.lang.ClassLoader".equals(name);
	}

	private static JsonProperty findNamedAnnotaion(final PropertyDescriptor propertyDescriptor, final Class<?> clazz) {
		Method method = propertyDescriptor.getWriteMethod();
		if (method != null) {
			final JsonProperty ann = method.getAnnotation(JsonProperty.class);
			if (ann != null) {
				return ann;
			}
		}
		method = propertyDescriptor.getReadMethod();
		if (method != null) {
			final JsonProperty ann = method.getAnnotation(JsonProperty.class);
			if (ann != null) {
				return ann;
			}
		}
		// Try to get annotation on field.
		final String name = propertyDescriptor.getName();
		try {
			final Field field = clazz.getDeclaredField(name);
			return field.getAnnotation(JsonProperty.class);
		} catch (NoSuchFieldException | SecurityException e) {
			LOG.trace("", e);
			LOG.warn("Could not find field for annotation: {}", name);
		}
		return null;
	}

	private boolean isContainer(final Class<?> clazz) {
		if (clazz.getName().contentEquals("java.util.List")) {
			return true;
		}
		if (clazz.getName().contentEquals("java.util.Map")) {
			return true;
		}
		if (simpleTypes.contains(clazz.getName())) {
			return false;
		}
		if (clazz.isEnum()) {
			return false;
		}
		LOG.warn("not in List/Map => {}", clazz.getName());
		return false;
	}

	private static Class<?> extractInnerListType(final PropertyDescriptor propertyDescriptor) {
		final Method method = propertyDescriptor.getReadMethod();
		final ParameterizedType returnType = (ParameterizedType) method.getGenericReturnType();
		final Type[] type = returnType.getActualTypeArguments();
		return (Class<?>) type[0];
	}

	private boolean isComplex(final Class<?> propertyType) {
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
