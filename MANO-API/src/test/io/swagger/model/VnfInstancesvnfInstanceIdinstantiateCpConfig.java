package io.swagger.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This type represents an externally provided link port or network address information per instance of an external connection point. In case a link port is provided, the VNFM shall use that link port when connecting the external CP to the external VL. In a link port is not provided, the VNFM shall create a link port on the external VL, and use that link port to connect the external CP to the external VL.
 **/
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "This type represents an externally provided link port or network address information per instance of an external connection point. In case a link port is provided, the VNFM shall use that link port when connecting the external CP to the external VL. In a link port is not provided, the VNFM shall create a link port on the external VL, and use that link port to connect the external CP to the external VL. ")

public class VnfInstancesvnfInstanceIdinstantiateCpConfig {

	private @Valid String cpInstanceId = null;
	private @Valid String linkPortId = null;
	private @Valid List<VnfInstancesvnfInstanceIdinstantiateCpProtocolData> cpProtocolData = new ArrayList<VnfInstancesvnfInstanceIdinstantiateCpProtocolData>();

	/**
	 * An identifier that is unique for the respective type within a VNF instance,
	 * but may not be globally unique.
	 **/
	public VnfInstancesvnfInstanceIdinstantiateCpConfig cpInstanceId(String cpInstanceId) {
		this.cpInstanceId = cpInstanceId;
		return this;
	}

	@ApiModelProperty(value = "An identifier that is unique for the respective type within a VNF instance, but may not be globally unique. ")
	@JsonProperty("cpInstanceId")
	public String getCpInstanceId() {
		return cpInstanceId;
	}

	public void setCpInstanceId(String cpInstanceId) {
		this.cpInstanceId = cpInstanceId;
	}

	/**
	 * An identifier with the intention of being globally unique.
	 **/
	public VnfInstancesvnfInstanceIdinstantiateCpConfig linkPortId(String linkPortId) {
		this.linkPortId = linkPortId;
		return this;
	}

	@ApiModelProperty(value = "An identifier with the intention of being globally unique. ")
	@JsonProperty("linkPortId")
	public String getLinkPortId() {
		return linkPortId;
	}

	public void setLinkPortId(String linkPortId) {
		this.linkPortId = linkPortId;
	}

	/**
	 * Parameters for configuring the network protocols on the link port that
	 * connects the CP to a VL. The following conditions apply to the attributes
	 * \&quot;linkPortId\&quot; and \&quot;cpProtocolData\&quot;: * The
	 * \&quot;linkPortId\&quot; and \&quot;cpProtocolData\&quot; attributes shall
	 * both be absent for the deletion of an existing external CP instance addressed
	 * by cpInstanceId. * At least one of these attributes shall be present for a
	 * to-be-created external CP instance or an existing external CP instance. * If
	 * the \&quot;linkPortId\&quot; attribute is absent, the VNFM shall create a
	 * link port. * If the \&quot;cpProtocolData\&quot; attribute is absent, the
	 * \&quot;linkPortId\&quot; attribute shall be provided referencing a
	 * pre-created link port, and the VNFM can use means outside the scope of the
	 * present document to obtain the pre-configured address information for the
	 * connection point from the resource representing the link port. * If both
	 * \&quot;cpProtocolData\&quot; and \&quot;linkportId\&quot; are provided, the
	 * API consumer shall ensure that the cpProtocolData can be used with the
	 * pre-created link port referenced by \&quot;linkPortId\&quot;.
	 **/
	public VnfInstancesvnfInstanceIdinstantiateCpConfig cpProtocolData(List<VnfInstancesvnfInstanceIdinstantiateCpProtocolData> cpProtocolData) {
		this.cpProtocolData = cpProtocolData;
		return this;
	}

	@ApiModelProperty(value = "Parameters for configuring the network protocols on the link port that connects the CP to a VL.  The following conditions apply to the attributes \"linkPortId\" and \"cpProtocolData\":  * The \"linkPortId\" and \"cpProtocolData\" attributes shall both be   absent for the deletion of an existing external CP instance   addressed by cpInstanceId.  * At least one of these attributes shall be present for a   to-be-created external CP instance or an existing external   CP instance. * If the \"linkPortId\" attribute is absent, the VNFM shall create a   link port. * If the \"cpProtocolData\" attribute is absent, the \"linkPortId\"   attribute shall be provided referencing a pre-created link port,   and the VNFM can use means outside the scope of the present   document to obtain the pre-configured address information for the   connection point from the resource representing the link port. * If both \"cpProtocolData\" and \"linkportId\" are provided, the API   consumer shall ensure that the cpProtocolData can be used with the   pre-created link port referenced by \"linkPortId\". ")
	@JsonProperty("cpProtocolData")
	public List<VnfInstancesvnfInstanceIdinstantiateCpProtocolData> getCpProtocolData() {
		return cpProtocolData;
	}

	public void setCpProtocolData(List<VnfInstancesvnfInstanceIdinstantiateCpProtocolData> cpProtocolData) {
		this.cpProtocolData = cpProtocolData;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final VnfInstancesvnfInstanceIdinstantiateCpConfig vnfInstancesvnfInstanceIdinstantiateCpConfig = (VnfInstancesvnfInstanceIdinstantiateCpConfig) o;
		return Objects.equals(cpInstanceId, vnfInstancesvnfInstanceIdinstantiateCpConfig.cpInstanceId) &&
				Objects.equals(linkPortId, vnfInstancesvnfInstanceIdinstantiateCpConfig.linkPortId) &&
				Objects.equals(cpProtocolData, vnfInstancesvnfInstanceIdinstantiateCpConfig.cpProtocolData);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpInstanceId, linkPortId, cpProtocolData);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfInstancesvnfInstanceIdinstantiateCpConfig {\n");

		sb.append("    cpInstanceId: ").append(toIndentedString(cpInstanceId)).append("\n");
		sb.append("    linkPortId: ").append(toIndentedString(linkPortId)).append("\n");
		sb.append("    cpProtocolData: ").append(toIndentedString(cpProtocolData)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
