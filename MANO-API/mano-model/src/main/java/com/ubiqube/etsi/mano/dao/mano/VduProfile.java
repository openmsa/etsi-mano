package com.ubiqube.etsi.mano.dao.mano;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class VduProfile implements Serializable {

	/** Serial. */
	private static final long serialVersionUID = 1L;

	private int minNumberOfInstances;

	private int maxNumberOfInstances;

	public int getMinNumberOfInstances() {
		return minNumberOfInstances;
	}

	public void setMinNumberOfInstances(final int minNumberOfInstances) {
		this.minNumberOfInstances = minNumberOfInstances;
	}

	public int getMaxNumberOfInstances() {
		return maxNumberOfInstances;
	}

	public void setMaxNumberOfInstances(final int maxNumberOfInstances) {
		this.maxNumberOfInstances = maxNumberOfInstances;
	}

}
