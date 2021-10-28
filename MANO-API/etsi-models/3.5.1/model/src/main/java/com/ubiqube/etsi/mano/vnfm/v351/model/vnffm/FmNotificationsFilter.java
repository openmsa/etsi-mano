package com.ubiqube.etsi.mano.vnfm.v351.model.vnffm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.vnfm.v351.model.vnffm.EventType;
import com.ubiqube.etsi.mano.vnfm.v351.model.vnffm.FaultyResourceType;
import com.ubiqube.etsi.mano.vnfm.v351.model.vnffm.PerceivedSeverityType;
import com.ubiqube.etsi.mano.vnfm.v351.model.vnffm.VnfInstanceSubscriptionFilter;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents a subscription filter related to notifications about VNF faults.  It shall comply with the provisions defined in table 7.5.3.2-1. At a particular nesting level in the filter structure, the following applies: All attributes  shall match in order for the filter to match (logical \&quot;and\&quot; between different filter attributes).  If an attribute is an array, the attribute shall match if at least one of the values in the array  matches (logical \&quot;or\&quot; between the values of one filter attribute). NOTE: The permitted values of the \&quot;notificationTypes\&quot; attribute are spelled exactly as the names        of the notification types to facilitate automated code generation systems. 
 */
@Schema(description = "This type represents a subscription filter related to notifications about VNF faults.  It shall comply with the provisions defined in table 7.5.3.2-1. At a particular nesting level in the filter structure, the following applies: All attributes  shall match in order for the filter to match (logical \"and\" between different filter attributes).  If an attribute is an array, the attribute shall match if at least one of the values in the array  matches (logical \"or\" between the values of one filter attribute). NOTE: The permitted values of the \"notificationTypes\" attribute are spelled exactly as the names        of the notification types to facilitate automated code generation systems. ")
@Validated


public class FmNotificationsFilter   {
  @JsonProperty("vnfInstanceSubscriptionFilter")
  private VnfInstanceSubscriptionFilter vnfInstanceSubscriptionFilter = null;

  /**
   * Gets or Sets notificationTypes
   */
  public enum NotificationTypesEnum {
    ALARMNOTIFICATION("AlarmNotification"),
    
    ALARMCLEAREDNOTIFICATION("AlarmClearedNotification"),
    
    ALARMLISTREBUILTNOTIFICATION("AlarmListRebuiltNotification");

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

  @JsonProperty("faultyResourceTypes")
  @Valid
  private List<FaultyResourceType> faultyResourceTypes = null;

  @JsonProperty("perceivedSeverities")
  @Valid
  private List<PerceivedSeverityType> perceivedSeverities = null;

  @JsonProperty("eventTypes")
  @Valid
  private List<EventType> eventTypes = null;

  @JsonProperty("probableCauses")
  @Valid
  private List<String> probableCauses = null;

  public FmNotificationsFilter vnfInstanceSubscriptionFilter(VnfInstanceSubscriptionFilter vnfInstanceSubscriptionFilter) {
    this.vnfInstanceSubscriptionFilter = vnfInstanceSubscriptionFilter;
    return this;
  }

  /**
   * Get vnfInstanceSubscriptionFilter
   * @return vnfInstanceSubscriptionFilter
   **/
  @Schema(description = "")
  
    @Valid
    public VnfInstanceSubscriptionFilter getVnfInstanceSubscriptionFilter() {
    return vnfInstanceSubscriptionFilter;
  }

  public void setVnfInstanceSubscriptionFilter(VnfInstanceSubscriptionFilter vnfInstanceSubscriptionFilter) {
    this.vnfInstanceSubscriptionFilter = vnfInstanceSubscriptionFilter;
  }

  public FmNotificationsFilter notificationTypes(List<NotificationTypesEnum> notificationTypes) {
    this.notificationTypes = notificationTypes;
    return this;
  }

  public FmNotificationsFilter addNotificationTypesItem(NotificationTypesEnum notificationTypesItem) {
    if (this.notificationTypes == null) {
      this.notificationTypes = new ArrayList<>();
    }
    this.notificationTypes.add(notificationTypesItem);
    return this;
  }

  /**
   * Match particular notification types.  Permitted values: - AlarmNotification - AlarmClearedNotification - AlarmListRebuiltNotification See note. 
   * @return notificationTypes
   **/
  @Schema(description = "Match particular notification types.  Permitted values: - AlarmNotification - AlarmClearedNotification - AlarmListRebuiltNotification See note. ")
  
    public List<NotificationTypesEnum> getNotificationTypes() {
    return notificationTypes;
  }

  public void setNotificationTypes(List<NotificationTypesEnum> notificationTypes) {
    this.notificationTypes = notificationTypes;
  }

  public FmNotificationsFilter faultyResourceTypes(List<FaultyResourceType> faultyResourceTypes) {
    this.faultyResourceTypes = faultyResourceTypes;
    return this;
  }

