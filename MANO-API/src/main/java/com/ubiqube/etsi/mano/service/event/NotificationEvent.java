package com.ubiqube.etsi.mano.service.event;

import java.util.stream.Stream;

/**
 * A few event that we need to handle.
 *
 * @author ovi@ubiqube.com
 *
 */
public enum NotificationEvent {
	VNF_PKG_ONBOARDING("VNF_PKG_ONBOARDING"),
	VNF_PKG_ONCHANGE("VNF_PKG_ONCHANGE"),
	VNF_PKG_ONDELETION("VNF_PKG_ONDELETION"),

	VNF_INSTANCE_CREATE("VNF_INSTANCE_CREATE"),
	VNF_INSTANTIATE("VNF_INSTANTIATE"),
	VNF_SCALE("VNF_SCALE"),
	VNF_UPDATE("VNF_UPDATE"),
	VNF_TERMINATE("VNF_TERMINATE"),
	VNF_HEAL("VNF_HEAL"),

	NS_PKG_ONBOARDING("NS_PKG_ONBOARDING"),
	NS_PKG_ONCHANGE("NS_PKG_ONCHANGE"),
	NS_PKG_ONDELETION("NS_PKG_ONDELETION"),

	NS_INSTANTIATE("NS_INSTANTIATE"),
	NS_SCALE("NS_SCALE"),
	NS_UPDATE("NS_UPDATE"),
	NS_TERMINATE("NS_TERMINATE"),
	NS_HEAL("NS_HEAL");

	private String value;

	NotificationEvent(final String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	@Override
	public String toString() {
		return value;
	}

	public static NotificationEvent fromValue(final String v) {
		return Stream.of(NotificationEvent.values())
				.filter(x -> x.value.equals(v))
				.findFirst()
				.orElse(null);
	}
}
