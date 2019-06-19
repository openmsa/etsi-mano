package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;

public class NsIdentifierCreationNotification  {
  
  @ApiModelProperty(value = "")
  @Valid
  private SubscriptionNsIdentifierCreationNotificationNsIdentifierCreationNotification nsIdentifierCreationNotification = null;
 /**
   * Get nsIdentifierCreationNotification
   * @return nsIdentifierCreationNotification
  **/
  @JsonProperty("NsIdentifierCreationNotification")
  public SubscriptionNsIdentifierCreationNotificationNsIdentifierCreationNotification getNsIdentifierCreationNotification() {
    return nsIdentifierCreationNotification;
  }

  public void setNsIdentifierCreationNotification(SubscriptionNsIdentifierCreationNotificationNsIdentifierCreationNotification nsIdentifierCreationNotification) {
    this.nsIdentifierCreationNotification = nsIdentifierCreationNotification;
  }

  public NsIdentifierCreationNotification nsIdentifierCreationNotification(SubscriptionNsIdentifierCreationNotificationNsIdentifierCreationNotification nsIdentifierCreationNotification) {
    this.nsIdentifierCreationNotification = nsIdentifierCreationNotification;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsIdentifierCreationNotification {\n");
    
    sb.append("    nsIdentifierCreationNotification: ").append(toIndentedString(nsIdentifierCreationNotification)).append("\n");
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

