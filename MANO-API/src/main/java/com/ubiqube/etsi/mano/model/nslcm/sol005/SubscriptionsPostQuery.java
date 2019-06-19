package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
  * Details of the subscription to be created, as defined in clause 6.5.2.2. 
 **/
@ApiModel(description="Details of the subscription to be created, as defined in clause 6.5.2.2. ")
public class SubscriptionsPostQuery  {
  
  @ApiModelProperty(required = true, value = "")
  @Valid
  private SubscriptionsLccnSubscriptionRequest lccnSubscriptionRequest = null;
 /**
   * Get lccnSubscriptionRequest
   * @return lccnSubscriptionRequest
  **/
  @JsonProperty("LccnSubscriptionRequest")
  @NotNull
  public SubscriptionsLccnSubscriptionRequest getLccnSubscriptionRequest() {
    return lccnSubscriptionRequest;
  }

  public void setLccnSubscriptionRequest(SubscriptionsLccnSubscriptionRequest lccnSubscriptionRequest) {
    this.lccnSubscriptionRequest = lccnSubscriptionRequest;
  }

  public SubscriptionsPostQuery lccnSubscriptionRequest(SubscriptionsLccnSubscriptionRequest lccnSubscriptionRequest) {
    this.lccnSubscriptionRequest = lccnSubscriptionRequest;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Body7 {\n");
    
    sb.append("    lccnSubscriptionRequest: ").append(toIndentedString(lccnSubscriptionRequest)).append("\n");
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

