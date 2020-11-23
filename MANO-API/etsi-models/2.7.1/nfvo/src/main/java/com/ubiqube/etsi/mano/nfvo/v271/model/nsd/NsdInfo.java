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

package com.ubiqube.etsi.mano.nfvo.v271.model.nsd;

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
 * This type represents a response for the query NSD operation.
 */
@ApiModel(description = "This type represents a response for the query NSD operation. ")
@Validated
public class NsdInfo {
	@JsonProperty("id")
	private String id = null;

	@JsonProperty("nsdId")
	private String nsdId = null;

	@JsonProperty("nsdName")
	private String nsdName = null;

	@JsonProperty("nsdVersion")
	private String nsdVersion = null;

	@JsonProperty("nsdDesigner")
	private String nsdDesigner = null;

	@JsonProperty("nsdInvariantId")
	private String nsdInvariantId = null;

	@JsonProperty("vnfPkgIds")
	@Valid
	private List<String> vnfPkgIds = null;

	@JsonProperty("pnfdInfoIds")
	@Valid
	private List<String> pnfdInfoIds = null;

	@JsonProperty("nestedNsdInfoIds")
	@Valid
	private List<String> nestedNsdInfoIds = null;

	/**
	 * Signals the security option used by the NSD archive as defined in clause 5.1
	 * of ETSI GS NFV SOL 007. Valid values: OPTION_1, OPTION_2
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
	private List<NsdArchiveArtifactInfo> artifacts = null;

	@JsonProperty("nsdOnboardingState")
	private NsdOnboardingStateType nsdOnboardingState = null;

	@JsonProperty("onboardingFailureDetails")
	private ProblemDetails onboardingFailureDetails = null;

	@JsonProperty("nsdOperationalState")
	private NsdOperationalStateType nsdOperationalState = null;

	@JsonProperty("nsdUsageState")
	private NsdUsageStateType nsdUsageState = null;

	@JsonProperty("userDefinedData")
	private Map<String, Object> userDefinedData = null;

	@JsonProperty("_links")
	private NsdInfoLinks _links = null;

	public NsdInfo id(final String id) {
		this.id = id;
		return this;
	}

	/**
	 * Get id
	 *
	 * @return id
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public NsdInfo nsdId(final String nsdId) {
		this.nsdId = nsdId;
		return this;
	}

	/**
	 * Get nsdId
	 *
	 * @return nsdId
	 **/
	@ApiModelProperty(value = "")

	public String getNsdId() {
		return nsdId;
	}

	public void setNsdId(final String nsdId) {
		this.nsdId = nsdId;
	}

	public NsdInfo nsdName(final String nsdName) {
		this.nsdName = nsdName;
		return this;
	}

	/**
	 * Name of the on boarded NSD. This information is copied from the NSD content
	 * and shall be present after the NSD content is on-boarded.
	 *
	 * @return nsdName
	 **/
	@ApiModelProperty(value = "Name of the on boarded NSD. This information is copied from the NSD content and shall be present after the NSD content is on-boarded. ")

	public String getNsdName() {
		return nsdName;
	}

	public void setNsdName(final String nsdName) {
		this.nsdName = nsdName;
	}

	public NsdInfo nsdVersion(final String nsdVersion) {
		this.nsdVersion = nsdVersion;
		return this;
	}

	/**
	 * Get nsdVersion
	 *
	 * @return nsdVersion
	 **/
	@ApiModelProperty(value = "")

	public String getNsdVersion() {
		return nsdVersion;
	}

	public void setNsdVersion(final String nsdVersion) {
		this.nsdVersion = nsdVersion;
	}

	public NsdInfo nsdDesigner(final String nsdDesigner) {
		this.nsdDesigner = nsdDesigner;
		return this;
	}

	/**
	 * Designer of the on-boarded NSD. This information is copied from the NSD
	 * content and shall be present after the NSD content is on-boarded.
	 *
	 * @return nsdDesigner
	 **/
	@ApiModelProperty(value = "Designer of the on-boarded NSD. This information is copied from the NSD content and shall be present after the NSD content is on-boarded. ")

	public String getNsdDesigner() {
		return nsdDesigner;
	}

	public void setNsdDesigner(final String nsdDesigner) {
		this.nsdDesigner = nsdDesigner;
	}

