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
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.KeyValuePairs;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Defines parameters for BGP routing. It shall only be present if the routingProtocol&#x3D;\&quot;BGP\&quot;. 
 */
@Schema(description = "Defines parameters for BGP routing. It shall only be present if the routingProtocol=\"BGP\". ")
@Validated


public class SiteToWanLayer3ProtocolDataRoutingInfoBgpRouting   {
  @JsonProperty("bgpAs")
  private Object bgpAs = null;

  @JsonProperty("bgpNeighbour")
  private String bgpNeighbour = null;

  @JsonProperty("bgpAdditionalParam")
  private KeyValuePairs bgpAdditionalParam = null;

  public SiteToWanLayer3ProtocolDataRoutingInfoBgpRouting bgpAs(Object bgpAs) {
    this.bgpAs = bgpAs;
    return this;
  }

  /**
   * The Autonomous System (AS) identification applicable to the BGP routing info entry. 
   * @return bgpAs
   **/
  @Schema(required = true, description = "The Autonomous System (AS) identification applicable to the BGP routing info entry. ")
      @NotNull

    public Object getBgpAs() {
    return bgpAs;
  }

  public void setBgpAs(Object bgpAs) {
    this.bgpAs = bgpAs;
  }

  public SiteToWanLayer3ProtocolDataRoutingInfoBgpRouting bgpNeighbour(String bgpNeighbour) {
    this.bgpNeighbour = bgpNeighbour;
    return this;
  }

  /**
   * Get bgpNeighbour
   * @return bgpNeighbour
   **/
  @Schema(description = "")
  
    public String getBgpNeighbour() {
    return bgpNeighbour;
  }

  public void setBgpNeighbour(String bgpNeighbour) {
    this.bgpNeighbour = bgpNeighbour;
  }

  public SiteToWanLayer3ProtocolDataRoutingInfoBgpRouting bgpAdditionalParam(KeyValuePairs bgpAdditionalParam) {
    this.bgpAdditionalParam = bgpAdditionalParam;
    return this;
  }

  /**
   * Get bgpAdditionalParam
   * @return bgpAdditionalParam
   **/
  @Schema(description = "")
  
    @Valid
    public KeyValuePairs getBgpAdditionalParam() {
    return bgpAdditionalParam;
  }

  public void setBgpAdditionalParam(KeyValuePairs bgpAdditionalParam) {
    this.bgpAdditionalParam = bgpAdditionalParam;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SiteToWanLayer3ProtocolDataRoutingInfoBgpRouting siteToWanLayer3ProtocolDataRoutingInfoBgpRouting = (SiteToWanLayer3ProtocolDataRoutingInfoBgpRouting) o;
    return Objects.equals(this.bgpAs, siteToWanLayer3ProtocolDataRoutingInfoBgpRouting.bgpAs) &&
        Objects.equals(this.bgpNeighbour, siteToWanLayer3ProtocolDataRoutingInfoBgpRouting.bgpNeighbour) &&
        Objects.equals(this.bgpAdditionalParam, siteToWanLayer3ProtocolDataRoutingInfoBgpRouting.bgpAdditionalParam);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bgpAs, bgpNeighbour, bgpAdditionalParam);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SiteToWanLayer3ProtocolDataRoutingInfoBgpRouting {\n");
    
    sb.append("    bgpAs: ").append(toIndentedString(bgpAs)).append("\n");
    sb.append("    bgpNeighbour: ").append(toIndentedString(bgpNeighbour)).append("\n");
    sb.append("    bgpAdditionalParam: ").append(toIndentedString(bgpAdditionalParam)).append("\n");
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
