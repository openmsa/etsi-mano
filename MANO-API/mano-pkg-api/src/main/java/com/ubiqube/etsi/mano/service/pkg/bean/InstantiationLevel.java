package com.ubiqube.etsi.mano.service.pkg.bean;

import java.util.Map;

public class InstantiationLevel {

	/**
	 * Human readable description of the level
	 * 
	 */
	private String description;
	/**
	 * Represents for each aspect the scale level that corresponds to this
	 * instantiation level. scale_info shall be present if the VNF supports scaling.
	 * 
	 */
	private Map<String, ScaleInfo> scaleInfo;

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public Map<String, ScaleInfo> getScaleInfo() {
		return scaleInfo;
	}

	public void setScaleInfo(final Map<String, ScaleInfo> scaleInfo) {
		this.scaleInfo = scaleInfo;
	}

}
