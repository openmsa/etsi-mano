/**
 * This copy of Woodstox XML processor is licensed under the
 * Apache (Software) License, version 2.0 ("the License").
 * See the License for details about distribution rights, and the
 * specific rights regarding derivate works.
 *
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/
 *
 * A copy is also included in the downloadable source code package
 * containing Woodstox, in file "ASL2.0", under the same directory
 * as this file.
 */
package com.ubiqube.etsi.mano.nfvo.v261.model.nsperfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
  * This type represents a subscription. 
 **/
@ApiModel(description="This type represents a subscription. ")
public class SubscriptionsPmSubscription  {
  
  @ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String id = null;

  @ApiModelProperty(value = "")
  @Valid
  private SubscriptionsPmSubscriptionFilter filter = null;

  @ApiModelProperty(required = true, value = "String formatted according to IETF RFC 3986. ")
 /**
   * String formatted according to IETF RFC 3986. 
  **/
  private String callbackUri = null;

  @ApiModelProperty(required = true, value = "")
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

