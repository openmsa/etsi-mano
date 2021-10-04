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
package com.ubiqube.etsi.mano.nfvo.v331.model.vnf;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v331.model.vnf.PkgmNotificationsFilterVnfProductsFromProviders;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * PkgmNotificationsFilterVnfProductsFromProviders1
 */
@Validated


public class PkgmNotificationsFilterVnfProductsFromProviders1   {
  @JsonProperty("vnfProvider")
  private String vnfProvider = null;

  @JsonProperty("vnfProductsFromProviders")
  @Valid
  private List<PkgmNotificationsFilterVnfProductsFromProviders> vnfProductsFromProviders = null;

  public PkgmNotificationsFilterVnfProductsFromProviders1 vnfProvider(String vnfProvider) {
    this.vnfProvider = vnfProvider;
    return this;
  }

  /**
   * Name of the VNFprovider to match. 
   * @return vnfProvider
   **/
  @Schema(required = true, description = "Name of the VNFprovider to match. ")
      @NotNull

    public String getVnfProvider() {
    return vnfProvider;
  }

  public void setVnfProvider(String vnfProvider) {
    this.vnfProvider = vnfProvider;
  }

  public PkgmNotificationsFilterVnfProductsFromProviders1 vnfProductsFromProviders(List<PkgmNotificationsFilterVnfProductsFromProviders> vnfProductsFromProviders) {
    this.vnfProductsFromProviders = vnfProductsFromProviders;
    return this;
  }

  public PkgmNotificationsFilterVnfProductsFromProviders1 addVnfProductsFromProvidersItem(PkgmNotificationsFilterVnfProductsFromProviders vnfProductsFromProvidersItem) {
    if (this.vnfProductsFromProviders == null) {
      this.vnfProductsFromProviders = new ArrayList<>();
    }
    this.vnfProductsFromProviders.add(vnfProductsFromProvidersItem);
    return this;
  }

  /**
   * If present, match VNF packages that contain VNF products with certain product names, from one particular provider. 
   * @return vnfProductsFromProviders
   **/
  @Schema(description = "If present, match VNF packages that contain VNF products with certain product names, from one particular provider. ")
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
    PkgmNotificationsFilterVnfProductsFromProviders1 pkgmNotificationsFilterVnfProductsFromProviders1 = (PkgmNotificationsFilterVnfProductsFromProviders1) o;
    return Objects.equals(this.vnfProvider, pkgmNotificationsFilterVnfProductsFromProviders1.vnfProvider) &&
        Objects.equals(this.vnfProductsFromProviders, pkgmNotificationsFilterVnfProductsFromProviders1.vnfProductsFromProviders);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vnfProvider, vnfProductsFromProviders);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PkgmNotificationsFilterVnfProductsFromProviders1 {\n");
    
    sb.append("    vnfProvider: ").append(toIndentedString(vnfProvider)).append("\n");
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
