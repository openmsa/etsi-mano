package com.ubiqube.etsi.mano.model.nsd.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;

public class PnfdOnBoardingNotification  {
  
  @ApiModelProperty(value = "")
  @Valid
  private SubscriptionPnfdOnBoardingNotificationPnfdOnBoardingNotification pnfdOnBoardingNotification = null;
 /**
   * Get pnfdOnBoardingNotification
   * @return pnfdOnBoardingNotification
  **/
  @JsonProperty("PnfdOnBoardingNotification")
  public SubscriptionPnfdOnBoardingNotificationPnfdOnBoardingNotification getPnfdOnBoardingNotification() {
    return pnfdOnBoardingNotification;
  }

  public void setPnfdOnBoardingNotification(SubscriptionPnfdOnBoardingNotificationPnfdOnBoardingNotification pnfdOnBoardingNotification) {
    this.pnfdOnBoardingNotification = pnfdOnBoardingNotification;
  }

  public PnfdOnBoardingNotification pnfdOnBoardingNotification(SubscriptionPnfdOnBoardingNotificationPnfdOnBoardingNotification pnfdOnBoardingNotification) {
    this.pnfdOnBoardingNotification = pnfdOnBoardingNotification;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PnfdOnBoardingNotification {\n");
    
    sb.append("    pnfdOnBoardingNotification: ").append(toIndentedString(pnfdOnBoardingNotification)).append("\n");
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

