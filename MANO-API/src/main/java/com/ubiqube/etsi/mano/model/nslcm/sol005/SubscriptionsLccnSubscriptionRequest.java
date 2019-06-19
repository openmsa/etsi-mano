package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
  * This type represents a subscription request related to notifications  about NS lifecycle changes. It shall comply with the provisions defined in Table 6.5.2.2-1.. 
 **/
@ApiModel(description="This type represents a subscription request related to notifications  about NS lifecycle changes. It shall comply with the provisions defined in Table 6.5.2.2-1.. ")
public class SubscriptionsLccnSubscriptionRequest  {
  
  @ApiModelProperty(value = "")
  @Valid
  private SubscriptionsLccnSubscriptionFilter filter = null;

  @ApiModelProperty(required = true, value = "String formatted according to IETF RFC 3986. ")
 /**
   * String formatted according to IETF RFC 3986. 
  **/
  private String callbackUri = null;

  @ApiModelProperty(value = "")
  @Valid
  private SubscriptionsLccnSubscriptionRequestAuthentication authentication = null;
 /**
   * Get filter
   * @return filter
  **/
  @JsonProperty("filter")
  public SubscriptionsLccnSubscriptionFilter getFilter() {
    return filter;
  }

  public void setFilter(SubscriptionsLccnSubscriptionFilter filter) {
    this.filter = filter;
  }

  public SubscriptionsLccnSubscriptionRequest filter(SubscriptionsLccnSubscriptionFilter filter) {
    this.filter = filter;
    return this;
  }

 /**
   * String formatted according to IETF RFC 3986. 
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

  public SubscriptionsLccnSubscriptionRequest callbackUri(String callbackUri) {
    this.callbackUri = callbackUri;
    return this;
  }

 /**
   * Get authentication
   * @return authentication
  **/
  @JsonProperty("authentication")
  public SubscriptionsLccnSubscriptionRequestAuthentication getAuthentication() {
    return authentication;
  }

  public void setAuthentication(SubscriptionsLccnSubscriptionRequestAuthentication authentication) {
    this.authentication = authentication;
  }

  public SubscriptionsLccnSubscriptionRequest authentication(SubscriptionsLccnSubscriptionRequestAuthentication authentication) {
    this.authentication = authentication;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubscriptionsLccnSubscriptionRequest {\n");
    
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

