package com.ubiqube.etsi.mano.dao.mano;

import java.io.Serializable;

import javax.persistence.Embeddable;

import com.ubiqube.etsi.mano.model.nslcm.VnfOperationalStateType;

@Embeddable
public class OperateChanges implements Serializable {

	/** Serial. */
	private static final long serialVersionUID = 1L;

	private VnfOperationalStateType terminationType;

	private int gracefulTerminationTimeout;

	public VnfOperationalStateType getTerminationType() {
		return terminationType;
	}

	public void setTerminationType(final VnfOperationalStateType terminationType) {
		this.terminationType = terminationType;
	}

	public int getGracefulTerminationTimeout() {
		return gracefulTerminationTimeout;
	}

	public void setGracefulTerminationTimeout(final int gracefulTerminationTimeout) {
		this.gracefulTerminationTimeout = gracefulTerminationTimeout;
	}

}
