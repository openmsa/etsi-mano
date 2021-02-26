package com.ubiqube.etsi.mano.nfvo.v331.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Configuration related to the L2 virtual routing and forwarding (MAC-VRF). 
 */
@Schema(description = "Configuration related to the L2 virtual routing and forwarding (MAC-VRF). ")
@Validated


public class SiteToWanLayer2ProtocolDataVirtualRoutingAndForwarding   {
  @JsonProperty("macVrfName")
  private String macVrfName = null;

  public SiteToWanLayer2ProtocolDataVirtualRoutingAndForwarding macVrfName(String macVrfName) {
    this.macVrfName = macVrfName;
    return this;
  }

  /**
   * Name (or identifier) of the MAC-VRF instance. 
   * @return macVrfName
   **/
  @Schema(required = true, description = "Name (or identifier) of the MAC-VRF instance. ")
      @NotNull

    public String getMacVrfName() {
    return macVrfName;
  }

  public void setMacVrfName(String macVrfName) {
    this.macVrfName = macVrfName;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SiteToWanLayer2ProtocolDataVirtualRoutingAndForwarding siteToWanLayer2ProtocolDataVirtualRoutingAndForwarding = (SiteToWanLayer2ProtocolDataVirtualRoutingAndForwarding) o;
    return Objects.equals(this.macVrfName, siteToWanLayer2ProtocolDataVirtualRoutingAndForwarding.macVrfName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(macVrfName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SiteToWanLayer2ProtocolDataVirtualRoutingAndForwarding {\n");
    
    sb.append("    macVrfName: ").append(toIndentedString(macVrfName)).append("\n");
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
