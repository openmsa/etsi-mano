package com.ubiqube.etsi.mano.model.lcmgrant.sol003;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Links to resources related to this request.
 */
@ApiModel(description = "Links to resources related to this request. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-08-30T10:47:24.034+02:00")

public class GrantRequestLinks {
	@JsonProperty("vnfLcmOpOcc")
	private Link vnfLcmOpOcc = null;

	@JsonProperty("vnfInstance")
	private Link vnfInstance = null;

	public GrantRequestLinks vnfLcmOpOcc(final Link vnfLcmOpOcc) {
		this.vnfLcmOpOcc = vnfLcmOpOcc;
		return this;
	}

	/**
	 * Related lifecycle management operation occurrence.
	 * 
	 * @return vnfLcmOpOcc
	 **/
	@ApiModelProperty(required = true, value = "Related lifecycle management operation occurrence. ")
	@NotNull

	@Valid

	public Link getVnfLcmOpOcc() {
		return vnfLcmOpOcc;
	}

	public void setVnfLcmOpOcc(final Link vnfLcmOpOcc) {
		this.vnfLcmOpOcc = vnfLcmOpOcc;
	}

	public GrantRequestLinks vnfInstance(final Link vnfInstance) {
		this.vnfInstance = vnfInstance;
		return this;
	}

	/**
	 * Related VNF instance.
	 * 
	 * @return vnfInstance
	 **/
	@ApiModelProperty(required = true, value = "Related VNF instance. ")
	@NotNull

	@Valid

	public Link getVnfInstance() {
		return vnfInstance;
	}

	public void setVnfInstance(final Link vnfInstance) {
		this.vnfInstance = vnfInstance;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final GrantRequestLinks grantRequestLinks = (GrantRequestLinks) o;
		return Objects.equals(this.vnfLcmOpOcc, grantRequestLinks.vnfLcmOpOcc) &&
				Objects.equals(this.vnfInstance, grantRequestLinks.vnfInstance);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vnfLcmOpOcc, vnfInstance);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class GrantRequestLinks {\n");

		sb.append("    vnfLcmOpOcc: ").append(toIndentedString(vnfLcmOpOcc)).append("\n");
		sb.append("    vnfInstance: ").append(toIndentedString(vnfInstance)).append("\n");
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
