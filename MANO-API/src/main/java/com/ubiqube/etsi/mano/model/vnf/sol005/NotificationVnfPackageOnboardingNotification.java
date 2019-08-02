package com.ubiqube.etsi.mano.model.vnf.sol005;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents a VNF package management notification, which informs the
 * receiver that the on boarding process of a VNF package incomplete and the
 * package is ready for use. A change of the on-boarding state before the VNF
 * package is on-boarded is not reported. It shall comply with the provisions
 * defined in Table 9.5.2.8-1. The support of this notification is mandatory.
 * The notification shall be triggered by the NFVO when the value of the
 * \"onboardingState\" attribute of a new VNF package has changed to
 * \"ONBOARDED\".
 **/
@ApiModel(description = "This type represents a VNF package management notification, which informs the receiver that the on boarding process of a VNF package incomplete and the package is ready for use. A change of the on-boarding state before the VNF package is on-boarded is not reported. It shall comply with the provisions defined in Table 9.5.2.8-1. The support of this notification is mandatory. The notification shall be triggered by the NFVO when the value of the \"onboardingState\" attribute of a new VNF package has changed to \"ONBOARDED\". ")
public class NotificationVnfPackageOnboardingNotification {

	@ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
	/**
	 * An identifier with the intention of being globally unique.
	 **/
	private String id = null;

	@ApiModelProperty(required = true, value = "Discriminator for the different notification types. Shall be set to \"VnfPackageOnboardingNotification\" for this notification type. ")
	/**
	 * Discriminator for the different notification types. Shall be set to
	 * \"VnfPackageOnboardingNotification\" for this notification type.
	 **/
	private String notificationType = null;

	@ApiModelProperty(value = "An identifier with the intention of being globally unique. ")
	/**
	 * An identifier with the intention of being globally unique.
	 **/
	private String subscriptionId = null;

	@ApiModelProperty(required = true, value = "Date-time stamp.  Representation: String formatted according to IETF RFC 3339. ")
	/**
	 * Date-time stamp. Representation: String formatted according to IETF RFC 3339.
	 **/
	private Date timeStamp = null;

	@ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
	/**
	 * An identifier with the intention of being globally unique.
	 **/
	private String vnfPkgId = null;

	@ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
	/**
	 * An identifier with the intention of being globally unique.
	 **/
	private String vnfdId = null;

	@ApiModelProperty(required = true)
	@Valid
	private VnfPackageOnboardingNotificationLinks links = null;

	public NotificationVnfPackageOnboardingNotification() {
		// Nothing.
	}

	public NotificationVnfPackageOnboardingNotification(String _id, String _notificationType, String _subscriptionId, String _vnfPkgId, String _vnfdId, String _hrefSubscription, String _hrefPackage) {
		super();
		this.id = _id;
		this.timeStamp = new Date();
		this.notificationType = _notificationType;
		this.subscriptionId = _subscriptionId;
		this.vnfPkgId = _vnfPkgId;
		this.vnfdId = _vnfdId;

		this.links = new VnfPackageOnboardingNotificationLinks();

		final VnfPackagesVnfPkgInfoLinksSelf subscription = new VnfPackagesVnfPkgInfoLinksSelf();
		subscription.setHref(_hrefSubscription);
		links.setSubscription(subscription);

		final VnfPackagesVnfPkgInfoLinksSelf vnfPackage = new VnfPackagesVnfPkgInfoLinksSelf();
		vnfPackage.setHref(_hrefPackage);
		links.setVnfPackage(vnfPackage);
	}

	/**
	 * An identifier with the intention of being globally unique.
	 *
	 * @return id
	 **/
	@JsonProperty("id")
	@NotNull
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public NotificationVnfPackageOnboardingNotification id(String id) {
		this.id = id;
		return this;
	}

	/**
	 * Discriminator for the different notification types. Shall be set to
	 * \&quot;VnfPackageOnboardingNotification\&quot; for this notification type.
	 *
	 * @return notificationType
	 **/
	@JsonProperty("notificationType")
	@NotNull
	public String getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

	public NotificationVnfPackageOnboardingNotification notificationType(String notificationType) {
		this.notificationType = notificationType;
		return this;
	}

	/**
	 * An identifier with the intention of being globally unique.
	 *
	 * @return subscriptionId
	 **/
	@JsonProperty("subscriptionId")
	public String getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public NotificationVnfPackageOnboardingNotification subscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
		return this;
	}

	/**
	 * Date-time stamp. Representation: String formatted according to IETF RFC 3339.
	 *
	 * @return timeStamp
	 **/
	@JsonProperty("timeStamp")
	@NotNull
	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public NotificationVnfPackageOnboardingNotification timeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
		return this;
	}

	/**
	 * An identifier with the intention of being globally unique.
	 *
	 * @return vnfPkgId
	 **/
	@JsonProperty("vnfPkgId")
	@NotNull
	public String getVnfPkgId() {
		return vnfPkgId;
	}

	public void setVnfPkgId(String vnfPkgId) {
		this.vnfPkgId = vnfPkgId;
	}

	public NotificationVnfPackageOnboardingNotification vnfPkgId(String vnfPkgId) {
		this.vnfPkgId = vnfPkgId;
		return this;
	}

	/**
	 * An identifier with the intention of being globally unique.
	 *
	 * @return vnfdId
	 **/
	@JsonProperty("vnfdId")
	@NotNull
	public String getVnfdId() {
		return vnfdId;
	}

	public void setVnfdId(String vnfdId) {
		this.vnfdId = vnfdId;
	}

	public NotificationVnfPackageOnboardingNotification vnfdId(String vnfdId) {
		this.vnfdId = vnfdId;
		return this;
	}

	/**
	 * Get links
	 *
	 * @return links
	 **/
	@JsonProperty("_links")
	@NotNull
	public VnfPackageOnboardingNotificationLinks getLinks() {
		return links;
	}

	public void setLinks(VnfPackageOnboardingNotificationLinks links) {
		this.links = links;
	}

	public NotificationVnfPackageOnboardingNotification links(VnfPackageOnboardingNotificationLinks links) {
		this.links = links;
		return this;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class URIIsProvidedByTheClientWhenCreatingTheSubscriptionVnfPackageOnboardingNotificationVnfPackageOnboardingNotification {\n");

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
	private static String toIndentedString(Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
