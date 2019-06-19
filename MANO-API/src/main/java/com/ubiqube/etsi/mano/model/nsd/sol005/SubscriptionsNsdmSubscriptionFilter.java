package com.ubiqube.etsi.mano.model.nsd.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
  * \"This type represents a subscription filter related to notifications about NSD management. It shall comply with the provisions defined in Table 5.5.3.2-1 of GS NFV-SOL 005. At a particular nesting level in the filter structure, the following applies: All attributes shall match in order for the filter to match (logical \"and\" between different filter attributes). If an attribute is an array, the attribute shall match if at least one of the values in the array matches (logical \"or\" between the values of one filter attribute).\"      
 **/
@ApiModel(description="\"This type represents a subscription filter related to notifications about NSD management. It shall comply with the provisions defined in Table 5.5.3.2-1 of GS NFV-SOL 005. At a particular nesting level in the filter structure, the following applies: All attributes shall match in order for the filter to match (logical \"and\" between different filter attributes). If an attribute is an array, the attribute shall match if at least one of the values in the array matches (logical \"or\" between the values of one filter attribute).\"      ")
public class SubscriptionsNsdmSubscriptionFilter  {
  

@XmlType(name="NotificationTypesEnum")
@XmlEnum(String.class)
public enum NotificationTypesEnum {

@XmlEnumValue("NsdOnBoardingNotification") NSDONBOARDINGNOTIFICATION(String.valueOf("NsdOnBoardingNotification")), @XmlEnumValue("NsdOnboardingFailureNotification") NSDONBOARDINGFAILURENOTIFICATION(String.valueOf("NsdOnboardingFailureNotification")), @XmlEnumValue("NsdChangeNotification") NSDCHANGENOTIFICATION(String.valueOf("NsdChangeNotification")), @XmlEnumValue("NsdDeletionNotification") NSDDELETIONNOTIFICATION(String.valueOf("NsdDeletionNotification")), @XmlEnumValue("PnfdOnBoardingNotification") PNFDONBOARDINGNOTIFICATION(String.valueOf("PnfdOnBoardingNotification")), @XmlEnumValue("PnfdOnBoardingFailureNotification") PNFDONBOARDINGFAILURENOTIFICATION(String.valueOf("PnfdOnBoardingFailureNotification")), @XmlEnumValue("PnfdDeletionNotification") PNFDDELETIONNOTIFICATION(String.valueOf("PnfdDeletionNotification"));


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

  @ApiModelProperty(value = "\"Match particular notification types. Permitted values: NsdOnBoardingNotification, NsdOnboardingFailureNotification, NsdChangeNotification, NsdDeletionNotification PnfdOnBoardingNotification, PnfdOnBoardingFailureNotification, PnfdDeletionNotification. The permitted values of the \"notificationTypes\" ] attribute are spelled exactly as the names of the notification types to facilitate automated code generation systems.\" ")
 /**
   * \"Match particular notification types. Permitted values: NsdOnBoardingNotification, NsdOnboardingFailureNotification, NsdChangeNotification, NsdDeletionNotification PnfdOnBoardingNotification, PnfdOnBoardingFailureNotification, PnfdDeletionNotification. The permitted values of the \"notificationTypes\" ] attribute are spelled exactly as the names of the notification types to facilitate automated code generation systems.\" 
  **/
  private List<NotificationTypesEnum> notificationTypes = null;

  @ApiModelProperty(value = "Match the NsdInfo identifier which is allocated by the NFVO. ")
 /**
   * Match the NsdInfo identifier which is allocated by the NFVO. 
  **/
  private List<String> nsdInfoId = null;

  @ApiModelProperty(value = "Match the NSD identifier, which is allocated by the NSD designer.        ")
 /**
   * Match the NSD identifier, which is allocated by the NSD designer.        
  **/
  private List<String> nsdId = null;

  @ApiModelProperty(value = "Match the name of the on boarded NSD.        ")
 /**
   * Match the name of the on boarded NSD.        
  **/
  private List<String> nsdName = null;

  @ApiModelProperty(value = "Match the NSD version listed as part of this attribute. ")
 /**
   * Match the NSD version listed as part of this attribute. 
  **/
  private List<String> nsdVersion = null;

