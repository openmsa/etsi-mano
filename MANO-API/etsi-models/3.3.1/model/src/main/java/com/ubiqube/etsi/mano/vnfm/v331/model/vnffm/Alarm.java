package com.ubiqube.etsi.mano.vnfm.v331.model.vnffm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnffm.AlarmLinks;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnffm.EventType;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnffm.FaultyResourceInfo;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnffm.PerceivedSeverityType;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * The alarm data type encapsulates information about an alarm. 
 */
@Schema(description = "The alarm data type encapsulates information about an alarm. ")
@Validated


public class Alarm   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("managedObjectId")
  private String managedObjectId = null;

  @JsonProperty("rootCauseFaultyResource")
  private FaultyResourceInfo rootCauseFaultyResource = null;

  @JsonProperty("alarmRaisedTime")
  private OffsetDateTime alarmRaisedTime = null;

  @JsonProperty("alarmChangedTime")
  private OffsetDateTime alarmChangedTime = null;

  @JsonProperty("alarmClearedTime")
  private OffsetDateTime alarmClearedTime = null;

  @JsonProperty("alarmAcknowledgedTime")
  private OffsetDateTime alarmAcknowledgedTime = null;

  /**
   * Acknowledgement state of the alarm. Permitted values: * UNACKNOWLEDGED * ACKNOWLEDGED. 
   */
  public enum AckStateEnum {
    UNACKNOWLEDGED("UNACKNOWLEDGED"),
    
    ACKNOWLEDGED("ACKNOWLEDGED");

    private String value;

    AckStateEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static AckStateEnum fromValue(String text) {
      for (AckStateEnum b : AckStateEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("ackState")
  private AckStateEnum ackState = null;

  @JsonProperty("perceivedSeverity")
  private PerceivedSeverityType perceivedSeverity = null;

  @JsonProperty("eventTime")
  private OffsetDateTime eventTime = null;

  @JsonProperty("eventType")
  private EventType eventType = null;

  @JsonProperty("faultType")
  private String faultType = null;

  @JsonProperty("probableCause")
  private String probableCause = null;

  @JsonProperty("isRootCause")
  private Boolean isRootCause = null;

  @JsonProperty("correlatedAlarmIds")
  @Valid
  private List<String> correlatedAlarmIds = null;

  @JsonProperty("faultDetails")
  @Valid
  private List<String> faultDetails = null;

  @JsonProperty("_links")
  private AlarmLinks _links = null;

  public Alarm id(String id) {
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

  public Alarm managedObjectId(String managedObjectId) {
    this.managedObjectId = managedObjectId;
    return this;
  }

  /**
   * Get managedObjectId
   * @return managedObjectId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getManagedObjectId() {
    return managedObjectId;
  }

  public void setManagedObjectId(String managedObjectId) {
    this.managedObjectId = managedObjectId;
  }

  public Alarm rootCauseFaultyResource(FaultyResourceInfo rootCauseFaultyResource) {
    this.rootCauseFaultyResource = rootCauseFaultyResource;
    return this;
  }

  /**
   * Get rootCauseFaultyResource
   * @return rootCauseFaultyResource
   **/
  @Schema(description = "")
  
    @Valid
    public FaultyResourceInfo getRootCauseFaultyResource() {
    return rootCauseFaultyResource;
  }

  public void setRootCauseFaultyResource(FaultyResourceInfo rootCauseFaultyResource) {
    this.rootCauseFaultyResource = rootCauseFaultyResource;
  }

  public Alarm alarmRaisedTime(OffsetDateTime alarmRaisedTime) {
    this.alarmRaisedTime = alarmRaisedTime;
    return this;
  }

  /**
   * Get alarmRaisedTime
   * @return alarmRaisedTime
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public OffsetDateTime getAlarmRaisedTime() {
    return alarmRaisedTime;
  }

  public void setAlarmRaisedTime(OffsetDateTime alarmRaisedTime) {
    this.alarmRaisedTime = alarmRaisedTime;
  }

  public Alarm alarmChangedTime(OffsetDateTime alarmChangedTime) {
    this.alarmChangedTime = alarmChangedTime;
    return this;
  }

  /**
   * Get alarmChangedTime
   * @return alarmChangedTime
   **/
  @Schema(description = "")
  
    @Valid
    public OffsetDateTime getAlarmChangedTime() {
    return alarmChangedTime;
  }

  public void setAlarmChangedTime(OffsetDateTime alarmChangedTime) {
    this.alarmChangedTime = alarmChangedTime;
  }

  public Alarm alarmClearedTime(OffsetDateTime alarmClearedTime) {
    this.alarmClearedTime = alarmClearedTime;
    return this;
  }

  /**
   * Get alarmClearedTime
   * @return alarmClearedTime
   **/
  @Schema(description = "")
  
    @Valid
    public OffsetDateTime getAlarmClearedTime() {
    return alarmClearedTime;
  }

  public void setAlarmClearedTime(OffsetDateTime alarmClearedTime) {
    this.alarmClearedTime = alarmClearedTime;
  }

  public Alarm alarmAcknowledgedTime(OffsetDateTime alarmAcknowledgedTime) {
    this.alarmAcknowledgedTime = alarmAcknowledgedTime;
    return this;
  }

  /**
   * Get alarmAcknowledgedTime
   * @return alarmAcknowledgedTime
   **/
  @Schema(description = "")
  
    @Valid
    public OffsetDateTime getAlarmAcknowledgedTime() {
    return alarmAcknowledgedTime;
  }

  public void setAlarmAcknowledgedTime(OffsetDateTime alarmAcknowledgedTime) {
    this.alarmAcknowledgedTime = alarmAcknowledgedTime;
  }

  public Alarm ackState(AckStateEnum ackState) {
    this.ackState = ackState;
    return this;
  }

  /**
   * Acknowledgement state of the alarm. Permitted values: * UNACKNOWLEDGED * ACKNOWLEDGED. 
   * @return ackState
   **/
  @Schema(required = true, description = "Acknowledgement state of the alarm. Permitted values: * UNACKNOWLEDGED * ACKNOWLEDGED. ")
      @NotNull

    public AckStateEnum getAckState() {
    return ackState;
  }

  public void setAckState(AckStateEnum ackState) {
    this.ackState = ackState;
  }

  public Alarm perceivedSeverity(PerceivedSeverityType perceivedSeverity) {
    this.perceivedSeverity = perceivedSeverity;
    return this;
  }

  /**
   * Get perceivedSeverity
   * @return perceivedSeverity
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public PerceivedSeverityType getPerceivedSeverity() {
    return perceivedSeverity;
  }

  public void setPerceivedSeverity(PerceivedSeverityType perceivedSeverity) {
    this.perceivedSeverity = perceivedSeverity;
  }

  public Alarm eventTime(OffsetDateTime eventTime) {
    this.eventTime = eventTime;
    return this;
  }

  /**
   * Get eventTime
   * @return eventTime
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public OffsetDateTime getEventTime() {
    return eventTime;
  }

  public void setEventTime(OffsetDateTime eventTime) {
    this.eventTime = eventTime;
  }

  public Alarm eventType(EventType eventType) {
    this.eventType = eventType;
    return this;
  }

  /**
   * Get eventType
   * @return eventType
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public EventType getEventType() {
    return eventType;
  }

  public void setEventType(EventType eventType) {
    this.eventType = eventType;
  }

  public Alarm faultType(String faultType) {
    this.faultType = faultType;
    return this;
  }

  /**
   * Additional information to clarify the type of the fault. 
   * @return faultType
   **/
  @Schema(description = "Additional information to clarify the type of the fault. ")
  
    public String getFaultType() {
    return faultType;
  }

  public void setFaultType(String faultType) {
    this.faultType = faultType;
  }

  public Alarm probableCause(String probableCause) {
    this.probableCause = probableCause;
    return this;
  }

  /**
   * Information about the probable cause of the fault. 
   * @return probableCause
   **/
  @Schema(required = true, description = "Information about the probable cause of the fault. ")
      @NotNull

    public String getProbableCause() {
    return probableCause;
  }

  public void setProbableCause(String probableCause) {
    this.probableCause = probableCause;
  }

  public Alarm isRootCause(Boolean isRootCause) {
    this.isRootCause = isRootCause;
    return this;
  }

  /**
   * Attribute indicating if this fault is the root for other correlated alarms. If true, then the alarms listed in the attribute \"correlatedAlarmIds\" are caused by this fault. 
   * @return isRootCause
   **/
  @Schema(required = true, description = "Attribute indicating if this fault is the root for other correlated alarms. If true, then the alarms listed in the attribute \"correlatedAlarmIds\" are caused by this fault. ")
      @NotNull

    public Boolean isIsRootCause() {
    return isRootCause;
  }

  public void setIsRootCause(Boolean isRootCause) {
    this.isRootCause = isRootCause;
  }

  public Alarm correlatedAlarmIds(List<String> correlatedAlarmIds) {
    this.correlatedAlarmIds = correlatedAlarmIds;
    return this;
  }

  public Alarm addCorrelatedAlarmIdsItem(String correlatedAlarmIdsItem) {
    if (this.correlatedAlarmIds == null) {
      this.correlatedAlarmIds = new ArrayList<>();
    }
    this.correlatedAlarmIds.add(correlatedAlarmIdsItem);
    return this;
  }

  /**
   * List of identifiers of other alarms correlated to this fault. 
   * @return correlatedAlarmIds
   **/
  @Schema(description = "List of identifiers of other alarms correlated to this fault. ")
  
    public List<String> getCorrelatedAlarmIds() {
    return correlatedAlarmIds;
  }

  public void setCorrelatedAlarmIds(List<String> correlatedAlarmIds) {
    this.correlatedAlarmIds = correlatedAlarmIds;
  }

  public Alarm faultDetails(List<String> faultDetails) {
    this.faultDetails = faultDetails;
    return this;
  }

  public Alarm addFaultDetailsItem(String faultDetailsItem) {
    if (this.faultDetails == null) {
      this.faultDetails = new ArrayList<>();
    }
    this.faultDetails.add(faultDetailsItem);
    return this;
  }

  /**
   * Provides additional information about the fault. 
   * @return faultDetails
   **/
  @Schema(description = "Provides additional information about the fault. ")
  
    public List<String> getFaultDetails() {
    return faultDetails;
  }

  public void setFaultDetails(List<String> faultDetails) {
    this.faultDetails = faultDetails;
  }

  public Alarm _links(AlarmLinks _links) {
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
    public AlarmLinks getLinks() {
    return _links;
  }

  public void setLinks(AlarmLinks _links) {
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
    Alarm alarm = (Alarm) o;
    return Objects.equals(this.id, alarm.id) &&
        Objects.equals(this.managedObjectId, alarm.managedObjectId) &&
        Objects.equals(this.rootCauseFaultyResource, alarm.rootCauseFaultyResource) &&
        Objects.equals(this.alarmRaisedTime, alarm.alarmRaisedTime) &&
        Objects.equals(this.alarmChangedTime, alarm.alarmChangedTime) &&
        Objects.equals(this.alarmClearedTime, alarm.alarmClearedTime) &&
        Objects.equals(this.alarmAcknowledgedTime, alarm.alarmAcknowledgedTime) &&
        Objects.equals(this.ackState, alarm.ackState) &&
        Objects.equals(this.perceivedSeverity, alarm.perceivedSeverity) &&
        Objects.equals(this.eventTime, alarm.eventTime) &&
        Objects.equals(this.eventType, alarm.eventType) &&
        Objects.equals(this.faultType, alarm.faultType) &&
        Objects.equals(this.probableCause, alarm.probableCause) &&
        Objects.equals(this.isRootCause, alarm.isRootCause) &&
        Objects.equals(this.correlatedAlarmIds, alarm.correlatedAlarmIds) &&
        Objects.equals(this.faultDetails, alarm.faultDetails) &&
        Objects.equals(this._links, alarm._links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, managedObjectId, rootCauseFaultyResource, alarmRaisedTime, alarmChangedTime, alarmClearedTime, alarmAcknowledgedTime, ackState, perceivedSeverity, eventTime, eventType, faultType, probableCause, isRootCause, correlatedAlarmIds, faultDetails, _links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Alarm {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    managedObjectId: ").append(toIndentedString(managedObjectId)).append("\n");
    sb.append("    rootCauseFaultyResource: ").append(toIndentedString(rootCauseFaultyResource)).append("\n");
    sb.append("    alarmRaisedTime: ").append(toIndentedString(alarmRaisedTime)).append("\n");
    sb.append("    alarmChangedTime: ").append(toIndentedString(alarmChangedTime)).append("\n");
    sb.append("    alarmClearedTime: ").append(toIndentedString(alarmClearedTime)).append("\n");
    sb.append("    alarmAcknowledgedTime: ").append(toIndentedString(alarmAcknowledgedTime)).append("\n");
    sb.append("    ackState: ").append(toIndentedString(ackState)).append("\n");
    sb.append("    perceivedSeverity: ").append(toIndentedString(perceivedSeverity)).append("\n");
    sb.append("    eventTime: ").append(toIndentedString(eventTime)).append("\n");
    sb.append("    eventType: ").append(toIndentedString(eventType)).append("\n");
    sb.append("    faultType: ").append(toIndentedString(faultType)).append("\n");
    sb.append("    probableCause: ").append(toIndentedString(probableCause)).append("\n");
    sb.append("    isRootCause: ").append(toIndentedString(isRootCause)).append("\n");
    sb.append("    correlatedAlarmIds: ").append(toIndentedString(correlatedAlarmIds)).append("\n");
    sb.append("    faultDetails: ").append(toIndentedString(faultDetails)).append("\n");
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
