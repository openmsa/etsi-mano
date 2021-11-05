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
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.IpOverEthernetAddressDataIpAddresses;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents network address data for IP over Ethernet. NOTE 1: At least one of \&quot;macAddress\&quot; or \&quot;ipAddresses\&quot; shall be present. NOTE 2: Exactly one of \&quot;fixedAddresses\&quot;, \&quot;numDynamicAddresses\&quot; or \&quot;ipAddressRange\&quot; shall be present. NOTE 3: If the CP instance represents a subport in a trunk, \&quot;segmentationId\&quot; shall be present. Otherwise it shall not be present. NOTE 4: Depending on the NFVI networking infrastructure, the \&quot;segmentationId\&quot; may indicate the actual network segment value (e.g. vlan Id, Vxlan segmentation id, etc.) used in the transport header of the packets or it may be an identifier used between the application and the NFVI networking infrastructure to identify the network sub-interface of the trunk port in question. In the latter case the NFVI infrastructure will map this local \&quot;segmentationId\&quot; to whatever \&quot;segmentationId\&quot; is actually used by the NFVI&#x27;s transport technology. 
 */
@Schema(description = "This type represents network address data for IP over Ethernet. NOTE 1: At least one of \"macAddress\" or \"ipAddresses\" shall be present. NOTE 2: Exactly one of \"fixedAddresses\", \"numDynamicAddresses\" or \"ipAddressRange\" shall be present. NOTE 3: If the CP instance represents a subport in a trunk, \"segmentationId\" shall be present. Otherwise it shall not be present. NOTE 4: Depending on the NFVI networking infrastructure, the \"segmentationId\" may indicate the actual network segment value (e.g. vlan Id, Vxlan segmentation id, etc.) used in the transport header of the packets or it may be an identifier used between the application and the NFVI networking infrastructure to identify the network sub-interface of the trunk port in question. In the latter case the NFVI infrastructure will map this local \"segmentationId\" to whatever \"segmentationId\" is actually used by the NFVI's transport technology. ")
@Validated


public class IpOverEthernetAddressData  implements AnyOfIpOverEthernetAddressData {
  @JsonProperty("macAddress")
  private String macAddress = null;

  /**
   * Specifies the encapsulation type for the traffics coming in and out of the trunk subport. Permitted values are: - VLAN: The subport uses VLAN as encapsulation type. - INHERIT: The subport gets its segmentation type from the network it is connected to. This attribute may be present for CP instances that represent subports in a trunk and shall be absent otherwise. If this attribute is not present for a subport CP instance, default value VLAN shall be used. 
   */
  public enum SegmentationTypeEnum {
    VLAN("VLAN"),
    
    INHERIT("INHERIT");

    private String value;

    SegmentationTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static SegmentationTypeEnum fromValue(String text) {
      for (SegmentationTypeEnum b : SegmentationTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("segmentationType")
  private SegmentationTypeEnum segmentationType = null;

  @JsonProperty("segmentationId")
  private String segmentationId = null;

  @JsonProperty("ipAddresses")
  @Valid
  private List<IpOverEthernetAddressDataIpAddresses> ipAddresses = null;

  public IpOverEthernetAddressData macAddress(String macAddress) {
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

  public IpOverEthernetAddressData segmentationType(SegmentationTypeEnum segmentationType) {
    this.segmentationType = segmentationType;
    return this;
  }

  /**
   * Specifies the encapsulation type for the traffics coming in and out of the trunk subport. Permitted values are: - VLAN: The subport uses VLAN as encapsulation type. - INHERIT: The subport gets its segmentation type from the network it is connected to. This attribute may be present for CP instances that represent subports in a trunk and shall be absent otherwise. If this attribute is not present for a subport CP instance, default value VLAN shall be used. 
   * @return segmentationType
   **/
  @Schema(description = "Specifies the encapsulation type for the traffics coming in and out of the trunk subport. Permitted values are: - VLAN: The subport uses VLAN as encapsulation type. - INHERIT: The subport gets its segmentation type from the network it is connected to. This attribute may be present for CP instances that represent subports in a trunk and shall be absent otherwise. If this attribute is not present for a subport CP instance, default value VLAN shall be used. ")
  
    public SegmentationTypeEnum getSegmentationType() {
    return segmentationType;
  }

  public void setSegmentationType(SegmentationTypeEnum segmentationType) {
    this.segmentationType = segmentationType;
  }

  public IpOverEthernetAddressData segmentationId(String segmentationId) {
    this.segmentationId = segmentationId;
    return this;
  }

  /**
   * Identification of the network segment to which the CP instance connects to. See note 3 and note 4. 
   * @return segmentationId
   **/
  @Schema(description = "Identification of the network segment to which the CP instance connects to. See note 3 and note 4. ")
  
    public String getSegmentationId() {
    return segmentationId;
  }

  public void setSegmentationId(String segmentationId) {
    this.segmentationId = segmentationId;
  }

  public IpOverEthernetAddressData ipAddresses(List<IpOverEthernetAddressDataIpAddresses> ipAddresses) {
    this.ipAddresses = ipAddresses;
    return this;
  }

  public IpOverEthernetAddressData addIpAddressesItem(IpOverEthernetAddressDataIpAddresses ipAddressesItem) {
    if (this.ipAddresses == null) {
      this.ipAddresses = new ArrayList<>();
    }
    this.ipAddresses.add(ipAddressesItem);
    return this;
  }

  /**
   * List of IP addresses to assign to the CP instance. Each entry represents IP address data for fixed or dynamic IP address assignment per subnet. If this attribute is not present, no IP address shall be assigned. See note 1. 
   * @return ipAddresses
   **/
  @Schema(description = "List of IP addresses to assign to the CP instance. Each entry represents IP address data for fixed or dynamic IP address assignment per subnet. If this attribute is not present, no IP address shall be assigned. See note 1. ")
      @Valid
    public List<IpOverEthernetAddressDataIpAddresses> getIpAddresses() {
    return ipAddresses;
  }

  public void setIpAddresses(List<IpOverEthernetAddressDataIpAddresses> ipAddresses) {
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
    IpOverEthernetAddressData ipOverEthernetAddressData = (IpOverEthernetAddressData) o;
    return Objects.equals(this.macAddress, ipOverEthernetAddressData.macAddress) &&
        Objects.equals(this.segmentationType, ipOverEthernetAddressData.segmentationType) &&
        Objects.equals(this.segmentationId, ipOverEthernetAddressData.segmentationId) &&
        Objects.equals(this.ipAddresses, ipOverEthernetAddressData.ipAddresses);
  }

  @Override
  public int hashCode() {
    return Objects.hash(macAddress, segmentationType, segmentationId, ipAddresses);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IpOverEthernetAddressData {\n");
    
    sb.append("    macAddress: ").append(toIndentedString(macAddress)).append("\n");
    sb.append("    segmentationType: ").append(toIndentedString(segmentationType)).append("\n");
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
