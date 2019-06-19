package com.ubiqube.etsi.mano.model.vnf.sol005;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.annotations.ApiModelProperty;

@JsonPropertyOrder({ "id", "vnfdId", "vnfProvider" })
public class VnfPkgInfo {

	@ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
	/**
	 * An identifier with the intention of being globally unique.
	 **/
	private String id = null;

	@ApiModelProperty(value = "An identifier with the intention of being globally unique. ")
	/**
	 * An identifier with the intention of being globally unique.
	 **/
	private String vnfdId = null;

	@ApiModelProperty(value = "Provider of the VNF package and the VNFD. This information is copied from the VNFD.  It shall be present after the VNF package content has been on-boarded and absent otherwise. ")
	/**
	 * Provider of the VNF package and the VNFD. This information is copied from the
	 * VNFD. It shall be present after the VNF package content has been on-boarded
	 * and absent otherwise.
	 **/
	private String vnfProvider = null;

	@ApiModelProperty(value = "Name to identify the VNF product.Invariant for the VNF product lifetime.  This information is copied from the VNFD. It shall be present after the VNF package content has been on-boarded and absent otherwise. ")
	/**
	 * Name to identify the VNF product.Invariant for the VNF product lifetime. This
	 * information is copied from the VNFD. It shall be present after the VNF
	 * package content has been on-boarded and absent otherwise.
	 **/
	private String vnfProductName = null;

	@ApiModelProperty(value = "Software version of the VNF. This is changed when there is any change to the software included in the VNF package. This information is copied from the VNFD. It shall be present after the VNF package content has been on-boarded and absent otherwise. ")
	/**
	 * Software version of the VNF. This is changed when there is any change to the
	 * software included in the VNF package. This information is copied from the
	 * VNFD. It shall be present after the VNF package content has been on-boarded
	 * and absent otherwise.
	 **/
	private String vnfSoftwareVersion = null;

	@ApiModelProperty(value = "Software version of the VNF. This is changed when there is any change to the software included in the VNF package. This information is copied from the VNFD. It shall be present after the VNF package content has been on-boarded and absent otherwise. ")
	/**
	 * Software version of the VNF. This is changed when there is any change to the
	 * software included in the VNF package. This information is copied from the
	 * VNFD. It shall be present after the VNF package content has been on-boarded
	 * and absent otherwise.
	 **/
	private String vnfdVersion = null;

	@ApiModelProperty(value = "")
	@Valid
	private VnfPackagesVnfPkgInfoChecksum checksum = null;

	@ApiModelProperty(value = "Information about VNF package artifacts that are software images. This attribute shall not be present before the VNF package content is on-boarded. Otherwise, this attribute shall be present unless it has been requested to be excluded per attribute selector. ")
	@Valid
	/**
	 * Information about VNF package artifacts that are software images. This
	 * attribute shall not be present before the VNF package content is on-boarded.
	 * Otherwise, this attribute shall be present unless it has been requested to be
	 * excluded per attribute selector.
	 **/
	private List<VnfPackagesVnfPkgInfoSoftwareImages> softwareImages = null;

	@ApiModelProperty(value = "Information about VNF package artifacts contained in the VNF package that are not software images. This attribute shall not be present before the VNF package content is on-boarded. Otherwise, this attribute shall be present if the VNF package contains additional artifacts. ")
	@Valid
	/**
	 * Information about VNF package artifacts contained in the VNF package that are
	 * not software images. This attribute shall not be present before the VNF
	 * package content is on-boarded. Otherwise, this attribute shall be present if
	 * the VNF package contains additional artifacts.
	 **/
	private List<VnfPackagesVnfPkgInfoAdditionalArtifacts> additionalArtifacts = null;

	@XmlType(name = "OnboardingStateEnum")
	@XmlEnum(String.class)
	public enum OnboardingStateEnum {

