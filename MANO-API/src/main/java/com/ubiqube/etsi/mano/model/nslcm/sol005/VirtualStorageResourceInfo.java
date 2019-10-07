package com.ubiqube.etsi.mano.model.nslcm.sol005;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents the information that allows addressing a virtualised
 * resource that is used by a VNF instance.
 */
@ApiModel(description = "This type represents the information that allows addressing a virtualised resource that is used by a VNF instance. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-07T10:02:43.347+02:00")

public class VirtualStorageResourceInfo {
	@JsonProperty("id")
	private String id = null;

	@JsonProperty("virtualStorageDescId")
	private String virtualStorageDescId = null;

	@JsonProperty("storageResource")
	private ResourceHandle storageResource = null;

	@JsonProperty("reservationId")
	private String reservationId = null;

	@JsonProperty("metadata")
	private KeyValuePairs metadata = null;

	public VirtualStorageResourceInfo id(final String id) {
		this.id = id;
		return this;
	}

	/**
	 * Identifier of this VirtualStorageResourceInfo instance.
	 * 
	 * @return id
	 **/
	@ApiModelProperty(required = true, value = "Identifier of this VirtualStorageResourceInfo instance. ")
	@NotNull

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public VirtualStorageResourceInfo virtualStorageDescId(final String virtualStorageDescId) {
		this.virtualStorageDescId = virtualStorageDescId;
		return this;
	}

	/**
	 * Identifier of the VirtualStorageDesc in the VNFD.
	 * 
	 * @return virtualStorageDescId
	 **/
	@ApiModelProperty(required = true, value = "Identifier of the VirtualStorageDesc in the VNFD. ")
	@NotNull

	public String getVirtualStorageDescId() {
		return virtualStorageDescId;
	}

	public void setVirtualStorageDescId(final String virtualStorageDescId) {
		this.virtualStorageDescId = virtualStorageDescId;
	}

	public VirtualStorageResourceInfo storageResource(final ResourceHandle storageResource) {
		this.storageResource = storageResource;
		return this;
	}

	/**
	 * Reference to the VirtualStorage resource.
	 * 
	 * @return storageResource
	 **/
	@ApiModelProperty(required = true, value = "Reference to the VirtualStorage resource. ")
	@NotNull

	@Valid

	public ResourceHandle getStorageResource() {
		return storageResource;
	}

	public void setStorageResource(final ResourceHandle storageResource) {
		this.storageResource = storageResource;
	}

	public VirtualStorageResourceInfo reservationId(final String reservationId) {
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

	public VirtualStorageResourceInfo metadata(final KeyValuePairs metadata) {
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
		final VirtualStorageResourceInfo virtualStorageResourceInfo = (VirtualStorageResourceInfo) o;
		return Objects.equals(this.id, virtualStorageResourceInfo.id) &&
				Objects.equals(this.virtualStorageDescId, virtualStorageResourceInfo.virtualStorageDescId) &&
				Objects.equals(this.storageResource, virtualStorageResourceInfo.storageResource) &&
				Objects.equals(this.reservationId, virtualStorageResourceInfo.reservationId) &&
				Objects.equals(this.metadata, virtualStorageResourceInfo.metadata);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, virtualStorageDescId, storageResource, reservationId, metadata);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VirtualStorageResourceInfo {\n");

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
	private String toIndentedString(final java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
