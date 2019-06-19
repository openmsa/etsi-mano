package io.swagger.model;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This type represents the information that allows addressing a virtualised resource that is used by a VNF instance.
 **/
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "This type represents the information that allows addressing a virtualised resource that is used by a VNF instance. ")

public class VnfInstancesInstantiatedVnfInfoVirtualStorageResourceInfo {

	private @Valid String id = null;
	private @Valid String virtualStorageDescId = null;
	private @Valid VnfInstancesInstantiatedVnfInfoResourceHandle storageResource = null;
	private @Valid String reservationId = null;
	private @Valid Object metadata = null;

	/**
	 * An identifier that is unique for the respective type within a VNF instance,
	 * but may not be globally unique.
	 **/
	public VnfInstancesInstantiatedVnfInfoVirtualStorageResourceInfo id(String id) {
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
	public VnfInstancesInstantiatedVnfInfoVirtualStorageResourceInfo virtualStorageDescId(String virtualStorageDescId) {
		this.virtualStorageDescId = virtualStorageDescId;
		return this;
	}

	@ApiModelProperty(required = true, value = "An identifier that is unique within a VNF descriptor. ")
	@JsonProperty("virtualStorageDescId")
	@NotNull
	public String getVirtualStorageDescId() {
		return virtualStorageDescId;
	}

	public void setVirtualStorageDescId(String virtualStorageDescId) {
		this.virtualStorageDescId = virtualStorageDescId;
	}

	/**
	 **/
	public VnfInstancesInstantiatedVnfInfoVirtualStorageResourceInfo storageResource(VnfInstancesInstantiatedVnfInfoResourceHandle storageResource) {
		this.storageResource = storageResource;
		return this;
	}

	@ApiModelProperty(required = true, value = "")
	@JsonProperty("storageResource")
	@NotNull
	public VnfInstancesInstantiatedVnfInfoResourceHandle getStorageResource() {
		return storageResource;
	}

	public void setStorageResource(VnfInstancesInstantiatedVnfInfoResourceHandle storageResource) {
		this.storageResource = storageResource;
	}

	/**
	 * An identifier with the intention of being globally unique.
	 **/
	public VnfInstancesInstantiatedVnfInfoVirtualStorageResourceInfo reservationId(String reservationId) {
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
	 * This type represents a list of key-value pairs. The order of the pairs in the
	 * list is not significant. In JSON, a set of key- value pairs is represented as
	 * an object. It shall comply with the provisions defined in clause 4 of IETF
	 * RFC 7159.
	 **/
	public VnfInstancesInstantiatedVnfInfoVirtualStorageResourceInfo metadata(Object metadata) {
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
		final VnfInstancesInstantiatedVnfInfoVirtualStorageResourceInfo vnfInstancesInstantiatedVnfInfoVirtualStorageResourceInfo = (VnfInstancesInstantiatedVnfInfoVirtualStorageResourceInfo) o;
		return Objects.equals(id, vnfInstancesInstantiatedVnfInfoVirtualStorageResourceInfo.id) &&
				Objects.equals(virtualStorageDescId, vnfInstancesInstantiatedVnfInfoVirtualStorageResourceInfo.virtualStorageDescId) &&
				Objects.equals(storageResource, vnfInstancesInstantiatedVnfInfoVirtualStorageResourceInfo.storageResource) &&
				Objects.equals(reservationId, vnfInstancesInstantiatedVnfInfoVirtualStorageResourceInfo.reservationId) &&
				Objects.equals(metadata, vnfInstancesInstantiatedVnfInfoVirtualStorageResourceInfo.metadata);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, virtualStorageDescId, storageResource, reservationId, metadata);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfInstancesInstantiatedVnfInfoVirtualStorageResourceInfo {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    virtualStorageDescId: ").append(toIndentedString(virtualStorageDescId)).append("\n");
		sb.append("    storageResource: ").append(toIndentedString(storageResource)).append("\n");
		sb.append("    reservationId: ").append(toIndentedString(reservationId)).append("\n");
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
