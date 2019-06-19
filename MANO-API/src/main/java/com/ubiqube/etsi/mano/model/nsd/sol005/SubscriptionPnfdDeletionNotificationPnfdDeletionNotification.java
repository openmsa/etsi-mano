package com.ubiqube.etsi.mano.model.nsd.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
  * \"This type represents a PNFD management notification, which informs the receiver of the deletion of an on-boarded PNFD. The notification shall comply with the provisions defined in Table 5.5.2.15-1. The support of this notification is mandatory. The notification is triggered when an on-boarded PNFD is deleted.\" 
 **/
@ApiModel(description="\"This type represents a PNFD management notification, which informs the receiver of the deletion of an on-boarded PNFD. The notification shall comply with the provisions defined in Table 5.5.2.15-1. The support of this notification is mandatory. The notification is triggered when an on-boarded PNFD is deleted.\" ")
public class SubscriptionPnfdDeletionNotificationPnfdDeletionNotification  {
  
  @ApiModelProperty(required = true, value = "\"Identifier of this notification. If a notification is sent multiple times due to multiple subscriptions, the \"id\" attribute of all these notifications shall have the same value.\" ")
 /**
   * \"Identifier of this notification. If a notification is sent multiple times due to multiple subscriptions, the \"id\" attribute of all these notifications shall have the same value.\" 
  **/
  private String id = null;

  @ApiModelProperty(required = true, value = "\"Discriminator for the different notification types. Shall be set to \"PnfdDeletionNotification \" for this notification type.\" ")
 /**
   * \"Discriminator for the different notification types. Shall be set to \"PnfdDeletionNotification \" for this notification type.\" 
  **/
  private String notificationType = null;

  @ApiModelProperty(value = "An identifier with the intention of being globally unique. ")
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
  private String pnfdInfoId = null;

  @ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String pnfdId = null;

  @ApiModelProperty(required = true, value = "")
  @Valid
  private SubscriptionPnfdOnBoardingNotificationPnfdOnBoardingNotificationLinks links = null;
 /**
   * \&quot;Identifier of this notification. If a notification is sent multiple times due to multiple subscriptions, the \&quot;id\&quot; attribute of all these notifications shall have the same value.\&quot; 
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

  public SubscriptionPnfdDeletionNotificationPnfdDeletionNotification id(String id) {
    this.id = id;
    return this;
  }

 /**
   * \&quot;Discriminator for the different notification types. Shall be set to \&quot;PnfdDeletionNotification \&quot; for this notification type.\&quot; 
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

  public SubscriptionPnfdDeletionNotificationPnfdDeletionNotification notificationType(String notificationType) {
    this.notificationType = notificationType;
    return this;
  }

 /**
   * An identifier with the intention of being globally unique. 
   * @return subscriptionId
  **/
  @JsonProperty("subscriptionId")
  public String getSubscriptionId() {
    return subscriptionId;
  }

  public void setSubscriptionId(String subscriptionId) {
    this.subscriptionId = subscriptionId;
  }

  public SubscriptionPnfdDeletionNotificationPnfdDeletionNotification subscriptionId(String subscriptionId) {
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

  public SubscriptionPnfdDeletionNotificationPnfdDeletionNotification timeStamp(Date timeStamp) {
    this.timeStamp = timeStamp;
    return this;
  }

 /**
   * An identifier with the intention of being globally unique. 
   * @return pnfdInfoId
  **/
  @JsonProperty("pnfdInfoId")
  @NotNull
  public String getPnfdInfoId() {
    return pnfdInfoId;
  }

  public void setPnfdInfoId(String pnfdInfoId) {
    this.pnfdInfoId = pnfdInfoId;
  }

  public SubscriptionPnfdDeletionNotificationPnfdDeletionNotification pnfdInfoId(String pnfdInfoId) {
    this.pnfdInfoId = pnfdInfoId;
    return this;
  }

 /**
   * An identifier with the intention of being globally unique. 
   * @return pnfdId
  **/
  @JsonProperty("pnfdId")
  @NotNull
  public String getPnfdId() {
    return pnfdId;
  }

  public void setPnfdId(String pnfdId) {
    this.pnfdId = pnfdId;
  }

  public SubscriptionPnfdDeletionNotificationPnfdDeletionNotification pnfdId(String pnfdId) {
    this.pnfdId = pnfdId;
    return this;
  }

 /**
   * Get links
   * @return links
  **/
  @JsonProperty("_links")
  @NotNull
  public SubscriptionPnfdOnBoardingNotificationPnfdOnBoardingNotificationLinks getLinks() {
    return links;
  }

  public void setLinks(SubscriptionPnfdOnBoardingNotificationPnfdOnBoardingNotificationLinks links) {
    this.links = links;
  }

  public SubscriptionPnfdDeletionNotificationPnfdDeletionNotification links(SubscriptionPnfdOnBoardingNotificationPnfdOnBoardingNotificationLinks links) {
    this.links = links;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class URIIsProvidedByTheClientWhenCreatingTheSubscriptionPnfdDeletionNotificationPnfdDeletionNotification {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    notificationType: ").append(toIndentedString(notificationType)).append("\n");
    sb.append("    subscriptionId: ").append(toIndentedString(subscriptionId)).append("\n");
    sb.append("    timeStamp: ").append(toIndentedString(timeStamp)).append("\n");
    sb.append("    pnfdInfoId: ").append(toIndentedString(pnfdInfoId)).append("\n");
    sb.append("    pnfdId: ").append(toIndentedString(pnfdId)).append("\n");
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

