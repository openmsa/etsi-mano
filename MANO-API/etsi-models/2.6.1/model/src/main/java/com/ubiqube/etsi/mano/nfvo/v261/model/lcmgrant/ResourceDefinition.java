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

package com.ubiqube.etsi.mano.nfvo.v261.model.lcmgrant;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.common.v261.model.ResourceHandle;

import io.swagger.v3.oas.annotations.media.Schema;


/**
 * This type provides information of an existing or proposed resource used by
 * the VNF.
 */
@Schema(description = "This type provides information of an existing or proposed resource used by the VNF. ")
@Validated


public class ResourceDefinition {
	@JsonProperty("id")
	private String id = null;

	/**
	 * Type of the resource definition referenced. Permitted values: * COMPUTE * VL
	 * * STORAGE * LINKPORT
	 */
	public enum TypeEnum {
		COMPUTE("COMPUTE"),

		VL("VL"),

		STORAGE("STORAGE"),

		LINKPORT("LINKPORT");

		private final String value;

		TypeEnum(final String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static TypeEnum fromValue(final String text) {
			for (final TypeEnum b : TypeEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	@JsonProperty("type")
	private TypeEnum type = null;

	@JsonProperty("vduId")
	private String vduId = null;

	@JsonProperty("resourceTemplateId")
	private String resourceTemplateId = null;

	@JsonProperty("resource")
	private ResourceHandle resource = null;

	public ResourceDefinition id(final String id) {
		this.id = id;
		return this;
	}

	/**
	 * Identifier of this \"ResourceDefinition\" structure, unique at least within
	 * the scope of the \"GrantRequest\" structure.
	 * 
	 * @return id
	 **/
	@Schema(required = true, description = "Identifier of this \"ResourceDefinition\" structure, unique at least within the scope of the \"GrantRequest\" structure. ")
	@NotNull

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public ResourceDefinition type(final TypeEnum type) {
		this.type = type;
		return this;
	}

	/**
	 * Type of the resource definition referenced. Permitted values: * COMPUTE * VL
	 * * STORAGE * LINKPORT
	 * 
	 * @return type
	 **/
	@Schema(required = true, description = "Type of the resource definition referenced. Permitted values: * COMPUTE * VL * STORAGE * LINKPORT ")
	@NotNull

	public TypeEnum getType() {
		return type;
	}

	public void setType(final TypeEnum type) {
		this.type = type;
	}

	public ResourceDefinition vduId(final String vduId) {
		this.vduId = vduId;
		return this;
	}

	/**
	 * Reference to the related VDU in the VNFD applicable to this resource. Shall
	 * only be present if a VDU is applicable to this resource.
	 * 
	 * @return vduId
	 **/
	@Schema(description = "Reference to the related VDU in the VNFD applicable to this resource. Shall only be present if a VDU is applicable to this resource. ")

	public String getVduId() {
		return vduId;
	}

	public void setVduId(final String vduId) {
		this.vduId = vduId;
	}

	public ResourceDefinition resourceTemplateId(final String resourceTemplateId) {
		this.resourceTemplateId = resourceTemplateId;
		return this;
	}

	/**
	 * Reference to a resource template (VnfVirtualLinkDesc, VirtualComputeDesc,
	 * VnfExtCpd, VirtualStorageDesc) in the VNFD.
	 * 
	 * @return resourceTemplateId
	 **/
	@Schema(description = "Reference to a resource template (VnfVirtualLinkDesc, VirtualComputeDesc, VnfExtCpd, VirtualStorageDesc) in the VNFD. ")

	public String getResourceTemplateId() {
		return resourceTemplateId;
	}

	public void setResourceTemplateId(final String resourceTemplateId) {
		this.resourceTemplateId = resourceTemplateId;
	}

	public ResourceDefinition resource(final ResourceHandle resource) {
		this.resource = resource;
		return this;
	}

	/**
	 * Resource information for an existing resource. Shall be present for resources
	 * that are planned to be deleted or modified. Shall be absent otherwise.
	 * 
	 * @return resource
	 **/
	@Schema(description = "Resource information for an existing resource. Shall be present for resources that are planned to be deleted or modified. Shall be absent otherwise. ")

	@Valid

	public ResourceHandle getResource() {
		return resource;
	}

	public void setResource(final ResourceHandle resource) {
		this.resource = resource;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final ResourceDefinition resourceDefinition = (ResourceDefinition) o;
		return Objects.equals(this.id, resourceDefinition.id) &&
				Objects.equals(this.type, resourceDefinition.type) &&
				Objects.equals(this.vduId, resourceDefinition.vduId) &&
				Objects.equals(this.resourceTemplateId, resourceDefinition.resourceTemplateId) &&
				Objects.equals(this.resource, resourceDefinition.resource);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, type, vduId, resourceTemplateId, resource);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class ResourceDefinition {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    type: ").append(toIndentedString(type)).append("\n");
		sb.append("    vduId: ").append(toIndentedString(vduId)).append("\n");
		sb.append("    resourceTemplateId: ").append(toIndentedString(resourceTemplateId)).append("\n");
		sb.append("    resource: ").append(toIndentedString(resource)).append("\n");
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