	public NsdInfo nsdInvariantId(final String nsdInvariantId) {
		this.nsdInvariantId = nsdInvariantId;
		return this;
	}

	/**
	 * Get nsdInvariantId
	 *
	 * @return nsdInvariantId
	 **/
	@ApiModelProperty(value = "")

	public String getNsdInvariantId() {
		return nsdInvariantId;
	}

	public void setNsdInvariantId(final String nsdInvariantId) {
		this.nsdInvariantId = nsdInvariantId;
	}

	public NsdInfo vnfPkgIds(final List<String> vnfPkgIds) {
		this.vnfPkgIds = vnfPkgIds;
		return this;
	}

	public NsdInfo addVnfPkgIdsItem(final String vnfPkgIdsItem) {
		if (this.vnfPkgIds == null) {
			this.vnfPkgIds = new ArrayList<>();
		}
		this.vnfPkgIds.add(vnfPkgIdsItem);
		return this;
	}

	/**
	 * Identifies the VNF package for the VNFD referenced by the on-boarded NS
	 * descriptor resource.
	 *
	 * @return vnfPkgIds
	 **/
	@ApiModelProperty(value = "Identifies the VNF package for the VNFD referenced by the on-boarded NS descriptor resource. ")

	public List<String> getVnfPkgIds() {
		return vnfPkgIds;
	}

	public void setVnfPkgIds(final List<String> vnfPkgIds) {
		this.vnfPkgIds = vnfPkgIds;
	}

	public NsdInfo pnfdInfoIds(final List<String> pnfdInfoIds) {
		this.pnfdInfoIds = pnfdInfoIds;
		return this;
	}

	public NsdInfo addPnfdInfoIdsItem(final String pnfdInfoIdsItem) {
		if (this.pnfdInfoIds == null) {
			this.pnfdInfoIds = new ArrayList<>();
		}
		this.pnfdInfoIds.add(pnfdInfoIdsItem);
		return this;
	}

	/**
	 * Identifies the PnfdInfo element for the PNFD referenced by the on-boarded NS
	 * descriptor resource.
	 *
	 * @return pnfdInfoIds
	 **/
	@ApiModelProperty(value = "Identifies the PnfdInfo element for the PNFD referenced by the on-boarded NS descriptor resource. ")

	public List<String> getPnfdInfoIds() {
		return pnfdInfoIds;
	}

	public void setPnfdInfoIds(final List<String> pnfdInfoIds) {
		this.pnfdInfoIds = pnfdInfoIds;
	}

	public NsdInfo nestedNsdInfoIds(final List<String> nestedNsdInfoIds) {
		this.nestedNsdInfoIds = nestedNsdInfoIds;
		return this;
	}

	public NsdInfo addNestedNsdInfoIdsItem(final String nestedNsdInfoIdsItem) {
		if (this.nestedNsdInfoIds == null) {
			this.nestedNsdInfoIds = new ArrayList<>();
		}
		this.nestedNsdInfoIds.add(nestedNsdInfoIdsItem);
		return this;
	}

	/**
	 * Identifies the NsdInfo element for the nested NSD referenced by the
	 * on-boarded NS descriptor resource.
	 *
	 * @return nestedNsdInfoIds
	 **/
	@ApiModelProperty(value = "Identifies the NsdInfo element for the nested NSD referenced by the on-boarded NS descriptor resource. ")

	public List<String> getNestedNsdInfoIds() {
		return nestedNsdInfoIds;
	}

	public void setNestedNsdInfoIds(final List<String> nestedNsdInfoIds) {
		this.nestedNsdInfoIds = nestedNsdInfoIds;
	}

	public NsdInfo archiveSecurityOption(final ArchiveSecurityOptionEnum archiveSecurityOption) {
		this.archiveSecurityOption = archiveSecurityOption;
		return this;
	}

	/**
	 * Signals the security option used by the NSD archive as defined in clause 5.1
	 * of ETSI GS NFV SOL 007. Valid values: OPTION_1, OPTION_2
	 *
	 * @return archiveSecurityOption
	 **/
	@ApiModelProperty(value = "Signals the security option used by the NSD archive as defined in clause 5.1 of ETSI GS NFV SOL 007. Valid values: OPTION_1, OPTION_2 ")

