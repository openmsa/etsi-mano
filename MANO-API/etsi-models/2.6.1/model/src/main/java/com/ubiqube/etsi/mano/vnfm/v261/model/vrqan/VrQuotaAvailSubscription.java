package com.ubiqube.etsi.mano.vnfm.v261.model.vrqan;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents a subscription related to notifications related to the availability of the virtualised resources quotas.
 */
@ApiModel(description = "This type represents a subscription related to notifications related to the availability of the virtualised resources quotas. ")
@Validated

public class VrQuotaAvailSubscription {
	@JsonProperty("id")
	private String id = null;

	@JsonProperty("filter")
	private VrQuotaAvailNotificationsFilter filter = null;

	@JsonProperty("callbackUri")
	private String callbackUri = null;

	@JsonProperty("_links")
	private VrQuotaAvailSubscriptionLinks _links = null;

	public VrQuotaAvailSubscription id(final String id) {
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

	public VrQuotaAvailSubscription filter(final VrQuotaAvailNotificationsFilter filter) {
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
	public VrQuotaAvailNotificationsFilter getFilter() {
		return filter;
	}

	public void setFilter(final VrQuotaAvailNotificationsFilter filter) {
		this.filter = filter;
	}

	public VrQuotaAvailSubscription callbackUri(final String callbackUri) {
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

	public VrQuotaAvailSubscription _links(final VrQuotaAvailSubscriptionLinks _links) {
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
	public VrQuotaAvailSubscriptionLinks getLinks() {
		return _links;
	}

	public void setLinks(final VrQuotaAvailSubscriptionLinks _links) {
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
		final VrQuotaAvailSubscription vrQuotaAvailSubscription = (VrQuotaAvailSubscription) o;
		return Objects.equals(this.id, vrQuotaAvailSubscription.id) &&
				Objects.equals(this.filter, vrQuotaAvailSubscription.filter) &&
				Objects.equals(this.callbackUri, vrQuotaAvailSubscription.callbackUri) &&
				Objects.equals(this._links, vrQuotaAvailSubscription._links);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, filter, callbackUri, _links);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VrQuotaAvailSubscription {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    filter: ").append(toIndentedString(filter)).append("\n");
		sb.append("    callbackUri: ").append(toIndentedString(callbackUri)).append("\n");
		sb.append("    _links: ").append(toIndentedString(_links)).append("\n");
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
