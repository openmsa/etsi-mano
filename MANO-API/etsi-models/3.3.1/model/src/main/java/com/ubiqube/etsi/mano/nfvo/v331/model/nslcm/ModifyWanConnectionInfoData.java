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
 * This type represents attribute modifications for WAN connection information. The attributes of the \&quot;WanConnectionInfo\&quot; that can be modified according to the provisions of the \&quot;UpdateNsRequest\&quot; in clause 6.5.2.12 related to WAN connection information are included in the \&quot;ModifyWanConnectionInfoData\&quot; data type. It shall comply with the provisions defined in table 6.5.3.92-1. 
 */
@Schema(description = "This type represents attribute modifications for WAN connection information. The attributes of the \"WanConnectionInfo\" that can be modified according to the provisions of the \"UpdateNsRequest\" in clause 6.5.2.12 related to WAN connection information are included in the \"ModifyWanConnectionInfoData\" data type. It shall comply with the provisions defined in table 6.5.3.92-1. ")
@Validated


public class ModifyWanConnectionInfoData   {
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

  public ModifyWanConnectionInfoData wanConnectionInfoId(String wanConnectionInfoId) {
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

  public ModifyWanConnectionInfoData mscsName(String mscsName) {
    this.mscsName = mscsName;
    return this;
  }

  /**
   * New value of the \"mscsName\" attribute in \"MscsInfo\", or \"null\" to remove the attribute. 
   * @return mscsName
   **/
  @Schema(description = "New value of the \"mscsName\" attribute in \"MscsInfo\", or \"null\" to remove the attribute. ")
  
    public String getMscsName() {
    return mscsName;
  }

  public void setMscsName(String mscsName) {
    this.mscsName = mscsName;
  }

  public ModifyWanConnectionInfoData mscsDescription(String mscsDescription) {
    this.mscsDescription = mscsDescription;
    return this;
  }

  /**
   * New value of the \"mscsDescription\" attribute in \"MscsInfo\", or \"null\" to remove the attribute. 
   * @return mscsDescription
   **/
  @Schema(description = "New value of the \"mscsDescription\" attribute in \"MscsInfo\", or \"null\" to remove the attribute. ")
  
    public String getMscsDescription() {
    return mscsDescription;
  }

  public void setMscsDescription(String mscsDescription) {
    this.mscsDescription = mscsDescription;
  }

  public ModifyWanConnectionInfoData mscsEndpoints(List<MscsEndpointInfo> mscsEndpoints) {
    this.mscsEndpoints = mscsEndpoints;
    return this;
  }

  public ModifyWanConnectionInfoData addMscsEndpointsItem(MscsEndpointInfo mscsEndpointsItem) {
    if (this.mscsEndpoints == null) {
      this.mscsEndpoints = new ArrayList<>();
    }
    this.mscsEndpoints.add(mscsEndpointsItem);
    return this;
  }

  /**
   * New content of certain entries of MSCS endpoints in the \"mscsEndpoints\" attribute in \"MscsInfo\", as defined below this table. 
   * @return mscsEndpoints
   **/
  @Schema(description = "New content of certain entries of MSCS endpoints in the \"mscsEndpoints\" attribute in \"MscsInfo\", as defined below this table. ")
      @Valid
    public List<MscsEndpointInfo> getMscsEndpoints() {
    return mscsEndpoints;
  }

  public void setMscsEndpoints(List<MscsEndpointInfo> mscsEndpoints) {
    this.mscsEndpoints = mscsEndpoints;
  }

  public ModifyWanConnectionInfoData removeMscsEndpointIds(List<String> removeMscsEndpointIds) {
    this.removeMscsEndpointIds = removeMscsEndpointIds;
    return this;
  }

  public ModifyWanConnectionInfoData addRemoveMscsEndpointIdsItem(String removeMscsEndpointIdsItem) {
    if (this.removeMscsEndpointIds == null) {
      this.removeMscsEndpointIds = new ArrayList<>();
    }
    this.removeMscsEndpointIds.add(removeMscsEndpointIdsItem);
    return this;
  }

  /**
   * List of identifier entries to be deleted from the \"mscsEndpoints\" attribute array in \"MscsInfo\", to be used as \"deleteIdList\" as defined below this table. 
   * @return removeMscsEndpointIds
   **/
  @Schema(description = "List of identifier entries to be deleted from the \"mscsEndpoints\" attribute array in \"MscsInfo\", to be used as \"deleteIdList\" as defined below this table. ")
  
    public List<String> getRemoveMscsEndpointIds() {
    return removeMscsEndpointIds;
  }

  public void setRemoveMscsEndpointIds(List<String> removeMscsEndpointIds) {
    this.removeMscsEndpointIds = removeMscsEndpointIds;
  }

  public ModifyWanConnectionInfoData connectivityServiceEndpoints(List<ConnectivityServiceEndpointInfo> connectivityServiceEndpoints) {
    this.connectivityServiceEndpoints = connectivityServiceEndpoints;
    return this;
  }

  public ModifyWanConnectionInfoData addConnectivityServiceEndpointsItem(ConnectivityServiceEndpointInfo connectivityServiceEndpointsItem) {
    if (this.connectivityServiceEndpoints == null) {
      this.connectivityServiceEndpoints = new ArrayList<>();
    }
    this.connectivityServiceEndpoints.add(connectivityServiceEndpointsItem);
    return this;
  }

  /**
   * New content of certain entries of connectivity service endpoints in the \"connectivityServiceEndpointInfos\" attribute in \"WanConnectionProtocolInfo\", as defined below this table. 
   * @return connectivityServiceEndpoints
   **/
  @Schema(description = "New content of certain entries of connectivity service endpoints in the \"connectivityServiceEndpointInfos\" attribute in \"WanConnectionProtocolInfo\", as defined below this table. ")
      @Valid
    public List<ConnectivityServiceEndpointInfo> getConnectivityServiceEndpoints() {
    return connectivityServiceEndpoints;
  }

  public void setConnectivityServiceEndpoints(List<ConnectivityServiceEndpointInfo> connectivityServiceEndpoints) {
    this.connectivityServiceEndpoints = connectivityServiceEndpoints;
  }

  public ModifyWanConnectionInfoData removeConnectivityServiceEndpoints(List<String> removeConnectivityServiceEndpoints) {
    this.removeConnectivityServiceEndpoints = removeConnectivityServiceEndpoints;
    return this;
  }

  public ModifyWanConnectionInfoData addRemoveConnectivityServiceEndpointsItem(String removeConnectivityServiceEndpointsItem) {
    if (this.removeConnectivityServiceEndpoints == null) {
      this.removeConnectivityServiceEndpoints = new ArrayList<>();
    }
    this.removeConnectivityServiceEndpoints.add(removeConnectivityServiceEndpointsItem);
    return this;
  }

  /**
   * List of identifier entries to be deleted from the \"connectivityServiceEndpointInfos\" attribute array in \"WanConnectionProtocolInfo\", to be used as \"deleteIdList\" as defined below this table. 
   * @return removeConnectivityServiceEndpoints
   **/
  @Schema(description = "List of identifier entries to be deleted from the \"connectivityServiceEndpointInfos\" attribute array in \"WanConnectionProtocolInfo\", to be used as \"deleteIdList\" as defined below this table. ")
  
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
    ModifyWanConnectionInfoData modifyWanConnectionInfoData = (ModifyWanConnectionInfoData) o;
    return Objects.equals(this.wanConnectionInfoId, modifyWanConnectionInfoData.wanConnectionInfoId) &&
        Objects.equals(this.mscsName, modifyWanConnectionInfoData.mscsName) &&
        Objects.equals(this.mscsDescription, modifyWanConnectionInfoData.mscsDescription) &&
        Objects.equals(this.mscsEndpoints, modifyWanConnectionInfoData.mscsEndpoints) &&
        Objects.equals(this.removeMscsEndpointIds, modifyWanConnectionInfoData.removeMscsEndpointIds) &&
        Objects.equals(this.connectivityServiceEndpoints, modifyWanConnectionInfoData.connectivityServiceEndpoints) &&
        Objects.equals(this.removeConnectivityServiceEndpoints, modifyWanConnectionInfoData.removeConnectivityServiceEndpoints);
  }

  @Override
  public int hashCode() {
    return Objects.hash(wanConnectionInfoId, mscsName, mscsDescription, mscsEndpoints, removeMscsEndpointIds, connectivityServiceEndpoints, removeConnectivityServiceEndpoints);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModifyWanConnectionInfoData {\n");
    
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
