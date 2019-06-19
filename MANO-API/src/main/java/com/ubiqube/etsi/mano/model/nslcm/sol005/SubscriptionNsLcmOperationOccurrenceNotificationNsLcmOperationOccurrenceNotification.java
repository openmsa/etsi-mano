package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;
import java.util.List;

public class SubscriptionNsLcmOperationOccurrenceNotificationNsLcmOperationOccurrenceNotification  {
  
  @ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String id = null;

  @ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String nsInstanceId = null;

  @ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String nsLcmOpOccId = null;


@XmlType(name="OperationEnum")
@XmlEnum(String.class)
public enum OperationEnum {

@XmlEnumValue("INSTANTIATE") INSTANTIATE(String.valueOf("INSTANTIATE")), @XmlEnumValue("SCALE") SCALE(String.valueOf("SCALE")), @XmlEnumValue("UPDATE") UPDATE(String.valueOf("UPDATE")), @XmlEnumValue("TERMINATE") TERMINATE(String.valueOf("TERMINATE")), @XmlEnumValue("HEAL") HEAL(String.valueOf("HEAL"));


    private String value;

    OperationEnum (String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static OperationEnum fromValue(String v) {
        for (OperationEnum b : OperationEnum.values()) {
            if (String.valueOf(b.value).equals(v)) {
                return b;
            }
        }
        return null;
    }
}

  @ApiModelProperty(value = "The enumeration NsLcmOpType represents those lifecycle operations that trigger a NS lifecycle management operation occurrence notification. Value | Description ------|------------ INSTANTIATE | Represents the \"Instantiate NS\" LCM operation. SCALE | Represents the \"Scale NS\" LCM operation. UPDATE | Represents the \"Update NS\" LCM operation. TERMINATE | Represents the \"Terminate NS\" LCM operation. HEAL | Represents the \"Heal NS\" LCM operation. ")
 /**
   * The enumeration NsLcmOpType represents those lifecycle operations that trigger a NS lifecycle management operation occurrence notification. Value | Description ------|------------ INSTANTIATE | Represents the \"Instantiate NS\" LCM operation. SCALE | Represents the \"Scale NS\" LCM operation. UPDATE | Represents the \"Update NS\" LCM operation. TERMINATE | Represents the \"Terminate NS\" LCM operation. HEAL | Represents the \"Heal NS\" LCM operation. 
  **/
  private OperationEnum operation = null;

  @ApiModelProperty(value = "Discriminator for the different notification types. Shall be set to \"NsLcmOperationOccurrenceNotification\" for this notification type. ")
 /**
   * Discriminator for the different notification types. Shall be set to \"NsLcmOperationOccurrenceNotification\" for this notification type. 
  **/
  private String notificationType = null;

  @ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String subscriptionId = null;

  @ApiModelProperty(value = "Date-time stamp.  Representation: String formatted according to IETF RFC 3339. ")
 /**
   * Date-time stamp.  Representation: String formatted according to IETF RFC 3339. 
  **/
  private Date timestamp = null;


@XmlType(name="NotificationStatusEnum")
@XmlEnum(String.class)
public enum NotificationStatusEnum {

@XmlEnumValue("START") START(String.valueOf("START")), @XmlEnumValue("RESULT") RESULT(String.valueOf("RESULT"));


    private String value;

    NotificationStatusEnum (String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static NotificationStatusEnum fromValue(String v) {
        for (NotificationStatusEnum b : NotificationStatusEnum.values()) {
            if (String.valueOf(b.value).equals(v)) {
                return b;
            }
        }
        return null;
    }
}

  @ApiModelProperty(value = "Indicates whether this notification reports about the start of a NS lifecycle operation or the result of a NS lifecycle operation. Permitted values: - START: Informs about the start of the NS LCM operation occurrence. - RESULT: Informs about the final or intermediate result of the NS LCM operation occurrence. ")
 /**
   * Indicates whether this notification reports about the start of a NS lifecycle operation or the result of a NS lifecycle operation. Permitted values: - START: Informs about the start of the NS LCM operation occurrence. - RESULT: Informs about the final or intermediate result of the NS LCM operation occurrence. 
  **/
  private NotificationStatusEnum notificationStatus = null;


@XmlType(name="OperationStateEnum")
@XmlEnum(String.class)
public enum OperationStateEnum {

@XmlEnumValue("PROCESSING") PROCESSING(String.valueOf("PROCESSING")), @XmlEnumValue("COMPLETED") COMPLETED(String.valueOf("COMPLETED")), @XmlEnumValue("FAILED_TEMP") FAILED_TEMP(String.valueOf("FAILED_TEMP")), @XmlEnumValue("FAILED") FAILED(String.valueOf("FAILED")), @XmlEnumValue("ROLLING_BACK") ROLLING_BACK(String.valueOf("ROLLING_BACK")), @XmlEnumValue("ROLLED_BACK") ROLLED_BACK(String.valueOf("ROLLED_BACK"));