  @ApiModelProperty(value = "\"Match the NSD designer of the on-boarded NSD.\" ")
 /**
   * \"Match the NSD designer of the on-boarded NSD.\" 
  **/
  private List<String> nsdDesigner = null;

  @ApiModelProperty(value = "Match the NSD invariant identifier which is allocated by the NSD designer and identifies an NSD in a version independent manner.        ")
 /**
   * Match the NSD invariant identifier which is allocated by the NSD designer and identifies an NSD in a version independent manner.        
  **/
  private List<String> nsdInvariantId = null;

  @ApiModelProperty(value = "Match VNF packages with a package identifier listed in the attribute.        ")
 /**
   * Match VNF packages with a package identifier listed in the attribute.        
  **/
  private List<String> vnfPkgIds = null;

  @ApiModelProperty(value = "Match the PnfdInfo identifier for the PNFD referenced by the on-boarded NSD.        ")
 /**
   * Match the PnfdInfo identifier for the PNFD referenced by the on-boarded NSD.        
  **/
  private List<String> pnfdInfoIds = null;

  @ApiModelProperty(value = "Match the NsdInfo identifier for the nested NSD referenced by the on-boarded NSD.        ")
 /**
   * Match the NsdInfo identifier for the nested NSD referenced by the on-boarded NSD.        
  **/
  private List<String> nestedNsdInfoIds = null;


@XmlType(name="NsdOnboardingStateEnum")
@XmlEnum(String.class)
public enum NsdOnboardingStateEnum {

@XmlEnumValue("CREATED") CREATED(String.valueOf("CREATED")), @XmlEnumValue("UPLOADING") UPLOADING(String.valueOf("UPLOADING")), @XmlEnumValue("PROCESSING") PROCESSING(String.valueOf("PROCESSING")), @XmlEnumValue("ONBOARDED") ONBOARDED(String.valueOf("ONBOARDED"));


    private String value;

    NsdOnboardingStateEnum (String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static NsdOnboardingStateEnum fromValue(String v) {
        for (NsdOnboardingStateEnum b : NsdOnboardingStateEnum.values()) {
            if (String.valueOf(b.value).equals(v)) {
                return b;
            }
        }
        return null;
    }
}

  @ApiModelProperty(value = "Match particular on-boarding state of the NSD. ")
 /**
   * Match particular on-boarding state of the NSD. 
  **/
  private List<NsdOnboardingStateEnum> nsdOnboardingState = null;


@XmlType(name="NsdOperationalStateEnum")
@XmlEnum(String.class)
public enum NsdOperationalStateEnum {

@XmlEnumValue("ENABLED") ENABLED(String.valueOf("ENABLED")), @XmlEnumValue("DISABLED") DISABLED(String.valueOf("DISABLED"));


    private String value;

    NsdOperationalStateEnum (String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static NsdOperationalStateEnum fromValue(String v) {
        for (NsdOperationalStateEnum b : NsdOperationalStateEnum.values()) {
            if (String.valueOf(b.value).equals(v)) {
                return b;
            }
        }
        return null;
    }
}

  @ApiModelProperty(value = "Match particular operational state of the on-boarded NSD.        ")
 /**
   * Match particular operational state of the on-boarded NSD.        
  **/
  private List<NsdOperationalStateEnum> nsdOperationalState = null;


@XmlType(name="NsdUsageStateEnum")
@XmlEnum(String.class)
public enum NsdUsageStateEnum {

@XmlEnumValue("IN_USE") IN_USE(String.valueOf("IN_USE")), @XmlEnumValue("NOT_IN_USE") NOT_IN_USE(String.valueOf("NOT_IN_USE"));


    private String value;

    NsdUsageStateEnum (String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static NsdUsageStateEnum fromValue(String v) {
        for (NsdUsageStateEnum b : NsdUsageStateEnum.values()) {
            if (String.valueOf(b.value).equals(v)) {
                return b;
            }
        }
        return null;
    }
}

  @ApiModelProperty(value = "Match particular usage state of the on-boarded NSD. ")
 /**
   * Match particular usage state of the on-boarded NSD. 
  **/
  private List<NsdUsageStateEnum> nsdUsageState = null;

