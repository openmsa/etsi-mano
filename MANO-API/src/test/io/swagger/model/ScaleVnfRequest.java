package io.swagger.model;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;

public class ScaleVnfRequest {

	public enum TypeEnum {

		OUT(String.valueOf("SCALE_OUT")), IN(String.valueOf("SCALE_IN"));

		private final String value;

		TypeEnum(String v) {
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
		public static TypeEnum fromValue(String v) {
			for (final TypeEnum b : TypeEnum.values()) {
				if (String.valueOf(b.value).equals(v)) {
					return b;
				}
			}
			return null;
		}
	}

	private @Valid TypeEnum type = null;
	private @Valid String aspectId = null;
	private @Valid Integer numberOfSteps = null;
	private @Valid Object additionalParams = null;

	/**
	 * Indicates the type of the scale operation requested. Permitted values: *
	 * SCALE_OUT: adding additional VNFC instances to the VNF to increase capacity *
	 * SCALE_IN: removing VNFC instances from the VNF in order to release unused
	 * capacity.
	 **/
	public ScaleVnfRequest type(TypeEnum type) {
		this.type = type;
		return this;
	}

	@ApiModelProperty(required = true, value = "Indicates the type of the scale operation requested. Permitted values: * SCALE_OUT: adding additional VNFC instances to the VNF to increase   capacity * SCALE_IN: removing VNFC instances from the VNF in order to release   unused capacity. ")
	@JsonProperty("type")
	@NotNull
	public TypeEnum getType() {
		return type;
	}

	public void setType(TypeEnum type) {
		this.type = type;
	}

	/**
	 * An identifier that is unique within a VNF descriptor.
	 **/
	public ScaleVnfRequest aspectId(String aspectId) {
		this.aspectId = aspectId;
		return this;
	}

	@ApiModelProperty(required = true, value = "An identifier that is unique within a VNF descriptor. ")
	@JsonProperty("aspectId")
	@NotNull
	public String getAspectId() {
		return aspectId;
	}

	public void setAspectId(String aspectId) {
		this.aspectId = aspectId;
	}

	/**
	 * Number of scaling steps to be executed as part of this Scale VNF operation.
	 * It shall be a positive number and the default value shall be 1.
	 **/
	public ScaleVnfRequest numberOfSteps(Integer numberOfSteps) {
		this.numberOfSteps = numberOfSteps;
		return this;
	}

	@ApiModelProperty(value = "Number of scaling steps to be executed as part of this Scale VNF operation. It shall be a positive number and the default value shall be 1. ")
	@JsonProperty("numberOfSteps")
	public Integer getNumberOfSteps() {
		return numberOfSteps;
	}

	public void setNumberOfSteps(Integer numberOfSteps) {
		this.numberOfSteps = numberOfSteps;
	}

	/**
	 * This type represents a list of key-value pairs. The order of the pairs in the
	 * list is not significant. In JSON, a set of key- value pairs is represented as
	 * an object. It shall comply with the provisions defined in clause 4 of IETF
	 * RFC 7159.
	 **/
	public ScaleVnfRequest additionalParams(Object additionalParams) {
		this.additionalParams = additionalParams;
		return this;
	}

	@ApiModelProperty(value = "This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  ")
	@JsonProperty("additionalParams")
	public Object getAdditionalParams() {
		return additionalParams;
	}

	public void setAdditionalParams(Object additionalParams) {
		this.additionalParams = additionalParams;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final ScaleVnfRequest scaleVnfRequest = (ScaleVnfRequest) o;
		return Objects.equals(type, scaleVnfRequest.type) &&
				Objects.equals(aspectId, scaleVnfRequest.aspectId) &&
				Objects.equals(numberOfSteps, scaleVnfRequest.numberOfSteps) &&
				Objects.equals(additionalParams, scaleVnfRequest.additionalParams);
	}

	@Override
	public int hashCode() {
		return Objects.hash(type, aspectId, numberOfSteps, additionalParams);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class ScaleVnfRequest {\n");

		sb.append("    type: ").append(toIndentedString(type)).append("\n");
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
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
