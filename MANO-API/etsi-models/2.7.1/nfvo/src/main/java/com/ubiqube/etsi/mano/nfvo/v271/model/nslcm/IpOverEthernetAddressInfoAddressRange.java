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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * An IP address range used, e.g., in case of egress connections. Exactly one of \&quot;addresses\&quot; or \&quot;addressRange\&quot; shall be present. 
 */
@ApiModel(description = "An IP address range used, e.g., in case of egress connections. Exactly one of \"addresses\" or \"addressRange\" shall be present. ")
@Validated
public class IpOverEthernetAddressInfoAddressRange   {
  @JsonProperty("minAddress")
  private String minAddress = null;

  @JsonProperty("maxAddress")
  private String maxAddress = null;

  public IpOverEthernetAddressInfoAddressRange minAddress(String minAddress) {
    this.minAddress = minAddress;
    return this;
  }

  /**
   * Get minAddress
   * @return minAddress
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getMinAddress() {
    return minAddress;
  }

  public void setMinAddress(String minAddress) {
    this.minAddress = minAddress;
  }

  public IpOverEthernetAddressInfoAddressRange maxAddress(String maxAddress) {
    this.maxAddress = maxAddress;
    return this;
  }

  /**
   * Get maxAddress
   * @return maxAddress
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getMaxAddress() {
    return maxAddress;
  }

  public void setMaxAddress(String maxAddress) {
    this.maxAddress = maxAddress;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IpOverEthernetAddressInfoAddressRange ipOverEthernetAddressInfoAddressRange = (IpOverEthernetAddressInfoAddressRange) o;
    return Objects.equals(this.minAddress, ipOverEthernetAddressInfoAddressRange.minAddress) &&
        Objects.equals(this.maxAddress, ipOverEthernetAddressInfoAddressRange.maxAddress);
  }

  @Override
  public int hashCode() {
    return Objects.hash(minAddress, maxAddress);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IpOverEthernetAddressInfoAddressRange {\n");
    
    sb.append("    minAddress: ").append(toIndentedString(minAddress)).append("\n");
    sb.append("    maxAddress: ").append(toIndentedString(maxAddress)).append("\n");
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
