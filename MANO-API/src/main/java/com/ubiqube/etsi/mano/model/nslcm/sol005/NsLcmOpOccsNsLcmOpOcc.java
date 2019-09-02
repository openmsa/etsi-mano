package com.ubiqube.etsi.mano.model.nslcm.sol005;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.model.ProblemDetails;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents a request a NS lifecycle operation occurrence. It shall
 * comply with the provisions defined in Table 6.5.2.3-1.
 **/
@ApiModel(description = "This type represents a request a NS lifecycle operation occurrence.  It shall comply with the provisions defined in Table 6.5.2.3-1. ")
public class NsLcmOpOccsNsLcmOpOcc {

	@ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
	/**
	 * An identifier with the intention of being globally unique.
	 **/
	private String id = null;

	@XmlType(name = "OperationStateEnum")
	@XmlEnum(String.class)
	public enum OperationStateEnum {

		@XmlEnumValue("PROCESSING")
		PROCESSING(String.valueOf("PROCESSING")), @XmlEnumValue("COMPLETED")
		COMPLETED(String.valueOf("COMPLETED")), @XmlEnumValue("FAILED_TEMP")
		FAILED_TEMP(String.valueOf("FAILED_TEMP")), @XmlEnumValue("FAILED")
		FAILED(String.valueOf("FAILED")), @XmlEnumValue("ROLLING_BACK")
		ROLLING_BACK(String.valueOf("ROLLING_BACK")), @XmlEnumValue("ROLLED_BACK")
		ROLLED_BACK(String.valueOf("ROLLED_BACK"));

		private final String value;

		OperationStateEnum(final String v) {
			value = v;
		}

		public String value() {
			return value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		public static OperationStateEnum fromValue(final String v) {
			for (final OperationStateEnum b : OperationStateEnum.values()) {
				if (String.valueOf(b.value).equals(v)) {
					return b;
				}
			}
			return null;
		}
	}

	@ApiModelProperty(required = true, value = "The enumeration NsLcmOperationStateType shall comply with the provisions defined in Table 6.5.4.4-1. Value | Description ------|------------ PROCESSING | The LCM operation is currently in execution. COMPLETED | The LCM operation has been completed successfully. PARTIALLY_COMPLETED | The LCM operation has been partially completed with accepTable errors. FAILED_TEMP | The LCM operation has failed and execution has stopped, but the execution of the operation is not considered to be closed. FAILED | The LCM operation has failed and it cannot be retried or rolled back, as it is determined that such action won't succeed. OLLING_BACK | The LCM operation is currently being rolled back. ROLLED_BACK | The LCM operation has been successfully rolled back, i.e. The state of the VNF prior to the original operation invocation has been restored as closely as possible. ")
	/**
	 * The enumeration NsLcmOperationStateType shall comply with the provisions
	 * defined in Table 6.5.4.4-1. Value | Description ------|------------
	 * PROCESSING | The LCM operation is currently in execution. COMPLETED | The LCM
	 * operation has been completed successfully. PARTIALLY_COMPLETED | The LCM
	 * operation has been partially completed with accepTable errors. FAILED_TEMP |
	 * The LCM operation has failed and execution has stopped, but the execution of
	 * the operation is not considered to be closed. FAILED | The LCM operation has
	 * failed and it cannot be retried or rolled back, as it is determined that such
	 * action won't succeed. OLLING_BACK | The LCM operation is currently being
	 * rolled back. ROLLED_BACK | The LCM operation has been successfully rolled
	 * back, i.e. The state of the VNF prior to the original operation invocation
	 * has been restored as closely as possible.
	 **/
	private OperationStateEnum operationState = null;

	@ApiModelProperty(required = true, value = "Date-time when the current state was entered. ")
	/**
	 * Date-time when the current state was entered.
	 **/
	private Date stateEnteredTime = null;

	@ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
	/**
	 * An identifier with the intention of being globally unique.
	 **/
	private String nsInstanceId = null;

	@XmlType(name = "LcmOperationTypeEnum")
	@XmlEnum(String.class)
	public enum LcmOperationTypeEnum {

		@XmlEnumValue("INSTANTIATE")
		INSTANTIATE(String.valueOf("INSTANTIATE")), @XmlEnumValue("SCALE")
		SCALE(String.valueOf("SCALE")), @XmlEnumValue("UPDATE")
		UPDATE(String.valueOf("UPDATE")), @XmlEnumValue("TERMINATE")
		TERMINATE(String.valueOf("TERMINATE")), @XmlEnumValue("HEAL")
		HEAL(String.valueOf("HEAL"));

