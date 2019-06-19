package com.ubiqube.etsi.mano.model.vnf.sol005;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;

public class VnfPackagesVnfPkgInfo {

	private @Valid String id = null;
	private @Valid String vnfdId = null;
	private @Valid String vnfProvider = null;
	private @Valid String vnfProductName = null;
	private @Valid String vnfSoftwareVersion = null;
	private @Valid String vnfdVersion = null;
	private @Valid VnfPackagesVnfPkgInfoChecksum checksum = null;
	private @Valid List<VnfPackagesVnfPkgInfoSoftwareImages> softwareImages = new ArrayList<VnfPackagesVnfPkgInfoSoftwareImages>();
	private @Valid List<VnfPackagesVnfPkgInfoAdditionalArtifacts> additionalArtifacts = new ArrayList<VnfPackagesVnfPkgInfoAdditionalArtifacts>();

	public enum OnboardingStateEnum {

		CREATED(String.valueOf("CREATED")), UPLOADING(String.valueOf("UPLOADING")), PROCESSING(String.valueOf("PROCESSING")), ONBOARDED(String.valueOf("ONBOARDED"));

		private final String value;

		OnboardingStateEnum(String v) {
			value = v;
		}

		public String value() {
			return value;
		}

		@Override
		@JsonValue
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

	private @Valid OnboardingStateEnum onboardingState = null;

	public enum OperationalStateEnum {

		ENABLED(String.valueOf("ENABLED")), DISABLED(String.valueOf("DISABLED"));

		private final String value;

		OperationalStateEnum(String v) {
			value = v;
		}

		public String value() {
			return value;
		}

		@Override
		@JsonValue
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

	private @Valid OperationalStateEnum operationalState = null;

	public enum UsageStateEnum {

		IN_USE(String.valueOf("IN_USE")), NOT_IN_USE(String.valueOf("NOT_IN_USE"));

		private final String value;

		UsageStateEnum(String v) {
			value = v;
		}

		public String value() {
			return value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static UsageStateEnum fromValue(String v) {
			for (final UsageStateEnum b : UsageStateEnum.values()) {
				if (String.valueOf(b.value).equals(v)) {
					return b;
				}
			}
			return null;
		}
	}

	private @Valid UsageStateEnum usageState = null;
	private @Valid Object userDefinedData = null;
	private @Valid VnfPackagesVnfPkgInfoLinks links = null;

	/**
	 * An identifier with the intention of being globally unique.
	 **/
	public VnfPackagesVnfPkgInfo id(String id) {
		this.id = id;
		return this;
	}

	@ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
	@JsonProperty("id")
	@NotNull
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * An identifier with the intention of being globally unique.
	 **/
	public VnfPackagesVnfPkgInfo vnfdId(String vnfdId) {
		this.vnfdId = vnfdId;
		return this;
	}

	@ApiModelProperty(value = "An identifier with the intention of being globally unique. ")
	@JsonProperty("vnfdId")
	public String getVnfdId() {
		return vnfdId;
	}

	public void setVnfdId(String vnfdId) {
		this.vnfdId = vnfdId;
	}

	/**
	 * Provider of the VNF package and the VNFD. This information is copied from the
	 * VNFD. It shall be present after the VNF package content has been on-boarded
	 * and absent otherwise.
	 **/
	public VnfPackagesVnfPkgInfo vnfProvider(String vnfProvider) {
		this.vnfProvider = vnfProvider;
		return this;
	}

	@ApiModelProperty(value = "Provider of the VNF package and the VNFD. This information is copied from the VNFD.  It shall be present after the VNF package content has been on-boarded and absent otherwise. ")
	@JsonProperty("vnfProvider")
	public String getVnfProvider() {
		return vnfProvider;
	}

	public void setVnfProvider(String vnfProvider) {
		this.vnfProvider = vnfProvider;
	}

	/**
	 * Name to identify the VNF product.Invariant for the VNF product lifetime. This
	 * information is copied from the VNFD. It shall be present after the VNF
	 * package content has been on-boarded and absent otherwise.
	 **/
	public VnfPackagesVnfPkgInfo vnfProductName(String vnfProductName) {
		this.vnfProductName = vnfProductName;
		return this;
	}

	@ApiModelProperty(value = "Name to identify the VNF product.Invariant for the VNF product lifetime.  This information is copied from the VNFD. It shall be present after the VNF package content has been on-boarded and absent otherwise. ")
	@JsonProperty("vnfProductName")
	public String getVnfProductName() {
		return vnfProductName;
	}

	public void setVnfProductName(String vnfProductName) {
		this.vnfProductName = vnfProductName;
	}

