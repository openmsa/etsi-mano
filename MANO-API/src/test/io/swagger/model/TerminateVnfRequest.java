package io.swagger.model;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;

public class TerminateVnfRequest {

	public enum TerminationTypeEnum {

		FORCEFUL(String.valueOf("FORCEFUL"));

		private final String value;

		TerminationTypeEnum(String v) {
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
		public static TerminationTypeEnum fromValue(String v) {
			for (final TerminationTypeEnum b : TerminationTypeEnum.values()) {
				if (String.valueOf(b.value).equals(v)) {
					return b;
				}
			}
			return null;
		}
	}

	private @Valid TerminationTypeEnum terminationType = null;
	private @Valid Object additionalParams = null;

	/**
	 * Indicates the type of termination is requested. Permitted values: * FORCEFUL:
	 * The VNFM will shut down the VNF and release the resources immediately after
	 * accepting the request.
	 **/
	public TerminateVnfRequest terminationType(TerminationTypeEnum terminationType) {
		this.terminationType = terminationType;
		return this;
	}

	@ApiModelProperty(required = true, value = "Indicates the type of termination is requested. Permitted values: * FORCEFUL: The VNFM will shut down the VNF and release the   resources immediately after accepting the request. ")
	@JsonProperty("terminationType")
	@NotNull
	public TerminationTypeEnum getTerminationType() {
		return terminationType;
	}

	public void setTerminationType(TerminationTypeEnum terminationType) {
		this.terminationType = terminationType;
	}

	/**
	 * This type represents a list of key-value pairs. The order of the pairs in the
	 * list is not significant. In JSON, a set of key- value pairs is represented as
	 * an object. It shall comply with the provisions defined in clause 4 of IETF
	 * RFC 7159.
	 **/
	public TerminateVnfRequest additionalParams(Object additionalParams) {
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
		final TerminateVnfRequest terminateVnfRequest = (TerminateVnfRequest) o;
		return Objects.equals(terminationType, terminateVnfRequest.terminationType) &&
				Objects.equals(additionalParams, terminateVnfRequest.additionalParams);
	}

	@Override
	public int hashCode() {
		return Objects.hash(terminationType, additionalParams);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class TerminateVnfRequest {\n");

		sb.append("    terminationType: ").append(toIndentedString(terminationType)).append("\n");
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
