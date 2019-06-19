package com.ubiqube.etsi.mano.model.vnf.sol005;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents the links to resources that a VNF package management
 * notification can contain.
 **/
@ApiModel(description = "This type represents the links to resources that a VNF package management notification can contain.   ")
public class VnfPackageOnboardingNotificationLinks {

	@ApiModelProperty(required = true, value = "")
	@Valid
	private VnfPackagesVnfPkgInfoLinksSelf vnfPackage = null;

	@ApiModelProperty(required = true, value = "")
	@Valid
	private VnfPackagesVnfPkgInfoLinksSelf subscription = null;

	public VnfPackageOnboardingNotificationLinks() {
		// Nothing.
	}

	public VnfPackageOnboardingNotificationLinks(String _hrefVnfPackage, String _hrefSubscription) {
		vnfPackage = new VnfPackagesVnfPkgInfoLinksSelf();
		vnfPackage.setHref(_hrefVnfPackage);
		subscription = new VnfPackagesVnfPkgInfoLinksSelf();
		subscription.setHref(_hrefSubscription);
	}

	/**
	 * Get vnfPackage
	 * 
	 * @return vnfPackage
	 **/
	@JsonProperty("vnfPackage")
	@NotNull
	public VnfPackagesVnfPkgInfoLinksSelf getVnfPackage() {
		return vnfPackage;
	}

	public void setVnfPackage(VnfPackagesVnfPkgInfoLinksSelf vnfPackage) {
		this.vnfPackage = vnfPackage;
	}

	public VnfPackageOnboardingNotificationLinks vnfPackage(VnfPackagesVnfPkgInfoLinksSelf vnfPackage) {
		this.vnfPackage = vnfPackage;
		return this;
	}

	/**
	 * Get subscription
	 * 
	 * @return subscription
	 **/
	@JsonProperty("subscription")
	@NotNull
	public VnfPackagesVnfPkgInfoLinksSelf getSubscription() {
		return subscription;
	}

	public void setSubscription(VnfPackagesVnfPkgInfoLinksSelf subscription) {
		this.subscription = subscription;
	}

	public VnfPackageOnboardingNotificationLinks subscription(VnfPackagesVnfPkgInfoLinksSelf subscription) {
		this.subscription = subscription;
		return this;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class URIIsProvidedByTheClientWhenCreatingTheSubscriptionVnfPackageOnboardingNotificationVnfPackageOnboardingNotificationLinks {\n");

		sb.append("    vnfPackage: ").append(toIndentedString(vnfPackage)).append("\n");
		sb.append("    subscription: ").append(toIndentedString(subscription)).append("\n");
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
