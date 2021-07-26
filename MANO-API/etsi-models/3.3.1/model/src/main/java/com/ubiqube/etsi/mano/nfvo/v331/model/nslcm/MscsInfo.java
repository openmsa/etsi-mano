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
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.MscsEndpointInfo;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.MscsInfoSiteAccessProtectionSchemes;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type provides information about an already provisioned multi-site connectivity service (MSCS) deployed across a WAN. It shall comply with the provisions defined in Table 6.5.3.82-1. 
 */
@Schema(description = "This type provides information about an already provisioned multi-site connectivity service (MSCS) deployed across a WAN. It shall comply with the provisions defined in Table 6.5.3.82-1. ")
@Validated


public class MscsInfo   {
  @JsonProperty("mscsId")
  private String mscsId = null;

  @JsonProperty("mscsName")
  private String mscsName = null;

  @JsonProperty("mscsDescription")
  private String mscsDescription = null;

  /**
   * The type of connectivity that is provided to the virtualized networks in the NFVI-PoP and characterizes the connectivity service across the WAN. Permitted values: - L2 - L3 
   */
  public enum MscsTypeEnum {
    L2VPN("L2VPN"),
    
    L3VPN("L3VPN");

    private String value;

    MscsTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static MscsTypeEnum fromValue(String text) {
      for (MscsTypeEnum b : MscsTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("mscsType")
  private MscsTypeEnum mscsType = null;

  /**
   * Type of underlying connectivity service and protocol associated to the MSCS. Permitted values are as listed below and restricted by the type of MSCS: - EVPN_BGP_MPLS: as specified in IETF RFC 7432. Only applicable for mscsType=\"L2\". - EVPN_VPWS: as specified in IETF RFC 8214. Only applicable for mscsType=\"L2\". - VPLS_BGP: as specified in IETF RFC 4761 and IETF RFC 6624. Only applicable for mscsType=\"L2\". - VPLS_LDP_L2TP: as specified in IETF RFC 4762. Only applicable for mscsType=\"L2\". - VPWS_LDP_L2TP: as specified in IETF RFC 6074. Only applicable for mscsType=\"L2\". - BGP_IP_VPN: BGP/MPLS based IP VPN as specified in IETF RFC 4364. Only applicable for mscsType=\"L3\". 
   */
  public enum MscsLayerProtocolEnum {
    EVPN_BGP_MPLS("EVPN_BGP_MPLS"),
    
    EVPN_VPWS("EVPN_VPWS"),
    
    VPLS_BGP("VPLS_BGP"),
    
    VPLS_LDP("VPLS_LDP"),
    
    VPWS("VPWS"),
    
    BGP_IP_VPN("BGP_IP_VPN");

    private String value;

    MscsLayerProtocolEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static MscsLayerProtocolEnum fromValue(String text) {
      for (MscsLayerProtocolEnum b : MscsLayerProtocolEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("mscsLayerProtocol")
  private MscsLayerProtocolEnum mscsLayerProtocol = null;

  @JsonProperty("siteAccessProtectionSchemes")
  @Valid
  private List<MscsInfoSiteAccessProtectionSchemes> siteAccessProtectionSchemes = null;

  @JsonProperty("mtuMscs")
  private BigDecimal mtuMscs = null;

  @JsonProperty("mscsEndpoints")
  @Valid
  private List<MscsEndpointInfo> mscsEndpoints = null;

  public MscsInfo mscsId(String mscsId) {
    this.mscsId = mscsId;
    return this;
  }

  /**
   * Get mscsId
   * @return mscsId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getMscsId() {
    return mscsId;
  }

  public void setMscsId(String mscsId) {
    this.mscsId = mscsId;
  }

  public MscsInfo mscsName(String mscsName) {
    this.mscsName = mscsName;
    return this;
  }

  /**
   * Human readable name of the MSCS. 
   * @return mscsName
   **/
  @Schema(description = "Human readable name of the MSCS. ")
  
    public String getMscsName() {
    return mscsName;
  }

  public void setMscsName(String mscsName) {
    this.mscsName = mscsName;
  }

  public MscsInfo mscsDescription(String mscsDescription) {
    this.mscsDescription = mscsDescription;
    return this;
  }

  /**
   * Human readable description of the MSCS. 
   * @return mscsDescription
   **/
  @Schema(description = "Human readable description of the MSCS. ")
  
    public String getMscsDescription() {
    return mscsDescription;
  }

  public void setMscsDescription(String mscsDescription) {
    this.mscsDescription = mscsDescription;
  }

  public MscsInfo mscsType(MscsTypeEnum mscsType) {
    this.mscsType = mscsType;
    return this;
  }

  /**
   * The type of connectivity that is provided to the virtualized networks in the NFVI-PoP and characterizes the connectivity service across the WAN. Permitted values: - L2 - L3 
   * @return mscsType
   **/
  @Schema(required = true, description = "The type of connectivity that is provided to the virtualized networks in the NFVI-PoP and characterizes the connectivity service across the WAN. Permitted values: - L2 - L3 ")
      @NotNull

    public MscsTypeEnum getMscsType() {
    return mscsType;
  }

  public void setMscsType(MscsTypeEnum mscsType) {
    this.mscsType = mscsType;
  }

  public MscsInfo mscsLayerProtocol(MscsLayerProtocolEnum mscsLayerProtocol) {
    this.mscsLayerProtocol = mscsLayerProtocol;
    return this;
  }

  /**
   * Type of underlying connectivity service and protocol associated to the MSCS. Permitted values are as listed below and restricted by the type of MSCS: - EVPN_BGP_MPLS: as specified in IETF RFC 7432. Only applicable for mscsType=\"L2\". - EVPN_VPWS: as specified in IETF RFC 8214. Only applicable for mscsType=\"L2\". - VPLS_BGP: as specified in IETF RFC 4761 and IETF RFC 6624. Only applicable for mscsType=\"L2\". - VPLS_LDP_L2TP: as specified in IETF RFC 4762. Only applicable for mscsType=\"L2\". - VPWS_LDP_L2TP: as specified in IETF RFC 6074. Only applicable for mscsType=\"L2\". - BGP_IP_VPN: BGP/MPLS based IP VPN as specified in IETF RFC 4364. Only applicable for mscsType=\"L3\". 
   * @return mscsLayerProtocol
   **/
  @Schema(description = "Type of underlying connectivity service and protocol associated to the MSCS. Permitted values are as listed below and restricted by the type of MSCS: - EVPN_BGP_MPLS: as specified in IETF RFC 7432. Only applicable for mscsType=\"L2\". - EVPN_VPWS: as specified in IETF RFC 8214. Only applicable for mscsType=\"L2\". - VPLS_BGP: as specified in IETF RFC 4761 and IETF RFC 6624. Only applicable for mscsType=\"L2\". - VPLS_LDP_L2TP: as specified in IETF RFC 4762. Only applicable for mscsType=\"L2\". - VPWS_LDP_L2TP: as specified in IETF RFC 6074. Only applicable for mscsType=\"L2\". - BGP_IP_VPN: BGP/MPLS based IP VPN as specified in IETF RFC 4364. Only applicable for mscsType=\"L3\". ")
  
    public MscsLayerProtocolEnum getMscsLayerProtocol() {
    return mscsLayerProtocol;
  }

  public void setMscsLayerProtocol(MscsLayerProtocolEnum mscsLayerProtocol) {
    this.mscsLayerProtocol = mscsLayerProtocol;
  }

  public MscsInfo siteAccessProtectionSchemes(List<MscsInfoSiteAccessProtectionSchemes> siteAccessProtectionSchemes) {
    this.siteAccessProtectionSchemes = siteAccessProtectionSchemes;
    return this;
  }

  public MscsInfo addSiteAccessProtectionSchemesItem(MscsInfoSiteAccessProtectionSchemes siteAccessProtectionSchemesItem) {
    if (this.siteAccessProtectionSchemes == null) {
      this.siteAccessProtectionSchemes = new ArrayList<>();
    }
    this.siteAccessProtectionSchemes.add(siteAccessProtectionSchemesItem);
    return this;
  }

  /**
   * Information to determine the proper MSCS endpoints configuration to fulfil certain resiliency/protection requirements, e.g., by considering certain availability and redundancy of connectivity service endpoints in between NFVI-PoP and WAN. 
   * @return siteAccessProtectionSchemes
   **/
  @Schema(description = "Information to determine the proper MSCS endpoints configuration to fulfil certain resiliency/protection requirements, e.g., by considering certain availability and redundancy of connectivity service endpoints in between NFVI-PoP and WAN. ")
      @Valid
    public List<MscsInfoSiteAccessProtectionSchemes> getSiteAccessProtectionSchemes() {
    return siteAccessProtectionSchemes;
  }

  public void setSiteAccessProtectionSchemes(List<MscsInfoSiteAccessProtectionSchemes> siteAccessProtectionSchemes) {
    this.siteAccessProtectionSchemes = siteAccessProtectionSchemes;
  }

  public MscsInfo mtuMscs(BigDecimal mtuMscs) {
    this.mtuMscs = mtuMscs;
    return this;
  }

  /**
   * Maximum Transmission Unit (MTU) that can be forwarded over the MSCS (in bytes). Default value is \"1500\" (bytes). 
   * @return mtuMscs
   **/
  @Schema(description = "Maximum Transmission Unit (MTU) that can be forwarded over the MSCS (in bytes). Default value is \"1500\" (bytes). ")
  
    @Valid
    public BigDecimal getMtuMscs() {
    return mtuMscs;
  }

  public void setMtuMscs(BigDecimal mtuMscs) {
    this.mtuMscs = mtuMscs;
  }

  public MscsInfo mscsEndpoints(List<MscsEndpointInfo> mscsEndpoints) {
    this.mscsEndpoints = mscsEndpoints;
    return this;
  }

  public MscsInfo addMscsEndpointsItem(MscsEndpointInfo mscsEndpointsItem) {
    if (this.mscsEndpoints == null) {
      this.mscsEndpoints = new ArrayList<>();
    }
    this.mscsEndpoints.add(mscsEndpointsItem);
    return this;
  }

  /**
   * Information about the MSCS endpoints of the MSCS. 
   * @return mscsEndpoints
   **/
  @Schema(description = "Information about the MSCS endpoints of the MSCS. ")
      @Valid
    public List<MscsEndpointInfo> getMscsEndpoints() {
    return mscsEndpoints;
  }

  public void setMscsEndpoints(List<MscsEndpointInfo> mscsEndpoints) {
    this.mscsEndpoints = mscsEndpoints;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MscsInfo mscsInfo = (MscsInfo) o;
    return Objects.equals(this.mscsId, mscsInfo.mscsId) &&
        Objects.equals(this.mscsName, mscsInfo.mscsName) &&
        Objects.equals(this.mscsDescription, mscsInfo.mscsDescription) &&
        Objects.equals(this.mscsType, mscsInfo.mscsType) &&
        Objects.equals(this.mscsLayerProtocol, mscsInfo.mscsLayerProtocol) &&
        Objects.equals(this.siteAccessProtectionSchemes, mscsInfo.siteAccessProtectionSchemes) &&
        Objects.equals(this.mtuMscs, mscsInfo.mtuMscs) &&
        Objects.equals(this.mscsEndpoints, mscsInfo.mscsEndpoints);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mscsId, mscsName, mscsDescription, mscsType, mscsLayerProtocol, siteAccessProtectionSchemes, mtuMscs, mscsEndpoints);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MscsInfo {\n");
    
    sb.append("    mscsId: ").append(toIndentedString(mscsId)).append("\n");
    sb.append("    mscsName: ").append(toIndentedString(mscsName)).append("\n");
    sb.append("    mscsDescription: ").append(toIndentedString(mscsDescription)).append("\n");
    sb.append("    mscsType: ").append(toIndentedString(mscsType)).append("\n");
    sb.append("    mscsLayerProtocol: ").append(toIndentedString(mscsLayerProtocol)).append("\n");
    sb.append("    siteAccessProtectionSchemes: ").append(toIndentedString(siteAccessProtectionSchemes)).append("\n");
    sb.append("    mtuMscs: ").append(toIndentedString(mtuMscs)).append("\n");
    sb.append("    mscsEndpoints: ").append(toIndentedString(mscsEndpoints)).append("\n");
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
