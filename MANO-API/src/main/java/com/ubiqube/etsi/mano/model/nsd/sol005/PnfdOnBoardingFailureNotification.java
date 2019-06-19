package com.ubiqube.etsi.mano.model.nsd.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;

public class PnfdOnBoardingFailureNotification  {
  
  @ApiModelProperty(value = "")
  @Valid
  private SubscriptionPnfdOnBoardingFailureNotificationPnfdOnBoardingFailureNotification pnfdOnBoardingFailureNotification = null;
 /**
   * Get pnfdOnBoardingFailureNotification
   * @return pnfdOnBoardingFailureNotification
  **/
  @JsonProperty("PnfdOnBoardingFailureNotification")
  public SubscriptionPnfdOnBoardingFailureNotificationPnfdOnBoardingFailureNotification getPnfdOnBoardingFailureNotification() {
    return pnfdOnBoardingFailureNotification;
  }

  public void setPnfdOnBoardingFailureNotification(SubscriptionPnfdOnBoardingFailureNotificationPnfdOnBoardingFailureNotification pnfdOnBoardingFailureNotification) {
    this.pnfdOnBoardingFailureNotification = pnfdOnBoardingFailureNotification;
  }

  public PnfdOnBoardingFailureNotification pnfdOnBoardingFailureNotification(SubscriptionPnfdOnBoardingFailureNotificationPnfdOnBoardingFailureNotification pnfdOnBoardingFailureNotification) {
    this.pnfdOnBoardingFailureNotification = pnfdOnBoardingFailureNotification;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PnfdOnBoardingFailureNotification {\n");
    
    sb.append("    pnfdOnBoardingFailureNotification: ").append(toIndentedString(pnfdOnBoardingFailureNotification)).append("\n");
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

