/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.etsi.mano.nfvo.v351.model.nslcm;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * This type represents a request a NS lifecycle operation occurrence. It shall
 * comply with the provisions defined in Table 6.5.2.3-1. NOTE 1: This allows
 * the OSS/BSS to obtain a copy of the latest \&quot;result\&quot; notification
 * if it has not received it due to an error. If the notification represents the
 * successful result of a lifecycle operation, at least an affectedVnf, or
 * affectedPnf, or affectedVl, or affectedVnffg or affectedNs, or affectedSap
 * shall be present. NOTE 2: A coordination action has timed out if the NFVO has
 * not been able to read the \&quot;Individual coordination action\&quot;
 * resource within a timeout interval after requesting the coordination to be
 * started or to be cancelled. The length of the timeout interval is defined by
 * means outside the scope of the present document NOTE 3: The list of rejected
 * coordinations may be garbage collected if the LCM operation occurrence has
 * reached a terminal state, i.e. one of \&quot;COMPLETED\&quot;,
 * \&quot;FAILED\&quot;, “PARTIALLY COMPLETED” and \&quot;ROLLED_BACK\&quot;.
 */
@Schema(description = "This type represents a request a NS lifecycle operation occurrence. It shall comply with the provisions defined in Table 6.5.2.3-1. NOTE 1: This allows the OSS/BSS to obtain a copy of the latest \"result\" notification if it has not received it due to an error. If the notification represents the successful result of a lifecycle operation, at least an affectedVnf, or affectedPnf, or affectedVl, or affectedVnffg or affectedNs, or affectedSap shall be present. NOTE 2: A coordination action has timed out if the NFVO has not been able to read the \"Individual coordination action\" resource within a timeout interval after requesting the coordination to be started or to be cancelled. The length of the timeout interval is defined by means outside the scope of the present document NOTE 3: The list of rejected coordinations may be garbage collected if the LCM operation occurrence has reached a terminal state, i.e. one of \"COMPLETED\", \"FAILED\", “PARTIALLY COMPLETED” and \"ROLLED_BACK\". ")
@Validated

public class NsLcmOpOcc {
	@JsonProperty("id")
	private String id = null;

	@JsonProperty("operationState")
	private NsLcmOperationStateType operationState = null;

	@JsonProperty("stateEnteredTime")
	private OffsetDateTime stateEnteredTime = null;

	@JsonProperty("nsInstanceId")
	private UUID nsInstanceId = null;

	@JsonProperty("lcmOperationType")
	private NsLcmOpType lcmOperationType = null;

	@JsonProperty("startTime")
	private OffsetDateTime startTime = null;

	@JsonProperty("isAutomaticInvocation")
	private Boolean isAutomaticInvocation = null;

	/**
	 * Input parameters of the LCM operation. This attribute shall be formatted
	 * according to the request data type of the related LCM operation. In addition,
	 * the provisions in clause 6.7 shall apply. The following mapping between
	 * lcmOperationType and the data type of this attribute shall apply: -
	 * INSTANTIATE: InstantiateNsRequest - SCALE: ScaleNsRequest - UPDATE:
	 * UpdateNsRequest - HEAL: HealNsRequest - TERMINATE: TerminateNsRequest This
	 * attribute shall be present if this data type is returned in a response to
	 * reading an individual resource, and may be present according to the chosen
	 * attribute selector parameter if this data type is returned in a response to a
	 * query of a container resource.
	 */
	public enum OperationParamsEnum {
		INSTANTIATE("INSTANTIATE"),

		SCALE("SCALE"),

		UPDATE("UPDATE"),

		HEAL("HEAL"),

		TERMINATE("TERMINATE");

		private final String value;

