package com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanopm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanopm.ManoEntitySubscriptionFilter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents a filter that can be used to subscribe for notifications  related to performance management events.  At a particular nesting level in the filter structure, the following applies:  All attributes shall match in order for the filter to match (logical \&quot;and\&quot; between  different filter attributes). If an attribute is an array, the attribute shall  match if at least one of the values in the array matches (logical \&quot;or\&quot; between the  values of one filter attribute). 
 */
@Schema(description = "This type represents a filter that can be used to subscribe for notifications  related to performance management events.  At a particular nesting level in the filter structure, the following applies:  All attributes shall match in order for the filter to match (logical \"and\" between  different filter attributes). If an attribute is an array, the attribute shall  match if at least one of the values in the array matches (logical \"or\" between the  values of one filter attribute). ")
@Validated


public class PmNotificationsFilter   {
  @JsonProperty("pmSubscriptionFilter")
  private ManoEntitySubscriptionFilter pmSubscriptionFilter = null;

  /**
   * Match particular notification types.  Permitted values:   - ThresholdCrossedNotification   - PerformanceInformationAvailableNotification  The permitted values of the \"notificationTypes\" attribute are spelled exactly  as the names of the notification types to facilitate automated code generation  systems. 
   */
  public enum NotificationTypesEnum {
    THRESHOLDCROSSEDNOTIFICATION("ThresholdCrossedNotification"),
    
    PERFORMANCEINFORMATIONAVAILABLENOTIFICATION("PerformanceInformationAvailableNotification");

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

  public PmNotificationsFilter pmSubscriptionFilter(ManoEntitySubscriptionFilter pmSubscriptionFilter) {
    this.pmSubscriptionFilter = pmSubscriptionFilter;
    return this;
  }

  /**
   * Get pmSubscriptionFilter
   * @return pmSubscriptionFilter
   **/
  @Schema(description = "")
  
    @Valid
    public ManoEntitySubscriptionFilter getPmSubscriptionFilter() {
    return pmSubscriptionFilter;
  }

  public void setPmSubscriptionFilter(ManoEntitySubscriptionFilter pmSubscriptionFilter) {
    this.pmSubscriptionFilter = pmSubscriptionFilter;
  }

  public PmNotificationsFilter notificationTypes(NotificationTypesEnum notificationTypes) {
    this.notificationTypes = notificationTypes;
    return this;
  }

  /**
   * Match particular notification types.  Permitted values:   - ThresholdCrossedNotification   - PerformanceInformationAvailableNotification  The permitted values of the \"notificationTypes\" attribute are spelled exactly  as the names of the notification types to facilitate automated code generation  systems. 
   * @return notificationTypes
   **/
  @Schema(description = "Match particular notification types.  Permitted values:   - ThresholdCrossedNotification   - PerformanceInformationAvailableNotification  The permitted values of the \"notificationTypes\" attribute are spelled exactly  as the names of the notification types to facilitate automated code generation  systems. ")
  
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
    PmNotificationsFilter pmNotificationsFilter = (PmNotificationsFilter) o;
    return Objects.equals(this.pmSubscriptionFilter, pmNotificationsFilter.pmSubscriptionFilter) &&
        Objects.equals(this.notificationTypes, pmNotificationsFilter.notificationTypes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(pmSubscriptionFilter, notificationTypes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PmNotificationsFilter {\n");
    
    sb.append("    pmSubscriptionFilter: ").append(toIndentedString(pmSubscriptionFilter)).append("\n");
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
