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

package com.ubiqube.etsi.mano.nfvo.v271.model.nslcm;

import java.util.Map;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents the information that is requested to be modified for a
 * VNF instance. The information to be modified shall comply with the associated
 * NSD. EXAMPLE. The vnfPkgId attribute value for a particular VNF instance can
 * only be updated with a value that matches the identifier value of a VNF
 * package whose vnfdId is present in the associated profile of the NSD.
 */
@ApiModel(description = "This type represents the information that is requested to be modified for a VNF instance. The information to be modified shall comply with the associated NSD. EXAMPLE. The vnfPkgId attribute value for a particular VNF instance can only be updated with a value that matches the identifier value of a VNF package whose vnfdId is present in the associated profile of the NSD. ")
@Validated
public class ModifyVnfInfoData {
	@JsonProperty("vnfInstanceId")
	private String vnfInstanceId = null;

	@JsonProperty("vnfInstanceName")
	private String vnfInstanceName = null;

	@JsonProperty("vnfInstanceDescription")
	private String vnfInstanceDescription = null;

	@JsonProperty("vnfdId")
	private String vnfdId = null;

	@JsonProperty("vnfConfigurableProperties")
	private Map<String, Object> vnfConfigurableProperties = null;

	@JsonProperty("metadata")
	private Map<String, Object> metadata = null;

	@JsonProperty("extensions")
	private Map<String, Object> extensions = null;

	public ModifyVnfInfoData vnfInstanceId(final String vnfInstanceId) {
		this.vnfInstanceId = vnfInstanceId;
		return this;
	}

	/**
	 * Get vnfInstanceId
	 *
	 * @return vnfInstanceId
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	public String getVnfInstanceId() {
		return vnfInstanceId;
	}

	public void setVnfInstanceId(final String vnfInstanceId) {
		this.vnfInstanceId = vnfInstanceId;
	}

	public ModifyVnfInfoData vnfInstanceName(final String vnfInstanceName) {
		this.vnfInstanceName = vnfInstanceName;
		return this;
	}

	/**
	 * New value of the \"vnfInstanceName\" attribute in \"VnfInstance\", or
	 * \"null\" to remove the attribute.
	 *
	 * @return vnfInstanceName
	 **/
	@ApiModelProperty(value = "New value of the \"vnfInstanceName\" attribute in \"VnfInstance\", or \"null\" to remove the attribute. ")

	public String getVnfInstanceName() {
		return vnfInstanceName;
	}

	public void setVnfInstanceName(final String vnfInstanceName) {
		this.vnfInstanceName = vnfInstanceName;
	}

	public ModifyVnfInfoData vnfInstanceDescription(final String vnfInstanceDescription) {
		this.vnfInstanceDescription = vnfInstanceDescription;
		return this;
	}

	/**
	 * New value of the \"vnfInstanceDescription\" attribute in \"VnfInstance\", or
	 * \"null\" to remove the attribute.
	 *
	 * @return vnfInstanceDescription
	 **/
	@ApiModelProperty(value = "New value of the \"vnfInstanceDescription\" attribute in \"VnfInstance\", or \"null\" to remove the attribute. ")

	public String getVnfInstanceDescription() {
		return vnfInstanceDescription;
	}

	public void setVnfInstanceDescription(final String vnfInstanceDescription) {
		this.vnfInstanceDescription = vnfInstanceDescription;
	}

	public ModifyVnfInfoData vnfdId(final String vnfdId) {
		this.vnfdId = vnfdId;
		return this;
	}

	/**
	 * Get vnfdId
	 *
	 * @return vnfdId
	 **/
	@ApiModelProperty(value = "")

	public String getVnfdId() {
		return vnfdId;
	}

	public void setVnfdId(final String vnfdId) {
		this.vnfdId = vnfdId;
	}

	public ModifyVnfInfoData vnfConfigurableProperties(final Map<String, Object> vnfConfigurableProperties) {
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

	public ModifyVnfInfoData metadata(final Map<String, Object> metadata) {
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

	public ModifyVnfInfoData extensions(final Map<String, Object> extensions) {
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

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final ModifyVnfInfoData modifyVnfInfoData = (ModifyVnfInfoData) o;
		return Objects.equals(this.vnfInstanceId, modifyVnfInfoData.vnfInstanceId) &&
				Objects.equals(this.vnfInstanceName, modifyVnfInfoData.vnfInstanceName) &&
				Objects.equals(this.vnfInstanceDescription, modifyVnfInfoData.vnfInstanceDescription) &&
				Objects.equals(this.vnfdId, modifyVnfInfoData.vnfdId) &&
				Objects.equals(this.vnfConfigurableProperties, modifyVnfInfoData.vnfConfigurableProperties) &&
				Objects.equals(this.metadata, modifyVnfInfoData.metadata) &&
				Objects.equals(this.extensions, modifyVnfInfoData.extensions);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vnfInstanceId, vnfInstanceName, vnfInstanceDescription, vnfdId, vnfConfigurableProperties, metadata, extensions);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class ModifyVnfInfoData {\n");

		sb.append("    vnfInstanceId: ").append(toIndentedString(vnfInstanceId)).append("\n");
		sb.append("    vnfInstanceName: ").append(toIndentedString(vnfInstanceName)).append("\n");
		sb.append("    vnfInstanceDescription: ").append(toIndentedString(vnfInstanceDescription)).append("\n");
		sb.append("    vnfdId: ").append(toIndentedString(vnfdId)).append("\n");
		sb.append("    vnfConfigurableProperties: ").append(toIndentedString(vnfConfigurableProperties)).append("\n");
		sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
		sb.append("    extensions: ").append(toIndentedString(extensions)).append("\n");
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
