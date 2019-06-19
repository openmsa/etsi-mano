package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
  * The NS creation parameters, as defined in clause 6.5.2.7.  
 **/
@ApiModel(description="The NS creation parameters, as defined in clause 6.5.2.7.  ")
public class NsInstancesPostQuery  {
  
  @ApiModelProperty(required = true, value = "")
  @Valid
  private NsInstancesCreateNsRequest createNsRequest = null;
 /**
   * Get createNsRequest
   * @return createNsRequest
  **/
  @JsonProperty("CreateNsRequest")
  @NotNull
  public NsInstancesCreateNsRequest getCreateNsRequest() {
    return createNsRequest;
  }

  public void setCreateNsRequest(NsInstancesCreateNsRequest createNsRequest) {
    this.createNsRequest = createNsRequest;
  }

  public NsInstancesPostQuery createNsRequest(NsInstancesCreateNsRequest createNsRequest) {
    this.createNsRequest = createNsRequest;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfPackagePostQuery {\n");
    
    sb.append("    createNsRequest: ").append(toIndentedString(createNsRequest)).append("\n");
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

