package com.ubiqube.etsi.mano.model.nslcm.sol005;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type contains information used to create or modify NFP instance
 * parameters for the update of an existing VNFFG instance. It shall comply with
 * the provisions defined in Table 6.5.3.38-1.
 */
@ApiModel(description = "This type contains information used to create or modify NFP instance parameters for the update of an existing VNFFG instance. It shall comply with the provisions defined in Table 6.5.3.38-1. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-07T10:02:43.347+02:00")

public class NfpData {
	@JsonProperty("nfpInfoId")
	private String nfpInfoId = null;

	@JsonProperty("nfpName")
	private String nfpName = null;

	@JsonProperty("description")
	private String description = null;

	@JsonProperty("cpGroup")
	@Valid
	private List<CpGroupInfo> cpGroup = null;

	@JsonProperty("nfpRule")
	private NfpRule nfpRule = null;

	public NfpData nfpInfoId(final String nfpInfoId) {
		this.nfpInfoId = nfpInfoId;
		return this;
	}

	/**
	 * Identifier of the NFP to be modified. It shall be present for modified NFPs
	 * and shall be absent for the new NFP. It shall be present for modified NFPs
	 * and shall be absent for the new NFP.
	 * 
	 * @return nfpInfoId
	 **/
	@ApiModelProperty(value = "Identifier of the NFP to be modified. It shall be present for modified NFPs and shall be absent for the new NFP. It shall be present for modified NFPs and shall be absent  for the new NFP. ")

	public String getNfpInfoId() {
		return nfpInfoId;
	}

	public void setNfpInfoId(final String nfpInfoId) {
		this.nfpInfoId = nfpInfoId;
	}

	public NfpData nfpName(final String nfpName) {
		this.nfpName = nfpName;
		return this;
	}

	/**
	 * Human readable name for the NFP. It shall be present for the new NFP, and it
	 * may be present otherwise. It shall be present for the new NFP, and it may be
	 * present otherwise.
	 * 
	 * @return nfpName
	 **/
	@ApiModelProperty(value = "Human readable name for the NFP. It shall be present for the new NFP, and it may be present otherwise. It shall be present for the new NFP, and it may be  present otherwise. ")

	public String getNfpName() {
		return nfpName;
	}

	public void setNfpName(final String nfpName) {
		this.nfpName = nfpName;
	}

	public NfpData description(final String description) {
		this.description = description;
		return this;
	}

	/**
	 * Human readable description for the NFP. It shall be present for the new NFP,
	 * and it may be present otherwise. It shall be present for the new NFP, and it
	 * may be present otherwise.
	 * 
	 * @return description
	 **/
	@ApiModelProperty(value = "Human readable description for the NFP. It shall be present for the new NFP, and it may be present otherwise. It shall be present for the new NFP, and it may be  present otherwise. ")

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public NfpData cpGroup(final List<CpGroupInfo> cpGroup) {
		this.cpGroup = cpGroup;
		return this;
	}

	public NfpData addCpGroupItem(final CpGroupInfo cpGroupItem) {
		if (this.cpGroup == null) {
			this.cpGroup = new ArrayList<>();
		}
		this.cpGroup.add(cpGroupItem);
		return this;
	}

	/**
	 * Group(s) of CPs and/or SAPs which the NFP passes by. Cardinality can be 0 if
	 * only updated or newly created NFP classification and selection rule which
	 * applied to an existing NFP is provided. At least a CP or an nfpRule shall be
	 * present. When multiple identifiers are included, the position of the
	 * identifier in the cpGroup value specifies the position of the group in the
	 * path.
	 * 
	 * @return cpGroup
	 **/
	@ApiModelProperty(value = "Group(s) of CPs and/or SAPs which the NFP passes by. Cardinality can be 0 if only updated or newly created NFP classification and selection rule which applied to an existing NFP is provided. At least a CP or an nfpRule shall be present. When multiple identifiers are included, the position of  the identifier in the cpGroup value specifies the position  of the group in the path. ")

	@Valid

	public List<CpGroupInfo> getCpGroup() {
		return cpGroup;
	}

	public void setCpGroup(final List<CpGroupInfo> cpGroup) {
		this.cpGroup = cpGroup;
	}

	public NfpData nfpRule(final NfpRule nfpRule) {
		this.nfpRule = nfpRule;
		return this;
	}

	/**
	 * NFP classification and selection rule. See note 1.
	 * 
	 * @return nfpRule
	 **/
	@ApiModelProperty(value = "NFP classification and selection rule. See note 1. ")

	@Valid

	public NfpRule getNfpRule() {
		return nfpRule;
	}

	public void setNfpRule(final NfpRule nfpRule) {
		this.nfpRule = nfpRule;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final NfpData nfpData = (NfpData) o;
		return Objects.equals(this.nfpInfoId, nfpData.nfpInfoId) &&
				Objects.equals(this.nfpName, nfpData.nfpName) &&
				Objects.equals(this.description, nfpData.description) &&
				Objects.equals(this.cpGroup, nfpData.cpGroup) &&
				Objects.equals(this.nfpRule, nfpData.nfpRule);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nfpInfoId, nfpName, description, cpGroup, nfpRule);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class NfpData {\n");

		sb.append("    nfpInfoId: ").append(toIndentedString(nfpInfoId)).append("\n");
		sb.append("    nfpName: ").append(toIndentedString(nfpName)).append("\n");
		sb.append("    description: ").append(toIndentedString(description)).append("\n");
		sb.append("    cpGroup: ").append(toIndentedString(cpGroup)).append("\n");
		sb.append("    nfpRule: ").append(toIndentedString(nfpRule)).append("\n");
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
