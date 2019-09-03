package com.ubiqube.etsi.mano.model.nslcm.sol005;

import java.util.List;

import javax.annotation.Nonnull;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents a response for Query NS operation. It shall comply with
 * the provisions defined in Table 6.5.2.10-1.
 **/
@ApiModel(description = "This type represents a response for Query NS operation.  It shall comply with the provisions defined in Table 6.5.2.10-1. ")
public class NsInstancesNsInstance {

	@ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
	/**
	 * An identifier with the intention of being globally unique.
	 **/
	private String id = null;

	@ApiModelProperty(required = true, value = "Human readable name of the NS instance. ")
	/**
	 * Human readable name of the NS instance.
	 **/
	private String nsInstanceName = null;

	@ApiModelProperty(required = true, value = "Human readable description of the NS instance. ")
	/**
	 * Human readable description of the NS instance.
	 **/
	private String nsInstanceDescription = null;

	@ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
	/**
	 * An identifier with the intention of being globally unique.
	 **/
	private String nsdId = null;

	@ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
	/**
	 * An identifier with the intention of being globally unique.
	 **/
	private String nsdInfoId = null;

	@ApiModelProperty(value = "An identifier that is unique within a NS descriptor. Representation: string of variable length. ")
	/**
	 * An identifier that is unique within a NS descriptor. Representation: string
	 * of variable length.
	 **/
	private String flavourId = null;

	@ApiModelProperty(value = "Information on constituent VNF(s) of the NS instance. ")
	@Valid
	/**
	 * Information on constituent VNF(s) of the NS instance.
	 **/
	private List<NsInstancesNsInstanceVnfInstance> vnfInstance = null;

	@ApiModelProperty(value = "Information on the PNF(s) that are part of the NS instance. ")
	@Valid
	/**
	 * Information on the PNF(s) that are part of the NS instance.
	 **/
	private List<NsInstancesNsInstancePnfInfo> pnfInfo = null;

	@ApiModelProperty(value = "Information on the VL(s) of the NS instance. This attribute shall be present if the nsState attribute value is INSTANTIATED and if the NS instance has specified connectivity. ")
	@Valid
	/**
	 * Information on the VL(s) of the NS instance. This attribute shall be present
	 * if the nsState attribute value is INSTANTIATED and if the NS instance has
	 * specified connectivity.
	 **/
	private List<NsInstancesNsInstanceVirtualLinkInfo> virtualLinkInfo = null;

	@ApiModelProperty(value = "Information on the VNFFG(s) of the NS instance. ")
	@Valid
	/**
	 * Information on the VNFFG(s) of the NS instance.
	 **/
	private List<NsInstancesNsInstanceVnffgInfo> vnffgInfo = null;

	@ApiModelProperty(value = "Information on the SAP(s) of the NS instance. ")
	@Valid
	/**
	 * Information on the SAP(s) of the NS instance.
	 **/
	private List<NsInstancesNsInstanceSapInfo> sapInfo = null;

	@ApiModelProperty(value = "Identifier of the nested NS(s) of the NS instance. ")
	/**
	 * Identifier of the nested NS(s) of the NS instance.
	 **/
	private List<String> nestedNsInstanceId = null;

	@XmlType(name = "NsStateEnum")
	@XmlEnum(String.class)
	public enum NsStateEnum {

		@XmlEnumValue("NOT_INSTANTIATED")
		NOT_INSTANTIATED(String.valueOf("NOT_INSTANTIATED")), @XmlEnumValue("INSTANTIATED")
		INSTANTIATED(String.valueOf("INSTANTIATED"));

		private final String value;

		NsStateEnum(final String v) {
			value = v;
		}

		public String value() {
			return value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		public static NsStateEnum fromValue(final String v) {
			for (final NsStateEnum b : NsStateEnum.values()) {
				if (String.valueOf(b.value).equals(v)) {
					return b;
				}
			}
			return null;
		}
	}

	@ApiModelProperty(required = true, value = "The state of the NS instance. Permitted values: NOT_INSTANTIATED: The NS instance is terminated or not instantiated. INSTANTIATED: The NS instance is instantiated. ")
	/**
	 * The state of the NS instance. Permitted values: NOT_INSTANTIATED: The NS
	 * instance is terminated or not instantiated. INSTANTIATED: The NS instance is
	 * instantiated.
	 **/
	private NsStateEnum nsState = null;

