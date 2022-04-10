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
import jakarta.validation.constraints.NotNull;
import tosca.datatypes.Root;

/**
 * represents information that affect the invocation of the ScaleVnfToLevel
 * operation
 */
public class VnfScaleToLevelOperationConfiguration extends Root {
	/**
	 * Signals whether scaling according to the parameter "scaleInfo" is supported
	 * by this VNF
	 */
	@Valid
	@NotNull
	@JsonProperty("arbitrary_target_levels_supported")
	private Boolean arbitraryTargetLevelsSupported;

	@NotNull
	public Boolean getArbitraryTargetLevelsSupported() {
		return this.arbitraryTargetLevelsSupported;
	}

	public void setArbitraryTargetLevelsSupported(
			@NotNull final Boolean arbitraryTargetLevelsSupported) {
		this.arbitraryTargetLevelsSupported = arbitraryTargetLevelsSupported;
	}
}
