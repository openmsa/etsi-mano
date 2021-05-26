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
import com.ubiqube.etsi.mano.nfvo.v281.model.nslcm.ExtManagedVirtualLinkData;
import com.ubiqube.etsi.mano.nfvo.v281.model.nslcm.ExtVirtualLinkData;
import com.ubiqube.etsi.mano.nfvo.v281.model.nslcm.KeyValuePairs;
import com.ubiqube.etsi.mano.nfvo.v281.model.nslcm.VnfLocationConstraint;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents the information related to a SAP of a NS. The InstantiateVnfData data type specifies the parameters that are needed for VNF instantiation. This information element is used for the bottom-up NS creation when the OSS/BSS explicitly requests VNF instantiation for a given NS. When the NFVO invokes the Instantiate VNF update operation, a set of these parameters are then passed by the NFVO to the VNFM. It shall comply with the provisions defined in Table 6.5.3.24-1. 
 */
@ApiModel(description = "This type represents the information related to a SAP of a NS. The InstantiateVnfData data type specifies the parameters that are needed for VNF instantiation. This information element is used for the bottom-up NS creation when the OSS/BSS explicitly requests VNF instantiation for a given NS. When the NFVO invokes the Instantiate VNF update operation, a set of these parameters are then passed by the NFVO to the VNFM. It shall comply with the provisions defined in Table 6.5.3.24-1. ")
@Validated

public class InstantiateVnfData   {
  @JsonProperty("vnfdId")
  private String vnfdId = null;

  @JsonProperty("vnfFlavourId")
  private String vnfFlavourId = null;

  @JsonProperty("vnfInstantiationLevelId")
  private String vnfInstantiationLevelId = null;

  @JsonProperty("vnfInstanceName")
  private String vnfInstanceName = null;

  @JsonProperty("vnfInstanceDescription")
  private String vnfInstanceDescription = null;

  @JsonProperty("extVirtualLinks")
  @Valid
  private List<ExtVirtualLinkData> extVirtualLinks = null;

  @JsonProperty("extManagedVirtualLinks")
  @Valid
  private List<ExtManagedVirtualLinkData> extManagedVirtualLinks = null;

  @JsonProperty("localizationLanguage")
  private String localizationLanguage = null;

  @JsonProperty("vnfConfigurableProperties")
  private KeyValuePairs vnfConfigurableProperties = null;

  @JsonProperty("additionalParams")
  private KeyValuePairs additionalParams = null;

  @JsonProperty("metadata")
  private KeyValuePairs metadata = null;

  @JsonProperty("extensions")
  private KeyValuePairs extensions = null;

  @JsonProperty("locationConstraints")
  private VnfLocationConstraint locationConstraints = null;

  public InstantiateVnfData vnfdId(String vnfdId) {
    this.vnfdId = vnfdId;
    return this;
  }

  /**
   * Information sufficient to identify the VNFD which defines the VNF to be instantiated. 
   * @return vnfdId
  **/
  @ApiModelProperty(required = true, value = "Information sufficient to identify the VNFD which defines the VNF to be instantiated. ")
  @NotNull


  public String getVnfdId() {
    return vnfdId;
  }

  public void setVnfdId(String vnfdId) {
    this.vnfdId = vnfdId;
  }

  public InstantiateVnfData vnfFlavourId(String vnfFlavourId) {
    this.vnfFlavourId = vnfFlavourId;
    return this;
  }

  /**
   * Identifier of the VNF deployment flavor to be instantiated. 
   * @return vnfFlavourId
  **/
  @ApiModelProperty(required = true, value = "Identifier of the VNF deployment flavor to be instantiated. ")
  @NotNull


  public String getVnfFlavourId() {
    return vnfFlavourId;
  }

  public void setVnfFlavourId(String vnfFlavourId) {
    this.vnfFlavourId = vnfFlavourId;
  }

  public InstantiateVnfData vnfInstantiationLevelId(String vnfInstantiationLevelId) {
    this.vnfInstantiationLevelId = vnfInstantiationLevelId;
    return this;
  }

  /**
   * Identifier of the instantiation level of the deployment flavor to be instantiated. If not present, the default instantiation level as declared in the VNFD is instantiated. 
   * @return vnfInstantiationLevelId
  **/
  @ApiModelProperty(value = "Identifier of the instantiation level of the deployment flavor to be instantiated. If not present, the default instantiation level as declared in the VNFD is instantiated. ")


  public String getVnfInstantiationLevelId() {
    return vnfInstantiationLevelId;
  }

  public void setVnfInstantiationLevelId(String vnfInstantiationLevelId) {
    this.vnfInstantiationLevelId = vnfInstantiationLevelId;
  }

  public InstantiateVnfData vnfInstanceName(String vnfInstanceName) {
    this.vnfInstanceName = vnfInstanceName;
    return this;
  }

  /**
   * Human-readable name of the VNF instance to be created. 
   * @return vnfInstanceName
  **/
  @ApiModelProperty(value = "Human-readable name of the VNF instance to be created. ")


