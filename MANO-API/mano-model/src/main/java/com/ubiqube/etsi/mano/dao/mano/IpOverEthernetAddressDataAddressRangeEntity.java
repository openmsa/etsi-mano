package com.ubiqube.etsi.mano.dao.mano;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class IpOverEthernetAddressDataAddressRangeEntity implements Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private String minAddress = null;

	private String maxAddress = null;

	public String getMinAddress() {
		return minAddress;
	}

	public void setMinAddress(final String minAddress) {
		this.minAddress = minAddress;
	}

	public String getMaxAddress() {
		return maxAddress;
	}

	public void setMaxAddress(final String maxAddress) {
		this.maxAddress = maxAddress;
	}

}
