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

package com.ubiqube.etsi.mano.vnfm.v261.model.nsperfo;

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
 * This type represents a filter that can be used to subscribe for notifications
 * related to performance management events.
 */
@ApiModel(description = "This type represents a filter that can be used to subscribe for notifications related to performance management events. ")
@Validated


public class PmNotificationsFilter {
	@JsonProperty("vnfInstanceSubscriptionFilter")
	private VnfInstanceSubscriptionFilter vnfInstanceSubscriptionFilter = null;

	/**
	 * Match particular notification types. Permitted values: *
	 * ThresholdCrossedNotification * PerformanceInformationAvailableNotification
	 * The permitted values of the \"notificationTypes\" attribute are spelled
	 * exactly as the names of the notification types to facilitate automated code
	 * generation systems.
	 */
	public enum NotificationTypesEnum {
		THRESHOLDCROSSEDNOTIFICATION("ThresholdCrossedNotification"),

		PERFORMANCEINFORMATIONAVAILABLENOTIFICATION("PerformanceInformationAvailableNotification");

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
	private NotificationTypesEnum notificationTypes = null;

	public PmNotificationsFilter vnfInstanceSubscriptionFilter(final VnfInstanceSubscriptionFilter vnfInstanceSubscriptionFilter) {
		this.vnfInstanceSubscriptionFilter = vnfInstanceSubscriptionFilter;
		return this;
	}

	/**
	 * Filter criteria to select VNF instances about which to notify.
	 *
	 * @return vnfInstanceSubscriptionFilter
	 **/
	@ApiModelProperty(value = "Filter criteria to select VNF instances about which to notify. ")

	@Valid

	public VnfInstanceSubscriptionFilter getVnfInstanceSubscriptionFilter() {
		return vnfInstanceSubscriptionFilter;
	}

	public void setVnfInstanceSubscriptionFilter(final VnfInstanceSubscriptionFilter vnfInstanceSubscriptionFilter) {
		this.vnfInstanceSubscriptionFilter = vnfInstanceSubscriptionFilter;
	}

	public PmNotificationsFilter notificationTypes(final NotificationTypesEnum notificationTypes) {
		this.notificationTypes = notificationTypes;
		return this;
	}

	/**
	 * Match particular notification types. Permitted values: *
	 * ThresholdCrossedNotification * PerformanceInformationAvailableNotification
	 * The permitted values of the \"notificationTypes\" attribute are spelled
	 * exactly as the names of the notification types to facilitate automated code
	 * generation systems.
	 *
	 * @return notificationTypes
	 **/
	@ApiModelProperty(value = "Match particular notification types. Permitted values: * ThresholdCrossedNotification * PerformanceInformationAvailableNotification The permitted values of the \"notificationTypes\" attribute are spelled exactly as the names of the notification types to facilitate automated code generation systems. ")

	public NotificationTypesEnum getNotificationTypes() {
		return notificationTypes;
	}

	public void setNotificationTypes(final NotificationTypesEnum notificationTypes) {
		this.notificationTypes = notificationTypes;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final PmNotificationsFilter pmNotificationsFilter = (PmNotificationsFilter) o;
		return Objects.equals(this.vnfInstanceSubscriptionFilter, pmNotificationsFilter.vnfInstanceSubscriptionFilter) &&
				Objects.equals(this.notificationTypes, pmNotificationsFilter.notificationTypes);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vnfInstanceSubscriptionFilter, notificationTypes);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class PmNotificationsFilter {\n");

		sb.append("    vnfInstanceSubscriptionFilter: ").append(toIndentedString(vnfInstanceSubscriptionFilter)).append("\n");
		sb.append("    notificationTypes: ").append(toIndentedString(notificationTypes)).append("\n");
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
