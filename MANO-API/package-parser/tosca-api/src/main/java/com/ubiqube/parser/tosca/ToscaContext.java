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
package com.ubiqube.parser.tosca;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ToscaContext {

	private static final Logger LOG = LoggerFactory.getLogger(ToscaContext.class);

	private Imports imports;
	private String description;
	private String version;
	private TopologyTemplate topologies;
	private Map<String, ToscaClass> nodeType = new LinkedHashMap<>();
	private Map<String, RelationshipType> relationship = new LinkedHashMap<>();
	private Map<String, ToscaClass> artifacts = new LinkedHashMap<>();
	private Map<String, CapabilityTypes> capabilities = new LinkedHashMap<>();
	private Map<String, GroupType> groupType = new LinkedHashMap<>();
	// Below goes internal Properties.
	private final Map<String, ToscaClassHolder> classHierarchy = new LinkedHashMap<>();
	private final IResolver resolver;

	private Map<String, DataType> dataTypes = new LinkedHashMap<>();

	private Map<String, GroupDefinition> groupDefinition = new LinkedHashMap<>();

	private Map<String, PolicyType> policiesType = new LinkedHashMap<>();

	private Map<String, PolicyDefinition> policies = new LinkedHashMap<>();

	private Map<String, InterfaceType> interfaceTypes;

	public ToscaContext(final ToscaRoot root, final IResolver inResolver) {
		artifacts = root.getArtifactTypes();
		capabilities = root.getCapabilityTypes();
		description = root.getDescription();
		imports = root.getImports();
		nodeType = root.getNodeTypes();
		relationship = root.getRelationshipTypes();
		topologies = root.getTopologyTemplate();
		version = root.getVersion();
		interfaceTypes = root.getInterfaceTypes();
		if (null != root.getDataTypes()) {
			dataTypes = root.getDataTypes();
		}
		if (null != root.getGroupTypes()) {
			groupType = root.getGroupTypes();
		}
		if (null != root.getGroups()) {
			groupDefinition = root.getGroups();
		}
		if (null != root.getPolicyTypes()) {
			policiesType = root.getPolicyTypes();
		}
		if (null != root.getPolicies()) {
			policies = root.getPolicies();
		}
		resolver = inResolver;
	}

	public void setImports(final Imports imports) {
		this.imports = imports;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public void setVersion(final String version) {
		this.version = version;
	}

	public void setTopologies(final TopologyTemplate topo) {
		this.topologies = topo;
	}

	public void setNodeType(final Map<String, ToscaClass> nodesType) {
		nodeType = nodesType;
	}

	public void setRelationship(final Map<String, RelationshipType> rels) {
		relationship = rels;
	}

	public void setArtifacts(final Map<String, ToscaClass> arts) {
		artifacts = arts;
	}

	public void setCapabilities(final Map<String, CapabilityTypes> caps) {
		capabilities = caps;
	}

	public Imports getImports() {
		return imports;
	}

	public String getDescription() {
		return description;
	}

	public String getVersion() {
		return version;
	}

	public TopologyTemplate getTopologies() {
		return topologies;
	}

	public Map<String, ToscaClass> getNodeType() {
		return nodeType;
	}

	public Map<String, RelationshipType> getRelationship() {
		return relationship;
	}

	public Map<String, ToscaClass> getArtifacts() {
		return artifacts;
	}

	public Map<String, CapabilityTypes> getCapabilities() {
		return capabilities;
	}

	public Map<String, DataType> getDataTypes() {
		return dataTypes;
	}

	public Map<String, GroupType> getGroupType() {
		return groupType;
	}

	public Map<String, GroupDefinition> getGroupDefinition() {
		return groupDefinition;
	}

	public Map<String, PolicyType> getPoliciesType() {
		return policiesType;
	}

	public Map<String, PolicyDefinition> getPolicies() {
		return policies;
	}

	public Map<String, InterfaceType> getInterfaceTypes() {
		return interfaceTypes;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("ToscaContext [imports=").append(description);
		sb.append(", version=").append(version).append(", topologies=").append(topologies);
		sb.append(", nodeType=\n");
		Set<Entry<String, ToscaClass>> entry = nodeType.entrySet();
		for (final Entry<String, ToscaClass> toscaClass : entry) {
			sb.append(" - ").append(toscaClass).append("\n");
		}
		sb.append(", relationship=\n");
		final Set<Entry<String, RelationshipType>> entry3 = relationship.entrySet();
		for (final Entry<String, RelationshipType> toscaClass : entry3) {
			sb.append(" - ").append(toscaClass).append("\n");
		}

		sb.append(", artifacts=\n");
		entry = artifacts.entrySet();
		for (final Entry<String, ToscaClass> toscaClass : entry) {
			sb.append(" - ").append(toscaClass).append("\n");
		}

		sb.append(", capabilities=\n");
		final Set<Entry<String, CapabilityTypes>> entry2 = capabilities.entrySet();
		for (final Entry<String, CapabilityTypes> toscaClass : entry2) {
			sb.append(" - ").append(toscaClass).append("\n");
		}
		sb.append(", classHierarchy=\n");
		final Set<Entry<String, ToscaClassHolder>> entry4 = classHierarchy.entrySet();
		for (final Entry<String, ToscaClassHolder> toscaClass : entry4) {
			sb.append(" - ").append(toscaClass).append("\n");
		}
		sb.append("]");
		return sb.toString();
	}

	public void resolvImports() {
		if (null == imports) {
			return;
		}
		final Set<Entry<String, Import>> entry = imports.entrySet();
		for (final Entry<String, Import> entry2 : entry) {
			LOG.info("Resolving: {} -> {}", entry2.getKey(), entry2.getValue());
			final Import value = entry2.getValue();
			final String resolv = resolver.resolvePath(value.getUrl());
			value.setResolved(resolv);
			final String content = resolver.getContent(value.getUrl());
			if (null == content) {
				continue;
			}
			final ToscaParser main = new ToscaParser(content, resolver);
			final ToscaContext context = main.getContext();

			context.resolvImports();
			LOG.debug("Before new CTX={}, current={}", context.nodeType.size(), nodeType.size());
			mergeContext(context);
			LOG.debug("After new CTX={}, current={}", context.nodeType.size(), nodeType.size());
		}
	}

	private void mergeContext(final ToscaContext context) {
		mergeHash(artifacts, context.getArtifacts());
		if (null != context.getCapabilities()) {
			capabilities.putAll(context.getCapabilities());
		}
		if (null != context.getDataTypes()) {
			dataTypes.putAll(context.getDataTypes());
		}
		mergeHash(nodeType, context.getNodeType());
		// group
		if (null != context.getGroupDefinition()) {
			groupDefinition.putAll(context.getGroupDefinition());
		}
		if (null != context.getGroupType()) {
			groupType.putAll(context.getGroupType());
		}
		// policy
		if (null != context.getPolicies()) {
			policies.putAll(context.getPolicies());
		}
		if (null != context.getPoliciesType()) {
			policiesType.putAll(context.getPoliciesType());
		}
		if (null != context.getInterfaceTypes()) {
			if (null == interfaceTypes) {
				interfaceTypes = context.getInterfaceTypes();
			} else {
				interfaceTypes.putAll(context.getInterfaceTypes());
			}
		}

	}

	private static void mergeHash(final Map<String, ToscaClass> dst, final Map<String, ToscaClass> src) {
		for (final Entry<String, ToscaClass> entry : src.entrySet()) {
			if (dst.containsKey(entry.getKey())) {
				LOG.debug("Ignoring {}.", entry.getKey());
			} else {
				dst.put(entry.getKey(), entry.getValue());
			}
		}
	}

	public void resolvDerivedFrom() {
		final Set<Entry<String, ToscaClass>> entries = nodeType.entrySet();
		resolvDerivedFromLoop(entries);
	}

	private void resolvDerivedFromLoop(final Set<Entry<String, ToscaClass>> entries) {
		for (final Entry<String, ToscaClass> entry : entries) {
			final ToscaClass val = entry.getValue();
			final String derived = val.getDerivedFrom();
			if (null != derived) {
				final ToscaClass clazz = nodeType.get(derived);
				if (null == clazz) {
					throw new ParseException("Unable to find " + derived);
				}
			}

		}
	}

	public void resolvSymbols() {
		LOG.debug("Resolv symbol of CTX={}", nodeType.size());
		final Set<Entry<String, ToscaClass>> entries = nodeType.entrySet();
		for (final Entry<String, ToscaClass> entry : entries) {
			final ToscaClass clazz = entry.getValue();
			final String derived = clazz.getDerivedFrom();
			if (null != derived && !nodeType.containsKey(derived)) {
				// Throw exception unresolvable external/.
				LOG.warn("{} not a Node Type.", derived);
			} else if (derived != null) {
				final ToscaClassHolder parent = resolvDerived(derived);
				final ToscaClassHolder tch = new ToscaClassHolder(entry.getKey(), clazz);
				tch.setParent(parent);

				registerTch(entry.getKey(), tch);
			} else {
				final ToscaClassHolder tch = new ToscaClassHolder(entry.getKey(), clazz);
				registerTch(entry.getKey(), tch);
			}
		}
		resolvTopology();
	}

	private void registerTch(final String key, final ToscaClassHolder tch) {
		classHierarchy.put(key, tch);
		if (key.startsWith("tosca.nodes.")) {
			classHierarchy.put(key.substring("tosca.nodes.".length()), tch);
		}
	}

	private void resolvTopology() {
		if (null == topologies) {
			LOG.debug("No Topology Nodes.");
			return;
		}
		final Set<Entry<String, NodeTemplate>> nodes = topologies.getNodeTemplate().entrySet();
		for (final Entry<String, NodeTemplate> entry : nodes) {
			LOG.debug("Analyzing Entry {}", entry.getKey());
			final NodeTemplate nodeTmpl = entry.getValue();
			final String type = nodeTmpl.getType();
			final ToscaClassHolder tch = classHierarchy.get(type);
			if (null == tch && !onClassPath(type)) {
				throw new ParseException("Unable to find implementation of: " + type + " in: " + entry.getKey());
			}
		}
	}

	private static boolean onClassPath(final String type) {
		try {
			Class.forName(type);
			return true;
		} catch (final ClassNotFoundException e) {
			LOG.trace("", e);
		}
		return false;
	}

	private ToscaClassHolder resolvDerived(final String derived) {
		final ToscaClassHolder parent = classHierarchy.get(derived);
		if (null != parent) {
			return parent;
		}
		final ToscaClass node = nodeType.get(derived);
		if (null == node) {
			throw new ParseException("Node " + derived + " cannot be found.");
		}
		LOG.debug("Building Tree Node {} ", derived);
		final ToscaClassHolder tch = new ToscaClassHolder(derived, node);
		classHierarchy.put(derived, tch);
		if (node.getDerivedFrom() != null) {
			final ToscaClassHolder newParent = resolvDerived(node.getDerivedFrom());
			tch.setParent(newParent);
		}
		return tch;
	}

	public void addRoot(final ToscaRoot root2) {
		Optional.ofNullable(root2.getArtifactTypes()).ifPresent(x -> artifacts.putAll(x));
		Optional.ofNullable(root2.getCapabilityTypes()).ifPresent(x -> capabilities.putAll(x));
		Optional.ofNullable(root2.getImports()).ifPresent(x -> {
			if (null == imports) {
				imports = x;
			} else {
				imports.putAll(x);
			}
		});
		Optional.ofNullable(root2.getNodeTypes()).ifPresent(x -> nodeType.putAll(x));
		Optional.ofNullable(root2.getDataTypes()).ifPresent(x -> dataTypes.putAll(x));
		Optional.ofNullable(root2.getRelationshipTypes()).ifPresent(x -> relationship.putAll(x));
		Optional.ofNullable(root2.getTopologyTemplate()).ifPresent(x -> {
			if (null == topologies) {
				topologies = root2.getTopologyTemplate();
			} else {
				topologies.putAll(root2.getTopologyTemplate());
			}
		});
		groupDefinition = assign(root2.getGroups(), groupDefinition);
		groupType = assign(root2.getGroupTypes(), groupType);
		policies = assign(root2.getPolicies(), policies);
		policiesType = assign(root2.getPolicyTypes(), policiesType);
		interfaceTypes = assign(root2.getInterfaceTypes(), interfaceTypes);
	}

	private static <K, V> Map<K, V> assign(final Map<K, V> in, final Map<K, V> here) {
		if (null == here) {
			return in;
		}
		if (Optional.ofNullable(in).isPresent()) {
			here.putAll(in);
			return here;
		}
		return new HashMap<>();
	}

	public boolean isAssignableFor(final String source, final String clazz) {
		final ToscaClassHolder ch = classHierarchy.get(source);
		if (null != ch) {
			final boolean res = ch.isInstanceOf(clazz);
			LOG.debug("isAssignalbe for: {}=>{} {}", source, clazz, res);
			return res;
		}
		final PolicyType policy = policiesType.get(source);
		if (null != policy && source.equalsIgnoreCase(clazz)) {
			return true;
		}

		final GroupType group = groupType.get(source);
		if (null != group && source.equalsIgnoreCase(clazz)) {
			return true;
		}
		LOG.debug("Not found: {}", source);
		return false;
	}

}
