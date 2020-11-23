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

package com.ubiqube.etsi.mano.common.v261.model.lcmgrant;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * If the VIM requires the use of virtual compute resource flavours during
 * compute resource instantiation, it is assumed that such flavours are selected
 * or created by the NFVO based on the information in the virtual compute
 * descriptor defined in the VNFD. This type defines the mapping between a
 * virtual compute descriptor in the VNFD and the corresponding compute resource
 * flavour managed by the NFVO in the VIM.
 */
@ApiModel(description = "If the VIM requires the use of virtual compute resource flavours during compute resource instantiation, it is assumed that such flavours are selected or created by the NFVO based on the information in the virtual compute descriptor defined in the VNFD. This type defines the mapping between a virtual compute descriptor in the VNFD and the corresponding compute resource flavour managed by the NFVO in the VIM. ")
@Validated
public class VimComputeResourceFlavour {
	@JsonProperty("vimConnectionId")
	private String vimConnectionId = null;

	@JsonProperty("resourceProviderId")
	private String resourceProviderId = null;

	@JsonProperty("vnfdVirtualComputeDescId")
	private String vnfdVirtualComputeDescId = null;

	@JsonProperty("vimFlavourId")
	private String vimFlavourId = null;

	public VimComputeResourceFlavour vimConnectionId(final String vimConnectionId) {
		this.vimConnectionId = vimConnectionId;
		return this;
	}

	/**
	 * Identifier of the VIM connection to access the flavour referenced in this
	 * structure. The applicable \"VimConnectionInfo\" structure, which is
	 * referenced by vimConnectionId, can be obtained from the \"vimConnectionInfo\"
	 * attribute of the \"VnfInstance\" structure. This attribute shall only be
	 * supported and present if VNF-related resource management in direct mode is
	 * applicable.
	 *
	 * @return vimConnectionId
	 **/
	@ApiModelProperty(value = "Identifier of the VIM connection to access the flavour referenced in this structure. The applicable \"VimConnectionInfo\" structure, which is referenced by vimConnectionId, can be obtained from the \"vimConnectionInfo\" attribute of the \"VnfInstance\" structure. This attribute shall only be supported and present if VNF-related resource management in direct mode is applicable. ")

	public String getVimConnectionId() {
		return vimConnectionId;
	}

	public void setVimConnectionId(final String vimConnectionId) {
		this.vimConnectionId = vimConnectionId;
	}

	public VimComputeResourceFlavour resourceProviderId(final String resourceProviderId) {
		this.resourceProviderId = resourceProviderId;
		return this;
	}

	/**
	 * Identifies the entity responsible for the management of the virtualised
	 * resource. This attribute shall only be supported and present if VNF-related
	 * resource management in indirect mode is applicable. The identification scheme
	 * is outside the scope of the present document.
	 *
	 * @return resourceProviderId
	 **/
	@ApiModelProperty(value = "Identifies the entity responsible for the management of the virtualised resource. This attribute shall only be supported and present if VNF-related resource management in indirect mode is applicable. The identification scheme is outside the scope of the present document. ")

	public String getResourceProviderId() {
		return resourceProviderId;
	}

	public void setResourceProviderId(final String resourceProviderId) {
		this.resourceProviderId = resourceProviderId;
	}

	public VimComputeResourceFlavour vnfdVirtualComputeDescId(final String vnfdVirtualComputeDescId) {
		this.vnfdVirtualComputeDescId = vnfdVirtualComputeDescId;
		return this;
	}

	/**
	 * Identifier which references the virtual compute descriptor in the VNFD that
	 * maps to this flavour.
	 *
	 * @return vnfdVirtualComputeDescId
	 **/
	@ApiModelProperty(required = true, value = "Identifier which references the virtual compute descriptor in the VNFD that maps to this flavour. ")
	@NotNull

	public String getVnfdVirtualComputeDescId() {
		return vnfdVirtualComputeDescId;
	}

	public void setVnfdVirtualComputeDescId(final String vnfdVirtualComputeDescId) {
		this.vnfdVirtualComputeDescId = vnfdVirtualComputeDescId;
	}

	public VimComputeResourceFlavour vimFlavourId(final String vimFlavourId) {
		this.vimFlavourId = vimFlavourId;
		return this;
	}

	/**
	 * Identifier of the compute resource flavour in the resource management layer
	 * (i.e. VIM).
	 *
	 * @return vimFlavourId
	 **/
	@ApiModelProperty(required = true, value = "Identifier of the compute resource flavour in the resource management layer (i.e. VIM). ")
	@NotNull

	public String getVimFlavourId() {
		return vimFlavourId;
	}

	public void setVimFlavourId(final String vimFlavourId) {
		this.vimFlavourId = vimFlavourId;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final VimComputeResourceFlavour vimComputeResourceFlavour = (VimComputeResourceFlavour) o;
		return Objects.equals(this.vimConnectionId, vimComputeResourceFlavour.vimConnectionId) &&
				Objects.equals(this.resourceProviderId, vimComputeResourceFlavour.resourceProviderId) &&
				Objects.equals(this.vnfdVirtualComputeDescId, vimComputeResourceFlavour.vnfdVirtualComputeDescId) &&
				Objects.equals(this.vimFlavourId, vimComputeResourceFlavour.vimFlavourId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vimConnectionId, resourceProviderId, vnfdVirtualComputeDescId, vimFlavourId);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VimComputeResourceFlavour {\n");

		sb.append("    vimConnectionId: ").append(toIndentedString(vimConnectionId)).append("\n");
		sb.append("    resourceProviderId: ").append(toIndentedString(resourceProviderId)).append("\n");
		sb.append("    vnfdVirtualComputeDescId: ").append(toIndentedString(vnfdVirtualComputeDescId)).append("\n");
		sb.append("    vimFlavourId: ").append(toIndentedString(vimFlavourId)).append("\n");
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
