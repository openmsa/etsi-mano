package io.swagger.model;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class VnfInstancesInstantiatedVnfInfoVnfLinkPorts {

	private @Valid String id = null;
	private @Valid VnfInstancesInstantiatedVnfInfoResourceHandle resourceHandle = null;
	private @Valid String cpInstanceId = null;

	/**
	 * An identifier that is unique for the respective type within a VNF instance,
	 * but may not be globally unique.
	 **/
	public VnfInstancesInstantiatedVnfInfoVnfLinkPorts id(String id) {
		this.id = id;
		return this;
	}

	@ApiModelProperty(required = true, value = "An identifier that is unique for the respective type within a VNF instance, but may not be globally unique. ")
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
	public VnfInstancesInstantiatedVnfInfoVnfLinkPorts resourceHandle(VnfInstancesInstantiatedVnfInfoResourceHandle resourceHandle) {
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

	/**
	 * An identifier that is unique for the respective type within a VNF instance,
	 * but may not be globally unique.
	 **/
	public VnfInstancesInstantiatedVnfInfoVnfLinkPorts cpInstanceId(String cpInstanceId) {
		this.cpInstanceId = cpInstanceId;
		return this;
	}

	@ApiModelProperty(value = "An identifier that is unique for the respective type within a VNF instance, but may not be globally unique. ")
	@JsonProperty("cpInstanceId")
	public String getCpInstanceId() {
		return cpInstanceId;
	}

	public void setCpInstanceId(String cpInstanceId) {
		this.cpInstanceId = cpInstanceId;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final VnfInstancesInstantiatedVnfInfoVnfLinkPorts vnfInstancesInstantiatedVnfInfoVnfLinkPorts = (VnfInstancesInstantiatedVnfInfoVnfLinkPorts) o;
		return Objects.equals(id, vnfInstancesInstantiatedVnfInfoVnfLinkPorts.id) &&
				Objects.equals(resourceHandle, vnfInstancesInstantiatedVnfInfoVnfLinkPorts.resourceHandle) &&
				Objects.equals(cpInstanceId, vnfInstancesInstantiatedVnfInfoVnfLinkPorts.cpInstanceId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, resourceHandle, cpInstanceId);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfInstancesInstantiatedVnfInfoVnfLinkPorts {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    resourceHandle: ").append(toIndentedString(resourceHandle)).append("\n");
		sb.append("    cpInstanceId: ").append(toIndentedString(cpInstanceId)).append("\n");
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
