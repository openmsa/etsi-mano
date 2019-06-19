package com.ubiqube.etsi.mano.model.vnf.sol005;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

/**
  * This type represents a VNF package management notification, which informs the receiver of a change of the status in an on-boarded VNF package. Only changes in the \"operationalState\" attribute of an on-boarded VNF package and the deletion of the VNF package will be reported. Change in the \"usageState\" and \"onboardingState\" attributes are not reported. The notification shall comply with the provisions defined in Table 9.5.2.9-1. The support of this notification is mandatory. The notification shall be triggered by the NFVO when there is a change in the status of an onboarded VNF package, as follows. • The \"operationalState\" attribute of a VNF package has changed, and the \"onboardingState\" attribute of the package has the value \"ONBOARDED\". • The on-boarded VNF package has been deleted. 
 **/
@ApiModel(description="This type represents a VNF package management notification, which informs the receiver of a change of the status in an on-boarded VNF package. Only changes in the \"operationalState\" attribute of an on-boarded VNF package and the deletion of the VNF package will be reported. Change in the \"usageState\" and \"onboardingState\" attributes are not reported. The notification shall comply with the provisions defined in Table 9.5.2.9-1. The support of this notification is mandatory. The notification shall be triggered by the NFVO when there is a change in the status of an onboarded VNF package, as follows. • The \"operationalState\" attribute of a VNF package has changed, and the \"onboardingState\" attribute of the package has the value \"ONBOARDED\". • The on-boarded VNF package has been deleted. ")
public class VnfPackageChangeNotificationVnfPackageChangeNotification  {
  
  @ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String id = null;

  @ApiModelProperty(required = true, value = "Discriminator for the different notification types. Shall be set to \"VnfPackageChangeNotification\" for this notification type. ")
 /**
   * Discriminator for the different notification types. Shall be set to \"VnfPackageChangeNotification\" for this notification type. 
  **/
  private String notificationType = null;