  public String getVnfInstanceName() {
    return vnfInstanceName;
  }

  public void setVnfInstanceName(String vnfInstanceName) {
    this.vnfInstanceName = vnfInstanceName;
  }

  public InstantiateVnfData vnfInstanceDescription(String vnfInstanceDescription) {
    this.vnfInstanceDescription = vnfInstanceDescription;
    return this;
  }

  /**
   * Human-readable description of the VNF instance to be created. 
   * @return vnfInstanceDescription
  **/
  @ApiModelProperty(value = "Human-readable description of the VNF instance to be created. ")


  public String getVnfInstanceDescription() {
    return vnfInstanceDescription;
  }

  public void setVnfInstanceDescription(String vnfInstanceDescription) {
    this.vnfInstanceDescription = vnfInstanceDescription;
  }

  public InstantiateVnfData extVirtualLinks(List<ExtVirtualLinkData> extVirtualLinks) {
    this.extVirtualLinks = extVirtualLinks;
    return this;
  }

  public InstantiateVnfData addExtVirtualLinksItem(ExtVirtualLinkData extVirtualLinksItem) {
    if (this.extVirtualLinks == null) {
      this.extVirtualLinks = new ArrayList<>();
    }
    this.extVirtualLinks.add(extVirtualLinksItem);
    return this;
  }

  /**
   * Information about external VLs to connect the VNF to. 
   * @return extVirtualLinks
  **/
  @ApiModelProperty(value = "Information about external VLs to connect the VNF to. ")

  @Valid

  public List<ExtVirtualLinkData> getExtVirtualLinks() {
    return extVirtualLinks;
  }

  public void setExtVirtualLinks(List<ExtVirtualLinkData> extVirtualLinks) {
    this.extVirtualLinks = extVirtualLinks;
  }

  public InstantiateVnfData extManagedVirtualLinks(List<ExtManagedVirtualLinkData> extManagedVirtualLinks) {
    this.extManagedVirtualLinks = extManagedVirtualLinks;
    return this;
  }

  public InstantiateVnfData addExtManagedVirtualLinksItem(ExtManagedVirtualLinkData extManagedVirtualLinksItem) {
    if (this.extManagedVirtualLinks == null) {
      this.extManagedVirtualLinks = new ArrayList<>();
    }
    this.extManagedVirtualLinks.add(extManagedVirtualLinksItem);
    return this;
  }

  /**
   * Information about internal VLs that are managed by other entities than the VNFM. 
   * @return extManagedVirtualLinks
  **/
  @ApiModelProperty(value = "Information about internal VLs that are managed by other entities than the VNFM. ")

  @Valid

  public List<ExtManagedVirtualLinkData> getExtManagedVirtualLinks() {
    return extManagedVirtualLinks;
  }

  public void setExtManagedVirtualLinks(List<ExtManagedVirtualLinkData> extManagedVirtualLinks) {
    this.extManagedVirtualLinks = extManagedVirtualLinks;
  }

  public InstantiateVnfData localizationLanguage(String localizationLanguage) {
    this.localizationLanguage = localizationLanguage;
    return this;
  }

  /**
   * Localization language of the VNF to be instantiated. The value shall comply with the format defined in IETF RFC 5646. 
   * @return localizationLanguage
  **/
  @ApiModelProperty(value = "Localization language of the VNF to be instantiated. The value shall comply with the format defined in IETF RFC 5646. ")


  public String getLocalizationLanguage() {
    return localizationLanguage;
  }

  public void setLocalizationLanguage(String localizationLanguage) {
    this.localizationLanguage = localizationLanguage;
  }

  public InstantiateVnfData vnfConfigurableProperties(KeyValuePairs vnfConfigurableProperties) {
    this.vnfConfigurableProperties = vnfConfigurableProperties;
    return this;
  }

  /**
   * Values for the \"vnfConfigurableProperties\" input parameter of the Instantiate VNF operation defined in ETSI GS NFV-SOL 003 [4]. 
   * @return vnfConfigurableProperties
  **/
  @ApiModelProperty(value = "Values for the \"vnfConfigurableProperties\" input parameter of the Instantiate VNF operation defined in ETSI GS NFV-SOL 003 [4]. ")

  @Valid

  public KeyValuePairs getVnfConfigurableProperties() {
    return vnfConfigurableProperties;
  }

  public void setVnfConfigurableProperties(KeyValuePairs vnfConfigurableProperties) {
    this.vnfConfigurableProperties = vnfConfigurableProperties;
  }

  public InstantiateVnfData additionalParams(KeyValuePairs additionalParams) {
    this.additionalParams = additionalParams;
    return this;
  }

  /**
   * Additional input parameters for the instantiation process, specific to the VNF being instantiated. 
   * @return additionalParams
  **/
  @ApiModelProperty(value = "Additional input parameters for the instantiation process, specific to the VNF being instantiated. ")

  @Valid

  public KeyValuePairs getAdditionalParams() {
    return additionalParams;
  }

