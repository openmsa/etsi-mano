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
package com.ubiqube.etsi.mec.meo.v211.model.grant;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mec.meo.v211.model.grant.AddressRange;
import com.ubiqube.etsi.mec.meo.v211.model.grant.FixedAddresses;
import com.ubiqube.etsi.mec.meo.v211.model.grant.IpAddressType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * &#x27;IP addresses to assign to the CP instance. Each entry represents IP address data for fixed or dynamic IP address assignment per subnet.&#x27;
 */
@ApiModel(description = "'IP addresses to assign to the CP instance. Each entry represents IP address data for fixed or dynamic IP address assignment per subnet.'")
@Validated
public class IpAddress   {
  @JsonProperty("type")
  private IpAddressType type = null;

  @JsonProperty("fixedAddresses")
  private FixedAddresses fixedAddresses = null;

  @JsonProperty("numDynamicAddresses")
  private Integer numDynamicAddresses = null;

  @JsonProperty("addressRange")
  private AddressRange addressRange = null;

  @JsonProperty("subnetId")
  private String subnetId = null;

  public IpAddress type(IpAddressType type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public IpAddressType getType() {
    return type;
  }

  public void setType(IpAddressType type) {
    this.type = type;
  }

  public IpAddress fixedAddresses(FixedAddresses fixedAddresses) {
    this.fixedAddresses = fixedAddresses;
    return this;
  }

  /**
   * Get fixedAddresses
   * @return fixedAddresses
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public FixedAddresses getFixedAddresses() {
    return fixedAddresses;
  }

  public void setFixedAddresses(FixedAddresses fixedAddresses) {
    this.fixedAddresses = fixedAddresses;
  }

  public IpAddress numDynamicAddresses(Integer numDynamicAddresses) {
    this.numDynamicAddresses = numDynamicAddresses;
    return this;
  }

  /**
   * Get numDynamicAddresses
   * @return numDynamicAddresses
  **/
  @ApiModelProperty(value = "")
  
    public Integer getNumDynamicAddresses() {
    return numDynamicAddresses;
  }

  public void setNumDynamicAddresses(Integer numDynamicAddresses) {
    this.numDynamicAddresses = numDynamicAddresses;
  }

  public IpAddress addressRange(AddressRange addressRange) {
    this.addressRange = addressRange;
    return this;
  }

  /**
   * Get addressRange
   * @return addressRange
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public AddressRange getAddressRange() {
    return addressRange;
  }

  public void setAddressRange(AddressRange addressRange) {
    this.addressRange = addressRange;
  }

  public IpAddress subnetId(String subnetId) {
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
    IpAddress ipAddress = (IpAddress) o;
    return Objects.equals(this.type, ipAddress.type) &&
        Objects.equals(this.fixedAddresses, ipAddress.fixedAddresses) &&
        Objects.equals(this.numDynamicAddresses, ipAddress.numDynamicAddresses) &&
        Objects.equals(this.addressRange, ipAddress.addressRange) &&
        Objects.equals(this.subnetId, ipAddress.subnetId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, fixedAddresses, numDynamicAddresses, addressRange, subnetId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IpAddress {\n");
    
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    fixedAddresses: ").append(toIndentedString(fixedAddresses)).append("\n");
    sb.append("    numDynamicAddresses: ").append(toIndentedString(numDynamicAddresses)).append("\n");
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
