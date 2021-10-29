package com.ubiqube.etsi.mano.em.v351.model.vnflcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.em.v351.model.vnflcm.LccnLinks;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents a VNF identifier deletion notification, which informs the receiver of the deletion of a new \&quot;Individual VNF instance\&quot; resource and the associated VNF instance identifier. This notification shall be triggered by the VNFM when it has deleted an \&quot;Individual VNF instance\&quot; resource and the associated VNF instance identifier. 
 */
@Schema(description = "This type represents a VNF identifier deletion notification, which informs the receiver of the deletion of a new \"Individual VNF instance\" resource and the associated VNF instance identifier. This notification shall be triggered by the VNFM when it has deleted an \"Individual VNF instance\" resource and the associated VNF instance identifier. ")
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
  private OffsetDateTime timeStamp = null;

  @JsonProperty("vnfInstanceId")
  private String vnfInstanceId = null;

  @JsonProperty("_links")
  private LccnLinks _links = null;

  public VnfIdentifierDeletionNotification id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @Schema(required = true, description = "")
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
  @Schema(required = true, description = "Discriminator for the different notification types. Shall be set to \"VnfIdentifierDeletionNotification\" for this notification type. ")
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
   * Get subscriptionId
   * @return subscriptionId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getSubscriptionId() {
    return subscriptionId;
  }

  public void setSubscriptionId(String subscriptionId) {
    this.subscriptionId = subscriptionId;
  }

  public VnfIdentifierDeletionNotification timeStamp(OffsetDateTime timeStamp) {
    this.timeStamp = timeStamp;
    return this;
  }

  /**
   * Get timeStamp
   * @return timeStamp
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public OffsetDateTime getTimeStamp() {
    return timeStamp;
  }

  public void setTimeStamp(OffsetDateTime timeStamp) {
    this.timeStamp = timeStamp;
  }

  public VnfIdentifierDeletionNotification vnfInstanceId(String vnfInstanceId) {
    this.vnfInstanceId = vnfInstanceId;
    return this;
  }

  /**
   * Get vnfInstanceId
   * @return vnfInstanceId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getVnfInstanceId() {
    return vnfInstanceId;
  }

  public void setVnfInstanceId(String vnfInstanceId) {
    this.vnfInstanceId = vnfInstanceId;
  }

  public VnfIdentifierDeletionNotification _links(LccnLinks _links) {
    this._links = _links;
    return this;
  }

  /**
   * Get _links
   * @return _links
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public LccnLinks getLinks() {
    return _links;
  }

  public void setLinks(LccnLinks _links) {
    this._links = _links;
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
        Objects.equals(this._links, vnfIdentifierDeletionNotification._links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, notificationType, subscriptionId, timeStamp, vnfInstanceId, _links);
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
    sb.append("    _links: ").append(toIndentedString(_links)).append("\n");
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
