package com.ubiqube.etsi.mano.vnfm.v261.model.faultmngt;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.vnfm.v261.model.VnfInstanceSubscriptionFilter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents a subscription filter related to notifications about VNF
 * faults. At a particular nesting level in the filter structure, the following
 * applies: All attributes shall match in order for the filter to match (logical
 * \&quot;and\&quot; between different filter attributes). If an attribute is an
 * array, the attribute shall match if at least one of the values in the array
 * matches (logical \&quot;or\&quot; between the values of one filter
 * attribute).
 */
@ApiModel(description = "This type represents a subscription filter related to notifications about VNF faults. At a particular nesting level in the filter structure, the following applies: All attributes shall match in order for the filter to match (logical \"and\" between different filter attributes). If an attribute is an array, the attribute shall match if at least one of the values in the array matches (logical \"or\" between the values of one filter attribute). ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-05-01T16:03:26.781+02:00")

public class FmNotificationsFilter {
	@JsonProperty("vnfInstanceSubscriptionFilter")
	private VnfInstanceSubscriptionFilter vnfInstanceSubscriptionFilter = null;

	/**
	 * Gets or Sets notificationTypes
	 */
	public enum NotificationTypesEnum {
		ALARMNOTIFICATION("AlarmNotification"),

		ALARMCLEAREDNOTIFICATION("AlarmClearedNotification"),

		ALARMLISTREBUILTNOTIFICATION("AlarmListRebuiltNotification");

		private final String value;