    private String value;

    OperationStateEnum (String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static OperationStateEnum fromValue(String v) {
        for (OperationStateEnum b : OperationStateEnum.values()) {
            if (String.valueOf(b.value).equals(v)) {
                return b;
            }
        }
        return null;
    }
}

  @ApiModelProperty(value = "The enumeration NsLcmOperationStateType shall comply with the provisions defined in Table 6.5.4.4-1. Value | Description ------|------------ PROCESSING | The LCM operation is currently in execution. COMPLETED | The LCM operation has been completed successfully. PARTIALLY_COMPLETED | The LCM operation has been partially completed with accepTable errors. FAILED_TEMP | The LCM operation has failed and execution has stopped, but the execution of the operation is not considered to be closed. FAILED | The LCM operation has failed and it cannot be retried or rolled back, as it is determined that such action won't succeed. OLLING_BACK | The LCM operation is currently being rolled back. ROLLED_BACK | The LCM operation has been successfully rolled back, i.e. The state of the VNF prior to the original operation invocation has been restored as closely as possible. ")
 /**
   * The enumeration NsLcmOperationStateType shall comply with the provisions defined in Table 6.5.4.4-1. Value | Description ------|------------ PROCESSING | The LCM operation is currently in execution. COMPLETED | The LCM operation has been completed successfully. PARTIALLY_COMPLETED | The LCM operation has been partially completed with accepTable errors. FAILED_TEMP | The LCM operation has failed and execution has stopped, but the execution of the operation is not considered to be closed. FAILED | The LCM operation has failed and it cannot be retried or rolled back, as it is determined that such action won't succeed. OLLING_BACK | The LCM operation is currently being rolled back. ROLLED_BACK | The LCM operation has been successfully rolled back, i.e. The state of the VNF prior to the original operation invocation has been restored as closely as possible. 
  **/
  private OperationStateEnum operationState = null;

  @ApiModelProperty(value = "Set to true if this NS LCM operation occurrence has been automatically triggered by the NFVO. This occurs in case of auto-scaling, auto-healing and when a nested NS is modified as a result of an operation on its composite NS. Set to false otherwise. ")
 /**
   * Set to true if this NS LCM operation occurrence has been automatically triggered by the NFVO. This occurs in case of auto-scaling, auto-healing and when a nested NS is modified as a result of an operation on its composite NS. Set to false otherwise. 
  **/
  private Boolean isAutomaticInvocation = null;

  @ApiModelProperty(value = "")
  @Valid
  private NsLcmOpOccsNsLcmOpOccResourceChangesAffectedVnfs affectedVnf = null;

  @ApiModelProperty(value = "")
  @Valid
  private NsLcmOpOccsNsLcmOpOccResourceChangesAffectedPnfs affectedPnf = null;

  @ApiModelProperty(value = "Information about the VL instances that were affected during the lifecycle operation. ")
  @Valid
 /**
   * Information about the VL instances that were affected during the lifecycle operation. 
  **/
  private List<SubscriptionNsLcmOperationOccurrenceNotificationNsLcmOperationOccurrenceNotificationAffectedVl> affectedVl = null;

  @ApiModelProperty(value = "Information about the VNFFG instances that were affected during the lifecycle operation. ")
  @Valid
 /**
   * Information about the VNFFG instances that were affected during the lifecycle operation. 
  **/
  private List<NsLcmOpOccsNsLcmOpOccResourceChangesAffectedVnffgs> affectedVnffg = null;

  @ApiModelProperty(value = "Information about the SAP instances that were affected during the lifecycle operation. See note. ")
  @Valid
 /**
   * Information about the SAP instances that were affected during the lifecycle operation. See note. 
  **/
  private List<NsLcmOpOccsNsLcmOpOccResourceChangesAffectedSaps> affectedNs = null;


@XmlType(name="AffectedSapEnum")
@XmlEnum(String.class)
public enum AffectedSapEnum {

@XmlEnumValue("INSTANTIATE") INSTANTIATE(String.valueOf("INSTANTIATE")), @XmlEnumValue("SCALE") SCALE(String.valueOf("SCALE")), @XmlEnumValue("UPDATE") UPDATE(String.valueOf("UPDATE")), @XmlEnumValue("TERMINATE") TERMINATE(String.valueOf("TERMINATE")), @XmlEnumValue("HEAL") HEAL(String.valueOf("HEAL"));


