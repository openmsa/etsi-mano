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

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents the information that allows addressing a virtualised
 * resource that is used by a VNF instance. Information about the resource is
 * available from the VIM.
 */
@ApiModel(description = "This type represents the information that allows addressing a virtualised resource that is used by a VNF instance. Information about the resource is available from the VIM. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-12-11T18:14:17.202+01:00")

public class ResourceHandle {
	@JsonProperty("vimConnectionId")
	private String vimConnectionId = null;

	@JsonProperty("resourceProviderId")
	private String resourceProviderId = null;

	@JsonProperty("resourceId")
	private String resourceId = null;

	@JsonProperty("vimLevelResourceType")
	private String vimLevelResourceType = null;

	public ResourceHandle vimConnectionId(final String vimConnectionId) {
		this.vimConnectionId = vimConnectionId;
		return this;
	}

	/**
	 * Identifier of the VIM connection to manage the resource. This attribute shall
	 * only be supported and present if VNF-related resource management in direct
	 * mode is applicable. The applicable \"VimConnectionInfo\" structure, which is
	 * referenced by vimConnectionId, can be obtained from the \"vimConnectionInfo\"
	 * attribute of the \"VnfInstance\" structure.
	 *
	 * @return vimConnectionId
	 **/
	@ApiModelProperty(required = true, value = "Identifier of the VIM connection to manage the resource. This attribute shall only be supported and present if VNF-related resource management in direct mode is applicable. The applicable \"VimConnectionInfo\" structure, which is referenced by vimConnectionId, can be obtained from the \"vimConnectionInfo\" attribute of the \"VnfInstance\" structure. ")
	@NotNull

	public String getVimConnectionId() {
		return vimConnectionId;
	}

	public void setVimConnectionId(final String vimConnectionId) {
		this.vimConnectionId = vimConnectionId;
	}

	public ResourceHandle resourceProviderId(final String resourceProviderId) {
		this.resourceProviderId = resourceProviderId;
		return this;
	}

	/**
	 * Identifier of the entity responsible for the management of the resource. This
	 * attribute shall only be supported and present when VNF-related resource
	 * management in indirect mode is applicable. The identification scheme is
	 * outside the scope of the present document.
	 *
	 * @return resourceProviderId
	 **/
	@ApiModelProperty(value = "Identifier of the entity responsible for the management of the resource. This attribute shall only be supported and present when VNF-related resource management in indirect mode is applicable. The identification scheme is outside the scope of the present document. ")

	public String getResourceProviderId() {
		return resourceProviderId;
	}

	public void setResourceProviderId(final String resourceProviderId) {
		this.resourceProviderId = resourceProviderId;
	}

	public ResourceHandle resourceId(final String resourceId) {
		this.resourceId = resourceId;
		return this;
	}

	/**
	 * Identifier of the resource in the scope of the VIM or the resource provider.
	 *
	 * @return resourceId
	 **/
	@ApiModelProperty(required = true, value = "Identifier of the resource in the scope of the VIM or the resource provider. ")
	@NotNull

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(final String resourceId) {
		this.resourceId = resourceId;
	}

	public ResourceHandle vimLevelResourceType(final String vimLevelResourceType) {
		this.vimLevelResourceType = vimLevelResourceType;
		return this;
	}

	/**
	 * The value set of the \"vimLevelResourceType\" attribute is within the scope
	 * of the VIM or the resource provider and can be used as information that
	 * complements the ResourceHandle. This value set is different from the value
	 * set of the \"type\" attribute in the ResourceDefinition (refer to clause
	 * 9.5.3.2 in SOL003).
	 *
	 * @return vimLevelResourceType
	 **/
	@ApiModelProperty(value = "The value set of the \"vimLevelResourceType\" attribute is within the scope of the VIM or the resource provider and can be used as information that complements the ResourceHandle. This value set is different from the value set of the \"type\" attribute in the ResourceDefinition (refer to clause 9.5.3.2 in SOL003). ")

	public String getVimLevelResourceType() {
		return vimLevelResourceType;
	}

	public void setVimLevelResourceType(final String vimLevelResourceType) {
		this.vimLevelResourceType = vimLevelResourceType;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final ResourceHandle resourceHandle = (ResourceHandle) o;
		return Objects.equals(this.vimConnectionId, resourceHandle.vimConnectionId) &&
				Objects.equals(this.resourceProviderId, resourceHandle.resourceProviderId) &&
				Objects.equals(this.resourceId, resourceHandle.resourceId) &&
				Objects.equals(this.vimLevelResourceType, resourceHandle.vimLevelResourceType);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vimConnectionId, resourceProviderId, resourceId, vimLevelResourceType);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class ResourceHandle {\n");

		sb.append("    vimConnectionId: ").append(toIndentedString(vimConnectionId)).append("\n");
		sb.append("    resourceProviderId: ").append(toIndentedString(resourceProviderId)).append("\n");
		sb.append("    resourceId: ").append(toIndentedString(resourceId)).append("\n");
		sb.append("    vimLevelResourceType: ").append(toIndentedString(vimLevelResourceType)).append("\n");
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
