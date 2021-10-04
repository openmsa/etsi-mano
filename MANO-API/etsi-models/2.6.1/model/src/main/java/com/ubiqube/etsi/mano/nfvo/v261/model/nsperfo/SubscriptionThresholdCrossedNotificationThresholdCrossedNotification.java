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

package com.ubiqube.etsi.mano.nfvo.v261.model.nsperfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

/**
  * This type represents a notification that is sent when a threshold has been crossed. 
 **/
@Schema(description="This type represents a notification that is sent when a threshold has been crossed. ")
public class SubscriptionThresholdCrossedNotificationThresholdCrossedNotification  {
  
  @Schema(required = true, description = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String id = null;

  @Schema(required = true, description = "Discriminator for the different notification types. Shall be set to \"ThresholdCrossedNotification \" for this notification type. ")
 /**
   * Discriminator for the different notification types. Shall be set to \"ThresholdCrossedNotification \" for this notification type. 
  **/
  private String notificationType = null;

  @Schema(required = true, description = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String subscriptionId = null;

  @Schema(required = true, description = "Date-time stamp.  Representation: String formatted according to IETF RFC 3339. ")
 /**
   * Date-time stamp.  Representation: String formatted according to IETF RFC 3339. 
  **/
  private Date timeStamp = null;

  @Schema(required = true, description = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String thresholdId = null;


@XmlType(name="CrossingDirectionEnum")
@XmlEnum(String.class)
public enum CrossingDirectionEnum {

@XmlEnumValue("UP") UP(String.valueOf("UP")), @XmlEnumValue("DOWN") DOWN(String.valueOf("DOWN"));


    private String value;

    CrossingDirectionEnum (String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static CrossingDirectionEnum fromValue(String v) {
        for (CrossingDirectionEnum b : CrossingDirectionEnum.values()) {
            if (String.valueOf(b.value).equals(v)) {
                return b;
            }
        }
        return null;
    }
}

  @Schema(required = true, description = "The enumeration CrossingDirectionType shall comply with the provisions.   Acceptable Values are: UP - The threshold was crossed in upward direction. DOWN - The threshold was crossed in downward direction. ")
 /**
   * The enumeration CrossingDirectionType shall comply with the provisions.   Acceptable Values are: UP - The threshold was crossed in upward direction. DOWN - The threshold was crossed in downward direction. 
  **/
  private CrossingDirectionEnum crossingDirection = null;

  @Schema(required = true, description = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String objectInstanceId = null;

  @Schema(required = true, description = "Performance metric associated with the threshold. ")
 /**
   * Performance metric associated with the threshold. 
  **/
  private String performanceMetric = null;

  @Schema(required = true, description = "Value of the metric that resulted in threshold crossing. See note. ")
 /**
   * Value of the metric that resulted in threshold crossing. See note. 
  **/
  private Object performanceValue = null;

  @Schema(required = true, description = "")
  @Valid
  private SubscriptionThresholdCrossedNotificationThresholdCrossedNotificationLinks links = null;
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

  public SubscriptionThresholdCrossedNotificationThresholdCrossedNotification id(String id) {
    this.id = id;
    return this;
  }

 /**
   * Discriminator for the different notification types. Shall be set to \&quot;ThresholdCrossedNotification \&quot; for this notification type. 
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

  public SubscriptionThresholdCrossedNotificationThresholdCrossedNotification notificationType(String notificationType) {
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

  public SubscriptionThresholdCrossedNotificationThresholdCrossedNotification subscriptionId(String subscriptionId) {
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

  public SubscriptionThresholdCrossedNotificationThresholdCrossedNotification timeStamp(Date timeStamp) {
    this.timeStamp = timeStamp;
    return this;
  }

 /**
   * An identifier with the intention of being globally unique. 
   * @return thresholdId
  **/
  @JsonProperty("thresholdId")
  @NotNull
  public String getThresholdId() {
    return thresholdId;
  }

  public void setThresholdId(String thresholdId) {
    this.thresholdId = thresholdId;
  }

  public SubscriptionThresholdCrossedNotificationThresholdCrossedNotification thresholdId(String thresholdId) {
    this.thresholdId = thresholdId;
    return this;
  }

 /**
   * The enumeration CrossingDirectionType shall comply with the provisions.   Acceptable Values are: UP - The threshold was crossed in upward direction. DOWN - The threshold was crossed in downward direction. 
   * @return crossingDirection
  **/
  @JsonProperty("crossingDirection")
  @NotNull
  public String getCrossingDirection() {
    if (crossingDirection == null) {
      return null;
    }
    return crossingDirection.value();
  }

  public void setCrossingDirection(CrossingDirectionEnum crossingDirection) {
    this.crossingDirection = crossingDirection;
  }

  public SubscriptionThresholdCrossedNotificationThresholdCrossedNotification crossingDirection(CrossingDirectionEnum crossingDirection) {
    this.crossingDirection = crossingDirection;
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

  public SubscriptionThresholdCrossedNotificationThresholdCrossedNotification objectInstanceId(String objectInstanceId) {
    this.objectInstanceId = objectInstanceId;
    return this;
  }

 /**
   * Performance metric associated with the threshold. 
   * @return performanceMetric
  **/
  @JsonProperty("performanceMetric")
  @NotNull
  public String getPerformanceMetric() {
    return performanceMetric;
  }

  public void setPerformanceMetric(String performanceMetric) {
    this.performanceMetric = performanceMetric;
  }

  public SubscriptionThresholdCrossedNotificationThresholdCrossedNotification performanceMetric(String performanceMetric) {
    this.performanceMetric = performanceMetric;
    return this;
  }

 /**
   * Value of the metric that resulted in threshold crossing. See note. 
   * @return performanceValue
  **/
  @JsonProperty("performanceValue")
  @NotNull
  public Object getPerformanceValue() {
    return performanceValue;
  }

  public void setPerformanceValue(Object performanceValue) {
    this.performanceValue = performanceValue;
  }

  public SubscriptionThresholdCrossedNotificationThresholdCrossedNotification performanceValue(Object performanceValue) {
    this.performanceValue = performanceValue;
    return this;
  }

 /**
   * Get links
   * @return links
  **/
  @JsonProperty("_links")
  @NotNull
  public SubscriptionThresholdCrossedNotificationThresholdCrossedNotificationLinks getLinks() {
    return links;
  }

  public void setLinks(SubscriptionThresholdCrossedNotificationThresholdCrossedNotificationLinks links) {
    this.links = links;
  }

  public SubscriptionThresholdCrossedNotificationThresholdCrossedNotification links(SubscriptionThresholdCrossedNotificationThresholdCrossedNotificationLinks links) {
    this.links = links;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class URIIsProvidedByTheClientWhenCreatingTheSubscriptionThresholdCrossedNotificationThresholdCrossedNotification {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    notificationType: ").append(toIndentedString(notificationType)).append("\n");
    sb.append("    subscriptionId: ").append(toIndentedString(subscriptionId)).append("\n");
    sb.append("    timeStamp: ").append(toIndentedString(timeStamp)).append("\n");
    sb.append("    thresholdId: ").append(toIndentedString(thresholdId)).append("\n");
    sb.append("    crossingDirection: ").append(toIndentedString(crossingDirection)).append("\n");
    sb.append("    objectInstanceId: ").append(toIndentedString(objectInstanceId)).append("\n");
    sb.append("    performanceMetric: ").append(toIndentedString(performanceMetric)).append("\n");
    sb.append("    performanceValue: ").append(toIndentedString(performanceValue)).append("\n");
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

