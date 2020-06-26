package com.ubiqube.etsi.mano.nfvo.v261.model.nsd;

public class NsdPkgOperation {
	private String id = null;
	private String processId = null;

	public NsdPkgOperation() {
		// Nothing.
	}

	public NsdPkgOperation(final String id, final String processId) {
		super();
		this.id = id;
		this.processId = processId;
	}

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(final String processId) {
		this.processId = processId;
	}

}
