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
import java.util.Queue;
import java.util.Set;

import javax.annotation.Nonnull;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.exception.GenericException;

/**
 *
 * @author olivier
 *
 */
@Service
public class JsonBeanUtil {
	/** Logger instance. */
	private static final Logger LOG = LoggerFactory.getLogger(JsonBeanUtil.class);
	private final Set<String> simpleTypes = new HashSet<>();
	private final Map<String, Map<String, JsonBeanProperty>> cache = new HashMap<>();

	public JsonBeanUtil() {
		simpleTypes.add("java.lang.String");
		simpleTypes.add("java.lang.Class");
		simpleTypes.add("java.lang.Integer");
		simpleTypes.add("int");
		simpleTypes.add("long");
		simpleTypes.add("float");
		simpleTypes.add("java.util.Date");
		simpleTypes.add("java.time.OffsetDateTime");
	}

	public Map<String, JsonBeanProperty> getProperties(@Nonnull final Object _object) {
		Map<String, JsonBeanProperty> cached = cache.get(_object.getClass().getName());
		if (cached != null) {
			return cached;
		}
		Map<String, JsonBeanProperty> res;
		try {
			res = buildCache(_object.getClass());
		} catch (final IntrospectionException e) {
			throw new GenericException(e);
		}
		cached = rebuildProperties(res);
		cache.put(_object.getClass().getName(), cached);
		return cached;
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
			} else {
				final String newKey = StringUtils.join(stackName.descendingIterator(), ".");
				final Queue<JsonBeanProperty> rev = Collections.asLifoQueue(stackObject);
				final List<JsonBeanProperty> listObject = new ArrayList<>(rev);
				Collections.reverse(listObject);
				jsonBeanProperty.setAccessorsList(listObject);
				ret.put(newKey, jsonBeanProperty);
			}
			stackName.pop();
			stackObject.pop();
		}
	}

	private Map<String, JsonBeanProperty> buildCache(final Class<?> clazz) throws IntrospectionException {
		final LinkedList<JsonBeanProperty> stack = new LinkedList<>();
		return buildCacheInner(clazz, stack);
	}

	private Map<String, JsonBeanProperty> buildCacheInner(final Class<?> clazz, final Deque<JsonBeanProperty> stack) throws IntrospectionException {
		final Map<String, JsonBeanProperty> properties = new HashMap<>();
		LOG.info("Building AST of a {}", clazz.getName());
		final BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
		final PropertyDescriptor[] propDescs = beanInfo.getPropertyDescriptors();

		for (final PropertyDescriptor propertyDescriptor : propDescs) {
			if ("class".equals(propertyDescriptor.getName())
					|| "declaringClass".equals(propertyDescriptor.getName())
					|| "java.lang.ClassLoader".equals(propertyDescriptor.getName())) {
				continue;
			}
			LOG.info("Handling property: {}", propertyDescriptor.getName());
			if ("links".equals(propertyDescriptor.getName())) {
				LOG.debug("");
			}
			final JsonProperty jsonProperty = findNamedAnnotaion(propertyDescriptor, clazz);
			String jsonName = propertyDescriptor.getName();
			if (null != jsonProperty) {
				jsonName = jsonProperty.value();
			}
			final JsonBeanProperty jsonBeanProperty = new JsonBeanProperty(propertyDescriptor, jsonName);
			final Class<?> propertyType = propertyDescriptor.getPropertyType();
			if (haveInnerType(propertyType)) {
				final Class<?> clazzRet = extractInnerListType(propertyDescriptor);
				if (isComplex(clazzRet)) {
					stack.push(jsonBeanProperty);
					final Map<String, JsonBeanProperty> res = buildCacheInner(clazzRet, stack);
					jsonBeanProperty.setRight(res);
				}
			} else if (isComplex(propertyType)) {
				stack.push(jsonBeanProperty);
				final Map<String, JsonBeanProperty> res = buildCacheInner(propertyType, stack);
				jsonBeanProperty.setRight(res);
			}
			properties.put(jsonName, jsonBeanProperty);
		}
		LOG.info("AST of a {} is Done...", clazz.getName());
		return properties;
	}

	private static JsonProperty findNamedAnnotaion(final PropertyDescriptor propertyDescriptor, final Class<?> clazz) {
		if (propertyDescriptor.getName().equals("links")) {
			LOG.debug("");
		}
		Method method = propertyDescriptor.getWriteMethod();
		if (method != null) {
			final JsonProperty ann = method.getAnnotation(JsonProperty.class);
			if (ann != null) {
				return ann;
			}
		}
		if (method != null) {
			method = propertyDescriptor.getReadMethod();
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
			LOG.warn("Could not find field for annotation: " + name);
		}
		return null;
	}

	private boolean haveInnerType(final Class<?> clazz) {
		if (clazz.getName().contentEquals("java.util.List")) {
			return true;
		}
		if (simpleTypes.contains(clazz.getName())) {
			return false;
		}
		LOG.debug("not in List/Map => {}", clazz.getName());
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
