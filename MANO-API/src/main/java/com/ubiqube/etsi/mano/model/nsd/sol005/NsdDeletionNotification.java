package com.ubiqube.etsi.mano.model.nsd.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;

public class NsdDeletionNotification  {
  
  @ApiModelProperty(value = "")
  @Valid
  private SubscriptionNsdDeletionNotificationNsdDeletionNotification nsdDeletionNotification = null;
 /**
   * Get nsdDeletionNotification
   * @return nsdDeletionNotification
  **/
  @JsonProperty("NsdDeletionNotification")
  public SubscriptionNsdDeletionNotificationNsdDeletionNotification getNsdDeletionNotification() {
    return nsdDeletionNotification;
  }

  public void setNsdDeletionNotification(SubscriptionNsdDeletionNotificationNsdDeletionNotification nsdDeletionNotification) {
    this.nsdDeletionNotification = nsdDeletionNotification;
  }

  public NsdDeletionNotification nsdDeletionNotification(SubscriptionNsdDeletionNotificationNsdDeletionNotification nsdDeletionNotification) {
    this.nsdDeletionNotification = nsdDeletionNotification;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsdDeletionNotification {\n");
    
    sb.append("    nsdDeletionNotification: ").append(toIndentedString(nsdDeletionNotification)).append("\n");
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