  public void setAdditionalParams(KeyValuePairs additionalParams) {
    this.additionalParams = additionalParams;
  }

  public InstantiateVnfData metadata(KeyValuePairs metadata) {
    this.metadata = metadata;
    return this;
  }

  /**
   * This attribute provides values for the \"metadata\" input parameter of the Create VNF Identifier operation. 
   * @return metadata
  **/
  @ApiModelProperty(value = "This attribute provides values for the \"metadata\" input parameter of the Create VNF Identifier operation. ")

  @Valid

  public KeyValuePairs getMetadata() {
    return metadata;
  }

  public void setMetadata(KeyValuePairs metadata) {
    this.metadata = metadata;
  }

  public InstantiateVnfData extensions(KeyValuePairs extensions) {
    this.extensions = extensions;
    return this;
  }

  /**
   * This attribute provides values for the \"extensions\" input parameter of the Instantiate VNF operation. 
   * @return extensions
  **/
  @ApiModelProperty(value = "This attribute provides values for the \"extensions\" input parameter of the Instantiate VNF operation. ")

  @Valid

  public KeyValuePairs getExtensions() {
    return extensions;
  }

  public void setExtensions(KeyValuePairs extensions) {
    this.extensions = extensions;
  }

  public InstantiateVnfData locationConstraints(VnfLocationConstraint locationConstraints) {
    this.locationConstraints = locationConstraints;
    return this;
  }

  /**
   * Defines the location constraints for the VNF to be instantiated as part of the NS Update. An example can be a constraint for the VNF to be in a specific geographic location. 
   * @return locationConstraints
  **/
  @ApiModelProperty(value = "Defines the location constraints for the VNF to be instantiated as part of the NS Update. An example can be a constraint for the VNF to be in a specific geographic location. ")

  @Valid

  public VnfLocationConstraint getLocationConstraints() {
    return locationConstraints;
  }

  public void setLocationConstraints(VnfLocationConstraint locationConstraints) {
    this.locationConstraints = locationConstraints;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InstantiateVnfData instantiateVnfData = (InstantiateVnfData) o;
    return Objects.equals(this.vnfdId, instantiateVnfData.vnfdId) &&
        Objects.equals(this.vnfFlavourId, instantiateVnfData.vnfFlavourId) &&
        Objects.equals(this.vnfInstantiationLevelId, instantiateVnfData.vnfInstantiationLevelId) &&
        Objects.equals(this.vnfInstanceName, instantiateVnfData.vnfInstanceName) &&
        Objects.equals(this.vnfInstanceDescription, instantiateVnfData.vnfInstanceDescription) &&
        Objects.equals(this.extVirtualLinks, instantiateVnfData.extVirtualLinks) &&
        Objects.equals(this.extManagedVirtualLinks, instantiateVnfData.extManagedVirtualLinks) &&
        Objects.equals(this.localizationLanguage, instantiateVnfData.localizationLanguage) &&
        Objects.equals(this.vnfConfigurableProperties, instantiateVnfData.vnfConfigurableProperties) &&
        Objects.equals(this.additionalParams, instantiateVnfData.additionalParams) &&
        Objects.equals(this.metadata, instantiateVnfData.metadata) &&
        Objects.equals(this.extensions, instantiateVnfData.extensions) &&
        Objects.equals(this.locationConstraints, instantiateVnfData.locationConstraints);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vnfdId, vnfFlavourId, vnfInstantiationLevelId, vnfInstanceName, vnfInstanceDescription, extVirtualLinks, extManagedVirtualLinks, localizationLanguage, vnfConfigurableProperties, additionalParams, metadata, extensions, locationConstraints);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InstantiateVnfData {\n");
    
    sb.append("    vnfdId: ").append(toIndentedString(vnfdId)).append("\n");
    sb.append("    vnfFlavourId: ").append(toIndentedString(vnfFlavourId)).append("\n");
    sb.append("    vnfInstantiationLevelId: ").append(toIndentedString(vnfInstantiationLevelId)).append("\n");
    sb.append("    vnfInstanceName: ").append(toIndentedString(vnfInstanceName)).append("\n");
    sb.append("    vnfInstanceDescription: ").append(toIndentedString(vnfInstanceDescription)).append("\n");
    sb.append("    extVirtualLinks: ").append(toIndentedString(extVirtualLinks)).append("\n");
    sb.append("    extManagedVirtualLinks: ").append(toIndentedString(extManagedVirtualLinks)).append("\n");
    sb.append("    localizationLanguage: ").append(toIndentedString(localizationLanguage)).append("\n");
    sb.append("    vnfConfigurableProperties: ").append(toIndentedString(vnfConfigurableProperties)).append("\n");
    sb.append("    additionalParams: ").append(toIndentedString(additionalParams)).append("\n");
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
    sb.append("    extensions: ").append(toIndentedString(extensions)).append("\n");
    sb.append("    locationConstraints: ").append(toIndentedString(locationConstraints)).append("\n");
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

