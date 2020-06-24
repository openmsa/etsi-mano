package com.ubiqube.etsi.mano.dao.mano;

import javax.persistence.Embeddable;

// TODO: Probably need to change NotificationTypesEnum.
@Embeddable
public class SubscriptionFilter {
	private String notificationTypes = null;

	// We will store some RAW Json or what ever for the momment.
	private String data;

	public String getNotificationTypes() {
		return notificationTypes;
	}

	public void setNotificationTypes(final String notificationTypes) {
		this.notificationTypes = notificationTypes;
	}

	public String getData() {
		return data;
	}

	public void setData(final String data) {
		this.data = data;
	}

}
