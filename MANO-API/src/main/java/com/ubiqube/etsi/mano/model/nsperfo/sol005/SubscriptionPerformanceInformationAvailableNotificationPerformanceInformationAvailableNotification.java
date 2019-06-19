package com.ubiqube.etsi.mano.model.nsperfo.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
  * This notification informs the receiver that performance information is available. 
 **/
@ApiModel(description="This notification informs the receiver that performance information is available. ")
public class SubscriptionPerformanceInformationAvailableNotificationPerformanceInformationAvailableNotification  {
  
  @ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String id = null;

  @ApiModelProperty(required = true, value = "Discriminator for the different notification types. Shall be set to \"PerformanceInformationAvailableNotification\" for this notification type. ")
 /**
   * Discriminator for the different notification types. Shall be set to \"PerformanceInformationAvailableNotification\" for this notification type. 
  **/
  private String notificationType = null;

  @ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String subscriptionId = null;

  @ApiModelProperty(required = true, value = "Date-time stamp.  Representation: String formatted according to IETF RFC 3339. ")
 /**
   * Date-time stamp.  Representation: String formatted according to IETF RFC 3339. 
  **/
  private Date timeStamp = null;

  @ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String objectInstanceId = null;

  @ApiModelProperty(required = true, value = "")
  @Valid
  private SubscriptionPerformanceInformationAvailableNotificationPerformanceInformationAvailableNotificationLinks links = null;
 /**
   * An identifier with the intention of being globally unique. 
   * @return id
  **/
  @JsonProperty("id")
  @NotNull
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public SubscriptionPerformanceInformationAvailableNotificationPerformanceInformationAvailableNotification id(String id) {
    this.id = id;
    return this;
  }

 /**
   * Discriminator for the different notification types. Shall be set to \&quot;PerformanceInformationAvailableNotification\&quot; for this notification type. 
   * @return notificationType
  **/
  @JsonProperty("notificationType")
  @NotNull
  public String getNotificationType() {
    return notificationType;
  }

  public void setNotificationType(String notificationType) {
    this.notificationType = notificationType;
  }

  public SubscriptionPerformanceInformationAvailableNotificationPerformanceInformationAvailableNotification notificationType(String notificationType) {
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

  public SubscriptionPerformanceInformationAvailableNotificationPerformanceInformationAvailableNotification subscriptionId(String subscriptionId) {
    this.subscriptionId = subscriptionId;
    return this;
  }

 /**
   * Date-time stamp.  Representation: String formatted according to IETF RFC 3339. 
   * @return timeStamp
  **/
  @JsonProperty("timeStamp")
  @NotNull
  public Date getTimeStamp() {
    return timeStamp;
  }

  public void setTimeStamp(Date timeStamp) {
    this.timeStamp = timeStamp;
  }

  public SubscriptionPerformanceInformationAvailableNotificationPerformanceInformationAvailableNotification timeStamp(Date timeStamp) {
    this.timeStamp = timeStamp;
    return this;
  }

 /**
   * An identifier with the intention of being globally unique. 
   * @return objectInstanceId
  **/
  @JsonProperty("objectInstanceId")
  @NotNull
  public String getObjectInstanceId() {
    return objectInstanceId;
  }

  public void setObjectInstanceId(String objectInstanceId) {
    this.objectInstanceId = objectInstanceId;
  }

  public SubscriptionPerformanceInformationAvailableNotificationPerformanceInformationAvailableNotification objectInstanceId(String objectInstanceId) {
    this.objectInstanceId = objectInstanceId;
    return this;
  }

 /**
   * Get links
   * @return links
  **/
  @JsonProperty("_links")
  @NotNull
  public SubscriptionPerformanceInformationAvailableNotificationPerformanceInformationAvailableNotificationLinks getLinks() {
    return links;
  }

  public void setLinks(SubscriptionPerformanceInformationAvailableNotificationPerformanceInformationAvailableNotificationLinks links) {
    this.links = links;
  }

  public SubscriptionPerformanceInformationAvailableNotificationPerformanceInformationAvailableNotification links(SubscriptionPerformanceInformationAvailableNotificationPerformanceInformationAvailableNotificationLinks links) {
    this.links = links;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class URIIsProvidedByTheClientWhenCreatingTheSubscriptionPerformanceInformationAvailableNotificationPerformanceInformationAvailableNotification {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    notificationType: ").append(toIndentedString(notificationType)).append("\n");
    sb.append("    subscriptionId: ").append(toIndentedString(subscriptionId)).append("\n");
    sb.append("    timeStamp: ").append(toIndentedString(timeStamp)).append("\n");
    sb.append("    objectInstanceId: ").append(toIndentedString(objectInstanceId)).append("\n");
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

