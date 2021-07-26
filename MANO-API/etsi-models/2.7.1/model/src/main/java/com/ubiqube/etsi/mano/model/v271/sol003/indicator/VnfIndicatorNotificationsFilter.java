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
package com.ubiqube.etsi.mano.model.v271.sol003.indicator;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.model.v271.sol003.indicator.VnfInstanceSubscriptionFilter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents a subscription filter related to notifications about VNF indicator value changes. At a particular nesting level in the filter structure, the following applies: All attributes shall match in order for the filter to match (logical \&quot;and\&quot; between different filter attributes). If an attribute is an array, the attribute shall match if at least one of the values in the array matches (logical \&quot;or\&quot; between the values of one filter attribute). 
 */
@ApiModel(description = "This type represents a subscription filter related to notifications about VNF indicator value changes. At a particular nesting level in the filter structure, the following applies: All attributes shall match in order for the filter to match (logical \"and\" between different filter attributes). If an attribute is an array, the attribute shall match if at least one of the values in the array matches (logical \"or\" between the values of one filter attribute). ")
@Validated


public class VnfIndicatorNotificationsFilter   {
  @JsonProperty("vnfInstanceSubscriptionFilter")
  private VnfInstanceSubscriptionFilter vnfInstanceSubscriptionFilter = null;

  @JsonProperty("indicatorIds")
  @Valid
  private List<String> indicatorIds = null;

  public VnfIndicatorNotificationsFilter vnfInstanceSubscriptionFilter(VnfInstanceSubscriptionFilter vnfInstanceSubscriptionFilter) {
    this.vnfInstanceSubscriptionFilter = vnfInstanceSubscriptionFilter;
    return this;
  }

  /**
   * Filter criteria to select VNF instances about which to notify. 
   * @return vnfInstanceSubscriptionFilter
  **/
  @ApiModelProperty(value = "Filter criteria to select VNF instances about which to notify. ")

  @Valid

  public VnfInstanceSubscriptionFilter getVnfInstanceSubscriptionFilter() {
    return vnfInstanceSubscriptionFilter;
  }

  public void setVnfInstanceSubscriptionFilter(VnfInstanceSubscriptionFilter vnfInstanceSubscriptionFilter) {
    this.vnfInstanceSubscriptionFilter = vnfInstanceSubscriptionFilter;
  }

  public VnfIndicatorNotificationsFilter indicatorIds(List<String> indicatorIds) {
    this.indicatorIds = indicatorIds;
    return this;
  }

  public VnfIndicatorNotificationsFilter addIndicatorIdsItem(String indicatorIdsItem) {
    if (this.indicatorIds == null) {
      this.indicatorIds = new ArrayList<>();
    }
    this.indicatorIds.add(indicatorIdsItem);
    return this;
  }

  /**
   * Match particular VNF indicator identifiers. 
   * @return indicatorIds
  **/
  @ApiModelProperty(value = "Match particular VNF indicator identifiers. ")


  public List<String> getIndicatorIds() {
    return indicatorIds;
  }

  public void setIndicatorIds(List<String> indicatorIds) {
    this.indicatorIds = indicatorIds;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VnfIndicatorNotificationsFilter vnfIndicatorNotificationsFilter = (VnfIndicatorNotificationsFilter) o;
    return Objects.equals(this.vnfInstanceSubscriptionFilter, vnfIndicatorNotificationsFilter.vnfInstanceSubscriptionFilter) &&
        Objects.equals(this.indicatorIds, vnfIndicatorNotificationsFilter.indicatorIds);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vnfInstanceSubscriptionFilter, indicatorIds);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfIndicatorNotificationsFilter {\n");
    
    sb.append("    vnfInstanceSubscriptionFilter: ").append(toIndentedString(vnfInstanceSubscriptionFilter)).append("\n");
    sb.append("    indicatorIds: ").append(toIndentedString(indicatorIds)).append("\n");
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

