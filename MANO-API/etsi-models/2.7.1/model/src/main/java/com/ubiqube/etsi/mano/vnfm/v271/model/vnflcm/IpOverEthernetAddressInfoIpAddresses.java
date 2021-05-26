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
package com.ubiqube.etsi.mano.vnfm.v271.model.vnflcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.vnfm.v271.model.vnflcm.IpOverEthernetAddressInfoAddressRange;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * IpOverEthernetAddressInfoIpAddresses
 */
@Validated

public class IpOverEthernetAddressInfoIpAddresses   {
  /**
   * The type of the IP addresses. Permitted values: IPV4, IPV6. 
   */
  public enum TypeEnum {
    IPV4("IPV4"),
    
    IPV6("IPV6");

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
  @Valid
  private List<String> addresses = null;

  @JsonProperty("isDynamic")
  private Boolean isDynamic = null;

  @JsonProperty("addressRange")
  private IpOverEthernetAddressInfoAddressRange addressRange = null;

  @JsonProperty("subnetId")
  private String subnetId = null;

  public IpOverEthernetAddressInfoIpAddresses type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * The type of the IP addresses. Permitted values: IPV4, IPV6. 
   * @return type
  **/
  @ApiModelProperty(required = true, value = "The type of the IP addresses. Permitted values: IPV4, IPV6. ")
  @NotNull


  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public IpOverEthernetAddressInfoIpAddresses addresses(List<String> addresses) {
    this.addresses = addresses;
    return this;
  }

  public IpOverEthernetAddressInfoIpAddresses addAddressesItem(String addressesItem) {
    if (this.addresses == null) {
      this.addresses = new ArrayList<>();
    }
    this.addresses.add(addressesItem);
    return this;
  }

  /**
   * Fixed addresses assigned (from the subnet defined by \"subnetId\" if provided). Exactly one of \"addresses\" or \"addressRange\" shall be present. 
   * @return addresses
  **/
  @ApiModelProperty(value = "Fixed addresses assigned (from the subnet defined by \"subnetId\" if provided). Exactly one of \"addresses\" or \"addressRange\" shall be present. ")


  public List<String> getAddresses() {
    return addresses;
  }

  public void setAddresses(List<String> addresses) {
    this.addresses = addresses;
  }

  public IpOverEthernetAddressInfoIpAddresses isDynamic(Boolean isDynamic) {
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

  public IpOverEthernetAddressInfoIpAddresses addressRange(IpOverEthernetAddressInfoAddressRange addressRange) {
    this.addressRange = addressRange;
    return this;
  }

  /**
   * Get addressRange
   * @return addressRange
  **/
  @ApiModelProperty(value = "")

  @Valid

  public IpOverEthernetAddressInfoAddressRange getAddressRange() {
    return addressRange;
  }

  public void setAddressRange(IpOverEthernetAddressInfoAddressRange addressRange) {
    this.addressRange = addressRange;
  }

  public IpOverEthernetAddressInfoIpAddresses subnetId(String subnetId) {
    this.subnetId = subnetId;
    return this;
  }

  /**
   * Subnet defined by the identifier of the subnet resource in the VIM. In case this attribute is present, IP addresses are bound to that subnet. 
   * @return subnetId
  **/
  @ApiModelProperty(value = "Subnet defined by the identifier of the subnet resource in the VIM. In case this attribute is present, IP addresses are bound to that subnet. ")


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
    IpOverEthernetAddressInfoIpAddresses ipOverEthernetAddressInfoIpAddresses = (IpOverEthernetAddressInfoIpAddresses) o;
    return Objects.equals(this.type, ipOverEthernetAddressInfoIpAddresses.type) &&
        Objects.equals(this.addresses, ipOverEthernetAddressInfoIpAddresses.addresses) &&
        Objects.equals(this.isDynamic, ipOverEthernetAddressInfoIpAddresses.isDynamic) &&
        Objects.equals(this.addressRange, ipOverEthernetAddressInfoIpAddresses.addressRange) &&
        Objects.equals(this.subnetId, ipOverEthernetAddressInfoIpAddresses.subnetId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, addresses, isDynamic, addressRange, subnetId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IpOverEthernetAddressInfoIpAddresses {\n");
    
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    addresses: ").append(toIndentedString(addresses)).append("\n");
    sb.append("    isDynamic: ").append(toIndentedString(isDynamic)).append("\n");
    sb.append("    addressRange: ").append(toIndentedString(addressRange)).append("\n");
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

