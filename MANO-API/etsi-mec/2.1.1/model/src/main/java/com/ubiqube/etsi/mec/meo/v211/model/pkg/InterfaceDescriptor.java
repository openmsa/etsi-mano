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
import com.ubiqube.etsi.mec.meo.v211.model.pkg.InterfaceType;
import com.ubiqube.etsi.mec.meo.v211.model.pkg.TunnelInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * InterfaceDescriptor
 */
@Validated
public class InterfaceDescriptor   {
  @JsonProperty("dstIPAddress")
  private String dstIPAddress = null;

  @JsonProperty("dstMACAddress")
  private String dstMACAddress = null;

  @JsonProperty("interfaceType")
  private InterfaceType interfaceType = null;

  @JsonProperty("srcMACAddress")
  private String srcMACAddress = null;

  @JsonProperty("tunnelInfo")
  private TunnelInfo tunnelInfo = null;

  public InterfaceDescriptor dstIPAddress(String dstIPAddress) {
    this.dstIPAddress = dstIPAddress;
    return this;
  }

  /**
   * If the interface type is IP, the destination address identifies the IP address of the destination. Only used for dstInterface.
   * @return dstIPAddress
  **/
  @ApiModelProperty(value = "If the interface type is IP, the destination address identifies the IP address of the destination. Only used for dstInterface.")
  
    public String getDstIPAddress() {
    return dstIPAddress;
  }

  public void setDstIPAddress(String dstIPAddress) {
    this.dstIPAddress = dstIPAddress;
  }

  public InterfaceDescriptor dstMACAddress(String dstMACAddress) {
    this.dstMACAddress = dstMACAddress;
    return this;
  }

  /**
   * If the interface type is MAC, the destination address identifies the MAC address of the destination. Only used for dstInterface.
   * @return dstMACAddress
  **/
  @ApiModelProperty(value = "If the interface type is MAC, the destination address identifies the MAC address of the destination. Only used for dstInterface.")
  
    public String getDstMACAddress() {
    return dstMACAddress;
  }

  public void setDstMACAddress(String dstMACAddress) {
    this.dstMACAddress = dstMACAddress;
  }

  public InterfaceDescriptor interfaceType(InterfaceType interfaceType) {
    this.interfaceType = interfaceType;
    return this;
  }

  /**
   * Get interfaceType
   * @return interfaceType
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public InterfaceType getInterfaceType() {
    return interfaceType;
  }

  public void setInterfaceType(InterfaceType interfaceType) {
    this.interfaceType = interfaceType;
  }

  public InterfaceDescriptor srcMACAddress(String srcMACAddress) {
    this.srcMACAddress = srcMACAddress;
    return this;
  }

  /**
   * If the interface type is MAC, the source address identifies the MAC address of the interface.
   * @return srcMACAddress
  **/
  @ApiModelProperty(value = "If the interface type is MAC, the source address identifies the MAC address of the interface.")
  
    public String getSrcMACAddress() {
    return srcMACAddress;
  }

  public void setSrcMACAddress(String srcMACAddress) {
    this.srcMACAddress = srcMACAddress;
  }

  public InterfaceDescriptor tunnelInfo(TunnelInfo tunnelInfo) {
    this.tunnelInfo = tunnelInfo;
    return this;
  }

  /**
   * Get tunnelInfo
   * @return tunnelInfo
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public TunnelInfo getTunnelInfo() {
    return tunnelInfo;
  }

  public void setTunnelInfo(TunnelInfo tunnelInfo) {
    this.tunnelInfo = tunnelInfo;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InterfaceDescriptor interfaceDescriptor = (InterfaceDescriptor) o;
    return Objects.equals(this.dstIPAddress, interfaceDescriptor.dstIPAddress) &&
        Objects.equals(this.dstMACAddress, interfaceDescriptor.dstMACAddress) &&
        Objects.equals(this.interfaceType, interfaceDescriptor.interfaceType) &&
        Objects.equals(this.srcMACAddress, interfaceDescriptor.srcMACAddress) &&
        Objects.equals(this.tunnelInfo, interfaceDescriptor.tunnelInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dstIPAddress, dstMACAddress, interfaceType, srcMACAddress, tunnelInfo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InterfaceDescriptor {\n");
    
    sb.append("    dstIPAddress: ").append(toIndentedString(dstIPAddress)).append("\n");
    sb.append("    dstMACAddress: ").append(toIndentedString(dstMACAddress)).append("\n");
    sb.append("    interfaceType: ").append(toIndentedString(interfaceType)).append("\n");
    sb.append("    srcMACAddress: ").append(toIndentedString(srcMACAddress)).append("\n");
    sb.append("    tunnelInfo: ").append(toIndentedString(tunnelInfo)).append("\n");
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
