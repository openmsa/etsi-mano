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
package com.ubiqube.etsi.mano.nfvo.v331.model.nfvici;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.em.v331.model.vnflcm.NotificationLink;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Links to resources related to this notification.
 */
@Schema(description = "Links to resources related to this notification. ")
@Validated

public class CapacityShortageNotificationLinks {
	@JsonProperty("objectInstance")
	private NotificationLink objectInstance = null;

	@JsonProperty("threshold")
	private NotificationLink threshold = null;

	public CapacityShortageNotificationLinks objectInstance(final NotificationLink objectInstance) {
		this.objectInstance = objectInstance;
		return this;
	}

	/**
	 * Get objectInstance
	 *
	 * @return objectInstance
	 **/
	@Schema(description = "")

	@Valid
	public NotificationLink getObjectInstance() {
		return objectInstance;
	}

	public void setObjectInstance(final NotificationLink objectInstance) {
		this.objectInstance = objectInstance;
	}

	public CapacityShortageNotificationLinks threshold(final NotificationLink threshold) {
		this.threshold = threshold;
		return this;
	}

	/**
	 * Get threshold
	 *
	 * @return threshold
	 **/
	@Schema(required = true, description = "")
	@NotNull

	@Valid
	public NotificationLink getThreshold() {
		return threshold;
	}

	public void setThreshold(final NotificationLink threshold) {
		this.threshold = threshold;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final CapacityShortageNotificationLinks capacityShortageNotificationLinks = (CapacityShortageNotificationLinks) o;
		return Objects.equals(this.objectInstance, capacityShortageNotificationLinks.objectInstance) &&
				Objects.equals(this.threshold, capacityShortageNotificationLinks.threshold);
	}

	@Override
	public int hashCode() {
		return Objects.hash(objectInstance, threshold);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class CapacityShortageNotificationLinks {\n");

		sb.append("    objectInstance: ").append(toIndentedString(objectInstance)).append("\n");
		sb.append("    threshold: ").append(toIndentedString(threshold)).append("\n");
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
