package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;

public class NsIdentifierDeletionNotification  {
  
  @ApiModelProperty(value = "")
  @Valid
  private SubscriptionNsIdentifierCreationNotificationNsIdentifierCreationNotification nsIdentifierDeletionNotification = null;
 /**
   * Get nsIdentifierDeletionNotification
   * @return nsIdentifierDeletionNotification
  **/
  @JsonProperty("NsIdentifierDeletionNotification")
  public SubscriptionNsIdentifierCreationNotificationNsIdentifierCreationNotification getNsIdentifierDeletionNotification() {
    return nsIdentifierDeletionNotification;
  }

  public void setNsIdentifierDeletionNotification(SubscriptionNsIdentifierCreationNotificationNsIdentifierCreationNotification nsIdentifierDeletionNotification) {
    this.nsIdentifierDeletionNotification = nsIdentifierDeletionNotification;
  }

  public NsIdentifierDeletionNotification nsIdentifierDeletionNotification(SubscriptionNsIdentifierCreationNotificationNsIdentifierCreationNotification nsIdentifierDeletionNotification) {
    this.nsIdentifierDeletionNotification = nsIdentifierDeletionNotification;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsIdentifierDeletionNotification {\n");
    
    sb.append("    nsIdentifierDeletionNotification: ").append(toIndentedString(nsIdentifierDeletionNotification)).append("\n");
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

