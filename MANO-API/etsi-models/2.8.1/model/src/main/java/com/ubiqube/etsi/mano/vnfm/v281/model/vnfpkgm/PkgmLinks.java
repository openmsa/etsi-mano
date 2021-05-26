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
package com.ubiqube.etsi.mano.vnfm.v281.model.vnfpkgm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.vnfm.v281.model.vnfpkgm.NotificationLink;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents the links to resources that a VNF package management notification can contain. 
 */
@ApiModel(description = "This type represents the links to resources that a VNF package management notification can contain. ")
@Validated

public class PkgmLinks   {
  @JsonProperty("vnfPackage")
  private NotificationLink vnfPackage = null;

  @JsonProperty("vnfPackageByVnfdId")
  private NotificationLink vnfPackageByVnfdId = null;

  @JsonProperty("subscription")
  private NotificationLink subscription = null;

  public PkgmLinks vnfPackage(NotificationLink vnfPackage) {
    this.vnfPackage = vnfPackage;
    return this;
  }

  /**
   * Link to the resource representing the VNF package to which the notified change  applies, i.e. the \"Individual VNF package\" resource that represents the VNF package,  identified by the \"vnfPkgId\" identifier which is managed by the NFVO.  This attribute shall be provided by the NFVO but is deprecated and can be removed  in future versions of the present document. 
   * @return vnfPackage
  **/
  @ApiModelProperty(required = true, value = "Link to the resource representing the VNF package to which the notified change  applies, i.e. the \"Individual VNF package\" resource that represents the VNF package,  identified by the \"vnfPkgId\" identifier which is managed by the NFVO.  This attribute shall be provided by the NFVO but is deprecated and can be removed  in future versions of the present document. ")
  @NotNull

  @Valid

  public NotificationLink getVnfPackage() {
    return vnfPackage;
  }

  public void setVnfPackage(NotificationLink vnfPackage) {
    this.vnfPackage = vnfPackage;
  }

  public PkgmLinks vnfPackageByVnfdId(NotificationLink vnfPackageByVnfdId) {
    this.vnfPackageByVnfdId = vnfPackageByVnfdId;
    return this;
  }

  /**
   * Link to the resource representing the VNF package to which the notified change applies,  i.e. the \"Individual VNF package\" resource that represents the VNF package, identified  by the \"vnfdId\" identifier which is assigned by the VNF vendor. 
   * @return vnfPackageByVnfdId
  **/
  @ApiModelProperty(value = "Link to the resource representing the VNF package to which the notified change applies,  i.e. the \"Individual VNF package\" resource that represents the VNF package, identified  by the \"vnfdId\" identifier which is assigned by the VNF vendor. ")

  @Valid

  public NotificationLink getVnfPackageByVnfdId() {
    return vnfPackageByVnfdId;
  }

  public void setVnfPackageByVnfdId(NotificationLink vnfPackageByVnfdId) {
    this.vnfPackageByVnfdId = vnfPackageByVnfdId;
  }

  public PkgmLinks subscription(NotificationLink subscription) {
    this.subscription = subscription;
    return this;
  }

  /**
   * Link to the related subscription. 
   * @return subscription
  **/
  @ApiModelProperty(required = true, value = "Link to the related subscription. ")
  @NotNull

  @Valid

  public NotificationLink getSubscription() {
    return subscription;
  }

  public void setSubscription(NotificationLink subscription) {
    this.subscription = subscription;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PkgmLinks pkgmLinks = (PkgmLinks) o;
    return Objects.equals(this.vnfPackage, pkgmLinks.vnfPackage) &&
        Objects.equals(this.vnfPackageByVnfdId, pkgmLinks.vnfPackageByVnfdId) &&
        Objects.equals(this.subscription, pkgmLinks.subscription);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vnfPackage, vnfPackageByVnfdId, subscription);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PkgmLinks {\n");
    
    sb.append("    vnfPackage: ").append(toIndentedString(vnfPackage)).append("\n");
    sb.append("    vnfPackageByVnfdId: ").append(toIndentedString(vnfPackageByVnfdId)).append("\n");
    sb.append("    subscription: ").append(toIndentedString(subscription)).append("\n");
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

