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
package com.ubiqube.etsi.mano.model.v271.sol003.lcmgrant;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.model.v271.sol003.lcmgrant.IpOverEthernetAddressDataIpAddresses;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents network address data for IP over Ethernet. 
 */
@ApiModel(description = "This type represents network address data for IP over Ethernet. ")
@Validated


public class IpOverEthernetAddressData   {
  @JsonProperty("macAddress")
  private String macAddress = null;

  @JsonProperty("ipAddresses")
  @Valid
  private List<IpOverEthernetAddressDataIpAddresses> ipAddresses = null;

  public IpOverEthernetAddressData macAddress(String macAddress) {
    this.macAddress = macAddress;
    return this;
  }

  /**
   * MAC address. If this attribute is not present, it shall be chosen by the VIM. At least one of \"macAddress\" or \"ipAddresses\" shall be present. 
   * @return macAddress
  **/
  @ApiModelProperty(value = "MAC address. If this attribute is not present, it shall be chosen by the VIM. At least one of \"macAddress\" or \"ipAddresses\" shall be present. ")


  public String getMacAddress() {
    return macAddress;
  }

  public void setMacAddress(String macAddress) {
    this.macAddress = macAddress;
  }

  public IpOverEthernetAddressData ipAddresses(List<IpOverEthernetAddressDataIpAddresses> ipAddresses) {
    this.ipAddresses = ipAddresses;
    return this;
  }

  public IpOverEthernetAddressData addIpAddressesItem(IpOverEthernetAddressDataIpAddresses ipAddressesItem) {
    if (this.ipAddresses == null) {
      this.ipAddresses = new ArrayList<>();
    }
    this.ipAddresses.add(ipAddressesItem);
    return this;
  }

  /**
   * List of IP addresses to assign to the CP instance. Each entry represents IP address data for fixed or dynamic IP address assignment per subnet. If this attribute is not present, no IP address shall be assigned. 
   * @return ipAddresses
  **/
  @ApiModelProperty(value = "List of IP addresses to assign to the CP instance. Each entry represents IP address data for fixed or dynamic IP address assignment per subnet. If this attribute is not present, no IP address shall be assigned. ")

  @Valid

  public List<IpOverEthernetAddressDataIpAddresses> getIpAddresses() {
    return ipAddresses;
  }

  public void setIpAddresses(List<IpOverEthernetAddressDataIpAddresses> ipAddresses) {
    this.ipAddresses = ipAddresses;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IpOverEthernetAddressData ipOverEthernetAddressData = (IpOverEthernetAddressData) o;
    return Objects.equals(this.macAddress, ipOverEthernetAddressData.macAddress) &&
        Objects.equals(this.ipAddresses, ipOverEthernetAddressData.ipAddresses);
  }

  @Override
  public int hashCode() {
    return Objects.hash(macAddress, ipAddresses);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IpOverEthernetAddressData {\n");
    
    sb.append("    macAddress: ").append(toIndentedString(macAddress)).append("\n");
    sb.append("    ipAddresses: ").append(toIndentedString(ipAddresses)).append("\n");
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