		private final String value;

		LcmOperationTypeEnum(final String v) {
			value = v;
		}

		public String value() {
			return value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		public static LcmOperationTypeEnum fromValue(final String v) {
			for (final LcmOperationTypeEnum b : LcmOperationTypeEnum.values()) {
				if (String.valueOf(b.value).equals(v)) {
					return b;
				}
			}
			return null;
		}
	}

	@ApiModelProperty(required = true, value = "The enumeration NsLcmOpType represents those lifecycle operations that trigger a NS lifecycle management operation occurrence notification. Value | Description ------|------------ INSTANTIATE | Represents the \"Instantiate NS\" LCM operation. SCALE | Represents the \"Scale NS\" LCM operation. UPDATE | Represents the \"Update NS\" LCM operation. TERMINATE | Represents the \"Terminate NS\" LCM operation. HEAL | Represents the \"Heal NS\" LCM operation. ")
	/**
	 * The enumeration NsLcmOpType represents those lifecycle operations that
	 * trigger a NS lifecycle management operation occurrence notification. Value |
	 * Description ------|------------ INSTANTIATE | Represents the \"Instantiate
	 * NS\" LCM operation. SCALE | Represents the \"Scale NS\" LCM operation. UPDATE
	 * | Represents the \"Update NS\" LCM operation. TERMINATE | Represents the
	 * \"Terminate NS\" LCM operation. HEAL | Represents the \"Heal NS\" LCM
	 * operation.
	 **/
	private LcmOperationTypeEnum lcmOperationType = null;

	@ApiModelProperty(required = true, value = "Date-time of the start of the operation. ")
	/**
	 * Date-time of the start of the operation.
	 **/
	private Date startTime = null;

	@ApiModelProperty(required = true, value = "Set to true if this NS LCM operation occurrence has been automatically triggered by the NFVO. This occurs in the case of auto-scaling, auto-healing and when a nested NS is modified as a result of an operation on its composite NS. Set to false otherwise. ")
	/**
	 * Set to true if this NS LCM operation occurrence has been automatically
	 * triggered by the NFVO. This occurs in the case of auto-scaling, auto-healing
	 * and when a nested NS is modified as a result of an operation on its composite
	 * NS. Set to false otherwise.
	 **/
	private Boolean isAutomaticInvocation = null;

	@XmlType(name = "OperationParamsEnum")
	@XmlEnum(String.class)
	public enum OperationParamsEnum {

		@XmlEnumValue("INSTANTIATE")
		INSTANTIATE(String.valueOf("INSTANTIATE")), @XmlEnumValue("SCALE")
		SCALE(String.valueOf("SCALE")), @XmlEnumValue("UPDATE")
		UPDATE(String.valueOf("UPDATE")), @XmlEnumValue("HEAL")
		HEAL(String.valueOf("HEAL")), @XmlEnumValue("TERMINATE")
		TERMINATE(String.valueOf("TERMINATE"));

		private final String value;

		OperationParamsEnum(final String v) {
			value = v;
		}

		public String value() {
			return value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		public static OperationParamsEnum fromValue(final String v) {
			for (final OperationParamsEnum b : OperationParamsEnum.values()) {
				if (String.valueOf(b.value).equals(v)) {
					return b;
				}
			}
			return null;
		}
	}

	@ApiModelProperty(required = true, value = "Input parameters of the LCM operation. This attribute shall be formatted according to the request data type of the related LCM operation. The following mapping between lcmOperationType and the data type of this attribute shall apply: - INSTANTIATE: InstantiateNsRequest - SCALE: ScaleNsRequest - UPDATE: UpdateNsRequest - HEAL: HealNsRequest - TERMINATE: TerminateNsRequest ")
	/**
	 * Input parameters of the LCM operation. This attribute shall be formatted
	 * according to the request data type of the related LCM operation. The
	 * following mapping between lcmOperationType and the data type of this
	 * attribute shall apply: - INSTANTIATE: InstantiateNsRequest - SCALE:
	 * ScaleNsRequest - UPDATE: UpdateNsRequest - HEAL: HealNsRequest - TERMINATE:
	 * TerminateNsRequest
	 **/
	private OperationParamsEnum operationParams = null;

