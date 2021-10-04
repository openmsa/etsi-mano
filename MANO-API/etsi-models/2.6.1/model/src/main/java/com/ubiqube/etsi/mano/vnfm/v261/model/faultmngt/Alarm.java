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

package com.ubiqube.etsi.mano.vnfm.v261.model.faultmngt;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;


/**
 * The alarm data type encapsulates information about an alarm.
 */
@Schema(description = "The alarm data type encapsulates information about an alarm. ")
@Validated


public class Alarm {
	@JsonProperty("id")
	private String id = null;

	@JsonProperty("managedObjectId")
	private String managedObjectId = null;

	@JsonProperty("vnfcInstanceIds")
	@Valid
	private List<String> vnfcInstanceIds = new ArrayList<>();

	@JsonProperty("rootCauseFaultyResource")
	private FaultyResourceInfo rootCauseFaultyResource = null;

	@JsonProperty("alarmRaisedTime")
	private String alarmRaisedTime = null;

	@JsonProperty("alarmChangedTime")
	private String alarmChangedTime = null;

	@JsonProperty("alarmClearedTime")
	private String alarmClearedTime = null;

	/**
	 * Acknowledgement state of the alarm. Permitted values: * UNACKNOWLEDGED *
	 * ACKNOWLEDGED.
	 */
	public enum AckStateEnum {
		UNACKNOWLEDGED("UNACKNOWLEDGED"),

		ACKNOWLEDGED("ACKNOWLEDGED");

		private final String value;

