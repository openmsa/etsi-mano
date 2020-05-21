package com.ubiqube.parser.tosca.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ubiqube.parser.tosca.GroupDefinition;
import com.ubiqube.parser.tosca.NodeTemplate;
import com.ubiqube.parser.tosca.PolicyDefinition;
import com.ubiqube.parser.tosca.ToscaContext;
import com.ubiqube.parser.tosca.scripting.ScriptingValue;

/**
 * Main front API around tosca files.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class ToscaApi {

	/**
	 * Constructor.
	 */
	public ToscaApi() {
		// Nothing.
	}

	/**
	 * Return a list of populated 'destination' objects.
	 *
	 * @param <T>         The type of returned objects.
	 * @param root        Tosca context.
	 * @param destination Destination class.
	 * @return A List of populated object.
	 */
	public <T> List<T> getObjects(final ToscaContext root, final Map<String, String> parameters, final Class<T> destination) {
		final List<NodeTemplate> nodes = getNodeMatching(root, parameters, destination);
		final ContextResolver contextResolver = new ContextResolver(root, parameters);
		if (!nodes.isEmpty()) {
			return contextResolver.mapToscaToClass(nodes, destination);
		}
		final List<GroupDefinition> groups = getGroupsMatching(root, destination);
		if (!groups.isEmpty()) {
			return contextResolver.mapGroupsToClass(groups, destination);
		}
		final List<PolicyDefinition> policies = getPoliciesMatching(root, destination);
		if (!policies.isEmpty()) {
			return contextResolver.mapPoliciesToClass(policies, destination);
		}
		return new ArrayList<>();
	}

	private static <T> List<PolicyDefinition> getPoliciesMatching(final ToscaContext root, final Class<T> destination) {
		final Map<String, PolicyDefinition> policies = root.getPolicies();
		if (null == policies) {
			return new ArrayList<>();
		}
		final String clazzname = destination.getName();
		return policies.entrySet()
				.stream()
				.filter(x -> root.isAssignableFor(x.getValue().getType(), clazzname))
				.map(x -> {
					final PolicyDefinition val = x.getValue();
					val.setName(x.getKey());
					return val;
				})
				.collect(Collectors.toList());
	}

	private static <T> List<GroupDefinition> getGroupsMatching(final ToscaContext root, final Class<T> destination) {
		final Map<String, GroupDefinition> groups = root.getGroupDefinition();
		if (null == groups) {
			return new ArrayList<>();
		}
		final String clazzname = destination.getName();
		return groups.entrySet()
				.stream()
				.filter(x -> root.isAssignableFor(x.getValue().getType(), clazzname))
				.map(x -> {
					final GroupDefinition val = x.getValue();
					val.setName(x.getKey());
					return val;
				})
				.collect(Collectors.toList());
	}

	/**
	 * Return node matching wanted class.
	 *
	 * @param <T>         The wanted class.
	 * @param root        Tosca context.
	 * @param destination Target class.
	 * @return A list of {@link NodeTemplate}.
	 */
	public <T> List<NodeTemplate> getNodeMatching(final ToscaContext root, final Map<String, String> parameters, final Class<T> destination) {
		final ContextResolver contextResolver = new ContextResolver(root, parameters);
		final String clazzname = destination.getName();
		return root.getTopologies()
				.getNodeTemplate()
				.entrySet()
				.stream()
				.filter(x -> root.isAssignableFor(x.getValue().getType(), clazzname))
				.map(x -> {
					final NodeTemplate val = x.getValue();
					val.setName(contextResolver.resolvValue(x.getKey(), ScriptingValue.class));
					return val;
				})
				.collect(Collectors.toList());
	}
}
