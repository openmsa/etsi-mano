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
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents a filter that can be used to subscribe for notifications related to NFVI capacity information events. It shall comply with the provisions defined in Table 10.5.3.2-1. 
 */
@Schema(description = "This type represents a filter that can be used to subscribe for notifications related to NFVI capacity information events. It shall comply with the provisions defined in Table 10.5.3.2-1. ")
@Validated


public class NfviCapacityInfoNotificationsFilter   {
  /**
   * Gets or Sets notificationTypes
   */
  public enum NotificationTypesEnum {
    CAPACITYSHORTAGENOTIFICATION("CapacityShortageNotification");

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

  public NfviCapacityInfoNotificationsFilter notificationTypes(List<NotificationTypesEnum> notificationTypes) {
    this.notificationTypes = notificationTypes;
    return this;
  }

  public NfviCapacityInfoNotificationsFilter addNotificationTypesItem(NotificationTypesEnum notificationTypesItem) {
    if (this.notificationTypes == null) {
      this.notificationTypes = new ArrayList<>();
    }
    this.notificationTypes.add(notificationTypesItem);
    return this;
  }

  /**
   * Match particular notification types. Permitted values: - CapacityShortageNotification 
   * @return notificationTypes
   **/
  @Schema(description = "Match particular notification types. Permitted values: - CapacityShortageNotification ")
  
    public List<NotificationTypesEnum> getNotificationTypes() {
    return notificationTypes;
  }

  public void setNotificationTypes(List<NotificationTypesEnum> notificationTypes) {
    this.notificationTypes = notificationTypes;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NfviCapacityInfoNotificationsFilter nfviCapacityInfoNotificationsFilter = (NfviCapacityInfoNotificationsFilter) o;
    return Objects.equals(this.notificationTypes, nfviCapacityInfoNotificationsFilter.notificationTypes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(notificationTypes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NfviCapacityInfoNotificationsFilter {\n");
    
    sb.append("    notificationTypes: ").append(toIndentedString(notificationTypes)).append("\n");
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
