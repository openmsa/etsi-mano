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
public class SubscriptionsPkgmSubscriptionLinks  {
  
  @ApiModelProperty(required = true, value = "")
  @Valid
  private VnfPackagesVnfPkgInfoLinksSelf self = null;
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

  public SubscriptionsPkgmSubscriptionLinks self(VnfPackagesVnfPkgInfoLinksSelf self) {
    this.self = self;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubscriptionsPkgmSubscriptionLinks {\n");
    
    sb.append("    self: ").append(toIndentedString(self)).append("\n");
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

