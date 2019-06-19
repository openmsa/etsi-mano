package io.swagger.model;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This type represents parameters to connect to a VIM for managing the resources of a VNF instance. This structure is used to convey VIM-related parameters over the Or-Vnfm interface. Additional parameters for a VIM may be configured into the VNFM by means outside the scope of the present document, and bound to the identifier of that VIM.
 **/
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "This type represents parameters to connect to a VIM for managing the resources of a VNF instance. This structure is used to convey VIM-related parameters over the Or-Vnfm interface. Additional parameters for a VIM may be configured into the VNFM by means outside the scope of the present document, and bound to the identifier of that VIM. ")

public class VnfInstancesvnfInstanceIdVimConnectionInfo {

	private @Valid String id = null;
	private @Valid String vimId = null;
	private @Valid String vimType = null;
	private @Valid Object interfaceInfo = null;
	private @Valid Object accessInfo = null;
	private @Valid Object extra = null;

	/**
	 * An identifier with the intention of being globally unique.
	 **/
	public VnfInstancesvnfInstanceIdVimConnectionInfo id(String id) {
		this.id = id;
		return this;
	}

	@ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
	@JsonProperty("id")
	@NotNull
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * An identifier with the intention of being globally unique.
	 **/
	public VnfInstancesvnfInstanceIdVimConnectionInfo vimId(String vimId) {
		this.vimId = vimId;
		return this;
	}

	@ApiModelProperty(value = "An identifier with the intention of being globally unique. ")
	@JsonProperty("vimId")
	public String getVimId() {
		return vimId;
	}

	public void setVimId(String vimId) {
		this.vimId = vimId;
	}

	/**
	 * Discriminator for the different types of the VIM information. The value of
	 * this attribute determines the structure of the \&quot;interfaceInfo\&quot;
	 * and \&quot;accessInfo\&quot; attributes, based on the type of the VIM. The
	 * set of permitted values is expected to change over time as new types or
	 * versions of VIMs become available. The ETSI NFV registry of VIM-related
	 * information provides access to information about VimConnectionInfo
	 * definitions for various VIM types. The structure of the registry is defined
	 * in Annex C of SOL003.
	 **/
	public VnfInstancesvnfInstanceIdVimConnectionInfo vimType(String vimType) {
		this.vimType = vimType;
		return this;
	}

	@ApiModelProperty(required = true, value = "Discriminator for the different types of the VIM information. The value of this attribute determines the structure of the \"interfaceInfo\" and \"accessInfo\" attributes, based on the type of the VIM. The set of permitted values is expected to change over time as new types or versions of VIMs become available. The ETSI NFV registry of VIM-related information provides access to information about VimConnectionInfo definitions for various VIM types. The structure of the registry is defined in Annex C of SOL003. ")
	@JsonProperty("vimType")
	@NotNull
	public String getVimType() {
		return vimType;
	}

	public void setVimType(String vimType) {
		this.vimType = vimType;
	}

	/**
	 * This type represents a list of key-value pairs. The order of the pairs in the
	 * list is not significant. In JSON, a set of key- value pairs is represented as
	 * an object. It shall comply with the provisions defined in clause 4 of IETF
	 * RFC 7159.
	 **/
	public VnfInstancesvnfInstanceIdVimConnectionInfo interfaceInfo(Object interfaceInfo) {
		this.interfaceInfo = interfaceInfo;
		return this;
	}

	@ApiModelProperty(value = "This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  ")
	@JsonProperty("interfaceInfo")
	public Object getInterfaceInfo() {
		return interfaceInfo;
	}

	public void setInterfaceInfo(Object interfaceInfo) {
		this.interfaceInfo = interfaceInfo;
	}

	/**
	 * This type represents a list of key-value pairs. The order of the pairs in the
	 * list is not significant. In JSON, a set of key- value pairs is represented as
	 * an object. It shall comply with the provisions defined in clause 4 of IETF
	 * RFC 7159.
	 **/
	public VnfInstancesvnfInstanceIdVimConnectionInfo accessInfo(Object accessInfo) {
		this.accessInfo = accessInfo;
		return this;
	}

	@ApiModelProperty(value = "This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  ")
	@JsonProperty("accessInfo")
	public Object getAccessInfo() {
		return accessInfo;
	}

	public void setAccessInfo(Object accessInfo) {
		this.accessInfo = accessInfo;
	}

	/**
	 * This type represents a list of key-value pairs. The order of the pairs in the
	 * list is not significant. In JSON, a set of key- value pairs is represented as
	 * an object. It shall comply with the provisions defined in clause 4 of IETF
	 * RFC 7159.
	 **/
	public VnfInstancesvnfInstanceIdVimConnectionInfo extra(Object extra) {
		this.extra = extra;
		return this;
	}

	@ApiModelProperty(value = "This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  ")
	@JsonProperty("extra")
	public Object getExtra() {
		return extra;
	}

	public void setExtra(Object extra) {
		this.extra = extra;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final VnfInstancesvnfInstanceIdVimConnectionInfo vnfInstancesvnfInstanceIdVimConnectionInfo = (VnfInstancesvnfInstanceIdVimConnectionInfo) o;
		return Objects.equals(id, vnfInstancesvnfInstanceIdVimConnectionInfo.id) &&
				Objects.equals(vimId, vnfInstancesvnfInstanceIdVimConnectionInfo.vimId) &&
				Objects.equals(vimType, vnfInstancesvnfInstanceIdVimConnectionInfo.vimType) &&
				Objects.equals(interfaceInfo, vnfInstancesvnfInstanceIdVimConnectionInfo.interfaceInfo) &&
				Objects.equals(accessInfo, vnfInstancesvnfInstanceIdVimConnectionInfo.accessInfo) &&
				Objects.equals(extra, vnfInstancesvnfInstanceIdVimConnectionInfo.extra);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, vimId, vimType, interfaceInfo, accessInfo, extra);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfInstancesvnfInstanceIdVimConnectionInfo {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    vimId: ").append(toIndentedString(vimId)).append("\n");
		sb.append("    vimType: ").append(toIndentedString(vimType)).append("\n");
		sb.append("    interfaceInfo: ").append(toIndentedString(interfaceInfo)).append("\n");
		sb.append("    accessInfo: ").append(toIndentedString(accessInfo)).append("\n");
		sb.append("    extra: ").append(toIndentedString(extra)).append("\n");
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
