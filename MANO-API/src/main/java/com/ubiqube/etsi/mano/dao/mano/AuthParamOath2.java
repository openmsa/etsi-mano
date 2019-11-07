package com.ubiqube.etsi.mano.dao.mano;

import javax.persistence.Embeddable;

@Embeddable
public class AuthParamOath2 {
	private String clientId = null;
	private String clientPassword = null;
	private String tokenEndpoint = null;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(final String clientId) {
		this.clientId = clientId;
	}

	public String getClientPassword() {
		return clientPassword;
	}

	public void setClientPassword(final String clientPassword) {
		this.clientPassword = clientPassword;
	}

	public String getTokenEndpoint() {
		return tokenEndpoint;
	}

	public void setTokenEndpoint(final String tokenEndpoint) {
		this.tokenEndpoint = tokenEndpoint;
	}

}
