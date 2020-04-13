package com.ubiqube.etsi.mano.dao.mano;

import javax.persistence.Embeddable;

@Embeddable
public class L2Data {
	private String name;

	private Boolean vlanTransparent = false;

	private String networkType;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Boolean getVlanTransparent() {
		return vlanTransparent;
	}

	public void setVlanTransparent(final Boolean vlanTransparent) {
		this.vlanTransparent = vlanTransparent;
	}

	public String getNetworkType() {
		return networkType;
	}

	public void setNetworkType(final String networkType) {
		this.networkType = networkType;
	}

}
