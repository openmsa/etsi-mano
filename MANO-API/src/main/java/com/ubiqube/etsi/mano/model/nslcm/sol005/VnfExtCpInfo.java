package com.ubiqube.etsi.mano.model.nslcm.sol005;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.model.nslcm.CpProtocolInfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents information about an external CP of a VNF. It shall
 * comply with the provisions defined in Table 6.5.3.70-1.
 */
@ApiModel(description = "This type represents information about an external CP of a VNF. It shall comply  with the provisions defined in Table 6.5.3.70-1. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-07T10:02:43.347+02:00")

public class VnfExtCpInfo {
	@JsonProperty("id")
	private String id = null;

	@JsonProperty("cpdId")
	private String cpdId = null;

	@JsonProperty("cpProtocolInfo")
	@Valid
	private List<CpProtocolInfo> cpProtocolInfo = null;

	@JsonProperty("extLinkPortId")
	private CpProtocolInfo extLinkPortId = null;

	@JsonProperty("metadata")
	private Map<String, String> metadata = null;

	@JsonProperty("associatedVnfcCpId")
	private String associatedVnfcCpId = null;

	@JsonProperty("associatedVnfVirtualLinkId")
	private String associatedVnfVirtualLinkId = null;

	public VnfExtCpInfo id(final String id) {
		this.id = id;
		return this;
	}

	/**
	 * Identifier of the external CP instance and the related information instance.
	 *
	 * @return id
	 **/
	@ApiModelProperty(required = true, value = "Identifier of the external CP instance and the related information instance. ")
	@NotNull

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public VnfExtCpInfo cpdId(final String cpdId) {
		this.cpdId = cpdId;
		return this;
	}

	/**
	 * Identifier of the external CPD, VnfExtCpd, in the VNFD.
	 *
	 * @return cpdId
	 **/
	@ApiModelProperty(required = true, value = "Identifier of the external CPD, VnfExtCpd, in the VNFD. ")
	@NotNull

	public String getCpdId() {
		return cpdId;
	}

	public void setCpdId(final String cpdId) {
		this.cpdId = cpdId;
	}

	public VnfExtCpInfo cpProtocolInfo(final List<CpProtocolInfo> cpProtocolInfo) {
		this.cpProtocolInfo = cpProtocolInfo;
		return this;
	}

	public VnfExtCpInfo addCpProtocolInfoItem(final CpProtocolInfo cpProtocolInfoItem) {
		if (this.cpProtocolInfo == null) {
			this.cpProtocolInfo = new ArrayList<>();
		}
		this.cpProtocolInfo.add(cpProtocolInfoItem);
		return this;
	}

	/**
	 * Network protocol information for this CP.
	 *
	 * @return cpProtocolInfo
	 **/
	@ApiModelProperty(value = "Network protocol information for this CP. ")

	@Valid
	@Size(min = 1)
	public List<CpProtocolInfo> getCpProtocolInfo() {
		return cpProtocolInfo;
	}

	public void setCpProtocolInfo(final List<CpProtocolInfo> cpProtocolInfo) {
		this.cpProtocolInfo = cpProtocolInfo;
	}

	public VnfExtCpInfo extLinkPortId(final CpProtocolInfo extLinkPortId) {
		this.extLinkPortId = extLinkPortId;
		return this;
	}

	/**
	 * Identifier of the \"extLinkPortInfo\" structure inside the
	 * \"extVirtualLinkInfo\" structure. Shall be present if the CP is associated to
	 * a link port.
	 *
	 * @return extLinkPortId
	 **/
	@ApiModelProperty(value = "Identifier of the \"extLinkPortInfo\" structure inside the \"extVirtualLinkInfo\"  structure. Shall be present if the CP is associated to a link port. ")

	@Valid

	public CpProtocolInfo getExtLinkPortId() {
		return extLinkPortId;
	}

	public void setExtLinkPortId(final CpProtocolInfo extLinkPortId) {
		this.extLinkPortId = extLinkPortId;
	}

	public VnfExtCpInfo metadata(final Map<String, String> metadata) {
		this.metadata = metadata;
		return this;
	}

	/**
	 * Metadata about this external CP.
	 *
	 * @return metadata
	 **/
	@ApiModelProperty(value = "Metadata about this external CP. ")

	@Valid

