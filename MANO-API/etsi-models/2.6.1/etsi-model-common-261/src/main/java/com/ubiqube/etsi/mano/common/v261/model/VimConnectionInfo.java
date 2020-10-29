/**
 * This copy of Woodstox XML processor is licensed under the
 * Apache (Software) License, version 2.0 ("the License").
 * See the License for details about distribution rights, and the
 * specific rights regarding derivate works.
 *
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/
 *
 * A copy is also included in the downloadable source code package
 * containing Woodstox, in file "ASL2.0", under the same directory
 * as this file.
 */
package com.ubiqube.etsi.mano.common.v261.model;

import java.util.Map;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents parameters to connect to a VIM for managing the
 * resources of a VNF instance. This structure is used to convey VIM-related
 * parameters over the Or-Vnfm interface. Additional parameters for a VIM may be
 * configured into the VNFM by means outside the scope of the present document,
 * and bound to the identifier of that VIM.
 */
@ApiModel(description = "This type represents parameters to connect to a VIM for managing the resources of a VNF instance. This structure is used to convey VIM-related parameters over the Or-Vnfm interface. Additional parameters for a VIM may be configured into the VNFM by means outside the scope of the present document, and bound to the identifier of that VIM. ")
@Validated
public class VimConnectionInfo {
	@JsonProperty("id")
	private String id = null;

	@JsonProperty("vimId")
	private String vimId = null;

	@JsonProperty("vimType")
	private String vimType = null;

	@JsonProperty("interfaceInfo")
	private Map<String, String> interfaceInfo = null;

	@JsonProperty("accessInfo")
	private Map<String, String> accessInfo = null;

	@JsonProperty("extra")
	private Map<String, String> extra = null;

	public VimConnectionInfo id(final String _id) {
		this.id = _id;
		return this;
	}

	/**
	 * The identifier of the VIM Connection. This identifier is managed by the NFVO.
	 *
	 * @return id
	 **/
	@ApiModelProperty(required = true, value = "The identifier of the VIM Connection. This identifier is managed by the NFVO. ")
	@NotNull

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public VimConnectionInfo vimId(final String _vimId) {
		this.vimId = _vimId;
		return this;
	}

	/**
	 * The identifier of the VIM instance. This identifier is managed by the NFVO.
	 * Shall be present to address additional information about the VIM if such
	 * information has been configured into the VNFM by means outside the scope of
	 * the present document, and should be absent otherwise.
	 *
	 * @return vimId
	 **/
	@ApiModelProperty(value = "The identifier of the VIM instance. This identifier is managed by the NFVO. Shall be present to address additional information about the VIM if such information has been configured into the VNFM by means outside the scope of the present document, and should be absent otherwise. ")

	public String getVimId() {
		return vimId;
	}

	public void setVimId(final String vimId) {
		this.vimId = vimId;
	}

	public VimConnectionInfo vimType(final String _vimType) {
		this.vimType = _vimType;
		return this;
	}

	/**
	 * Discriminator for the different types of the VIM information. The value of
	 * this attribute determines the structure of the \"interfaceInfo\" and
	 * \"accessInfo\" attributes, based on the type of the VIM. The set of permitted
	 * values is expected to change over time as new types or versions of VIMs
	 * become available. The ETSI NFV registry of VIM-related information provides
	 * access to information about VimConnectionInfo definitions for various VIM
	 * types. The structure of the registry is defined in Annex C of SOL003.
	 *
	 * @return vimType
	 **/
	@ApiModelProperty(required = true, value = "Discriminator for the different types of the VIM information. The value of this attribute determines the structure of the \"interfaceInfo\" and \"accessInfo\" attributes, based on the type of the VIM. The set of permitted values is expected to change over time as new types or versions of VIMs become available. The ETSI NFV registry of VIM-related information provides access to information about VimConnectionInfo definitions for various VIM types. The structure of the registry is defined in Annex C of SOL003. ")
	@NotNull

