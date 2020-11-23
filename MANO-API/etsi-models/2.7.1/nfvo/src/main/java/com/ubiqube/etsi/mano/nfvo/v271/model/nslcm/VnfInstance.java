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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents a VNF instance. Clause B.3.2 of ETSI GS NFV-SOL 003 [4]
 * provides examples illustrating the relationship among the different run-time
 * information elements (CP, VL and link ports) used to represent the
 * connectivity of a VNF.
 */
@ApiModel(description = "This type represents a VNF instance. Clause B.3.2 of ETSI GS NFV-SOL 003 [4] provides examples illustrating the relationship among the different run-time information elements (CP, VL and link ports) used to represent the connectivity of a VNF. ")
@Validated
public class VnfInstance {
	@JsonProperty("id")
	private String id = null;

	@JsonProperty("vnfInstanceName")
	private String vnfInstanceName = null;

	@JsonProperty("vnfInstanceDescription")
	private String vnfInstanceDescription = null;

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

	@JsonProperty("vnfPkgId")
	private String vnfPkgId = null;

	@JsonProperty("vnfConfigurableProperties")
	private Map<String, Object> vnfConfigurableProperties = null;

	@JsonProperty("vimId")
	private String vimId = null;

	/**
	 * The instantiation state of the VNF.
	 */
	public enum InstantiationStateEnum {
		NOT_INSTANTIATED("NOT_INSTANTIATED"),

		INSTANTIATED("INSTANTIATED");

		private final String value;

