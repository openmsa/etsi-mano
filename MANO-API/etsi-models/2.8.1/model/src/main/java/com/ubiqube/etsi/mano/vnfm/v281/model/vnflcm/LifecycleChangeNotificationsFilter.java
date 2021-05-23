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
package com.ubiqube.etsi.mano.vnfm.v281.model.vnflcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.vnfm.v281.model.vnflcm.LcmOperationStateType;
import com.ubiqube.etsi.mano.vnfm.v281.model.vnflcm.LcmOperationType;
import com.ubiqube.etsi.mano.vnfm.v281.model.vnflcm.VnfInstanceSubscriptionFilter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents a subscription filter related to notifications about VNF lifecycle changes. At a particular nesting level in the filter structure, the following applies: All attributes shall match in order for the filter to match (logical \&quot;and\&quot; between different filter attributes). If an attribute is an array, the attribute shall match if at least one of the values in the array matches (logical \&quot;or\&quot; between the values of one filter attribute). 
 */
@ApiModel(description = "This type represents a subscription filter related to notifications about VNF lifecycle changes. At a particular nesting level in the filter structure, the following applies: All attributes shall match in order for the filter to match (logical \"and\" between different filter attributes). If an attribute is an array, the attribute shall match if at least one of the values in the array matches (logical \"or\" between the values of one filter attribute). ")
@Validated

public class LifecycleChangeNotificationsFilter   {
  @JsonProperty("vnfInstanceSubscriptionFilter")
  private VnfInstanceSubscriptionFilter vnfInstanceSubscriptionFilter = null;

  /**
   * Gets or Sets notificationTypes
   */
  public enum NotificationTypesEnum {
    VNFLCMOPERATIONOCCURRENCENOTIFICATION("VnfLcmOperationOccurrenceNotification"),
    
    VNFIDENTIFIERCREATIONNOTIFICATION("VnfIdentifierCreationNotification"),
    
    VNFIDENTIFIERDELETIONNOTIFICATION("VnfIdentifierDeletionNotification");

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
  private List<LcmOperationType> operationTypes = null;

  @JsonProperty("operationStates")
  @Valid
  private List<LcmOperationStateType> operationStates = null;

  public LifecycleChangeNotificationsFilter vnfInstanceSubscriptionFilter(VnfInstanceSubscriptionFilter vnfInstanceSubscriptionFilter) {
    this.vnfInstanceSubscriptionFilter = vnfInstanceSubscriptionFilter;
    return this;
  }

  /**
   * Filter criteria to select VNF instances about which to notify. 
   * @return vnfInstanceSubscriptionFilter
  **/
  @ApiModelProperty(value = "Filter criteria to select VNF instances about which to notify. ")

  @Valid

  public VnfInstanceSubscriptionFilter getVnfInstanceSubscriptionFilter() {
    return vnfInstanceSubscriptionFilter;
  }

  public void setVnfInstanceSubscriptionFilter(VnfInstanceSubscriptionFilter vnfInstanceSubscriptionFilter) {
    this.vnfInstanceSubscriptionFilter = vnfInstanceSubscriptionFilter;
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
   * Match particular notification types. Permitted values: * VnfLcmOperationOccurrenceNotification * VnfIdentifierCreationNotification * VnfIdentifierDeletionNotification The permitted values of the \"notificationTypes\" attribute are spelled exactly as the names of the notification types to facilitate automated code generation systems. 
   * @return notificationTypes
  **/
  @ApiModelProperty(value = "Match particular notification types. Permitted values: * VnfLcmOperationOccurrenceNotification * VnfIdentifierCreationNotification * VnfIdentifierDeletionNotification The permitted values of the \"notificationTypes\" attribute are spelled exactly as the names of the notification types to facilitate automated code generation systems. ")


  public List<NotificationTypesEnum> getNotificationTypes() {
    return notificationTypes;
  }

  public void setNotificationTypes(List<NotificationTypesEnum> notificationTypes) {
    this.notificationTypes = notificationTypes;
  }

  public LifecycleChangeNotificationsFilter operationTypes(List<LcmOperationType> operationTypes) {
    this.operationTypes = operationTypes;
    return this;
  }

  public LifecycleChangeNotificationsFilter addOperationTypesItem(LcmOperationType operationTypesItem) {
    if (this.operationTypes == null) {
      this.operationTypes = new ArrayList<>();
    }
    this.operationTypes.add(operationTypesItem);
    return this;
  }

  /**
   * Match particular VNF lifecycle operation types for the notification of type VnfLcmOperationOccurrenceNotification. May be present if the \"notificationTypes\" attribute contains the value \"VnfLcmOperationOccurrenceNotification\", and shall be absent otherwise. 
   * @return operationTypes
  **/
  @ApiModelProperty(value = "Match particular VNF lifecycle operation types for the notification of type VnfLcmOperationOccurrenceNotification. May be present if the \"notificationTypes\" attribute contains the value \"VnfLcmOperationOccurrenceNotification\", and shall be absent otherwise. ")

  @Valid

  public List<LcmOperationType> getOperationTypes() {
    return operationTypes;
  }

  public void setOperationTypes(List<LcmOperationType> operationTypes) {
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
   * Match particular LCM operation state values as reported in notifications of type VnfLcmOperationOccurrenceNotification. May be present if the \"notificationTypes\" attribute contains the value \"VnfLcmOperationOccurrenceNotification\", and shall be absent otherwise. 
   * @return operationStates
  **/
  @ApiModelProperty(value = "Match particular LCM operation state values as reported in notifications of type VnfLcmOperationOccurrenceNotification. May be present if the \"notificationTypes\" attribute contains the value \"VnfLcmOperationOccurrenceNotification\", and shall be absent otherwise. ")

  @Valid

  public List<LcmOperationStateType> getOperationStates() {
    return operationStates;
  }

  public void setOperationStates(List<LcmOperationStateType> operationStates) {
    this.operationStates = operationStates;
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
    return Objects.equals(this.vnfInstanceSubscriptionFilter, lifecycleChangeNotificationsFilter.vnfInstanceSubscriptionFilter) &&
        Objects.equals(this.notificationTypes, lifecycleChangeNotificationsFilter.notificationTypes) &&
        Objects.equals(this.operationTypes, lifecycleChangeNotificationsFilter.operationTypes) &&
        Objects.equals(this.operationStates, lifecycleChangeNotificationsFilter.operationStates);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vnfInstanceSubscriptionFilter, notificationTypes, operationTypes, operationStates);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LifecycleChangeNotificationsFilter {\n");
    
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

