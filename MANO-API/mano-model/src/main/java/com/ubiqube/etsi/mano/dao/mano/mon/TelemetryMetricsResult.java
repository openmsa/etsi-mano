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
package com.ubiqube.etsi.mano.dao.mano.mon;

import java.time.OffsetDateTime;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Setter
@Getter
@ToString
public class TelemetryMetricsResult {
	@NotNull
	private String masterJobId;

	private String vnfInstanceId;

	private String key;

	private Double value;

	private OffsetDateTime timestamp;

	private boolean status;

	public TelemetryMetricsResult() {
		// Nothing.
	}

	public TelemetryMetricsResult(@NotNull final String masterJobId, final String vnfInstanceId, final String key, final Double value, final OffsetDateTime timestamp, final boolean status) {
		super();
		this.masterJobId = masterJobId;
		this.vnfInstanceId = vnfInstanceId;
		this.key = key;
		this.value = value;
		this.timestamp = timestamp;
		this.status = status;
	}

}
