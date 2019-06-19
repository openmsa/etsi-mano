package io.swagger.model;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * An IP address range to be used, e.g. in case of egress connections. In case this attribute is present, IP addresses from the range will be used.
 **/
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "An IP address range to be used, e.g. in case of egress connections. In case this attribute is present, IP addresses from the range will be used. ")

public class VnfInstancesvnfInstanceIdinstantiateIpOverEthernetAddressRange {

	private @Valid String minAddress = null;
	private @Valid String maxAddress = null;

	/**
	 * An IPV4 or IPV6 address. Representation: In case of an IPV4 address, string
	 * that consists of four decimal integers separated by dots, each integer
	 * ranging from 0 to 255. In case of an IPV6 address, string that consists of
	 * groups of zero to four hexadecimal digits, separated by colons.
	 **/
	public VnfInstancesvnfInstanceIdinstantiateIpOverEthernetAddressRange minAddress(String minAddress) {
		this.minAddress = minAddress;
		return this;
	}

	@ApiModelProperty(required = true, value = "An IPV4 or IPV6 address. Representation: In case of an IPV4 address, string that consists of four decimal integers separated by dots, each integer ranging from 0 to 255. In case of an IPV6 address, string that  consists of groups of zero to four hexadecimal digits, separated by colons. ")
	@JsonProperty("minAddress")
	@NotNull
	public String getMinAddress() {
		return minAddress;
	}

	public void setMinAddress(String minAddress) {
		this.minAddress = minAddress;
	}

	/**
	 * An IPV4 or IPV6 address. Representation: In case of an IPV4 address, string
	 * that consists of four decimal integers separated by dots, each integer
	 * ranging from 0 to 255. In case of an IPV6 address, string that consists of
	 * groups of zero to four hexadecimal digits, separated by colons.
	 **/
	public VnfInstancesvnfInstanceIdinstantiateIpOverEthernetAddressRange maxAddress(String maxAddress) {
		this.maxAddress = maxAddress;
		return this;
	}

	@ApiModelProperty(required = true, value = "An IPV4 or IPV6 address. Representation: In case of an IPV4 address, string that consists of four decimal integers separated by dots, each integer ranging from 0 to 255. In case of an IPV6 address, string that  consists of groups of zero to four hexadecimal digits, separated by colons. ")
	@JsonProperty("maxAddress")
	@NotNull
	public String getMaxAddress() {
		return maxAddress;
	}

	public void setMaxAddress(String maxAddress) {
		this.maxAddress = maxAddress;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final VnfInstancesvnfInstanceIdinstantiateIpOverEthernetAddressRange vnfInstancesvnfInstanceIdinstantiateIpOverEthernetAddressRange = (VnfInstancesvnfInstanceIdinstantiateIpOverEthernetAddressRange) o;
		return Objects.equals(minAddress, vnfInstancesvnfInstanceIdinstantiateIpOverEthernetAddressRange.minAddress) &&
				Objects.equals(maxAddress, vnfInstancesvnfInstanceIdinstantiateIpOverEthernetAddressRange.maxAddress);
	}

	@Override
	public int hashCode() {
		return Objects.hash(minAddress, maxAddress);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfInstancesvnfInstanceIdinstantiateIpOverEthernetAddressRange {\n");

		sb.append("    minAddress: ").append(toIndentedString(minAddress)).append("\n");
		sb.append("    maxAddress: ").append(toIndentedString(maxAddress)).append("\n");
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
