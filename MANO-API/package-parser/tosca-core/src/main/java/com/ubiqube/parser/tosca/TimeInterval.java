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
package com.ubiqube.parser.tosca;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TimeInterval {
	@JsonProperty("start_time")
	private String startTime;
	@JsonProperty("end_time")
	private String endTime;

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(final String start_time) {
		this.startTime = start_time;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(final String end_time) {
		this.endTime = end_time;
	}

}
