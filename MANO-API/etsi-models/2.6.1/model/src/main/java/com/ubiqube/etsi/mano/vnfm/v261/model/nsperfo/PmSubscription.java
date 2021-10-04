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
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;


/**
 * This type represents a subscription.
 */
@Schema(description = "This type represents a subscription. ")
@Validated


public class PmSubscription {
	@JsonProperty("id")
	private String id = null;

	@JsonProperty("filter")
	private PmNotificationsFilter filter = null;

	@JsonProperty("callbackUri")
	private String callbackUri = null;

	@JsonProperty("_links")
	private PmSubscriptionLinks links = null;

	public PmSubscription id(final String id) {
		this.id = id;
		return this;
	}

	/**
	 * Identifier that identifies the subscription.
	 * 
	 * @return id
	 **/
	@Schema(required = true, description = "Identifier that identifies the subscription. ")
	@NotNull

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public PmSubscription filter(final PmNotificationsFilter filter) {
		this.filter = filter;
		return this;
	}

	/**
	 * Filter settings for this subscription, to define the subset of all
	 * notifications this subscription relates to. A particular notification is sent
	 * to the subscriber if the filter matches, or if there is no filter.
	 * 
	 * @return filter
	 **/
	@Schema(description = "Filter settings for this subscription, to define the subset of all notifications this subscription relates to. A particular notification is sent to the subscriber if the filter matches, or if there is no filter. ")

	@Valid

	public PmNotificationsFilter getFilter() {
		return filter;
	}

	public void setFilter(final PmNotificationsFilter filter) {
		this.filter = filter;
	}

	public PmSubscription callbackUri(final String callbackUri) {
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

	public PmSubscription links(final PmSubscriptionLinks links) {
		this.links = links;
		return this;
	}

	/**
	 * Get links
	 * 
	 * @return links
	 **/
	@Schema(required = true, description = "")
	@NotNull

	@Valid

	public PmSubscriptionLinks getLinks() {
		return links;
	}

	public void setLinks(final PmSubscriptionLinks links) {
		this.links = links;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final PmSubscription pmSubscription = (PmSubscription) o;
		return Objects.equals(this.id, pmSubscription.id) &&
				Objects.equals(this.filter, pmSubscription.filter) &&
				Objects.equals(this.callbackUri, pmSubscription.callbackUri) &&
				Objects.equals(this.links, pmSubscription.links);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, filter, callbackUri, links);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class PmSubscription {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    filter: ").append(toIndentedString(filter)).append("\n");
		sb.append("    callbackUri: ").append(toIndentedString(callbackUri)).append("\n");
		sb.append("    links: ").append(toIndentedString(links)).append("\n");
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