  @ApiModelProperty(value = "Match the PNFD identifier which is copied from the PNFD content.        ")
 /**
   * Match the PNFD identifier which is copied from the PNFD content.        
  **/
  private List<String> pnfdId = null;

  @ApiModelProperty(value = "Match the name of the on-boarded PNFD. ")
 /**
   * Match the name of the on-boarded PNFD. 
  **/
  private List<String> pnfdName = null;

  @ApiModelProperty(value = "Match the PNFD designer of the on-boarded PNFD.          ")
 /**
   * Match the PNFD designer of the on-boarded PNFD.          
  **/
  private List<String> pnfdVersion = null;

  @ApiModelProperty(value = "Match the provider of the on-boarded PNFD. ")
 /**
   * Match the provider of the on-boarded PNFD. 
  **/
  private List<String> pnfdProvider = null;

  @ApiModelProperty(value = "Match the PNFD in a version independent manner.        ")
 /**
   * Match the PNFD in a version independent manner.        
  **/
  private List<String> pnfdInvariantId = null;


@XmlType(name="PnfdOnboardingStateEnum")
@XmlEnum(String.class)
public enum PnfdOnboardingStateEnum {

@XmlEnumValue("CREATED") CREATED(String.valueOf("CREATED")), @XmlEnumValue("UPLOADING") UPLOADING(String.valueOf("UPLOADING")), @XmlEnumValue("PROCESSING") PROCESSING(String.valueOf("PROCESSING")), @XmlEnumValue("ONBOARDING") ONBOARDING(String.valueOf("ONBOARDING"));


    private String value;

    PnfdOnboardingStateEnum (String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static PnfdOnboardingStateEnum fromValue(String v) {
        for (PnfdOnboardingStateEnum b : PnfdOnboardingStateEnum.values()) {
            if (String.valueOf(b.value).equals(v)) {
                return b;
            }
        }
        return null;
    }
}

  @ApiModelProperty(value = "Match particular on-boarding state of the PNFD.          ")
 /**
   * Match particular on-boarding state of the PNFD.          
  **/
  private List<PnfdOnboardingStateEnum> pnfdOnboardingState = null;


@XmlType(name="PnfdUsageStateEnum")
@XmlEnum(String.class)
public enum PnfdUsageStateEnum {

@XmlEnumValue("IN_USE") IN_USE(String.valueOf("IN_USE")), @XmlEnumValue("NOT_IN_USE") NOT_IN_USE(String.valueOf("NOT_IN_USE"));


    private String value;

    PnfdUsageStateEnum (String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static PnfdUsageStateEnum fromValue(String v) {
        for (PnfdUsageStateEnum b : PnfdUsageStateEnum.values()) {
            if (String.valueOf(b.value).equals(v)) {
                return b;
            }
        }
        return null;
    }
}

  @ApiModelProperty(value = "Match the usage state of the individual PNF descriptor resource.   ")
 /**
   * Match the usage state of the individual PNF descriptor resource.   
  **/
  private List<PnfdUsageStateEnum> pnfdUsageState = null;
 /**
   * \&quot;Match particular notification types. Permitted values: NsdOnBoardingNotification, NsdOnboardingFailureNotification, NsdChangeNotification, NsdDeletionNotification PnfdOnBoardingNotification, PnfdOnBoardingFailureNotification, PnfdDeletionNotification. The permitted values of the \&quot;notificationTypes\&quot; ] attribute are spelled exactly as the names of the notification types to facilitate automated code generation systems.\&quot; 
   * @return notificationTypes
  **/
  @JsonProperty("notificationTypes")
  public List<NotificationTypesEnum> getNotificationTypes() {
    return notificationTypes;
  }

  public void setNotificationTypes(List<NotificationTypesEnum> notificationTypes) {
    this.notificationTypes = notificationTypes;
  }

  public SubscriptionsNsdmSubscriptionFilter notificationTypes(List<NotificationTypesEnum> notificationTypes) {
    this.notificationTypes = notificationTypes;
    return this;
  }

  public SubscriptionsNsdmSubscriptionFilter addNotificationTypesItem(NotificationTypesEnum notificationTypesItem) {
    this.notificationTypes.add(notificationTypesItem);
    return this;
  }

 /**
   * Match the NsdInfo identifier which is allocated by the NFVO. 
   * @return nsdInfoId
  **/
  @JsonProperty("nsdInfoId")
  public List<String> getNsdInfoId() {
    return nsdInfoId;
  }

