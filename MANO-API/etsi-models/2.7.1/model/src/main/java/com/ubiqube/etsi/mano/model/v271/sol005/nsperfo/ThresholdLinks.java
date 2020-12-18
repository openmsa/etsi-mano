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

package com.ubiqube.etsi.mano.model.v271.sol005.nsperfo;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.nfvo.v271.model.Link;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Links for this resource.
 */
@ApiModel(description = "Links for this resource. ")
@Validated
public class ThresholdLinks {
	@JsonProperty("self")
	private Link self = null;

	@JsonProperty("object")
	private Object object = null;

	public ThresholdLinks self(final Link self) {
		this.self = self;
		return this;
	}

	/**
	 * Get self
	 *
	 * @return self
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	@Valid
	public Link getSelf() {
		return self;
	}

	public void setSelf(final Link self) {
		this.self = self;
	}

	public ThresholdLinks object(final Object object) {
		this.object = object;
		return this;
	}

	/**
	 * Link to a resource representing the NS instance for which performance
	 * information is collected. Shall be present if the NS instance information is
	 * accessible as a resource.
	 *
	 * @return object
	 **/
	@ApiModelProperty(value = "Link to a resource representing the NS instance for which performance information is collected. Shall be present if the NS instance information is accessible as a resource. ")

	public Object getObject() {
		return object;
	}

	public void setObject(final Object object) {
		this.object = object;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final ThresholdLinks thresholdLinks = (ThresholdLinks) o;
		return Objects.equals(this.self, thresholdLinks.self) &&
				Objects.equals(this.object, thresholdLinks.object);
	}

	@Override
	public int hashCode() {
		return Objects.hash(self, object);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class ThresholdLinks {\n");

		sb.append("    self: ").append(toIndentedString(self)).append("\n");
		sb.append("    object: ").append(toIndentedString(object)).append("\n");
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
