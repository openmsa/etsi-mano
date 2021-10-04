package com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanologm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanologm.ManoEntitySubscriptionFilter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents a filter that can be used to subscribe for notifications related to log management events. It shall comply with the provisions defined in table 8.6.3.7-1. At a particular nesting level in the filter structure, the following applies: All attributes shall match in order for the filter to match (logical \&quot;and\&quot; between different filter attributes). If an attribute is an array, the attribute shall match if at least one of the values in the array matches (logical \&quot;or\&quot; between the values of one filter attribute).
 */
@Schema(description = "This type represents a filter that can be used to subscribe for notifications related to log management events. It shall comply with the provisions defined in table 8.6.3.7-1. At a particular nesting level in the filter structure, the following applies: All attributes shall match in order for the filter to match (logical \"and\" between different filter attributes). If an attribute is an array, the attribute shall match if at least one of the values in the array matches (logical \"or\" between the values of one filter attribute).")
@Validated


public class LogmNotificationsFilter   {
  @JsonProperty("objectInstanceFilter")
  private ManoEntitySubscriptionFilter objectInstanceFilter = null;

  /**
   * Match particular notification types. Permitted values: - LogReportAvailableNotification
   */
  public enum NotificationTypesEnum {
    LOGREPORTAVAILABLENOTIFICATION("LogReportAvailableNotification");

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
  private NotificationTypesEnum notificationTypes = null;

  public LogmNotificationsFilter objectInstanceFilter(ManoEntitySubscriptionFilter objectInstanceFilter) {
    this.objectInstanceFilter = objectInstanceFilter;
    return this;
  }

  /**
   * Get objectInstanceFilter
   * @return objectInstanceFilter
   **/
  @Schema(description = "")
  
    @Valid
    public ManoEntitySubscriptionFilter getObjectInstanceFilter() {
    return objectInstanceFilter;
  }

  public void setObjectInstanceFilter(ManoEntitySubscriptionFilter objectInstanceFilter) {
    this.objectInstanceFilter = objectInstanceFilter;
  }

  public LogmNotificationsFilter notificationTypes(NotificationTypesEnum notificationTypes) {
    this.notificationTypes = notificationTypes;
    return this;
  }

  /**
   * Match particular notification types. Permitted values: - LogReportAvailableNotification
   * @return notificationTypes
   **/
  @Schema(description = "Match particular notification types. Permitted values: - LogReportAvailableNotification")
  
    public NotificationTypesEnum getNotificationTypes() {
    return notificationTypes;
  }

  public void setNotificationTypes(NotificationTypesEnum notificationTypes) {
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
    LogmNotificationsFilter logmNotificationsFilter = (LogmNotificationsFilter) o;
    return Objects.equals(this.objectInstanceFilter, logmNotificationsFilter.objectInstanceFilter) &&
        Objects.equals(this.notificationTypes, logmNotificationsFilter.notificationTypes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(objectInstanceFilter, notificationTypes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LogmNotificationsFilter {\n");
    
    sb.append("    objectInstanceFilter: ").append(toIndentedString(objectInstanceFilter)).append("\n");
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