		AckStateEnum(final String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static AckStateEnum fromValue(final String text) {
			for (final AckStateEnum b : AckStateEnum.values()) {
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

	public Alarm id(final String id) {
		this.id = id;
		return this;
	}

	/**
	 * Identifier of this Alarm information element.
	 * 
	 * @return id
	 **/
	@Schema(required = true, description = "Identifier of this Alarm information element. ")
	@NotNull

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public Alarm managedObjectId(final String managedObjectId) {
		this.managedObjectId = managedObjectId;
		return this;
	}

	/**
	 * Identifier of the affected VNF instance.
	 * 
	 * @return managedObjectId
	 **/
	@Schema(required = true, description = "Identifier of the affected VNF instance. ")
	@NotNull

	public String getManagedObjectId() {
		return managedObjectId;
	}

	public void setManagedObjectId(final String managedObjectId) {
		this.managedObjectId = managedObjectId;
	}

	public Alarm vnfcInstanceIds(final List<String> vnfcInstanceIds) {
		this.vnfcInstanceIds = vnfcInstanceIds;
		return this;
	}

	public Alarm addVnfcInstanceIdsItem(final String vnfcInstanceIdsItem) {
		this.vnfcInstanceIds.add(vnfcInstanceIdsItem);
		return this;
	}

	/**
	 * Identifiers of the affected VNFC instances.
	 *
	 * @return vnfcInstanceIds
	 **/
	@Schema(required = true, description = "Identifiers of the affected VNFC instances. ")
	@NotNull

	public List<String> getVnfcInstanceIds() {
		return vnfcInstanceIds;
	}

	public void setVnfcInstanceIds(final List<String> vnfcInstanceIds) {
		this.vnfcInstanceIds = vnfcInstanceIds;
	}

	public Alarm rootCauseFaultyResource(final FaultyResourceInfo rootCauseFaultyResource) {
		this.rootCauseFaultyResource = rootCauseFaultyResource;
		return this;
	}

	/**
	 * The virtualised resources that are causing the VNF fault.
	 * 
	 * @return rootCauseFaultyResource
	 **/
	@Schema(required = true, description = "The virtualised resources that are causing the VNF fault. ")
	@NotNull

	@Valid

	public FaultyResourceInfo getRootCauseFaultyResource() {
		return rootCauseFaultyResource;
	}

	public void setRootCauseFaultyResource(final FaultyResourceInfo rootCauseFaultyResource) {
		this.rootCauseFaultyResource = rootCauseFaultyResource;
	}

	public Alarm alarmRaisedTime(final String alarmRaisedTime) {
		this.alarmRaisedTime = alarmRaisedTime;
		return this;
	}

	/**
	 * Time stamp indicating when the alarm is raised by the managed object.
	 * 
	 * @return alarmRaisedTime
	 **/
	@Schema(required = true, description = "Time stamp indicating when the alarm is raised by the managed object. ")
	@NotNull

	public String getAlarmRaisedTime() {
		return alarmRaisedTime;
	}

	public void setAlarmRaisedTime(final String alarmRaisedTime) {
		this.alarmRaisedTime = alarmRaisedTime;
	}

	public Alarm alarmChangedTime(final String alarmChangedTime) {
		this.alarmChangedTime = alarmChangedTime;
		return this;
	}

	/**
	 * Time stamp indicating when the alarm was last changed. It shall be present if
	 * the alarm has been updated.
	 * 
	 * @return alarmChangedTime
	 **/
	@Schema(description = "Time stamp indicating when the alarm was last changed. It shall be present if the alarm has been updated. ")

	public String getAlarmChangedTime() {
		return alarmChangedTime;
	}

	public void setAlarmChangedTime(final String alarmChangedTime) {
		this.alarmChangedTime = alarmChangedTime;
	}

	public Alarm alarmClearedTime(final String alarmClearedTime) {
		this.alarmClearedTime = alarmClearedTime;
		return this;
	}

	/**
	 * Time stamp indicating when the alarm was cleared. It shall be present if the
	 * alarm has been cleared.
	 * 
	 * @return alarmClearedTime
	 **/
	@Schema(description = "Time stamp indicating when the alarm was cleared. It shall be present if the alarm has been cleared. ")

	public String getAlarmClearedTime() {
		return alarmClearedTime;
	}

	public void setAlarmClearedTime(final String alarmClearedTime) {
		this.alarmClearedTime = alarmClearedTime;
	}

	public Alarm ackState(final AckStateEnum ackState) {
		this.ackState = ackState;
		return this;
	}

	/**
	 * Acknowledgement state of the alarm. Permitted values: * UNACKNOWLEDGED *
	 * ACKNOWLEDGED.
	 * 
	 * @return ackState
	 **/
	@Schema(required = true, description = "Acknowledgement state of the alarm. Permitted values: * UNACKNOWLEDGED * ACKNOWLEDGED. ")
	@NotNull

	public AckStateEnum getAckState() {
		return ackState;
	}

	public void setAckState(final AckStateEnum ackState) {
		this.ackState = ackState;
	}

	public Alarm perceivedSeverity(final PerceivedSeverityType perceivedSeverity) {
		this.perceivedSeverity = perceivedSeverity;
		return this;
	}

	/**
	 * Perceived severity of the managed object failure.
	 * 
	 * @return perceivedSeverity
	 **/
	@Schema(required = true, description = "Perceived severity of the managed object failure. ")
	@NotNull

	@Valid

	public PerceivedSeverityType getPerceivedSeverity() {
		return perceivedSeverity;
	}

	public void setPerceivedSeverity(final PerceivedSeverityType perceivedSeverity) {
		this.perceivedSeverity = perceivedSeverity;
	}

	public Alarm eventTime(final String eventTime) {
		this.eventTime = eventTime;
		return this;
	}

	/**
	 * Time stamp indicating when the fault was observed.
	 * 
	 * @return eventTime
	 **/
	@Schema(required = true, description = "Time stamp indicating when the fault was observed. ")
	@NotNull

	public String getEventTime() {
		return eventTime;
	}

	public void setEventTime(final String eventTime) {
		this.eventTime = eventTime;
	}

	public Alarm eventType(final EventType eventType) {
		this.eventType = eventType;
		return this;
	}

	/**
	 * Type of event.
	 * 
	 * @return eventType
	 **/
	@Schema(required = true, description = "Type of event. ")
	@NotNull

	@Valid

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(final EventType eventType) {
		this.eventType = eventType;
	}

	public Alarm faultType(final String faultType) {
		this.faultType = faultType;
		return this;
	}

	/**
	 * Additional information to clarify the type of the fault.
	 * 
	 * @return faultType
	 **/
	@Schema(description = "Additional information to clarify the type of the fault. ")

	public String getFaultType() {
		return faultType;
	}

	public void setFaultType(final String faultType) {
		this.faultType = faultType;
	}

	public Alarm probableCause(final String probableCause) {
		this.probableCause = probableCause;
		return this;
	}

	/**
	 * Information about the probable cause of the fault.
	 * 
	 * @return probableCause
	 **/
	@Schema(required = true, description = "Information about the probable cause of the fault. ")
	@NotNull

	public String getProbableCause() {
		return probableCause;
	}

	public void setProbableCause(final String probableCause) {
		this.probableCause = probableCause;
	}

	public Alarm isRootCause(final Boolean isRootCause) {
		this.isRootCause = isRootCause;
		return this;
	}

	/**
	 * Attribute indicating if this fault is the root for other correlated alarms.
	 * If TRUE, then the alarms listed in the attribute CorrelatedAlarmId are caused
	 * by this fault.
	 * 
	 * @return isRootCause
	 **/
	@Schema(required = true, description = "Attribute indicating if this fault is the root for other correlated alarms. If TRUE, then the alarms listed in the attribute CorrelatedAlarmId are caused by this fault. ")
	@NotNull

	public Boolean getIsRootCause() {
		return isRootCause;
	}

	public void setIsRootCause(final Boolean isRootCause) {
		this.isRootCause = isRootCause;
	}

	public Alarm correlatedAlarmIds(final List<String> correlatedAlarmIds) {
		this.correlatedAlarmIds = correlatedAlarmIds;
		return this;
	}

	public Alarm addCorrelatedAlarmIdsItem(final String correlatedAlarmIdsItem) {
		if (this.correlatedAlarmIds == null) {
			this.correlatedAlarmIds = new ArrayList<>();
		}
		this.correlatedAlarmIds.add(correlatedAlarmIdsItem);
		return this;
	}

	/**
	 * List of identifiers of other alarms correlated to this fault.
	 * 
	 * @return correlatedAlarmIds
	 **/
	@Schema(description = "List of identifiers of other alarms correlated to this fault. ")

	public List<String> getCorrelatedAlarmIds() {
		return correlatedAlarmIds;
	}

	public void setCorrelatedAlarmIds(final List<String> correlatedAlarmIds) {
		this.correlatedAlarmIds = correlatedAlarmIds;
	}

	public Alarm faultDetails(final List<String> faultDetails) {
		this.faultDetails = faultDetails;
		return this;
	}

	public Alarm addFaultDetailsItem(final String faultDetailsItem) {
		if (this.faultDetails == null) {
			this.faultDetails = new ArrayList<>();
		}
		this.faultDetails.add(faultDetailsItem);
		return this;
	}

	/**
	 * Provides additional information about the fault.
	 * 
	 * @return faultDetails
	 **/
	@Schema(description = "Provides additional information about the fault. ")

	public List<String> getFaultDetails() {
		return faultDetails;
	}

	public void setFaultDetails(final List<String> faultDetails) {
		this.faultDetails = faultDetails;
	}

	public Alarm links(final AlarmLinks links) {
		this.links = links;
		return this;
	}

	/**
	 * Get links
	 * 
	 * @return links
	 **/
	@Schema(required = true, description = "")
	@NotNull

	@Valid

	public AlarmLinks getLinks() {
		return links;
	}

	public void setLinks(final AlarmLinks links) {
		this.links = links;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final Alarm alarm = (Alarm) o;
		return Objects.equals(this.id, alarm.id) &&
				Objects.equals(this.managedObjectId, alarm.managedObjectId) &&
				Objects.equals(this.vnfcInstanceIds, alarm.vnfcInstanceIds) &&
				Objects.equals(this.rootCauseFaultyResource, alarm.rootCauseFaultyResource) &&
				Objects.equals(this.alarmRaisedTime, alarm.alarmRaisedTime) &&
				Objects.equals(this.alarmChangedTime, alarm.alarmChangedTime) &&
				Objects.equals(this.alarmClearedTime, alarm.alarmClearedTime) &&
				Objects.equals(this.ackState, alarm.ackState) &&
				Objects.equals(this.perceivedSeverity, alarm.perceivedSeverity) &&
				Objects.equals(this.eventTime, alarm.eventTime) &&
				Objects.equals(this.eventType, alarm.eventType) &&
				Objects.equals(this.faultType, alarm.faultType) &&
				Objects.equals(this.probableCause, alarm.probableCause) &&
				Objects.equals(this.isRootCause, alarm.isRootCause) &&
				Objects.equals(this.correlatedAlarmIds, alarm.correlatedAlarmIds) &&
				Objects.equals(this.faultDetails, alarm.faultDetails) &&
				Objects.equals(this.links, alarm.links);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, managedObjectId, vnfcInstanceIds, rootCauseFaultyResource, alarmRaisedTime, alarmChangedTime, alarmClearedTime, ackState, perceivedSeverity, eventTime, eventType, faultType, probableCause, isRootCause, correlatedAlarmIds, faultDetails, links);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class Alarm {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    managedObjectId: ").append(toIndentedString(managedObjectId)).append("\n");
		sb.append("    vnfcInstanceIds: ").append(toIndentedString(vnfcInstanceIds)).append("\n");
		sb.append("    rootCauseFaultyResource: ").append(toIndentedString(rootCauseFaultyResource)).append("\n");
		sb.append("    alarmRaisedTime: ").append(toIndentedString(alarmRaisedTime)).append("\n");
		sb.append("    alarmChangedTime: ").append(toIndentedString(alarmChangedTime)).append("\n");
		sb.append("    alarmClearedTime: ").append(toIndentedString(alarmClearedTime)).append("\n");
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
	private String toIndentedString(final java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
