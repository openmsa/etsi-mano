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
package com.ubiqube.etsi.mano.dao.mano;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ResourceTypeEnum {
	COMPUTE("COMPUTE"),

	VL("VL"),

	STORAGE("STORAGE"),

	LINKPORT("LINKPORT"), DNSZONE("DNSZONE"), SUBNETWORK("SUBNETWORK"),
	MONITORING("MONITORING"), SECURITY_GROUP("SECURITY_GROUP"), AFFINITY_RULE("AFFINITY_RULE"),
	VNFFG("VNFFG");

	private final String value;

	ResourceTypeEnum(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static ResourceTypeEnum fromValue(final String text) {
		for (final ResourceTypeEnum b : ResourceTypeEnum.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

}
