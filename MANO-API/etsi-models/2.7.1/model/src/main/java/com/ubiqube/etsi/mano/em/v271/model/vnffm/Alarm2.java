package com.ubiqube.etsi.mano.em.v271.model.vnffm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.em.v271.model.vnffm.AlarmLinks;
import com.ubiqube.etsi.mano.em.v271.model.vnffm.EventType;
import com.ubiqube.etsi.mano.em.v271.model.vnffm.FaultyResourceInfo;
import com.ubiqube.etsi.mano.em.v271.model.vnffm.PerceivedSeverityType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * The alarm data type encapsulates information about an alarm. 
 */
@ApiModel(description = "The alarm data type encapsulates information about an alarm. ")
@Validated

public class Alarm2   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("managedObjectId")
  private String managedObjectId = null;

  @JsonProperty("vnfcInstanceIds")
  @Valid
  private List<String> vnfcInstanceIds = null;

  @JsonProperty("rootCauseFaultyResource")
  private FaultyResourceInfo rootCauseFaultyResource = null;

  @JsonProperty("alarmRaisedTime")
  private String alarmRaisedTime = null;

  @JsonProperty("alarmChangedTime")
  private String alarmChangedTime = null;

  @JsonProperty("alarmClearedTime")
  private String alarmClearedTime = null;

  @JsonProperty("alarmAcknowledgedTime")
  private String alarmAcknowledgedTime = null;

  /**
   * Acknowledgement state of the alarm.  Permitted values: * UNACKNOWLEDGED * ACKNOWLEDGED. 
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
  private String eventTime = null;

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
  private AlarmLinks links = null;

  public Alarm2 id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Identifier of this Alarm information element. 
   * @return id
  **/
  @ApiModelProperty(required = true, value = "Identifier of this Alarm information element. ")
  @NotNull


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Alarm2 managedObjectId(String managedObjectId) {
    this.managedObjectId = managedObjectId;
    return this;
  }

  /**
   * Identifier of the affected VNF instance. 
   * @return managedObjectId
  **/
  @ApiModelProperty(required = true, value = "Identifier of the affected VNF instance. ")
  @NotNull


  public String getManagedObjectId() {
    return managedObjectId;
  }

  public void setManagedObjectId(String managedObjectId) {
    this.managedObjectId = managedObjectId;
  }

  public Alarm2 vnfcInstanceIds(List<String> vnfcInstanceIds) {
    this.vnfcInstanceIds = vnfcInstanceIds;
    return this;
  }

  public Alarm2 addVnfcInstanceIdsItem(String vnfcInstanceIdsItem) {
    if (this.vnfcInstanceIds == null) {
      this.vnfcInstanceIds = new ArrayList<>();
    }
    this.vnfcInstanceIds.add(vnfcInstanceIdsItem);
    return this;
  }

  /**
   * Identifiers of the affected VNFC instances. 
   * @return vnfcInstanceIds
  **/
  @ApiModelProperty(value = "Identifiers of the affected VNFC instances. ")


  public List<String> getVnfcInstanceIds() {
    return vnfcInstanceIds;
  }

  public void setVnfcInstanceIds(List<String> vnfcInstanceIds) {
    this.vnfcInstanceIds = vnfcInstanceIds;
  }

  public Alarm2 rootCauseFaultyResource(FaultyResourceInfo rootCauseFaultyResource) {
    this.rootCauseFaultyResource = rootCauseFaultyResource;
    return this;
  }

  /**
   * The virtualised resources that are causing the VNF fault. 
   * @return rootCauseFaultyResource
  **/
  @ApiModelProperty(value = "The virtualised resources that are causing the VNF fault. ")

  @Valid

  public FaultyResourceInfo getRootCauseFaultyResource() {
    return rootCauseFaultyResource;
  }

  public void setRootCauseFaultyResource(FaultyResourceInfo rootCauseFaultyResource) {
    this.rootCauseFaultyResource = rootCauseFaultyResource;
  }

  public Alarm2 alarmRaisedTime(String alarmRaisedTime) {
    this.alarmRaisedTime = alarmRaisedTime;
    return this;
  }

  /**
   * Time stamp indicating when the alarm is raised by the managed object. 
   * @return alarmRaisedTime
  **/
  @ApiModelProperty(required = true, value = "Time stamp indicating when the alarm is raised by the managed object. ")
  @NotNull


  public String getAlarmRaisedTime() {
    return alarmRaisedTime;
  }

  public void setAlarmRaisedTime(String alarmRaisedTime) {
    this.alarmRaisedTime = alarmRaisedTime;
  }

  public Alarm2 alarmChangedTime(String alarmChangedTime) {
    this.alarmChangedTime = alarmChangedTime;
    return this;
  }

  /**
   * Time stamp indicating when the alarm was last changed. It shall be present if the alarm has been updated. 
   * @return alarmChangedTime
  **/
  @ApiModelProperty(value = "Time stamp indicating when the alarm was last changed. It shall be present if the alarm has been updated. ")


  public String getAlarmChangedTime() {
    return alarmChangedTime;
  }

  public void setAlarmChangedTime(String alarmChangedTime) {
    this.alarmChangedTime = alarmChangedTime;
  }

  public Alarm2 alarmClearedTime(String alarmClearedTime) {
    this.alarmClearedTime = alarmClearedTime;
    return this;
  }

  /**
   * Time stamp indicating when the alarm was cleared. It shall be present if the alarm has been cleared. 
   * @return alarmClearedTime
  **/
  @ApiModelProperty(value = "Time stamp indicating when the alarm was cleared. It shall be present if the alarm has been cleared. ")


  public String getAlarmClearedTime() {
    return alarmClearedTime;
  }

  public void setAlarmClearedTime(String alarmClearedTime) {
    this.alarmClearedTime = alarmClearedTime;
  }

  public Alarm2 alarmAcknowledgedTime(String alarmAcknowledgedTime) {
    this.alarmAcknowledgedTime = alarmAcknowledgedTime;
    return this;
  }

  /**
   * Time stamp indicating when the alarm was acknowledged. It shall be present if the alarm has been acknowledged. 
   * @return alarmAcknowledgedTime
  **/
  @ApiModelProperty(value = "Time stamp indicating when the alarm was acknowledged. It shall be present if the alarm has been acknowledged. ")


  public String getAlarmAcknowledgedTime() {
    return alarmAcknowledgedTime;
  }

  public void setAlarmAcknowledgedTime(String alarmAcknowledgedTime) {
    this.alarmAcknowledgedTime = alarmAcknowledgedTime;
  }

  public Alarm2 ackState(AckStateEnum ackState) {
    this.ackState = ackState;
    return this;
  }

  /**
   * Acknowledgement state of the alarm.  Permitted values: * UNACKNOWLEDGED * ACKNOWLEDGED. 
   * @return ackState
  **/
  @ApiModelProperty(required = true, value = "Acknowledgement state of the alarm.  Permitted values: * UNACKNOWLEDGED * ACKNOWLEDGED. ")
  @NotNull


  public AckStateEnum getAckState() {
    return ackState;
  }

  public void setAckState(AckStateEnum ackState) {
    this.ackState = ackState;
  }

  public Alarm2 perceivedSeverity(PerceivedSeverityType perceivedSeverity) {
    this.perceivedSeverity = perceivedSeverity;
    return this;
  }

  /**
   * Perceived severity of the managed object failure. 
   * @return perceivedSeverity
  **/
  @ApiModelProperty(required = true, value = "Perceived severity of the managed object failure. ")
  @NotNull

  @Valid

  public PerceivedSeverityType getPerceivedSeverity() {
    return perceivedSeverity;
  }

  public void setPerceivedSeverity(PerceivedSeverityType perceivedSeverity) {
    this.perceivedSeverity = perceivedSeverity;
  }

  public Alarm2 eventTime(String eventTime) {
    this.eventTime = eventTime;
    return this;
  }

  /**
   * Time stamp indicating when the fault was observed. 
   * @return eventTime
  **/
  @ApiModelProperty(required = true, value = "Time stamp indicating when the fault was observed. ")
  @NotNull


  public String getEventTime() {
    return eventTime;
  }

  public void setEventTime(String eventTime) {
    this.eventTime = eventTime;
  }

  public Alarm2 eventType(EventType eventType) {
    this.eventType = eventType;
    return this;
  }

  /**
   * Type of event. 
   * @return eventType
  **/
  @ApiModelProperty(required = true, value = "Type of event. ")
  @NotNull

  @Valid

  public EventType getEventType() {
    return eventType;
  }

  public void setEventType(EventType eventType) {
    this.eventType = eventType;
  }

  public Alarm2 faultType(String faultType) {
    this.faultType = faultType;
    return this;
  }

  /**
   * Additional information to clarify the type of the fault. 
   * @return faultType
  **/
  @ApiModelProperty(value = "Additional information to clarify the type of the fault. ")


  public String getFaultType() {
    return faultType;
  }

  public void setFaultType(String faultType) {
    this.faultType = faultType;
  }

  public Alarm2 probableCause(String probableCause) {
    this.probableCause = probableCause;
    return this;
  }

  /**
   * Information about the probable cause of the fault. 
   * @return probableCause
  **/
  @ApiModelProperty(required = true, value = "Information about the probable cause of the fault. ")
  @NotNull


  public String getProbableCause() {
    return probableCause;
  }

  public void setProbableCause(String probableCause) {
    this.probableCause = probableCause;
  }

  public Alarm2 isRootCause(Boolean isRootCause) {
    this.isRootCause = isRootCause;
    return this;
  }

  /**
   * Attribute indicating if this fault is the root for other correlated alarms. If TRUE, then the alarms listed in the attribute CorrelatedAlarmId are caused by this fault. 
   * @return isRootCause
  **/
  @ApiModelProperty(required = true, value = "Attribute indicating if this fault is the root for other correlated alarms. If TRUE, then the alarms listed in the attribute CorrelatedAlarmId are caused by this fault. ")
  @NotNull


  public Boolean getIsRootCause() {
    return isRootCause;
  }

  public void setIsRootCause(Boolean isRootCause) {
    this.isRootCause = isRootCause;
  }

  public Alarm2 correlatedAlarmIds(List<String> correlatedAlarmIds) {
    this.correlatedAlarmIds = correlatedAlarmIds;
    return this;
  }

  public Alarm2 addCorrelatedAlarmIdsItem(String correlatedAlarmIdsItem) {
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
  @ApiModelProperty(value = "List of identifiers of other alarms correlated to this fault. ")


  public List<String> getCorrelatedAlarmIds() {
    return correlatedAlarmIds;
  }

  public void setCorrelatedAlarmIds(List<String> correlatedAlarmIds) {
    this.correlatedAlarmIds = correlatedAlarmIds;
  }

  public Alarm2 faultDetails(List<String> faultDetails) {
    this.faultDetails = faultDetails;
    return this;
  }

  public Alarm2 addFaultDetailsItem(String faultDetailsItem) {
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
  @ApiModelProperty(value = "Provides additional information about the fault. ")


  public List<String> getFaultDetails() {
    return faultDetails;
  }

  public void setFaultDetails(List<String> faultDetails) {
    this.faultDetails = faultDetails;
  }

  public Alarm2 links(AlarmLinks links) {
    this.links = links;
    return this;
  }

  /**
   * Get links
   * @return links
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public AlarmLinks getLinks() {
    return links;
  }

  public void setLinks(AlarmLinks links) {
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
    Alarm2 alarm2 = (Alarm2) o;
    return Objects.equals(this.id, alarm2.id) &&
        Objects.equals(this.managedObjectId, alarm2.managedObjectId) &&
        Objects.equals(this.vnfcInstanceIds, alarm2.vnfcInstanceIds) &&
        Objects.equals(this.rootCauseFaultyResource, alarm2.rootCauseFaultyResource) &&
        Objects.equals(this.alarmRaisedTime, alarm2.alarmRaisedTime) &&
        Objects.equals(this.alarmChangedTime, alarm2.alarmChangedTime) &&
        Objects.equals(this.alarmClearedTime, alarm2.alarmClearedTime) &&
        Objects.equals(this.alarmAcknowledgedTime, alarm2.alarmAcknowledgedTime) &&
        Objects.equals(this.ackState, alarm2.ackState) &&
        Objects.equals(this.perceivedSeverity, alarm2.perceivedSeverity) &&
        Objects.equals(this.eventTime, alarm2.eventTime) &&
        Objects.equals(this.eventType, alarm2.eventType) &&
        Objects.equals(this.faultType, alarm2.faultType) &&
        Objects.equals(this.probableCause, alarm2.probableCause) &&
        Objects.equals(this.isRootCause, alarm2.isRootCause) &&
        Objects.equals(this.correlatedAlarmIds, alarm2.correlatedAlarmIds) &&
        Objects.equals(this.faultDetails, alarm2.faultDetails) &&
        Objects.equals(this.links, alarm2.links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, managedObjectId, vnfcInstanceIds, rootCauseFaultyResource, alarmRaisedTime, alarmChangedTime, alarmClearedTime, alarmAcknowledgedTime, ackState, perceivedSeverity, eventTime, eventType, faultType, probableCause, isRootCause, correlatedAlarmIds, faultDetails, links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Alarm2 {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    managedObjectId: ").append(toIndentedString(managedObjectId)).append("\n");
    sb.append("    vnfcInstanceIds: ").append(toIndentedString(vnfcInstanceIds)).append("\n");
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