	@ApiModelProperty(required = true, value = "If the LCM operation occurrence is in \"PROCESSING\" or \"ROLLING_BACK\" state and the operation is being cancelled, this attribute shall be set to true. Otherwise, it shall be set to false. ")
	/**
	 * If the LCM operation occurrence is in \"PROCESSING\" or \"ROLLING_BACK\"
	 * state and the operation is being cancelled, this attribute shall be set to
	 * true. Otherwise, it shall be set to false.
	 **/
	private Boolean isCancelPending = null;

	@XmlType(name = "CancelModeEnum")
	@XmlEnum(String.class)
	public enum CancelModeEnum {

		@XmlEnumValue("GRACEFUL")
		GRACEFUL(String.valueOf("GRACEFUL")), @XmlEnumValue("FORCEFUL")
		FORCEFUL(String.valueOf("FORCEFUL"));

		private final String value;

		CancelModeEnum(final String v) {
			value = v;
		}

		public String value() {
			return value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		public static CancelModeEnum fromValue(final String v) {
			for (final CancelModeEnum b : CancelModeEnum.values()) {
				if (String.valueOf(b.value).equals(v)) {
					return b;
				}
			}
			return null;
		}
	}

	@ApiModelProperty(value = "Cancellation mode. The NFVO shall not start any new VNF lifecycle management and resource management operation, and shall wait for the ongoing VNF lifecycle management and resource management operations in the underlying system, typically the VNFM and VIM, to finish execution or to time out. After that, the NFVO shall put the operation occurrence into the FAILED_TEMP state. The NFVO shall not start any new VNF lifecycle management and resource management operation, shall cancel the ongoing VNF lifecycle management and resource management operations in the underlying system, typically the VNFM and VIM, and shall wait for the cancellation to finish or to time out. After that, the NFVO shall put the operation occurrence into the FAILED_TEMP state. ")
	/**
	 * Cancellation mode. The NFVO shall not start any new VNF lifecycle management
	 * and resource management operation, and shall wait for the ongoing VNF
	 * lifecycle management and resource management operations in the underlying
	 * system, typically the VNFM and VIM, to finish execution or to time out. After
	 * that, the NFVO shall put the operation occurrence into the FAILED_TEMP state.
	 * The NFVO shall not start any new VNF lifecycle management and resource
	 * management operation, shall cancel the ongoing VNF lifecycle management and
	 * resource management operations in the underlying system, typically the VNFM
	 * and VIM, and shall wait for the cancellation to finish or to time out. After
	 * that, the NFVO shall put the operation occurrence into the FAILED_TEMP state.
	 **/
	private CancelModeEnum cancelMode = null;

	@ApiModelProperty(value = "")
	@Valid
	private ProblemDetails error = null;

	@ApiModelProperty(value = "")
	@Valid
	private NsLcmOpOccsNsLcmOpOccResourceChanges resourceChanges = null;

	@ApiModelProperty(required = true, value = "")
	@Valid
	private NsLcmOpOccsNsLcmOpOccLinks links = null;

	/**
	 * An identifier with the intention of being globally unique.
	 * 
	 * @return id
	 **/
	@JsonProperty("id")
	@NotNull
	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public NsLcmOpOccsNsLcmOpOcc id(final String id) {
		this.id = id;
		return this;
	}

	/**
	 * The enumeration NsLcmOperationStateType shall comply with the provisions
	 * defined in Table 6.5.4.4-1. Value | Description ------|------------
	 * PROCESSING | The LCM operation is currently in execution. COMPLETED | The LCM
	 * operation has been completed successfully. PARTIALLY_COMPLETED | The LCM
	 * operation has been partially completed with accepTable errors. FAILED_TEMP |
	 * The LCM operation has failed and execution has stopped, but the execution of
	 * the operation is not considered to be closed. FAILED | The LCM operation has
	 * failed and it cannot be retried or rolled back, as it is determined that such
	 * action won&#39;t succeed. OLLING_BACK | The LCM operation is currently being
	 * rolled back. ROLLED_BACK | The LCM operation has been successfully rolled
	 * back, i.e. The state of the VNF prior to the original operation invocation
	 * has been restored as closely as possible.
	 * 
	 * @return operationState
	 **/
	@JsonProperty("operationState")
	@NotNull
	public String getOperationState() {
		if (operationState == null) {
			return null;
		}
		return operationState.value();
	}

	public void setOperationState(final OperationStateEnum operationState) {
		this.operationState = operationState;
	}

	public NsLcmOpOccsNsLcmOpOcc operationState(final OperationStateEnum operationState) {
		this.operationState = operationState;
		return this;
	}

	/**
	 * Date-time when the current state was entered.
	 * 
	 * @return stateEnteredTime
	 **/
	@JsonProperty("stateEnteredTime")
	@NotNull
	public Date getStateEnteredTime() {
		return stateEnteredTime;
	}

