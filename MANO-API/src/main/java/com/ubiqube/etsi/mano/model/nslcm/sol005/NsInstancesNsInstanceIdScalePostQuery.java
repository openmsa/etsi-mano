package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
  * Parameters for the scale NS operation, as defined in clause 6.5.2.13.             
 **/
@ApiModel(description="Parameters for the scale NS operation, as defined in clause 6.5.2.13.             ")
public class NsInstancesNsInstanceIdScalePostQuery  {
  
  @ApiModelProperty(required = true, value = "")
  @Valid
  private NsInstancesnsInstanceIdscaleScaleNsRequest scaleNsRequest = null;
 /**
   * Get scaleNsRequest
   * @return scaleNsRequest
  **/
  @JsonProperty("ScaleNsRequest")
  @NotNull
  public NsInstancesnsInstanceIdscaleScaleNsRequest getScaleNsRequest() {
    return scaleNsRequest;
  }

  public void setScaleNsRequest(NsInstancesnsInstanceIdscaleScaleNsRequest scaleNsRequest) {
    this.scaleNsRequest = scaleNsRequest;
  }

  public NsInstancesNsInstanceIdScalePostQuery scaleNsRequest(NsInstancesnsInstanceIdscaleScaleNsRequest scaleNsRequest) {
    this.scaleNsRequest = scaleNsRequest;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Body2 {\n");
    
    sb.append("    scaleNsRequest: ").append(toIndentedString(scaleNsRequest)).append("\n");
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

