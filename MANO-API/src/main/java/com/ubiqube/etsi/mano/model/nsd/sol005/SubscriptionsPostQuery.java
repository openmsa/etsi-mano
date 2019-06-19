package com.ubiqube.etsi.mano.model.nsd.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
  * Details of the subscription to be created, as defined in clause 5.5.2.7. 
 **/
@ApiModel(description="Details of the subscription to be created, as defined in clause 5.5.2.7. ")
public class SubscriptionsPostQuery  {
  
  @ApiModelProperty(required = true, value = "")
  @Valid
  private SubscriptionsNsdmSubscriptionRequest nsdmSubscriptionRequest = null;
 /**
   * Get nsdmSubscriptionRequest
   * @return nsdmSubscriptionRequest
  **/
  @JsonProperty("NsdmSubscriptionRequest")
  @NotNull
  public SubscriptionsNsdmSubscriptionRequest getNsdmSubscriptionRequest() {
    return nsdmSubscriptionRequest;
  }

  public void setNsdmSubscriptionRequest(SubscriptionsNsdmSubscriptionRequest nsdmSubscriptionRequest) {
    this.nsdmSubscriptionRequest = nsdmSubscriptionRequest;
  }

  public SubscriptionsPostQuery nsdmSubscriptionRequest(SubscriptionsNsdmSubscriptionRequest nsdmSubscriptionRequest) {
    this.nsdmSubscriptionRequest = nsdmSubscriptionRequest;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Body4 {\n");
    
    sb.append("    nsdmSubscriptionRequest: ").append(toIndentedString(nsdmSubscriptionRequest)).append("\n");
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

