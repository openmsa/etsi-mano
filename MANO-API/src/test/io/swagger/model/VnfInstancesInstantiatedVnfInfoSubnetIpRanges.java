package io.swagger.model;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class VnfInstancesInstantiatedVnfInfoSubnetIpRanges {

	private @Valid String minIpAddress = null;
	private @Valid String maxIpAddress = null;

	/**
	 * Lowest IP address belonging to the range.
	 **/
	public VnfInstancesInstantiatedVnfInfoSubnetIpRanges minIpAddress(String minIpAddress) {
		this.minIpAddress = minIpAddress;
		return this;
	}

	@ApiModelProperty(required = true, value = "Lowest IP address belonging to the range. ")
	@JsonProperty("minIpAddress")
	@NotNull
	public String getMinIpAddress() {
		return minIpAddress;
	}

	public void setMinIpAddress(String minIpAddress) {
		this.minIpAddress = minIpAddress;
	}

	/**
	 * Highest IP address belonging to the range.
	 **/
	public VnfInstancesInstantiatedVnfInfoSubnetIpRanges maxIpAddress(String maxIpAddress) {
		this.maxIpAddress = maxIpAddress;
		return this;
	}

	@ApiModelProperty(required = true, value = "Highest IP address belonging to the range. ")
	@JsonProperty("maxIpAddress")
	@NotNull
	public String getMaxIpAddress() {
		return maxIpAddress;
	}

	public void setMaxIpAddress(String maxIpAddress) {
		this.maxIpAddress = maxIpAddress;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final VnfInstancesInstantiatedVnfInfoSubnetIpRanges vnfInstancesInstantiatedVnfInfoSubnetIpRanges = (VnfInstancesInstantiatedVnfInfoSubnetIpRanges) o;
		return Objects.equals(minIpAddress, vnfInstancesInstantiatedVnfInfoSubnetIpRanges.minIpAddress) &&
				Objects.equals(maxIpAddress, vnfInstancesInstantiatedVnfInfoSubnetIpRanges.maxIpAddress);
	}

	@Override
	public int hashCode() {
		return Objects.hash(minIpAddress, maxIpAddress);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfInstancesInstantiatedVnfInfoSubnetIpRanges {\n");

		sb.append("    minIpAddress: ").append(toIndentedString(minIpAddress)).append("\n");
		sb.append("    maxIpAddress: ").append(toIndentedString(maxIpAddress)).append("\n");
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
