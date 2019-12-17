package com.ubiqube.etsi.mano.model;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents an externally provided link port to be used to connect
 * an external connection point to an external VL.
 */
@ApiModel(description = "This type represents an externally provided link port to be used to connect an external connection point to an external VL. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-08-30T10:47:24.034+02:00")

public class ExtLinkPortData {
	@JsonProperty("id")
	private String id = null;

	@JsonProperty("resourceHandle")
	private ResourceHandle resourceHandle = null;

	public ExtLinkPortData id(final String _id) {
		this.id = _id;
		return this;
	}

	/**
	 * Identifier of this link port as provided by the entity that has created the
	 * link port.
	 *
	 * @return id
	 **/
	@ApiModelProperty(required = true, value = "Identifier of this link port as provided by the entity that has created the link port. ")
	@NotNull

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public ExtLinkPortData resourceHandle(final ResourceHandle _resourceHandle) {
		this.resourceHandle = _resourceHandle;
		return this;
	}

	/**
	 * Reference to the virtualised resource realizing this link port.
	 *
	 * @return resourceHandle
	 **/
	@ApiModelProperty(required = true, value = "Reference to the virtualised resource realizing this link port. ")
	@NotNull

	@Valid

	public ResourceHandle getResourceHandle() {
		return resourceHandle;
	}

	public void setResourceHandle(final ResourceHandle resourceHandle) {
		this.resourceHandle = resourceHandle;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final ExtLinkPortData extLinkPortData = (ExtLinkPortData) o;
		return Objects.equals(this.id, extLinkPortData.id) &&
				Objects.equals(this.resourceHandle, extLinkPortData.resourceHandle);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, resourceHandle);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class ExtLinkPortData {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    resourceHandle: ").append(toIndentedString(resourceHandle)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private static String toIndentedString(final java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
