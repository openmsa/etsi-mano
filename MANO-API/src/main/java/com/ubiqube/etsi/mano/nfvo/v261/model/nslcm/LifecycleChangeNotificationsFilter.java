package com.ubiqube.etsi.mano.nfvo.v261.model.nslcm;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents a subscription filter related to notifications about NS
 * lifecycle changes. It shall comply with the provisions defined in Table
 * 6.5.3.8-1. At a particular nesting level in the filter structure, the
 * following applies: All attributes shall match in order for the filter to
 * match (logical \&quot;and\&quot; between different filter attributes). If an
 * attribute is an array, the attribute shall match if at least one of the
 * values in the array matches (logical \&quot;or\&quot; between the values of
 * one filter attribute).
 */
@ApiModel(description = "This type represents a subscription filter related to notifications about  NS lifecycle changes. It shall comply with the provisions defined in Table 6.5.3.8-1. At a particular nesting level in the filter structure, the following applies:  All attributes shall match in order for the filter to match (logical \"and\" between different filter attributes).  If an attribute is an array, the attribute shall match if at least one of the values in the array matches (logical \"or\" between the values of one filter attribute). ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-07T10:02:43.347+02:00")

public class LifecycleChangeNotificationsFilter {
	@JsonProperty("nsInstanceSubscriptionFilter")
	private NsInstanceSubscriptionFilter nsInstanceSubscriptionFilter = null;

	/**
	 * Gets or Sets notificationTypes
	 */
	public enum NotificationTypesEnum {
		NSLCMOPERATIONOCCURENCENOTIFICATION("NsLcmOperationOccurenceNotification"),

		NSIDENTIFIERCREATIONNOTIFICATION("NsIdentifierCreationNotification"),

		NSIDENTIFIERDELETIONNOTIFICATION("NsIdentifierDeletionNotification"),

		NSCHANGENOTIFICATION("NsChangeNotification");

		private final String value;

