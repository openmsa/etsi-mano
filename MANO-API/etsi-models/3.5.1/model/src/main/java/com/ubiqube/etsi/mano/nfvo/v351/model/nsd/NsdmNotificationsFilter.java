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
package com.ubiqube.etsi.mano.nfvo.v351.model.nsd;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.nfvo.v351.model.nsd.NsdOnboardingStateType;
import com.ubiqube.etsi.mano.nfvo.v351.model.nsd.NsdOperationalStateType;
import com.ubiqube.etsi.mano.nfvo.v351.model.nsd.NsdUsageStateType;
import com.ubiqube.etsi.mano.nfvo.v351.model.nsd.PnfdOnboardingStateType;
import com.ubiqube.etsi.mano.nfvo.v351.model.nsd.PnfdUsageStateType;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents a subscription filter related to notifications about NSD management. It shall comply with the provisions defined in Table 5.5.3.2-1 of GS NFV-SOL 005. At a particular nesting level in the filter structure, the following applies: All attributes shall match in order for the filter to match (logical \&quot;and\&quot; between different filter attributes). If an attribute is an array, the attribute shall match if at least one of the values in the array matches (logical \&quot;or\&quot; between the values of one filter attribute). NOTE 1: The permitted values of the \&quot;notificationTypes\&quot; attribute are spelled exactly as the names of the notification types to facilitate automated code generation systems. NOTE 2: The attributes \&quot;nsdId\&quot; and \&quot;nsdInfoId\&quot; are alternatives to reference to a particular NSD in a filter. They should not be used both in the same filter instance, but one alternative should be chosen. NOTE 3: The attributes \&quot;pnfdId\&quot; and \&quot;pnfdInfoId\&quot; are alternatives to reference to a particular PNFD in a filter. They should not be used both in the same filter instance, but one alternative should be chosen. 
 */
@Schema(description = "This type represents a subscription filter related to notifications about NSD management. It shall comply with the provisions defined in Table 5.5.3.2-1 of GS NFV-SOL 005. At a particular nesting level in the filter structure, the following applies: All attributes shall match in order for the filter to match (logical \"and\" between different filter attributes). If an attribute is an array, the attribute shall match if at least one of the values in the array matches (logical \"or\" between the values of one filter attribute). NOTE 1: The permitted values of the \"notificationTypes\" attribute are spelled exactly as the names of the notification types to facilitate automated code generation systems. NOTE 2: The attributes \"nsdId\" and \"nsdInfoId\" are alternatives to reference to a particular NSD in a filter. They should not be used both in the same filter instance, but one alternative should be chosen. NOTE 3: The attributes \"pnfdId\" and \"pnfdInfoId\" are alternatives to reference to a particular PNFD in a filter. They should not be used both in the same filter instance, but one alternative should be chosen. ")
@Validated


public class NsdmNotificationsFilter  implements AnyOfNsdmNotificationsFilter {
  /**
   * Gets or Sets notificationTypes
   */
  public enum NotificationTypesEnum {
    NSDONBOARDINGNOTIFICATION("NsdOnBoardingNotification"),
    
    NSDONBOARDINGFAILURENOTIFICATION("NsdOnboardingFailureNotification"),
    
    NSDCHANGENOTIFICATION("NsdChangeNotification"),
    
    NSDDELETIONNOTIFICATION("NsdDeletionNotification"),
    
    PNFDONBOARDINGNOTIFICATION("PnfdOnBoardingNotification"),
    
    PNFDONBOARDINGFAILURENOTIFICATION("PnfdOnBoardingFailureNotification"),
    
    PNFDDELETIONNOTIFICATION("PnfdDeletionNotification");

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

  @JsonProperty("nsdInfoId")
  @Valid
  private List<String> nsdInfoId = null;

  @JsonProperty("nsdId")
  @Valid
  private List<String> nsdId = null;

  @JsonProperty("nsdName")
  @Valid
  private List<String> nsdName = null;

  @JsonProperty("nsdVersion")
  @Valid
  private List<String> nsdVersion = null;

  @JsonProperty("nsdDesigner")
  @Valid
  private List<String> nsdDesigner = null;

  @JsonProperty("nsdInvariantId")
  @Valid
  private List<String> nsdInvariantId = null;

  @JsonProperty("vnfPkgIds")
  @Valid
  private List<String> vnfPkgIds = null;

  @JsonProperty("pnfdInfoIds")
  @Valid
  private List<String> pnfdInfoIds = null;

