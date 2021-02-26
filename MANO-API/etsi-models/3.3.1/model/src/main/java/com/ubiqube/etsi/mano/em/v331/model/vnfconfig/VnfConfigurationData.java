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
 * This type represents configuration parameters of a VNF instance. 
 */
@Schema(description = "This type represents configuration parameters of a VNF instance. ")
@Validated


public class VnfConfigurationData   {
  @JsonProperty("extCpConfig")
  @Valid
  private List<CpConfiguration> extCpConfig = null;

  @JsonProperty("dhcpServer")
  private String dhcpServer = null;

  @JsonProperty("vnfSpecificData")
  private KeyValuePairs vnfSpecificData = null;

  public VnfConfigurationData extCpConfig(List<CpConfiguration> extCpConfig) {
    this.extCpConfig = extCpConfig;
    return this;
  }

  public VnfConfigurationData addExtCpConfigItem(CpConfiguration extCpConfigItem) {
    if (this.extCpConfig == null) {
      this.extCpConfig = new ArrayList<>();
    }
    this.extCpConfig.add(extCpConfigItem);
    return this;
  }

  /**
   * Configuration parameters for the external CPs of the VNF instance. 
   * @return extCpConfig
   **/
  @Schema(description = "Configuration parameters for the external CPs of the VNF instance. ")
      @Valid
    public List<CpConfiguration> getExtCpConfig() {
    return extCpConfig;
  }

  public void setExtCpConfig(List<CpConfiguration> extCpConfig) {
    this.extCpConfig = extCpConfig;
  }

  public VnfConfigurationData dhcpServer(String dhcpServer) {
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

  public VnfConfigurationData vnfSpecificData(KeyValuePairs vnfSpecificData) {
    this.vnfSpecificData = vnfSpecificData;
    return this;
  }

  /**
   * Get vnfSpecificData
   * @return vnfSpecificData
   **/
  @Schema(description = "")
  
    @Valid
    public KeyValuePairs getVnfSpecificData() {
    return vnfSpecificData;
  }

  public void setVnfSpecificData(KeyValuePairs vnfSpecificData) {
    this.vnfSpecificData = vnfSpecificData;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VnfConfigurationData vnfConfigurationData = (VnfConfigurationData) o;
    return Objects.equals(this.extCpConfig, vnfConfigurationData.extCpConfig) &&
        Objects.equals(this.dhcpServer, vnfConfigurationData.dhcpServer) &&
        Objects.equals(this.vnfSpecificData, vnfConfigurationData.vnfSpecificData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(extCpConfig, dhcpServer, vnfSpecificData);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfConfigurationData {\n");
    
    sb.append("    extCpConfig: ").append(toIndentedString(extCpConfig)).append("\n");
    sb.append("    dhcpServer: ").append(toIndentedString(dhcpServer)).append("\n");
    sb.append("    vnfSpecificData: ").append(toIndentedString(vnfSpecificData)).append("\n");
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
