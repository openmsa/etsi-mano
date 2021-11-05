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
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.SiteToWanLayer2ProtocolData;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.SiteToWanLayer3ProtocolData;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type provides configuration data for the NFVI-PoP network gateway providing connectivity service endpoints. The connectivity service endpoints are used as endpoints by an MSCS. It shall comply with the provisions defined in Table 6.5.3.84-1. 
 */
@Schema(description = "This type provides configuration data for the NFVI-PoP network gateway providing connectivity service endpoints. The connectivity service endpoints are used as endpoints by an MSCS. It shall comply with the provisions defined in Table 6.5.3.84-1. ")
@Validated


public class ConnectivityServiceEndpointInfo   {
  @JsonProperty("connectivityServiceEndpointId")
  private String connectivityServiceEndpointId = null;

  @JsonProperty("vimId")
  private String vimId = null;

  @JsonProperty("siteToWanLayer2ProtocolData")
  private SiteToWanLayer2ProtocolData siteToWanLayer2ProtocolData = null;

  @JsonProperty("siteToWanLayer3ProtocolData")
  private SiteToWanLayer3ProtocolData siteToWanLayer3ProtocolData = null;

  public ConnectivityServiceEndpointInfo connectivityServiceEndpointId(String connectivityServiceEndpointId) {
    this.connectivityServiceEndpointId = connectivityServiceEndpointId;
    return this;
  }

  /**
   * Get connectivityServiceEndpointId
   * @return connectivityServiceEndpointId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getConnectivityServiceEndpointId() {
    return connectivityServiceEndpointId;
  }

  public void setConnectivityServiceEndpointId(String connectivityServiceEndpointId) {
    this.connectivityServiceEndpointId = connectivityServiceEndpointId;
  }

  public ConnectivityServiceEndpointInfo vimId(String vimId) {
    this.vimId = vimId;
    return this;
  }

  /**
   * Get vimId
   * @return vimId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getVimId() {
    return vimId;
  }

  public void setVimId(String vimId) {
    this.vimId = vimId;
  }

  public ConnectivityServiceEndpointInfo siteToWanLayer2ProtocolData(SiteToWanLayer2ProtocolData siteToWanLayer2ProtocolData) {
    this.siteToWanLayer2ProtocolData = siteToWanLayer2ProtocolData;
    return this;
  }

  /**
   * Get siteToWanLayer2ProtocolData
   * @return siteToWanLayer2ProtocolData
   **/
  @Schema(description = "")
  
    @Valid
    public SiteToWanLayer2ProtocolData getSiteToWanLayer2ProtocolData() {
    return siteToWanLayer2ProtocolData;
  }

  public void setSiteToWanLayer2ProtocolData(SiteToWanLayer2ProtocolData siteToWanLayer2ProtocolData) {
    this.siteToWanLayer2ProtocolData = siteToWanLayer2ProtocolData;
  }

  public ConnectivityServiceEndpointInfo siteToWanLayer3ProtocolData(SiteToWanLayer3ProtocolData siteToWanLayer3ProtocolData) {
    this.siteToWanLayer3ProtocolData = siteToWanLayer3ProtocolData;
    return this;
  }

  /**
   * Get siteToWanLayer3ProtocolData
   * @return siteToWanLayer3ProtocolData
   **/
  @Schema(description = "")
  
    @Valid
    public SiteToWanLayer3ProtocolData getSiteToWanLayer3ProtocolData() {
    return siteToWanLayer3ProtocolData;
  }

  public void setSiteToWanLayer3ProtocolData(SiteToWanLayer3ProtocolData siteToWanLayer3ProtocolData) {
    this.siteToWanLayer3ProtocolData = siteToWanLayer3ProtocolData;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConnectivityServiceEndpointInfo connectivityServiceEndpointInfo = (ConnectivityServiceEndpointInfo) o;
    return Objects.equals(this.connectivityServiceEndpointId, connectivityServiceEndpointInfo.connectivityServiceEndpointId) &&
        Objects.equals(this.vimId, connectivityServiceEndpointInfo.vimId) &&
        Objects.equals(this.siteToWanLayer2ProtocolData, connectivityServiceEndpointInfo.siteToWanLayer2ProtocolData) &&
        Objects.equals(this.siteToWanLayer3ProtocolData, connectivityServiceEndpointInfo.siteToWanLayer3ProtocolData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(connectivityServiceEndpointId, vimId, siteToWanLayer2ProtocolData, siteToWanLayer3ProtocolData);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConnectivityServiceEndpointInfo {\n");
    
    sb.append("    connectivityServiceEndpointId: ").append(toIndentedString(connectivityServiceEndpointId)).append("\n");
    sb.append("    vimId: ").append(toIndentedString(vimId)).append("\n");
    sb.append("    siteToWanLayer2ProtocolData: ").append(toIndentedString(siteToWanLayer2ProtocolData)).append("\n");
    sb.append("    siteToWanLayer3ProtocolData: ").append(toIndentedString(siteToWanLayer3ProtocolData)).append("\n");
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
