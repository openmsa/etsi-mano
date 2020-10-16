package com.ubiqube.etsi.mano.model;

import java.io.Serializable;
import java.time.OffsetDateTime;

public class NsInstantiate implements Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private String nsFlavourId = null;

	private OffsetDateTime startTime = null;

	private String nsInstantiationLevelId = null;

	public String getNsFlavourId() {
		return nsFlavourId;
	}

	public void setNsFlavourId(final String nsFlavourId) {
		this.nsFlavourId = nsFlavourId;
	}

	public OffsetDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(final OffsetDateTime startTime) {
		this.startTime = startTime;
	}

	public String getNsInstantiationLevelId() {
		return nsInstantiationLevelId;
	}

	public void setNsInstantiationLevelId(final String nsInstantiationLevelId) {
		this.nsInstantiationLevelId = nsInstantiationLevelId;
	}

}
