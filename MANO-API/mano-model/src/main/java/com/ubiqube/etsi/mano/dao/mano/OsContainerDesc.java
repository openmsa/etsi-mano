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
package com.ubiqube.etsi.mano.dao.mano;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class OsContainerDesc {
	/**
	 * Unique identifier of this OsContainerDesc in the VNFD.
	 */
	private UUID osContainerDescId;

	/*
	 * Number of CPU resources requested for the container (e.g. in milli-CPU-s).
	 */
	private Integer requestedCpuResources;

	/**
	 * Amount of memory resources requested ces for the container (e.g. in MB).
	 */
	private Long requestedMemoryResour;

	/**
	 * Size of ephemeral storage resources ageResources requested for the container (e.g. in GB).
	 */
	private Long requestedEphemeralStor;

	/**
	 * An array of key-value pairs of extended sts resources required by the container. See note.
	 */
	private Map<String, String> extendedResourceReque;

	/**
	 * Number of CPU resources the container can maximally use (e.g. in milli-CPU).
	 */
	private Long cpuResourceLimit;

	/**
	 * Amount of memory resources the container can maximum use (e.g. in MB).
	 */
	private Long memoryResourceLimit;

	/**
	 * Size of ephemeral storage resources the rceLimit container can maximum use (e.g. in GB).
	 */
	private Long ephemeralStorageResou;

	/**
	 * Describes the software image realizing (Reference to this OS container. SwImageDesc)
	 */
	private String swImageDesc;

	/**
	 * Contains a string or a URL to a file contained in the VNF package used to customize a container resource at boot time. The bootData may contain variable parts that are replaced by deployment specific values before being sent.
	 */
	// bootData

	/**
	 * Links to virtualStorageDesc-s of the Vdu. (Reference to The storages represented by the linked VirtualStorageDe VirtualStorageDesc-s are attached to the sc) OS Container as volumes. Shall be present in case the OS container requires storage resources.
	 */
	private Set<String> virtualStorageDesc;

	/**
	 * MonitoringParam Specifies the virtualised resource related eter performance metrics on the OsContainerDesc level to be tracked by the VNFM. MonitoringParameter is defined in clause 7.1.11.3.
	 */
	Set<MonitoringParams> monitoringParameters;

}
