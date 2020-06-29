package com.ubiqube.etsi.mano.nfvo.v261.model.nslcm;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.common.v261.model.nslcm.CpProtocolData;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents the information related to a SAP of a NS. It shall
 * comply with the provisions defined in Table 6.5.3.10-1.
 */
@ApiModel(description = "This type represents the information related to a SAP of a NS. It shall comply with the provisions defined in Table 6.5.3.10-1. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-07T10:02:43.347+02:00")

public class SapData {
	@JsonProperty("sapdId")
	private String sapdId = null;

	@JsonProperty("sapName")
	private String sapName = null;

	@JsonProperty("description")
	private String description = null;

	@JsonProperty("sapProtocolData")
	@Valid
	private List<CpProtocolData> sapProtocolData = null;

	public SapData sapdId(final String sapdId) {
		this.sapdId = sapdId;
		return this;
	}

	/**
	 * Reference to the SAPD for this SAP.
	 *
	 * @return sapdId
	 **/
	@ApiModelProperty(required = true, value = "Reference to the SAPD for this SAP. ")
	@NotNull

	public String getSapdId() {
		return sapdId;
	}

	public void setSapdId(final String sapdId) {
		this.sapdId = sapdId;
	}

	public SapData sapName(final String sapName) {
		this.sapName = sapName;
		return this;
	}

	/**
	 * Human readable name for the SAP.
	 *
	 * @return sapName
	 **/
	@ApiModelProperty(required = true, value = "Human readable name for the SAP. ")
	@NotNull

	public String getSapName() {
		return sapName;
	}

	public void setSapName(final String sapName) {
		this.sapName = sapName;
	}

	public SapData description(final String description) {
		this.description = description;
		return this;
	}

	/**
	 * Human readable description for the SAP.
	 *
	 * @return description
	 **/
	@ApiModelProperty(required = true, value = "Human readable description for the SAP. ")
	@NotNull

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public SapData sapProtocolData(final List<CpProtocolData> sapProtocolData) {
		this.sapProtocolData = sapProtocolData;
		return this;
	}

	public SapData addSapProtocolDataItem(final CpProtocolData sapProtocolDataItem) {
		if (this.sapProtocolData == null) {
			this.sapProtocolData = new ArrayList<>();
		}
		this.sapProtocolData.add(sapProtocolDataItem);
		return this;
	}

	/**
	 * Parameters for configuring the network protocols on the SAP.
	 *
	 * @return sapProtocolData
	 **/
	@ApiModelProperty(value = "Parameters for configuring the network protocols on the SAP. ")

	@Valid

	public List<CpProtocolData> getSapProtocolData() {
		return sapProtocolData;
	}

	public void setSapProtocolData(final List<CpProtocolData> sapProtocolData) {
		this.sapProtocolData = sapProtocolData;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final SapData sapData = (SapData) o;
		return Objects.equals(this.sapdId, sapData.sapdId) &&
				Objects.equals(this.sapName, sapData.sapName) &&
				Objects.equals(this.description, sapData.description) &&
				Objects.equals(this.sapProtocolData, sapData.sapProtocolData);
	}

	@Override
	public int hashCode() {
		return Objects.hash(sapdId, sapName, description, sapProtocolData);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class SapData {\n");

		sb.append("    sapdId: ").append(toIndentedString(sapdId)).append("\n");
		sb.append("    sapName: ").append(toIndentedString(sapName)).append("\n");
		sb.append("    description: ").append(toIndentedString(description)).append("\n");
		sb.append("    sapProtocolData: ").append(toIndentedString(sapProtocolData)).append("\n");
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
