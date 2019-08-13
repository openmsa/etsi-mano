package com.ubiqube.etsi.mano.model.vnf.sol005;

import java.util.Date;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackageChangeNotificationVnfPackageChangeNotification.ChangeTypeEnum;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackageChangeNotificationVnfPackageChangeNotification.OperationalStateEnum;

import io.swagger.annotations.ApiModelProperty;

public class VnfPackageChangeNotification {

	@ApiModelProperty(value = "")
	@Valid
	private VnfPackageChangeNotificationVnfPackageChangeNotification vnfPackageChangeNotification = null;

	public VnfPackageChangeNotification() {
		// Nothing.
	}

	public VnfPackageChangeNotification(final String _id, final String _vnfPkgId, final String _vnfdId, final String _subscriptionId, final String _hrefVnfPackage, final String _hrefSubscription) {
		vnfPackageChangeNotification = new VnfPackageChangeNotificationVnfPackageChangeNotification();
		vnfPackageChangeNotification.setChangeType(ChangeTypeEnum.OP_STATE_CHANGE);
		vnfPackageChangeNotification.setId(_id);
		final VnfPackageOnboardingNotificationLinks links = new VnfPackageOnboardingNotificationLinks(_hrefVnfPackage, _hrefSubscription);
		vnfPackageChangeNotification.setLinks(links);
		vnfPackageChangeNotification.setNotificationType("VnfPackageChangeNotification");
		vnfPackageChangeNotification.setOperationalState(OperationalStateEnum.ENABLED);
		vnfPackageChangeNotification.setSubscriptionId(_subscriptionId);
		vnfPackageChangeNotification.setTimeStamp(new Date());
		vnfPackageChangeNotification.setVnfdId(_vnfdId);
		vnfPackageChangeNotification.setVnfPkgId(_vnfPkgId);

	}

	/**
	 * Get vnfPackageChangeNotification
	 *
	 * @return vnfPackageChangeNotification
	 **/
	@JsonProperty("VnfPackageChangeNotification")
	public VnfPackageChangeNotificationVnfPackageChangeNotification getVnfPackageChangeNotification() {
		return vnfPackageChangeNotification;
	}

	public void setVnfPackageChangeNotification(final VnfPackageChangeNotificationVnfPackageChangeNotification vnfPackageChangeNotification) {
		this.vnfPackageChangeNotification = vnfPackageChangeNotification;
	}

	public VnfPackageChangeNotification vnfPackageChangeNotification(final VnfPackageChangeNotificationVnfPackageChangeNotification vnfPackageChangeNotification) {
		this.vnfPackageChangeNotification = vnfPackageChangeNotification;
		return this;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfPackageChangeNotification {\n");

		sb.append("    vnfPackageChangeNotification: ").append(toIndentedString(vnfPackageChangeNotification)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private static String toIndentedString(final Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
