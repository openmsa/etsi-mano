package com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents a subscription related to notifications about NSD
 * management.
 */
@ApiModel(description = "This type represents a subscription related to notifications about NSD management. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-11-25T16:34:13.188+01:00")

public class NsdmSubscription {
	@JsonProperty("id")
	private String id = null;

	@JsonProperty("filter")
	private NsdmNotificationsFilter filter = null;

	@JsonProperty("callbackUri")
	private String callbackUri = null;

	@JsonProperty("_links")
	private NsdmSubscriptionLinks links = null;

	public NsdmSubscription id(final String id) {
		this.id = id;
		return this;
	}

	/**
	 * Identifier of this subscription resource.
	 * 
	 * @return id
	 **/
	@ApiModelProperty(required = true, value = "Identifier of this subscription resource. ")
	@NotNull

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public NsdmSubscription filter(final NsdmNotificationsFilter filter) {
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

	public NsdmNotificationsFilter getFilter() {
		return filter;
	}

	public void setFilter(final NsdmNotificationsFilter filter) {
		this.filter = filter;
	}

	public NsdmSubscription callbackUri(final String callbackUri) {
		this.callbackUri = callbackUri;
		return this;
	}

	/**
	 * The URI of the endpoint to send the notification to.
	 * 
	 * @return callbackUri
	 **/
	@ApiModelProperty(required = true, value = "The URI of the endpoint to send the notification to. ")
	@NotNull

	public String getCallbackUri() {
		return callbackUri;
	}

	public void setCallbackUri(final String callbackUri) {
		this.callbackUri = callbackUri;
	}

	public NsdmSubscription links(final NsdmSubscriptionLinks links) {
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

	public NsdmSubscriptionLinks getLinks() {
		return links;
	}

	public void setLinks(final NsdmSubscriptionLinks links) {
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
		final NsdmSubscription nsdmSubscription = (NsdmSubscription) o;
		return Objects.equals(this.id, nsdmSubscription.id) &&
				Objects.equals(this.filter, nsdmSubscription.filter) &&
				Objects.equals(this.callbackUri, nsdmSubscription.callbackUri) &&
				Objects.equals(this.links, nsdmSubscription.links);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, filter, callbackUri, links);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class NsdmSubscription {\n");

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
