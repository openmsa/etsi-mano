package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;

/**
  * The state of the NS lifecycle management operation occurrence was changed successfully. The response shall include a representation of the NS lifecycle management operation occurrence resource. 
 **/
@ApiModel(description="The state of the NS lifecycle management operation occurrence was changed successfully. The response shall include a representation of the NS lifecycle management operation occurrence resource. ")
public class NslcmV1NsLcmOpOccsNsLcmOpOccIdFailPostResponse {
  
  @ApiModelProperty(value = "")
  @Valid
  private NsLcmOpOccsNsLcmOpOcc nsLcmOpOcc = null;
 /**
   * Get nsLcmOpOcc
   * @return nsLcmOpOcc
  **/
  @JsonProperty("NsLcmOpOcc")
  public NsLcmOpOccsNsLcmOpOcc getNsLcmOpOcc() {
    return nsLcmOpOcc;
  }

  public void setNsLcmOpOcc(NsLcmOpOccsNsLcmOpOcc nsLcmOpOcc) {
    this.nsLcmOpOcc = nsLcmOpOcc;
  }

  public NslcmV1NsLcmOpOccsNsLcmOpOccIdFailPostResponse nsLcmOpOcc(NsLcmOpOccsNsLcmOpOcc nsLcmOpOcc) {
    this.nsLcmOpOcc = nsLcmOpOcc;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NslcmV1NsLcmOpOccsNsLcmOpOccIdFailPostResponse {\n");
    
    sb.append("    nsLcmOpOcc: ").append(toIndentedString(nsLcmOpOcc)).append("\n");
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

