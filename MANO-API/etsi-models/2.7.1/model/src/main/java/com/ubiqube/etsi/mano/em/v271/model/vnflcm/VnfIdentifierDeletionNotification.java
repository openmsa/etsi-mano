package com.ubiqube.etsi.mano.em.v271.model.vnflcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.em.v271.model.vnflcm.LccnLinks;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents a VNF identifier deletion notification, which informs the receiver of the deletion of a new \&quot;Individual VNF instance\&quot; resource and the associated VNF instance identifier. This notification shall be triggered by the VNFM when it has deleted an \&quot;Individual VNF instance\&quot; resource and the associated VNF instance identifier. 
 */
@ApiModel(description = "This type represents a VNF identifier deletion notification, which informs the receiver of the deletion of a new \"Individual VNF instance\" resource and the associated VNF instance identifier. This notification shall be triggered by the VNFM when it has deleted an \"Individual VNF instance\" resource and the associated VNF instance identifier. ")
@Validated

public class VnfIdentifierDeletionNotification   {
  @JsonProperty("id")
  private String id = null;

  /**
   * Discriminator for the different notification types. Shall be set to \"VnfIdentifierDeletionNotification\" for this notification type. 
   */
  public enum NotificationTypeEnum {
    VNFIDENTIFIERDELETIONNOTIFICATION("VnfIdentifierDeletionNotification");

    private String value;

    NotificationTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static NotificationTypeEnum fromValue(String text) {
      for (NotificationTypeEnum b : NotificationTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("notificationType")
  private NotificationTypeEnum notificationType = null;

  @JsonProperty("subscriptionId")
  private String subscriptionId = null;

  @JsonProperty("timeStamp")
  private String timeStamp = null;

  @JsonProperty("vnfInstanceId")
  private String vnfInstanceId = null;

  @JsonProperty("_links")
  private LccnLinks links = null;

  public VnfIdentifierDeletionNotification id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Identifier of this notification. If a notification is sent multiple times due to multiple subscriptions, the \"id\" attribute of all these notifications shall have the same value. 
   * @return id
  **/
  @ApiModelProperty(required = true, value = "Identifier of this notification. If a notification is sent multiple times due to multiple subscriptions, the \"id\" attribute of all these notifications shall have the same value. ")
  @NotNull


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public VnfIdentifierDeletionNotification notificationType(NotificationTypeEnum notificationType) {
    this.notificationType = notificationType;
    return this;
  }

  /**
   * Discriminator for the different notification types. Shall be set to \"VnfIdentifierDeletionNotification\" for this notification type. 
   * @return notificationType
  **/
  @ApiModelProperty(required = true, value = "Discriminator for the different notification types. Shall be set to \"VnfIdentifierDeletionNotification\" for this notification type. ")
  @NotNull


  public NotificationTypeEnum getNotificationType() {
    return notificationType;
  }

  public void setNotificationType(NotificationTypeEnum notificationType) {
    this.notificationType = notificationType;
  }

  public VnfIdentifierDeletionNotification subscriptionId(String subscriptionId) {
    this.subscriptionId = subscriptionId;
    return this;
  }

  /**
   * Identifier of the subscription that this notification relates to. 
   * @return subscriptionId
  **/
  @ApiModelProperty(required = true, value = "Identifier of the subscription that this notification relates to. ")
  @NotNull


  public String getSubscriptionId() {
    return subscriptionId;
  }

  public void setSubscriptionId(String subscriptionId) {
    this.subscriptionId = subscriptionId;
  }

  public VnfIdentifierDeletionNotification timeStamp(String timeStamp) {
    this.timeStamp = timeStamp;
    return this;
  }

  /**
   * Date-time of the generation of the notification. 
   * @return timeStamp
  **/
  @ApiModelProperty(required = true, value = "Date-time of the generation of the notification. ")
  @NotNull


  public String getTimeStamp() {
    return timeStamp;
  }

  public void setTimeStamp(String timeStamp) {
    this.timeStamp = timeStamp;
  }

  public VnfIdentifierDeletionNotification vnfInstanceId(String vnfInstanceId) {
    this.vnfInstanceId = vnfInstanceId;
    return this;
  }

  /**
   * The deleted VNF instance identifier. 
   * @return vnfInstanceId
  **/
  @ApiModelProperty(required = true, value = "The deleted VNF instance identifier. ")
  @NotNull


  public String getVnfInstanceId() {
    return vnfInstanceId;
  }

  public void setVnfInstanceId(String vnfInstanceId) {
    this.vnfInstanceId = vnfInstanceId;
  }

  public VnfIdentifierDeletionNotification links(LccnLinks links) {
    this.links = links;
    return this;
  }

  /**
   * Links to resources related to this notification. 
   * @return links
  **/
  @ApiModelProperty(required = true, value = "Links to resources related to this notification. ")
  @NotNull

  @Valid

  public LccnLinks getLinks() {
    return links;
  }

  public void setLinks(LccnLinks links) {
    this.links = links;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VnfIdentifierDeletionNotification vnfIdentifierDeletionNotification = (VnfIdentifierDeletionNotification) o;
    return Objects.equals(this.id, vnfIdentifierDeletionNotification.id) &&
        Objects.equals(this.notificationType, vnfIdentifierDeletionNotification.notificationType) &&
        Objects.equals(this.subscriptionId, vnfIdentifierDeletionNotification.subscriptionId) &&
        Objects.equals(this.timeStamp, vnfIdentifierDeletionNotification.timeStamp) &&
        Objects.equals(this.vnfInstanceId, vnfIdentifierDeletionNotification.vnfInstanceId) &&
        Objects.equals(this.links, vnfIdentifierDeletionNotification.links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, notificationType, subscriptionId, timeStamp, vnfInstanceId, links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfIdentifierDeletionNotification {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    notificationType: ").append(toIndentedString(notificationType)).append("\n");
    sb.append("    subscriptionId: ").append(toIndentedString(subscriptionId)).append("\n");
    sb.append("    timeStamp: ").append(toIndentedString(timeStamp)).append("\n");
    sb.append("    vnfInstanceId: ").append(toIndentedString(vnfInstanceId)).append("\n");
    sb.append("    links: ").append(toIndentedString(links)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