    private String value;

    AffectedSapEnum (String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static AffectedSapEnum fromValue(String v) {
        for (AffectedSapEnum b : AffectedSapEnum.values()) {
            if (String.valueOf(b.value).equals(v)) {
                return b;
            }
        }
        return null;
    }
}

  @ApiModelProperty(value = "The enumeration NsLcmOpType represents those lifecycle operations that trigger a NS lifecycle management operation occurrence notification. Value | Description ------|------------ INSTANTIATE | Represents the \"Instantiate NS\" LCM operation. SCALE | Represents the \"Scale NS\" LCM operation. UPDATE | Represents the \"Update NS\" LCM operation. TERMINATE | Represents the \"Terminate NS\" LCM operation. HEAL | Represents the \"Heal NS\" LCM operation. ")
 /**
   * The enumeration NsLcmOpType represents those lifecycle operations that trigger a NS lifecycle management operation occurrence notification. Value | Description ------|------------ INSTANTIATE | Represents the \"Instantiate NS\" LCM operation. SCALE | Represents the \"Scale NS\" LCM operation. UPDATE | Represents the \"Update NS\" LCM operation. TERMINATE | Represents the \"Terminate NS\" LCM operation. HEAL | Represents the \"Heal NS\" LCM operation. 
  **/
  private AffectedSapEnum affectedSap = null;

  @ApiModelProperty(value = "")
  @Valid
  private InlineResponse400 error = null;

  @ApiModelProperty(value = "")
  @Valid
  private SubscriptionNsLcmOperationOccurrenceNotificationNsLcmOperationOccurrenceNotificationLinks links = null;
 /**
   * An identifier with the intention of being globally unique. 
   * @return id
  **/
  @JsonProperty("id")
  @NotNull
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public SubscriptionNsLcmOperationOccurrenceNotificationNsLcmOperationOccurrenceNotification id(String id) {
    this.id = id;
    return this;
  }

 /**
   * An identifier with the intention of being globally unique. 
   * @return nsInstanceId
  **/
  @JsonProperty("nsInstanceId")
  @NotNull
  public String getNsInstanceId() {
    return nsInstanceId;
  }

  public void setNsInstanceId(String nsInstanceId) {
    this.nsInstanceId = nsInstanceId;
  }

  public SubscriptionNsLcmOperationOccurrenceNotificationNsLcmOperationOccurrenceNotification nsInstanceId(String nsInstanceId) {
    this.nsInstanceId = nsInstanceId;
    return this;
  }

 /**
   * An identifier with the intention of being globally unique. 
   * @return nsLcmOpOccId
  **/
  @JsonProperty("nsLcmOpOccId")
  @NotNull
  public String getNsLcmOpOccId() {
    return nsLcmOpOccId;
  }

  public void setNsLcmOpOccId(String nsLcmOpOccId) {
    this.nsLcmOpOccId = nsLcmOpOccId;
  }

  public SubscriptionNsLcmOperationOccurrenceNotificationNsLcmOperationOccurrenceNotification nsLcmOpOccId(String nsLcmOpOccId) {
    this.nsLcmOpOccId = nsLcmOpOccId;
    return this;
  }

 /**
   * The enumeration NsLcmOpType represents those lifecycle operations that trigger a NS lifecycle management operation occurrence notification. Value | Description ------|------------ INSTANTIATE | Represents the \&quot;Instantiate NS\&quot; LCM operation. SCALE | Represents the \&quot;Scale NS\&quot; LCM operation. UPDATE | Represents the \&quot;Update NS\&quot; LCM operation. TERMINATE | Represents the \&quot;Terminate NS\&quot; LCM operation. HEAL | Represents the \&quot;Heal NS\&quot; LCM operation. 
   * @return operation
  **/
  @JsonProperty("operation")
  public String getOperation() {
    if (operation == null) {
      return null;
    }
    return operation.value();
  }

  public void setOperation(OperationEnum operation) {
    this.operation = operation;
  }

  public SubscriptionNsLcmOperationOccurrenceNotificationNsLcmOperationOccurrenceNotification operation(OperationEnum operation) {
    this.operation = operation;
    return this;
  }

