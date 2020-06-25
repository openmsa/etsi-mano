package com.ubiqube.etsi.mano.dao.mano;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class OperateChanges implements Serializable {

	/** Serial. */
	private static final long serialVersionUID = 1L;

	private OperationalStateType terminationType;

	private int gracefulTerminationTimeout;

	public OperationalStateType getTerminationType() {
		return terminationType;
	}

	public void setTerminationType(final OperationalStateType terminationType) {
		this.terminationType = terminationType;
	}

	public int getGracefulTerminationTimeout() {
		return gracefulTerminationTimeout;
	}

	public void setGracefulTerminationTimeout(final int gracefulTerminationTimeout) {
		this.gracefulTerminationTimeout = gracefulTerminationTimeout;
	}

}
