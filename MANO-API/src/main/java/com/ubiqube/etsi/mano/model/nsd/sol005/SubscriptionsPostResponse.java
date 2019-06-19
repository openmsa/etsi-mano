package com.ubiqube.etsi.mano.model.nsd.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;

public class SubscriptionsPostResponse {
  
  @ApiModelProperty(value = "")
  @Valid
  private SubscriptionsNsdmSubscription nsdmSubscription = null;
 /**
   * Get nsdmSubscription
   * @return nsdmSubscription
  **/
  @JsonProperty("NsdmSubscription")
  public SubscriptionsNsdmSubscription getNsdmSubscription() {
    return nsdmSubscription;
  }

  public void setNsdmSubscription(SubscriptionsNsdmSubscription nsdmSubscription) {
    this.nsdmSubscription = nsdmSubscription;
  }

  public SubscriptionsPostResponse nsdmSubscription(SubscriptionsNsdmSubscription nsdmSubscription) {
    this.nsdmSubscription = nsdmSubscription;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubscriptionsPostResponse {\n");
    
    sb.append("    nsdmSubscription: ").append(toIndentedString(nsdmSubscription)).append("\n");
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

