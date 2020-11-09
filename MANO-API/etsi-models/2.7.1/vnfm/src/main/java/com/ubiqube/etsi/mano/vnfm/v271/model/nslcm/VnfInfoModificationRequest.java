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
package com.ubiqube.etsi.mano.vnfm.v271.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.vnfm.v271.model.nslcm.KeyValuePairs;
import com.ubiqube.etsi.mano.vnfm.v271.model.nslcm.VimConnectionInfo;
import com.fasterxml.jackson.annotation.JsonCreator;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents attribute modifications for an \&quot;Individual VNF instance\&quot; resource, i.e. modifications to a resource representation based on the \&quot;VnfInstance\&quot; data type. 
 */
@ApiModel(description = "This type represents attribute modifications for an \"Individual VNF instance\" resource, i.e. modifications to a resource representation based on the \"VnfInstance\" data type. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-11-09T10:14:43.989+01:00")

public class VnfInfoModificationRequest   {
  @JsonProperty("vnfInstanceName")
  private String vnfInstanceName = null;

  @JsonProperty("vnfInstanceDescription")
  private String vnfInstanceDescription = null;

  @JsonProperty("vnfPkgId")
  private String vnfPkgId = null;

  @JsonProperty("vnfConfigurableProperties")
  private KeyValuePairs vnfConfigurableProperties = null;

  @JsonProperty("metadata")
  private KeyValuePairs metadata = null;

  @JsonProperty("extensions")
  private KeyValuePairs extensions = null;

  @JsonProperty("vimConnectionInfo")
  @Valid
  private List<VimConnectionInfo> vimConnectionInfo = null;

  @JsonProperty("vimConnectionInfoDeleteIds")
  @Valid
  private List<String> vimConnectionInfoDeleteIds = null;

