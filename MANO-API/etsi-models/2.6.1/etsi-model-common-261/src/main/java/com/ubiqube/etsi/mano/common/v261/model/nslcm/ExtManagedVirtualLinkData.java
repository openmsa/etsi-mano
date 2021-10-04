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

package com.ubiqube.etsi.mano.common.v261.model.nslcm;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;


/**
 * This type represents an externally-managed internal VL. It shall comply with the provisions defined in Table 6.5.3.27-1.
 */
@Schema(description = "This type represents an externally-managed internal VL. It shall comply with the provisions defined in Table 6.5.3.27-1. ")
@Validated
public class ExtManagedVirtualLinkData {

	private String id;

	@JsonProperty("extManagedVirtualLinkId")
	private String extManagedVirtualLinkId = null;

	@JsonProperty("vnfVirtualLinkDescId")
	private String vmfVirtualLinkDescId = null;

	@JsonProperty("vimId")
	private String vimId = null;

	@JsonProperty("resourceProviderId")
	private String resourceProviderId = null;

	@JsonProperty("resourceId")
	private String resourceId = null;

	public ExtManagedVirtualLinkData extManagedVirtualLinkId(final String extManagedVirtualLinkId) {
		this.extManagedVirtualLinkId = extManagedVirtualLinkId;
		return this;
	}

	/**
	 * The identifier of the externally-managed internal VL instance, if provided.
	 *
	 * @return extManagedVirtualLinkId
	 **/
	@Schema(description = "The identifier of the externally-managed internal VL instance, if provided. ")

	public String getExtManagedVirtualLinkId() {
		return extManagedVirtualLinkId;
	}

	public void setExtManagedVirtualLinkId(final String extManagedVirtualLinkId) {
		this.extManagedVirtualLinkId = extManagedVirtualLinkId;
	}

	public ExtManagedVirtualLinkData vmfVirtualLinkDescId(final String vmfVirtualLinkDescId) {
		this.vmfVirtualLinkDescId = vmfVirtualLinkDescId;
		return this;
	}

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	/**
	 * The identifier of the VLD in the VNFD for this VL.
	 *
	 * @return vmfVirtualLinkDescId
	 **/
	@Schema(description = "The identifier of the VLD in the VNFD for this VL. ")

	public String getVmfVirtualLinkDescId() {
		return vmfVirtualLinkDescId;
	}

	public void setVmfVirtualLinkDescId(final String vmfVirtualLinkDescId) {
		this.vmfVirtualLinkDescId = vmfVirtualLinkDescId;
	}

	public ExtManagedVirtualLinkData vimId(final String vimId) {
		this.vimId = vimId;
		return this;
	}

	/**
	 * Identifier of the VIM that manage this resource. This attribute shall only be supported and present if VNFrelated resource management in direct mode is applicable.
	 *
	 * @return vimId
	 **/
	@Schema(description = "Identifier of the VIM that manage this resource. This attribute shall only be supported and present if VNFrelated resource management in direct mode is applicable. ")

	public String getVimId() {
		return vimId;
	}

	public void setVimId(final String vimId) {
		this.vimId = vimId;
	}

	public ExtManagedVirtualLinkData resourceProviderId(final String resourceProviderId) {
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

	public ExtManagedVirtualLinkData resourceId(final String resourceId) {
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

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final ExtManagedVirtualLinkData extManagedVirtualLinkData = (ExtManagedVirtualLinkData) o;
		return Objects.equals(this.id, extManagedVirtualLinkData.id) &&
				Objects.equals(this.extManagedVirtualLinkId, extManagedVirtualLinkData.extManagedVirtualLinkId) &&
				Objects.equals(this.vmfVirtualLinkDescId, extManagedVirtualLinkData.vmfVirtualLinkDescId) &&
				Objects.equals(this.vimId, extManagedVirtualLinkData.vimId) &&
				Objects.equals(this.resourceProviderId, extManagedVirtualLinkData.resourceProviderId) &&
				Objects.equals(this.resourceId, extManagedVirtualLinkData.resourceId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(extManagedVirtualLinkId, vmfVirtualLinkDescId, vimId, resourceProviderId, resourceId);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class ExtManagedVirtualLinkData {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    extManagedVirtualLinkId: ").append(toIndentedString(extManagedVirtualLinkId)).append("\n");
		sb.append("    vmfVirtualLinkDescId: ").append(toIndentedString(vmfVirtualLinkDescId)).append("\n");
		sb.append("    vimId: ").append(toIndentedString(vimId)).append("\n");
		sb.append("    resourceProviderId: ").append(toIndentedString(resourceProviderId)).append("\n");
		sb.append("    resourceId: ").append(toIndentedString(resourceId)).append("\n");
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
