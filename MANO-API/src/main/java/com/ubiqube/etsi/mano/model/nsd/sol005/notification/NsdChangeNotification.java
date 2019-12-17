package com.ubiqube.etsi.mano.model.nsd.sol005.notification;

import java.time.OffsetDateTime;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsdOperationalStateType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents an NSD management notification, which informs the
 * receiver of a change of the \&quot;nsdOperationalState\&quot; attribute of an
 * on-boarded NSD. Changes in the value of the \&quot;nsdUsageState\&quot; and
 * \&quot;nsdOnboardingState\&quot; attributes are not reported. The
 * notification shall comply with the provisions defined in Table 5.5.2.11-1.
 * The support of this notification is mandatory. The notification shall be
 * triggered by the NFVO when the value of the \&quot;nsdOperationalState\&quot;
 * attribute has changed, and the \&quot;nsdOperationalState\&quot; attribute
 * has the value \&quot;ONBOARDED\&quot;.
 */
@ApiModel(description = "This type represents an NSD management notification, which informs the receiver of a change of the \"nsdOperationalState\" attribute of an on-boarded NSD. Changes in the value of the \"nsdUsageState\" and \"nsdOnboardingState\" attributes are not reported. The notification shall comply with the provisions defined in Table 5.5.2.11-1. The support of this notification is mandatory. The notification shall be triggered by the NFVO when the value of the \"nsdOperationalState\" attribute has changed, and the \"nsdOperationalState\" attribute has the value \"ONBOARDED\". ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-11-25T17:15:58.583+01:00")

public class NsdChangeNotification {
	@JsonProperty("id")
	private String id = null;

	@JsonProperty("notificationType")
	private String notificationType = null;

	@JsonProperty("subscriptionId")
	private String subscriptionId = null;

	@JsonProperty("timeStamp")
	private OffsetDateTime timeStamp = null;

	@JsonProperty("nsdInfoId")
	private String nsdInfoId = null;

	@JsonProperty("nsdId")
	private String nsdId = null;

	@JsonProperty("nsdOperationalState")
	private NsdOperationalStateType nsdOperationalState = null;

	@JsonProperty("_links")
	private NsdmLinks links = null;

	public NsdChangeNotification id(final String id) {
		this.id = id;
		return this;
	}

	/**
	 * Get id
	 *
	 * @return id
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public NsdChangeNotification notificationType(final String notificationType) {
		this.notificationType = notificationType;
		return this;
	}

	/**
	 * Discriminator for the different notification types. Shall be set to
	 * \"NsdChangeNotification\" for this notification type.
	 *
	 * @return notificationType
	 **/
	@ApiModelProperty(required = true, value = "Discriminator for the different notification types. Shall be set to \"NsdChangeNotification\" for this notification type. ")
	@NotNull

	public String getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(final String notificationType) {
		this.notificationType = notificationType;
	}

	public NsdChangeNotification subscriptionId(final String subscriptionId) {
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

	public NsdChangeNotification timeStamp(final OffsetDateTime timeStamp) {
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

	public NsdChangeNotification nsdInfoId(final String nsdInfoId) {
		this.nsdInfoId = nsdInfoId;
		return this;
	}

	/**
	 * Get nsdInfoId
	 *
	 * @return nsdInfoId
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	public String getNsdInfoId() {
		return nsdInfoId;
	}

	public void setNsdInfoId(final String nsdInfoId) {
		this.nsdInfoId = nsdInfoId;
	}

	public NsdChangeNotification nsdId(final String nsdId) {
		this.nsdId = nsdId;
		return this;
	}

	/**
	 * Get nsdId
	 *
	 * @return nsdId
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	public String getNsdId() {
		return nsdId;
	}

	public void setNsdId(final String nsdId) {
		this.nsdId = nsdId;
	}

	public NsdChangeNotification nsdOperationalState(final NsdOperationalStateType nsdOperationalState) {
		this.nsdOperationalState = nsdOperationalState;
		return this;
	}

	/**
	 * Get nsdOperationalState
	 *
	 * @return nsdOperationalState
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	@Valid

	public NsdOperationalStateType getNsdOperationalState() {
		return nsdOperationalState;
	}

	public void setNsdOperationalState(final NsdOperationalStateType nsdOperationalState) {
		this.nsdOperationalState = nsdOperationalState;
	}

	public NsdChangeNotification links(final NsdmLinks links) {
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

	public NsdmLinks getLinks() {
		return links;
	}

	public void setLinks(final NsdmLinks links) {
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
		final NsdChangeNotification nsdChangeNotification = (NsdChangeNotification) o;
		return Objects.equals(this.id, nsdChangeNotification.id) &&
				Objects.equals(this.notificationType, nsdChangeNotification.notificationType) &&
				Objects.equals(this.subscriptionId, nsdChangeNotification.subscriptionId) &&
				Objects.equals(this.timeStamp, nsdChangeNotification.timeStamp) &&
				Objects.equals(this.nsdInfoId, nsdChangeNotification.nsdInfoId) &&
				Objects.equals(this.nsdId, nsdChangeNotification.nsdId) &&
				Objects.equals(this.nsdOperationalState, nsdChangeNotification.nsdOperationalState) &&
				Objects.equals(this.links, nsdChangeNotification.links);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, notificationType, subscriptionId, timeStamp, nsdInfoId, nsdId, nsdOperationalState, links);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class NsdChangeNotification {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    notificationType: ").append(toIndentedString(notificationType)).append("\n");
		sb.append("    subscriptionId: ").append(toIndentedString(subscriptionId)).append("\n");
		sb.append("    timeStamp: ").append(toIndentedString(timeStamp)).append("\n");
		sb.append("    nsdInfoId: ").append(toIndentedString(nsdInfoId)).append("\n");
		sb.append("    nsdId: ").append(toIndentedString(nsdId)).append("\n");
		sb.append("    nsdOperationalState: ").append(toIndentedString(nsdOperationalState)).append("\n");
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
