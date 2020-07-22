package com.ubiqube.etsi.mano.model;

import java.util.List;
import java.util.Map;

public class VnfScaleToLevelRequest {
	private String instantiationLevelId = null;

	private List<CommScaleInfo> scaleInfo;

	private Map<String, String> additionalParams = null;

	public String getInstantiationLevelId() {
		return instantiationLevelId;
	}

	public void setInstantiationLevelId(final String instantiationLevelId) {
		this.instantiationLevelId = instantiationLevelId;
	}

	public List<CommScaleInfo> getScaleInfo() {
		return scaleInfo;
	}

	public void setScaleInfo(final List<CommScaleInfo> scaleInfo) {
		this.scaleInfo = scaleInfo;
	}

	public Map<String, String> getAdditionalParams() {
		return additionalParams;
	}

	public void setAdditionalParams(final Map<String, String> additionalParams) {
		this.additionalParams = additionalParams;
	}

}
