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
package com.ubiqube.etsi.mano.vnfm.v281.model.vrqan;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.vnfm.v281.model.vrqan.VrQuotaAvailNotificationsFilter;
import com.ubiqube.etsi.mano.vnfm.v281.model.vrqan.VrQuotaAvailSubscriptionLinks;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents a subscription related to notifications related to the availability of the virtualised resources quotas. 
 */
@ApiModel(description = "This type represents a subscription related to notifications related to the availability of the virtualised resources quotas. ")
@Validated

public class VrQuotaAvailSubscription   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("filter")
  private VrQuotaAvailNotificationsFilter filter = null;

  @JsonProperty("callbackUri")
  private String callbackUri = null;

  @JsonProperty("_links")
  private VrQuotaAvailSubscriptionLinks links = null;

  public VrQuotaAvailSubscription id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Identifier of this \"Individual subscription\" resource 
   * @return id
  **/
  @ApiModelProperty(required = true, value = "Identifier of this \"Individual subscription\" resource ")
  @NotNull


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public VrQuotaAvailSubscription filter(VrQuotaAvailNotificationsFilter filter) {
    this.filter = filter;
    return this;
  }

  /**
   * Input filter for selecting notifications to subscribe to. This filter can contain information about specific attributes of the virtualised resources quota. 
   * @return filter
  **/
  @ApiModelProperty(value = "Input filter for selecting notifications to subscribe to. This filter can contain information about specific attributes of the virtualised resources quota. ")

  @Valid

  public VrQuotaAvailNotificationsFilter getFilter() {
    return filter;
  }

  public void setFilter(VrQuotaAvailNotificationsFilter filter) {
    this.filter = filter;
  }

  public VrQuotaAvailSubscription callbackUri(String callbackUri) {
    this.callbackUri = callbackUri;
    return this;
  }

  /**
   * The URI of the endpoint to send the notification to. 
   * @return callbackUri
  **/
  @ApiModelProperty(required = true, value = "The URI of the endpoint to send the notification to. ")
  @NotNull


  public String getCallbackUri() {
    return callbackUri;
  }

  public void setCallbackUri(String callbackUri) {
    this.callbackUri = callbackUri;
  }

  public VrQuotaAvailSubscription links(VrQuotaAvailSubscriptionLinks links) {
    this.links = links;
    return this;
  }

  /**
   * Get links
   * @return links
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public VrQuotaAvailSubscriptionLinks getLinks() {
    return links;
  }

  public void setLinks(VrQuotaAvailSubscriptionLinks links) {
    this.links = links;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VrQuotaAvailSubscription vrQuotaAvailSubscription = (VrQuotaAvailSubscription) o;
    return Objects.equals(this.id, vrQuotaAvailSubscription.id) &&
        Objects.equals(this.filter, vrQuotaAvailSubscription.filter) &&
        Objects.equals(this.callbackUri, vrQuotaAvailSubscription.callbackUri) &&
        Objects.equals(this.links, vrQuotaAvailSubscription.links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, filter, callbackUri, links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VrQuotaAvailSubscription {\n");
    
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
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

