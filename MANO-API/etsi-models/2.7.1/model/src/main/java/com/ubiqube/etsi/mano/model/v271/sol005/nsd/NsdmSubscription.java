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

package com.ubiqube.etsi.mano.model.v271.sol005.nsd;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.model.v271.sol005.nsd.NsdmNotificationsFilter;
import com.ubiqube.etsi.mano.model.v271.sol005.nsd.NsdmSubscriptionLinks;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents a subscription related to notifications about NSD management. 
 */
@ApiModel(description = "This type represents a subscription related to notifications about NSD management. ")
@Validated
public class NsdmSubscription   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("filter")
  private NsdmNotificationsFilter filter = null;

  @JsonProperty("callbackUri")
  private String callbackUri = null;

  @JsonProperty("_links")
  private NsdmSubscriptionLinks _links = null;

  public NsdmSubscription id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public NsdmSubscription filter(NsdmNotificationsFilter filter) {
    this.filter = filter;
    return this;
  }

  /**
   * Get filter
   * @return filter
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public NsdmNotificationsFilter getFilter() {
    return filter;
  }

  public void setFilter(NsdmNotificationsFilter filter) {
    this.filter = filter;
  }

  public NsdmSubscription callbackUri(String callbackUri) {
    this.callbackUri = callbackUri;
    return this;
  }

  /**
   * Get callbackUri
   * @return callbackUri
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getCallbackUri() {
    return callbackUri;
  }

  public void setCallbackUri(String callbackUri) {
    this.callbackUri = callbackUri;
  }

  public NsdmSubscription _links(NsdmSubscriptionLinks _links) {
    this._links = _links;
    return this;
  }

  /**
   * Get _links
   * @return _links
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public NsdmSubscriptionLinks getLinks() {
    return _links;
  }

  public void setLinks(NsdmSubscriptionLinks _links) {
    this._links = _links;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NsdmSubscription nsdmSubscription = (NsdmSubscription) o;
    return Objects.equals(this.id, nsdmSubscription.id) &&
        Objects.equals(this.filter, nsdmSubscription.filter) &&
        Objects.equals(this.callbackUri, nsdmSubscription.callbackUri) &&
        Objects.equals(this._links, nsdmSubscription._links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, filter, callbackUri, _links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsdmSubscription {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    filter: ").append(toIndentedString(filter)).append("\n");
    sb.append("    callbackUri: ").append(toIndentedString(callbackUri)).append("\n");
    sb.append("    _links: ").append(toIndentedString(_links)).append("\n");
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