		@XmlEnumValue("CREATED")
		CREATED(String.valueOf("CREATED")), @XmlEnumValue("UPLOADING")
		UPLOADING(String.valueOf("UPLOADING")), @XmlEnumValue("PROCESSING")
		PROCESSING(String.valueOf("PROCESSING")), @XmlEnumValue("ONBOARDED")
		ONBOARDED(String.valueOf("ONBOARDED"));

		private final String value;

		OnboardingStateEnum(String v) {
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
		public static OnboardingStateEnum fromValue(String v) {
			for (final OnboardingStateEnum b : OnboardingStateEnum.values()) {
				if (String.valueOf(b.value).equals(v)) {
					return b;
				}
			}
			return null;
		}
	}

	@ApiModelProperty(required = true, value = "The enumeration PackageOnboardingStateType shall comply with the provisions defined in Table 9.5.4.3-1. Permitted values: - CREATED: The VNF package resource has been created. - UPLOADING: The associated VNF package content is being uploaded. - PROCESSING: The associated VNF package content is being processed, e.g. validation. - ONBOARDED: The associated VNF package content is successfully on-boarded. ")
	/**
	 * The enumeration PackageOnboardingStateType shall comply with the provisions
	 * defined in Table 9.5.4.3-1. Permitted values: - CREATED: The VNF package
	 * resource has been created. - UPLOADING: The associated VNF package content is
	 * being uploaded. - PROCESSING: The associated VNF package content is being
	 * processed, e.g. validation. - ONBOARDED: The associated VNF package content
	 * is successfully on-boarded.
	 **/
	private OnboardingStateEnum onboardingState = null;

	@XmlType(name = "OperationalStateEnum")
	@XmlEnum(String.class)
	public enum OperationalStateEnum {

		@XmlEnumValue("ENABLED")
		ENABLED(String.valueOf("ENABLED")), @XmlEnumValue("DISABLED")
		DISABLED(String.valueOf("DISABLED"));

		private final String value;

		OperationalStateEnum(String v) {
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
		public static OperationalStateEnum fromValue(String v) {
			for (final OperationalStateEnum b : OperationalStateEnum.values()) {
				if (String.valueOf(b.value).equals(v)) {
					return b;
				}
			}
			return null;
		}
	}

	@ApiModelProperty(required = true, value = "\"The enumeration PackageOperationalStateType shall  comply with the provisions defined in Table 9.5.4.4-1.\" Acceptable values are: -ENABLED - The VNF package is enabled, i.e. it can be used for instantiation of new VNF instances. -DISABLED - The VNF package is disabled, i.e. it cannot be used for further VNF instantiation requests (unless and until the VNF package is re-enabled). ")
	/**
	 * \"The enumeration PackageOperationalStateType shall comply with the
	 * provisions defined in Table 9.5.4.4-1.\" Acceptable values are: -ENABLED -
	 * The VNF package is enabled, i.e. it can be used for instantiation of new VNF
	 * instances. -DISABLED - The VNF package is disabled, i.e. it cannot be used
	 * for further VNF instantiation requests (unless and until the VNF package is
	 * re-enabled).
	 **/
	private OperationalStateEnum operationalState = null;

	@XmlType(name = "UsageStateEnum")
	@XmlEnum(String.class)
	public enum UsageStateEnum {

		@XmlEnumValue("IN_USE")
		IN_USE(String.valueOf("IN_USE")), @XmlEnumValue("NOT_IN_USE")
		NOT_IN_USE(String.valueOf("NOT_IN_USE"));

		private final String value;

		UsageStateEnum(String v) {
			value = v;
		}

		public String value() {
			return value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		public static UsageStateEnum fromValue(String v) {
			for (final UsageStateEnum b : UsageStateEnum.values()) {
				if (String.valueOf(b.value).equals(v)) {
					return b;
				}
			}
			return null;
		}
	}

