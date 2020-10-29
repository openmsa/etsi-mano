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

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProcessId {

	private Long id;
	private Long lastExecNumber;
	private String name;
	private String submissionType;

	@JsonProperty("id")
	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	@JsonProperty("lastExecNumber")
	public Long getLastExecNumber() {
		return lastExecNumber;
	}

	public void setLastExecNumber(final Long lastExecNumber) {
		this.lastExecNumber = lastExecNumber;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@JsonProperty("submissionType")
	public String getSubmissionType() {
		return submissionType;
	}

	public void setSubmissionType(final String submissionType) {
		this.submissionType = submissionType;
	}

	@Override
	public String toString() {
		return "ProcessId [id=" + id + ", lastExecNumber=" + lastExecNumber + ", name=" + name + ", submissionType=" + submissionType + "]";
	}
}
