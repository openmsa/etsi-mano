package com.ubiqube.etsi.mano.model.vnf.sol005;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class VnfPackageChangeNotification {

	@ApiModelProperty(value = "")
	@Valid
	private VnfPackageChangeNotificationVnfPackageChangeNotification vnfPackageChangeNotification = null;

	public VnfPackageChangeNotification() {
		// Nothing.
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
