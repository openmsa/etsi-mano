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
package com.ubiqube.etsi.mano.em.v271.model.vnflcm;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.em.v271.model.vnfconfig.KeyValuePairs;

import io.swagger.annotations.ApiModelProperty;

/**
 * CreateVnfRequest
 */
@Validated

public class CreateVnfRequest {
	@JsonProperty("vnfdId")
	private String vnfdId = null;

	@JsonProperty("vnfInstanceName")
	private String vnfInstanceName = null;

	@JsonProperty("vnfInstanceDescription")
	private String vnfInstanceDescription = null;

	@JsonProperty("metadata")
	private KeyValuePairs metadata = null;

	public CreateVnfRequest vnfdId(final String vnfdId) {
		this.vnfdId = vnfdId;
		return this;
	}

	/**
	 * Identifier that identifies the VNFD which defines the VNF instance to be
	 * created.
	 *
	 * @return vnfdId
	 **/
	@ApiModelProperty(required = true, value = "Identifier that identifies the VNFD which defines the VNF instance to be created. ")
	@NotNull

	public String getVnfdId() {
		return vnfdId;
	}

	public void setVnfdId(final String vnfdId) {
		this.vnfdId = vnfdId;
	}

	public CreateVnfRequest vnfInstanceName(final String vnfInstanceName) {
		this.vnfInstanceName = vnfInstanceName;
		return this;
	}

	/**
	 * Human-readable name of the VNF instance to be created.
	 *
	 * @return vnfInstanceName
	 **/
	@ApiModelProperty(value = "Human-readable name of the VNF instance to be created. ")

	public String getVnfInstanceName() {
		return vnfInstanceName;
	}

	public void setVnfInstanceName(final String vnfInstanceName) {
		this.vnfInstanceName = vnfInstanceName;
	}

	public CreateVnfRequest vnfInstanceDescription(final String vnfInstanceDescription) {
		this.vnfInstanceDescription = vnfInstanceDescription;
		return this;
	}

	/**
	 * Human-readable description of the VNF instance to be created.
	 *
	 * @return vnfInstanceDescription
	 **/
	@ApiModelProperty(value = "Human-readable description of the VNF instance to be created. ")

	public String getVnfInstanceDescription() {
		return vnfInstanceDescription;
	}

	public void setVnfInstanceDescription(final String vnfInstanceDescription) {
		this.vnfInstanceDescription = vnfInstanceDescription;
	}

	public CreateVnfRequest metadata(final KeyValuePairs metadata) {
		this.metadata = metadata;
		return this;
	}

	/**
	 * If present, this attribute provides values for the \"metadata\" attribute in
	 * \"VnfInstance\", as defined in clause 5.5.2.2. If a “metadata” entry in this
	 * CreateVnfRequest data structure has a corresponding default value declared in
	 * the VNFD, the value in the “metadata” entry in the CreateVnfRequest structure
	 * takes precedence.
	 *
	 * @return metadata
	 **/
	@ApiModelProperty(value = "If present, this attribute provides values for the \"metadata\" attribute in \"VnfInstance\", as defined in clause 5.5.2.2. If a “metadata” entry in this CreateVnfRequest data structure has a corresponding default value declared in the VNFD, the value in the “metadata” entry in the CreateVnfRequest structure takes precedence. ")

	@Valid

	public KeyValuePairs getMetadata() {
		return metadata;
	}

	public void setMetadata(final KeyValuePairs metadata) {
		this.metadata = metadata;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final CreateVnfRequest createVnfRequest = (CreateVnfRequest) o;
		return Objects.equals(this.vnfdId, createVnfRequest.vnfdId) &&
				Objects.equals(this.vnfInstanceName, createVnfRequest.vnfInstanceName) &&
				Objects.equals(this.vnfInstanceDescription, createVnfRequest.vnfInstanceDescription) &&
				Objects.equals(this.metadata, createVnfRequest.metadata);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vnfdId, vnfInstanceName, vnfInstanceDescription, metadata);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class CreateVnfRequest {\n");

		sb.append("    vnfdId: ").append(toIndentedString(vnfdId)).append("\n");
		sb.append("    vnfInstanceName: ").append(toIndentedString(vnfInstanceName)).append("\n");
		sb.append("    vnfInstanceDescription: ").append(toIndentedString(vnfInstanceDescription)).append("\n");
		sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
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
