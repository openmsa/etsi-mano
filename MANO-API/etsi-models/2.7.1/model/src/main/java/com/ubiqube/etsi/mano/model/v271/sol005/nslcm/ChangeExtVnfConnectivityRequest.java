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

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.model.v271.sol005.nslcm.ExtVirtualLinkData;
import com.ubiqube.etsi.mano.model.v271.sol005.nslcm.KeyValuePairs;
import com.ubiqube.etsi.mano.model.v271.sol005.nslcm.VimConnectionInfo;
import com.fasterxml.jackson.annotation.JsonCreator;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents request parameters for the \&quot;Change external VNF connectivity\&quot; operation to modify the external connectivity of a VNF instance. 
 */
@ApiModel(description = "This type represents request parameters for the \"Change external VNF connectivity\" operation to modify the external connectivity of a VNF instance. ")
@Validated


public class ChangeExtVnfConnectivityRequest   {
  @JsonProperty("extVirtualLinks")
  @Valid
  private List<ExtVirtualLinkData> extVirtualLinks = new ArrayList<>();

  @JsonProperty("vimConnectionInfo")
  @Valid
  private List<VimConnectionInfo> vimConnectionInfo = null;

  @JsonProperty("additionalParams")
  private KeyValuePairs additionalParams = null;

  public ChangeExtVnfConnectivityRequest extVirtualLinks(List<ExtVirtualLinkData> extVirtualLinks) {
    this.extVirtualLinks = extVirtualLinks;
    return this;
  }

  public ChangeExtVnfConnectivityRequest addExtVirtualLinksItem(ExtVirtualLinkData extVirtualLinksItem) {
    this.extVirtualLinks.add(extVirtualLinksItem);
    return this;
  }

  /**
   * Information about external VLs to change (e.g. connect the VNF to). Entries in the list of external VLs that are unchanged need not be supplied as part of this request. 
   * @return extVirtualLinks
  **/
  @ApiModelProperty(required = true, value = "Information about external VLs to change (e.g. connect the VNF to). Entries in the list of external VLs that are unchanged need not be supplied as part of this request. ")
  @NotNull

  @Valid

  public List<ExtVirtualLinkData> getExtVirtualLinks() {
    return extVirtualLinks;
  }

  public void setExtVirtualLinks(List<ExtVirtualLinkData> extVirtualLinks) {
    this.extVirtualLinks = extVirtualLinks;
  }

  public ChangeExtVnfConnectivityRequest vimConnectionInfo(List<VimConnectionInfo> vimConnectionInfo) {
    this.vimConnectionInfo = vimConnectionInfo;
    return this;
  }

  public ChangeExtVnfConnectivityRequest addVimConnectionInfoItem(VimConnectionInfo vimConnectionInfoItem) {
    if (this.vimConnectionInfo == null) {
      this.vimConnectionInfo = new ArrayList<>();
    }
    this.vimConnectionInfo.add(vimConnectionInfoItem);
    return this;
  }

  /**
   * Information about VIM connections to be used for managing the resources for the VNF instance, or refer to external virtual links. This attribute shall only be supported and may be present if VNF-related resource management in direct mode is applicable. 
   * @return vimConnectionInfo
  **/
  @ApiModelProperty(value = "Information about VIM connections to be used for managing the resources for the VNF instance, or refer to external virtual links. This attribute shall only be supported and may be present if VNF-related resource management in direct mode is applicable. ")

  @Valid

  public List<VimConnectionInfo> getVimConnectionInfo() {
    return vimConnectionInfo;
  }

  public void setVimConnectionInfo(List<VimConnectionInfo> vimConnectionInfo) {
    this.vimConnectionInfo = vimConnectionInfo;
  }

  public ChangeExtVnfConnectivityRequest additionalParams(KeyValuePairs additionalParams) {
    this.additionalParams = additionalParams;
    return this;
  }

  /**
   * Additional input parameters for the instantiation process, specific to the VNF being instantiated, as declared in the VNFD as part of \"ChangeExtVnfConnectivityOpConfig\".\". 
   * @return additionalParams
  **/
  @ApiModelProperty(value = "Additional input parameters for the instantiation process, specific to the VNF being instantiated, as declared in the VNFD as part of \"ChangeExtVnfConnectivityOpConfig\".\". ")

  @Valid

  public KeyValuePairs getAdditionalParams() {
    return additionalParams;
  }

  public void setAdditionalParams(KeyValuePairs additionalParams) {
    this.additionalParams = additionalParams;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ChangeExtVnfConnectivityRequest changeExtVnfConnectivityRequest = (ChangeExtVnfConnectivityRequest) o;
    return Objects.equals(this.extVirtualLinks, changeExtVnfConnectivityRequest.extVirtualLinks) &&
        Objects.equals(this.vimConnectionInfo, changeExtVnfConnectivityRequest.vimConnectionInfo) &&
        Objects.equals(this.additionalParams, changeExtVnfConnectivityRequest.additionalParams);
  }

  @Override
  public int hashCode() {
    return Objects.hash(extVirtualLinks, vimConnectionInfo, additionalParams);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ChangeExtVnfConnectivityRequest {\n");
    
    sb.append("    extVirtualLinks: ").append(toIndentedString(extVirtualLinks)).append("\n");
    sb.append("    vimConnectionInfo: ").append(toIndentedString(vimConnectionInfo)).append("\n");
    sb.append("    additionalParams: ").append(toIndentedString(additionalParams)).append("\n");
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

