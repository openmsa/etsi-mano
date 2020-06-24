package com.ubiqube.etsi.mano.dao.mano;

import java.io.Serializable;

import javax.persistence.Embeddable;

import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.ScaleVnfRequest.TypeEnum;

@Embeddable
public class VnfScaleInfo implements Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private TypeEnum scaleType = null;

	private Integer numberOfSteps = null;

	private String aspectId;

	public VnfScaleInfo() {
		// Nothing.
	}

	public VnfScaleInfo(final TypeEnum scaleType, final String aspectId, final Integer numberOfSteps) {
		super();
		this.scaleType = scaleType;
		this.numberOfSteps = numberOfSteps;
		this.aspectId = aspectId;
	}

	public TypeEnum getScaleType() {
		return scaleType;
	}

	public void setScaleType(final TypeEnum scaleType) {
		this.scaleType = scaleType;
	}

	public Integer getNumberOfSteps() {
		return numberOfSteps;
	}

	public void setNumberOfSteps(final Integer numberOfSteps) {
		this.numberOfSteps = numberOfSteps;
	}

	public String getAspectId() {
		return aspectId;
	}

	public void setAspectId(final String aspectId) {
		this.aspectId = aspectId;
	}

}
