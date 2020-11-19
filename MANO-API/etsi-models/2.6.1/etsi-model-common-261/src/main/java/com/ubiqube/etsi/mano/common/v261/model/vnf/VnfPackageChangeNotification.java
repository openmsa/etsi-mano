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

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents a VNF package management notification, which informs the
 * receiver of a change of the status in an on-boarded VNF package. Only changes
 * in the \&quot;operationalState\&quot; attribute of an on-boarded VNF package
 * and the deletion of the VNF package will be reported. Change in the
 * \&quot;usageState\&quot; and \&quot;onboardingState\&quot; attributes are not
 * reported. The notification shall comply with the provisions defined in Table
 * 9.5.2.9-1. The support of this notification is mandatory. The notification
 * shall be triggered by the NFVO when there is a change in the status of an
 * onboarded VNF package, as follows. - The \&quot;operationalState\&quot;
 * attribute of a VNF package has changed, and the \&quot;onboardingState\&quot;
 * attribute of the package has the value \&quot;ONBOARDED\&quot;. - The
 * on-boarded VNF package has been deleted.
 */
@ApiModel(description = "This type represents a VNF package management notification, which informs the receiver of a change of the status in an on-boarded VNF package. Only changes in the \"operationalState\" attribute of an on-boarded VNF package and the deletion of the VNF package will be reported. Change in the \"usageState\" and \"onboardingState\" attributes are not reported. The notification shall comply with the provisions defined in Table 9.5.2.9-1. The support of this notification is mandatory. The notification shall be triggered by the NFVO when there is a change in the status of an onboarded VNF package, as follows.   - The \"operationalState\" attribute of a VNF package has changed, and the \"onboardingState\" attribute of the     package has the value \"ONBOARDED\".   - The on-boarded VNF package has been deleted. ")
@Validated
public class VnfPackageChangeNotification {
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

	@JsonProperty("changeType")
	private PackageChangeType changeType = null;

	@JsonProperty("operationalState")
	private PackageOperationalStateType operationalState = null;

	@JsonProperty("_links")
	private PkgmLinks links = null;

