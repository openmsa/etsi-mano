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
import com.ubiqube.etsi.mano.nfvo.v281.model.vnf.PackageOperationalStateType;
import com.ubiqube.etsi.mano.nfvo.v281.model.vnf.PackageUsageStateType;
import com.ubiqube.etsi.mano.nfvo.v281.model.vnf.PkgmNotificationsFilterVnfProducts;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * PkgmNotificationsFilterVnfProductsFromProviders
 */
@Validated

public class PkgmNotificationsFilterVnfProductsFromProviders   {
  @JsonProperty("vnfProvider")
  private String vnfProvider = null;

  @JsonProperty("vnfProducts")
  @Valid
  private List<PkgmNotificationsFilterVnfProducts> vnfProducts = null;

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

  public PkgmNotificationsFilterVnfProductsFromProviders vnfProvider(String vnfProvider) {
    this.vnfProvider = vnfProvider;
    return this;
  }

  /**
   * Name of the VNFprovider to match. 
   * @return vnfProvider
  **/
  @ApiModelProperty(required = true, value = "Name of the VNFprovider to match. ")
  @NotNull


  public String getVnfProvider() {
    return vnfProvider;
  }

  public void setVnfProvider(String vnfProvider) {
    this.vnfProvider = vnfProvider;
  }

  public PkgmNotificationsFilterVnfProductsFromProviders vnfProducts(List<PkgmNotificationsFilterVnfProducts> vnfProducts) {
    this.vnfProducts = vnfProducts;
    return this;
  }

  public PkgmNotificationsFilterVnfProductsFromProviders addVnfProductsItem(PkgmNotificationsFilterVnfProducts vnfProductsItem) {
    if (this.vnfProducts == null) {
      this.vnfProducts = new ArrayList<>();
    }
    this.vnfProducts.add(vnfProductsItem);
    return this;
  }

  /**
   * If present, match VNF packages that contain VNF products with certain product names, from one particular provider. 
   * @return vnfProducts
  **/
  @ApiModelProperty(value = "If present, match VNF packages that contain VNF products with certain product names, from one particular provider. ")

  @Valid

  public List<PkgmNotificationsFilterVnfProducts> getVnfProducts() {
    return vnfProducts;
  }

  public void setVnfProducts(List<PkgmNotificationsFilterVnfProducts> vnfProducts) {
    this.vnfProducts = vnfProducts;
  }

  public PkgmNotificationsFilterVnfProductsFromProviders vnfdId(List<String> vnfdId) {
    this.vnfdId = vnfdId;
    return this;
  }

  public PkgmNotificationsFilterVnfProductsFromProviders addVnfdIdItem(String vnfdIdItem) {
    if (this.vnfdId == null) {
      this.vnfdId = new ArrayList<>();
    }
    this.vnfdId.add(vnfdIdItem);
    return this;
  }

  /**
   * Match VNF packages with a VNFD identifier listed in the attribute. 
   * @return vnfdId
  **/
  @ApiModelProperty(value = "Match VNF packages with a VNFD identifier listed in the attribute. ")


  public List<String> getVnfdId() {
    return vnfdId;
  }

  public void setVnfdId(List<String> vnfdId) {
    this.vnfdId = vnfdId;
  }

  public PkgmNotificationsFilterVnfProductsFromProviders vnfPkgId(List<String> vnfPkgId) {
    this.vnfPkgId = vnfPkgId;
    return this;
  }

  public PkgmNotificationsFilterVnfProductsFromProviders addVnfPkgIdItem(String vnfPkgIdItem) {
    if (this.vnfPkgId == null) {
      this.vnfPkgId = new ArrayList<>();
    }
    this.vnfPkgId.add(vnfPkgIdItem);
    return this;
  }

