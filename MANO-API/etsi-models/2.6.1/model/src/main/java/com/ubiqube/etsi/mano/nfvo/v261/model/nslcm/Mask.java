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

package com.ubiqube.etsi.mano.nfvo.v261.model.nslcm;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * The Mask data type identifies the value to be matched for a sequence of bits
 * at a particular location in a frame. It shall comply with the provisions
 * defined in Table 6.5.3.41-1.
 */
@ApiModel(description = "The Mask data type identifies the value to be matched for a sequence of bits at a particular location in a frame. It shall comply with the provisions defined in Table 6.5.3.41-1. ")
@Validated


public class Mask {
	@JsonProperty("startingPoint")
	private Integer startingPoint = null;

	@JsonProperty("length")
	private Integer length = null;

	@JsonProperty("value")
	private String value = null;

	public Mask startingPoint(final Integer startingPoint) {
		this.startingPoint = startingPoint;
		return this;
	}

	/**
	 * Indicates the offset between the last bit of the source mac address and the
	 * first bit of the sequence of bits to be matched.
	 * 
	 * @return startingPoint
	 **/
	@ApiModelProperty(required = true, value = "Indicates the offset between the last bit of the source mac address and the first bit of the sequence of bits to be matched. ")
	@NotNull

	public Integer getStartingPoint() {
		return startingPoint;
	}

	public void setStartingPoint(final Integer startingPoint) {
		this.startingPoint = startingPoint;
	}

	public Mask length(final Integer length) {
		this.length = length;
		return this;
	}

	/**
	 * Indicates the number of bits to be matched.
	 * 
	 * @return length
	 **/
	@ApiModelProperty(required = true, value = "Indicates the number of bits to be matched. ")
	@NotNull

	public Integer getLength() {
		return length;
	}

	public void setLength(final Integer length) {
		this.length = length;
	}

	public Mask value(final String value) {
		this.value = value;
		return this;
	}

	/**
	 * Provide the sequence of bit values to be matched.
	 * 
	 * @return value
	 **/
	@ApiModelProperty(required = true, value = "Provide the sequence of bit values to be matched. ")
	@NotNull

	public String getValue() {
		return value;
	}

	public void setValue(final String value) {
		this.value = value;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final Mask mask = (Mask) o;
		return Objects.equals(this.startingPoint, mask.startingPoint) &&
				Objects.equals(this.length, mask.length) &&
				Objects.equals(this.value, mask.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(startingPoint, length, value);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class Mask {\n");

		sb.append("    startingPoint: ").append(toIndentedString(startingPoint)).append("\n");
		sb.append("    length: ").append(toIndentedString(length)).append("\n");
		sb.append("    value: ").append(toIndentedString(value)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(final java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
