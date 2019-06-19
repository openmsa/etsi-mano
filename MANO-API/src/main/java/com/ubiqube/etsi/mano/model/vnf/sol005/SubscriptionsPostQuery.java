package com.ubiqube.etsi.mano.model.vnf.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
  * Representation of the created subscription resource. The HTTP response shall include a \"Location\" HTTP header that points to the created subscription resource. 
 **/
@ApiModel(description="Representation of the created subscription resource. The HTTP response shall include a \"Location\" HTTP header that points to the created subscription resource. ")
public class SubscriptionsPostQuery {
  
  @ApiModelProperty(required = true, value = "")
  @Valid
  private SubscriptionsPkgmSubscriptionRequest pkgmSubscriptionRequest = null;
 /**
   * Get pkgmSubscriptionRequest
   * @return pkgmSubscriptionRequest
  **/
  @JsonProperty("PkgmSubscriptionRequest")
  @NotNull
  public SubscriptionsPkgmSubscriptionRequest getPkgmSubscriptionRequest() {
    return pkgmSubscriptionRequest;
  }

  public void setPkgmSubscriptionRequest(SubscriptionsPkgmSubscriptionRequest pkgmSubscriptionRequest) {
    this.pkgmSubscriptionRequest = pkgmSubscriptionRequest;
  }

  public SubscriptionsPostQuery pkgmSubscriptionRequest(SubscriptionsPkgmSubscriptionRequest pkgmSubscriptionRequest) {
    this.pkgmSubscriptionRequest = pkgmSubscriptionRequest;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubscriptionsPostQuery {\n");
    
    sb.append("    pkgmSubscriptionRequest: ").append(toIndentedString(pkgmSubscriptionRequest)).append("\n");
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

