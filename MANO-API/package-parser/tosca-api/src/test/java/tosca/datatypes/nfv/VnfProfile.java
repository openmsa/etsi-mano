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
package tosca.datatypes.nfv;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class VnfProfile {
	/**
	 * Identifier of the instantiation level of the VNF DF to be used for
	 * instantiation. If not present, the default instantiation level as declared in
	 * the VNFD shall be used.
	 */
	@Valid
	@JsonProperty("instantiation_level")
	private String instantiationLevel;

	/**
	 * Specifies the service availability level for the VNF instance created from
	 * this profile.
	 */
	@Valid
	@JsonProperty("service_availability_level")
	@DecimalMin(value = "1", inclusive = true)
	private Integer serviceAvailabilityLevel;

	/**
	 * Minimum number of instances of the VNF based on this VNFD that is permitted
	 * to exist for this VnfProfile.
	 */
	@Valid
	@NotNull
	@JsonProperty("min_number_of_instances")
	@DecimalMin(value = "0", inclusive = true)
	private Integer minNumberOfInstances;

	/**
	 * Maximum number of instances of the VNF based on this VNFD that is permitted
	 * to exist for this VnfProfile.
	 */
	@Valid
	@NotNull
	@JsonProperty("max_number_of_instances")
	@DecimalMin(value = "0", inclusive = true)
	private Integer maxNumberOfInstances;

	public String getInstantiationLevel() {
		return this.instantiationLevel;
	}

	public void setInstantiationLevel(final String instantiationLevel) {
		this.instantiationLevel = instantiationLevel;
	}

	public Integer getServiceAvailabilityLevel() {
		return this.serviceAvailabilityLevel;
	}

	public void setServiceAvailabilityLevel(final Integer serviceAvailabilityLevel) {
		this.serviceAvailabilityLevel = serviceAvailabilityLevel;
	}

	@NotNull
	public Integer getMinNumberOfInstances() {
		return this.minNumberOfInstances;
	}

	public void setMinNumberOfInstances(@NotNull final Integer minNumberOfInstances) {
		this.minNumberOfInstances = minNumberOfInstances;
	}

	@NotNull
	public Integer getMaxNumberOfInstances() {
		return this.maxNumberOfInstances;
	}

	public void setMaxNumberOfInstances(@NotNull final Integer maxNumberOfInstances) {
		this.maxNumberOfInstances = maxNumberOfInstances;
	}

}
