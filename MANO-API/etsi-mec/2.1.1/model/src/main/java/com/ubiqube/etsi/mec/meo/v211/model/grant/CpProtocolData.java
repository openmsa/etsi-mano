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
import com.ubiqube.etsi.mec.meo.v211.model.grant.IpOverEthernetAddressData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CpProtocolData
 */
@Validated
public class CpProtocolData   {
  @JsonProperty("ipOverEthernet")
  private IpOverEthernetAddressData ipOverEthernet = null;

  @JsonProperty("layerProtocol")
  private IpOverEthernetAddressData layerProtocol = null;

  public CpProtocolData ipOverEthernet(IpOverEthernetAddressData ipOverEthernet) {
    this.ipOverEthernet = ipOverEthernet;
    return this;
  }

  /**
   * Get ipOverEthernet
   * @return ipOverEthernet
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public IpOverEthernetAddressData getIpOverEthernet() {
    return ipOverEthernet;
  }

  public void setIpOverEthernet(IpOverEthernetAddressData ipOverEthernet) {
    this.ipOverEthernet = ipOverEthernet;
  }

  public CpProtocolData layerProtocol(IpOverEthernetAddressData layerProtocol) {
    this.layerProtocol = layerProtocol;
    return this;
  }

  /**
   * Get layerProtocol
   * @return layerProtocol
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public IpOverEthernetAddressData getLayerProtocol() {
    return layerProtocol;
  }

  public void setLayerProtocol(IpOverEthernetAddressData layerProtocol) {
    this.layerProtocol = layerProtocol;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CpProtocolData cpProtocolData = (CpProtocolData) o;
    return Objects.equals(this.ipOverEthernet, cpProtocolData.ipOverEthernet) &&
        Objects.equals(this.layerProtocol, cpProtocolData.layerProtocol);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ipOverEthernet, layerProtocol);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CpProtocolData {\n");
    
    sb.append("    ipOverEthernet: ").append(toIndentedString(ipOverEthernet)).append("\n");
    sb.append("    layerProtocol: ").append(toIndentedString(layerProtocol)).append("\n");
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
