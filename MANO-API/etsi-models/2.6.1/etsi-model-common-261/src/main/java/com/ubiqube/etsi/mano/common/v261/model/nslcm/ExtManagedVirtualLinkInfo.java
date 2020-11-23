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

package com.ubiqube.etsi.mano.common.v261.model.nslcm;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.common.v261.model.ResourceHandle;

import io.swagger.annotations.ApiModelProperty;

/**
 * ExtManagedVirtualLinkInfo
 */
public class ExtManagedVirtualLinkInfo {
	@JsonProperty("id")
	private String id = null;

	@JsonProperty("vnfVirtualLinkDescId")
	private String vnfVirtualLinkDescId = null;

	@JsonProperty("networkResource")
	private ResourceHandle networkResource = null;

	@JsonProperty("vnfLinkPorts")
	private List<VnfLinkPortInfo> vnfLinkPorts = null;

	public ExtManagedVirtualLinkInfo id(final String id) {
		this.id = id;
		return this;
	}

	/**
	 * Identifier of the externally-managed internal VL and the related
	 * externally-managed VL information instance. The identifier is assigned by the
	 * NFV-MANO entity that manages this VL instance.
	 *
	 * @return id
	 **/
	@JsonProperty("id")
	@ApiModelProperty(required = true, value = "Identifier of the externally-managed internal VL and the related externally-managed VL information instance. The identifier is assigned by the NFV-MANO entity that manages this VL instance. ")
	@NotNull
	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public ExtManagedVirtualLinkInfo vnfVirtualLinkDescId(final String vnfVirtualLinkDescId) {
		this.vnfVirtualLinkDescId = vnfVirtualLinkDescId;
		return this;
	}

	/**
	 * Identifier of the VNF Virtual Link Descriptor (VLD) in the VNFD.
	 *
	 * @return vnfVirtualLinkDescId
	 **/
	@JsonProperty("vnfVirtualLinkDescId")
	@ApiModelProperty(required = true, value = "Identifier of the VNF Virtual Link Descriptor (VLD) in the VNFD. ")
	@NotNull
	public String getVnfVirtualLinkDescId() {
		return vnfVirtualLinkDescId;
	}

	public void setVnfVirtualLinkDescId(final String vnfVirtualLinkDescId) {
		this.vnfVirtualLinkDescId = vnfVirtualLinkDescId;
	}

	public ExtManagedVirtualLinkInfo networkResource(final ResourceHandle networkResource) {
		this.networkResource = networkResource;
		return this;
	}

	/**
	 * Reference to the VirtualNetwork resource.
	 *
	 * @return networkResource
	 **/
	@JsonProperty("networkResource")
	@ApiModelProperty(value = "Reference to the VirtualNetwork resource. ")
	public ResourceHandle getNetworkResource() {
		return networkResource;
	}

	public void setNetworkResource(final ResourceHandle networkResource) {
		this.networkResource = networkResource;
	}

	public ExtManagedVirtualLinkInfo vnfLinkPorts(final List<VnfLinkPortInfo> vnfLinkPorts) {
		this.vnfLinkPorts = vnfLinkPorts;
		return this;
	}

	public ExtManagedVirtualLinkInfo addVnfLinkPortsItem(final VnfLinkPortInfo vnfLinkPortsItem) {
		if (this.vnfLinkPorts == null) {
			this.vnfLinkPorts = new ArrayList<>();
		}
		this.vnfLinkPorts.add(vnfLinkPortsItem);
		return this;
	}

	/**
	 * Link ports of this VL.
	 *
	 * @return vnfLinkPorts
	 **/
	@JsonProperty("vnfLinkPorts")
	@ApiModelProperty(value = "Link ports of this VL. ")
	public List<VnfLinkPortInfo> getVnfLinkPorts() {
		return vnfLinkPorts;
	}

	public void setVnfLinkPorts(final List<VnfLinkPortInfo> vnfLinkPorts) {
		this.vnfLinkPorts = vnfLinkPorts;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class ExtManagedVirtualLinkInfo {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    vnfVirtualLinkDescId: ").append(toIndentedString(vnfVirtualLinkDescId)).append("\n");
		sb.append("    networkResource: ").append(toIndentedString(networkResource)).append("\n");
		sb.append("    vnfLinkPorts: ").append(toIndentedString(vnfLinkPorts)).append("\n");
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
