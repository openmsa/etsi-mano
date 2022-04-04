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
package com.ubiqube.etsi.mano.em.v281.model.vnfconfig;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.em.v281.model.vnfconfig.CpConfiguration;
import java.util.Map;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents configuration parameters of a VNFC instance. 
 */
@ApiModel(description = "This type represents configuration parameters of a VNFC instance. ")
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
  private Map<String, String> vnfcSpecificData = null;

  public VnfcConfigurationData vnfcInstanceId(String vnfcInstanceId) {
    this.vnfcInstanceId = vnfcInstanceId;
    return this;
  }

  /**
   * Identifier of a VNFC instance to which this set of configuration data applies. The identifier references the \"id\" attribute in a \"VnfcInfo\" structure. 
   * @return vnfcInstanceId
  **/
  @ApiModelProperty(required = true, value = "Identifier of a VNFC instance to which this set of configuration data applies. The identifier references the \"id\" attribute in a \"VnfcInfo\" structure. ")
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
  @ApiModelProperty(value = "Configuration parameters for the internal CPs of the VNFC instance. ")

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
   * IP address of the DHCP server that the VNF instance can use to obtain IP addresses to be assigned to its external CPs. 
   * @return dhcpServer
  **/
  @ApiModelProperty(value = "IP address of the DHCP server that the VNF instance can use to obtain IP addresses to be assigned to its external CPs. ")


  public String getDhcpServer() {
    return dhcpServer;
  }

  public void setDhcpServer(String dhcpServer) {
    this.dhcpServer = dhcpServer;
  }

  public VnfcConfigurationData vnfcSpecificData(Map<String, String> vnfcSpecificData) {
    this.vnfcSpecificData = vnfcSpecificData;
    return this;
  }

  /**
   * Additional configurable properties of the VNFC instance declared in the VNFD as \"VnfcConfigurableProperties\". 
   * @return vnfcSpecificData
  **/
  @ApiModelProperty(value = "Additional configurable properties of the VNFC instance declared in the VNFD as \"VnfcConfigurableProperties\". ")

  @Valid

  public Map<String, String> getVnfcSpecificData() {
    return vnfcSpecificData;
  }

  public void setVnfcSpecificData(Map<String, String> vnfcSpecificData) {
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

