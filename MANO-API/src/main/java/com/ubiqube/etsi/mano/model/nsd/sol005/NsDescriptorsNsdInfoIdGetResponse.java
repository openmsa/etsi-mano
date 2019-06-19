package com.ubiqube.etsi.mano.model.nsd.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;

public class NsDescriptorsNsdInfoIdGetResponse {
  
  @ApiModelProperty(value = "")
  @Valid
  private NsDescriptorsNsdInfo nsdInfo = null;
 /**
   * Get nsdInfo
   * @return nsdInfo
  **/
  @JsonProperty("NsdInfo")
  public NsDescriptorsNsdInfo getNsdInfo() {
    return nsdInfo;
  }

  public void setNsdInfo(NsDescriptorsNsdInfo nsdInfo) {
    this.nsdInfo = nsdInfo;
  }

  public NsDescriptorsNsdInfoIdGetResponse nsdInfo(NsDescriptorsNsdInfo nsdInfo) {
    this.nsdInfo = nsdInfo;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsDescriptorsNsdInfoIdGetResponse {\n");
    
    sb.append("    nsdInfo: ").append(toIndentedString(nsdInfo)).append("\n");
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

