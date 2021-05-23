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
package com.ubiqube.etsi.mano.nfvo.v281.model.vnf;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.nfvo.v281.model.vnf.PkgmNotificationsFilterVnfProductsFromProviders;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents a subscription filter related to notifications related to VNF package management. At a particular nesting level in the filter structure, the following applies: All attributes shall match in order for the filter to match (logical \&quot;and\&quot; between different filter attributes). If an attribute is an array, the attribute shall match if at least one of the values in the array matches (logical \&quot;or\&quot; between the values of one filter attribute). 
 */
@ApiModel(description = "This type represents a subscription filter related to notifications related to VNF package management. At a particular nesting level in the filter structure, the following applies: All attributes shall match in order for the filter to match (logical \"and\" between different filter attributes). If an attribute is an array, the attribute shall match if at least one of the values in the array matches (logical \"or\" between the values of one filter attribute). ")
@Validated

public class PkgmNotificationsFilter   {
  /**
   * Match particular notification types. Permitted values: - VnfPackageOnboardingNotification - VnfPackageChangeNotification 
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
  private List<PkgmNotificationsFilterVnfProductsFromProviders> vnfProductsFromProviders = null;

  public PkgmNotificationsFilter notificationTypes(NotificationTypesEnum notificationTypes) {
    this.notificationTypes = notificationTypes;
    return this;
  }

  /**
   * Match particular notification types. Permitted values: - VnfPackageOnboardingNotification - VnfPackageChangeNotification 
   * @return notificationTypes
  **/
  @ApiModelProperty(value = "Match particular notification types. Permitted values: - VnfPackageOnboardingNotification - VnfPackageChangeNotification ")


  public NotificationTypesEnum getNotificationTypes() {
    return notificationTypes;
  }

  public void setNotificationTypes(NotificationTypesEnum notificationTypes) {
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
   * If present, match VNF packages that contain VNF products from certain providers. 
   * @return vnfProductsFromProviders
  **/
  @ApiModelProperty(value = "If present, match VNF packages that contain VNF products from certain providers. ")

  @Valid

  public List<PkgmNotificationsFilterVnfProductsFromProviders> getVnfProductsFromProviders() {
    return vnfProductsFromProviders;
  }

  public void setVnfProductsFromProviders(List<PkgmNotificationsFilterVnfProductsFromProviders> vnfProductsFromProviders) {
    this.vnfProductsFromProviders = vnfProductsFromProviders;
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
        Objects.equals(this.vnfProductsFromProviders, pkgmNotificationsFilter.vnfProductsFromProviders);
  }

  @Override
  public int hashCode() {
    return Objects.hash(notificationTypes, vnfProductsFromProviders);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PkgmNotificationsFilter {\n");
    
    sb.append("    notificationTypes: ").append(toIndentedString(notificationTypes)).append("\n");
    sb.append("    vnfProductsFromProviders: ").append(toIndentedString(vnfProductsFromProviders)).append("\n");
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