	public void setStateEnteredTime(final Date stateEnteredTime) {
		this.stateEnteredTime = stateEnteredTime;
	}

	public NsLcmOpOccsNsLcmOpOcc stateEnteredTime(final Date stateEnteredTime) {
		this.stateEnteredTime = stateEnteredTime;
		return this;
	}

	/**
	 * An identifier with the intention of being globally unique.
	 * 
	 * @return nsInstanceId
	 **/
	@JsonProperty("nsInstanceId")
	@NotNull
	public String getNsInstanceId() {
		return nsInstanceId;
	}

	public void setNsInstanceId(final String nsInstanceId) {
		this.nsInstanceId = nsInstanceId;
	}

	public NsLcmOpOccsNsLcmOpOcc nsInstanceId(final String nsInstanceId) {
		this.nsInstanceId = nsInstanceId;
		return this;
	}

	/**
	 * The enumeration NsLcmOpType represents those lifecycle operations that
	 * trigger a NS lifecycle management operation occurrence notification. Value |
	 * Description ------|------------ INSTANTIATE | Represents the
	 * \&quot;Instantiate NS\&quot; LCM operation. SCALE | Represents the
	 * \&quot;Scale NS\&quot; LCM operation. UPDATE | Represents the \&quot;Update
	 * NS\&quot; LCM operation. TERMINATE | Represents the \&quot;Terminate
	 * NS\&quot; LCM operation. HEAL | Represents the \&quot;Heal NS\&quot; LCM
	 * operation.
	 * 
	 * @return lcmOperationType
	 **/
	@JsonProperty("lcmOperationType")
	@NotNull
	public String getLcmOperationType() {
		if (lcmOperationType == null) {
			return null;
		}
		return lcmOperationType.value();
	}

	public void setLcmOperationType(final LcmOperationTypeEnum lcmOperationType) {
		this.lcmOperationType = lcmOperationType;
	}

	public NsLcmOpOccsNsLcmOpOcc lcmOperationType(final LcmOperationTypeEnum lcmOperationType) {
		this.lcmOperationType = lcmOperationType;
		return this;
	}

	/**
	 * Date-time of the start of the operation.
	 * 
	 * @return startTime
	 **/
	@JsonProperty("startTime")
	@NotNull
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(final Date startTime) {
		this.startTime = startTime;
	}

	public NsLcmOpOccsNsLcmOpOcc startTime(final Date startTime) {
		this.startTime = startTime;
		return this;
	}

	/**
	 * Set to true if this NS LCM operation occurrence has been automatically
	 * triggered by the NFVO. This occurs in the case of auto-scaling, auto-healing
	 * and when a nested NS is modified as a result of an operation on its composite
	 * NS. Set to false otherwise.
	 * 
	 * @return isAutomaticInvocation
	 **/
	@JsonProperty("isAutomaticInvocation")
	@NotNull
	public Boolean isIsAutomaticInvocation() {
		return isAutomaticInvocation;
	}

	public void setIsAutomaticInvocation(final Boolean isAutomaticInvocation) {
		this.isAutomaticInvocation = isAutomaticInvocation;
	}

	public NsLcmOpOccsNsLcmOpOcc isAutomaticInvocation(final Boolean isAutomaticInvocation) {
		this.isAutomaticInvocation = isAutomaticInvocation;
		return this;
	}

	/**
	 * Input parameters of the LCM operation. This attribute shall be formatted
	 * according to the request data type of the related LCM operation. The
	 * following mapping between lcmOperationType and the data type of this
	 * attribute shall apply: - INSTANTIATE: InstantiateNsRequest - SCALE:
	 * ScaleNsRequest - UPDATE: UpdateNsRequest - HEAL: HealNsRequest - TERMINATE:
	 * TerminateNsRequest
	 * 
	 * @return operationParams
	 **/
	@JsonProperty("operationParams")
	@NotNull
	public String getOperationParams() {
		if (operationParams == null) {
			return null;
		}
		return operationParams.value();
	}

	public void setOperationParams(final OperationParamsEnum operationParams) {
		this.operationParams = operationParams;
	}

	public NsLcmOpOccsNsLcmOpOcc operationParams(final OperationParamsEnum operationParams) {
		this.operationParams = operationParams;
		return this;
	}

