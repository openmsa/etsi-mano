package com.ubiqube.parser.tosca;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ToscaContext {

	private static final Logger LOG = LoggerFactory.getLogger(ToscaContext.class);

	private Imports imports;
	private String description;
	private String version;
	private TopologyTemplate topologies;
	private Map<String, ToscaClass> nodeType = new HashMap<>();
	private Map<String, RelationshipType> relationship = new HashMap<>();
	private Map<String, ToscaClass> artifacts = new HashMap<>();
	private Map<String, CapabilityTypes> capabilities = new HashMap<>();
	private Map<String, GroupType> groupType = new HashMap<>();
	// Below goes internal Properties.
	private final Map<String, ToscaClassHolder> classHierarchy = new HashMap<>();
	private final IResolver resolver;

	private Map<String, DataType> dataTypes = new HashMap<>();

	private Map<String, GroupDefinition> groupDefinition = new HashMap<>();

	private Map<String, PolicyType> policiesType = new HashMap<>();

	private Map<String, PolicyDefinition> policies = new HashMap<>();

	public ToscaContext(final ToscaRoot root, final IResolver _resolver) {

		artifacts = root.getArtifactTypes();
		capabilities = root.getCapabilityTypes();
		description = root.getDescription();
		imports = root.getImports();
		nodeType = root.getNodeTypes();
		relationship = root.getRelationshipTypes();
		topologies = root.getTopologyTemplate();
		version = root.getVersion();
		dataTypes = root.getData_types();
		groupType = root.getGroup_types();
		groupDefinition = root.getGroups();
		policiesType = root.getPolicy_types();
		policies = root.getPolicies();
		resolver = _resolver;
	}

	public void setImports(final Imports _imports) {
		imports = _imports;
	}

	public void setDescription(final String _description) {
		description = _description;
	}

	public void setVersion(final String _version) {
		version = _version;
	}

	public void setTopologies(final TopologyTemplate topo) {
		topologies = topo;
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
			final String content = resolver.getContent(value.getUrl());
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
		// mergeHash(relationship, context.getRelationship());
		// topologies.merge(context.getTopologies());
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
			if ((null != derived) && !nodeType.containsKey(derived)) {
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
			if (null == tch) {
				throw new ParseException("Unable to find implementation of: " + type + " in: " + entry.getKey());
			}
		}
	}

	private ToscaClassHolder resolvDerived(final String derived) {
		final ToscaClassHolder parent = classHierarchy.get(derived);
		if (null != parent) {
			return parent;
		}
		final ToscaClass node = nodeType.get(derived);
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
		if (null != root2.getArtifactTypes()) {
			artifacts.putAll(root2.getArtifactTypes());
		}
		if (null != root2.getCapabilityTypes()) {
			capabilities.putAll(root2.getCapabilityTypes());
		}
		if ((null != root2.getImports())) {
			if (null == imports) {
				imports = root2.getImports();
			} else {
				imports.putAll(root2.getImports());
			}
		}
		if (null != root2.getNodeTypes()) {
			nodeType.putAll(root2.getNodeTypes());
		}
		if (null != root2.getData_types()) {
			dataTypes.putAll(root2.getData_types());
		}
		if (null != root2.getRelationshipTypes()) {
			relationship.putAll(root2.getRelationshipTypes());
		}
		if ((null != root2.getTopologyTemplate())) {
			if (null == topologies) {
				topologies = root2.getTopologyTemplate();
			} else {
				topologies.putAll(root2.getTopologyTemplate());
			}
		}

		if (null != root2.getGroups()) {
			if (null == groupDefinition) {
				groupDefinition = root2.getGroups();
			} else {
				groupDefinition.putAll(root2.getGroups());
			}
		}
		if (null != root2.getGroup_types()) {
			if (null == groupType) {
				groupType = root2.getGroup_types();
			} else {
				groupType.putAll(root2.getGroup_types());
			}
		}

		if (null != root2.getPolicies()) {
			if (null == policies) {
				policies = root2.getPolicies();
			} else {
				policies.putAll(root2.getPolicies());
			}
		}
		if (null != root2.getPolicy_types()) {
			if (null == policiesType) {
				policiesType = root2.getPolicy_types();
			} else {
				policiesType.putAll(root2.getPolicy_types());
			}
		}
	}

	public boolean isAssignableFor(final String source, final String clazz) {
		final ToscaClassHolder ch = classHierarchy.get(source);
		final boolean res = ch.isInstanceOf(clazz);
		LOG.debug("isAssignalbe for: {}=>{} {}", source, clazz, res);
		return res;
	}
}