 /**
   * Discriminator for the different notification types. Shall be set to \&quot;NsLcmOperationOccurrenceNotification\&quot; for this notification type. 
   * @return notificationType
  **/
  @JsonProperty("notificationType")
  public String getNotificationType() {
    return notificationType;
  }

  public void setNotificationType(String notificationType) {
    this.notificationType = notificationType;
  }

  public SubscriptionNsLcmOperationOccurrenceNotificationNsLcmOperationOccurrenceNotification notificationType(String notificationType) {
    this.notificationType = notificationType;
    return this;
  }

 /**
   * An identifier with the intention of being globally unique. 
   * @return subscriptionId
  **/
  @JsonProperty("subscriptionId")
  @NotNull
  public String getSubscriptionId() {
    return subscriptionId;
  }

  public void setSubscriptionId(String subscriptionId) {
    this.subscriptionId = subscriptionId;
  }

  public SubscriptionNsLcmOperationOccurrenceNotificationNsLcmOperationOccurrenceNotification subscriptionId(String subscriptionId) {
    this.subscriptionId = subscriptionId;
    return this;
  }

 /**
   * Date-time stamp.  Representation: String formatted according to IETF RFC 3339. 
   * @return timestamp
  **/
  @JsonProperty("timestamp")
  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  public SubscriptionNsLcmOperationOccurrenceNotificationNsLcmOperationOccurrenceNotification timestamp(Date timestamp) {
    this.timestamp = timestamp;
    return this;
  }

 /**
   * Indicates whether this notification reports about the start of a NS lifecycle operation or the result of a NS lifecycle operation. Permitted values: - START: Informs about the start of the NS LCM operation occurrence. - RESULT: Informs about the final or intermediate result of the NS LCM operation occurrence. 
   * @return notificationStatus
  **/
  @JsonProperty("notificationStatus")
  public String getNotificationStatus() {
    if (notificationStatus == null) {
      return null;
    }
    return notificationStatus.value();
  }

  public void setNotificationStatus(NotificationStatusEnum notificationStatus) {
    this.notificationStatus = notificationStatus;
  }

  public SubscriptionNsLcmOperationOccurrenceNotificationNsLcmOperationOccurrenceNotification notificationStatus(NotificationStatusEnum notificationStatus) {
    this.notificationStatus = notificationStatus;
    return this;
  }

 /**
   * The enumeration NsLcmOperationStateType shall comply with the provisions defined in Table 6.5.4.4-1. Value | Description ------|------------ PROCESSING | The LCM operation is currently in execution. COMPLETED | The LCM operation has been completed successfully. PARTIALLY_COMPLETED | The LCM operation has been partially completed with accepTable errors. FAILED_TEMP | The LCM operation has failed and execution has stopped, but the execution of the operation is not considered to be closed. FAILED | The LCM operation has failed and it cannot be retried or rolled back, as it is determined that such action won&#39;t succeed. OLLING_BACK | The LCM operation is currently being rolled back. ROLLED_BACK | The LCM operation has been successfully rolled back, i.e. The state of the VNF prior to the original operation invocation has been restored as closely as possible. 
   * @return operationState
  **/
  @JsonProperty("operationState")
  public String getOperationState() {
    if (operationState == null) {
      return null;
    }
    return operationState.value();
  }

  public void setOperationState(OperationStateEnum operationState) {
    this.operationState = operationState;
  }

  public SubscriptionNsLcmOperationOccurrenceNotificationNsLcmOperationOccurrenceNotification operationState(OperationStateEnum operationState) {
    this.operationState = operationState;
    return this;
  }

 /**
   * Set to true if this NS LCM operation occurrence has been automatically triggered by the NFVO. This occurs in case of auto-scaling, auto-healing and when a nested NS is modified as a result of an operation on its composite NS. Set to false otherwise. 
   * @return isAutomaticInvocation
  **/
  @JsonProperty("isAutomaticInvocation")
  public Boolean isIsAutomaticInvocation() {
    return isAutomaticInvocation;
  }

  public void setIsAutomaticInvocation(Boolean isAutomaticInvocation) {
    this.isAutomaticInvocation = isAutomaticInvocation;
  }

  public SubscriptionNsLcmOperationOccurrenceNotificationNsLcmOperationOccurrenceNotification isAutomaticInvocation(Boolean isAutomaticInvocation) {
    this.isAutomaticInvocation = isAutomaticInvocation;
    return this;
  }