	/**
	 * If the LCM operation occurrence is in \&quot;PROCESSING\&quot; or
	 * \&quot;ROLLING_BACK\&quot; state and the operation is being cancelled, this
	 * attribute shall be set to true. Otherwise, it shall be set to false.
	 * 
	 * @return isCancelPending
	 **/
	@JsonProperty("isCancelPending")
	@NotNull
	public Boolean isIsCancelPending() {
		return isCancelPending;
	}

	public void setIsCancelPending(final Boolean isCancelPending) {
		this.isCancelPending = isCancelPending;
	}

	public NsLcmOpOccsNsLcmOpOcc isCancelPending(final Boolean isCancelPending) {
		this.isCancelPending = isCancelPending;
		return this;
	}

	/**
	 * Cancellation mode. The NFVO shall not start any new VNF lifecycle management
	 * and resource management operation, and shall wait for the ongoing VNF
	 * lifecycle management and resource management operations in the underlying
	 * system, typically the VNFM and VIM, to finish execution or to time out. After
	 * that, the NFVO shall put the operation occurrence into the FAILED_TEMP state.
	 * The NFVO shall not start any new VNF lifecycle management and resource
	 * management operation, shall cancel the ongoing VNF lifecycle management and
	 * resource management operations in the underlying system, typically the VNFM
	 * and VIM, and shall wait for the cancellation to finish or to time out. After
	 * that, the NFVO shall put the operation occurrence into the FAILED_TEMP state.
	 * 
	 * @return cancelMode
	 **/
	@JsonProperty("cancelMode")
	public String getCancelMode() {
		if (cancelMode == null) {
			return null;
		}
		return cancelMode.value();
	}

	public void setCancelMode(final CancelModeEnum cancelMode) {
		this.cancelMode = cancelMode;
	}

	public NsLcmOpOccsNsLcmOpOcc cancelMode(final CancelModeEnum cancelMode) {
		this.cancelMode = cancelMode;
		return this;
	}

	/**
	 * Get error
	 * 
	 * @return error
	 **/
	@JsonProperty("error")
	public ProblemDetails getError() {
		return error;
	}

	public void setError(final ProblemDetails error) {
		this.error = error;
	}

	public NsLcmOpOccsNsLcmOpOcc error(final ProblemDetails error) {
		this.error = error;
		return this;
	}

	/**
	 * Get resourceChanges
	 * 
	 * @return resourceChanges
	 **/
	@JsonProperty("resourceChanges")
	public NsLcmOpOccsNsLcmOpOccResourceChanges getResourceChanges() {
		return resourceChanges;
	}

	public void setResourceChanges(final NsLcmOpOccsNsLcmOpOccResourceChanges resourceChanges) {
		this.resourceChanges = resourceChanges;
	}

	public NsLcmOpOccsNsLcmOpOcc resourceChanges(final NsLcmOpOccsNsLcmOpOccResourceChanges resourceChanges) {
		this.resourceChanges = resourceChanges;
		return this;
	}

	/**
	 * Get links
	 * 
	 * @return links
	 **/
	@JsonProperty("_links")
	@NotNull
	public NsLcmOpOccsNsLcmOpOccLinks getLinks() {
		return links;
	}

	public void setLinks(final NsLcmOpOccsNsLcmOpOccLinks links) {
		this.links = links;
	}

	public NsLcmOpOccsNsLcmOpOcc links(final NsLcmOpOccsNsLcmOpOccLinks links) {
		this.links = links;
		return this;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class NsLcmOpOccsNsLcmOpOcc {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    operationState: ").append(toIndentedString(operationState)).append("\n");
		sb.append("    stateEnteredTime: ").append(toIndentedString(stateEnteredTime)).append("\n");
		sb.append("    nsInstanceId: ").append(toIndentedString(nsInstanceId)).append("\n");
		sb.append("    lcmOperationType: ").append(toIndentedString(lcmOperationType)).append("\n");
		sb.append("    startTime: ").append(toIndentedString(startTime)).append("\n");
		sb.append("    isAutomaticInvocation: ").append(toIndentedString(isAutomaticInvocation)).append("\n");
		sb.append("    operationParams: ").append(toIndentedString(operationParams)).append("\n");
		sb.append("    isCancelPending: ").append(toIndentedString(isCancelPending)).append("\n");
		sb.append("    cancelMode: ").append(toIndentedString(cancelMode)).append("\n");
		sb.append("    error: ").append(toIndentedString(error)).append("\n");
		sb.append("    resourceChanges: ").append(toIndentedString(resourceChanges)).append("\n");
		sb.append("    links: ").append(toIndentedString(links)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private static String toIndentedString(final Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
