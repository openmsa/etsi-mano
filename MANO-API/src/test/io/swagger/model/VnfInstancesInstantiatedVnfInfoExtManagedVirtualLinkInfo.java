package io.swagger.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class VnfInstancesInstantiatedVnfInfoExtManagedVirtualLinkInfo {

	private @Valid String id = null;
	private @Valid String vnfVirtualLinkDescId = null;
	private @Valid VnfInstancesInstantiatedVnfInfoResourceHandle networkResource = null;
	private @Valid List<VnfInstancesInstantiatedVnfInfoVnfLinkPorts> vnfLinkPorts = new ArrayList<VnfInstancesInstantiatedVnfInfoVnfLinkPorts>();

	/**
	 * An identifier with the intention of being globally unique.
	 **/
	public VnfInstancesInstantiatedVnfInfoExtManagedVirtualLinkInfo id(String id) {
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
	 * An identifier that is unique for the respective type within a VNF instance,
	 * but may not be globally unique.
	 **/
	public VnfInstancesInstantiatedVnfInfoExtManagedVirtualLinkInfo vnfVirtualLinkDescId(String vnfVirtualLinkDescId) {
		this.vnfVirtualLinkDescId = vnfVirtualLinkDescId;
		return this;
	}

	@ApiModelProperty(required = true, value = "An identifier that is unique for the respective type within a VNF instance, but may not be globally unique. ")
	@JsonProperty("vnfVirtualLinkDescId")
	@NotNull
	public String getVnfVirtualLinkDescId() {
		return vnfVirtualLinkDescId;
	}

	public void setVnfVirtualLinkDescId(String vnfVirtualLinkDescId) {
		this.vnfVirtualLinkDescId = vnfVirtualLinkDescId;
	}

	/**
	 **/
	public VnfInstancesInstantiatedVnfInfoExtManagedVirtualLinkInfo networkResource(VnfInstancesInstantiatedVnfInfoResourceHandle networkResource) {
		this.networkResource = networkResource;
		return this;
	}

	@ApiModelProperty(value = "")
	@JsonProperty("networkResource")
	public VnfInstancesInstantiatedVnfInfoResourceHandle getNetworkResource() {
		return networkResource;
	}

	public void setNetworkResource(VnfInstancesInstantiatedVnfInfoResourceHandle networkResource) {
		this.networkResource = networkResource;
	}

	/**
	 * Link ports of this VL.
	 **/
	public VnfInstancesInstantiatedVnfInfoExtManagedVirtualLinkInfo vnfLinkPorts(List<VnfInstancesInstantiatedVnfInfoVnfLinkPorts> vnfLinkPorts) {
		this.vnfLinkPorts = vnfLinkPorts;
		return this;
	}

	@ApiModelProperty(value = "Link ports of this VL. ")
	@JsonProperty("vnfLinkPorts")
	public List<VnfInstancesInstantiatedVnfInfoVnfLinkPorts> getVnfLinkPorts() {
		return vnfLinkPorts;
	}

	public void setVnfLinkPorts(List<VnfInstancesInstantiatedVnfInfoVnfLinkPorts> vnfLinkPorts) {
		this.vnfLinkPorts = vnfLinkPorts;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final VnfInstancesInstantiatedVnfInfoExtManagedVirtualLinkInfo vnfInstancesInstantiatedVnfInfoExtManagedVirtualLinkInfo = (VnfInstancesInstantiatedVnfInfoExtManagedVirtualLinkInfo) o;
		return Objects.equals(id, vnfInstancesInstantiatedVnfInfoExtManagedVirtualLinkInfo.id) &&
				Objects.equals(vnfVirtualLinkDescId, vnfInstancesInstantiatedVnfInfoExtManagedVirtualLinkInfo.vnfVirtualLinkDescId) &&
				Objects.equals(networkResource, vnfInstancesInstantiatedVnfInfoExtManagedVirtualLinkInfo.networkResource) &&
				Objects.equals(vnfLinkPorts, vnfInstancesInstantiatedVnfInfoExtManagedVirtualLinkInfo.vnfLinkPorts);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, vnfVirtualLinkDescId, networkResource, vnfLinkPorts);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfInstancesInstantiatedVnfInfoExtManagedVirtualLinkInfo {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    vnfVirtualLinkDescId: ").append(toIndentedString(vnfVirtualLinkDescId)).append("\n");
		sb.append("    networkResource: ").append(toIndentedString(networkResource)).append("\n");
		sb.append("    vnfLinkPorts: ").append(toIndentedString(vnfLinkPorts)).append("\n");
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