  public FmNotificationsFilter addFaultyResourceTypesItem(FaultyResourceType faultyResourceTypesItem) {
    if (this.faultyResourceTypes == null) {
      this.faultyResourceTypes = new ArrayList<>();
    }
    this.faultyResourceTypes.add(faultyResourceTypesItem);
    return this;
  }

  /**
   * Match VNF alarms with a faulty resource type listed in this attribute. 
   * @return faultyResourceTypes
   **/
  @Schema(description = "Match VNF alarms with a faulty resource type listed in this attribute. ")
      @Valid
    public List<FaultyResourceType> getFaultyResourceTypes() {
    return faultyResourceTypes;
  }

  public void setFaultyResourceTypes(List<FaultyResourceType> faultyResourceTypes) {
    this.faultyResourceTypes = faultyResourceTypes;
  }

  public FmNotificationsFilter perceivedSeverities(List<PerceivedSeverityType> perceivedSeverities) {
    this.perceivedSeverities = perceivedSeverities;
    return this;
  }

  public FmNotificationsFilter addPerceivedSeveritiesItem(PerceivedSeverityType perceivedSeveritiesItem) {
    if (this.perceivedSeverities == null) {
      this.perceivedSeverities = new ArrayList<>();
    }
    this.perceivedSeverities.add(perceivedSeveritiesItem);
    return this;
  }

  /**
   * Match VNF alarms with a perceived severity listed in this attribute. 
   * @return perceivedSeverities
   **/
  @Schema(description = "Match VNF alarms with a perceived severity listed in this attribute. ")
      @Valid
    public List<PerceivedSeverityType> getPerceivedSeverities() {
    return perceivedSeverities;
  }

  public void setPerceivedSeverities(List<PerceivedSeverityType> perceivedSeverities) {
    this.perceivedSeverities = perceivedSeverities;
  }

  public FmNotificationsFilter eventTypes(List<EventType> eventTypes) {
    this.eventTypes = eventTypes;
    return this;
  }

  public FmNotificationsFilter addEventTypesItem(EventType eventTypesItem) {
    if (this.eventTypes == null) {
      this.eventTypes = new ArrayList<>();
    }
    this.eventTypes.add(eventTypesItem);
    return this;
  }

  /**
   * Match VNF alarms with an event type listed in this attribute. 
   * @return eventTypes
   **/
  @Schema(description = "Match VNF alarms with an event type listed in this attribute. ")
      @Valid
    public List<EventType> getEventTypes() {
    return eventTypes;
  }

  public void setEventTypes(List<EventType> eventTypes) {
    this.eventTypes = eventTypes;
  }

  public FmNotificationsFilter probableCauses(List<String> probableCauses) {
    this.probableCauses = probableCauses;
    return this;
  }

  public FmNotificationsFilter addProbableCausesItem(String probableCausesItem) {
    if (this.probableCauses == null) {
      this.probableCauses = new ArrayList<>();
    }
    this.probableCauses.add(probableCausesItem);
    return this;
  }

  /**
   * Match VNF alarms with a probable cause listed in this attribute. 
   * @return probableCauses
   **/
  @Schema(description = "Match VNF alarms with a probable cause listed in this attribute. ")
  
    public List<String> getProbableCauses() {
    return probableCauses;
  }

  public void setProbableCauses(List<String> probableCauses) {
    this.probableCauses = probableCauses;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FmNotificationsFilter fmNotificationsFilter = (FmNotificationsFilter) o;
    return Objects.equals(this.vnfInstanceSubscriptionFilter, fmNotificationsFilter.vnfInstanceSubscriptionFilter) &&
        Objects.equals(this.notificationTypes, fmNotificationsFilter.notificationTypes) &&
        Objects.equals(this.faultyResourceTypes, fmNotificationsFilter.faultyResourceTypes) &&
        Objects.equals(this.perceivedSeverities, fmNotificationsFilter.perceivedSeverities) &&
        Objects.equals(this.eventTypes, fmNotificationsFilter.eventTypes) &&
        Objects.equals(this.probableCauses, fmNotificationsFilter.probableCauses);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vnfInstanceSubscriptionFilter, notificationTypes, faultyResourceTypes, perceivedSeverities, eventTypes, probableCauses);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FmNotificationsFilter {\n");
    
    sb.append("    vnfInstanceSubscriptionFilter: ").append(toIndentedString(vnfInstanceSubscriptionFilter)).append("\n");
    sb.append("    notificationTypes: ").append(toIndentedString(notificationTypes)).append("\n");
    sb.append("    faultyResourceTypes: ").append(toIndentedString(faultyResourceTypes)).append("\n");
    sb.append("    perceivedSeverities: ").append(toIndentedString(perceivedSeverities)).append("\n");
    sb.append("    eventTypes: ").append(toIndentedString(eventTypes)).append("\n");
    sb.append("    probableCauses: ").append(toIndentedString(probableCauses)).append("\n");
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