	public Map<String, String> getMetadata() {
		return metadata;
	}

	public void setMetadata(final Map<String, String> metadata) {
		this.metadata = metadata;
	}

	public VnfExtCpInfo associatedVnfcCpId(final String associatedVnfcCpId) {
		this.associatedVnfcCpId = associatedVnfcCpId;
		return this;
	}

	/**
	 * Identifier of the \"vnfcCpInfo\" structure in \"VnfcResourceInfo\" structure
	 * that represents the VNFC CP which is exposed by this external CP instance.
	 * Shall be present in case this CP instance maps to a VNFC CP(s). The
	 * attributes \"associatedVnfcCpId\" and \"associatedVnfVirtualLinkId\" are
	 * mutually exclusive. One and only one shall be present.
	 *
	 * @return associatedVnfcCpId
	 **/
	@ApiModelProperty(value = "Identifier of the \"vnfcCpInfo\" structure in \"VnfcResourceInfo\" structure  that represents the VNFC CP which is exposed by this external CP instance.  Shall be present in case this CP instance maps to a VNFC CP(s). The attributes \"associatedVnfcCpId\" and \"associatedVnfVirtualLinkId\" are  mutually exclusive. One and only one shall be present. ")

	public String getAssociatedVnfcCpId() {
		return associatedVnfcCpId;
	}

	public void setAssociatedVnfcCpId(final String associatedVnfcCpId) {
		this.associatedVnfcCpId = associatedVnfcCpId;
	}

	public VnfExtCpInfo associatedVnfVirtualLinkId(final String associatedVnfVirtualLinkId) {
		this.associatedVnfVirtualLinkId = associatedVnfVirtualLinkId;
		return this;
	}

	/**
	 * Identifier of the \"VnfVirtualLinkResourceInfo\" structure that represents
	 * the internal VL, which is exposed by this external CP instance. Shall be
	 * present in case this CP instance maps to an internal VL. The attributes
	 * \"associatedVnfcCpId\" and \"associatedVnfVirtualLinkId\" are mutually
	 * exclusive. One and only one shall be present.
	 *
	 * @return associatedVnfVirtualLinkId
	 **/
	@ApiModelProperty(value = "Identifier of the \"VnfVirtualLinkResourceInfo\" structure that represents  the internal VL, which is exposed by this external CP instance. Shall be  present in case this CP instance maps to an internal VL. The attributes \"associatedVnfcCpId\" and \"associatedVnfVirtualLinkId\" are  mutually exclusive. One and only one shall be present. ")

	public String getAssociatedVnfVirtualLinkId() {
		return associatedVnfVirtualLinkId;
	}

	public void setAssociatedVnfVirtualLinkId(final String associatedVnfVirtualLinkId) {
		this.associatedVnfVirtualLinkId = associatedVnfVirtualLinkId;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final VnfExtCpInfo vnfExtCpInfo = (VnfExtCpInfo) o;
		return Objects.equals(this.id, vnfExtCpInfo.id) &&
				Objects.equals(this.cpdId, vnfExtCpInfo.cpdId) &&
				Objects.equals(this.cpProtocolInfo, vnfExtCpInfo.cpProtocolInfo) &&
				Objects.equals(this.extLinkPortId, vnfExtCpInfo.extLinkPortId) &&
				Objects.equals(this.metadata, vnfExtCpInfo.metadata) &&
				Objects.equals(this.associatedVnfcCpId, vnfExtCpInfo.associatedVnfcCpId) &&
				Objects.equals(this.associatedVnfVirtualLinkId, vnfExtCpInfo.associatedVnfVirtualLinkId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, cpdId, cpProtocolInfo, extLinkPortId, metadata, associatedVnfcCpId, associatedVnfVirtualLinkId);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfExtCpInfo {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    cpdId: ").append(toIndentedString(cpdId)).append("\n");
		sb.append("    cpProtocolInfo: ").append(toIndentedString(cpProtocolInfo)).append("\n");
		sb.append("    extLinkPortId: ").append(toIndentedString(extLinkPortId)).append("\n");
		sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
		sb.append("    associatedVnfcCpId: ").append(toIndentedString(associatedVnfcCpId)).append("\n");
		sb.append("    associatedVnfVirtualLinkId: ").append(toIndentedString(associatedVnfVirtualLinkId)).append("\n");
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
