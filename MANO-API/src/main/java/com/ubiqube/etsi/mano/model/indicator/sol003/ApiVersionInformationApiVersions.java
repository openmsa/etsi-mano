package com.ubiqube.etsi.mano.model.indicator.sol003;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * ApiVersionInformationApiVersions
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-05-01T15:58:19.438+02:00")

public class ApiVersionInformationApiVersions {
	@JsonProperty("version")
	private String version = null;

	@JsonProperty("isDeprecated")
	private Boolean isDeprecated = null;

	@JsonProperty("retirementDate")
	private String retirementDate = null;

	public ApiVersionInformationApiVersions version(final String version) {
		this.version = version;
		return this;
	}

	/**
	 * Identifies a supported version. The value of the version attribute shall be a
	 * version identifier as specified in clause 9.1 (SOL013).
	 * 
	 * @return version
	 **/
	@ApiModelProperty(required = true, value = "Identifies a supported version. The value of the version attribute shall be a version identifier as specified in clause 9.1 (SOL013). ")
	@NotNull

	public String getVersion() {
		return version;
	}

	public void setVersion(final String version) {
		this.version = version;
	}

	public ApiVersionInformationApiVersions isDeprecated(final Boolean isDeprecated) {
		this.isDeprecated = isDeprecated;
		return this;
	}

	/**
	 * If such information is available, this attribute indicates whether use of the
	 * version signaled by the version attribute is deprecated (true) or not
	 * (false). A deprecated version is still supported by the API producer but is
	 * recommended not to be used any longer. When a version is no longer supported,
	 * it does not appear in the response body.
	 * 
	 * @return isDeprecated
	 **/
	@ApiModelProperty(value = "If such information is available, this attribute indicates whether use of the version signaled by the version attribute is deprecated (true) or not (false). A deprecated version is still supported by the API producer but is recommended not to be used any longer. When a version is no longer supported, it does not appear in the response body. ")

	public Boolean isIsDeprecated() {
		return isDeprecated;
	}

	public void setIsDeprecated(final Boolean isDeprecated) {
		this.isDeprecated = isDeprecated;
	}

	public ApiVersionInformationApiVersions retirementDate(final String retirementDate) {
		this.retirementDate = retirementDate;
		return this;
	}

	/**
	 * The date and time after which the API version will no longer be supported.
	 * This attribute may be included if the value of the isDeprecated attribute is
	 * set to true and shall be absent otherwise.
	 * 
	 * @return retirementDate
	 **/
	@ApiModelProperty(value = "The date and time after which the API version will no longer be supported. This attribute may be included if the value of the isDeprecated attribute is set to true and shall be absent otherwise. ")

	public String getRetirementDate() {
		return retirementDate;
	}

	public void setRetirementDate(final String retirementDate) {
		this.retirementDate = retirementDate;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final ApiVersionInformationApiVersions apiVersionInformationApiVersions = (ApiVersionInformationApiVersions) o;
		return Objects.equals(this.version, apiVersionInformationApiVersions.version) &&
				Objects.equals(this.isDeprecated, apiVersionInformationApiVersions.isDeprecated) &&
				Objects.equals(this.retirementDate, apiVersionInformationApiVersions.retirementDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(version, isDeprecated, retirementDate);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class ApiVersionInformationApiVersions {\n");

		sb.append("    version: ").append(toIndentedString(version)).append("\n");
		sb.append("    isDeprecated: ").append(toIndentedString(isDeprecated)).append("\n");
		sb.append("    retirementDate: ").append(toIndentedString(retirementDate)).append("\n");
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