		NotificationTypesEnum(final String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static NotificationTypesEnum fromValue(final String text) {
			for (final NotificationTypesEnum b : NotificationTypesEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	@JsonProperty("notificationTypes")
	@Valid
	private List<NotificationTypesEnum> notificationTypes = null;

	@JsonProperty("operationTypes")
	@Valid
	private List<NsLcmOpType> operationTypes = null;

	@JsonProperty("operationStates")
	@Valid
	private List<LcmOperationStateType> operationStates = null;

	@JsonProperty("nsComponentTypes")
	@Valid
	private List<NsComponentType> nsComponentTypes = null;

	@JsonProperty("lcmOpNameImpactingNsComponent")
	@Valid
	private List<LcmOpNameForChangeNotificationType> lcmOpNameImpactingNsComponent = null;

	@JsonProperty("lcmOpOccStatusImpactingNsComponent")
	@Valid
	private List<LcmOpOccStatusForChangeNotificationType> lcmOpOccStatusImpactingNsComponent = null;

	public LifecycleChangeNotificationsFilter nsInstanceSubscriptionFilter(final NsInstanceSubscriptionFilter nsInstanceSubscriptionFilter) {
		this.nsInstanceSubscriptionFilter = nsInstanceSubscriptionFilter;
		return this;
	}

	/**
	 * Filter criteria to select NS instances about which to notify.
	 *
	 * @return nsInstanceSubscriptionFilter
	 **/
	@ApiModelProperty(value = "Filter criteria to select NS instances about which to notify. ")

	@Valid

	public NsInstanceSubscriptionFilter getNsInstanceSubscriptionFilter() {
		return nsInstanceSubscriptionFilter;
	}

	public void setNsInstanceSubscriptionFilter(final NsInstanceSubscriptionFilter nsInstanceSubscriptionFilter) {
		this.nsInstanceSubscriptionFilter = nsInstanceSubscriptionFilter;
	}

	public LifecycleChangeNotificationsFilter notificationTypes(final List<NotificationTypesEnum> notificationTypes) {
		this.notificationTypes = notificationTypes;
		return this;
	}

	public LifecycleChangeNotificationsFilter addNotificationTypesItem(final NotificationTypesEnum notificationTypesItem) {
		if (this.notificationTypes == null) {
			this.notificationTypes = new ArrayList<>();
		}
		this.notificationTypes.add(notificationTypesItem);
		return this;
	}

	/**
	 * Match particular notification types. Permitted values: -
	 * NsLcmOperationOccurenceNotification - NsIdentifierCreationNotification -
	 * NsIdentifierDeletionNotification - NsChangeNotification
	 *
	 * @return notificationTypes
	 **/
	@ApiModelProperty(value = "Match particular notification types. Permitted values: - NsLcmOperationOccurenceNotification - NsIdentifierCreationNotification - NsIdentifierDeletionNotification - NsChangeNotification ")

	public List<NotificationTypesEnum> getNotificationTypes() {
		return notificationTypes;
	}

	public void setNotificationTypes(final List<NotificationTypesEnum> notificationTypes) {
		this.notificationTypes = notificationTypes;
	}

	public LifecycleChangeNotificationsFilter operationTypes(final List<NsLcmOpType> operationTypes) {
		this.operationTypes = operationTypes;
		return this;
	}

	public LifecycleChangeNotificationsFilter addOperationTypesItem(final NsLcmOpType operationTypesItem) {
		if (this.operationTypes == null) {
			this.operationTypes = new ArrayList<>();
		}
		this.operationTypes.add(operationTypesItem);
		return this;
	}

	/**
	 * Match particular NS lifecycle operation types for the notification of type
	 * NsLcmOperationOccurrenceNotification. May be present if the
	 * \"notificationTypes\" attribute contains the value
	 * \"NsLcmOperationOccurrenceNotification\", and shall be absent otherwise.
	 *
	 * @return operationTypes
	 **/
	@ApiModelProperty(value = "Match particular NS lifecycle operation types for the notification of type NsLcmOperationOccurrenceNotification. May be present if the \"notificationTypes\" attribute contains the value \"NsLcmOperationOccurrenceNotification\", and shall be absent otherwise. ")

	@Valid

	public List<NsLcmOpType> getOperationTypes() {
		return operationTypes;
	}

	public void setOperationTypes(final List<NsLcmOpType> operationTypes) {
		this.operationTypes = operationTypes;
	}

	public LifecycleChangeNotificationsFilter operationStates(final List<LcmOperationStateType> operationStates) {
		this.operationStates = operationStates;
		return this;
	}

	public LifecycleChangeNotificationsFilter addOperationStatesItem(final LcmOperationStateType operationStatesItem) {
		if (this.operationStates == null) {
			this.operationStates = new ArrayList<>();
		}
		this.operationStates.add(operationStatesItem);
		return this;
	}

	/**
	 * Match particular LCM operation state values as reported in notifications of
	 * type NsLcmOperationOccurrenceNotification. May be present if the
	 * \"notificationTypes\" attribute contains the value
	 * \"NsLcmOperationOccurrenceNotification\", and shall be absent otherwise.
	 *
	 * @return operationStates
	 **/
	@ApiModelProperty(value = "Match particular LCM operation state values as reported in notifications of type NsLcmOperationOccurrenceNotification. May be present if the \"notificationTypes\" attribute contains the value \"NsLcmOperationOccurrenceNotification\", and shall be absent otherwise. ")

	@Valid

	public List<LcmOperationStateType> getOperationStates() {
		return operationStates;
	}

	public void setOperationStates(final List<LcmOperationStateType> operationStates) {
		this.operationStates = operationStates;
	}

	public LifecycleChangeNotificationsFilter nsComponentTypes(final List<NsComponentType> nsComponentTypes) {
		this.nsComponentTypes = nsComponentTypes;
		return this;
	}

	public LifecycleChangeNotificationsFilter addNsComponentTypesItem(final NsComponentType nsComponentTypesItem) {
		if (this.nsComponentTypes == null) {
			this.nsComponentTypes = new ArrayList<>();
		}
		this.nsComponentTypes.add(nsComponentTypesItem);
		return this;
	}

	/**
	 * Match particular NS component types for the notification of type
	 * NsChangeNotification. May be present if the \"notificationTypes\" attribute
	 * contains the value \"NsChang.
	 *
	 * @return nsComponentTypes
	 **/
	@ApiModelProperty(value = "Match particular NS component types for the notification of type NsChangeNotification. May be present if the \"notificationTypes\" attribute contains the value \"NsChang. ")

	@Valid

	public List<NsComponentType> getNsComponentTypes() {
		return nsComponentTypes;
	}

	public void setNsComponentTypes(final List<NsComponentType> nsComponentTypes) {
		this.nsComponentTypes = nsComponentTypes;
	}

	public LifecycleChangeNotificationsFilter lcmOpNameImpactingNsComponent(final List<LcmOpNameForChangeNotificationType> lcmOpNameImpactingNsComponent) {
		this.lcmOpNameImpactingNsComponent = lcmOpNameImpactingNsComponent;
		return this;
	}

	public LifecycleChangeNotificationsFilter addLcmOpNameImpactingNsComponentItem(final LcmOpNameForChangeNotificationType lcmOpNameImpactingNsComponentItem) {
		if (this.lcmOpNameImpactingNsComponent == null) {
			this.lcmOpNameImpactingNsComponent = new ArrayList<>();
		}
		this.lcmOpNameImpactingNsComponent.add(lcmOpNameImpactingNsComponentItem);
		return this;
	}

	/**
	 * Match particular LCM operation names for the notification of type
	 * NsChangeNotification. May be present if the \"notificationTypes\" attribute
	 * contains the value \"NsChangeNotification\", and shall be absent otherwise.
	 *
	 * @return lcmOpNameImpactingNsComponent
	 **/
	@ApiModelProperty(value = "Match particular LCM operation names for the notification of type NsChangeNotification. May be present if the \"notificationTypes\" attribute contains the value \"NsChangeNotification\", and shall be absent otherwise. ")

	@Valid

	public List<LcmOpNameForChangeNotificationType> getLcmOpNameImpactingNsComponent() {
		return lcmOpNameImpactingNsComponent;
	}

	public void setLcmOpNameImpactingNsComponent(final List<LcmOpNameForChangeNotificationType> lcmOpNameImpactingNsComponent) {
		this.lcmOpNameImpactingNsComponent = lcmOpNameImpactingNsComponent;
	}

	public LifecycleChangeNotificationsFilter lcmOpOccStatusImpactingNsComponent(final List<LcmOpOccStatusForChangeNotificationType> lcmOpOccStatusImpactingNsComponent) {
		this.lcmOpOccStatusImpactingNsComponent = lcmOpOccStatusImpactingNsComponent;
		return this;
	}

	public LifecycleChangeNotificationsFilter addLcmOpOccStatusImpactingNsComponentItem(final LcmOpOccStatusForChangeNotificationType lcmOpOccStatusImpactingNsComponentItem) {
		if (this.lcmOpOccStatusImpactingNsComponent == null) {
			this.lcmOpOccStatusImpactingNsComponent = new ArrayList<>();
		}
		this.lcmOpOccStatusImpactingNsComponent.add(lcmOpOccStatusImpactingNsComponentItem);
		return this;
	}

	/**
	 * Match particular LCM operation status values as reported in notifications of
	 * type NsChangeNotification. May be present if the \"notificationTypes\"
	 * attribute contains the value \"NsChangeNotification\", and shall be absent
	 * otherwise.
	 *
	 * @return lcmOpOccStatusImpactingNsComponent
	 **/
	@ApiModelProperty(value = "Match particular LCM operation status values as reported in notifications of type NsChangeNotification. May be present if the \"notificationTypes\" attribute contains the value \"NsChangeNotification\", and shall be absent otherwise. ")

	@Valid

	public List<LcmOpOccStatusForChangeNotificationType> getLcmOpOccStatusImpactingNsComponent() {
		return lcmOpOccStatusImpactingNsComponent;
	}

	public void setLcmOpOccStatusImpactingNsComponent(final List<LcmOpOccStatusForChangeNotificationType> lcmOpOccStatusImpactingNsComponent) {
		this.lcmOpOccStatusImpactingNsComponent = lcmOpOccStatusImpactingNsComponent;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final LifecycleChangeNotificationsFilter lifecycleChangeNotificationsFilter = (LifecycleChangeNotificationsFilter) o;
		return Objects.equals(this.nsInstanceSubscriptionFilter, lifecycleChangeNotificationsFilter.nsInstanceSubscriptionFilter) &&
				Objects.equals(this.notificationTypes, lifecycleChangeNotificationsFilter.notificationTypes) &&
				Objects.equals(this.operationTypes, lifecycleChangeNotificationsFilter.operationTypes) &&
				Objects.equals(this.operationStates, lifecycleChangeNotificationsFilter.operationStates) &&
				Objects.equals(this.nsComponentTypes, lifecycleChangeNotificationsFilter.nsComponentTypes) &&
				Objects.equals(this.lcmOpNameImpactingNsComponent, lifecycleChangeNotificationsFilter.lcmOpNameImpactingNsComponent) &&
				Objects.equals(this.lcmOpOccStatusImpactingNsComponent, lifecycleChangeNotificationsFilter.lcmOpOccStatusImpactingNsComponent);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nsInstanceSubscriptionFilter, notificationTypes, operationTypes, operationStates, nsComponentTypes, lcmOpNameImpactingNsComponent, lcmOpOccStatusImpactingNsComponent);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class LifecycleChangeNotificationsFilter {\n");

		sb.append("    nsInstanceSubscriptionFilter: ").append(toIndentedString(nsInstanceSubscriptionFilter)).append("\n");
		sb.append("    notificationTypes: ").append(toIndentedString(notificationTypes)).append("\n");
		sb.append("    operationTypes: ").append(toIndentedString(operationTypes)).append("\n");
		sb.append("    operationStates: ").append(toIndentedString(operationStates)).append("\n");
		sb.append("    nsComponentTypes: ").append(toIndentedString(nsComponentTypes)).append("\n");
		sb.append("    lcmOpNameImpactingNsComponent: ").append(toIndentedString(lcmOpNameImpactingNsComponent)).append("\n");
		sb.append("    lcmOpOccStatusImpactingNsComponent: ").append(toIndentedString(lcmOpOccStatusImpactingNsComponent)).append("\n");
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
