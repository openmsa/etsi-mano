package com.ubiqube.etsi.mano.em.v331.model.vnfconfig;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.em.v331.model.vnfconfig.CpConfiguration;
import com.ubiqube.etsi.mano.em.v331.model.vnfconfig.KeyValuePairs;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents configuration parameters of a VNFC instance. 
 */
@Schema(description = "This type represents configuration parameters of a VNFC instance. ")
@Validated


public class VnfcConfigurationData   {
  @JsonProperty("vnfcInstanceId")
  private String vnfcInstanceId = null;

  @JsonProperty("intCpConfig")
  @Valid
  private List<CpConfiguration> intCpConfig = null;

  @JsonProperty("dhcpServer")
  private String dhcpServer = null;

  @JsonProperty("vnfcSpecificData")
  private KeyValuePairs vnfcSpecificData = null;

  public VnfcConfigurationData vnfcInstanceId(String vnfcInstanceId) {
    this.vnfcInstanceId = vnfcInstanceId;
    return this;
  }

  /**
   * Get vnfcInstanceId
   * @return vnfcInstanceId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getVnfcInstanceId() {
    return vnfcInstanceId;
  }

  public void setVnfcInstanceId(String vnfcInstanceId) {
    this.vnfcInstanceId = vnfcInstanceId;
  }

  public VnfcConfigurationData intCpConfig(List<CpConfiguration> intCpConfig) {
    this.intCpConfig = intCpConfig;
    return this;
  }

  public VnfcConfigurationData addIntCpConfigItem(CpConfiguration intCpConfigItem) {
    if (this.intCpConfig == null) {
      this.intCpConfig = new ArrayList<>();
    }
    this.intCpConfig.add(intCpConfigItem);
    return this;
  }

  /**
   * Configuration parameters for the internal CPs of the VNFC instance. 
   * @return intCpConfig
   **/
  @Schema(description = "Configuration parameters for the internal CPs of the VNFC instance. ")
      @Valid
    public List<CpConfiguration> getIntCpConfig() {
    return intCpConfig;
  }

  public void setIntCpConfig(List<CpConfiguration> intCpConfig) {
    this.intCpConfig = intCpConfig;
  }

  public VnfcConfigurationData dhcpServer(String dhcpServer) {
    this.dhcpServer = dhcpServer;
    return this;
  }

  /**
   * Get dhcpServer
   * @return dhcpServer
   **/
  @Schema(description = "")
  
    public String getDhcpServer() {
    return dhcpServer;
  }

  public void setDhcpServer(String dhcpServer) {
    this.dhcpServer = dhcpServer;
  }

  public VnfcConfigurationData vnfcSpecificData(KeyValuePairs vnfcSpecificData) {
    this.vnfcSpecificData = vnfcSpecificData;
    return this;
  }

  /**
   * Get vnfcSpecificData
   * @return vnfcSpecificData
   **/
  @Schema(description = "")
  
    @Valid
    public KeyValuePairs getVnfcSpecificData() {
    return vnfcSpecificData;
  }

  public void setVnfcSpecificData(KeyValuePairs vnfcSpecificData) {
    this.vnfcSpecificData = vnfcSpecificData;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VnfcConfigurationData vnfcConfigurationData = (VnfcConfigurationData) o;
    return Objects.equals(this.vnfcInstanceId, vnfcConfigurationData.vnfcInstanceId) &&
        Objects.equals(this.intCpConfig, vnfcConfigurationData.intCpConfig) &&
        Objects.equals(this.dhcpServer, vnfcConfigurationData.dhcpServer) &&
        Objects.equals(this.vnfcSpecificData, vnfcConfigurationData.vnfcSpecificData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vnfcInstanceId, intCpConfig, dhcpServer, vnfcSpecificData);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfcConfigurationData {\n");
    
    sb.append("    vnfcInstanceId: ").append(toIndentedString(vnfcInstanceId)).append("\n");
    sb.append("    intCpConfig: ").append(toIndentedString(intCpConfig)).append("\n");
    sb.append("    dhcpServer: ").append(toIndentedString(dhcpServer)).append("\n");
    sb.append("    vnfcSpecificData: ").append(toIndentedString(vnfcSpecificData)).append("\n");
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