		NotificationTypesEnum(final String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static NotificationTypesEnum fromValue(final String text) {
			for (final NotificationTypesEnum b : NotificationTypesEnum.values()) {
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

	public FmNotificationsFilter vnfInstanceSubscriptionFilter(final VnfInstanceSubscriptionFilter vnfInstanceSubscriptionFilter) {
		this.vnfInstanceSubscriptionFilter = vnfInstanceSubscriptionFilter;
		return this;
	}

	/**
	 * Filter criteria to select VNF instances about which to notify.
	 * 
	 * @return vnfInstanceSubscriptionFilter
	 **/
	@ApiModelProperty(value = "Filter criteria to select VNF instances about which to notify.     ")

	@Valid

	public VnfInstanceSubscriptionFilter getVnfInstanceSubscriptionFilter() {
		return vnfInstanceSubscriptionFilter;
	}

	public void setVnfInstanceSubscriptionFilter(final VnfInstanceSubscriptionFilter vnfInstanceSubscriptionFilter) {
		this.vnfInstanceSubscriptionFilter = vnfInstanceSubscriptionFilter;
	}

	public FmNotificationsFilter notificationTypes(final List<NotificationTypesEnum> notificationTypes) {
		this.notificationTypes = notificationTypes;
		return this;
	}

	public FmNotificationsFilter addNotificationTypesItem(final NotificationTypesEnum notificationTypesItem) {
		if (this.notificationTypes == null) {
			this.notificationTypes = new ArrayList<>();
		}
		this.notificationTypes.add(notificationTypesItem);
		return this;
	}

	/**
	 * Match particular notification types. Permitted values: * AlarmNotification *
	 * AlarmClearedNotification * AlarmListRebuiltNotification The permitted values
	 * of the \"notificationTypes\" attribute are spelled exactly as the names of
	 * the notification types to facilitate automated code generation systems.
	 * 
	 * @return notificationTypes
	 **/
	@ApiModelProperty(value = "Match particular notification types. Permitted values: * AlarmNotification * AlarmClearedNotification * AlarmListRebuiltNotification The permitted values of the \"notificationTypes\" attribute are spelled exactly as the names of the notification types to facilitate automated code generation systems. ")

	public List<NotificationTypesEnum> getNotificationTypes() {
		return notificationTypes;
	}

	public void setNotificationTypes(final List<NotificationTypesEnum> notificationTypes) {
		this.notificationTypes = notificationTypes;
	}

	public FmNotificationsFilter faultyResourceTypes(final List<FaultyResourceType> faultyResourceTypes) {
		this.faultyResourceTypes = faultyResourceTypes;
		return this;
	}

	public FmNotificationsFilter addFaultyResourceTypesItem(final FaultyResourceType faultyResourceTypesItem) {
		if (this.faultyResourceTypes == null) {
			this.faultyResourceTypes = new ArrayList<>();
		}
		this.faultyResourceTypes.add(faultyResourceTypesItem);
		return this;
	}

	/**
	 * Match VNF alarms with a faulty resource type listed in this attribute.
	 * 
	 * @return faultyResourceTypes
	 **/
	@ApiModelProperty(value = "Match VNF alarms with a faulty resource type listed in this attribute. ")

	@Valid

	public List<FaultyResourceType> getFaultyResourceTypes() {
		return faultyResourceTypes;
	}

	public void setFaultyResourceTypes(final List<FaultyResourceType> faultyResourceTypes) {
		this.faultyResourceTypes = faultyResourceTypes;
	}

	public FmNotificationsFilter perceivedSeverities(final List<PerceivedSeverityType> perceivedSeverities) {
		this.perceivedSeverities = perceivedSeverities;
		return this;
	}

	public FmNotificationsFilter addPerceivedSeveritiesItem(final PerceivedSeverityType perceivedSeveritiesItem) {
		if (this.perceivedSeverities == null) {
			this.perceivedSeverities = new ArrayList<>();
		}
		this.perceivedSeverities.add(perceivedSeveritiesItem);
		return this;
	}

	/**
	 * Match VNF alarms with a perceived severity listed in this attribute.
	 * 
	 * @return perceivedSeverities
	 **/
	@ApiModelProperty(value = "Match VNF alarms with a perceived severity listed in this attribute. ")

	@Valid

	public List<PerceivedSeverityType> getPerceivedSeverities() {
		return perceivedSeverities;
	}

	public void setPerceivedSeverities(final List<PerceivedSeverityType> perceivedSeverities) {
		this.perceivedSeverities = perceivedSeverities;
	}

	public FmNotificationsFilter eventTypes(final List<EventType> eventTypes) {
		this.eventTypes = eventTypes;
		return this;
	}

	public FmNotificationsFilter addEventTypesItem(final EventType eventTypesItem) {
		if (this.eventTypes == null) {
			this.eventTypes = new ArrayList<>();
		}
		this.eventTypes.add(eventTypesItem);
		return this;
	}

	/**
	 * Match VNF alarms with an event type listed in this attribute.
	 * 
	 * @return eventTypes
	 **/
	@ApiModelProperty(value = "Match VNF alarms with an event type listed in this attribute. ")

	@Valid

	public List<EventType> getEventTypes() {
		return eventTypes;
	}

	public void setEventTypes(final List<EventType> eventTypes) {
		this.eventTypes = eventTypes;
	}

	public FmNotificationsFilter probableCauses(final List<String> probableCauses) {
		this.probableCauses = probableCauses;
		return this;
	}

	public FmNotificationsFilter addProbableCausesItem(final String probableCausesItem) {
		if (this.probableCauses == null) {
			this.probableCauses = new ArrayList<>();
		}
		this.probableCauses.add(probableCausesItem);
		return this;
	}

	/**
	 * Match VNF alarms with a probable cause listed in this attribute.
	 * 
	 * @return probableCauses
	 **/
	@ApiModelProperty(value = "Match VNF alarms with a probable cause listed in this attribute. ")

	public List<String> getProbableCauses() {
		return probableCauses;
	}

	public void setProbableCauses(final List<String> probableCauses) {
		this.probableCauses = probableCauses;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final FmNotificationsFilter fmNotificationsFilter = (FmNotificationsFilter) o;
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
		final StringBuilder sb = new StringBuilder();
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
	private String toIndentedString(final java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