  @JsonProperty("nestedNsdInfoIds")
  @Valid
  private List<String> nestedNsdInfoIds = null;

  @JsonProperty("nsdOnboardingState")
  @Valid
  private List<NsdOnboardingStateType> nsdOnboardingState = null;

  @JsonProperty("nsdOperationalState")
  @Valid
  private List<NsdOperationalStateType> nsdOperationalState = null;

  @JsonProperty("nsdUsageState")
  @Valid
  private List<NsdUsageStateType> nsdUsageState = null;

  @JsonProperty("pnfdId")
  @Valid
  private List<String> pnfdId = null;

  @JsonProperty("pnfdName")
  @Valid
  private List<String> pnfdName = null;

  @JsonProperty("pnfdVersion")
  @Valid
  private List<String> pnfdVersion = null;

  @JsonProperty("pnfdProvider")
  @Valid
  private List<String> pnfdProvider = null;

  @JsonProperty("pnfdInvariantId")
  @Valid
  private List<String> pnfdInvariantId = null;

  @JsonProperty("pnfdOnboardingState")
  @Valid
  private List<PnfdOnboardingStateType> pnfdOnboardingState = null;

  @JsonProperty("pnfdUsageState")
  @Valid
  private List<PnfdUsageStateType> pnfdUsageState = null;

  public NsdmNotificationsFilter notificationTypes(List<NotificationTypesEnum> notificationTypes) {
    this.notificationTypes = notificationTypes;
    return this;
  }

  public NsdmNotificationsFilter addNotificationTypesItem(NotificationTypesEnum notificationTypesItem) {
    if (this.notificationTypes == null) {
      this.notificationTypes = new ArrayList<>();
    }
    this.notificationTypes.add(notificationTypesItem);
    return this;
  }

  /**
   * Match particular notification types. Permitted values: - NsdOnBoardingNotification - NsdOnboardingFailureNotification - NsdChangeNotification - NsdDeletionNotification - PnfdOnBoardingNotification - PnfdOnBoardingFailureNotification - PnfdDeletionNotification See note 1. 
   * @return notificationTypes
   **/
  @Schema(description = "Match particular notification types. Permitted values: - NsdOnBoardingNotification - NsdOnboardingFailureNotification - NsdChangeNotification - NsdDeletionNotification - PnfdOnBoardingNotification - PnfdOnBoardingFailureNotification - PnfdDeletionNotification See note 1. ")
  
    public List<NotificationTypesEnum> getNotificationTypes() {
    return notificationTypes;
  }

  public void setNotificationTypes(List<NotificationTypesEnum> notificationTypes) {
    this.notificationTypes = notificationTypes;
  }

  public NsdmNotificationsFilter nsdInfoId(List<String> nsdInfoId) {
    this.nsdInfoId = nsdInfoId;
    return this;
  }

  public NsdmNotificationsFilter addNsdInfoIdItem(String nsdInfoIdItem) {
    if (this.nsdInfoId == null) {
      this.nsdInfoId = new ArrayList<>();
    }
    this.nsdInfoId.add(nsdInfoIdItem);
    return this;
  }

  /**
   * Match the NsdInfo identifier which is allocated by the NFVO. See note 2. 
   * @return nsdInfoId
   **/
  @Schema(description = "Match the NsdInfo identifier which is allocated by the NFVO. See note 2. ")
  
    public List<String> getNsdInfoId() {
    return nsdInfoId;
  }

  public void setNsdInfoId(List<String> nsdInfoId) {
    this.nsdInfoId = nsdInfoId;
  }

  public NsdmNotificationsFilter nsdId(List<String> nsdId) {
    this.nsdId = nsdId;
    return this;
  }

  public NsdmNotificationsFilter addNsdIdItem(String nsdIdItem) {
    if (this.nsdId == null) {
      this.nsdId = new ArrayList<>();
    }
    this.nsdId.add(nsdIdItem);
    return this;
  }

  /**
   * Match the NSD identifier, which is allocated by the NSD designer. See note 2. 
   * @return nsdId
   **/
  @Schema(description = "Match the NSD identifier, which is allocated by the NSD designer. See note 2. ")
  
    public List<String> getNsdId() {
    return nsdId;
  }

  public void setNsdId(List<String> nsdId) {
    this.nsdId = nsdId;
  }

  public NsdmNotificationsFilter nsdName(List<String> nsdName) {
    this.nsdName = nsdName;
    return this;
  }

