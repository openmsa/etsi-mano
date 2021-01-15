package com.ubiqube.etsi.mec.meo.v211.model.pkg;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * TrafficFilter
 */
@Validated
public class TrafficFilter   {
  @JsonProperty("dSCP")
  private Integer dSCP = null;

  @JsonProperty("dstAddress")
  @Valid
  private List<String> dstAddress = null;

  @JsonProperty("dstPort")
  @Valid
  private List<String> dstPort = null;

  @JsonProperty("dstTunnelPort")
  @Valid
  private List<String> dstTunnelPort = null;

  @JsonProperty("protocol")
  @Valid
  private List<String> protocol = null;

  @JsonProperty("qCI")
  private Integer qCI = null;

  @JsonProperty("srcAddress")
  @Valid
  private List<String> srcAddress = null;

  @JsonProperty("srcPort")
  @Valid
  private List<String> srcPort = null;

  @JsonProperty("srcTunnelAddress")
  @Valid
  private List<String> srcTunnelAddress = null;

  @JsonProperty("srcTunnelPort")
  @Valid
  private List<String> srcTunnelPort = null;

  @JsonProperty("tC")
  private Integer tC = null;

  @JsonProperty("tag")
  @Valid
  private List<String> tag = null;

  @JsonProperty("tgtTunnelAddress")
  @Valid
  private List<String> tgtTunnelAddress = null;

  public TrafficFilter dSCP(Integer dSCP) {
    this.dSCP = dSCP;
    return this;
  }

  /**
   * Used to match all IPv4 packets that have the same DSCP.
   * @return dSCP
  **/
  @ApiModelProperty(value = "Used to match all IPv4 packets that have the same DSCP.")
  
    public Integer getDSCP() {
    return dSCP;
  }

  public void setDSCP(Integer dSCP) {
    this.dSCP = dSCP;
  }

  public TrafficFilter dstAddress(List<String> dstAddress) {
    this.dstAddress = dstAddress;
    return this;
  }

  public TrafficFilter addDstAddressItem(String dstAddressItem) {
    if (this.dstAddress == null) {
      this.dstAddress = new ArrayList<>();
    }
    this.dstAddress.add(dstAddressItem);
    return this;
  }

  /**
   * A IP address or a range of IP addresses.For IPv4, the IP address could be an IP address plus mask, or an individual IP address, or a range of IP addresses.For IPv6, the IP address could be an IP prefix, or a range of IP prefixes.
   * @return dstAddress
  **/
  @ApiModelProperty(value = "A IP address or a range of IP addresses.For IPv4, the IP address could be an IP address plus mask, or an individual IP address, or a range of IP addresses.For IPv6, the IP address could be an IP prefix, or a range of IP prefixes.")
  
    public List<String> getDstAddress() {
    return dstAddress;
  }

  public void setDstAddress(List<String> dstAddress) {
    this.dstAddress = dstAddress;
  }

  public TrafficFilter dstPort(List<String> dstPort) {
    this.dstPort = dstPort;
    return this;
  }

  public TrafficFilter addDstPortItem(String dstPortItem) {
    if (this.dstPort == null) {
      this.dstPort = new ArrayList<>();
    }
    this.dstPort.add(dstPortItem);
    return this;
  }

  /**
   * A port or a range of ports.
   * @return dstPort
  **/
  @ApiModelProperty(value = "A port or a range of ports.")
  
    public List<String> getDstPort() {
    return dstPort;
  }

  public void setDstPort(List<String> dstPort) {
    this.dstPort = dstPort;
  }

  public TrafficFilter dstTunnelPort(List<String> dstTunnelPort) {
    this.dstTunnelPort = dstTunnelPort;
    return this;
  }

  public TrafficFilter addDstTunnelPortItem(String dstTunnelPortItem) {
    if (this.dstTunnelPort == null) {
      this.dstTunnelPort = new ArrayList<>();
    }
    this.dstTunnelPort.add(dstTunnelPortItem);
    return this;
  }

  /**
   * Used for GTP tunnel based traffic rule.
   * @return dstTunnelPort
  **/
  @ApiModelProperty(value = "Used for GTP tunnel based traffic rule.")
  
    public List<String> getDstTunnelPort() {
    return dstTunnelPort;
  }

  public void setDstTunnelPort(List<String> dstTunnelPort) {
    this.dstTunnelPort = dstTunnelPort;
  }

  public TrafficFilter protocol(List<String> protocol) {
    this.protocol = protocol;
    return this;
  }

