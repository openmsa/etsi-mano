package com.ubiqube.etsi.mano.service.event.jms;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;

import com.ubiqube.etsi.mano.service.event.ActionType;

public class ActionMessage {
	@NotNull
	private ActionType actionType;
	@NotNull
	private String objectId;
	@NotNull
	private Map<String, Object> parameters = new HashMap<>();

	public ActionMessage() {
		// Nothing.
	}

	public ActionMessage(@NotNull final ActionType actionType, @NotNull final String objectId, @NotNull final Map<String, Object> parameters) {
		super();
		this.actionType = actionType;
		this.objectId = objectId;
		this.parameters = parameters;
	}

	@NotNull
	public ActionType getActionType() {
		return actionType;
	}

	public void setActionType(@NotNull final ActionType actionType) {
		this.actionType = actionType;
	}

	@NotNull
	public String getObjectId() {
		return objectId;
	}

	@NotNull
	public Map<String, Object> getParameters() {
		return parameters;
	}

	public void setObjectId(@NotNull final String objectId) {
		this.objectId = objectId;
	}

	public void setParameters(@NotNull final Map<String, Object> parameters) {
		this.parameters = parameters;
	}

	@Override
	public String toString() {
		return "ActionMessage [actionType=" + actionType + ", objectId=" + objectId + ", parameters=" + parameters + "]";
	}

}