	/**
	 * Software version of the VNF. This is changed when there is any change to the
	 * software included in the VNF package. This information is copied from the
	 * VNFD. It shall be present after the VNF package content has been on-boarded
	 * and absent otherwise.
	 **/
	public VnfPackagesVnfPkgInfo vnfSoftwareVersion(String vnfSoftwareVersion) {
		this.vnfSoftwareVersion = vnfSoftwareVersion;
		return this;
	}

	@ApiModelProperty(value = "Software version of the VNF. This is changed when there is any change to the software included in the VNF package. This information is copied from the VNFD. It shall be present after the VNF package content has been on-boarded and absent otherwise. ")
	@JsonProperty("vnfSoftwareVersion")
	public String getVnfSoftwareVersion() {
		return vnfSoftwareVersion;
	}

	public void setVnfSoftwareVersion(String vnfSoftwareVersion) {
		this.vnfSoftwareVersion = vnfSoftwareVersion;
	}

	/**
	 * Software version of the VNF. This is changed when there is any change to the
	 * software included in the VNF package. This information is copied from the
	 * VNFD. It shall be present after the VNF package content has been on-boarded
	 * and absent otherwise.
	 **/
	public VnfPackagesVnfPkgInfo vnfdVersion(String vnfdVersion) {
		this.vnfdVersion = vnfdVersion;
		return this;
	}

	@ApiModelProperty(value = "Software version of the VNF. This is changed when there is any change to the software included in the VNF package. This information is copied from the VNFD. It shall be present after the VNF package content has been on-boarded and absent otherwise. ")
	@JsonProperty("vnfdVersion")
	public String getVnfdVersion() {
		return vnfdVersion;
	}

	public void setVnfdVersion(String vnfdVersion) {
		this.vnfdVersion = vnfdVersion;
	}

	/**
	 **/
	public VnfPackagesVnfPkgInfo checksum(VnfPackagesVnfPkgInfoChecksum checksum) {
		this.checksum = checksum;
		return this;
	}

	@ApiModelProperty(value = "")
	@JsonProperty("checksum")
	public VnfPackagesVnfPkgInfoChecksum getChecksum() {
		return checksum;
	}

	public void setChecksum(VnfPackagesVnfPkgInfoChecksum checksum) {
		this.checksum = checksum;
	}

	/**
	 * Information about VNF package artifacts that are software images. This
	 * attribute shall not be present before the VNF package content is on-boarded.
	 * Otherwise, this attribute shall be present unless it has been requested to be
	 * excluded per attribute selector.
	 **/
	public VnfPackagesVnfPkgInfo softwareImages(List<VnfPackagesVnfPkgInfoSoftwareImages> softwareImages) {
		this.softwareImages = softwareImages;
		return this;
	}

	@ApiModelProperty(value = "Information about VNF package artifacts that are software images. This attribute shall not be present before the VNF package content is on-boarded. Otherwise, this attribute shall be present unless it has been requested to be excluded per attribute selector. ")
	@JsonProperty("softwareImages")
	public List<VnfPackagesVnfPkgInfoSoftwareImages> getSoftwareImages() {
		return softwareImages;
	}

	public void setSoftwareImages(List<VnfPackagesVnfPkgInfoSoftwareImages> softwareImages) {
		this.softwareImages = softwareImages;
	}

	/**
	 * Information about VNF package artifacts contained in the VNF package that are
	 * not software images. This attribute shall not be present before the VNF
	 * package content is on-boarded. Otherwise, this attribute shall be present if
	 * the VNF package contains additional artifacts.
	 **/
	public VnfPackagesVnfPkgInfo additionalArtifacts(List<VnfPackagesVnfPkgInfoAdditionalArtifacts> additionalArtifacts) {
		this.additionalArtifacts = additionalArtifacts;
		return this;
	}

	@ApiModelProperty(value = "Information about VNF package artifacts contained in the VNF package that are not software images. This attribute shall not be present before the VNF package content is on-boarded. Otherwise, this attribute shall be present if the VNF package contains additional artifacts. ")
	@JsonProperty("additionalArtifacts")
	public List<VnfPackagesVnfPkgInfoAdditionalArtifacts> getAdditionalArtifacts() {
		return additionalArtifacts;
	}

	public void setAdditionalArtifacts(List<VnfPackagesVnfPkgInfoAdditionalArtifacts> additionalArtifacts) {
		this.additionalArtifacts = additionalArtifacts;
	}

