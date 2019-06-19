package io.swagger.model;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This type represents the information that allows addressing a virtualised resource that is used by a VNF instance. Information about the resource is available from the VIM.
 **/
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "This type represents the information that allows addressing a virtualised resource that is used by a VNF instance. Information about the resource is available from the VIM. ")

public class VnfInstancesInstantiatedVnfInfoResourceHandle {

	private @Valid String vimConnectionId = null;
	private @Valid String resourceProviderId = null;
	private @Valid String resourceId = null;
	private @Valid String vimLevelResourceType = null;

	/**
	 * An identifier with the intention of being globally unique.
	 **/
	public VnfInstancesInstantiatedVnfInfoResourceHandle vimConnectionId(String vimConnectionId) {
		this.vimConnectionId = vimConnectionId;
		return this;
	}

	@ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
	@JsonProperty("vimConnectionId")
	@NotNull
	public String getVimConnectionId() {
		return vimConnectionId;
	}

	public void setVimConnectionId(String vimConnectionId) {
		this.vimConnectionId = vimConnectionId;
	}

	/**
	 * An identifier with the intention of being globally unique.
	 **/
	public VnfInstancesInstantiatedVnfInfoResourceHandle resourceProviderId(String resourceProviderId) {
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
	public VnfInstancesInstantiatedVnfInfoResourceHandle resourceId(String resourceId) {
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

	/**
	 * Type of the resource in the scope of the VIM or the resource provider.
	 **/
	public VnfInstancesInstantiatedVnfInfoResourceHandle vimLevelResourceType(String vimLevelResourceType) {
		this.vimLevelResourceType = vimLevelResourceType;
		return this;
	}

	@ApiModelProperty(value = "Type of the resource in the scope of the VIM or the resource provider. ")
	@JsonProperty("vimLevelResourceType")
	public String getVimLevelResourceType() {
		return vimLevelResourceType;
	}

	public void setVimLevelResourceType(String vimLevelResourceType) {
		this.vimLevelResourceType = vimLevelResourceType;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final VnfInstancesInstantiatedVnfInfoResourceHandle vnfInstancesInstantiatedVnfInfoResourceHandle = (VnfInstancesInstantiatedVnfInfoResourceHandle) o;
		return Objects.equals(vimConnectionId, vnfInstancesInstantiatedVnfInfoResourceHandle.vimConnectionId) &&
				Objects.equals(resourceProviderId, vnfInstancesInstantiatedVnfInfoResourceHandle.resourceProviderId) &&
				Objects.equals(resourceId, vnfInstancesInstantiatedVnfInfoResourceHandle.resourceId) &&
				Objects.equals(vimLevelResourceType, vnfInstancesInstantiatedVnfInfoResourceHandle.vimLevelResourceType);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vimConnectionId, resourceProviderId, resourceId, vimLevelResourceType);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfInstancesInstantiatedVnfInfoResourceHandle {\n");

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
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
