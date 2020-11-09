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

package com.ubiqube.etsi.mano.nfvo.v271.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.nfvo.v271.model.nslcm.IpOverEthernetAddressInfoAddressRange1;
import com.ubiqube.etsi.mano.nfvo.v271.model.nslcm.IpOverEthernetAddressInfoIpAddresses;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents information about a network address that has been assigned. It shall comply with the provisions defined in Table 6.5.3.18-1. 
 */
@ApiModel(description = "This type represents information about a network address that has been assigned. It shall comply with the provisions defined in Table 6.5.3.18-1. ")
@Validated
public class IpOverEthernetAddressInfo   {
  @JsonProperty("macAddress")
  private String macAddress = null;

  @JsonProperty("ipAddresses")
  @Valid
  private List<IpOverEthernetAddressInfoIpAddresses> ipAddresses = new ArrayList<>();

  /**
   * The type of the IP addresses 
   */
  public enum TypeEnum {
    PV4("PV4"),
    
    PV6("PV6");

    private String value;

    TypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TypeEnum fromValue(String text) {
      for (TypeEnum b : TypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("type")
  private TypeEnum type = null;

  @JsonProperty("addresses")
  private String addresses = null;

  @JsonProperty("isDynamic")
  private Boolean isDynamic = null;

  @JsonProperty("addressRange")
  private IpOverEthernetAddressInfoAddressRange1 addressRange = null;

  @JsonProperty("minAddress")
  private String minAddress = null;

  @JsonProperty("maxAddress")
  private String maxAddress = null;

  @JsonProperty("subnetId")
  private String subnetId = null;

  public IpOverEthernetAddressInfo macAddress(String macAddress) {
    this.macAddress = macAddress;
    return this;
  }

  /**
   * Get macAddress
   * @return macAddress
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getMacAddress() {
    return macAddress;
  }

  public void setMacAddress(String macAddress) {
    this.macAddress = macAddress;
  }

  public IpOverEthernetAddressInfo ipAddresses(List<IpOverEthernetAddressInfoIpAddresses> ipAddresses) {
    this.ipAddresses = ipAddresses;
    return this;
  }

  public IpOverEthernetAddressInfo addIpAddressesItem(IpOverEthernetAddressInfoIpAddresses ipAddressesItem) {
    this.ipAddresses.add(ipAddressesItem);
    return this;
  }

  /**
   * Addresses assigned to the CP instance. Each entry represents IP addresses assigned by fixed or dynamic IP address assignment per subnet. 
   * @return ipAddresses
  **/
  @ApiModelProperty(required = true, value = "Addresses assigned to the CP instance. Each entry represents IP addresses assigned by fixed or dynamic IP address assignment per subnet. ")
      @NotNull
    @Valid
    public List<IpOverEthernetAddressInfoIpAddresses> getIpAddresses() {
    return ipAddresses;
  }

  public void setIpAddresses(List<IpOverEthernetAddressInfoIpAddresses> ipAddresses) {
    this.ipAddresses = ipAddresses;
  }

  public IpOverEthernetAddressInfo type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * The type of the IP addresses 
   * @return type
  **/
  @ApiModelProperty(value = "The type of the IP addresses ")
  
    public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public IpOverEthernetAddressInfo addresses(String addresses) {
    this.addresses = addresses;
    return this;
  }

  /**
   * Get addresses
   * @return addresses
  **/
  @ApiModelProperty(value = "")
  
    public String getAddresses() {
    return addresses;
  }

  public void setAddresses(String addresses) {
    this.addresses = addresses;
  }

  public IpOverEthernetAddressInfo isDynamic(Boolean isDynamic) {
    this.isDynamic = isDynamic;
    return this;
  }

  /**
   * Indicates whether this set of addresses was assigned dynamically (true) or based on address information provided as input from the API consumer (false). Shall be present if \"addresses\" is present and shall be absent otherwise. 
   * @return isDynamic
  **/
  @ApiModelProperty(value = "Indicates whether this set of addresses was assigned dynamically (true) or based on address information provided as input from the API consumer (false). Shall be present if \"addresses\" is present and shall be absent otherwise. ")
  
    public Boolean isIsDynamic() {
    return isDynamic;
  }

  public void setIsDynamic(Boolean isDynamic) {
    this.isDynamic = isDynamic;
  }

  public IpOverEthernetAddressInfo addressRange(IpOverEthernetAddressInfoAddressRange1 addressRange) {
    this.addressRange = addressRange;
    return this;
  }

  /**
   * Get addressRange
   * @return addressRange
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public IpOverEthernetAddressInfoAddressRange1 getAddressRange() {
    return addressRange;
  }

  public void setAddressRange(IpOverEthernetAddressInfoAddressRange1 addressRange) {
    this.addressRange = addressRange;
  }

  public IpOverEthernetAddressInfo minAddress(String minAddress) {
    this.minAddress = minAddress;
    return this;
  }

  /**
   * Get minAddress
   * @return minAddress
  **/
  @ApiModelProperty(value = "")
  
    public String getMinAddress() {
    return minAddress;
  }

  public void setMinAddress(String minAddress) {
    this.minAddress = minAddress;
  }

  public IpOverEthernetAddressInfo maxAddress(String maxAddress) {
    this.maxAddress = maxAddress;
    return this;
  }

  /**
   * Get maxAddress
   * @return maxAddress
  **/
  @ApiModelProperty(value = "")
  
    public String getMaxAddress() {
    return maxAddress;
  }

  public void setMaxAddress(String maxAddress) {
    this.maxAddress = maxAddress;
  }

  public IpOverEthernetAddressInfo subnetId(String subnetId) {
    this.subnetId = subnetId;
    return this;
  }

  /**
   * Get subnetId
   * @return subnetId
  **/
  @ApiModelProperty(value = "")
  
    public String getSubnetId() {
    return subnetId;
  }

  public void setSubnetId(String subnetId) {
    this.subnetId = subnetId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IpOverEthernetAddressInfo ipOverEthernetAddressInfo = (IpOverEthernetAddressInfo) o;
    return Objects.equals(this.macAddress, ipOverEthernetAddressInfo.macAddress) &&
        Objects.equals(this.ipAddresses, ipOverEthernetAddressInfo.ipAddresses) &&
        Objects.equals(this.type, ipOverEthernetAddressInfo.type) &&
        Objects.equals(this.addresses, ipOverEthernetAddressInfo.addresses) &&
        Objects.equals(this.isDynamic, ipOverEthernetAddressInfo.isDynamic) &&
        Objects.equals(this.addressRange, ipOverEthernetAddressInfo.addressRange) &&
        Objects.equals(this.minAddress, ipOverEthernetAddressInfo.minAddress) &&
        Objects.equals(this.maxAddress, ipOverEthernetAddressInfo.maxAddress) &&
        Objects.equals(this.subnetId, ipOverEthernetAddressInfo.subnetId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(macAddress, ipAddresses, type, addresses, isDynamic, addressRange, minAddress, maxAddress, subnetId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IpOverEthernetAddressInfo {\n");
    
    sb.append("    macAddress: ").append(toIndentedString(macAddress)).append("\n");
    sb.append("    ipAddresses: ").append(toIndentedString(ipAddresses)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    addresses: ").append(toIndentedString(addresses)).append("\n");
    sb.append("    isDynamic: ").append(toIndentedString(isDynamic)).append("\n");
    sb.append("    addressRange: ").append(toIndentedString(addressRange)).append("\n");
    sb.append("    minAddress: ").append(toIndentedString(minAddress)).append("\n");
    sb.append("    maxAddress: ").append(toIndentedString(maxAddress)).append("\n");
    sb.append("    subnetId: ").append(toIndentedString(subnetId)).append("\n");
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
