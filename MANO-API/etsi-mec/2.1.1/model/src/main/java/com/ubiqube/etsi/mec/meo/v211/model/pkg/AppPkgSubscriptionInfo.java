package com.ubiqube.etsi.mec.meo.v211.model.pkg;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.model.WebEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * &#x27;The data type represents a subscription to notification of application package management for the onboarding, or operational state change of application package&#x27;
 */
@ApiModel(description = "'The data type represents a subscription to notification of application package management for the onboarding, or operational state change of application package'")
@Validated
public class AppPkgSubscriptionInfo extends WebEntity<AppPkgSubscriptionInfo> {

	@JsonProperty("subscriptionType")
	private AppPkgSubscriptionType subscriptionType = null;

	@JsonProperty("callbackUri")
	private String callbackUri = null;

	@JsonProperty("_links")
	private AppPkgSubscriptionInfoLinks _links = null;

	public AppPkgSubscriptionInfo subscriptionType(final AppPkgSubscriptionType subscriptionType) {
		this.subscriptionType = subscriptionType;
		return this;
	}

	/**
	 * Get subscriptionType
	 *
	 * @return subscriptionType
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	@Valid
	public AppPkgSubscriptionType getSubscriptionType() {
		return subscriptionType;
	}

	public void setSubscriptionType(final AppPkgSubscriptionType subscriptionType) {
		this.subscriptionType = subscriptionType;
	}

	public AppPkgSubscriptionInfo callbackUri(final String callbackUri) {
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

	public AppPkgSubscriptionInfo _links(final AppPkgSubscriptionInfoLinks _links) {
		this._links = _links;
		return this;
	}

	public void setLinks(final AppPkgSubscriptionInfoLinks _links) {
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
		final AppPkgSubscriptionInfo appPkgSubscriptionInfo = (AppPkgSubscriptionInfo) o;
		return Objects.equals(this.getId(), appPkgSubscriptionInfo.getId()) &&
				Objects.equals(this.subscriptionType, appPkgSubscriptionInfo.subscriptionType) &&
				Objects.equals(this.callbackUri, appPkgSubscriptionInfo.callbackUri) &&
				Objects.equals(this._links, appPkgSubscriptionInfo._links);
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), subscriptionType, callbackUri, _links);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class AppPkgSubscriptionInfo {\n");

		sb.append("    id: ").append(toIndentedString(getId())).append("\n");
		sb.append("    subscriptionType: ").append(toIndentedString(subscriptionType)).append("\n");
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
