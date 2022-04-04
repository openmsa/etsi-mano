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
 * Enum for all asynchronous event.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public enum ActionType {
	VNF_PKG_ONBOARD_FROM_URI("VNF_PKG_ONBOARD_FROM_URI"),
	VNF_PKG_ONBOARD_FROM_BYTES("VNF_PKG_ONBOARD_FROM_BYTES"),
	NSD_PKG_ONBOARD_FROM_BYTES("NSD_PKG_ONBOARD_FROM_BYTES"),
	NS_INSTANTIATE("NS_INSTANTIATE"),
	NS_TERMINATE("NS_TERMINATE"),
	VNF_INSTANTIATE("VNF_INSTANTIATE"),
	VNF_TERMINATE("VNF_TERMINATE"),
	VNF_OPERATE("VNF_OPERATE"),
	VNF_CHANGE_CONN("VNF_CHANGE_CONN"),
	VNF_SCALE_TO_LEVEL("VNF_SCALE_TO_LEVEL"),
	GRANT_REQUEST("GRANT_REQUEST"),
	MEO_ONBOARDING("MEO_ONBOARDING"),
	MEPM_INSTANTIATE("MEPM_INSTANTIATE"),
	MEPM_TERMINATE("MEPM_TERMINATE"),
	MEPM_OPERATE("MEPM_OPERATE"),
	UNKNOW("UNKNOWN"),
	VNF_PKG_ONBOARD_DOWNLOAD("VNF_PKG_ONBOARD_DOWNLOAD"),
	VNF_PKG_ONBOARD_DOWNLOAD_INSTANTIATE("VNF_PKG_ONBOARD_DOWNLOAD_INSTANTIATE"),
	REGISTER_SERVER("REGISTER_SERVER"),
	NS_SCALE("NS_SCALE"),
	NS_HEAL("NS_HEAL"),
	;

	private String value;

	ActionType(final String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	public static ActionType fromValue(final String v) {
		return Stream.of(ActionType.values())
				.filter(x -> String.valueOf(x.value).equals(v))
				.findFirst()
				.orElse(null);
	}
}
