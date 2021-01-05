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
package com.ubiqube.etsi.mano.nfvo.v261.model.nsfm;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents the information that allows addressing a virtualised
 * resource that is used by a VNF instance or by an NS instance. Information
 * about the resource is available from the VIM.
 */
@ApiModel(description = "This type represents the information that allows addressing a virtualised resource that is used by a VNF instance or by an NS instance. Information about the resource is available from the VIM. ")
@Validated
public class ResourceHandle {
	@JsonProperty("vimId")
	private String vimId = null;

	@JsonProperty("resourceProviderId")
	private String resourceProviderId = null;

	@JsonProperty("resourceId")
	private String resourceId = null;

	@JsonProperty("vimLevelResourceType")
	private String vimLevelResourceType = null;

	public ResourceHandle vimId(final String vimId) {
		this.vimId = vimId;
		return this;
	}

	/**
	 * Get vimId
	 *
	 * @return vimId
	 **/
	@ApiModelProperty(value = "")

	public String getVimId() {
		return vimId;
	}

	public void setVimId(final String vimId) {
		this.vimId = vimId;
	}

	public ResourceHandle resourceProviderId(final String resourceProviderId) {
		this.resourceProviderId = resourceProviderId;
		return this;
	}

	/**
	 * Get resourceProviderId
	 *
	 * @return resourceProviderId
	 **/
	@ApiModelProperty(value = "")

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
	 * Get resourceId
	 *
	 * @return resourceId
	 **/
	@ApiModelProperty(required = true, value = "")
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
	 * Type of the resource in the scope of the VIM or the resource provider. The
	 * value set of the \"vimLevelResourceType\" attribute is within the scope of
	 * the VIM or the resource provider and can be used as information that
	 * complements the ResourceHandle.
	 *
	 * @return vimLevelResourceType
	 **/
	@ApiModelProperty(value = "Type of the resource in the scope of the VIM or the resource provider. The value set of the \"vimLevelResourceType\" attribute is within the scope of the VIM or the resource provider and can be used as information that complements the ResourceHandle. ")

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
		return Objects.equals(this.vimId, resourceHandle.vimId) &&
				Objects.equals(this.resourceProviderId, resourceHandle.resourceProviderId) &&
				Objects.equals(this.resourceId, resourceHandle.resourceId) &&
				Objects.equals(this.vimLevelResourceType, resourceHandle.vimLevelResourceType);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vimId, resourceProviderId, resourceId, vimLevelResourceType);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class ResourceHandle {\n");

		sb.append("    vimId: ").append(toIndentedString(vimId)).append("\n");
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
