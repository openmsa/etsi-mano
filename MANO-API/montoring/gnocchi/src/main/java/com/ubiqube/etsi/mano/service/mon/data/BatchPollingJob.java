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
package com.ubiqube.etsi.mano.service.mon.data;

import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BatchPollingJob {

	private UUID id;
	private List<String> hosts;
	private List<Metric> metrics;
	private UUID vimId;

	public BatchPollingJob() {
		// Nothing.
	}

	public BatchPollingJob(final UUID id, final List<String> hosts, final List<Metric> metrics, final UUID vimId) {
		super();
		this.id = id;
		this.hosts = hosts;
		this.metrics = metrics;
		this.vimId = vimId;
	}

}
