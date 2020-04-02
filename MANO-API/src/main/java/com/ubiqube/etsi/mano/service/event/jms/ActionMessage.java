package com.ubiqube.etsi.mano.service.event.jms;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;

import com.ubiqube.etsi.mano.service.event.ActionType;

public class ActionMessage {
	@Nonnull
	private ActionType actionType;
	@Nonnull
	private String objectId;
	@Nonnull
	private Map<String, Object> parameters = new HashMap<>();

	public ActionMessage() {
		// Nothing.
	}

	public ActionMessage(@Nonnull final ActionType actionType, @Nonnull final String objectId, @Nonnull final Map<String, Object> parameters) {
		super();
		this.actionType = actionType;
		this.objectId = objectId;
		this.parameters = parameters;
	}

	@Nonnull
	public ActionType getActionType() {
		return actionType;
	}

	public void setActionType(final ActionType actionType) {
		this.actionType = actionType;
	}

	@Nonnull
	public String getObjectId() {
		return objectId;
	}

	@Nonnull
	public Map<String, Object> getParameters() {
		return parameters;
	}

	public void setObjectId(final String objectId) {
		this.objectId = objectId;
	}

	public void setParameters(final Map<String, Object> parameters) {
		this.parameters = parameters;
	}

	@Override
	public String toString() {
		return "ActionMessage [actionType=" + actionType + ", objectId=" + objectId + ", parameters=" + parameters + "]";
	}

}
