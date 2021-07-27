package com.ubiqube.etsi.mano.policy.v341.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.policy.v341.model.PolicyOperationType;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents a subscription filter related to notifications about policy changes and policy conflicts.  It shall comply with the provisions defined in table 5.6.3.2-1. At a particular nesting level in the filter structure, the following applies: All attributes shall match in  order for the filter to match (logical \&quot;and\&quot; between different filter attributes). If an attribute is an array,  the attribute shall match if at least one of the values in the array matches (logical \&quot;or\&quot; between the values  of one filter attribute). 
 */
@Schema(description = "This type represents a subscription filter related to notifications about policy changes and policy conflicts.  It shall comply with the provisions defined in table 5.6.3.2-1. At a particular nesting level in the filter structure, the following applies: All attributes shall match in  order for the filter to match (logical \"and\" between different filter attributes). If an attribute is an array,  the attribute shall match if at least one of the values in the array matches (logical \"or\" between the values  of one filter attribute). ")
@Validated


public class PolicyNotificationsFilter   {
  /**
   * Gets or Sets notificationTypes
   */
  public enum NotificationTypesEnum {
    POLICYCHANGENOTIFICATION("PolicyChangeNotification"),
    
    POLICYCONFLICTNOTIFICATION("PolicyConflictNotification");

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

  @JsonProperty("policyIds")
  @Valid
  private List<String> policyIds = null;

  @JsonProperty("changeTypes")
  @Valid
  private List<PolicyOperationType> changeTypes = null;

  public PolicyNotificationsFilter notificationTypes(List<NotificationTypesEnum> notificationTypes) {
    this.notificationTypes = notificationTypes;
    return this;
  }

  public PolicyNotificationsFilter addNotificationTypesItem(NotificationTypesEnum notificationTypesItem) {
    if (this.notificationTypes == null) {
      this.notificationTypes = new ArrayList<>();
    }
    this.notificationTypes.add(notificationTypesItem);
    return this;
  }

  /**
   * Match particular notification types. Permitted values: - PolicyChangeNotification - PolicyConflictNotification The permitted values of the \"notificationTypes\" attribute are spelled exactly as the names of the notification  types to facilitate automated code generation systems. 
   * @return notificationTypes
   **/
  @Schema(description = "Match particular notification types. Permitted values: - PolicyChangeNotification - PolicyConflictNotification The permitted values of the \"notificationTypes\" attribute are spelled exactly as the names of the notification  types to facilitate automated code generation systems. ")
  
    public List<NotificationTypesEnum> getNotificationTypes() {
    return notificationTypes;
  }

  public void setNotificationTypes(List<NotificationTypesEnum> notificationTypes) {
    this.notificationTypes = notificationTypes;
  }

  public PolicyNotificationsFilter policyIds(List<String> policyIds) {
    this.policyIds = policyIds;
    return this;
  }

  public PolicyNotificationsFilter addPolicyIdsItem(String policyIdsItem) {
    if (this.policyIds == null) {
      this.policyIds = new ArrayList<>();
    }
    this.policyIds.add(policyIdsItem);
    return this;
  }

  /**
   * Match particular policy identifiers. For \"PolicyConflictNotification\", the notification is sent if any of the policies specified in the subscription  is impacted by the conflict, as defined in clause 5.6.2.8. 
   * @return policyIds
   **/
  @Schema(description = "Match particular policy identifiers. For \"PolicyConflictNotification\", the notification is sent if any of the policies specified in the subscription  is impacted by the conflict, as defined in clause 5.6.2.8. ")
  
    public List<String> getPolicyIds() {
    return policyIds;
  }

  public void setPolicyIds(List<String> policyIds) {
    this.policyIds = policyIds;
  }

  public PolicyNotificationsFilter changeTypes(List<PolicyOperationType> changeTypes) {
    this.changeTypes = changeTypes;
    return this;
  }

  public PolicyNotificationsFilter addChangeTypesItem(PolicyOperationType changeTypesItem) {
    if (this.changeTypes == null) {
      this.changeTypes = new ArrayList<>();
    }
    this.changeTypes.add(changeTypesItem);
    return this;
  }

  /**
   * Match particular policy management operation types that cause the change of the policy. 
   * @return changeTypes
   **/
  @Schema(description = "Match particular policy management operation types that cause the change of the policy. ")
      @Valid
    public List<PolicyOperationType> getChangeTypes() {
    return changeTypes;
  }

  public void setChangeTypes(List<PolicyOperationType> changeTypes) {
    this.changeTypes = changeTypes;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PolicyNotificationsFilter policyNotificationsFilter = (PolicyNotificationsFilter) o;
    return Objects.equals(this.notificationTypes, policyNotificationsFilter.notificationTypes) &&
        Objects.equals(this.policyIds, policyNotificationsFilter.policyIds) &&
        Objects.equals(this.changeTypes, policyNotificationsFilter.changeTypes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(notificationTypes, policyIds, changeTypes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PolicyNotificationsFilter {\n");
    
    sb.append("    notificationTypes: ").append(toIndentedString(notificationTypes)).append("\n");
    sb.append("    policyIds: ").append(toIndentedString(policyIds)).append("\n");
    sb.append("    changeTypes: ").append(toIndentedString(changeTypes)).append("\n");
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
