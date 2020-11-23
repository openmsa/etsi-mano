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

package com.ubiqube.etsi.mano.nfvo.v261.model.nslcm;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Information on the VNFFG(s) of the NS instance.
 */
@ApiModel(description = "Information on the VNFFG(s) of the NS instance. ")
@Validated


public class VnffgInfo {
	@JsonProperty("id")
	private String id = null;

	@JsonProperty("vnffgdId")
	private String vnffgdId = null;

	@JsonProperty("vnfInstanceId")
	@Valid
	private List<String> vnfInstanceId = new ArrayList<>();

	@JsonProperty("pnfdInfoId")
	@Valid
	private List<String> pnfdInfoId = null;

	@JsonProperty("nsVirtualLinkInfoId")
	@Valid
	private List<String> nsVirtualLinkInfoId = null;

	@JsonProperty("nsCpHandle")
	@Valid
	private List<NsCpHandle> nsCpHandle = null;

	public VnffgInfo id(final String id) {
		this.id = id;
		return this;
	}

	/**
	 * Identifier of this VNFFG instance.
	 * 
	 * @return id
	 **/
	@ApiModelProperty(required = true, value = "Identifier of this VNFFG instance. ")
	@NotNull

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public VnffgInfo vnffgdId(final String vnffgdId) {
		this.vnffgdId = vnffgdId;
		return this;
	}

	/**
	 * Identifier of the VNFFGD in the NSD.
	 * 
	 * @return vnffgdId
	 **/
	@ApiModelProperty(required = true, value = "Identifier of the VNFFGD in the NSD. ")
	@NotNull

	public String getVnffgdId() {
		return vnffgdId;
	}

	public void setVnffgdId(final String vnffgdId) {
		this.vnffgdId = vnffgdId;
	}

	public VnffgInfo vnfInstanceId(final List<String> vnfInstanceId) {
		this.vnfInstanceId = vnfInstanceId;
		return this;
	}

	public VnffgInfo addVnfInstanceIdItem(final String vnfInstanceIdItem) {
		this.vnfInstanceId.add(vnfInstanceIdItem);
		return this;
	}

	/**
	 * Identifier(s) of the constituent VNF instance(s) of this VNFFG instance.
	 * 
	 * @return vnfInstanceId
	 **/
	@ApiModelProperty(required = true, value = "Identifier(s) of the constituent VNF instance(s) of this VNFFG instance. ")
	@NotNull

	public List<String> getVnfInstanceId() {
		return vnfInstanceId;
	}

	public void setVnfInstanceId(final List<String> vnfInstanceId) {
		this.vnfInstanceId = vnfInstanceId;
	}

	public VnffgInfo pnfdInfoId(final List<String> pnfdInfoId) {
		this.pnfdInfoId = pnfdInfoId;
		return this;
	}

	public VnffgInfo addPnfdInfoIdItem(final String pnfdInfoIdItem) {
		if (this.pnfdInfoId == null) {
			this.pnfdInfoId = new ArrayList<>();
		}
		this.pnfdInfoId.add(pnfdInfoIdItem);
		return this;
	}

	/**
	 * Identifier(s) of the constituent PNF instance(s) of this VNFFG instance.
	 * 
	 * @return pnfdInfoId
	 **/
	@ApiModelProperty(value = "Identifier(s) of the constituent PNF instance(s) of this VNFFG instance. ")

	public List<String> getPnfdInfoId() {
		return pnfdInfoId;
	}

	public void setPnfdInfoId(final List<String> pnfdInfoId) {
		this.pnfdInfoId = pnfdInfoId;
	}

	public VnffgInfo nsVirtualLinkInfoId(final List<String> nsVirtualLinkInfoId) {
		this.nsVirtualLinkInfoId = nsVirtualLinkInfoId;
		return this;
	}

	public VnffgInfo addNsVirtualLinkInfoIdItem(final String nsVirtualLinkInfoIdItem) {
		if (this.nsVirtualLinkInfoId == null) {
			this.nsVirtualLinkInfoId = new ArrayList<>();
		}
		this.nsVirtualLinkInfoId.add(nsVirtualLinkInfoIdItem);
		return this;
	}

	/**
	 * Identifier(s) of the constituent VL instance(s) of this VNFFG instance.
	 * 
	 * @return nsVirtualLinkInfoId
	 **/
	@ApiModelProperty(value = "Identifier(s) of the constituent VL instance(s) of this VNFFG instance. ")

	public List<String> getNsVirtualLinkInfoId() {
		return nsVirtualLinkInfoId;
	}

	public void setNsVirtualLinkInfoId(final List<String> nsVirtualLinkInfoId) {
		this.nsVirtualLinkInfoId = nsVirtualLinkInfoId;
	}

	public VnffgInfo nsCpHandle(final List<NsCpHandle> nsCpHandle) {
		this.nsCpHandle = nsCpHandle;
		return this;
	}

	public VnffgInfo addNsCpHandleItem(final NsCpHandle nsCpHandleItem) {
		if (this.nsCpHandle == null) {
			this.nsCpHandle = new ArrayList<>();
		}
		this.nsCpHandle.add(nsCpHandleItem);
		return this;
	}

	/**
	 * Identifiers of the CP instances attached to the constituent VNFs and PNFs or
	 * the SAP instances of the VNFFG. See note.
	 * 
	 * @return nsCpHandle
	 **/
	@ApiModelProperty(value = "Identifiers of the CP instances attached to the constituent VNFs and PNFs or the SAP instances of the VNFFG. See note. ")

	@Valid

	public List<NsCpHandle> getNsCpHandle() {
		return nsCpHandle;
	}

	public void setNsCpHandle(final List<NsCpHandle> nsCpHandle) {
		this.nsCpHandle = nsCpHandle;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final VnffgInfo vnffgInfo = (VnffgInfo) o;
		return Objects.equals(this.id, vnffgInfo.id) &&
				Objects.equals(this.vnffgdId, vnffgInfo.vnffgdId) &&
				Objects.equals(this.vnfInstanceId, vnffgInfo.vnfInstanceId) &&
				Objects.equals(this.pnfdInfoId, vnffgInfo.pnfdInfoId) &&
				Objects.equals(this.nsVirtualLinkInfoId, vnffgInfo.nsVirtualLinkInfoId) &&
				Objects.equals(this.nsCpHandle, vnffgInfo.nsCpHandle);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, vnffgdId, vnfInstanceId, pnfdInfoId, nsVirtualLinkInfoId, nsCpHandle);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnffgInfo {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    vnffgdId: ").append(toIndentedString(vnffgdId)).append("\n");
		sb.append("    vnfInstanceId: ").append(toIndentedString(vnfInstanceId)).append("\n");
		sb.append("    pnfdInfoId: ").append(toIndentedString(pnfdInfoId)).append("\n");
		sb.append("    nsVirtualLinkInfoId: ").append(toIndentedString(nsVirtualLinkInfoId)).append("\n");
		sb.append("    nsCpHandle: ").append(toIndentedString(nsCpHandle)).append("\n");
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
