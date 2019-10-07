package com.ubiqube.etsi.mano.model.nslcm.sol005;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.model.nslcm.ExtVirtualLinkInfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Information about the changed VNF instance information, including VNF
 * configurable properties,if applicable. When the \&quot;changedInfo\&quot;
 * attribute is present, either the \&quot;changedVnfInfo\&quot; attribute or
 * the \&quot;changedExtConnectivity\&quot; attribute or both shall be present.
 */
@ApiModel(description = "Information about the changed VNF instance information, including VNF configurable properties,if applicable. When the \"changedInfo\" attribute is present,  either the \"changedVnfInfo\" attribute or the \"changedExtConnectivity\" attribute or both shall be present. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-07T10:02:43.347+02:00")

public class AffectedVnfChangedInfo {
	@JsonProperty("changedVnfInfo")
	private ModifyVnfInfoData changedVnfInfo = null;

	@JsonProperty("changedExtConnectivity")
	private ExtVirtualLinkInfo changedExtConnectivity = null;

	public AffectedVnfChangedInfo changedVnfInfo(final ModifyVnfInfoData changedVnfInfo) {
		this.changedVnfInfo = changedVnfInfo;
		return this;
	}

	/**
	 * Information about the changed VNF instance information, including
	 * configurable properties, if applicable.
	 *
	 * @return changedVnfInfo
	 **/
	@ApiModelProperty(value = "Information about the changed VNF instance information, including configurable properties,  if applicable. ")

	@Valid

	public ModifyVnfInfoData getChangedVnfInfo() {
		return changedVnfInfo;
	}

	public void setChangedVnfInfo(final ModifyVnfInfoData changedVnfInfo) {
		this.changedVnfInfo = changedVnfInfo;
	}

	public AffectedVnfChangedInfo changedExtConnectivity(final ExtVirtualLinkInfo changedExtConnectivity) {
		this.changedExtConnectivity = changedExtConnectivity;
		return this;
	}

	/**
	 * Information about changed external connectivity, if applicable.
	 *
	 * @return changedExtConnectivity
	 **/
	@ApiModelProperty(value = "Information about changed external connectivity, if applicable. ")

	@Valid

	public ExtVirtualLinkInfo getChangedExtConnectivity() {
		return changedExtConnectivity;
	}

	public void setChangedExtConnectivity(final ExtVirtualLinkInfo changedExtConnectivity) {
		this.changedExtConnectivity = changedExtConnectivity;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final AffectedVnfChangedInfo affectedVnfChangedInfo = (AffectedVnfChangedInfo) o;
		return Objects.equals(this.changedVnfInfo, affectedVnfChangedInfo.changedVnfInfo) &&
				Objects.equals(this.changedExtConnectivity, affectedVnfChangedInfo.changedExtConnectivity);
	}

	@Override
	public int hashCode() {
		return Objects.hash(changedVnfInfo, changedExtConnectivity);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class AffectedVnfChangedInfo {\n");

		sb.append("    changedVnfInfo: ").append(toIndentedString(changedVnfInfo)).append("\n");
		sb.append("    changedExtConnectivity: ").append(toIndentedString(changedExtConnectivity)).append("\n");
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
