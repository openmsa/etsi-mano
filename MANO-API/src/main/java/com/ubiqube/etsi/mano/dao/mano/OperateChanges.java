package com.ubiqube.etsi.mano.dao.mano;

import javax.persistence.Embeddable;

import com.ubiqube.etsi.mano.model.nslcm.VnfOperationalStateType;

@Embeddable
public class OperateChanges {

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
