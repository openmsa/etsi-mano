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
package com.ubiqube.etsi.mano.nfvo.v351.model.vnf;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.nfvo.v351.model.vnf.PackageOperationalStateType;
import com.ubiqube.etsi.mano.nfvo.v351.model.vnf.PackageUsageStateType;
import com.ubiqube.etsi.mano.nfvo.v351.model.vnf.PkgmNotificationsFilterVnfProductsFromProviders1;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents a subscription filter related to notifications related to VNF package management. At a particular nesting level in the filter structure, the following applies: All attributes shall match in order for the filter to match (logical \&quot;and\&quot; between different filter attributes). If an attribute is an array, the attribute shall match if at least one of the values in the array matches (logical \&quot;or\&quot; between the values of one filter attribute). NOTE 1: The permitted values of the \&quot;notificationTypes\&quot; attribute are spelled exactly as the names of the notification types to facilitate automated code generation systems. NOTE 2: The attributes \&quot;vnfProductsFromProviders\&quot;, \&quot;vnfdId\&quot;, and \&quot;vnfPkgId\&quot; are alternatives to reference particular VNF packages in a filter. They should not be used both in the same filter instance, but one alternative should be chosen. 
 */
@Schema(description = "This type represents a subscription filter related to notifications related to VNF package management. At a particular nesting level in the filter structure, the following applies: All attributes shall match in order for the filter to match (logical \"and\" between different filter attributes). If an attribute is an array, the attribute shall match if at least one of the values in the array matches (logical \"or\" between the values of one filter attribute). NOTE 1: The permitted values of the \"notificationTypes\" attribute are spelled exactly as the names of the notification types to facilitate automated code generation systems. NOTE 2: The attributes \"vnfProductsFromProviders\", \"vnfdId\", and \"vnfPkgId\" are alternatives to reference particular VNF packages in a filter. They should not be used both in the same filter instance, but one alternative should be chosen. ")
@Validated


public class PkgmNotificationsFilter  implements OneOfPkgmNotificationsFilter {
  /**
   * Match particular notification types. Permitted values: - VnfPackageOnboardingNotification - VnfPackageChangeNotification See note 1. 
   */
  public enum NotificationTypesEnum {
    VNFPACKAGEONBOARDINGNOTIFICATION("VnfPackageOnboardingNotification"),
    
    VNFPACKAGECHANGENOTIFICATION("VnfPackageChangeNotification");

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
  private NotificationTypesEnum notificationTypes = null;

  @JsonProperty("vnfProductsFromProviders")
  @Valid
  private List<PkgmNotificationsFilterVnfProductsFromProviders1> vnfProductsFromProviders = null;

  @JsonProperty("vnfdId")
  @Valid
  private List<String> vnfdId = null;

  @JsonProperty("vnfPkgId")
  @Valid
  private List<String> vnfPkgId = null;

  @JsonProperty("operationalState")
  @Valid
  private List<PackageOperationalStateType> operationalState = null;

  @JsonProperty("usageState")
  @Valid
  private List<PackageUsageStateType> usageState = null;

  @JsonProperty("vnfmInfo")
  @Valid
  private List<String> vnfmInfo = null;

  public PkgmNotificationsFilter notificationTypes(NotificationTypesEnum notificationTypes) {
    this.notificationTypes = notificationTypes;
    return this;
  }

  /**
   * Match particular notification types. Permitted values: - VnfPackageOnboardingNotification - VnfPackageChangeNotification See note 1. 
   * @return notificationTypes
   **/
  @Schema(description = "Match particular notification types. Permitted values: - VnfPackageOnboardingNotification - VnfPackageChangeNotification See note 1. ")
  
    public NotificationTypesEnum getNotificationTypes() {
    return notificationTypes;
  }

  public void setNotificationTypes(NotificationTypesEnum notificationTypes) {
    this.notificationTypes = notificationTypes;
  }

