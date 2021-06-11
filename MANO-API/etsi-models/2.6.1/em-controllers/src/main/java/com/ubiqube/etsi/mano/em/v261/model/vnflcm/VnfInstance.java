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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents a VNF instance.
 */
@ApiModel(description = "This type represents a VNF instance. ")
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

	@JsonProperty("vnfPkgInfoId")
	private String vnfPkgInfoId = null;

	@JsonProperty("vnfConfigurableProperties")
	private Map<String, Object> vnfConfigurableProperties = null;

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

	@JsonProperty("_links")
	private VnfInstanceLinks links = null;

	public VnfInstance id(final String id) {
		this.id = id;
		return this;
	}

	/**
	 * Identifier of the VNF instance.
	 *
	 * @return id
	 **/
	@ApiModelProperty(required = true, value = "Identifier of the VNF instance. ")
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
	 * Name of the VNF instance. This attribute can be modified with the PATCH method.
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
	 * Human-readable description of the VNF instance. This attribute can be modified with the PATCH method.
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
	 * Identifier of the VNFD on which the VNF instance is based. This attribute can be modified with the PATCH method. Modifying the value of this attribute shall not be performed when conflicts exist between the previous and the newly referred VNF package, i.e. when the new VNFD is not changed with respect to the previous VNFD apart from referencing to other VNF software images. In order to avoid misalignment of the VnfInstance with the current VNF's on-boarded VNF package, the values of
	 * attributes in the VnfInstance that have corresponding attributes in the VNFD shall be kept in sync with the values in the VNFD.
	 *
	 * @return vnfdId
	 **/
	@ApiModelProperty(required = true, value = "Identifier of the VNFD on which the VNF instance is based. This attribute can be modified with the PATCH method. Modifying the value of this attribute shall not be performed  when conflicts exist between the previous and the newly referred  VNF package, i.e. when the new VNFD is not changed with respect  to the previous VNFD apart from referencing to other VNF software  images. In order to avoid misalignment of the VnfInstance with  the current VNF's on-boarded VNF package, the values of attributes  in the VnfInstance that have corresponding attributes in the VNFD  shall be kept in sync with the values in the VNFD. ")
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
	 * Software version of the VNF. The value is copied from the VNFD.
	 *
	 * @return vnfSoftwareVersion
	 **/
	@ApiModelProperty(required = true, value = "Software version of the VNF. The value is copied from the VNFD. ")
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
	 * Identifies the version of the VNFD. The value is copied from the VNFD.
	 *
	 * @return vnfdVersion
	 **/
	@ApiModelProperty(required = true, value = "Identifies the version of the VNFD. The value is copied from the VNFD. ")
	@NotNull

	public String getVnfdVersion() {
		return vnfdVersion;
	}

	public void setVnfdVersion(final String vnfdVersion) {
		this.vnfdVersion = vnfdVersion;
	}

	public VnfInstance vnfPkgInfoId(final String vnfPkgInfoId) {
		this.vnfPkgInfoId = vnfPkgInfoId;
		return this;
	}

	/**
	 * Identifier of information held by the NFVO about the specific VNF package on which the VNF is based. This identifier was allocated by the NFVO. This attribute can be modified with the PATCHmethod.
	 *
	 * @return vnfPkgInfoId
	 **/
	@ApiModelProperty(required = true, value = "Identifier of information held by the NFVO about the specific VNF package on which the VNF is based. This identifier was allocated by the NFVO. This attribute can be modified with the PATCHmethod. ")
	@NotNull

	public String getVnfPkgInfoId() {
		return vnfPkgInfoId;
	}

	public void setVnfPkgInfoId(final String vnfPkgInfoId) {
		this.vnfPkgInfoId = vnfPkgInfoId;
	}

	public VnfInstance vnfConfigurableProperties(final Map<String, Object> vnfConfigurableProperties) {
		this.vnfConfigurableProperties = vnfConfigurableProperties;
		return this;
	}

	/**
	 * Additional VNF-specific attributes that provide the current values of the configurable properties of the VNF instance. These attributes represent values that are stored persistently in the VnfInstance structure and that correspond to configuration parameters of the VNF instance. Modifying these attributes affects the configuration of the VNF instance either directly (if the VNF instance is in INSTANTIATED state at the time of the modification) or as part of the subsequent VNF instantiation
	 * operation (if the VNF instance is in NOT_INSTANTIATED state at the time of the modification). Configurable properties referred in these attributes shall be declared in the VNFD. The declaration of configurable properties in the VNFD can optionally contain the specification of initial values. ETSI GS NFV-SOL 001 specifies the structure and format of the VNFD based on TOSCA specifications. VNF configurable properties are sometimes also referred to as configuration parameters applicable to a
	 * VNF. Some of these are set prior to instantiation and cannot be modified if the VNF is instantiated, some are set prior to instantiation (are part of initial configuration) and can be modified later, and others can be set only after instantiation. The applicability of certain configuration may depend on the VNF and the required operation of the VNF at a certain point in time. These configurable properties include the following standard attributes, which are declared in the VNFD if
	 * auto-scaling and/or auto-healing are supported by the VNF: - isAutoscaleEnabled: If present, the VNF supports auto-scaling. If set to true, auto-scaling is currently enabled. If set to false, auto-scaling is currently disabled. - isAutohealEnabled: If present, the VNF supports auto-healing. If set to true, auto-healing is currently enabled. If set to false, auto-healing is currently disabled. This attributeThese attributes can be modified with the PATCH method.
	 *
	 * @return vnfConfigurableProperties
	 **/
	@ApiModelProperty(value = "Additional VNF-specific attributes that provide the current values  of the configurable properties of the VNF instance. These attributes represent values that are stored persistently in  the VnfInstance structure and that correspond to configuration  parameters of the VNF instance.  Modifying these attributes affects the configuration of the VNF  instance either directly (if the VNF instance is in INSTANTIATED  state at the time of the modification) or as part of the subsequent  VNF instantiation operation (if the VNF instance is in NOT_INSTANTIATED  state at the time of the modification). Configurable properties referred in these attributes shall be declared  in the VNFD.  The declaration of configurable properties in the VNFD can optionally  contain the specification of initial values.  ETSI GS NFV-SOL 001 specifies the structure and format of the  VNFD based on TOSCA specifications. VNF configurable properties are sometimes also referred to as configuration  parameters applicable to a VNF. Some of these are set prior to instantiation  and cannot be modified if the VNF is instantiated, some are set prior to  instantiation (are part of initial configuration) and can be modified later,  and others can be set only after instantiation. The applicability of certain  configuration may depend on the VNF and the required operation of the VNF at  a certain point in time. These configurable properties include the following standard attributes,  which are declared in the VNFD if auto-scaling and/or auto-healing are  supported by the VNF:    - isAutoscaleEnabled: If present, the VNF supports auto-scaling. If set    to true, auto-scaling is currently enabled. If set to false, auto-scaling    is currently disabled.   - isAutohealEnabled: If present, the VNF supports auto-healing. If set    to true, auto-healing is currently enabled. If set to false, auto-healing    is currently disabled. This attributeThese attributes can be modified with the PATCH method. ")

	@Valid

	public Map<String, Object> getVnfConfigurableProperties() {
		return vnfConfigurableProperties;
	}

	public void setVnfConfigurableProperties(final Map<String, Object> vnfConfigurableProperties) {
		this.vnfConfigurableProperties = vnfConfigurableProperties;
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
	 * Additional VNF-specific attributes that provide metadata describing the VNF instance. These attributes represent values that are stored persistently in the VnfInstance structure for consumption by functional blocks that invoke the VNF lifecycle management interface. They are not consumed by the VNFM, or the lifecycle management scripts. Modifying the values of these attributes has no effect on the VNF instance, it only affects the information represented in the VnfInstance structure. Metadata
	 * that VNF provider foresees shall be declared in the VNFD. The VNFM shall accept requests to write metadata that are not are declared in the VNFD. These attributes can be initialized with default values from VNFD and/or with values passed in the CreateVnfRequest structure (see clause 5.5.2.3). This attribute can be modified with the PATCH method. ETSI GS NFV-SOL 001 specifies the structure and format of the VNFD based on TOSCA specifications.
	 *
	 * @return metadata
	 **/
	@ApiModelProperty(value = "Additional VNF-specific attributes that provide metadata describing the VNF instance. These attributes represent values that are stored persistently in the VnfInstance  structure for consumption by functional blocks that invoke the VNF lifecycle management  interface. They are not consumed by the VNFM, or the lifecycle management scripts. Modifying the values of these attributes has no effect on the VNF instance, it only  affects the information represented in the VnfInstance structure. Metadata that VNF provider foresees shall be declared in the VNFD. The VNFM shall  accept requests to write metadata that are not  are declared in the VNFD.   These attributes can be initialized with default values from VNFD and/or with values  passed in the CreateVnfRequest structure (see clause 5.5.2.3). This attribute can be modified with the PATCH method. ETSI GS NFV-SOL 001 specifies the structure and format of the VNFD based on TOSCA specifications. ")

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
	 * Additional VNF specific attributes that affect the lifecycle management of this VNF instance by the VNFM, or the lifecycle management scripts. These attributes represent values that are stored persistently in the VnfInstance structure for consumption by the VNFM, or by the lifecycle management scripts during the execution of VNF lifecycle management operations. Modifying the values of these attributes has no direct effect on the VNF instance; however, the modified attribute values can be
	 * considered during subsequent VNF lifecycle management operations, which means that the modified values can indirectly affect the configuration of the VNF instance. All extensions that are allowed for the VNF shall be declared in the VNFD. This attribute can be be initialized with default values from VNFD and/or with values passed in the InstantiateVnfRequest structure (see clause 5.5.2.4). A value initialised with default values from the VNFD can be updated with values passed in the
	 * InstantiateVnfRequest structure. Further, these attributes can be modified with the PATCH method. ETSI GS NFV-SOL 001 specifies the structure and format of the VNFD based on TOSCA specifications.
	 *
	 * @return extensions
	 **/
	@ApiModelProperty(value = "Additional VNF specific attributes that affect the lifecycle management of this  VNF instance by the VNFM, or the lifecycle management scripts.  These attributes represent values that are stored persistently in the VnfInstance  structure for consumption by the VNFM, or by the lifecycle management scripts  during the execution of VNF lifecycle management operations.  Modifying the values of these attributes has no direct effect on the VNF instance;  however, the modified attribute values can be considered during subsequent VNF  lifecycle management operations, which means that the modified values can indirectly  affect the configuration of the VNF instance. All extensions that are allowed for the VNF shall be declared in the VNFD. This attribute can be be initialized with default values from VNFD and/or  with values passed in the InstantiateVnfRequest structure (see clause 5.5.2.4). A value initialised with default values from the VNFD can be updated with values  passed in the InstantiateVnfRequest structure. Further, these attributes can be modified with the PATCH method. ETSI GS NFV-SOL 001 specifies the structure and format of the VNFD based on  TOSCA specifications. ")

	@Valid

	public Map<String, Object> getExtensions() {
		return extensions;
	}

	public void setExtensions(final Map<String, Object> extensions) {
		this.extensions = extensions;
	}

	public VnfInstance links(final VnfInstanceLinks links) {
		this.links = links;
		return this;
	}

	/**
	 * Get links
	 *
	 * @return links
	 **/
	@ApiModelProperty(value = "")

	@Valid

	public VnfInstanceLinks getLinks() {
		return links;
	}

	public void setLinks(final VnfInstanceLinks links) {
		this.links = links;
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
				Objects.equals(this.vnfPkgInfoId, vnfInstance.vnfPkgInfoId) &&
				Objects.equals(this.vnfConfigurableProperties, vnfInstance.vnfConfigurableProperties) &&
				Objects.equals(this.instantiationState, vnfInstance.instantiationState) &&
				Objects.equals(this.instantiatedVnfInfo, vnfInstance.instantiatedVnfInfo) &&
				Objects.equals(this.metadata, vnfInstance.metadata) &&
				Objects.equals(this.extensions, vnfInstance.extensions) &&
				Objects.equals(this.links, vnfInstance.links);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, vnfInstanceName, vnfInstanceDescription, vnfdId, vnfProvider, vnfProductName, vnfSoftwareVersion, vnfdVersion, vnfPkgInfoId, vnfConfigurableProperties, instantiationState, instantiatedVnfInfo, metadata, extensions, links);
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
		sb.append("    vnfPkgInfoId: ").append(toIndentedString(vnfPkgInfoId)).append("\n");
		sb.append("    vnfConfigurableProperties: ").append(toIndentedString(vnfConfigurableProperties)).append("\n");
		sb.append("    instantiationState: ").append(toIndentedString(instantiationState)).append("\n");
		sb.append("    instantiatedVnfInfo: ").append(toIndentedString(instantiatedVnfInfo)).append("\n");
		sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
		sb.append("    extensions: ").append(toIndentedString(extensions)).append("\n");
		sb.append("    links: ").append(toIndentedString(links)).append("\n");
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
