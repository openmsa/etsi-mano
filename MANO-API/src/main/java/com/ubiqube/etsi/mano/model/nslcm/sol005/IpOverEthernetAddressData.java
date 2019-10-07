package com.ubiqube.etsi.mano.model.nslcm.sol005;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents network address data for IP over Ethernet.
 */
@ApiModel(description = "This type represents network address data for IP over Ethernet. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-07T10:02:43.347+02:00")

public class IpOverEthernetAddressData {
	@JsonProperty("macAddress")
	private String macAddress = null;

	@JsonProperty("ipAddresses")
	@Valid
	private List<IpOverEthernetAddressDataIpAddresses> ipAddresses = null;

	public IpOverEthernetAddressData macAddress(final String macAddress) {
		this.macAddress = macAddress;
		return this;
	}

	/**
	 * MAC address. If this attribute is not present, it shall be chosen by the NFV
	 * MANO.
	 * 
	 * @return macAddress
	 **/
	@ApiModelProperty(value = "MAC address. If this attribute is not present, it shall be chosen by the NFV MANO. ")

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(final String macAddress) {
		this.macAddress = macAddress;
	}

	public IpOverEthernetAddressData ipAddresses(final List<IpOverEthernetAddressDataIpAddresses> ipAddresses) {
		this.ipAddresses = ipAddresses;
		return this;
	}

	public IpOverEthernetAddressData addIpAddressesItem(final IpOverEthernetAddressDataIpAddresses ipAddressesItem) {
		if (this.ipAddresses == null) {
			this.ipAddresses = new ArrayList<>();
		}
		this.ipAddresses.add(ipAddressesItem);
		return this;
	}

	/**
	 * List of IP addresses to assign to the CP instance. Each entry represents IP
	 * address data for fixed or dynamic IP address assignment per subnet. If this
	 * attribute is not present, no IP address shall be assigned.
	 * 
	 * @return ipAddresses
	 **/
	@ApiModelProperty(value = "List of IP addresses to assign to the CP instance. Each entry represents IP address data for fixed or dynamic IP address assignment per subnet. If this attribute is not present, no IP address shall be assigned. ")

	@Valid

	public List<IpOverEthernetAddressDataIpAddresses> getIpAddresses() {
		return ipAddresses;
	}

	public void setIpAddresses(final List<IpOverEthernetAddressDataIpAddresses> ipAddresses) {
		this.ipAddresses = ipAddresses;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final IpOverEthernetAddressData ipOverEthernetAddressData = (IpOverEthernetAddressData) o;
		return Objects.equals(this.macAddress, ipOverEthernetAddressData.macAddress) &&
				Objects.equals(this.ipAddresses, ipOverEthernetAddressData.ipAddresses);
	}

	@Override
	public int hashCode() {
		return Objects.hash(macAddress, ipAddresses);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class IpOverEthernetAddressData {\n");

		sb.append("    macAddress: ").append(toIndentedString(macAddress)).append("\n");
		sb.append("    ipAddresses: ").append(toIndentedString(ipAddresses)).append("\n");
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
