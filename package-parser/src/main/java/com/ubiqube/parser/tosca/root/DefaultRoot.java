package com.ubiqube.parser.tosca.root;

public class DefaultRoot {
	private String tosca_definitions_version;
	private Object node_types;
	private Object relationship_types;
	private Object capability_types;
	private Object interface_types;
	private Object data_types;
	private Object artifact_types;
	private Object policy_types;
	private Object group_types;

	public String getTosca_definitions_version() {
		return tosca_definitions_version;
	}

	public void setTosca_definitions_version(final String tosca_definitions_version) {
		this.tosca_definitions_version = tosca_definitions_version;
	}

	public Object getNode_types() {
		return node_types;
	}

	public void setNode_types(final Object node_types) {
		this.node_types = node_types;
	}

	public Object getRelationship_types() {
		return relationship_types;
	}

	public void setRelationship_types(final Object relationship_types) {
		this.relationship_types = relationship_types;
	}

	public Object getCapability_types() {
		return capability_types;
	}

	public void setCapability_types(final Object capability_types) {
		this.capability_types = capability_types;
	}

	public Object getInterface_types() {
		return interface_types;
	}

	public void setInterface_types(final Object interface_types) {
		this.interface_types = interface_types;
	}

	public Object getData_types() {
		return data_types;
	}

	public void setData_types(final Object data_types) {
		this.data_types = data_types;
	}

	public Object getArtifact_types() {
		return artifact_types;
	}

	public void setArtifact_types(final Object artifact_types) {
		this.artifact_types = artifact_types;
	}

	public Object getPolicy_types() {
		return policy_types;
	}

	public void setPolicy_types(final Object policy_types) {
		this.policy_types = policy_types;
	}

	public Object getGroup_types() {
		return group_types;
	}

	public void setGroup_types(final Object group_types) {
		this.group_types = group_types;
	}

}
