/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.ubiqube.etsi.mano.nfvo.v261.model.nsperfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
  * This type represents a subscription request. 
 **/
@ApiModel(description="This type represents a subscription request. ")
public class SubscriptionsPmSubscriptionRequest  {
  
  @ApiModelProperty(value = "")
  @Valid
  private SubscriptionsPmSubscriptionFilter filter = null;

  @ApiModelProperty(required = true, value = "String formatted according to IETF RFC 3986. ")
 /**
   * String formatted according to IETF RFC 3986. 
  **/
  private String callbackUri = null;

  @ApiModelProperty(value = "")
  @Valid
  private SubscriptionsPmSubscriptionRequestAuthentication authentication = null;
 /**
   * Get filter
   * @return filter
  **/
  @JsonProperty("filter")
  public SubscriptionsPmSubscriptionFilter getFilter() {
    return filter;
  }

  public void setFilter(SubscriptionsPmSubscriptionFilter filter) {
    this.filter = filter;
  }

  public SubscriptionsPmSubscriptionRequest filter(SubscriptionsPmSubscriptionFilter filter) {
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

  public SubscriptionsPmSubscriptionRequest callbackUri(String callbackUri) {
    this.callbackUri = callbackUri;
    return this;
  }

 /**
   * Get authentication
   * @return authentication
  **/
  @JsonProperty("authentication")
  public SubscriptionsPmSubscriptionRequestAuthentication getAuthentication() {
    return authentication;
  }

  public void setAuthentication(SubscriptionsPmSubscriptionRequestAuthentication authentication) {
    this.authentication = authentication;
  }

  public SubscriptionsPmSubscriptionRequest authentication(SubscriptionsPmSubscriptionRequestAuthentication authentication) {
    this.authentication = authentication;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubscriptionsPmSubscriptionRequest {\n");
    
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

