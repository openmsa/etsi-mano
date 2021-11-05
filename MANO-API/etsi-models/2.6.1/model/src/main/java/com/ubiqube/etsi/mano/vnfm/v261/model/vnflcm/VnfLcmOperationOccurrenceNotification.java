package com.ubiqube.etsi.mano.vnfm.v261.model.vnflcm;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.model.ProblemDetails;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * This type represents a VNF lifecycle management operation occurrence
 * notification, which informs the receiver of changes in the VNF lifecycle
 * caused by a VNF LCM operation occurrence. The support of the notification is
 * mandatory. This notification shall be triggered by the VNFM when there is a
 * change in the state of a VNF LCM operation occurrence that changes the VNF
 * lifecycle, including: * Instantiation of the VNF * Scaling of the VNF
 * instance (including auto-scaling) * Healing of the VNF instance (including
 * auto-healing) * Change of the state of the VNF instance (i.e. Operate VNF) *
 * Change of the deployment flavour of the VNF instance * Change of the external
 * connectivity of the VNF instance * Termination of the VNF instance *
 * Modification of VNF instance information and/or VNF configurable properties
 * through the \&quot;PATCH\&quot; method on the \&quot;Individual VNF
 * instance\&quot; resource. Clause 5.6.2 defines the states and state
 * transition of a VNF LCM operation occurrence, and also specifies details of
 * the notifications to be emitted at each state transition. If this is the
 * initial notification about the start of a VNF LCM operation occurrence, it is
 * assumed that the notification is sent by the VNFM before any action
 * (including sending the grant request) is taken as part of the LCM operation.
 * Due to possible race conditions, the \&quot;start\&quot; notification, the
 * grant request and the LCM operation acknowledgment (i.e. the \&quot;202
 * Accepted\&quot; response) can arrive in any order at the NFVO, and the NFVO
 * shall be able to handle such a situation. If this is a notification about a
 * final or intermediate result state of a VNF LCM operation occurrence, the
 * notification shall be sent after all related actions of the LCM operation
 * that led to this state have been executed. The new state shall be set in the
 * \&quot;Individual VNF LCM operation occurrence\&quot; resource before the
 * notification about the state change is sent. See clause 5.6.2.2 for further
 * provisions regarding sending this notification, including in cases of
 * handling LCM operation errors.
 */
@Schema(description = "This type represents a VNF lifecycle management operation occurrence notification, which informs the receiver of changes in the VNF lifecycle caused by a VNF LCM operation occurrence. The support of the notification is mandatory. This notification shall be triggered by the VNFM when there is a change in the state of a VNF LCM operation occurrence that changes the VNF lifecycle, including: * Instantiation of the VNF * Scaling of the VNF instance (including auto-scaling) * Healing of the VNF instance (including auto-healing) * Change of the state of the VNF instance (i.e. Operate VNF) * Change of the deployment flavour of the VNF instance * Change of the external connectivity of the VNF instance * Termination of the VNF instance * Modification of VNF instance information and/or VNF configurable   properties through the \"PATCH\" method on the \"Individual VNF instance\"   resource. Clause 5.6.2 defines the states and state transition of a VNF LCM operation occurrence, and also specifies details of the notifications to be emitted at each state transition. If this is the initial notification about the start of a VNF LCM operation occurrence, it is assumed that the notification is sent by the VNFM before any action (including sending the grant request) is taken as part of the LCM operation. Due to possible race conditions, the \"start\" notification, the grant request and the LCM operation acknowledgment (i.e. the \"202 Accepted\" response) can arrive in any order at the NFVO, and the NFVO shall be able to handle such a situation. If this is a notification about a final or intermediate result state of a VNF LCM operation occurrence, the notification shall be sent after all related actions of the LCM operation that led to this state have been executed. The new state shall be set in the \"Individual VNF LCM operation occurrence\" resource before the notification about the state change is sent. See clause 5.6.2.2 for further provisions regarding sending this notification, including in cases of handling LCM operation errors. ")
@Validated

