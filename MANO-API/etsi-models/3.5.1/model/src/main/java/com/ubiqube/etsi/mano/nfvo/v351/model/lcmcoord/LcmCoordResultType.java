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
package com.ubiqube.etsi.mano.nfvo.v351.model.lcmcoord;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The enumeration LcmCoordResultType defines the permitted values to represent
 * the result of executing an LCM coordination action. The coordination result
 * also implies the action to be performed by the NFVO as the follow-up to this
 * coordination. The LcmCoordResultType shall comply with the provisions defined
 * in table 12.5.4.3.-1. - CONTINUE: The related LCM operation shall be
 * continued, staying in the state \"PROCESSING\". - ABORT: The related LCM
 * operation shall be aborted by transitioning into the state \"FAILED_TEMP\". -
 * CANCELLED: The coordination action has been cancelled upon request of the API
 * consumer, i.e. the NFVO. The related LCM operation shall be aborted by
 * transitioning into the state \"FAILED_TEMP\".
 */
public enum LcmCoordResultType {
	CONTINUE("CONTINUE"),
	ABORT("ABORT"),
	CANCELLED("CANCELLED");

	private String value;

	LcmCoordResultType(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static LcmCoordResultType fromValue(final String text) {
		for (final LcmCoordResultType b : LcmCoordResultType.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}
