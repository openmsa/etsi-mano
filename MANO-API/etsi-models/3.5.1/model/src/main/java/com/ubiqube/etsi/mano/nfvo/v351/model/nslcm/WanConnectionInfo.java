package com.ubiqube.etsi.mano.nfvo.v351.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.WanConnectionProtocolInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type provides information about the connectivity to the WAN of network resources realizing a VL, e.g., when the VL is deployed on several sites across a WAN. It shall comply with the provisions defined in table 6.5.3.90-1. NOTE: Either a \&quot;nsVirtualLinkInfoId\&quot; or a \&quot;vnfVirtualLinkResourceInfoId\&quot; shall be provided, but not both. 
 */
@Schema(description = "This type provides information about the connectivity to the WAN of network resources realizing a VL, e.g., when the VL is deployed on several sites across a WAN. It shall comply with the provisions defined in table 6.5.3.90-1. NOTE: Either a \"nsVirtualLinkInfoId\" or a \"vnfVirtualLinkResourceInfoId\" shall be provided, but not both. ")
@Validated


public class WanConnectionInfo  implements OneOfWanConnectionInfo {
  @JsonProperty("wanConnectionInfoId")
  private String wanConnectionInfoId = null;

  @JsonProperty("nsVirtualLinkInfoId")
  private String nsVirtualLinkInfoId = null;

  @JsonProperty("vnfVirtualLinkResourceInfoId")
  private String vnfVirtualLinkResourceInfoId = null;

  @JsonProperty("protocolInfo")
  private WanConnectionProtocolInfo protocolInfo = null;

  public WanConnectionInfo wanConnectionInfoId(String wanConnectionInfoId) {
    this.wanConnectionInfoId = wanConnectionInfoId;
    return this;
  }

  /**
   * Get wanConnectionInfoId
   * @return wanConnectionInfoId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getWanConnectionInfoId() {
    return wanConnectionInfoId;
  }

  public void setWanConnectionInfoId(String wanConnectionInfoId) {
    this.wanConnectionInfoId = wanConnectionInfoId;
  }

  public WanConnectionInfo nsVirtualLinkInfoId(String nsVirtualLinkInfoId) {
    this.nsVirtualLinkInfoId = nsVirtualLinkInfoId;
    return this;
  }

  /**
   * Get nsVirtualLinkInfoId
   * @return nsVirtualLinkInfoId
   **/
  @Schema(description = "")
  
    public String getNsVirtualLinkInfoId() {
    return nsVirtualLinkInfoId;
  }

  public void setNsVirtualLinkInfoId(String nsVirtualLinkInfoId) {
    this.nsVirtualLinkInfoId = nsVirtualLinkInfoId;
  }

  public WanConnectionInfo vnfVirtualLinkResourceInfoId(String vnfVirtualLinkResourceInfoId) {
    this.vnfVirtualLinkResourceInfoId = vnfVirtualLinkResourceInfoId;
    return this;
  }

  /**
   * Get vnfVirtualLinkResourceInfoId
   * @return vnfVirtualLinkResourceInfoId
   **/
  @Schema(description = "")
  
    public String getVnfVirtualLinkResourceInfoId() {
    return vnfVirtualLinkResourceInfoId;
  }

  public void setVnfVirtualLinkResourceInfoId(String vnfVirtualLinkResourceInfoId) {
    this.vnfVirtualLinkResourceInfoId = vnfVirtualLinkResourceInfoId;
  }

  public WanConnectionInfo protocolInfo(WanConnectionProtocolInfo protocolInfo) {
    this.protocolInfo = protocolInfo;
    return this;
  }

  /**
   * Get protocolInfo
   * @return protocolInfo
   **/
  @Schema(description = "")
  
    @Valid
    public WanConnectionProtocolInfo getProtocolInfo() {
    return protocolInfo;
  }

  public void setProtocolInfo(WanConnectionProtocolInfo protocolInfo) {
    this.protocolInfo = protocolInfo;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WanConnectionInfo wanConnectionInfo = (WanConnectionInfo) o;
    return Objects.equals(this.wanConnectionInfoId, wanConnectionInfo.wanConnectionInfoId) &&
        Objects.equals(this.nsVirtualLinkInfoId, wanConnectionInfo.nsVirtualLinkInfoId) &&
        Objects.equals(this.vnfVirtualLinkResourceInfoId, wanConnectionInfo.vnfVirtualLinkResourceInfoId) &&
        Objects.equals(this.protocolInfo, wanConnectionInfo.protocolInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(wanConnectionInfoId, nsVirtualLinkInfoId, vnfVirtualLinkResourceInfoId, protocolInfo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WanConnectionInfo {\n");
    
    sb.append("    wanConnectionInfoId: ").append(toIndentedString(wanConnectionInfoId)).append("\n");
    sb.append("    nsVirtualLinkInfoId: ").append(toIndentedString(nsVirtualLinkInfoId)).append("\n");
    sb.append("    vnfVirtualLinkResourceInfoId: ").append(toIndentedString(vnfVirtualLinkResourceInfoId)).append("\n");
    sb.append("    protocolInfo: ").append(toIndentedString(protocolInfo)).append("\n");
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
