package com.ubiqube.parser.tosca;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnySetter;

public class InterfaceType extends ToscaBaseEntity {
	private ToscaProperties inputs;
	// In V1.0 Json any setter is usefull, while not in V1.3
	@JsonAnySetter
	private Map<String, OperationDefinition> operations;
	private Object notifications;

	public ToscaProperties getInputs() {
		return inputs;
	}

	public void setInputs(final ToscaProperties inputs) {
		this.inputs = inputs;
	}

	public Map<String, OperationDefinition> getOperations() {
		return operations;
	}

	public void setOperations(final Map<String, OperationDefinition> operations) {
		this.operations = operations;
	}

	public Object getNotifications() {
		return notifications;
	}

	public void setNotifications(final Object notifications) {
		this.notifications = notifications;
	}

}
