/*
 * SOL003 - VNF Lifecycle Management interface
 * SOL003 - VNF Lifecycle Management interface definition  IMPORTANT: Please note that this file might be not aligned to the current version of the ETSI Group Specification it refers to. In case of discrepancies the published ETSI Group Specification takes precedence.  In clause 4.3.2 of ETSI GS NFV-SOL 003 v2.4.1, an attribute-based filtering mechanism is defined. This mechanism is currently not included in the corresponding OpenAPI design for this GS version. Changes to the attribute-based filtering mechanism are being considered in v2.5.1 of this GS for inclusion in the corresponding future ETSI NFV OpenAPI design. Please report bugs to https://forge.etsi.org/bugzilla/buglist.cgi?component=Nfv-Openapis&list_id=61&product=NFV&resolution=
 *
 * OpenAPI spec version: 1.1.0
 *
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package com.ubiqube.etsi.mano.vnfm.v261.model.nslcm;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.nfvo.v261.model.nslcm.CpProtocolInfo;

import io.swagger.annotations.ApiModelProperty;

/**
 * VnfInstanceInstantiatedVnfInfoExtCpInfo
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2019-06-13T10:04:39.223+02:00")
public class VnfInstanceInstantiatedVnfInfoExtCpInfo {
	@JsonProperty("id")
	private String id = null;

	@JsonProperty("cpdId")
	private String cpdId = null;

	@JsonProperty("cpProtocolInfo")
	private List<CpProtocolInfo> cpProtocolInfo = null;

	@JsonProperty("extLinkPortId")
	private String extLinkPortId = null;

	public VnfInstanceInstantiatedVnfInfoExtCpInfo id(final String id) {
		this.id = id;
		return this;
	}

	/**
	 * Identifier of the external CP instance and the related information instance.
	 *
	 * @return id
	 **/
	@JsonProperty("id")
	@ApiModelProperty(required = true, value = "Identifier of the external CP instance and the related information instance. ")
	@NotNull
	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public VnfInstanceInstantiatedVnfInfoExtCpInfo cpdId(final String cpdId) {
		this.cpdId = cpdId;
		return this;
	}

	/**
	 * Identifier of the external CPD, VnfExtCpd, in the VNFD.
	 *
	 * @return cpdId
	 **/
	@JsonProperty("cpdId")
	@ApiModelProperty(required = true, value = "Identifier of the external CPD, VnfExtCpd, in the VNFD. ")
	@NotNull
	public String getCpdId() {
		return cpdId;
	}

	public void setCpdId(final String cpdId) {
		this.cpdId = cpdId;
	}

	public VnfInstanceInstantiatedVnfInfoExtCpInfo cpProtocolInfo(final List<CpProtocolInfo> cpProtocolInfo) {
		this.cpProtocolInfo = cpProtocolInfo;
		return this;
	}

	public VnfInstanceInstantiatedVnfInfoExtCpInfo addCpProtocolInfoItem(final CpProtocolInfo cpProtocolInfoItem) {
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
	@JsonProperty("cpProtocolInfo")
	@ApiModelProperty(value = "Network protocol information for this CP. ")
	public List<CpProtocolInfo> getCpProtocolInfo() {
		return cpProtocolInfo;
	}

	public void setCpProtocolInfo(final List<CpProtocolInfo> cpProtocolInfo) {
		this.cpProtocolInfo = cpProtocolInfo;
	}

	public VnfInstanceInstantiatedVnfInfoExtCpInfo extLinkPortId(final String extLinkPortId) {
		this.extLinkPortId = extLinkPortId;
		return this;
	}

	/**
	 * Identifier of the \&quot;extLinkPortInfo\&quot; structure inside the the
	 * \&quot;extVirtualLinkInfo\&quot; structure. Shall be present if the CP is
	 * associated to a link port.
	 *
	 * @return extLinkPortId
	 **/
	@JsonProperty("extLinkPortId")
	@ApiModelProperty(value = "Identifier of the \"extLinkPortInfo\" structure inside the  the \"extVirtualLinkInfo\" structure. Shall be present if the CP is associated to a link port. ")
	public String getExtLinkPortId() {
		return extLinkPortId;
	}

	public void setExtLinkPortId(final String extLinkPortId) {
		this.extLinkPortId = extLinkPortId;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfInstanceInstantiatedVnfInfoExtCpInfo {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    cpdId: ").append(toIndentedString(cpdId)).append("\n");
		sb.append("    cpProtocolInfo: ").append(toIndentedString(cpProtocolInfo)).append("\n");
		sb.append("    extLinkPortId: ").append(toIndentedString(extLinkPortId)).append("\n");
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