  /**
   * Match VNF packages with a package identifier listed in the attribute. May be present if the \"notificationTypes\" attribute contains the value \"VnfPackageChangeNotification\", and shall be absent otherwise. 
   * @return vnfPkgId
  **/
  @ApiModelProperty(value = "Match VNF packages with a package identifier listed in the attribute. May be present if the \"notificationTypes\" attribute contains the value \"VnfPackageChangeNotification\", and shall be absent otherwise. ")


  public List<String> getVnfPkgId() {
    return vnfPkgId;
  }

  public void setVnfPkgId(List<String> vnfPkgId) {
    this.vnfPkgId = vnfPkgId;
  }

  public PkgmNotificationsFilterVnfProductsFromProviders operationalState(List<PackageOperationalStateType> operationalState) {
    this.operationalState = operationalState;
    return this;
  }

  public PkgmNotificationsFilterVnfProductsFromProviders addOperationalStateItem(PackageOperationalStateType operationalStateItem) {
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
  @ApiModelProperty(value = "Match VNF packages with a package identifier listed in the attribute. May be present if the \"notificationTypes\" attribute contains the value \"VnfPackageChangeNotification\", and shall be absent otherwise. ")

  @Valid

  public List<PackageOperationalStateType> getOperationalState() {
    return operationalState;
  }

  public void setOperationalState(List<PackageOperationalStateType> operationalState) {
    this.operationalState = operationalState;
  }

  public PkgmNotificationsFilterVnfProductsFromProviders usageState(List<PackageUsageStateType> usageState) {
    this.usageState = usageState;
    return this;
  }

  public PkgmNotificationsFilterVnfProductsFromProviders addUsageStateItem(PackageUsageStateType usageStateItem) {
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
  @ApiModelProperty(value = "Match particular usage state of the on-boarded VNF package. May be present if the \"notificationTypes\" attribute contains the value \"VnfPackageChangeNotification\", and shall be absent otherwise. ")

  @Valid

  public List<PackageUsageStateType> getUsageState() {
    return usageState;
  }

  public void setUsageState(List<PackageUsageStateType> usageState) {
    this.usageState = usageState;
  }

  public PkgmNotificationsFilterVnfProductsFromProviders vnfmInfo(List<String> vnfmInfo) {
    this.vnfmInfo = vnfmInfo;
    return this;
  }

  public PkgmNotificationsFilterVnfProductsFromProviders addVnfmInfoItem(String vnfmInfoItem) {
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
  @ApiModelProperty(value = "Match strings that specify VNFMs compatible with the VNF. See Table 9.5.2.5-1. ")


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
    PkgmNotificationsFilterVnfProductsFromProviders pkgmNotificationsFilterVnfProductsFromProviders = (PkgmNotificationsFilterVnfProductsFromProviders) o;
    return Objects.equals(this.vnfProvider, pkgmNotificationsFilterVnfProductsFromProviders.vnfProvider) &&
        Objects.equals(this.vnfProducts, pkgmNotificationsFilterVnfProductsFromProviders.vnfProducts) &&
        Objects.equals(this.vnfdId, pkgmNotificationsFilterVnfProductsFromProviders.vnfdId) &&
        Objects.equals(this.vnfPkgId, pkgmNotificationsFilterVnfProductsFromProviders.vnfPkgId) &&
        Objects.equals(this.operationalState, pkgmNotificationsFilterVnfProductsFromProviders.operationalState) &&
        Objects.equals(this.usageState, pkgmNotificationsFilterVnfProductsFromProviders.usageState) &&
        Objects.equals(this.vnfmInfo, pkgmNotificationsFilterVnfProductsFromProviders.vnfmInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vnfProvider, vnfProducts, vnfdId, vnfPkgId, operationalState, usageState, vnfmInfo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PkgmNotificationsFilterVnfProductsFromProviders {\n");
    
    sb.append("    vnfProvider: ").append(toIndentedString(vnfProvider)).append("\n");
    sb.append("    vnfProducts: ").append(toIndentedString(vnfProducts)).append("\n");
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

