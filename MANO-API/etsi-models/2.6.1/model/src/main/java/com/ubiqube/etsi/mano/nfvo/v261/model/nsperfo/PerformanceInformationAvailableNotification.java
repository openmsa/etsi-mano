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
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;

public class PerformanceInformationAvailableNotification  {
  
  @ApiModelProperty(value = "")
  @Valid
  private SubscriptionPerformanceInformationAvailableNotificationPerformanceInformationAvailableNotification performanceInformationAvailableNotification = null;
 /**
   * Get performanceInformationAvailableNotification
   * @return performanceInformationAvailableNotification
  **/
  @JsonProperty("PerformanceInformationAvailableNotification")
  public SubscriptionPerformanceInformationAvailableNotificationPerformanceInformationAvailableNotification getPerformanceInformationAvailableNotification() {
    return performanceInformationAvailableNotification;
  }

  public void setPerformanceInformationAvailableNotification(SubscriptionPerformanceInformationAvailableNotificationPerformanceInformationAvailableNotification performanceInformationAvailableNotification) {
    this.performanceInformationAvailableNotification = performanceInformationAvailableNotification;
  }

  public PerformanceInformationAvailableNotification performanceInformationAvailableNotification(SubscriptionPerformanceInformationAvailableNotificationPerformanceInformationAvailableNotification performanceInformationAvailableNotification) {
    this.performanceInformationAvailableNotification = performanceInformationAvailableNotification;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PerformanceInformationAvailableNotification {\n");
    
    sb.append("    performanceInformationAvailableNotification: ").append(toIndentedString(performanceInformationAvailableNotification)).append("\n");
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

