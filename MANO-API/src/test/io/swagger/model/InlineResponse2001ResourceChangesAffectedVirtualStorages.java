package io.swagger.model;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * This type provides information about added, deleted, modified and temporary virtual storage resources.
 **/
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "This type provides information about added, deleted, modified and temporary virtual storage resources. ")

public class InlineResponse2001ResourceChangesAffectedVirtualStorages {

	private @Valid String id = null;
	private @Valid String virtualStorageDescId = null;

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
	private @Valid VnfInstancesInstantiatedVnfInfoResourceHandle storageResource = null;
	private @Valid Object metadata = null;

	/**
	 * An identifier that is unique for the respective type within a VNF instance,
	 * but may not be globally unique.
	 **/
	public InlineResponse2001ResourceChangesAffectedVirtualStorages id(String id) {
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
	public InlineResponse2001ResourceChangesAffectedVirtualStorages virtualStorageDescId(String virtualStorageDescId) {
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
	 * Signals the type of change. Permitted values: * ADDED * REMOVED * MODIFIED *
	 * TEMPORARY For a temporary resource, an AffectedVirtualStorage structure
	 * exists as long as the temporary resource exists.
	 **/
	public InlineResponse2001ResourceChangesAffectedVirtualStorages changeType(ChangeTypeEnum changeType) {
		this.changeType = changeType;
		return this;
	}

	@ApiModelProperty(required = true, value = "Signals the type of change. Permitted values: * ADDED * REMOVED * MODIFIED * TEMPORARY For a temporary resource, an AffectedVirtualStorage structure exists as long as the temporary resource exists. ")
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
	public InlineResponse2001ResourceChangesAffectedVirtualStorages storageResource(VnfInstancesInstantiatedVnfInfoResourceHandle storageResource) {
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
	 * This type represents a list of key-value pairs. The order of the pairs in the
	 * list is not significant. In JSON, a set of key- value pairs is represented as
	 * an object. It shall comply with the provisions defined in clause 4 of IETF
	 * RFC 7159.
	 **/
	public InlineResponse2001ResourceChangesAffectedVirtualStorages metadata(Object metadata) {
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
		final InlineResponse2001ResourceChangesAffectedVirtualStorages inlineResponse2001ResourceChangesAffectedVirtualStorages = (InlineResponse2001ResourceChangesAffectedVirtualStorages) o;
		return Objects.equals(id, inlineResponse2001ResourceChangesAffectedVirtualStorages.id) &&
				Objects.equals(virtualStorageDescId, inlineResponse2001ResourceChangesAffectedVirtualStorages.virtualStorageDescId) &&
				Objects.equals(changeType, inlineResponse2001ResourceChangesAffectedVirtualStorages.changeType) &&
				Objects.equals(storageResource, inlineResponse2001ResourceChangesAffectedVirtualStorages.storageResource) &&
				Objects.equals(metadata, inlineResponse2001ResourceChangesAffectedVirtualStorages.metadata);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, virtualStorageDescId, changeType, storageResource, metadata);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class InlineResponse2001ResourceChangesAffectedVirtualStorages {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    virtualStorageDescId: ").append(toIndentedString(virtualStorageDescId)).append("\n");
		sb.append("    changeType: ").append(toIndentedString(changeType)).append("\n");
		sb.append("    storageResource: ").append(toIndentedString(storageResource)).append("\n");
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