  public NsdmNotificationsFilter addNsdNameItem(String nsdNameItem) {
    if (this.nsdName == null) {
      this.nsdName = new ArrayList<>();
    }
    this.nsdName.add(nsdNameItem);
    return this;
  }

  /**
   * Match the name of the on boarded NSD. 
   * @return nsdName
   **/
  @Schema(description = "Match the name of the on boarded NSD. ")
  
    public List<String> getNsdName() {
    return nsdName;
  }

  public void setNsdName(List<String> nsdName) {
    this.nsdName = nsdName;
  }

  public NsdmNotificationsFilter nsdVersion(List<String> nsdVersion) {
    this.nsdVersion = nsdVersion;
    return this;
  }

  public NsdmNotificationsFilter addNsdVersionItem(String nsdVersionItem) {
    if (this.nsdVersion == null) {
      this.nsdVersion = new ArrayList<>();
    }
    this.nsdVersion.add(nsdVersionItem);
    return this;
  }

  /**
   * Match the NSD version listed as part of this attribute. 
   * @return nsdVersion
   **/
  @Schema(description = "Match the NSD version listed as part of this attribute. ")
  
    public List<String> getNsdVersion() {
    return nsdVersion;
  }

  public void setNsdVersion(List<String> nsdVersion) {
    this.nsdVersion = nsdVersion;
  }

  public NsdmNotificationsFilter nsdDesigner(List<String> nsdDesigner) {
    this.nsdDesigner = nsdDesigner;
    return this;
  }

  public NsdmNotificationsFilter addNsdDesignerItem(String nsdDesignerItem) {
    if (this.nsdDesigner == null) {
      this.nsdDesigner = new ArrayList<>();
    }
    this.nsdDesigner.add(nsdDesignerItem);
    return this;
  }

  /**
   * Match the NSD designer of the on-boarded NSD. 
   * @return nsdDesigner
   **/
  @Schema(description = "Match the NSD designer of the on-boarded NSD. ")
  
    public List<String> getNsdDesigner() {
    return nsdDesigner;
  }

  public void setNsdDesigner(List<String> nsdDesigner) {
    this.nsdDesigner = nsdDesigner;
  }

  public NsdmNotificationsFilter nsdInvariantId(List<String> nsdInvariantId) {
    this.nsdInvariantId = nsdInvariantId;
    return this;
  }

  public NsdmNotificationsFilter addNsdInvariantIdItem(String nsdInvariantIdItem) {
    if (this.nsdInvariantId == null) {
      this.nsdInvariantId = new ArrayList<>();
    }
    this.nsdInvariantId.add(nsdInvariantIdItem);
    return this;
  }

  /**
   * Match the NSD invariant identifier which is allocated by the NSD designer and identifies an NSD in a version independent manner. 
   * @return nsdInvariantId
   **/
  @Schema(description = "Match the NSD invariant identifier which is allocated by the NSD designer and identifies an NSD in a version independent manner. ")
  
    public List<String> getNsdInvariantId() {
    return nsdInvariantId;
  }

  public void setNsdInvariantId(List<String> nsdInvariantId) {
    this.nsdInvariantId = nsdInvariantId;
  }

  public NsdmNotificationsFilter vnfPkgIds(List<String> vnfPkgIds) {
    this.vnfPkgIds = vnfPkgIds;
    return this;
  }

  public NsdmNotificationsFilter addVnfPkgIdsItem(String vnfPkgIdsItem) {
    if (this.vnfPkgIds == null) {
      this.vnfPkgIds = new ArrayList<>();
    }
    this.vnfPkgIds.add(vnfPkgIdsItem);
    return this;
  }

  /**
   * Match VNF packages with a package identifier listed in the attribute. 
   * @return vnfPkgIds
   **/
  @Schema(description = "Match VNF packages with a package identifier listed in the attribute. ")
  
    public List<String> getVnfPkgIds() {
    return vnfPkgIds;
  }

  public void setVnfPkgIds(List<String> vnfPkgIds) {
    this.vnfPkgIds = vnfPkgIds;
  }

  public NsdmNotificationsFilter pnfdInfoIds(List<String> pnfdInfoIds) {
    this.pnfdInfoIds = pnfdInfoIds;
    return this;
  }

  public NsdmNotificationsFilter addPnfdInfoIdsItem(String pnfdInfoIdsItem) {
    if (this.pnfdInfoIds == null) {
      this.pnfdInfoIds = new ArrayList<>();
    }
    this.pnfdInfoIds.add(pnfdInfoIdsItem);
    return this;
  }

