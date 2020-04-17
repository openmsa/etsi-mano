package com.ubiqube.etsi.mano.dao.mano;

import javax.persistence.Embeddable;

@Embeddable
public class IpPool {
	private String startIpAddress;

	private String endIpAddress;

	public String getStartIpAddress() {
		return startIpAddress;
	}

	public void setStartIpAddress(final String startIpAddress) {
		this.startIpAddress = startIpAddress;
	}

	public String getEndIpAddress() {
		return endIpAddress;
	}

	public void setEndIpAddress(final String endIpAddress) {
		this.endIpAddress = endIpAddress;
	}

}
