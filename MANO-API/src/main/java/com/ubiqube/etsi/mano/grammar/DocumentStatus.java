package com.ubiqube.etsi.mano.grammar;

public class DocumentStatus {
	public enum Status {
		REFUSED,
		VALIDATED,
		NOSTATE
	}

	public DocumentStatus(Status status) {
		super();
		this.status = status;
	}

	private Status status;
	private boolean result;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

}
