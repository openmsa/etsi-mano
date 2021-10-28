package com.ubiqube.etsi.mano.nfvo.v351.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type provides information about Layer 2 protocol specific information for the configuration of the MSCS over the WAN. It shall comply with the provisions defined in Table 6.5.3.88-1. 
 */
@Schema(description = "This type provides information about Layer 2 protocol specific information for the configuration of the MSCS over the WAN. It shall comply with the provisions defined in Table 6.5.3.88-1. ")
@Validated


public class WanLayer2ProtocolData   {
  /**
   * Type of underlying connectivity service and protocol associated to the type of MSCS. Permitted values are as listed below and restricted by the type of MSCS: - EVPN_BGP_MPLS: as specified in IETF RFC 7432. - EVPN_VPWS: as specified in IETF RFC 8214. - VPLS_BGP: as specified in IETF RFC 4761 and IETF RFC 6624. - VPLS_LDP_L2TP: as specified in IETF RFC 4762 and IETF RFC 6074. - VPWS_LDP_L2TP: as specified in IETF RFC 6074. 
   */
  public enum MscsLayer2ProtocolEnum {
    EVPN_BGP_MPLS("EVPN_BGP_MPLS"),
    
    EVPN_VPWS("EVPN_VPWS"),
    
    VPLS_BGP("VPLS_BGP"),
    
    VPLS_LDP_L2TP("VPLS_LDP_L2TP"),
    
    VPWS_LDP_L2TP("VPWS_LDP_L2TP");

    private String value;

    MscsLayer2ProtocolEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static MscsLayer2ProtocolEnum fromValue(String text) {
      for (MscsLayer2ProtocolEnum b : MscsLayer2ProtocolEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("mscsLayer2Protocol")
  private MscsLayer2ProtocolEnum mscsLayer2Protocol = null;

  @JsonProperty("isSegmentPreservation")
  private Boolean isSegmentPreservation = null;

  @JsonProperty("isSegmentCosPreservation")
  private Boolean isSegmentCosPreservation = null;

  public WanLayer2ProtocolData mscsLayer2Protocol(MscsLayer2ProtocolEnum mscsLayer2Protocol) {
    this.mscsLayer2Protocol = mscsLayer2Protocol;
    return this;
  }

  /**
   * Type of underlying connectivity service and protocol associated to the type of MSCS. Permitted values are as listed below and restricted by the type of MSCS: - EVPN_BGP_MPLS: as specified in IETF RFC 7432. - EVPN_VPWS: as specified in IETF RFC 8214. - VPLS_BGP: as specified in IETF RFC 4761 and IETF RFC 6624. - VPLS_LDP_L2TP: as specified in IETF RFC 4762 and IETF RFC 6074. - VPWS_LDP_L2TP: as specified in IETF RFC 6074. 
   * @return mscsLayer2Protocol
   **/
  @Schema(description = "Type of underlying connectivity service and protocol associated to the type of MSCS. Permitted values are as listed below and restricted by the type of MSCS: - EVPN_BGP_MPLS: as specified in IETF RFC 7432. - EVPN_VPWS: as specified in IETF RFC 8214. - VPLS_BGP: as specified in IETF RFC 4761 and IETF RFC 6624. - VPLS_LDP_L2TP: as specified in IETF RFC 4762 and IETF RFC 6074. - VPWS_LDP_L2TP: as specified in IETF RFC 6074. ")
  
    public MscsLayer2ProtocolEnum getMscsLayer2Protocol() {
    return mscsLayer2Protocol;
  }

  public void setMscsLayer2Protocol(MscsLayer2ProtocolEnum mscsLayer2Protocol) {
    this.mscsLayer2Protocol = mscsLayer2Protocol;
  }

  public WanLayer2ProtocolData isSegmentPreservation(Boolean isSegmentPreservation) {
    this.isSegmentPreservation = isSegmentPreservation;
    return this;
  }

  /**
   * Indicates the requirement of whether to ensure network segment (e.g., VLAN id) preservation across the MSCS endpoints (i.e., from/to the NFVI-PoPs). If \"TRUE\", segment identifiers shall be preserved, \"FALSE\" otherwise. Default value is \"FALSE\". 
   * @return isSegmentPreservation
   **/
  @Schema(required = true, description = "Indicates the requirement of whether to ensure network segment (e.g., VLAN id) preservation across the MSCS endpoints (i.e., from/to the NFVI-PoPs). If \"TRUE\", segment identifiers shall be preserved, \"FALSE\" otherwise. Default value is \"FALSE\". ")
      @NotNull

    public Boolean isIsSegmentPreservation() {
    return isSegmentPreservation;
  }

  public void setIsSegmentPreservation(Boolean isSegmentPreservation) {
    this.isSegmentPreservation = isSegmentPreservation;
  }

  public WanLayer2ProtocolData isSegmentCosPreservation(Boolean isSegmentCosPreservation) {
    this.isSegmentCosPreservation = isSegmentCosPreservation;
    return this;
  }

  /**
   * Indicates the requirement of whether to ensure network segment class of service preservation across the MSCS endpoints (i.e., from/to the NFVI-PoPs). If \"TRUE\", segment class of service shall be preserved, \"FALSE\" otherwise. Default value is \"FALSE\". 
   * @return isSegmentCosPreservation
   **/
  @Schema(required = true, description = "Indicates the requirement of whether to ensure network segment class of service preservation across the MSCS endpoints (i.e., from/to the NFVI-PoPs). If \"TRUE\", segment class of service shall be preserved, \"FALSE\" otherwise. Default value is \"FALSE\". ")
      @NotNull

    public Boolean isIsSegmentCosPreservation() {
    return isSegmentCosPreservation;
  }

  public void setIsSegmentCosPreservation(Boolean isSegmentCosPreservation) {
    this.isSegmentCosPreservation = isSegmentCosPreservation;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WanLayer2ProtocolData wanLayer2ProtocolData = (WanLayer2ProtocolData) o;
    return Objects.equals(this.mscsLayer2Protocol, wanLayer2ProtocolData.mscsLayer2Protocol) &&
        Objects.equals(this.isSegmentPreservation, wanLayer2ProtocolData.isSegmentPreservation) &&
        Objects.equals(this.isSegmentCosPreservation, wanLayer2ProtocolData.isSegmentCosPreservation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mscsLayer2Protocol, isSegmentPreservation, isSegmentCosPreservation);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WanLayer2ProtocolData {\n");
    
    sb.append("    mscsLayer2Protocol: ").append(toIndentedString(mscsLayer2Protocol)).append("\n");
    sb.append("    isSegmentPreservation: ").append(toIndentedString(isSegmentPreservation)).append("\n");
    sb.append("    isSegmentCosPreservation: ").append(toIndentedString(isSegmentCosPreservation)).append("\n");
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
