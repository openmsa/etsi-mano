package com.ubiqube.etsi.mano.vnfm.v261.model.nsperfo;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Details of a simple threshold. Shall be present if
 * thresholdType&#x3D;\&quot;SIMPLE\&quot;.
 */
@ApiModel(description = "Details of a simple threshold. Shall be present if thresholdType=\"SIMPLE\". ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-05-01T16:01:09.127+02:00")

public class ThresholdCriteriaSimpleThresholdDetails {
	@JsonProperty("thresholdValue")
	private Integer thresholdValue = null;

	@JsonProperty("hysteresis")
	private Integer hysteresis = null;

	public ThresholdCriteriaSimpleThresholdDetails thresholdValue(final Integer thresholdValue) {
		this.thresholdValue = thresholdValue;
		return this;
	}

	/**
	 * The threshold value. Shall be represented as a floating point number.
	 * 
	 * @return thresholdValue
	 **/
	@ApiModelProperty(required = true, value = "The threshold value. Shall be represented as a floating point number. ")
	@NotNull

	public Integer getThresholdValue() {
		return thresholdValue;
	}

	public void setThresholdValue(final Integer thresholdValue) {
		this.thresholdValue = thresholdValue;
	}

	public ThresholdCriteriaSimpleThresholdDetails hysteresis(final Integer hysteresis) {
		this.hysteresis = hysteresis;
		return this;
	}

	/**
	 * The hysteresis of the threshold. Shall be represented as a non-negative
	 * floating point number. A notification with crossing direction \"UP\" will be
	 * generated if the measured value reaches or exceeds \"thresholdValue\" +
	 * \"hysteresis\". A notification with crossing direction \"DOWN\" will be
	 * generated if the measured value reaches or undercuts \"thresholdValue\" -
	 * \"hysteresis\". The hysteresis is defined to prevent storms of threshold
	 * crossing notifications. When processing a request to create a threshold,
	 * implementations should enforce a suitable minimum value for this attribute
	 * (e.g. override the value or reject the request).
	 * 
	 * @return hysteresis
	 **/
	@ApiModelProperty(required = true, value = "The hysteresis of the threshold. Shall be represented as a non-negative floating point number. A notification with crossing direction \"UP\" will be generated if the measured value reaches or exceeds \"thresholdValue\" + \"hysteresis\". A notification with crossing direction \"DOWN\" will be generated if the measured value reaches or undercuts \"thresholdValue\" - \"hysteresis\". The hysteresis is defined to prevent storms of threshold crossing notifications. When processing a request to create a threshold, implementations should enforce a suitable minimum value for this attribute (e.g. override the value or reject the request). ")
	@NotNull

	public Integer getHysteresis() {
		return hysteresis;
	}

	public void setHysteresis(final Integer hysteresis) {
		this.hysteresis = hysteresis;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final ThresholdCriteriaSimpleThresholdDetails thresholdCriteriaSimpleThresholdDetails = (ThresholdCriteriaSimpleThresholdDetails) o;
		return Objects.equals(this.thresholdValue, thresholdCriteriaSimpleThresholdDetails.thresholdValue) &&
				Objects.equals(this.hysteresis, thresholdCriteriaSimpleThresholdDetails.hysteresis);
	}

	@Override
	public int hashCode() {
		return Objects.hash(thresholdValue, hysteresis);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class ThresholdCriteriaSimpleThresholdDetails {\n");

		sb.append("    thresholdValue: ").append(toIndentedString(thresholdValue)).append("\n");
		sb.append("    hysteresis: ").append(toIndentedString(hysteresis)).append("\n");
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
