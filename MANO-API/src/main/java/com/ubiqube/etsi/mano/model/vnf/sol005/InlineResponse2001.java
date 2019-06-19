package com.ubiqube.etsi.mano.model.vnf.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;

public class InlineResponse2001  {
  
  @ApiModelProperty(value = "")
  @Valid
  private SubscriptionsPkgmSubscription pkgmSubscription = null;
 /**
   * Get pkgmSubscription
   * @return pkgmSubscription
  **/
  @JsonProperty("PkgmSubscription")
  public SubscriptionsPkgmSubscription getPkgmSubscription() {
    return pkgmSubscription;
  }

  public void setPkgmSubscription(SubscriptionsPkgmSubscription pkgmSubscription) {
    this.pkgmSubscription = pkgmSubscription;
  }

  public InlineResponse2001 pkgmSubscription(SubscriptionsPkgmSubscription pkgmSubscription) {
    this.pkgmSubscription = pkgmSubscription;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineResponse2001 {\n");
    
    sb.append("    pkgmSubscription: ").append(toIndentedString(pkgmSubscription)).append("\n");
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

