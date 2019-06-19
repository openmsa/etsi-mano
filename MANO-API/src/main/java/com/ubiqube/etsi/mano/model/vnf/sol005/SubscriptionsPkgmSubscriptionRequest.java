package com.ubiqube.etsi.mano.model.vnf.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
  * This type represents a subscription request related to VNF package management  notifications about VNF package on boarding or changes. 
 **/
@ApiModel(description="This type represents a subscription request related to VNF package management  notifications about VNF package on boarding or changes. ")
public class SubscriptionsPkgmSubscriptionRequest  {
  
  @ApiModelProperty(value = "")
  @Valid
  private SubscriptionsPkgmSubscriptionFilter filter = null;

  @ApiModelProperty(required = true, value = "String formatted according to IETF RFC 3986. ")
 /**
   * String formatted according to IETF RFC 3986. 
  **/
  private String callbackUri = null;

  @ApiModelProperty(value = "")
  @Valid
  private SubscriptionsPkgmSubscriptionRequestAuthentication authentication = null;
 /**
   * Get filter
   * @return filter
  **/
  @JsonProperty("filter")
  public SubscriptionsPkgmSubscriptionFilter getFilter() {
    return filter;
  }

  public void setFilter(SubscriptionsPkgmSubscriptionFilter filter) {
    this.filter = filter;
  }

  public SubscriptionsPkgmSubscriptionRequest filter(SubscriptionsPkgmSubscriptionFilter filter) {
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

  public SubscriptionsPkgmSubscriptionRequest callbackUri(String callbackUri) {
    this.callbackUri = callbackUri;
    return this;
  }

 /**
   * Get authentication
   * @return authentication
  **/
  @JsonProperty("authentication")
  public SubscriptionsPkgmSubscriptionRequestAuthentication getAuthentication() {
    return authentication;
  }

  public void setAuthentication(SubscriptionsPkgmSubscriptionRequestAuthentication authentication) {
    this.authentication = authentication;
  }

  public SubscriptionsPkgmSubscriptionRequest authentication(SubscriptionsPkgmSubscriptionRequestAuthentication authentication) {
    this.authentication = authentication;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubscriptionsPkgmSubscriptionRequest {\n");
    
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

