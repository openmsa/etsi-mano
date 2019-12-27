package com.ubiqube.parser.tosca.api;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.ubiqube.parser.tosca.NodeTemplate;
import com.ubiqube.parser.tosca.ParseException;
import com.ubiqube.parser.tosca.ToscaContext;
import com.ubiqube.parser.tosca.ValueObject;

public class ToscaApi {

	public <T> List<T> getObjects(final ToscaContext root, final Class<T> destination) {
		final List<NodeTemplate> nodes = getNodeMatching(root, destination);
		return mapToscaToClass(nodes, destination);
	}

	public <T> List<NodeTemplate> getNodeMatching(final ToscaContext root, final Class<T> destination) {
		final String clazzname = destination.getName();
		return root.getTopologies()
				.getNodeTemplate()
				.entrySet()
				.stream()
				.filter(x -> root.isAssignableFor(x.getValue().getType(), clazzname))
				.map(Entry::getValue)
				.collect(Collectors.toList());
	}

	private <T> List<T> mapToscaToClass(final List<NodeTemplate> nodes, final Class<T> destination) {
		T obj;
		try {
			obj = destination.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new ParseException(e);
		}
		nodes.stream().forEach(x -> {
			applyAttributes(obj, x.getAttributes());
		});
		System.out.println("" + nodes);
		return new ArrayList<>();
	}

	private <T> void applyAttributes(final T obj, final Map<String, ValueObject> attributes) {
		BeanInfo beanInfo;

		try {
			beanInfo = Introspector.getBeanInfo(obj.getClass());
		} catch (final IntrospectionException e) {
			throw new ParseException(e);
		}
		final PropertyDescriptor[] props = beanInfo.getPropertyDescriptors();
		attributes.forEach((x, y) -> {

			y.getType();
		});
	}
}