	@ApiModelProperty(value = "Status of each NS scaling aspect declared in the applicable DF, how \"big\" the NS instance has been scaled w.r.t. that aspect. This attribute shall be present if the nsState attribute value is INSTANTIATED. ")
	@Valid
	/**
	 * Status of each NS scaling aspect declared in the applicable DF, how \"big\"
	 * the NS instance has been scaled w.r.t. that aspect. This attribute shall be
	 * present if the nsState attribute value is INSTANTIATED.
	 **/
	private List<NsInstancesNsInstanceNsScaleStatus> nsScaleStatus = null;

	@ApiModelProperty(value = "Information on the additional affinity or anti-affinity rule from NS instantiation operation. Shall not conflict with rules already specified in the NSD. ")
	@Valid
	/**
	 * Information on the additional affinity or anti-affinity rule from NS
	 * instantiation operation. Shall not conflict with rules already specified in
	 * the NSD.
	 **/
	private List<NsInstancesNsInstanceAdditionalAffinityOrAntiAffinityRule> additionalAffinityOrAntiAffinityRule = null;

	@ApiModelProperty(value = "")
	@Valid
	private NsInstancesNsInstanceLinks links = null;

	/**
	 * An identifier with the intention of being globally unique.
	 * 
	 * @return id
	 **/
	@JsonProperty("id")
	@NotNull
	@Nonnull
	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public NsInstancesNsInstance id(final String id) {
		this.id = id;
		return this;
	}

	/**
	 * Human readable name of the NS instance.
	 * 
	 * @return nsInstanceName
	 **/
	@JsonProperty("nsInstanceName")
	@NotNull
	public String getNsInstanceName() {
		return nsInstanceName;
	}

	public void setNsInstanceName(final String nsInstanceName) {
		this.nsInstanceName = nsInstanceName;
	}

	public NsInstancesNsInstance nsInstanceName(final String nsInstanceName) {
		this.nsInstanceName = nsInstanceName;
		return this;
	}

	/**
	 * Human readable description of the NS instance.
	 * 
	 * @return nsInstanceDescription
	 **/
	@JsonProperty("nsInstanceDescription")
	@NotNull
	public String getNsInstanceDescription() {
		return nsInstanceDescription;
	}

	public void setNsInstanceDescription(final String nsInstanceDescription) {
		this.nsInstanceDescription = nsInstanceDescription;
	}

	public NsInstancesNsInstance nsInstanceDescription(final String nsInstanceDescription) {
		this.nsInstanceDescription = nsInstanceDescription;
		return this;
	}

	/**
	 * An identifier with the intention of being globally unique.
	 * 
	 * @return nsdId
	 **/
	@JsonProperty("nsdId")
	@NotNull
	public String getNsdId() {
		return nsdId;
	}

	public void setNsdId(final String nsdId) {
		this.nsdId = nsdId;
	}

	public NsInstancesNsInstance nsdId(final String nsdId) {
		this.nsdId = nsdId;
		return this;
	}

	/**
	 * An identifier with the intention of being globally unique.
	 * 
	 * @return nsdInfoId
	 **/
	@JsonProperty("nsdInfoId")
	@NotNull
	public String getNsdInfoId() {
		return nsdInfoId;
	}

	public void setNsdInfoId(final String nsdInfoId) {
		this.nsdInfoId = nsdInfoId;
	}

	public NsInstancesNsInstance nsdInfoId(final String nsdInfoId) {
		this.nsdInfoId = nsdInfoId;
		return this;
	}

	/**
	 * An identifier that is unique within a NS descriptor. Representation: string
	 * of variable length.
	 * 
	 * @return flavourId
	 **/
	@JsonProperty("flavourId")
	public String getFlavourId() {
		return flavourId;
	}

	public void setFlavourId(final String flavourId) {
		this.flavourId = flavourId;
	}

	public NsInstancesNsInstance flavourId(final String flavourId) {
		this.flavourId = flavourId;
		return this;
	}

	/**
	 * Information on constituent VNF(s) of the NS instance.
	 * 
	 * @return vnfInstance
	 **/
	@JsonProperty("vnfInstance")
	public List<NsInstancesNsInstanceVnfInstance> getVnfInstance() {
		return vnfInstance;
	}

	public void setVnfInstance(final List<NsInstancesNsInstanceVnfInstance> vnfInstance) {
		this.vnfInstance = vnfInstance;
	}

	public NsInstancesNsInstance vnfInstance(final List<NsInstancesNsInstanceVnfInstance> vnfInstance) {
		this.vnfInstance = vnfInstance;
		return this;
	}

