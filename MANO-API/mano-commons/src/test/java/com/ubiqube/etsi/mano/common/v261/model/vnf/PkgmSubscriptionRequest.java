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

package com.ubiqube.etsi.mano.common.v261.model.vnf;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;


/**
 * This type represents a subscription request related to VNF package management notifications about VNF package on boarding or changes.
 */
@Schema(description = "This type represents a subscription request related to VNF package management  notifications about VNF package on boarding or changes. ")
@Validated
public class PkgmSubscriptionRequest {
	@JsonProperty("filter")
	private PkgmNotificationsFilter filter = null;

	@JsonProperty("callbackUri")
	private String callbackUri = null;

	@JsonProperty("authentication")
	private SubscriptionAuthentication authentication = null;

	public PkgmSubscriptionRequest filter(final PkgmNotificationsFilter filter) {
		this.filter = filter;
		return this;
	}

	/**
	 * Filter settings for this subscription, to define the subset of all notifications this subscription relates to. A particular notification is sent to the subscriber if the filter matches, or if there is no filter.
	 *
	 * @return filter
	 **/
	@Schema(description = "Filter settings for this subscription, to define the subset of all notifications this subscription relates to. A particular notification is sent to the subscriber if the filter matches, or if there is no filter. ")

	@Valid

	public PkgmNotificationsFilter getFilter() {
		return filter;
	}

	public void setFilter(final PkgmNotificationsFilter filter) {
		this.filter = filter;
	}

	public PkgmSubscriptionRequest callbackUri(final String callbackUri) {
		this.callbackUri = callbackUri;
		return this;
	}

	/**
	 * The URI of the endpoint to send the notification to.
	 *
	 * @return callbackUri
	 **/
	@Schema(required = true, description = "The URI of the endpoint to send the notification to. ")
	@NotNull

	public String getCallbackUri() {
		return callbackUri;
	}

	public void setCallbackUri(final String callbackUri) {
		this.callbackUri = callbackUri;
	}

	public PkgmSubscriptionRequest authentication(final SubscriptionAuthentication authentication) {
		this.authentication = authentication;
		return this;
	}

	/**
	 * Authentication parameters to conFigure the use of authorization when sending notifications corresponding to this subscription, as defined in clause 8.3.4 of ETSI GS NFV SOL 013. This attribute shall only be present if the subscriber requires authorization of notifications.
	 *
	 * @return authentication
	 **/
	@Schema(description = "Authentication parameters to conFigure the use of authorization when sending notifications corresponding to this subscription, as defined in clause 8.3.4 of ETSI GS NFV SOL 013. This attribute shall only be present if the subscriber requires authorization of notifications. ")

	@Valid

	public SubscriptionAuthentication getAuthentication() {
		return authentication;
	}

	public void setAuthentication(final SubscriptionAuthentication authentication) {
		this.authentication = authentication;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final PkgmSubscriptionRequest pkgmSubscriptionRequest = (PkgmSubscriptionRequest) o;
		return Objects.equals(this.filter, pkgmSubscriptionRequest.filter) &&
				Objects.equals(this.callbackUri, pkgmSubscriptionRequest.callbackUri) &&
				Objects.equals(this.authentication, pkgmSubscriptionRequest.authentication);
	}

	@Override
	public int hashCode() {
		return Objects.hash(filter, callbackUri, authentication);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class PkgmSubscriptionRequest {\n");

		sb.append("    filter: ").append(toIndentedString(filter)).append("\n");
		sb.append("    callbackUri: ").append(toIndentedString(callbackUri)).append("\n");
		sb.append("    authentication: ").append(toIndentedString(authentication)).append("\n");
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
