package com.ubiqube.etsi.mano.service.event.jms;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.validation.constraints.NotNull;

public class GrantMessage {
	@NotNull
	private UUID objectId;
	@NotNull
	private Map<String, Object> parameters = new HashMap<>();

	public GrantMessage() {
		// Nothing.
	}

	public GrantMessage(@NotNull final UUID objectId, @NotNull final Map<String, Object> parameters) {
		super();
		this.objectId = objectId;
		this.parameters = parameters;
	}

	public UUID getObjectId() {
		return objectId;
	}

	public void setObjectId(final UUID objectId) {
		this.objectId = objectId;
	}

	public Map<String, Object> getParameters() {
		return parameters;
	}

	public void setParameters(final Map<String, Object> parameters) {
		this.parameters = parameters;
	}

}
