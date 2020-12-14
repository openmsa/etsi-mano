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

package com.ubiqube.etsi.mano.model.v271.sol005.nsfault;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.model.v271.sol005.nsfault.AlarmLinks;
import com.ubiqube.etsi.mano.model.v271.sol005.nsfault.DateTime2;
import com.ubiqube.etsi.mano.model.v271.sol005.nsfault.EventType;
import com.ubiqube.etsi.mano.model.v271.sol005.nsfault.FaultyComponentInfo;
import com.ubiqube.etsi.mano.model.v271.sol005.nsfault.FaultyResourceInfo;
import com.ubiqube.etsi.mano.model.v271.sol005.nsfault.PerceivedSeverityType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * The alarm data type encapsulates information about an alarm. It shall comply with the provisions defined in Table 8.5.2.4-1 
 */
@ApiModel(description = "The alarm data type encapsulates information about an alarm. It shall comply with the provisions defined in Table 8.5.2.4-1 ")
@Validated
public class Alarm   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("managedObjectId")
  private String managedObjectId = null;

  @JsonProperty("rootCauseFaultyComponent")
  private FaultyComponentInfo rootCauseFaultyComponent = null;

  @JsonProperty("rootCauseFaultyResource")
  private FaultyResourceInfo rootCauseFaultyResource = null;

  @JsonProperty("alarmRaisedTime")
  private DateTime2 alarmRaisedTime = null;

  @JsonProperty("alarmChangedTime")
  private DateTime2 alarmChangedTime = null;

  @JsonProperty("alarmClearedTime")
  private DateTime2 alarmClearedTime = null;

  @JsonProperty("alarmAcknowledgedTime")
  private DateTime2 alarmAcknowledgedTime = null;

  /**
   * Acknowledgment state of the alarm. Permitted values: UNACKNOWLEDGED ACKNOWLEDGED 
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
  private DateTime2 eventTime = null;

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
  private String faultDetails = null;

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
  @ApiModelProperty(required = true, value = "")
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
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getManagedObjectId() {
    return managedObjectId;
  }

  public void setManagedObjectId(String managedObjectId) {
    this.managedObjectId = managedObjectId;
  }

  public Alarm rootCauseFaultyComponent(FaultyComponentInfo rootCauseFaultyComponent) {
    this.rootCauseFaultyComponent = rootCauseFaultyComponent;
    return this;
  }

  /**
   * Get rootCauseFaultyComponent
   * @return rootCauseFaultyComponent
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public FaultyComponentInfo getRootCauseFaultyComponent() {
    return rootCauseFaultyComponent;
  }

  public void setRootCauseFaultyComponent(FaultyComponentInfo rootCauseFaultyComponent) {
    this.rootCauseFaultyComponent = rootCauseFaultyComponent;
  }

  public Alarm rootCauseFaultyResource(FaultyResourceInfo rootCauseFaultyResource) {
    this.rootCauseFaultyResource = rootCauseFaultyResource;
    return this;
  }

  /**
   * Get rootCauseFaultyResource
   * @return rootCauseFaultyResource
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public FaultyResourceInfo getRootCauseFaultyResource() {
    return rootCauseFaultyResource;
  }

  public void setRootCauseFaultyResource(FaultyResourceInfo rootCauseFaultyResource) {
    this.rootCauseFaultyResource = rootCauseFaultyResource;
  }

  public Alarm alarmRaisedTime(DateTime2 alarmRaisedTime) {
    this.alarmRaisedTime = alarmRaisedTime;
    return this;
  }

  /**
   * Get alarmRaisedTime
   * @return alarmRaisedTime
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public DateTime2 getAlarmRaisedTime() {
    return alarmRaisedTime;
  }

  public void setAlarmRaisedTime(DateTime2 alarmRaisedTime) {
    this.alarmRaisedTime = alarmRaisedTime;
  }

  public Alarm alarmChangedTime(DateTime2 alarmChangedTime) {
    this.alarmChangedTime = alarmChangedTime;
    return this;
  }

  /**
   * Get alarmChangedTime
   * @return alarmChangedTime
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public DateTime2 getAlarmChangedTime() {
    return alarmChangedTime;
  }

  public void setAlarmChangedTime(DateTime2 alarmChangedTime) {
    this.alarmChangedTime = alarmChangedTime;
  }

  public Alarm alarmClearedTime(DateTime2 alarmClearedTime) {
    this.alarmClearedTime = alarmClearedTime;
    return this;
  }

  /**
   * Get alarmClearedTime
   * @return alarmClearedTime
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public DateTime2 getAlarmClearedTime() {
    return alarmClearedTime;
  }

  public void setAlarmClearedTime(DateTime2 alarmClearedTime) {
    this.alarmClearedTime = alarmClearedTime;
  }

  public Alarm alarmAcknowledgedTime(DateTime2 alarmAcknowledgedTime) {
    this.alarmAcknowledgedTime = alarmAcknowledgedTime;
    return this;
  }

  /**
   * Get alarmAcknowledgedTime
   * @return alarmAcknowledgedTime
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public DateTime2 getAlarmAcknowledgedTime() {
    return alarmAcknowledgedTime;
  }

  public void setAlarmAcknowledgedTime(DateTime2 alarmAcknowledgedTime) {
    this.alarmAcknowledgedTime = alarmAcknowledgedTime;
  }

  public Alarm ackState(AckStateEnum ackState) {
    this.ackState = ackState;
    return this;
  }

  /**
   * Acknowledgment state of the alarm. Permitted values: UNACKNOWLEDGED ACKNOWLEDGED 
   * @return ackState
  **/
  @ApiModelProperty(required = true, value = "Acknowledgment state of the alarm. Permitted values: UNACKNOWLEDGED ACKNOWLEDGED ")
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
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public PerceivedSeverityType getPerceivedSeverity() {
    return perceivedSeverity;
  }

  public void setPerceivedSeverity(PerceivedSeverityType perceivedSeverity) {
    this.perceivedSeverity = perceivedSeverity;
  }

  public Alarm eventTime(DateTime2 eventTime) {
    this.eventTime = eventTime;
    return this;
  }

  /**
   * Get eventTime
   * @return eventTime
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public DateTime2 getEventTime() {
    return eventTime;
  }

  public void setEventTime(DateTime2 eventTime) {
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
  @ApiModelProperty(required = true, value = "")
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
  @ApiModelProperty(value = "Additional information to clarify the type of the fault. ")
  
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
  @ApiModelProperty(required = true, value = "Information about the probable cause of the fault. ")
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
   * Attribute indicating if this fault is the root for other correlated alarms. If TRUE, then the alarms listed in the attribute CorrelatedAlarmId are caused by this fault. 
   * @return isRootCause
  **/
  @ApiModelProperty(required = true, value = "Attribute indicating if this fault is the root for other correlated alarms. If TRUE, then the alarms listed in the attribute CorrelatedAlarmId are caused by this fault. ")
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
  @ApiModelProperty(value = "List of identifiers of other alarms correlated to this fault. ")
  
    public List<String> getCorrelatedAlarmIds() {
    return correlatedAlarmIds;
  }

  public void setCorrelatedAlarmIds(List<String> correlatedAlarmIds) {
    this.correlatedAlarmIds = correlatedAlarmIds;
  }

  public Alarm faultDetails(String faultDetails) {
    this.faultDetails = faultDetails;
    return this;
  }

  /**
   * Provides additional information about the fault.. 
   * @return faultDetails
  **/
  @ApiModelProperty(value = "Provides additional information about the fault.. ")
  
    public String getFaultDetails() {
    return faultDetails;
  }

  public void setFaultDetails(String faultDetails) {
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
  @ApiModelProperty(required = true, value = "")
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
        Objects.equals(this.rootCauseFaultyComponent, alarm.rootCauseFaultyComponent) &&
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
    return Objects.hash(id, managedObjectId, rootCauseFaultyComponent, rootCauseFaultyResource, alarmRaisedTime, alarmChangedTime, alarmClearedTime, alarmAcknowledgedTime, ackState, perceivedSeverity, eventTime, eventType, faultType, probableCause, isRootCause, correlatedAlarmIds, faultDetails, _links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Alarm {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    managedObjectId: ").append(toIndentedString(managedObjectId)).append("\n");
    sb.append("    rootCauseFaultyComponent: ").append(toIndentedString(rootCauseFaultyComponent)).append("\n");
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
