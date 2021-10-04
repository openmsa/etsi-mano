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

package com.ubiqube.etsi.mano.common.v261.model.vnf;

import java.time.OffsetDateTime;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;


/**
 * This type represents a VNF package management notification, which informs the
 * receiver that the on boarding process of a VNF package incomplete and the
 * package is ready for use. A change of the on-boarding state before the VNF
 * package is on-boarded is not reported. It shall comply with the provisions
 * defined in Table 9.5.2.8-1. The support of this notification is mandatory.
 * The notification shall be triggered by the NFVO when the value of the
 * \&quot;onboardingState\&quot; attribute of a new VNF package has changed to
 * \&quot;ONBOARDED\&quot;.
 */
@Schema(description = "This type represents a VNF package management notification, which informs the receiver that the on boarding process of a VNF package incomplete and the package is ready for use. A change of the on-boarding state before the VNF package is on-boarded is not reported. It shall comply with the provisions defined in Table 9.5.2.8-1. The support of this notification is mandatory. The notification shall be triggered by the NFVO when the value of the \"onboardingState\" attribute of a new VNF package has changed to \"ONBOARDED\". ")
@Validated
public class VnfPackageOnboardingNotification {
	@JsonProperty("id")
	private String id = null;

	@JsonProperty("notificationType")
	private String notificationType = null;

	@JsonProperty("subscriptionId")
	private String subscriptionId = null;

	@JsonProperty("timeStamp")
	private OffsetDateTime timeStamp = null;

	@JsonProperty("vnfPkgId")
	private String vnfPkgId = null;

	@JsonProperty("vnfdId")
	private String vnfdId = null;

	@JsonProperty("_links")
	private PkgmLinks links = null;

	public VnfPackageOnboardingNotification id(final String id) {
		this.id = id;
		return this;
	}

	/**
	 * Identifier of this notification. If a notification is sent multiple times due
	 * to multiple subscriptions, the \"id\" attribute of all these notifications
	 * shall have the same value.
	 *
	 * @return id
	 **/
	@Schema(required = true, description = "Identifier of this notification. If a notification is sent multiple times due to multiple subscriptions, the \"id\" attribute of all these notifications shall have the same value. ")
	@NotNull

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public VnfPackageOnboardingNotification notificationType(final String notificationType) {
		this.notificationType = notificationType;
		return this;
	}

	/**
	 * Discriminator for the different notification types. Shall be set to
	 * \"VnfPackageOnboardingNotification\" for this notification type.
	 *
	 * @return notificationType
	 **/
	@Schema(required = true, description = "Discriminator for the different notification types. Shall be set to \"VnfPackageOnboardingNotification\" for this notification type. ")
	@NotNull

	public String getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(final String notificationType) {
		this.notificationType = notificationType;
	}

	public VnfPackageOnboardingNotification subscriptionId(final String subscriptionId) {
		this.subscriptionId = subscriptionId;
		return this;
	}

	/**
	 * Identifier of the subscription that this notification relates to.
	 *
	 * @return subscriptionId
	 **/
	@Schema(required = true, description = "Identifier of the subscription that this notification relates to. ")
	@NotNull

	public String getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(final String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public VnfPackageOnboardingNotification timeStamp(final OffsetDateTime timeStamp) {
		this.timeStamp = timeStamp;
		return this;
	}

	/**
	 * Date and time of the generation of the notification.
	 *
	 * @return timeStamp
	 **/
	@Schema(required = true, description = "Date and time of the generation of the notification. ")
	@NotNull

	@Valid

	public OffsetDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(final OffsetDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	public VnfPackageOnboardingNotification vnfPkgId(final String vnfPkgId) {
		this.vnfPkgId = vnfPkgId;
		return this;
	}

	/**
	 * Identifier of the VNF package. This identifier is allocated by the NFVO. Its
	 * value is the same as the value of the \"id\" attribute of the related
	 * \"Individual VNF package\" resource.
	 *
	 * @return vnfPkgId
	 **/
	@Schema(required = true, description = "Identifier of the VNF package. This identifier is allocated by the NFVO. Its value is the same as the value of the \"id\" attribute of the related \"Individual VNF package\" resource. ")
	@NotNull

	public String getVnfPkgId() {
		return vnfPkgId;
	}

	public void setVnfPkgId(final String vnfPkgId) {
		this.vnfPkgId = vnfPkgId;
	}

	public VnfPackageOnboardingNotification vnfdId(final String vnfdId) {
		this.vnfdId = vnfdId;
		return this;
	}

	/**
	 * This identifier, which is managed by the VNF provider, identifies the VNF
	 * package and the VNFD in a globally unique way. It is copied from the VNFD of
	 * the on-boarded VNF package.
	 *
	 * @return vnfdId
	 **/
	@Schema(required = true, description = "This identifier, which is managed by the VNF provider, identifies the VNF package and the VNFD in a globally unique way. It is copied from the VNFD of the on-boarded VNF package. ")
	@NotNull

	public String getVnfdId() {
		return vnfdId;
	}

	public void setVnfdId(final String vnfdId) {
		this.vnfdId = vnfdId;
	}

	public VnfPackageOnboardingNotification links(final PkgmLinks links) {
		this.links = links;
		return this;
	}

	/**
	 * Links to resources related to this notification.
	 *
	 * @return links
	 **/
	@Schema(required = true, description = "Links to resources related to this notification. ")
	@NotNull

	@Valid

	public PkgmLinks getLinks() {
		return links;
	}

	public void setLinks(final PkgmLinks links) {
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
		final VnfPackageOnboardingNotification vnfPackageOnboardingNotification = (VnfPackageOnboardingNotification) o;
		return Objects.equals(this.id, vnfPackageOnboardingNotification.id) &&
				Objects.equals(this.notificationType, vnfPackageOnboardingNotification.notificationType) &&
				Objects.equals(this.subscriptionId, vnfPackageOnboardingNotification.subscriptionId) &&
				Objects.equals(this.timeStamp, vnfPackageOnboardingNotification.timeStamp) &&
				Objects.equals(this.vnfPkgId, vnfPackageOnboardingNotification.vnfPkgId) &&
				Objects.equals(this.vnfdId, vnfPackageOnboardingNotification.vnfdId) &&
				Objects.equals(this.links, vnfPackageOnboardingNotification.links);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, notificationType, subscriptionId, timeStamp, vnfPkgId, vnfdId, links);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfPackageOnboardingNotification {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    notificationType: ").append(toIndentedString(notificationType)).append("\n");
		sb.append("    subscriptionId: ").append(toIndentedString(subscriptionId)).append("\n");
		sb.append("    timeStamp: ").append(toIndentedString(timeStamp)).append("\n");
		sb.append("    vnfPkgId: ").append(toIndentedString(vnfPkgId)).append("\n");
		sb.append("    vnfdId: ").append(toIndentedString(vnfdId)).append("\n");
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
