package com.ubiqube.parser.tosca;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TopologyTemplate {

	private Object inputs;
	private Map<String, NodeTemplate> nodeTemplate;
	private Map<String, GroupDefinition> groups = new HashMap<>();

	public Object getInputs() {
		return inputs;
	}

	public void setInputs(final Object inputs) {
		this.inputs = inputs;
	}

	@JsonProperty("node_templates")
	public Map<String, NodeTemplate> getNodeTemplate() {
		return nodeTemplate;
	}

	public void setNodeTemplate(final Map<String, NodeTemplate> nodeTemplate) {
		this.nodeTemplate = nodeTemplate;
	}

	public Map<String, GroupDefinition> getGroups() {
		return groups;
	}

	public void setGroups(final Map<String, GroupDefinition> groups) {
		this.groups = groups;
	}

	@Override
	public String toString() {
		return "TopologyTemplate [inputs=" + inputs + ", nodeTemplate=" + nodeTemplate + "]";
	}

}
