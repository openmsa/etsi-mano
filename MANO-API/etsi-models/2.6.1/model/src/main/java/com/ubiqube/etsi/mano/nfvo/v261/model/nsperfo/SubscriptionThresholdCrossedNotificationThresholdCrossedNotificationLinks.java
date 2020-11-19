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
  * Links to resources related to this notification. 
 **/
@ApiModel(description="Links to resources related to this notification. ")
public class SubscriptionThresholdCrossedNotificationThresholdCrossedNotificationLinks  {
  
  @ApiModelProperty(required = true, value = "")
  @Valid
  private PmJobsPmJobReportsLinksSelf subscription = null;

  @ApiModelProperty(required = true, value = "")
  @Valid
  private PmJobsPmJobReportsLinksSelf objectInstance = null;

  @ApiModelProperty(required = true, value = "")
  @Valid
  private PmJobsPmJobReportsLinksSelf threshold = null;
 /**
   * Get subscription
   * @return subscription
  **/
  @JsonProperty("subscription")
  @NotNull
  public PmJobsPmJobReportsLinksSelf getSubscription() {
    return subscription;
  }

  public void setSubscription(PmJobsPmJobReportsLinksSelf subscription) {
    this.subscription = subscription;
  }

  public SubscriptionThresholdCrossedNotificationThresholdCrossedNotificationLinks subscription(PmJobsPmJobReportsLinksSelf subscription) {
    this.subscription = subscription;
    return this;
  }

 /**
   * Get objectInstance
   * @return objectInstance
  **/
  @JsonProperty("objectInstance")
  @NotNull
  public PmJobsPmJobReportsLinksSelf getObjectInstance() {
    return objectInstance;
  }

  public void setObjectInstance(PmJobsPmJobReportsLinksSelf objectInstance) {
    this.objectInstance = objectInstance;
  }

  public SubscriptionThresholdCrossedNotificationThresholdCrossedNotificationLinks objectInstance(PmJobsPmJobReportsLinksSelf objectInstance) {
    this.objectInstance = objectInstance;
    return this;
  }

 /**
   * Get threshold
   * @return threshold
  **/
  @JsonProperty("threshold")
  @NotNull
  public PmJobsPmJobReportsLinksSelf getThreshold() {
    return threshold;
  }

  public void setThreshold(PmJobsPmJobReportsLinksSelf threshold) {
    this.threshold = threshold;
  }

  public SubscriptionThresholdCrossedNotificationThresholdCrossedNotificationLinks threshold(PmJobsPmJobReportsLinksSelf threshold) {
    this.threshold = threshold;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class URIIsProvidedByTheClientWhenCreatingTheSubscriptionThresholdCrossedNotificationThresholdCrossedNotificationLinks {\n");
    
    sb.append("    subscription: ").append(toIndentedString(subscription)).append("\n");
    sb.append("    objectInstance: ").append(toIndentedString(objectInstance)).append("\n");
    sb.append("    threshold: ").append(toIndentedString(threshold)).append("\n");
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

