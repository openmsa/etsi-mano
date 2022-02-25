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
package com.ubiqube.etsi.mano.nfvo.v281.model.vnfconfig;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.em.v281.model.vnflcm.NotificationLink;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents the links to resources that a PNFD management
 * notification can contain.
 */
@ApiModel(description = "This type represents the links to resources that a PNFD management notification can contain.")
@Validated

public class PnfdmLinks {
	@JsonProperty("pnfdInfo")
	private NotificationLink pnfdInfo = null;

	@JsonProperty("subscription")
	private NotificationLink subscription = null;

	public PnfdmLinks pnfdInfo(final NotificationLink pnfdInfo) {
		this.pnfdInfo = pnfdInfo;
		return this;
	}

	/**
	 * Get pnfdInfo
	 *
	 * @return pnfdInfo
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	@Valid

	public NotificationLink getPnfdInfo() {
		return pnfdInfo;
	}

	public void setPnfdInfo(final NotificationLink pnfdInfo) {
		this.pnfdInfo = pnfdInfo;
	}

	public PnfdmLinks subscription(final NotificationLink subscription) {
		this.subscription = subscription;
		return this;
	}

	/**
	 * Get subscription
	 *
	 * @return subscription
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	@Valid

	public NotificationLink getSubscription() {
		return subscription;
	}

	public void setSubscription(final NotificationLink subscription) {
		this.subscription = subscription;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final PnfdmLinks pnfdmLinks = (PnfdmLinks) o;
		return Objects.equals(this.pnfdInfo, pnfdmLinks.pnfdInfo) &&
				Objects.equals(this.subscription, pnfdmLinks.subscription);
	}

	@Override
	public int hashCode() {
		return Objects.hash(pnfdInfo, subscription);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class PnfdmLinks {\n");

		sb.append("    pnfdInfo: ").append(toIndentedString(pnfdInfo)).append("\n");
		sb.append("    subscription: ").append(toIndentedString(subscription)).append("\n");
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