  /**
   * Match the PnfdInfo identifier for the PNFD referenced by the on-boarded NSD. See note 3. 
   * @return pnfdInfoIds
   **/
  @Schema(description = "Match the PnfdInfo identifier for the PNFD referenced by the on-boarded NSD. See note 3. ")
  
    public List<String> getPnfdInfoIds() {
    return pnfdInfoIds;
  }

  public void setPnfdInfoIds(List<String> pnfdInfoIds) {
    this.pnfdInfoIds = pnfdInfoIds;
  }

  public NsdmNotificationsFilter nestedNsdInfoIds(List<String> nestedNsdInfoIds) {
    this.nestedNsdInfoIds = nestedNsdInfoIds;
    return this;
  }

  public NsdmNotificationsFilter addNestedNsdInfoIdsItem(String nestedNsdInfoIdsItem) {
    if (this.nestedNsdInfoIds == null) {
      this.nestedNsdInfoIds = new ArrayList<>();
    }
    this.nestedNsdInfoIds.add(nestedNsdInfoIdsItem);
    return this;
  }

  /**
   * Match the NsdInfo identifier for the nested NSD referenced by the on-boarded NSD. 
   * @return nestedNsdInfoIds
   **/
  @Schema(description = "Match the NsdInfo identifier for the nested NSD referenced by the on-boarded NSD. ")
  
    public List<String> getNestedNsdInfoIds() {
    return nestedNsdInfoIds;
  }

  public void setNestedNsdInfoIds(List<String> nestedNsdInfoIds) {
    this.nestedNsdInfoIds = nestedNsdInfoIds;
  }

  public NsdmNotificationsFilter nsdOnboardingState(List<NsdOnboardingStateType> nsdOnboardingState) {
    this.nsdOnboardingState = nsdOnboardingState;
    return this;
  }

  public NsdmNotificationsFilter addNsdOnboardingStateItem(NsdOnboardingStateType nsdOnboardingStateItem) {
    if (this.nsdOnboardingState == null) {
      this.nsdOnboardingState = new ArrayList<>();
    }
    this.nsdOnboardingState.add(nsdOnboardingStateItem);
    return this;
  }

  /**
   * Match particular on-boarding state of the NSD. 
   * @return nsdOnboardingState
   **/
  @Schema(description = "Match particular on-boarding state of the NSD. ")
      @Valid
    public List<NsdOnboardingStateType> getNsdOnboardingState() {
    return nsdOnboardingState;
  }

  public void setNsdOnboardingState(List<NsdOnboardingStateType> nsdOnboardingState) {
    this.nsdOnboardingState = nsdOnboardingState;
  }

  public NsdmNotificationsFilter nsdOperationalState(List<NsdOperationalStateType> nsdOperationalState) {
    this.nsdOperationalState = nsdOperationalState;
    return this;
  }

  public NsdmNotificationsFilter addNsdOperationalStateItem(NsdOperationalStateType nsdOperationalStateItem) {
    if (this.nsdOperationalState == null) {
      this.nsdOperationalState = new ArrayList<>();
    }
    this.nsdOperationalState.add(nsdOperationalStateItem);
    return this;
  }

  /**
   * Match particular operational state of the on-boarded NSD. 
   * @return nsdOperationalState
   **/
  @Schema(description = "Match particular operational state of the on-boarded NSD. ")
      @Valid
    public List<NsdOperationalStateType> getNsdOperationalState() {
    return nsdOperationalState;
  }

  public void setNsdOperationalState(List<NsdOperationalStateType> nsdOperationalState) {
    this.nsdOperationalState = nsdOperationalState;
  }

  public NsdmNotificationsFilter nsdUsageState(List<NsdUsageStateType> nsdUsageState) {
    this.nsdUsageState = nsdUsageState;
    return this;
  }

  public NsdmNotificationsFilter addNsdUsageStateItem(NsdUsageStateType nsdUsageStateItem) {
    if (this.nsdUsageState == null) {
      this.nsdUsageState = new ArrayList<>();
    }
    this.nsdUsageState.add(nsdUsageStateItem);
    return this;
  }

  /**
   * Match particular usage state of the on-boarded NSD. 
   * @return nsdUsageState
   **/
  @Schema(description = "Match particular usage state of the on-boarded NSD. ")
      @Valid
    public List<NsdUsageStateType> getNsdUsageState() {
    return nsdUsageState;
  }

  public void setNsdUsageState(List<NsdUsageStateType> nsdUsageState) {
    this.nsdUsageState = nsdUsageState;
  }