  public void setNsdInfoId(List<String> nsdInfoId) {
    this.nsdInfoId = nsdInfoId;
  }

  public SubscriptionsNsdmSubscriptionFilter nsdInfoId(List<String> nsdInfoId) {
    this.nsdInfoId = nsdInfoId;
    return this;
  }

  public SubscriptionsNsdmSubscriptionFilter addNsdInfoIdItem(String nsdInfoIdItem) {
    this.nsdInfoId.add(nsdInfoIdItem);
    return this;
  }

 /**
   * Match the NSD identifier, which is allocated by the NSD designer.        
   * @return nsdId
  **/
  @JsonProperty("nsdId")
  public List<String> getNsdId() {
    return nsdId;
  }

  public void setNsdId(List<String> nsdId) {
    this.nsdId = nsdId;
  }

  public SubscriptionsNsdmSubscriptionFilter nsdId(List<String> nsdId) {
    this.nsdId = nsdId;
    return this;
  }

  public SubscriptionsNsdmSubscriptionFilter addNsdIdItem(String nsdIdItem) {
    this.nsdId.add(nsdIdItem);
    return this;
  }

 /**
   * Match the name of the on boarded NSD.        
   * @return nsdName
  **/
  @JsonProperty("nsdName")
  public List<String> getNsdName() {
    return nsdName;
  }

  public void setNsdName(List<String> nsdName) {
    this.nsdName = nsdName;
  }

  public SubscriptionsNsdmSubscriptionFilter nsdName(List<String> nsdName) {
    this.nsdName = nsdName;
    return this;
  }

  public SubscriptionsNsdmSubscriptionFilter addNsdNameItem(String nsdNameItem) {
    this.nsdName.add(nsdNameItem);
    return this;
  }

 /**
   * Match the NSD version listed as part of this attribute. 
   * @return nsdVersion
  **/
  @JsonProperty("nsdVersion")
  public List<String> getNsdVersion() {
    return nsdVersion;
  }

  public void setNsdVersion(List<String> nsdVersion) {
    this.nsdVersion = nsdVersion;
  }

  public SubscriptionsNsdmSubscriptionFilter nsdVersion(List<String> nsdVersion) {
    this.nsdVersion = nsdVersion;
    return this;
  }

  public SubscriptionsNsdmSubscriptionFilter addNsdVersionItem(String nsdVersionItem) {
    this.nsdVersion.add(nsdVersionItem);
    return this;
  }

 /**
   * \&quot;Match the NSD designer of the on-boarded NSD.\&quot; 
   * @return nsdDesigner
  **/
  @JsonProperty("nsdDesigner")
  public List<String> getNsdDesigner() {
    return nsdDesigner;
  }

  public void setNsdDesigner(List<String> nsdDesigner) {
    this.nsdDesigner = nsdDesigner;
  }

  public SubscriptionsNsdmSubscriptionFilter nsdDesigner(List<String> nsdDesigner) {
    this.nsdDesigner = nsdDesigner;
    return this;
  }

  public SubscriptionsNsdmSubscriptionFilter addNsdDesignerItem(String nsdDesignerItem) {
    this.nsdDesigner.add(nsdDesignerItem);
    return this;
  }

 /**
   * Match the NSD invariant identifier which is allocated by the NSD designer and identifies an NSD in a version independent manner.        
   * @return nsdInvariantId
  **/
  @JsonProperty("nsdInvariantId")
  public List<String> getNsdInvariantId() {
    return nsdInvariantId;
  }

  public void setNsdInvariantId(List<String> nsdInvariantId) {
    this.nsdInvariantId = nsdInvariantId;
  }

  public SubscriptionsNsdmSubscriptionFilter nsdInvariantId(List<String> nsdInvariantId) {
    this.nsdInvariantId = nsdInvariantId;
    return this;
  }

  public SubscriptionsNsdmSubscriptionFilter addNsdInvariantIdItem(String nsdInvariantIdItem) {
    this.nsdInvariantId.add(nsdInvariantIdItem);
    return this;
  }

