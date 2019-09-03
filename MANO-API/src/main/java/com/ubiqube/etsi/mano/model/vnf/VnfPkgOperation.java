package com.ubiqube.etsi.mano.model.vnf;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VnfPkgOperation {
	private String id = null;
	private String processId = null;

	public VnfPkgOperation(@NotNull final String _id, final String _processId) {
		id = _id;
		processId = _processId;
	}

	@JsonProperty("Id")
	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	@JsonProperty("ProcessId")
	public String getProcessId() {
		return processId;
	}

	public void setProcessId(final String processId) {
		this.processId = processId;
	}
}
