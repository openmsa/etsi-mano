package com.ubiqube.etsi.mano.nfvo.v331.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Additional configuration needed when using VXLAN encapsulation. Shall be present if interfaceTagging&#x3D;\&quot;TAGGED\&quot; and encapsulationType&#x3D;\&quot;VXLAN\&quot;. 
 */
@Schema(description = "Additional configuration needed when using VXLAN encapsulation. Shall be present if interfaceTagging=\"TAGGED\" and encapsulationType=\"VXLAN\". ")
@Validated


public class SiteToWanLayer2ProtocolDataLayer2ConnectionInfoVxlanConfig   {
  /**
   * Type of VXLAN access mode. Default value is \"STATIC\". Permitted values: - STATIC - BGP_EVPN 
   */
  public enum PeerModeEnum {
    STATIC("STATIC"),
    
    BGP_EVPN("BGP_EVPN");

    private String value;

    PeerModeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static PeerModeEnum fromValue(String text) {
      for (PeerModeEnum b : PeerModeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("peerMode")
  private PeerModeEnum peerMode = null;

  @JsonProperty("peers")
  @Valid
  private List<String> peers = null;

  public SiteToWanLayer2ProtocolDataLayer2ConnectionInfoVxlanConfig peerMode(PeerModeEnum peerMode) {
    this.peerMode = peerMode;
    return this;
  }

  /**
   * Type of VXLAN access mode. Default value is \"STATIC\". Permitted values: - STATIC - BGP_EVPN 
   * @return peerMode
   **/
  @Schema(required = true, description = "Type of VXLAN access mode. Default value is \"STATIC\". Permitted values: - STATIC - BGP_EVPN ")
      @NotNull

    public PeerModeEnum getPeerMode() {
    return peerMode;
  }

  public void setPeerMode(PeerModeEnum peerMode) {
    this.peerMode = peerMode;
  }

  public SiteToWanLayer2ProtocolDataLayer2ConnectionInfoVxlanConfig peers(List<String> peers) {
    this.peers = peers;
    return this;
  }

  public SiteToWanLayer2ProtocolDataLayer2ConnectionInfoVxlanConfig addPeersItem(String peersItem) {
    if (this.peers == null) {
      this.peers = new ArrayList<>();
    }
    this.peers.add(peersItem);
    return this;
  }

  /**
   * List of IP addresses of VTEP peers when using static mode. 
   * @return peers
   **/
  @Schema(description = "List of IP addresses of VTEP peers when using static mode. ")
  
    public List<String> getPeers() {
    return peers;
  }

  public void setPeers(List<String> peers) {
    this.peers = peers;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SiteToWanLayer2ProtocolDataLayer2ConnectionInfoVxlanConfig siteToWanLayer2ProtocolDataLayer2ConnectionInfoVxlanConfig = (SiteToWanLayer2ProtocolDataLayer2ConnectionInfoVxlanConfig) o;
    return Objects.equals(this.peerMode, siteToWanLayer2ProtocolDataLayer2ConnectionInfoVxlanConfig.peerMode) &&
        Objects.equals(this.peers, siteToWanLayer2ProtocolDataLayer2ConnectionInfoVxlanConfig.peers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(peerMode, peers);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SiteToWanLayer2ProtocolDataLayer2ConnectionInfoVxlanConfig {\n");
    
    sb.append("    peerMode: ").append(toIndentedString(peerMode)).append("\n");
    sb.append("    peers: ").append(toIndentedString(peers)).append("\n");
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
