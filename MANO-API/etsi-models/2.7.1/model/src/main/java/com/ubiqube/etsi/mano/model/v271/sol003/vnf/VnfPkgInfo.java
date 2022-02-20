/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.etsi.mano.model.v271.sol003.vnf;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents the information of an VNF package.
 */
@ApiModel(description = "This type represents the information of an VNF package. ")
@Validated
@javax.annotation.processing.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-24T10:38:36.740+02:00")

public class VnfPkgInfo {
	@JsonProperty("id")
	private String id = null;

	@JsonProperty("vnfdId")
	private String vnfdId = null;

	@JsonProperty("vnfProvider")
	private String vnfProvider = null;

	@JsonProperty("vnfProductName")
	private String vnfProductName = null;

	@JsonProperty("vnfSoftwareVersion")
	private String vnfSoftwareVersion = null;

	@JsonProperty("vnfdVersion")
	private String vnfdVersion = null;

	@JsonProperty("compatibleSpecificationVersions")
	@Valid
	private List<String> compatibleSpecificationVersions = null;

	@JsonProperty("checksum")
	private Checksum checksum = null;

	/**
	 * Signals the security option used by the package as defined in clause 5.1 of
	 * ETSI GS NFV-SOL 004. Valid values: OPTION_1, OPTION_2
	 */
	public enum PackageSecurityOptionEnum {
		_1("OPTION_1"),

		_2("OPTION_2");

		private final String value;

