package com.ubiqube.etsi.mano.em.v331.model.vnfconfig;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Network address that has been configured on the CP. See NOTE 1. 
 */
@Schema(description = "Network address that has been configured on the CP. See NOTE 1. ")
@Validated


public class CpAddressAddress   {
  @JsonProperty("macAddress")
  private String macAddress = null;

  @JsonProperty("ipAddress")
  private String ipAddress = null;

  public CpAddressAddress macAddress(String macAddress) {
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

  public CpAddressAddress ipAddress(String ipAddress) {
    this.ipAddress = ipAddress;
    return this;
  }

  /**
   * Get ipAddress
   * @return ipAddress
   **/
  @Schema(description = "")
  
    public String getIpAddress() {
    return ipAddress;
  }

  public void setIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CpAddressAddress cpAddressAddress = (CpAddressAddress) o;
    return Objects.equals(this.macAddress, cpAddressAddress.macAddress) &&
        Objects.equals(this.ipAddress, cpAddressAddress.ipAddress);
  }

  @Override
  public int hashCode() {
    return Objects.hash(macAddress, ipAddress);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CpAddressAddress {\n");
    
    sb.append("    macAddress: ").append(toIndentedString(macAddress)).append("\n");
    sb.append("    ipAddress: ").append(toIndentedString(ipAddress)).append("\n");
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
