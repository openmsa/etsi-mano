package com.ubiqube.etsi.mano.dao.mano;

import javax.persistence.Embeddable;

@Embeddable
public class SubscriptionQuery {
	private SubscriptionFilter subscriptionFilter;
	private String callbackUri = null;

	public SubscriptionFilter getSubscriptionFilter() {
		return subscriptionFilter;
	}

	public void setSubscriptionFilter(final SubscriptionFilter subscriptionFilter) {
		this.subscriptionFilter = subscriptionFilter;
	}

	public String getCallbackUri() {
		return callbackUri;
	}

	public void setCallbackUri(final String callbackUri) {
		this.callbackUri = callbackUri;
	}

}