  public NsdmNotificationsFilter pnfdId(List<String> pnfdId) {
    this.pnfdId = pnfdId;
    return this;
  }

  public NsdmNotificationsFilter addPnfdIdItem(String pnfdIdItem) {
    if (this.pnfdId == null) {
      this.pnfdId = new ArrayList<>();
    }
    this.pnfdId.add(pnfdIdItem);
    return this;
  }

  /**
   * Match the PNFD identifier which is copied from the PNFD content. 
   * @return pnfdId
   **/
  @Schema(description = "Match the PNFD identifier which is copied from the PNFD content. ")
  
    public List<String> getPnfdId() {
    return pnfdId;
  }

  public void setPnfdId(List<String> pnfdId) {
    this.pnfdId = pnfdId;
  }

  public NsdmNotificationsFilter pnfdName(List<String> pnfdName) {
    this.pnfdName = pnfdName;
    return this;
  }

  public NsdmNotificationsFilter addPnfdNameItem(String pnfdNameItem) {
    if (this.pnfdName == null) {
      this.pnfdName = new ArrayList<>();
    }
    this.pnfdName.add(pnfdNameItem);
    return this;
  }

  /**
   * Match the name of the on-boarded PNFD. 
   * @return pnfdName
   **/
  @Schema(description = "Match the name of the on-boarded PNFD. ")
  
    public List<String> getPnfdName() {
    return pnfdName;
  }

  public void setPnfdName(List<String> pnfdName) {
    this.pnfdName = pnfdName;
  }

  public NsdmNotificationsFilter pnfdVersion(List<String> pnfdVersion) {
    this.pnfdVersion = pnfdVersion;
    return this;
  }

  public NsdmNotificationsFilter addPnfdVersionItem(String pnfdVersionItem) {
    if (this.pnfdVersion == null) {
      this.pnfdVersion = new ArrayList<>();
    }
    this.pnfdVersion.add(pnfdVersionItem);
    return this;
  }

  /**
   * Match the PNFD designer of the on-boarded PNFD. 
   * @return pnfdVersion
   **/
  @Schema(description = "Match the PNFD designer of the on-boarded PNFD. ")
  
    public List<String> getPnfdVersion() {
    return pnfdVersion;
  }

  public void setPnfdVersion(List<String> pnfdVersion) {
    this.pnfdVersion = pnfdVersion;
  }

  public NsdmNotificationsFilter pnfdProvider(List<String> pnfdProvider) {
    this.pnfdProvider = pnfdProvider;
    return this;
  }

  public NsdmNotificationsFilter addPnfdProviderItem(String pnfdProviderItem) {
    if (this.pnfdProvider == null) {
      this.pnfdProvider = new ArrayList<>();
    }
    this.pnfdProvider.add(pnfdProviderItem);
    return this;
  }

  /**
   * Match the provider of the on-boarded PNFD. 
   * @return pnfdProvider
   **/
  @Schema(description = "Match the provider of the on-boarded PNFD. ")
  
    public List<String> getPnfdProvider() {
    return pnfdProvider;
  }

  public void setPnfdProvider(List<String> pnfdProvider) {
    this.pnfdProvider = pnfdProvider;
  }

  public NsdmNotificationsFilter pnfdInvariantId(List<String> pnfdInvariantId) {
    this.pnfdInvariantId = pnfdInvariantId;
    return this;
  }

  public NsdmNotificationsFilter addPnfdInvariantIdItem(String pnfdInvariantIdItem) {
    if (this.pnfdInvariantId == null) {
      this.pnfdInvariantId = new ArrayList<>();
    }
    this.pnfdInvariantId.add(pnfdInvariantIdItem);
    return this;
  }

  /**
   * Match the PNFD in a version independent manner. 
   * @return pnfdInvariantId
   **/
  @Schema(description = "Match the PNFD in a version independent manner. ")
  
    public List<String> getPnfdInvariantId() {
    return pnfdInvariantId;
  }

  public void setPnfdInvariantId(List<String> pnfdInvariantId) {
    this.pnfdInvariantId = pnfdInvariantId;
  }

  public NsdmNotificationsFilter pnfdOnboardingState(List<PnfdOnboardingStateType> pnfdOnboardingState) {
    this.pnfdOnboardingState = pnfdOnboardingState;
    return this;
  }

