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
package com.ubiqube.etsi.mano.em.v261.model.vnflcm;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;


/**
 * This type represents an external VL.
 */
@Schema(description = "This type represents an external VL. ")
@Validated

public class ExtVirtualLinkData {
	@JsonProperty("id")
	private String id = null;

	@JsonProperty("vimConnectionId")
	private String vimConnectionId = null;

	@JsonProperty("resourceProviderId")
	private String resourceProviderId = null;

	@JsonProperty("resourceId")
	private String resourceId = null;

	@JsonProperty("extCps")
	@Valid
	private List<VnfExtCpData> extCps = new ArrayList<>();

	@JsonProperty("extLinkPorts")
	@Valid
	private List<ExtLinkPortData> extLinkPorts = null;

	public ExtVirtualLinkData id(final String id) {
		this.id = id;
		return this;
	}

	/**
	 * The identifier of the external VL instance. The identifier is assigned by the NFV-MANO entity that manages this VL instance.
	 *
	 * @return id
	 **/
	@Schema(required = true, description = "The identifier of the external VL instance. The identifier is assigned by the NFV-MANO entity that manages this VL instance. ")
	@NotNull

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public ExtVirtualLinkData vimConnectionId(final String vimConnectionId) {
		this.vimConnectionId = vimConnectionId;
		return this;
	}

	/**
	 * Identifier of the VIM connection to manage this resource. This attribute shall only be supported and present if VNF-related resource management in direct mode is applicable.
	 *
	 * @return vimConnectionId
	 **/
	@Schema(description = "Identifier of the VIM connection to manage this resource. This attribute shall only be supported and present if VNF-related resource management in direct mode is applicable. ")

	public String getVimConnectionId() {
		return vimConnectionId;
	}

	public void setVimConnectionId(final String vimConnectionId) {
		this.vimConnectionId = vimConnectionId;
	}

	public ExtVirtualLinkData resourceProviderId(final String resourceProviderId) {
		this.resourceProviderId = resourceProviderId;
		return this;
	}

	/**
	 * Identifies the entity responsible for the management of this resource. This attribute shall only be supported and present if VNF-related resource management in indirect mode is applicable. The identification scheme is outside the scope of the present document.
	 *
	 * @return resourceProviderId
	 **/
	@Schema(description = "Identifies the entity responsible for the management of this resource. This attribute shall only be supported and present if VNF-related resource management in indirect mode is applicable. The identification scheme is outside the scope of the present document. ")

	public String getResourceProviderId() {
		return resourceProviderId;
	}

	public void setResourceProviderId(final String resourceProviderId) {
		this.resourceProviderId = resourceProviderId;
	}

	public ExtVirtualLinkData resourceId(final String resourceId) {
		this.resourceId = resourceId;
		return this;
	}

	/**
	 * The identifier of the resource in the scope of the VIM or the resource provider.
	 *
	 * @return resourceId
	 **/
	@Schema(required = true, description = "The identifier of the resource in the scope of the VIM or the resource provider. ")
	@NotNull

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(final String resourceId) {
		this.resourceId = resourceId;
	}

	public ExtVirtualLinkData extCps(final List<VnfExtCpData> extCps) {
		this.extCps = extCps;
		return this;
	}

	public ExtVirtualLinkData addExtCpsItem(final VnfExtCpData extCpsItem) {
		this.extCps.add(extCpsItem);
		return this;
	}

	/**
	 * External CPs of the VNF to be connected to this external VL.
	 *
	 * @return extCps
	 **/
	@Schema(required = true, description = "External CPs of the VNF to be connected to this external VL. ")
	@NotNull

	@Valid

	public List<VnfExtCpData> getExtCps() {
		return extCps;
	}

	public void setExtCps(final List<VnfExtCpData> extCps) {
		this.extCps = extCps;
	}

	public ExtVirtualLinkData extLinkPorts(final List<ExtLinkPortData> extLinkPorts) {
		this.extLinkPorts = extLinkPorts;
		return this;
	}

	public ExtVirtualLinkData addExtLinkPortsItem(final ExtLinkPortData extLinkPortsItem) {
		if (this.extLinkPorts == null) {
			this.extLinkPorts = new ArrayList<>();
		}
		this.extLinkPorts.add(extLinkPortsItem);
		return this;
	}

	/**
	 * Externally provided link ports to be used to connect external connection points to this external VL. If this attribute is not present, the VNFM shall create the link ports on the external VL.
	 *
	 * @return extLinkPorts
	 **/
	@Schema(description = "Externally provided link ports to be used to connect external connection points to this external VL. If this attribute is not present, the VNFM shall create the link ports on the external VL. ")

	@Valid

	public List<ExtLinkPortData> getExtLinkPorts() {
		return extLinkPorts;
	}

	public void setExtLinkPorts(final List<ExtLinkPortData> extLinkPorts) {
		this.extLinkPorts = extLinkPorts;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final ExtVirtualLinkData extVirtualLinkData = (ExtVirtualLinkData) o;
		return Objects.equals(this.id, extVirtualLinkData.id) &&
				Objects.equals(this.vimConnectionId, extVirtualLinkData.vimConnectionId) &&
				Objects.equals(this.resourceProviderId, extVirtualLinkData.resourceProviderId) &&
				Objects.equals(this.resourceId, extVirtualLinkData.resourceId) &&
				Objects.equals(this.extCps, extVirtualLinkData.extCps) &&
				Objects.equals(this.extLinkPorts, extVirtualLinkData.extLinkPorts);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, vimConnectionId, resourceProviderId, resourceId, extCps, extLinkPorts);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class ExtVirtualLinkData {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    vimConnectionId: ").append(toIndentedString(vimConnectionId)).append("\n");
		sb.append("    resourceProviderId: ").append(toIndentedString(resourceProviderId)).append("\n");
		sb.append("    resourceId: ").append(toIndentedString(resourceId)).append("\n");
		sb.append("    extCps: ").append(toIndentedString(extCps)).append("\n");
		sb.append("    extLinkPorts: ").append(toIndentedString(extLinkPorts)).append("\n");
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
