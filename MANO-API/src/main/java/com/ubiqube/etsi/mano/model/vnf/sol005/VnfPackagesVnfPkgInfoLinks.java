package com.ubiqube.etsi.mano.model.vnf.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
  * Links to resources related to this resource. 
 **/
@ApiModel(description="Links to resources related to this resource. ")
public class VnfPackagesVnfPkgInfoLinks  {
  
  @ApiModelProperty(required = true, value = "")
  @Valid
  private VnfPackagesVnfPkgInfoLinksSelf self = null;

  @ApiModelProperty(value = "")
  @Valid
  private VnfPackagesVnfPkgInfoLinksSelf vnfd = null;

  @ApiModelProperty(required = true, value = "")
  @Valid
  private VnfPackagesVnfPkgInfoLinksSelf packageContent = null;
 /**
   * Get self
   * @return self
  **/
  @JsonProperty("self")
  @NotNull
  public VnfPackagesVnfPkgInfoLinksSelf getSelf() {
    return self;
  }

  public void setSelf(VnfPackagesVnfPkgInfoLinksSelf self) {
    this.self = self;
  }

  public VnfPackagesVnfPkgInfoLinks self(VnfPackagesVnfPkgInfoLinksSelf self) {
    this.self = self;
    return this;
  }

 /**
   * Get vnfd
   * @return vnfd
  **/
  @JsonProperty("vnfd")
  public VnfPackagesVnfPkgInfoLinksSelf getVnfd() {
    return vnfd;
  }

  public void setVnfd(VnfPackagesVnfPkgInfoLinksSelf vnfd) {
    this.vnfd = vnfd;
  }

  public VnfPackagesVnfPkgInfoLinks vnfd(VnfPackagesVnfPkgInfoLinksSelf vnfd) {
    this.vnfd = vnfd;
    return this;
  }

 /**
   * Get packageContent
   * @return packageContent
  **/
  @JsonProperty("packageContent")
  @NotNull
  public VnfPackagesVnfPkgInfoLinksSelf getPackageContent() {
    return packageContent;
  }

  public void setPackageContent(VnfPackagesVnfPkgInfoLinksSelf packageContent) {
    this.packageContent = packageContent;
  }

  public VnfPackagesVnfPkgInfoLinks packageContent(VnfPackagesVnfPkgInfoLinksSelf packageContent) {
    this.packageContent = packageContent;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfPackagesVnfPkgInfoLinks {\n");
    
    sb.append("    self: ").append(toIndentedString(self)).append("\n");
    sb.append("    vnfd: ").append(toIndentedString(vnfd)).append("\n");
    sb.append("    packageContent: ").append(toIndentedString(packageContent)).append("\n");
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

