package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
  * Parameters for the update NS operation, as defined in clause 6.5.2.11. 
 **/
@ApiModel(description="Parameters for the update NS operation, as defined in clause 6.5.2.11. ")
public class NsInstancesNsInstanceIdUpdatePostQuery  {
  
  @ApiModelProperty(required = true, value = "")
  @Valid
  private NsInstancesnsInstanceIdupdateUpdateNsRequest updateNsRequest = null;
 /**
   * Get updateNsRequest
   * @return updateNsRequest
  **/
  @JsonProperty("UpdateNsRequest")
  @NotNull
  public NsInstancesnsInstanceIdupdateUpdateNsRequest getUpdateNsRequest() {
    return updateNsRequest;
  }

  public void setUpdateNsRequest(NsInstancesnsInstanceIdupdateUpdateNsRequest updateNsRequest) {
    this.updateNsRequest = updateNsRequest;
  }

  public NsInstancesNsInstanceIdUpdatePostQuery updateNsRequest(NsInstancesnsInstanceIdupdateUpdateNsRequest updateNsRequest) {
    this.updateNsRequest = updateNsRequest;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubscriptionsPostQuery {\n");
    
    sb.append("    updateNsRequest: ").append(toIndentedString(updateNsRequest)).append("\n");
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

