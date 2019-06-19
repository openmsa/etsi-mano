package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class SubscriptionNsIdentifierCreationNotificationNsIdentifierCreationNotification  {
  
  @ApiModelProperty(value = "Discriminator for the different notification types. Shall be set to \"NsIdentifierDeletionNotification\" for this notification type. ")
 /**
   * Discriminator for the different notification types. Shall be set to \"NsIdentifierDeletionNotification\" for this notification type. 
  **/
  private String notificationType = null;

  @ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String subscriptionId = null;

  @ApiModelProperty(value = "Date-time stamp.  Representation: String formatted according to IETF RFC 3339. ")
 /**
   * Date-time stamp.  Representation: String formatted according to IETF RFC 3339. 
  **/
  private Date timestamp = null;

  @ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String nsInstanceId = null;

  @ApiModelProperty(value = "")
  @Valid
  private SubscriptionNsLcmOperationOccurrenceNotificationNsLcmOperationOccurrenceNotificationLinks links = null;
 /**
   * Discriminator for the different notification types. Shall be set to \&quot;NsIdentifierDeletionNotification\&quot; for this notification type. 
   * @return notificationType
  **/
  @JsonProperty("notificationType")
  public String getNotificationType() {
    return notificationType;
  }

  public void setNotificationType(String notificationType) {
    this.notificationType = notificationType;
  }

  public SubscriptionNsIdentifierCreationNotificationNsIdentifierCreationNotification notificationType(String notificationType) {
    this.notificationType = notificationType;
    return this;
  }

 /**
   * An identifier with the intention of being globally unique. 
   * @return subscriptionId
  **/
  @JsonProperty("subscriptionId")
  @NotNull
  public String getSubscriptionId() {
    return subscriptionId;
  }

  public void setSubscriptionId(String subscriptionId) {
    this.subscriptionId = subscriptionId;
  }

  public SubscriptionNsIdentifierCreationNotificationNsIdentifierCreationNotification subscriptionId(String subscriptionId) {
    this.subscriptionId = subscriptionId;
    return this;
  }

 /**
   * Date-time stamp.  Representation: String formatted according to IETF RFC 3339. 
   * @return timestamp
  **/
  @JsonProperty("timestamp")
  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  public SubscriptionNsIdentifierCreationNotificationNsIdentifierCreationNotification timestamp(Date timestamp) {
    this.timestamp = timestamp;
    return this;
  }

 /**
   * An identifier with the intention of being globally unique. 
   * @return nsInstanceId
  **/
  @JsonProperty("nsInstanceId")
  @NotNull
  public String getNsInstanceId() {
    return nsInstanceId;
  }

  public void setNsInstanceId(String nsInstanceId) {
    this.nsInstanceId = nsInstanceId;
  }

  public SubscriptionNsIdentifierCreationNotificationNsIdentifierCreationNotification nsInstanceId(String nsInstanceId) {
    this.nsInstanceId = nsInstanceId;
    return this;
  }

 /**
   * Get links
   * @return links
  **/
  @JsonProperty("_links")
  public SubscriptionNsLcmOperationOccurrenceNotificationNsLcmOperationOccurrenceNotificationLinks getLinks() {
    return links;
  }

  public void setLinks(SubscriptionNsLcmOperationOccurrenceNotificationNsLcmOperationOccurrenceNotificationLinks links) {
    this.links = links;
  }

  public SubscriptionNsIdentifierCreationNotificationNsIdentifierCreationNotification links(SubscriptionNsLcmOperationOccurrenceNotificationNsLcmOperationOccurrenceNotificationLinks links) {
    this.links = links;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class URIIsProvidedByTheClientWhenCreatingTheSubscriptionNsIdentifierCreationNotificationNsIdentifierCreationNotification {\n");
    
    sb.append("    notificationType: ").append(toIndentedString(notificationType)).append("\n");
    sb.append("    subscriptionId: ").append(toIndentedString(subscriptionId)).append("\n");
    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
    sb.append("    nsInstanceId: ").append(toIndentedString(nsInstanceId)).append("\n");
    sb.append("    links: ").append(toIndentedString(links)).append("\n");
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

