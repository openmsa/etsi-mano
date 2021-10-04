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
import java.util.Map;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;


/**
 * This type represents the information that allows addressing a virtualised resource that is used by an internal VL instance in a VNF instance.
 */
@Schema(description = "This type represents the information that allows addressing a virtualised resource that is used by an internal VL instance in a VNF instance. ")
@Validated

public class VnfVirtualLinkResourceInfo {
	@JsonProperty("id")
	private String id = null;

	@JsonProperty("vnfVirtualLinkDescId")
	private String vnfVirtualLinkDescId = null;

	@JsonProperty("networkResource")
	private ResourceHandle networkResource = null;

	@JsonProperty("reservationId")
	private String reservationId = null;

	@JsonProperty("vnfLinkPorts")
	@Valid
	private List<VnfLinkPortInfo> vnfLinkPorts = null;

	@JsonProperty("metadata")
	private Map<String, Object> metadata = null;

	public VnfVirtualLinkResourceInfo id(final String id) {
		this.id = id;
		return this;
	}

	/**
	 * Identifier of this VnfVirtualLinkResourceInfo instance.
	 *
	 * @return id
	 **/
	@Schema(required = true, description = "Identifier of this VnfVirtualLinkResourceInfo instance. ")
	@NotNull

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public VnfVirtualLinkResourceInfo vnfVirtualLinkDescId(final String vnfVirtualLinkDescId) {
		this.vnfVirtualLinkDescId = vnfVirtualLinkDescId;
		return this;
	}

	/**
	 * Identifier of the VNF Virtual Link Descriptor (VLD) in the VNFD.
	 *
	 * @return vnfVirtualLinkDescId
	 **/
	@Schema(required = true, description = "Identifier of the VNF Virtual Link Descriptor (VLD) in the VNFD. ")
	@NotNull

	public String getVnfVirtualLinkDescId() {
		return vnfVirtualLinkDescId;
	}

	public void setVnfVirtualLinkDescId(final String vnfVirtualLinkDescId) {
		this.vnfVirtualLinkDescId = vnfVirtualLinkDescId;
	}

	public VnfVirtualLinkResourceInfo networkResource(final ResourceHandle networkResource) {
		this.networkResource = networkResource;
		return this;
	}

	/**
	 * Reference to the VirtualNetwork resource.
	 *
	 * @return networkResource
	 **/
	@Schema(required = true, description = "Reference to the VirtualNetwork resource. ")
	@NotNull

	@Valid

	public ResourceHandle getNetworkResource() {
		return networkResource;
	}

	public void setNetworkResource(final ResourceHandle networkResource) {
		this.networkResource = networkResource;
	}

	public VnfVirtualLinkResourceInfo reservationId(final String reservationId) {
		this.reservationId = reservationId;
		return this;
	}

	/**
	 * The reservation identifier applicable to the resource. It shall be present when an applicable reservation exists.
	 *
	 * @return reservationId
	 **/
	@Schema(description = "The reservation identifier applicable to the resource. It shall be present when an applicable reservation exists. ")

	public String getReservationId() {
		return reservationId;
	}

	public void setReservationId(final String reservationId) {
		this.reservationId = reservationId;
	}

	public VnfVirtualLinkResourceInfo vnfLinkPorts(final List<VnfLinkPortInfo> vnfLinkPorts) {
		this.vnfLinkPorts = vnfLinkPorts;
		return this;
	}

	public VnfVirtualLinkResourceInfo addVnfLinkPortsItem(final VnfLinkPortInfo vnfLinkPortsItem) {
		if (this.vnfLinkPorts == null) {
			this.vnfLinkPorts = new ArrayList<>();
		}
		this.vnfLinkPorts.add(vnfLinkPortsItem);
		return this;
	}

	/**
	 * Links ports of this VL. Shall be present when the linkPort is used for external connectivity by the VNF (refer to VnfLinkPortInfo). May be present otherwise.
	 *
	 * @return vnfLinkPorts
	 **/
	@Schema(description = "Links ports of this VL. Shall be present when the linkPort is used for external connectivity by the VNF (refer to VnfLinkPortInfo). May be present otherwise. ")

	@Valid

	public List<VnfLinkPortInfo> getVnfLinkPorts() {
		return vnfLinkPorts;
	}

	public void setVnfLinkPorts(final List<VnfLinkPortInfo> vnfLinkPorts) {
		this.vnfLinkPorts = vnfLinkPorts;
	}

	public VnfVirtualLinkResourceInfo metadata(final Map<String, Object> metadata) {
		this.metadata = metadata;
		return this;
	}

	/**
	 * Metadata about this resource.
	 *
	 * @return metadata
	 **/
	@Schema(description = "Metadata about this resource. ")

	@Valid

	public Map<String, Object> getMetadata() {
		return metadata;
	}

	public void setMetadata(final Map<String, Object> metadata) {
		this.metadata = metadata;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final VnfVirtualLinkResourceInfo vnfVirtualLinkResourceInfo = (VnfVirtualLinkResourceInfo) o;
		return Objects.equals(this.id, vnfVirtualLinkResourceInfo.id) &&
				Objects.equals(this.vnfVirtualLinkDescId, vnfVirtualLinkResourceInfo.vnfVirtualLinkDescId) &&
				Objects.equals(this.networkResource, vnfVirtualLinkResourceInfo.networkResource) &&
				Objects.equals(this.reservationId, vnfVirtualLinkResourceInfo.reservationId) &&
				Objects.equals(this.vnfLinkPorts, vnfVirtualLinkResourceInfo.vnfLinkPorts) &&
				Objects.equals(this.metadata, vnfVirtualLinkResourceInfo.metadata);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, vnfVirtualLinkDescId, networkResource, reservationId, vnfLinkPorts, metadata);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfVirtualLinkResourceInfo {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    vnfVirtualLinkDescId: ").append(toIndentedString(vnfVirtualLinkDescId)).append("\n");
		sb.append("    networkResource: ").append(toIndentedString(networkResource)).append("\n");
		sb.append("    reservationId: ").append(toIndentedString(reservationId)).append("\n");
		sb.append("    vnfLinkPorts: ").append(toIndentedString(vnfLinkPorts)).append("\n");
		sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
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