  @ApiModelProperty(value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String subscriptionId = null;

  @ApiModelProperty(required = true, value = "Date-time stamp.  Representation: String formatted according to IETF RFC 3339. ")
 /**
   * Date-time stamp.  Representation: String formatted according to IETF RFC 3339. 
  **/
  private Date timeStamp = null;

  @ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String vnfPkgId = null;

  @ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String vnfdId = null;


@XmlType(name="ChangeTypeEnum")
@XmlEnum(String.class)
public enum ChangeTypeEnum {

@XmlEnumValue("OP_STATE_CHANGE") OP_STATE_CHANGE(String.valueOf("OP_STATE_CHANGE")), @XmlEnumValue("PKG_DELETE") PKG_DELETE(String.valueOf("PKG_DELETE"));


    private String value;

    ChangeTypeEnum (String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static ChangeTypeEnum fromValue(String v) {
        for (ChangeTypeEnum b : ChangeTypeEnum.values()) {
            if (String.valueOf(b.value).equals(v)) {
                return b;
            }
        }
        return null;
    }
}

  @ApiModelProperty(required = true, value = "The enumeration PackageChangeType shall comply with the provisions defined in Table 9.5.4.6-1. Permitted Values:  - OP_STATE_CHANGE: The \"operationalState\" attribute has been changed. - PKG_DELETE: The VNF package has been deleted. ")
 /**
   * The enumeration PackageChangeType shall comply with the provisions defined in Table 9.5.4.6-1. Permitted Values:  - OP_STATE_CHANGE: The \"operationalState\" attribute has been changed. - PKG_DELETE: The VNF package has been deleted. 
  **/
  private ChangeTypeEnum changeType = null;


@XmlType(name="OperationalStateEnum")
@XmlEnum(String.class)
public enum OperationalStateEnum {

@XmlEnumValue("ENABLED") ENABLED(String.valueOf("ENABLED")), @XmlEnumValue("DISABLED") DISABLED(String.valueOf("DISABLED"));


    private String value;

    OperationalStateEnum (String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static OperationalStateEnum fromValue(String v) {
        for (OperationalStateEnum b : OperationalStateEnum.values()) {
            if (String.valueOf(b.value).equals(v)) {
                return b;
            }
        }
        return null;
    }
}

  @ApiModelProperty(value = "\"The enumeration PackageOperationalStateType shall  comply with the provisions defined in Table 9.5.4.4-1.\" Acceptable values are: -ENABLED - The VNF package is enabled, i.e. it can be used for instantiation of new VNF instances. -DISABLED - The VNF package is disabled, i.e. it cannot be used for further VNF instantiation requests (unless and until the VNF package is re-enabled). ")
 /**
   * \"The enumeration PackageOperationalStateType shall  comply with the provisions defined in Table 9.5.4.4-1.\" Acceptable values are: -ENABLED - The VNF package is enabled, i.e. it can be used for instantiation of new VNF instances. -DISABLED - The VNF package is disabled, i.e. it cannot be used for further VNF instantiation requests (unless and until the VNF package is re-enabled). 
  **/
  private OperationalStateEnum operationalState = null;

  @ApiModelProperty(required = true, value = "")
  @Valid
  private VnfPackageOnboardingNotificationLinks links = null;
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

  public VnfPackageChangeNotificationVnfPackageChangeNotification id(String id) {
    this.id = id;
    return this;
  }

 /**
   * Discriminator for the different notification types. Shall be set to \&quot;VnfPackageChangeNotification\&quot; for this notification type. 
   * @return notificationType
  **/
  @JsonProperty("notificationType")
  @NotNull
  public String getNotificationType() {
    return notificationType;
  }

  public void setNotificationType(String notificationType) {
    this.notificationType = notificationType;
  }

  public VnfPackageChangeNotificationVnfPackageChangeNotification notificationType(String notificationType) {
    this.notificationType = notificationType;
    return this;
  }

 /**
   * An identifier with the intention of being globally unique. 
   * @return subscriptionId
  **/
  @JsonProperty("subscriptionId")
  public String getSubscriptionId() {
    return subscriptionId;
  }

  public void setSubscriptionId(String subscriptionId) {
    this.subscriptionId = subscriptionId;
  }

  public VnfPackageChangeNotificationVnfPackageChangeNotification subscriptionId(String subscriptionId) {
    this.subscriptionId = subscriptionId;
    return this;
  }

 /**
   * Date-time stamp.  Representation: String formatted according to IETF RFC 3339. 
   * @return timeStamp
  **/
  @JsonProperty("timeStamp")
  @NotNull
  public Date getTimeStamp() {
    return timeStamp;
  }

  public void setTimeStamp(Date timeStamp) {
    this.timeStamp = timeStamp;
  }

  public VnfPackageChangeNotificationVnfPackageChangeNotification timeStamp(Date timeStamp) {
    this.timeStamp = timeStamp;
    return this;
  }

 /**
   * An identifier with the intention of being globally unique. 
   * @return vnfPkgId
  **/
  @JsonProperty("vnfPkgId")
  @NotNull
  public String getVnfPkgId() {
    return vnfPkgId;
  }

  public void setVnfPkgId(String vnfPkgId) {
    this.vnfPkgId = vnfPkgId;
  }

  public VnfPackageChangeNotificationVnfPackageChangeNotification vnfPkgId(String vnfPkgId) {
    this.vnfPkgId = vnfPkgId;
    return this;
  }

 /**
   * An identifier with the intention of being globally unique. 
   * @return vnfdId
  **/
  @JsonProperty("vnfdId")
  @NotNull
  public String getVnfdId() {
    return vnfdId;
  }

  public void setVnfdId(String vnfdId) {
    this.vnfdId = vnfdId;
  }

  public VnfPackageChangeNotificationVnfPackageChangeNotification vnfdId(String vnfdId) {
    this.vnfdId = vnfdId;
    return this;
  }

 /**
   * The enumeration PackageChangeType shall comply with the provisions defined in Table 9.5.4.6-1. Permitted Values:  - OP_STATE_CHANGE: The \&quot;operationalState\&quot; attribute has been changed. - PKG_DELETE: The VNF package has been deleted. 
   * @return changeType
  **/
  @JsonProperty("changeType")
  @NotNull
  public String getChangeType() {
    if (changeType == null) {
      return null;
    }
    return changeType.value();
  }

  public void setChangeType(ChangeTypeEnum changeType) {
    this.changeType = changeType;
  }

  public VnfPackageChangeNotificationVnfPackageChangeNotification changeType(ChangeTypeEnum changeType) {
    this.changeType = changeType;
    return this;
  }

 /**
   * \&quot;The enumeration PackageOperationalStateType shall  comply with the provisions defined in Table 9.5.4.4-1.\&quot; Acceptable values are: -ENABLED - The VNF package is enabled, i.e. it can be used for instantiation of new VNF instances. -DISABLED - The VNF package is disabled, i.e. it cannot be used for further VNF instantiation requests (unless and until the VNF package is re-enabled). 
   * @return operationalState
  **/
  @JsonProperty("operationalState")
  public String getOperationalState() {
    if (operationalState == null) {
      return null;
    }
    return operationalState.value();
  }

  public void setOperationalState(OperationalStateEnum operationalState) {
    this.operationalState = operationalState;
  }

  public VnfPackageChangeNotificationVnfPackageChangeNotification operationalState(OperationalStateEnum operationalState) {
    this.operationalState = operationalState;
    return this;
  }

 /**
   * Get links
   * @return links
  **/
  @JsonProperty("_links")
  @NotNull
  public VnfPackageOnboardingNotificationLinks getLinks() {
    return links;
  }

  public void setLinks(VnfPackageOnboardingNotificationLinks links) {
    this.links = links;
  }

  public VnfPackageChangeNotificationVnfPackageChangeNotification links(VnfPackageOnboardingNotificationLinks links) {
    this.links = links;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class URIIsProvidedByTheClientWhenCreatingTheSubscriptionVnfPackageChangeNotificationVnfPackageChangeNotification {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    notificationType: ").append(toIndentedString(notificationType)).append("\n");
    sb.append("    subscriptionId: ").append(toIndentedString(subscriptionId)).append("\n");
    sb.append("    timeStamp: ").append(toIndentedString(timeStamp)).append("\n");
    sb.append("    vnfPkgId: ").append(toIndentedString(vnfPkgId)).append("\n");
    sb.append("    vnfdId: ").append(toIndentedString(vnfdId)).append("\n");
    sb.append("    changeType: ").append(toIndentedString(changeType)).append("\n");
    sb.append("    operationalState: ").append(toIndentedString(operationalState)).append("\n");
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

