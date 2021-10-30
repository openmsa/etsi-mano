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
package com.ubiqube.etsi.mano.vnfm.v351.model.vnfind;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * This type represents a VNF indicator value. It shall comply with the
 * provisions defined in table 8.5.2.2-1. NOTE: ETSI GS NFV-SOL 001 specifies
 * the structure and format of the VNFD based on TOSCA specifications.
 */
@Schema(description = "This type represents a VNF indicator value. It shall comply with the provisions defined in table 8.5.2.2-1. NOTE: ETSI GS NFV-SOL 001 specifies the structure and format of the VNFD based on TOSCA specifications. ")
@Validated

public class VnfIndicator {
	@JsonProperty("id")
	private String id = null;

	@JsonProperty("name")
	private String name = null;

	@JsonProperty("value")
	private Object value = null;

	@JsonProperty("vnfInstanceId")
	private String vnfInstanceId = null;

	@JsonProperty("_links")
	private VnfIndicatorLinks _links = null;

	public VnfIndicator id(final String id) {
		this.id = id;
		return this;
	}

	/**
	 * Get id
	 *
	 * @return id
	 **/
	@Schema(required = true, description = "")
	@NotNull

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public VnfIndicator name(final String name) {
		this.name = name;
		return this;
	}

	/**
	 * Human readable name of the indicator. Shall be present if defined in the
	 * VNFD.
	 *
	 * @return name
	 **/
	@Schema(description = "Human readable name of the indicator. Shall be present if defined in the VNFD. ")

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public VnfIndicator value(final Object value) {
		this.value = value;
		return this;
	}

	/**
	 * Provides the value of the indicator. The value format is defined in the VNFD.
	 * See note.
	 *
	 * @return value
	 **/
	@Schema(required = true, description = "Provides the value of the indicator. The value format is defined in the VNFD. See note. ")
	@NotNull

	public Object getValue() {
		return value;
	}

	public void setValue(final Object value) {
		this.value = value;
	}

	public VnfIndicator vnfInstanceId(final String vnfInstanceId) {
		this.vnfInstanceId = vnfInstanceId;
		return this;
	}

	/**
	 * Get vnfInstanceId
	 *
	 * @return vnfInstanceId
	 **/
	@Schema(required = true, description = "")
	@NotNull

	public String getVnfInstanceId() {
		return vnfInstanceId;
	}

	public void setVnfInstanceId(final String vnfInstanceId) {
		this.vnfInstanceId = vnfInstanceId;
	}

	public VnfIndicator _links(final VnfIndicatorLinks _links) {
		this._links = _links;
		return this;
	}

	/**
	 * Get _links
	 *
	 * @return _links
	 **/
	@Schema(required = true, description = "")
	@NotNull

	@Valid
	public VnfIndicatorLinks getLinks() {
		return _links;
	}

	public void setLinks(final VnfIndicatorLinks _links) {
		this._links = _links;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final VnfIndicator vnfIndicator = (VnfIndicator) o;
		return Objects.equals(this.id, vnfIndicator.id) &&
				Objects.equals(this.name, vnfIndicator.name) &&
				Objects.equals(this.value, vnfIndicator.value) &&
				Objects.equals(this.vnfInstanceId, vnfIndicator.vnfInstanceId) &&
				Objects.equals(this._links, vnfIndicator._links);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, value, vnfInstanceId, _links);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfIndicator {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    value: ").append(toIndentedString(value)).append("\n");
		sb.append("    vnfInstanceId: ").append(toIndentedString(vnfInstanceId)).append("\n");
		sb.append("    _links: ").append(toIndentedString(_links)).append("\n");
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