  public PkgmNotificationsFilter vnfProductsFromProviders(List<PkgmNotificationsFilterVnfProductsFromProviders1> vnfProductsFromProviders) {
    this.vnfProductsFromProviders = vnfProductsFromProviders;
    return this;
  }

  public PkgmNotificationsFilter addVnfProductsFromProvidersItem(PkgmNotificationsFilterVnfProductsFromProviders1 vnfProductsFromProvidersItem) {
    if (this.vnfProductsFromProviders == null) {
      this.vnfProductsFromProviders = new ArrayList<>();
    }
    this.vnfProductsFromProviders.add(vnfProductsFromProvidersItem);
    return this;
  }

  /**
   * If present, match VNF packages that contain VNF products from certain providers. See note 2. 
   * @return vnfProductsFromProviders
   **/
  @Schema(description = "If present, match VNF packages that contain VNF products from certain providers. See note 2. ")
      @Valid
    public List<PkgmNotificationsFilterVnfProductsFromProviders1> getVnfProductsFromProviders() {
    return vnfProductsFromProviders;
  }

  public void setVnfProductsFromProviders(List<PkgmNotificationsFilterVnfProductsFromProviders1> vnfProductsFromProviders) {
    this.vnfProductsFromProviders = vnfProductsFromProviders;
  }

  public PkgmNotificationsFilter vnfdId(List<String> vnfdId) {
    this.vnfdId = vnfdId;
    return this;
  }

  public PkgmNotificationsFilter addVnfdIdItem(String vnfdIdItem) {
    if (this.vnfdId == null) {
      this.vnfdId = new ArrayList<>();
    }
    this.vnfdId.add(vnfdIdItem);
    return this;
  }

  /**
   * Match VNF packages with a VNFD identifier listed in the attribute. See note 2. 
   * @return vnfdId
   **/
  @Schema(description = "Match VNF packages with a VNFD identifier listed in the attribute. See note 2. ")
  
    public List<String> getVnfdId() {
    return vnfdId;
  }

  public void setVnfdId(List<String> vnfdId) {
    this.vnfdId = vnfdId;
  }

  public PkgmNotificationsFilter vnfPkgId(List<String> vnfPkgId) {
    this.vnfPkgId = vnfPkgId;
    return this;
  }

  public PkgmNotificationsFilter addVnfPkgIdItem(String vnfPkgIdItem) {
    if (this.vnfPkgId == null) {
      this.vnfPkgId = new ArrayList<>();
    }
    this.vnfPkgId.add(vnfPkgIdItem);
    return this;
  }

  /**
   * Match VNF packages with a package identifier listed in the attribute. May be present if the \"notificationTypes\" attribute contains the value \"VnfPackageChangeNotification\", and shall be absent otherwise. See note 2. 
   * @return vnfPkgId
   **/
  @Schema(description = "Match VNF packages with a package identifier listed in the attribute. May be present if the \"notificationTypes\" attribute contains the value \"VnfPackageChangeNotification\", and shall be absent otherwise. See note 2. ")
  
    public List<String> getVnfPkgId() {
    return vnfPkgId;
  }

  public void setVnfPkgId(List<String> vnfPkgId) {
    this.vnfPkgId = vnfPkgId;
  }

  public PkgmNotificationsFilter operationalState(List<PackageOperationalStateType> operationalState) {
    this.operationalState = operationalState;
    return this;
  }

  public PkgmNotificationsFilter addOperationalStateItem(PackageOperationalStateType operationalStateItem) {
    if (this.operationalState == null) {
      this.operationalState = new ArrayList<>();
    }
    this.operationalState.add(operationalStateItem);
    return this;
  }

  /**
   * Match VNF packages with a package identifier listed in the attribute. May be present if the \"notificationTypes\" attribute contains the value \"VnfPackageChangeNotification\", and shall be absent otherwise. 
   * @return operationalState
   **/
  @Schema(description = "Match VNF packages with a package identifier listed in the attribute. May be present if the \"notificationTypes\" attribute contains the value \"VnfPackageChangeNotification\", and shall be absent otherwise. ")
      @Valid
    public List<PackageOperationalStateType> getOperationalState() {
    return operationalState;
  }

