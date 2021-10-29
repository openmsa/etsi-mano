package com.ubiqube.etsi.mano.em.v351.model.vnflcm;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.em.v351.model.SubscriptionAuthentication;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * This type represents a subscription request related to notifications about
 * VNF lifecycle changes.
 */
@Schema(description = "This type represents a subscription request related to notifications about VNF lifecycle changes. ")
@Validated

public class LccnSubscriptionRequest {
	@JsonProperty("filter")
	private LifecycleChangeNotificationsFilter filter = null;

	@JsonProperty("callbackUri")
	private String callbackUri = null;

	@JsonProperty("authentication")
	private SubscriptionAuthentication authentication = null;

	@JsonProperty("verbosity")
	private LcmOpOccNotificationVerbosityType verbosity = null;

	public LccnSubscriptionRequest filter(final LifecycleChangeNotificationsFilter filter) {
		this.filter = filter;
		return this;
	}

	/**
	 * Get filter
	 *
	 * @return filter
	 **/
	@Schema(description = "")

	@Valid
	public LifecycleChangeNotificationsFilter getFilter() {
		return filter;
	}

	public void setFilter(final LifecycleChangeNotificationsFilter filter) {
		this.filter = filter;
	}

	public LccnSubscriptionRequest callbackUri(final String callbackUri) {
		this.callbackUri = callbackUri;
		return this;
	}

	/**
	 * Get callbackUri
	 *
	 * @return callbackUri
	 **/
	@Schema(required = true, description = "")
	@NotNull

	public String getCallbackUri() {
		return callbackUri;
	}

	public void setCallbackUri(final String callbackUri) {
		this.callbackUri = callbackUri;
	}

	public LccnSubscriptionRequest authentication(final SubscriptionAuthentication authentication) {
		this.authentication = authentication;
		return this;
	}

	/**
	 * Get authentication
	 *
	 * @return authentication
	 **/
	@Schema(description = "")

	@Valid
	public SubscriptionAuthentication getAuthentication() {
		return authentication;
	}

	public void setAuthentication(final SubscriptionAuthentication authentication) {
		this.authentication = authentication;
	}

	public LccnSubscriptionRequest verbosity(final LcmOpOccNotificationVerbosityType verbosity) {
		this.verbosity = verbosity;
		return this;
	}

	/**
	 * Get verbosity
	 *
	 * @return verbosity
	 **/
	@Schema(description = "")

	@Valid
	public LcmOpOccNotificationVerbosityType getVerbosity() {
		return verbosity;
	}

	public void setVerbosity(final LcmOpOccNotificationVerbosityType verbosity) {
		this.verbosity = verbosity;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final LccnSubscriptionRequest lccnSubscriptionRequest = (LccnSubscriptionRequest) o;
		return Objects.equals(this.filter, lccnSubscriptionRequest.filter) &&
				Objects.equals(this.callbackUri, lccnSubscriptionRequest.callbackUri) &&
				Objects.equals(this.authentication, lccnSubscriptionRequest.authentication) &&
				Objects.equals(this.verbosity, lccnSubscriptionRequest.verbosity);
	}

	@Override
	public int hashCode() {
		return Objects.hash(filter, callbackUri, authentication, verbosity);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class LccnSubscriptionRequest {\n");

		sb.append("    filter: ").append(toIndentedString(filter)).append("\n");
		sb.append("    callbackUri: ").append(toIndentedString(callbackUri)).append("\n");
		sb.append("    authentication: ").append(toIndentedString(authentication)).append("\n");
		sb.append("    verbosity: ").append(toIndentedString(verbosity)).append("\n");
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
