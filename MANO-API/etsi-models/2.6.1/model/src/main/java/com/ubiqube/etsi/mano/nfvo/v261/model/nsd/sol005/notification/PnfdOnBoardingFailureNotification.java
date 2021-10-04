/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005.notification;

import java.time.OffsetDateTime;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.model.ProblemDetails;

import io.swagger.v3.oas.annotations.media.Schema;


/**
 * This type represents a PNFD management notification, which informs the
 * receiver of the failure of on-boarding a PNFD. It shall comply with the
 * provisions defined in Table 5.5.2.14-1. The support of this notification is
 * mandatory. The notification is triggered when the on-boarding of a PNFD
 * fails.
 */
@Schema(description = "This type represents a PNFD management notification, which informs the receiver of the failure of on-boarding a PNFD. It shall comply with the provisions defined in Table 5.5.2.14-1. The support of this notification is mandatory. The notification is triggered when the on-boarding of a PNFD fails. ")
@Validated


public class PnfdOnBoardingFailureNotification {
	@JsonProperty("id")
	private String id = null;

	@JsonProperty("notificationType")
	private String notificationType = null;

	@JsonProperty("subscriptionId")
	private String subscriptionId = null;

	@JsonProperty("timeStamp")
	private OffsetDateTime timeStamp = null;

	@JsonProperty("pnfdInfoId")
	private String pnfdInfoId = null;

	@JsonProperty("pnfdId")
	private String pnfdId = null;

	@JsonProperty("onboardingFailureDetails")
	private ProblemDetails onboardingFailureDetails = null;

	@JsonProperty("_links")
	private PnfdmLinks links = null;

	public PnfdOnBoardingFailureNotification id(final String id) {
		this.id = id;
		return this;
	}

	/**
	 * Get id
	 *
	 * @return id
	 **/
	@Schema(required = true, description = "")
	@NotNull

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public PnfdOnBoardingFailureNotification notificationType(final String notificationType) {
		this.notificationType = notificationType;
		return this;
	}

	/**
	 * Discriminator for the different notification types. Shall be set to
	 * \"PnfdOnboardingFailureNotification\" for this notification type.
	 *
	 * @return notificationType
	 **/
	@Schema(required = true, description = "Discriminator for the different notification types. Shall be set to \"PnfdOnboardingFailureNotification\" for this notification type. ")
	@NotNull

	public String getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(final String notificationType) {
		this.notificationType = notificationType;
	}

	public PnfdOnBoardingFailureNotification subscriptionId(final String subscriptionId) {
		this.subscriptionId = subscriptionId;
		return this;
	}

	/**
	 * Get subscriptionId
	 *
	 * @return subscriptionId
	 **/
	@Schema(required = true, description = "")
	@NotNull

	public String getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(final String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public PnfdOnBoardingFailureNotification timeStamp(final OffsetDateTime timeStamp) {
		this.timeStamp = timeStamp;
		return this;
	}

	/**
	 * Date-time of the generation of the notification.
	 *
	 * @return timeStamp
	 **/
	@Schema(required = true, description = "Date-time of the generation of the notification. ")
	@NotNull

	@Valid

	public OffsetDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(final OffsetDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	public PnfdOnBoardingFailureNotification pnfdInfoId(final String pnfdInfoId) {
		this.pnfdInfoId = pnfdInfoId;
		return this;
	}

	/**
	 * Get pnfdInfoId
	 *
	 * @return pnfdInfoId
	 **/
	@Schema(required = true, description = "")
	@NotNull

	public String getPnfdInfoId() {
		return pnfdInfoId;
	}

	public void setPnfdInfoId(final String pnfdInfoId) {
		this.pnfdInfoId = pnfdInfoId;
	}

	public PnfdOnBoardingFailureNotification pnfdId(final String pnfdId) {
		this.pnfdId = pnfdId;
		return this;
	}

	/**
	 * Get pnfdId
	 *
	 * @return pnfdId
	 **/
	@Schema(description = "")

	public String getPnfdId() {
		return pnfdId;
	}

	public void setPnfdId(final String pnfdId) {
		this.pnfdId = pnfdId;
	}

	public PnfdOnBoardingFailureNotification onboardingFailureDetails(final ProblemDetails onboardingFailureDetails) {
		this.onboardingFailureDetails = onboardingFailureDetails;
		return this;
	}

	/**
	 * Failure details of current onboarding procedure. See clause 6.3 of ETSI GS
	 * NFV-SOL 013 for the details of \"ProblemDetails\" structure.
	 *
	 * @return onboardingFailureDetails
	 **/
	@Schema(required = true, description = "Failure details of current onboarding procedure. See clause 6.3 of ETSI GS NFV-SOL 013 for the details of \"ProblemDetails\" structure. ")
	@NotNull

	@Valid

	public ProblemDetails getOnboardingFailureDetails() {
		return onboardingFailureDetails;
	}

	public void setOnboardingFailureDetails(final ProblemDetails onboardingFailureDetails) {
		this.onboardingFailureDetails = onboardingFailureDetails;
	}

	public PnfdOnBoardingFailureNotification links(final PnfdmLinks links) {
		this.links = links;
		return this;
	}

	/**
	 * Get links
	 *
	 * @return links
	 **/
	@Schema(required = true, description = "")
	@NotNull

	@Valid

	public PnfdmLinks getLinks() {
		return links;
	}

	public void setLinks(final PnfdmLinks links) {
		this.links = links;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final PnfdOnBoardingFailureNotification pnfdOnBoardingFailureNotification = (PnfdOnBoardingFailureNotification) o;
		return Objects.equals(this.id, pnfdOnBoardingFailureNotification.id) &&
				Objects.equals(this.notificationType, pnfdOnBoardingFailureNotification.notificationType) &&
				Objects.equals(this.subscriptionId, pnfdOnBoardingFailureNotification.subscriptionId) &&
				Objects.equals(this.timeStamp, pnfdOnBoardingFailureNotification.timeStamp) &&
				Objects.equals(this.pnfdInfoId, pnfdOnBoardingFailureNotification.pnfdInfoId) &&
				Objects.equals(this.pnfdId, pnfdOnBoardingFailureNotification.pnfdId) &&
				Objects.equals(this.onboardingFailureDetails, pnfdOnBoardingFailureNotification.onboardingFailureDetails) &&
				Objects.equals(this.links, pnfdOnBoardingFailureNotification.links);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, notificationType, subscriptionId, timeStamp, pnfdInfoId, pnfdId, onboardingFailureDetails, links);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class PnfdOnBoardingFailureNotification {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    notificationType: ").append(toIndentedString(notificationType)).append("\n");
		sb.append("    subscriptionId: ").append(toIndentedString(subscriptionId)).append("\n");
		sb.append("    timeStamp: ").append(toIndentedString(timeStamp)).append("\n");
		sb.append("    pnfdInfoId: ").append(toIndentedString(pnfdInfoId)).append("\n");
		sb.append("    pnfdId: ").append(toIndentedString(pnfdId)).append("\n");
		sb.append("    onboardingFailureDetails: ").append(toIndentedString(onboardingFailureDetails)).append("\n");
		sb.append("    links: ").append(toIndentedString(links)).append("\n");
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
