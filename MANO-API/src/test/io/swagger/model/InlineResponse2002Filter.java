package io.swagger.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * This type represents a subscription filter related to notifications about VNF lifecycle changes. At a particular nesting level in the filter structure, the following applies: All attributes shall match in order for the filter to match (logical \&quot;and\&quot; between different filter attributes). If an attribute is an array, the attribute shall match if at least one of the values in the array matches (logical \&quot;or\&quot; between the values of one filter attribute).
 **/
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "This type represents a subscription filter related to notifications about VNF lifecycle changes. At a particular nesting level in the filter structure, the following applies: All attributes shall match in order for the filter to match (logical \"and\" between different filter attributes). If an attribute is an array, the attribute shall match if at least one of the values in the array matches (logical \"or\" between the values of one filter attribute). ")

public class InlineResponse2002Filter {

	private @Valid InlineResponse2002FilterVnfInstanceSubscriptionFilter vnfInstanceSubscriptionFilter = null;

	public enum NotificationTypesEnum {

		VNFLCMOPERATIONOCCURRENCENOTIFICATION(String.valueOf("VnfLcmOperationOccurrenceNotification")), VNFIDENTIFIERCREATIONNOTIFICATION(String.valueOf("VnfIdentifierCreationNotification")), VNFIDENTIFIERDELETIONNOTIFICATION(String.valueOf("VnfIdentifierDeletionNotification"));

		private final String value;

		NotificationTypesEnum(String v) {
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
		public static NotificationTypesEnum fromValue(String v) {
			for (final NotificationTypesEnum b : NotificationTypesEnum.values()) {
				if (String.valueOf(b.value).equals(v)) {
					return b;
				}
			}
			return null;
		}
	}

	private @Valid List<NotificationTypesEnum> notificationTypes = new ArrayList<NotificationTypesEnum>();

	public enum OperationTypesEnum {

		INSTANTIATE(String.valueOf("INSTANTIATE")), SCALE(String.valueOf("SCALE")), SCALE_TO_LEVEL(String.valueOf("SCALE_TO_LEVEL")), CHANGE_FLAVOUR(String.valueOf("CHANGE_FLAVOUR")), TERMINATE(String.valueOf("TERMINATE")), HEAL(String.valueOf("HEAL")), OPERATE(String.valueOf("OPERATE")), CHANGE_EXT_CONN(String.valueOf("CHANGE_EXT_CONN")), MODIFY_INFO(String.valueOf("MODIFY_INFO"));

		private final String value;

		OperationTypesEnum(String v) {
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
		public static OperationTypesEnum fromValue(String v) {
			for (final OperationTypesEnum b : OperationTypesEnum.values()) {
				if (String.valueOf(b.value).equals(v)) {
					return b;
				}
			}
			return null;
		}
	}

	private @Valid List<OperationTypesEnum> operationTypes = new ArrayList<OperationTypesEnum>();

	public enum OperationStatesEnum {

		STARTING(String.valueOf("STARTING")), PROCESSING(String.valueOf("PROCESSING")), COMPLETED(String.valueOf("COMPLETED")), FAILED_TEMP(String.valueOf("FAILED_TEMP")), FAILED(String.valueOf("FAILED")), ROLLING_BACK(String.valueOf("ROLLING_BACK")), ROLLED_BACK(String.valueOf("ROLLED_BACK"));

		private final String value;

		OperationStatesEnum(String v) {
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
		public static OperationStatesEnum fromValue(String v) {
			for (final OperationStatesEnum b : OperationStatesEnum.values()) {
				if (String.valueOf(b.value).equals(v)) {
					return b;
				}
			}
			return null;
		}
	}

	private @Valid List<OperationStatesEnum> operationStates = new ArrayList<OperationStatesEnum>();

	/**
	 **/
	public InlineResponse2002Filter vnfInstanceSubscriptionFilter(InlineResponse2002FilterVnfInstanceSubscriptionFilter vnfInstanceSubscriptionFilter) {
		this.vnfInstanceSubscriptionFilter = vnfInstanceSubscriptionFilter;
		return this;
	}

	@ApiModelProperty(value = "")
	@JsonProperty("vnfInstanceSubscriptionFilter")
	public InlineResponse2002FilterVnfInstanceSubscriptionFilter getVnfInstanceSubscriptionFilter() {
		return vnfInstanceSubscriptionFilter;
	}

