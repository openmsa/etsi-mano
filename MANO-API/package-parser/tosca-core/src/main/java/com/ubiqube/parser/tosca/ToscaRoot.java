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
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ToscaRoot {
	private Imports imports;
	private TopologyTemplate topologyTemplate = new TopologyTemplate();
	@JsonProperty("tosca_definitions_version")
	private String version;
	private String description;
	private String namespace;
	private Map<String, CapabilityTypes> capabilityTypes = new HashMap<>();
	private Map<String, ToscaClass> artifactTypes = new HashMap<>();
	private Map<String, RelationshipType> relationshipTypes = new HashMap<>();
	private Map<String, ToscaClass> nodeTypes = new HashMap<>();
	@JsonProperty("interface_types")
	private Map<String, InterfaceType> interfaceTypes;
	@JsonProperty("data_types")
	private Map<String, DataType> dataTypes;
	@JsonProperty("policy_types")
	private Map<String, PolicyType> policyTypes;
	// XXX Musrt be deleted.
	private Map<String, PolicyDefinition> policies;
	@JsonProperty("group_types")
	private Map<String, GroupType> groupTypes;
	// XXX: Must be deleted.
	private Map<String, GroupDefinition> groups;
	private Map<String, String> metadata;
	private Map<String, RepositoryDefinition> repositories;

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

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(final String namespace) {
		this.namespace = namespace;
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

	public Map<String, InterfaceType> getInterfaceTypes() {
		return interfaceTypes;
	}

	public void setInterfaceTypes(final Map<String, InterfaceType> interfaceType) {
		this.interfaceTypes = interfaceType;
	}

	public Map<String, DataType> getDataTypes() {
		return dataTypes;
	}

	public void setDataTypes(final Map<String, DataType> dataTypes) {
		this.dataTypes = dataTypes;
	}

	public Map<String, PolicyType> getPolicyTypes() {
		return policyTypes;
	}

	public void setPolicyTypes(final Map<String, PolicyType> policyTypes) {
		this.policyTypes = policyTypes;
	}

	public Map<String, GroupType> getGroupTypes() {
		return groupTypes;
	}

	public void setGroupTypes(final Map<String, GroupType> groupTypes) {
		this.groupTypes = groupTypes;
	}

	public Map<String, String> getMetadata() {
		return metadata;
	}

	public void setMetadata(final Map<String, String> metadata) {
		this.metadata = metadata;
	}

	public Map<String, GroupDefinition> getGroups() {
		return groups;
	}

	public void setGroups(final Map<String, GroupDefinition> groups) {
		this.groups = groups;
	}

	public Map<String, PolicyDefinition> getPolicies() {
		return policies;
	}

	public void setPolicies(final Map<String, PolicyDefinition> policies) {
		this.policies = policies;
	}

	public Map<String, RepositoryDefinition> getRepositories() {
		return repositories;
	}

	public void setRepositories(final Map<String, RepositoryDefinition> repositories) {
		this.repositories = repositories;
	}

	@Override
	public String toString() {
		return "ToscaRoot [imports=" + imports + ",\n topologyTemplate=" + topologyTemplate + ",\n version=" + version + ",\n description=" + description + ",\n capabilityTypes=" + capabilityTypes + ",\n artifactTypes=" + artifactTypes + ",\n relationshipTypes=" + relationshipTypes + ",\n nodeTypes=" + nodeTypes + "]";
	}

}
