package com.ubiqube.etsi.mano.vnfm.v331.model.vnf;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnf.PackageOperationalStateType;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnf.PackageUsageStateType;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnf.PkgmNotificationsFilterVnfProductsFromProviders;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents a subscription filter related to notifications related to VNF package management. At a particular nesting level in the filter structure, the following applies: All attributes shall match in order for the filter to match (logical \&quot;and\&quot; between different filter attributes). If an attribute is an array, the attribute shall match if at least one of the values in the array matches (logical \&quot;or\&quot; between the values of one filter attribute). 
 */
@Schema(description = "This type represents a subscription filter related to notifications related to VNF package management. At a particular nesting level in the filter structure, the following applies: All attributes shall match in order for the filter to match (logical \"and\" between different filter attributes). If an attribute is an array, the attribute shall match if at least one of the values in the array matches (logical \"or\" between the values of one filter attribute). ")
@Validated


public class PkgmNotificationsFilter  implements AnyOfPkgmNotificationsFilter {
  /**
   * Gets or Sets notificationTypes
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
  @Valid
  private List<NotificationTypesEnum> notificationTypes = null;

  @JsonProperty("vnfProductsFromProviders")
  @Valid
  private List<PkgmNotificationsFilterVnfProductsFromProviders> vnfProductsFromProviders = null;

  @JsonProperty("vnfdId")
  @Valid
  private List<String> vnfdId = null;

  @JsonProperty("vnfPkgId")
  @Valid
  private List<String> vnfPkgId = null;

  @JsonProperty("operationalState")
  private PackageOperationalStateType operationalState = null;

  @JsonProperty("usageState")
  private PackageUsageStateType usageState = null;

  @JsonProperty("vnfmInfo")
  @Valid
  private List<String> vnfmInfo = null;

  public PkgmNotificationsFilter notificationTypes(List<NotificationTypesEnum> notificationTypes) {
    this.notificationTypes = notificationTypes;
    return this;
  }

  public PkgmNotificationsFilter addNotificationTypesItem(NotificationTypesEnum notificationTypesItem) {
    if (this.notificationTypes == null) {
      this.notificationTypes = new ArrayList<>();
    }
    this.notificationTypes.add(notificationTypesItem);
    return this;
  }

  /**
   * Match particular notification types. Permitted values: - VnfPackageOnboardingNotification - VnfPackageChangeNotification The permitted values of the \"notificationTypes\" attribute are spelled exactly as the names of the notification types to facilitate automated code generation systems. 
   * @return notificationTypes
   **/
  @Schema(description = "Match particular notification types. Permitted values: - VnfPackageOnboardingNotification - VnfPackageChangeNotification The permitted values of the \"notificationTypes\" attribute are spelled exactly as the names of the notification types to facilitate automated code generation systems. ")
  
    public List<NotificationTypesEnum> getNotificationTypes() {
    return notificationTypes;
  }

  public void setNotificationTypes(List<NotificationTypesEnum> notificationTypes) {
    this.notificationTypes = notificationTypes;
  }

  public PkgmNotificationsFilter vnfProductsFromProviders(List<PkgmNotificationsFilterVnfProductsFromProviders> vnfProductsFromProviders) {
    this.vnfProductsFromProviders = vnfProductsFromProviders;
    return this;
  }

  public PkgmNotificationsFilter addVnfProductsFromProvidersItem(PkgmNotificationsFilterVnfProductsFromProviders vnfProductsFromProvidersItem) {
    if (this.vnfProductsFromProviders == null) {
      this.vnfProductsFromProviders = new ArrayList<>();
    }
    this.vnfProductsFromProviders.add(vnfProductsFromProvidersItem);
    return this;
  }

  /**
   * If present, match VNF packages that contain VNF products from certain providers. The attributes \"vnfProductsFromProviders\", \"vnfdId\" and \"vnfPkgId\" are alternatives to reference to particular VNF packages in a filter. They should not be used both in the same filter instance, but one alternative should be chosen. 
   * @return vnfProductsFromProviders
   **/
  @Schema(description = "If present, match VNF packages that contain VNF products from certain providers. The attributes \"vnfProductsFromProviders\", \"vnfdId\" and \"vnfPkgId\" are alternatives to reference to particular VNF packages in a filter. They should not be used both in the same filter instance, but one alternative should be chosen. ")
      @Valid
    public List<PkgmNotificationsFilterVnfProductsFromProviders> getVnfProductsFromProviders() {
    return vnfProductsFromProviders;
  }

  public void setVnfProductsFromProviders(List<PkgmNotificationsFilterVnfProductsFromProviders> vnfProductsFromProviders) {
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
   * Match VNF packages with a VNFD identifier listed in the attribute. The attributes \"vnfProductsFromProviders\", \"vnfdId\" and \"vnfPkgId\" are alternatives to reference to particular VNF packages in a filter. They should not be used both in the same filter instance, but one alternative should be chosen. 
   * @return vnfdId
   **/
  @Schema(description = "Match VNF packages with a VNFD identifier listed in the attribute. The attributes \"vnfProductsFromProviders\", \"vnfdId\" and \"vnfPkgId\" are alternatives to reference to particular VNF packages in a filter. They should not be used both in the same filter instance, but one alternative should be chosen. ")
  
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
   * Match VNF packages with a package identifier listed in the attribute. May be present if the \"notificationTypes\" attribute contains the value \"VnfPackageChangeNotification\" and shall be absent otherwise. The attributes \"vnfProductsFromProviders\", \"vnfdId\" and \"vnfPkgId\" are alternatives to reference to particular VNF packages in a filter. They should not be used both in the same filter instance, but one alternative should be chosen. 
   * @return vnfPkgId
   **/
  @Schema(description = "Match VNF packages with a package identifier listed in the attribute. May be present if the \"notificationTypes\" attribute contains the value \"VnfPackageChangeNotification\" and shall be absent otherwise. The attributes \"vnfProductsFromProviders\", \"vnfdId\" and \"vnfPkgId\" are alternatives to reference to particular VNF packages in a filter. They should not be used both in the same filter instance, but one alternative should be chosen. ")
  
    public List<String> getVnfPkgId() {
    return vnfPkgId;
  }

  public void setVnfPkgId(List<String> vnfPkgId) {
    this.vnfPkgId = vnfPkgId;
  }

  public PkgmNotificationsFilter operationalState(PackageOperationalStateType operationalState) {
    this.operationalState = operationalState;
    return this;
  }

  /**
   * Get operationalState
   * @return operationalState
   **/
  @Schema(description = "")
  
    @Valid
    public PackageOperationalStateType getOperationalState() {
    return operationalState;
  }

  public void setOperationalState(PackageOperationalStateType operationalState) {
    this.operationalState = operationalState;
  }

  public PkgmNotificationsFilter usageState(PackageUsageStateType usageState) {
    this.usageState = usageState;
    return this;
  }

  /**
   * Get usageState
   * @return usageState
   **/
  @Schema(description = "")
  
    @Valid
    public PackageUsageStateType getUsageState() {
    return usageState;
  }

  public void setUsageState(PackageUsageStateType usageState) {
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
   * Match strings that specify VNFMs compatible with the VNF. See table 10.5.2.2-1. 
   * @return vnfmInfo
   **/
  @Schema(description = "Match strings that specify VNFMs compatible with the VNF. See table 10.5.2.2-1. ")
  
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
