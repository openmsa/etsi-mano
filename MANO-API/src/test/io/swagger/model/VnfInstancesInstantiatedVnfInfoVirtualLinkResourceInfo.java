package io.swagger.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This type represents the information that allows addressing a virtualised resource that is used by an internal VL instance in a VNF instance.
 **/
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "This type represents the information that allows addressing a virtualised resource that is used by an internal VL instance in a VNF instance. ")

public class VnfInstancesInstantiatedVnfInfoVirtualLinkResourceInfo {

	private @Valid String id = null;
	private @Valid String virtualLinkDescId = null;
	private @Valid VnfInstancesInstantiatedVnfInfoResourceHandle networkResource = null;
	private @Valid String reservationId = null;
	private @Valid List<VnfInstancesInstantiatedVnfInfoVnfLinkPorts> vnfLinkPorts = new ArrayList<VnfInstancesInstantiatedVnfInfoVnfLinkPorts>();
	private @Valid Object metadata = null;

	/**
	 * An identifier that is unique for the respective type within a VNF instance,
	 * but may not be globally unique.
	 **/
	public VnfInstancesInstantiatedVnfInfoVirtualLinkResourceInfo id(String id) {
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
	 * An identifier that is unique within a VNF descriptor.
	 **/
	public VnfInstancesInstantiatedVnfInfoVirtualLinkResourceInfo virtualLinkDescId(String virtualLinkDescId) {
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
	 **/
	public VnfInstancesInstantiatedVnfInfoVirtualLinkResourceInfo networkResource(VnfInstancesInstantiatedVnfInfoResourceHandle networkResource) {
		this.networkResource = networkResource;
		return this;
	}

	@ApiModelProperty(required = true, value = "")
	@JsonProperty("networkResource")
	@NotNull
	public VnfInstancesInstantiatedVnfInfoResourceHandle getNetworkResource() {
		return networkResource;
	}

	public void setNetworkResource(VnfInstancesInstantiatedVnfInfoResourceHandle networkResource) {
		this.networkResource = networkResource;
	}

	/**
	 * An identifier with the intention of being globally unique.
	 **/
	public VnfInstancesInstantiatedVnfInfoVirtualLinkResourceInfo reservationId(String reservationId) {
		this.reservationId = reservationId;
		return this;
	}

	@ApiModelProperty(value = "An identifier with the intention of being globally unique. ")
	@JsonProperty("reservationId")
	public String getReservationId() {
		return reservationId;
	}

	public void setReservationId(String reservationId) {
		this.reservationId = reservationId;
	}

	/**
	 * Links ports of this VL. Shall be present when the linkPort is used for
	 * external connectivity by the VNF (refer to VnfLinkPort). May be present
	 * otherwise.
	 **/
	public VnfInstancesInstantiatedVnfInfoVirtualLinkResourceInfo vnfLinkPorts(List<VnfInstancesInstantiatedVnfInfoVnfLinkPorts> vnfLinkPorts) {
		this.vnfLinkPorts = vnfLinkPorts;
		return this;
	}

	@ApiModelProperty(required = true, value = "Links ports of this VL. Shall be present when the linkPort is used for external connectivity by the VNF (refer to VnfLinkPort). May be present otherwise. ")
	@JsonProperty("vnfLinkPorts")
	@NotNull
	public List<VnfInstancesInstantiatedVnfInfoVnfLinkPorts> getVnfLinkPorts() {
		return vnfLinkPorts;
	}

	public void setVnfLinkPorts(List<VnfInstancesInstantiatedVnfInfoVnfLinkPorts> vnfLinkPorts) {
		this.vnfLinkPorts = vnfLinkPorts;
	}

	/**
	 * This type represents a list of key-value pairs. The order of the pairs in the
	 * list is not significant. In JSON, a set of key- value pairs is represented as
	 * an object. It shall comply with the provisions defined in clause 4 of IETF
	 * RFC 7159.
	 **/
	public VnfInstancesInstantiatedVnfInfoVirtualLinkResourceInfo metadata(Object metadata) {
		this.metadata = metadata;
		return this;
	}

	@ApiModelProperty(value = "This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  ")
	@JsonProperty("metadata")
	public Object getMetadata() {
		return metadata;
	}

	public void setMetadata(Object metadata) {
		this.metadata = metadata;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final VnfInstancesInstantiatedVnfInfoVirtualLinkResourceInfo vnfInstancesInstantiatedVnfInfoVirtualLinkResourceInfo = (VnfInstancesInstantiatedVnfInfoVirtualLinkResourceInfo) o;
		return Objects.equals(id, vnfInstancesInstantiatedVnfInfoVirtualLinkResourceInfo.id) &&
				Objects.equals(virtualLinkDescId, vnfInstancesInstantiatedVnfInfoVirtualLinkResourceInfo.virtualLinkDescId) &&
				Objects.equals(networkResource, vnfInstancesInstantiatedVnfInfoVirtualLinkResourceInfo.networkResource) &&
				Objects.equals(reservationId, vnfInstancesInstantiatedVnfInfoVirtualLinkResourceInfo.reservationId) &&
				Objects.equals(vnfLinkPorts, vnfInstancesInstantiatedVnfInfoVirtualLinkResourceInfo.vnfLinkPorts) &&
				Objects.equals(metadata, vnfInstancesInstantiatedVnfInfoVirtualLinkResourceInfo.metadata);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, virtualLinkDescId, networkResource, reservationId, vnfLinkPorts, metadata);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfInstancesInstantiatedVnfInfoVirtualLinkResourceInfo {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    virtualLinkDescId: ").append(toIndentedString(virtualLinkDescId)).append("\n");
		sb.append("    networkResource: ").append(toIndentedString(networkResource)).append("\n");
		sb.append("    reservationId: ").append(toIndentedString(reservationId)).append("\n");
		sb.append("    vnfLinkPorts: ").append(toIndentedString(vnfLinkPorts)).append("\n");
		sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
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
