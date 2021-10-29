package com.ubiqube.etsi.mano.vnfm.v351.model.vnf;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.vnfm.v351.model.vnf.KeyValuePairs;
import com.ubiqube.etsi.mano.vnfm.v351.model.vnf.PackageOnboardingStateType;
import com.ubiqube.etsi.mano.vnfm.v351.model.vnf.PackageOperationalStateType;
import com.ubiqube.etsi.mano.vnfm.v351.model.vnf.PackageUsageStateType;
import com.ubiqube.etsi.mano.vnfm.v351.model.vnf.ProblemDetails;
import com.ubiqube.etsi.mano.vnfm.v351.model.vnf.VnfPackageArtifactInfo;
import com.ubiqube.etsi.mano.vnfm.v351.model.vnf.VnfPackageSoftwareImageInfo;
import com.ubiqube.etsi.mano.vnfm.v351.model.vnf.VnfPkgInfoLinks;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents the information of a VNF package. It shall comply with the provisions defined in table 10.5.2.2-1. NOTE 1: If the value of the onboardingState attribute is not equal to \&quot;ONBOARDED\&quot;, the value of the operationalState          attribute shall be equal to \&quot;DISABLED\&quot;. NOTE 2: If the value of the onboardingState attribute is not equal to \&quot;ONBOARDED\&quot;, the value of the usageState attribute          shall be equal to \&quot;NOT_IN_USE\&quot;. NOTE 3: ETSI GS NFV-SOL 001 specifies the structure and format of the VNFD based on TOSCA specifications. 
 */
