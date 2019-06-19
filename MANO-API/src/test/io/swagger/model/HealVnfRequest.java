package io.swagger.model;

import java.util.Objects;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class HealVnfRequest {

	private @Valid String cause = null;
	private @Valid String vnfcInstanceId = null;
	private @Valid String healScript = null;
	private @Valid Object additionalParams = null;

	/**
	 * Indicates the reason why a healing procedure is required.
	 **/
	public HealVnfRequest cause(String cause) {
		this.cause = cause;
		return this;
	}

	@ApiModelProperty(value = "Indicates the reason why a healing procedure is required. ")
	@JsonProperty("cause")
	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	/**
	 * TBD
	 **/
	public HealVnfRequest vnfcInstanceId(String vnfcInstanceId) {
		this.vnfcInstanceId = vnfcInstanceId;
		return this;
	}

	@ApiModelProperty(value = "TBD")
	@JsonProperty("vnfcInstanceId")
	public String getVnfcInstanceId() {
		return vnfcInstanceId;
	}

	public void setVnfcInstanceId(String vnfcInstanceId) {
		this.vnfcInstanceId = vnfcInstanceId;
	}

	/**
	 * TBD
	 **/
	public HealVnfRequest healScript(String healScript) {
		this.healScript = healScript;
		return this;
	}

	@ApiModelProperty(value = "TBD")
	@JsonProperty("healScript")
	public String getHealScript() {
		return healScript;
	}

	public void setHealScript(String healScript) {
		this.healScript = healScript;
	}

	/**
	 * This type represents a list of key-value pairs. The order of the pairs in the
	 * list is not significant. In JSON, a set of key- value pairs is represented as
	 * an object. It shall comply with the provisions defined in clause 4 of IETF
	 * RFC 7159.
	 **/
	public HealVnfRequest additionalParams(Object additionalParams) {
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
		final HealVnfRequest healVnfRequest = (HealVnfRequest) o;
		return Objects.equals(cause, healVnfRequest.cause) &&
				Objects.equals(vnfcInstanceId, healVnfRequest.vnfcInstanceId) &&
				Objects.equals(healScript, healVnfRequest.healScript) &&
				Objects.equals(additionalParams, healVnfRequest.additionalParams);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cause, vnfcInstanceId, healScript, additionalParams);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class HealVnfRequest {\n");

		sb.append("    cause: ").append(toIndentedString(cause)).append("\n");
		sb.append("    vnfcInstanceId: ").append(toIndentedString(vnfcInstanceId)).append("\n");
		sb.append("    healScript: ").append(toIndentedString(healScript)).append("\n");
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
