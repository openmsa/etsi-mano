package com.ubiqube.parser.tosca;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ToscaRoot {
	private Imports imports;
	private TopologyTemplate topologyTemplate;
	private String version;
	private String description;
	private Map<String, CapabilityTypes> capabilityTypes = new HashMap<>();
	private Map<String, ToscaClass> artifactTypes = new HashMap<>();
	private Map<String, RelationshipType> relationshipTypes = new HashMap<>();
	private Map<String, ToscaClass> nodeTypes = new HashMap<>();
	private Map<String, InterfaceType> interface_types;
	private Map<String, DataType> data_types;
	private Map<String, PolicyType> policy_types;
	private Map<String, GroupType> group_types;
	private Map<String, String> metadata;

	public Imports getImports() {
		return imports;
	}

	public void setImports(final Imports imports) {
		this.imports = imports;
	}

	@JsonProperty("topology_template")
	public TopologyTemplate getTopologyTemplate() {
		return topologyTemplate;
	}

	public void setTopologyTemplate(final TopologyTemplate topologyTemplate) {
		this.topologyTemplate = topologyTemplate;
	}

	@JsonProperty("tosca_definitions_version")
	public String getVersion() {
		return version;
	}

	public void setVersion(final String version) {
		this.version = version;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@JsonProperty("capability_types")
	public Map<String, CapabilityTypes> getCapabilityTypes() {
		return capabilityTypes;
	}

	public void setCapabilityTypes(final Map<String, CapabilityTypes> capabilityTypes) {
		this.capabilityTypes = capabilityTypes;
	}

	@JsonProperty("artifact_types")
	public Map<String, ToscaClass> getArtifactTypes() {
		return artifactTypes;
	}

	public void setArtifactTypes(final Map<String, ToscaClass> artifactTypes) {
		this.artifactTypes = artifactTypes;
	}

	@JsonProperty("relationship_types")
	public Map<String, RelationshipType> getRelationshipTypes() {
		return relationshipTypes;
	}

	public void setRelationshipTypes(final Map<String, RelationshipType> relationshipTypes) {
		this.relationshipTypes = relationshipTypes;
	}

	@JsonProperty("node_types")
	public Map<String, ToscaClass> getNodeTypes() {
		return nodeTypes;
	}

	public void setNodeTypes(final Map<String, ToscaClass> nodeTypes) {
		this.nodeTypes = nodeTypes;
	}

	public Map<String, InterfaceType> getInterface_types() {
		return interface_types;
	}

	public void setInterface_types(final Map<String, InterfaceType> interface_type) {
		this.interface_types = interface_type;
	}

	public Map<String, DataType> getData_types() {
		return data_types;
	}

	public void setData_types(final Map<String, DataType> data_types) {
		this.data_types = data_types;
	}

	public Map<String, PolicyType> getPolicy_types() {
		return policy_types;
	}

	public void setPolicy_types(final Map<String, PolicyType> policy_types) {
		this.policy_types = policy_types;
	}

	public Map<String, GroupType> getGroup_types() {
		return group_types;
	}

	public void setGroup_types(final Map<String, GroupType> group_types) {
		this.group_types = group_types;
	}

	public Map<String, String> getMetadata() {
		return metadata;
	}

	public void setMetadata(final Map<String, String> metadata) {
		this.metadata = metadata;
	}

	@Override
	public String toString() {
		return "ToscaRoot [imports=" + imports + ",\n topologyTemplate=" + topologyTemplate + ",\n version=" + version + ",\n description=" + description + ",\n capabilityTypes=" + capabilityTypes + ",\n artifactTypes=" + artifactTypes + ",\n relationshipTypes=" + relationshipTypes + ",\n nodeTypes=" + nodeTypes + "]";
	}

}
