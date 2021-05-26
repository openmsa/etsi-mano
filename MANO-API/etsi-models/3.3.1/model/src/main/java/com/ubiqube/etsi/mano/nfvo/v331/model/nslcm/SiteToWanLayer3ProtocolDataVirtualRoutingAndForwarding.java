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
 * Configuration related to the virtual routing and forwarding (VRF). 
 */
@Schema(description = "Configuration related to the virtual routing and forwarding (VRF). ")
@Validated


public class SiteToWanLayer3ProtocolDataVirtualRoutingAndForwarding   {
  @JsonProperty("vrfName")
  private String vrfName = null;

  public SiteToWanLayer3ProtocolDataVirtualRoutingAndForwarding vrfName(String vrfName) {
    this.vrfName = vrfName;
    return this;
  }

  /**
   * Name (or identifier) of the VRF instance. 
   * @return vrfName
   **/
  @Schema(required = true, description = "Name (or identifier) of the VRF instance. ")
      @NotNull

    public String getVrfName() {
    return vrfName;
  }

  public void setVrfName(String vrfName) {
    this.vrfName = vrfName;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SiteToWanLayer3ProtocolDataVirtualRoutingAndForwarding siteToWanLayer3ProtocolDataVirtualRoutingAndForwarding = (SiteToWanLayer3ProtocolDataVirtualRoutingAndForwarding) o;
    return Objects.equals(this.vrfName, siteToWanLayer3ProtocolDataVirtualRoutingAndForwarding.vrfName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vrfName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SiteToWanLayer3ProtocolDataVirtualRoutingAndForwarding {\n");
    
    sb.append("    vrfName: ").append(toIndentedString(vrfName)).append("\n");
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
