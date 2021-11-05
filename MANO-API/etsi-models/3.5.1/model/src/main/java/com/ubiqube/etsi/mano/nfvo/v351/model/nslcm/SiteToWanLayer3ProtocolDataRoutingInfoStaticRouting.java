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
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Defines a static route. It shall only be present if the routingProtocol&#x3D;\&quot;STATIC\&quot;. 
 */
@Schema(description = "Defines a static route. It shall only be present if the routingProtocol=\"STATIC\". ")
@Validated


public class SiteToWanLayer3ProtocolDataRoutingInfoStaticRouting   {
  /**
   * The IP version applicable to the routing entry. Permitted values: - IPV4 - IPV6 
   */
  public enum IpVersionEnum {
    IPV4("IPV4"),
    
    IPV6("IPV6");

    private String value;

    IpVersionEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static IpVersionEnum fromValue(String text) {
      for (IpVersionEnum b : IpVersionEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("ipVersion")
  private IpVersionEnum ipVersion = null;

  @JsonProperty("ipPrefix")
  private String ipPrefix = null;

  @JsonProperty("prefixSize")
  private BigDecimal prefixSize = null;

  @JsonProperty("nextHop")
  private String nextHop = null;

  public SiteToWanLayer3ProtocolDataRoutingInfoStaticRouting ipVersion(IpVersionEnum ipVersion) {
    this.ipVersion = ipVersion;
    return this;
  }

  /**
   * The IP version applicable to the routing entry. Permitted values: - IPV4 - IPV6 
   * @return ipVersion
   **/
  @Schema(required = true, description = "The IP version applicable to the routing entry. Permitted values: - IPV4 - IPV6 ")
      @NotNull

    public IpVersionEnum getIpVersion() {
    return ipVersion;
  }

  public void setIpVersion(IpVersionEnum ipVersion) {
    this.ipVersion = ipVersion;
  }

  public SiteToWanLayer3ProtocolDataRoutingInfoStaticRouting ipPrefix(String ipPrefix) {
    this.ipPrefix = ipPrefix;
    return this;
  }

  /**
   * Get ipPrefix
   * @return ipPrefix
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getIpPrefix() {
    return ipPrefix;
  }

  public void setIpPrefix(String ipPrefix) {
    this.ipPrefix = ipPrefix;
  }

  public SiteToWanLayer3ProtocolDataRoutingInfoStaticRouting prefixSize(BigDecimal prefixSize) {
    this.prefixSize = prefixSize;
    return this;
  }

  /**
   * The IP prefix size. 
   * @return prefixSize
   **/
  @Schema(required = true, description = "The IP prefix size. ")
      @NotNull

    @Valid
    public BigDecimal getPrefixSize() {
    return prefixSize;
  }

  public void setPrefixSize(BigDecimal prefixSize) {
    this.prefixSize = prefixSize;
  }

  public SiteToWanLayer3ProtocolDataRoutingInfoStaticRouting nextHop(String nextHop) {
    this.nextHop = nextHop;
    return this;
  }

  /**
   * Get nextHop
   * @return nextHop
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getNextHop() {
    return nextHop;
  }

  public void setNextHop(String nextHop) {
    this.nextHop = nextHop;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SiteToWanLayer3ProtocolDataRoutingInfoStaticRouting siteToWanLayer3ProtocolDataRoutingInfoStaticRouting = (SiteToWanLayer3ProtocolDataRoutingInfoStaticRouting) o;
    return Objects.equals(this.ipVersion, siteToWanLayer3ProtocolDataRoutingInfoStaticRouting.ipVersion) &&
        Objects.equals(this.ipPrefix, siteToWanLayer3ProtocolDataRoutingInfoStaticRouting.ipPrefix) &&
        Objects.equals(this.prefixSize, siteToWanLayer3ProtocolDataRoutingInfoStaticRouting.prefixSize) &&
        Objects.equals(this.nextHop, siteToWanLayer3ProtocolDataRoutingInfoStaticRouting.nextHop);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ipVersion, ipPrefix, prefixSize, nextHop);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SiteToWanLayer3ProtocolDataRoutingInfoStaticRouting {\n");
    
    sb.append("    ipVersion: ").append(toIndentedString(ipVersion)).append("\n");
    sb.append("    ipPrefix: ").append(toIndentedString(ipPrefix)).append("\n");
    sb.append("    prefixSize: ").append(toIndentedString(prefixSize)).append("\n");
    sb.append("    nextHop: ").append(toIndentedString(nextHop)).append("\n");
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
