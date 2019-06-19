package com.ubiqube.etsi.mano.model.vnf.sol005;

import java.util.List;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents a subscription filter related to notifications related
 * to VNF package management. At a particular nesting level in the filter
 * structure, the following applies: All attributes shall match in order for the
 * filter to match (logical \"and\" between different filter attributes). If an
 * attribute is an array, the attribute shall match if at least one of the
 * values in the array matches (logical \"or\" between the values of one filter
 * attribute).
 **/
@ApiModel(description = "This type represents a subscription filter related to notifications related to VNF package management. At a particular nesting level in the filter structure, the following applies: All attributes shall match in order for the filter to match (logical \"and\" between different filter attributes). If an attribute is an array, the attribute shall match if at least one of the values in the array matches (logical \"or\" between the values of one filter attribute). ")
public class SubscriptionsPkgmSubscriptionFilter {

	@XmlType(name = "NotificationTypesEnum")
	@XmlEnum(String.class)
	public enum NotificationTypesEnum {
		@XmlEnumValue("VnfPackageOnboardingNotification")
		VNFPACKAGEONBOARDINGNOTIFICATION(String.valueOf("VnfPackageOnboardingNotification")), @XmlEnumValue("VnfPackageChangeNotification")
		VNFPACKAGECHANGENOTIFICATION(String.valueOf("VnfPackageChangeNotification"));

		private final String value;

		NotificationTypesEnum(String v) {
			value = v;
		}

		public String value() {
			return value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static NotificationTypesEnum fromValue(String v) {
			for (final NotificationTypesEnum b : NotificationTypesEnum.values()) {
				if (String.valueOf(b.value).equals(v)) {
					return b;
				}
			}
			return null;
		}
	}

	@ApiModelProperty(value = "Match particular notification types. Permitted values: - VnfPackageOnboardingNotification - VnfPackageChangeNotification ")
	/**
	 * Match particular notification types. Permitted values: -
	 * VnfPackageOnboardingNotification - VnfPackageChangeNotification
	 **/
	private NotificationTypesEnum notificationTypes = null;

	@ApiModelProperty(value = "If present, match VNF packages that contain VNF products from certain providers. ")
	@Valid
	/**
	 * If present, match VNF packages that contain VNF products from certain
	 * providers.
	 **/
	private List<SubscriptionsPkgmSubscriptionFilterVnfProductsFromProviders> vnfProductsFromProviders = null;

	/**
	 * Match particular notification types. Permitted values: -
	 * VnfPackageOnboardingNotification - VnfPackageChangeNotification
	 *
	 * @return notificationTypes
	 **/
	@JsonProperty("notificationTypes")
	public String getNotificationTypes() {
		if (notificationTypes == null) {
			return null;
		}
		return notificationTypes.value();
	}

	@JsonIgnore
	public NotificationTypesEnum getNotificationTypesRaw() {
		if (notificationTypes == null) {
			return null;
		}
		return notificationTypes;
	}

	public void setNotificationTypes(NotificationTypesEnum _notificationTypes) {
		this.notificationTypes = _notificationTypes;
	}

	public SubscriptionsPkgmSubscriptionFilter notificationTypes(NotificationTypesEnum _notificationTypes) {
		this.notificationTypes = _notificationTypes;
		return this;
	}

	/**
	 * If present, match VNF packages that contain VNF products from certain
	 * providers.
	 *
	 * @return vnfProductsFromProviders
	 **/
	@JsonProperty("vnfProductsFromProviders")
	public List<SubscriptionsPkgmSubscriptionFilterVnfProductsFromProviders> getVnfProductsFromProviders() {
		return vnfProductsFromProviders;
	}

	public void setVnfProductsFromProviders(List<SubscriptionsPkgmSubscriptionFilterVnfProductsFromProviders> _vnfProductsFromProviders) {
		this.vnfProductsFromProviders = _vnfProductsFromProviders;
	}

	public SubscriptionsPkgmSubscriptionFilter vnfProductsFromProviders(List<SubscriptionsPkgmSubscriptionFilterVnfProductsFromProviders> _vnfProductsFromProviders) {
		this.vnfProductsFromProviders = _vnfProductsFromProviders;
		return this;
	}

	public SubscriptionsPkgmSubscriptionFilter addVnfProductsFromProvidersItem(SubscriptionsPkgmSubscriptionFilterVnfProductsFromProviders vnfProductsFromProvidersItem) {
		this.vnfProductsFromProviders.add(vnfProductsFromProvidersItem);
		return this;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class SubscriptionsPkgmSubscriptionFilter {\n");

		sb.append("    notificationTypes: ").append(toIndentedString(notificationTypes)).append("\n");
		sb.append("    vnfProductsFromProviders: ").append(toIndentedString(vnfProductsFromProviders)).append("\n");
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
