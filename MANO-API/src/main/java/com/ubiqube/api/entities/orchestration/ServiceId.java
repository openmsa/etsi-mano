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

public class ServiceId {
	private Long id;
	private String name;
	private String serviceExternalReference;
	private String state;

	@JsonProperty("id")
	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@JsonProperty("serviceExternalReference")
	public String getServiceExternalReference() {
		return serviceExternalReference;
	}

	public void setServiceExternalReference(final String serviceExternalReference) {
		this.serviceExternalReference = serviceExternalReference;
	}

	@JsonProperty("state")
	public String getState() {
		return state;
	}

	public void setState(final String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "ServiceId [id=" + id + ", name=" + name + ", serviceExternalReference=" + serviceExternalReference + ", state=" + state + "]";
	}

}
