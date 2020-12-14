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

package com.ubiqube.etsi.mano.model.v271.sol005.nslcm;

import java.util.Map;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type defines the additional parameters for the VNF instance to be
 * created associated with an NS instance. It shall comply with the provisions
 * defined in Table 6.5.3.22-1.
 */
@ApiModel(description = "This type defines the additional parameters for the VNF instance to be created associated with an NS instance. It shall comply with the provisions defined in Table 6.5.3.22-1. ")
@Validated
public class ParamsForVnf {
	@JsonProperty("vnfProfileId")
	private String vnfProfileId = null;

	@JsonProperty("vnfInstanceName")
	private String vnfInstanceName = null;

	@JsonProperty("vnfInstanceDescription")
	private String vnfInstanceDescription = null;

	@JsonProperty("vnfConfigurableProperties")
	private Map<String, Object> vnfConfigurableProperties = null;

	@JsonProperty("metadata")
	private Map<String, Object> metadata = null;

	@JsonProperty("extensions")
	private Map<String, Object> extensions = null;

	@JsonProperty("additionalParams")
	private Map<String, Object> additionalParams = null;

	public ParamsForVnf vnfProfileId(final String vnfProfileId) {
		this.vnfProfileId = vnfProfileId;
		return this;
	}

	/**
	 * Get vnfProfileId
	 *
	 * @return vnfProfileId
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	public String getVnfProfileId() {
		return vnfProfileId;
	}

	public void setVnfProfileId(final String vnfProfileId) {
		this.vnfProfileId = vnfProfileId;
	}

	public ParamsForVnf vnfInstanceName(final String vnfInstanceName) {
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

	public ParamsForVnf vnfInstanceDescription(final String vnfInstanceDescription) {
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

	public ParamsForVnf vnfConfigurableProperties(final Map<String, Object> vnfConfigurableProperties) {
		this.vnfConfigurableProperties = vnfConfigurableProperties;
		return this;
	}

	/**
	 * Get vnfConfigurableProperties
	 *
	 * @return vnfConfigurableProperties
	 **/
	@ApiModelProperty(value = "")

	@Valid
	public Map<String, Object> getVnfConfigurableProperties() {
		return vnfConfigurableProperties;
	}

	public void setVnfConfigurableProperties(final Map<String, Object> vnfConfigurableProperties) {
		this.vnfConfigurableProperties = vnfConfigurableProperties;
	}

	public ParamsForVnf metadata(final Map<String, Object> metadata) {
		this.metadata = metadata;
		return this;
	}

	/**
	 * Get metadata
	 *
	 * @return metadata
	 **/
	@ApiModelProperty(value = "")

	@Valid
	public Map<String, Object> getMetadata() {
		return metadata;
	}

	public void setMetadata(final Map<String, Object> metadata) {
		this.metadata = metadata;
	}

	public ParamsForVnf extensions(final Map<String, Object> extensions) {
		this.extensions = extensions;
		return this;
	}

	/**
	 * Get extensions
	 *
	 * @return extensions
	 **/
	@ApiModelProperty(value = "")

	@Valid
	public Map<String, Object> getExtensions() {
		return extensions;
	}

	public void setExtensions(final Map<String, Object> extensions) {
		this.extensions = extensions;
	}

	public ParamsForVnf additionalParams(final Map<String, Object> additionalParams) {
		this.additionalParams = additionalParams;
		return this;
	}

	/**
	 * Get additionalParams
	 *
	 * @return additionalParams
	 **/
	@ApiModelProperty(value = "")

	@Valid
	public Map<String, Object> getAdditionalParams() {
		return additionalParams;
	}

	public void setAdditionalParams(final Map<String, Object> additionalParams) {
		this.additionalParams = additionalParams;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final ParamsForVnf paramsForVnf = (ParamsForVnf) o;
		return Objects.equals(this.vnfProfileId, paramsForVnf.vnfProfileId) &&
				Objects.equals(this.vnfInstanceName, paramsForVnf.vnfInstanceName) &&
				Objects.equals(this.vnfInstanceDescription, paramsForVnf.vnfInstanceDescription) &&
				Objects.equals(this.vnfConfigurableProperties, paramsForVnf.vnfConfigurableProperties) &&
				Objects.equals(this.metadata, paramsForVnf.metadata) &&
				Objects.equals(this.extensions, paramsForVnf.extensions) &&
				Objects.equals(this.additionalParams, paramsForVnf.additionalParams);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vnfProfileId, vnfInstanceName, vnfInstanceDescription, vnfConfigurableProperties, metadata, extensions, additionalParams);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class ParamsForVnf {\n");

		sb.append("    vnfProfileId: ").append(toIndentedString(vnfProfileId)).append("\n");
		sb.append("    vnfInstanceName: ").append(toIndentedString(vnfInstanceName)).append("\n");
		sb.append("    vnfInstanceDescription: ").append(toIndentedString(vnfInstanceDescription)).append("\n");
		sb.append("    vnfConfigurableProperties: ").append(toIndentedString(vnfConfigurableProperties)).append("\n");
		sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
		sb.append("    extensions: ").append(toIndentedString(extensions)).append("\n");
		sb.append("    additionalParams: ").append(toIndentedString(additionalParams)).append("\n");
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
