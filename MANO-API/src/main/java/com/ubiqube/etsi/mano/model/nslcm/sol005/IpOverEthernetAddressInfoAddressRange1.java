package com.ubiqube.etsi.mano.model.nslcm.sol005;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * An IP address range used, e.g. in case of egress connections. See note.
 */
@ApiModel(description = "An IP address range used, e.g. in case of egress connections. See note. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-05T16:49:58.135+01:00")

public class IpOverEthernetAddressInfoAddressRange1 {
	@JsonProperty("minAddress")
	private String minAddress = null;

	@JsonProperty("maxAddress")
	private String maxAddress = null;

	public IpOverEthernetAddressInfoAddressRange1 minAddress(final String minAddress) {
		this.minAddress = minAddress;
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

	public IpOverEthernetAddressInfoAddressRange1 maxAddress(final String maxAddress) {
		this.maxAddress = maxAddress;
		return this;
	}

	/**
	 * Highest IP address belonging to the range
	 * 
	 * @return maxAddress
	 **/
	@ApiModelProperty(required = true, value = "Highest IP address belonging to the range ")
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
		final IpOverEthernetAddressInfoAddressRange1 ipOverEthernetAddressInfoAddressRange1 = (IpOverEthernetAddressInfoAddressRange1) o;
		return Objects.equals(this.minAddress, ipOverEthernetAddressInfoAddressRange1.minAddress) &&
				Objects.equals(this.maxAddress, ipOverEthernetAddressInfoAddressRange1.maxAddress);
	}

	@Override
	public int hashCode() {
		return Objects.hash(minAddress, maxAddress);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class IpOverEthernetAddressInfoAddressRange1 {\n");

		sb.append("    minAddress: ").append(toIndentedString(minAddress)).append("\n");
		sb.append("    maxAddress: ").append(toIndentedString(maxAddress)).append("\n");
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
