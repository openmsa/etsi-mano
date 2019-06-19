package io.swagger.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * This type provides information about added, deleted, modified and temporary VNFCs.
 **/
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "This type provides information about added, deleted, modified and temporary VNFCs.   ")

public class InlineResponse2001ResourceChangesAffectedVnfcs {

	private @Valid String id = null;
	private @Valid String vduId = null;

	public enum ChangeTypeEnum {

		ADDED(String.valueOf("ADDED")), REMOVED(String.valueOf("REMOVED")), MODIFIED(String.valueOf("MODIFIED")), TEMPORARY(String.valueOf("TEMPORARY"));

		private final String value;

		ChangeTypeEnum(String v) {
			value = v;
		}

		public String value() {
			return value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static ChangeTypeEnum fromValue(String v) {
			for (final ChangeTypeEnum b : ChangeTypeEnum.values()) {
				if (String.valueOf(b.value).equals(v)) {
					return b;
				}
			}
			return null;
		}
	}

	private @Valid ChangeTypeEnum changeType = null;
	private @Valid VnfInstancesInstantiatedVnfInfoResourceHandle computeResource = null;
	private @Valid Object metadata = null;
	private @Valid List<String> affectedVnfcCpIds = new ArrayList<String>();
	private @Valid List<String> addedStorageResourceIds = new ArrayList<String>();
	private @Valid List<String> removedStorageResourceIds = new ArrayList<String>();

