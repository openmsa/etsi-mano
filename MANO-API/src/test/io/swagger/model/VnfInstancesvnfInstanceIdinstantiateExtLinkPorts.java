package io.swagger.model;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This type represents an externally provided link port to be used to connect an external connection point to an external VL.
 **/
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "This type represents an externally provided link port to be used to connect an external connection point to an external VL. ")

public class VnfInstancesvnfInstanceIdinstantiateExtLinkPorts {

	private @Valid String id = null;
	private @Valid VnfInstancesInstantiatedVnfInfoResourceHandle resourceHandle = null;

	/**
	 * An identifier with the intention of being globally unique.
	 **/
	public VnfInstancesvnfInstanceIdinstantiateExtLinkPorts id(String id) {
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
	 **/
	public VnfInstancesvnfInstanceIdinstantiateExtLinkPorts resourceHandle(VnfInstancesInstantiatedVnfInfoResourceHandle resourceHandle) {
		this.resourceHandle = resourceHandle;
		return this;
	}

	@ApiModelProperty(required = true, value = "")
	@JsonProperty("resourceHandle")
	@NotNull
	public VnfInstancesInstantiatedVnfInfoResourceHandle getResourceHandle() {
		return resourceHandle;
	}

	public void setResourceHandle(VnfInstancesInstantiatedVnfInfoResourceHandle resourceHandle) {
		this.resourceHandle = resourceHandle;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final VnfInstancesvnfInstanceIdinstantiateExtLinkPorts vnfInstancesvnfInstanceIdinstantiateExtLinkPorts = (VnfInstancesvnfInstanceIdinstantiateExtLinkPorts) o;
		return Objects.equals(id, vnfInstancesvnfInstanceIdinstantiateExtLinkPorts.id) &&
				Objects.equals(resourceHandle, vnfInstancesvnfInstanceIdinstantiateExtLinkPorts.resourceHandle);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, resourceHandle);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfInstancesvnfInstanceIdinstantiateExtLinkPorts {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    resourceHandle: ").append(toIndentedString(resourceHandle)).append("\n");
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
