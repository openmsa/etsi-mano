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
package com.ubiqube.etsi.mano.em.v271.model.vnfconfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents configuration parameters of a VNFC instance.
 */
@ApiModel(description = "This type represents configuration parameters of a VNFC instance. ")
@Validated

public class VnfcConfigurationData {
	@JsonProperty("vnfcInstanceId")
	private String vnfcInstanceId = null;

	@JsonProperty("intCpConfig")
	@Valid
	private List<CpConfiguration> intCpConfig = null;

	@JsonProperty("dhcpServer")
	private String dhcpServer = null;

	@JsonProperty("vnfcSpecificData")
	private Map<String, String> vnfcSpecificData = null;

	public VnfcConfigurationData vnfcInstanceId(final String vnfcInstanceId) {
		this.vnfcInstanceId = vnfcInstanceId;
		return this;
	}

	/**
	 * Identifier of a VNFC instance to which this set of configuration data
	 * applies.
	 *
	 * @return vnfcInstanceId
	 **/
	@ApiModelProperty(required = true, value = "Identifier of a VNFC instance to which this set of configuration data applies. ")
	@NotNull

	public String getVnfcInstanceId() {
		return vnfcInstanceId;
	}

	public void setVnfcInstanceId(final String vnfcInstanceId) {
		this.vnfcInstanceId = vnfcInstanceId;
	}

	public VnfcConfigurationData intCpConfig(final List<CpConfiguration> intCpConfig) {
		this.intCpConfig = intCpConfig;
		return this;
	}

	public VnfcConfigurationData addIntCpConfigItem(final CpConfiguration intCpConfigItem) {
		if (this.intCpConfig == null) {
			this.intCpConfig = new ArrayList<>();
		}
		this.intCpConfig.add(intCpConfigItem);
		return this;
	}

	/**
	 * Configuration parameters for the internal CPs of the VNFC instance.
	 *
	 * @return intCpConfig
	 **/
	@ApiModelProperty(value = "Configuration parameters for the internal CPs of the VNFC instance. ")

	@Valid

	public List<CpConfiguration> getIntCpConfig() {
		return intCpConfig;
	}

	public void setIntCpConfig(final List<CpConfiguration> intCpConfig) {
		this.intCpConfig = intCpConfig;
	}

	public VnfcConfigurationData dhcpServer(final String dhcpServer) {
		this.dhcpServer = dhcpServer;
		return this;
	}

	/**
	 * IP address of the DHCP server that the VNF instance can use to obtain IP
	 * addresses to be assigned to its external CPs.
	 *
	 * @return dhcpServer
	 **/
	@ApiModelProperty(value = "IP address of the DHCP server that the VNF instance can use to obtain IP addresses to be assigned to its external CPs. ")

	public String getDhcpServer() {
		return dhcpServer;
	}

	public void setDhcpServer(final String dhcpServer) {
		this.dhcpServer = dhcpServer;
	}

	public VnfcConfigurationData vnfcSpecificData(final Map<String, String> vnfcSpecificData) {
		this.vnfcSpecificData = vnfcSpecificData;
		return this;
	}

	/**
	 * Additional configurable properties of the VNFC instance declared in the VNFD
	 * as \"VnfcConfigurableProperties\".
	 *
	 * @return vnfcSpecificData
	 **/
	@ApiModelProperty(value = "Additional configurable properties of the VNFC instance declared in the VNFD as \"VnfcConfigurableProperties\". ")

	@Valid

	public Map<String, String> getVnfcSpecificData() {
		return vnfcSpecificData;
	}

	public void setVnfcSpecificData(final Map<String, String> vnfcSpecificData) {
		this.vnfcSpecificData = vnfcSpecificData;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final VnfcConfigurationData vnfcConfigurationData = (VnfcConfigurationData) o;
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
		final StringBuilder sb = new StringBuilder();
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
	private String toIndentedString(final java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
