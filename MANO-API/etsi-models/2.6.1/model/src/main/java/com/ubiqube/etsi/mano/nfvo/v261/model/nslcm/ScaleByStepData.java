package com.ubiqube.etsi.mano.nfvo.v261.model.nslcm;

import java.util.Map;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type describes the information to scale a VNF instance by steps. The
 * NFVO shall then invoke the Scale VNF operation towards the appropriate VNFM.
 */
@ApiModel(description = "This type describes the information to scale a VNF instance by steps.  The NFVO shall then invoke the Scale VNF operation towards the appropriate VNFM. ")
@Validated


public class ScaleByStepData {
	@JsonProperty("aspectId")
	private String aspectId = null;

	@JsonProperty("numberOfSteps")
	private Integer numberOfSteps = null;

	@JsonProperty("additionalParams")
	private Map<String, String> additionalParams = null;

	public ScaleByStepData aspectId(final String aspectId) {
		this.aspectId = aspectId;
		return this;
	}

	/**
	 * Identifier of (reference to) the aspect of the VNF that is requested to be
	 * scaled, as declared in the VNFD.
	 *
	 * @return aspectId
	 **/
	@ApiModelProperty(required = true, value = "Identifier of (reference to) the aspect of the VNF that is requested to be scaled, as declared in the VNFD. ")
	@NotNull

	public String getAspectId() {
		return aspectId;
	}

	public void setAspectId(final String aspectId) {
		this.aspectId = aspectId;
	}

	public ScaleByStepData numberOfSteps(final Integer numberOfSteps) {
		this.numberOfSteps = numberOfSteps;
		return this;
	}

	/**
	 * Number of scaling steps. It shall be a positive number. Defaults to 1. The
	 * VNF provider defines in the VNFD whether or not a particular VNF supports
	 * performing more than one step at a time. Such a property in the VNFD applies
	 * for all instances of a particular VNF.
	 *
	 * @return numberOfSteps
	 **/
	@ApiModelProperty(value = "Number of scaling steps. It shall be a positive number. Defaults to 1. The VNF provider defines in the VNFD whether or not a particular VNF supports performing more than one step at a time. Such a property in the VNFD applies for all instances of a particular VNF. ")

	public Integer getNumberOfSteps() {
		return numberOfSteps;
	}

	public void setNumberOfSteps(final Integer numberOfSteps) {
		this.numberOfSteps = numberOfSteps;
	}

	public ScaleByStepData additionalParams(final Map<String, String> additionalParams) {
		this.additionalParams = additionalParams;
		return this;
	}

	/**
	 * Additional parameters passed by the NFVO as input to the scaling process,
	 * specific to the VNF instance being scaled.
	 *
	 * @return additionalParams
	 **/
	@ApiModelProperty(value = "Additional parameters passed by the NFVO as input to the scaling process, specific to the VNF instance being scaled. ")

	@Valid

	public Map<String, String> getAdditionalParams() {
		return additionalParams;
	}

	public void setAdditionalParams(final Map<String, String> additionalParams) {
		this.additionalParams = additionalParams;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final ScaleByStepData scaleByStepData = (ScaleByStepData) o;
		return Objects.equals(this.aspectId, scaleByStepData.aspectId) &&
				Objects.equals(this.numberOfSteps, scaleByStepData.numberOfSteps) &&
				Objects.equals(this.additionalParams, scaleByStepData.additionalParams);
	}

	@Override
	public int hashCode() {
		return Objects.hash(aspectId, numberOfSteps, additionalParams);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class ScaleByStepData {\n");

		sb.append("    aspectId: ").append(toIndentedString(aspectId)).append("\n");
		sb.append("    numberOfSteps: ").append(toIndentedString(numberOfSteps)).append("\n");
		sb.append("    additionalParams: ").append(toIndentedString(additionalParams)).append("\n");
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
