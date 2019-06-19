package io.swagger.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * This type represents a VNF lifecycle management operation occurrence.
 **/
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "This type represents a VNF lifecycle management operation occurrence. ")

public class InlineResponse2001 {

	private @Valid String id = null;

	public enum OperationStateEnum {

		STARTING(String.valueOf("STARTING")), PROCESSING(String.valueOf("PROCESSING")), COMPLETED(String.valueOf("COMPLETED")), FAILED_TEMP(String.valueOf("FAILED_TEMP")), FAILED(String.valueOf("FAILED")), ROLLING_BACK(String.valueOf("ROLLING_BACK")), ROLLED_BACK(String.valueOf("ROLLED_BACK"));

		private final String value;

		OperationStateEnum(String v) {
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
		public static OperationStateEnum fromValue(String v) {
			for (final OperationStateEnum b : OperationStateEnum.values()) {
				if (String.valueOf(b.value).equals(v)) {
					return b;
				}
			}
			return null;
		}
	}

	private @Valid OperationStateEnum operationState = null;
	private @Valid Date stateEnteredTime = null;
	private @Valid Date startTime = null;
	private @Valid String vnfInstanceId = null;
	private @Valid String grantId = null;

	public enum OperationEnum {

		INSTANTIATE(String.valueOf("INSTANTIATE")), SCALE(String.valueOf("SCALE")), SCALE_TO_LEVEL(String.valueOf("SCALE_TO_LEVEL")), CHANGE_FLAVOUR(String.valueOf("CHANGE_FLAVOUR")), TERMINATE(String.valueOf("TERMINATE")), HEAL(String.valueOf("HEAL")), OPERATE(String.valueOf("OPERATE")), CHANGE_EXT_CONN(String.valueOf("CHANGE_EXT_CONN")), MODIFY_INFO(String.valueOf("MODIFY_INFO"));

		private final String value;

		OperationEnum(String v) {
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
		public static OperationEnum fromValue(String v) {
			for (final OperationEnum b : OperationEnum.values()) {
				if (String.valueOf(b.value).equals(v)) {
					return b;
				}
			}
			return null;
		}
	}

	private @Valid OperationEnum operation = null;
	private @Valid Boolean isAutomaticInvocation = null;
	private @Valid Object operationParams = null;
	private @Valid Boolean isCancelPending = null;

	public enum CancelModeEnum {

		GRACEFUL(String.valueOf("GRACEFUL")), FORCEFUL(String.valueOf("FORCEFUL"));

		private final String value;

		CancelModeEnum(String v) {
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
		public static CancelModeEnum fromValue(String v) {
			for (final CancelModeEnum b : CancelModeEnum.values()) {
				if (String.valueOf(b.value).equals(v)) {
					return b;
				}
			}
			return null;
		}
	}

	private @Valid CancelModeEnum cancelMode = null;
	private @Valid InlineResponse400 error = null;
	private @Valid InlineResponse2001ResourceChanges resourceChanges = null;
	private @Valid InlineResponse2001ChangedInfo changedInfo = null;
	private @Valid List<VnfInstancesInstantiatedVnfInfoExtVirtualLinkInfo> changedExtConnectivity = new ArrayList<VnfInstancesInstantiatedVnfInfoExtVirtualLinkInfo>();
	private @Valid InlineResponse2001Links links = null;

	/**
	 * An identifier with the intention of being globally unique.
	 **/
	public InlineResponse2001 id(String id) {
		this.id = id;
		return this;
	}

	@ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
	@JsonProperty("id")
	@NotNull
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Value | Description ------|------------ STARTING | The LCM operation is
	 * starting. PROCESSING | The LCM operation is currently in execution. COMPLETED
	 * | he LCM operation has been completed successfully. FAILED_TEMP | The LCM
	 * operation has failed and execution has stopped, but the execution of the
	 * operation is not considered to be closed. FAILED | The LCM operation has
	 * failed and it cannot be retried or rolled back, as it is determined that such
	 * action won&#39;t succeed. ROLLING_BACK | The LCM operation is currently being
	 * rolled back. ROLLED_BACK | The LCM operation has been successfully rolled
	 * back, i.e. The state of the VNF prior to the original operation invocation
	 * has been restored as closely as possible.
	 **/
	public InlineResponse2001 operationState(OperationStateEnum operationState) {
		this.operationState = operationState;
		return this;
	}

	@ApiModelProperty(required = true, value = "Value | Description ------|------------ STARTING | The LCM operation is starting. PROCESSING | The LCM operation is currently in execution. COMPLETED | he LCM operation has been completed successfully. FAILED_TEMP | The LCM operation has failed and execution has stopped, but the execution of the operation is not considered to be closed. FAILED | The LCM operation has failed and it cannot be retried or rolled back, as it is determined that such action won't succeed. ROLLING_BACK | The LCM operation is currently being rolled back. ROLLED_BACK | The LCM operation has been successfully rolled back, i.e. The state of the VNF prior to the original operation invocation has been restored as closely as possible. ")
	@JsonProperty("operationState")
	@NotNull
	public OperationStateEnum getOperationState() {
		return operationState;
	}

