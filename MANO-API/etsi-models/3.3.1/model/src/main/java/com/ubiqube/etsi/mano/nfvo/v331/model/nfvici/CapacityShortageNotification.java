/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.etsi.mano.nfvo.v331.model.nfvici;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.nfvo.v331.model.nfvici.CapacityShortageNotificationLinks;
import com.ubiqube.etsi.mano.nfvo.v331.model.nfvici.NfviCapacityMeasurement;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This notification informs the receiver that the available NFVI capacity has crossed below a threshold value or has re-covered from a capacity shortage. It shall comply with the provisions defined in Table 10.5.2.10-1. 
 */
@Schema(description = "This notification informs the receiver that the available NFVI capacity has crossed below a threshold value or has re-covered from a capacity shortage. It shall comply with the provisions defined in Table 10.5.2.10-1. ")
@Validated


public class CapacityShortageNotification   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("notificationType")
  private String notificationType = null;

  @JsonProperty("thresholdId")
  private String thresholdId = null;

  @JsonProperty("timeStamp")
  private OffsetDateTime timeStamp = null;

  @JsonProperty("objectInstanceId")
  private String objectInstanceId = null;

  @JsonProperty("subObjectInstanceId")
  private String subObjectInstanceId = null;

  /**
   * Specifies if the threshold has been crossed in UP or DOWN direction. 
   */
  public enum DirectionEnum {
    UP("UP"),
    
    DOWN("DOWN");

    private String value;

    DirectionEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static DirectionEnum fromValue(String text) {
      for (DirectionEnum b : DirectionEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("direction")
  private DirectionEnum direction = null;

  @JsonProperty("capacityInformation")
  private NfviCapacityMeasurement capacityInformation = null;

  @JsonProperty("_links")
  private CapacityShortageNotificationLinks _links = null;

  public CapacityShortageNotification id(String id) {
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

  public CapacityShortageNotification notificationType(String notificationType) {
    this.notificationType = notificationType;
    return this;
  }

  /**
   * Discriminator for the different notification types. Shall be set to \"CapacityShortageNotification\" for this notification type. 
   * @return notificationType
   **/
  @Schema(required = true, description = "Discriminator for the different notification types. Shall be set to \"CapacityShortageNotification\" for this notification type. ")
      @NotNull

    public String getNotificationType() {
    return notificationType;
  }

  public void setNotificationType(String notificationType) {
    this.notificationType = notificationType;
  }

  public CapacityShortageNotification thresholdId(String thresholdId) {
    this.thresholdId = thresholdId;
    return this;
  }

  /**
   * Get thresholdId
   * @return thresholdId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getThresholdId() {
    return thresholdId;
  }

  public void setThresholdId(String thresholdId) {
    this.thresholdId = thresholdId;
  }

  public CapacityShortageNotification timeStamp(OffsetDateTime timeStamp) {
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

  public CapacityShortageNotification objectInstanceId(String objectInstanceId) {
    this.objectInstanceId = objectInstanceId;
    return this;
  }

  /**
   * Get objectInstanceId
   * @return objectInstanceId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getObjectInstanceId() {
    return objectInstanceId;
  }

  public void setObjectInstanceId(String objectInstanceId) {
    this.objectInstanceId = objectInstanceId;
  }

  public CapacityShortageNotification subObjectInstanceId(String subObjectInstanceId) {
    this.subObjectInstanceId = subObjectInstanceId;
    return this;
  }

  /**
   * Get subObjectInstanceId
   * @return subObjectInstanceId
   **/
  @Schema(description = "")
  
    public String getSubObjectInstanceId() {
    return subObjectInstanceId;
  }

  public void setSubObjectInstanceId(String subObjectInstanceId) {
    this.subObjectInstanceId = subObjectInstanceId;
  }

  public CapacityShortageNotification direction(DirectionEnum direction) {
    this.direction = direction;
    return this;
  }

  /**
   * Specifies if the threshold has been crossed in UP or DOWN direction. 
   * @return direction
   **/
  @Schema(required = true, description = "Specifies if the threshold has been crossed in UP or DOWN direction. ")
      @NotNull

    public DirectionEnum getDirection() {
    return direction;
  }

  public void setDirection(DirectionEnum direction) {
    this.direction = direction;
  }

  public CapacityShortageNotification capacityInformation(NfviCapacityMeasurement capacityInformation) {
    this.capacityInformation = capacityInformation;
    return this;
  }

  /**
   * Get capacityInformation
   * @return capacityInformation
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public NfviCapacityMeasurement getCapacityInformation() {
    return capacityInformation;
  }

  public void setCapacityInformation(NfviCapacityMeasurement capacityInformation) {
    this.capacityInformation = capacityInformation;
  }

  public CapacityShortageNotification _links(CapacityShortageNotificationLinks _links) {
    this._links = _links;
    return this;
  }

  /**
   * Get _links
   * @return _links
   **/
  @Schema(description = "")
  
    @Valid
    public CapacityShortageNotificationLinks getLinks() {
    return _links;
  }

  public void setLinks(CapacityShortageNotificationLinks _links) {
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
    CapacityShortageNotification capacityShortageNotification = (CapacityShortageNotification) o;
    return Objects.equals(this.id, capacityShortageNotification.id) &&
        Objects.equals(this.notificationType, capacityShortageNotification.notificationType) &&
        Objects.equals(this.thresholdId, capacityShortageNotification.thresholdId) &&
        Objects.equals(this.timeStamp, capacityShortageNotification.timeStamp) &&
        Objects.equals(this.objectInstanceId, capacityShortageNotification.objectInstanceId) &&
        Objects.equals(this.subObjectInstanceId, capacityShortageNotification.subObjectInstanceId) &&
        Objects.equals(this.direction, capacityShortageNotification.direction) &&
        Objects.equals(this.capacityInformation, capacityShortageNotification.capacityInformation) &&
        Objects.equals(this._links, capacityShortageNotification._links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, notificationType, thresholdId, timeStamp, objectInstanceId, subObjectInstanceId, direction, capacityInformation, _links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CapacityShortageNotification {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    notificationType: ").append(toIndentedString(notificationType)).append("\n");
    sb.append("    thresholdId: ").append(toIndentedString(thresholdId)).append("\n");
    sb.append("    timeStamp: ").append(toIndentedString(timeStamp)).append("\n");
    sb.append("    objectInstanceId: ").append(toIndentedString(objectInstanceId)).append("\n");
    sb.append("    subObjectInstanceId: ").append(toIndentedString(subObjectInstanceId)).append("\n");
    sb.append("    direction: ").append(toIndentedString(direction)).append("\n");
    sb.append("    capacityInformation: ").append(toIndentedString(capacityInformation)).append("\n");
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
