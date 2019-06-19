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

	public SubscriptionsPkgmSubscription(String _callbackUri, String _id, String _href, SubscriptionsPkgmSubscriptionFilter _filter) {
		this.callbackUri = _callbackUri;
		this.id = _id;
		this.links = new SubscriptionsPkgmSubscriptionLinks();
		final VnfPackagesVnfPkgInfoLinksSelf self = new VnfPackagesVnfPkgInfoLinksSelf();
		self.href(_href);
		links.setSelf(self);
		this.filter = _filter;
	}

	public SubscriptionsPkgmSubscription(String _callbackUri, String _id, String _href) {
		this(_callbackUri, _id, _href, new SubscriptionsPkgmSubscriptionFilter());
	}

	public SubscriptionsPkgmSubscription(SubscriptionsPkgmSubscriptionRequest _subscriptionsPkgmSubscriptionRequest, String _id, String _href, SubscriptionsPkgmSubscriptionFilter _filter) {
		this(_subscriptionsPkgmSubscriptionRequest.getCallbackUri(), _id, _href, _filter);
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

	public void setId(String id) {
		this.id = id;
	}

	public SubscriptionsPkgmSubscription id(String id) {
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

	public void setFilter(SubscriptionsPkgmSubscriptionFilter filter) {
		this.filter = filter;
	}

	public SubscriptionsPkgmSubscription filter(SubscriptionsPkgmSubscriptionFilter filter) {
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

	public void setCallbackUri(String callbackUri) {
		this.callbackUri = callbackUri;
	}

	public SubscriptionsPkgmSubscription callbackUri(String callbackUri) {
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

	public void setLinks(SubscriptionsPkgmSubscriptionLinks links) {
		this.links = links;
	}

	public SubscriptionsPkgmSubscription links(SubscriptionsPkgmSubscriptionLinks links) {
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
	private static String toIndentedString(Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
