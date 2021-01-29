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
package com.ubiqube.etsi.mec.meo.v211.model.pkg;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mec.meo.v211.model.pkg.TunnelType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * TunnelInfo
 */
@Validated
public class TunnelInfo   {
  @JsonProperty("tunnelDstAddress")
  private String tunnelDstAddress = null;

  @JsonProperty("tunnelSpecificData")
  private String tunnelSpecificData = null;

  @JsonProperty("tunnelSrcAddress")
  private String tunnelSrcAddress = null;

  @JsonProperty("tunnelType")
  private TunnelType tunnelType = null;

  public TunnelInfo tunnelDstAddress(String tunnelDstAddress) {
    this.tunnelDstAddress = tunnelDstAddress;
    return this;
  }

  /**
   * Destination address of the tunnel.
   * @return tunnelDstAddress
  **/
  @ApiModelProperty(required = true, value = "Destination address of the tunnel.")
      @NotNull

    public String getTunnelDstAddress() {
    return tunnelDstAddress;
  }

  public void setTunnelDstAddress(String tunnelDstAddress) {
    this.tunnelDstAddress = tunnelDstAddress;
  }

  public TunnelInfo tunnelSpecificData(String tunnelSpecificData) {
    this.tunnelSpecificData = tunnelSpecificData;
    return this;
  }

  /**
   * Get tunnelSpecificData
   * @return tunnelSpecificData
  **/
  @ApiModelProperty(value = "")
  
    public String getTunnelSpecificData() {
    return tunnelSpecificData;
  }

  public void setTunnelSpecificData(String tunnelSpecificData) {
    this.tunnelSpecificData = tunnelSpecificData;
  }

  public TunnelInfo tunnelSrcAddress(String tunnelSrcAddress) {
    this.tunnelSrcAddress = tunnelSrcAddress;
    return this;
  }

  /**
   * Source address of the tunnel.
   * @return tunnelSrcAddress
  **/
  @ApiModelProperty(required = true, value = "Source address of the tunnel.")
      @NotNull

    public String getTunnelSrcAddress() {
    return tunnelSrcAddress;
  }

  public void setTunnelSrcAddress(String tunnelSrcAddress) {
    this.tunnelSrcAddress = tunnelSrcAddress;
  }

  public TunnelInfo tunnelType(TunnelType tunnelType) {
    this.tunnelType = tunnelType;
    return this;
  }

  /**
   * Get tunnelType
   * @return tunnelType
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public TunnelType getTunnelType() {
    return tunnelType;
  }

  public void setTunnelType(TunnelType tunnelType) {
    this.tunnelType = tunnelType;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TunnelInfo tunnelInfo = (TunnelInfo) o;
    return Objects.equals(this.tunnelDstAddress, tunnelInfo.tunnelDstAddress) &&
        Objects.equals(this.tunnelSpecificData, tunnelInfo.tunnelSpecificData) &&
        Objects.equals(this.tunnelSrcAddress, tunnelInfo.tunnelSrcAddress) &&
        Objects.equals(this.tunnelType, tunnelInfo.tunnelType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tunnelDstAddress, tunnelSpecificData, tunnelSrcAddress, tunnelType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TunnelInfo {\n");
    
    sb.append("    tunnelDstAddress: ").append(toIndentedString(tunnelDstAddress)).append("\n");
    sb.append("    tunnelSpecificData: ").append(toIndentedString(tunnelSpecificData)).append("\n");
    sb.append("    tunnelSrcAddress: ").append(toIndentedString(tunnelSrcAddress)).append("\n");
    sb.append("    tunnelType: ").append(toIndentedString(tunnelType)).append("\n");
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
