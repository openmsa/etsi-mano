package com.ubiqube.etsi.mano.nfvo.v261.model.nslcm;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents the target NS Scale level for each NS scaling aspect of
 * the current deployment flavor.
 */
@ApiModel(description = "This type represents the target NS Scale level for each NS scaling aspect of the current deployment flavor. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-07T10:02:43.347+02:00")

public class NsScaleInfo {
	@JsonProperty("nsScalingAspectId")
	private String nsScalingAspectId = null;

	@JsonProperty("nsScaleLevelId")
	private String nsScaleLevelId = null;

	public NsScaleInfo nsScalingAspectId(final String nsScalingAspectId) {
		this.nsScalingAspectId = nsScalingAspectId;
		return this;
	}

	/**
	 * Identifier of the NS scaling aspect.
	 * 
	 * @return nsScalingAspectId
	 **/
	@ApiModelProperty(required = true, value = "Identifier of the NS scaling aspect. ")
	@NotNull

	public String getNsScalingAspectId() {
		return nsScalingAspectId;
	}

	public void setNsScalingAspectId(final String nsScalingAspectId) {
		this.nsScalingAspectId = nsScalingAspectId;
	}

	public NsScaleInfo nsScaleLevelId(final String nsScaleLevelId) {
		this.nsScaleLevelId = nsScaleLevelId;
		return this;
	}

	/**
	 * Identifier of the NS scale level.
	 * 
	 * @return nsScaleLevelId
	 **/
	@ApiModelProperty(required = true, value = "Identifier of the NS scale level. ")
	@NotNull

	public String getNsScaleLevelId() {
		return nsScaleLevelId;
	}

	public void setNsScaleLevelId(final String nsScaleLevelId) {
		this.nsScaleLevelId = nsScaleLevelId;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final NsScaleInfo nsScaleInfo = (NsScaleInfo) o;
		return Objects.equals(this.nsScalingAspectId, nsScaleInfo.nsScalingAspectId) &&
				Objects.equals(this.nsScaleLevelId, nsScaleInfo.nsScaleLevelId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nsScalingAspectId, nsScaleLevelId);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class NsScaleInfo {\n");

		sb.append("    nsScalingAspectId: ").append(toIndentedString(nsScalingAspectId)).append("\n");
		sb.append("    nsScaleLevelId: ").append(toIndentedString(nsScaleLevelId)).append("\n");
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
