package io.swagger.model;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * This type provides information about added, deleted, modified and temporary VLs.
 **/
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "This type provides information about added, deleted, modified and temporary VLs. ")

public class InlineResponse2001ResourceChangesAffectedVirtualLinks {

	private @Valid String id = null;
	private @Valid String virtualLinkDescId = null;

	public enum ChangeTypeEnum {

		ADDED(String.valueOf("ADDED")), REMOVED(String.valueOf("REMOVED")), MODIFIED(String.valueOf("MODIFIED")), TEMPORARY(String.valueOf("TEMPORARY")), LINK_PORT_ADDED(String.valueOf("LINK_PORT_ADDED")), LINK_PORT_REMOVED(String.valueOf("LINK_PORT_REMOVED"));

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
	private @Valid VnfInstancesInstantiatedVnfInfoResourceHandle networkResource = null;
	private @Valid Object metadata = null;

	/**
	 * An identifier that is unique for the respective type within a VNF instance,
	 * but may not be globally unique.
	 **/
	public InlineResponse2001ResourceChangesAffectedVirtualLinks id(String id) {
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
	public InlineResponse2001ResourceChangesAffectedVirtualLinks virtualLinkDescId(String virtualLinkDescId) {
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
	 * Signals the type of change. Permitted values: * ADDED * REMOVED * MODIFIED *
	 * TEMPORARY * LINK_PORT_ADDED * LINK_PORT_REMOVED For a temporary resource, an
	 * AffectedVirtualLink structure exists as long as the temporary resource
	 * exists.
	 **/
	public InlineResponse2001ResourceChangesAffectedVirtualLinks changeType(ChangeTypeEnum changeType) {
		this.changeType = changeType;
		return this;
	}

	@ApiModelProperty(required = true, value = "Signals the type of change. Permitted values: * ADDED * REMOVED * MODIFIED * TEMPORARY * LINK_PORT_ADDED * LINK_PORT_REMOVED For a temporary resource, an AffectedVirtualLink structure exists as long as the temporary resource exists. ")
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
	public InlineResponse2001ResourceChangesAffectedVirtualLinks networkResource(VnfInstancesInstantiatedVnfInfoResourceHandle networkResource) {
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
	 * This type represents a list of key-value pairs. The order of the pairs in the
	 * list is not significant. In JSON, a set of key- value pairs is represented as
	 * an object. It shall comply with the provisions defined in clause 4 of IETF
	 * RFC 7159.
	 **/
	public InlineResponse2001ResourceChangesAffectedVirtualLinks metadata(Object metadata) {
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
		final InlineResponse2001ResourceChangesAffectedVirtualLinks inlineResponse2001ResourceChangesAffectedVirtualLinks = (InlineResponse2001ResourceChangesAffectedVirtualLinks) o;
		return Objects.equals(id, inlineResponse2001ResourceChangesAffectedVirtualLinks.id) &&
				Objects.equals(virtualLinkDescId, inlineResponse2001ResourceChangesAffectedVirtualLinks.virtualLinkDescId) &&
				Objects.equals(changeType, inlineResponse2001ResourceChangesAffectedVirtualLinks.changeType) &&
				Objects.equals(networkResource, inlineResponse2001ResourceChangesAffectedVirtualLinks.networkResource) &&
				Objects.equals(metadata, inlineResponse2001ResourceChangesAffectedVirtualLinks.metadata);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, virtualLinkDescId, changeType, networkResource, metadata);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class InlineResponse2001ResourceChangesAffectedVirtualLinks {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    virtualLinkDescId: ").append(toIndentedString(virtualLinkDescId)).append("\n");
		sb.append("    changeType: ").append(toIndentedString(changeType)).append("\n");
		sb.append("    networkResource: ").append(toIndentedString(networkResource)).append("\n");
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
