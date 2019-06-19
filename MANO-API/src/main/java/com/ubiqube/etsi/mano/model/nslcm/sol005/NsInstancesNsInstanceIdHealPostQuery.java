package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
  * Parameters for the heal NS operation, as defined in clause 6.5.2.12. 
 **/
@ApiModel(description="Parameters for the heal NS operation, as defined in clause 6.5.2.12. ")
public class NsInstancesNsInstanceIdHealPostQuery  {
  
  @ApiModelProperty(required = true, value = "")
  @Valid
  private NsInstancesnsInstanceIdhealHealNsRequest healNsRequest = null;
 /**
   * Get healNsRequest
   * @return healNsRequest
  **/
  @JsonProperty("HealNsRequest")
  @NotNull
  public NsInstancesnsInstanceIdhealHealNsRequest getHealNsRequest() {
    return healNsRequest;
  }

  public void setHealNsRequest(NsInstancesnsInstanceIdhealHealNsRequest healNsRequest) {
    this.healNsRequest = healNsRequest;
  }

  public NsInstancesNsInstanceIdHealPostQuery healNsRequest(NsInstancesnsInstanceIdhealHealNsRequest healNsRequest) {
    this.healNsRequest = healNsRequest;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Body4 {\n");
    
    sb.append("    healNsRequest: ").append(toIndentedString(healNsRequest)).append("\n");
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