 /**
   * Match VNF packages with a package identifier listed in the attribute.        
   * @return vnfPkgIds
  **/
  @JsonProperty("vnfPkgIds")
  public List<String> getVnfPkgIds() {
    return vnfPkgIds;
  }

  public void setVnfPkgIds(List<String> vnfPkgIds) {
    this.vnfPkgIds = vnfPkgIds;
  }

  public SubscriptionsNsdmSubscriptionFilter vnfPkgIds(List<String> vnfPkgIds) {
    this.vnfPkgIds = vnfPkgIds;
    return this;
  }

  public SubscriptionsNsdmSubscriptionFilter addVnfPkgIdsItem(String vnfPkgIdsItem) {
    this.vnfPkgIds.add(vnfPkgIdsItem);
    return this;
  }

 /**
   * Match the PnfdInfo identifier for the PNFD referenced by the on-boarded NSD.        
   * @return pnfdInfoIds
  **/
  @JsonProperty("pnfdInfoIds")
  public List<String> getPnfdInfoIds() {
    return pnfdInfoIds;
  }

  public void setPnfdInfoIds(List<String> pnfdInfoIds) {
    this.pnfdInfoIds = pnfdInfoIds;
  }

  public SubscriptionsNsdmSubscriptionFilter pnfdInfoIds(List<String> pnfdInfoIds) {
    this.pnfdInfoIds = pnfdInfoIds;
    return this;
  }

  public SubscriptionsNsdmSubscriptionFilter addPnfdInfoIdsItem(String pnfdInfoIdsItem) {
    this.pnfdInfoIds.add(pnfdInfoIdsItem);
    return this;
  }

 /**
   * Match the NsdInfo identifier for the nested NSD referenced by the on-boarded NSD.        
   * @return nestedNsdInfoIds
  **/
  @JsonProperty("nestedNsdInfoIds")
  public List<String> getNestedNsdInfoIds() {
    return nestedNsdInfoIds;
  }

  public void setNestedNsdInfoIds(List<String> nestedNsdInfoIds) {
    this.nestedNsdInfoIds = nestedNsdInfoIds;
  }

  public SubscriptionsNsdmSubscriptionFilter nestedNsdInfoIds(List<String> nestedNsdInfoIds) {
    this.nestedNsdInfoIds = nestedNsdInfoIds;
    return this;
  }

  public SubscriptionsNsdmSubscriptionFilter addNestedNsdInfoIdsItem(String nestedNsdInfoIdsItem) {
    this.nestedNsdInfoIds.add(nestedNsdInfoIdsItem);
    return this;
  }

 /**
   * Match particular on-boarding state of the NSD. 
   * @return nsdOnboardingState
  **/
  @JsonProperty("nsdOnboardingState")
  public List<NsdOnboardingStateEnum> getNsdOnboardingState() {
    return nsdOnboardingState;
  }

  public void setNsdOnboardingState(List<NsdOnboardingStateEnum> nsdOnboardingState) {
    this.nsdOnboardingState = nsdOnboardingState;
  }

  public SubscriptionsNsdmSubscriptionFilter nsdOnboardingState(List<NsdOnboardingStateEnum> nsdOnboardingState) {
    this.nsdOnboardingState = nsdOnboardingState;
    return this;
  }

  public SubscriptionsNsdmSubscriptionFilter addNsdOnboardingStateItem(NsdOnboardingStateEnum nsdOnboardingStateItem) {
    this.nsdOnboardingState.add(nsdOnboardingStateItem);
    return this;
  }

 /**
   * Match particular operational state of the on-boarded NSD.        
   * @return nsdOperationalState
  **/
  @JsonProperty("nsdOperationalState")
  public List<NsdOperationalStateEnum> getNsdOperationalState() {
    return nsdOperationalState;
  }

  public void setNsdOperationalState(List<NsdOperationalStateEnum> nsdOperationalState) {
    this.nsdOperationalState = nsdOperationalState;
  }

  public SubscriptionsNsdmSubscriptionFilter nsdOperationalState(List<NsdOperationalStateEnum> nsdOperationalState) {
    this.nsdOperationalState = nsdOperationalState;
    return this;
  }

  public SubscriptionsNsdmSubscriptionFilter addNsdOperationalStateItem(NsdOperationalStateEnum nsdOperationalStateItem) {
    this.nsdOperationalState.add(nsdOperationalStateItem);
    return this;
  }

