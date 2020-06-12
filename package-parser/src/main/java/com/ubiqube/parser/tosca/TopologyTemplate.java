package com.ubiqube.parser.tosca;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TopologyTemplate {

	private Object inputs;
	private Map<String, NodeTemplate> nodeTemplate;

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

}
