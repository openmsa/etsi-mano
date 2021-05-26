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
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.SiteToWanLayer2ProtocolDataForwardingConfig;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.SiteToWanLayer2ProtocolDataLayer2ConnectionInfo;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.SiteToWanLayer2ProtocolDataVirtualRoutingAndForwarding;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type provides information about Layer 2 protocol specific information for the configuration of the NFVI-PoP network gateway to enable the stitching of the intra-site VN to the MSCS over the WAN. It shall comply with the provisions defined in Table 6.5.3.85-1. 
 */
@Schema(description = "This type provides information about Layer 2 protocol specific information for the configuration of the NFVI-PoP network gateway to enable the stitching of the intra-site VN to the MSCS over the WAN. It shall comply with the provisions defined in Table 6.5.3.85-1. ")
@Validated


public class SiteToWanLayer2ProtocolData   {
  @JsonProperty("layer2ConnectionInfo")
  private SiteToWanLayer2ProtocolDataLayer2ConnectionInfo layer2ConnectionInfo = null;

  @JsonProperty("mtuL2")
  private BigDecimal mtuL2 = null;

  @JsonProperty("virtualRoutingAndForwarding")
  private SiteToWanLayer2ProtocolDataVirtualRoutingAndForwarding virtualRoutingAndForwarding = null;

  @JsonProperty("forwardingConfig")
  private SiteToWanLayer2ProtocolDataForwardingConfig forwardingConfig = null;

  public SiteToWanLayer2ProtocolData layer2ConnectionInfo(SiteToWanLayer2ProtocolDataLayer2ConnectionInfo layer2ConnectionInfo) {
    this.layer2ConnectionInfo = layer2ConnectionInfo;
    return this;
  }

  /**
   * Get layer2ConnectionInfo
   * @return layer2ConnectionInfo
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public SiteToWanLayer2ProtocolDataLayer2ConnectionInfo getLayer2ConnectionInfo() {
    return layer2ConnectionInfo;
  }

  public void setLayer2ConnectionInfo(SiteToWanLayer2ProtocolDataLayer2ConnectionInfo layer2ConnectionInfo) {
    this.layer2ConnectionInfo = layer2ConnectionInfo;
  }

  public SiteToWanLayer2ProtocolData mtuL2(BigDecimal mtuL2) {
    this.mtuL2 = mtuL2;
    return this;
  }

  /**
   * Maximum Transmission Unit (MTU) that can be forwarded at layer 2 (in bytes). Default value is \"1500\" (bytes). 
   * @return mtuL2
   **/
  @Schema(description = "Maximum Transmission Unit (MTU) that can be forwarded at layer 2 (in bytes). Default value is \"1500\" (bytes). ")
  
    @Valid
    public BigDecimal getMtuL2() {
    return mtuL2;
  }

  public void setMtuL2(BigDecimal mtuL2) {
    this.mtuL2 = mtuL2;
  }

  public SiteToWanLayer2ProtocolData virtualRoutingAndForwarding(SiteToWanLayer2ProtocolDataVirtualRoutingAndForwarding virtualRoutingAndForwarding) {
    this.virtualRoutingAndForwarding = virtualRoutingAndForwarding;
    return this;
  }

  /**
   * Get virtualRoutingAndForwarding
   * @return virtualRoutingAndForwarding
   **/
  @Schema(description = "")
  
    @Valid
    public SiteToWanLayer2ProtocolDataVirtualRoutingAndForwarding getVirtualRoutingAndForwarding() {
    return virtualRoutingAndForwarding;
  }

  public void setVirtualRoutingAndForwarding(SiteToWanLayer2ProtocolDataVirtualRoutingAndForwarding virtualRoutingAndForwarding) {
    this.virtualRoutingAndForwarding = virtualRoutingAndForwarding;
  }

  public SiteToWanLayer2ProtocolData forwardingConfig(SiteToWanLayer2ProtocolDataForwardingConfig forwardingConfig) {
    this.forwardingConfig = forwardingConfig;
    return this;
  }

  /**
   * Get forwardingConfig
   * @return forwardingConfig
   **/
  @Schema(description = "")
  
    @Valid
    public SiteToWanLayer2ProtocolDataForwardingConfig getForwardingConfig() {
    return forwardingConfig;
  }

  public void setForwardingConfig(SiteToWanLayer2ProtocolDataForwardingConfig forwardingConfig) {
    this.forwardingConfig = forwardingConfig;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SiteToWanLayer2ProtocolData siteToWanLayer2ProtocolData = (SiteToWanLayer2ProtocolData) o;
    return Objects.equals(this.layer2ConnectionInfo, siteToWanLayer2ProtocolData.layer2ConnectionInfo) &&
        Objects.equals(this.mtuL2, siteToWanLayer2ProtocolData.mtuL2) &&
        Objects.equals(this.virtualRoutingAndForwarding, siteToWanLayer2ProtocolData.virtualRoutingAndForwarding) &&
        Objects.equals(this.forwardingConfig, siteToWanLayer2ProtocolData.forwardingConfig);
  }

  @Override
  public int hashCode() {
    return Objects.hash(layer2ConnectionInfo, mtuL2, virtualRoutingAndForwarding, forwardingConfig);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SiteToWanLayer2ProtocolData {\n");
    
    sb.append("    layer2ConnectionInfo: ").append(toIndentedString(layer2ConnectionInfo)).append("\n");
    sb.append("    mtuL2: ").append(toIndentedString(mtuL2)).append("\n");
    sb.append("    virtualRoutingAndForwarding: ").append(toIndentedString(virtualRoutingAndForwarding)).append("\n");
    sb.append("    forwardingConfig: ").append(toIndentedString(forwardingConfig)).append("\n");
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
