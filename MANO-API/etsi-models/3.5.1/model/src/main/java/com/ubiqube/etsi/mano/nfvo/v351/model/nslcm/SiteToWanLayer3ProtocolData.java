package com.ubiqube.etsi.mano.nfvo.v351.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.KeyValuePairs;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.SiteToWanLayer3ProtocolDataLogicalInterfaceIpAddress;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.SiteToWanLayer3ProtocolDataRoutingInfo;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.SiteToWanLayer3ProtocolDataVirtualRoutingAndForwarding;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type provides information about Layer 3 protocol specific information for the stitching of the intra-site VN to the multi-site connectivity service over the WAN. It shall comply with the provisions defined in Table 6.5.3.86-1. 
 */
@Schema(description = "This type provides information about Layer 3 protocol specific information for the stitching of the intra-site VN to the multi-site connectivity service over the WAN. It shall comply with the provisions defined in Table 6.5.3.86-1. ")
@Validated


public class SiteToWanLayer3ProtocolData   {
  @JsonProperty("logicalInterfaceIpAddress")
  private SiteToWanLayer3ProtocolDataLogicalInterfaceIpAddress logicalInterfaceIpAddress = null;

  @JsonProperty("routingInfo")
  private SiteToWanLayer3ProtocolDataRoutingInfo routingInfo = null;

  @JsonProperty("mtuL3")
  private BigDecimal mtuL3 = null;

  @JsonProperty("virtualRoutingAndForwarding")
  private SiteToWanLayer3ProtocolDataVirtualRoutingAndForwarding virtualRoutingAndForwarding = null;

  @JsonProperty("bfdConfig")
  private KeyValuePairs bfdConfig = null;

  public SiteToWanLayer3ProtocolData logicalInterfaceIpAddress(SiteToWanLayer3ProtocolDataLogicalInterfaceIpAddress logicalInterfaceIpAddress) {
    this.logicalInterfaceIpAddress = logicalInterfaceIpAddress;
    return this;
  }

  /**
   * Get logicalInterfaceIpAddress
   * @return logicalInterfaceIpAddress
   **/
  @Schema(description = "")
  
    @Valid
    public SiteToWanLayer3ProtocolDataLogicalInterfaceIpAddress getLogicalInterfaceIpAddress() {
    return logicalInterfaceIpAddress;
  }

  public void setLogicalInterfaceIpAddress(SiteToWanLayer3ProtocolDataLogicalInterfaceIpAddress logicalInterfaceIpAddress) {
    this.logicalInterfaceIpAddress = logicalInterfaceIpAddress;
  }

  public SiteToWanLayer3ProtocolData routingInfo(SiteToWanLayer3ProtocolDataRoutingInfo routingInfo) {
    this.routingInfo = routingInfo;
    return this;
  }

  /**
   * Get routingInfo
   * @return routingInfo
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public SiteToWanLayer3ProtocolDataRoutingInfo getRoutingInfo() {
    return routingInfo;
  }

  public void setRoutingInfo(SiteToWanLayer3ProtocolDataRoutingInfo routingInfo) {
    this.routingInfo = routingInfo;
  }

  public SiteToWanLayer3ProtocolData mtuL3(BigDecimal mtuL3) {
    this.mtuL3 = mtuL3;
    return this;
  }

  /**
   * Maximum Transmission Unit (MTU) that can be forwarded at layer 3 (in bytes). Default value is \"1500\" (bytes). 
   * @return mtuL3
   **/
  @Schema(description = "Maximum Transmission Unit (MTU) that can be forwarded at layer 3 (in bytes). Default value is \"1500\" (bytes). ")
  
    @Valid
    public BigDecimal getMtuL3() {
    return mtuL3;
  }

  public void setMtuL3(BigDecimal mtuL3) {
    this.mtuL3 = mtuL3;
  }

  public SiteToWanLayer3ProtocolData virtualRoutingAndForwarding(SiteToWanLayer3ProtocolDataVirtualRoutingAndForwarding virtualRoutingAndForwarding) {
    this.virtualRoutingAndForwarding = virtualRoutingAndForwarding;
    return this;
  }

  /**
   * Get virtualRoutingAndForwarding
   * @return virtualRoutingAndForwarding
   **/
  @Schema(description = "")
  
    @Valid
    public SiteToWanLayer3ProtocolDataVirtualRoutingAndForwarding getVirtualRoutingAndForwarding() {
    return virtualRoutingAndForwarding;
  }

  public void setVirtualRoutingAndForwarding(SiteToWanLayer3ProtocolDataVirtualRoutingAndForwarding virtualRoutingAndForwarding) {
    this.virtualRoutingAndForwarding = virtualRoutingAndForwarding;
  }

  public SiteToWanLayer3ProtocolData bfdConfig(KeyValuePairs bfdConfig) {
    this.bfdConfig = bfdConfig;
    return this;
  }

  /**
   * Get bfdConfig
   * @return bfdConfig
   **/
  @Schema(description = "")
  
    @Valid
    public KeyValuePairs getBfdConfig() {
    return bfdConfig;
  }

  public void setBfdConfig(KeyValuePairs bfdConfig) {
    this.bfdConfig = bfdConfig;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SiteToWanLayer3ProtocolData siteToWanLayer3ProtocolData = (SiteToWanLayer3ProtocolData) o;
    return Objects.equals(this.logicalInterfaceIpAddress, siteToWanLayer3ProtocolData.logicalInterfaceIpAddress) &&
        Objects.equals(this.routingInfo, siteToWanLayer3ProtocolData.routingInfo) &&
        Objects.equals(this.mtuL3, siteToWanLayer3ProtocolData.mtuL3) &&
        Objects.equals(this.virtualRoutingAndForwarding, siteToWanLayer3ProtocolData.virtualRoutingAndForwarding) &&
        Objects.equals(this.bfdConfig, siteToWanLayer3ProtocolData.bfdConfig);
  }

  @Override
  public int hashCode() {
    return Objects.hash(logicalInterfaceIpAddress, routingInfo, mtuL3, virtualRoutingAndForwarding, bfdConfig);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SiteToWanLayer3ProtocolData {\n");
    
    sb.append("    logicalInterfaceIpAddress: ").append(toIndentedString(logicalInterfaceIpAddress)).append("\n");
    sb.append("    routingInfo: ").append(toIndentedString(routingInfo)).append("\n");
    sb.append("    mtuL3: ").append(toIndentedString(mtuL3)).append("\n");
    sb.append("    virtualRoutingAndForwarding: ").append(toIndentedString(virtualRoutingAndForwarding)).append("\n");
    sb.append("    bfdConfig: ").append(toIndentedString(bfdConfig)).append("\n");
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
