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
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.parser.tosca.GroupDefinition;
import com.ubiqube.parser.tosca.InputBean;
import com.ubiqube.parser.tosca.NodeTemplate;
import com.ubiqube.parser.tosca.ParseException;
import com.ubiqube.parser.tosca.PolicyDefinition;
import com.ubiqube.parser.tosca.Requirement;
import com.ubiqube.parser.tosca.RequirementDefinition;
import com.ubiqube.parser.tosca.ToscaContext;
import com.ubiqube.parser.tosca.convert.ConvertApi;
import com.ubiqube.parser.tosca.convert.FloatConverter;
import com.ubiqube.parser.tosca.convert.FrequencyConverter;
import com.ubiqube.parser.tosca.convert.SizeConverter;
import com.ubiqube.parser.tosca.convert.TimeConverter;
import com.ubiqube.parser.tosca.convert.VersionConverter;
import com.ubiqube.parser.tosca.scalar.Frequency;
import com.ubiqube.parser.tosca.scalar.Size;
import com.ubiqube.parser.tosca.scalar.Time;
import com.ubiqube.parser.tosca.scalar.Version;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ContextResolver {

	private static final Logger LOG = LoggerFactory.getLogger(ContextResolver.class);

	private final ConvertApi conv = new ConvertApi();

	private final ToscaContext root;

	private final Map<String, String> parameters;

	public ContextResolver(final ToscaContext _root, final Map<String, String> _parameters) {
		root = _root;
		parameters = _parameters;
		conv.register(Size.class.getCanonicalName(), new SizeConverter());
		conv.register(Time.class.getCanonicalName(), new TimeConverter());
		conv.register(Double.class.getCanonicalName(), new FloatConverter());
		conv.register(Float.class.getCanonicalName(), new FloatConverter());
		conv.register(Frequency.class.getCanonicalName(), new FrequencyConverter());
		conv.register(Version.class.getCanonicalName(), new VersionConverter());
	}

	public <T> List<T> mapPoliciesToClass(final List<PolicyDefinition> policies, final Class<T> destination) {
		return (List<T>) policies.stream().map(x -> handlePolicy(x, destination)).collect(Collectors.toList());
	}

	public <T> List<T> mapGroupsToClass(final List<GroupDefinition> groups, final Class<T> destination) {
		return (List<T>) groups.stream().map(x -> handleGroup(x, destination)).collect(Collectors.toList());
	}

	public <T> List<T> mapToscaToClass(final List<NodeTemplate> nodes, final Class<T> destination) {
		return (List<T>) nodes.stream().map(x -> handleObject(x, destination)).collect(Collectors.toList());
	}

	public <T> T resolvValue(final String key, final Class<?> target) {
		if (null != parameters.get(key)) {
			return (T) parameters.get(key);
		}
		final Map<String, InputBean> inputs = root.getTopologies().getInputs();
		if (null != inputs) {
			return (T) inputs.get(key);
		}
		LOG.warn("Unknown property: {}", key);
		return null;
	}

	private Object handlePolicy(final PolicyDefinition policy, final Class clazz) {
		BeanInfo beanInfo;
		try {
			beanInfo = Introspector.getBeanInfo(clazz);
		} catch (final IntrospectionException e) {
			throw new ParseException(e);
		}
		final PropertyDescriptor[] propsDescr = beanInfo.getPropertyDescriptors();
		LOG.debug("class=>{} --- [{}]", clazz.getName(), Arrays.toString(propsDescr));
		final Object cls = newInstance(clazz);
		final Map<String, Object> props = policy.getProperties();
		if (null != props) {
			handleMap(props, clazz, propsDescr, cls, null);
		}
		// Fixed fields
		setProperty2(cls, findReadMethod(propsDescr, "targets"), policy.getTargets());
		setProperty2(cls, findReadMethod(propsDescr, "triggers"), policy.getTriggers());
		// XXX it looks the same ?
		final ToscaInernalBase tib = (ToscaInernalBase) cls;
		tib.setInternalName(policy.getName());
		tib.setInternalDescription(policy.getDescription());
		setProperty(cls, "setName", policy.getName());
		setProperty(cls, "setDescription", policy.getDescription());
		return cls;
	}

	private Object handleGroup(final GroupDefinition group, final Class clazz) {
		BeanInfo beanInfo;
		try {
			beanInfo = Introspector.getBeanInfo(clazz);
		} catch (final IntrospectionException e) {
			throw new ParseException(e);
		}
		final PropertyDescriptor[] propsDescr = beanInfo.getPropertyDescriptors();
		LOG.debug("class=>{} --- [{}]", clazz.getName(), Arrays.toString(propsDescr));
		final Object cls = newInstance(clazz);
		final Map<String, Object> props = group.getProperties();
		if (null != props) {
			handleMap(props, clazz, propsDescr, cls, null);
		}
		// Fixed fields
		setProperty2(cls, findReadMethod(propsDescr, "members"), group.getMembers());
		// XXX it looks the same ?
		final ToscaInernalBase tib = (ToscaInernalBase) cls;
		tib.setInternalName(group.getName());
		tib.setInternalDescription(group.getDescription());
		setProperty(cls, "setName", group.getName());
		setProperty(cls, "setDescription", group.getDescription());
		return cls;
	}

	private static Method findReadMethod(final PropertyDescriptor[] propsDescr, final String string) {
		return Arrays.stream(propsDescr)
				.filter(x -> x.getName().equals(string))
				.map(PropertyDescriptor::getWriteMethod)
				.findFirst().orElseThrow(() -> new ParseException("Method: " + string + " doesn't have a write method."));
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
		// XXX it looks the same ?
		final ToscaInernalBase tib = (ToscaInernalBase) cls;
		tib.setInternalName(node.getName());
		tib.setInternalDescription(node.getDescription());
		setProperty(cls, "setName", node.getName());
		setProperty(cls, "setDescription", node.getDescription());
		return cls;
	}

	private static void setProperty2(final Object cls, final Method write, final Object value) {
		try {
			write.invoke(cls, value);
		} catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			LOG.trace("", e);
		}
	}

	private static void setProperty(final Object cls, final String methodName, final Object value) {
		if (null == value) {
			return;
		}
		try {
			final Method meth = cls.getClass().getMethod(methodName, value.getClass());
			meth.invoke(cls, value);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NullPointerException e) {
			LOG.trace("", e);
		}
	}

	private static void handleRequirements(final Map<String, Requirement> requirements, final Class<?> clazz, final PropertyDescriptor[] propsDescr, final Object cls, final Object object) {
		requirements.forEach((x, y) -> {
			// XXX I think it could be ONE of Node, caps, Link
			final PropertyDescriptor props = getPropertyFor(underScoreToCamleCase(x) + "Req", propsDescr);
			if (props != null) {
				methodInvoke(props.getWriteMethod(), cls, y.getNode());
			}
		});
	}

	private static PropertyDescriptor getPropertyFor(final String x, final PropertyDescriptor[] propsDescr) {
		for (final PropertyDescriptor propertyDescriptor : propsDescr) {
			if (propertyDescriptor.getName().contentEquals(x)) {
				return propertyDescriptor;
			}
		}
		return null;
	}

	private Object handleMap(final Map<String, Object> caps, final Class<?> clazz, final PropertyDescriptor[] props, final Object cls, final Class generic) {
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

	private void handleRealMap(final Map<Object, Object> map, final Class<?> generic, final Map<String, Object> caps, final PropertyDescriptor[] propsDescr, final Object cls) {
		caps.forEach((x, y) -> {
			Object res;
			try {
				if (y instanceof Map) {
					res = handleMap((Map<String, Object>) y, generic, propsDescr, generic.newInstance(), null);
					map.put(x, res);
				} else {
					map.put(x, y);
				}
			} catch (ClassCastException | InstantiationException | IllegalAccessException e) {
				throw new ParseException(e);
			}

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

			Object ret = null;
			if (rm.getReturnType().isAssignableFrom(Map.class)) {
				ret = handleMap(caps, Map.class, propsDescrNew, new HashMap(), zz);
			} else if (rm.getReturnType().isAssignableFrom(String.class)
					|| rm.getReturnType().isAssignableFrom(Integer.class)
					|| rm.getReturnType().isAssignableFrom(Size.class)) {
				// resolv Scripting Value.
				ret = resolvValue(rm.getReturnType(), res);
				ret = convertValue(ret, rm.getReturnType());
				// XXX If you want to resolv values after onboarding you have to do this.
				// ret = new ScriptingValue();
			} else {
				final Object clsNew = newInstance(zz);
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

	private static Object convertValue(final Object ret, final Class<?> returnType) {
		if (returnType.isAssignableFrom(String.class)) {
			return ret;
		} else if (returnType.isAssignableFrom(Integer.class)) {
			return Integer.parseInt((String) ret);
		} else if (returnType.isAssignableFrom(Size.class)) {
			return new Size((String) ret);
		}
		throw new ParseException("Unable to cast: " + returnType.getName());
	}

	private Object resolvValue(final Class<?> returnType, final Object res) {
		Object ret = null;
		if (res instanceof Map) {
			final Map<?, ?> map = (Map) res;
			for (final Map.Entry<?, ?> entry : map.entrySet()) {
				if ("get_input".equals(entry.getKey())) {
					final Map<String, InputBean> inputs = root.getTopologies().getInputs();
					if (null != inputs) {
						final InputBean input = Optional.ofNullable(inputs.get(entry.getValue())).orElseThrow(() -> new ParseException("Unknown input: " + entry.getValue()));
						final String value = parameters.get(entry.getValue());
						if (value != null) {
							ret = value;
						} else {
							ret = input.getDef();
						}
					}
				} else {
					throw new ParseException("Unknown method: " + entry.getKey());
				}
			}
		}
		return ret;
	}

	private Object handleList(final List res, final Class<?> zz, final PropertyDescriptor[] propsDescr, final Object clsNew, final Class generic) {
		final ArrayList<Object> ret = new ArrayList<>();
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

	private static Class<?> getReturnType(final Method readMethod) {
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

	/**
	 * XXX This is very limited. If tosca use camel case, it's will fail. Maybe consider reading JSONProperty value.
	 *
	 * @param key
	 * @return
	 */
	private static String camelCaseToUnderscore(final String key) {
		final Matcher m = Pattern.compile("(?<=[a-z0-9])[A-Z]").matcher(key);

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
				return new HashMap<>();
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
			throw new ParseException("Could not invoke: " + method + " with parameters of type :" + paramter.getClass(), e);
		}
	}

}
