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
 * This type specifies an PNF to be modified in the NS instance. It shall comply
 * with the provisions defined in Table 6.5.3.15-1.
 */
@ApiModel(description = "This type specifies an PNF to be modified in the NS instance. It shall comply with the provisions defined in Table 6.5.3.15-1. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-07T10:02:43.347+02:00")

public class ModifyPnfData {
	@JsonProperty("pnfId")
	private String pnfId = null;

	@JsonProperty("pnfName")
	private String pnfName = null;

	@JsonProperty("cpData")
	@Valid
	private List<PnfExtCpData> cpData = null;

	public ModifyPnfData pnfId(final String pnfId) {
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

	public ModifyPnfData pnfName(final String pnfName) {
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

	public ModifyPnfData cpData(final List<PnfExtCpData> cpData) {
		this.cpData = cpData;
		return this;
	}

	public ModifyPnfData addCpDataItem(final PnfExtCpData cpDataItem) {
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
		final ModifyPnfData modifyPnfData = (ModifyPnfData) o;
		return Objects.equals(this.pnfId, modifyPnfData.pnfId) &&
				Objects.equals(this.pnfName, modifyPnfData.pnfName) &&
				Objects.equals(this.cpData, modifyPnfData.cpData);
	}

	@Override
	public int hashCode() {
		return Objects.hash(pnfId, pnfName, cpData);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class ModifyPnfData {\n");

		sb.append("    pnfId: ").append(toIndentedString(pnfId)).append("\n");
		sb.append("    pnfName: ").append(toIndentedString(pnfName)).append("\n");
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