	/**
	 * An identifier that is unique for the respective type within a VNF instance,
	 * but may not be globally unique.
	 **/
	public InlineResponse2001ResourceChangesAffectedVnfcs id(String id) {
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
	public InlineResponse2001ResourceChangesAffectedVnfcs vduId(String vduId) {
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
	 * Signals the type of change. Permitted values: * ADDED * REMOVED * MODIFIED *
	 * TEMPORARY For a temporary resource, an AffectedVnfc structure exists as long
	 * as the temporary resource exists.
	 **/
	public InlineResponse2001ResourceChangesAffectedVnfcs changeType(ChangeTypeEnum changeType) {
		this.changeType = changeType;
		return this;
	}

	@ApiModelProperty(required = true, value = "Signals the type of change. Permitted values: * ADDED * REMOVED * MODIFIED * TEMPORARY For a temporary resource, an AffectedVnfc structure exists as long as the temporary resource exists. ")
	@JsonProperty("changeType")
	@NotNull
	public ChangeTypeEnum getChangeType() {
		return changeType;
	}

	public void setChangeType(ChangeTypeEnum changeType) {
		this.changeType = changeType;
	}

	/**
	 **/
	public InlineResponse2001ResourceChangesAffectedVnfcs computeResource(VnfInstancesInstantiatedVnfInfoResourceHandle computeResource) {
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
	 * This type represents a list of key-value pairs. The order of the pairs in the
	 * list is not significant. In JSON, a set of key- value pairs is represented as
	 * an object. It shall comply with the provisions defined in clause 4 of IETF
	 * RFC 7159.
	 **/
	public InlineResponse2001ResourceChangesAffectedVnfcs metadata(Object metadata) {
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

	/**
	 * Identifiers of CP(s) of the VNFC instance that were affected by the change.
	 * Shall be present for those affected CPs of the VNFC instance that are
	 * associated to an external CP of the VNF instance. May be present for further
	 * affected CPs of the VNFC instance.
	 **/
	public InlineResponse2001ResourceChangesAffectedVnfcs affectedVnfcCpIds(List<String> affectedVnfcCpIds) {
		this.affectedVnfcCpIds = affectedVnfcCpIds;
		return this;
	}

	@ApiModelProperty(value = "Identifiers of CP(s) of the VNFC instance that were affected by the change.  Shall be present for those affected CPs of the VNFC instance that are associated to an external CP of the VNF instance. May be present for further affected CPs of the VNFC instance. ")
	@JsonProperty("affectedVnfcCpIds")
	public List<String> getAffectedVnfcCpIds() {
		return affectedVnfcCpIds;
	}

	public void setAffectedVnfcCpIds(List<String> affectedVnfcCpIds) {
		this.affectedVnfcCpIds = affectedVnfcCpIds;
	}

	/**
	 * References to VirtualStorage resources that have been added. Each value
	 * refers to a VirtualStorageResourceInfo item in the VnfInstance that was added
	 * to the VNFC. It shall be provided if at least one storage resource was added
	 * to the VNFC.
	 **/
	public InlineResponse2001ResourceChangesAffectedVnfcs addedStorageResourceIds(List<String> addedStorageResourceIds) {
		this.addedStorageResourceIds = addedStorageResourceIds;
		return this;
	}

	@ApiModelProperty(value = "References to VirtualStorage resources that have been added. Each value refers to a VirtualStorageResourceInfo item in the VnfInstance that was added to the VNFC. It shall be provided if at least one storage resource was added to the VNFC. ")
	@JsonProperty("addedStorageResourceIds")
	public List<String> getAddedStorageResourceIds() {
		return addedStorageResourceIds;
	}

	public void setAddedStorageResourceIds(List<String> addedStorageResourceIds) {
		this.addedStorageResourceIds = addedStorageResourceIds;
	}

	/**
	 * References to VirtualStorage resources that have been removed. The value
	 * contains the identifier of a VirtualStorageResourceInfo item that has been
	 * removed from the VNFC, and might no longer exist in the VnfInstance. It shall
	 * be provided if at least one storage resource was removed from the VNFC.
	 **/
	public InlineResponse2001ResourceChangesAffectedVnfcs removedStorageResourceIds(List<String> removedStorageResourceIds) {
		this.removedStorageResourceIds = removedStorageResourceIds;
		return this;
	}

	@ApiModelProperty(value = "References to VirtualStorage resources that have been removed. The value contains the identifier of a VirtualStorageResourceInfo item that has been removed from the VNFC, and might no longer exist in the VnfInstance. It shall be provided if at least one storage resource was removed from the VNFC. ")
	@JsonProperty("removedStorageResourceIds")
	public List<String> getRemovedStorageResourceIds() {
		return removedStorageResourceIds;
	}

	public void setRemovedStorageResourceIds(List<String> removedStorageResourceIds) {
		this.removedStorageResourceIds = removedStorageResourceIds;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final InlineResponse2001ResourceChangesAffectedVnfcs inlineResponse2001ResourceChangesAffectedVnfcs = (InlineResponse2001ResourceChangesAffectedVnfcs) o;
		return Objects.equals(id, inlineResponse2001ResourceChangesAffectedVnfcs.id) &&
				Objects.equals(vduId, inlineResponse2001ResourceChangesAffectedVnfcs.vduId) &&
				Objects.equals(changeType, inlineResponse2001ResourceChangesAffectedVnfcs.changeType) &&
				Objects.equals(computeResource, inlineResponse2001ResourceChangesAffectedVnfcs.computeResource) &&
				Objects.equals(metadata, inlineResponse2001ResourceChangesAffectedVnfcs.metadata) &&
				Objects.equals(affectedVnfcCpIds, inlineResponse2001ResourceChangesAffectedVnfcs.affectedVnfcCpIds) &&
				Objects.equals(addedStorageResourceIds, inlineResponse2001ResourceChangesAffectedVnfcs.addedStorageResourceIds) &&
				Objects.equals(removedStorageResourceIds, inlineResponse2001ResourceChangesAffectedVnfcs.removedStorageResourceIds);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, vduId, changeType, computeResource, metadata, affectedVnfcCpIds, addedStorageResourceIds, removedStorageResourceIds);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class InlineResponse2001ResourceChangesAffectedVnfcs {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    vduId: ").append(toIndentedString(vduId)).append("\n");
		sb.append("    changeType: ").append(toIndentedString(changeType)).append("\n");
		sb.append("    computeResource: ").append(toIndentedString(computeResource)).append("\n");
		sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
		sb.append("    affectedVnfcCpIds: ").append(toIndentedString(affectedVnfcCpIds)).append("\n");
		sb.append("    addedStorageResourceIds: ").append(toIndentedString(addedStorageResourceIds)).append("\n");
		sb.append("    removedStorageResourceIds: ").append(toIndentedString(removedStorageResourceIds)).append("\n");
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
