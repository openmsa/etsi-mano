package com.ubiqube.etsi.mano.model.nsperfo.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
  * This type represents a filter that can be used to subscribe for  notifications related to performance management events. It shall comply with the provisions defined in Table 7.5.3.2-1. At a particular nesting level in the filter structure, the following applies:  All attributes shall match in order for the filter to match (logical \"and\" between different filter attributes).  If an attribute is an array, the attribute shall match if at least one of the values in the array matches (logical \"or\" between the values of one filter attribute). 
 **/
@ApiModel(description="This type represents a filter that can be used to subscribe for  notifications related to performance management events. It shall comply with the provisions defined in Table 7.5.3.2-1. At a particular nesting level in the filter structure, the following applies:  All attributes shall match in order for the filter to match (logical \"and\" between different filter attributes).  If an attribute is an array, the attribute shall match if at least one of the values in the array matches (logical \"or\" between the values of one filter attribute). ")
public class SubscriptionsPmSubscriptionFilter  {
  
  @ApiModelProperty(value = "")
  @Valid
  private SubscriptionsPmSubscriptionFilterNsInstanceSubscriptionFilter nsInstanceSubscriptionFilter = null;


@XmlType(name="NotificationTypesEnum")
@XmlEnum(String.class)
public enum NotificationTypesEnum {

@XmlEnumValue("ThresholdCrossedNotification") THRESHOLDCROSSEDNOTIFICATION(String.valueOf("ThresholdCrossedNotification")), @XmlEnumValue("PerformanceInformationAvailableNotification") PERFORMANCEINFORMATIONAVAILABLENOTIFICATION(String.valueOf("PerformanceInformationAvailableNotification"));


    private String value;

    NotificationTypesEnum (String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static NotificationTypesEnum fromValue(String v) {
        for (NotificationTypesEnum b : NotificationTypesEnum.values()) {
            if (String.valueOf(b.value).equals(v)) {
                return b;
            }
        }
        return null;
    }
}

  @ApiModelProperty(value = "Match particular notification types. Permitted values: - ThresholdCrossedNotification - PerformanceInformationAvailableNotification The permitted values of the \"notificationTypes\" attribute are spelled exactly as the names of the notification types to facilitate automated code generation systems.           ")
 /**
   * Match particular notification types. Permitted values: - ThresholdCrossedNotification - PerformanceInformationAvailableNotification The permitted values of the \"notificationTypes\" attribute are spelled exactly as the names of the notification types to facilitate automated code generation systems.           
  **/
  private List<NotificationTypesEnum> notificationTypes = null;
 /**
   * Get nsInstanceSubscriptionFilter
   * @return nsInstanceSubscriptionFilter
  **/
  @JsonProperty("nsInstanceSubscriptionFilter")
  public SubscriptionsPmSubscriptionFilterNsInstanceSubscriptionFilter getNsInstanceSubscriptionFilter() {
    return nsInstanceSubscriptionFilter;
  }

  public void setNsInstanceSubscriptionFilter(SubscriptionsPmSubscriptionFilterNsInstanceSubscriptionFilter nsInstanceSubscriptionFilter) {
    this.nsInstanceSubscriptionFilter = nsInstanceSubscriptionFilter;
  }

  public SubscriptionsPmSubscriptionFilter nsInstanceSubscriptionFilter(SubscriptionsPmSubscriptionFilterNsInstanceSubscriptionFilter nsInstanceSubscriptionFilter) {
    this.nsInstanceSubscriptionFilter = nsInstanceSubscriptionFilter;
    return this;
  }

 /**
   * Match particular notification types. Permitted values: - ThresholdCrossedNotification - PerformanceInformationAvailableNotification The permitted values of the \&quot;notificationTypes\&quot; attribute are spelled exactly as the names of the notification types to facilitate automated code generation systems.           
   * @return notificationTypes
  **/
  @JsonProperty("notificationTypes")
  public List<NotificationTypesEnum> getNotificationTypes() {
    return notificationTypes;
  }

  public void setNotificationTypes(List<NotificationTypesEnum> notificationTypes) {
    this.notificationTypes = notificationTypes;
  }

  public SubscriptionsPmSubscriptionFilter notificationTypes(List<NotificationTypesEnum> notificationTypes) {
    this.notificationTypes = notificationTypes;
    return this;
  }

  public SubscriptionsPmSubscriptionFilter addNotificationTypesItem(NotificationTypesEnum notificationTypesItem) {
    this.notificationTypes.add(notificationTypesItem);
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubscriptionsPmSubscriptionFilter {\n");
    
    sb.append("    nsInstanceSubscriptionFilter: ").append(toIndentedString(nsInstanceSubscriptionFilter)).append("\n");
    sb.append("    notificationTypes: ").append(toIndentedString(notificationTypes)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private static String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

