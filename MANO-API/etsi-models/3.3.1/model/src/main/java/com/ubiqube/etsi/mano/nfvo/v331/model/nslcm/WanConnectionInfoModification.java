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
package com.ubiqube.etsi.mano.nfvo.v331.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.ConnectivityServiceEndpointInfo;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.MscsEndpointInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents attribute modifications that were performed on WAN connection information. The attributes that can be included consist of those requested to be modified explicitly with the \&quot;UpdateNsRequest\&quot; data structure. It shall comply with the provisions defined in table 6.5.3.93-1. 
 */
@Schema(description = "This type represents attribute modifications that were performed on WAN connection information. The attributes that can be included consist of those requested to be modified explicitly with the \"UpdateNsRequest\" data structure. It shall comply with the provisions defined in table 6.5.3.93-1. ")
@Validated


public class WanConnectionInfoModification   {
  @JsonProperty("wanConnectionInfoId")
  private String wanConnectionInfoId = null;

  @JsonProperty("mscsName")
  private String mscsName = null;

  @JsonProperty("mscsDescription")
  private String mscsDescription = null;

  @JsonProperty("mscsEndpoints")
  @Valid
  private List<MscsEndpointInfo> mscsEndpoints = null;

  @JsonProperty("removeMscsEndpointIds")
  @Valid
  private List<String> removeMscsEndpointIds = null;

  @JsonProperty("connectivityServiceEndpoints")
  @Valid
  private List<ConnectivityServiceEndpointInfo> connectivityServiceEndpoints = null;

  @JsonProperty("removeConnectivityServiceEndpoints")
  @Valid
  private List<String> removeConnectivityServiceEndpoints = null;

  public WanConnectionInfoModification wanConnectionInfoId(String wanConnectionInfoId) {
    this.wanConnectionInfoId = wanConnectionInfoId;
    return this;
  }

  /**
   * Get wanConnectionInfoId
   * @return wanConnectionInfoId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getWanConnectionInfoId() {
    return wanConnectionInfoId;
  }

  public void setWanConnectionInfoId(String wanConnectionInfoId) {
    this.wanConnectionInfoId = wanConnectionInfoId;
  }

  public WanConnectionInfoModification mscsName(String mscsName) {
    this.mscsName = mscsName;
    return this;
  }

  /**
   * If present, this attribute signals modifications of the \"mscsName\" attribute in \"MscsInfo\" as defined in clause 6.5.3.82. 
   * @return mscsName
   **/
  @Schema(description = "If present, this attribute signals modifications of the \"mscsName\" attribute in \"MscsInfo\" as defined in clause 6.5.3.82. ")
  
    public String getMscsName() {
    return mscsName;
  }

  public void setMscsName(String mscsName) {
    this.mscsName = mscsName;
  }

  public WanConnectionInfoModification mscsDescription(String mscsDescription) {
    this.mscsDescription = mscsDescription;
    return this;
  }

  /**
   * If present, this attribute signals modifications of the \"mscsDescription\" attribute in \"MscsInfo\" as defined in clause 6.5.3.82. 
   * @return mscsDescription
   **/
  @Schema(description = "If present, this attribute signals modifications of the \"mscsDescription\" attribute in \"MscsInfo\" as defined in clause 6.5.3.82. ")
  
    public String getMscsDescription() {
    return mscsDescription;
  }

  public void setMscsDescription(String mscsDescription) {
    this.mscsDescription = mscsDescription;
  }

  public WanConnectionInfoModification mscsEndpoints(List<MscsEndpointInfo> mscsEndpoints) {
    this.mscsEndpoints = mscsEndpoints;
    return this;
  }

  public WanConnectionInfoModification addMscsEndpointsItem(MscsEndpointInfo mscsEndpointsItem) {
    if (this.mscsEndpoints == null) {
      this.mscsEndpoints = new ArrayList<>();
    }
    this.mscsEndpoints.add(mscsEndpointsItem);
    return this;
  }

  /**
   * If present, this attribute signals modifications of certain entries in the \"mscsEndpoints\" attribute array in \"MscsInfo\", as defined in clause 6.5.3.82. 
   * @return mscsEndpoints
   **/
  @Schema(description = "If present, this attribute signals modifications of certain entries in the \"mscsEndpoints\" attribute array in \"MscsInfo\", as defined in clause 6.5.3.82. ")
      @Valid
    public List<MscsEndpointInfo> getMscsEndpoints() {
    return mscsEndpoints;
  }

  public void setMscsEndpoints(List<MscsEndpointInfo> mscsEndpoints) {
    this.mscsEndpoints = mscsEndpoints;
  }

  public WanConnectionInfoModification removeMscsEndpointIds(List<String> removeMscsEndpointIds) {
    this.removeMscsEndpointIds = removeMscsEndpointIds;
    return this;
  }

  public WanConnectionInfoModification addRemoveMscsEndpointIdsItem(String removeMscsEndpointIdsItem) {
    if (this.removeMscsEndpointIds == null) {
      this.removeMscsEndpointIds = new ArrayList<>();
    }
    this.removeMscsEndpointIds.add(removeMscsEndpointIdsItem);
    return this;
  }

  /**
   * If present, this attribute signals the deletion of certain entries in the \"mscsEndpoints\" attribute array in \"MscsInfo\", as defined in clause 6.5.3.82. 
   * @return removeMscsEndpointIds
   **/
  @Schema(description = "If present, this attribute signals the deletion of certain entries in the \"mscsEndpoints\" attribute array in \"MscsInfo\", as defined in clause 6.5.3.82. ")
  