  public void setOperationalState(List<PackageOperationalStateType> operationalState) {
    this.operationalState = operationalState;
  }

  public PkgmNotificationsFilter usageState(List<PackageUsageStateType> usageState) {
    this.usageState = usageState;
    return this;
  }

  public PkgmNotificationsFilter addUsageStateItem(PackageUsageStateType usageStateItem) {
    if (this.usageState == null) {
      this.usageState = new ArrayList<>();
    }
    this.usageState.add(usageStateItem);
    return this;
  }

  /**
   * Match particular usage state of the on-boarded VNF package. May be present if the \"notificationTypes\" attribute contains the value \"VnfPackageChangeNotification\", and shall be absent otherwise. 
   * @return usageState
   **/
  @Schema(description = "Match particular usage state of the on-boarded VNF package. May be present if the \"notificationTypes\" attribute contains the value \"VnfPackageChangeNotification\", and shall be absent otherwise. ")
      @Valid
    public List<PackageUsageStateType> getUsageState() {
    return usageState;
  }

  public void setUsageState(List<PackageUsageStateType> usageState) {
    this.usageState = usageState;
  }

  public PkgmNotificationsFilter vnfmInfo(List<String> vnfmInfo) {
    this.vnfmInfo = vnfmInfo;
    return this;
  }

  public PkgmNotificationsFilter addVnfmInfoItem(String vnfmInfoItem) {
    if (this.vnfmInfo == null) {
      this.vnfmInfo = new ArrayList<>();
    }
    this.vnfmInfo.add(vnfmInfoItem);
    return this;
  }

  /**
   * Match strings that specify VNFMs compatible with the VNF. See Table 9.5.2.5-1. 
   * @return vnfmInfo
   **/
  @Schema(description = "Match strings that specify VNFMs compatible with the VNF. See Table 9.5.2.5-1. ")
  
    public List<String> getVnfmInfo() {
    return vnfmInfo;
  }

  public void setVnfmInfo(List<String> vnfmInfo) {
    this.vnfmInfo = vnfmInfo;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PkgmNotificationsFilter pkgmNotificationsFilter = (PkgmNotificationsFilter) o;
    return Objects.equals(this.notificationTypes, pkgmNotificationsFilter.notificationTypes) &&
        Objects.equals(this.vnfProductsFromProviders, pkgmNotificationsFilter.vnfProductsFromProviders) &&
        Objects.equals(this.vnfdId, pkgmNotificationsFilter.vnfdId) &&
        Objects.equals(this.vnfPkgId, pkgmNotificationsFilter.vnfPkgId) &&
        Objects.equals(this.operationalState, pkgmNotificationsFilter.operationalState) &&
        Objects.equals(this.usageState, pkgmNotificationsFilter.usageState) &&
        Objects.equals(this.vnfmInfo, pkgmNotificationsFilter.vnfmInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(notificationTypes, vnfProductsFromProviders, vnfdId, vnfPkgId, operationalState, usageState, vnfmInfo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PkgmNotificationsFilter {\n");
    
    sb.append("    notificationTypes: ").append(toIndentedString(notificationTypes)).append("\n");
    sb.append("    vnfProductsFromProviders: ").append(toIndentedString(vnfProductsFromProviders)).append("\n");
    sb.append("    vnfdId: ").append(toIndentedString(vnfdId)).append("\n");
    sb.append("    vnfPkgId: ").append(toIndentedString(vnfPkgId)).append("\n");
    sb.append("    operationalState: ").append(toIndentedString(operationalState)).append("\n");
    sb.append("    usageState: ").append(toIndentedString(usageState)).append("\n");
    sb.append("    vnfmInfo: ").append(toIndentedString(vnfmInfo)).append("\n");
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
