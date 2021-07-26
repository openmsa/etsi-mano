package com.ubiqube.etsi.mano.em.v261.model.vnfconfig;

import java.util.Map;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents configuration parameters of a VNF instance.
 */
@ApiModel(description = "This type represents configuration parameters of a VNF instance. ")
@Validated

public class VnfConfigurationData {
	@JsonProperty("extCpConfig")
	private CpConfiguration extCpConfig = null;

	@JsonProperty("dhcpServer")
	private String dhcpServer = null;

	@JsonProperty("vnfSpecificData")
	private Map<String, Object> vnfSpecificData = null;

	public VnfConfigurationData extCpConfig(final CpConfiguration extCpConfig) {
		this.extCpConfig = extCpConfig;
		return this;
	}

	/**
	 * Configuration parameters for the external CPs of the VNF instance.
	 *
	 * @return extCpConfig
	 **/
	@ApiModelProperty(value = "Configuration parameters for the external CPs of the VNF instance. ")

	@Valid

	public CpConfiguration getExtCpConfig() {
		return extCpConfig;
	}

	public void setExtCpConfig(final CpConfiguration extCpConfig) {
		this.extCpConfig = extCpConfig;
	}

	public VnfConfigurationData dhcpServer(final String dhcpServer) {
		this.dhcpServer = dhcpServer;
		return this;
	}

	/**
	 * IP address of the DHCP server that the VNF instance can use to obtain IP addresses to be assigned to its external CPs.
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

	public VnfConfigurationData vnfSpecificData(final Map<String, Object> vnfSpecificData) {
		this.vnfSpecificData = vnfSpecificData;
		return this;
	}

	/**
	 * Additional configurable properties of the VNF instance declared in the VNFD as \"VnfConfigurableProperties\".
	 *
	 * @return vnfSpecificData
	 **/
	@ApiModelProperty(value = "Additional configurable properties of the VNF instance declared in the VNFD as \"VnfConfigurableProperties\". ")

	@Valid

	public Map<String, Object> getVnfSpecificData() {
		return vnfSpecificData;
	}

	public void setVnfSpecificData(final Map<String, Object> vnfSpecificData) {
		this.vnfSpecificData = vnfSpecificData;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final VnfConfigurationData vnfConfigurationData = (VnfConfigurationData) o;
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
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfConfigurationData {\n");

		sb.append("    extCpConfig: ").append(toIndentedString(extCpConfig)).append("\n");
		sb.append("    dhcpServer: ").append(toIndentedString(dhcpServer)).append("\n");
		sb.append("    vnfSpecificData: ").append(toIndentedString(vnfSpecificData)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces (except the first line).
	 */
	private String toIndentedString(final java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