	public String getVimType() {
		return vimType;
	}

	public void setVimType(final String vimType) {
		this.vimType = vimType;
	}

	public VimConnectionInfo interfaceInfo(final Map<String, String> _interfaceInfo) {
		this.interfaceInfo = _interfaceInfo;
		return this;
	}

	/**
	 * Information about the interface or interfaces to the VIM, if applicable, such
	 * as the URI of an interface endpoint to communicate with the VIM. The
	 * applicable keys are dependent on the content of vimType. Alternatively, such
	 * information may have been configured into the VNFM and bound to the vimId.
	 *
	 * @return interfaceInfo
	 **/
	@ApiModelProperty(value = "Information about the interface or interfaces to the VIM, if applicable, such as the URI of an interface endpoint to communicate with the VIM. The applicable keys are dependent on the content of vimType. Alternatively, such information may have been configured into the VNFM and bound to the vimId. ")

	@Valid

	public Map<String, String> getInterfaceInfo() {
		return interfaceInfo;
	}

	public void setInterfaceInfo(final Map<String, String> interfaceInfo) {
		this.interfaceInfo = interfaceInfo;
	}

	public VimConnectionInfo accessInfo(final Map<String, String> _accessInfo) {
		this.accessInfo = _accessInfo;
		return this;
	}

	/**
	 * Authentication credentials for accessing the VIM, and other access-related
	 * information such as tenants or infrastructure resource groups (see note). The
	 * applicable keys are dependent on the content of vimType. If the
	 * VimConnectionInfo structure is part of an HTTP response payload body,
	 * sensitive attributes that are children of this attributes (such as passwords)
	 * shall not be included. If the VimConnectionInfo structure is part of an HTTP
	 * request payload body, sensitive attributes that are children of this
	 * attribute (such as passwords) shall be present if they have not been
	 * provisioned out of band.
	 *
	 * @return accessInfo
	 **/
	@ApiModelProperty(value = "Authentication credentials for accessing the VIM, and other access-related information such as tenants or infrastructure resource groups (see note). The applicable keys are dependent on the content of vimType. If the VimConnectionInfo structure is part of an HTTP response payload body, sensitive attributes that are children of this attributes (such as passwords) shall not be included. If the VimConnectionInfo structure is part of an HTTP request payload body, sensitive attributes that are children of this attribute (such as passwords) shall be present if they have not been provisioned out of band. ")

	@Valid

	public Map<String, String> getAccessInfo() {
		return accessInfo;
	}

	public void setAccessInfo(final Map<String, String> accessInfo) {
		this.accessInfo = accessInfo;
	}

	public VimConnectionInfo extra(final Map<String, String> _extra) {
		this.extra = _extra;
		return this;
	}

	/**
	 * VIM type specific additional information. The applicable structure, and
	 * whether or not this attribute is available, is dependent on the content of
	 * vimType.
	 *
	 * @return extra
	 **/
	@ApiModelProperty(value = "VIM type specific additional information. The applicable structure, and whether or not this attribute is available, is dependent on the content of vimType. ")

	@Valid

	public Map<String, String> getExtra() {
		return extra;
	}

	public void setExtra(final Map<String, String> extra) {
		this.extra = extra;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final VimConnectionInfo vimConnectionInfo = (VimConnectionInfo) o;
		return Objects.equals(this.id, vimConnectionInfo.id) &&
				Objects.equals(this.vimId, vimConnectionInfo.vimId) &&
				Objects.equals(this.vimType, vimConnectionInfo.vimType) &&
				Objects.equals(this.interfaceInfo, vimConnectionInfo.interfaceInfo) &&
				Objects.equals(this.accessInfo, vimConnectionInfo.accessInfo) &&
				Objects.equals(this.extra, vimConnectionInfo.extra);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, vimId, vimType, interfaceInfo, accessInfo, extra);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VimConnectionInfo {\n");

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
	private static String toIndentedString(final java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
