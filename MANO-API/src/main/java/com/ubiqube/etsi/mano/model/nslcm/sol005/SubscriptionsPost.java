package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;

public class SubscriptionsPost {
  
  @ApiModelProperty(value = "")
  @Valid
  private SubscriptionsLccnSubscription lccnSubscription = null;
 /**
   * Get lccnSubscription
   * @return lccnSubscription
  **/
  @JsonProperty("LccnSubscription")
  public SubscriptionsLccnSubscription getLccnSubscription() {
    return lccnSubscription;
  }

  public void setLccnSubscription(SubscriptionsLccnSubscription lccnSubscription) {
    this.lccnSubscription = lccnSubscription;
  }

  public SubscriptionsPost lccnSubscription(SubscriptionsLccnSubscription lccnSubscription) {
    this.lccnSubscription = lccnSubscription;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubscriptionsPost {\n");
    
    sb.append("    lccnSubscription: ").append(toIndentedString(lccnSubscription)).append("\n");
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

