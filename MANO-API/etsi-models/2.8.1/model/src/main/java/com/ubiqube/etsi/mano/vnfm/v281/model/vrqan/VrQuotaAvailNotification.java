package com.ubiqube.etsi.mano.vnfm.v281.model.vrqan;

import java.time.OffsetDateTime;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.vnfm.v281.model.grant.VimConnectionInfo;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * This type represents a notification which indicates the availability of a
 * quota applicable to the consumer. It shall comply with the provisions defined
 * in table 11.5.2.4-1. Support of this notification is mandatory if the
 * Virtualised Resources Quota Available Notification interface is supported.
 * The notification shall be triggered by the NFVO when a virtualised resource
 * quota applicable to the consumer has been set.
 */
@Schema(description = "This type represents a notification which indicates the availability  of a quota applicable to the consumer. It shall comply with the provisions  defined in table 11.5.2.4-1. Support of this notification is mandatory if  the Virtualised Resources Quota Available Notification interface is supported. The notification shall be triggered by the NFVO when a virtualised resource  quota applicable to the consumer has been set. ")
@Validated

public class VrQuotaAvailNotification {
	@JsonProperty("id")
	private String id = null;

	@JsonProperty("notificationType")
	private String notificationType = null;

	@JsonProperty("subscriptionId")
	private String subscriptionId = null;

	@JsonProperty("timeStamp")
	private OffsetDateTime timeStamp = null;

	@JsonProperty("resourceGroupId")
	private String resourceGroupId = null;

	@JsonProperty("vimConnectionInfo")
	private VimConnectionInfo vimConnectionInfo = null;

	@JsonProperty("resourceProviderId")
	private String resourceProviderId = null;

	@JsonProperty("_links")
	private QuotaAvailLinks _links = null;

	public VrQuotaAvailNotification id(final String id) {
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

	public VrQuotaAvailNotification notificationType(final String notificationType) {
		this.notificationType = notificationType;
		return this;
	}

	/**
	 * Get notificationType
	 *
	 * @return notificationType
	 **/
	@Schema(required = true, description = "")
	@NotNull

	public String getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(final String notificationType) {
		this.notificationType = notificationType;
	}

	public VrQuotaAvailNotification subscriptionId(final String subscriptionId) {
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

	public VrQuotaAvailNotification timeStamp(final OffsetDateTime timeStamp) {
		this.timeStamp = timeStamp;
		return this;
	}

	/**
	 * Get timeStamp
	 *
	 * @return timeStamp
	 **/
	@Schema(required = true, description = "")
	@NotNull

	@Valid
	public OffsetDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(final OffsetDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	public VrQuotaAvailNotification resourceGroupId(final String resourceGroupId) {
		this.resourceGroupId = resourceGroupId;
		return this;
	}

	/**
	 * Get resourceGroupId
	 *
	 * @return resourceGroupId
	 **/
	@Schema(required = true, description = "")
	@NotNull

	public String getResourceGroupId() {
		return resourceGroupId;
	}

	public void setResourceGroupId(final String resourceGroupId) {
		this.resourceGroupId = resourceGroupId;
	}

	public VrQuotaAvailNotification vimConnectionInfo(final VimConnectionInfo vimConnectionInfo) {
		this.vimConnectionInfo = vimConnectionInfo;
		return this;
	}

	/**
	 * Get vimConnectionInfo
	 *
	 * @return vimConnectionInfo
	 **/
	@Schema(description = "")

	@Valid
	public VimConnectionInfo getVimConnectionInfo() {
		return vimConnectionInfo;
	}

	public void setVimConnectionInfo(final VimConnectionInfo vimConnectionInfo) {
		this.vimConnectionInfo = vimConnectionInfo;
	}

	public VrQuotaAvailNotification resourceProviderId(final String resourceProviderId) {
		this.resourceProviderId = resourceProviderId;
		return this;
	}

	/**
	 * Get resourceProviderId
	 *
	 * @return resourceProviderId
	 **/
	@Schema(description = "")

	public String getResourceProviderId() {
		return resourceProviderId;
	}

	public void setResourceProviderId(final String resourceProviderId) {
		this.resourceProviderId = resourceProviderId;
	}

	public VrQuotaAvailNotification _links(final QuotaAvailLinks _links) {
		this._links = _links;
		return this;
	}

	/**
	 * Get _links
	 *
	 * @return _links
	 **/
	@Schema(required = true, description = "")
	@NotNull

	@Valid
	public QuotaAvailLinks getLinks() {
		return _links;
	}

	public void setLinks(final QuotaAvailLinks _links) {
		this._links = _links;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final VrQuotaAvailNotification vrQuotaAvailNotification = (VrQuotaAvailNotification) o;
		return Objects.equals(this.id, vrQuotaAvailNotification.id) &&
				Objects.equals(this.notificationType, vrQuotaAvailNotification.notificationType) &&
				Objects.equals(this.subscriptionId, vrQuotaAvailNotification.subscriptionId) &&
				Objects.equals(this.timeStamp, vrQuotaAvailNotification.timeStamp) &&
				Objects.equals(this.resourceGroupId, vrQuotaAvailNotification.resourceGroupId) &&
				Objects.equals(this.vimConnectionInfo, vrQuotaAvailNotification.vimConnectionInfo) &&
				Objects.equals(this.resourceProviderId, vrQuotaAvailNotification.resourceProviderId) &&
				Objects.equals(this._links, vrQuotaAvailNotification._links);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, notificationType, subscriptionId, timeStamp, resourceGroupId, vimConnectionInfo, resourceProviderId, _links);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VrQuotaAvailNotification {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    notificationType: ").append(toIndentedString(notificationType)).append("\n");
		sb.append("    subscriptionId: ").append(toIndentedString(subscriptionId)).append("\n");
		sb.append("    timeStamp: ").append(toIndentedString(timeStamp)).append("\n");
		sb.append("    resourceGroupId: ").append(toIndentedString(resourceGroupId)).append("\n");
		sb.append("    vimConnectionInfo: ").append(toIndentedString(vimConnectionInfo)).append("\n");
		sb.append("    resourceProviderId: ").append(toIndentedString(resourceProviderId)).append("\n");
		sb.append("    _links: ").append(toIndentedString(_links)).append("\n");
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
