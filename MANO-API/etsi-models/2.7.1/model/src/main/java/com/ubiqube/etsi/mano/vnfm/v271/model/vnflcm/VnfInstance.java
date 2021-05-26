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
package com.ubiqube.etsi.mano.vnfm.v271.model.vnflcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.vnfm.v271.model.vnflcm.KeyValuePairs;
import com.ubiqube.etsi.mano.vnfm.v271.model.vnflcm.VimConnectionInfo;
import com.ubiqube.etsi.mano.vnfm.v271.model.vnflcm.VnfInstanceInstantiatedVnfInfo;
import com.ubiqube.etsi.mano.vnfm.v271.model.vnflcm.VnfInstanceLinks;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents a VNF instance. 
 */
@ApiModel(description = "This type represents a VNF instance. ")
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

  @JsonProperty("vnfConfigurableProperties")
  private KeyValuePairs vnfConfigurableProperties = null;

  @JsonProperty("vimConnectionInfo")
  @Valid
  private List<VimConnectionInfo> vimConnectionInfo = null;

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

  @JsonProperty("_links")
  private VnfInstanceLinks links = null;

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
   * Name of the VNF instance. This attribute can be modified with the PATCH method. 
   * @return vnfInstanceName
  **/
  @ApiModelProperty(value = "Name of the VNF instance. This attribute can be modified with the PATCH method. ")


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
   * Human-readable description of the VNF instance. This attribute can be modified with the PATCH method. 
   * @return vnfInstanceDescription
  **/
  @ApiModelProperty(value = "Human-readable description of the VNF instance. This attribute can be modified with the PATCH method. ")


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
   * Identifier of the VNFD on which the VNF instance is based. Modifying the value of this attribute shall not be performed when conflicts exist between the previous and the newly referred VNF package, i.e. when the new VNFD is not changed with respect to the previous VNFD in other aspects than merely referencing to other VNF software images. In order to avoid misalignment of the VnfInstance with the current VNF's on-boarded VNF Package, the values of attributes in the VnfInstance that have corresponding attributes in the VNFD shall be kept in sync with the values in the VNFD. 
   * @return vnfdId
  **/
  @ApiModelProperty(required = true, value = "Identifier of the VNFD on which the VNF instance is based. Modifying the value of this attribute shall not be performed when conflicts exist between the previous and the newly referred VNF package, i.e. when the new VNFD is not changed with respect to the previous VNFD in other aspects than merely referencing to other VNF software images. In order to avoid misalignment of the VnfInstance with the current VNF's on-boarded VNF Package, the values of attributes in the VnfInstance that have corresponding attributes in the VNFD shall be kept in sync with the values in the VNFD. ")
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

  public VnfInstance vnfConfigurableProperties(KeyValuePairs vnfConfigurableProperties) {
    this.vnfConfigurableProperties = vnfConfigurableProperties;
    return this;
  }

  /**
   * Current values of the configurable properties of the VNF instance. Configurable properties referred in this attribute are declared in the VNFD. ETSI GS NFV-SOL 001 specifies the structure and format of the VNFD based on TOSCA specifications. VNF configurable properties are sometimes also referred to as configuration parameters applicable to a VNF. Some of these are set prior to instantiation and cannot be modified if the VNF is instantiated, some are set prior to instantiation (are part of initial configuration) and can be modified later, and others can be set only after instantiation. The applicability of certain configuration may depend on the VNF and the required operation of the VNF at a certain point in time. These configurable properties include the following standard attributes, which are declared in the VNFD if auto-scaling and/or auto-healing are supported by the VNF: * isAutoscaleEnabled: If present, the VNF supports auto-scaling. If   set to true, auto-scaling is currently enabled. If set to false,   auto-scaling is currently disabled. * isAutohealEnabled: If present, the VNF supports auto-healing. If   set to true, auto-healing is currently enabled. If set to false,   auto-healing is currently disabled. These configurable properties can be initialized with default values from the VNFD (see note 4) or with values passed in the InstantiateVnfRequest structure (see clause 5.5.2.4). Configurable properties initialized with default values from the VNFD can be updated with values passed in the InstantiateVnfRequest structure. Further, these configurable properties can be created, modified or deleted with the PATCH method. 
   * @return vnfConfigurableProperties
  **/
  @ApiModelProperty(value = "Current values of the configurable properties of the VNF instance. Configurable properties referred in this attribute are declared in the VNFD. ETSI GS NFV-SOL 001 specifies the structure and format of the VNFD based on TOSCA specifications. VNF configurable properties are sometimes also referred to as configuration parameters applicable to a VNF. Some of these are set prior to instantiation and cannot be modified if the VNF is instantiated, some are set prior to instantiation (are part of initial configuration) and can be modified later, and others can be set only after instantiation. The applicability of certain configuration may depend on the VNF and the required operation of the VNF at a certain point in time. These configurable properties include the following standard attributes, which are declared in the VNFD if auto-scaling and/or auto-healing are supported by the VNF: * isAutoscaleEnabled: If present, the VNF supports auto-scaling. If   set to true, auto-scaling is currently enabled. If set to false,   auto-scaling is currently disabled. * isAutohealEnabled: If present, the VNF supports auto-healing. If   set to true, auto-healing is currently enabled. If set to false,   auto-healing is currently disabled. These configurable properties can be initialized with default values from the VNFD (see note 4) or with values passed in the InstantiateVnfRequest structure (see clause 5.5.2.4). Configurable properties initialized with default values from the VNFD can be updated with values passed in the InstantiateVnfRequest structure. Further, these configurable properties can be created, modified or deleted with the PATCH method. ")

  @Valid

  public KeyValuePairs getVnfConfigurableProperties() {
    return vnfConfigurableProperties;
  }

  public void setVnfConfigurableProperties(KeyValuePairs vnfConfigurableProperties) {
    this.vnfConfigurableProperties = vnfConfigurableProperties;
  }

  public VnfInstance vimConnectionInfo(List<VimConnectionInfo> vimConnectionInfo) {
    this.vimConnectionInfo = vimConnectionInfo;
    return this;
  }

  public VnfInstance addVimConnectionInfoItem(VimConnectionInfo vimConnectionInfoItem) {
    if (this.vimConnectionInfo == null) {
      this.vimConnectionInfo = new ArrayList<>();
    }
    this.vimConnectionInfo.add(vimConnectionInfoItem);
    return this;
  }

  /**
   * Information about VIM connections to be used for managing the resources for the VNF instance. This attribute shall only be supported and present if VNF-related resource management in direct mode is applicable. This attribute can be modified with the PATCH method. 
   * @return vimConnectionInfo
  **/
  @ApiModelProperty(value = "Information about VIM connections to be used for managing the resources for the VNF instance. This attribute shall only be supported and present if VNF-related resource management in direct mode is applicable. This attribute can be modified with the PATCH method. ")

  @Valid

  public List<VimConnectionInfo> getVimConnectionInfo() {
    return vimConnectionInfo;
  }

  public void setVimConnectionInfo(List<VimConnectionInfo> vimConnectionInfo) {
    this.vimConnectionInfo = vimConnectionInfo;
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
   * Additional VNF-specific attributes that provide metadata describing the VNF instance. These attributes represent values that are stored persistently in the VnfInstance structure for consumption by functional blocks that invoke the VNF lifecycle management interface. They are not consumed by the VNFM, or the lifecycle management scripts. Modifying the values of these attributes has no effect on the VNF instance, it only affects the information represented in the VnfInstance structure. Metadata that are writeable are the VNF provider foresees are expected to be declared in the VNFD. The declaration of metadata in the VNFD can optionally contain the specification of initial values. The VNFM shall accept requests to write metadata that are not declared in the VNFD. These attributes can be initialized with default values from the VNFD or with values passed in the CreateVnfRequest structure (see clause 5.5.2.3). This attributeThese attributes can be created, modified or removed with the PATCH method. ETSI GS NFV-SOL 001 specifies the structure and format of the VNFD based on TOSCA specifications. Upon creation of the VnfInstance structure, the VNFM shall create and initialize all child attributes of \"vnfConfigurableProperties\", \"metadata\" and \"extensions\" that were declared in the VNFD with a defined initial value. Child attributes of \"vnfConfigurableProperties\", \"metadata\" and \"extensions\" that have no declared initial value shall not be created, in order to be consistent with the semantics of the JSON Merge Patch method (see IETF RFC 7396) that interprets null values as deletion request. 
   * @return metadata
  **/
  @ApiModelProperty(value = "Additional VNF-specific attributes that provide metadata describing the VNF instance. These attributes represent values that are stored persistently in the VnfInstance structure for consumption by functional blocks that invoke the VNF lifecycle management interface. They are not consumed by the VNFM, or the lifecycle management scripts. Modifying the values of these attributes has no effect on the VNF instance, it only affects the information represented in the VnfInstance structure. Metadata that are writeable are the VNF provider foresees are expected to be declared in the VNFD. The declaration of metadata in the VNFD can optionally contain the specification of initial values. The VNFM shall accept requests to write metadata that are not declared in the VNFD. These attributes can be initialized with default values from the VNFD or with values passed in the CreateVnfRequest structure (see clause 5.5.2.3). This attributeThese attributes can be created, modified or removed with the PATCH method. ETSI GS NFV-SOL 001 specifies the structure and format of the VNFD based on TOSCA specifications. Upon creation of the VnfInstance structure, the VNFM shall create and initialize all child attributes of \"vnfConfigurableProperties\", \"metadata\" and \"extensions\" that were declared in the VNFD with a defined initial value. Child attributes of \"vnfConfigurableProperties\", \"metadata\" and \"extensions\" that have no declared initial value shall not be created, in order to be consistent with the semantics of the JSON Merge Patch method (see IETF RFC 7396) that interprets null values as deletion request. ")

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
   * Additional VNF-specific attributes that affect the lifecycle management of this VNF instance. These attributes represent values that are stored persistently in the VnfInstance structure for consumption by the VNFM or the lifecycle management scripts during the execution of VNF lifecycle management operations. All extensions that are allowed for the VNF are declared in the VNFD. The declaration of an extension in the VNFD contains information on whether its presence is optional or required, and optionally can specify an initial value. See note 2 and note 4. The VNFM shall reject requests to write extension attributes that are not declared in the VNFD with a \"422 Unprocessable entity\" error response as defined in clause 6.4 of ETSI GS NFV-SOL 013. Modifying the values of these attributes has no direct effect on the VNF instance; however, the modified attribute values can be considered during subsequent VNF lifecycle management operations, which means that the modified values can indirectly affect the configuration of the VNF instance. These attributes can be initialized with default values from the VNFD or with values passed in the InstantiateVnfRequest structure (see clause 5.5.2.4). Attributes initialized with default values from the VNFD can be updated with values passed in the InstantiateVnfRequest structure. Further, these attributes can be created, modified or deleted with the PATCH method. Upon creation of the VnfInstance structure, the VNFM shall create and initialize all child attributes of \"vnfConfigurableProperties\", \"metadata\" and \"extensions\" that were declared in the VNFD with a defined initial value. Child attributes of \"vnfConfigurableProperties\", \"metadata\" and \"extensions\" that have no declared initial value shall not be created, in order to be consistent with the semantics of the JSON Merge Patch method (see IETF RFC 7396) that interprets null values as deletion request. 
   * @return extensions
  **/
  @ApiModelProperty(value = "Additional VNF-specific attributes that affect the lifecycle management of this VNF instance. These attributes represent values that are stored persistently in the VnfInstance structure for consumption by the VNFM or the lifecycle management scripts during the execution of VNF lifecycle management operations. All extensions that are allowed for the VNF are declared in the VNFD. The declaration of an extension in the VNFD contains information on whether its presence is optional or required, and optionally can specify an initial value. See note 2 and note 4. The VNFM shall reject requests to write extension attributes that are not declared in the VNFD with a \"422 Unprocessable entity\" error response as defined in clause 6.4 of ETSI GS NFV-SOL 013. Modifying the values of these attributes has no direct effect on the VNF instance; however, the modified attribute values can be considered during subsequent VNF lifecycle management operations, which means that the modified values can indirectly affect the configuration of the VNF instance. These attributes can be initialized with default values from the VNFD or with values passed in the InstantiateVnfRequest structure (see clause 5.5.2.4). Attributes initialized with default values from the VNFD can be updated with values passed in the InstantiateVnfRequest structure. Further, these attributes can be created, modified or deleted with the PATCH method. Upon creation of the VnfInstance structure, the VNFM shall create and initialize all child attributes of \"vnfConfigurableProperties\", \"metadata\" and \"extensions\" that were declared in the VNFD with a defined initial value. Child attributes of \"vnfConfigurableProperties\", \"metadata\" and \"extensions\" that have no declared initial value shall not be created, in order to be consistent with the semantics of the JSON Merge Patch method (see IETF RFC 7396) that interprets null values as deletion request. ")

  @Valid

  public KeyValuePairs getExtensions() {
    return extensions;
  }

  public void setExtensions(KeyValuePairs extensions) {
    this.extensions = extensions;
  }

  public VnfInstance links(VnfInstanceLinks links) {
    this.links = links;
    return this;
  }

  /**
   * Get links
   * @return links
  **/
  @ApiModelProperty(value = "")

  @Valid

  public VnfInstanceLinks getLinks() {
    return links;
  }

  public void setLinks(VnfInstanceLinks links) {
    this.links = links;
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
        Objects.equals(this.vnfConfigurableProperties, vnfInstance.vnfConfigurableProperties) &&
        Objects.equals(this.vimConnectionInfo, vnfInstance.vimConnectionInfo) &&
        Objects.equals(this.instantiationState, vnfInstance.instantiationState) &&
        Objects.equals(this.instantiatedVnfInfo, vnfInstance.instantiatedVnfInfo) &&
        Objects.equals(this.metadata, vnfInstance.metadata) &&
        Objects.equals(this.extensions, vnfInstance.extensions) &&
        Objects.equals(this.links, vnfInstance.links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, vnfInstanceName, vnfInstanceDescription, vnfdId, vnfProvider, vnfProductName, vnfSoftwareVersion, vnfdVersion, vnfConfigurableProperties, vimConnectionInfo, instantiationState, instantiatedVnfInfo, metadata, extensions, links);
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
    sb.append("    vnfConfigurableProperties: ").append(toIndentedString(vnfConfigurableProperties)).append("\n");
    sb.append("    vimConnectionInfo: ").append(toIndentedString(vimConnectionInfo)).append("\n");
    sb.append("    instantiationState: ").append(toIndentedString(instantiationState)).append("\n");
    sb.append("    instantiatedVnfInfo: ").append(toIndentedString(instantiatedVnfInfo)).append("\n");
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
    sb.append("    extensions: ").append(toIndentedString(extensions)).append("\n");
    sb.append("    links: ").append(toIndentedString(links)).append("\n");
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

