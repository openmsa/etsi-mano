package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;

public class InlineResponse200  {
  
  @ApiModelProperty(value = "")
  @Valid
  private NsInstance nsInstance = null;
 /**
   * Get nsInstance
   * @return nsInstance
  **/
  @JsonProperty("NsInstance")
  public NsInstance getNsInstance() {
    return nsInstance;
  }

  public void setNsInstance(NsInstance nsInstance) {
    this.nsInstance = nsInstance;
  }

  public InlineResponse200 nsInstance(NsInstance nsInstance) {
    this.nsInstance = nsInstance;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PmJobsPostResponse {\n");
    
    sb.append("    nsInstance: ").append(toIndentedString(nsInstance)).append("\n");
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