	@ApiModelProperty(required = true, value = "\"The enumeration PackageUsageStateType shall comply with the provisions. Acceptable values are: -IN_USE - VNF instances instantiated from this VNF package exist. -NOT_IN_USE - No existing VNF instance is instantiated from this VNF package\"       ")
	/**
	 * \"The enumeration PackageUsageStateType shall comply with the provisions.
	 * Acceptable values are: -IN_USE - VNF instances instantiated from this VNF
	 * package exist. -NOT_IN_USE - No existing VNF instance is instantiated from
	 * this VNF package\"
	 **/
	private UsageStateEnum usageState = null;

	@ApiModelProperty(value = "This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  ")
	/**
	 * This type represents a list of key-value pairs. The order of the pairs in the
	 * list is not significant. In JSON, a set of key- value pairs is represented as
	 * an object. It shall comply with the provisions defined in clause 4 of IETF
	 * RFC 7159.
	 **/
	private Object userDefinedData = null;

	@ApiModelProperty(value = "")
	@Valid
	private VnfPackagesVnfPkgInfoLinks links = null;

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

	public VnfPkgInfo id(String id) {
		this.id = id;
		return this;
	}

	/**
	 * An identifier with the intention of being globally unique.
	 *
	 * @return vnfdId
	 **/
	@JsonProperty("vnfdId")
	public String getVnfdId() {
		return vnfdId;
	}

	public void setVnfdId(String vnfdId) {
		this.vnfdId = vnfdId;
	}

	public VnfPkgInfo vnfdId(String vnfdId) {
		this.vnfdId = vnfdId;
		return this;
	}

	/**
	 * Provider of the VNF package and the VNFD. This information is copied from the
	 * VNFD. It shall be present after the VNF package content has been on-boarded
	 * and absent otherwise.
	 *
	 * @return vnfProvider
	 **/
	@JsonProperty("vnfProvider")
	public String getVnfProvider() {
		return vnfProvider;
	}

	public void setVnfProvider(String vnfProvider) {
		this.vnfProvider = vnfProvider;
	}

	public VnfPkgInfo vnfProvider(String vnfProvider) {
		this.vnfProvider = vnfProvider;
		return this;
	}

	/**
	 * Name to identify the VNF product.Invariant for the VNF product lifetime. This
	 * information is copied from the VNFD. It shall be present after the VNF
	 * package content has been on-boarded and absent otherwise.
	 *
	 * @return vnfProductName
	 **/
	@JsonProperty("vnfProductName")
	public String getVnfProductName() {
		return vnfProductName;
	}

	public void setVnfProductName(String vnfProductName) {
		this.vnfProductName = vnfProductName;
	}

	public VnfPkgInfo vnfProductName(String vnfProductName) {
		this.vnfProductName = vnfProductName;
		return this;
	}

	/**
	 * Software version of the VNF. This is changed when there is any change to the
	 * software included in the VNF package. This information is copied from the
	 * VNFD. It shall be present after the VNF package content has been on-boarded
	 * and absent otherwise.
	 *
	 * @return vnfSoftwareVersion
	 **/
	@JsonProperty("vnfSoftwareVersion")
	public String getVnfSoftwareVersion() {
		return vnfSoftwareVersion;
	}

	public void setVnfSoftwareVersion(String vnfSoftwareVersion) {
		this.vnfSoftwareVersion = vnfSoftwareVersion;
	}

	public VnfPkgInfo vnfSoftwareVersion(String vnfSoftwareVersion) {
		this.vnfSoftwareVersion = vnfSoftwareVersion;
		return this;
	}

	/**
	 * Software version of the VNF. This is changed when there is any change to the
	 * software included in the VNF package. This information is copied from the
	 * VNFD. It shall be present after the VNF package content has been on-boarded
	 * and absent otherwise.
	 *
	 * @return vnfdVersion
	 **/
	@JsonProperty("vnfdVersion")
	public String getVnfdVersion() {
		return vnfdVersion;
	}

