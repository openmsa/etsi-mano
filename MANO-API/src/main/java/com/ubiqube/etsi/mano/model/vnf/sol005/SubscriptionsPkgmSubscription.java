package com.ubiqube.etsi.mano.model.vnf.sol005;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents a subscription related to notifications about VNF
 * package management.
 **/
@ApiModel(description = "This type represents a subscription related to notifications about VNF package management. ")
@JsonPropertyOrder({ "id", "filter", "callbackUri", "_links" })
public class SubscriptionsPkgmSubscription {

	@ApiModelProperty(required = true, value = "String formatted according to IETF RFC 3986. ")
	/**
	 * String formatted according to IETF RFC 3986.
	 **/
	private String id = null;

	@ApiModelProperty(value = "")
	@Valid
	private SubscriptionsPkgmSubscriptionFilter filter = null;

	@ApiModelProperty(required = true, value = "String formatted according to IETF RFC 3986. ")
	/**
	 * String formatted according to IETF RFC 3986.
	 **/
	private String callbackUri = null;

	public SubscriptionsPkgmSubscription() {
		// Nothing.
	}

	@ApiModelProperty(required = true, value = "")
	@Valid
	private SubscriptionsPkgmSubscriptionLinks links = null;

	/**
	 * String formatted according to IETF RFC 3986.
	 *
	 * @return id
	 **/
	@JsonProperty("id")
	@NotNull
	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public SubscriptionsPkgmSubscription id(final String id) {
		this.id = id;
		return this;
	}

	/**
	 * Get filter
	 *
	 * @return filter
	 **/
	@JsonProperty("filter")
	public SubscriptionsPkgmSubscriptionFilter getFilter() {
		return filter;
	}

	public void setFilter(final SubscriptionsPkgmSubscriptionFilter filter) {
		this.filter = filter;
	}

	public SubscriptionsPkgmSubscription filter(final SubscriptionsPkgmSubscriptionFilter filter) {
		this.filter = filter;
		return this;
	}

	/**
	 * String formatted according to IETF RFC 3986.
	 *
	 * @return callbackUri
	 **/
	@JsonProperty("callbackUri")
	@NotNull
	public String getCallbackUri() {
		return callbackUri;
	}

	public void setCallbackUri(final String callbackUri) {
		this.callbackUri = callbackUri;
	}

	public SubscriptionsPkgmSubscription callbackUri(final String callbackUri) {
		this.callbackUri = callbackUri;
		return this;
	}

	/**
	 * Get links
	 *
	 * @return links
	 **/
	@JsonProperty("_links")
	@NotNull
	public SubscriptionsPkgmSubscriptionLinks getLinks() {
		return links;
	}

	public void setLinks(final SubscriptionsPkgmSubscriptionLinks links) {
		this.links = links;
	}

	public SubscriptionsPkgmSubscription links(final SubscriptionsPkgmSubscriptionLinks links) {
		this.links = links;
		return this;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class SubscriptionsPkgmSubscription {\n");

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
	private static String toIndentedString(final Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
