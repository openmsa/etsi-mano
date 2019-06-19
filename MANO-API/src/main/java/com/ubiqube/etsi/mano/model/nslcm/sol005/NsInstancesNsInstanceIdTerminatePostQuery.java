package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
  * The terminate NS request parameters, as defined in  clause 6.5.2.14. 
 **/
@ApiModel(description="The terminate NS request parameters, as defined in  clause 6.5.2.14. ")
public class NsInstancesNsInstanceIdTerminatePostQuery  {
  
  @ApiModelProperty(required = true, value = "")
  @Valid
  private NsInstancesnsInstanceIdterminateTerminateNsRequest terminateNsRequest = null;
 /**
   * Get terminateNsRequest
   * @return terminateNsRequest
  **/
  @JsonProperty("TerminateNsRequest")
  @NotNull
  public NsInstancesnsInstanceIdterminateTerminateNsRequest getTerminateNsRequest() {
    return terminateNsRequest;
  }

  public void setTerminateNsRequest(NsInstancesnsInstanceIdterminateTerminateNsRequest terminateNsRequest) {
    this.terminateNsRequest = terminateNsRequest;
  }

  public NsInstancesNsInstanceIdTerminatePostQuery terminateNsRequest(NsInstancesnsInstanceIdterminateTerminateNsRequest terminateNsRequest) {
    this.terminateNsRequest = terminateNsRequest;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Body5 {\n");
    
    sb.append("    terminateNsRequest: ").append(toIndentedString(terminateNsRequest)).append("\n");
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

