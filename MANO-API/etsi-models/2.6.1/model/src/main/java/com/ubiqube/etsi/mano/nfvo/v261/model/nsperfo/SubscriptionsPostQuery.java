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

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;


/**
 *
 * TODO DELETE this. Details of the subscription to be created.
 **/
@Schema(description = "Details of the subscription to be created.             ")
public class SubscriptionsPostQuery {

	@Schema(required = true, description = "")
	@Valid
	private SubscriptionsPmSubscriptionRequest pmSubscriptionRequest = null;

	/**
	 * Get pmSubscriptionRequest
	 *
	 * @return pmSubscriptionRequest
	 **/
	@JsonProperty("PmSubscriptionRequest")
	@NotNull
	public SubscriptionsPmSubscriptionRequest getPmSubscriptionRequest() {
		return pmSubscriptionRequest;
	}

	public void setPmSubscriptionRequest(final SubscriptionsPmSubscriptionRequest pmSubscriptionRequest) {
		this.pmSubscriptionRequest = pmSubscriptionRequest;
	}

	public SubscriptionsPostQuery pmSubscriptionRequest(final SubscriptionsPmSubscriptionRequest pmSubscriptionRequest) {
		this.pmSubscriptionRequest = pmSubscriptionRequest;
		return this;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfPackagePostQuery {\n");

		sb.append("    pmSubscriptionRequest: ").append(toIndentedString(pmSubscriptionRequest)).append("\n");
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
