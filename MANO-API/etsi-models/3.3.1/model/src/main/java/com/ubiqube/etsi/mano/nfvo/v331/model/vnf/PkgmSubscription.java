package com.ubiqube.etsi.mano.nfvo.v331.model.vnf;

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
	private PkgmSubscriptionLinks _links = null;

	public PkgmSubscription id(final String id) {
		this.id = id;
		return this;
	}

	/**
	 * Get id
	 *
	 * @return id
	 **/
	@ApiModelProperty(required = true, value = "")
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
	 * Get filter
	 *
	 * @return filter
	 **/
	@ApiModelProperty(value = "")

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
	 * Get callbackUri
	 *
	 * @return callbackUri
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	public String getCallbackUri() {
		return callbackUri;
	}

	public void setCallbackUri(final String callbackUri) {
		this.callbackUri = callbackUri;
	}

	public PkgmSubscription _links(final PkgmSubscriptionLinks _links) {
		this._links = _links;
		return this;
	}

	/**
	 * Get _links
	 *
	 * @return _links
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	@Valid
	public PkgmSubscriptionLinks getLinks() {
		return _links;
	}

	public void setLinks(final PkgmSubscriptionLinks _links) {
		this._links = _links;
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
				Objects.equals(this._links, pkgmSubscription._links);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, filter, callbackUri, _links);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class PkgmSubscription {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    filter: ").append(toIndentedString(filter)).append("\n");
		sb.append("    callbackUri: ").append(toIndentedString(callbackUri)).append("\n");
		sb.append("    _links: ").append(toIndentedString(_links)).append("\n");
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