	public void setVnfdVersion(String vnfdVersion) {
		this.vnfdVersion = vnfdVersion;
	}

	public VnfPkgInfo vnfdVersion(String vnfdVersion) {
		this.vnfdVersion = vnfdVersion;
		return this;
	}

	/**
	 * Get checksum
	 *
	 * @return checksum
	 **/
	@JsonProperty("checksum")
	public VnfPackagesVnfPkgInfoChecksum getChecksum() {
		return checksum;
	}

	public void setChecksum(VnfPackagesVnfPkgInfoChecksum checksum) {
		this.checksum = checksum;
	}

	public VnfPkgInfo checksum(VnfPackagesVnfPkgInfoChecksum checksum) {
		this.checksum = checksum;
		return this;
	}

	/**
	 * Information about VNF package artifacts that are software images. This
	 * attribute shall not be present before the VNF package content is on-boarded.
	 * Otherwise, this attribute shall be present unless it has been requested to be
	 * excluded per attribute selector.
	 *
	 * @return softwareImages
	 **/
	@JsonProperty("softwareImages")
	public List<VnfPackagesVnfPkgInfoSoftwareImages> getSoftwareImages() {
		return softwareImages;
	}

	public void setSoftwareImages(List<VnfPackagesVnfPkgInfoSoftwareImages> softwareImages) {
		this.softwareImages = softwareImages;
	}

	public VnfPkgInfo softwareImages(List<VnfPackagesVnfPkgInfoSoftwareImages> softwareImages) {
		this.softwareImages = softwareImages;
		return this;
	}

	public VnfPkgInfo addSoftwareImagesItem(VnfPackagesVnfPkgInfoSoftwareImages softwareImagesItem) {
		this.softwareImages.add(softwareImagesItem);
		return this;
	}

	/**
	 * Information about VNF package artifacts contained in the VNF package that are
	 * not software images. This attribute shall not be present before the VNF
	 * package content is on-boarded. Otherwise, this attribute shall be present if
	 * the VNF package contains additional artifacts.
	 *
	 * @return additionalArtifacts
	 **/
	@JsonProperty("additionalArtifacts")
	public List<VnfPackagesVnfPkgInfoAdditionalArtifacts> getAdditionalArtifacts() {
		return additionalArtifacts;
	}

	public void setAdditionalArtifacts(List<VnfPackagesVnfPkgInfoAdditionalArtifacts> additionalArtifacts) {
		this.additionalArtifacts = additionalArtifacts;
	}

	public VnfPkgInfo additionalArtifacts(List<VnfPackagesVnfPkgInfoAdditionalArtifacts> additionalArtifacts) {
		this.additionalArtifacts = additionalArtifacts;
		return this;
	}

	public VnfPkgInfo addAdditionalArtifactsItem(VnfPackagesVnfPkgInfoAdditionalArtifacts additionalArtifactsItem) {
		this.additionalArtifacts.add(additionalArtifactsItem);
		return this;
	}

	/**
	 * The enumeration PackageOnboardingStateType shall comply with the provisions
	 * defined in Table 9.5.4.3-1. Permitted values: - CREATED: The VNF package
	 * resource has been created. - UPLOADING: The associated VNF package content is
	 * being uploaded. - PROCESSING: The associated VNF package content is being
	 * processed, e.g. validation. - ONBOARDED: The associated VNF package content
	 * is successfully on-boarded.
	 *
	 * @return onboardingState
	 **/
	@JsonProperty("onboardingState")
	@NotNull
	public String getOnboardingState() {
		if (onboardingState == null) {
			return null;
		}
		return onboardingState.value();
	}

	public void setOnboardingState(OnboardingStateEnum onboardingState) {
		this.onboardingState = onboardingState;
	}

	public VnfPkgInfo onboardingState(OnboardingStateEnum onboardingState) {
		this.onboardingState = onboardingState;
		return this;
	}

