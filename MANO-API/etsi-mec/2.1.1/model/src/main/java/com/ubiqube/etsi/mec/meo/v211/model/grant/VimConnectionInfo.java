/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.etsi.mec.meo.v211.model.grant;

import java.util.Map;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * VimConnectionInfo
 */
@Validated
public class VimConnectionInfo {
	@JsonProperty("accessInfo")
	private Map<String, String> accessInfo = null;

	@JsonProperty("extra")
	private Map<String, String> extra = null;

	@JsonProperty("id")
	private String id = null;

	@JsonProperty("interfaceInfo")
	private Map<String, String> interfaceInfo = null;

	@JsonProperty("vimId")
	private String vimId = null;

	@JsonProperty("vimType")
	private String vimType = null;

	public VimConnectionInfo accessInfo(final Map<String, String> accessInfo) {
		this.accessInfo = accessInfo;
		return this;
	}

	/**
	 * Get accessInfo
	 *
	 * @return accessInfo
	 **/
	@ApiModelProperty(value = "")

	@Valid
	public Map<String, String> getAccessInfo() {
		return accessInfo;
	}

	public void setAccessInfo(final Map<String, String> accessInfo) {
		this.accessInfo = accessInfo;
	}

	public VimConnectionInfo extra(final Map<String, String> extra) {
		this.extra = extra;
		return this;
	}

	/**
	 * Get extra
	 *
	 * @return extra
	 **/
	@ApiModelProperty(value = "")

	@Valid
	public Map<String, String> getExtra() {
		return extra;
	}

	public void setExtra(final Map<String, String> extra) {
		this.extra = extra;
	}

	public VimConnectionInfo id(final String id) {
		this.id = id;
		return this;
	}

	/**
	 * The identifier of the VIM Connection. This identifier is managed by the MEO.
	 *
	 * @return id
	 **/
	@ApiModelProperty(required = true, value = "The identifier of the VIM Connection. This identifier is managed by the MEO.")
	@NotNull

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public VimConnectionInfo interfaceInfo(final Map<String, String> interfaceInfo) {
		this.interfaceInfo = interfaceInfo;
		return this;
	}

	/**
	 * Get interfaceInfo
	 *
	 * @return interfaceInfo
	 **/
	@ApiModelProperty(value = "")

	@Valid
	public Map<String, String> getInterfaceInfo() {
		return interfaceInfo;
	}

	public void setInterfaceInfo(final Map<String, String> interfaceInfo) {
		this.interfaceInfo = interfaceInfo;
	}

	public VimConnectionInfo vimId(final String vimId) {
		this.vimId = vimId;
		return this;
	}

	/**
	 * The identifier of the VIM instance. This identifier is managed by the MEO.Shall be present to address additional information about the VIM if such information has been configured into the MEPM by means outside the scope of the present document, and should be absent otherwise.
	 *
	 * @return vimId
	 **/
	@ApiModelProperty(value = "The identifier of the VIM instance. This identifier is managed by the MEO.Shall be present to address additional information about the VIM if such information has been configured into the MEPM by means outside the scope of the present document, and should be absent otherwise.")

	public String getVimId() {
		return vimId;
	}

	public void setVimId(final String vimId) {
		this.vimId = vimId;
	}

	public VimConnectionInfo vimType(final String vimType) {
		this.vimType = vimType;
		return this;
	}

	/**
	 * Discriminator for the different types of the VIM information. The value of this attribute determines the structure of the \"interfaceInfo\" and \"accessInfo\" attributes, based on the type of the VIM.The set of permitted values is expected to change over time as new types or versions of VIMs become available.
	 *
	 * @return vimType
	 **/
	@ApiModelProperty(required = true, value = "Discriminator for the different types of the VIM information. The value of this attribute determines the structure of the \"interfaceInfo\" and \"accessInfo\" attributes, based on the type of the VIM.The set of permitted values is expected to change over time as new types or versions of VIMs become available. ")
	@NotNull

	public String getVimType() {
		return vimType;
	}

	public void setVimType(final String vimType) {
		this.vimType = vimType;
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
		return Objects.equals(this.accessInfo, vimConnectionInfo.accessInfo) &&
				Objects.equals(this.extra, vimConnectionInfo.extra) &&
				Objects.equals(this.id, vimConnectionInfo.id) &&
				Objects.equals(this.interfaceInfo, vimConnectionInfo.interfaceInfo) &&
				Objects.equals(this.vimId, vimConnectionInfo.vimId) &&
				Objects.equals(this.vimType, vimConnectionInfo.vimType);
	}

	@Override
	public int hashCode() {
		return Objects.hash(accessInfo, extra, id, interfaceInfo, vimId, vimType);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VimConnectionInfo {\n");

		sb.append("    accessInfo: ").append(toIndentedString(accessInfo)).append("\n");
		sb.append("    extra: ").append(toIndentedString(extra)).append("\n");
		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    interfaceInfo: ").append(toIndentedString(interfaceInfo)).append("\n");
		sb.append("    vimId: ").append(toIndentedString(vimId)).append("\n");
		sb.append("    vimType: ").append(toIndentedString(vimType)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces (except the first line).
	 */
	private String toIndentedString(final java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
