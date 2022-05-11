package com.ubiqube.etsi.mano.vnfm.v281.model.vrqan;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * This type represents the links to resources that a notification of type
 * \&quot;VrQuotaAvailNotification\&quot; can contain. It shall comply with the
 * provisions defined in table 11.5.3.3-1.
 */
@Schema(description = "This type represents the links to resources that a notification of type \"VrQuotaAvailNotification\"  can contain. It shall comply with the provisions defined in table 11.5.3.3-1. ")
@Validated

public class QuotaAvailLinks {
	@JsonProperty("subscription")
	private NotificationLink subscription = null;

	public QuotaAvailLinks subscription(final NotificationLink subscription) {
		this.subscription = subscription;
		return this;
	}

	/**
	 * Get subscription
	 *
	 * @return subscription
	 **/
	@Schema(required = true, description = "")
	@NotNull

	@Valid
	public NotificationLink getSubscription() {
		return subscription;
	}

	public void setSubscription(final NotificationLink subscription) {
		this.subscription = subscription;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final QuotaAvailLinks quotaAvailLinks = (QuotaAvailLinks) o;
		return Objects.equals(this.subscription, quotaAvailLinks.subscription);
	}

	@Override
	public int hashCode() {
		return Objects.hash(subscription);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class QuotaAvailLinks {\n");

		sb.append("    subscription: ").append(toIndentedString(subscription)).append("\n");
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
