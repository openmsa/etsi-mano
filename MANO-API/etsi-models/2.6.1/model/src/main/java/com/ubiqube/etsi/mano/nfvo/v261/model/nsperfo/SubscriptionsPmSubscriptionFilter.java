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

package com.ubiqube.etsi.mano.nfvo.v261.model.nsperfo;

import java.util.List;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;


/**
 * This type represents a filter that can be used to subscribe for notifications related to performance management events. It shall comply with the provisions defined in Table 7.5.3.2-1. At a particular nesting level in the filter structure, the following applies: All attributes shall match in order for the filter to match (logical \"and\" between different filter attributes). If an attribute is an array, the attribute shall match if at least one of the values in the array matches (logical
 * \"or\" between the values of one filter attribute).
 **/
@Schema(description = "This type represents a filter that can be used to subscribe for  notifications related to performance management events. It shall comply with the provisions defined in Table 7.5.3.2-1. At a particular nesting level in the filter structure, the following applies:  All attributes shall match in order for the filter to match (logical \"and\" between different filter attributes).  If an attribute is an array, the attribute shall match if at least one of the values in the array matches (logical \"or\" between the values of one filter attribute). ")
public class SubscriptionsPmSubscriptionFilter {

	@Schema(description = "")
	@Valid
	private SubscriptionsPmSubscriptionFilterNsInstanceSubscriptionFilter nsInstanceSubscriptionFilter = null;

	@XmlType(name = "NotificationTypesEnum")
	@XmlEnum(String.class)
	public enum NotificationTypesEnum {

		ThresholdCrossedNotification("ThresholdCrossedNotification"),
		PerformanceInformationAvailableNotification("PerformanceInformationAvailableNotification");

		private final String value;

		NotificationTypesEnum(final String v) {
			value = v;
		}

		public String value() {
			return value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		public static NotificationTypesEnum fromValue(final String v) {
			for (final NotificationTypesEnum b : NotificationTypesEnum.values()) {
				if (String.valueOf(b.value).equals(v)) {
					return b;
				}
			}
			return null;
		}
	}

	@Schema(description = "Match particular notification types. Permitted values: - ThresholdCrossedNotification - PerformanceInformationAvailableNotification The permitted values of the \"notificationTypes\" attribute are spelled exactly as the names of the notification types to facilitate automated code generation systems.           ")
	/**
	 * Match particular notification types. Permitted values: - ThresholdCrossedNotification - PerformanceInformationAvailableNotification The permitted values of the \"notificationTypes\" attribute are spelled exactly as the names of the notification types to facilitate automated code generation systems.
	 **/
	private List<NotificationTypesEnum> notificationTypes = null;

	/**
	 * Get nsInstanceSubscriptionFilter
	 *
	 * @return nsInstanceSubscriptionFilter
	 **/
	@JsonProperty("nsInstanceSubscriptionFilter")
	public SubscriptionsPmSubscriptionFilterNsInstanceSubscriptionFilter getNsInstanceSubscriptionFilter() {
		return nsInstanceSubscriptionFilter;
	}

	public void setNsInstanceSubscriptionFilter(final SubscriptionsPmSubscriptionFilterNsInstanceSubscriptionFilter nsInstanceSubscriptionFilter) {
		this.nsInstanceSubscriptionFilter = nsInstanceSubscriptionFilter;
	}

	public SubscriptionsPmSubscriptionFilter nsInstanceSubscriptionFilter(final SubscriptionsPmSubscriptionFilterNsInstanceSubscriptionFilter nsInstanceSubscriptionFilter) {
		this.nsInstanceSubscriptionFilter = nsInstanceSubscriptionFilter;
		return this;
	}

	/**
	 * Match particular notification types. Permitted values: - ThresholdCrossedNotification - PerformanceInformationAvailableNotification The permitted values of the \&quot;notificationTypes\&quot; attribute are spelled exactly as the names of the notification types to facilitate automated code generation systems.
	 *
	 * @return notificationTypes
	 **/
	@JsonProperty("notificationTypes")
	public List<NotificationTypesEnum> getNotificationTypes() {
		return notificationTypes;
	}

	public void setNotificationTypes(final List<NotificationTypesEnum> notificationTypes) {
		this.notificationTypes = notificationTypes;
	}

	public SubscriptionsPmSubscriptionFilter notificationTypes(final List<NotificationTypesEnum> notificationTypes) {
		this.notificationTypes = notificationTypes;
		return this;
	}

	public SubscriptionsPmSubscriptionFilter addNotificationTypesItem(final NotificationTypesEnum notificationTypesItem) {
		this.notificationTypes.add(notificationTypesItem);
		return this;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class SubscriptionsPmSubscriptionFilter {\n");

		sb.append("    nsInstanceSubscriptionFilter: ").append(toIndentedString(nsInstanceSubscriptionFilter)).append("\n");
		sb.append("    notificationTypes: ").append(toIndentedString(notificationTypes)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces (except the first line).
	 */
	private static String toIndentedString(final Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
