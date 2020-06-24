package com.ubiqube.etsi.mano.dao.mano;

import javax.persistence.Embeddable;

import com.ubiqube.etsi.mano.nfvo.v261.model.vnf.PkgmNotificationsFilter.NotificationTypesEnum;

// TODO: Probably need to change NotificationTypesEnum.
@Embeddable
public class SubscriptionFilter {
	private NotificationTypesEnum notificationTypes = null;

	// We will store some RAW Json or what ever for the momment.
	private String data;

	public NotificationTypesEnum getNotificationTypes() {
		return notificationTypes;
	}

	public void setNotificationTypes(final NotificationTypesEnum notificationTypes) {
		this.notificationTypes = notificationTypes;
	}

	public String getData() {
		return data;
	}

	public void setData(final String data) {
		this.data = data;
	}

}