		InstantiationStateEnum(final String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static InstantiationStateEnum fromValue(final String text) {
			for (final InstantiationStateEnum b : InstantiationStateEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	@JsonProperty("instantiationState")
	private InstantiationStateEnum instantiationState = null;

	@JsonProperty("instantiatedVnfInfo")
	private VnfInstanceInstantiatedVnfInfo instantiatedVnfInfo = null;

	@JsonProperty("metadata")
	private Map<String, Object> metadata = null;

	@JsonProperty("extensions")
	private Map<String, Object> extensions = null;

	public VnfInstance id(final String id) {
		this.id = id;
		return this;
	}

	/**
	 * Get id
	 *
	 * @return id
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public VnfInstance vnfInstanceName(final String vnfInstanceName) {
		this.vnfInstanceName = vnfInstanceName;
		return this;
	}

	/**
	 * Name of the VNF instance. This attribute can be modified with the PATCH
	 * method.
	 *
	 * @return vnfInstanceName
	 **/
	@ApiModelProperty(value = "Name of the VNF instance. This attribute can be modified with the PATCH method. ")

	public String getVnfInstanceName() {
		return vnfInstanceName;
	}

	public void setVnfInstanceName(final String vnfInstanceName) {
		this.vnfInstanceName = vnfInstanceName;
	}

	public VnfInstance vnfInstanceDescription(final String vnfInstanceDescription) {
		this.vnfInstanceDescription = vnfInstanceDescription;
		return this;
	}

	/**
	 * Human-readable description of the VNF instance. This attribute can be
	 * modified with the PATCH method.
	 *
	 * @return vnfInstanceDescription
	 **/
	@ApiModelProperty(value = "Human-readable description of the VNF instance. This attribute can be modified with the PATCH method. ")

	public String getVnfInstanceDescription() {
		return vnfInstanceDescription;
	}

	public void setVnfInstanceDescription(final String vnfInstanceDescription) {
		this.vnfInstanceDescription = vnfInstanceDescription;
	}

	public VnfInstance vnfdId(final String vnfdId) {
		this.vnfdId = vnfdId;
		return this;
	}

	/**
	 * Get vnfdId
	 *
	 * @return vnfdId
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	public String getVnfdId() {
		return vnfdId;
	}

	public void setVnfdId(final String vnfdId) {
		this.vnfdId = vnfdId;
	}

	public VnfInstance vnfProvider(final String vnfProvider) {
		this.vnfProvider = vnfProvider;
		return this;
	}

	/**
	 * Provider of the VNF and the VNFD. The value is copied from the VNFD.
	 *
	 * @return vnfProvider
	 **/
	@ApiModelProperty(required = true, value = "Provider of the VNF and the VNFD. The value is copied from the VNFD. ")
	@NotNull

	public String getVnfProvider() {
		return vnfProvider;
	}

	public void setVnfProvider(final String vnfProvider) {
		this.vnfProvider = vnfProvider;
	}

	public VnfInstance vnfProductName(final String vnfProductName) {
		this.vnfProductName = vnfProductName;
		return this;
	}

	/**
	 * Name to identify the VNF Product. The value is copied from the VNFD.
	 *
	 * @return vnfProductName
	 **/
	@ApiModelProperty(required = true, value = "Name to identify the VNF Product. The value is copied from the VNFD. ")
	@NotNull

	public String getVnfProductName() {
		return vnfProductName;
	}

	public void setVnfProductName(final String vnfProductName) {
		this.vnfProductName = vnfProductName;
	}

	public VnfInstance vnfSoftwareVersion(final String vnfSoftwareVersion) {
		this.vnfSoftwareVersion = vnfSoftwareVersion;
		return this;
	}

	/**
	 * Get vnfSoftwareVersion
	 *
	 * @return vnfSoftwareVersion
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	public String getVnfSoftwareVersion() {
		return vnfSoftwareVersion;
	}

	public void setVnfSoftwareVersion(final String vnfSoftwareVersion) {
		this.vnfSoftwareVersion = vnfSoftwareVersion;
	}

	public VnfInstance vnfdVersion(final String vnfdVersion) {
		this.vnfdVersion = vnfdVersion;
		return this;
	}

	/**
	 * Get vnfdVersion
	 *
	 * @return vnfdVersion
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	public String getVnfdVersion() {
		return vnfdVersion;
	}

	public void setVnfdVersion(final String vnfdVersion) {
		this.vnfdVersion = vnfdVersion;
	}

	public VnfInstance vnfPkgId(final String vnfPkgId) {
		this.vnfPkgId = vnfPkgId;
		return this;
	}

	/**
	 * Get vnfPkgId
	 *
	 * @return vnfPkgId
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	public String getVnfPkgId() {
		return vnfPkgId;
	}

	public void setVnfPkgId(final String vnfPkgId) {
		this.vnfPkgId = vnfPkgId;
	}

	public VnfInstance vnfConfigurableProperties(final Map<String, Object> vnfConfigurableProperties) {
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

	public VnfInstance vimId(final String vimId) {
		this.vimId = vimId;
		return this;
	}

	/**
	 * Get vimId
	 *
	 * @return vimId
	 **/
	@ApiModelProperty(value = "")

	public String getVimId() {
		return vimId;
	}

	public void setVimId(final String vimId) {
		this.vimId = vimId;
	}

	public VnfInstance instantiationState(final InstantiationStateEnum instantiationState) {
		this.instantiationState = instantiationState;
		return this;
	}

	/**
	 * The instantiation state of the VNF.
	 *
	 * @return instantiationState
	 **/
	@ApiModelProperty(required = true, value = "The instantiation state of the VNF. ")
	@NotNull

	public InstantiationStateEnum getInstantiationState() {
		return instantiationState;
	}

	public void setInstantiationState(final InstantiationStateEnum instantiationState) {
		this.instantiationState = instantiationState;
	}

	public VnfInstance instantiatedVnfInfo(final VnfInstanceInstantiatedVnfInfo instantiatedVnfInfo) {
		this.instantiatedVnfInfo = instantiatedVnfInfo;
		return this;
	}

	/**
	 * Get instantiatedVnfInfo
	 *
	 * @return instantiatedVnfInfo
	 **/
	@ApiModelProperty(value = "")

	@Valid
	public VnfInstanceInstantiatedVnfInfo getInstantiatedVnfInfo() {
		return instantiatedVnfInfo;
	}

	public void setInstantiatedVnfInfo(final VnfInstanceInstantiatedVnfInfo instantiatedVnfInfo) {
		this.instantiatedVnfInfo = instantiatedVnfInfo;
	}

	public VnfInstance metadata(final Map<String, Object> metadata) {
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

	public VnfInstance extensions(final Map<String, Object> extensions) {
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
		final VnfInstance vnfInstance = (VnfInstance) o;
		return Objects.equals(this.id, vnfInstance.id) &&
				Objects.equals(this.vnfInstanceName, vnfInstance.vnfInstanceName) &&
				Objects.equals(this.vnfInstanceDescription, vnfInstance.vnfInstanceDescription) &&
				Objects.equals(this.vnfdId, vnfInstance.vnfdId) &&
				Objects.equals(this.vnfProvider, vnfInstance.vnfProvider) &&
				Objects.equals(this.vnfProductName, vnfInstance.vnfProductName) &&
				Objects.equals(this.vnfSoftwareVersion, vnfInstance.vnfSoftwareVersion) &&
				Objects.equals(this.vnfdVersion, vnfInstance.vnfdVersion) &&
				Objects.equals(this.vnfPkgId, vnfInstance.vnfPkgId) &&
				Objects.equals(this.vnfConfigurableProperties, vnfInstance.vnfConfigurableProperties) &&
				Objects.equals(this.vimId, vnfInstance.vimId) &&
				Objects.equals(this.instantiationState, vnfInstance.instantiationState) &&
				Objects.equals(this.instantiatedVnfInfo, vnfInstance.instantiatedVnfInfo) &&
				Objects.equals(this.metadata, vnfInstance.metadata) &&
				Objects.equals(this.extensions, vnfInstance.extensions);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, vnfInstanceName, vnfInstanceDescription, vnfdId, vnfProvider, vnfProductName, vnfSoftwareVersion, vnfdVersion, vnfPkgId, vnfConfigurableProperties, vimId, instantiationState, instantiatedVnfInfo, metadata, extensions);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfInstance {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    vnfInstanceName: ").append(toIndentedString(vnfInstanceName)).append("\n");
		sb.append("    vnfInstanceDescription: ").append(toIndentedString(vnfInstanceDescription)).append("\n");
		sb.append("    vnfdId: ").append(toIndentedString(vnfdId)).append("\n");
		sb.append("    vnfProvider: ").append(toIndentedString(vnfProvider)).append("\n");
		sb.append("    vnfProductName: ").append(toIndentedString(vnfProductName)).append("\n");
		sb.append("    vnfSoftwareVersion: ").append(toIndentedString(vnfSoftwareVersion)).append("\n");
		sb.append("    vnfdVersion: ").append(toIndentedString(vnfdVersion)).append("\n");
		sb.append("    vnfPkgId: ").append(toIndentedString(vnfPkgId)).append("\n");
		sb.append("    vnfConfigurableProperties: ").append(toIndentedString(vnfConfigurableProperties)).append("\n");
		sb.append("    vimId: ").append(toIndentedString(vimId)).append("\n");
		sb.append("    instantiationState: ").append(toIndentedString(instantiationState)).append("\n");
		sb.append("    instantiatedVnfInfo: ").append(toIndentedString(instantiatedVnfInfo)).append("\n");
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