    public List<String> getRemoveMscsEndpointIds() {
    return removeMscsEndpointIds;
  }

  public void setRemoveMscsEndpointIds(List<String> removeMscsEndpointIds) {
    this.removeMscsEndpointIds = removeMscsEndpointIds;
  }

  public WanConnectionInfoModification connectivityServiceEndpoints(List<ConnectivityServiceEndpointInfo> connectivityServiceEndpoints) {
    this.connectivityServiceEndpoints = connectivityServiceEndpoints;
    return this;
  }

  public WanConnectionInfoModification addConnectivityServiceEndpointsItem(ConnectivityServiceEndpointInfo connectivityServiceEndpointsItem) {
    if (this.connectivityServiceEndpoints == null) {
      this.connectivityServiceEndpoints = new ArrayList<>();
    }
    this.connectivityServiceEndpoints.add(connectivityServiceEndpointsItem);
    return this;
  }

  /**
   * If present, this attribute signals modifications of certain entries in the \"connectivityServiceEndpoints\" attribute array in \"WanConnectionProtocolInfo\", as defined in clause 6.5.3.91. 
   * @return connectivityServiceEndpoints
   **/
  @Schema(description = "If present, this attribute signals modifications of certain entries in the \"connectivityServiceEndpoints\" attribute array in \"WanConnectionProtocolInfo\", as defined in clause 6.5.3.91. ")
      @Valid
    public List<ConnectivityServiceEndpointInfo> getConnectivityServiceEndpoints() {
    return connectivityServiceEndpoints;
  }

  public void setConnectivityServiceEndpoints(List<ConnectivityServiceEndpointInfo> connectivityServiceEndpoints) {
    this.connectivityServiceEndpoints = connectivityServiceEndpoints;
  }

  public WanConnectionInfoModification removeConnectivityServiceEndpoints(List<String> removeConnectivityServiceEndpoints) {
    this.removeConnectivityServiceEndpoints = removeConnectivityServiceEndpoints;
    return this;
  }

  public WanConnectionInfoModification addRemoveConnectivityServiceEndpointsItem(String removeConnectivityServiceEndpointsItem) {
    if (this.removeConnectivityServiceEndpoints == null) {
      this.removeConnectivityServiceEndpoints = new ArrayList<>();
    }
    this.removeConnectivityServiceEndpoints.add(removeConnectivityServiceEndpointsItem);
    return this;
  }

  /**
   * If present, this attribute signals the deletion of certain entries in the \"connectivityServiceEndpoints\" attribute array in \"WanConnectionProtocolInfo\", as defined in clause 6.5.3.91. 
   * @return removeConnectivityServiceEndpoints
   **/
  @Schema(description = "If present, this attribute signals the deletion of certain entries in the \"connectivityServiceEndpoints\" attribute array in \"WanConnectionProtocolInfo\", as defined in clause 6.5.3.91. ")
  
    public List<String> getRemoveConnectivityServiceEndpoints() {
    return removeConnectivityServiceEndpoints;
  }

  public void setRemoveConnectivityServiceEndpoints(List<String> removeConnectivityServiceEndpoints) {
    this.removeConnectivityServiceEndpoints = removeConnectivityServiceEndpoints;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WanConnectionInfoModification wanConnectionInfoModification = (WanConnectionInfoModification) o;
    return Objects.equals(this.wanConnectionInfoId, wanConnectionInfoModification.wanConnectionInfoId) &&
        Objects.equals(this.mscsName, wanConnectionInfoModification.mscsName) &&
        Objects.equals(this.mscsDescription, wanConnectionInfoModification.mscsDescription) &&
        Objects.equals(this.mscsEndpoints, wanConnectionInfoModification.mscsEndpoints) &&
        Objects.equals(this.removeMscsEndpointIds, wanConnectionInfoModification.removeMscsEndpointIds) &&
        Objects.equals(this.connectivityServiceEndpoints, wanConnectionInfoModification.connectivityServiceEndpoints) &&
        Objects.equals(this.removeConnectivityServiceEndpoints, wanConnectionInfoModification.removeConnectivityServiceEndpoints);
  }

  @Override
  public int hashCode() {
    return Objects.hash(wanConnectionInfoId, mscsName, mscsDescription, mscsEndpoints, removeMscsEndpointIds, connectivityServiceEndpoints, removeConnectivityServiceEndpoints);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WanConnectionInfoModification {\n");
    
    sb.append("    wanConnectionInfoId: ").append(toIndentedString(wanConnectionInfoId)).append("\n");
    sb.append("    mscsName: ").append(toIndentedString(mscsName)).append("\n");
    sb.append("    mscsDescription: ").append(toIndentedString(mscsDescription)).append("\n");
    sb.append("    mscsEndpoints: ").append(toIndentedString(mscsEndpoints)).append("\n");
    sb.append("    removeMscsEndpointIds: ").append(toIndentedString(removeMscsEndpointIds)).append("\n");
    sb.append("    connectivityServiceEndpoints: ").append(toIndentedString(connectivityServiceEndpoints)).append("\n");
    sb.append("    removeConnectivityServiceEndpoints: ").append(toIndentedString(removeConnectivityServiceEndpoints)).append("\n");
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
