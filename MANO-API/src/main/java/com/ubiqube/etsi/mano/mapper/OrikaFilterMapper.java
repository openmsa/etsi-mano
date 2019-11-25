package com.ubiqube.etsi.mano.mapper;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.etsi.mano.dao.mano.FilterAttributes;
import com.ubiqube.etsi.mano.exception.GenericException;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.ObjectFactory;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.impl.DefaultConstructorObjectFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import ma.glasnost.orika.metadata.Type;

public class OrikaFilterMapper extends BidirectionalConverter<Object, List<FilterAttributes>> {

	private static final Logger LOG = LoggerFactory.getLogger(OrikaFilterMapper.class);
	private final Set<String> simpleTypes = new HashSet<>();

	public OrikaFilterMapper() {
		simpleTypes.add("java.lang.String");
		simpleTypes.add("java.lang.Class");
		simpleTypes.add("java.lang.Integer");
		simpleTypes.add("int");
		simpleTypes.add("long");
		simpleTypes.add("float");
		simpleTypes.add("java.util.Date");
	}

	@Override
	public List<FilterAttributes> convertTo(final Object source, final Type<List<FilterAttributes>> destinationType, final MappingContext mappingContext) {
		final List<FilterAttributes> ret = makeField(source);
		LOG.info("A to B");
		return ret;
	}

	private List<FilterAttributes> makeField(final Object source) {
		final LinkedList<String> stack = new LinkedList<>();
		try {
			return makeFieldInner(source, stack);
		} catch (final IntrospectionException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new GenericException(e);
		}
	}

	private List<FilterAttributes> makeFieldInner(final Object source, final Deque<String> stack) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		final List<FilterAttributes> ret = new ArrayList<>();
		final BeanInfo cls = Introspector.getBeanInfo(source.getClass());
		final PropertyDescriptor[] clspd = cls.getPropertyDescriptors();
		if (!isComplex(source.getClass())) {
			ret.add(makeField(stack, source));
			return ret;
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
					final List val = (List) readMethod.invoke(source);
					if (null == val) {
						continue;
					}
					for (int i = 0; i < val.size(); i++) {
						stack.push(propertyDescriptor.getName());
						stack.push(String.valueOf(i));
						final Object subObj = val.get(i);
						final List<FilterAttributes> res = makeFieldInner(subObj, stack);
						ret.addAll(res);
						stack.pop();
						stack.pop();
					}
				} else if (isComplex(clazzRet)) {
					stack.push(propertyDescriptor.getName());
					final Object val = readMethod.invoke(source);
					if (null != val) {
						final List<FilterAttributes> res = makeFieldInner(val, stack);
						ret.addAll(res);
					}
					stack.pop();
				}
			} else if (isComplex(retCls)) {
				stack.push(propertyDescriptor.getName());
				final Object val = readMethod.invoke(source);
				if (null != val) {
					final List<FilterAttributes> res = makeFieldInner(val, stack);
					ret.addAll(res);
				}
				stack.pop();
			} else {
				final Object val = readMethod.invoke(source);
				if (null != val) {
					stack.push(propertyDescriptor.getName());
					ret.add(makeField(stack, val));
					stack.pop();
				}
			}
		}
		return ret;
	}

	private static FilterAttributes makeField(final Deque<String> _stack, final Object value) {
		final FilterAttributes fa = new FilterAttributes();
		fa.setAttribute(StringUtils.join(_stack.descendingIterator(), "."));
		fa.setValue(value.toString());
		return fa;
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

	@Override
	public Object convertFrom(final List<FilterAttributes> source, final Type<Object> destinationType, final MappingContext mappingContext) {
		final MapperFactory mapperFactory = new DefaultMapperFactory.Builder()
				.build();
		final ObjectFactory objectFactory = new DefaultConstructorObjectFactory(destinationType.getRawType());
		final Object ret = objectFactory.create(source, mappingContext);
		final ClassMapBuilder<? extends List, Object> mapper = mapperFactory.classMap(source.getClass(), destinationType.getRawType())
				.byDefault();
		for (final FilterAttributes filterAttributes : source) {
			final String attr = filterAttributes.getAttribute();
			final String[] elem = attr.split("\\.");
			// setValue(ret, elem, filterAttributes.getValue());
		}
		LOG.info("B to A => ");
		return ret;
	}

	private static void setValue(final Object source, final String[] elem, final String value) throws IntrospectionException {
		for (final String string : elem) {
			final BeanInfo bi = Introspector.getBeanInfo(source.getClass());
			final PropertyDescriptor pd = getElem(bi, string);
			final Method rm = pd.getReadMethod();
			// rm.invoke(source);
		}
	}

	private static PropertyDescriptor getElem(final BeanInfo bi, final String elem) {
		final PropertyDescriptor[] pd = bi.getPropertyDescriptors();
		for (final PropertyDescriptor propertyDescriptor : pd) {
			if (propertyDescriptor.getName().equals(elem)) {
				return propertyDescriptor;
			}
		}
		return null;
	}

}
