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
package com.ubiqube.etsi.mano.model.v271.sol003.faultmngt;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.model.v271.sol003.faultmngt.VnfInstanceSubscriptionFilterVnfProducts;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * VnfInstanceSubscriptionFilterVnfProductsFromProviders
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-11-09T10:22:49.340+01:00")

public class VnfInstanceSubscriptionFilterVnfProductsFromProviders   {
  @JsonProperty("vnfProvider")
  private String vnfProvider = null;

  @JsonProperty("vnfProducts")
  @Valid
  private List<VnfInstanceSubscriptionFilterVnfProducts> vnfProducts = null;

  public VnfInstanceSubscriptionFilterVnfProductsFromProviders vnfProvider(String vnfProvider) {
    this.vnfProvider = vnfProvider;
    return this;
  }

  /**
   * Name of the VNF provider to match. 
   * @return vnfProvider
  **/
  @ApiModelProperty(required = true, value = "Name of the VNF provider to match. ")
  @NotNull


  public String getVnfProvider() {
    return vnfProvider;
  }

  public void setVnfProvider(String vnfProvider) {
    this.vnfProvider = vnfProvider;
  }

  public VnfInstanceSubscriptionFilterVnfProductsFromProviders vnfProducts(List<VnfInstanceSubscriptionFilterVnfProducts> vnfProducts) {
    this.vnfProducts = vnfProducts;
    return this;
  }

  public VnfInstanceSubscriptionFilterVnfProductsFromProviders addVnfProductsItem(VnfInstanceSubscriptionFilterVnfProducts vnfProductsItem) {
    if (this.vnfProducts == null) {
      this.vnfProducts = new ArrayList<>();
    }
    this.vnfProducts.add(vnfProductsItem);
    return this;
  }

  /**
   * If present, match VNF instances that belong to VNF products with certain product names, from one particular provider. 
   * @return vnfProducts
  **/
  @ApiModelProperty(value = "If present, match VNF instances that belong to VNF products with certain product names, from one particular provider. ")

  @Valid

  public List<VnfInstanceSubscriptionFilterVnfProducts> getVnfProducts() {
    return vnfProducts;
  }

  public void setVnfProducts(List<VnfInstanceSubscriptionFilterVnfProducts> vnfProducts) {
    this.vnfProducts = vnfProducts;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VnfInstanceSubscriptionFilterVnfProductsFromProviders vnfInstanceSubscriptionFilterVnfProductsFromProviders = (VnfInstanceSubscriptionFilterVnfProductsFromProviders) o;
    return Objects.equals(this.vnfProvider, vnfInstanceSubscriptionFilterVnfProductsFromProviders.vnfProvider) &&
        Objects.equals(this.vnfProducts, vnfInstanceSubscriptionFilterVnfProductsFromProviders.vnfProducts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vnfProvider, vnfProducts);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfInstanceSubscriptionFilterVnfProductsFromProviders {\n");
    
    sb.append("    vnfProvider: ").append(toIndentedString(vnfProvider)).append("\n");
    sb.append("    vnfProducts: ").append(toIndentedString(vnfProducts)).append("\n");
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