		OperationParamsEnum(final String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static OperationParamsEnum fromValue(final String text) {
			for (final OperationParamsEnum b : OperationParamsEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	@JsonProperty("operationParams")
	private OperationParamsEnum operationParams = null;

	@JsonProperty("isCancelPending")
	private Boolean isCancelPending = null;

	@JsonProperty("cancelMode")
	private CancelModeType cancelMode = null;

	@JsonProperty("error")
	private ProblemDetails error = null;

	@JsonProperty("resourceChanges")
	private NsLcmOpOccResourceChanges resourceChanges = null;

	@JsonProperty("lcmCoordinations")
	@Valid
	private List<NsLcmOpOccLcmCoordinations> lcmCoordinations = null;

	@JsonProperty("rejectedLcmCoordinations")
	@Valid
	private List<NsLcmOpOccRejectedLcmCoordinations> rejectedLcmCoordinations = null;

	@JsonProperty("warnings")
	@Valid
	private List<String> warnings = null;

	@JsonProperty("_links")
	private NsLcmOpOccLinks _links = null;

	public NsLcmOpOcc id(final String id) {
		this.id = id;
		return this;
	}

	/**
	 * Get id
	 *
	 * @return id
	 **/
	@Schema(required = true, description = "")
	@NotNull

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public NsLcmOpOcc operationState(final NsLcmOperationStateType operationState) {
		this.operationState = operationState;
		return this;
	}

	/**
	 * Get operationState
	 *
	 * @return operationState
	 **/
	@Schema(required = true, description = "")
	@NotNull

	@Valid
	public NsLcmOperationStateType getOperationState() {
		return operationState;
	}

	public void setOperationState(final NsLcmOperationStateType operationState) {
		this.operationState = operationState;
	}

	public NsLcmOpOcc stateEnteredTime(final OffsetDateTime stateEnteredTime) {
		this.stateEnteredTime = stateEnteredTime;
		return this;
	}

	/**
	 * Get stateEnteredTime
	 *
	 * @return stateEnteredTime
	 **/
	@Schema(description = "")

	@Valid
	public OffsetDateTime getStateEnteredTime() {
		return stateEnteredTime;
	}

	public void setStateEnteredTime(final OffsetDateTime stateEnteredTime) {
		this.stateEnteredTime = stateEnteredTime;
	}

	public NsLcmOpOcc nsInstanceId(final UUID nsInstanceId) {
		this.nsInstanceId = nsInstanceId;
		return this;
	}

	/**
	 * Get nsInstanceId
	 *
	 * @return nsInstanceId
	 **/
	@Schema(required = true, description = "")
	@NotNull

	public UUID getNsInstanceId() {
		return nsInstanceId;
	}

	public void setNsInstanceId(final UUID nsInstanceId) {
		this.nsInstanceId = nsInstanceId;
	}

	public NsLcmOpOcc lcmOperationType(final NsLcmOpType lcmOperationType) {
		this.lcmOperationType = lcmOperationType;
		return this;
	}

	/**
	 * Get lcmOperationType
	 *
	 * @return lcmOperationType
	 **/
	@Schema(required = true, description = "")
	@NotNull

	@Valid
	public NsLcmOpType getLcmOperationType() {
		return lcmOperationType;
	}

	public void setLcmOperationType(final NsLcmOpType lcmOperationType) {
		this.lcmOperationType = lcmOperationType;
	}

	public NsLcmOpOcc startTime(final OffsetDateTime startTime) {
		this.startTime = startTime;
		return this;
	}

	/**
	 * Get startTime
	 *
	 * @return startTime
	 **/
	@Schema(required = true, description = "")
	@NotNull

	@Valid
	public OffsetDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(final OffsetDateTime startTime) {
		this.startTime = startTime;
	}

	public NsLcmOpOcc isAutomaticInvocation(final Boolean isAutomaticInvocation) {
		this.isAutomaticInvocation = isAutomaticInvocation;
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
	@Schema(required = true, description = "Set to true if this NS LCM operation occurrence has been automatically triggered by the NFVO. This occurs in the case of auto-scaling, auto-healing and when a nested NS is modified as a result of an operation on its composite NS. Set to false otherwise. ")
	@NotNull

	public Boolean isIsAutomaticInvocation() {
		return isAutomaticInvocation;
	}

	public void setIsAutomaticInvocation(final Boolean isAutomaticInvocation) {
		this.isAutomaticInvocation = isAutomaticInvocation;
	}

	public NsLcmOpOcc operationParams(final OperationParamsEnum operationParams) {
		this.operationParams = operationParams;
		return this;
	}

	/**
	 * Input parameters of the LCM operation. This attribute shall be formatted
	 * according to the request data type of the related LCM operation. In addition,
	 * the provisions in clause 6.7 shall apply. The following mapping between
	 * lcmOperationType and the data type of this attribute shall apply: -
	 * INSTANTIATE: InstantiateNsRequest - SCALE: ScaleNsRequest - UPDATE:
	 * UpdateNsRequest - HEAL: HealNsRequest - TERMINATE: TerminateNsRequest This
	 * attribute shall be present if this data type is returned in a response to
	 * reading an individual resource, and may be present according to the chosen
	 * attribute selector parameter if this data type is returned in a response to a
	 * query of a container resource.
	 *
	 * @return operationParams
	 **/
	@Schema(description = "Input parameters of the LCM operation. This attribute shall be formatted according to the request data type of the related LCM operation. In addition, the provisions in clause 6.7 shall apply. The following mapping between lcmOperationType and the data type of this attribute shall apply: - INSTANTIATE: InstantiateNsRequest - SCALE: ScaleNsRequest - UPDATE: UpdateNsRequest - HEAL: HealNsRequest - TERMINATE: TerminateNsRequest This attribute shall be present if this data type is returned in a response to reading an individual resource, and may be present according to the chosen attribute selector parameter if this data type is returned in a response to a query of a container resource. ")

	public OperationParamsEnum getOperationParams() {
		return operationParams;
	}

	public void setOperationParams(final OperationParamsEnum operationParams) {
		this.operationParams = operationParams;
	}

	public NsLcmOpOcc isCancelPending(final Boolean isCancelPending) {
		this.isCancelPending = isCancelPending;
		return this;
	}

	/**
	 * If the LCM operation occurrence is in \"PROCESSING\" or \"ROLLING_BACK\"
	 * state and the operation is being cancelled, this attribute shall be set to
	 * true. Otherwise, it shall be set to false.
	 *
	 * @return isCancelPending
	 **/
	@Schema(required = true, description = "If the LCM operation occurrence is in \"PROCESSING\" or \"ROLLING_BACK\" state and the operation is being cancelled, this attribute shall be set to true. Otherwise, it shall be set to false. ")
	@NotNull

	public Boolean isIsCancelPending() {
		return isCancelPending;
	}

	public void setIsCancelPending(final Boolean isCancelPending) {
		this.isCancelPending = isCancelPending;
	}

	public NsLcmOpOcc cancelMode(final CancelModeType cancelMode) {
		this.cancelMode = cancelMode;
		return this;
	}

	/**
	 * Get cancelMode
	 *
	 * @return cancelMode
	 **/
	@Schema(description = "")

	@Valid
	public CancelModeType getCancelMode() {
		return cancelMode;
	}

	public void setCancelMode(final CancelModeType cancelMode) {
		this.cancelMode = cancelMode;
	}

	public NsLcmOpOcc error(final ProblemDetails error) {
		this.error = error;
		return this;
	}

	/**
	 * Get error
	 *
	 * @return error
	 **/
	@Schema(description = "")

	@Valid
	public ProblemDetails getError() {
		return error;
	}

	public void setError(final ProblemDetails error) {
		this.error = error;
	}

	public NsLcmOpOcc resourceChanges(final NsLcmOpOccResourceChanges resourceChanges) {
		this.resourceChanges = resourceChanges;
		return this;
	}

	/**
	 * Get resourceChanges
	 *
	 * @return resourceChanges
	 **/
	@Schema(description = "")

	@Valid
	public NsLcmOpOccResourceChanges getResourceChanges() {
		return resourceChanges;
	}

	public void setResourceChanges(final NsLcmOpOccResourceChanges resourceChanges) {
		this.resourceChanges = resourceChanges;
	}

	public NsLcmOpOcc lcmCoordinations(final List<NsLcmOpOccLcmCoordinations> lcmCoordinations) {
		this.lcmCoordinations = lcmCoordinations;
		return this;
	}

	public NsLcmOpOcc addLcmCoordinationsItem(final NsLcmOpOccLcmCoordinations lcmCoordinationsItem) {
		if (this.lcmCoordinations == null) {
			this.lcmCoordinations = new ArrayList<>();
		}
		this.lcmCoordinations.add(lcmCoordinationsItem);
		return this;
	}

	/**
	 * Information about LCM coordination actions (see clause 12) related to this
	 * LCM operation occurrence.
	 *
	 * @return lcmCoordinations
	 **/
	@Schema(description = "Information about LCM coordination actions (see clause 12) related to this LCM operation occurrence. ")
	@Valid
	public List<NsLcmOpOccLcmCoordinations> getLcmCoordinations() {
		return lcmCoordinations;
	}

	public void setLcmCoordinations(final List<NsLcmOpOccLcmCoordinations> lcmCoordinations) {
		this.lcmCoordinations = lcmCoordinations;
	}

	public NsLcmOpOcc rejectedLcmCoordinations(final List<NsLcmOpOccRejectedLcmCoordinations> rejectedLcmCoordinations) {
		this.rejectedLcmCoordinations = rejectedLcmCoordinations;
		return this;
	}

	public NsLcmOpOcc addRejectedLcmCoordinationsItem(final NsLcmOpOccRejectedLcmCoordinations rejectedLcmCoordinationsItem) {
		if (this.rejectedLcmCoordinations == null) {
			this.rejectedLcmCoordinations = new ArrayList<>();
		}
		this.rejectedLcmCoordinations.add(rejectedLcmCoordinationsItem);
		return this;
	}

	/**
	 * Information about LCM coordination actions (see clause 12) that were rejected
	 * by 503 error which means they can be tried again after a delay. See note 3.
	 *
	 * @return rejectedLcmCoordinations
	 **/
	@Schema(description = "Information about LCM coordination actions (see clause 12) that were rejected by 503 error which means they can be tried again after a delay. See note 3. ")
	@Valid
	public List<NsLcmOpOccRejectedLcmCoordinations> getRejectedLcmCoordinations() {
		return rejectedLcmCoordinations;
	}

	public void setRejectedLcmCoordinations(final List<NsLcmOpOccRejectedLcmCoordinations> rejectedLcmCoordinations) {
		this.rejectedLcmCoordinations = rejectedLcmCoordinations;
	}

	public NsLcmOpOcc warnings(final List<String> warnings) {
		this.warnings = warnings;
		return this;
	}

	public NsLcmOpOcc addWarningsItem(final String warningsItem) {
		if (this.warnings == null) {
			this.warnings = new ArrayList<>();
		}
		this.warnings.add(warningsItem);
		return this;
	}

	/**
	 * Warning messages that were generated while the operation was executing. If
	 * the operation has included VNF LCM operations or NS LCM coordination actions
	 * and these have resulted in warnings, such warnings should be added to this
	 * attribute.
	 *
	 * @return warnings
	 **/
	@Schema(description = "Warning messages that were generated while the operation was executing. If the operation has included VNF LCM operations or NS LCM coordination actions and these have resulted in warnings, such warnings should be added to this attribute. ")

	public List<String> getWarnings() {
		return warnings;
	}

	public void setWarnings(final List<String> warnings) {
		this.warnings = warnings;
	}

	public NsLcmOpOcc _links(final NsLcmOpOccLinks _links) {
		this._links = _links;
		return this;
	}

	/**
	 * Get _links
	 *
	 * @return _links
	 **/
	@Schema(required = true, description = "")
	@NotNull

	@Valid
	public NsLcmOpOccLinks getLinks() {
		return _links;
	}

	public void setLinks(final NsLcmOpOccLinks _links) {
		this._links = _links;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final NsLcmOpOcc nsLcmOpOcc = (NsLcmOpOcc) o;
		return Objects.equals(this.id, nsLcmOpOcc.id) &&
				Objects.equals(this.operationState, nsLcmOpOcc.operationState) &&
				Objects.equals(this.stateEnteredTime, nsLcmOpOcc.stateEnteredTime) &&
				Objects.equals(this.nsInstanceId, nsLcmOpOcc.nsInstanceId) &&
				Objects.equals(this.lcmOperationType, nsLcmOpOcc.lcmOperationType) &&
				Objects.equals(this.startTime, nsLcmOpOcc.startTime) &&
				Objects.equals(this.isAutomaticInvocation, nsLcmOpOcc.isAutomaticInvocation) &&
				Objects.equals(this.operationParams, nsLcmOpOcc.operationParams) &&
				Objects.equals(this.isCancelPending, nsLcmOpOcc.isCancelPending) &&
				Objects.equals(this.cancelMode, nsLcmOpOcc.cancelMode) &&
				Objects.equals(this.error, nsLcmOpOcc.error) &&
				Objects.equals(this.resourceChanges, nsLcmOpOcc.resourceChanges) &&
				Objects.equals(this.lcmCoordinations, nsLcmOpOcc.lcmCoordinations) &&
				Objects.equals(this.rejectedLcmCoordinations, nsLcmOpOcc.rejectedLcmCoordinations) &&
				Objects.equals(this.warnings, nsLcmOpOcc.warnings) &&
				Objects.equals(this._links, nsLcmOpOcc._links);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, operationState, stateEnteredTime, nsInstanceId, lcmOperationType, startTime, isAutomaticInvocation, operationParams, isCancelPending, cancelMode, error, resourceChanges, lcmCoordinations, rejectedLcmCoordinations, warnings, _links);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class NsLcmOpOcc {\n");

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
		sb.append("    lcmCoordinations: ").append(toIndentedString(lcmCoordinations)).append("\n");
		sb.append("    rejectedLcmCoordinations: ").append(toIndentedString(rejectedLcmCoordinations)).append("\n");
		sb.append("    warnings: ").append(toIndentedString(warnings)).append("\n");
		sb.append("    _links: ").append(toIndentedString(_links)).append("\n");
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
