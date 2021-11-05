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
package com.ubiqube.etsi.mano.vnfm.v351.model.vnflcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.vnfm.v351.model.vnflcm.IpOverEthernetAddressInfoIpAddresses;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents information about a network address that has been assigned.  It shall comply with the provisions defined in table 5.5.3.10-1. NOTE 1: At least one of \&quot;macAddress\&quot; or \&quot;ipAddresses\&quot; shall be present. NOTE 2: Exactly one of \&quot;addresses\&quot; or \&quot;addressRange\&quot; shall be present. NOTE 3: If the Cp instance represents a subport in a trunk, segmentationId shall be present.          Otherwise it shall not be present. NOTE 4: Depending on the NFVI networking infrastructure, the segmentationId may indicate the          actual network segment value (e.g. vlan Id, Vxlan segmentation id, etc.) used in the          transport header of the packets or it may be an identifier used between the application          and the NFVI networking infrastructure to identify the network sub-interface of the trunk          port in question. In the latter case the NFVI infrastructure will map this local segmentationId          to whatever segmentationId is actually used by the NFVI’s transport technology. 
 */
@Schema(description = "This type represents information about a network address that has been assigned.  It shall comply with the provisions defined in table 5.5.3.10-1. NOTE 1: At least one of \"macAddress\" or \"ipAddresses\" shall be present. NOTE 2: Exactly one of \"addresses\" or \"addressRange\" shall be present. NOTE 3: If the Cp instance represents a subport in a trunk, segmentationId shall be present.          Otherwise it shall not be present. NOTE 4: Depending on the NFVI networking infrastructure, the segmentationId may indicate the          actual network segment value (e.g. vlan Id, Vxlan segmentation id, etc.) used in the          transport header of the packets or it may be an identifier used between the application          and the NFVI networking infrastructure to identify the network sub-interface of the trunk          port in question. In the latter case the NFVI infrastructure will map this local segmentationId          to whatever segmentationId is actually used by the NFVI’s transport technology. ")
@Validated


public class IpOverEthernetAddressInfo  implements OneOfIpOverEthernetAddressInfo {
  @JsonProperty("macAddress")
  private String macAddress = null;

  @JsonProperty("segmentationId")
  private String segmentationId = null;

  @JsonProperty("ipAddresses")
  @Valid
  private List<IpOverEthernetAddressInfoIpAddresses> ipAddresses = null;

  public IpOverEthernetAddressInfo macAddress(String macAddress) {
    this.macAddress = macAddress;
    return this;
  }

  /**
   * Get macAddress
   * @return macAddress
   **/
  @Schema(description = "")
  
    public String getMacAddress() {
    return macAddress;
  }

  public void setMacAddress(String macAddress) {
    this.macAddress = macAddress;
  }

  public IpOverEthernetAddressInfo segmentationId(String segmentationId) {
    this.segmentationId = segmentationId;
    return this;
  }

  /**
   * Identification of the network segment to which the Cp instance connects to. See notes 3 and 4. 
   * @return segmentationId
   **/
  @Schema(description = "Identification of the network segment to which the Cp instance connects to. See notes 3 and 4. ")
  
    public String getSegmentationId() {
    return segmentationId;
  }

  public void setSegmentationId(String segmentationId) {
    this.segmentationId = segmentationId;
  }

  public IpOverEthernetAddressInfo ipAddresses(List<IpOverEthernetAddressInfoIpAddresses> ipAddresses) {
    this.ipAddresses = ipAddresses;
    return this;
  }

  public IpOverEthernetAddressInfo addIpAddressesItem(IpOverEthernetAddressInfoIpAddresses ipAddressesItem) {
    if (this.ipAddresses == null) {
      this.ipAddresses = new ArrayList<>();
    }
    this.ipAddresses.add(ipAddressesItem);
    return this;
  }

  /**
   * Addresses assigned to the CP instance. Each entry represents IP addresses assigned by fixed or  dynamic IP address assignment per subnet. See note 1. 
   * @return ipAddresses
   **/
  @Schema(description = "Addresses assigned to the CP instance. Each entry represents IP addresses assigned by fixed or  dynamic IP address assignment per subnet. See note 1. ")
      @Valid
    public List<IpOverEthernetAddressInfoIpAddresses> getIpAddresses() {
    return ipAddresses;
  }

  public void setIpAddresses(List<IpOverEthernetAddressInfoIpAddresses> ipAddresses) {
    this.ipAddresses = ipAddresses;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IpOverEthernetAddressInfo ipOverEthernetAddressInfo = (IpOverEthernetAddressInfo) o;
    return Objects.equals(this.macAddress, ipOverEthernetAddressInfo.macAddress) &&
        Objects.equals(this.segmentationId, ipOverEthernetAddressInfo.segmentationId) &&
        Objects.equals(this.ipAddresses, ipOverEthernetAddressInfo.ipAddresses);
  }

  @Override
  public int hashCode() {
    return Objects.hash(macAddress, segmentationId, ipAddresses);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IpOverEthernetAddressInfo {\n");
    
    sb.append("    macAddress: ").append(toIndentedString(macAddress)).append("\n");
    sb.append("    segmentationId: ").append(toIndentedString(segmentationId)).append("\n");
    sb.append("    ipAddresses: ").append(toIndentedString(ipAddresses)).append("\n");
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
