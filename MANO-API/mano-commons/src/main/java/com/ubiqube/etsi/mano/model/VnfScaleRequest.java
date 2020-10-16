package com.ubiqube.etsi.mano.model;

import java.util.Map;

import com.ubiqube.etsi.mano.dao.mano.ScaleTypeEnum;

public class VnfScaleRequest {

	private ScaleTypeEnum type;

	private String aspectId = null;

	private Integer numberOfSteps;

	private Map<String, String> additionalParams = null;

	public ScaleTypeEnum getType() {
		return type;
	}

	public void setType(final ScaleTypeEnum type) {
		this.type = type;
	}

	public String getAspectId() {
		return aspectId;
	}

	public void setAspectId(final String aspectId) {
		this.aspectId = aspectId;
	}

	public Integer getNumberOfSteps() {
		return numberOfSteps;
	}

	public void setNumberOfSteps(final Integer numberOfSteps) {
		this.numberOfSteps = numberOfSteps;
	}

	public Map<String, String> getAdditionalParams() {
		return additionalParams;
	}

	public void setAdditionalParams(final Map<String, String> additionalParams) {
		this.additionalParams = additionalParams;
	}

}
