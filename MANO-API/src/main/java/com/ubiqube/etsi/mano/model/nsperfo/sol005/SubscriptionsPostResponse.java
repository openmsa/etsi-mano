package com.ubiqube.etsi.mano.model.nsperfo.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;

public class SubscriptionsPostResponse {
  
  @ApiModelProperty(value = "")
  @Valid
  private SubscriptionsPmSubscription pmSubscription = null;
 /**
   * Get pmSubscription
   * @return pmSubscription
  **/
  @JsonProperty("PmSubscription")
  public SubscriptionsPmSubscription getPmSubscription() {
    return pmSubscription;
  }

  public void setPmSubscription(SubscriptionsPmSubscription pmSubscription) {
    this.pmSubscription = pmSubscription;
  }

  public SubscriptionsPostResponse pmSubscription(SubscriptionsPmSubscription pmSubscription) {
    this.pmSubscription = pmSubscription;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubscriptionsPostResponse {\n");
    
    sb.append("    pmSubscription: ").append(toIndentedString(pmSubscription)).append("\n");
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

