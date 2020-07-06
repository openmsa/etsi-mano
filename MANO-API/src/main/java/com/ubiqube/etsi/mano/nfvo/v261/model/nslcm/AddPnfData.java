package com.ubiqube.etsi.mano.nfvo.v261.model.nslcm;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type specifies an PNF to be added to the NS instance and the PNF Profile
 * to use for this PNF. It shall comply with the provisions defined in Table
 * 6.5.3.14-1.
 */
@ApiModel(description = "This type specifies an PNF to be added to the NS instance and the PNF Profile to use for this PNF. It shall comply with the provisions defined in Table 6.5.3.14-1. ")
@Validated


public class AddPnfData {
	@JsonProperty("pnfId")
	private String pnfId = null;

	@JsonProperty("pnfName")
	private String pnfName = null;

	@JsonProperty("pnfdId")
	private String pnfdId = null;

	@JsonProperty("pnfProfileId")
	private String pnfProfileId = null;

	@JsonProperty("cpData")
	@Valid
	private List<PnfExtCpData> cpData = null;

	public AddPnfData pnfId(final String pnfId) {
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

	public AddPnfData pnfName(final String pnfName) {
		this.pnfName = pnfName;
		return this;
	}

	/**
	 * Name of the PNF
	 * 
	 * @return pnfName
	 **/
	@ApiModelProperty(required = true, value = "Name of the PNF ")
	@NotNull

	public String getPnfName() {
		return pnfName;
	}

	public void setPnfName(final String pnfName) {
		this.pnfName = pnfName;
	}

	public AddPnfData pnfdId(final String pnfdId) {
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

	public AddPnfData pnfProfileId(final String pnfProfileId) {
		this.pnfProfileId = pnfProfileId;
		return this;
	}

	/**
	 * Identifier of related PnfProfile in the NSD on which the PNF is based.
	 * 
	 * @return pnfProfileId
	 **/
	@ApiModelProperty(required = true, value = "Identifier of related PnfProfile in the NSD on which the PNF is based. ")
	@NotNull

	public String getPnfProfileId() {
		return pnfProfileId;
	}

	public void setPnfProfileId(final String pnfProfileId) {
		this.pnfProfileId = pnfProfileId;
	}

	public AddPnfData cpData(final List<PnfExtCpData> cpData) {
		this.cpData = cpData;
		return this;
	}

	public AddPnfData addCpDataItem(final PnfExtCpData cpDataItem) {
		if (this.cpData == null) {
			this.cpData = new ArrayList<>();
		}
		this.cpData.add(cpDataItem);
		return this;
	}

	/**
	 * Address assigned for the PNF external CP(s).
	 * 
	 * @return cpData
	 **/
	@ApiModelProperty(value = "Address assigned for the PNF external CP(s). ")

	@Valid

	public List<PnfExtCpData> getCpData() {
		return cpData;
	}

	public void setCpData(final List<PnfExtCpData> cpData) {
		this.cpData = cpData;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final AddPnfData addPnfData = (AddPnfData) o;
		return Objects.equals(this.pnfId, addPnfData.pnfId) &&
				Objects.equals(this.pnfName, addPnfData.pnfName) &&
				Objects.equals(this.pnfdId, addPnfData.pnfdId) &&
				Objects.equals(this.pnfProfileId, addPnfData.pnfProfileId) &&
				Objects.equals(this.cpData, addPnfData.cpData);
	}

	@Override
	public int hashCode() {
		return Objects.hash(pnfId, pnfName, pnfdId, pnfProfileId, cpData);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class AddPnfData {\n");

		sb.append("    pnfId: ").append(toIndentedString(pnfId)).append("\n");
		sb.append("    pnfName: ").append(toIndentedString(pnfName)).append("\n");
		sb.append("    pnfdId: ").append(toIndentedString(pnfdId)).append("\n");
		sb.append("    pnfProfileId: ").append(toIndentedString(pnfProfileId)).append("\n");
		sb.append("    cpData: ").append(toIndentedString(cpData)).append("\n");
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