 /**
   * Match particular usage state of the on-boarded NSD. 
   * @return nsdUsageState
  **/
  @JsonProperty("nsdUsageState")
  public List<NsdUsageStateEnum> getNsdUsageState() {
    return nsdUsageState;
  }

  public void setNsdUsageState(List<NsdUsageStateEnum> nsdUsageState) {
    this.nsdUsageState = nsdUsageState;
  }

  public SubscriptionsNsdmSubscriptionFilter nsdUsageState(List<NsdUsageStateEnum> nsdUsageState) {
    this.nsdUsageState = nsdUsageState;
    return this;
  }

  public SubscriptionsNsdmSubscriptionFilter addNsdUsageStateItem(NsdUsageStateEnum nsdUsageStateItem) {
    this.nsdUsageState.add(nsdUsageStateItem);
    return this;
  }

 /**
   * Match the PNFD identifier which is copied from the PNFD content.        
   * @return pnfdId
  **/
  @JsonProperty("pnfdId")
  public List<String> getPnfdId() {
    return pnfdId;
  }

  public void setPnfdId(List<String> pnfdId) {
    this.pnfdId = pnfdId;
  }

  public SubscriptionsNsdmSubscriptionFilter pnfdId(List<String> pnfdId) {
    this.pnfdId = pnfdId;
    return this;
  }

  public SubscriptionsNsdmSubscriptionFilter addPnfdIdItem(String pnfdIdItem) {
    this.pnfdId.add(pnfdIdItem);
    return this;
  }

 /**
   * Match the name of the on-boarded PNFD. 
   * @return pnfdName
  **/
  @JsonProperty("pnfdName")
  public List<String> getPnfdName() {
    return pnfdName;
  }

  public void setPnfdName(List<String> pnfdName) {
    this.pnfdName = pnfdName;
  }

  public SubscriptionsNsdmSubscriptionFilter pnfdName(List<String> pnfdName) {
    this.pnfdName = pnfdName;
    return this;
  }

  public SubscriptionsNsdmSubscriptionFilter addPnfdNameItem(String pnfdNameItem) {
    this.pnfdName.add(pnfdNameItem);
    return this;
  }

 /**
   * Match the PNFD designer of the on-boarded PNFD.          
   * @return pnfdVersion
  **/
  @JsonProperty("pnfdVersion")
  public List<String> getPnfdVersion() {
    return pnfdVersion;
  }

  public void setPnfdVersion(List<String> pnfdVersion) {
    this.pnfdVersion = pnfdVersion;
  }

  public SubscriptionsNsdmSubscriptionFilter pnfdVersion(List<String> pnfdVersion) {
    this.pnfdVersion = pnfdVersion;
    return this;
  }

  public SubscriptionsNsdmSubscriptionFilter addPnfdVersionItem(String pnfdVersionItem) {
    this.pnfdVersion.add(pnfdVersionItem);
    return this;
  }

 /**
   * Match the provider of the on-boarded PNFD. 
   * @return pnfdProvider
  **/
  @JsonProperty("pnfdProvider")
  public List<String> getPnfdProvider() {
    return pnfdProvider;
  }

  public void setPnfdProvider(List<String> pnfdProvider) {
    this.pnfdProvider = pnfdProvider;
  }

  public SubscriptionsNsdmSubscriptionFilter pnfdProvider(List<String> pnfdProvider) {
    this.pnfdProvider = pnfdProvider;
    return this;
  }

  public SubscriptionsNsdmSubscriptionFilter addPnfdProviderItem(String pnfdProviderItem) {
    this.pnfdProvider.add(pnfdProviderItem);
    return this;
  }

 /**
   * Match the PNFD in a version independent manner.        
   * @return pnfdInvariantId
  **/
  @JsonProperty("pnfdInvariantId")
  public List<String> getPnfdInvariantId() {
    return pnfdInvariantId;
  }

  public void setPnfdInvariantId(List<String> pnfdInvariantId) {
    this.pnfdInvariantId = pnfdInvariantId;
  }

  public SubscriptionsNsdmSubscriptionFilter pnfdInvariantId(List<String> pnfdInvariantId) {
    this.pnfdInvariantId = pnfdInvariantId;
    return this;
  }

