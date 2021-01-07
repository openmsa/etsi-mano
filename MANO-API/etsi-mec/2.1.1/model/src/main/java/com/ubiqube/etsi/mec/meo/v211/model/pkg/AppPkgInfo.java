package com.ubiqube.etsi.mec.meo.v211.model.pkg;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mec.meo.v211.model.pkg.AppPkgArtifactInfo;
import com.ubiqube.etsi.mec.meo.v211.model.pkg.AppPkgInfoLinks;
import com.ubiqube.etsi.mec.meo.v211.model.pkg.AppPkgOperationalState;
import com.ubiqube.etsi.mec.meo.v211.model.pkg.AppPkgSWImageInfo;
import com.ubiqube.etsi.mec.meo.v211.model.pkg.Checksum;
import com.ubiqube.etsi.mec.meo.v211.model.pkg.KeyValuePairs;
import com.ubiqube.etsi.mec.meo.v211.model.pkg.OnboardingState;
import com.ubiqube.etsi.mec.meo.v211.model.pkg.UsageState;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * &#x27;The data type AppPkgInfo represents the parameters for an application package resource&#x27;
 */
@ApiModel(description = "'The data type AppPkgInfo represents the parameters for an application package resource'")
@Validated
public class AppPkgInfo   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("appDId")
  private String appDId = null;

  @JsonProperty("appProvider")
  private String appProvider = null;

  @JsonProperty("appName")
  private String appName = null;

  @JsonProperty("appSoftwareVersion")
  private String appSoftwareVersion = null;

  @JsonProperty("appDVersion")
  private String appDVersion = null;

  @JsonProperty("checksum")
  private Checksum checksum = null;

  @JsonProperty("softwareImages")
  private AppPkgSWImageInfo softwareImages = null;

  @JsonProperty("additionalArtifacts")
  private AppPkgArtifactInfo additionalArtifacts = null;

  @JsonProperty("onboardingState")
  private OnboardingState onboardingState = null;

  @JsonProperty("operationalState")
  private AppPkgOperationalState operationalState = null;

  @JsonProperty("usageState")
  private UsageState usageState = null;

  @JsonProperty("userDefinedData")
  private KeyValuePairs userDefinedData = null;

  @JsonProperty("_links")
  private AppPkgInfoLinks _links = null;

  public AppPkgInfo id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public AppPkgInfo appDId(String appDId) {
    this.appDId = appDId;
    return this;
  }

  /**
   * Get appDId
   * @return appDId
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getAppDId() {
    return appDId;
  }

  public void setAppDId(String appDId) {
    this.appDId = appDId;
  }

  public AppPkgInfo appProvider(String appProvider) {
    this.appProvider = appProvider;
    return this;
  }

  /**
   * Get appProvider
   * @return appProvider
  **/
  @ApiModelProperty(value = "")
  
    public String getAppProvider() {
    return appProvider;
  }

  public void setAppProvider(String appProvider) {
    this.appProvider = appProvider;
  }

  public AppPkgInfo appName(String appName) {
    this.appName = appName;
    return this;
  }

  /**
   * Get appName
   * @return appName
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getAppName() {
    return appName;
  }

  public void setAppName(String appName) {
    this.appName = appName;
  }

  public AppPkgInfo appSoftwareVersion(String appSoftwareVersion) {
    this.appSoftwareVersion = appSoftwareVersion;
    return this;
  }

  /**
   * Get appSoftwareVersion
   * @return appSoftwareVersion
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getAppSoftwareVersion() {
    return appSoftwareVersion;
  }

  public void setAppSoftwareVersion(String appSoftwareVersion) {
    this.appSoftwareVersion = appSoftwareVersion;
  }

  public AppPkgInfo appDVersion(String appDVersion) {
    this.appDVersion = appDVersion;
    return this;
  }

  /**
   * Get appDVersion
   * @return appDVersion
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getAppDVersion() {
    return appDVersion;
  }

  public void setAppDVersion(String appDVersion) {
    this.appDVersion = appDVersion;
  }

  public AppPkgInfo checksum(Checksum checksum) {
    this.checksum = checksum;
    return this;
  }

  /**
   * Get checksum
   * @return checksum
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public Checksum getChecksum() {
    return checksum;
  }

  public void setChecksum(Checksum checksum) {
    this.checksum = checksum;
  }

  public AppPkgInfo softwareImages(AppPkgSWImageInfo softwareImages) {
    this.softwareImages = softwareImages;
    return this;
  }

  /**
   * Get softwareImages
   * @return softwareImages
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public AppPkgSWImageInfo getSoftwareImages() {
    return softwareImages;
  }

  public void setSoftwareImages(AppPkgSWImageInfo softwareImages) {
    this.softwareImages = softwareImages;
  }

  public AppPkgInfo additionalArtifacts(AppPkgArtifactInfo additionalArtifacts) {
    this.additionalArtifacts = additionalArtifacts;
    return this;
  }

  /**
   * Get additionalArtifacts
   * @return additionalArtifacts
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public AppPkgArtifactInfo getAdditionalArtifacts() {
    return additionalArtifacts;
  }

  public void setAdditionalArtifacts(AppPkgArtifactInfo additionalArtifacts) {
    this.additionalArtifacts = additionalArtifacts;
  }

  public AppPkgInfo onboardingState(OnboardingState onboardingState) {
    this.onboardingState = onboardingState;
    return this;
  }

  /**
   * Get onboardingState
   * @return onboardingState
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public OnboardingState getOnboardingState() {
    return onboardingState;
  }

  public void setOnboardingState(OnboardingState onboardingState) {
    this.onboardingState = onboardingState;
  }

  public AppPkgInfo operationalState(AppPkgOperationalState operationalState) {
    this.operationalState = operationalState;
    return this;
  }

  /**
   * Get operationalState
   * @return operationalState
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public AppPkgOperationalState getOperationalState() {
    return operationalState;
  }

  public void setOperationalState(AppPkgOperationalState operationalState) {
    this.operationalState = operationalState;
  }

  public AppPkgInfo usageState(UsageState usageState) {
    this.usageState = usageState;
    return this;
  }

  /**
   * Get usageState
   * @return usageState
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public UsageState getUsageState() {
    return usageState;
  }

  public void setUsageState(UsageState usageState) {
    this.usageState = usageState;
  }

  public AppPkgInfo userDefinedData(KeyValuePairs userDefinedData) {
    this.userDefinedData = userDefinedData;
    return this;
  }

  /**
   * Get userDefinedData
   * @return userDefinedData
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public KeyValuePairs getUserDefinedData() {
    return userDefinedData;
  }

  public void setUserDefinedData(KeyValuePairs userDefinedData) {
    this.userDefinedData = userDefinedData;
  }

  public AppPkgInfo _links(AppPkgInfoLinks _links) {
    this._links = _links;
    return this;
  }

  /**
   * Get _links
   * @return _links
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public AppPkgInfoLinks getLinks() {
    return _links;
  }

  public void setLinks(AppPkgInfoLinks _links) {
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
    AppPkgInfo appPkgInfo = (AppPkgInfo) o;
    return Objects.equals(this.id, appPkgInfo.id) &&
        Objects.equals(this.appDId, appPkgInfo.appDId) &&
        Objects.equals(this.appProvider, appPkgInfo.appProvider) &&
        Objects.equals(this.appName, appPkgInfo.appName) &&
        Objects.equals(this.appSoftwareVersion, appPkgInfo.appSoftwareVersion) &&
        Objects.equals(this.appDVersion, appPkgInfo.appDVersion) &&
        Objects.equals(this.checksum, appPkgInfo.checksum) &&
        Objects.equals(this.softwareImages, appPkgInfo.softwareImages) &&
        Objects.equals(this.additionalArtifacts, appPkgInfo.additionalArtifacts) &&
        Objects.equals(this.onboardingState, appPkgInfo.onboardingState) &&
        Objects.equals(this.operationalState, appPkgInfo.operationalState) &&
        Objects.equals(this.usageState, appPkgInfo.usageState) &&
        Objects.equals(this.userDefinedData, appPkgInfo.userDefinedData) &&
        Objects.equals(this._links, appPkgInfo._links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, appDId, appProvider, appName, appSoftwareVersion, appDVersion, checksum, softwareImages, additionalArtifacts, onboardingState, operationalState, usageState, userDefinedData, _links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AppPkgInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    appDId: ").append(toIndentedString(appDId)).append("\n");
    sb.append("    appProvider: ").append(toIndentedString(appProvider)).append("\n");
    sb.append("    appName: ").append(toIndentedString(appName)).append("\n");
    sb.append("    appSoftwareVersion: ").append(toIndentedString(appSoftwareVersion)).append("\n");
    sb.append("    appDVersion: ").append(toIndentedString(appDVersion)).append("\n");
    sb.append("    checksum: ").append(toIndentedString(checksum)).append("\n");
    sb.append("    softwareImages: ").append(toIndentedString(softwareImages)).append("\n");
    sb.append("    additionalArtifacts: ").append(toIndentedString(additionalArtifacts)).append("\n");
    sb.append("    onboardingState: ").append(toIndentedString(onboardingState)).append("\n");
    sb.append("    operationalState: ").append(toIndentedString(operationalState)).append("\n");
    sb.append("    usageState: ").append(toIndentedString(usageState)).append("\n");
    sb.append("    userDefinedData: ").append(toIndentedString(userDefinedData)).append("\n");
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
