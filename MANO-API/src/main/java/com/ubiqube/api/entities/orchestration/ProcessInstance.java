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

public class ProcessInstance {

	private ProcessId processId;
	private ServiceId serviceId;
	private Status status;

	@JsonProperty("processId")
	public ProcessId getProcessId() {
		return processId;
	}

	public void setProcessId(final ProcessId processId) {
		this.processId = processId;
	}

	@JsonProperty("serviceId")
	public ServiceId getServiceId() {
		return serviceId;
	}

	public void setServiceId(final ServiceId serviceId) {
		this.serviceId = serviceId;
	}

	@JsonProperty("status")
	public Status getStatus() {
		return status;
	}

	public void setStatus(final Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ProcessInstance [processId=" + processId + ", serviceId=" + serviceId + ", status=" + status + "]";
	}

}
