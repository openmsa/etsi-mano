package com.ubiqube.parser.tosca;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tosca.nodes.BlockStorage;
import tosca.nodes.Compute;
import tosca.nodes.DBMS;
import tosca.nodes.Database;
import tosca.nodes.LoadBalancer;
import tosca.nodes.ObjectStorage;
import tosca.nodes.Root;
import tosca.nodes.SoftwareComponent;
import tosca.nodes.WebApplication;
import tosca.nodes.WebServer;

public class ToscaContext {

	private static final Logger LOG = LoggerFactory.getLogger(ToscaContext.class);

	private Imports imports;
	private String description;
	private String version;
	private TopologyTemplate topologies;
	private Map<String, ToscaClass> nodeType = new HashMap<>();
	private Map<String, ToscaClass> relationship = new HashMap<>();
	private Map<String, ToscaClass> artifacts = new HashMap<>();
	private Map<String, ToscaClass> capabilities = new HashMap<>();
	private final Resolver resolver = new Resolver();

	private void init() {
		nodeType.put("tosca.nodes.Root", new Root());
		nodeType.put("tosca.nodes.Compute", new Compute());
		nodeType.put("tosca.nodes.SoftwareComponent", new SoftwareComponent());
		nodeType.put("tosca.nodes.WebServer", new WebServer());
		nodeType.put("tosca.nodes.WebApplication", new WebApplication());
		nodeType.put("tosca.nodes.DBMS", new DBMS());
		nodeType.put("tosca.nodes.Database", new Database());

		nodeType.put("tosca.nodes.ObjectStorage", new ObjectStorage());
		nodeType.put("tosca.nodes.BlockStorage", new BlockStorage());
		nodeType.put("tosca.nodes.Container.Runtime", new DBMS());
		nodeType.put("tosca.nodes.Container.Application.Docker", new DBMS());
		nodeType.put("tosca.nodes.LoadBalancer", new LoadBalancer());
	}

	public ToscaContext(final ToscaRoot root) {

		artifacts = root.getArtifactTypes();
		capabilities = root.getCapabilityTypes();
		description = root.getDescription();
		imports = root.getImports();
		nodeType = root.getNodeTypes();
		relationship = root.getRelationshipTypes();
		topologies = root.getTopologyTemplate();
		version = root.getVersion();

		init();
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

	public void setRelationship(final Map<String, ToscaClass> rels) {
		relationship = rels;
	}

	public void setArtifacts(final Map<String, ToscaClass> arts) {
		artifacts = arts;
	}

	public void setCapabilities(final Map<String, ToscaClass> caps) {
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

	public Map<String, ToscaClass> getRelationship() {
		return relationship;
	}

	public Map<String, ToscaClass> getArtifacts() {
		return artifacts;
	}

	public Map<String, ToscaClass> getCapabilities() {
		return capabilities;
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
		final Set<Entry<String, ToscaClass>> entry3 = relationship.entrySet();
		for (final Entry<String, ToscaClass> toscaClass : entry3) {
			sb.append(" - ").append(toscaClass).append("\n");
		}

		sb.append(", artifacts=\n");
		entry = artifacts.entrySet();
		for (final Entry<String, ToscaClass> toscaClass : entry) {
			sb.append(" - ").append(toscaClass).append("\n");
		}

		sb.append(", capabilities=\n");
		final Set<Entry<String, ToscaClass>> entry2 = capabilities.entrySet();
		for (final Entry<String, ToscaClass> toscaClass : entry2) {
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
			LOG.info("Resolving: {}", entry2.getKey());
			final Import value = entry2.getValue();
			final String content = resolver.getContent(value.getUrl());
			final ToscaParser main = new ToscaParser();
			final ToscaContext context = main.parseContent(content);
			context.resolvImports();
			mergeContext(context);
		}
	}

	private void mergeContext(final ToscaContext context) {
		mergeHash(artifacts, context.getArtifacts());
		mergeHash(capabilities, context.getCapabilities());
		mergeHash(nodeType, context.getNodeType());
		mergeHash(relationship, context.getRelationship());
		// topologies.merge(context.getTopologies());
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
		final Set<Entry<String, ToscaClass>> entries = nodeType.entrySet();
		for (final Entry<String, ToscaClass> entry : entries) {
			final ToscaClass clazz = entry.getValue();
			final String derived = clazz.getDerivedFrom();
			if ((null != derived) && !nodeType.containsKey(derived)) {
				// Throw exception unresolvable external/.
				System.out.println(derived + " not a Node Type.");
			} else {
				// clazz.setDerived(nodeType.get(derived));
			}
		}
	}
}
