package com.ubiqube.etsi.mano.model.nsd.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
  * This type represents a subscription request related to notifications about NSD management. 
 **/
@ApiModel(description="This type represents a subscription request related to notifications about NSD management. ")
public class SubscriptionsNsdmSubscriptionRequest  {
  
  @ApiModelProperty(value = "")
  @Valid
  private SubscriptionsNsdmSubscriptionFilter filter = null;

  @ApiModelProperty(required = true, value = "The URI of the endpoint to send the notification to. ")
 /**
   * The URI of the endpoint to send the notification to. 
  **/
  private String callbackUri = null;

  @ApiModelProperty(value = "")
  @Valid
  private SubscriptionsNsdmSubscriptionRequestAuthentication authentication = null;
 /**
   * Get filter
   * @return filter
  **/
  @JsonProperty("filter")
  public SubscriptionsNsdmSubscriptionFilter getFilter() {
    return filter;
  }

  public void setFilter(SubscriptionsNsdmSubscriptionFilter filter) {
    this.filter = filter;
  }

  public SubscriptionsNsdmSubscriptionRequest filter(SubscriptionsNsdmSubscriptionFilter filter) {
    this.filter = filter;
    return this;
  }

 /**
   * The URI of the endpoint to send the notification to. 
   * @return callbackUri
  **/
  @JsonProperty("callbackUri")
  @NotNull
  public String getCallbackUri() {
    return callbackUri;
  }

  public void setCallbackUri(String callbackUri) {
    this.callbackUri = callbackUri;
  }

  public SubscriptionsNsdmSubscriptionRequest callbackUri(String callbackUri) {
    this.callbackUri = callbackUri;
    return this;
  }

 /**
   * Get authentication
   * @return authentication
  **/
  @JsonProperty("authentication")
  public SubscriptionsNsdmSubscriptionRequestAuthentication getAuthentication() {
    return authentication;
  }

  public void setAuthentication(SubscriptionsNsdmSubscriptionRequestAuthentication authentication) {
    this.authentication = authentication;
  }

  public SubscriptionsNsdmSubscriptionRequest authentication(SubscriptionsNsdmSubscriptionRequestAuthentication authentication) {
    this.authentication = authentication;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubscriptionsNsdmSubscriptionRequest {\n");
    
    sb.append("    filter: ").append(toIndentedString(filter)).append("\n");
    sb.append("    callbackUri: ").append(toIndentedString(callbackUri)).append("\n");
    sb.append("    authentication: ").append(toIndentedString(authentication)).append("\n");
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

