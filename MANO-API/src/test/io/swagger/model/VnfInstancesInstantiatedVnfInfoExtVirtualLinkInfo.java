package io.swagger.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class VnfInstancesInstantiatedVnfInfoExtVirtualLinkInfo {

	private @Valid String id = null;
	private @Valid VnfInstancesInstantiatedVnfInfoResourceHandle resourceHandle = null;
	private @Valid List<VnfInstancesInstantiatedVnfInfoExtLinkPorts> extLinkPorts = new ArrayList<VnfInstancesInstantiatedVnfInfoExtLinkPorts>();

	/**
	 * An identifier with the intention of being globally unique.
	 **/
	public VnfInstancesInstantiatedVnfInfoExtVirtualLinkInfo id(String id) {
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
	public VnfInstancesInstantiatedVnfInfoExtVirtualLinkInfo resourceHandle(VnfInstancesInstantiatedVnfInfoResourceHandle resourceHandle) {
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
	 * Link ports of this VL.
	 **/
	public VnfInstancesInstantiatedVnfInfoExtVirtualLinkInfo extLinkPorts(List<VnfInstancesInstantiatedVnfInfoExtLinkPorts> extLinkPorts) {
		this.extLinkPorts = extLinkPorts;
		return this;
	}

	@ApiModelProperty(value = "Link ports of this VL. ")
	@JsonProperty("extLinkPorts")
	public List<VnfInstancesInstantiatedVnfInfoExtLinkPorts> getExtLinkPorts() {
		return extLinkPorts;
	}

	public void setExtLinkPorts(List<VnfInstancesInstantiatedVnfInfoExtLinkPorts> extLinkPorts) {
		this.extLinkPorts = extLinkPorts;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final VnfInstancesInstantiatedVnfInfoExtVirtualLinkInfo vnfInstancesInstantiatedVnfInfoExtVirtualLinkInfo = (VnfInstancesInstantiatedVnfInfoExtVirtualLinkInfo) o;
		return Objects.equals(id, vnfInstancesInstantiatedVnfInfoExtVirtualLinkInfo.id) &&
				Objects.equals(resourceHandle, vnfInstancesInstantiatedVnfInfoExtVirtualLinkInfo.resourceHandle) &&
				Objects.equals(extLinkPorts, vnfInstancesInstantiatedVnfInfoExtVirtualLinkInfo.extLinkPorts);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, resourceHandle, extLinkPorts);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfInstancesInstantiatedVnfInfoExtVirtualLinkInfo {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    resourceHandle: ").append(toIndentedString(resourceHandle)).append("\n");
		sb.append("    extLinkPorts: ").append(toIndentedString(extLinkPorts)).append("\n");
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
