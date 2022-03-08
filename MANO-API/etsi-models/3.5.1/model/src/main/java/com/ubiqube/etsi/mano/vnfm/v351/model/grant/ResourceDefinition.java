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
package com.ubiqube.etsi.mano.vnfm.v351.model.grant;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.em.v351.model.vnflcm.ResourceHandle;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * This type provides information of an existing or proposed resource used by
 * the VNF. It shall comply with the provisions defined in table 9.5.3.2-1. NOTE
 * 1: The use cases UC#4 and UC#5 in Annex A.4 of ETSI GS NFV-IFA 007 provide
 * examples for such a configuration. NOTE 2: In the context of an operation
 * that changes the current VNF package, the following applies: If this
 * ResourceDefinition is related to a resource to be created or modified, the
 * \&quot;vnfdId\&quot; attribute shall contain the identifier of the
 * destination VNFD. If this ResourceDefinition is related to a resource to be
 * deleted, the \&quot;vnfdId\&quot; attribute shall contain the identifier of
 * the source VNFD. If this ResourceDefinition is related to a temporary
 * resource, the \&quot;vnfdId\&quot; attribute shall contain the identifier of
 * either the source VNFD or the destination VNFD.
 */
@Schema(description = "This type provides information of an existing or proposed resource used by the VNF.  It shall comply with the provisions defined in table 9.5.3.2-1. NOTE 1: The use cases UC#4 and UC#5 in Annex A.4 of ETSI GS NFV-IFA 007 provide examples           for such a configuration. NOTE 2: In the context of an operation that changes the current VNF package, the following applies: If this           ResourceDefinition is related to a resource to be created or modified, the \"vnfdId\" attribute shall           contain the identifier of the destination VNFD. If this ResourceDefinition is related to a resource           to be deleted, the \"vnfdId\" attribute shall contain the identifier of the source VNFD. If this           ResourceDefinition is related to a temporary resource, the \"vnfdId\" attribute shall contain the           identifier of either the source VNFD or the destination VNFD. ")
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

	@JsonProperty("vnfdId")
	private String vnfdId = null;

	@JsonProperty("resourceTemplateId")
	private String resourceTemplateId = null;

	@JsonProperty("secondaryResourceTemplateId")
	private String secondaryResourceTemplateId = null;

	@JsonProperty("resource")
	private ResourceHandle resource = null;

	@JsonProperty("snapshotResDef")
	private SnapshotResourceDefinition snapshotResDef = null;

	public ResourceDefinition id(final String id) {
		this.id = id;
		return this;
	}

	/**
	 * Get id
	 *
	 * @return id
	 **/
	@Schema(required = true, description = "")
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
	 * Get vduId
	 *
	 * @return vduId
	 **/
	@Schema(description = "")

	public String getVduId() {
		return vduId;
	}

	public void setVduId(final String vduId) {
		this.vduId = vduId;
	}

	public ResourceDefinition vnfdId(final String vnfdId) {
		this.vnfdId = vnfdId;
		return this;
	}

	/**
	 * Get vnfdId
	 *
	 * @return vnfdId
	 **/
	@Schema(description = "")

	public String getVnfdId() {
		return vnfdId;
	}

	public void setVnfdId(final String vnfdId) {
		this.vnfdId = vnfdId;
	}

	public ResourceDefinition resourceTemplateId(final String resourceTemplateId) {
		this.resourceTemplateId = resourceTemplateId;
		return this;
	}

	/**
	 * Get resourceTemplateId
	 *
	 * @return resourceTemplateId
	 **/
	@Schema(description = "")

	public String getResourceTemplateId() {
		return resourceTemplateId;
	}

	public void setResourceTemplateId(final String resourceTemplateId) {
		this.resourceTemplateId = resourceTemplateId;
	}

	public ResourceDefinition secondaryResourceTemplateId(final String secondaryResourceTemplateId) {
		this.secondaryResourceTemplateId = secondaryResourceTemplateId;
		return this;
	}

	/**
	 * Get secondaryResourceTemplateId
	 *
	 * @return secondaryResourceTemplateId
	 **/
	@Schema(description = "")

	public String getSecondaryResourceTemplateId() {
		return secondaryResourceTemplateId;
	}

	public void setSecondaryResourceTemplateId(final String secondaryResourceTemplateId) {
		this.secondaryResourceTemplateId = secondaryResourceTemplateId;
	}

	public ResourceDefinition resource(final ResourceHandle resource) {
		this.resource = resource;
		return this;
	}

	/**
	 * Get resource
	 *
	 * @return resource
	 **/
	@Schema(description = "")

	@Valid
	public ResourceHandle getResource() {
		return resource;
	}

	public void setResource(final ResourceHandle resource) {
		this.resource = resource;
	}

	public ResourceDefinition snapshotResDef(final SnapshotResourceDefinition snapshotResDef) {
		this.snapshotResDef = snapshotResDef;
		return this;
	}

	/**
	 * Get snapshotResDef
	 *
	 * @return snapshotResDef
	 **/
	@Schema(description = "")

	@Valid
	public SnapshotResourceDefinition getSnapshotResDef() {
		return snapshotResDef;
	}

	public void setSnapshotResDef(final SnapshotResourceDefinition snapshotResDef) {
		this.snapshotResDef = snapshotResDef;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final ResourceDefinition resourceDefinition = (ResourceDefinition) o;
		return Objects.equals(this.id, resourceDefinition.id) &&
				Objects.equals(this.type, resourceDefinition.type) &&
				Objects.equals(this.vduId, resourceDefinition.vduId) &&
				Objects.equals(this.vnfdId, resourceDefinition.vnfdId) &&
				Objects.equals(this.resourceTemplateId, resourceDefinition.resourceTemplateId) &&
				Objects.equals(this.secondaryResourceTemplateId, resourceDefinition.secondaryResourceTemplateId) &&
				Objects.equals(this.resource, resourceDefinition.resource) &&
				Objects.equals(this.snapshotResDef, resourceDefinition.snapshotResDef);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, type, vduId, vnfdId, resourceTemplateId, secondaryResourceTemplateId, resource, snapshotResDef);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class ResourceDefinition {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    type: ").append(toIndentedString(type)).append("\n");
		sb.append("    vduId: ").append(toIndentedString(vduId)).append("\n");
		sb.append("    vnfdId: ").append(toIndentedString(vnfdId)).append("\n");
		sb.append("    resourceTemplateId: ").append(toIndentedString(resourceTemplateId)).append("\n");
		sb.append("    secondaryResourceTemplateId: ").append(toIndentedString(secondaryResourceTemplateId)).append("\n");
		sb.append("    resource: ").append(toIndentedString(resource)).append("\n");
		sb.append("    snapshotResDef: ").append(toIndentedString(snapshotResDef)).append("\n");
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