	public void setVnfInstanceSubscriptionFilter(InlineResponse2002FilterVnfInstanceSubscriptionFilter vnfInstanceSubscriptionFilter) {
		this.vnfInstanceSubscriptionFilter = vnfInstanceSubscriptionFilter;
	}

	/**
	 * Match particular notification types. Permitted values: *
	 * VnfLcmOperationOccurrenceNotification * VnfIdentifierCreationNotification *
	 * VnfIdentifierDeletionNotification The permitted values of the
	 * \&quot;notificationTypes\&quot; attribute are spelled exactly as the names of
	 * the notification types to facilitate automated code generation systems.
	 **/
	public InlineResponse2002Filter notificationTypes(List<NotificationTypesEnum> notificationTypes) {
		this.notificationTypes = notificationTypes;
		return this;
	}

	@ApiModelProperty(value = "Match particular notification types. Permitted values: * VnfLcmOperationOccurrenceNotification * VnfIdentifierCreationNotification * VnfIdentifierDeletionNotification The permitted values of the \"notificationTypes\" attribute are spelled exactly as the names of the notification types to facilitate automated code generation systems. ")
	@JsonProperty("notificationTypes")
	public List<NotificationTypesEnum> getNotificationTypes() {
		return notificationTypes;
	}

	public void setNotificationTypes(List<NotificationTypesEnum> notificationTypes) {
		this.notificationTypes = notificationTypes;
	}

	/**
	 * Match particular VNF lifecycle operation types for the notification of type
	 * VnfLcmOperationOccurrenceNotification. May be present if the
	 * \&quot;notificationTypes\&quot; attribute contains the value
	 * \&quot;VnfLcmOperationOccurrenceNotification\&quot;, and shall be absent
	 * otherwise.
	 **/
	public InlineResponse2002Filter operationTypes(List<OperationTypesEnum> operationTypes) {
		this.operationTypes = operationTypes;
		return this;
	}

	@ApiModelProperty(value = "Match particular VNF lifecycle operation types for the notification of type VnfLcmOperationOccurrenceNotification. May be present if the \"notificationTypes\" attribute contains the value \"VnfLcmOperationOccurrenceNotification\", and shall be absent otherwise. ")
	@JsonProperty("operationTypes")
	public List<OperationTypesEnum> getOperationTypes() {
		return operationTypes;
	}

	public void setOperationTypes(List<OperationTypesEnum> operationTypes) {
		this.operationTypes = operationTypes;
	}

	/**
	 * Match particular LCM operation state values as reported in notifications of
	 * type VnfLcmOperationOccurrenceNotification. May be present if the
	 * \&quot;notificationTypes\&quot; attribute contains the value
	 * \&quot;VnfLcmOperationOccurrenceNotification\&quot;, and shall be absent
	 * otherwise.
	 **/
	public InlineResponse2002Filter operationStates(List<OperationStatesEnum> operationStates) {
		this.operationStates = operationStates;
		return this;
	}

	@ApiModelProperty(value = "Match particular LCM operation state values as reported in notifications of type VnfLcmOperationOccurrenceNotification. May be present if the \"notificationTypes\" attribute contains the value \"VnfLcmOperationOccurrenceNotification\", and shall be absent otherwise. ")
	@JsonProperty("operationStates")
	public List<OperationStatesEnum> getOperationStates() {
		return operationStates;
	}

	public void setOperationStates(List<OperationStatesEnum> operationStates) {
		this.operationStates = operationStates;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final InlineResponse2002Filter inlineResponse2002Filter = (InlineResponse2002Filter) o;
		return Objects.equals(vnfInstanceSubscriptionFilter, inlineResponse2002Filter.vnfInstanceSubscriptionFilter) &&
				Objects.equals(notificationTypes, inlineResponse2002Filter.notificationTypes) &&
				Objects.equals(operationTypes, inlineResponse2002Filter.operationTypes) &&
				Objects.equals(operationStates, inlineResponse2002Filter.operationStates);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vnfInstanceSubscriptionFilter, notificationTypes, operationTypes, operationStates);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class InlineResponse2002Filter {\n");

		sb.append("    vnfInstanceSubscriptionFilter: ").append(toIndentedString(vnfInstanceSubscriptionFilter)).append("\n");
		sb.append("    notificationTypes: ").append(toIndentedString(notificationTypes)).append("\n");
		sb.append("    operationTypes: ").append(toIndentedString(operationTypes)).append("\n");
		sb.append("    operationStates: ").append(toIndentedString(operationStates)).append("\n");
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
