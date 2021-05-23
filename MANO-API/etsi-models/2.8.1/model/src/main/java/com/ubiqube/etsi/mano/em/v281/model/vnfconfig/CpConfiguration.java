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
package com.ubiqube.etsi.mano.em.v281.model.vnfconfig;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.em.v281.model.vnfconfig.CpAddress;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents configuration parameters of a CP instance. 
 */
@ApiModel(description = "This type represents configuration parameters of a CP instance. ")
@Validated

public class CpConfiguration   {
  @JsonProperty("cpId")
  private String cpId = null;

  @JsonProperty("cpdId")
  private String cpdId = null;

  @JsonProperty("addresses")
  @Valid
  private List<CpAddress> addresses = new ArrayList<>();

  public CpConfiguration cpId(String cpId) {
    this.cpId = cpId;
    return this;
  }

  /**
   * Identifier of a CP instance within the namespace of a specific VNF instance or a VNFC instance. 
   * @return cpId
  **/
  @ApiModelProperty(required = true, value = "Identifier of a CP instance within the namespace of a specific VNF instance or a VNFC instance. ")
  @NotNull


  public String getCpId() {
    return cpId;
  }

  public void setCpId(String cpId) {
    this.cpId = cpId;
  }

  public CpConfiguration cpdId(String cpdId) {
    this.cpdId = cpdId;
    return this;
  }

  /**
   * Identifier of the CPD in the VNFD. 
   * @return cpdId
  **/
  @ApiModelProperty(required = true, value = "Identifier of the CPD in the VNFD. ")
  @NotNull


  public String getCpdId() {
    return cpdId;
  }

  public void setCpdId(String cpdId) {
    this.cpdId = cpdId;
  }

  public CpConfiguration addresses(List<CpAddress> addresses) {
    this.addresses = addresses;
    return this;
  }

  public CpConfiguration addAddressesItem(CpAddress addressesItem) {
    this.addresses.add(addressesItem);
    return this;
  }

  /**
   * Network address and port assigned to the CP. 
   * @return addresses
  **/
  @ApiModelProperty(required = true, value = "Network address and port assigned to the CP. ")
  @NotNull

  @Valid

  public List<CpAddress> getAddresses() {
    return addresses;
  }

  public void setAddresses(List<CpAddress> addresses) {
    this.addresses = addresses;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CpConfiguration cpConfiguration = (CpConfiguration) o;
    return Objects.equals(this.cpId, cpConfiguration.cpId) &&
        Objects.equals(this.cpdId, cpConfiguration.cpdId) &&
        Objects.equals(this.addresses, cpConfiguration.addresses);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cpId, cpdId, addresses);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CpConfiguration {\n");
    
    sb.append("    cpId: ").append(toIndentedString(cpId)).append("\n");
    sb.append("    cpdId: ").append(toIndentedString(cpdId)).append("\n");
    sb.append("    addresses: ").append(toIndentedString(addresses)).append("\n");
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

