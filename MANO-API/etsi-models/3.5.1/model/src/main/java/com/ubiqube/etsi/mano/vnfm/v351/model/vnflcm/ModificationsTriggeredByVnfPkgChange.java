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
package com.ubiqube.etsi.mano.vnfm.v351.model.vnflcm;

import java.util.Map;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * This type represents attribute modifications that were performed on an
 * \&quot;Individual VNF instance\&quot; resource when changing the current VNF
 * package. The attributes that can be included consist of those requested to be
 * modified explicitly in the \&quot;ChangeCurrentVnfPkgRequest\&quot; data
 * structure, and additional attributes of the \&quot;VnfInstance\&quot; data
 * structure that were modified implicitly during the operation. The
 * \&quot;ModificationsTriggeredByVnfPkgChange\&quot; data type shall comply
 * with the provisions defined in table 5.5.3.21-1. NOTE 1: This attribute
 * represents the delta (semantics as per IETF RFC 7396, JSON Merge Patch)
 * between the value of the attribute at the start of the \&quot;Change current
 * VNF package\&quot; operation and the value of the attribute at its
 * completion. NOTE 2: If present, this attribute (which depends on the value of
 * the \&quot;vnfdId\&quot; attribute) was modified implicitly during the
 * related operation and contains a copy of the value of the related attribute
 * from the VNFD in the VNF Package identified by the \&quot;vnfdId\&quot;
 * attribute.
 */
@Schema(description = "This type represents attribute modifications that were performed on an \"Individual VNF instance\" resource  when changing the current VNF package. The attributes that can be included consist of those requested to  be modified explicitly in the \"ChangeCurrentVnfPkgRequest\" data structure, and additional attributes of the  \"VnfInstance\" data structure that were modified implicitly during the operation. The \"ModificationsTriggeredByVnfPkgChange\" data type shall comply with the provisions defined in table 5.5.3.21-1. NOTE 1: This attribute represents the delta (semantics as per IETF RFC 7396, JSON Merge Patch) between the value          of the attribute at the start of the \"Change current VNF package\" operation and the value of the attribute          at its completion. NOTE 2: If present, this attribute (which depends on the value of the \"vnfdId\" attribute) was modified implicitly          during the related operation and contains a copy of the value of the related attribute from the VNFD in the          VNF Package identified by the \"vnfdId\" attribute. ")
@Validated

public class ModificationsTriggeredByVnfPkgChange {
	@JsonProperty("vnfConfigurableProperties")
	private Map<String, String> vnfConfigurableProperties = null;

	@JsonProperty("metadata")
	private Map<String, String> metadata = null;

	@JsonProperty("extensions")
	private Map<String, String> extensions = null;

	@JsonProperty("vnfdId")
	private String vnfdId = null;

	@JsonProperty("vnfProvider")
	private String vnfProvider = null;

	@JsonProperty("vnfProductName")
	private String vnfProductName = null;

	@JsonProperty("vnfSoftwareVersion")
	private String vnfSoftwareVersion = null;

	@JsonProperty("vnfdVersion")
	private String vnfdVersion = null;

	public ModificationsTriggeredByVnfPkgChange vnfConfigurableProperties(final Map<String, String> vnfConfigurableProperties) {
		this.vnfConfigurableProperties = vnfConfigurableProperties;
		return this;
	}

	/**
	 * Get vnfConfigurableProperties
	 *
	 * @return vnfConfigurableProperties
	 **/
	@Schema(description = "")

	@Valid
	public Map<String, String> getVnfConfigurableProperties() {
		return vnfConfigurableProperties;
	}

	public void setVnfConfigurableProperties(final Map<String, String> vnfConfigurableProperties) {
		this.vnfConfigurableProperties = vnfConfigurableProperties;
	}

	public ModificationsTriggeredByVnfPkgChange metadata(final Map<String, String> metadata) {
		this.metadata = metadata;
		return this;
	}

	/**
	 * Get metadata
	 *
	 * @return metadata
	 **/
	@Schema(description = "")

	@Valid
	public Map<String, String> getMetadata() {
		return metadata;
	}

	public void setMetadata(final Map<String, String> metadata) {
		this.metadata = metadata;
	}

	public ModificationsTriggeredByVnfPkgChange extensions(final Map<String, String> extensions) {
		this.extensions = extensions;
		return this;
	}

	/**
	 * Get extensions
	 *
	 * @return extensions
	 **/
	@Schema(description = "")

	@Valid
	public Map<String, String> getExtensions() {
		return extensions;
	}

	public void setExtensions(final Map<String, String> extensions) {
		this.extensions = extensions;
	}

	public ModificationsTriggeredByVnfPkgChange vnfdId(final String vnfdId) {
		this.vnfdId = vnfdId;
		return this;
	}

