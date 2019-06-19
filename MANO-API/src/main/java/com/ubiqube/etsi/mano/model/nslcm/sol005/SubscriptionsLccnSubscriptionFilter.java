package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
  * This type represents a subscription filter related to notifications about  NS lifecycle changes. It shall comply with the provisions defined in Table 6.5.3.8-1. At a particular nesting level in the filter structure, the following applies:  All attributes shall match in order for the filter to match (logical \"and\" between different filter attributes).  If an attribute is an array, the attribute shall match if at least one of the values in the array matches (logical \"or\" between the values of one filter attribute). 
 **/
@ApiModel(description="This type represents a subscription filter related to notifications about  NS lifecycle changes. It shall comply with the provisions defined in Table 6.5.3.8-1. At a particular nesting level in the filter structure, the following applies:  All attributes shall match in order for the filter to match (logical \"and\" between different filter attributes).  If an attribute is an array, the attribute shall match if at least one of the values in the array matches (logical \"or\" between the values of one filter attribute). ")
public class SubscriptionsLccnSubscriptionFilter  {
  
  @ApiModelProperty(value = "")
  @Valid
  private SubscriptionsLccnSubscriptionFilterNsInstanceSubscriptionFilter nsInstanceSubscriptionFilter = null;


@XmlType(name="NotificationTypesEnum")
@XmlEnum(String.class)
public enum NotificationTypesEnum {

@XmlEnumValue("NsLcmOperationOccurenceNotification") NSLCMOPERATIONOCCURENCENOTIFICATION(String.valueOf("NsLcmOperationOccurenceNotification")), @XmlEnumValue("NsIdentifierCreationNotification") NSIDENTIFIERCREATIONNOTIFICATION(String.valueOf("NsIdentifierCreationNotification")), @XmlEnumValue("NsIdentifierDeletionNotification") NSIDENTIFIERDELETIONNOTIFICATION(String.valueOf("NsIdentifierDeletionNotification")), @XmlEnumValue("NsChangeNotification") NSCHANGENOTIFICATION(String.valueOf("NsChangeNotification"));


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

  @ApiModelProperty(value = "Match particular notification types. Permitted values: - NsLcmOperationOccurenceNotification - NsIdentifierCreationNotification - NsIdentifierDeletionNotification - NsChangeNotification ")
 /**
   * Match particular notification types. Permitted values: - NsLcmOperationOccurenceNotification - NsIdentifierCreationNotification - NsIdentifierDeletionNotification - NsChangeNotification 
  **/
  private List<NotificationTypesEnum> notificationTypes = null;


@XmlType(name="OperationTypesEnum")
@XmlEnum(String.class)
public enum OperationTypesEnum {

@XmlEnumValue("INSTANTIATE") INSTANTIATE(String.valueOf("INSTANTIATE")), @XmlEnumValue("SCALE") SCALE(String.valueOf("SCALE")), @XmlEnumValue("UPDATE") UPDATE(String.valueOf("UPDATE")), @XmlEnumValue("TERMINATE") TERMINATE(String.valueOf("TERMINATE")), @XmlEnumValue("HEAL") HEAL(String.valueOf("HEAL"));


    private String value;

    OperationTypesEnum (String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static OperationTypesEnum fromValue(String v) {
        for (OperationTypesEnum b : OperationTypesEnum.values()) {
            if (String.valueOf(b.value).equals(v)) {
                return b;
            }
        }
        return null;
    }
}

  @ApiModelProperty(value = "Match particular NS lifecycle operation types for the notification of type NsLcmOperationOccurrenceNotification. May be present if the \"notificationTypes\" attribute contains the value \"NsLcmOperationOccurrenceNotification\", and shall be absent otherwise. ")
 /**
   * Match particular NS lifecycle operation types for the notification of type NsLcmOperationOccurrenceNotification. May be present if the \"notificationTypes\" attribute contains the value \"NsLcmOperationOccurrenceNotification\", and shall be absent otherwise. 
  **/
  private List<OperationTypesEnum> operationTypes = null;


@XmlType(name="OperationStatesEnum")
@XmlEnum(String.class)
public enum OperationStatesEnum {

@XmlEnumValue("PROCESSING") PROCESSING(String.valueOf("PROCESSING")), @XmlEnumValue("COMPLETED") COMPLETED(String.valueOf("COMPLETED")), @XmlEnumValue("PARTIALLY_COMPLETED") PARTIALLY_COMPLETED(String.valueOf("PARTIALLY_COMPLETED")), @XmlEnumValue("FAILED_TEMP") FAILED_TEMP(String.valueOf("FAILED_TEMP")), @XmlEnumValue("FAILED") FAILED(String.valueOf("FAILED")), @XmlEnumValue("ROLLING_BACK") ROLLING_BACK(String.valueOf("ROLLING_BACK")), @XmlEnumValue("ROLLED_BACK") ROLLED_BACK(String.valueOf("ROLLED_BACK"));


    private String value;

    OperationStatesEnum (String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static OperationStatesEnum fromValue(String v) {
        for (OperationStatesEnum b : OperationStatesEnum.values()) {
            if (String.valueOf(b.value).equals(v)) {
                return b;
            }
        }
        return null;
    }
}

  @ApiModelProperty(value = "Match particular LCM operation state values as reported in notifications of type NsLcmOperationOccurrenceNotification. May be present if the \"notificationTypes\" attribute contains the value \"NsLcmOperationOccurrenceNotification\", and shall be absent otherwise. ")
 /**
   * Match particular LCM operation state values as reported in notifications of type NsLcmOperationOccurrenceNotification. May be present if the \"notificationTypes\" attribute contains the value \"NsLcmOperationOccurrenceNotification\", and shall be absent otherwise. 
  **/
  private List<OperationStatesEnum> operationStates = null;


@XmlType(name="NsComponentTypesEnum")
@XmlEnum(String.class)
public enum NsComponentTypesEnum {

@XmlEnumValue("VNF") VNF(String.valueOf("VNF")), @XmlEnumValue("PNF") PNF(String.valueOf("PNF")), @XmlEnumValue("NS") NS(String.valueOf("NS"));


    private String value;

    NsComponentTypesEnum (String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static NsComponentTypesEnum fromValue(String v) {
        for (NsComponentTypesEnum b : NsComponentTypesEnum.values()) {
            if (String.valueOf(b.value).equals(v)) {
                return b;
            }
        }
        return null;
    }
}

  @ApiModelProperty(value = "Match particular NS component types for the notification of type NsChangeNotification. May be present if the \"notificationTypes\" attribute contains the value \"NsChang. ")
 /**
   * Match particular NS component types for the notification of type NsChangeNotification. May be present if the \"notificationTypes\" attribute contains the value \"NsChang. 
  **/
  private List<NsComponentTypesEnum> nsComponentTypes = null;


@XmlType(name="LcmOpNameImpactingNsComponentEnum")
@XmlEnum(String.class)
public enum LcmOpNameImpactingNsComponentEnum {

@XmlEnumValue("VNF_INSTANTIATE") VNF_INSTANTIATE(String.valueOf("VNF_INSTANTIATE")), @XmlEnumValue("VNF_SCALE") VNF_SCALE(String.valueOf("VNF_SCALE")), @XmlEnumValue("VNF_SCALE_TO_LEVEL") VNF_SCALE_TO_LEVEL(String.valueOf("VNF_SCALE_TO_LEVEL")), @XmlEnumValue("VNF_CHANGE_FLAVOUR") VNF_CHANGE_FLAVOUR(String.valueOf("VNF_CHANGE_FLAVOUR")), @XmlEnumValue("VNF_TERMINATE") VNF_TERMINATE(String.valueOf("VNF_TERMINATE")), @XmlEnumValue("VNF_HEAL") VNF_HEAL(String.valueOf("VNF_HEAL")), @XmlEnumValue("VNF_OPERATE") VNF_OPERATE(String.valueOf("VNF_OPERATE")), @XmlEnumValue("VNF_CHANGE_EXT_CONN") VNF_CHANGE_EXT_CONN(String.valueOf("VNF_CHANGE_EXT_CONN")), @XmlEnumValue("VNF_MODIFY_INFO") VNF_MODIFY_INFO(String.valueOf("VNF_MODIFY_INFO")), @XmlEnumValue("NS_INSTANTIATE") NS_INSTANTIATE(String.valueOf("NS_INSTANTIATE")), @XmlEnumValue("NS_SCALE") NS_SCALE(String.valueOf("NS_SCALE")), @XmlEnumValue("NS_UPDATE") NS_UPDATE(String.valueOf("NS_UPDATE")), @XmlEnumValue("NS_TERMINATE") NS_TERMINATE(String.valueOf("NS_TERMINATE")), @XmlEnumValue("NS_HEAL") NS_HEAL(String.valueOf("NS_HEAL"));


    private String value;

    LcmOpNameImpactingNsComponentEnum (String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static LcmOpNameImpactingNsComponentEnum fromValue(String v) {
        for (LcmOpNameImpactingNsComponentEnum b : LcmOpNameImpactingNsComponentEnum.values()) {
            if (String.valueOf(b.value).equals(v)) {
                return b;
            }
        }
        return null;
    }
}

  @ApiModelProperty(value = "Match particular LCM operation names for the notification of type NsChangeNotification. May be present if the \"notificationTypes\" attribute contains the value \"NsChangeNotification\", and shall be absent otherwise. ")
 /**
   * Match particular LCM operation names for the notification of type NsChangeNotification. May be present if the \"notificationTypes\" attribute contains the value \"NsChangeNotification\", and shall be absent otherwise. 
  **/
  private List<LcmOpNameImpactingNsComponentEnum> lcmOpNameImpactingNsComponent = null;


@XmlType(name="LcmOpOccStatusImpactingNsComponentEnum")
@XmlEnum(String.class)
public enum LcmOpOccStatusImpactingNsComponentEnum {

@XmlEnumValue("START") START(String.valueOf("START")), @XmlEnumValue("COMPLETED") COMPLETED(String.valueOf("COMPLETED")), @XmlEnumValue("PARTIALLY_COMPLETED") PARTIALLY_COMPLETED(String.valueOf("PARTIALLY_COMPLETED")), @XmlEnumValue("FAILED") FAILED(String.valueOf("FAILED")), @XmlEnumValue("ROLLED_BACK") ROLLED_BACK(String.valueOf("ROLLED_BACK"));


    private String value;

    LcmOpOccStatusImpactingNsComponentEnum (String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static LcmOpOccStatusImpactingNsComponentEnum fromValue(String v) {
        for (LcmOpOccStatusImpactingNsComponentEnum b : LcmOpOccStatusImpactingNsComponentEnum.values()) {
            if (String.valueOf(b.value).equals(v)) {
                return b;
            }
        }
        return null;
    }
}

  @ApiModelProperty(value = "Match particular LCM operation status values as reported in notifications of type NsChangeNotification. May be present if the \"notificationTypes\" attribute contains the value \"NsChangeNotification\", and shall be absent otherwise. ")
 /**
   * Match particular LCM operation status values as reported in notifications of type NsChangeNotification. May be present if the \"notificationTypes\" attribute contains the value \"NsChangeNotification\", and shall be absent otherwise. 
  **/
  private List<LcmOpOccStatusImpactingNsComponentEnum> lcmOpOccStatusImpactingNsComponent = null;
 /**
   * Get nsInstanceSubscriptionFilter
   * @return nsInstanceSubscriptionFilter
  **/
  @JsonProperty("nsInstanceSubscriptionFilter")
  public SubscriptionsLccnSubscriptionFilterNsInstanceSubscriptionFilter getNsInstanceSubscriptionFilter() {
    return nsInstanceSubscriptionFilter;
  }

  public void setNsInstanceSubscriptionFilter(SubscriptionsLccnSubscriptionFilterNsInstanceSubscriptionFilter nsInstanceSubscriptionFilter) {
    this.nsInstanceSubscriptionFilter = nsInstanceSubscriptionFilter;
  }

  public SubscriptionsLccnSubscriptionFilter nsInstanceSubscriptionFilter(SubscriptionsLccnSubscriptionFilterNsInstanceSubscriptionFilter nsInstanceSubscriptionFilter) {
    this.nsInstanceSubscriptionFilter = nsInstanceSubscriptionFilter;
    return this;
  }

 /**
   * Match particular notification types. Permitted values: - NsLcmOperationOccurenceNotification - NsIdentifierCreationNotification - NsIdentifierDeletionNotification - NsChangeNotification 
   * @return notificationTypes
  **/
  @JsonProperty("notificationTypes")
  public List<NotificationTypesEnum> getNotificationTypes() {
    return notificationTypes;
  }

  public void setNotificationTypes(List<NotificationTypesEnum> notificationTypes) {
    this.notificationTypes = notificationTypes;
  }

  public SubscriptionsLccnSubscriptionFilter notificationTypes(List<NotificationTypesEnum> notificationTypes) {
    this.notificationTypes = notificationTypes;
    return this;
  }

  public SubscriptionsLccnSubscriptionFilter addNotificationTypesItem(NotificationTypesEnum notificationTypesItem) {
    this.notificationTypes.add(notificationTypesItem);
    return this;
  }

 /**
   * Match particular NS lifecycle operation types for the notification of type NsLcmOperationOccurrenceNotification. May be present if the \&quot;notificationTypes\&quot; attribute contains the value \&quot;NsLcmOperationOccurrenceNotification\&quot;, and shall be absent otherwise. 
   * @return operationTypes
  **/
  @JsonProperty("operationTypes")
  public List<OperationTypesEnum> getOperationTypes() {
    return operationTypes;
  }

  public void setOperationTypes(List<OperationTypesEnum> operationTypes) {
    this.operationTypes = operationTypes;
  }

  public SubscriptionsLccnSubscriptionFilter operationTypes(List<OperationTypesEnum> operationTypes) {
    this.operationTypes = operationTypes;
    return this;
  }

  public SubscriptionsLccnSubscriptionFilter addOperationTypesItem(OperationTypesEnum operationTypesItem) {
    this.operationTypes.add(operationTypesItem);
    return this;
  }

 /**
   * Match particular LCM operation state values as reported in notifications of type NsLcmOperationOccurrenceNotification. May be present if the \&quot;notificationTypes\&quot; attribute contains the value \&quot;NsLcmOperationOccurrenceNotification\&quot;, and shall be absent otherwise. 
   * @return operationStates
  **/
  @JsonProperty("operationStates")
  public List<OperationStatesEnum> getOperationStates() {
    return operationStates;
  }

  public void setOperationStates(List<OperationStatesEnum> operationStates) {
    this.operationStates = operationStates;
  }

  public SubscriptionsLccnSubscriptionFilter operationStates(List<OperationStatesEnum> operationStates) {
    this.operationStates = operationStates;
    return this;
  }

  public SubscriptionsLccnSubscriptionFilter addOperationStatesItem(OperationStatesEnum operationStatesItem) {
    this.operationStates.add(operationStatesItem);
    return this;
  }

 /**
   * Match particular NS component types for the notification of type NsChangeNotification. May be present if the \&quot;notificationTypes\&quot; attribute contains the value \&quot;NsChang. 
   * @return nsComponentTypes
  **/
  @JsonProperty("nsComponentTypes")
  public List<NsComponentTypesEnum> getNsComponentTypes() {
    return nsComponentTypes;
  }

  public void setNsComponentTypes(List<NsComponentTypesEnum> nsComponentTypes) {
    this.nsComponentTypes = nsComponentTypes;
  }

  public SubscriptionsLccnSubscriptionFilter nsComponentTypes(List<NsComponentTypesEnum> nsComponentTypes) {
    this.nsComponentTypes = nsComponentTypes;
    return this;
  }

  public SubscriptionsLccnSubscriptionFilter addNsComponentTypesItem(NsComponentTypesEnum nsComponentTypesItem) {
    this.nsComponentTypes.add(nsComponentTypesItem);
    return this;
  }

 /**
   * Match particular LCM operation names for the notification of type NsChangeNotification. May be present if the \&quot;notificationTypes\&quot; attribute contains the value \&quot;NsChangeNotification\&quot;, and shall be absent otherwise. 
   * @return lcmOpNameImpactingNsComponent
  **/
  @JsonProperty("lcmOpNameImpactingNsComponent")
  public List<LcmOpNameImpactingNsComponentEnum> getLcmOpNameImpactingNsComponent() {
    return lcmOpNameImpactingNsComponent;
  }

  public void setLcmOpNameImpactingNsComponent(List<LcmOpNameImpactingNsComponentEnum> lcmOpNameImpactingNsComponent) {
    this.lcmOpNameImpactingNsComponent = lcmOpNameImpactingNsComponent;
  }

  public SubscriptionsLccnSubscriptionFilter lcmOpNameImpactingNsComponent(List<LcmOpNameImpactingNsComponentEnum> lcmOpNameImpactingNsComponent) {
    this.lcmOpNameImpactingNsComponent = lcmOpNameImpactingNsComponent;
    return this;
  }

  public SubscriptionsLccnSubscriptionFilter addLcmOpNameImpactingNsComponentItem(LcmOpNameImpactingNsComponentEnum lcmOpNameImpactingNsComponentItem) {
    this.lcmOpNameImpactingNsComponent.add(lcmOpNameImpactingNsComponentItem);
    return this;
  }

 /**
   * Match particular LCM operation status values as reported in notifications of type NsChangeNotification. May be present if the \&quot;notificationTypes\&quot; attribute contains the value \&quot;NsChangeNotification\&quot;, and shall be absent otherwise. 
   * @return lcmOpOccStatusImpactingNsComponent
  **/
  @JsonProperty("lcmOpOccStatusImpactingNsComponent")
  public List<LcmOpOccStatusImpactingNsComponentEnum> getLcmOpOccStatusImpactingNsComponent() {
    return lcmOpOccStatusImpactingNsComponent;
  }

  public void setLcmOpOccStatusImpactingNsComponent(List<LcmOpOccStatusImpactingNsComponentEnum> lcmOpOccStatusImpactingNsComponent) {
    this.lcmOpOccStatusImpactingNsComponent = lcmOpOccStatusImpactingNsComponent;
  }

  public SubscriptionsLccnSubscriptionFilter lcmOpOccStatusImpactingNsComponent(List<LcmOpOccStatusImpactingNsComponentEnum> lcmOpOccStatusImpactingNsComponent) {
    this.lcmOpOccStatusImpactingNsComponent = lcmOpOccStatusImpactingNsComponent;
    return this;
  }

  public SubscriptionsLccnSubscriptionFilter addLcmOpOccStatusImpactingNsComponentItem(LcmOpOccStatusImpactingNsComponentEnum lcmOpOccStatusImpactingNsComponentItem) {
    this.lcmOpOccStatusImpactingNsComponent.add(lcmOpOccStatusImpactingNsComponentItem);
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubscriptionsLccnSubscriptionFilter {\n");
    
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
  private static String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

