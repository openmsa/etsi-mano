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
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.ConnectivityServiceEndpointInfo;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.MscsConfigData;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.MscsInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type provides protocol specific information used to connect the comprising network resources realizing a VL, e.g., when the VL is deployed on several sites and across a WAN. This type supports signalling input information about both pre-provisioned WAN connectivity realized by external entities to NFV-MANO, as well as for the creation of MSCS under NFV-MANO responsibility (i.e., when connectivity is realized when NFVO communicates with the WIM). It shall comply with the provisions defined in table 6.5.3.81-1. NOTE: At least one of these attributes shall be present. Annex E documents the applicability of certain attributes depending on the WAN and NFVI-PoP network management and the responsibilities of NFV-MANO in its provisioning. 
 */
@Schema(description = "This type provides protocol specific information used to connect the comprising network resources realizing a VL, e.g., when the VL is deployed on several sites and across a WAN. This type supports signalling input information about both pre-provisioned WAN connectivity realized by external entities to NFV-MANO, as well as for the creation of MSCS under NFV-MANO responsibility (i.e., when connectivity is realized when NFVO communicates with the WIM). It shall comply with the provisions defined in table 6.5.3.81-1. NOTE: At least one of these attributes shall be present. Annex E documents the applicability of certain attributes depending on the WAN and NFVI-PoP network management and the responsibilities of NFV-MANO in its provisioning. ")
@Validated


public class WanConnectionProtocolData  implements AnyOfWanConnectionProtocolData {
  @JsonProperty("mscsInfo")
  private MscsInfo mscsInfo = null;

  @JsonProperty("connectivityServiceEndpointConfigDatas")
  @Valid
  private List<ConnectivityServiceEndpointInfo> connectivityServiceEndpointConfigDatas = null;

  @JsonProperty("mscsConfigData")
  private MscsConfigData mscsConfigData = null;

  public WanConnectionProtocolData mscsInfo(MscsInfo mscsInfo) {
    this.mscsInfo = mscsInfo;
    return this;
  }

  /**
   * Get mscsInfo
   * @return mscsInfo
   **/
  @Schema(description = "")
  
    @Valid
    public MscsInfo getMscsInfo() {
    return mscsInfo;
  }

  public void setMscsInfo(MscsInfo mscsInfo) {
    this.mscsInfo = mscsInfo;
  }

  public WanConnectionProtocolData connectivityServiceEndpointConfigDatas(List<ConnectivityServiceEndpointInfo> connectivityServiceEndpointConfigDatas) {
    this.connectivityServiceEndpointConfigDatas = connectivityServiceEndpointConfigDatas;
    return this;
  }

  public WanConnectionProtocolData addConnectivityServiceEndpointConfigDatasItem(ConnectivityServiceEndpointInfo connectivityServiceEndpointConfigDatasItem) {
    if (this.connectivityServiceEndpointConfigDatas == null) {
      this.connectivityServiceEndpointConfigDatas = new ArrayList<>();
    }
    this.connectivityServiceEndpointConfigDatas.add(connectivityServiceEndpointConfigDatasItem);
    return this;
  }

  /**
   * Configuration data for the network resources in the NFVI-PoP. See note. 
   * @return connectivityServiceEndpointConfigDatas
   **/
  @Schema(description = "Configuration data for the network resources in the NFVI-PoP. See note. ")
      @Valid
    public List<ConnectivityServiceEndpointInfo> getConnectivityServiceEndpointConfigDatas() {
    return connectivityServiceEndpointConfigDatas;
  }

  public void setConnectivityServiceEndpointConfigDatas(List<ConnectivityServiceEndpointInfo> connectivityServiceEndpointConfigDatas) {
    this.connectivityServiceEndpointConfigDatas = connectivityServiceEndpointConfigDatas;
  }

  public WanConnectionProtocolData mscsConfigData(MscsConfigData mscsConfigData) {
    this.mscsConfigData = mscsConfigData;
    return this;
  }

  /**
   * Get mscsConfigData
   * @return mscsConfigData
   **/
  @Schema(description = "")
  
    @Valid
    public MscsConfigData getMscsConfigData() {
    return mscsConfigData;
  }

  public void setMscsConfigData(MscsConfigData mscsConfigData) {
    this.mscsConfigData = mscsConfigData;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WanConnectionProtocolData wanConnectionProtocolData = (WanConnectionProtocolData) o;
    return Objects.equals(this.mscsInfo, wanConnectionProtocolData.mscsInfo) &&
        Objects.equals(this.connectivityServiceEndpointConfigDatas, wanConnectionProtocolData.connectivityServiceEndpointConfigDatas) &&
        Objects.equals(this.mscsConfigData, wanConnectionProtocolData.mscsConfigData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mscsInfo, connectivityServiceEndpointConfigDatas, mscsConfigData);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WanConnectionProtocolData {\n");
    
    sb.append("    mscsInfo: ").append(toIndentedString(mscsInfo)).append("\n");
    sb.append("    connectivityServiceEndpointConfigDatas: ").append(toIndentedString(connectivityServiceEndpointConfigDatas)).append("\n");
    sb.append("    mscsConfigData: ").append(toIndentedString(mscsConfigData)).append("\n");
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