	public NsInstancesNsInstance addVnfInstanceItem(final NsInstancesNsInstanceVnfInstance vnfInstanceItem) {
		this.vnfInstance.add(vnfInstanceItem);
		return this;
	}

	/**
	 * Information on the PNF(s) that are part of the NS instance.
	 * 
	 * @return pnfInfo
	 **/
	@JsonProperty("pnfInfo")
	public List<NsInstancesNsInstancePnfInfo> getPnfInfo() {
		return pnfInfo;
	}

	public void setPnfInfo(final List<NsInstancesNsInstancePnfInfo> pnfInfo) {
		this.pnfInfo = pnfInfo;
	}

	public NsInstancesNsInstance pnfInfo(final List<NsInstancesNsInstancePnfInfo> pnfInfo) {
		this.pnfInfo = pnfInfo;
		return this;
	}

	public NsInstancesNsInstance addPnfInfoItem(final NsInstancesNsInstancePnfInfo pnfInfoItem) {
		this.pnfInfo.add(pnfInfoItem);
		return this;
	}

	/**
	 * Information on the VL(s) of the NS instance. This attribute shall be present
	 * if the nsState attribute value is INSTANTIATED and if the NS instance has
	 * specified connectivity.
	 * 
	 * @return virtualLinkInfo
	 **/
	@JsonProperty("virtualLinkInfo")
	public List<NsInstancesNsInstanceVirtualLinkInfo> getVirtualLinkInfo() {
		return virtualLinkInfo;
	}

	public void setVirtualLinkInfo(final List<NsInstancesNsInstanceVirtualLinkInfo> virtualLinkInfo) {
		this.virtualLinkInfo = virtualLinkInfo;
	}

	public NsInstancesNsInstance virtualLinkInfo(final List<NsInstancesNsInstanceVirtualLinkInfo> virtualLinkInfo) {
		this.virtualLinkInfo = virtualLinkInfo;
		return this;
	}

	public NsInstancesNsInstance addVirtualLinkInfoItem(final NsInstancesNsInstanceVirtualLinkInfo virtualLinkInfoItem) {
		this.virtualLinkInfo.add(virtualLinkInfoItem);
		return this;
	}

	/**
	 * Information on the VNFFG(s) of the NS instance.
	 * 
	 * @return vnffgInfo
	 **/
	@JsonProperty("vnffgInfo")
	public List<NsInstancesNsInstanceVnffgInfo> getVnffgInfo() {
		return vnffgInfo;
	}

	public void setVnffgInfo(final List<NsInstancesNsInstanceVnffgInfo> vnffgInfo) {
		this.vnffgInfo = vnffgInfo;
	}

	public NsInstancesNsInstance vnffgInfo(final List<NsInstancesNsInstanceVnffgInfo> vnffgInfo) {
		this.vnffgInfo = vnffgInfo;
		return this;
	}

	public NsInstancesNsInstance addVnffgInfoItem(final NsInstancesNsInstanceVnffgInfo vnffgInfoItem) {
		this.vnffgInfo.add(vnffgInfoItem);
		return this;
	}

	/**
	 * Information on the SAP(s) of the NS instance.
	 * 
	 * @return sapInfo
	 **/
	@JsonProperty("sapInfo")
	public List<NsInstancesNsInstanceSapInfo> getSapInfo() {
		return sapInfo;
	}

	public void setSapInfo(final List<NsInstancesNsInstanceSapInfo> sapInfo) {
		this.sapInfo = sapInfo;
	}

	public NsInstancesNsInstance sapInfo(final List<NsInstancesNsInstanceSapInfo> sapInfo) {
		this.sapInfo = sapInfo;
		return this;
	}

	public NsInstancesNsInstance addSapInfoItem(final NsInstancesNsInstanceSapInfo sapInfoItem) {
		this.sapInfo.add(sapInfoItem);
		return this;
	}

	/**
	 * Identifier of the nested NS(s) of the NS instance.
	 * 
	 * @return nestedNsInstanceId
	 **/
	@JsonProperty("nestedNsInstanceId")
	public List<String> getNestedNsInstanceId() {
		return nestedNsInstanceId;
	}

	public void setNestedNsInstanceId(final List<String> nestedNsInstanceId) {
		this.nestedNsInstanceId = nestedNsInstanceId;
	}

