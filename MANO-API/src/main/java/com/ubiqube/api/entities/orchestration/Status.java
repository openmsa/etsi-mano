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
package com.ubiqube.api.entities.orchestration;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Status {
	private String details;
	private String endingDate;
	private long execNumber;
	List<ProcessTaskStatus> processTaskStatus;
	private String startingDate;
	private String status;

	@JsonProperty("details")
	public String getDetails() {
		return details;
	}

	public void setDetails(final String details) {
		this.details = details;
	}

	@JsonProperty("endingDate")
	public String getEndingDate() {
		return endingDate;
	}

	public void setEndingDate(final String endingDate) {
		this.endingDate = endingDate;
	}

	@JsonProperty("execNumber")
	public long getExecNumber() {
		return execNumber;
	}

	public void setExecNumber(final long execNumber) {
		this.execNumber = execNumber;
	}

	@JsonProperty("processTaskStatus")
	public List<ProcessTaskStatus> getProcessTaskStatus() {
		return processTaskStatus;
	}

	public void setProcessTaskStatus(final List<ProcessTaskStatus> processTaskStatus) {
		this.processTaskStatus = processTaskStatus;
	}

	@JsonProperty("startingDate")
	public String getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(final String startingDate) {
		this.startingDate = startingDate;
	}

	@JsonProperty("status")
	public String getStatus() {
		return status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Status [details=" + details + ", endingDate=" + endingDate + ", execNumber=" + execNumber + ", processTaskStatus=" + processTaskStatus + ", startingDate=" + startingDate + ", status=" + status + "]";
	}

}