 /**
   * Get affectedVnf
   * @return affectedVnf
  **/
  @JsonProperty("affectedVnf")
  public NsLcmOpOccsNsLcmOpOccResourceChangesAffectedVnfs getAffectedVnf() {
    return affectedVnf;
  }

  public void setAffectedVnf(NsLcmOpOccsNsLcmOpOccResourceChangesAffectedVnfs affectedVnf) {
    this.affectedVnf = affectedVnf;
  }

  public SubscriptionNsLcmOperationOccurrenceNotificationNsLcmOperationOccurrenceNotification affectedVnf(NsLcmOpOccsNsLcmOpOccResourceChangesAffectedVnfs affectedVnf) {
    this.affectedVnf = affectedVnf;
    return this;
  }

 /**
   * Get affectedPnf
   * @return affectedPnf
  **/
  @JsonProperty("affectedPnf")
  public NsLcmOpOccsNsLcmOpOccResourceChangesAffectedPnfs getAffectedPnf() {
    return affectedPnf;
  }

  public void setAffectedPnf(NsLcmOpOccsNsLcmOpOccResourceChangesAffectedPnfs affectedPnf) {
    this.affectedPnf = affectedPnf;
  }

  public SubscriptionNsLcmOperationOccurrenceNotificationNsLcmOperationOccurrenceNotification affectedPnf(NsLcmOpOccsNsLcmOpOccResourceChangesAffectedPnfs affectedPnf) {
    this.affectedPnf = affectedPnf;
    return this;
  }

 /**
   * Information about the VL instances that were affected during the lifecycle operation. 
   * @return affectedVl
  **/
  @JsonProperty("affectedVl")
  public List<SubscriptionNsLcmOperationOccurrenceNotificationNsLcmOperationOccurrenceNotificationAffectedVl> getAffectedVl() {
    return affectedVl;
  }

  public void setAffectedVl(List<SubscriptionNsLcmOperationOccurrenceNotificationNsLcmOperationOccurrenceNotificationAffectedVl> affectedVl) {
    this.affectedVl = affectedVl;
  }

  public SubscriptionNsLcmOperationOccurrenceNotificationNsLcmOperationOccurrenceNotification affectedVl(List<SubscriptionNsLcmOperationOccurrenceNotificationNsLcmOperationOccurrenceNotificationAffectedVl> affectedVl) {
    this.affectedVl = affectedVl;
    return this;
  }

  public SubscriptionNsLcmOperationOccurrenceNotificationNsLcmOperationOccurrenceNotification addAffectedVlItem(SubscriptionNsLcmOperationOccurrenceNotificationNsLcmOperationOccurrenceNotificationAffectedVl affectedVlItem) {
    this.affectedVl.add(affectedVlItem);
    return this;
  }

 /**
   * Information about the VNFFG instances that were affected during the lifecycle operation. 
   * @return affectedVnffg
  **/
  @JsonProperty("affectedVnffg")
  public List<NsLcmOpOccsNsLcmOpOccResourceChangesAffectedVnffgs> getAffectedVnffg() {
    return affectedVnffg;
  }

  public void setAffectedVnffg(List<NsLcmOpOccsNsLcmOpOccResourceChangesAffectedVnffgs> affectedVnffg) {
    this.affectedVnffg = affectedVnffg;
  }

  public SubscriptionNsLcmOperationOccurrenceNotificationNsLcmOperationOccurrenceNotification affectedVnffg(List<NsLcmOpOccsNsLcmOpOccResourceChangesAffectedVnffgs> affectedVnffg) {
    this.affectedVnffg = affectedVnffg;
    return this;
  }

  public SubscriptionNsLcmOperationOccurrenceNotificationNsLcmOperationOccurrenceNotification addAffectedVnffgItem(NsLcmOpOccsNsLcmOpOccResourceChangesAffectedVnffgs affectedVnffgItem) {
    this.affectedVnffg.add(affectedVnffgItem);
    return this;
  }

 /**
   * Information about the SAP instances that were affected during the lifecycle operation. See note. 
   * @return affectedNs
  **/
  @JsonProperty("affectedNs")
  public List<NsLcmOpOccsNsLcmOpOccResourceChangesAffectedSaps> getAffectedNs() {
    return affectedNs;
  }

  public void setAffectedNs(List<NsLcmOpOccsNsLcmOpOccResourceChangesAffectedSaps> affectedNs) {
    this.affectedNs = affectedNs;
  }

