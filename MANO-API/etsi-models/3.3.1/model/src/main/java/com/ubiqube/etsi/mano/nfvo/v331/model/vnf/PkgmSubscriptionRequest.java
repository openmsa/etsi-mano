package com.ubiqube.etsi.mano.nfvo.v331.model.vnf;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents a subscription request related to VNF package management
 * notifications about VNF package on boarding or changes.
 */
@ApiModel(description = "This type represents a subscription request related to VNF package management  notifications about VNF package on boarding or changes. ")
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

	public PkgmSubscriptionRequest callbackUri(final String callbackUri) {
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

	public PkgmSubscriptionRequest authentication(final SubscriptionAuthentication authentication) {
		this.authentication = authentication;
		return this;
	}

	/**
	 * Get authentication
	 *
	 * @return authentication
	 **/
	@ApiModelProperty(value = "")

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