  public TrafficFilter addProtocolItem(String protocolItem) {
    if (this.protocol == null) {
      this.protocol = new ArrayList<>();
    }
    this.protocol.add(protocolItem);
    return this;
  }

  /**
   * Specify the protocol of the traffic filter.
   * @return protocol
  **/
  @ApiModelProperty(value = "Specify the protocol of the traffic filter.")
  
    public List<String> getProtocol() {
    return protocol;
  }

  public void setProtocol(List<String> protocol) {
    this.protocol = protocol;
  }

  public TrafficFilter qCI(Integer qCI) {
    this.qCI = qCI;
    return this;
  }

  /**
   * Used to match all packets that have the same QCI.
   * @return qCI
  **/
  @ApiModelProperty(value = "Used to match all packets that have the same QCI.")
  
    public Integer getQCI() {
    return qCI;
  }

  public void setQCI(Integer qCI) {
    this.qCI = qCI;
  }

  public TrafficFilter srcAddress(List<String> srcAddress) {
    this.srcAddress = srcAddress;
    return this;
  }

  public TrafficFilter addSrcAddressItem(String srcAddressItem) {
    if (this.srcAddress == null) {
      this.srcAddress = new ArrayList<>();
    }
    this.srcAddress.add(srcAddressItem);
    return this;
  }

  /**
   * An IP address or a range of IP addresses.For IPv4, the IP address could be an IP address plus mask, or an individual IP address, or a range of IP addresses.For IPv6, the IP address could be an IP prefix, or a range of IP prefixes.
   * @return srcAddress
  **/
  @ApiModelProperty(value = "An IP address or a range of IP addresses.For IPv4, the IP address could be an IP address plus mask, or an individual IP address, or a range of IP addresses.For IPv6, the IP address could be an IP prefix, or a range of IP prefixes.")
  
    public List<String> getSrcAddress() {
    return srcAddress;
  }

  public void setSrcAddress(List<String> srcAddress) {
    this.srcAddress = srcAddress;
  }

  public TrafficFilter srcPort(List<String> srcPort) {
    this.srcPort = srcPort;
    return this;
  }

  public TrafficFilter addSrcPortItem(String srcPortItem) {
    if (this.srcPort == null) {
      this.srcPort = new ArrayList<>();
    }
    this.srcPort.add(srcPortItem);
    return this;
  }

  /**
   * A port or a range of ports.
   * @return srcPort
  **/
  @ApiModelProperty(value = "A port or a range of ports.")
  
    public List<String> getSrcPort() {
    return srcPort;
  }

  public void setSrcPort(List<String> srcPort) {
    this.srcPort = srcPort;
  }

  public TrafficFilter srcTunnelAddress(List<String> srcTunnelAddress) {
    this.srcTunnelAddress = srcTunnelAddress;
    return this;
  }

  public TrafficFilter addSrcTunnelAddressItem(String srcTunnelAddressItem) {
    if (this.srcTunnelAddress == null) {
      this.srcTunnelAddress = new ArrayList<>();
    }
    this.srcTunnelAddress.add(srcTunnelAddressItem);
    return this;
  }

  /**
   * Used for GTP tunnel based traffic rule.
   * @return srcTunnelAddress
  **/
  @ApiModelProperty(value = "Used for GTP tunnel based traffic rule.")
  
    public List<String> getSrcTunnelAddress() {
    return srcTunnelAddress;
  }

  public void setSrcTunnelAddress(List<String> srcTunnelAddress) {
    this.srcTunnelAddress = srcTunnelAddress;
  }

  public TrafficFilter srcTunnelPort(List<String> srcTunnelPort) {
    this.srcTunnelPort = srcTunnelPort;
    return this;
  }

  public TrafficFilter addSrcTunnelPortItem(String srcTunnelPortItem) {
    if (this.srcTunnelPort == null) {
      this.srcTunnelPort = new ArrayList<>();
    }
    this.srcTunnelPort.add(srcTunnelPortItem);
    return this;
  }

  /**
   * Used for GTP tunnel based traffic rule.
   * @return srcTunnelPort
  **/
  @ApiModelProperty(value = "Used for GTP tunnel based traffic rule.")
  
    public List<String> getSrcTunnelPort() {
    return srcTunnelPort;
  }

  public void setSrcTunnelPort(List<String> srcTunnelPort) {
    this.srcTunnelPort = srcTunnelPort;
  }