  public SubscriptionsNsdmSubscriptionFilter addPnfdInvariantIdItem(String pnfdInvariantIdItem) {
    this.pnfdInvariantId.add(pnfdInvariantIdItem);
    return this;
  }

 /**
   * Match particular on-boarding state of the PNFD.          
   * @return pnfdOnboardingState
  **/
  @JsonProperty("pnfdOnboardingState")
  public List<PnfdOnboardingStateEnum> getPnfdOnboardingState() {
    return pnfdOnboardingState;
  }

  public void setPnfdOnboardingState(List<PnfdOnboardingStateEnum> pnfdOnboardingState) {
    this.pnfdOnboardingState = pnfdOnboardingState;
  }

  public SubscriptionsNsdmSubscriptionFilter pnfdOnboardingState(List<PnfdOnboardingStateEnum> pnfdOnboardingState) {
    this.pnfdOnboardingState = pnfdOnboardingState;
    return this;
  }

  public SubscriptionsNsdmSubscriptionFilter addPnfdOnboardingStateItem(PnfdOnboardingStateEnum pnfdOnboardingStateItem) {
    this.pnfdOnboardingState.add(pnfdOnboardingStateItem);
    return this;
  }

 /**
   * Match the usage state of the individual PNF descriptor resource.   
   * @return pnfdUsageState
  **/
  @JsonProperty("pnfdUsageState")
  public List<PnfdUsageStateEnum> getPnfdUsageState() {
    return pnfdUsageState;
  }

  public void setPnfdUsageState(List<PnfdUsageStateEnum> pnfdUsageState) {
    this.pnfdUsageState = pnfdUsageState;
  }

  public SubscriptionsNsdmSubscriptionFilter pnfdUsageState(List<PnfdUsageStateEnum> pnfdUsageState) {
    this.pnfdUsageState = pnfdUsageState;
    return this;
  }

  public SubscriptionsNsdmSubscriptionFilter addPnfdUsageStateItem(PnfdUsageStateEnum pnfdUsageStateItem) {
    this.pnfdUsageState.add(pnfdUsageStateItem);
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubscriptionsNsdmSubscriptionFilter {\n");
    
    sb.append("    notificationTypes: ").append(toIndentedString(notificationTypes)).append("\n");
    sb.append("    nsdInfoId: ").append(toIndentedString(nsdInfoId)).append("\n");
    sb.append("    nsdId: ").append(toIndentedString(nsdId)).append("\n");
    sb.append("    nsdName: ").append(toIndentedString(nsdName)).append("\n");
    sb.append("    nsdVersion: ").append(toIndentedString(nsdVersion)).append("\n");
    sb.append("    nsdDesigner: ").append(toIndentedString(nsdDesigner)).append("\n");
    sb.append("    nsdInvariantId: ").append(toIndentedString(nsdInvariantId)).append("\n");
    sb.append("    vnfPkgIds: ").append(toIndentedString(vnfPkgIds)).append("\n");
    sb.append("    pnfdInfoIds: ").append(toIndentedString(pnfdInfoIds)).append("\n");
    sb.append("    nestedNsdInfoIds: ").append(toIndentedString(nestedNsdInfoIds)).append("\n");
    sb.append("    nsdOnboardingState: ").append(toIndentedString(nsdOnboardingState)).append("\n");
    sb.append("    nsdOperationalState: ").append(toIndentedString(nsdOperationalState)).append("\n");
    sb.append("    nsdUsageState: ").append(toIndentedString(nsdUsageState)).append("\n");
    sb.append("    pnfdId: ").append(toIndentedString(pnfdId)).append("\n");
    sb.append("    pnfdName: ").append(toIndentedString(pnfdName)).append("\n");
    sb.append("    pnfdVersion: ").append(toIndentedString(pnfdVersion)).append("\n");
    sb.append("    pnfdProvider: ").append(toIndentedString(pnfdProvider)).append("\n");
    sb.append("    pnfdInvariantId: ").append(toIndentedString(pnfdInvariantId)).append("\n");
    sb.append("    pnfdOnboardingState: ").append(toIndentedString(pnfdOnboardingState)).append("\n");
    sb.append("    pnfdUsageState: ").append(toIndentedString(pnfdUsageState)).append("\n");
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

