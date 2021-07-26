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
package com.ubiqube.etsi.mano.nfvo.v331.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.WanConnectionDataNsVirtualLink;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.WanConnectionDataVnfVirtualLink;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.WanConnectionProtocolData;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type provides information used to connect the comprising network resources realizing a VL, e.g., when the VL is deployed on several sites and across a WAN. It shall comply with the provisions defined in table 6.5.3.80-1. 
 */
@Schema(description = "This type provides information used to connect the comprising network resources realizing a VL, e.g., when the VL is deployed on several sites and across a WAN. It shall comply with the provisions defined in table 6.5.3.80-1. ")
@Validated


public class WanConnectionData  implements OneOfWanConnectionData {
  @JsonProperty("nsVirtualLink")
  private WanConnectionDataNsVirtualLink nsVirtualLink = null;

  @JsonProperty("vnfVirtualLink")
  private WanConnectionDataVnfVirtualLink vnfVirtualLink = null;

  @JsonProperty("protocolData")
  private WanConnectionProtocolData protocolData = null;

  public WanConnectionData nsVirtualLink(WanConnectionDataNsVirtualLink nsVirtualLink) {
    this.nsVirtualLink = nsVirtualLink;
    return this;
  }

  /**
   * Get nsVirtualLink
   * @return nsVirtualLink
   **/
  @Schema(description = "")
  
    @Valid
    public WanConnectionDataNsVirtualLink getNsVirtualLink() {
    return nsVirtualLink;
  }

  public void setNsVirtualLink(WanConnectionDataNsVirtualLink nsVirtualLink) {
    this.nsVirtualLink = nsVirtualLink;
  }

  public WanConnectionData vnfVirtualLink(WanConnectionDataVnfVirtualLink vnfVirtualLink) {
    this.vnfVirtualLink = vnfVirtualLink;
    return this;
  }

  /**
   * Get vnfVirtualLink
   * @return vnfVirtualLink
   **/
  @Schema(description = "")
  
    @Valid
    public WanConnectionDataVnfVirtualLink getVnfVirtualLink() {
    return vnfVirtualLink;
  }

  public void setVnfVirtualLink(WanConnectionDataVnfVirtualLink vnfVirtualLink) {
    this.vnfVirtualLink = vnfVirtualLink;
  }

  public WanConnectionData protocolData(WanConnectionProtocolData protocolData) {
    this.protocolData = protocolData;
    return this;
  }

  /**
   * Get protocolData
   * @return protocolData
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public WanConnectionProtocolData getProtocolData() {
    return protocolData;
  }

  public void setProtocolData(WanConnectionProtocolData protocolData) {
    this.protocolData = protocolData;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WanConnectionData wanConnectionData = (WanConnectionData) o;
    return Objects.equals(this.nsVirtualLink, wanConnectionData.nsVirtualLink) &&
        Objects.equals(this.vnfVirtualLink, wanConnectionData.vnfVirtualLink) &&
        Objects.equals(this.protocolData, wanConnectionData.protocolData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nsVirtualLink, vnfVirtualLink, protocolData);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WanConnectionData {\n");
    
    sb.append("    nsVirtualLink: ").append(toIndentedString(nsVirtualLink)).append("\n");
    sb.append("    vnfVirtualLink: ").append(toIndentedString(vnfVirtualLink)).append("\n");
    sb.append("    protocolData: ").append(toIndentedString(protocolData)).append("\n");
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
