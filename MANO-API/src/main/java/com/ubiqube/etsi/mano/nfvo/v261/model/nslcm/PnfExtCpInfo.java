package com.ubiqube.etsi.mano.nfvo.v261.model.nslcm;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.model.CpProtocolData;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents the information about the external CP of the PNF. It
 * shall comply with the provisions defined in Table 6.5.3.17-1.
 */
@ApiModel(description = "This type represents the information about the external CP of the PNF.  It shall comply with the provisions defined in Table 6.5.3.17-1. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-07T10:02:43.347+02:00")

public class PnfExtCpInfo {
	@JsonProperty("cpInstanceId")
	private String cpInstanceId = null;

	@JsonProperty("cpdId")
	private String cpdId = null;

	@JsonProperty("cpProtocolData")
	@Valid
	private List<CpProtocolData> cpProtocolData = null;

	public PnfExtCpInfo cpInstanceId(final String cpInstanceId) {
		this.cpInstanceId = cpInstanceId;
		return this;
	}

	/**
	 * Identifier of the CP in the scope of the PNF.
	 *
	 * @return cpInstanceId
	 **/
	@ApiModelProperty(required = true, value = "Identifier of the CP in the scope of the PNF. ")
	@NotNull

	public String getCpInstanceId() {
		return cpInstanceId;
	}

	public void setCpInstanceId(final String cpInstanceId) {
		this.cpInstanceId = cpInstanceId;
	}

	public PnfExtCpInfo cpdId(final String cpdId) {
		this.cpdId = cpdId;
		return this;
	}

	/**
	 * Identifier of (reference to) the Connection Point Descriptor (CPD) for this
	 * CP.
	 *
	 * @return cpdId
	 **/
	@ApiModelProperty(required = true, value = "Identifier of (reference to) the Connection Point Descriptor (CPD) for this CP. ")
	@NotNull

	public String getCpdId() {
		return cpdId;
	}

	public void setCpdId(final String cpdId) {
		this.cpdId = cpdId;
	}

	public PnfExtCpInfo cpProtocolData(final List<CpProtocolData> cpProtocolData) {
		this.cpProtocolData = cpProtocolData;
		return this;
	}

	public PnfExtCpInfo addCpProtocolDataItem(final CpProtocolData cpProtocolDataItem) {
		if (this.cpProtocolData == null) {
			this.cpProtocolData = new ArrayList<>();
		}
		this.cpProtocolData.add(cpProtocolDataItem);
		return this;
	}

	/**
	 * Parameters for configuring the network protocols on the CP.
	 *
	 * @return cpProtocolData
	 **/
	@ApiModelProperty(value = "Parameters for configuring the network protocols on the CP. ")

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
		final PnfExtCpInfo pnfExtCpInfo = (PnfExtCpInfo) o;
		return Objects.equals(this.cpInstanceId, pnfExtCpInfo.cpInstanceId) &&
				Objects.equals(this.cpdId, pnfExtCpInfo.cpdId) &&
				Objects.equals(this.cpProtocolData, pnfExtCpInfo.cpProtocolData);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpInstanceId, cpdId, cpProtocolData);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class PnfExtCpInfo {\n");

		sb.append("    cpInstanceId: ").append(toIndentedString(cpInstanceId)).append("\n");
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
