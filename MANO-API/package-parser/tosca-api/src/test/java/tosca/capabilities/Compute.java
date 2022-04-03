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
package tosca.capabilities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.parser.tosca.api.ToscaInernalBase;
import com.ubiqube.parser.tosca.scalar.Frequency;
import com.ubiqube.parser.tosca.scalar.Size;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;

public class Compute extends ToscaInernalBase {
	@Valid
	@JsonProperty("num_cpus")
	@DecimalMin(value = "1", inclusive = true)
	private Integer numCpus;

	@Valid
	@JsonProperty("name")
	private String name;

	@Valid
	@JsonProperty("disk_size")
	private Size diskSize;

	@Valid
	@JsonProperty("cpu_frequency")
	private Frequency cpuFrequency;

	@Valid
	@JsonProperty("mem_size")
	private Size memSize;

	public Integer getNumCpus() {
		return this.numCpus;
	}

	public void setNumCpus(final Integer numCpus) {
		this.numCpus = numCpus;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Size getDiskSize() {
		return this.diskSize;
	}

	public void setDiskSize(final Size diskSize) {
		this.diskSize = diskSize;
	}

	public Frequency getCpuFrequency() {
		return this.cpuFrequency;
	}

	public void setCpuFrequency(final Frequency cpuFrequency) {
		this.cpuFrequency = cpuFrequency;
	}

	public Size getMemSize() {
		return this.memSize;
	}

	public void setMemSize(final Size memSize) {
		this.memSize = memSize;
	}

}
