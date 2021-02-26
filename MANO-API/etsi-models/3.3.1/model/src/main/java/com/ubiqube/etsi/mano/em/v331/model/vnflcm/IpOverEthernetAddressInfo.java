package com.ubiqube.etsi.mano.em.v331.model.vnflcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.em.v331.model.vnflcm.IpOverEthernetAddressInfoIpAddresses;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents information about a network address that has been assigned. 
 */
@Schema(description = "This type represents information about a network address that has been assigned. ")
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
   * Identification of the network segment to which the Cp instance connects to. If the Cp instance represents a subport in a trunk, segmentationId shall be present.  Otherwise it shall not be present. Depending on the NFVI networking infrastructure, the segmentationId may indicate the actual network segment value (e.g. vlan Id, Vxlan segmentation id, etc.) used in the transport header of the packets or it may be an identifier used between the application and the NFVI networking infrastructure to identify the network sub-interface of the trunk port in question. In the latter case the NFVI infrastructure will map this local segmentationId to whatever segmentationId is actually used by the NFVI’s transport technology. 
   * @return segmentationId
   **/
  @Schema(description = "Identification of the network segment to which the Cp instance connects to. If the Cp instance represents a subport in a trunk, segmentationId shall be present.  Otherwise it shall not be present. Depending on the NFVI networking infrastructure, the segmentationId may indicate the actual network segment value (e.g. vlan Id, Vxlan segmentation id, etc.) used in the transport header of the packets or it may be an identifier used between the application and the NFVI networking infrastructure to identify the network sub-interface of the trunk port in question. In the latter case the NFVI infrastructure will map this local segmentationId to whatever segmentationId is actually used by the NFVI’s transport technology. ")
  
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
   * Addresses assigned to the CP instance. Each entry represents IP addresses assigned by fixed or dynamic IP address assignment per subnet. At least one of \"macAddress\" or \"ipAddresses\" shall be present. 
   * @return ipAddresses
   **/
  @Schema(description = "Addresses assigned to the CP instance. Each entry represents IP addresses assigned by fixed or dynamic IP address assignment per subnet. At least one of \"macAddress\" or \"ipAddresses\" shall be present. ")
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
