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
package com.ubiqube.etsi.mano.service.mon.model;

import java.time.OffsetDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Setter
@Getter
@Entity
public class MonitoringData {
	private UUID id;
	@Id
	private OffsetDateTime time;
	private String masterJobId;
	private Double value;
	private String key;
	private boolean status;
	private String vnfInstanceId;

	public MonitoringData() {
		// Nothing.
	}

	public MonitoringData(final String key2, final String masterJobId2, final OffsetDateTime timestamp, final Double value2, final String vnfInstanceId2, final boolean status) {
		this.id = UUID.randomUUID();
		this.time = timestamp;
		this.masterJobId = masterJobId2;
		this.value = value2;
		this.key = key2;
		this.status = status;
		this.vnfInstanceId = vnfInstanceId2;

	}

}
