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

package com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005.notification;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.common.v261.model.Link;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents the links to resources that an NSD management
 * notification can contain.
 */
@ApiModel(description = "This type represents the links to resources that an NSD management notification can contain. ")
@Validated


public class NsdmLinks {
	@JsonProperty("nsdInfo")
	private Link nsdInfo = null;

	@JsonProperty("subscription")
	private Link subscription = null;

	public NsdmLinks nsdInfo(final Link nsdInfo) {
		this.nsdInfo = nsdInfo;
		return this;
	}

	/**
	 * Get nsdInfo
	 * 
	 * @return nsdInfo
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	@Valid

	public Link getNsdInfo() {
		return nsdInfo;
	}

	public void setNsdInfo(final Link nsdInfo) {
		this.nsdInfo = nsdInfo;
	}

	public NsdmLinks subscription(final Link subscription) {
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

	public Link getSubscription() {
		return subscription;
	}

	public void setSubscription(final Link subscription) {
		this.subscription = subscription;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final NsdmLinks nsdmLinks = (NsdmLinks) o;
		return Objects.equals(this.nsdInfo, nsdmLinks.nsdInfo) &&
				Objects.equals(this.subscription, nsdmLinks.subscription);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nsdInfo, subscription);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class NsdmLinks {\n");

		sb.append("    nsdInfo: ").append(toIndentedString(nsdInfo)).append("\n");
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
