package com.ubiqube.etsi.mano.mapper;

import java.beans.BeanInfo;
import java.beans.FeatureDescriptor;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.etsi.mano.exception.GenericException;

public class BeanWalker {
	private static final Logger LOG = LoggerFactory.getLogger(BeanWalker.class);
	private final Set<String> simpleTypes = new HashSet<>();

	public BeanWalker() {
		simpleTypes.add("java.lang.String");
		simpleTypes.add("java.lang.Class");
		simpleTypes.add("java.lang.Integer");
		simpleTypes.add("int");
		simpleTypes.add("long");
		simpleTypes.add("float");
		simpleTypes.add("java.util.Date");
	}

	public void walk(final Object source, final BeanListener beanListener) {
		try {
			makeFieldInner(source, beanListener);
		} catch (final IntrospectionException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new GenericException(e);
		}
	}

	private void makeFieldInner(final Object source, final BeanListener beanListener) throws IntrospectionException, IllegalAccessException, InvocationTargetException {
		if (null == source) {
			beanListener.addProperty(source);
			return;
		}
		final BeanInfo cls = Introspector.getBeanInfo(source.getClass());
		final PropertyDescriptor[] clspd = cls.getPropertyDescriptors();
		if (!isComplex(source.getClass())) {
			beanListener.addProperty(source);
		}
		for (final PropertyDescriptor propertyDescriptor : clspd) {
			if ("class".equals(propertyDescriptor.getName())
					|| "declaringClass".equals(propertyDescriptor.getName())
					|| "java.lang.ClassLoader".equals(propertyDescriptor.getName())) {
				continue;
			}
			LOG.info("Handling property: {}", propertyDescriptor.getName());
			final Method readMethod = propertyDescriptor.getReadMethod();
			final Class<?> retCls = readMethod.getReturnType();
			if (haveInnerType(retCls)) {
				final Class<?> clazzRet = extractInnerListType(propertyDescriptor);
				if (retCls.isAssignableFrom(List.class)) {
					handleList(source, propertyDescriptor.getName(), readMethod, beanListener);
				} else if (retCls.isAssignableFrom(Map.class)) {
					handleMap(source, propertyDescriptor.getName(), readMethod, beanListener);
				} else if (isComplex(clazzRet)) {
					handleComplex(source, propertyDescriptor, readMethod, beanListener);
				}
			} else if (isComplex(retCls)) {
				handleComplex(source, propertyDescriptor, readMethod, beanListener);
			} else {
				beanListener.complexStart(propertyDescriptor.getName());
				final Object val = readMethod.invoke(source);
				beanListener.addProperty(val);
				beanListener.complexEnd();
			}
		}
	}

	@SuppressWarnings("rawtypes")
	private static void handleMap(final Object source, final String name, final Method readMethod, final BeanListener beanListener) throws IllegalAccessException, InvocationTargetException {
		final Map map = (Map) readMethod.invoke(source);
		beanListener.startMap(name);
		final Set<Entry> entries = map.entrySet();
		for (final Entry entry : entries) {
			beanListener.mapStartEntry((String) entry.getKey());
			beanListener.addProperty(entry.getValue());
			beanListener.mapEndEntry((String) entry.getKey());
		}
		beanListener.endMap(name);
	}

	private void handleComplex(final Object source, final FeatureDescriptor propertyDescriptor, final Method readMethod, final BeanListener beanListener) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IntrospectionException {
		beanListener.complexStart(propertyDescriptor.getName());
		final Object val = readMethod.invoke(source);
		makeFieldInner(val, beanListener);
		beanListener.complexEnd();
	}

	private void handleList(final Object source, final String name, final Method readMethod, final BeanListener beanListener) throws IllegalAccessException, InvocationTargetException, IntrospectionException {
		@SuppressWarnings("rawtypes")
		final List val = (List) readMethod.invoke(source);
		if (null == val) {
			return;
		}
		beanListener.startList(name);
		for (int i = 0; i < val.size(); i++) {
			beanListener.listElementStart(i);
			final Object subObj = val.get(i);
			makeFieldInner(subObj, beanListener);
			beanListener.listElementEnd();
		}
		beanListener.endList();
	}

	private boolean haveInnerType(final Class<?> clazz) {
		if (clazz.getName().contentEquals("java.util.List")) {
			return true;
		}
		if (clazz.getName().contentEquals("java.util.Map")) {
			return true;
		}
		if (simpleTypes.contains(clazz.getName())) {
			return false;
		}
		LOG.debug("not in List/Map => {}", clazz.getName());
		return false;
	}

	private boolean isComplex(final Class<?> propertyType) {
		final String name = propertyType.getName();
		if (simpleTypes.contains(name)) {
			return false;
		} else if ("java.lang.Object".equals(name)) {
			LOG.warn("Could not handle {}, considering as a simple type.", name);
			return false;
		} else if (propertyType.isEnum()) {
			return false;
		}
		LOG.debug("Complex: {}", propertyType.getName());
		return true;
	}

	private static Class<?> extractInnerListType(final PropertyDescriptor propertyDescriptor) {
		final Method method = propertyDescriptor.getReadMethod();
		final ParameterizedType returnType = (ParameterizedType) method.getGenericReturnType();
		final java.lang.reflect.Type[] type = returnType.getActualTypeArguments();
		return (Class<?>) type[0];
	}

}