	public ArchiveSecurityOptionEnum getArchiveSecurityOption() {
		return archiveSecurityOption;
	}

	public void setArchiveSecurityOption(final ArchiveSecurityOptionEnum archiveSecurityOption) {
		this.archiveSecurityOption = archiveSecurityOption;
	}

	public NsdInfo signingCertificate(final String signingCertificate) {
		this.signingCertificate = signingCertificate;
		return this;
	}

	/**
	 * Get signingCertificate
	 *
	 * @return signingCertificate
	 **/
	@ApiModelProperty(value = "")

	public String getSigningCertificate() {
		return signingCertificate;
	}

	public void setSigningCertificate(final String signingCertificate) {
		this.signingCertificate = signingCertificate;
	}

	public NsdInfo artifacts(final List<NsdArchiveArtifactInfo> artifacts) {
		this.artifacts = artifacts;
		return this;
	}

	public NsdInfo addArtifactsItem(final NsdArchiveArtifactInfo artifactsItem) {
		if (this.artifacts == null) {
			this.artifacts = new ArrayList<>();
		}
		this.artifacts.add(artifactsItem);
		return this;
	}

	/**
	 * Information about NSD archive artifacts contained in the NSD archive. This
	 * attribute shall not be present before the NSD archive content is on-boarded.
	 * Otherwise, this attribute shall be present if the NSD archive contains
	 * artifacts.
	 *
	 * @return artifacts
	 **/
	@ApiModelProperty(value = "Information about NSD archive artifacts contained in the NSD archive. This attribute shall not be present before the NSD archive content is on-boarded. Otherwise, this attribute shall be present if the NSD archive contains artifacts. ")
	@Valid
	public List<NsdArchiveArtifactInfo> getArtifacts() {
		return artifacts;
	}

	public void setArtifacts(final List<NsdArchiveArtifactInfo> artifacts) {
		this.artifacts = artifacts;
	}

	public NsdInfo nsdOnboardingState(final NsdOnboardingStateType nsdOnboardingState) {
		this.nsdOnboardingState = nsdOnboardingState;
		return this;
	}

	/**
	 * Get nsdOnboardingState
	 *
	 * @return nsdOnboardingState
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	@Valid
	public NsdOnboardingStateType getNsdOnboardingState() {
		return nsdOnboardingState;
	}

	public void setNsdOnboardingState(final NsdOnboardingStateType nsdOnboardingState) {
		this.nsdOnboardingState = nsdOnboardingState;
	}

	public NsdInfo onboardingFailureDetails(final ProblemDetails onboardingFailureDetails) {
		this.onboardingFailureDetails = onboardingFailureDetails;
		return this;
	}

	/**
	 * Get onboardingFailureDetails
	 *
	 * @return onboardingFailureDetails
	 **/
	@ApiModelProperty(value = "")

	@Valid
	public ProblemDetails getOnboardingFailureDetails() {
		return onboardingFailureDetails;
	}

	public void setOnboardingFailureDetails(final ProblemDetails onboardingFailureDetails) {
		this.onboardingFailureDetails = onboardingFailureDetails;
	}

	public NsdInfo nsdOperationalState(final NsdOperationalStateType nsdOperationalState) {
		this.nsdOperationalState = nsdOperationalState;
		return this;
	}

