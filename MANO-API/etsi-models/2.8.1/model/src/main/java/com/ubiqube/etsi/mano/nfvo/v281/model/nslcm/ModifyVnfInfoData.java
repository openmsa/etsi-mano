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
import java.util.Map;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents the information that is requested to be modified for a VNF instance. The information to be modified shall comply with the associated NSD. EXAMPLE. The vnfPkgId attribute value for a particular VNF instance can only be updated with a value that matches the identifier value of a VNF package whose vnfdId is present in the associated profile of the NSD. 
 */
@ApiModel(description = "This type represents the information that is requested to be modified for a VNF instance. The information to be modified shall comply with the associated NSD. EXAMPLE. The vnfPkgId attribute value for a particular VNF instance can only be updated with a value that matches the identifier value of a VNF package whose vnfdId is present in the associated profile of the NSD. ")
@Validated

public class ModifyVnfInfoData   {
  @JsonProperty("vnfInstanceId")
  private String vnfInstanceId = null;

  @JsonProperty("vnfInstanceName")
  private String vnfInstanceName = null;

  @JsonProperty("vnfInstanceDescription")
  private String vnfInstanceDescription = null;

  @JsonProperty("vnfdId")
  private String vnfdId = null;

  @JsonProperty("vnfConfigurableProperties")
  private Map<String, String> vnfConfigurableProperties = null;

  @JsonProperty("metadata")
  private Map<String, String> metadata = null;

  @JsonProperty("extensions")
  private Map<String, String> extensions = null;

  public ModifyVnfInfoData vnfInstanceId(String vnfInstanceId) {
    this.vnfInstanceId = vnfInstanceId;
    return this;
  }

  /**
   * Identifier of the VNF instance. 
   * @return vnfInstanceId
  **/
  @ApiModelProperty(required = true, value = "Identifier of the VNF instance. ")
  @NotNull


  public String getVnfInstanceId() {
    return vnfInstanceId;
  }

  public void setVnfInstanceId(String vnfInstanceId) {
    this.vnfInstanceId = vnfInstanceId;
  }

  public ModifyVnfInfoData vnfInstanceName(String vnfInstanceName) {
    this.vnfInstanceName = vnfInstanceName;
    return this;
  }

  /**
   * New value of the \"vnfInstanceName\" attribute in \"VnfInstance\", or \"null\" to remove the attribute. 
   * @return vnfInstanceName
  **/
  @ApiModelProperty(value = "New value of the \"vnfInstanceName\" attribute in \"VnfInstance\", or \"null\" to remove the attribute. ")


  public String getVnfInstanceName() {
    return vnfInstanceName;
  }

  public void setVnfInstanceName(String vnfInstanceName) {
    this.vnfInstanceName = vnfInstanceName;
  }

  public ModifyVnfInfoData vnfInstanceDescription(String vnfInstanceDescription) {
    this.vnfInstanceDescription = vnfInstanceDescription;
    return this;
  }

  /**
   * New value of the \"vnfInstanceDescription\" attribute in \"VnfInstance\", or \"null\" to remove the attribute. 
   * @return vnfInstanceDescription
  **/
  @ApiModelProperty(value = "New value of the \"vnfInstanceDescription\" attribute in \"VnfInstance\", or \"null\" to remove the attribute. ")


  public String getVnfInstanceDescription() {
    return vnfInstanceDescription;
  }

  public void setVnfInstanceDescription(String vnfInstanceDescription) {
    this.vnfInstanceDescription = vnfInstanceDescription;
  }

  public ModifyVnfInfoData vnfdId(String vnfdId) {
    this.vnfdId = vnfdId;
    return this;
  }

  /**
   * New value of the \"vnfdId\" attribute in \"VnfInstance\". The value \"null\" is not permitted 
   * @return vnfdId
  **/
  @ApiModelProperty(value = "New value of the \"vnfdId\" attribute in \"VnfInstance\". The value \"null\" is not permitted ")


  public String getVnfdId() {
    return vnfdId;
  }

  public void setVnfdId(String vnfdId) {
    this.vnfdId = vnfdId;
  }

  public ModifyVnfInfoData vnfConfigurableProperties(Map<String, String> vnfConfigurableProperties) {
    this.vnfConfigurableProperties = vnfConfigurableProperties;
    return this;
  }

  /**
   * Modifications to entries in the \"vnfConfigurableProperties\" attribute in \"VnfInstance\", as defined below in clause 6.5.3.57. 
   * @return vnfConfigurableProperties
  **/
  @ApiModelProperty(value = "Modifications to entries in the \"vnfConfigurableProperties\" attribute in \"VnfInstance\", as defined below in clause 6.5.3.57. ")

  @Valid

  public Map<String, String> getVnfConfigurableProperties() {
    return vnfConfigurableProperties;
  }

  public void setVnfConfigurableProperties(Map<String, String> vnfConfigurableProperties) {
    this.vnfConfigurableProperties = vnfConfigurableProperties;
  }

  public ModifyVnfInfoData metadata(Map<String, String> metadata) {
    this.metadata = metadata;
    return this;
  }

  /**
   * Modifications to entries in the \"metadata\" attribute in \"VnfInstance\", as defined below in clause 6.5.3.57. 
   * @return metadata
  **/
  @ApiModelProperty(value = "Modifications to entries in the \"metadata\" attribute in \"VnfInstance\", as defined below in clause 6.5.3.57. ")

  @Valid

  public Map<String, String> getMetadata() {
    return metadata;
  }

  public void setMetadata(Map<String, String> metadata) {
    this.metadata = metadata;
  }

  public ModifyVnfInfoData extensions(Map<String, String> extensions) {
    this.extensions = extensions;
    return this;
  }

  /**
   * Modifications to entries in the \"extensions\" attribute in \"VnfInstance\", as defined below in clause 6.5.3.57. 
   * @return extensions
  **/
  @ApiModelProperty(value = "Modifications to entries in the \"extensions\" attribute in \"VnfInstance\", as defined below in clause 6.5.3.57. ")

  @Valid

  public Map<String, String> getExtensions() {
    return extensions;
  }

  public void setExtensions(Map<String, String> extensions) {
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
    ModifyVnfInfoData modifyVnfInfoData = (ModifyVnfInfoData) o;
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
    StringBuilder sb = new StringBuilder();
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
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

