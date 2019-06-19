package com.ubiqube.etsi.mano.model.nsd.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;

public class NsdChangeNotification  {
  
  @ApiModelProperty(value = "")
  @Valid
  private SubscriptionNsdChangeNotificationNsdChangeNotification nsdChangeNotification = null;
 /**
   * Get nsdChangeNotification
   * @return nsdChangeNotification
  **/
  @JsonProperty("NsdChangeNotification")
  public SubscriptionNsdChangeNotificationNsdChangeNotification getNsdChangeNotification() {
    return nsdChangeNotification;
  }

  public void setNsdChangeNotification(SubscriptionNsdChangeNotificationNsdChangeNotification nsdChangeNotification) {
    this.nsdChangeNotification = nsdChangeNotification;
  }

  public NsdChangeNotification nsdChangeNotification(SubscriptionNsdChangeNotificationNsdChangeNotification nsdChangeNotification) {
    this.nsdChangeNotification = nsdChangeNotification;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsdChangeNotification {\n");
    
    sb.append("    nsdChangeNotification: ").append(toIndentedString(nsdChangeNotification)).append("\n");
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