	/**
	 * Get vnfdId
	 *
	 * @return vnfdId
	 **/
	@Schema(description = "")

	public String getVnfdId() {
		return vnfdId;
	}

	public void setVnfdId(final String vnfdId) {
		this.vnfdId = vnfdId;
	}

	public ModificationsTriggeredByVnfPkgChange vnfProvider(final String vnfProvider) {
		this.vnfProvider = vnfProvider;
		return this;
	}

	/**
	 * If present, this attribute signals the new value of the \"vnfProvider\"
	 * attribute in \"VnfInstance\". See note 2.
	 *
	 * @return vnfProvider
	 **/
	@Schema(description = "If present, this attribute signals the new value of the \"vnfProvider\" attribute in \"VnfInstance\". See note 2. ")

	public String getVnfProvider() {
		return vnfProvider;
	}

	public void setVnfProvider(final String vnfProvider) {
		this.vnfProvider = vnfProvider;
	}

	public ModificationsTriggeredByVnfPkgChange vnfProductName(final String vnfProductName) {
		this.vnfProductName = vnfProductName;
		return this;
	}

	/**
	 * If present, this attribute signals the new value of the \"vnfProductName\"
	 * attribute in \"VnfInstance\". See note 2.
	 *
	 * @return vnfProductName
	 **/
	@Schema(description = "If present, this attribute signals the new value of the \"vnfProductName\" attribute in \"VnfInstance\". See note 2. ")

	public String getVnfProductName() {
		return vnfProductName;
	}

	public void setVnfProductName(final String vnfProductName) {
		this.vnfProductName = vnfProductName;
	}

	public ModificationsTriggeredByVnfPkgChange vnfSoftwareVersion(final String vnfSoftwareVersion) {
		this.vnfSoftwareVersion = vnfSoftwareVersion;
		return this;
	}

	/**
	 * Get vnfSoftwareVersion
	 *
	 * @return vnfSoftwareVersion
	 **/
	@Schema(description = "")

	public String getVnfSoftwareVersion() {
		return vnfSoftwareVersion;
	}

	public void setVnfSoftwareVersion(final String vnfSoftwareVersion) {
		this.vnfSoftwareVersion = vnfSoftwareVersion;
	}

	public ModificationsTriggeredByVnfPkgChange vnfdVersion(final String vnfdVersion) {
		this.vnfdVersion = vnfdVersion;
		return this;
	}

	/**
	 * Get vnfdVersion
	 *
	 * @return vnfdVersion
	 **/
	@Schema(description = "")

	public String getVnfdVersion() {
		return vnfdVersion;
	}

	public void setVnfdVersion(final String vnfdVersion) {
		this.vnfdVersion = vnfdVersion;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final ModificationsTriggeredByVnfPkgChange modificationsTriggeredByVnfPkgChange = (ModificationsTriggeredByVnfPkgChange) o;
		return Objects.equals(this.vnfConfigurableProperties, modificationsTriggeredByVnfPkgChange.vnfConfigurableProperties) &&
				Objects.equals(this.metadata, modificationsTriggeredByVnfPkgChange.metadata) &&
				Objects.equals(this.extensions, modificationsTriggeredByVnfPkgChange.extensions) &&
				Objects.equals(this.vnfdId, modificationsTriggeredByVnfPkgChange.vnfdId) &&
				Objects.equals(this.vnfProvider, modificationsTriggeredByVnfPkgChange.vnfProvider) &&
				Objects.equals(this.vnfProductName, modificationsTriggeredByVnfPkgChange.vnfProductName) &&
				Objects.equals(this.vnfSoftwareVersion, modificationsTriggeredByVnfPkgChange.vnfSoftwareVersion) &&
				Objects.equals(this.vnfdVersion, modificationsTriggeredByVnfPkgChange.vnfdVersion);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vnfConfigurableProperties, metadata, extensions, vnfdId, vnfProvider, vnfProductName, vnfSoftwareVersion, vnfdVersion);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class ModificationsTriggeredByVnfPkgChange {\n");

		sb.append("    vnfConfigurableProperties: ").append(toIndentedString(vnfConfigurableProperties)).append("\n");
		sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
		sb.append("    extensions: ").append(toIndentedString(extensions)).append("\n");
		sb.append("    vnfdId: ").append(toIndentedString(vnfdId)).append("\n");
		sb.append("    vnfProvider: ").append(toIndentedString(vnfProvider)).append("\n");
		sb.append("    vnfProductName: ").append(toIndentedString(vnfProductName)).append("\n");
		sb.append("    vnfSoftwareVersion: ").append(toIndentedString(vnfSoftwareVersion)).append("\n");
		sb.append("    vnfdVersion: ").append(toIndentedString(vnfdVersion)).append("\n");
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
