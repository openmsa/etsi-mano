package com.ubiqube.etsi.mano.model.nsd.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;

public class NsdOnBoardingNotification  {
  
  @ApiModelProperty(value = "")
  @Valid
  private SubscriptionNsdOnBoardingNotificationNsdOnBoardingNotification nsdOnBoardingNotification = null;
 /**
   * Get nsdOnBoardingNotification
   * @return nsdOnBoardingNotification
  **/
  @JsonProperty("NsdOnBoardingNotification")
  public SubscriptionNsdOnBoardingNotificationNsdOnBoardingNotification getNsdOnBoardingNotification() {
    return nsdOnBoardingNotification;
  }

  public void setNsdOnBoardingNotification(SubscriptionNsdOnBoardingNotificationNsdOnBoardingNotification nsdOnBoardingNotification) {
    this.nsdOnBoardingNotification = nsdOnBoardingNotification;
  }

  public NsdOnBoardingNotification nsdOnBoardingNotification(SubscriptionNsdOnBoardingNotificationNsdOnBoardingNotification nsdOnBoardingNotification) {
    this.nsdOnBoardingNotification = nsdOnBoardingNotification;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsdOnBoardingNotification {\n");
    
    sb.append("    nsdOnBoardingNotification: ").append(toIndentedString(nsdOnBoardingNotification)).append("\n");
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

