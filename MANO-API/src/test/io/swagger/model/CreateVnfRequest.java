package io.swagger.model;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class CreateVnfRequest {

	private @Valid String vnfdId = null;
	private @Valid String vnfInstanceName = null;
	private @Valid String vnfInstanceDescription = null;

	/**
	 * An identifier with the intention of being globally unique.
	 **/
	public CreateVnfRequest vnfdId(String vnfdId) {
		this.vnfdId = vnfdId;
		return this;
	}

	@ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
	@JsonProperty("vnfdId")
	@NotNull
	public String getVnfdId() {
		return vnfdId;
	}

	public void setVnfdId(String vnfdId) {
		this.vnfdId = vnfdId;
	}

	/**
	 * Human-readable name of the VNF instance to be created.
	 **/
	public CreateVnfRequest vnfInstanceName(String vnfInstanceName) {
		this.vnfInstanceName = vnfInstanceName;
		return this;
	}

	@ApiModelProperty(value = "Human-readable name of the VNF instance to be created. ")
	@JsonProperty("vnfInstanceName")
	public String getVnfInstanceName() {
		return vnfInstanceName;
	}

	public void setVnfInstanceName(String vnfInstanceName) {
		this.vnfInstanceName = vnfInstanceName;
	}

	/**
	 * Human-readable description of the VNF instance to be created.
	 **/
	public CreateVnfRequest vnfInstanceDescription(String vnfInstanceDescription) {
		this.vnfInstanceDescription = vnfInstanceDescription;
		return this;
	}

	@ApiModelProperty(value = "Human-readable description of the VNF instance to be created. ")
	@JsonProperty("vnfInstanceDescription")
	public String getVnfInstanceDescription() {
		return vnfInstanceDescription;
	}

	public void setVnfInstanceDescription(String vnfInstanceDescription) {
		this.vnfInstanceDescription = vnfInstanceDescription;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final CreateVnfRequest createVnfRequest = (CreateVnfRequest) o;
		return Objects.equals(vnfdId, createVnfRequest.vnfdId) &&
				Objects.equals(vnfInstanceName, createVnfRequest.vnfInstanceName) &&
				Objects.equals(vnfInstanceDescription, createVnfRequest.vnfInstanceDescription);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vnfdId, vnfInstanceName, vnfInstanceDescription);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class CreateVnfRequest {\n");

		sb.append("    vnfdId: ").append(toIndentedString(vnfdId)).append("\n");
		sb.append("    vnfInstanceName: ").append(toIndentedString(vnfInstanceName)).append("\n");
		sb.append("    vnfInstanceDescription: ").append(toIndentedString(vnfInstanceDescription)).append("\n");
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
