package com.ubiqube.parser.tosca;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ToscaRoot {
	private List<HashMap<String, String>> imports;
	private TopologyTemplate topologyTemplate;
	private String version;
	private String description;
	private Map<String, CapabilityTypes> capabilityTypes;
	private Map<String, ArtifactTypes> artifactTypes;
	private Map<String, RelationshipTypes> relationshipTypes;
	private Map<String, NodeTypes> nodeTypes;

	public List<HashMap<String, String>> getImports() {
		return imports;
	}

	public void setImports(final List<HashMap<String, String>> imports) {
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
	public Map<String, ArtifactTypes> getArtifactTypes() {
		return artifactTypes;
	}

	public void setArtifactTypes(final Map<String, ArtifactTypes> artifactTypes) {
		this.artifactTypes = artifactTypes;
	}

	@JsonProperty("relationship_types")
	public Map<String, RelationshipTypes> getRelationshipTypes() {
		return relationshipTypes;
	}

	public void setRelationshipTypes(final Map<String, RelationshipTypes> relationshipTypes) {
		this.relationshipTypes = relationshipTypes;
	}

	@JsonProperty("node_types")
	public Map<String, NodeTypes> getNodeTypes() {
		return nodeTypes;
	}

	public void setNodeTypes(final Map<String, NodeTypes> nodeTypes) {
		this.nodeTypes = nodeTypes;
	}

}
