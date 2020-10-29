/**
 * This copy of Woodstox XML processor is licensed under the
 * Apache (Software) License, version 2.0 ("the License").
 * See the License for details about distribution rights, and the
 * specific rights regarding derivate works.
 *
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/
 *
 * A copy is also included in the downloadable source code package
 * containing Woodstox, in file "ASL2.0", under the same directory
 * as this file.
 */
package com.ubiqube.etsi.mano.common.v261.model.vnf;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents a subscription related to notifications about VNF
 * package management.
 */
@ApiModel(description = "This type represents a subscription related to notifications about VNF package management. ")
@Validated
public class PkgmSubscription {
	@JsonProperty("id")
	private String id = null;

	@JsonProperty("filter")
	private PkgmNotificationsFilter filter = null;

	@JsonProperty("callbackUri")
	private String callbackUri = null;

	@JsonProperty("_links")
	private PkgmSubscriptionLinks links = null;

	public PkgmSubscription id(final String id) {
		this.id = id;
		return this;
	}

	/**
	 * Identifier of this subscription resource
	 *
	 * @return id
	 **/
	@ApiModelProperty(required = true, value = "Identifier of this subscription resource ")
	@NotNull

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public PkgmSubscription filter(final PkgmNotificationsFilter filter) {
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
	@ApiModelProperty(value = "Filter settings for this subscription, to define the subset of all notifications this subscription relates to. A particular notification is sent to the subscriber if the filter matches, or if there is no filter. ")

	@Valid

	public PkgmNotificationsFilter getFilter() {
		return filter;
	}

	public void setFilter(final PkgmNotificationsFilter filter) {
		this.filter = filter;
	}

	public PkgmSubscription callbackUri(final String callbackUri) {
		this.callbackUri = callbackUri;
		return this;
	}

	/**
	 * The URI of the endpoint to send the notification to
	 *
	 * @return callbackUri
	 **/
	@ApiModelProperty(required = true, value = "The URI of the endpoint to send the notification to ")
	@NotNull

	public String getCallbackUri() {
		return callbackUri;
	}

	public void setCallbackUri(final String callbackUri) {
		this.callbackUri = callbackUri;
	}

	public PkgmSubscription links(final PkgmSubscriptionLinks links) {
		this.links = links;
		return this;
	}

	/**
	 * Get links
	 *
	 * @return links
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	@Valid

	public PkgmSubscriptionLinks getLinks() {
		return links;
	}

	public void setLinks(final PkgmSubscriptionLinks links) {
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
		final PkgmSubscription pkgmSubscription = (PkgmSubscription) o;
		return Objects.equals(this.id, pkgmSubscription.id) &&
				Objects.equals(this.filter, pkgmSubscription.filter) &&
				Objects.equals(this.callbackUri, pkgmSubscription.callbackUri) &&
				Objects.equals(this.links, pkgmSubscription.links);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, filter, callbackUri, links);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class PkgmSubscription {\n");

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
