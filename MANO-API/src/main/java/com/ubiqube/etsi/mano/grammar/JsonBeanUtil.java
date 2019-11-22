package com.ubiqube.etsi.mano.grammar;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
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
	}

	public Map<String, JsonBeanProperty> getProperties(@Nonnull Object _object) {
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

	private Map<String, JsonBeanProperty> rebuildProperties(Map<String, JsonBeanProperty> res) {
		final Map<String, JsonBeanProperty> ret = new HashMap<>();
		final Deque<String> stack = new LinkedList<>();
		final Deque<JsonBeanProperty> stackObject = new LinkedList<>();
		rebuildPropertiesInner(res, stack, stackObject, ret);
		return ret;
	}

	private void rebuildPropertiesInner(Map<String, JsonBeanProperty> rawProps, Deque<String> stackName, Deque<JsonBeanProperty> stackObject, final Map<String, JsonBeanProperty> ret) {

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

	private Map<String, JsonBeanProperty> buildCache(Class<?> clazz) throws IntrospectionException {
		final LinkedList<JsonBeanProperty> stack = new LinkedList<>();
		return buildCacheInner(clazz, stack);
	}

	private Map<String, JsonBeanProperty> buildCacheInner(Class<?> clazz, Deque<JsonBeanProperty> stack) throws IntrospectionException {
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
			final Method readMethod = propertyDescriptor.getReadMethod();
			final JsonProperty jsonProperty = readMethod.getAnnotation(JsonProperty.class);
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

	private boolean haveInnerType(Class<?> clazz) {
		if (clazz.getName().contentEquals("java.util.List")) {
			return true;
		}
		if (simpleTypes.contains(clazz.getName())) {
			return false;
		}
		LOG.debug("not in List/Map => {}", clazz.getName());
		return false;
	}

	private static Class<?> extractInnerListType(PropertyDescriptor propertyDescriptor) {
		final Method method = propertyDescriptor.getReadMethod();
		final ParameterizedType returnType = (ParameterizedType) method.getGenericReturnType();
		final Type[] type = returnType.getActualTypeArguments();
		return (Class<?>) type[0];
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
