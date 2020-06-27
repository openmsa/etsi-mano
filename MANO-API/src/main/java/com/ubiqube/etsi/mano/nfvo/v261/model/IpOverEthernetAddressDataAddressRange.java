package com.ubiqube.etsi.mano.nfvo.v261.model;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * An IP address range to be used, e.g. in case of egress connections. In case
 * this attribute is present, IP addresses from the range will be used.
 */
@ApiModel(description = "An IP address range to be used, e.g. in case of egress connections. In case this attribute is present, IP addresses from the range will be used. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-08-30T10:47:24.034+02:00")

public class IpOverEthernetAddressDataAddressRange {
	@JsonProperty("minAddress")
	private String minAddress = null;

	@JsonProperty("maxAddress")
	private String maxAddress = null;

	public IpOverEthernetAddressDataAddressRange minAddress(final String _minAddress) {
		this.minAddress = _minAddress;
		return this;
	}

	/**
	 * Lowest IP address belonging to the range.
	 *
	 * @return minAddress
	 **/
	@ApiModelProperty(required = true, value = "Lowest IP address belonging to the range. ")
	@NotNull

	public String getMinAddress() {
		return minAddress;
	}

	public void setMinAddress(final String minAddress) {
		this.minAddress = minAddress;
	}

	public IpOverEthernetAddressDataAddressRange maxAddress(final String _maxAddress) {
		this.maxAddress = _maxAddress;
		return this;
	}

	/**
	 * Highest IP address belonging to the range.
	 *
	 * @return maxAddress
	 **/
	@ApiModelProperty(required = true, value = "Highest IP address belonging to the range. ")
	@NotNull

	public String getMaxAddress() {
		return maxAddress;
	}

	public void setMaxAddress(final String maxAddress) {
		this.maxAddress = maxAddress;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final IpOverEthernetAddressDataAddressRange ipOverEthernetAddressDataAddressRange = (IpOverEthernetAddressDataAddressRange) o;
		return Objects.equals(this.minAddress, ipOverEthernetAddressDataAddressRange.minAddress) &&
				Objects.equals(this.maxAddress, ipOverEthernetAddressDataAddressRange.maxAddress);
	}

	@Override
	public int hashCode() {
		return Objects.hash(minAddress, maxAddress);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class IpOverEthernetAddressDataAddressRange {\n");

		sb.append("    minAddress: ").append(toIndentedString(minAddress)).append("\n");
		sb.append("    maxAddress: ").append(toIndentedString(maxAddress)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private static String toIndentedString(final java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