@Schema(description = "This type represents the information of a VNF package. It shall comply with the provisions defined in table 10.5.2.2-1. NOTE 1: If the value of the onboardingState attribute is not equal to \"ONBOARDED\", the value of the operationalState          attribute shall be equal to \"DISABLED\". NOTE 2: If the value of the onboardingState attribute is not equal to \"ONBOARDED\", the value of the usageState attribute          shall be equal to \"NOT_IN_USE\". NOTE 3: ETSI GS NFV-SOL 001 specifies the structure and format of the VNFD based on TOSCA specifications. ")
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
  @Valid
  private List<String> compatibleSpecificationVersions = null;

  @JsonProperty("checksum")
  private String checksum = null;

  /**
   * Signals the security option used by the package as defined in clause 5.1 of ETSI GS NFV-SOL 004. It shall be present after the VNF package content has been on-boarded and absent otherwise. Valid values: OPTION_1, OPTION_2 
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
  private String vnfmInfo = null;

  @JsonProperty("userDefinedData")
  private KeyValuePairs userDefinedData = null;

  @JsonProperty("onboardingFailureDetails")
  private ProblemDetails onboardingFailureDetails = null;

  @JsonProperty("_links")
  private VnfPkgInfoLinks _links = null;

  public VnfPkgInfo id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @Schema(required = true, description = "")
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
   * Get vnfdId
   * @return vnfdId
   **/
  @Schema(description = "")
  
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
   * Get vnfProvider
   * @return vnfProvider
   **/
  @Schema(description = "")
  
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
   * Get vnfProductName
   * @return vnfProductName
   **/
  @Schema(description = "")
  
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
   * Get vnfSoftwareVersion
   * @return vnfSoftwareVersion
   **/
  @Schema(description = "")
  
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
   * Get vnfdVersion
   * @return vnfdVersion
   **/
  @Schema(description = "")
  
    public String getVnfdVersion() {
    return vnfdVersion;
  }

  public void setVnfdVersion(String vnfdVersion) {
    this.vnfdVersion = vnfdVersion;
  }

  public VnfPkgInfo compatibleSpecificationVersions(List<String> compatibleSpecificationVersions) {
    this.compatibleSpecificationVersions = compatibleSpecificationVersions;
    return this;
  }

  public VnfPkgInfo addCompatibleSpecificationVersionsItem(String compatibleSpecificationVersionsItem) {
    if (this.compatibleSpecificationVersions == null) {
      this.compatibleSpecificationVersions = new ArrayList<>();
    }
    this.compatibleSpecificationVersions.add(compatibleSpecificationVersionsItem);
    return this;
  }

  /**
   * Indicates which versions of the ETSI GS NFV-SOL 004 specification the package complies to, as defined in the manifest of the package. Each entry shall be formatted as defined in clause 4.3.2 of ETSI GS NFV-SOL 004. 
   * @return compatibleSpecificationVersions
   **/
  @Schema(description = "Indicates which versions of the ETSI GS NFV-SOL 004 specification the package complies to, as defined in the manifest of the package. Each entry shall be formatted as defined in clause 4.3.2 of ETSI GS NFV-SOL 004. ")
  
    public List<String> getCompatibleSpecificationVersions() {
    return compatibleSpecificationVersions;
  }

  public void setCompatibleSpecificationVersions(List<String> compatibleSpecificationVersions) {
    this.compatibleSpecificationVersions = compatibleSpecificationVersions;
  }

  public VnfPkgInfo checksum(String checksum) {
    this.checksum = checksum;
    return this;
  }

  /**
   * Get checksum
   * @return checksum
   **/
  @Schema(description = "")
  
    public String getChecksum() {
    return checksum;
  }

  public void setChecksum(String checksum) {
    this.checksum = checksum;
  }

  public VnfPkgInfo packageSecurityOption(PackageSecurityOptionEnum packageSecurityOption) {
    this.packageSecurityOption = packageSecurityOption;
    return this;
  }

  /**
   * Signals the security option used by the package as defined in clause 5.1 of ETSI GS NFV-SOL 004. It shall be present after the VNF package content has been on-boarded and absent otherwise. Valid values: OPTION_1, OPTION_2 
   * @return packageSecurityOption
   **/
  @Schema(description = "Signals the security option used by the package as defined in clause 5.1 of ETSI GS NFV-SOL 004. It shall be present after the VNF package content has been on-boarded and absent otherwise. Valid values: OPTION_1, OPTION_2 ")
  
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
   * Get signingCertificate
   * @return signingCertificate
   **/
  @Schema(description = "")
  
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
  @Schema(description = "Information about VNF package artifacts that are software images. This attribute shall not be present before the VNF package content is on-boarded. Otherwise, this attribute shall be present unless it has been requested to be excluded per attribute selector. ")
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
   * Information about VNF package artifacts contained in the VNF package that are not software images. Every local and external artifact declared in the manifest shall be included, except the software images and the files that make up the parts of the VNFD (see clause 10.4.4.3.2). Signature files and certificate files are not considered as artifacts, however, the content of the \"Licenses\" and \"Testing\" directories in the VNF package is. This attribute shall not be present before the VNF package content is on-boarded. Otherwise, this attribute shall be present if the VNF package contains additional artifacts. 
   * @return additionalArtifacts
   **/
  @Schema(description = "Information about VNF package artifacts contained in the VNF package that are not software images. Every local and external artifact declared in the manifest shall be included, except the software images and the files that make up the parts of the VNFD (see clause 10.4.4.3.2). Signature files and certificate files are not considered as artifacts, however, the content of the \"Licenses\" and \"Testing\" directories in the VNF package is. This attribute shall not be present before the VNF package content is on-boarded. Otherwise, this attribute shall be present if the VNF package contains additional artifacts. ")
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
   * Get onboardingState
   * @return onboardingState
   **/
  @Schema(description = "")
  
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
   * Get operationalState
   * @return operationalState
   **/
  @Schema(required = true, description = "")
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
   * Get usageState
   * @return usageState
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public PackageUsageStateType getUsageState() {
    return usageState;
  }

  public void setUsageState(PackageUsageStateType usageState) {
    this.usageState = usageState;
  }

  public VnfPkgInfo vnfmInfo(String vnfmInfo) {
    this.vnfmInfo = vnfmInfo;
    return this;
  }

  /**
   * Get vnfmInfo
   * @return vnfmInfo
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getVnfmInfo() {
    return vnfmInfo;
  }

  public void setVnfmInfo(String vnfmInfo) {
    this.vnfmInfo = vnfmInfo;
  }

  public VnfPkgInfo userDefinedData(KeyValuePairs userDefinedData) {
    this.userDefinedData = userDefinedData;
    return this;
  }

  /**
   * Get userDefinedData
   * @return userDefinedData
   **/
  @Schema(description = "")
  
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
   * Get onboardingFailureDetails
   * @return onboardingFailureDetails
   **/
  @Schema(description = "")
  
    @Valid
    public ProblemDetails getOnboardingFailureDetails() {
    return onboardingFailureDetails;
  }

  public void setOnboardingFailureDetails(ProblemDetails onboardingFailureDetails) {
    this.onboardingFailureDetails = onboardingFailureDetails;
  }

  public VnfPkgInfo _links(VnfPkgInfoLinks _links) {
    this._links = _links;
    return this;
  }

  /**
   * Get _links
   * @return _links
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public VnfPkgInfoLinks getLinks() {
    return _links;
  }

  public void setLinks(VnfPkgInfoLinks _links) {
    this._links = _links;
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
        Objects.equals(this._links, vnfPkgInfo._links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, vnfdId, vnfProvider, vnfProductName, vnfSoftwareVersion, vnfdVersion, compatibleSpecificationVersions, checksum, packageSecurityOption, signingCertificate, softwareImages, additionalArtifacts, onboardingState, operationalState, usageState, vnfmInfo, userDefinedData, onboardingFailureDetails, _links);
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
    sb.append("    _links: ").append(toIndentedString(_links)).append("\n");
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
