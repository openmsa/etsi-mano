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
package com.ubiqube.etsi.mano.nfvo.v331.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.LcmOpNameForChangeNotificationType;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.LcmOpOccStatusForChangeNotificationType;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.LcmOperationStateType;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.NsComponentType;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.NsInstanceSubscriptionFilter;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.NsLcmOpType;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents a subscription filter related to notifications about  NS lifecycle changes. It shall comply with the provisions defined in Table 6.5.3.8-1. At a particular nesting level in the filter structure, the following applies:  All attributes shall match in order for the filter to match (logical \&quot;and\&quot; between different filter attributes).  If an attribute is an array, the attribute shall match if at least one of the values in the array matches (logical \&quot;or\&quot; between the values of one filter attribute). 
 */
@Schema(description = "This type represents a subscription filter related to notifications about  NS lifecycle changes. It shall comply with the provisions defined in Table 6.5.3.8-1. At a particular nesting level in the filter structure, the following applies:  All attributes shall match in order for the filter to match (logical \"and\" between different filter attributes).  If an attribute is an array, the attribute shall match if at least one of the values in the array matches (logical \"or\" between the values of one filter attribute). ")
@Validated


public class LifecycleChangeNotificationsFilter   {
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

    private String value;

    NotificationTypesEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static NotificationTypesEnum fromValue(String text) {
      for (NotificationTypesEnum b : NotificationTypesEnum.values()) {
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

  public LifecycleChangeNotificationsFilter nsInstanceSubscriptionFilter(NsInstanceSubscriptionFilter nsInstanceSubscriptionFilter) {
    this.nsInstanceSubscriptionFilter = nsInstanceSubscriptionFilter;
    return this;
  }

  /**
   * Get nsInstanceSubscriptionFilter
   * @return nsInstanceSubscriptionFilter
   **/
  @Schema(description = "")
  
    @Valid
    public NsInstanceSubscriptionFilter getNsInstanceSubscriptionFilter() {
    return nsInstanceSubscriptionFilter;
  }

  public void setNsInstanceSubscriptionFilter(NsInstanceSubscriptionFilter nsInstanceSubscriptionFilter) {
    this.nsInstanceSubscriptionFilter = nsInstanceSubscriptionFilter;
  }

  public LifecycleChangeNotificationsFilter notificationTypes(List<NotificationTypesEnum> notificationTypes) {
    this.notificationTypes = notificationTypes;
    return this;
  }

  public LifecycleChangeNotificationsFilter addNotificationTypesItem(NotificationTypesEnum notificationTypesItem) {
    if (this.notificationTypes == null) {
      this.notificationTypes = new ArrayList<>();
    }
    this.notificationTypes.add(notificationTypesItem);
    return this;
  }

  /**
   * Match particular notification types. Permitted values: - NsLcmOperationOccurenceNotification - NsIdentifierCreationNotification - NsIdentifierDeletionNotification - NsChangeNotification 
   * @return notificationTypes
   **/
  @Schema(description = "Match particular notification types. Permitted values: - NsLcmOperationOccurenceNotification - NsIdentifierCreationNotification - NsIdentifierDeletionNotification - NsChangeNotification ")
  
    public List<NotificationTypesEnum> getNotificationTypes() {
    return notificationTypes;
  }

  public void setNotificationTypes(List<NotificationTypesEnum> notificationTypes) {
    this.notificationTypes = notificationTypes;
  }

  public LifecycleChangeNotificationsFilter operationTypes(List<NsLcmOpType> operationTypes) {
    this.operationTypes = operationTypes;
    return this;
  }

  public LifecycleChangeNotificationsFilter addOperationTypesItem(NsLcmOpType operationTypesItem) {
    if (this.operationTypes == null) {
      this.operationTypes = new ArrayList<>();
    }
    this.operationTypes.add(operationTypesItem);
    return this;
  }

  /**
   * Match particular NS lifecycle operation types for the notification of type NsLcmOperationOccurrenceNotification. May be present if the \"notificationTypes\" attribute contains the value \"NsLcmOperationOccurrenceNotification\", and shall be absent otherwise. 
   * @return operationTypes
   **/
  @Schema(description = "Match particular NS lifecycle operation types for the notification of type NsLcmOperationOccurrenceNotification. May be present if the \"notificationTypes\" attribute contains the value \"NsLcmOperationOccurrenceNotification\", and shall be absent otherwise. ")
      @Valid
    public List<NsLcmOpType> getOperationTypes() {
    return operationTypes;
  }

  public void setOperationTypes(List<NsLcmOpType> operationTypes) {
    this.operationTypes = operationTypes;
  }

  public LifecycleChangeNotificationsFilter operationStates(List<LcmOperationStateType> operationStates) {
    this.operationStates = operationStates;
    return this;
  }

  public LifecycleChangeNotificationsFilter addOperationStatesItem(LcmOperationStateType operationStatesItem) {
    if (this.operationStates == null) {
      this.operationStates = new ArrayList<>();
    }
    this.operationStates.add(operationStatesItem);
    return this;
  }

  /**
   * Match particular LCM operation state values as reported in notifications of type NsLcmOperationOccurrenceNotification. May be present if the \"notificationTypes\" attribute contains the value \"NsLcmOperationOccurrenceNotification\", and shall be absent otherwise. 
   * @return operationStates
   **/
  @Schema(description = "Match particular LCM operation state values as reported in notifications of type NsLcmOperationOccurrenceNotification. May be present if the \"notificationTypes\" attribute contains the value \"NsLcmOperationOccurrenceNotification\", and shall be absent otherwise. ")
      @Valid
    public List<LcmOperationStateType> getOperationStates() {
    return operationStates;
  }

  public void setOperationStates(List<LcmOperationStateType> operationStates) {
    this.operationStates = operationStates;
  }

  public LifecycleChangeNotificationsFilter nsComponentTypes(List<NsComponentType> nsComponentTypes) {
    this.nsComponentTypes = nsComponentTypes;
    return this;
  }

  public LifecycleChangeNotificationsFilter addNsComponentTypesItem(NsComponentType nsComponentTypesItem) {
    if (this.nsComponentTypes == null) {
      this.nsComponentTypes = new ArrayList<>();
    }
    this.nsComponentTypes.add(nsComponentTypesItem);
    return this;
  }

  /**
   * Match particular NS component types for the notification of type NsChangeNotification. May be present if the \"notificationTypes\" attribute contains the value \"NsChang. 
   * @return nsComponentTypes
   **/
  @Schema(description = "Match particular NS component types for the notification of type NsChangeNotification. May be present if the \"notificationTypes\" attribute contains the value \"NsChang. ")
      @Valid
    public List<NsComponentType> getNsComponentTypes() {
    return nsComponentTypes;
  }

  public void setNsComponentTypes(List<NsComponentType> nsComponentTypes) {
    this.nsComponentTypes = nsComponentTypes;
  }

  public LifecycleChangeNotificationsFilter lcmOpNameImpactingNsComponent(List<LcmOpNameForChangeNotificationType> lcmOpNameImpactingNsComponent) {
    this.lcmOpNameImpactingNsComponent = lcmOpNameImpactingNsComponent;
    return this;
  }

  public LifecycleChangeNotificationsFilter addLcmOpNameImpactingNsComponentItem(LcmOpNameForChangeNotificationType lcmOpNameImpactingNsComponentItem) {
    if (this.lcmOpNameImpactingNsComponent == null) {
      this.lcmOpNameImpactingNsComponent = new ArrayList<>();
    }
    this.lcmOpNameImpactingNsComponent.add(lcmOpNameImpactingNsComponentItem);
    return this;
  }

  /**
   * Match particular LCM operation names for the notification of type NsChangeNotification. May be present if the \"notificationTypes\" attribute contains the value \"NsChangeNotification\", and shall be absent otherwise. 
   * @return lcmOpNameImpactingNsComponent
   **/
  @Schema(description = "Match particular LCM operation names for the notification of type NsChangeNotification. May be present if the \"notificationTypes\" attribute contains the value \"NsChangeNotification\", and shall be absent otherwise. ")
      @Valid
    public List<LcmOpNameForChangeNotificationType> getLcmOpNameImpactingNsComponent() {
    return lcmOpNameImpactingNsComponent;
  }

  public void setLcmOpNameImpactingNsComponent(List<LcmOpNameForChangeNotificationType> lcmOpNameImpactingNsComponent) {
    this.lcmOpNameImpactingNsComponent = lcmOpNameImpactingNsComponent;
  }

  public LifecycleChangeNotificationsFilter lcmOpOccStatusImpactingNsComponent(List<LcmOpOccStatusForChangeNotificationType> lcmOpOccStatusImpactingNsComponent) {
    this.lcmOpOccStatusImpactingNsComponent = lcmOpOccStatusImpactingNsComponent;
    return this;
  }

  public LifecycleChangeNotificationsFilter addLcmOpOccStatusImpactingNsComponentItem(LcmOpOccStatusForChangeNotificationType lcmOpOccStatusImpactingNsComponentItem) {
    if (this.lcmOpOccStatusImpactingNsComponent == null) {
      this.lcmOpOccStatusImpactingNsComponent = new ArrayList<>();
    }
    this.lcmOpOccStatusImpactingNsComponent.add(lcmOpOccStatusImpactingNsComponentItem);
    return this;
  }

  /**
   * Match particular LCM operation status values as reported in notifications of type NsChangeNotification. May be present if the \"notificationTypes\" attribute contains the value \"NsChangeNotification\", and shall be absent otherwise. 
   * @return lcmOpOccStatusImpactingNsComponent
   **/
  @Schema(description = "Match particular LCM operation status values as reported in notifications of type NsChangeNotification. May be present if the \"notificationTypes\" attribute contains the value \"NsChangeNotification\", and shall be absent otherwise. ")
      @Valid
    public List<LcmOpOccStatusForChangeNotificationType> getLcmOpOccStatusImpactingNsComponent() {
    return lcmOpOccStatusImpactingNsComponent;
  }

  public void setLcmOpOccStatusImpactingNsComponent(List<LcmOpOccStatusForChangeNotificationType> lcmOpOccStatusImpactingNsComponent) {
    this.lcmOpOccStatusImpactingNsComponent = lcmOpOccStatusImpactingNsComponent;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LifecycleChangeNotificationsFilter lifecycleChangeNotificationsFilter = (LifecycleChangeNotificationsFilter) o;
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
    StringBuilder sb = new StringBuilder();
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
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
