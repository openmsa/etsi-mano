package io.swagger.model;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This type represents a subscription request related to notifications about VNF lifecycle changes.
 **/
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "This type represents a subscription request related to notifications about VNF lifecycle changes. ")

public class LccnSubscriptionRequest {

	private @Valid InlineResponse2002Filter filter = null;
	private @Valid String callbackUri = null;
	private @Valid SubscriptionsAuthentication authentication = null;

	/**
	 **/
	public LccnSubscriptionRequest filter(InlineResponse2002Filter filter) {
		this.filter = filter;
		return this;
	}

	@ApiModelProperty(value = "")
	@JsonProperty("filter")
	public InlineResponse2002Filter getFilter() {
		return filter;
	}

	public void setFilter(InlineResponse2002Filter filter) {
		this.filter = filter;
	}

	/**
	 * String formatted according to IETF RFC 3986.
	 **/
	public LccnSubscriptionRequest callbackUri(String callbackUri) {
		this.callbackUri = callbackUri;
		return this;
	}

	@ApiModelProperty(required = true, value = "String formatted according to IETF RFC 3986. ")
	@JsonProperty("callbackUri")
	@NotNull
	public String getCallbackUri() {
		return callbackUri;
	}

	public void setCallbackUri(String callbackUri) {
		this.callbackUri = callbackUri;
	}

	/**
	 **/
	public LccnSubscriptionRequest authentication(SubscriptionsAuthentication authentication) {
		this.authentication = authentication;
		return this;
	}

	@ApiModelProperty(value = "")
	@JsonProperty("authentication")
	public SubscriptionsAuthentication getAuthentication() {
		return authentication;
	}

	public void setAuthentication(SubscriptionsAuthentication authentication) {
		this.authentication = authentication;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final LccnSubscriptionRequest lccnSubscriptionRequest = (LccnSubscriptionRequest) o;
		return Objects.equals(filter, lccnSubscriptionRequest.filter) &&
				Objects.equals(callbackUri, lccnSubscriptionRequest.callbackUri) &&
				Objects.equals(authentication, lccnSubscriptionRequest.authentication);
	}

	@Override
	public int hashCode() {
		return Objects.hash(filter, callbackUri, authentication);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class LccnSubscriptionRequest {\n");

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
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
