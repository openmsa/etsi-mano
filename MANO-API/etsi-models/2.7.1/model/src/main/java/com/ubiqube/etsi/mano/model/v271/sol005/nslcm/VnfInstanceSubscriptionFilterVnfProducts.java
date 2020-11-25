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
package com.ubiqube.etsi.mano.model.v271.sol005.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.model.v271.sol005.nslcm.VnfInstanceSubscriptionFilterVersions;
import com.fasterxml.jackson.annotation.JsonCreator;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * VnfInstanceSubscriptionFilterVnfProducts
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-11-09T10:14:43.989+01:00")

public class VnfInstanceSubscriptionFilterVnfProducts   {
  @JsonProperty("vnfProductName")
  private String vnfProductName = null;

  @JsonProperty("versions")
  @Valid
  private List<VnfInstanceSubscriptionFilterVersions> versions = null;

  public VnfInstanceSubscriptionFilterVnfProducts vnfProductName(String vnfProductName) {
    this.vnfProductName = vnfProductName;
    return this;
  }

  /**
   * Name of the VNF product to match. 
   * @return vnfProductName
  **/
  @ApiModelProperty(required = true, value = "Name of the VNF product to match. ")
  @NotNull


  public String getVnfProductName() {
    return vnfProductName;
  }

  public void setVnfProductName(String vnfProductName) {
    this.vnfProductName = vnfProductName;
  }

  public VnfInstanceSubscriptionFilterVnfProducts versions(List<VnfInstanceSubscriptionFilterVersions> versions) {
    this.versions = versions;
    return this;
  }

  public VnfInstanceSubscriptionFilterVnfProducts addVersionsItem(VnfInstanceSubscriptionFilterVersions versionsItem) {
    if (this.versions == null) {
      this.versions = new ArrayList<>();
    }
    this.versions.add(versionsItem);
    return this;
  }

  /**
   * If present, match VNF instances that belong to VNF products with certain versions and a certain product name, from one particular provider. 
   * @return versions
  **/
  @ApiModelProperty(value = "If present, match VNF instances that belong to VNF products with certain versions and a certain product name, from one particular provider. ")

  @Valid

  public List<VnfInstanceSubscriptionFilterVersions> getVersions() {
    return versions;
  }

  public void setVersions(List<VnfInstanceSubscriptionFilterVersions> versions) {
    this.versions = versions;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VnfInstanceSubscriptionFilterVnfProducts vnfInstanceSubscriptionFilterVnfProducts = (VnfInstanceSubscriptionFilterVnfProducts) o;
    return Objects.equals(this.vnfProductName, vnfInstanceSubscriptionFilterVnfProducts.vnfProductName) &&
        Objects.equals(this.versions, vnfInstanceSubscriptionFilterVnfProducts.versions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vnfProductName, versions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfInstanceSubscriptionFilterVnfProducts {\n");
    
    sb.append("    vnfProductName: ").append(toIndentedString(vnfProductName)).append("\n");
    sb.append("    versions: ").append(toIndentedString(versions)).append("\n");
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