	public void setOperationState(OperationStateEnum operationState) {
		this.operationState = operationState;
	}

	/**
	 * Date-time when the current state was entered.
	 **/
	public InlineResponse2001 stateEnteredTime(Date stateEnteredTime) {
		this.stateEnteredTime = stateEnteredTime;
		return this;
	}

	@ApiModelProperty(required = true, value = "Date-time when the current state was entered. ")
	@JsonProperty("stateEnteredTime")
	@NotNull
	public Date getStateEnteredTime() {
		return stateEnteredTime;
	}

	public void setStateEnteredTime(Date stateEnteredTime) {
		this.stateEnteredTime = stateEnteredTime;
	}

	/**
	 * Date-time of the start of the operation.
	 **/
	public InlineResponse2001 startTime(Date startTime) {
		this.startTime = startTime;
		return this;
	}

	@ApiModelProperty(required = true, value = "Date-time of the start of the operation. ")
	@JsonProperty("startTime")
	@NotNull
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * An identifier with the intention of being globally unique.
	 **/
	public InlineResponse2001 vnfInstanceId(String vnfInstanceId) {
		this.vnfInstanceId = vnfInstanceId;
		return this;
	}

	@ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
	@JsonProperty("vnfInstanceId")
	@NotNull
	public String getVnfInstanceId() {
		return vnfInstanceId;
	}

	public void setVnfInstanceId(String vnfInstanceId) {
		this.vnfInstanceId = vnfInstanceId;
	}

	/**
	 * An identifier with the intention of being globally unique.
	 **/
	public InlineResponse2001 grantId(String grantId) {
		this.grantId = grantId;
		return this;
	}

	@ApiModelProperty(value = "An identifier with the intention of being globally unique. ")
	@JsonProperty("grantId")
	public String getGrantId() {
		return grantId;
	}

	public void setGrantId(String grantId) {
		this.grantId = grantId;
	}

	/**
	 * Value | Description ------|------------ INSTANTIATE | Represents the
	 * \&quot;Instantiate VNF\&quot; LCM operation. SCALE | Represents the
	 * \&quot;Scale VNF\&quot; LCM operation. SCALE_TO_LEVEL | Represents the
	 * \&quot;Scale VNF to Level\&quot; LCM operation. CHANGE_FLAVOUR | Represents
	 * the \&quot;Change VNF Flavour\&quot; LCM operation. TERMINATE | Represents
	 * the \&quot;Terminate VNF\&quot; LCM operation. HEAL | Represents the
	 * \&quot;Heal VNF\&quot; LCM operation. OPERATE | Represents the \&quot;Operate
	 * VNF\&quot; LCM operation. CHANGE_EXT_CONN | Represents the \&quot;Change
	 * external VNF connectivity\&quot; LCM operation. MODIFY_INFO | Represents the
	 * \&quot;Modify VNF Information\&quot; LCM operation.
	 **/
	public InlineResponse2001 operation(OperationEnum operation) {
		this.operation = operation;
		return this;
	}

	@ApiModelProperty(required = true, value = "Value | Description ------|------------ INSTANTIATE | Represents the \"Instantiate VNF\" LCM operation.    SCALE | Represents the \"Scale VNF\" LCM operation. SCALE_TO_LEVEL | Represents the \"Scale VNF to Level\" LCM operation. CHANGE_FLAVOUR | Represents the \"Change VNF Flavour\" LCM operation. TERMINATE | Represents the \"Terminate VNF\" LCM operation. HEAL | Represents the \"Heal VNF\" LCM operation. OPERATE | Represents the \"Operate VNF\" LCM operation. CHANGE_EXT_CONN | Represents the \"Change external VNF connectivity\" LCM operation. MODIFY_INFO | Represents the \"Modify VNF Information\" LCM operation.      ")
	@JsonProperty("operation")
	@NotNull
	public OperationEnum getOperation() {
		return operation;
	}

	public void setOperation(OperationEnum operation) {
		this.operation = operation;
	}

	/**
	 * Set to true if this VNF LCM operation occurrence has been triggered by an
	 * automated procedure inside the VNFM (i.e. ScaleVnf / ScaleVnfToLevel
	 * triggered by auto-scale, or HealVnf triggered by auto-heal). Set to false
	 * otherwise.
	 **/
	public InlineResponse2001 isAutomaticInvocation(Boolean isAutomaticInvocation) {
		this.isAutomaticInvocation = isAutomaticInvocation;
		return this;
	}

