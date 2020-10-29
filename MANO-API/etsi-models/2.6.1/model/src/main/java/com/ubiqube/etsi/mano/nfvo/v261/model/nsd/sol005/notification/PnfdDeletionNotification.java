/**
 * This copy of Woodstox XML processor is licensed under the
 * Apache (Software) License, version 2.0 ("the License").
 * See the License for details about distribution rights, and the
 * specific rights regarding derivate works.
 *
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/
 *
 * A copy is also included in the downloadable source code package
 * containing Woodstox, in file "ASL2.0", under the same directory
 * as this file.
 */
package com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005.notification;

import java.time.OffsetDateTime;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents a PNFD management notification, which informs the
 * receiver of the deletion of an on-boarded PNFD. The notification shall comply
 * with the provisions defined in Table 5.5.2.15-1. The support of this
 * notification is mandatory. The notification is triggered when an on-boarded
 * PNFD is deleted.
 */
@ApiModel(description = "This type represents a PNFD management notification, which informs the receiver of the deletion of an on-boarded PNFD. The notification shall comply with the provisions defined in Table 5.5.2.15-1. The support of this notification is mandatory. The notification is triggered when an on-boarded PNFD is deleted. ")
@Validated


public class PnfdDeletionNotification {
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

	@JsonProperty("_links")
	private PnfdmLinks links = null;

	public PnfdDeletionNotification id(final String id) {
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

	public PnfdDeletionNotification notificationType(final String notificationType) {
		this.notificationType = notificationType;
		return this;
	}

	/**
	 * Discriminator for the different notification types. Shall be set to
	 * \"PnfdDeletionNotification \" for this notification type.
	 * 
	 * @return notificationType
	 **/
	@ApiModelProperty(required = true, value = "Discriminator for the different notification types. Shall be set to \"PnfdDeletionNotification \" for this notification type. ")
	@NotNull

	public String getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(final String notificationType) {
		this.notificationType = notificationType;
	}

	public PnfdDeletionNotification subscriptionId(final String subscriptionId) {
		this.subscriptionId = subscriptionId;
		return this;
	}

	/**
	 * Get subscriptionId
	 * 
	 * @return subscriptionId
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	public String getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(final String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public PnfdDeletionNotification timeStamp(final OffsetDateTime timeStamp) {
		this.timeStamp = timeStamp;
		return this;
	}

	/**
	 * Date-time of the generation of the notification.
	 * 
	 * @return timeStamp
	 **/
	@ApiModelProperty(required = true, value = "Date-time of the generation of the notification. ")
	@NotNull

	@Valid

	public OffsetDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(final OffsetDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	public PnfdDeletionNotification pnfdInfoId(final String pnfdInfoId) {
		this.pnfdInfoId = pnfdInfoId;
		return this;
	}

	/**
	 * Get pnfdInfoId
	 * 
	 * @return pnfdInfoId
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	public String getPnfdInfoId() {
		return pnfdInfoId;
	}

	public void setPnfdInfoId(final String pnfdInfoId) {
		this.pnfdInfoId = pnfdInfoId;
	}

	public PnfdDeletionNotification pnfdId(final String pnfdId) {
		this.pnfdId = pnfdId;
		return this;
	}

	/**
	 * Get pnfdId
	 * 
	 * @return pnfdId
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	public String getPnfdId() {
		return pnfdId;
	}

	public void setPnfdId(final String pnfdId) {
		this.pnfdId = pnfdId;
	}

	public PnfdDeletionNotification links(final PnfdmLinks links) {
		this.links = links;
		return this;
	}

	/**
	 * Get links
	 * 
	 * @return links
	 **/
	@ApiModelProperty(required = true, value = "")
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
		final PnfdDeletionNotification pnfdDeletionNotification = (PnfdDeletionNotification) o;
		return Objects.equals(this.id, pnfdDeletionNotification.id) &&
				Objects.equals(this.notificationType, pnfdDeletionNotification.notificationType) &&
				Objects.equals(this.subscriptionId, pnfdDeletionNotification.subscriptionId) &&
				Objects.equals(this.timeStamp, pnfdDeletionNotification.timeStamp) &&
				Objects.equals(this.pnfdInfoId, pnfdDeletionNotification.pnfdInfoId) &&
				Objects.equals(this.pnfdId, pnfdDeletionNotification.pnfdId) &&
				Objects.equals(this.links, pnfdDeletionNotification.links);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, notificationType, subscriptionId, timeStamp, pnfdInfoId, pnfdId, links);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class PnfdDeletionNotification {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    notificationType: ").append(toIndentedString(notificationType)).append("\n");
		sb.append("    subscriptionId: ").append(toIndentedString(subscriptionId)).append("\n");
		sb.append("    timeStamp: ").append(toIndentedString(timeStamp)).append("\n");
		sb.append("    pnfdInfoId: ").append(toIndentedString(pnfdInfoId)).append("\n");
		sb.append("    pnfdId: ").append(toIndentedString(pnfdId)).append("\n");
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
