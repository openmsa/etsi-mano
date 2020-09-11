package com.ubiqube.etsi.mano.dao.mano;

import javax.persistence.Embeddable;

/**
 * Database entity.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Embeddable
public class SubscriptionQuery {

	private String callbackUri = null;

	public String getCallbackUri() {
		return callbackUri;
	}

	public void setCallbackUri(final String callbackUri) {
		this.callbackUri = callbackUri;
	}

}
