package com.ubiqube.etsi.mano.nfvo.v261.model.nslcm;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents describes a pair of ingress and egress CPs or SAPs which
 * the NFP passes by. It shall comply with the provisions defined in Table
 * 6.5.3.72-1.
 */
@ApiModel(description = "This type represents describes a pair of ingress and egress CPs or SAPs which  the NFP passes by. It shall comply with the provisions defined in Table 6.5.3.72-1. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-05T16:49:58.135+01:00")

public class CpPairInfo {
	@JsonProperty("vnfExtCpIds")
	@Valid
	private List<String> vnfExtCpIds = null;

	@JsonProperty("pnfExtCpIds")
	@Valid
	private List<String> pnfExtCpIds = null;

	@JsonProperty("sapIds")
	@Valid
	private List<String> sapIds = null;

	public CpPairInfo vnfExtCpIds(final List<String> vnfExtCpIds) {
		this.vnfExtCpIds = vnfExtCpIds;
		return this;
	}

	public CpPairInfo addVnfExtCpIdsItem(final String vnfExtCpIdsItem) {
		if (this.vnfExtCpIds == null) {
			this.vnfExtCpIds = new ArrayList<>();
		}
		this.vnfExtCpIds.add(vnfExtCpIdsItem);
		return this;
	}

	/**
	 * Identifier(s) of the VNF CP(s) which form the pair. The presence of a single
	 * vnfExpCpId, pnfExtCpId, or sapId occurrence indicates that the CP or SAP is
	 * used both as an ingress and egress port at a particular NFP position.
	 * 
	 * @return vnfExtCpIds
	 **/
	@ApiModelProperty(value = "Identifier(s) of the VNF CP(s) which form the pair. The presence of a single vnfExpCpId, pnfExtCpId, or sapId occurrence indicates  that the CP or SAP is used both as an ingress and egress port at a particular  NFP position. ")

	@Size(max = 2)
	public List<String> getVnfExtCpIds() {
		return vnfExtCpIds;
	}

	public void setVnfExtCpIds(final List<String> vnfExtCpIds) {
		this.vnfExtCpIds = vnfExtCpIds;
	}

	public CpPairInfo pnfExtCpIds(final List<String> pnfExtCpIds) {
		this.pnfExtCpIds = pnfExtCpIds;
		return this;
	}

	public CpPairInfo addPnfExtCpIdsItem(final String pnfExtCpIdsItem) {
		if (this.pnfExtCpIds == null) {
			this.pnfExtCpIds = new ArrayList<>();
		}
		this.pnfExtCpIds.add(pnfExtCpIdsItem);
		return this;
	}

	/**
	 * Identifier(s) of the PNF CP(s) which form the pair. The presence of a single
	 * vnfExpCpId, pnfExtCpId, or sapId occurrence indicates that the CP or SAP is
	 * used both as an ingress and egress port at a particular NFP position.
	 * 
	 * @return pnfExtCpIds
	 **/
	@ApiModelProperty(value = "Identifier(s) of the PNF CP(s) which form the pair. The presence of a single vnfExpCpId, pnfExtCpId, or sapId occurrence indicates  that the CP or SAP is used both as an ingress and egress port at a particular  NFP position. ")

	@Size(max = 2)
	public List<String> getPnfExtCpIds() {
		return pnfExtCpIds;
	}

	public void setPnfExtCpIds(final List<String> pnfExtCpIds) {
		this.pnfExtCpIds = pnfExtCpIds;
	}

	public CpPairInfo sapIds(final List<String> sapIds) {
		this.sapIds = sapIds;
		return this;
	}

	public CpPairInfo addSapIdsItem(final String sapIdsItem) {
		if (this.sapIds == null) {
			this.sapIds = new ArrayList<>();
		}
		this.sapIds.add(sapIdsItem);
		return this;
	}

	/**
	 * Identifier(s) of the SAP(s) which form the pair. The presence of a single
	 * vnfExpCpId, pnfExtCpId, or sapId occurrence indicates that the CP or SAP is
	 * used both as an ingress and egress port at a particular NFP position.
	 * 
	 * @return sapIds
	 **/
	@ApiModelProperty(value = "Identifier(s) of the SAP(s) which form the pair. The presence of a single vnfExpCpId, pnfExtCpId, or sapId occurrence indicates  that the CP or SAP is used both as an ingress and egress port at a particular  NFP position. ")

	@Size(max = 2)
	public List<String> getSapIds() {
		return sapIds;
	}

	public void setSapIds(final List<String> sapIds) {
		this.sapIds = sapIds;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final CpPairInfo cpPairInfo = (CpPairInfo) o;
		return Objects.equals(this.vnfExtCpIds, cpPairInfo.vnfExtCpIds) &&
				Objects.equals(this.pnfExtCpIds, cpPairInfo.pnfExtCpIds) &&
				Objects.equals(this.sapIds, cpPairInfo.sapIds);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vnfExtCpIds, pnfExtCpIds, sapIds);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class CpPairInfo {\n");

		sb.append("    vnfExtCpIds: ").append(toIndentedString(vnfExtCpIds)).append("\n");
		sb.append("    pnfExtCpIds: ").append(toIndentedString(pnfExtCpIds)).append("\n");
		sb.append("    sapIds: ").append(toIndentedString(sapIds)).append("\n");
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
