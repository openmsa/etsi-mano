package com.ubiqube.parser.tosca;

import java.util.Map;

// TODO rename activityDefinition
public class ActionDefinition {
	private String delegate;
	private String workflow;
	Map<String, Object> inputs;

	public String getDelegate() {
		return delegate;
	}

	public void setDelegate(final String delegate) {
		this.delegate = delegate;
	}

	public String getWorkflow() {
		return workflow;
	}

	public void setWorkflow(final String workflow) {
		this.workflow = workflow;
	}

	public Map<String, Object> getInputs() {
		return inputs;
	}

	public void setInputs(final Map<String, Object> inputs) {
		this.inputs = inputs;
	}

}
