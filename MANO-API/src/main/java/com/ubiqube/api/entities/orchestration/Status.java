package com.ubiqube.api.entities.orchestration;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Status {
	private String details;
	private String endingDate;
	private long execNumber;
	List<ProcessTaskStatus> processTaskStatus;
	private String startingDate;
	private String status;

	@JsonProperty("details")
	public String getDetails() {
		return details;
	}

	public void setDetails(final String details) {
		this.details = details;
	}

	@JsonProperty("endingDate")
	public String getEndingDate() {
		return endingDate;
	}

	public void setEndingDate(final String endingDate) {
		this.endingDate = endingDate;
	}

	@JsonProperty("execNumber")
	public long getExecNumber() {
		return execNumber;
	}

	public void setExecNumber(final long execNumber) {
		this.execNumber = execNumber;
	}

	@JsonProperty("processTaskStatus")
	public List<ProcessTaskStatus> getProcessTaskStatus() {
		return processTaskStatus;
	}

	public void setProcessTaskStatus(final List<ProcessTaskStatus> processTaskStatus) {
		this.processTaskStatus = processTaskStatus;
	}

	@JsonProperty("startingDate")
	public String getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(final String startingDate) {
		this.startingDate = startingDate;
	}

	@JsonProperty("status")
	public String getStatus() {
		return status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Status [details=" + details + ", endingDate=" + endingDate + ", execNumber=" + execNumber + ", processTaskStatus=" + processTaskStatus + ", startingDate=" + startingDate + ", status=" + status + "]";
	}

}