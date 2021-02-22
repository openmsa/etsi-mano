package com.ubiqube.etsi.mano.nfvo.v331.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Identifiers of the network segments of the VN resources to be forwarded into/from the MSCS. 
 */
@Schema(description = "Identifiers of the network segments of the VN resources to be forwarded into/from the MSCS. ")
@Validated


public class SiteToWanLayer2ProtocolDataForwardingConfigVnSegmentIds   {
  @JsonProperty("vnSegmentIdValue")
  private String vnSegmentIdValue = null;

  @JsonProperty("vnSegmentIdUpperRange")
  private String vnSegmentIdUpperRange = null;

  public SiteToWanLayer2ProtocolDataForwardingConfigVnSegmentIds vnSegmentIdValue(String vnSegmentIdValue) {
    this.vnSegmentIdValue = vnSegmentIdValue;
    return this;
  }

  /**
   * Identifier of the network segment. 
   * @return vnSegmentIdValue
   **/
  @Schema(required = true, description = "Identifier of the network segment. ")
      @NotNull

    public String getVnSegmentIdValue() {
    return vnSegmentIdValue;
  }

  public void setVnSegmentIdValue(String vnSegmentIdValue) {
    this.vnSegmentIdValue = vnSegmentIdValue;
  }

  public SiteToWanLayer2ProtocolDataForwardingConfigVnSegmentIds vnSegmentIdUpperRange(String vnSegmentIdUpperRange) {
    this.vnSegmentIdUpperRange = vnSegmentIdUpperRange;
    return this;
  }

  /**
   * Identifier of the upper range network segment, in case the \"vnSegmentIds\" is used to define a range. 
   * @return vnSegmentIdUpperRange
   **/
  @Schema(description = "Identifier of the upper range network segment, in case the \"vnSegmentIds\" is used to define a range. ")
  
    public String getVnSegmentIdUpperRange() {
    return vnSegmentIdUpperRange;
  }

  public void setVnSegmentIdUpperRange(String vnSegmentIdUpperRange) {
    this.vnSegmentIdUpperRange = vnSegmentIdUpperRange;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SiteToWanLayer2ProtocolDataForwardingConfigVnSegmentIds siteToWanLayer2ProtocolDataForwardingConfigVnSegmentIds = (SiteToWanLayer2ProtocolDataForwardingConfigVnSegmentIds) o;
    return Objects.equals(this.vnSegmentIdValue, siteToWanLayer2ProtocolDataForwardingConfigVnSegmentIds.vnSegmentIdValue) &&
        Objects.equals(this.vnSegmentIdUpperRange, siteToWanLayer2ProtocolDataForwardingConfigVnSegmentIds.vnSegmentIdUpperRange);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vnSegmentIdValue, vnSegmentIdUpperRange);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SiteToWanLayer2ProtocolDataForwardingConfigVnSegmentIds {\n");
    
    sb.append("    vnSegmentIdValue: ").append(toIndentedString(vnSegmentIdValue)).append("\n");
    sb.append("    vnSegmentIdUpperRange: ").append(toIndentedString(vnSegmentIdUpperRange)).append("\n");
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
