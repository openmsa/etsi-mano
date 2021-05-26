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
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Defines parameters for OSPF routing. It shall only be present if the routingProtocol&#x3D;\&quot;OSPF\&quot;. 
 */
@Schema(description = "Defines parameters for OSPF routing. It shall only be present if the routingProtocol=\"OSPF\". ")
@Validated


public class SiteToWanLayer3ProtocolDataRoutingInfoOspfRouting   {
  @JsonProperty("areaId")
  private String areaId = null;

  public SiteToWanLayer3ProtocolDataRoutingInfoOspfRouting areaId(String areaId) {
    this.areaId = areaId;
    return this;
  }

  /**
   * The routing area identifier, e.g., a number or an IP address. 
   * @return areaId
   **/
  @Schema(required = true, description = "The routing area identifier, e.g., a number or an IP address. ")
      @NotNull

    public String getAreaId() {
    return areaId;
  }

  public void setAreaId(String areaId) {
    this.areaId = areaId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SiteToWanLayer3ProtocolDataRoutingInfoOspfRouting siteToWanLayer3ProtocolDataRoutingInfoOspfRouting = (SiteToWanLayer3ProtocolDataRoutingInfoOspfRouting) o;
    return Objects.equals(this.areaId, siteToWanLayer3ProtocolDataRoutingInfoOspfRouting.areaId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(areaId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SiteToWanLayer3ProtocolDataRoutingInfoOspfRouting {\n");
    
    sb.append("    areaId: ").append(toIndentedString(areaId)).append("\n");
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
