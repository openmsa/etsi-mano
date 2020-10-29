/**
 * This copy of Woodstox XML processor is licensed under the
 * Apache (Software) License, version 2.0 ("the License").
 * See the License for details about distribution rights, and the
 * specific rights regarding derivate works.
 *
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/
 *
 * A copy is also included in the downloadable source code package
 * containing Woodstox, in file "ASL2.0", under the same directory
 * as this file.
 */
package com.ubiqube.etsi.mano.common.v261.model;

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
