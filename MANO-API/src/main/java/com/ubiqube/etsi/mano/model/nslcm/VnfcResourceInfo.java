package com.ubiqube.etsi.mano.model.nslcm;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.model.KeyValuePairs;
import com.ubiqube.etsi.mano.model.ResourceHandle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents the information on virtualised compute and storage
 * resources used by a VNFC in a VNF instance.
 */
@ApiModel(description = "This type represents the information on virtualised compute and storage resources used by a VNFC in a VNF instance. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-07T10:02:43.347+02:00")

public class VnfcResourceInfo {
	@JsonProperty("id")
	private String id = null;

	@JsonProperty("vduId")
	private String vduId = null;

	@JsonProperty("computeResource")
	private ResourceHandle computeResource = null;

	@JsonProperty("storageResourceIds")
	@Valid
	private List<String> storageResourceIds = null;

	@JsonProperty("reservationId")
	private String reservationId = null;

	@JsonProperty("vnfcCpInfo")
	@Valid
	private List<VnfcResourceInfoVnfcCpInfo> vnfcCpInfo = null;

	@JsonProperty("metadata")
	private com.ubiqube.etsi.mano.model.KeyValuePairs metadata = null;

	public VnfcResourceInfo id(final String id) {
		this.id = id;
		return this;
	}

	/**
	 * Identifier of this VnfcResourceInfo instance.
	 *
	 * @return id
	 **/
	@ApiModelProperty(required = true, value = "Identifier of this VnfcResourceInfo instance. ")
	@NotNull

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public VnfcResourceInfo vduId(final String vduId) {
		this.vduId = vduId;
		return this;
	}

	/**
	 * Reference to the applicable VDU in the VNFD.
	 *
	 * @return vduId
	 **/
	@ApiModelProperty(required = true, value = "Reference to the applicable VDU in the VNFD. ")
	@NotNull

	public String getVduId() {
		return vduId;
	}

	public void setVduId(final String vduId) {
		this.vduId = vduId;
	}

	public VnfcResourceInfo computeResource(final ResourceHandle computeResource) {
		this.computeResource = computeResource;
		return this;
	}

	/**
	 * Reference to the VirtualCompute resource.
	 *
	 * @return computeResource
	 **/
	@ApiModelProperty(required = true, value = "Reference to the VirtualCompute resource. ")
	@NotNull

	@Valid

	public ResourceHandle getComputeResource() {
		return computeResource;
	}

	public void setComputeResource(final ResourceHandle computeResource) {
		this.computeResource = computeResource;
	}

	public VnfcResourceInfo storageResourceIds(final List<String> storageResourceIds) {
		this.storageResourceIds = storageResourceIds;
		return this;
	}

	public VnfcResourceInfo addStorageResourceIdsItem(final String storageResourceIdsItem) {
		if (this.storageResourceIds == null) {
			this.storageResourceIds = new ArrayList<>();
		}
		this.storageResourceIds.add(storageResourceIdsItem);
		return this;
	}

	/**
	 * References to the VirtualStorage resources. The value refers to a
	 * VirtualStorageResourceInfo item in the VnfInstance.
	 *
	 * @return storageResourceIds
	 **/
	@ApiModelProperty(value = "References to the VirtualStorage resources. The value refers to a VirtualStorageResourceInfo item in the VnfInstance. ")

	public List<String> getStorageResourceIds() {
		return storageResourceIds;
	}

	public void setStorageResourceIds(final List<String> storageResourceIds) {
		this.storageResourceIds = storageResourceIds;
	}

	public VnfcResourceInfo reservationId(final String reservationId) {
		this.reservationId = reservationId;
		return this;
	}

	/**
	 * The reservation identifier applicable to the resource. It shall be present
	 * when an applicable reservation exists.
	 *
	 * @return reservationId
	 **/
	@ApiModelProperty(value = "The reservation identifier applicable to the resource. It shall be present when an applicable reservation exists. ")

	public String getReservationId() {
		return reservationId;
	}

	public void setReservationId(final String reservationId) {
		this.reservationId = reservationId;
	}

	public VnfcResourceInfo vnfcCpInfo(final List<VnfcResourceInfoVnfcCpInfo> vnfcCpInfo) {
		this.vnfcCpInfo = vnfcCpInfo;
		return this;
	}

	public VnfcResourceInfo addVnfcCpInfoItem(final VnfcResourceInfoVnfcCpInfo vnfcCpInfoItem) {
		if (this.vnfcCpInfo == null) {
			this.vnfcCpInfo = new ArrayList<>();
		}
		this.vnfcCpInfo.add(vnfcCpInfoItem);
		return this;
	}

	/**
	 * CPs of the VNFC instance. Shall be present when that particular CP of the
	 * VNFC instance is associated to an external CP of the VNF instance. May be
	 * present otherwise.
	 *
	 * @return vnfcCpInfo
	 **/
	@ApiModelProperty(value = "CPs of the VNFC instance. Shall be present when that particular CP of the VNFC instance is associated to an external CP of the VNF instance. May be present otherwise. ")

	@Valid

	public List<VnfcResourceInfoVnfcCpInfo> getVnfcCpInfo() {
		return vnfcCpInfo;
	}

	public void setVnfcCpInfo(final List<VnfcResourceInfoVnfcCpInfo> vnfcCpInfo) {
		this.vnfcCpInfo = vnfcCpInfo;
	}

	public VnfcResourceInfo metadata(final KeyValuePairs metadata) {
		this.metadata = metadata;
		return this;
	}

	/**
	 * Metadata about this resource.
	 *
	 * @return metadata
	 **/
	@ApiModelProperty(value = "Metadata about this resource. ")

	@Valid

	public KeyValuePairs getMetadata() {
		return metadata;
	}

	public void setMetadata(final KeyValuePairs metadata) {
		this.metadata = metadata;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final VnfcResourceInfo vnfcResourceInfo = (VnfcResourceInfo) o;
		return Objects.equals(this.id, vnfcResourceInfo.id) &&
				Objects.equals(this.vduId, vnfcResourceInfo.vduId) &&
				Objects.equals(this.computeResource, vnfcResourceInfo.computeResource) &&
				Objects.equals(this.storageResourceIds, vnfcResourceInfo.storageResourceIds) &&
				Objects.equals(this.reservationId, vnfcResourceInfo.reservationId) &&
				Objects.equals(this.vnfcCpInfo, vnfcResourceInfo.vnfcCpInfo) &&
				Objects.equals(this.metadata, vnfcResourceInfo.metadata);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, vduId, computeResource, storageResourceIds, reservationId, vnfcCpInfo, metadata);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfcResourceInfo {\n");

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
	private String toIndentedString(final java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
