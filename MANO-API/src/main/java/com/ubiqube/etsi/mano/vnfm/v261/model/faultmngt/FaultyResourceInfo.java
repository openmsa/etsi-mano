package com.ubiqube.etsi.mano.vnfm.v261.model.faultmngt;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.common.v261.model.ResourceHandle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents the faulty virtual resources that have a negative impact
 * on a VNF.
 */
@ApiModel(description = "This type represents the faulty virtual resources that have a negative impact on a VNF. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-05-01T16:03:26.781+02:00")

public class FaultyResourceInfo {
	@JsonProperty("faultyResource")
	private ResourceHandle faultyResource = null;

	@JsonProperty("faultyResourceType")
	private FaultyResourceType faultyResourceType = null;

	public FaultyResourceInfo faultyResource(final ResourceHandle faultyResource) {
		this.faultyResource = faultyResource;
		return this;
	}

	/**
	 * Information that identifies the faulty resource instance and its managing
	 * entity.
	 *
	 * @return faultyResource
	 **/
	@ApiModelProperty(required = true, value = "Information that identifies the faulty resource instance and its managing entity. ")
	@NotNull

	@Valid

	public ResourceHandle getFaultyResource() {
		return faultyResource;
	}

	public void setFaultyResource(final ResourceHandle faultyResource) {
		this.faultyResource = faultyResource;
	}

	public FaultyResourceInfo faultyResourceType(final FaultyResourceType faultyResourceType) {
		this.faultyResourceType = faultyResourceType;
		return this;
	}

	/**
	 * Type of the faulty resource.
	 *
	 * @return faultyResourceType
	 **/
	@ApiModelProperty(required = true, value = "Type of the faulty resource. ")
	@NotNull

	@Valid

	public FaultyResourceType getFaultyResourceType() {
		return faultyResourceType;
	}

	public void setFaultyResourceType(final FaultyResourceType faultyResourceType) {
		this.faultyResourceType = faultyResourceType;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final FaultyResourceInfo faultyResourceInfo = (FaultyResourceInfo) o;
		return Objects.equals(this.faultyResource, faultyResourceInfo.faultyResource) &&
				Objects.equals(this.faultyResourceType, faultyResourceInfo.faultyResourceType);
	}

	@Override
	public int hashCode() {
		return Objects.hash(faultyResource, faultyResourceType);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class FaultyResourceInfo {\n");

		sb.append("    faultyResource: ").append(toIndentedString(faultyResource)).append("\n");
		sb.append("    faultyResourceType: ").append(toIndentedString(faultyResourceType)).append("\n");
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