  public SubscriptionNsLcmOperationOccurrenceNotificationNsLcmOperationOccurrenceNotification affectedNs(List<NsLcmOpOccsNsLcmOpOccResourceChangesAffectedSaps> affectedNs) {
    this.affectedNs = affectedNs;
    return this;
  }

  public SubscriptionNsLcmOperationOccurrenceNotificationNsLcmOperationOccurrenceNotification addAffectedNsItem(NsLcmOpOccsNsLcmOpOccResourceChangesAffectedSaps affectedNsItem) {
    this.affectedNs.add(affectedNsItem);
    return this;
  }

 /**
   * The enumeration NsLcmOpType represents those lifecycle operations that trigger a NS lifecycle management operation occurrence notification. Value | Description ------|------------ INSTANTIATE | Represents the \&quot;Instantiate NS\&quot; LCM operation. SCALE | Represents the \&quot;Scale NS\&quot; LCM operation. UPDATE | Represents the \&quot;Update NS\&quot; LCM operation. TERMINATE | Represents the \&quot;Terminate NS\&quot; LCM operation. HEAL | Represents the \&quot;Heal NS\&quot; LCM operation. 
   * @return affectedSap
  **/
  @JsonProperty("affectedSap")
  public String getAffectedSap() {
    if (affectedSap == null) {
      return null;
    }
    return affectedSap.value();
  }

  public void setAffectedSap(AffectedSapEnum affectedSap) {
    this.affectedSap = affectedSap;
  }

  public SubscriptionNsLcmOperationOccurrenceNotificationNsLcmOperationOccurrenceNotification affectedSap(AffectedSapEnum affectedSap) {
    this.affectedSap = affectedSap;
    return this;
  }

 /**
   * Get error
   * @return error
  **/
  @JsonProperty("error")
  public InlineResponse400 getError() {
    return error;
  }

  public void setError(InlineResponse400 error) {
    this.error = error;
  }

  public SubscriptionNsLcmOperationOccurrenceNotificationNsLcmOperationOccurrenceNotification error(InlineResponse400 error) {
    this.error = error;
    return this;
  }

 /**
   * Get links
   * @return links
  **/
  @JsonProperty("_links")
  public SubscriptionNsLcmOperationOccurrenceNotificationNsLcmOperationOccurrenceNotificationLinks getLinks() {
    return links;
  }

  public void setLinks(SubscriptionNsLcmOperationOccurrenceNotificationNsLcmOperationOccurrenceNotificationLinks links) {
    this.links = links;
  }

  public SubscriptionNsLcmOperationOccurrenceNotificationNsLcmOperationOccurrenceNotification links(SubscriptionNsLcmOperationOccurrenceNotificationNsLcmOperationOccurrenceNotificationLinks links) {
    this.links = links;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class URIIsProvidedByTheClientWhenCreatingTheSubscriptionNsLcmOperationOccurrenceNotificationNsLcmOperationOccurrenceNotification {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    nsInstanceId: ").append(toIndentedString(nsInstanceId)).append("\n");
    sb.append("    nsLcmOpOccId: ").append(toIndentedString(nsLcmOpOccId)).append("\n");
    sb.append("    operation: ").append(toIndentedString(operation)).append("\n");
    sb.append("    notificationType: ").append(toIndentedString(notificationType)).append("\n");
    sb.append("    subscriptionId: ").append(toIndentedString(subscriptionId)).append("\n");
    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
    sb.append("    notificationStatus: ").append(toIndentedString(notificationStatus)).append("\n");
    sb.append("    operationState: ").append(toIndentedString(operationState)).append("\n");
    sb.append("    isAutomaticInvocation: ").append(toIndentedString(isAutomaticInvocation)).append("\n");
    sb.append("    affectedVnf: ").append(toIndentedString(affectedVnf)).append("\n");
    sb.append("    affectedPnf: ").append(toIndentedString(affectedPnf)).append("\n");
    sb.append("    affectedVl: ").append(toIndentedString(affectedVl)).append("\n");
    sb.append("    affectedVnffg: ").append(toIndentedString(affectedVnffg)).append("\n");
    sb.append("    affectedNs: ").append(toIndentedString(affectedNs)).append("\n");
    sb.append("    affectedSap: ").append(toIndentedString(affectedSap)).append("\n");
    sb.append("    error: ").append(toIndentedString(error)).append("\n");
    sb.append("    links: ").append(toIndentedString(links)).append("\n");
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

