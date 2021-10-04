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
package com.ubiqube.etsi.mano.em.v261.model.vnflcm;

import java.util.Map;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;


/**
 * This type represents modifications of an entry in an array of \&quot;VnfcInfo\&quot; objects. It shall comply with the provisions defined in table 5.5.3.24-1.
 */
@Schema(description = "This type represents modifications of an entry in an array of \"VnfcInfo\" objects. It shall comply with the provisions defined in table 5.5.3.24-1. ")
@Validated

public class VnfcInfoModifications {
	@JsonProperty("id")
	private String id = null;

	@JsonProperty("vnfcConfigurableProperties")
	private Map<String, Object> vnfcConfigurableProperties = null;

	public VnfcInfoModifications id(final String id) {
		this.id = id;
		return this;
	}

	/**
	 * Identifier of the VNFC instance of which the information is to be modified.
	 *
	 * @return id
	 **/
	@Schema(required = true, description = "Identifier of the VNFC instance of which the information is to be modified. ")
	@NotNull

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public VnfcInfoModifications vnfcConfigurableProperties(final Map<String, Object> vnfcConfigurableProperties) {
		this.vnfcConfigurableProperties = vnfcConfigurableProperties;
		return this;
	}

	/**
	 * Changes of the configurable properties of the VNFC instance.
	 *
	 * @return vnfcConfigurableProperties
	 **/
	@Schema(required = true, description = "Changes of the configurable properties of the VNFC instance. ")
	@NotNull

	@Valid

	public Map<String, Object> getVnfcConfigurableProperties() {
		return vnfcConfigurableProperties;
	}

	public void setVnfcConfigurableProperties(final Map<String, Object> vnfcConfigurableProperties) {
		this.vnfcConfigurableProperties = vnfcConfigurableProperties;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final VnfcInfoModifications vnfcInfoModifications = (VnfcInfoModifications) o;
		return Objects.equals(this.id, vnfcInfoModifications.id) &&
				Objects.equals(this.vnfcConfigurableProperties, vnfcInfoModifications.vnfcConfigurableProperties);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, vnfcConfigurableProperties);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfcInfoModifications {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    vnfcConfigurableProperties: ").append(toIndentedString(vnfcConfigurableProperties)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces (except the first line).
	 */
	private String toIndentedString(final java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