  public VnfInfoModificationRequest vnfInstanceName(String vnfInstanceName) {
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

  public VnfInfoModificationRequest vnfInstanceDescription(String vnfInstanceDescription) {
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

  public VnfInfoModificationRequest vnfPkgId(String vnfPkgId) {
    this.vnfPkgId = vnfPkgId;
    return this;
  }

  /**
   * New value of the \"vnfPkgId\" attribute in \"VnfInstance\". The value \"null\" is not permitted. 
   * @return vnfPkgId
  **/
  @ApiModelProperty(value = "New value of the \"vnfPkgId\" attribute in \"VnfInstance\". The value \"null\" is not permitted. ")


  public String getVnfPkgId() {
    return vnfPkgId;
  }

  public void setVnfPkgId(String vnfPkgId) {
    this.vnfPkgId = vnfPkgId;
  }

  public VnfInfoModificationRequest vnfConfigurableProperties(KeyValuePairs vnfConfigurableProperties) {
    this.vnfConfigurableProperties = vnfConfigurableProperties;
    return this;
  }

  /**
   * Modifications of the \"vnfConfigurableProperties\" attribute in \"VnfInstance\". If present, these modifications shall be applied according to the rules of JSON Merge PATCH (see IETF RFC 7396). 
   * @return vnfConfigurableProperties
  **/
  @ApiModelProperty(value = "Modifications of the \"vnfConfigurableProperties\" attribute in \"VnfInstance\". If present, these modifications shall be applied according to the rules of JSON Merge PATCH (see IETF RFC 7396). ")

  @Valid

  public KeyValuePairs getVnfConfigurableProperties() {
    return vnfConfigurableProperties;
  }

  public void setVnfConfigurableProperties(KeyValuePairs vnfConfigurableProperties) {
    this.vnfConfigurableProperties = vnfConfigurableProperties;
  }

  public VnfInfoModificationRequest metadata(KeyValuePairs metadata) {
    this.metadata = metadata;
    return this;
  }

  /**
   * Modifications of the \"metadata\" attribute in \"VnfInstance\". If present, these modifications shall be applied according to the rules of JSON Merge PATCH (see IETF RFC 7396). 
   * @return metadata
  **/
  @ApiModelProperty(value = "Modifications of the \"metadata\" attribute in \"VnfInstance\". If present, these modifications shall be applied according to the rules of JSON Merge PATCH (see IETF RFC 7396). ")

  @Valid

  public KeyValuePairs getMetadata() {
    return metadata;
  }

  public void setMetadata(KeyValuePairs metadata) {
    this.metadata = metadata;
  }

  public VnfInfoModificationRequest extensions(KeyValuePairs extensions) {
    this.extensions = extensions;
    return this;
  }

  /**
   * Modifications of the \"extensions\" attribute in \"VnfInstance\". If present, these modifications shall be applied according to the rules of JSON Merge PATCH (see IETF RFC 7396). 
   * @return extensions
  **/
  @ApiModelProperty(value = "Modifications of the \"extensions\" attribute in \"VnfInstance\". If present, these modifications shall be applied according to the rules of JSON Merge PATCH (see IETF RFC 7396). ")

  @Valid

  public KeyValuePairs getExtensions() {
    return extensions;
  }

  public void setExtensions(KeyValuePairs extensions) {
    this.extensions = extensions;
  }

  public VnfInfoModificationRequest vimConnectionInfo(List<VimConnectionInfo> vimConnectionInfo) {
    this.vimConnectionInfo = vimConnectionInfo;
    return this;
  }

  public VnfInfoModificationRequest addVimConnectionInfoItem(VimConnectionInfo vimConnectionInfoItem) {
    if (this.vimConnectionInfo == null) {
      this.vimConnectionInfo = new ArrayList<>();
    }
    this.vimConnectionInfo.add(vimConnectionInfoItem);
    return this;
  }

  /**
   * New content of certain entries in the \"vimConnectionInfo\" attribute array in \"VnfInstance\", as defined below this table. 
   * @return vimConnectionInfo
  **/
  @ApiModelProperty(value = "New content of certain entries in the \"vimConnectionInfo\" attribute array in \"VnfInstance\", as defined below this table. ")

  @Valid

  public List<VimConnectionInfo> getVimConnectionInfo() {
    return vimConnectionInfo;
  }

  public void setVimConnectionInfo(List<VimConnectionInfo> vimConnectionInfo) {
    this.vimConnectionInfo = vimConnectionInfo;
  }

  public VnfInfoModificationRequest vimConnectionInfoDeleteIds(List<String> vimConnectionInfoDeleteIds) {
    this.vimConnectionInfoDeleteIds = vimConnectionInfoDeleteIds;
    return this;
  }

  public VnfInfoModificationRequest addVimConnectionInfoDeleteIdsItem(String vimConnectionInfoDeleteIdsItem) {
    if (this.vimConnectionInfoDeleteIds == null) {
      this.vimConnectionInfoDeleteIds = new ArrayList<>();
    }
    this.vimConnectionInfoDeleteIds.add(vimConnectionInfoDeleteIdsItem);
    return this;
  }

  /**
   * List of identifiers entries to be deleted from the \"vimConnectionInfo\" attribute array in \"VnfInstance\", to be used as \"deleteIdList\" as defined below this table. 
   * @return vimConnectionInfoDeleteIds
  **/
  @ApiModelProperty(value = "List of identifiers entries to be deleted from the \"vimConnectionInfo\" attribute array in \"VnfInstance\", to be used as \"deleteIdList\" as defined below this table. ")


  public List<String> getVimConnectionInfoDeleteIds() {
    return vimConnectionInfoDeleteIds;
  }

  public void setVimConnectionInfoDeleteIds(List<String> vimConnectionInfoDeleteIds) {
    this.vimConnectionInfoDeleteIds = vimConnectionInfoDeleteIds;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VnfInfoModificationRequest vnfInfoModificationRequest = (VnfInfoModificationRequest) o;
    return Objects.equals(this.vnfInstanceName, vnfInfoModificationRequest.vnfInstanceName) &&
        Objects.equals(this.vnfInstanceDescription, vnfInfoModificationRequest.vnfInstanceDescription) &&
        Objects.equals(this.vnfPkgId, vnfInfoModificationRequest.vnfPkgId) &&
        Objects.equals(this.vnfConfigurableProperties, vnfInfoModificationRequest.vnfConfigurableProperties) &&
        Objects.equals(this.metadata, vnfInfoModificationRequest.metadata) &&
        Objects.equals(this.extensions, vnfInfoModificationRequest.extensions) &&
        Objects.equals(this.vimConnectionInfo, vnfInfoModificationRequest.vimConnectionInfo) &&
        Objects.equals(this.vimConnectionInfoDeleteIds, vnfInfoModificationRequest.vimConnectionInfoDeleteIds);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vnfInstanceName, vnfInstanceDescription, vnfPkgId, vnfConfigurableProperties, metadata, extensions, vimConnectionInfo, vimConnectionInfoDeleteIds);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfInfoModificationRequest {\n");
    
    sb.append("    vnfInstanceName: ").append(toIndentedString(vnfInstanceName)).append("\n");
    sb.append("    vnfInstanceDescription: ").append(toIndentedString(vnfInstanceDescription)).append("\n");
    sb.append("    vnfPkgId: ").append(toIndentedString(vnfPkgId)).append("\n");
    sb.append("    vnfConfigurableProperties: ").append(toIndentedString(vnfConfigurableProperties)).append("\n");
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
    sb.append("    extensions: ").append(toIndentedString(extensions)).append("\n");
    sb.append("    vimConnectionInfo: ").append(toIndentedString(vimConnectionInfo)).append("\n");
    sb.append("    vimConnectionInfoDeleteIds: ").append(toIndentedString(vimConnectionInfoDeleteIds)).append("\n");
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

