package com.ubiqube.etsi.mano.vnfm.v261.model.vrqan;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.common.v261.model.Link;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Links to resources related to this notification.
 */
@ApiModel(description = "Links to resources related to this notification. ")
@Validated

public class AlarmListRebuiltNotificationLinks {
	@JsonProperty("subscription")
	private Link subscription = null;

	@JsonProperty("alarms")
	private Link alarms = null;

	public AlarmListRebuiltNotificationLinks subscription(final Link subscription) {
		this.subscription = subscription;
		return this;
	}

	/**
	 * Link to the related subscription.
	 *
	 * @return subscription
	 **/
	@ApiModelProperty(required = true, value = "Link to the related subscription. ")
	@NotNull

	@Valid

	public Link getSubscription() {
		return subscription;
	}

	public void setSubscription(final Link subscription) {
		this.subscription = subscription;
	}

	public AlarmListRebuiltNotificationLinks alarms(final Link alarms) {
		this.alarms = alarms;
		return this;
	}

	/**
	 * Link to the alarm list, i.e. the \"Alarms\" resource.
	 *
	 * @return alarms
	 **/
	@ApiModelProperty(required = true, value = "Link to the alarm list, i.e. the \"Alarms\" resource. ")
	@NotNull

	@Valid

	public Link getAlarms() {
		return alarms;
	}

	public void setAlarms(final Link alarms) {
		this.alarms = alarms;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final AlarmListRebuiltNotificationLinks alarmListRebuiltNotificationLinks = (AlarmListRebuiltNotificationLinks) o;
		return Objects.equals(this.subscription, alarmListRebuiltNotificationLinks.subscription) &&
				Objects.equals(this.alarms, alarmListRebuiltNotificationLinks.alarms);
	}

	@Override
	public int hashCode() {
		return Objects.hash(subscription, alarms);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class AlarmListRebuiltNotificationLinks {\n");

		sb.append("    subscription: ").append(toIndentedString(subscription)).append("\n");
		sb.append("    alarms: ").append(toIndentedString(alarms)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces (except the first line).
	 */
	private String toIndentedString(final java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