  public TrafficFilter tC(Integer tC) {
    this.tC = tC;
    return this;
  }

  /**
   * Used to match all IPv6 packets that have the same TC.
   * @return tC
  **/
  @ApiModelProperty(value = "Used to match all IPv6 packets that have the same TC.")
  
    public Integer getTC() {
    return tC;
  }

  public void setTC(Integer tC) {
    this.tC = tC;
  }

  public TrafficFilter tag(List<String> tag) {
    this.tag = tag;
    return this;
  }

  public TrafficFilter addTagItem(String tagItem) {
    if (this.tag == null) {
      this.tag = new ArrayList<>();
    }
    this.tag.add(tagItem);
    return this;
  }

  /**
   * Used for tag based traffic rule.
   * @return tag
  **/
  @ApiModelProperty(value = "Used for tag based traffic rule.")
  
    public List<String> getTag() {
    return tag;
  }

  public void setTag(List<String> tag) {
    this.tag = tag;
  }

  public TrafficFilter tgtTunnelAddress(List<String> tgtTunnelAddress) {
    this.tgtTunnelAddress = tgtTunnelAddress;
    return this;
  }

  public TrafficFilter addTgtTunnelAddressItem(String tgtTunnelAddressItem) {
    if (this.tgtTunnelAddress == null) {
      this.tgtTunnelAddress = new ArrayList<>();
    }
    this.tgtTunnelAddress.add(tgtTunnelAddressItem);
    return this;
  }

  /**
   * Used for GTP tunnel based traffic rule.
   * @return tgtTunnelAddress
  **/
  @ApiModelProperty(value = "Used for GTP tunnel based traffic rule.")
  
    public List<String> getTgtTunnelAddress() {
    return tgtTunnelAddress;
  }

  public void setTgtTunnelAddress(List<String> tgtTunnelAddress) {
    this.tgtTunnelAddress = tgtTunnelAddress;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TrafficFilter trafficFilter = (TrafficFilter) o;
    return Objects.equals(this.dSCP, trafficFilter.dSCP) &&
        Objects.equals(this.dstAddress, trafficFilter.dstAddress) &&
        Objects.equals(this.dstPort, trafficFilter.dstPort) &&
        Objects.equals(this.dstTunnelPort, trafficFilter.dstTunnelPort) &&
        Objects.equals(this.protocol, trafficFilter.protocol) &&
        Objects.equals(this.qCI, trafficFilter.qCI) &&
        Objects.equals(this.srcAddress, trafficFilter.srcAddress) &&
        Objects.equals(this.srcPort, trafficFilter.srcPort) &&
        Objects.equals(this.srcTunnelAddress, trafficFilter.srcTunnelAddress) &&
        Objects.equals(this.srcTunnelPort, trafficFilter.srcTunnelPort) &&
        Objects.equals(this.tC, trafficFilter.tC) &&
        Objects.equals(this.tag, trafficFilter.tag) &&
        Objects.equals(this.tgtTunnelAddress, trafficFilter.tgtTunnelAddress);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dSCP, dstAddress, dstPort, dstTunnelPort, protocol, qCI, srcAddress, srcPort, srcTunnelAddress, srcTunnelPort, tC, tag, tgtTunnelAddress);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TrafficFilter {\n");
    
    sb.append("    dSCP: ").append(toIndentedString(dSCP)).append("\n");
    sb.append("    dstAddress: ").append(toIndentedString(dstAddress)).append("\n");
    sb.append("    dstPort: ").append(toIndentedString(dstPort)).append("\n");
    sb.append("    dstTunnelPort: ").append(toIndentedString(dstTunnelPort)).append("\n");
    sb.append("    protocol: ").append(toIndentedString(protocol)).append("\n");
    sb.append("    qCI: ").append(toIndentedString(qCI)).append("\n");
    sb.append("    srcAddress: ").append(toIndentedString(srcAddress)).append("\n");
    sb.append("    srcPort: ").append(toIndentedString(srcPort)).append("\n");
    sb.append("    srcTunnelAddress: ").append(toIndentedString(srcTunnelAddress)).append("\n");
    sb.append("    srcTunnelPort: ").append(toIndentedString(srcTunnelPort)).append("\n");
    sb.append("    tC: ").append(toIndentedString(tC)).append("\n");
    sb.append("    tag: ").append(toIndentedString(tag)).append("\n");
    sb.append("    tgtTunnelAddress: ").append(toIndentedString(tgtTunnelAddress)).append("\n");
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
