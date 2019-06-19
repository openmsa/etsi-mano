package com.ubiqube.etsi.mano.model.vnf.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NotificationsMessage {
	private String subscriptionId;
	private String vnfPkgId;
	private String vnfdId;

	@JsonProperty("subscriptionId")
	public String getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(String _subscriptionId) {
		subscriptionId = _subscriptionId;
	}

	@JsonProperty("vnfPkgId")
	public String getVnfPkgId() {
		return vnfPkgId;
	}

	public void setVnfPkgId(String _vnfPkgId) {
		vnfPkgId = _vnfPkgId;
	}

	@JsonProperty("vnfdId")
	public String getVnfdId() {
		return vnfdId;
	}

	public void setVnfdId(String _vnfdId) {
		vnfdId = _vnfdId;
	}

}