	@ApiModelProperty(required = true, value = "Set to true if this VNF LCM operation occurrence has been triggered by an automated procedure inside the VNFM (i.e. ScaleVnf / ScaleVnfToLevel triggered by auto-scale, or HealVnf triggered by auto-heal). Set to false otherwise. ")
	@JsonProperty("isAutomaticInvocation")
	@NotNull
	public Boolean isIsAutomaticInvocation() {
		return isAutomaticInvocation;
	}

	public void setIsAutomaticInvocation(Boolean isAutomaticInvocation) {
		this.isAutomaticInvocation = isAutomaticInvocation;
	}

	/**
	 * This type represents a list of key-value pairs. The order of the pairs in the
	 * list is not significant. In JSON, a set of key- value pairs is represented as
	 * an object. It shall comply with the provisions defined in clause 4 of IETF
	 * RFC 7159.
	 **/
	public InlineResponse2001 operationParams(Object operationParams) {
		this.operationParams = operationParams;
		return this;
	}

	@ApiModelProperty(required = true, value = "This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  ")
	@JsonProperty("operationParams")
	@NotNull
	public Object getOperationParams() {
		return operationParams;
	}

	public void setOperationParams(Object operationParams) {
		this.operationParams = operationParams;
	}

	/**
	 * If the VNF LCM operation occurrence is in \&quot;STARTING\&quot;,
	 * \&quot;PROCESSING\&quot; or \&quot;ROLLING_BACK\&quot; state and the
	 * operation is being cancelled, this attribute shall be set to true. Otherwise,
	 * it shall be set to false.
	 **/
	public InlineResponse2001 isCancelPending(Boolean isCancelPending) {
		this.isCancelPending = isCancelPending;
		return this;
	}

	@ApiModelProperty(required = true, value = "If the VNF LCM operation occurrence is in \"STARTING\", \"PROCESSING\" or \"ROLLING_BACK\" state and the operation is being cancelled, this attribute shall be set to true. Otherwise, it shall be set to false. ")
	@JsonProperty("isCancelPending")
	@NotNull
	public Boolean isIsCancelPending() {
		return isCancelPending;
	}

	public void setIsCancelPending(Boolean isCancelPending) {
		this.isCancelPending = isCancelPending;
	}

	/**
	 * Cancellation mode. GRACEFUL: The VNFM shall not start any new resource
	 * management operation and shall wait for the ongoing resource management
	 * operations in the underlying system, typically the VIM, to finish execution
	 * or to time out. After that, the VNFM shall put the operation occurrence into
	 * the FAILED_TEMP state. FORCEFUL: The VNFM shall not start any new resource
	 * management operation, shall cancel the ongoing resource management operations
	 * in the underlying system, typically the VIM, and shall wait for the
	 * cancellation to finish or to time out. After that, the VNFM shall put the
	 * operation occurrence into the FAILED_TEMP state.
	 **/
	public InlineResponse2001 cancelMode(CancelModeEnum cancelMode) {
		this.cancelMode = cancelMode;
		return this;
	}

	@ApiModelProperty(value = "Cancellation mode. GRACEFUL: The VNFM shall not start any new resource management operation and shall wait for the ongoing resource management operations in the underlying system, typically the VIM, to finish execution or to time out. After that, the VNFM shall put the operation occurrence into the FAILED_TEMP state. FORCEFUL: The VNFM shall not start any new resource management operation, shall cancel the ongoing resource management operations in the underlying system, typically the VIM, and shall wait for the cancellation to finish or to time out. After that, the VNFM shall put the operation occurrence into the FAILED_TEMP state. ")
	@JsonProperty("cancelMode")
	public CancelModeEnum getCancelMode() {
		return cancelMode;
	}

	public void setCancelMode(CancelModeEnum cancelMode) {
		this.cancelMode = cancelMode;
	}

	/**
	 **/
	public InlineResponse2001 error(InlineResponse400 error) {
		this.error = error;
		return this;
	}

	@ApiModelProperty(value = "")
	@JsonProperty("error")
	public InlineResponse400 getError() {
		return error;
	}

	public void setError(InlineResponse400 error) {
		this.error = error;
	}

	/**
	 **/
	public InlineResponse2001 resourceChanges(InlineResponse2001ResourceChanges resourceChanges) {
		this.resourceChanges = resourceChanges;
		return this;
	}

	@ApiModelProperty(value = "")
	@JsonProperty("resourceChanges")
	public InlineResponse2001ResourceChanges getResourceChanges() {
		return resourceChanges;
	}

	public void setResourceChanges(InlineResponse2001ResourceChanges resourceChanges) {
		this.resourceChanges = resourceChanges;
	}

	/**
	 **/
	public InlineResponse2001 changedInfo(InlineResponse2001ChangedInfo changedInfo) {
		this.changedInfo = changedInfo;
		return this;
	}

