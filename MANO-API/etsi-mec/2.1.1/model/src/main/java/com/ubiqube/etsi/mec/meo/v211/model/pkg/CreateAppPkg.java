package com.ubiqube.etsi.mec.meo.v211.model.pkg;

import java.util.Map;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * CreateAppPkg
 */
@Validated
public class CreateAppPkg {
	@JsonProperty("appPkgName")
	private String appPkgName = null;

	@JsonProperty("appPkgPath")
	private String appPkgPath = null;

	@JsonProperty("appPkgVersion")
	private String appPkgVersion = null;

	@JsonProperty("appProvider")
	private String appProvider = null;

	@JsonProperty("checksum")
	private Checksum checksum = null;

	@JsonProperty("userDefinedData")
	private Map<String, String> userDefinedData = null;

	public CreateAppPkg appPkgName(final String appPkgName) {
		this.appPkgName = appPkgName;
		return this;
	}

	/**
	 * Name of the application package to be onboarded.
	 *
	 * @return appPkgName
	 **/
	@ApiModelProperty(required = true, value = "Name of the application package to be onboarded.")
	@NotNull

	public String getAppPkgName() {
		return appPkgName;
	}

	public void setAppPkgName(final String appPkgName) {
		this.appPkgName = appPkgName;
	}

	public CreateAppPkg appPkgPath(final String appPkgPath) {
		this.appPkgPath = appPkgPath;
		return this;
	}

	/**
	 * Get appPkgPath
	 *
	 * @return appPkgPath
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	public String getAppPkgPath() {
		return appPkgPath;
	}

	public void setAppPkgPath(final String appPkgPath) {
		this.appPkgPath = appPkgPath;
	}

	public CreateAppPkg appPkgVersion(final String appPkgVersion) {
		this.appPkgVersion = appPkgVersion;
		return this;
	}

	/**
	 * Version of the application package to be onboarded. The appPkgName with appPkgVersion can be used to uniquely identify the application package.
	 *
	 * @return appPkgVersion
	 **/
	@ApiModelProperty(required = true, value = "Version of the application package to be onboarded. The appPkgName with appPkgVersion can be used to uniquely identify the application package.")
	@NotNull

	public String getAppPkgVersion() {
		return appPkgVersion;
	}

	public void setAppPkgVersion(final String appPkgVersion) {
		this.appPkgVersion = appPkgVersion;
	}

	public CreateAppPkg appProvider(final String appProvider) {
		this.appProvider = appProvider;
		return this;
	}

	/**
	 * The provider's name of the application package to be onboarded.
	 *
	 * @return appProvider
	 **/
	@ApiModelProperty(value = "The provider's name of the application package to be onboarded.")

	public String getAppProvider() {
		return appProvider;
	}

	public void setAppProvider(final String appProvider) {
		this.appProvider = appProvider;
	}

	public CreateAppPkg checksum(final Checksum checksum) {
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

	public CreateAppPkg userDefinedData(final Map<String, String> userDefinedData) {
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
		final CreateAppPkg createAppPkg = (CreateAppPkg) o;
		return Objects.equals(this.appPkgName, createAppPkg.appPkgName) &&
				Objects.equals(this.appPkgPath, createAppPkg.appPkgPath) &&
				Objects.equals(this.appPkgVersion, createAppPkg.appPkgVersion) &&
				Objects.equals(this.appProvider, createAppPkg.appProvider) &&
				Objects.equals(this.checksum, createAppPkg.checksum) &&
				Objects.equals(this.userDefinedData, createAppPkg.userDefinedData);
	}

	@Override
	public int hashCode() {
		return Objects.hash(appPkgName, appPkgPath, appPkgVersion, appProvider, checksum, userDefinedData);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class CreateAppPkg {\n");

		sb.append("    appPkgName: ").append(toIndentedString(appPkgName)).append("\n");
		sb.append("    appPkgPath: ").append(toIndentedString(appPkgPath)).append("\n");
		sb.append("    appPkgVersion: ").append(toIndentedString(appPkgVersion)).append("\n");
		sb.append("    appProvider: ").append(toIndentedString(appProvider)).append("\n");
		sb.append("    checksum: ").append(toIndentedString(checksum)).append("\n");
		sb.append("    userDefinedData: ").append(toIndentedString(userDefinedData)).append("\n");
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
