/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
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
	VNF_INSTANTIATE_FAILED("VNF_INSTANTIATE_FAILED"),
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
	NS_HEAL("NS_HEAL"),

	APP_INSTANCE_CREATE("APP_INSTANCE_CREATE"),
	APPINSTANTIATE("APPINSTANTIATE"),
	APP_SCALE_FAILED("APP_SCALE_FAILED"),
	APP_SCALE_SUCCESS("APP_SCALE_SUCCESS"),
	APP_SCALETOLEVEL_FAILED("APP_SCALETOLEVEL_FAILED"),
	APP_SCALETOLEVEL_SUCCESS("APP_SCALETOLEVEL_SUCCESS"),
	APP_TERMINATE_FAILED("APP_TERMINATE_FAILED"),
	APP_TERMINATE_SUCCESS("APP_TERMINATE_SUCCESS"),

	VNF_INSTANCE_CHANGED("VNF_INSTANCE_CHANGED"),
	VRQAN("VRQAN");

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
