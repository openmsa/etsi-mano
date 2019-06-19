package io.swagger.model;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class VnfInstancesvnfInstanceIdinstantiateExtManagedVirtualLinks {

	private @Valid String id = null;
	private @Valid String virtualLinkDescId = null;
	private @Valid String vimConnectionId = null;
	private @Valid String resourceProviderId = null;
	private @Valid String resourceId = null;

	/**
	 * An identifier with the intention of being globally unique.
	 **/
	public VnfInstancesvnfInstanceIdinstantiateExtManagedVirtualLinks id(String id) {
		this.id = id;
		return this;
	}

	@ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
	@JsonProperty("id")
	@NotNull
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * An identifier that is unique within a VNF descriptor.
	 **/
	public VnfInstancesvnfInstanceIdinstantiateExtManagedVirtualLinks virtualLinkDescId(String virtualLinkDescId) {
		this.virtualLinkDescId = virtualLinkDescId;
		return this;
	}

	@ApiModelProperty(required = true, value = "An identifier that is unique within a VNF descriptor. ")
	@JsonProperty("virtualLinkDescId")
	@NotNull
	public String getVirtualLinkDescId() {
		return virtualLinkDescId;
	}

	public void setVirtualLinkDescId(String virtualLinkDescId) {
		this.virtualLinkDescId = virtualLinkDescId;
	}

	/**
	 * An identifier with the intention of being globally unique.
	 **/
	public VnfInstancesvnfInstanceIdinstantiateExtManagedVirtualLinks vimConnectionId(String vimConnectionId) {
		this.vimConnectionId = vimConnectionId;
		return this;
	}

	@ApiModelProperty(value = "An identifier with the intention of being globally unique. ")
	@JsonProperty("vimConnectionId")
	public String getVimConnectionId() {
		return vimConnectionId;
	}

	public void setVimConnectionId(String vimConnectionId) {
		this.vimConnectionId = vimConnectionId;
	}

	/**
	 * An identifier with the intention of being globally unique.
	 **/
	public VnfInstancesvnfInstanceIdinstantiateExtManagedVirtualLinks resourceProviderId(String resourceProviderId) {
		this.resourceProviderId = resourceProviderId;
		return this;
	}

	@ApiModelProperty(value = "An identifier with the intention of being globally unique. ")
	@JsonProperty("resourceProviderId")
	public String getResourceProviderId() {
		return resourceProviderId;
	}

	public void setResourceProviderId(String resourceProviderId) {
		this.resourceProviderId = resourceProviderId;
	}

	/**
	 * An identifier maintained by the VIM or other resource provider. It is
	 * expected to be unique within the VIM instance.
	 **/
	public VnfInstancesvnfInstanceIdinstantiateExtManagedVirtualLinks resourceId(String resourceId) {
		this.resourceId = resourceId;
		return this;
	}

	@ApiModelProperty(required = true, value = "An identifier maintained by the VIM or other resource provider. It is expected to be unique within the VIM instance. ")
	@JsonProperty("resourceId")
	@NotNull
	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final VnfInstancesvnfInstanceIdinstantiateExtManagedVirtualLinks vnfInstancesvnfInstanceIdinstantiateExtManagedVirtualLinks = (VnfInstancesvnfInstanceIdinstantiateExtManagedVirtualLinks) o;
		return Objects.equals(id, vnfInstancesvnfInstanceIdinstantiateExtManagedVirtualLinks.id) &&
				Objects.equals(virtualLinkDescId, vnfInstancesvnfInstanceIdinstantiateExtManagedVirtualLinks.virtualLinkDescId) &&
				Objects.equals(vimConnectionId, vnfInstancesvnfInstanceIdinstantiateExtManagedVirtualLinks.vimConnectionId) &&
				Objects.equals(resourceProviderId, vnfInstancesvnfInstanceIdinstantiateExtManagedVirtualLinks.resourceProviderId) &&
				Objects.equals(resourceId, vnfInstancesvnfInstanceIdinstantiateExtManagedVirtualLinks.resourceId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, virtualLinkDescId, vimConnectionId, resourceProviderId, resourceId);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfInstancesvnfInstanceIdinstantiateExtManagedVirtualLinks {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    virtualLinkDescId: ").append(toIndentedString(virtualLinkDescId)).append("\n");
		sb.append("    vimConnectionId: ").append(toIndentedString(vimConnectionId)).append("\n");
		sb.append("    resourceProviderId: ").append(toIndentedString(resourceProviderId)).append("\n");
		sb.append("    resourceId: ").append(toIndentedString(resourceId)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
