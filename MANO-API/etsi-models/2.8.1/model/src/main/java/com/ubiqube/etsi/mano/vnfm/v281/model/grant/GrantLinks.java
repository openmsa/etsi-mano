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
package com.ubiqube.etsi.mano.vnfm.v281.model.grant;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.em.v281.model.vnflcm.Link;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Links to resources related to this resource.
 */
@ApiModel(description = "Links to resources related to this resource. ")
@Validated

public class GrantLinks {
	@JsonProperty("self")
	private Link self = null;

	@JsonProperty("vnfLcmOpOcc")
	private Link vnfLcmOpOcc = null;

	@JsonProperty("vnfInstance")
	private Link vnfInstance = null;

	public GrantLinks self(final Link self) {
		this.self = self;
		return this;
	}

	/**
	 * URI of this resource.
	 *
	 * @return self
	 **/
	@ApiModelProperty(required = true, value = "URI of this resource. ")
	@NotNull

	@Valid

	public Link getSelf() {
		return self;
	}

	public void setSelf(final Link self) {
		this.self = self;
	}

	public GrantLinks vnfLcmOpOcc(final Link vnfLcmOpOcc) {
		this.vnfLcmOpOcc = vnfLcmOpOcc;
		return this;
	}

	/**
	 * Related VNF lifecycle management operation occurrence.
	 *
	 * @return vnfLcmOpOcc
	 **/
	@ApiModelProperty(required = true, value = "Related VNF lifecycle management operation occurrence. ")
	@NotNull

	@Valid

	public Link getVnfLcmOpOcc() {
		return vnfLcmOpOcc;
	}

	public void setVnfLcmOpOcc(final Link vnfLcmOpOcc) {
		this.vnfLcmOpOcc = vnfLcmOpOcc;
	}

	public GrantLinks vnfInstance(final Link vnfInstance) {
		this.vnfInstance = vnfInstance;
		return this;
	}

	/**
	 * Related VNF instance.
	 *
	 * @return vnfInstance
	 **/
	@ApiModelProperty(required = true, value = "Related VNF instance. ")
	@NotNull

	@Valid

	public Link getVnfInstance() {
		return vnfInstance;
	}

	public void setVnfInstance(final Link vnfInstance) {
		this.vnfInstance = vnfInstance;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final GrantLinks grantLinks = (GrantLinks) o;
		return Objects.equals(this.self, grantLinks.self) &&
				Objects.equals(this.vnfLcmOpOcc, grantLinks.vnfLcmOpOcc) &&
				Objects.equals(this.vnfInstance, grantLinks.vnfInstance);
	}

	@Override
	public int hashCode() {
		return Objects.hash(self, vnfLcmOpOcc, vnfInstance);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class GrantLinks {\n");

		sb.append("    self: ").append(toIndentedString(self)).append("\n");
		sb.append("    vnfLcmOpOcc: ").append(toIndentedString(vnfLcmOpOcc)).append("\n");
		sb.append("    vnfInstance: ").append(toIndentedString(vnfInstance)).append("\n");
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
