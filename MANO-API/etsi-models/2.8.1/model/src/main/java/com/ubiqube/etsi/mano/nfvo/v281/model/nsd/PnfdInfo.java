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
package com.ubiqube.etsi.mano.nfvo.v281.model.nsd;

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
import com.ubiqube.etsi.mano.em.v281.model.vnfconfig.ProblemDetails;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents a response for the query PNFD operation.
 */
@ApiModel(description = "This type represents a response for the query PNFD operation. ")
@Validated

public class PnfdInfo {
	@JsonProperty("id")
	private String id = null;

	@JsonProperty("pnfdId")
	private String pnfdId = null;

	@JsonProperty("pnfdName")
	private String pnfdName = null;

	@JsonProperty("pnfdersion")
	private String pnfdersion = null;

	@JsonProperty("pnfdProvider")
	private String pnfdProvider = null;

	@JsonProperty("pnfdInvariantId")
	private String pnfdInvariantId = null;

	/**
	 * Signals the security option used by the PNFD archive as defined in clause 5.1
	 * of ETSI GS NFV SOL 004. Valid values: OPTION_1, OPTION_2
	 */
	public enum ArchiveSecurityOptionEnum {
		_1("OPTION_1"),

		_2("OPTION_2");

		private final String value;

		ArchiveSecurityOptionEnum(final String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static ArchiveSecurityOptionEnum fromValue(final String text) {
			for (final ArchiveSecurityOptionEnum b : ArchiveSecurityOptionEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	@JsonProperty("archiveSecurityOption")
	private ArchiveSecurityOptionEnum archiveSecurityOption = null;

	@JsonProperty("signingCertificate")
	private String signingCertificate = null;

	@JsonProperty("artifacts")
	@Valid
	private List<PnfdArchiveArtifactInfo> artifacts = null;

	@JsonProperty("pnfdOnboardingState")
	private PnfdOnboardingStateType pnfdOnboardingState = null;

	@JsonProperty("onboardingFailureDetails")
	private ProblemDetails onboardingFailureDetails = null;

	@JsonProperty("pnfdUsageState")
	private PnfdUsageStateType pnfdUsageState = null;

	@JsonProperty("userDefinedData")
	private Map<String, String> userDefinedData = null;

	@JsonProperty("_links")
	private PnfdInfoLinks links = null;

	public PnfdInfo id(final String id) {
		this.id = id;
		return this;
	}

	/**
	 * Identifier of the on-boarded individual PNF descriptor resource. This
	 * identifier is allocated by the NFVO.
	 *
	 * @return id
	 **/
	@ApiModelProperty(required = true, value = "Identifier of the on-boarded individual PNF descriptor resource. This identifier is allocated by the NFVO. ")
	@NotNull

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public PnfdInfo pnfdId(final String pnfdId) {
		this.pnfdId = pnfdId;
		return this;
	}

	/**
	 * This identifier, which is managed by the PNFD designer, identifies the PNFD
	 * in a globally unique way. It is copied from the PNFD content and shall be
	 * present after the PNFD content is on-boarded.
	 *
	 * @return pnfdId
	 **/
	@ApiModelProperty(value = "This identifier, which is managed by the PNFD designer, identifies the PNFD in a globally unique way. It is copied from the PNFD content and shall be present after the PNFD content is on-boarded. ")

	public String getPnfdId() {
		return pnfdId;
	}

	public void setPnfdId(final String pnfdId) {
		this.pnfdId = pnfdId;
	}

	public PnfdInfo pnfdName(final String pnfdName) {
		this.pnfdName = pnfdName;
		return this;
	}

	/**
	 * Name of the on-boarded PNFD. This information is copied from the PNFD content
	 * and shall be present after the PNFD content is on-boarded.
	 *
	 * @return pnfdName
	 **/
	@ApiModelProperty(value = "Name of the on-boarded PNFD. This information is copied from the PNFD content and shall be present after the PNFD content is on-boarded. ")

	public String getPnfdName() {
		return pnfdName;
	}

	public void setPnfdName(final String pnfdName) {
		this.pnfdName = pnfdName;
	}

	public PnfdInfo pnfdersion(final String pnfdersion) {
		this.pnfdersion = pnfdersion;
		return this;
	}

	/**
	 * Get pnfdersion
	 *
	 * @return pnfdersion
	 **/
	@ApiModelProperty(value = "")

	public String getPnfdersion() {
		return pnfdersion;
	}

	public void setPnfdersion(final String pnfdersion) {
		this.pnfdersion = pnfdersion;
	}

	public PnfdInfo pnfdProvider(final String pnfdProvider) {
		this.pnfdProvider = pnfdProvider;
		return this;
	}

	/**
	 * Provider of the on-boarded PNFD. This information is copied from the PNFD
	 * content and shall be present after the PNFD content is on-boarded.
	 *
	 * @return pnfdProvider
	 **/
	@ApiModelProperty(value = "Provider of the on-boarded PNFD. This information is copied from the PNFD content and shall be present after the PNFD content is on-boarded. ")

	public String getPnfdProvider() {
		return pnfdProvider;
	}

	public void setPnfdProvider(final String pnfdProvider) {
		this.pnfdProvider = pnfdProvider;
	}

	public PnfdInfo pnfdInvariantId(final String pnfdInvariantId) {
		this.pnfdInvariantId = pnfdInvariantId;
		return this;
	}

	/**
	 * Identifies a PNFD in a version independent manner. This attribute is
	 * invariant across versions of PNFD.
	 *
	 * @return pnfdInvariantId
	 **/
	@ApiModelProperty(value = "Identifies a PNFD in a version independent manner. This attribute is invariant across versions of PNFD. ")

	public String getPnfdInvariantId() {
		return pnfdInvariantId;
	}

	public void setPnfdInvariantId(final String pnfdInvariantId) {
		this.pnfdInvariantId = pnfdInvariantId;
	}

	public PnfdInfo archiveSecurityOption(final ArchiveSecurityOptionEnum archiveSecurityOption) {
		this.archiveSecurityOption = archiveSecurityOption;
		return this;
	}

	/**
	 * Signals the security option used by the PNFD archive as defined in clause 5.1
	 * of ETSI GS NFV SOL 004. Valid values: OPTION_1, OPTION_2
	 *
	 * @return archiveSecurityOption
	 **/
	@ApiModelProperty(value = "Signals the security option used by the PNFD archive as defined in clause 5.1 of ETSI GS NFV SOL 004. Valid values: OPTION_1, OPTION_2 ")

	public ArchiveSecurityOptionEnum getArchiveSecurityOption() {
		return archiveSecurityOption;
	}

	public void setArchiveSecurityOption(final ArchiveSecurityOptionEnum archiveSecurityOption) {
		this.archiveSecurityOption = archiveSecurityOption;
	}

	public PnfdInfo signingCertificate(final String signingCertificate) {
		this.signingCertificate = signingCertificate;
		return this;
	}

	/**
	 * The singleton signing certificate if it is included as a file in the PNFD
	 * archive.
	 *
	 * @return signingCertificate
	 **/
	@ApiModelProperty(value = "The singleton signing certificate if it is included as a file in the PNFD archive. ")

	public String getSigningCertificate() {
		return signingCertificate;
	}

	public void setSigningCertificate(final String signingCertificate) {
		this.signingCertificate = signingCertificate;
	}

	public PnfdInfo artifacts(final List<PnfdArchiveArtifactInfo> artifacts) {
		this.artifacts = artifacts;
		return this;
	}

	public PnfdInfo addArtifactsItem(final PnfdArchiveArtifactInfo artifactsItem) {
		if (this.artifacts == null) {
			this.artifacts = new ArrayList<>();
		}
		this.artifacts.add(artifactsItem);
		return this;
	}

	/**
	 * Information about PNFD archive artifacts contained in the PNFD archive. This
	 * attribute shall not be present before the PNFD archive content is on-boarded.
	 * Otherwise, this attribute shall be present if the PNFD archive contains
	 * artifacts.
	 *
	 * @return artifacts
	 **/
	@ApiModelProperty(value = "Information about PNFD archive artifacts contained in the PNFD archive. This attribute shall not be present before the PNFD archive content is on-boarded. Otherwise, this attribute shall be present if the PNFD archive contains artifacts. ")

	@Valid

	public List<PnfdArchiveArtifactInfo> getArtifacts() {
		return artifacts;
	}

	public void setArtifacts(final List<PnfdArchiveArtifactInfo> artifacts) {
		this.artifacts = artifacts;
	}

	public PnfdInfo pnfdOnboardingState(final PnfdOnboardingStateType pnfdOnboardingState) {
		this.pnfdOnboardingState = pnfdOnboardingState;
		return this;
	}

	/**
	 * On-boarding state of the individual PNF descriptor resource.
	 *
	 * @return pnfdOnboardingState
	 **/
	@ApiModelProperty(required = true, value = "On-boarding state of the individual PNF descriptor resource. ")
	@NotNull

	@Valid

	public PnfdOnboardingStateType getPnfdOnboardingState() {
		return pnfdOnboardingState;
	}

	public void setPnfdOnboardingState(final PnfdOnboardingStateType pnfdOnboardingState) {
		this.pnfdOnboardingState = pnfdOnboardingState;
	}

	public PnfdInfo onboardingFailureDetails(final ProblemDetails onboardingFailureDetails) {
		this.onboardingFailureDetails = onboardingFailureDetails;
		return this;
	}

	/**
	 * Failure details of current on-boarding procedure. See clause 6.3 of ETSI GS
	 * NFV-SOL 013 for the details of \"ProblemDetails\" structure. It shall be
	 * present when the pnfdOnboardingState attribute is \"ERROR\", i.e. the
	 * uploading or processing has failed in the NFVO, unless it has been requested
	 * to be excluded via an attribute selector.
	 *
	 * @return onboardingFailureDetails
	 **/
	@ApiModelProperty(value = "Failure details of current on-boarding procedure. See clause 6.3 of ETSI GS NFV-SOL 013 for the details of \"ProblemDetails\" structure. It shall be present when the pnfdOnboardingState attribute is \"ERROR\", i.e. the uploading or processing has failed in the NFVO, unless it has been requested to be excluded via an attribute selector. ")

	@Valid

	public ProblemDetails getOnboardingFailureDetails() {
		return onboardingFailureDetails;
	}

	public void setOnboardingFailureDetails(final ProblemDetails onboardingFailureDetails) {
		this.onboardingFailureDetails = onboardingFailureDetails;
	}

	public PnfdInfo pnfdUsageState(final PnfdUsageStateType pnfdUsageState) {
		this.pnfdUsageState = pnfdUsageState;
		return this;
	}

	/**
	 * Usage state of the individual PNF descriptor resource.
	 *
	 * @return pnfdUsageState
	 **/
	@ApiModelProperty(required = true, value = "Usage state of the individual PNF descriptor resource. ")
	@NotNull

	@Valid

	public PnfdUsageStateType getPnfdUsageState() {
		return pnfdUsageState;
	}

	public void setPnfdUsageState(final PnfdUsageStateType pnfdUsageState) {
		this.pnfdUsageState = pnfdUsageState;
	}

	public PnfdInfo userDefinedData(final Map<String, String> userDefinedData) {
		this.userDefinedData = userDefinedData;
		return this;
	}

	/**
	 * User defined data for the individual PNF descriptor resource. This attribute
	 * can be modified with the PATCH method.
	 *
	 * @return userDefinedData
	 **/
	@ApiModelProperty(value = "User defined data for the individual PNF descriptor resource. This attribute can be modified with the PATCH method. ")

	@Valid

	public Map<String, String> getUserDefinedData() {
		return userDefinedData;
	}

	public void setUserDefinedData(final Map<String, String> userDefinedData) {
		this.userDefinedData = userDefinedData;
	}

	public PnfdInfo links(final PnfdInfoLinks links) {
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

	public PnfdInfoLinks getLinks() {
		return links;
	}

	public void setLinks(final PnfdInfoLinks links) {
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
		final PnfdInfo pnfdInfo = (PnfdInfo) o;
		return Objects.equals(this.id, pnfdInfo.id) &&
				Objects.equals(this.pnfdId, pnfdInfo.pnfdId) &&
				Objects.equals(this.pnfdName, pnfdInfo.pnfdName) &&
				Objects.equals(this.pnfdersion, pnfdInfo.pnfdersion) &&
				Objects.equals(this.pnfdProvider, pnfdInfo.pnfdProvider) &&
				Objects.equals(this.pnfdInvariantId, pnfdInfo.pnfdInvariantId) &&
				Objects.equals(this.archiveSecurityOption, pnfdInfo.archiveSecurityOption) &&
				Objects.equals(this.signingCertificate, pnfdInfo.signingCertificate) &&
				Objects.equals(this.artifacts, pnfdInfo.artifacts) &&
				Objects.equals(this.pnfdOnboardingState, pnfdInfo.pnfdOnboardingState) &&
				Objects.equals(this.onboardingFailureDetails, pnfdInfo.onboardingFailureDetails) &&
				Objects.equals(this.pnfdUsageState, pnfdInfo.pnfdUsageState) &&
				Objects.equals(this.userDefinedData, pnfdInfo.userDefinedData) &&
				Objects.equals(this.links, pnfdInfo.links);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, pnfdId, pnfdName, pnfdersion, pnfdProvider, pnfdInvariantId, archiveSecurityOption, signingCertificate, artifacts, pnfdOnboardingState, onboardingFailureDetails, pnfdUsageState, userDefinedData, links);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class PnfdInfo {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    pnfdId: ").append(toIndentedString(pnfdId)).append("\n");
		sb.append("    pnfdName: ").append(toIndentedString(pnfdName)).append("\n");
		sb.append("    pnfdersion: ").append(toIndentedString(pnfdersion)).append("\n");
		sb.append("    pnfdProvider: ").append(toIndentedString(pnfdProvider)).append("\n");
		sb.append("    pnfdInvariantId: ").append(toIndentedString(pnfdInvariantId)).append("\n");
		sb.append("    archiveSecurityOption: ").append(toIndentedString(archiveSecurityOption)).append("\n");
		sb.append("    signingCertificate: ").append(toIndentedString(signingCertificate)).append("\n");
		sb.append("    artifacts: ").append(toIndentedString(artifacts)).append("\n");
		sb.append("    pnfdOnboardingState: ").append(toIndentedString(pnfdOnboardingState)).append("\n");
		sb.append("    onboardingFailureDetails: ").append(toIndentedString(onboardingFailureDetails)).append("\n");
		sb.append("    pnfdUsageState: ").append(toIndentedString(pnfdUsageState)).append("\n");
		sb.append("    userDefinedData: ").append(toIndentedString(userDefinedData)).append("\n");
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
