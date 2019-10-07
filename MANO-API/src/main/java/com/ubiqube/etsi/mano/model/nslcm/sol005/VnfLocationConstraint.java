package com.ubiqube.etsi.mano.model.nslcm.sol005;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents the association of location constraints to a VNF
 * instance to be created according to a specific VNF profile. It shall comply
 * with the provisions defined in Table 6.5.3.20-1.
 */
@ApiModel(description = "This type represents the association of location constraints to a VNF instance to be created according to a specific VNF profile. It shall comply with the provisions defined in Table 6.5.3.20-1. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-07T10:02:43.347+02:00")

public class VnfLocationConstraint {
	@JsonProperty("vnfProfileId")
	private String vnfProfileId = null;

	@JsonProperty("locationConstraints")
	private LocationConstraints locationConstraints = null;

	public VnfLocationConstraint vnfProfileId(final String vnfProfileId) {
		this.vnfProfileId = vnfProfileId;
		return this;
	}

	/**
	 * Identifier of (reference to) a vnfProfile to which the additional parameters
	 * apply.
	 * 
	 * @return vnfProfileId
	 **/
	@ApiModelProperty(required = true, value = "Identifier of (reference to) a vnfProfile to which the additional parameters apply. ")
	@NotNull

	public String getVnfProfileId() {
		return vnfProfileId;
	}

	public void setVnfProfileId(final String vnfProfileId) {
		this.vnfProfileId = vnfProfileId;
	}

	public VnfLocationConstraint locationConstraints(final LocationConstraints locationConstraints) {
		this.locationConstraints = locationConstraints;
		return this;
	}

	/**
	 * Defines the location constraints for the VNF instance to be created based on
	 * the VNF profile.
	 * 
	 * @return locationConstraints
	 **/
	@ApiModelProperty(value = "Defines the location constraints for the VNF instance to be created based on the VNF profile. ")

	@Valid

	public LocationConstraints getLocationConstraints() {
		return locationConstraints;
	}

	public void setLocationConstraints(final LocationConstraints locationConstraints) {
		this.locationConstraints = locationConstraints;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final VnfLocationConstraint vnfLocationConstraint = (VnfLocationConstraint) o;
		return Objects.equals(this.vnfProfileId, vnfLocationConstraint.vnfProfileId) &&
				Objects.equals(this.locationConstraints, vnfLocationConstraint.locationConstraints);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vnfProfileId, locationConstraints);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfLocationConstraint {\n");

		sb.append("    vnfProfileId: ").append(toIndentedString(vnfProfileId)).append("\n");
		sb.append("    locationConstraints: ").append(toIndentedString(locationConstraints)).append("\n");
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
