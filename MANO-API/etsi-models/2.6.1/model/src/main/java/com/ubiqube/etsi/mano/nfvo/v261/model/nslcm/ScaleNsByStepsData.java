package com.ubiqube.etsi.mano.nfvo.v261.model.nslcm;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents the information used to scale an NS instance by one or
 * more scaling steps, with respect to a particular NS scaling aspect.
 * Performing a scaling step means increasing/decreasing the capacity of an NS
 * instance in a discrete manner, i.e. moving from one NS scale level to
 * another. The NS scaling aspects and their corresponding NS scale levels
 * applicable to the NS instance are declared in the NSD.
 */
@ApiModel(description = "This type represents the information used to scale an NS instance by one or more scaling steps, with respect to a particular NS scaling aspect. Performing a scaling step means increasing/decreasing the capacity of an NS instance in a discrete manner, i.e. moving from one NS scale level to another. The NS scaling aspects and their corresponding NS scale levels applicable to the NS instance are declared in the NSD. ")
@Validated


public class ScaleNsByStepsData {
	/**
	 * The scaling direction. Possible values are: - SCALE_IN - SCALE_OUT.
	 */
	public enum ScalingDirectionEnum {
		IN("SCALE_IN"),

		OUT("SCALE_OUT");

		private final String value;

		ScalingDirectionEnum(final String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static ScalingDirectionEnum fromValue(final String text) {
			for (final ScalingDirectionEnum b : ScalingDirectionEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	@JsonProperty("scalingDirection")
	private ScalingDirectionEnum scalingDirection = null;

	@JsonProperty("aspectId")
	private String aspectId = null;

	@JsonProperty("numberOfSteps")
	private Integer numberOfSteps = null;

	public ScaleNsByStepsData scalingDirection(final ScalingDirectionEnum scalingDirection) {
		this.scalingDirection = scalingDirection;
		return this;
	}

	/**
	 * The scaling direction. Possible values are: - SCALE_IN - SCALE_OUT.
	 * 
	 * @return scalingDirection
	 **/
	@ApiModelProperty(required = true, value = "The scaling direction. Possible values are: - SCALE_IN - SCALE_OUT. ")
	@NotNull

	public ScalingDirectionEnum getScalingDirection() {
		return scalingDirection;
	}

	public void setScalingDirection(final ScalingDirectionEnum scalingDirection) {
		this.scalingDirection = scalingDirection;
	}

	public ScaleNsByStepsData aspectId(final String aspectId) {
		this.aspectId = aspectId;
		return this;
	}

	/**
	 * The aspect of the NS that is requested to be scaled, as declared in the NSD.
	 * 
	 * @return aspectId
	 **/
	@ApiModelProperty(required = true, value = "The aspect of the NS that is requested to be scaled, as declared in the NSD. ")
	@NotNull

	public String getAspectId() {
		return aspectId;
	}

	public void setAspectId(final String aspectId) {
		this.aspectId = aspectId;
	}

	public ScaleNsByStepsData numberOfSteps(final Integer numberOfSteps) {
		this.numberOfSteps = numberOfSteps;
		return this;
	}

	/**
	 * The number of scaling steps to be performed. Defaults to 1.
	 * 
	 * @return numberOfSteps
	 **/
	@ApiModelProperty(value = "The number of scaling steps to be performed. Defaults to 1. ")

	public Integer getNumberOfSteps() {
		return numberOfSteps;
	}

	public void setNumberOfSteps(final Integer numberOfSteps) {
		this.numberOfSteps = numberOfSteps;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final ScaleNsByStepsData scaleNsByStepsData = (ScaleNsByStepsData) o;
		return Objects.equals(this.scalingDirection, scaleNsByStepsData.scalingDirection) &&
				Objects.equals(this.aspectId, scaleNsByStepsData.aspectId) &&
				Objects.equals(this.numberOfSteps, scaleNsByStepsData.numberOfSteps);
	}

	@Override
	public int hashCode() {
		return Objects.hash(scalingDirection, aspectId, numberOfSteps);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class ScaleNsByStepsData {\n");

		sb.append("    scalingDirection: ").append(toIndentedString(scalingDirection)).append("\n");
		sb.append("    aspectId: ").append(toIndentedString(aspectId)).append("\n");
		sb.append("    numberOfSteps: ").append(toIndentedString(numberOfSteps)).append("\n");
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
