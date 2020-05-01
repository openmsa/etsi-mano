package com.ubiqube.etsi.mano.service.event;

import java.util.stream.Stream;

/**
 * A few event that we need to handle.
 *
 * @author ovi@ubiqube.com
 *
 */
public enum NotificationEvent {
	VNF_PKG_ONBOARDING(String.valueOf("VNF_PKG_ONBOARDING")),
	VNF_PKG_ONCHANGE(String.valueOf("VNF_PKG_ONCHANGE")),
	VNF_PKG_ONDELETION(String.valueOf("VNF_PKG_ONDELETION")),

	VNF_INSTANCE_CREATE(String.valueOf("VNF_INSTANCE_CREATE")),
	VNF_INSTANTIATE(String.valueOf("VNF_INSTANTIATE")),
	VNF_SCALE(String.valueOf("VNF_SCALE")),
	VNF_UPDATE(String.valueOf("VNF_UPDATE")),
	VNF_TERMINATE(String.valueOf("VNF_TERMINATE")),
	VNF_HEAL(String.valueOf("VNF_HEAL")),

	NS_PKG_ONBOARDING(String.valueOf("NS_PKG_ONBOARDING")),
	NS_PKG_ONCHANGE(String.valueOf("NS_PKG_ONCHANGE")),
	NS_PKG_ONDELETION(String.valueOf("NS_PKG_ONDELETION")),

	NS_INSTANTIATE(String.valueOf("NS_INSTANTIATE")),
	NS_SCALE(String.valueOf("NS_SCALE")),
	NS_UPDATE(String.valueOf("NS_UPDATE")),
	NS_TERMINATE(String.valueOf("NS_TERMINATE")),
	NS_HEAL(String.valueOf("NS_HEAL"));

	private String value;

	NotificationEvent(final String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	public static NotificationEvent fromValue(final String v) {
		return Stream.of(NotificationEvent.values())
				.filter(x -> String.valueOf(x.value).equals(v))
				.findFirst()
				.orElse(null);
	}
}
