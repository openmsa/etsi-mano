package com.ubiqube.etsi.mano.nfvo.v331.model.vnf;

import java.time.OffsetDateTime;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * ApiVersionInformationApiVersions
 */
@Validated
public class ApiVersionInformationApiVersions {
	@JsonProperty("version")
	private String version = null;

	@JsonProperty("isDeprecated")
	private Boolean isDeprecated = null;

	@JsonProperty("retirementDate")
	private OffsetDateTime retirementDate = null;

	public ApiVersionInformationApiVersions version(final String version) {
		this.version = version;
		return this;
	}

	/**
	 * Identifies a supported version. The value of the version attribute shall be a
	 * version identifier as specified in clause 4.6.1.
	 *
	 * @return version
	 **/
	@ApiModelProperty(required = true, value = "Identifies a supported version. The value of the version attribute shall be a version identifier as specified in clause 4.6.1. ")
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
	 * Get isDeprecated
	 *
	 * @return isDeprecated
	 **/
	@ApiModelProperty(value = "")

	public Boolean getIsDeprecated() {
		return isDeprecated;
	}

	public void setIsDeprecated(final Boolean isDeprecated) {
		this.isDeprecated = isDeprecated;
	}

	public ApiVersionInformationApiVersions retirementDate(final OffsetDateTime retirementDate) {
		this.retirementDate = retirementDate;
		return this;
	}

	/**
	 * Get retirementDate
	 *
	 * @return retirementDate
	 **/
	@ApiModelProperty(value = "")

	@Valid
	public OffsetDateTime getRetirementDate() {
		return retirementDate;
	}

	public void setRetirementDate(final OffsetDateTime retirementDate) {
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
