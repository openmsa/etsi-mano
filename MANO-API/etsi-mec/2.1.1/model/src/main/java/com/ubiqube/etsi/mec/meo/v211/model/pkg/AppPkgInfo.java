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
package com.ubiqube.etsi.mec.meo.v211.model.pkg;

import java.util.Map;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.model.WebEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * &#x27;The data type AppPkgInfo represents the parameters for an application package resource&#x27;
 */
@ApiModel(description = "'The data type AppPkgInfo represents the parameters for an application package resource'")
@Validated
public class AppPkgInfo extends WebEntity<AppPkgInfo> {

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
	private Map<String, String> userDefinedData = null;

	public AppPkgInfo appDId(final String appDId) {
		this.appDId = appDId;
		return this;
	}

	/**
	 * Get appDId
	 *
	 * @return appDId
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	public String getAppDId() {
		return appDId;
	}

	public void setAppDId(final String appDId) {
		this.appDId = appDId;
	}

	public AppPkgInfo appProvider(final String appProvider) {
		this.appProvider = appProvider;
		return this;
	}

	/**
	 * Get appProvider
	 *
	 * @return appProvider
	 **/
	@ApiModelProperty(value = "")

	public String getAppProvider() {
		return appProvider;
	}

	public void setAppProvider(final String appProvider) {
		this.appProvider = appProvider;
	}

	public AppPkgInfo appName(final String appName) {
		this.appName = appName;
		return this;
	}

	/**
	 * Get appName
	 *
	 * @return appName
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	public String getAppName() {
		return appName;
	}

	public void setAppName(final String appName) {
		this.appName = appName;
	}

	public AppPkgInfo appSoftwareVersion(final String appSoftwareVersion) {
		this.appSoftwareVersion = appSoftwareVersion;
		return this;
	}

	/**
	 * Get appSoftwareVersion
	 *
	 * @return appSoftwareVersion
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	public String getAppSoftwareVersion() {
		return appSoftwareVersion;
	}

	public void setAppSoftwareVersion(final String appSoftwareVersion) {
		this.appSoftwareVersion = appSoftwareVersion;
	}

	public AppPkgInfo appDVersion(final String appDVersion) {
		this.appDVersion = appDVersion;
		return this;
	}

	/**
	 * Get appDVersion
	 *
	 * @return appDVersion
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	public String getAppDVersion() {
		return appDVersion;
	}

	public void setAppDVersion(final String appDVersion) {
		this.appDVersion = appDVersion;
	}

	public AppPkgInfo checksum(final Checksum checksum) {
		this.checksum = checksum;
		return this;
	}

	/**
	 * Get checksum
	 *
	 * @return checksum
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	@Valid
	public Checksum getChecksum() {
		return checksum;
	}

	public void setChecksum(final Checksum checksum) {
		this.checksum = checksum;
	}

	public AppPkgInfo softwareImages(final AppPkgSWImageInfo softwareImages) {
		this.softwareImages = softwareImages;
		return this;
	}

	/**
	 * Get softwareImages
	 *
	 * @return softwareImages
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	@Valid
	public AppPkgSWImageInfo getSoftwareImages() {
		return softwareImages;
	}

	public void setSoftwareImages(final AppPkgSWImageInfo softwareImages) {
		this.softwareImages = softwareImages;
	}

	public AppPkgInfo additionalArtifacts(final AppPkgArtifactInfo additionalArtifacts) {
		this.additionalArtifacts = additionalArtifacts;
		return this;
	}

	/**
	 * Get additionalArtifacts
	 *
	 * @return additionalArtifacts
	 **/
	@ApiModelProperty(value = "")

	@Valid
	public AppPkgArtifactInfo getAdditionalArtifacts() {
		return additionalArtifacts;
	}

	public void setAdditionalArtifacts(final AppPkgArtifactInfo additionalArtifacts) {
		this.additionalArtifacts = additionalArtifacts;
	}

	public AppPkgInfo onboardingState(final OnboardingState onboardingState) {
		this.onboardingState = onboardingState;
		return this;
	}

	/**
	 * Get onboardingState
	 *
	 * @return onboardingState
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	@Valid
	public OnboardingState getOnboardingState() {
		return onboardingState;
	}

	public void setOnboardingState(final OnboardingState onboardingState) {
		this.onboardingState = onboardingState;
	}

	public AppPkgInfo operationalState(final AppPkgOperationalState operationalState) {
		this.operationalState = operationalState;
		return this;
	}

	/**
	 * Get operationalState
	 *
	 * @return operationalState
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	@Valid
	public AppPkgOperationalState getOperationalState() {
		return operationalState;
	}

	public void setOperationalState(final AppPkgOperationalState operationalState) {
		this.operationalState = operationalState;
	}

	public AppPkgInfo usageState(final UsageState usageState) {
		this.usageState = usageState;
		return this;
	}

	/**
	 * Get usageState
	 *
	 * @return usageState
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	@Valid
	public UsageState getUsageState() {
		return usageState;
	}

	public void setUsageState(final UsageState usageState) {
		this.usageState = usageState;
	}

	public AppPkgInfo userDefinedData(final Map<String, String> userDefinedData) {
		this.userDefinedData = userDefinedData;
		return this;
	}

	/**
	 * Get userDefinedData
	 *
	 * @return userDefinedData
	 **/
	@ApiModelProperty(value = "")

	@Valid
	public Map<String, String> getUserDefinedData() {
		return userDefinedData;
	}

	public void setUserDefinedData(final Map<String, String> userDefinedData) {
		this.userDefinedData = userDefinedData;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final AppPkgInfo appPkgInfo = (AppPkgInfo) o;
		return // Objects.equals(this.getId(), appPkgInfo.getId()) &&
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
				Objects.equals(this.getLinks(), appPkgInfo.getLinks());
	}

	@Override
	public int hashCode() {
		return Objects.hash(/* getId() */ null, appDId, appProvider, appName, appSoftwareVersion, appDVersion, checksum, softwareImages, additionalArtifacts, onboardingState, operationalState, usageState, userDefinedData, getLinks());
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class AppPkgInfo {\n");

		// sb.append(" id: ").append(toIndentedString(getId())).append("\n");
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
		sb.append("    _links: ").append(toIndentedString(getLinks())).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces (except the first line).
	 */
	private String toIndentedString(final java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