	public VnfPackageChangeNotification id(final String id) {
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
	@ApiModelProperty(required = true, value = "Identifier of this notification. If a notification is sent multiple times due to multiple subscriptions, the \"id\" attribute of all these notifications shall have the same value. ")
	@NotNull

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public VnfPackageChangeNotification notificationType(final String notificationType) {
		this.notificationType = notificationType;
		return this;
	}

	/**
	 * Discriminator for the different notification types. Shall be set to
	 * \"VnfPackageChangeNotification\" for this notification type.
	 *
	 * @return notificationType
	 **/
	@ApiModelProperty(required = true, value = "Discriminator for the different notification types. Shall be set to \"VnfPackageChangeNotification\" for this notification type. ")
	@NotNull

	public String getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(final String notificationType) {
		this.notificationType = notificationType;
	}

	public VnfPackageChangeNotification subscriptionId(final String subscriptionId) {
		this.subscriptionId = subscriptionId;
		return this;
	}

	/**
	 * Identifier of the subscription that this notification relates to.
	 *
	 * @return subscriptionId
	 **/
	@ApiModelProperty(required = true, value = "Identifier of the subscription that this notification relates to. ")
	@NotNull

	public String getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(final String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public VnfPackageChangeNotification timeStamp(final OffsetDateTime timeStamp) {
		this.timeStamp = timeStamp;
		return this;
	}

	/**
	 * Date and time of the generation of the notification.
	 *
	 * @return timeStamp
	 **/
	@ApiModelProperty(required = true, value = "Date and time of the generation of the notification. ")
	@NotNull

	@Valid

	public OffsetDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(final OffsetDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	public VnfPackageChangeNotification vnfPkgId(final String vnfPkgId) {
		this.vnfPkgId = vnfPkgId;
		return this;
	}

	/**
	 * Identifier of the on-boarded VNF package. This identifier is allocated by the
	 * NFVO. Its value is the same as the value of the \"id\" attribute of the
	 * related \"Individual VNF package\" resource.
	 *
	 * @return vnfPkgId
	 **/
	@ApiModelProperty(required = true, value = "Identifier of the on-boarded VNF package. This identifier is allocated by the NFVO. Its value is the same as the value of the \"id\" attribute of the related \"Individual VNF package\" resource. ")
	@NotNull

	public String getVnfPkgId() {
		return vnfPkgId;
	}

	public void setVnfPkgId(final String vnfPkgId) {
		this.vnfPkgId = vnfPkgId;
	}

	public VnfPackageChangeNotification vnfdId(final String vnfdId) {
		this.vnfdId = vnfdId;
		return this;
	}

	/**
	 * Identifier of the VNFD contained in the VNF package, which also identifies
	 * the VNF package. This identifier is allocated by the VNF provider and copied
	 * from the VNFD.
	 *
	 * @return vnfdId
	 **/
	@ApiModelProperty(required = true, value = "Identifier of the VNFD contained in the VNF package, which also identifies the VNF package. This identifier is allocated by the VNF provider and copied from the VNFD. ")
	@NotNull

	public String getVnfdId() {
		return vnfdId;
	}

	public void setVnfdId(final String vnfdId) {
		this.vnfdId = vnfdId;
	}

	public VnfPackageChangeNotification changeType(final PackageChangeType changeType) {
		this.changeType = changeType;
		return this;
	}

	/**
	 * The type of change of the VNF package.
	 *
	 * @return changeType
	 **/
	@ApiModelProperty(required = true, value = "The type of change of the VNF package. ")
	@NotNull

	@Valid

	public PackageChangeType getChangeType() {
		return changeType;
	}

	public void setChangeType(final PackageChangeType changeType) {
		this.changeType = changeType;
	}

	public VnfPackageChangeNotification operationalState(final PackageOperationalStateType operationalState) {
		this.operationalState = operationalState;
		return this;
	}

	/**
	 * New operational state of the VNF package. Only present when changeType is
	 * OP_STATE_CHANGE.
	 *
	 * @return operationalState
	 **/
	@ApiModelProperty(value = "New operational state of the VNF package. Only present when changeType is OP_STATE_CHANGE. ")

	@Valid

	public PackageOperationalStateType getOperationalState() {
		return operationalState;
	}

	public void setOperationalState(final PackageOperationalStateType operationalState) {
		this.operationalState = operationalState;
	}

	public VnfPackageChangeNotification links(final PkgmLinks links) {
		this.links = links;
		return this;
	}

	/**
	 * Links to resources related to this notification.
	 *
	 * @return links
	 **/
	@ApiModelProperty(required = true, value = "Links to resources related to this notification. ")
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
		final VnfPackageChangeNotification vnfPackageChangeNotification = (VnfPackageChangeNotification) o;
		return Objects.equals(this.id, vnfPackageChangeNotification.id) &&
				Objects.equals(this.notificationType, vnfPackageChangeNotification.notificationType) &&
				Objects.equals(this.subscriptionId, vnfPackageChangeNotification.subscriptionId) &&
				Objects.equals(this.timeStamp, vnfPackageChangeNotification.timeStamp) &&
				Objects.equals(this.vnfPkgId, vnfPackageChangeNotification.vnfPkgId) &&
				Objects.equals(this.vnfdId, vnfPackageChangeNotification.vnfdId) &&
				Objects.equals(this.changeType, vnfPackageChangeNotification.changeType) &&
				Objects.equals(this.operationalState, vnfPackageChangeNotification.operationalState) &&
				Objects.equals(this.links, vnfPackageChangeNotification.links);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, notificationType, subscriptionId, timeStamp, vnfPkgId, vnfdId, changeType, operationalState, links);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfPackageChangeNotification {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    notificationType: ").append(toIndentedString(notificationType)).append("\n");
		sb.append("    subscriptionId: ").append(toIndentedString(subscriptionId)).append("\n");
		sb.append("    timeStamp: ").append(toIndentedString(timeStamp)).append("\n");
		sb.append("    vnfPkgId: ").append(toIndentedString(vnfPkgId)).append("\n");
		sb.append("    vnfdId: ").append(toIndentedString(vnfdId)).append("\n");
		sb.append("    changeType: ").append(toIndentedString(changeType)).append("\n");
		sb.append("    operationalState: ").append(toIndentedString(operationalState)).append("\n");
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
