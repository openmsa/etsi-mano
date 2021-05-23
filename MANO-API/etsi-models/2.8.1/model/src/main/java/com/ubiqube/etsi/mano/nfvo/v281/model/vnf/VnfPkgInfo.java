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
package com.ubiqube.etsi.mano.nfvo.v281.model.vnf;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.nfvo.v281.model.vnf.Checksum;
import com.ubiqube.etsi.mano.nfvo.v281.model.vnf.KeyValuePairs;
import com.ubiqube.etsi.mano.nfvo.v281.model.vnf.PackageOnboardingStateType;
import com.ubiqube.etsi.mano.nfvo.v281.model.vnf.PackageOperationalStateType;
import com.ubiqube.etsi.mano.nfvo.v281.model.vnf.PackageUsageStateType;
import com.ubiqube.etsi.mano.nfvo.v281.model.vnf.ProblemDetails;
import com.ubiqube.etsi.mano.nfvo.v281.model.vnf.VnfPackageArtifactInfo;
import com.ubiqube.etsi.mano.nfvo.v281.model.vnf.VnfPackageSoftwareImageInfo;
import com.ubiqube.etsi.mano.nfvo.v281.model.vnf.VnfPkgInfoLinks;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * VnfPkgInfo
 */
@Validated

public class VnfPkgInfo   {
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
  private String compatibleSpecificationVersions = null;

  @JsonProperty("checksum")
  private Checksum checksum = null;

  /**
   * Signals the security option used by the package as defined in clause 5.1 of ETSI GS NFV-SOL 004 [5]. Valid values: OPTION_1, OPTION_2 
   */
  public enum PackageSecurityOptionEnum {
    _1("OPTION_1"),
    
    _2("OPTION_2");

    private String value;

    PackageSecurityOptionEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static PackageSecurityOptionEnum fromValue(String text) {
      for (PackageSecurityOptionEnum b : PackageSecurityOptionEnum.values()) {
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
  @Valid
  private List<String> vnfmInfo = new ArrayList<>();

  @JsonProperty("userDefinedData")
  private KeyValuePairs userDefinedData = null;

  @JsonProperty("onboardingFailureDetails")
  private ProblemDetails onboardingFailureDetails = null;

  @JsonProperty("_links")
  private VnfPkgInfoLinks links = null;

  public VnfPkgInfo id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Identifier of the VNF package. This identifier is allocated by the NFVO. 
   * @return id
  **/
  @ApiModelProperty(required = true, value = "Identifier of the VNF package. This identifier is allocated by the NFVO. ")
  @NotNull


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public VnfPkgInfo vnfdId(String vnfdId) {
    this.vnfdId = vnfdId;
    return this;
  }

  /**
   * This identifier, which is managed by the VNF provider, identifies the VNF package and the VNFD in a globally unique way. It is copied from the VNFD of the on boarded VNF package. It shall be present after the VNF package content has been on-boarded and absent otherwise. 
   * @return vnfdId
  **/
  @ApiModelProperty(value = "This identifier, which is managed by the VNF provider, identifies the VNF package and the VNFD in a globally unique way. It is copied from the VNFD of the on boarded VNF package. It shall be present after the VNF package content has been on-boarded and absent otherwise. ")


  public String getVnfdId() {
    return vnfdId;
  }

  public void setVnfdId(String vnfdId) {
    this.vnfdId = vnfdId;
  }

  public VnfPkgInfo vnfProvider(String vnfProvider) {
    this.vnfProvider = vnfProvider;
    return this;
  }

  /**
   * Provider of the VNF package and the VNFD. This information is copied from the VNFD.  It shall be present after the VNF package content has been on-boarded and absent otherwise. 
   * @return vnfProvider
  **/
  @ApiModelProperty(value = "Provider of the VNF package and the VNFD. This information is copied from the VNFD.  It shall be present after the VNF package content has been on-boarded and absent otherwise. ")


  public String getVnfProvider() {
    return vnfProvider;
  }

  public void setVnfProvider(String vnfProvider) {
    this.vnfProvider = vnfProvider;
  }

  public VnfPkgInfo vnfProductName(String vnfProductName) {
    this.vnfProductName = vnfProductName;
    return this;
  }

  /**
   * Name to identify the VNF product.Invariant for the VNF product lifetime.  This information is copied from the VNFD. It shall be present after the VNF package content has been on-boarded and absent otherwise. 
   * @return vnfProductName
  **/
  @ApiModelProperty(value = "Name to identify the VNF product.Invariant for the VNF product lifetime.  This information is copied from the VNFD. It shall be present after the VNF package content has been on-boarded and absent otherwise. ")


  public String getVnfProductName() {
    return vnfProductName;
  }

  public void setVnfProductName(String vnfProductName) {
    this.vnfProductName = vnfProductName;
  }

  public VnfPkgInfo vnfSoftwareVersion(String vnfSoftwareVersion) {
    this.vnfSoftwareVersion = vnfSoftwareVersion;
    return this;
  }

  /**
   * Software version of the VNF. This is changed when there is any change to the software included in the VNF package. This information is copied from the VNFD. It shall be present after the VNF package content has been on-boarded and absent otherwise. 
   * @return vnfSoftwareVersion
  **/
  @ApiModelProperty(value = "Software version of the VNF. This is changed when there is any change to the software included in the VNF package. This information is copied from the VNFD. It shall be present after the VNF package content has been on-boarded and absent otherwise. ")


  public String getVnfSoftwareVersion() {
    return vnfSoftwareVersion;
  }

  public void setVnfSoftwareVersion(String vnfSoftwareVersion) {
    this.vnfSoftwareVersion = vnfSoftwareVersion;
  }

  public VnfPkgInfo vnfdVersion(String vnfdVersion) {
    this.vnfdVersion = vnfdVersion;
    return this;
  }

  /**
   * The version of the VNFD. This information is copied from the VNFD. It shall be present after the VNF package content has been on-boarded and absent otherwise. 
   * @return vnfdVersion
  **/
  @ApiModelProperty(value = "The version of the VNFD. This information is copied from the VNFD. It shall be present after the VNF package content has been on-boarded and absent otherwise. ")


  public String getVnfdVersion() {
    return vnfdVersion;
  }

  public void setVnfdVersion(String vnfdVersion) {
    this.vnfdVersion = vnfdVersion;
  }

  public VnfPkgInfo compatibleSpecificationVersions(String compatibleSpecificationVersions) {
    this.compatibleSpecificationVersions = compatibleSpecificationVersions;
    return this;
  }

  /**
   * Indicates which versions of the ETSI GS NFV-SOL 004 [5] specification the package complies to, as defined in the manifest of the package. Each entry shall be formatted as defined in clause 4.3.2 of ETSI GS NFV-SOL 004 [5]. 
   * @return compatibleSpecificationVersions
  **/
  @ApiModelProperty(value = "Indicates which versions of the ETSI GS NFV-SOL 004 [5] specification the package complies to, as defined in the manifest of the package. Each entry shall be formatted as defined in clause 4.3.2 of ETSI GS NFV-SOL 004 [5]. ")


  public String getCompatibleSpecificationVersions() {
    return compatibleSpecificationVersions;
  }

  public void setCompatibleSpecificationVersions(String compatibleSpecificationVersions) {
    this.compatibleSpecificationVersions = compatibleSpecificationVersions;
  }

  public VnfPkgInfo checksum(Checksum checksum) {
    this.checksum = checksum;
    return this;
  }

  /**
   * Checksum of the on-boarded VNF package. It shall be present after the VNF package content has been on-boarded and absent otherwise. 
   * @return checksum
  **/
  @ApiModelProperty(value = "Checksum of the on-boarded VNF package. It shall be present after the VNF package content has been on-boarded and absent otherwise. ")

  @Valid

  public Checksum getChecksum() {
    return checksum;
  }

  public void setChecksum(Checksum checksum) {
    this.checksum = checksum;
  }

  public VnfPkgInfo packageSecurityOption(PackageSecurityOptionEnum packageSecurityOption) {
    this.packageSecurityOption = packageSecurityOption;
    return this;
  }

  /**
   * Signals the security option used by the package as defined in clause 5.1 of ETSI GS NFV-SOL 004 [5]. Valid values: OPTION_1, OPTION_2 
   * @return packageSecurityOption
  **/
  @ApiModelProperty(required = true, value = "Signals the security option used by the package as defined in clause 5.1 of ETSI GS NFV-SOL 004 [5]. Valid values: OPTION_1, OPTION_2 ")
  @NotNull


  public PackageSecurityOptionEnum getPackageSecurityOption() {
    return packageSecurityOption;
  }

  public void setPackageSecurityOption(PackageSecurityOptionEnum packageSecurityOption) {
    this.packageSecurityOption = packageSecurityOption;
  }

  public VnfPkgInfo signingCertificate(String signingCertificate) {
    this.signingCertificate = signingCertificate;
    return this;
  }

  /**
   * The singleton signing certificate if it is included as a file in the VNF package. 
   * @return signingCertificate
  **/
  @ApiModelProperty(value = "The singleton signing certificate if it is included as a file in the VNF package. ")


  public String getSigningCertificate() {
    return signingCertificate;
  }

  public void setSigningCertificate(String signingCertificate) {
    this.signingCertificate = signingCertificate;
  }

  public VnfPkgInfo softwareImages(List<VnfPackageSoftwareImageInfo> softwareImages) {
    this.softwareImages = softwareImages;
    return this;
  }

  public VnfPkgInfo addSoftwareImagesItem(VnfPackageSoftwareImageInfo softwareImagesItem) {
    if (this.softwareImages == null) {
      this.softwareImages = new ArrayList<>();
    }
    this.softwareImages.add(softwareImagesItem);
    return this;
  }

  /**
   * Information about VNF package artifacts that are software images. This attribute shall not be present before the VNF package content is on-boarded. Otherwise, this attribute shall be present unless it has been requested to be excluded per attribute selector. 
   * @return softwareImages
  **/
  @ApiModelProperty(value = "Information about VNF package artifacts that are software images. This attribute shall not be present before the VNF package content is on-boarded. Otherwise, this attribute shall be present unless it has been requested to be excluded per attribute selector. ")

  @Valid

  public List<VnfPackageSoftwareImageInfo> getSoftwareImages() {
    return softwareImages;
  }

  public void setSoftwareImages(List<VnfPackageSoftwareImageInfo> softwareImages) {
    this.softwareImages = softwareImages;
  }

  public VnfPkgInfo additionalArtifacts(List<VnfPackageArtifactInfo> additionalArtifacts) {
    this.additionalArtifacts = additionalArtifacts;
    return this;
  }

  public VnfPkgInfo addAdditionalArtifactsItem(VnfPackageArtifactInfo additionalArtifactsItem) {
    if (this.additionalArtifacts == null) {
      this.additionalArtifacts = new ArrayList<>();
    }
    this.additionalArtifacts.add(additionalArtifactsItem);
    return this;
  }

  /**
   * Information about VNF package artifacts contained in the VNF package that are not software images. This attribute shall not be present before the VNF package content is on-boarded. Otherwise, this attribute shall be present if the VNF package contains additional artifacts. 
   * @return additionalArtifacts
  **/
  @ApiModelProperty(value = "Information about VNF package artifacts contained in the VNF package that are not software images. This attribute shall not be present before the VNF package content is on-boarded. Otherwise, this attribute shall be present if the VNF package contains additional artifacts. ")

  @Valid

  public List<VnfPackageArtifactInfo> getAdditionalArtifacts() {
    return additionalArtifacts;
  }

  public void setAdditionalArtifacts(List<VnfPackageArtifactInfo> additionalArtifacts) {
    this.additionalArtifacts = additionalArtifacts;
  }

  public VnfPkgInfo onboardingState(PackageOnboardingStateType onboardingState) {
    this.onboardingState = onboardingState;
    return this;
  }

  /**
   * On-boarding state of the VNF package. 
   * @return onboardingState
  **/
  @ApiModelProperty(required = true, value = "On-boarding state of the VNF package. ")
  @NotNull

  @Valid

  public PackageOnboardingStateType getOnboardingState() {
    return onboardingState;
  }

  public void setOnboardingState(PackageOnboardingStateType onboardingState) {
    this.onboardingState = onboardingState;
  }

  public VnfPkgInfo operationalState(PackageOperationalStateType operationalState) {
    this.operationalState = operationalState;
    return this;
  }

  /**
   * Operational state of the VNF package. 
   * @return operationalState
  **/
  @ApiModelProperty(required = true, value = "Operational state of the VNF package. ")
  @NotNull

  @Valid

  public PackageOperationalStateType getOperationalState() {
    return operationalState;
  }

  public void setOperationalState(PackageOperationalStateType operationalState) {
    this.operationalState = operationalState;
  }

  public VnfPkgInfo usageState(PackageUsageStateType usageState) {
    this.usageState = usageState;
    return this;
  }

  /**
   * Usage state of the VNF package. 
   * @return usageState
  **/
  @ApiModelProperty(required = true, value = "Usage state of the VNF package. ")
  @NotNull

  @Valid

  public PackageUsageStateType getUsageState() {
    return usageState;
  }

  public void setUsageState(PackageUsageStateType usageState) {
    this.usageState = usageState;
  }

  public VnfPkgInfo vnfmInfo(List<String> vnfmInfo) {
    this.vnfmInfo = vnfmInfo;
    return this;
  }

  public VnfPkgInfo addVnfmInfoItem(String vnfmInfoItem) {
    this.vnfmInfo.add(vnfmInfoItem);
    return this;
  }

  /**
   * Specifies VNFMs compatible with the VNF. This information is copied from the VNFD. See note 4. 
   * @return vnfmInfo
  **/
  @ApiModelProperty(required = true, value = "Specifies VNFMs compatible with the VNF. This information is copied from the VNFD. See note 4. ")
  @NotNull


  public List<String> getVnfmInfo() {
    return vnfmInfo;
  }

  public void setVnfmInfo(List<String> vnfmInfo) {
    this.vnfmInfo = vnfmInfo;
  }

  public VnfPkgInfo userDefinedData(KeyValuePairs userDefinedData) {
    this.userDefinedData = userDefinedData;
    return this;
  }

  /**
   * Usage state of the VNF package. 
   * @return userDefinedData
  **/
  @ApiModelProperty(value = "Usage state of the VNF package. ")

  @Valid

  public KeyValuePairs getUserDefinedData() {
    return userDefinedData;
  }

  public void setUserDefinedData(KeyValuePairs userDefinedData) {
    this.userDefinedData = userDefinedData;
  }

  public VnfPkgInfo onboardingFailureDetails(ProblemDetails onboardingFailureDetails) {
    this.onboardingFailureDetails = onboardingFailureDetails;
    return this;
  }

  /**
   * Failure details of current onboarding procedure. See clause 6.3 of ETSI GS NFV-SOL 013 [16] for the details of \"ProblemDetails\" structure. If \"onboardingState\" is \"ERROR\", this attribute shall be present and contain error information (such as failed onboarding or processing operation, affected artifact, etc.), unless it has been requested to be excluded via an attribute selector. 
   * @return onboardingFailureDetails
  **/
  @ApiModelProperty(value = "Failure details of current onboarding procedure. See clause 6.3 of ETSI GS NFV-SOL 013 [16] for the details of \"ProblemDetails\" structure. If \"onboardingState\" is \"ERROR\", this attribute shall be present and contain error information (such as failed onboarding or processing operation, affected artifact, etc.), unless it has been requested to be excluded via an attribute selector. ")

  @Valid

  public ProblemDetails getOnboardingFailureDetails() {
    return onboardingFailureDetails;
  }

  public void setOnboardingFailureDetails(ProblemDetails onboardingFailureDetails) {
    this.onboardingFailureDetails = onboardingFailureDetails;
  }

  public VnfPkgInfo links(VnfPkgInfoLinks links) {
    this.links = links;
    return this;
  }

  /**
   * Get links
   * @return links
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public VnfPkgInfoLinks getLinks() {
    return links;
  }

  public void setLinks(VnfPkgInfoLinks links) {
    this.links = links;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VnfPkgInfo vnfPkgInfo = (VnfPkgInfo) o;
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
    StringBuilder sb = new StringBuilder();
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
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

