package io.swagger.model;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * This type represents request parameters for the \&quot;Operate VNF\&quot; operation.
 **/
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "This type represents request parameters for the \"Operate VNF\" operation. ")

public class OperateVnfRequest {

	public enum ChangeStateToEnum {

		STARTED(String.valueOf("STARTED")), STOPPED(String.valueOf("STOPPED"));

		private final String value;

		ChangeStateToEnum(String v) {
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
		public static ChangeStateToEnum fromValue(String v) {
			for (final ChangeStateToEnum b : ChangeStateToEnum.values()) {
				if (String.valueOf(b.value).equals(v)) {
					return b;
				}
			}
			return null;
		}
	}

	private @Valid ChangeStateToEnum changeStateTo = null;

	public enum StopTypeEnum {

		FORCEFUL(String.valueOf("FORCEFUL")), GRACEFUL(String.valueOf("GRACEFUL"));

		private final String value;

		StopTypeEnum(String v) {
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
		public static StopTypeEnum fromValue(String v) {
			for (final StopTypeEnum b : StopTypeEnum.values()) {
				if (String.valueOf(b.value).equals(v)) {
					return b;
				}
			}
			return null;
		}
	}

	private @Valid StopTypeEnum stopType = null;
	private @Valid String vnfcInstanceId = null;
	private @Valid Object additionalParams = null;

	/**
	 **/
	public OperateVnfRequest changeStateTo(ChangeStateToEnum changeStateTo) {
		this.changeStateTo = changeStateTo;
		return this;
	}

	@ApiModelProperty(required = true, value = "")
	@JsonProperty("changeStateTo")
	@NotNull
	public ChangeStateToEnum getChangeStateTo() {
		return changeStateTo;
	}

	public void setChangeStateTo(ChangeStateToEnum changeStateTo) {
		this.changeStateTo = changeStateTo;
	}

	/**
	 * * FORCEFUL: The VNFM will stop the VNF immediately after accepting the
	 * request. * GRACEFUL: The VNFM will first arrange to take the VNF out of
	 * service after accepting the request. Once that operation is successful or
	 * once the timer value specified in the \&quot;gracefulStopTimeout\&quot;
	 * attribute expires, the VNFM will stop the VNF.
	 **/
	public OperateVnfRequest stopType(StopTypeEnum stopType) {
		this.stopType = stopType;
		return this;
	}

	@ApiModelProperty(value = "* FORCEFUL: The VNFM will stop the VNF immediately after accepting the   request. * GRACEFUL: The VNFM will first arrange to take the VNF out of service   after accepting the request. Once that operation is successful or once   the timer value specified in the \"gracefulStopTimeout\" attribute   expires, the VNFM will stop the VNF. ")
	@JsonProperty("stopType")
	public StopTypeEnum getStopType() {
		return stopType;
	}

	public void setStopType(StopTypeEnum stopType) {
		this.stopType = stopType;
	}

	/**
	 * TBD
	 **/
	public OperateVnfRequest vnfcInstanceId(String vnfcInstanceId) {
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
	 * This type represents a list of key-value pairs. The order of the pairs in the
	 * list is not significant. In JSON, a set of key- value pairs is represented as
	 * an object. It shall comply with the provisions defined in clause 4 of IETF
	 * RFC 7159.
	 **/
	public OperateVnfRequest additionalParams(Object additionalParams) {
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
		final OperateVnfRequest operateVnfRequest = (OperateVnfRequest) o;
		return Objects.equals(changeStateTo, operateVnfRequest.changeStateTo) &&
				Objects.equals(stopType, operateVnfRequest.stopType) &&
				Objects.equals(vnfcInstanceId, operateVnfRequest.vnfcInstanceId) &&
				Objects.equals(additionalParams, operateVnfRequest.additionalParams);
	}

	@Override
	public int hashCode() {
		return Objects.hash(changeStateTo, stopType, vnfcInstanceId, additionalParams);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class OperateVnfRequest {\n");

		sb.append("    changeStateTo: ").append(toIndentedString(changeStateTo)).append("\n");
		sb.append("    stopType: ").append(toIndentedString(stopType)).append("\n");
		sb.append("    vnfcInstanceId: ").append(toIndentedString(vnfcInstanceId)).append("\n");
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