	public NsInstancesNsInstance nestedNsInstanceId(final List<String> nestedNsInstanceId) {
		this.nestedNsInstanceId = nestedNsInstanceId;
		return this;
	}

	public NsInstancesNsInstance addNestedNsInstanceIdItem(final String nestedNsInstanceIdItem) {
		this.nestedNsInstanceId.add(nestedNsInstanceIdItem);
		return this;
	}

	/**
	 * The state of the NS instance. Permitted values: NOT_INSTANTIATED: The NS
	 * instance is terminated or not instantiated. INSTANTIATED: The NS instance is
	 * instantiated.
	 * 
	 * @return nsState
	 **/
	@JsonProperty("nsState")
	@NotNull
	public String getNsState() {
		if (nsState == null) {
			return null;
		}
		return nsState.value();
	}

	public void setNsState(final NsStateEnum nsState) {
		this.nsState = nsState;
	}

	public NsInstancesNsInstance nsState(final NsStateEnum nsState) {
		this.nsState = nsState;
		return this;
	}

	/**
	 * Status of each NS scaling aspect declared in the applicable DF, how
	 * \&quot;big\&quot; the NS instance has been scaled w.r.t. that aspect. This
	 * attribute shall be present if the nsState attribute value is INSTANTIATED.
	 * 
	 * @return nsScaleStatus
	 **/
	@JsonProperty("nsScaleStatus")
	public List<NsInstancesNsInstanceNsScaleStatus> getNsScaleStatus() {
		return nsScaleStatus;
	}

	public void setNsScaleStatus(final List<NsInstancesNsInstanceNsScaleStatus> nsScaleStatus) {
		this.nsScaleStatus = nsScaleStatus;
	}

	public NsInstancesNsInstance nsScaleStatus(final List<NsInstancesNsInstanceNsScaleStatus> nsScaleStatus) {
		this.nsScaleStatus = nsScaleStatus;
		return this;
	}

	public NsInstancesNsInstance addNsScaleStatusItem(final NsInstancesNsInstanceNsScaleStatus nsScaleStatusItem) {
		this.nsScaleStatus.add(nsScaleStatusItem);
		return this;
	}

	/**
	 * Information on the additional affinity or anti-affinity rule from NS
	 * instantiation operation. Shall not conflict with rules already specified in
	 * the NSD.
	 * 
	 * @return additionalAffinityOrAntiAffinityRule
	 **/
	@JsonProperty("additionalAffinityOrAntiAffinityRule")
	public List<NsInstancesNsInstanceAdditionalAffinityOrAntiAffinityRule> getAdditionalAffinityOrAntiAffinityRule() {
		return additionalAffinityOrAntiAffinityRule;
	}

	public void setAdditionalAffinityOrAntiAffinityRule(final List<NsInstancesNsInstanceAdditionalAffinityOrAntiAffinityRule> additionalAffinityOrAntiAffinityRule) {
		this.additionalAffinityOrAntiAffinityRule = additionalAffinityOrAntiAffinityRule;
	}

	public NsInstancesNsInstance additionalAffinityOrAntiAffinityRule(final List<NsInstancesNsInstanceAdditionalAffinityOrAntiAffinityRule> additionalAffinityOrAntiAffinityRule) {
		this.additionalAffinityOrAntiAffinityRule = additionalAffinityOrAntiAffinityRule;
		return this;
	}

	public NsInstancesNsInstance addAdditionalAffinityOrAntiAffinityRuleItem(final NsInstancesNsInstanceAdditionalAffinityOrAntiAffinityRule additionalAffinityOrAntiAffinityRuleItem) {
		this.additionalAffinityOrAntiAffinityRule.add(additionalAffinityOrAntiAffinityRuleItem);
		return this;
	}

	/**
	 * Get links
	 * 
	 * @return links
	 **/
	@JsonProperty("_links")
	public NsInstancesNsInstanceLinks getLinks() {
		return links;
	}

	public void setLinks(final NsInstancesNsInstanceLinks links) {
		this.links = links;
	}

	public NsInstancesNsInstance links(final NsInstancesNsInstanceLinks links) {
		this.links = links;
		return this;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class NsInstancesNsInstance {\n");

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
		sb.append("    nsScaleStatus: ").append(toIndentedString(nsScaleStatus)).append("\n");
		sb.append("    additionalAffinityOrAntiAffinityRule: ").append(toIndentedString(additionalAffinityOrAntiAffinityRule)).append("\n");
		sb.append("    links: ").append(toIndentedString(links)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private static String toIndentedString(final Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
