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
package com.ubiqube.etsi.mano.or.v331.model.nsium;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.or.v331.model.nsium.NsInstanceUsageStatusType;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents a subscription filter related to notifications about NS  instance usage. It shall comply with the provisions defined in table 8.6.3.1-1. 
 */
@Schema(description = "This type represents a subscription filter related to notifications about NS  instance usage. It shall comply with the provisions defined in table 8.6.3.1-1. ")
@Validated


public class NsInstanceUsageNotificationsFilter   {
  /**
   * Gets or Sets notificationTypes
   */
  public enum NotificationTypesEnum {
    NSINSTANCEUSAGENOTIFICATION("NsInstanceUsageNotification");

    private String value;

    NotificationTypesEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static NotificationTypesEnum fromValue(String text) {
      for (NotificationTypesEnum b : NotificationTypesEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("notificationTypes")
  @Valid
  private List<NotificationTypesEnum> notificationTypes = null;

  @JsonProperty("nsInstanceId")
  @Valid
  private List<String> nsInstanceId = null;

  @JsonProperty("status")
  private NsInstanceUsageStatusType status = null;

  public NsInstanceUsageNotificationsFilter notificationTypes(List<NotificationTypesEnum> notificationTypes) {
    this.notificationTypes = notificationTypes;
    return this;
  }

  public NsInstanceUsageNotificationsFilter addNotificationTypesItem(NotificationTypesEnum notificationTypesItem) {
    if (this.notificationTypes == null) {
      this.notificationTypes = new ArrayList<>();
    }
    this.notificationTypes.add(notificationTypesItem);
    return this;
  }

  /**
   * Match particular notification types.  Permitted values: - NsInstanceUsageNotification NOTE: The permitted values of the \"notificationTypes\" attribute are spelled  exactly as the names of the notification types to facilitate automated code  generation systems.
   * @return notificationTypes
   **/
  @Schema(description = "Match particular notification types.  Permitted values: - NsInstanceUsageNotification NOTE: The permitted values of the \"notificationTypes\" attribute are spelled  exactly as the names of the notification types to facilitate automated code  generation systems.")
  
    public List<NotificationTypesEnum> getNotificationTypes() {
    return notificationTypes;
  }

  public void setNotificationTypes(List<NotificationTypesEnum> notificationTypes) {
    this.notificationTypes = notificationTypes;
  }

  public NsInstanceUsageNotificationsFilter nsInstanceId(List<String> nsInstanceId) {
    this.nsInstanceId = nsInstanceId;
    return this;
  }

  public NsInstanceUsageNotificationsFilter addNsInstanceIdItem(String nsInstanceIdItem) {
    if (this.nsInstanceId == null) {
      this.nsInstanceId = new ArrayList<>();
    }
    this.nsInstanceId.add(nsInstanceIdItem);
    return this;
  }

  /**
   * If present, match NS instances with an instance identifier listed in this attribute.
   * @return nsInstanceId
   **/
  @Schema(description = "If present, match NS instances with an instance identifier listed in this attribute.")
  
    public List<String> getNsInstanceId() {
    return nsInstanceId;
  }

  public void setNsInstanceId(List<String> nsInstanceId) {
    this.nsInstanceId = nsInstanceId;
  }

  public NsInstanceUsageNotificationsFilter status(NsInstanceUsageStatusType status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
   **/
  @Schema(description = "")
  
    @Valid
    public NsInstanceUsageStatusType getStatus() {
    return status;
  }

  public void setStatus(NsInstanceUsageStatusType status) {
    this.status = status;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NsInstanceUsageNotificationsFilter nsInstanceUsageNotificationsFilter = (NsInstanceUsageNotificationsFilter) o;
    return Objects.equals(this.notificationTypes, nsInstanceUsageNotificationsFilter.notificationTypes) &&
        Objects.equals(this.nsInstanceId, nsInstanceUsageNotificationsFilter.nsInstanceId) &&
        Objects.equals(this.status, nsInstanceUsageNotificationsFilter.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(notificationTypes, nsInstanceId, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstanceUsageNotificationsFilter {\n");
    
    sb.append("    notificationTypes: ").append(toIndentedString(notificationTypes)).append("\n");
    sb.append("    nsInstanceId: ").append(toIndentedString(nsInstanceId)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
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
