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
package com.ubiqube.etsi.mano.dao.mano.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public enum NicType {
	NORMAL("normal"),
	MACVTAP("macvtap"),
	DIRECT("direct"),
	BAREMETAL("baremetal"),
	DIRECT_PHYSICAL("direct-physical"),
	VIRTIO_FORWARDER("virtio-forwarder"),
	SMART_NIC("smart-nic");

	private final String value;

	NicType(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static NicType fromValue(final String text) {
		for (final NicType b : NicType.values()) {
			if (b.value.equals(text)) {
				return b;
			}
		}
		return NORMAL;
	}

}
