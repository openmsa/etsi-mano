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

package com.ubiqube.etsi.mano.model.v271.sol005.nslcm;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents a response for Query NS operation. It shall comply with
 * the provisions defined in Table 6.5.2.10-1.
 */
@ApiModel(description = "This type represents a response for Query NS operation.  It shall comply with the provisions defined in Table 6.5.2.10-1. ")
@Validated
public class NsInstance {
	@JsonProperty("id")
	private UUID id = null;

	@JsonProperty("nsInstanceName")
	private String nsInstanceName = null;

	@JsonProperty("nsInstanceDescription")
	private String nsInstanceDescription = null;

	@JsonProperty("nsdId")
	private UUID nsdId = null;

	@JsonProperty("nsdInfoId")
	private UUID nsdInfoId = null;

	@JsonProperty("flavourId")
	private String flavourId = null;

	@JsonProperty("vnfInstance")
	@Valid
	private List<VnfInstance> vnfInstance = null;

	@JsonProperty("pnfInfo")
	@Valid
	private List<PnfInfo> pnfInfo = null;

	@JsonProperty("virtualLinkInfo")
	@Valid
	private List<NsVirtualLinkInfo> virtualLinkInfo = null;

	@JsonProperty("vnffgInfo")
	@Valid
	private List<VnffgInfo> vnffgInfo = null;

	@JsonProperty("sapInfo")
	@Valid
	private List<SapInfo> sapInfo = null;

	@JsonProperty("nestedNsInstanceId")
	@Valid
	private List<UUID> nestedNsInstanceId = null;

	/**
	 * The state of the NS instance. Permitted values: NOT_INSTANTIATED: The NS
	 * instance is terminated or not instantiated. INSTANTIATED: The NS instance is
	 * instantiated.
	 */
	public enum NsStateEnum {
		NOT_INSTANTIATED("NOT_INSTANTIATED"),

		INSTANTIATED("INSTANTIATED");

		private final String value;

