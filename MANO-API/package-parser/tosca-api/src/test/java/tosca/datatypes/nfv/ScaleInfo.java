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

public class ScaleInfo {
	/**
	 * The scale level for a particular aspect
	 */
	@Valid
	@NotNull
	@JsonProperty("scale_level")
	@DecimalMin(value = "0", inclusive = true)
	private Integer scaleLevel;

	@NotNull
	public Integer getScaleLevel() {
		return this.scaleLevel;
	}

	public void setScaleLevel(@NotNull final Integer scaleLevel) {
		this.scaleLevel = scaleLevel;
	}

}
