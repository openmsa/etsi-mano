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

package com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.model.ProblemDetails;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsd.NsdOnboardingStateType;

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
	private List<UUID> vnfPkgIds = null;

	@JsonProperty("pnfdInfoIds")
	@Valid
	private List<UUID> pnfdInfoIds = null;

	@JsonProperty("nestedNsdInfoIds")
	@Valid
	private List<UUID> nestedNsdInfoIds = null;

	@JsonProperty("nsdOnboardingState")
	private NsdOnboardingStateType nsdOnboardingState = null;

	@JsonProperty("onboardingFailureDetails")
	private ProblemDetails onboardingFailureDetails = null;

	@JsonProperty("nsdOperationalState")
	private NsdOperationalStateType nsdOperationalState = null;

	@JsonProperty("nsdUsageState")
	private NsdUsageStateType nsdUsageState = null;

	@JsonProperty("userDefinedData")
	private Map<String, String> userDefinedData = null;

	@JsonProperty("_links")
	private NsdInfoLinks links = null;

	public NsdInfo id(final String id) {
		this.id = id;
		return this;
	}

	/**
	 * Identifier of the on boarded individual NS descriptor resource. This identifier is allocated by the NFVO.
	 *
	 * @return id
	 **/
	@ApiModelProperty(required = true, value = "Identifier of the on boarded individual NS descriptor resource. This identifier is allocated by the NFVO. ")
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
	 * This identifier, which is allocated by the NSD designer, identifies the NSD in a globally unique way. It is copied from the NSD content and shall be present after the NSD content is on-boarded.
	 *
	 * @return nsdId
	 **/
	@ApiModelProperty(value = "This identifier, which is allocated by the NSD designer, identifies the NSD in a globally unique way. It is copied from the NSD content and shall be present after the NSD content is on-boarded. ")

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
	 * Name of the on boarded NSD. This information is copied from the NSD content and shall be present after the NSD content is on-boarded.
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
	 * Version of the on-boarded NSD. This information is copied from the NSD content and shall be present after the NSD content is on-boarded.
	 *
	 * @return nsdVersion
	 **/
	@ApiModelProperty(value = "Version of the on-boarded NSD. This information is copied from the NSD content and shall be present after the NSD content is on-boarded. ")

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
	 * Designer of the on-boarded NSD. This information is copied from the NSD content and shall be present after the NSD content is on-boarded.
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
	 * This identifier, which is allocated by the NSD designer, identifies an NSD in a version independent manner. This information is copied from the NSD content and shall be present after the NSD content is on-boarded.
	 *
	 * @return nsdInvariantId
	 **/
	@ApiModelProperty(value = "This identifier, which is allocated by the NSD designer, identifies an NSD in a version independent manner. This information is copied from the NSD content and shall be present after the NSD content is on-boarded. ")

	public String getNsdInvariantId() {
		return nsdInvariantId;
	}

	public void setNsdInvariantId(final String nsdInvariantId) {
		this.nsdInvariantId = nsdInvariantId;
	}

	public NsdInfo vnfPkgIds(final List<UUID> vnfPkgIds) {
		this.vnfPkgIds = vnfPkgIds;
		return this;
	}

	public NsdInfo addVnfPkgIdsItem(final UUID vnfPkgIdsItem) {
		if (this.vnfPkgIds == null) {
			this.vnfPkgIds = new ArrayList<>();
		}
		this.vnfPkgIds.add(vnfPkgIdsItem);
		return this;
	}

	/**
	 * Identifies the VNF package for the VNFD referenced by the on-boarded NS descriptor resource.
	 *
	 * @return vnfPkgIds
	 **/
	@ApiModelProperty(value = "Identifies the VNF package for the VNFD referenced by the on-boarded NS descriptor resource. ")

	public List<UUID> getVnfPkgIds() {
		return vnfPkgIds;
	}

	public void setVnfPkgIds(final List<UUID> vnfPkgIds) {
		this.vnfPkgIds = vnfPkgIds;
	}

	public NsdInfo pnfdInfoIds(final List<UUID> pnfdInfoIds) {
		this.pnfdInfoIds = pnfdInfoIds;
		return this;
	}

	public NsdInfo addPnfdInfoIdsItem(final UUID pnfdInfoIdsItem) {
		if (this.pnfdInfoIds == null) {
			this.pnfdInfoIds = new ArrayList<>();
		}
		this.pnfdInfoIds.add(pnfdInfoIdsItem);
		return this;
	}

	/**
	 * Identifies the PnfdInfo element for the PNFD referenced by the on-boarded NS descriptor resource.
	 *
	 * @return pnfdInfoIds
	 **/
	@ApiModelProperty(value = "Identifies the PnfdInfo element for the PNFD referenced by the on-boarded NS descriptor resource. ")

	public List<UUID> getPnfdInfoIds() {
		return pnfdInfoIds;
	}

	public void setPnfdInfoIds(final List<UUID> pnfdInfoIds) {
		this.pnfdInfoIds = pnfdInfoIds;
	}

	public NsdInfo nestedNsdInfoIds(final List<UUID> nestedNsdInfoIds) {
		this.nestedNsdInfoIds = nestedNsdInfoIds;
		return this;
	}

	public NsdInfo addNestedNsdInfoIdsItem(final UUID nestedNsdInfoIdsItem) {
		if (this.nestedNsdInfoIds == null) {
			this.nestedNsdInfoIds = new ArrayList<>();
		}
		this.nestedNsdInfoIds.add(nestedNsdInfoIdsItem);
		return this;
	}

	/**
	 * Identifies the NsdInfo element for the nested NSD referenced by the on-boarded NS descriptor resource.
	 *
	 * @return nestedNsdInfoIds
	 **/
	@ApiModelProperty(value = "Identifies the NsdInfo element for the nested NSD referenced by the on-boarded NS descriptor resource. ")

	public List<UUID> getNestedNsdInfoIds() {
		return nestedNsdInfoIds;
	}

	public void setNestedNsdInfoIds(final List<UUID> nestedNsdInfoIds) {
		this.nestedNsdInfoIds = nestedNsdInfoIds;
	}

	public NsdInfo nsdOnboardingState(final NsdOnboardingStateType nsdOnboardingState) {
		this.nsdOnboardingState = nsdOnboardingState;
		return this;
	}

	/**
	 * On boarding state of the individual NS descriptor resource.
	 *
	 * @return nsdOnboardingState
	 **/
	@ApiModelProperty(required = true, value = "On boarding state of the individual NS descriptor resource. ")
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
	 * Failure details of current on boarding procedure. See clause 6.3 of ETSI GS NFV-SOL 013 for the details of \"ProblemDetails\" structure. It shall be present when the \"nsdOnboardingState\" attribute is CREATED and the uploading or processing fails in NFVO.
	 *
	 * @return onboardingFailureDetails
	 **/
	@ApiModelProperty(value = "Failure details of current on boarding procedure. See clause 6.3 of ETSI GS NFV-SOL 013 for the details of \"ProblemDetails\" structure. It shall be present when the \"nsdOnboardingState\" attribute is CREATED and the uploading or processing fails in NFVO. ")

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
	 * Operational state of the individual NS descriptor resource. This attribute can be modified with the PATCH method.
	 *
	 * @return nsdOperationalState
	 **/
	@ApiModelProperty(required = true, value = "Operational state of the individual NS descriptor resource. This attribute can be modified with the PATCH method. ")
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
	 * Usage state of the individual NS descriptor resource.
	 *
	 * @return nsdUsageState
	 **/
	@ApiModelProperty(required = true, value = "Usage state of the individual NS descriptor resource. ")
	@NotNull

	@Valid

	public NsdUsageStateType getNsdUsageState() {
		return nsdUsageState;
	}

	public void setNsdUsageState(final NsdUsageStateType nsdUsageState) {
		this.nsdUsageState = nsdUsageState;
	}

	public NsdInfo userDefinedData(final Map<String, String> userDefinedData) {
		this.userDefinedData = userDefinedData;
		return this;
	}

	/**
	 * User defined data for the individual NS descriptor resource. This attribute can be modified with the PATCH method.
	 *
	 * @return userDefinedData
	 **/
	@ApiModelProperty(value = "User defined data for the individual NS descriptor resource. This attribute can be modified with the PATCH method. ")

	@Valid

	public Map<String, String> getUserDefinedData() {
		return userDefinedData;
	}

	public void setUserDefinedData(final Map<String, String> userDefinedData) {
		this.userDefinedData = userDefinedData;
	}

	public NsdInfo links(final NsdInfoLinks links) {
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

	public NsdInfoLinks getLinks() {
		return links;
	}

	public void setLinks(final NsdInfoLinks links) {
		this.links = links;
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
				Objects.equals(this.nsdOnboardingState, nsdInfo.nsdOnboardingState) &&
				Objects.equals(this.onboardingFailureDetails, nsdInfo.onboardingFailureDetails) &&
				Objects.equals(this.nsdOperationalState, nsdInfo.nsdOperationalState) &&
				Objects.equals(this.nsdUsageState, nsdInfo.nsdUsageState) &&
				Objects.equals(this.userDefinedData, nsdInfo.userDefinedData) &&
				Objects.equals(this.links, nsdInfo.links);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nsdId, nsdName, nsdVersion, nsdDesigner, nsdInvariantId, vnfPkgIds, pnfdInfoIds, nestedNsdInfoIds, nsdOnboardingState, onboardingFailureDetails, nsdOperationalState, nsdUsageState, userDefinedData, links);
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
		sb.append("    nsdOnboardingState: ").append(toIndentedString(nsdOnboardingState)).append("\n");
		sb.append("    onboardingFailureDetails: ").append(toIndentedString(onboardingFailureDetails)).append("\n");
		sb.append("    nsdOperationalState: ").append(toIndentedString(nsdOperationalState)).append("\n");
		sb.append("    nsdUsageState: ").append(toIndentedString(nsdUsageState)).append("\n");
		sb.append("    userDefinedData: ").append(toIndentedString(userDefinedData)).append("\n");
		sb.append("    links: ").append(toIndentedString(links)).append("\n");
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
