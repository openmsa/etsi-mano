package com.ubiqube.api.entities.orchestration;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProcessInstance {

	private ProcessId processId;
	private ServiceId serviceId;
	private Status status;

	@JsonProperty("processId")
	public ProcessId getProcessId() {
		return processId;
	}

	public void setProcessId(final ProcessId processId) {
		this.processId = processId;
	}

	@JsonProperty("serviceId")
	public ServiceId getServiceId() {
		return serviceId;
	}

	public void setServiceId(final ServiceId serviceId) {
		this.serviceId = serviceId;
	}

	@JsonProperty("status")
	public Status getStatus() {
		return status;
	}

	public void setStatus(final Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ProcessInstance [processId=" + processId + ", serviceId=" + serviceId + ", status=" + status + "]";
	}

}
