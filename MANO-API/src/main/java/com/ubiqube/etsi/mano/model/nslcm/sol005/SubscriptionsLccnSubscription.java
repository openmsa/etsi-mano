package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
  * This type represents a subscription related to notifications about NS lifecycle changes.  It shall comply with the provisions defined in Table 6.5.2.4-1. 
 **/
@ApiModel(description="This type represents a subscription related to notifications about NS lifecycle changes.  It shall comply with the provisions defined in Table 6.5.2.4-1. ")
public class SubscriptionsLccnSubscription  {
  
  @ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String id = null;

  @ApiModelProperty(value = "")
  @Valid
  private SubscriptionsLccnSubscriptionFilter filter = null;

  @ApiModelProperty(required = true, value = "String formatted according to IETF RFC 3986. ")
 /**
   * String formatted according to IETF RFC 3986. 
  **/
  private String callbackUri = null;

  @ApiModelProperty(required = true, value = "")
  @Valid
  private SubscriptionsLccnSubscriptionLinks links = null;
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

  public SubscriptionsLccnSubscription id(String id) {
    this.id = id;
    return this;
  }

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

  public SubscriptionsLccnSubscription filter(SubscriptionsLccnSubscriptionFilter filter) {
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

  public SubscriptionsLccnSubscription callbackUri(String callbackUri) {
    this.callbackUri = callbackUri;
    return this;
  }

 /**
   * Get links
   * @return links
  **/
  @JsonProperty("_links")
  @NotNull
  public SubscriptionsLccnSubscriptionLinks getLinks() {
    return links;
  }

  public void setLinks(SubscriptionsLccnSubscriptionLinks links) {
    this.links = links;
  }

  public SubscriptionsLccnSubscription links(SubscriptionsLccnSubscriptionLinks links) {
    this.links = links;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubscriptionsLccnSubscription {\n");
    
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

