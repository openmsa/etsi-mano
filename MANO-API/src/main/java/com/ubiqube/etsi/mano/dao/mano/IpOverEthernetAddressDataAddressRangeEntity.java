package com.ubiqube.etsi.mano.dao.mano;

import javax.persistence.Embeddable;

@Embeddable
public class IpOverEthernetAddressDataAddressRangeEntity {
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
