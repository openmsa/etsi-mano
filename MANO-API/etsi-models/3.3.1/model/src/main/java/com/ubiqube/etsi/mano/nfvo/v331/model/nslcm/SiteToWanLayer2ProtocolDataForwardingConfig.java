package com.ubiqube.etsi.mano.nfvo.v331.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.ResourceHandle;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.SiteToWanLayer2ProtocolDataForwardingConfigVnSegmentIds;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Information related to the forwarding of the VN in the NFVI-PoP to the connectivity service endpoint, if information about the VN to \&quot;stitch\&quot; is already known. . by the OSS/BSS. Shall not be provided otherwise, in which case the NFVO will infer the forwarding configuration based on the NS VL, or external VL, or externally-managed VL provisioning. 
 */
@Schema(description = "Information related to the forwarding of the VN in the NFVI-PoP to the connectivity service endpoint, if information about the VN to \"stitch\" is already known. . by the OSS/BSS. Shall not be provided otherwise, in which case the NFVO will infer the forwarding configuration based on the NS VL, or external VL, or externally-managed VL provisioning. ")
@Validated


public class SiteToWanLayer2ProtocolDataForwardingConfig   {
  @JsonProperty("networkResources")
  @Valid
  private List<ResourceHandle> networkResources = null;

  @JsonProperty("vnSegmentIds")
  private SiteToWanLayer2ProtocolDataForwardingConfigVnSegmentIds vnSegmentIds = null;

  public SiteToWanLayer2ProtocolDataForwardingConfig networkResources(List<ResourceHandle> networkResources) {
    this.networkResources = networkResources;
    return this;
  }

  public SiteToWanLayer2ProtocolDataForwardingConfig addNetworkResourcesItem(ResourceHandle networkResourcesItem) {
    if (this.networkResources == null) {
      this.networkResources = new ArrayList<>();
    }
    this.networkResources.add(networkResourcesItem);
    return this;
  }

  /**
   * Reference to the VN resource to be forwarded into/from the MSCS. Either \"networkResources\" or \"vnSegmentsIds\" shall be provided, but not both. 
   * @return networkResources
   **/
  @Schema(description = "Reference to the VN resource to be forwarded into/from the MSCS. Either \"networkResources\" or \"vnSegmentsIds\" shall be provided, but not both. ")
      @Valid
    public List<ResourceHandle> getNetworkResources() {
    return networkResources;
  }

  public void setNetworkResources(List<ResourceHandle> networkResources) {
    this.networkResources = networkResources;
  }

  public SiteToWanLayer2ProtocolDataForwardingConfig vnSegmentIds(SiteToWanLayer2ProtocolDataForwardingConfigVnSegmentIds vnSegmentIds) {
    this.vnSegmentIds = vnSegmentIds;
    return this;
  }

  /**
   * Get vnSegmentIds
   * @return vnSegmentIds
   **/
  @Schema(description = "")
  
    @Valid
    public SiteToWanLayer2ProtocolDataForwardingConfigVnSegmentIds getVnSegmentIds() {
    return vnSegmentIds;
  }

  public void setVnSegmentIds(SiteToWanLayer2ProtocolDataForwardingConfigVnSegmentIds vnSegmentIds) {
    this.vnSegmentIds = vnSegmentIds;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SiteToWanLayer2ProtocolDataForwardingConfig siteToWanLayer2ProtocolDataForwardingConfig = (SiteToWanLayer2ProtocolDataForwardingConfig) o;
    return Objects.equals(this.networkResources, siteToWanLayer2ProtocolDataForwardingConfig.networkResources) &&
        Objects.equals(this.vnSegmentIds, siteToWanLayer2ProtocolDataForwardingConfig.vnSegmentIds);
  }

  @Override
  public int hashCode() {
    return Objects.hash(networkResources, vnSegmentIds);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SiteToWanLayer2ProtocolDataForwardingConfig {\n");
    
    sb.append("    networkResources: ").append(toIndentedString(networkResources)).append("\n");
    sb.append("    vnSegmentIds: ").append(toIndentedString(vnSegmentIds)).append("\n");
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