	/**
	 * \&quot;The enumeration PackageOperationalStateType shall comply with the
	 * provisions defined in Table 9.5.4.4-1.\&quot; Acceptable values are: -ENABLED
	 * - The VNF package is enabled, i.e. it can be used for instantiation of new
	 * VNF instances. -DISABLED - The VNF package is disabled, i.e. it cannot be
	 * used for further VNF instantiation requests (unless and until the VNF package
	 * is re-enabled).
	 *
	 * @return operationalState
	 **/
	@JsonProperty("operationalState")
	@NotNull
	public String getOperationalState() {
		if (operationalState == null) {
			return null;
		}
		return operationalState.value();
	}

	public void setOperationalState(OperationalStateEnum operationalState) {
		this.operationalState = operationalState;
	}

	public VnfPkgInfo operationalState(OperationalStateEnum operationalState) {
		this.operationalState = operationalState;
		return this;
	}

	/**
	 * \&quot;The enumeration PackageUsageStateType shall comply with the
	 * provisions. Acceptable values are: -IN_USE - VNF instances instantiated from
	 * this VNF package exist. -NOT_IN_USE - No existing VNF instance is
	 * instantiated from this VNF package\&quot;
	 *
	 * @return usageState
	 **/
	@JsonProperty("usageState")
	@NotNull
	public String getUsageState() {
		if (usageState == null) {
			return null;
		}
		return usageState.value();
	}

	public void setUsageState(UsageStateEnum usageState) {
		this.usageState = usageState;
	}

	public VnfPkgInfo usageState(UsageStateEnum usageState) {
		this.usageState = usageState;
		return this;
	}

	/**
	 * This type represents a list of key-value pairs. The order of the pairs in the
	 * list is not significant. In JSON, a set of key- value pairs is represented as
	 * an object. It shall comply with the provisions defined in clause 4 of IETF
	 * RFC 7159.
	 *
	 * @return userDefinedData
	 **/
	@JsonProperty("userDefinedData")
	public Object getUserDefinedData() {
		return userDefinedData;
	}

	public void setUserDefinedData(Object userDefinedData) {
		this.userDefinedData = userDefinedData;
	}

	public VnfPkgInfo userDefinedData(Object userDefinedData) {
		this.userDefinedData = userDefinedData;
		return this;
	}

	/**
	 * Get links
	 *
	 * @return links
	 **/
	@JsonProperty("_links")
	public VnfPackagesVnfPkgInfoLinks getLinks() {
		return links;
	}

	public void setLinks(VnfPackagesVnfPkgInfoLinks links) {
		this.links = links;
	}

	public VnfPkgInfo links(VnfPackagesVnfPkgInfoLinks links) {
		this.links = links;
		return this;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfPkgInfo {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    vnfdId: ").append(toIndentedString(vnfdId)).append("\n");
		sb.append("    vnfProvider: ").append(toIndentedString(vnfProvider)).append("\n");
		sb.append("    vnfProductName: ").append(toIndentedString(vnfProductName)).append("\n");
		sb.append("    vnfSoftwareVersion: ").append(toIndentedString(vnfSoftwareVersion)).append("\n");
		sb.append("    vnfdVersion: ").append(toIndentedString(vnfdVersion)).append("\n");
		sb.append("    checksum: ").append(toIndentedString(checksum)).append("\n");
		sb.append("    softwareImages: ").append(toIndentedString(softwareImages)).append("\n");
		sb.append("    additionalArtifacts: ").append(toIndentedString(additionalArtifacts)).append("\n");
		sb.append("    onboardingState: ").append(toIndentedString(onboardingState)).append("\n");
		sb.append("    operationalState: ").append(toIndentedString(operationalState)).append("\n");
		sb.append("    usageState: ").append(toIndentedString(usageState)).append("\n");
		sb.append("    userDefinedData: ").append(toIndentedString(userDefinedData)).append("\n");
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
