package com.ubiqube.etsi.mano.model.nsperfo.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;

public class ThresholdCrossedNotification  {
  
  @ApiModelProperty(value = "")
  @Valid
  private SubscriptionThresholdCrossedNotificationThresholdCrossedNotification thresholdCrossedNotification = null;
 /**
   * Get thresholdCrossedNotification
   * @return thresholdCrossedNotification
  **/
  @JsonProperty("ThresholdCrossedNotification")
  public SubscriptionThresholdCrossedNotificationThresholdCrossedNotification getThresholdCrossedNotification() {
    return thresholdCrossedNotification;
  }

  public void setThresholdCrossedNotification(SubscriptionThresholdCrossedNotificationThresholdCrossedNotification thresholdCrossedNotification) {
    this.thresholdCrossedNotification = thresholdCrossedNotification;
  }

  public ThresholdCrossedNotification thresholdCrossedNotification(SubscriptionThresholdCrossedNotificationThresholdCrossedNotification thresholdCrossedNotification) {
    this.thresholdCrossedNotification = thresholdCrossedNotification;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ThresholdCrossedNotification {\n");
    
    sb.append("    thresholdCrossedNotification: ").append(toIndentedString(thresholdCrossedNotification)).append("\n");
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

