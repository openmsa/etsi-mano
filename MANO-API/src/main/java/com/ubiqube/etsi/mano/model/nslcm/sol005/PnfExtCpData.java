package com.ubiqube.etsi.mano.model.nslcm.sol005;

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
 * This type represents the configuration data on the external CP of the PNF. It
 * shall comply with the provisions defined in Table 6.5.3.16-1.
 */
@ApiModel(description = "This type represents the configuration data on the external CP of the PNF. It shall comply with the provisions defined in Table 6.5.3.16-1. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-07T10:02:43.347+02:00")

public class PnfExtCpData {
	@JsonProperty("cpInstanceI16")
	private String cpInstanceI16 = null;

	@JsonProperty("cpdId")
	private String cpdId = null;

	@JsonProperty("cpProtocolData")
	@Valid
	private List<CpProtocolData> cpProtocolData = new ArrayList<>();

	public PnfExtCpData cpInstanceI16(final String cpInstanceI16) {
		this.cpInstanceI16 = cpInstanceI16;
		return this;
	}

	/**
	 * Identifier of the CP. Shall be present for existing CP.
	 * 
	 * @return cpInstanceI16
	 **/
	@ApiModelProperty(value = "Identifier of the CP. Shall be present for existing CP. ")

	public String getCpInstanceI16() {
		return cpInstanceI16;
	}

	public void setCpInstanceI16(final String cpInstanceI16) {
		this.cpInstanceI16 = cpInstanceI16;
	}

	public PnfExtCpData cpdId(final String cpdId) {
		this.cpdId = cpdId;
		return this;
	}

	/**
	 * Identifier of the Connection Point Descriptor (CPD) for this CP. Shall be
	 * present for new CP.
	 * 
	 * @return cpdId
	 **/
	@ApiModelProperty(value = "Identifier of the Connection Point Descriptor (CPD) for this CP. Shall be present for new CP. ")

	public String getCpdId() {
		return cpdId;
	}

	public void setCpdId(final String cpdId) {
		this.cpdId = cpdId;
	}

	public PnfExtCpData cpProtocolData(final List<CpProtocolData> cpProtocolData) {
		this.cpProtocolData = cpProtocolData;
		return this;
	}

	public PnfExtCpData addCpProtocolDataItem(final CpProtocolData cpProtocolDataItem) {
		this.cpProtocolData.add(cpProtocolDataItem);
		return this;
	}

	/**
	 * Address assigned for this CP.
	 * 
	 * @return cpProtocolData
	 **/
	@ApiModelProperty(required = true, value = "Address assigned for this CP. ")
	@NotNull

	@Valid

	public List<CpProtocolData> getCpProtocolData() {
		return cpProtocolData;
	}

	public void setCpProtocolData(final List<CpProtocolData> cpProtocolData) {
		this.cpProtocolData = cpProtocolData;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final PnfExtCpData pnfExtCpData = (PnfExtCpData) o;
		return Objects.equals(this.cpInstanceI16, pnfExtCpData.cpInstanceI16) &&
				Objects.equals(this.cpdId, pnfExtCpData.cpdId) &&
				Objects.equals(this.cpProtocolData, pnfExtCpData.cpProtocolData);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpInstanceI16, cpdId, cpProtocolData);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class PnfExtCpData {\n");

		sb.append("    cpInstanceI16: ").append(toIndentedString(cpInstanceI16)).append("\n");
		sb.append("    cpdId: ").append(toIndentedString(cpdId)).append("\n");
		sb.append("    cpProtocolData: ").append(toIndentedString(cpProtocolData)).append("\n");
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
