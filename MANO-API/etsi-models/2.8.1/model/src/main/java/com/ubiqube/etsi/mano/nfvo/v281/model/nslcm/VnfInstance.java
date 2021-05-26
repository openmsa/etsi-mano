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
package com.ubiqube.etsi.mano.nfvo.v281.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.nfvo.v281.model.nslcm.KeyValuePairs;
import com.ubiqube.etsi.mano.nfvo.v281.model.nslcm.VnfInstanceInstantiatedVnfInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents a VNF instance. Clause B.3.2 of ETSI GS NFV-SOL 003 [4] provides examples illustrating the relationship among the different run-time information elements (CP, VL and link ports) used to represent the connectivity of a VNF. 
 */
@ApiModel(description = "This type represents a VNF instance. Clause B.3.2 of ETSI GS NFV-SOL 003 [4] provides examples illustrating the relationship among the different run-time information elements (CP, VL and link ports) used to represent the connectivity of a VNF. ")
@Validated

public class VnfInstance   {
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
  private KeyValuePairs vnfConfigurableProperties = null;

  @JsonProperty("vimId")
  private String vimId = null;

  /**
   * The instantiation state of the VNF. 
   */
  public enum InstantiationStateEnum {
    NOT_INSTANTIATED("NOT_INSTANTIATED"),
    
    INSTANTIATED("INSTANTIATED");

    private String value;

    InstantiationStateEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static InstantiationStateEnum fromValue(String text) {
      for (InstantiationStateEnum b : InstantiationStateEnum.values()) {
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
  private KeyValuePairs metadata = null;

  @JsonProperty("extensions")
  private KeyValuePairs extensions = null;

  public VnfInstance id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Identifier of the VNF instance. 
   * @return id
  **/
  @ApiModelProperty(required = true, value = "Identifier of the VNF instance. ")
  @NotNull


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public VnfInstance vnfInstanceName(String vnfInstanceName) {
    this.vnfInstanceName = vnfInstanceName;
    return this;
  }

  /**
   * Name of the VNF instance. Modifications to this attribute can be requested using the \"ModifyVnfInfoData\" structure. 
   * @return vnfInstanceName
  **/
  @ApiModelProperty(value = "Name of the VNF instance. Modifications to this attribute can be requested using the \"ModifyVnfInfoData\" structure. ")


  public String getVnfInstanceName() {
    return vnfInstanceName;
  }

  public void setVnfInstanceName(String vnfInstanceName) {
    this.vnfInstanceName = vnfInstanceName;
  }

  public VnfInstance vnfInstanceDescription(String vnfInstanceDescription) {
    this.vnfInstanceDescription = vnfInstanceDescription;
    return this;
  }

  /**
   * Human-readable description of the VNF instance. Modifications to this attribute can be requested using the \"ModifyVnfInfoData\" structure. 
   * @return vnfInstanceDescription
  **/
  @ApiModelProperty(value = "Human-readable description of the VNF instance. Modifications to this attribute can be requested using the \"ModifyVnfInfoData\" structure. ")


  public String getVnfInstanceDescription() {
    return vnfInstanceDescription;
  }

  public void setVnfInstanceDescription(String vnfInstanceDescription) {
    this.vnfInstanceDescription = vnfInstanceDescription;
  }

  public VnfInstance vnfdId(String vnfdId) {
    this.vnfdId = vnfdId;
    return this;
  }

  /**
   * Identifier of the VNFD on which the VNF instance is based. Modifications to this attribute can be requested using the \"ModifyVnfInfoData\" structure. Modifying the value of this attribute shall not be performed when conflicts exist between the previous and the newly referred VNF package, i.e. when the new VNFD is changed with respect to the previous VNFD in other aspects than merely referencing to other VNF software images. In order to avoid misalignment of the VnfInstance with the current VNF's on-boarded VNF Package, the values of attributes in the VnfInstance that have corresponding attributes in the VNFD shall be kept in sync with the values in the VNFD. 
   * @return vnfdId
  **/
  @ApiModelProperty(required = true, value = "Identifier of the VNFD on which the VNF instance is based. Modifications to this attribute can be requested using the \"ModifyVnfInfoData\" structure. Modifying the value of this attribute shall not be performed when conflicts exist between the previous and the newly referred VNF package, i.e. when the new VNFD is changed with respect to the previous VNFD in other aspects than merely referencing to other VNF software images. In order to avoid misalignment of the VnfInstance with the current VNF's on-boarded VNF Package, the values of attributes in the VnfInstance that have corresponding attributes in the VNFD shall be kept in sync with the values in the VNFD. ")
  @NotNull


  public String getVnfdId() {
    return vnfdId;
  }

  public void setVnfdId(String vnfdId) {
    this.vnfdId = vnfdId;
  }

  public VnfInstance vnfProvider(String vnfProvider) {
    this.vnfProvider = vnfProvider;
    return this;
  }

  /**
   * Provider of the VNF and the VNFD. The value is copied from the VNFD. 
   * @return vnfProvider
  **/
  @ApiModelProperty(required = true, value = "Provider of the VNF and the VNFD. The value is copied from the VNFD. ")
  @NotNull


  public String getVnfProvider() {
    return vnfProvider;
  }

  public void setVnfProvider(String vnfProvider) {
    this.vnfProvider = vnfProvider;
  }

  public VnfInstance vnfProductName(String vnfProductName) {
    this.vnfProductName = vnfProductName;
    return this;
  }

  /**
   * Name to identify the VNF Product. The value is copied from the VNFD. 
   * @return vnfProductName
  **/
  @ApiModelProperty(required = true, value = "Name to identify the VNF Product. The value is copied from the VNFD. ")
  @NotNull


  public String getVnfProductName() {
    return vnfProductName;
  }

  public void setVnfProductName(String vnfProductName) {
    this.vnfProductName = vnfProductName;
  }

  public VnfInstance vnfSoftwareVersion(String vnfSoftwareVersion) {
    this.vnfSoftwareVersion = vnfSoftwareVersion;
    return this;
  }

  /**
   * Software version of the VNF. The value is copied from the VNFD. 
   * @return vnfSoftwareVersion
  **/
  @ApiModelProperty(required = true, value = "Software version of the VNF. The value is copied from the VNFD. ")
  @NotNull


  public String getVnfSoftwareVersion() {
    return vnfSoftwareVersion;
  }

  public void setVnfSoftwareVersion(String vnfSoftwareVersion) {
    this.vnfSoftwareVersion = vnfSoftwareVersion;
  }

  public VnfInstance vnfdVersion(String vnfdVersion) {
    this.vnfdVersion = vnfdVersion;
    return this;
  }

  /**
   * Identifies the version of the VNFD. The value is copied from the VNFD. 
   * @return vnfdVersion
  **/
  @ApiModelProperty(required = true, value = "Identifies the version of the VNFD. The value is copied from the VNFD. ")
  @NotNull


  public String getVnfdVersion() {
    return vnfdVersion;
  }

  public void setVnfdVersion(String vnfdVersion) {
    this.vnfdVersion = vnfdVersion;
  }

  public VnfInstance vnfPkgId(String vnfPkgId) {
    this.vnfPkgId = vnfPkgId;
    return this;
  }

  /**
   * Identifier of information held by the NFVO about the specific VNF package on which the VNF is based. This identifier has been allocated by the NFVO. 
   * @return vnfPkgId
  **/
  @ApiModelProperty(required = true, value = "Identifier of information held by the NFVO about the specific VNF package on which the VNF is based. This identifier has been allocated by the NFVO. ")
  @NotNull


  public String getVnfPkgId() {
    return vnfPkgId;
  }

  public void setVnfPkgId(String vnfPkgId) {
    this.vnfPkgId = vnfPkgId;
  }

  public VnfInstance vnfConfigurableProperties(KeyValuePairs vnfConfigurableProperties) {
    this.vnfConfigurableProperties = vnfConfigurableProperties;
    return this;
  }

  /**
   * Additional VNF-specific attributes that provide the current values of the configurable properties of the VNF instance. These attributes represent values that are stored persistently in the VnfInstance structure and that correspond to configuration parameters of the VNF instance. Modifying these attributes affects the configuration of the VNF instance either directly(if the VNF instance is in INSTANTIATED state at the time of the modification) or as part of the subsequent VNF instantiation operation (if the VNF instance is in NOT_INSTANTIATED state at the time of the modification). Configurable properties referred in these attributes are declared in the VNFD. ETSI GS NFV-SOL 001 specifies the structure and format of the VNFD based on TOSCA specifications. VNF configurable properties are sometimes also referred to as configuration parameters applicable to a VNF. Some of these are set prior to instantiation and cannot be modified if the VNF is instantiated, some are set prior to instantiation (are part of initial configuration) and can be modified later, and others can be set only after instantiation. The applicability of certain configuration may depend on the VNF and the required operation of the VNF at a certain point in time. These configurable properties include the following standard attributes, which are declared in the VNFD if auto-scaling and/or auto-healing are supported by the VNF:    - isAutoscaleEnabled: If present, the VNF supports auto-scaling. If     set to true, auto-scaling is currently enabled. If set to false,     auto-scaling is currently disabled.   - isAutohealEnabled: If present, the VNF supports auto-healing. If     set to true, auto-healing is currently enabled. If set to false,     auto-healing is currently disabled.  Modifications to these attributes can be requested using the \"ModifyVnfInfoData\" structure. 
   * @return vnfConfigurableProperties
  **/
  @ApiModelProperty(value = "Additional VNF-specific attributes that provide the current values of the configurable properties of the VNF instance. These attributes represent values that are stored persistently in the VnfInstance structure and that correspond to configuration parameters of the VNF instance. Modifying these attributes affects the configuration of the VNF instance either directly(if the VNF instance is in INSTANTIATED state at the time of the modification) or as part of the subsequent VNF instantiation operation (if the VNF instance is in NOT_INSTANTIATED state at the time of the modification). Configurable properties referred in these attributes are declared in the VNFD. ETSI GS NFV-SOL 001 specifies the structure and format of the VNFD based on TOSCA specifications. VNF configurable properties are sometimes also referred to as configuration parameters applicable to a VNF. Some of these are set prior to instantiation and cannot be modified if the VNF is instantiated, some are set prior to instantiation (are part of initial configuration) and can be modified later, and others can be set only after instantiation. The applicability of certain configuration may depend on the VNF and the required operation of the VNF at a certain point in time. These configurable properties include the following standard attributes, which are declared in the VNFD if auto-scaling and/or auto-healing are supported by the VNF:    - isAutoscaleEnabled: If present, the VNF supports auto-scaling. If     set to true, auto-scaling is currently enabled. If set to false,     auto-scaling is currently disabled.   - isAutohealEnabled: If present, the VNF supports auto-healing. If     set to true, auto-healing is currently enabled. If set to false,     auto-healing is currently disabled.  Modifications to these attributes can be requested using the \"ModifyVnfInfoData\" structure. ")

  @Valid

  public KeyValuePairs getVnfConfigurableProperties() {
    return vnfConfigurableProperties;
  }

  public void setVnfConfigurableProperties(KeyValuePairs vnfConfigurableProperties) {
    this.vnfConfigurableProperties = vnfConfigurableProperties;
  }

  public VnfInstance vimId(String vimId) {
    this.vimId = vimId;
    return this;
  }

  /**
   * Identifier of a VIM that manages resources for the VNF instance. 
   * @return vimId
  **/
  @ApiModelProperty(value = "Identifier of a VIM that manages resources for the VNF instance. ")


  public String getVimId() {
    return vimId;
  }

  public void setVimId(String vimId) {
    this.vimId = vimId;
  }

  public VnfInstance instantiationState(InstantiationStateEnum instantiationState) {
    this.instantiationState = instantiationState;
    return this;
  }

  /**
   * The instantiation state of the VNF. 
   * @return instantiationState
  **/
  @ApiModelProperty(required = true, value = "The instantiation state of the VNF. ")
  @NotNull


  public InstantiationStateEnum getInstantiationState() {
    return instantiationState;
  }

  public void setInstantiationState(InstantiationStateEnum instantiationState) {
    this.instantiationState = instantiationState;
  }

  public VnfInstance instantiatedVnfInfo(VnfInstanceInstantiatedVnfInfo instantiatedVnfInfo) {
    this.instantiatedVnfInfo = instantiatedVnfInfo;
    return this;
  }

  /**
   * Get instantiatedVnfInfo
   * @return instantiatedVnfInfo
  **/
  @ApiModelProperty(value = "")

  @Valid

  public VnfInstanceInstantiatedVnfInfo getInstantiatedVnfInfo() {
    return instantiatedVnfInfo;
  }

  public void setInstantiatedVnfInfo(VnfInstanceInstantiatedVnfInfo instantiatedVnfInfo) {
    this.instantiatedVnfInfo = instantiatedVnfInfo;
  }

  public VnfInstance metadata(KeyValuePairs metadata) {
    this.metadata = metadata;
    return this;
  }

  /**
   * Additional VNF-specific attributes that provide metadata describing the VNF instance.  These attributes represent values that are stored persistently in the VnfInstance structure  for consumption by functional blocks that invoke the VNF lifecycle management interface.  They are not consumed by the VNFM, or the lifecycle management scripts. Modifying the values of these attributes has no effect on the VNF instance, it only affects  the information represented in the VnfInstance structure. Metadata that the VNF provider foresees are expected to be declared in the VNFD (see note 2). Modifications to these attributes can be requested using the \"ModifyVnfInfoData\" structure. NOTE 2: ETSI GS NFV-SOL 001 specifies the structure and format of the VNFD based on TOSCA specifications. 
   * @return metadata
  **/
  @ApiModelProperty(value = "Additional VNF-specific attributes that provide metadata describing the VNF instance.  These attributes represent values that are stored persistently in the VnfInstance structure  for consumption by functional blocks that invoke the VNF lifecycle management interface.  They are not consumed by the VNFM, or the lifecycle management scripts. Modifying the values of these attributes has no effect on the VNF instance, it only affects  the information represented in the VnfInstance structure. Metadata that the VNF provider foresees are expected to be declared in the VNFD (see note 2). Modifications to these attributes can be requested using the \"ModifyVnfInfoData\" structure. NOTE 2: ETSI GS NFV-SOL 001 specifies the structure and format of the VNFD based on TOSCA specifications. ")

  @Valid

  public KeyValuePairs getMetadata() {
    return metadata;
  }

  public void setMetadata(KeyValuePairs metadata) {
    this.metadata = metadata;
  }

  public VnfInstance extensions(KeyValuePairs extensions) {
    this.extensions = extensions;
    return this;
  }

  /**
   * Additional VNF-specific attributes that affect the lifecycle management of this VNF instance. These attributes represent values that are stored persistently in the VnfInstance structure for  consumption by the VNFM or by the lifecycle management scripts during the execution of VNF lifecycle management operations. Modifying the values of these attributes has no direct effect on the VNF instance; however, the modified  attribute values can be considered during subsequent VNF lifecycle management operations, which means that  the modified values can indirectly affect the configuration of the VNF instance. All extensions that are allowed for the VNF are declared in the VNFD. Modifications to these attributes can be requested using the \"ModifyVnfInfoData\" structure. 
   * @return extensions
  **/
  @ApiModelProperty(value = "Additional VNF-specific attributes that affect the lifecycle management of this VNF instance. These attributes represent values that are stored persistently in the VnfInstance structure for  consumption by the VNFM or by the lifecycle management scripts during the execution of VNF lifecycle management operations. Modifying the values of these attributes has no direct effect on the VNF instance; however, the modified  attribute values can be considered during subsequent VNF lifecycle management operations, which means that  the modified values can indirectly affect the configuration of the VNF instance. All extensions that are allowed for the VNF are declared in the VNFD. Modifications to these attributes can be requested using the \"ModifyVnfInfoData\" structure. ")

  @Valid

  public KeyValuePairs getExtensions() {
    return extensions;
  }

  public void setExtensions(KeyValuePairs extensions) {
    this.extensions = extensions;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VnfInstance vnfInstance = (VnfInstance) o;
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
    StringBuilder sb = new StringBuilder();
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
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

