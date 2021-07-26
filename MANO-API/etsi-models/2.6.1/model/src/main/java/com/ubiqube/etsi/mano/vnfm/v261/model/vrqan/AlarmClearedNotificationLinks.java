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

public class AlarmClearedNotificationLinks {
	@JsonProperty("subscription")
	private Link subscription = null;

	@JsonProperty("alarm")
	private Link alarm = null;

	public AlarmClearedNotificationLinks subscription(final Link subscription) {
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

	public AlarmClearedNotificationLinks alarm(final Link alarm) {
		this.alarm = alarm;
		return this;
	}

	/**
	 * Link to the resource that represents the related alarm.
	 *
	 * @return alarm
	 **/
	@ApiModelProperty(required = true, value = "Link to the resource that represents the related alarm. ")
	@NotNull

	@Valid

	public Link getAlarm() {
		return alarm;
	}

	public void setAlarm(final Link alarm) {
		this.alarm = alarm;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final AlarmClearedNotificationLinks alarmClearedNotificationLinks = (AlarmClearedNotificationLinks) o;
		return Objects.equals(this.subscription, alarmClearedNotificationLinks.subscription) &&
				Objects.equals(this.alarm, alarmClearedNotificationLinks.alarm);
	}

	@Override
	public int hashCode() {
		return Objects.hash(subscription, alarm);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class AlarmClearedNotificationLinks {\n");

		sb.append("    subscription: ").append(toIndentedString(subscription)).append("\n");
		sb.append("    alarm: ").append(toIndentedString(alarm)).append("\n");
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