		PackageSecurityOptionEnum(final String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static PackageSecurityOptionEnum fromValue(final String text) {
			for (final PackageSecurityOptionEnum b : PackageSecurityOptionEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	@JsonProperty("packageSecurityOption")
	private PackageSecurityOptionEnum packageSecurityOption = null;

	@JsonProperty("signingCertificate")
	private String signingCertificate = null;

	@JsonProperty("softwareImages")
	@Valid
	private List<VnfPackageSoftwareImageInfo> softwareImages = null;

	@JsonProperty("additionalArtifacts")
	@Valid
	private List<VnfPackageArtifactInfo> additionalArtifacts = null;

	@JsonProperty("onboardingState")
	private PackageOnboardingStateType onboardingState = null;

	@JsonProperty("operationalState")
	private PackageOperationalStateType operationalState = null;

	@JsonProperty("usageState")
	private PackageUsageStateType usageState = null;

	@JsonProperty("vnfmInfo")
	private List<String> vnfmInfo = null;

	@JsonProperty("userDefinedData")
	private Map<String, String> userDefinedData = null;

	@JsonProperty("onboardingFailureDetails")
	private ProblemDetails onboardingFailureDetails = null;

	@JsonProperty("_links")
	private VnfPkgInfoLinks links = null;

	public VnfPkgInfo id(final String id) {
		this.id = id;
		return this;
	}

	/**
	 * Identifier of the on-boarded VNF package. This identifier is allocated by the
	 * NFVO.
	 *
	 * @return id
	 **/
	@ApiModelProperty(required = true, value = "Identifier of the on-boarded VNF package. This identifier is allocated by the NFVO. ")
	@NotNull

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public VnfPkgInfo vnfdId(final String vnfdId) {
		this.vnfdId = vnfdId;
		return this;
	}

	/**
	 * This identifier, which is managed by the VNF provider, identifies the VNF
	 * package and the VNFD in a globally unique way. It's copied from the VNFD of
	 * the on-boarded VNF package. It shall be present after the VNF package content
	 * has been on-boarded and absent otherwise.
	 *
	 * @return vnfdId
	 **/
	@ApiModelProperty(value = "This identifier, which is managed by the VNF provider, identifies the VNF package and the VNFD in a globally unique way. It's copied from the VNFD of the on-boarded VNF package. It shall be present after the VNF package content has been on-boarded and absent otherwise. ")

	public String getVnfdId() {
		return vnfdId;
	}

	public void setVnfdId(final String vnfdId) {
		this.vnfdId = vnfdId;
	}

	public VnfPkgInfo vnfProvider(final String vnfProvider) {
		this.vnfProvider = vnfProvider;
		return this;
	}

	/**
	 * Provider of the VNF package and the VNFD. This information is copied from the
	 * VNFD.
	 *
	 * @return vnfProvider
	 **/
	@ApiModelProperty(value = "Provider of the VNF package and the VNFD. This information is copied from the VNFD. ")

	public String getVnfProvider() {
		return vnfProvider;
	}

	public void setVnfProvider(final String vnfProvider) {
		this.vnfProvider = vnfProvider;
	}

	public VnfPkgInfo vnfProductName(final String vnfProductName) {
		this.vnfProductName = vnfProductName;
		return this;
	}

	/**
	 * Name to identify the VNF product. Invariant for the VNF product lifetime.
	 * This information is copied from the VNFD. It shall be present after the VNF
	 * package content has been on-boarded and absent otherwise.
	 *
	 * @return vnfProductName
	 **/
	@ApiModelProperty(value = "Name to identify the VNF product. Invariant for the VNF product lifetime. This information is copied from the VNFD. It shall be present after the VNF package content has been on-boarded and absent otherwise. ")

	public String getVnfProductName() {
		return vnfProductName;
	}

	public void setVnfProductName(final String vnfProductName) {
		this.vnfProductName = vnfProductName;
	}

	public VnfPkgInfo vnfSoftwareVersion(final String vnfSoftwareVersion) {
		this.vnfSoftwareVersion = vnfSoftwareVersion;
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
	@ApiModelProperty(value = "Software version of the VNF. This is changed when there is any change to the software included in the VNF package. This information is copied from the VNFD. It shall be present after the VNF package content has been on-boarded and absent otherwise. ")

	public String getVnfSoftwareVersion() {
		return vnfSoftwareVersion;
	}

	public void setVnfSoftwareVersion(final String vnfSoftwareVersion) {
		this.vnfSoftwareVersion = vnfSoftwareVersion;
	}

	public VnfPkgInfo vnfdVersion(final String vnfdVersion) {
		this.vnfdVersion = vnfdVersion;
		return this;
	}

	/**
	 * The version of the VNFD. This information is copied from the VNFD. It shall
	 * be present after the VNF package content has been on-boarded and absent
	 * otherwise.
	 *
	 * @return vnfdVersion
	 **/
	@ApiModelProperty(value = "The version of the VNFD. This information is copied from the VNFD. It shall be present after the VNF package content has been on-boarded and absent otherwise. ")

	public String getVnfdVersion() {
		return vnfdVersion;
	}

	public void setVnfdVersion(final String vnfdVersion) {
		this.vnfdVersion = vnfdVersion;
	}

	public VnfPkgInfo compatibleSpecificationVersions(final List<String> compatibleSpecificationVersions) {
		this.compatibleSpecificationVersions = compatibleSpecificationVersions;
		return this;
	}

	public VnfPkgInfo addCompatibleSpecificationVersionsItem(final String compatibleSpecificationVersionsItem) {
		if (this.compatibleSpecificationVersions == null) {
			this.compatibleSpecificationVersions = new ArrayList<>();
		}
		this.compatibleSpecificationVersions.add(compatibleSpecificationVersionsItem);
		return this;
	}

	/**
	 * Indicates which versions of the ETSI GS NFV-SOL 004 specification the package
	 * complies to, as defined in the manifest of the package. Each entry shall be
	 * formatted as defined in clause 4.3.2 of ETSI GS NFV-SOL 004.
	 *
	 * @return compatibleSpecificationVersions
	 **/
	@ApiModelProperty(value = "Indicates which versions of the ETSI GS NFV-SOL 004 specification the package complies to, as defined in the manifest of the package. Each entry shall be formatted as defined in clause 4.3.2 of ETSI GS NFV-SOL 004. ")

	public List<String> getCompatibleSpecificationVersions() {
		return compatibleSpecificationVersions;
	}

	public void setCompatibleSpecificationVersions(final List<String> compatibleSpecificationVersions) {
		this.compatibleSpecificationVersions = compatibleSpecificationVersions;
	}

	public VnfPkgInfo checksum(final Checksum checksum) {
		this.checksum = checksum;
		return this;
	}

	/**
	 * Checksum of the on-boarded VNF package. It shall be present after the VNF
	 * package content has been on-boarded and absent otherwise.
	 *
	 * @return checksum
	 **/
	@ApiModelProperty(value = "Checksum of the on-boarded VNF package. It shall be present after the VNF package content has been on-boarded and absent otherwise. ")

	@Valid

	public Checksum getChecksum() {
		return checksum;
	}

	public void setChecksum(final Checksum checksum) {
		this.checksum = checksum;
	}

	public VnfPkgInfo packageSecurityOption(final PackageSecurityOptionEnum packageSecurityOption) {
		this.packageSecurityOption = packageSecurityOption;
		return this;
	}

	/**
	 * Signals the security option used by the package as defined in clause 5.1 of
	 * ETSI GS NFV-SOL 004. Valid values: OPTION_1, OPTION_2
	 *
	 * @return packageSecurityOption
	 **/
	@ApiModelProperty(required = true, value = "Signals the security option used by the package as defined in clause 5.1 of ETSI GS NFV-SOL 004. Valid values: OPTION_1, OPTION_2 ")
	@NotNull

	public PackageSecurityOptionEnum getPackageSecurityOption() {
		return packageSecurityOption;
	}

	public void setPackageSecurityOption(final PackageSecurityOptionEnum packageSecurityOption) {
		this.packageSecurityOption = packageSecurityOption;
	}

	public VnfPkgInfo signingCertificate(final String signingCertificate) {
		this.signingCertificate = signingCertificate;
		return this;
	}

	/**
	 * The singleton signing certificate if it is included as a file in the VNF
	 * package.
	 *
	 * @return signingCertificate
	 **/
	@ApiModelProperty(value = "The singleton signing certificate if it is included as a file in the VNF package. ")

	public String getSigningCertificate() {
		return signingCertificate;
	}

	public void setSigningCertificate(final String signingCertificate) {
		this.signingCertificate = signingCertificate;
	}

	public VnfPkgInfo softwareImages(final List<VnfPackageSoftwareImageInfo> softwareImages) {
		this.softwareImages = softwareImages;
		return this;
	}

	public VnfPkgInfo addSoftwareImagesItem(final VnfPackageSoftwareImageInfo softwareImagesItem) {
		if (this.softwareImages == null) {
			this.softwareImages = new ArrayList<>();
		}
		this.softwareImages.add(softwareImagesItem);
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
	@ApiModelProperty(value = "Information about VNF package artifacts that are software images. This attribute shall not be present before the VNF package content is on-boarded. Otherwise, this attribute shall be present unless it has been requested to be excluded per attribute selector. ")

	@Valid

	public List<VnfPackageSoftwareImageInfo> getSoftwareImages() {
		return softwareImages;
	}

	public void setSoftwareImages(final List<VnfPackageSoftwareImageInfo> softwareImages) {
		this.softwareImages = softwareImages;
	}

	public VnfPkgInfo additionalArtifacts(final List<VnfPackageArtifactInfo> additionalArtifacts) {
		this.additionalArtifacts = additionalArtifacts;
		return this;
	}

	public VnfPkgInfo addAdditionalArtifactsItem(final VnfPackageArtifactInfo additionalArtifactsItem) {
		if (this.additionalArtifacts == null) {
			this.additionalArtifacts = new ArrayList<>();
		}
		this.additionalArtifacts.add(additionalArtifactsItem);
		return this;
	}

	/**
	 * Information about VNF package artifacts contained in the VNF package that are
	 * not software images. Every local and external artifact declared in the
	 * manifest shall be included, except the software images and the files that
	 * make up the parts of the VNFD (see clause 10.4.4.3.2). Signature files and
	 * certificate files are not considered as artifacts, however, the content of
	 * the \"Licenses\" and \"Testing\" directories in the VNF package is. This
	 * attribute shall not be present before the VNF package content is on-boarded.
	 * Otherwise, this attribute shall be present if the VNF package contains
	 * additional artifacts.
	 *
	 * @return additionalArtifacts
	 **/
	@ApiModelProperty(value = "Information about VNF package artifacts contained in the VNF package that are not software images. Every local and external artifact declared in the manifest shall be included, except the software images and the files that make up the parts of the VNFD (see clause 10.4.4.3.2). Signature files and certificate files are not considered as artifacts, however, the content of the \"Licenses\" and \"Testing\" directories in the VNF package is. This attribute shall not be present before the VNF package content is on-boarded. Otherwise, this attribute shall be present if the VNF package contains additional artifacts. ")

	@Valid

	public List<VnfPackageArtifactInfo> getAdditionalArtifacts() {
		return additionalArtifacts;
	}

	public void setAdditionalArtifacts(final List<VnfPackageArtifactInfo> additionalArtifacts) {
		this.additionalArtifacts = additionalArtifacts;
	}

	public VnfPkgInfo onboardingState(final PackageOnboardingStateType onboardingState) {
		this.onboardingState = onboardingState;
		return this;
	}

	/**
	 * On-boarding state of the VNF package.
	 *
	 * @return onboardingState
	 **/
	@ApiModelProperty(value = "On-boarding state of the VNF package. ")

	@Valid

	public PackageOnboardingStateType getOnboardingState() {
		return onboardingState;
	}

	public void setOnboardingState(final PackageOnboardingStateType onboardingState) {
		this.onboardingState = onboardingState;
	}

	public VnfPkgInfo operationalState(final PackageOperationalStateType operationalState) {
		this.operationalState = operationalState;
		return this;
	}

	/**
	 * Operational state of the VNF package. If the value of the onboardingState
	 * attribute is not equal to \"ONBOARDED\", the value of the operationalState
	 * attribute shall be equal to \"DISABLED\".
	 *
	 * @return operationalState
	 **/
	@ApiModelProperty(required = true, value = "Operational state of the VNF package. If the value of the onboardingState attribute is not equal to \"ONBOARDED\", the value of the operationalState attribute shall be equal to \"DISABLED\". ")
	@NotNull

	@Valid

	public PackageOperationalStateType getOperationalState() {
		return operationalState;
	}

	public void setOperationalState(final PackageOperationalStateType operationalState) {
		this.operationalState = operationalState;
	}

	public VnfPkgInfo usageState(final PackageUsageStateType usageState) {
		this.usageState = usageState;
		return this;
	}

	/**
	 * Usage state of the VNF package. If the value of the onboardingState attribute
	 * is not equal to \"ONBOARDED\", the value of the usageState attribute shall be
	 * equal to \"NOT_IN_USE\".
	 *
	 * @return usageState
	 **/
	@ApiModelProperty(required = true, value = "Usage state of the VNF package. If the value of the onboardingState attribute is not equal to \"ONBOARDED\", the value of the usageState attribute shall be equal to \"NOT_IN_USE\". ")
	@NotNull

	@Valid

	public PackageUsageStateType getUsageState() {
		return usageState;
	}

	public void setUsageState(final PackageUsageStateType usageState) {
		this.usageState = usageState;
	}

	public VnfPkgInfo vnfmInfo(final List<String> vnfmInfo) {
		this.vnfmInfo = vnfmInfo;
		return this;
	}

	/**
	 * Specifies VNFMs compatible with the VNF. This information is copied from the
	 * VNFD. ETSI GS NFV-SOL 001 specifies the structure and format of the VNFD
	 * based on TOSCA specifications.
	 *
	 * @return vnfmInfo
	 **/
	@ApiModelProperty(required = true, value = "Specifies VNFMs compatible with the VNF. This information is copied from the VNFD. ETSI GS NFV-SOL 001 specifies the structure and format of the VNFD based on TOSCA specifications. ")
	@NotNull

	@Valid

	public List<String> getVnfmInfo() {
		return vnfmInfo;
	}

	public void setVnfmInfo(final List<String> vnfmInfo) {
		this.vnfmInfo = vnfmInfo;
	}

	public VnfPkgInfo userDefinedData(final Map<String, String> userDefinedData) {
		this.userDefinedData = userDefinedData;
		return this;
	}

	/**
	 * User defined data for the VNF package.
	 *
	 * @return userDefinedData
	 **/
	@ApiModelProperty(value = "User defined data for the VNF package. ")

	@Valid

	public Map<String, String> getUserDefinedData() {
		return userDefinedData;
	}

	public void setUserDefinedData(final Map<String, String> userDefinedData) {
		this.userDefinedData = userDefinedData;
	}

	public VnfPkgInfo onboardingFailureDetails(final ProblemDetails onboardingFailureDetails) {
		this.onboardingFailureDetails = onboardingFailureDetails;
		return this;
	}

	/**
	 * Failure details of current onboarding procedure. See clause 6.3 of ETSI GS
	 * NFV-SOL 013 for the details of \"ProblemDetails\" structure. If
	 * \"onboardingState\" is \"ERROR\", this attribute shall be present and contain
	 * error information (such as failed onboarding or processing operation,
	 * affected artifact etc.), unless it has been requested to be excluded via an
	 * attribute selector.
	 *
	 * @return onboardingFailureDetails
	 **/
	@ApiModelProperty(value = "Failure details of current onboarding procedure. See clause 6.3 of ETSI GS NFV-SOL 013 for the details of \"ProblemDetails\" structure. If \"onboardingState\" is \"ERROR\", this attribute shall be present and contain error information (such as failed onboarding or processing operation, affected artifact etc.), unless it has been requested to be excluded via an attribute selector. ")

	@Valid

	public ProblemDetails getOnboardingFailureDetails() {
		return onboardingFailureDetails;
	}

	public void setOnboardingFailureDetails(final ProblemDetails onboardingFailureDetails) {
		this.onboardingFailureDetails = onboardingFailureDetails;
	}

	public VnfPkgInfo links(final VnfPkgInfoLinks links) {
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

	public VnfPkgInfoLinks getLinks() {
		return links;
	}

	public void setLinks(final VnfPkgInfoLinks links) {
		this.links = links;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final VnfPkgInfo vnfPkgInfo = (VnfPkgInfo) o;
		return Objects.equals(this.id, vnfPkgInfo.id) &&
				Objects.equals(this.vnfdId, vnfPkgInfo.vnfdId) &&
				Objects.equals(this.vnfProvider, vnfPkgInfo.vnfProvider) &&
				Objects.equals(this.vnfProductName, vnfPkgInfo.vnfProductName) &&
				Objects.equals(this.vnfSoftwareVersion, vnfPkgInfo.vnfSoftwareVersion) &&
				Objects.equals(this.vnfdVersion, vnfPkgInfo.vnfdVersion) &&
				Objects.equals(this.compatibleSpecificationVersions, vnfPkgInfo.compatibleSpecificationVersions) &&
				Objects.equals(this.checksum, vnfPkgInfo.checksum) &&
				Objects.equals(this.packageSecurityOption, vnfPkgInfo.packageSecurityOption) &&
				Objects.equals(this.signingCertificate, vnfPkgInfo.signingCertificate) &&
				Objects.equals(this.softwareImages, vnfPkgInfo.softwareImages) &&
				Objects.equals(this.additionalArtifacts, vnfPkgInfo.additionalArtifacts) &&
				Objects.equals(this.onboardingState, vnfPkgInfo.onboardingState) &&
				Objects.equals(this.operationalState, vnfPkgInfo.operationalState) &&
				Objects.equals(this.usageState, vnfPkgInfo.usageState) &&
				Objects.equals(this.vnfmInfo, vnfPkgInfo.vnfmInfo) &&
				Objects.equals(this.userDefinedData, vnfPkgInfo.userDefinedData) &&
				Objects.equals(this.onboardingFailureDetails, vnfPkgInfo.onboardingFailureDetails) &&
				Objects.equals(this.links, vnfPkgInfo.links);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, vnfdId, vnfProvider, vnfProductName, vnfSoftwareVersion, vnfdVersion, compatibleSpecificationVersions, checksum, packageSecurityOption, signingCertificate, softwareImages, additionalArtifacts, onboardingState, operationalState, usageState, vnfmInfo, userDefinedData, onboardingFailureDetails, links);
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
		sb.append("    compatibleSpecificationVersions: ").append(toIndentedString(compatibleSpecificationVersions)).append("\n");
		sb.append("    checksum: ").append(toIndentedString(checksum)).append("\n");
		sb.append("    packageSecurityOption: ").append(toIndentedString(packageSecurityOption)).append("\n");
		sb.append("    signingCertificate: ").append(toIndentedString(signingCertificate)).append("\n");
		sb.append("    softwareImages: ").append(toIndentedString(softwareImages)).append("\n");
		sb.append("    additionalArtifacts: ").append(toIndentedString(additionalArtifacts)).append("\n");
		sb.append("    onboardingState: ").append(toIndentedString(onboardingState)).append("\n");
		sb.append("    operationalState: ").append(toIndentedString(operationalState)).append("\n");
		sb.append("    usageState: ").append(toIndentedString(usageState)).append("\n");
		sb.append("    vnfmInfo: ").append(toIndentedString(vnfmInfo)).append("\n");
		sb.append("    userDefinedData: ").append(toIndentedString(userDefinedData)).append("\n");
		sb.append("    onboardingFailureDetails: ").append(toIndentedString(onboardingFailureDetails)).append("\n");
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