	/**
	 * The enumeration PackageOnboardingStateType shall comply with the provisions
	 * defined in Table 9.5.4.3-1. Permitted values: - CREATED: The VNF package
	 * resource has been created. - UPLOADING: The associated VNF package content is
	 * being uploaded. - PROCESSING: The associated VNF package content is being
	 * processed, e.g. validation. - ONBOARDED: The associated VNF package content
	 * is successfully on-boarded.
	 **/
	public VnfPackagesVnfPkgInfo onboardingState(OnboardingStateEnum onboardingState) {
		this.onboardingState = onboardingState;
		return this;
	}

	@ApiModelProperty(required = true, value = "The enumeration PackageOnboardingStateType shall comply with the provisions defined in Table 9.5.4.3-1. Permitted values: - CREATED: The VNF package resource has been created. - UPLOADING: The associated VNF package content is being uploaded. - PROCESSING: The associated VNF package content is being processed, e.g. validation. - ONBOARDED: The associated VNF package content is successfully on-boarded. ")
	@JsonProperty("onboardingState")
	@NotNull
	public OnboardingStateEnum getOnboardingState() {
		return onboardingState;
	}

	public void setOnboardingState(OnboardingStateEnum onboardingState) {
		this.onboardingState = onboardingState;
	}

	/**
	 * \&quot;The enumeration PackageOperationalStateType shall comply with the
	 * provisions defined in Table 9.5.4.4-1.\&quot; Acceptable values are: -ENABLED
	 * - The VNF package is enabled, i.e. it can be used for instantiation of new
	 * VNF instances. -DISABLED - The VNF package is disabled, i.e. it cannot be
	 * used for further VNF instantiation requests (unless and until the VNF package
	 * is re-enabled).
	 **/
	public VnfPackagesVnfPkgInfo operationalState(OperationalStateEnum operationalState) {
		this.operationalState = operationalState;
		return this;
	}

	@ApiModelProperty(required = true, value = "\"The enumeration PackageOperationalStateType shall  comply with the provisions defined in Table 9.5.4.4-1.\" Acceptable values are: -ENABLED - The VNF package is enabled, i.e. it can be used for instantiation of new VNF instances. -DISABLED - The VNF package is disabled, i.e. it cannot be used for further VNF instantiation requests (unless and until the VNF package is re-enabled). ")
	@JsonProperty("operationalState")
	@NotNull
	public OperationalStateEnum getOperationalState() {
		return operationalState;
	}

	public void setOperationalState(OperationalStateEnum operationalState) {
		this.operationalState = operationalState;
	}

	/**
	 * \&quot;The enumeration PackageUsageStateType shall comply with the
	 * provisions. Acceptable values are: -IN_USE - VNF instances instantiated from
	 * this VNF package exist. -NOT_IN_USE - No existing VNF instance is
	 * instantiated from this VNF package\&quot;
	 **/
	public VnfPackagesVnfPkgInfo usageState(UsageStateEnum usageState) {
		this.usageState = usageState;
		return this;
	}

	@ApiModelProperty(required = true, value = "\"The enumeration PackageUsageStateType shall comply with the provisions. Acceptable values are: -IN_USE - VNF instances instantiated from this VNF package exist. -NOT_IN_USE - No existing VNF instance is instantiated from this VNF package\"       ")
	@JsonProperty("usageState")
	@NotNull
	public UsageStateEnum getUsageState() {
		return usageState;
	}

	public void setUsageState(UsageStateEnum usageState) {
		this.usageState = usageState;
	}

	/**
	 * This type represents a list of key-value pairs. The order of the pairs in the
	 * list is not significant. In JSON, a set of key- value pairs is represented as
	 * an object. It shall comply with the provisions defined in clause 4 of IETF
	 * RFC 7159.
	 **/
	public VnfPackagesVnfPkgInfo userDefinedData(Object userDefinedData) {
		this.userDefinedData = userDefinedData;
		return this;
	}

	@ApiModelProperty(value = "This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  ")
	@JsonProperty("userDefinedData")
	public Object getUserDefinedData() {
		return userDefinedData;
	}

	public void setUserDefinedData(Object userDefinedData) {
		this.userDefinedData = userDefinedData;
	}

	/**
	 **/
	public VnfPackagesVnfPkgInfo links(VnfPackagesVnfPkgInfoLinks links) {
		this.links = links;
		return this;
	}

	@ApiModelProperty(value = "")
	@JsonProperty("_links")
	public VnfPackagesVnfPkgInfoLinks getLinks() {
		return links;
	}

	public void setLinks(VnfPackagesVnfPkgInfoLinks links) {
		this.links = links;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfPackagesVnfPkgInfo {\n");

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
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
