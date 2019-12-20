package com.ubiqube.parser.tosca.api;

import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.ubiqube.parser.tosca.NodeTemplate;
import com.ubiqube.parser.tosca.ToscaContext;

public class ToscaApi {

	public <T> List<T> getObjects(final ToscaContext root, final Class<T> destination) {
		final String clazzname = destination.getName();
		final List<NodeTemplate> nodes = root.getTopologies()
				.getNodeTemplate()
				.entrySet()
				.stream()
				.filter(x -> root.isAssignableFor(x.getKey(), clazzname))
				.map(Entry::getValue)
				.collect(Collectors.toList());

		return mapToscaToClass(nodes, destination);
	}

	private <T> List<T> mapToscaToClass(final List<NodeTemplate> nodes, final Class<T> destination) {
		nodes.stream();
		return null;
	}
}
