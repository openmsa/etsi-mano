package io.swagger.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This type represents the information on virtualised compute and storage resources used by a VNFC in a VNF instance.
 **/
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "This type represents the information on virtualised compute and storage resources used by a VNFC in a VNF instance. ")

public class VnfInstancesInstantiatedVnfInfoVnfcResourceInfo {

	private @Valid String id = null;
	private @Valid String vduId = null;
	private @Valid VnfInstancesInstantiatedVnfInfoResourceHandle computeResource = null;
	private @Valid List<String> storageResourceIds = new ArrayList<String>();
	private @Valid String reservationId = null;
	private @Valid List<VnfInstancesInstantiatedVnfInfoVnfcCpInfo> vnfcCpInfo = new ArrayList<VnfInstancesInstantiatedVnfInfoVnfcCpInfo>();
	private @Valid Object metadata = null;

	/**
	 * An identifier that is unique for the respective type within a VNF instance,
	 * but may not be globally unique.
	 **/
	public VnfInstancesInstantiatedVnfInfoVnfcResourceInfo id(String id) {
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
	public VnfInstancesInstantiatedVnfInfoVnfcResourceInfo vduId(String vduId) {
		this.vduId = vduId;
		return this;
	}

	@ApiModelProperty(required = true, value = "An identifier that is unique within a VNF descriptor. ")
	@JsonProperty("vduId")
	@NotNull
	public String getVduId() {
		return vduId;
	}

	public void setVduId(String vduId) {
		this.vduId = vduId;
	}

	/**
	 **/
	public VnfInstancesInstantiatedVnfInfoVnfcResourceInfo computeResource(VnfInstancesInstantiatedVnfInfoResourceHandle computeResource) {
		this.computeResource = computeResource;
		return this;
	}

	@ApiModelProperty(required = true, value = "")
	@JsonProperty("computeResource")
	@NotNull
	public VnfInstancesInstantiatedVnfInfoResourceHandle getComputeResource() {
		return computeResource;
	}

	public void setComputeResource(VnfInstancesInstantiatedVnfInfoResourceHandle computeResource) {
		this.computeResource = computeResource;
	}

	/**
	 * References to the VirtualStorage resources. The value refers to a
	 * VirtualStorageResourceInfo item in the VnfInstance.
	 **/
	public VnfInstancesInstantiatedVnfInfoVnfcResourceInfo storageResourceIds(List<String> storageResourceIds) {
		this.storageResourceIds = storageResourceIds;
		return this;
	}

	@ApiModelProperty(value = "References to the VirtualStorage resources. The value refers to a VirtualStorageResourceInfo item in the VnfInstance. ")
	@JsonProperty("storageResourceIds")
	public List<String> getStorageResourceIds() {
		return storageResourceIds;
	}

	public void setStorageResourceIds(List<String> storageResourceIds) {
		this.storageResourceIds = storageResourceIds;
	}

	/**
	 * An identifier with the intention of being globally unique.
	 **/
	public VnfInstancesInstantiatedVnfInfoVnfcResourceInfo reservationId(String reservationId) {
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
	 * CPs of the VNFC instance. Shall be present when that particular CP of the
	 * VNFC instance is associated to an external CP of the VNF instance. May be
	 * present otherwise.
	 **/
	public VnfInstancesInstantiatedVnfInfoVnfcResourceInfo vnfcCpInfo(List<VnfInstancesInstantiatedVnfInfoVnfcCpInfo> vnfcCpInfo) {
		this.vnfcCpInfo = vnfcCpInfo;
		return this;
	}

	@ApiModelProperty(required = true, value = "CPs of the VNFC instance. Shall be present when that particular CP of the VNFC instance is associated to an external CP of the VNF instance. May be present otherwise. ")
	@JsonProperty("vnfcCpInfo")
	@NotNull
	public List<VnfInstancesInstantiatedVnfInfoVnfcCpInfo> getVnfcCpInfo() {
		return vnfcCpInfo;
	}

	public void setVnfcCpInfo(List<VnfInstancesInstantiatedVnfInfoVnfcCpInfo> vnfcCpInfo) {
		this.vnfcCpInfo = vnfcCpInfo;
	}

	/**
	 * This type represents a list of key-value pairs. The order of the pairs in the
	 * list is not significant. In JSON, a set of key- value pairs is represented as
	 * an object. It shall comply with the provisions defined in clause 4 of IETF
	 * RFC 7159.
	 **/
	public VnfInstancesInstantiatedVnfInfoVnfcResourceInfo metadata(Object metadata) {
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
		final VnfInstancesInstantiatedVnfInfoVnfcResourceInfo vnfInstancesInstantiatedVnfInfoVnfcResourceInfo = (VnfInstancesInstantiatedVnfInfoVnfcResourceInfo) o;
		return Objects.equals(id, vnfInstancesInstantiatedVnfInfoVnfcResourceInfo.id) &&
				Objects.equals(vduId, vnfInstancesInstantiatedVnfInfoVnfcResourceInfo.vduId) &&
				Objects.equals(computeResource, vnfInstancesInstantiatedVnfInfoVnfcResourceInfo.computeResource) &&
				Objects.equals(storageResourceIds, vnfInstancesInstantiatedVnfInfoVnfcResourceInfo.storageResourceIds) &&
				Objects.equals(reservationId, vnfInstancesInstantiatedVnfInfoVnfcResourceInfo.reservationId) &&
				Objects.equals(vnfcCpInfo, vnfInstancesInstantiatedVnfInfoVnfcResourceInfo.vnfcCpInfo) &&
				Objects.equals(metadata, vnfInstancesInstantiatedVnfInfoVnfcResourceInfo.metadata);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, vduId, computeResource, storageResourceIds, reservationId, vnfcCpInfo, metadata);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfInstancesInstantiatedVnfInfoVnfcResourceInfo {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    vduId: ").append(toIndentedString(vduId)).append("\n");
		sb.append("    computeResource: ").append(toIndentedString(computeResource)).append("\n");
		sb.append("    storageResourceIds: ").append(toIndentedString(storageResourceIds)).append("\n");
		sb.append("    reservationId: ").append(toIndentedString(reservationId)).append("\n");
		sb.append("    vnfcCpInfo: ").append(toIndentedString(vnfcCpInfo)).append("\n");
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