		NsStateEnum(final String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static NsStateEnum fromValue(final String text) {
			for (final NsStateEnum b : NsStateEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	@JsonProperty("nsState")
	private NsStateEnum nsState = null;

	@JsonProperty("monitoringParameter")
	@Valid
	private List<NsMonitoringParameter> monitoringParameter = null;

	@JsonProperty("nsScaleStatus")
	@Valid
	private List<NsScaleInfo> nsScaleStatus = null;

	@JsonProperty("additionalAffinityOrAntiAffinityRule")
	@Valid
	private List<AffinityOrAntiAffinityRule> additionalAffinityOrAntiAffinityRule = null;

	@JsonProperty("_links")
	private NsInstanceLinks _links = null;

	public NsInstance id(final UUID id) {
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

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public NsInstance nsInstanceName(final String nsInstanceName) {
		this.nsInstanceName = nsInstanceName;
		return this;
	}

	/**
	 * Human readable name of the NS instance.
	 *
	 * @return nsInstanceName
	 **/
	@ApiModelProperty(required = true, value = "Human readable name of the NS instance. ")
	@NotNull

	public String getNsInstanceName() {
		return nsInstanceName;
	}

	public void setNsInstanceName(final String nsInstanceName) {
		this.nsInstanceName = nsInstanceName;
	}

	public NsInstance nsInstanceDescription(final String nsInstanceDescription) {
		this.nsInstanceDescription = nsInstanceDescription;
		return this;
	}

	/**
	 * Human readable description of the NS instance.
	 *
	 * @return nsInstanceDescription
	 **/
	@ApiModelProperty(required = true, value = "Human readable description of the NS instance. ")
	@NotNull

	public String getNsInstanceDescription() {
		return nsInstanceDescription;
	}

	public void setNsInstanceDescription(final String nsInstanceDescription) {
		this.nsInstanceDescription = nsInstanceDescription;
	}

	public NsInstance nsdId(final UUID nsdId) {
		this.nsdId = nsdId;
		return this;
	}

	/**
	 * Get nsdId
	 *
	 * @return nsdId
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	public UUID getNsdId() {
		return nsdId;
	}

	public void setNsdId(final UUID nsdId) {
		this.nsdId = nsdId;
	}

	public NsInstance nsdInfoId(final UUID nsdInfoId) {
		this.nsdInfoId = nsdInfoId;
		return this;
	}

	/**
	 * Get nsdInfoId
	 *
	 * @return nsdInfoId
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	public UUID getNsdInfoId() {
		return nsdInfoId;
	}

	public void setNsdInfoId(final UUID nsdInfoId) {
		this.nsdInfoId = nsdInfoId;
	}

	public NsInstance flavourId(final String flavourId) {
		this.flavourId = flavourId;
		return this;
	}

	/**
	 * Get flavourId
	 *
	 * @return flavourId
	 **/
	@ApiModelProperty(value = "")

	public String getFlavourId() {
		return flavourId;
	}

	public void setFlavourId(final String flavourId) {
		this.flavourId = flavourId;
	}

	public NsInstance vnfInstance(final List<VnfInstance> vnfInstance) {
		this.vnfInstance = vnfInstance;
		return this;
	}

	public NsInstance addVnfInstanceItem(final VnfInstance vnfInstanceItem) {
		if (this.vnfInstance == null) {
			this.vnfInstance = new ArrayList<>();
		}
		this.vnfInstance.add(vnfInstanceItem);
		return this;
	}

	/**
	 * Information on constituent VNF(s) of the NS instance.
	 *
	 * @return vnfInstance
	 **/
	@ApiModelProperty(value = "Information on constituent VNF(s) of the NS instance. ")
	@Valid
	public List<VnfInstance> getVnfInstance() {
		return vnfInstance;
	}

	public void setVnfInstance(final List<VnfInstance> vnfInstance) {
		this.vnfInstance = vnfInstance;
	}

	public NsInstance pnfInfo(final List<PnfInfo> pnfInfo) {
		this.pnfInfo = pnfInfo;
		return this;
	}

	public NsInstance addPnfInfoItem(final PnfInfo pnfInfoItem) {
		if (this.pnfInfo == null) {
			this.pnfInfo = new ArrayList<>();
		}
		this.pnfInfo.add(pnfInfoItem);
		return this;
	}

	/**
	 * Information on the PNF(s) that are part of the NS instance.
	 *
	 * @return pnfInfo
	 **/
	@ApiModelProperty(value = "Information on the PNF(s) that are part of the NS instance. ")
	@Valid
	public List<PnfInfo> getPnfInfo() {
		return pnfInfo;
	}

	public void setPnfInfo(final List<PnfInfo> pnfInfo) {
		this.pnfInfo = pnfInfo;
	}

	public NsInstance virtualLinkInfo(final List<NsVirtualLinkInfo> virtualLinkInfo) {
		this.virtualLinkInfo = virtualLinkInfo;
		return this;
	}

	public NsInstance addVirtualLinkInfoItem(final NsVirtualLinkInfo virtualLinkInfoItem) {
		if (this.virtualLinkInfo == null) {
			this.virtualLinkInfo = new ArrayList<>();
		}
		this.virtualLinkInfo.add(virtualLinkInfoItem);
		return this;
	}

	/**
	 * Information on the VL(s) of the NS instance. This attribute shall be present
	 * if the nsState attribute value is INSTANTIATED and if the NS instance has
	 * specified connectivity.
	 *
	 * @return virtualLinkInfo
	 **/
	@ApiModelProperty(value = "Information on the VL(s) of the NS instance. This attribute shall be present if the nsState attribute value is INSTANTIATED and if the NS instance has specified connectivity. ")
	@Valid
	public List<NsVirtualLinkInfo> getVirtualLinkInfo() {
		return virtualLinkInfo;
	}

	public void setVirtualLinkInfo(final List<NsVirtualLinkInfo> virtualLinkInfo) {
		this.virtualLinkInfo = virtualLinkInfo;
	}

	public NsInstance vnffgInfo(final List<VnffgInfo> vnffgInfo) {
		this.vnffgInfo = vnffgInfo;
		return this;
	}

	public NsInstance addVnffgInfoItem(final VnffgInfo vnffgInfoItem) {
		if (this.vnffgInfo == null) {
			this.vnffgInfo = new ArrayList<>();
		}
		this.vnffgInfo.add(vnffgInfoItem);
		return this;
	}

	/**
	 * Information on the VNFFG(s) of the NS instance.
	 *
	 * @return vnffgInfo
	 **/
	@ApiModelProperty(value = "Information on the VNFFG(s) of the NS instance. ")
	@Valid
	public List<VnffgInfo> getVnffgInfo() {
		return vnffgInfo;
	}

	public void setVnffgInfo(final List<VnffgInfo> vnffgInfo) {
		this.vnffgInfo = vnffgInfo;
	}

	public NsInstance sapInfo(final List<SapInfo> sapInfo) {
		this.sapInfo = sapInfo;
		return this;
	}

	public NsInstance addSapInfoItem(final SapInfo sapInfoItem) {
		if (this.sapInfo == null) {
			this.sapInfo = new ArrayList<>();
		}
		this.sapInfo.add(sapInfoItem);
		return this;
	}

	/**
	 * Information on the SAP(s) of the NS instance.
	 *
	 * @return sapInfo
	 **/
	@ApiModelProperty(value = "Information on the SAP(s) of the NS instance. ")
	@Valid
	public List<SapInfo> getSapInfo() {
		return sapInfo;
	}

	public void setSapInfo(final List<SapInfo> sapInfo) {
		this.sapInfo = sapInfo;
	}

	public NsInstance nestedNsInstanceId(final List<UUID> nestedNsInstanceId) {
		this.nestedNsInstanceId = nestedNsInstanceId;
		return this;
	}

	public NsInstance addNestedNsInstanceIdItem(final UUID nestedNsInstanceIdItem) {
		if (this.nestedNsInstanceId == null) {
			this.nestedNsInstanceId = new ArrayList<>();
		}
		this.nestedNsInstanceId.add(nestedNsInstanceIdItem);
		return this;
	}

	/**
	 * Identifier of the nested NS(s) of the NS instance.
	 *
	 * @return nestedNsInstanceId
	 **/
	@ApiModelProperty(value = "Identifier of the nested NS(s) of the NS instance. ")

	public List<UUID> getNestedNsInstanceId() {
		return nestedNsInstanceId;
	}

	public void setNestedNsInstanceId(final List<UUID> nestedNsInstanceId) {
		this.nestedNsInstanceId = nestedNsInstanceId;
	}

	public NsInstance nsState(final NsStateEnum nsState) {
		this.nsState = nsState;
		return this;
	}

	/**
	 * The state of the NS instance. Permitted values: NOT_INSTANTIATED: The NS
	 * instance is terminated or not instantiated. INSTANTIATED: The NS instance is
	 * instantiated.
	 *
	 * @return nsState
	 **/
	@ApiModelProperty(required = true, value = "The state of the NS instance. Permitted values: NOT_INSTANTIATED: The NS instance is terminated or not instantiated. INSTANTIATED: The NS instance is instantiated. ")
	@NotNull

	public NsStateEnum getNsState() {
		return nsState;
	}

	public void setNsState(final NsStateEnum nsState) {
		this.nsState = nsState;
	}

	public NsInstance monitoringParameter(final List<NsMonitoringParameter> monitoringParameter) {
		this.monitoringParameter = monitoringParameter;
		return this;
	}

	public NsInstance addMonitoringParameterItem(final NsMonitoringParameter monitoringParameterItem) {
		if (this.monitoringParameter == null) {
			this.monitoringParameter = new ArrayList<>();
		}
		this.monitoringParameter.add(monitoringParameterItem);
		return this;
	}

	/**
	 * Performance metrics tracked by the NFVO (e.g. for auto-scaling purposes) as
	 * identified by the NS designer in the NSD.
	 *
	 * @return monitoringParameter
	 **/
	@ApiModelProperty(value = "Performance metrics tracked by the NFVO (e.g. for auto-scaling purposes) as identified by the NS designer in the NSD. ")
	@Valid
	public List<NsMonitoringParameter> getMonitoringParameter() {
		return monitoringParameter;
	}

	public void setMonitoringParameter(final List<NsMonitoringParameter> monitoringParameter) {
		this.monitoringParameter = monitoringParameter;
	}

	public NsInstance nsScaleStatus(final List<NsScaleInfo> nsScaleStatus) {
		this.nsScaleStatus = nsScaleStatus;
		return this;
	}

	public NsInstance addNsScaleStatusItem(final NsScaleInfo nsScaleStatusItem) {
		if (this.nsScaleStatus == null) {
			this.nsScaleStatus = new ArrayList<>();
		}
		this.nsScaleStatus.add(nsScaleStatusItem);
		return this;
	}

	/**
	 * Status of each NS scaling aspect declared in the applicable DF, how \"big\"
	 * the NS instance has been scaled w.r.t. that aspect. This attribute shall be
	 * present if the nsState attribute value is INSTANTIATED.
	 *
	 * @return nsScaleStatus
	 **/
	@ApiModelProperty(value = "Status of each NS scaling aspect declared in the applicable DF, how \"big\" the NS instance has been scaled w.r.t. that aspect. This attribute shall be present if the nsState attribute value is INSTANTIATED. ")
	@Valid
	public List<NsScaleInfo> getNsScaleStatus() {
		return nsScaleStatus;
	}

	public void setNsScaleStatus(final List<NsScaleInfo> nsScaleStatus) {
		this.nsScaleStatus = nsScaleStatus;
	}

	public NsInstance additionalAffinityOrAntiAffinityRule(final List<AffinityOrAntiAffinityRule> additionalAffinityOrAntiAffinityRule) {
		this.additionalAffinityOrAntiAffinityRule = additionalAffinityOrAntiAffinityRule;
		return this;
	}

	public NsInstance addAdditionalAffinityOrAntiAffinityRuleItem(final AffinityOrAntiAffinityRule additionalAffinityOrAntiAffinityRuleItem) {
		if (this.additionalAffinityOrAntiAffinityRule == null) {
			this.additionalAffinityOrAntiAffinityRule = new ArrayList<>();
		}
		this.additionalAffinityOrAntiAffinityRule.add(additionalAffinityOrAntiAffinityRuleItem);
		return this;
	}

	/**
	 * Information on the additional affinity or anti-affinity rule from NS
	 * instantiation operation. Shall not conflict with rules already specified in
	 * the NSD.
	 *
	 * @return additionalAffinityOrAntiAffinityRule
	 **/
	@ApiModelProperty(value = "Information on the additional affinity or anti-affinity rule from NS instantiation operation. Shall not conflict with rules already specified in the NSD. ")
	@Valid
	public List<AffinityOrAntiAffinityRule> getAdditionalAffinityOrAntiAffinityRule() {
		return additionalAffinityOrAntiAffinityRule;
	}

	public void setAdditionalAffinityOrAntiAffinityRule(final List<AffinityOrAntiAffinityRule> additionalAffinityOrAntiAffinityRule) {
		this.additionalAffinityOrAntiAffinityRule = additionalAffinityOrAntiAffinityRule;
	}

	public NsInstance _links(final NsInstanceLinks _links) {
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
	public NsInstanceLinks getLinks() {
		return _links;
	}

	public void setLinks(final NsInstanceLinks _links) {
		this._links = _links;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final NsInstance nsInstance = (NsInstance) o;
		return Objects.equals(this.id, nsInstance.id) &&
				Objects.equals(this.nsInstanceName, nsInstance.nsInstanceName) &&
				Objects.equals(this.nsInstanceDescription, nsInstance.nsInstanceDescription) &&
				Objects.equals(this.nsdId, nsInstance.nsdId) &&
				Objects.equals(this.nsdInfoId, nsInstance.nsdInfoId) &&
				Objects.equals(this.flavourId, nsInstance.flavourId) &&
				Objects.equals(this.vnfInstance, nsInstance.vnfInstance) &&
				Objects.equals(this.pnfInfo, nsInstance.pnfInfo) &&
				Objects.equals(this.virtualLinkInfo, nsInstance.virtualLinkInfo) &&
				Objects.equals(this.vnffgInfo, nsInstance.vnffgInfo) &&
				Objects.equals(this.sapInfo, nsInstance.sapInfo) &&
				Objects.equals(this.nestedNsInstanceId, nsInstance.nestedNsInstanceId) &&
				Objects.equals(this.nsState, nsInstance.nsState) &&
				Objects.equals(this.monitoringParameter, nsInstance.monitoringParameter) &&
				Objects.equals(this.nsScaleStatus, nsInstance.nsScaleStatus) &&
				Objects.equals(this.additionalAffinityOrAntiAffinityRule, nsInstance.additionalAffinityOrAntiAffinityRule) &&
				Objects.equals(this._links, nsInstance._links);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nsInstanceName, nsInstanceDescription, nsdId, nsdInfoId, flavourId, vnfInstance, pnfInfo, virtualLinkInfo, vnffgInfo, sapInfo, nestedNsInstanceId, nsState, monitoringParameter, nsScaleStatus, additionalAffinityOrAntiAffinityRule, _links);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class NsInstance {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    nsInstanceName: ").append(toIndentedString(nsInstanceName)).append("\n");
		sb.append("    nsInstanceDescription: ").append(toIndentedString(nsInstanceDescription)).append("\n");
		sb.append("    nsdId: ").append(toIndentedString(nsdId)).append("\n");
		sb.append("    nsdInfoId: ").append(toIndentedString(nsdInfoId)).append("\n");
		sb.append("    flavourId: ").append(toIndentedString(flavourId)).append("\n");
		sb.append("    vnfInstance: ").append(toIndentedString(vnfInstance)).append("\n");
		sb.append("    pnfInfo: ").append(toIndentedString(pnfInfo)).append("\n");
		sb.append("    virtualLinkInfo: ").append(toIndentedString(virtualLinkInfo)).append("\n");
		sb.append("    vnffgInfo: ").append(toIndentedString(vnffgInfo)).append("\n");
		sb.append("    sapInfo: ").append(toIndentedString(sapInfo)).append("\n");
		sb.append("    nestedNsInstanceId: ").append(toIndentedString(nestedNsInstanceId)).append("\n");
		sb.append("    nsState: ").append(toIndentedString(nsState)).append("\n");
		sb.append("    monitoringParameter: ").append(toIndentedString(monitoringParameter)).append("\n");
		sb.append("    nsScaleStatus: ").append(toIndentedString(nsScaleStatus)).append("\n");
		sb.append("    additionalAffinityOrAntiAffinityRule: ").append(toIndentedString(additionalAffinityOrAntiAffinityRule)).append("\n");
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