  public NsdmNotificationsFilter addPnfdOnboardingStateItem(PnfdOnboardingStateType pnfdOnboardingStateItem) {
    if (this.pnfdOnboardingState == null) {
      this.pnfdOnboardingState = new ArrayList<>();
    }
    this.pnfdOnboardingState.add(pnfdOnboardingStateItem);
    return this;
  }

  /**
   * Match particular on-boarding state of the PNFD. 
   * @return pnfdOnboardingState
   **/
  @Schema(description = "Match particular on-boarding state of the PNFD. ")
      @Valid
    public List<PnfdOnboardingStateType> getPnfdOnboardingState() {
    return pnfdOnboardingState;
  }

  public void setPnfdOnboardingState(List<PnfdOnboardingStateType> pnfdOnboardingState) {
    this.pnfdOnboardingState = pnfdOnboardingState;
  }

  public NsdmNotificationsFilter pnfdUsageState(List<PnfdUsageStateType> pnfdUsageState) {
    this.pnfdUsageState = pnfdUsageState;
    return this;
  }

  public NsdmNotificationsFilter addPnfdUsageStateItem(PnfdUsageStateType pnfdUsageStateItem) {
    if (this.pnfdUsageState == null) {
      this.pnfdUsageState = new ArrayList<>();
    }
    this.pnfdUsageState.add(pnfdUsageStateItem);
    return this;
  }

  /**
   * Match the usage state of the individual PNF descriptor resource. 
   * @return pnfdUsageState
   **/
  @Schema(description = "Match the usage state of the individual PNF descriptor resource. ")
      @Valid
    public List<PnfdUsageStateType> getPnfdUsageState() {
    return pnfdUsageState;
  }

  public void setPnfdUsageState(List<PnfdUsageStateType> pnfdUsageState) {
    this.pnfdUsageState = pnfdUsageState;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NsdmNotificationsFilter nsdmNotificationsFilter = (NsdmNotificationsFilter) o;
    return Objects.equals(this.notificationTypes, nsdmNotificationsFilter.notificationTypes) &&
        Objects.equals(this.nsdInfoId, nsdmNotificationsFilter.nsdInfoId) &&
        Objects.equals(this.nsdId, nsdmNotificationsFilter.nsdId) &&
        Objects.equals(this.nsdName, nsdmNotificationsFilter.nsdName) &&
        Objects.equals(this.nsdVersion, nsdmNotificationsFilter.nsdVersion) &&
        Objects.equals(this.nsdDesigner, nsdmNotificationsFilter.nsdDesigner) &&
        Objects.equals(this.nsdInvariantId, nsdmNotificationsFilter.nsdInvariantId) &&
        Objects.equals(this.vnfPkgIds, nsdmNotificationsFilter.vnfPkgIds) &&
        Objects.equals(this.pnfdInfoIds, nsdmNotificationsFilter.pnfdInfoIds) &&
        Objects.equals(this.nestedNsdInfoIds, nsdmNotificationsFilter.nestedNsdInfoIds) &&
        Objects.equals(this.nsdOnboardingState, nsdmNotificationsFilter.nsdOnboardingState) &&
        Objects.equals(this.nsdOperationalState, nsdmNotificationsFilter.nsdOperationalState) &&
        Objects.equals(this.nsdUsageState, nsdmNotificationsFilter.nsdUsageState) &&
        Objects.equals(this.pnfdId, nsdmNotificationsFilter.pnfdId) &&
        Objects.equals(this.pnfdName, nsdmNotificationsFilter.pnfdName) &&
        Objects.equals(this.pnfdVersion, nsdmNotificationsFilter.pnfdVersion) &&
        Objects.equals(this.pnfdProvider, nsdmNotificationsFilter.pnfdProvider) &&
        Objects.equals(this.pnfdInvariantId, nsdmNotificationsFilter.pnfdInvariantId) &&
        Objects.equals(this.pnfdOnboardingState, nsdmNotificationsFilter.pnfdOnboardingState) &&
        Objects.equals(this.pnfdUsageState, nsdmNotificationsFilter.pnfdUsageState);
  }

  @Override
  public int hashCode() {
    return Objects.hash(notificationTypes, nsdInfoId, nsdId, nsdName, nsdVersion, nsdDesigner, nsdInvariantId, vnfPkgIds, pnfdInfoIds, nestedNsdInfoIds, nsdOnboardingState, nsdOperationalState, nsdUsageState, pnfdId, pnfdName, pnfdVersion, pnfdProvider, pnfdInvariantId, pnfdOnboardingState, pnfdUsageState);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsdmNotificationsFilter {\n");
    
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
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