	/**
	 * Get nsdOperationalState
	 *
	 * @return nsdOperationalState
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	@Valid
	public NsdOperationalStateType getNsdOperationalState() {
		return nsdOperationalState;
	}

	public void setNsdOperationalState(final NsdOperationalStateType nsdOperationalState) {
		this.nsdOperationalState = nsdOperationalState;
	}

	public NsdInfo nsdUsageState(final NsdUsageStateType nsdUsageState) {
		this.nsdUsageState = nsdUsageState;
		return this;
	}

	/**
	 * Get nsdUsageState
	 *
	 * @return nsdUsageState
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	@Valid
	public NsdUsageStateType getNsdUsageState() {
		return nsdUsageState;
	}

	public void setNsdUsageState(final NsdUsageStateType nsdUsageState) {
		this.nsdUsageState = nsdUsageState;
	}

	public NsdInfo userDefinedData(final Map<String, Object> userDefinedData) {
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
	public Map<String, Object> getUserDefinedData() {
		return userDefinedData;
	}

	public void setUserDefinedData(final Map<String, Object> userDefinedData) {
		this.userDefinedData = userDefinedData;
	}

	public NsdInfo _links(final NsdInfoLinks _links) {
		this._links = _links;
		return this;
	}

	/**
	 * Get _links
	 *
	 * @return _links
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	@Valid
	public NsdInfoLinks getLinks() {
		return _links;
	}

	public void setLinks(final NsdInfoLinks _links) {
		this._links = _links;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final NsdInfo nsdInfo = (NsdInfo) o;
		return Objects.equals(this.id, nsdInfo.id) &&
				Objects.equals(this.nsdId, nsdInfo.nsdId) &&
				Objects.equals(this.nsdName, nsdInfo.nsdName) &&
				Objects.equals(this.nsdVersion, nsdInfo.nsdVersion) &&
				Objects.equals(this.nsdDesigner, nsdInfo.nsdDesigner) &&
				Objects.equals(this.nsdInvariantId, nsdInfo.nsdInvariantId) &&
				Objects.equals(this.vnfPkgIds, nsdInfo.vnfPkgIds) &&
				Objects.equals(this.pnfdInfoIds, nsdInfo.pnfdInfoIds) &&
				Objects.equals(this.nestedNsdInfoIds, nsdInfo.nestedNsdInfoIds) &&
				Objects.equals(this.archiveSecurityOption, nsdInfo.archiveSecurityOption) &&
				Objects.equals(this.signingCertificate, nsdInfo.signingCertificate) &&
				Objects.equals(this.artifacts, nsdInfo.artifacts) &&
				Objects.equals(this.nsdOnboardingState, nsdInfo.nsdOnboardingState) &&
				Objects.equals(this.onboardingFailureDetails, nsdInfo.onboardingFailureDetails) &&
				Objects.equals(this.nsdOperationalState, nsdInfo.nsdOperationalState) &&
				Objects.equals(this.nsdUsageState, nsdInfo.nsdUsageState) &&
				Objects.equals(this.userDefinedData, nsdInfo.userDefinedData) &&
				Objects.equals(this._links, nsdInfo._links);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nsdId, nsdName, nsdVersion, nsdDesigner, nsdInvariantId, vnfPkgIds, pnfdInfoIds, nestedNsdInfoIds, archiveSecurityOption, signingCertificate, artifacts, nsdOnboardingState, onboardingFailureDetails, nsdOperationalState, nsdUsageState, userDefinedData, _links);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class NsdInfo {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    nsdId: ").append(toIndentedString(nsdId)).append("\n");
		sb.append("    nsdName: ").append(toIndentedString(nsdName)).append("\n");
		sb.append("    nsdVersion: ").append(toIndentedString(nsdVersion)).append("\n");
		sb.append("    nsdDesigner: ").append(toIndentedString(nsdDesigner)).append("\n");
		sb.append("    nsdInvariantId: ").append(toIndentedString(nsdInvariantId)).append("\n");
		sb.append("    vnfPkgIds: ").append(toIndentedString(vnfPkgIds)).append("\n");
		sb.append("    pnfdInfoIds: ").append(toIndentedString(pnfdInfoIds)).append("\n");
		sb.append("    nestedNsdInfoIds: ").append(toIndentedString(nestedNsdInfoIds)).append("\n");
		sb.append("    archiveSecurityOption: ").append(toIndentedString(archiveSecurityOption)).append("\n");
		sb.append("    signingCertificate: ").append(toIndentedString(signingCertificate)).append("\n");
		sb.append("    artifacts: ").append(toIndentedString(artifacts)).append("\n");
		sb.append("    nsdOnboardingState: ").append(toIndentedString(nsdOnboardingState)).append("\n");
		sb.append("    onboardingFailureDetails: ").append(toIndentedString(onboardingFailureDetails)).append("\n");
		sb.append("    nsdOperationalState: ").append(toIndentedString(nsdOperationalState)).append("\n");
		sb.append("    nsdUsageState: ").append(toIndentedString(nsdUsageState)).append("\n");
		sb.append("    userDefinedData: ").append(toIndentedString(userDefinedData)).append("\n");
		sb.append("    _links: ").append(toIndentedString(_links)).append("\n");
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
