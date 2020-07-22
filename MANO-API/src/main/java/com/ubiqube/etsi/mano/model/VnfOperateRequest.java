package com.ubiqube.etsi.mano.model;

import java.util.Map;

import com.ubiqube.etsi.mano.dao.mano.OperationalStateType;

public class VnfOperateRequest {

	private OperationalStateType changeStateTo = null;

	private Integer gracefulStopTimeout = null;

	private Map<String, String> additionalParams = null;

	public OperationalStateType getChangeStateTo() {
		return changeStateTo;
	}

	public void setChangeStateTo(final OperationalStateType changeStateTo) {
		this.changeStateTo = changeStateTo;
	}

	public Integer getGracefulStopTimeout() {
		return gracefulStopTimeout;
	}

	public void setGracefulStopTimeout(final Integer gracefulStopTimeout) {
		this.gracefulStopTimeout = gracefulStopTimeout;
	}

	public Map<String, String> getAdditionalParams() {
		return additionalParams;
	}

	public void setAdditionalParams(final Map<String, String> additionalParams) {
		this.additionalParams = additionalParams;
	}

}
