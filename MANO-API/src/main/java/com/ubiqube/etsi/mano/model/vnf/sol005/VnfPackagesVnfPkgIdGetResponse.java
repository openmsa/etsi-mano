package com.ubiqube.etsi.mano.model.vnf.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;

public class VnfPackagesVnfPkgIdGetResponse {
  
  @ApiModelProperty(value = "")
  @Valid
  private VnfPkgInfo vnfPkgInfo = null;
 /**
   * Get vnfPkgInfo
   * @return vnfPkgInfo
  **/
  @JsonProperty("VnfPkgInfo")
  public VnfPkgInfo getVnfPkgInfo() {
    return vnfPkgInfo;
  }

  public void setVnfPkgInfo(VnfPkgInfo vnfPkgInfo) {
    this.vnfPkgInfo = vnfPkgInfo;
  }

  public VnfPackagesVnfPkgIdGetResponse vnfPkgInfo(VnfPkgInfo vnfPkgInfo) {
    this.vnfPkgInfo = vnfPkgInfo;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfPackagesVnfPkgIdGetResponse {\n");
    
    sb.append("    vnfPkgInfo: ").append(toIndentedString(vnfPkgInfo)).append("\n");
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

