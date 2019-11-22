package com.ubiqube.etsi.mano.dao.mano;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Embeddable
public class SubscriptionQuery {
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "subscription")
	private List<FilterAttributes> subscriptionFilter;

	private String callbackUri = null;

	public List<FilterAttributes> getSubscriptionFilter() {
		return subscriptionFilter;
	}

	public void setSubscriptionFilter(final List<FilterAttributes> subscriptionFilter) {
		this.subscriptionFilter = subscriptionFilter;
	}

	public String getCallbackUri() {
		return callbackUri;
	}

	public void setCallbackUri(final String callbackUri) {
		this.callbackUri = callbackUri;
	}

}
