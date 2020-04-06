package com.ubiqube.parser.tosca.api;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.parser.tosca.NodeTemplate;
import com.ubiqube.parser.tosca.ParseException;
import com.ubiqube.parser.tosca.Requirement;
import com.ubiqube.parser.tosca.RequirementDefinition;
import com.ubiqube.parser.tosca.ToscaContext;
import com.ubiqube.parser.tosca.convert.ConvertApi;
import com.ubiqube.parser.tosca.convert.FloatConverter;
import com.ubiqube.parser.tosca.convert.SizeConverter;
import com.ubiqube.parser.tosca.convert.TimeConverter;
import com.ubiqube.parser.tosca.scalar.Size;
import com.ubiqube.parser.tosca.scalar.Time;

/**
 * Main front API around tosca files.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ToscaApi {

	private static final Logger LOG = LoggerFactory.getLogger(ToscaApi.class);
	private final ConvertApi conv = new ConvertApi();

	/**
	 * Constructor.
	 */
	public ToscaApi() {
		conv.register(Size.class.getCanonicalName(), new SizeConverter());
		conv.register(Time.class.getCanonicalName(), new TimeConverter());
		conv.register(Double.class.getCanonicalName(), new FloatConverter());
		conv.register(Float.class.getCanonicalName(), new FloatConverter());
	}

	/**
	 * Return a list of populated 'destination' objects.
	 *
	 * @param <T>         The type of returned objects.
	 * @param root        Tosca context.
	 * @param destination Destination class.
	 * @return A List of populated object.
	 */
	public <T> List<T> getObjects(final ToscaContext root, final Class<T> destination) {
		final List<NodeTemplate> nodes = getNodeMatching(root, destination);
		return mapToscaToClass(nodes, destination);
	}

	/**
	 * Return node matching wanted class.
	 *
	 * @param <T>         The wanted class.
	 * @param root        Tosca context.
	 * @param destination Target class.
	 * @return A list of {@link NodeTemplate}.
	 */
	public <T> List<NodeTemplate> getNodeMatching(final ToscaContext root, final Class<T> destination) {
		final String clazzname = destination.getName();
		return root.getTopologies()
				.getNodeTemplate()
				.entrySet()
				.stream()
				.filter(x -> root.isAssignableFor(x.getValue().getType(), clazzname))
				.map(x -> {
					final NodeTemplate val = x.getValue();
					val.setName(x.getKey());
					return val;
				})
				.collect(Collectors.toList());
	}

	private <T> List<T> mapToscaToClass(final List<NodeTemplate> nodes, final Class<T> destination) {
		return (List<T>) nodes.stream().map(x -> handleObject(x, destination)).collect(Collectors.toList());
	}

	private Object handleObject(final NodeTemplate node, final Class clazz) {
		BeanInfo beanInfo;
		try {
			beanInfo = Introspector.getBeanInfo(clazz);
		} catch (final IntrospectionException e) {
			throw new ParseException(e);
		}
		final PropertyDescriptor[] propsDescr = beanInfo.getPropertyDescriptors();
		LOG.debug("class=>{} --- [{}]", clazz.getName(), Arrays.toString(propsDescr));
		final Object cls = newInstance(clazz);

		final Object caps = node.getCapabilities();
		if (null != caps) {
			handleMap((Map<String, Object>) caps, clazz, propsDescr, cls, null);
		}
		final Map<String, Object> props = node.getProperties();
		if (null != props) {
			handleMap(props, clazz, propsDescr, cls, null);
		}

		final RequirementDefinition req = node.getRequirements();
		if ((null != req) && (null != req.getRequirements())) {
			handleRequirements(req.getRequirements(), clazz, propsDescr, cls, null);
		}
		final ToscaInernalBase tib = (ToscaInernalBase) cls;
		tib.setInternalName(node.getName());
		tib.setInternalDescription(node.getDescription());
		return cls;
	}

	private void handleRequirements(final Map<String, Requirement> requirements, final Class clazz, final PropertyDescriptor[] propsDescr, final Object cls, final Object object) {
		requirements.forEach((x, y) -> {
			// XXX I think it could be ONE of Node, caps, Link
			final PropertyDescriptor props = getPropertyFor(underScoreToCamleCase(x) + "Req", propsDescr);
			if (props != null) {
				methodInvoke(props.getWriteMethod(), cls, y.getNode());
			}
		});
	}

	private PropertyDescriptor getPropertyFor(final String x, final PropertyDescriptor[] propsDescr) {
		for (final PropertyDescriptor propertyDescriptor : propsDescr) {
			if (propertyDescriptor.getName().contentEquals(x)) {
				return propertyDescriptor;
			}
		}
		return null;
	}

	private Object handleMap(final Map<String, Object> caps, final Class clazz, final PropertyDescriptor[] props, final Object cls, final Class generic) {
		if (clazz.isAssignableFrom(Map.class)) {
			LOG.debug("Handling map of {}", generic);
			final Map map = (Map) cls;
			handleRealMap(map, generic, caps, props, cls);
		}
		final Stream<PropertyDescriptor> stream = Arrays.stream(props);
		stream.forEach(x -> {
			final Object res = caps.get(camelCaseToUnderscore(x.getName()));
			if (null != res) {
				LOG.debug("Property: {}={}", x.getName(), res);
				handleCaps(res, x, props, cls);
			}
		});
		return cls;
	}

	private void handleRealMap(final Map map, final Class generic, final Map<String, Object> caps, final PropertyDescriptor[] propsDescr, final Object cls) {
		caps.forEach((x, y) -> {
			Object res;
			try {
				res = handleMap((Map<String, Object>) y, generic, propsDescr, generic.newInstance(), null);
			} catch (InstantiationException | IllegalAccessException e) {
				throw new ParseException(e);
			}
			map.put(x, res);
		});
	}

	private void handleCaps(final Object res, final PropertyDescriptor x, final PropertyDescriptor[] propsDescr, final Object cls) {
		if (res instanceof Map) {
			Map<String, Object> caps = (Map<String, Object>) res;
			if (null != caps.get("properties")) {
				caps = (Map<String, Object>) caps.get("properties");
			}
			LOG.debug("Recursing: {}", caps);
			final Method rm = x.getReadMethod();
			final Class zz = getReturnType(rm);

			BeanInfo beanInfo;
			try {
				beanInfo = Introspector.getBeanInfo(zz);
			} catch (final IntrospectionException e) {
				throw new ParseException(e);
			}
			final PropertyDescriptor[] propsDescrNew = beanInfo.getPropertyDescriptors();
			LOG.debug("class=>{} --- [{}]", zz.getName(), Arrays.toString(propsDescr));
			final Object clsNew = newInstance(zz);

			Object ret = null;
			if (rm.getReturnType().isAssignableFrom(Map.class)) {
				ret = handleMap(caps, Map.class, propsDescrNew, new HashMap(), zz);
			} else {
				ret = handleMap(caps, zz, propsDescrNew, clsNew, zz);
			}
			LOG.debug("return: {} for property: {}", ret, x.getName());
			final Method meth = x.getWriteMethod();
			methodInvoke(meth, cls, ret);
		} else if (res instanceof List) {
			final Method rm = x.getReadMethod();
			final Class zz = getReturnType(rm);
			BeanInfo beanInfo;
			try {
				beanInfo = Introspector.getBeanInfo(zz);
			} catch (final IntrospectionException e) {
				throw new ParseException(e);
			}

			LOG.debug("Entring List of {}", zz);
			final PropertyDescriptor[] propsDescrNew = beanInfo.getPropertyDescriptors();
			LOG.info("class=>{} --- [{}]", zz.getName(), Arrays.toString(propsDescrNew));
			final Object clsNew = newInstance(zz);
			final Object ret = handleList((List) res, zz, propsDescrNew, clsNew, zz);
			LOG.debug("return: {} for property: {}", ret, x.getName());
			final Method meth = x.getWriteMethod();
			methodInvoke(meth, cls, ret);

		} else {
			final Method writeMethod = x.getWriteMethod();
			methodInvoke(writeMethod, cls, convert(res, writeMethod.getParameterTypes()[0]));
		}
	}

	private Object handleList(final List res, final Class zz, final PropertyDescriptor[] propsDescr, final Object clsNew, final Class generic) {
		final ArrayList ret = new ArrayList<>();
		res.forEach(x -> {
			Object elem;
			if (x instanceof Map) {
				try {
					elem = handleMap((Map<String, Object>) x, generic, propsDescr, generic.newInstance(), null);
				} catch (InstantiationException | IllegalAccessException e) {
					throw new ParseException(e);
				}
			} else {
				elem = x;
			}
			ret.add(elem);
		});
		return ret;

	}

	private Object convert(final Object res, final Class<?> parameterType) {
		if (res.getClass().equals(parameterType)) {
			return res;
		}
		if (parameterType.isAssignableFrom(res.getClass())) {
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

	private static String underScoreToCamleCase(final String key) {
		final Pattern p = Pattern.compile("_(.)");
		final Matcher m = p.matcher(key);
		final StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, m.group(1).toUpperCase());
		}
		m.appendTail(sb);
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