	@ApiModelProperty(value = "")
	@JsonProperty("changedInfo")
	public InlineResponse2001ChangedInfo getChangedInfo() {
		return changedInfo;
	}

	public void setChangedInfo(InlineResponse2001ChangedInfo changedInfo) {
		this.changedInfo = changedInfo;
	}

	/**
	 * Information about changed external connectivity, if applicable. This allows
	 * the NFVO to obtain the information contained in the latest
	 * \&quot;result\&quot; notification if it has not received it due to an error
	 * or a wrongly configured subscription filter.
	 **/
	public InlineResponse2001 changedExtConnectivity(List<VnfInstancesInstantiatedVnfInfoExtVirtualLinkInfo> changedExtConnectivity) {
		this.changedExtConnectivity = changedExtConnectivity;
		return this;
	}

	@ApiModelProperty(value = "Information about changed external connectivity, if applicable. This allows the NFVO to obtain the information contained in the latest \"result\" notification if it has not received it due to an error or a wrongly configured subscription filter. ")
	@JsonProperty("changedExtConnectivity")
	public List<VnfInstancesInstantiatedVnfInfoExtVirtualLinkInfo> getChangedExtConnectivity() {
		return changedExtConnectivity;
	}

	public void setChangedExtConnectivity(List<VnfInstancesInstantiatedVnfInfoExtVirtualLinkInfo> changedExtConnectivity) {
		this.changedExtConnectivity = changedExtConnectivity;
	}

	/**
	 **/
	public InlineResponse2001 links(InlineResponse2001Links links) {
		this.links = links;
		return this;
	}

	@ApiModelProperty(value = "")
	@JsonProperty("_links")
	public InlineResponse2001Links getLinks() {
		return links;
	}

	public void setLinks(InlineResponse2001Links links) {
		this.links = links;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final InlineResponse2001 inlineResponse2001 = (InlineResponse2001) o;
		return Objects.equals(id, inlineResponse2001.id) &&
				Objects.equals(operationState, inlineResponse2001.operationState) &&
				Objects.equals(stateEnteredTime, inlineResponse2001.stateEnteredTime) &&
				Objects.equals(startTime, inlineResponse2001.startTime) &&
				Objects.equals(vnfInstanceId, inlineResponse2001.vnfInstanceId) &&
				Objects.equals(grantId, inlineResponse2001.grantId) &&
				Objects.equals(operation, inlineResponse2001.operation) &&
				Objects.equals(isAutomaticInvocation, inlineResponse2001.isAutomaticInvocation) &&
				Objects.equals(operationParams, inlineResponse2001.operationParams) &&
				Objects.equals(isCancelPending, inlineResponse2001.isCancelPending) &&
				Objects.equals(cancelMode, inlineResponse2001.cancelMode) &&
				Objects.equals(error, inlineResponse2001.error) &&
				Objects.equals(resourceChanges, inlineResponse2001.resourceChanges) &&
				Objects.equals(changedInfo, inlineResponse2001.changedInfo) &&
				Objects.equals(changedExtConnectivity, inlineResponse2001.changedExtConnectivity) &&
				Objects.equals(links, inlineResponse2001.links);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, operationState, stateEnteredTime, startTime, vnfInstanceId, grantId, operation, isAutomaticInvocation, operationParams, isCancelPending, cancelMode, error, resourceChanges, changedInfo, changedExtConnectivity, links);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class InlineResponse2001 {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    operationState: ").append(toIndentedString(operationState)).append("\n");
		sb.append("    stateEnteredTime: ").append(toIndentedString(stateEnteredTime)).append("\n");
		sb.append("    startTime: ").append(toIndentedString(startTime)).append("\n");
		sb.append("    vnfInstanceId: ").append(toIndentedString(vnfInstanceId)).append("\n");
		sb.append("    grantId: ").append(toIndentedString(grantId)).append("\n");
		sb.append("    operation: ").append(toIndentedString(operation)).append("\n");
		sb.append("    isAutomaticInvocation: ").append(toIndentedString(isAutomaticInvocation)).append("\n");
		sb.append("    operationParams: ").append(toIndentedString(operationParams)).append("\n");
		sb.append("    isCancelPending: ").append(toIndentedString(isCancelPending)).append("\n");
		sb.append("    cancelMode: ").append(toIndentedString(cancelMode)).append("\n");
		sb.append("    error: ").append(toIndentedString(error)).append("\n");
		sb.append("    resourceChanges: ").append(toIndentedString(resourceChanges)).append("\n");
		sb.append("    changedInfo: ").append(toIndentedString(changedInfo)).append("\n");
		sb.append("    changedExtConnectivity: ").append(toIndentedString(changedExtConnectivity)).append("\n");
		sb.append("    links: ").append(toIndentedString(links)).append("\n");
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
