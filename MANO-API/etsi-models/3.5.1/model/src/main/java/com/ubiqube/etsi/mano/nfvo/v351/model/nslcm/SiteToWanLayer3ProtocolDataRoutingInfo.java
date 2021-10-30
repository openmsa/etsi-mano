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
package com.ubiqube.etsi.mano.nfvo.v351.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.SiteToWanLayer3ProtocolDataRoutingInfoBgpRouting;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.SiteToWanLayer3ProtocolDataRoutingInfoOspfRouting;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.SiteToWanLayer3ProtocolDataRoutingInfoRouteMapsDistribution;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.SiteToWanLayer3ProtocolDataRoutingInfoStaticRouting;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * The routing information that is activated on the connectivity service endpoint. More than one \&quot;routingInfo\&quot; is allowed to enable stacking different routing protocols (e.g., one routing protocol for IPv4 and another one for IPv6). 
 */
@Schema(description = "The routing information that is activated on the connectivity service endpoint. More than one \"routingInfo\" is allowed to enable stacking different routing protocols (e.g., one routing protocol for IPv4 and another one for IPv6). ")
@Validated


public class SiteToWanLayer3ProtocolDataRoutingInfo   {
  /**
   * The routing protocol that is activated on the connectivity service endpoint. Permitted values: - BGP: used for dynamic routing BGPv4. - RIP: used for dynamic routing RIPv2. - OSPF: used for dynamic routing (OSPF version 2 for IPv4; and OSPF version 3 for IPv6). - STATIC: used for static routing. - DIRECT: used when the NFVI-PoP network is directly connected to the WAN provider network. - VRRP: used when the NFVI-PoP network is directly connected to the WAN provider network with virtual   router redundancy protocol support (VRRP). 
   */
  public enum RoutingProtocolEnum {
    BGP("BGP"),
    
    RIP("RIP"),
    
    OSPF("OSPF"),
    
    STATIC("STATIC"),
    
    DIRECT("DIRECT"),
    
    VRRP("VRRP");

    private String value;

    RoutingProtocolEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static RoutingProtocolEnum fromValue(String text) {
      for (RoutingProtocolEnum b : RoutingProtocolEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("routingProtocol")
  private RoutingProtocolEnum routingProtocol = null;

  @JsonProperty("staticRouting")
  private SiteToWanLayer3ProtocolDataRoutingInfoStaticRouting staticRouting = null;

  /**
   * The IP version applicable to the dynamic routing protocol. Shall be present for dynamic routing protocols. Permitted values: - IPV4 - IPV6 
   */
  public enum RoutingAddressFamilyEnum {
    IPV4("IPV4"),
    
    IPV6("IPv6");

    private String value;

    RoutingAddressFamilyEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static RoutingAddressFamilyEnum fromValue(String text) {
      for (RoutingAddressFamilyEnum b : RoutingAddressFamilyEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("routingAddressFamily")
  private RoutingAddressFamilyEnum routingAddressFamily = null;

  @JsonProperty("ospfRouting")
  private SiteToWanLayer3ProtocolDataRoutingInfoOspfRouting ospfRouting = null;

  @JsonProperty("bgpRouting")
  private SiteToWanLayer3ProtocolDataRoutingInfoBgpRouting bgpRouting = null;

  @JsonProperty("routeMapsDistribution")
  private SiteToWanLayer3ProtocolDataRoutingInfoRouteMapsDistribution routeMapsDistribution = null;

  public SiteToWanLayer3ProtocolDataRoutingInfo routingProtocol(RoutingProtocolEnum routingProtocol) {
    this.routingProtocol = routingProtocol;
    return this;
  }

  /**
   * The routing protocol that is activated on the connectivity service endpoint. Permitted values: - BGP: used for dynamic routing BGPv4. - RIP: used for dynamic routing RIPv2. - OSPF: used for dynamic routing (OSPF version 2 for IPv4; and OSPF version 3 for IPv6). - STATIC: used for static routing. - DIRECT: used when the NFVI-PoP network is directly connected to the WAN provider network. - VRRP: used when the NFVI-PoP network is directly connected to the WAN provider network with virtual   router redundancy protocol support (VRRP). 
   * @return routingProtocol
   **/
  @Schema(required = true, description = "The routing protocol that is activated on the connectivity service endpoint. Permitted values: - BGP: used for dynamic routing BGPv4. - RIP: used for dynamic routing RIPv2. - OSPF: used for dynamic routing (OSPF version 2 for IPv4; and OSPF version 3 for IPv6). - STATIC: used for static routing. - DIRECT: used when the NFVI-PoP network is directly connected to the WAN provider network. - VRRP: used when the NFVI-PoP network is directly connected to the WAN provider network with virtual   router redundancy protocol support (VRRP). ")
      @NotNull

    public RoutingProtocolEnum getRoutingProtocol() {
    return routingProtocol;
  }

  public void setRoutingProtocol(RoutingProtocolEnum routingProtocol) {
    this.routingProtocol = routingProtocol;
  }

  public SiteToWanLayer3ProtocolDataRoutingInfo staticRouting(SiteToWanLayer3ProtocolDataRoutingInfoStaticRouting staticRouting) {
    this.staticRouting = staticRouting;
    return this;
  }

  /**
   * Get staticRouting
   * @return staticRouting
   **/
  @Schema(description = "")
  
    @Valid
    public SiteToWanLayer3ProtocolDataRoutingInfoStaticRouting getStaticRouting() {
    return staticRouting;
  }

  public void setStaticRouting(SiteToWanLayer3ProtocolDataRoutingInfoStaticRouting staticRouting) {
    this.staticRouting = staticRouting;
  }

  public SiteToWanLayer3ProtocolDataRoutingInfo routingAddressFamily(RoutingAddressFamilyEnum routingAddressFamily) {
    this.routingAddressFamily = routingAddressFamily;
    return this;
  }

  /**
   * The IP version applicable to the dynamic routing protocol. Shall be present for dynamic routing protocols. Permitted values: - IPV4 - IPV6 
   * @return routingAddressFamily
   **/
  @Schema(required = true, description = "The IP version applicable to the dynamic routing protocol. Shall be present for dynamic routing protocols. Permitted values: - IPV4 - IPV6 ")
      @NotNull

    public RoutingAddressFamilyEnum getRoutingAddressFamily() {
    return routingAddressFamily;
  }

  public void setRoutingAddressFamily(RoutingAddressFamilyEnum routingAddressFamily) {
    this.routingAddressFamily = routingAddressFamily;
  }

  public SiteToWanLayer3ProtocolDataRoutingInfo ospfRouting(SiteToWanLayer3ProtocolDataRoutingInfoOspfRouting ospfRouting) {
    this.ospfRouting = ospfRouting;
    return this;
  }

  /**
   * Get ospfRouting
   * @return ospfRouting
   **/
  @Schema(description = "")
  
    @Valid
    public SiteToWanLayer3ProtocolDataRoutingInfoOspfRouting getOspfRouting() {
    return ospfRouting;
  }

  public void setOspfRouting(SiteToWanLayer3ProtocolDataRoutingInfoOspfRouting ospfRouting) {
    this.ospfRouting = ospfRouting;
  }

  public SiteToWanLayer3ProtocolDataRoutingInfo bgpRouting(SiteToWanLayer3ProtocolDataRoutingInfoBgpRouting bgpRouting) {
    this.bgpRouting = bgpRouting;
    return this;
  }

  /**
   * Get bgpRouting
   * @return bgpRouting
   **/
  @Schema(description = "")
  
    @Valid
    public SiteToWanLayer3ProtocolDataRoutingInfoBgpRouting getBgpRouting() {
    return bgpRouting;
  }

  public void setBgpRouting(SiteToWanLayer3ProtocolDataRoutingInfoBgpRouting bgpRouting) {
    this.bgpRouting = bgpRouting;
  }

  public SiteToWanLayer3ProtocolDataRoutingInfo routeMapsDistribution(SiteToWanLayer3ProtocolDataRoutingInfoRouteMapsDistribution routeMapsDistribution) {
    this.routeMapsDistribution = routeMapsDistribution;
    return this;
  }

  /**
   * Get routeMapsDistribution
   * @return routeMapsDistribution
   **/
  @Schema(description = "")
  
    @Valid
    public SiteToWanLayer3ProtocolDataRoutingInfoRouteMapsDistribution getRouteMapsDistribution() {
    return routeMapsDistribution;
  }

  public void setRouteMapsDistribution(SiteToWanLayer3ProtocolDataRoutingInfoRouteMapsDistribution routeMapsDistribution) {
    this.routeMapsDistribution = routeMapsDistribution;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SiteToWanLayer3ProtocolDataRoutingInfo siteToWanLayer3ProtocolDataRoutingInfo = (SiteToWanLayer3ProtocolDataRoutingInfo) o;
    return Objects.equals(this.routingProtocol, siteToWanLayer3ProtocolDataRoutingInfo.routingProtocol) &&
        Objects.equals(this.staticRouting, siteToWanLayer3ProtocolDataRoutingInfo.staticRouting) &&
        Objects.equals(this.routingAddressFamily, siteToWanLayer3ProtocolDataRoutingInfo.routingAddressFamily) &&
        Objects.equals(this.ospfRouting, siteToWanLayer3ProtocolDataRoutingInfo.ospfRouting) &&
        Objects.equals(this.bgpRouting, siteToWanLayer3ProtocolDataRoutingInfo.bgpRouting) &&
        Objects.equals(this.routeMapsDistribution, siteToWanLayer3ProtocolDataRoutingInfo.routeMapsDistribution);
  }

  @Override
  public int hashCode() {
    return Objects.hash(routingProtocol, staticRouting, routingAddressFamily, ospfRouting, bgpRouting, routeMapsDistribution);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SiteToWanLayer3ProtocolDataRoutingInfo {\n");
    
    sb.append("    routingProtocol: ").append(toIndentedString(routingProtocol)).append("\n");
    sb.append("    staticRouting: ").append(toIndentedString(staticRouting)).append("\n");
    sb.append("    routingAddressFamily: ").append(toIndentedString(routingAddressFamily)).append("\n");
    sb.append("    ospfRouting: ").append(toIndentedString(ospfRouting)).append("\n");
    sb.append("    bgpRouting: ").append(toIndentedString(bgpRouting)).append("\n");
    sb.append("    routeMapsDistribution: ").append(toIndentedString(routeMapsDistribution)).append("\n");
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
