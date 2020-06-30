package com.ubiqube.etsi.mano.vnfm.v261.model.indicator;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.common.v261.model.SubscriptionAuthentication;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents a subscription request related to VNF indicator value
 * change notifications.
 */
@ApiModel(description = "This type represents a subscription request related to VNF indicator value change notifications. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-05-01T15:58:19.438+02:00")

public class VnfIndicatorSubscriptionRequest {
	@JsonProperty("filter")
	private VnfIndicatorNotificationsFilter filter = null;

	@JsonProperty("callbackUri")
	private String callbackUri = null;

	@JsonProperty("authentication")
	private SubscriptionAuthentication authentication = null;

	public VnfIndicatorSubscriptionRequest filter(final VnfIndicatorNotificationsFilter filter) {
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

	public VnfIndicatorNotificationsFilter getFilter() {
		return filter;
	}

	public void setFilter(final VnfIndicatorNotificationsFilter filter) {
		this.filter = filter;
	}

	public VnfIndicatorSubscriptionRequest callbackUri(final String callbackUri) {
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

	public VnfIndicatorSubscriptionRequest authentication(final SubscriptionAuthentication authentication) {
		this.authentication = authentication;
		return this;
	}

	/**
	 * Authentication parameters to configure the use of Authorization when sending
	 * notifications corresponding to this subscription, as defined in clause 8.3.4
	 * of ETSI GS NFV-SOL 013. This attribute shall only be present if the
	 * subscriber requires authorization of notifications.
	 *
	 * @return authentication
	 **/
	@ApiModelProperty(value = "Authentication parameters to configure the use of Authorization when sending notifications corresponding to this subscription, as defined in clause 8.3.4 of ETSI GS NFV-SOL 013. This attribute shall only be present if the subscriber requires authorization of notifications. ")

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
		final VnfIndicatorSubscriptionRequest vnfIndicatorSubscriptionRequest = (VnfIndicatorSubscriptionRequest) o;
		return Objects.equals(this.filter, vnfIndicatorSubscriptionRequest.filter) &&
				Objects.equals(this.callbackUri, vnfIndicatorSubscriptionRequest.callbackUri) &&
				Objects.equals(this.authentication, vnfIndicatorSubscriptionRequest.authentication);
	}

	@Override
	public int hashCode() {
		return Objects.hash(filter, callbackUri, authentication);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfIndicatorSubscriptionRequest {\n");

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
