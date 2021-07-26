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
 * IP addressing information associated to a logical interface. Shall be present if the \&quot;interfaceType\&quot; of the SiteToWanLayer2ProtocolData is equal to \&quot;LOGICAL\&quot;. 
 */
@Schema(description = "IP addressing information associated to a logical interface. Shall be present if the \"interfaceType\" of the SiteToWanLayer2ProtocolData is equal to \"LOGICAL\". ")
@Validated


public class SiteToWanLayer3ProtocolDataLogicalInterfaceIpAddress   {
  @JsonProperty("ipAddress")
  private String ipAddress = null;

  @JsonProperty("associatedSegmentId")
  private String associatedSegmentId = null;

  public SiteToWanLayer3ProtocolDataLogicalInterfaceIpAddress ipAddress(String ipAddress) {
    this.ipAddress = ipAddress;
    return this;
  }

  /**
   * Get ipAddress
   * @return ipAddress
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getIpAddress() {
    return ipAddress;
  }

  public void setIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
  }

  public SiteToWanLayer3ProtocolDataLogicalInterfaceIpAddress associatedSegmentId(String associatedSegmentId) {
    this.associatedSegmentId = associatedSegmentId;
    return this;
  }

  /**
   * The associated segment identifier that has triggered the creation of the logical interface. The value shall be one of the values listed in the \"wanSegmentIds\" of the \"siteToWanLayer2ProtocolData\". 
   * @return associatedSegmentId
   **/
  @Schema(required = true, description = "The associated segment identifier that has triggered the creation of the logical interface. The value shall be one of the values listed in the \"wanSegmentIds\" of the \"siteToWanLayer2ProtocolData\". ")
      @NotNull

    public String getAssociatedSegmentId() {
    return associatedSegmentId;
  }

  public void setAssociatedSegmentId(String associatedSegmentId) {
    this.associatedSegmentId = associatedSegmentId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SiteToWanLayer3ProtocolDataLogicalInterfaceIpAddress siteToWanLayer3ProtocolDataLogicalInterfaceIpAddress = (SiteToWanLayer3ProtocolDataLogicalInterfaceIpAddress) o;
    return Objects.equals(this.ipAddress, siteToWanLayer3ProtocolDataLogicalInterfaceIpAddress.ipAddress) &&
        Objects.equals(this.associatedSegmentId, siteToWanLayer3ProtocolDataLogicalInterfaceIpAddress.associatedSegmentId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ipAddress, associatedSegmentId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SiteToWanLayer3ProtocolDataLogicalInterfaceIpAddress {\n");
    
    sb.append("    ipAddress: ").append(toIndentedString(ipAddress)).append("\n");
    sb.append("    associatedSegmentId: ").append(toIndentedString(associatedSegmentId)).append("\n");
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
