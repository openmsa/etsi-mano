package io.swagger.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This type represents network address data for IP over Ethernet.
 **/
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "This type represents network address data for IP over Ethernet. ")

public class VnfInstancesvnfInstanceIdinstantiateIpOverEthernet {

	private @Valid String macAddress = null;
	private @Valid List<VnfInstancesvnfInstanceIdinstantiateIpOverEthernetIpAddresses> ipAddresses = new ArrayList<VnfInstancesvnfInstanceIdinstantiateIpOverEthernetIpAddresses>();

	/**
	 * A MAC address. Representation: string that consists of groups of two
	 * hexadecimal digits, separated by hyphens or colons.
	 **/
	public VnfInstancesvnfInstanceIdinstantiateIpOverEthernet macAddress(String macAddress) {
		this.macAddress = macAddress;
		return this;
	}

	@ApiModelProperty(value = "A MAC address. Representation: string that consists of groups of two hexadecimal digits, separated by hyphens or colons. ")
	@JsonProperty("macAddress")
	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	/**
	 * List of IP addresses to assign to the CP instance. Each entry represents IP
	 * address data for fixed or dynamic IP address assignment per subnet. If this
	 * attribute is not present, no IP address shall be assigned.
	 **/
	public VnfInstancesvnfInstanceIdinstantiateIpOverEthernet ipAddresses(List<VnfInstancesvnfInstanceIdinstantiateIpOverEthernetIpAddresses> ipAddresses) {
		this.ipAddresses = ipAddresses;
		return this;
	}

	@ApiModelProperty(value = "List of IP addresses to assign to the CP instance. Each entry represents IP address data for fixed or dynamic IP address assignment per subnet. If this attribute is not present, no IP address shall be assigned. ")
	@JsonProperty("ipAddresses")
	public List<VnfInstancesvnfInstanceIdinstantiateIpOverEthernetIpAddresses> getIpAddresses() {
		return ipAddresses;
	}

	public void setIpAddresses(List<VnfInstancesvnfInstanceIdinstantiateIpOverEthernetIpAddresses> ipAddresses) {
		this.ipAddresses = ipAddresses;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final VnfInstancesvnfInstanceIdinstantiateIpOverEthernet vnfInstancesvnfInstanceIdinstantiateIpOverEthernet = (VnfInstancesvnfInstanceIdinstantiateIpOverEthernet) o;
		return Objects.equals(macAddress, vnfInstancesvnfInstanceIdinstantiateIpOverEthernet.macAddress) &&
				Objects.equals(ipAddresses, vnfInstancesvnfInstanceIdinstantiateIpOverEthernet.ipAddresses);
	}

	@Override
	public int hashCode() {
		return Objects.hash(macAddress, ipAddresses);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfInstancesvnfInstanceIdinstantiateIpOverEthernet {\n");

		sb.append("    macAddress: ").append(toIndentedString(macAddress)).append("\n");
		sb.append("    ipAddresses: ").append(toIndentedString(ipAddresses)).append("\n");
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
