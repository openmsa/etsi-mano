package io.swagger.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This type represents information about a network address that has been assigned.
 **/
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "This type represents information about a network address that has been assigned. ")

public class VnfInstancesInstantiatedVnfInfoAddresses {

	private @Valid String macAddress = null;
	private @Valid String ipAddress = null;
	private @Valid List<VnfInstancesInstantiatedVnfInfoSubnetIpRanges> subnetIpRanges = new ArrayList<VnfInstancesInstantiatedVnfInfoSubnetIpRanges>();

	/**
	 * Assigned MAC address.
	 **/
	public VnfInstancesInstantiatedVnfInfoAddresses macAddress(String macAddress) {
		this.macAddress = macAddress;
		return this;
	}

	@ApiModelProperty(required = true, value = "Assigned MAC address. ")
	@JsonProperty("macAddress")
	@NotNull
	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	/**
	 * IP address. Present if an IP address was assigned.
	 **/
	public VnfInstancesInstantiatedVnfInfoAddresses ipAddress(String ipAddress) {
		this.ipAddress = ipAddress;
		return this;
	}

	@ApiModelProperty(value = "IP address. Present if an IP address was assigned. ")
	@JsonProperty("ipAddress")
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * IP address ranges defining the subnet in which the IP address was assigned.
	 * May be present if the \&quot;ipAddress\&quot; attribute is present, and shall
	 * be absent if the \&quot;ipAddress\&quot; attribute is not present.
	 **/
	public VnfInstancesInstantiatedVnfInfoAddresses subnetIpRanges(List<VnfInstancesInstantiatedVnfInfoSubnetIpRanges> subnetIpRanges) {
		this.subnetIpRanges = subnetIpRanges;
		return this;
	}

	@ApiModelProperty(value = "IP address ranges defining the subnet in which the IP address was assigned. May be present if the \"ipAddress\" attribute is present, and shall be absent if the \"ipAddress\" attribute is not present. ")
	@JsonProperty("subnetIpRanges")
	public List<VnfInstancesInstantiatedVnfInfoSubnetIpRanges> getSubnetIpRanges() {
		return subnetIpRanges;
	}

	public void setSubnetIpRanges(List<VnfInstancesInstantiatedVnfInfoSubnetIpRanges> subnetIpRanges) {
		this.subnetIpRanges = subnetIpRanges;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final VnfInstancesInstantiatedVnfInfoAddresses vnfInstancesInstantiatedVnfInfoAddresses = (VnfInstancesInstantiatedVnfInfoAddresses) o;
		return Objects.equals(macAddress, vnfInstancesInstantiatedVnfInfoAddresses.macAddress) &&
				Objects.equals(ipAddress, vnfInstancesInstantiatedVnfInfoAddresses.ipAddress) &&
				Objects.equals(subnetIpRanges, vnfInstancesInstantiatedVnfInfoAddresses.subnetIpRanges);
	}

	@Override
	public int hashCode() {
		return Objects.hash(macAddress, ipAddress, subnetIpRanges);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfInstancesInstantiatedVnfInfoAddresses {\n");

		sb.append("    macAddress: ").append(toIndentedString(macAddress)).append("\n");
		sb.append("    ipAddress: ").append(toIndentedString(ipAddress)).append("\n");
		sb.append("    subnetIpRanges: ").append(toIndentedString(subnetIpRanges)).append("\n");
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
