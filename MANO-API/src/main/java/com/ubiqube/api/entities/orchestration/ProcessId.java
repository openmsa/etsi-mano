package com.ubiqube.api.entities.orchestration;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProcessId {

	private Long id;
	private Long lastExecNumber;
	private String name;
	private String submissionType;

	@JsonProperty("id")
	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	@JsonProperty("lastExecNumber")
	public Long getLastExecNumber() {
		return lastExecNumber;
	}

	public void setLastExecNumber(final Long lastExecNumber) {
		this.lastExecNumber = lastExecNumber;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@JsonProperty("submissionType")
	public String getSubmissionType() {
		return submissionType;
	}

	public void setSubmissionType(final String submissionType) {
		this.submissionType = submissionType;
	}

	@Override
	public String toString() {
		return "ProcessId [id=" + id + ", lastExecNumber=" + lastExecNumber + ", name=" + name + ", submissionType=" + submissionType + "]";
	}
}