public class VnfLcmOperationOccurrenceNotification {
	@JsonProperty("id")
	private String id = null;

	/**
	 * Discriminator for the different notification types. Shall be set to
	 * \"VnfLcmOperationOccurrenceNotification\" for this notification type.
	 */
	public enum NotificationTypeEnum {
		VNFLCMOPERATIONOCCURRENCENOTIFICATION("VnfLcmOperationOccurrenceNotification");

		private final String value;

		NotificationTypeEnum(final String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static NotificationTypeEnum fromValue(final String text) {
			for (final NotificationTypeEnum b : NotificationTypeEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	@JsonProperty("notificationType")
	private NotificationTypeEnum notificationType = null;

	@JsonProperty("subscriptionId")
	private String subscriptionId = null;

	@JsonProperty("timeStamp")
	private OffsetDateTime timeStamp = null;

	/**
	 * Indicates whether this notification reports about the start of a lifecycle
	 * operation or the result of a lifecycle operation. Permitted values: * START:
	 * Informs about the start of the VNF LCM operation occurrence. * RESULT:
	 * Informs about the final or intermediate result of the VNF LCM operation
	 * occurrence.
	 */
	public enum NotificationStatusEnum {
		START("START"),

		RESULT("RESULT");

		private final String value;

		NotificationStatusEnum(final String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static NotificationStatusEnum fromValue(final String text) {
			for (final NotificationStatusEnum b : NotificationStatusEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	@JsonProperty("notificationStatus")
	private NotificationStatusEnum notificationStatus = null;

	@JsonProperty("operationState")
	private LcmOperationStateType operationState = null;

	@JsonProperty("vnfInstanceId")
	private String vnfInstanceId = null;

	@JsonProperty("operation")
	private LcmOperationType operation = null;

	@JsonProperty("isAutomaticInvocation")
	private Boolean isAutomaticInvocation = null;

	@JsonProperty("vnfLcmOpOccId")
	private String vnfLcmOpOccId = null;

	@JsonProperty("affectedVnfcs")
	@Valid
	private List<AffectedVnfc> affectedVnfcs = null;

	@JsonProperty("affectedVirtualLinks")
	@Valid
	private List<AffectedVirtualLink> affectedVirtualLinks = null;

	@JsonProperty("affectedVirtualStorages")
	@Valid
	private List<AffectedVirtualStorage> affectedVirtualStorages = null;

	@JsonProperty("changedInfo")
	private VnfInfoModifications changedInfo = null;

	@JsonProperty("changedExtConnectivity")
	@Valid
	private List<ExtVirtualLinkInfo> changedExtConnectivity = null;

	@JsonProperty("error")
	private ProblemDetails error = null;

	@JsonProperty("_links")
	private LccnLinks _links = null;

	public VnfLcmOperationOccurrenceNotification id(final String id) {
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

	public VnfLcmOperationOccurrenceNotification notificationType(final NotificationTypeEnum notificationType) {
		this.notificationType = notificationType;
		return this;
	}

	/**
	 * Discriminator for the different notification types. Shall be set to
	 * \"VnfLcmOperationOccurrenceNotification\" for this notification type.
	 *
	 * @return notificationType
	 **/
	@Schema(required = true, description = "Discriminator for the different notification types. Shall be set to \"VnfLcmOperationOccurrenceNotification\" for this notification type. ")
	@NotNull

	public NotificationTypeEnum getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(final NotificationTypeEnum notificationType) {
		this.notificationType = notificationType;
	}

	public VnfLcmOperationOccurrenceNotification subscriptionId(final String subscriptionId) {
		this.subscriptionId = subscriptionId;
		return this;
	}

	/**
	 * Get subscriptionId
	 *
	 * @return subscriptionId
	 **/
	@Schema(required = true, description = "")
	@NotNull

	public String getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(final String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public VnfLcmOperationOccurrenceNotification timeStamp(final OffsetDateTime timeStamp) {
		this.timeStamp = timeStamp;
		return this;
	}

	/**
	 * Get timeStamp
	 *
	 * @return timeStamp
	 **/
	@Schema(required = true, description = "")
	@NotNull

	@Valid
	public OffsetDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(final OffsetDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	public VnfLcmOperationOccurrenceNotification notificationStatus(final NotificationStatusEnum notificationStatus) {
		this.notificationStatus = notificationStatus;
		return this;
	}

	/**
	 * Indicates whether this notification reports about the start of a lifecycle
	 * operation or the result of a lifecycle operation. Permitted values: * START:
	 * Informs about the start of the VNF LCM operation occurrence. * RESULT:
	 * Informs about the final or intermediate result of the VNF LCM operation
	 * occurrence.
	 *
	 * @return notificationStatus
	 **/
	@Schema(required = true, description = "Indicates whether this notification reports about the start of a lifecycle operation or the result of a lifecycle operation. Permitted values: * START: Informs about the start of the VNF LCM operation   occurrence. * RESULT: Informs about the final or intermediate result of the VNF   LCM operation occurrence. ")
	@NotNull

	public NotificationStatusEnum getNotificationStatus() {
		return notificationStatus;
	}

	public void setNotificationStatus(final NotificationStatusEnum notificationStatus) {
		this.notificationStatus = notificationStatus;
	}

	public VnfLcmOperationOccurrenceNotification operationState(final LcmOperationStateType operationState) {
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
	public LcmOperationStateType getOperationState() {
		return operationState;
	}

	public void setOperationState(final LcmOperationStateType operationState) {
		this.operationState = operationState;
	}

	public VnfLcmOperationOccurrenceNotification vnfInstanceId(final String vnfInstanceId) {
		this.vnfInstanceId = vnfInstanceId;
		return this;
	}

	/**
	 * Get vnfInstanceId
	 *
	 * @return vnfInstanceId
	 **/
	@Schema(required = true, description = "")
	@NotNull

	public String getVnfInstanceId() {
		return vnfInstanceId;
	}

	public void setVnfInstanceId(final String vnfInstanceId) {
		this.vnfInstanceId = vnfInstanceId;
	}

	public VnfLcmOperationOccurrenceNotification operation(final LcmOperationType operation) {
		this.operation = operation;
		return this;
	}

	/**
	 * Get operation
	 *
	 * @return operation
	 **/
	@Schema(required = true, description = "")
	@NotNull

	@Valid
	public LcmOperationType getOperation() {
		return operation;
	}

	public void setOperation(final LcmOperationType operation) {
		this.operation = operation;
	}

	public VnfLcmOperationOccurrenceNotification isAutomaticInvocation(final Boolean isAutomaticInvocation) {
		this.isAutomaticInvocation = isAutomaticInvocation;
		return this;
	}

	/**
	 * Set to true if this VNF LCM operation occurrence has been triggered by an
	 * automated procedure inside the VNFM (i.e. ScaleVnf / ScaleVnfToLevel
	 * triggered by auto-scale, or HealVnf triggered by auto-heal). Set to false
	 * otherwise.
	 *
	 * @return isAutomaticInvocation
	 **/
	@Schema(required = true, description = "Set to true if this VNF LCM operation occurrence has been triggered by an automated procedure inside the VNFM (i.e. ScaleVnf / ScaleVnfToLevel triggered by auto-scale, or HealVnf triggered by auto-heal). Set to false otherwise. ")
	@NotNull

	public Boolean isIsAutomaticInvocation() {
		return isAutomaticInvocation;
	}

	public void setIsAutomaticInvocation(final Boolean isAutomaticInvocation) {
		this.isAutomaticInvocation = isAutomaticInvocation;
	}

	public VnfLcmOperationOccurrenceNotification vnfLcmOpOccId(final String vnfLcmOpOccId) {
		this.vnfLcmOpOccId = vnfLcmOpOccId;
		return this;
	}

	/**
	 * Get vnfLcmOpOccId
	 *
	 * @return vnfLcmOpOccId
	 **/
	@Schema(required = true, description = "")
	@NotNull

	public String getVnfLcmOpOccId() {
		return vnfLcmOpOccId;
	}

	public void setVnfLcmOpOccId(final String vnfLcmOpOccId) {
		this.vnfLcmOpOccId = vnfLcmOpOccId;
	}

	public VnfLcmOperationOccurrenceNotification affectedVnfcs(final List<AffectedVnfc> affectedVnfcs) {
		this.affectedVnfcs = affectedVnfcs;
		return this;
	}

	public VnfLcmOperationOccurrenceNotification addAffectedVnfcsItem(final AffectedVnfc affectedVnfcsItem) {
		if (this.affectedVnfcs == null) {
			this.affectedVnfcs = new ArrayList<>();
		}
		this.affectedVnfcs.add(affectedVnfcsItem);
		return this;
	}

	/**
	 * Information about VNFC instances that were affected during the lifecycle
	 * operation. Shall be present if the \"notificationStatus\" is set to
	 * \"RESULT\" and the operation has performed any resource modification. Shall
	 * be absent otherwise. This attribute contains information about the cumulative
	 * changes to virtualised resources that were performed so far by the VNF LCM
	 * operation occurrence and by any of the error handling procedures for that
	 * operation occurrence.
	 *
	 * @return affectedVnfcs
	 **/
	@Schema(description = "Information about VNFC instances that were affected during the lifecycle operation. Shall be present if the \"notificationStatus\" is set to \"RESULT\" and the operation has performed any resource modification. Shall be absent otherwise. This attribute contains information about the cumulative changes to virtualised resources that were performed so far by the VNF LCM operation occurrence and by any of the error handling procedures for that operation occurrence. ")
	@Valid
	public List<AffectedVnfc> getAffectedVnfcs() {
		return affectedVnfcs;
	}

	public void setAffectedVnfcs(final List<AffectedVnfc> affectedVnfcs) {
		this.affectedVnfcs = affectedVnfcs;
	}

	public VnfLcmOperationOccurrenceNotification affectedVirtualLinks(final List<AffectedVirtualLink> affectedVirtualLinks) {
		this.affectedVirtualLinks = affectedVirtualLinks;
		return this;
	}

	public VnfLcmOperationOccurrenceNotification addAffectedVirtualLinksItem(final AffectedVirtualLink affectedVirtualLinksItem) {
		if (this.affectedVirtualLinks == null) {
			this.affectedVirtualLinks = new ArrayList<>();
		}
		this.affectedVirtualLinks.add(affectedVirtualLinksItem);
		return this;
	}

	/**
	 * Information about VL instances that were affected during the lifecycle
	 * operation. Shall be present if the \"notificationStatus\" is set to
	 * \"RESULT\" and the operation has performed any resource modification. Shall
	 * be absent otherwise. This attribute contains information about the cumulative
	 * changes to virtualised resources that were performed so far by the VNF LCM
	 * operation occurrence and by any of the error handling procedures for that
	 * operation occurrence.
	 *
	 * @return affectedVirtualLinks
	 **/
	@Schema(description = "Information about VL instances that were affected during the lifecycle operation. Shall be present if the \"notificationStatus\" is set to \"RESULT\" and the operation has performed any resource modification. Shall be absent otherwise. This attribute contains information about the cumulative changes to virtualised resources that were performed so far by the VNF LCM operation occurrence and by any of the error handling procedures for that operation occurrence. ")
	@Valid
	public List<AffectedVirtualLink> getAffectedVirtualLinks() {
		return affectedVirtualLinks;
	}

	public void setAffectedVirtualLinks(final List<AffectedVirtualLink> affectedVirtualLinks) {
		this.affectedVirtualLinks = affectedVirtualLinks;
	}

	public VnfLcmOperationOccurrenceNotification affectedVirtualStorages(final List<AffectedVirtualStorage> affectedVirtualStorages) {
		this.affectedVirtualStorages = affectedVirtualStorages;
		return this;
	}

	public VnfLcmOperationOccurrenceNotification addAffectedVirtualStoragesItem(final AffectedVirtualStorage affectedVirtualStoragesItem) {
		if (this.affectedVirtualStorages == null) {
			this.affectedVirtualStorages = new ArrayList<>();
		}
		this.affectedVirtualStorages.add(affectedVirtualStoragesItem);
		return this;
	}

	/**
	 * Information about virtualised storage instances that were affected during the
	 * lifecycle operation. Shall be present if the \"notificationStatus\" is set to
	 * \"RESULT\" and the operation has performed any resource modification. Shall
	 * be absent otherwise. This attribute contains information about the cumulative
	 * changes to virtualised resources that were performed so far by the VNF LCM
	 * operation occurrence and by any of the error handling procedures for that
	 * operation occurrence.
	 *
	 * @return affectedVirtualStorages
	 **/
	@Schema(description = "Information about virtualised storage instances that were affected during the lifecycle operation. Shall be present if the \"notificationStatus\" is set to \"RESULT\" and the operation has performed any resource modification. Shall be absent otherwise. This attribute contains information about the cumulative changes to virtualised resources that were performed so far by the VNF LCM operation occurrence and by any of the error handling procedures for that operation occurrence. ")
	@Valid
	public List<AffectedVirtualStorage> getAffectedVirtualStorages() {
		return affectedVirtualStorages;
	}

	public void setAffectedVirtualStorages(final List<AffectedVirtualStorage> affectedVirtualStorages) {
		this.affectedVirtualStorages = affectedVirtualStorages;
	}

	public VnfLcmOperationOccurrenceNotification changedInfo(final VnfInfoModifications changedInfo) {
		this.changedInfo = changedInfo;
		return this;
	}

	/**
	 * Get changedInfo
	 *
	 * @return changedInfo
	 **/
	@Schema(description = "")

	@Valid
	public VnfInfoModifications getChangedInfo() {
		return changedInfo;
	}

	public void setChangedInfo(final VnfInfoModifications changedInfo) {
		this.changedInfo = changedInfo;
	}

	public VnfLcmOperationOccurrenceNotification changedExtConnectivity(final List<ExtVirtualLinkInfo> changedExtConnectivity) {
		this.changedExtConnectivity = changedExtConnectivity;
		return this;
	}

	public VnfLcmOperationOccurrenceNotification addChangedExtConnectivityItem(final ExtVirtualLinkInfo changedExtConnectivityItem) {
		if (this.changedExtConnectivity == null) {
			this.changedExtConnectivity = new ArrayList<>();
		}
		this.changedExtConnectivity.add(changedExtConnectivityItem);
		return this;
	}

	/**
	 * Information about changed external connectivity, if this notification
	 * represents the result of a lifecycle operation occurrence. Shall be present
	 * if the \"notificationStatus\" is set to \"RESULT\" and the \"operation\" has
	 * made any changes to the external connectivity of the VNF instance. Shall be
	 * absent otherwise.
	 *
	 * @return changedExtConnectivity
	 **/
	@Schema(description = "Information about changed external connectivity, if this notification represents the result of a lifecycle operation occurrence. Shall be present if the \"notificationStatus\" is set to \"RESULT\" and the \"operation\" has made any changes to the external connectivity of the VNF instance. Shall be absent otherwise. ")
	@Valid
	public List<ExtVirtualLinkInfo> getChangedExtConnectivity() {
		return changedExtConnectivity;
	}

	public void setChangedExtConnectivity(final List<ExtVirtualLinkInfo> changedExtConnectivity) {
		this.changedExtConnectivity = changedExtConnectivity;
	}

	public VnfLcmOperationOccurrenceNotification error(final ProblemDetails error) {
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

	public VnfLcmOperationOccurrenceNotification _links(final LccnLinks _links) {
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
	public LccnLinks getLinks() {
		return _links;
	}

	public void setLinks(final LccnLinks _links) {
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
		final VnfLcmOperationOccurrenceNotification vnfLcmOperationOccurrenceNotification = (VnfLcmOperationOccurrenceNotification) o;
		return Objects.equals(this.id, vnfLcmOperationOccurrenceNotification.id) &&
				Objects.equals(this.notificationType, vnfLcmOperationOccurrenceNotification.notificationType) &&
				Objects.equals(this.subscriptionId, vnfLcmOperationOccurrenceNotification.subscriptionId) &&
				Objects.equals(this.timeStamp, vnfLcmOperationOccurrenceNotification.timeStamp) &&
				Objects.equals(this.notificationStatus, vnfLcmOperationOccurrenceNotification.notificationStatus) &&
				Objects.equals(this.operationState, vnfLcmOperationOccurrenceNotification.operationState) &&
				Objects.equals(this.vnfInstanceId, vnfLcmOperationOccurrenceNotification.vnfInstanceId) &&
				Objects.equals(this.operation, vnfLcmOperationOccurrenceNotification.operation) &&
				Objects.equals(this.isAutomaticInvocation, vnfLcmOperationOccurrenceNotification.isAutomaticInvocation) &&
				Objects.equals(this.vnfLcmOpOccId, vnfLcmOperationOccurrenceNotification.vnfLcmOpOccId) &&
				Objects.equals(this.affectedVnfcs, vnfLcmOperationOccurrenceNotification.affectedVnfcs) &&
				Objects.equals(this.affectedVirtualLinks, vnfLcmOperationOccurrenceNotification.affectedVirtualLinks) &&
				Objects.equals(this.affectedVirtualStorages, vnfLcmOperationOccurrenceNotification.affectedVirtualStorages) &&
				Objects.equals(this.changedInfo, vnfLcmOperationOccurrenceNotification.changedInfo) &&
				Objects.equals(this.changedExtConnectivity, vnfLcmOperationOccurrenceNotification.changedExtConnectivity) &&
				Objects.equals(this.error, vnfLcmOperationOccurrenceNotification.error) &&
				Objects.equals(this._links, vnfLcmOperationOccurrenceNotification._links);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, notificationType, subscriptionId, timeStamp, notificationStatus, operationState, vnfInstanceId, operation, isAutomaticInvocation, vnfLcmOpOccId, affectedVnfcs, affectedVirtualLinks, affectedVirtualStorages, changedInfo, changedExtConnectivity, error, _links);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfLcmOperationOccurrenceNotification {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    notificationType: ").append(toIndentedString(notificationType)).append("\n");
		sb.append("    subscriptionId: ").append(toIndentedString(subscriptionId)).append("\n");
		sb.append("    timeStamp: ").append(toIndentedString(timeStamp)).append("\n");
		sb.append("    notificationStatus: ").append(toIndentedString(notificationStatus)).append("\n");
		sb.append("    operationState: ").append(toIndentedString(operationState)).append("\n");
		sb.append("    vnfInstanceId: ").append(toIndentedString(vnfInstanceId)).append("\n");
		sb.append("    operation: ").append(toIndentedString(operation)).append("\n");
		sb.append("    isAutomaticInvocation: ").append(toIndentedString(isAutomaticInvocation)).append("\n");
		sb.append("    vnfLcmOpOccId: ").append(toIndentedString(vnfLcmOpOccId)).append("\n");
		sb.append("    affectedVnfcs: ").append(toIndentedString(affectedVnfcs)).append("\n");
		sb.append("    affectedVirtualLinks: ").append(toIndentedString(affectedVirtualLinks)).append("\n");
		sb.append("    affectedVirtualStorages: ").append(toIndentedString(affectedVirtualStorages)).append("\n");
		sb.append("    changedInfo: ").append(toIndentedString(changedInfo)).append("\n");
		sb.append("    changedExtConnectivity: ").append(toIndentedString(changedExtConnectivity)).append("\n");
		sb.append("    error: ").append(toIndentedString(error)).append("\n");
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
