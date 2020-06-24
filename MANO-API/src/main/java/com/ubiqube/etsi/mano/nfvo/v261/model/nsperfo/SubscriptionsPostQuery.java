package com.ubiqube.etsi.mano.nfvo.v261.model.nsperfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
  * Details of the subscription to be created.             
 **/
@ApiModel(description="Details of the subscription to be created.             ")
public class SubscriptionsPostQuery  {
  
  @ApiModelProperty(required = true, value = "")
  @Valid
  private SubscriptionsPmSubscriptionRequest pmSubscriptionRequest = null;
 /**
   * Get pmSubscriptionRequest
   * @return pmSubscriptionRequest
  **/
  @JsonProperty("PmSubscriptionRequest")
  @NotNull
  public SubscriptionsPmSubscriptionRequest getPmSubscriptionRequest() {
    return pmSubscriptionRequest;
  }

  public void setPmSubscriptionRequest(SubscriptionsPmSubscriptionRequest pmSubscriptionRequest) {
    this.pmSubscriptionRequest = pmSubscriptionRequest;
  }

  public SubscriptionsPostQuery pmSubscriptionRequest(SubscriptionsPmSubscriptionRequest pmSubscriptionRequest) {
    this.pmSubscriptionRequest = pmSubscriptionRequest;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfPackagePostQuery {\n");
    
    sb.append("    pmSubscriptionRequest: ").append(toIndentedString(pmSubscriptionRequest)).append("\n");
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

