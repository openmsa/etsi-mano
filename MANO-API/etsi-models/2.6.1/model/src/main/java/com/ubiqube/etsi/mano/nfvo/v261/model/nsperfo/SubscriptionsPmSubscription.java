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
import io.swagger.v3.oas.annotations.media.Schema;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
  * This type represents a subscription. 
 **/
@Schema(description="This type represents a subscription. ")
public class SubscriptionsPmSubscription  {
  
  @Schema(required = true, description = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String id = null;

  @Schema(description = "")
  @Valid
  private SubscriptionsPmSubscriptionFilter filter = null;

  @Schema(required = true, description = "String formatted according to IETF RFC 3986. ")
 /**
   * String formatted according to IETF RFC 3986. 
  **/
  private String callbackUri = null;

  @Schema(required = true, description = "")
  @Valid
  private SubscriptionsPmSubscriptionLinks links = null;
 /**
   * An identifier with the intention of being globally unique. 
   * @return id
  **/
  @JsonProperty("id")
  @NotNull
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public SubscriptionsPmSubscription id(String id) {
    this.id = id;
    return this;
  }

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

  public SubscriptionsPmSubscription filter(SubscriptionsPmSubscriptionFilter filter) {
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

  public SubscriptionsPmSubscription callbackUri(String callbackUri) {
    this.callbackUri = callbackUri;
    return this;
  }

 /**
   * Get links
   * @return links
  **/
  @JsonProperty("_links")
  @NotNull
  public SubscriptionsPmSubscriptionLinks getLinks() {
    return links;
  }

  public void setLinks(SubscriptionsPmSubscriptionLinks links) {
    this.links = links;
  }

  public SubscriptionsPmSubscription links(SubscriptionsPmSubscriptionLinks links) {
    this.links = links;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubscriptionsPmSubscription {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    filter: ").append(toIndentedString(filter)).append("\n");
    sb.append("    callbackUri: ").append(toIndentedString(callbackUri)).append("\n");
    sb.append("    links: ").append(toIndentedString(links)).append("\n");
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

