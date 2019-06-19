package com.ubiqube.etsi.mano.model.nsd.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;

public class PnfdDeletionNotification  {
  
  @ApiModelProperty(value = "")
  @Valid
  private SubscriptionPnfdDeletionNotificationPnfdDeletionNotification pnfdDeletionNotification = null;
 /**
   * Get pnfdDeletionNotification
   * @return pnfdDeletionNotification
  **/
  @JsonProperty("PnfdDeletionNotification")
  public SubscriptionPnfdDeletionNotificationPnfdDeletionNotification getPnfdDeletionNotification() {
    return pnfdDeletionNotification;
  }

  public void setPnfdDeletionNotification(SubscriptionPnfdDeletionNotificationPnfdDeletionNotification pnfdDeletionNotification) {
    this.pnfdDeletionNotification = pnfdDeletionNotification;
  }

  public PnfdDeletionNotification pnfdDeletionNotification(SubscriptionPnfdDeletionNotificationPnfdDeletionNotification pnfdDeletionNotification) {
    this.pnfdDeletionNotification = pnfdDeletionNotification;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PnfdDeletionNotification {\n");
    
    sb.append("    pnfdDeletionNotification: ").append(toIndentedString(pnfdDeletionNotification)).append("\n");
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

