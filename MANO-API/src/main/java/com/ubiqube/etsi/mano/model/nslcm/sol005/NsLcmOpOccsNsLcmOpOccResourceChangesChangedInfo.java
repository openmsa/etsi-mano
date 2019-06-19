package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;

/**
  * Information about the changed VNF instance information, including VNF configurable properties,if applicable. When the \"changedInfo\" attribute is present,  either the \"changedVnfInfo\" attribute or the \"changedExtConnectivity\" attribute or both shall be present. 
 **/
@ApiModel(description="Information about the changed VNF instance information, including VNF configurable properties,if applicable. When the \"changedInfo\" attribute is present,  either the \"changedVnfInfo\" attribute or the \"changedExtConnectivity\" attribute or both shall be present. ")
public class NsLcmOpOccsNsLcmOpOccResourceChangesChangedInfo  {
  
  @ApiModelProperty(value = "")
  @Valid
  private NsInstancesnsInstanceIdupdateUpdateNsRequestModifyVnfInfoData changedVnfInfo = null;

  @ApiModelProperty(value = "")
  @Valid
  private NsInstancesNsInstanceInstantiatedVnfInfoExtVirtualLinkInfo changedExtConnectivity = null;
 /**
   * Get changedVnfInfo
   * @return changedVnfInfo
  **/
  @JsonProperty("changedVnfInfo")
  public NsInstancesnsInstanceIdupdateUpdateNsRequestModifyVnfInfoData getChangedVnfInfo() {
    return changedVnfInfo;
  }

  public void setChangedVnfInfo(NsInstancesnsInstanceIdupdateUpdateNsRequestModifyVnfInfoData changedVnfInfo) {
    this.changedVnfInfo = changedVnfInfo;
  }

  public NsLcmOpOccsNsLcmOpOccResourceChangesChangedInfo changedVnfInfo(NsInstancesnsInstanceIdupdateUpdateNsRequestModifyVnfInfoData changedVnfInfo) {
    this.changedVnfInfo = changedVnfInfo;
    return this;
  }

 /**
   * Get changedExtConnectivity
   * @return changedExtConnectivity
  **/
  @JsonProperty("changedExtConnectivity")
  public NsInstancesNsInstanceInstantiatedVnfInfoExtVirtualLinkInfo getChangedExtConnectivity() {
    return changedExtConnectivity;
  }

  public void setChangedExtConnectivity(NsInstancesNsInstanceInstantiatedVnfInfoExtVirtualLinkInfo changedExtConnectivity) {
    this.changedExtConnectivity = changedExtConnectivity;
  }

  public NsLcmOpOccsNsLcmOpOccResourceChangesChangedInfo changedExtConnectivity(NsInstancesNsInstanceInstantiatedVnfInfoExtVirtualLinkInfo changedExtConnectivity) {
    this.changedExtConnectivity = changedExtConnectivity;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsLcmOpOccsNsLcmOpOccResourceChangesChangedInfo {\n");
    
    sb.append("    changedVnfInfo: ").append(toIndentedString(changedVnfInfo)).append("\n");
    sb.append("    changedExtConnectivity: ").append(toIndentedString(changedExtConnectivity)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private static String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

