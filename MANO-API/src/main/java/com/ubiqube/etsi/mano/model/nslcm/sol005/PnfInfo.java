package com.ubiqube.etsi.mano.model.nslcm.sol005;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents the information about a PNF that is part of an NS
 * instance. It shall comply with the provisions defined in Table 6.5.3.13-1.
 */
@ApiModel(description = "This type represents the information about a PNF that is part of an NS instance.  It shall comply with the provisions defined in Table 6.5.3.13-1. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-07T10:02:43.347+02:00")

public class PnfInfo {
	@JsonProperty("pnfId")
	private String pnfId = null;

	@JsonProperty("pnfName")
	private String pnfName = null;

	@JsonProperty("pnfdId")
	private String pnfdId = null;

	@JsonProperty("pnfdInfoId")
	private String pnfdInfoId = null;

	@JsonProperty("pnfProfileId")
	private String pnfProfileId = null;

	@JsonProperty("cpInfo")
	private PnfExtCpInfo cpInfo = null;

	public PnfInfo pnfId(final String pnfId) {
		this.pnfId = pnfId;
		return this;
	}

	/**
	 * Identifier of the PNF. This identifier is allocated by the OSS/BSS.
	 * 
	 * @return pnfId
	 **/
	@ApiModelProperty(required = true, value = "Identifier of the PNF. This identifier is allocated by the OSS/BSS. ")
	@NotNull

	public String getPnfId() {
		return pnfId;
	}

	public void setPnfId(final String pnfId) {
		this.pnfId = pnfId;
	}

	public PnfInfo pnfName(final String pnfName) {
		this.pnfName = pnfName;
		return this;
	}

	/**
	 * Name of the PNF.
	 * 
	 * @return pnfName
	 **/
	@ApiModelProperty(value = "Name of the PNF. ")

	public String getPnfName() {
		return pnfName;
	}

	public void setPnfName(final String pnfName) {
		this.pnfName = pnfName;
	}

	public PnfInfo pnfdId(final String pnfdId) {
		this.pnfdId = pnfdId;
		return this;
	}

	/**
	 * Identifier of the PNFD on which the PNF is based.
	 * 
	 * @return pnfdId
	 **/
	@ApiModelProperty(required = true, value = "Identifier of the PNFD on which the PNF is based. ")
	@NotNull

	public String getPnfdId() {
		return pnfdId;
	}

	public void setPnfdId(final String pnfdId) {
		this.pnfdId = pnfdId;
	}

	public PnfInfo pnfdInfoId(final String pnfdInfoId) {
		this.pnfdInfoId = pnfdInfoId;
		return this;
	}

	/**
	 * Identifier of the PNFD information onject related to this PNF. This
	 * identifier is allocated by the NFVO
	 * 
	 * @return pnfdInfoId
	 **/
	@ApiModelProperty(required = true, value = "Identifier of the PNFD information onject related to this PNF. This identifier is allocated by the NFVO ")
	@NotNull

	public String getPnfdInfoId() {
		return pnfdInfoId;
	}

	public void setPnfdInfoId(final String pnfdInfoId) {
		this.pnfdInfoId = pnfdInfoId;
	}

	public PnfInfo pnfProfileId(final String pnfProfileId) {
		this.pnfProfileId = pnfProfileId;
		return this;
	}

	/**
	 * Identifier of the related PnfProfile in the NSD on which the PNF is based.
	 * 
	 * @return pnfProfileId
	 **/
	@ApiModelProperty(required = true, value = "Identifier of the related PnfProfile in the NSD on which the PNF is based. ")
	@NotNull

	public String getPnfProfileId() {
		return pnfProfileId;
	}

	public void setPnfProfileId(final String pnfProfileId) {
		this.pnfProfileId = pnfProfileId;
	}

	public PnfInfo cpInfo(final PnfExtCpInfo cpInfo) {
		this.cpInfo = cpInfo;
		return this;
	}

	/**
	 * Information on the external CP of the PNF.
	 * 
	 * @return cpInfo
	 **/
	@ApiModelProperty(value = "Information on the external CP of the PNF. ")

	@Valid

	public PnfExtCpInfo getCpInfo() {
		return cpInfo;
	}

	public void setCpInfo(final PnfExtCpInfo cpInfo) {
		this.cpInfo = cpInfo;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final PnfInfo pnfInfo = (PnfInfo) o;
		return Objects.equals(this.pnfId, pnfInfo.pnfId) &&
				Objects.equals(this.pnfName, pnfInfo.pnfName) &&
				Objects.equals(this.pnfdId, pnfInfo.pnfdId) &&
				Objects.equals(this.pnfdInfoId, pnfInfo.pnfdInfoId) &&
				Objects.equals(this.pnfProfileId, pnfInfo.pnfProfileId) &&
				Objects.equals(this.cpInfo, pnfInfo.cpInfo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(pnfId, pnfName, pnfdId, pnfdInfoId, pnfProfileId, cpInfo);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class PnfInfo {\n");

		sb.append("    pnfId: ").append(toIndentedString(pnfId)).append("\n");
		sb.append("    pnfName: ").append(toIndentedString(pnfName)).append("\n");
		sb.append("    pnfdId: ").append(toIndentedString(pnfdId)).append("\n");
		sb.append("    pnfdInfoId: ").append(toIndentedString(pnfdInfoId)).append("\n");
		sb.append("    pnfProfileId: ").append(toIndentedString(pnfProfileId)).append("\n");
		sb.append("    cpInfo: ").append(toIndentedString(cpInfo)).append("\n");
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
