/**
 * This copy of Woodstox XML processor is licensed under the
 * Apache (Software) License, version 2.0 ("the License").
 * See the License for details about distribution rights, and the
 * specific rights regarding derivate works.
 *
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/
 *
 * A copy is also included in the downloadable source code package
 * containing Woodstox, in file "ASL2.0", under the same directory
 * as this file.
 */
package com.ubiqube.etsi.mano.common.v261.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;

/**
 * IpOverEthernetAddressDataIpAddresses
 */
@Validated
public class IpOverEthernetAddressDataIpAddresses {
	/**
	 * The type of the IP addresses. Permitted values: IPV4, IPV6.
	 */
	public enum TypeEnum {
		IPV4("IPV4"),

		IPV6("IPV6");

		private final String value;

		TypeEnum(final String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static TypeEnum fromValue(final String text) {
			for (final TypeEnum b : TypeEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	@JsonProperty("type")
	private TypeEnum type = null;

	@JsonProperty("fixedAddresses")
	@Valid
	private List<String> fixedAddresses = null;

	@JsonProperty("numDynamicAddresses")
	private Integer numDynamicAddresses = null;

	@JsonProperty("addressRange")
	private IpOverEthernetAddressDataAddressRange addressRange = null;

	@JsonProperty("subnetId")
	private String subnetId = null;

	public IpOverEthernetAddressDataIpAddresses type(final TypeEnum _type) {
		this.type = _type;
		return this;
	}

	/**
	 * The type of the IP addresses. Permitted values: IPV4, IPV6.
	 *
	 * @return type
	 **/
	@ApiModelProperty(required = true, value = "The type of the IP addresses. Permitted values: IPV4, IPV6. ")
	@NotNull

	public TypeEnum getType() {
		return type;
	}

	public void setType(final TypeEnum type) {
		this.type = type;
	}

	public IpOverEthernetAddressDataIpAddresses fixedAddresses(final List<String> _fixedAddresses) {
		this.fixedAddresses = _fixedAddresses;
		return this;
	}

	public IpOverEthernetAddressDataIpAddresses addFixedAddressesItem(final String fixedAddressesItem) {
		if (this.fixedAddresses == null) {
			this.fixedAddresses = new ArrayList<>();
		}
		this.fixedAddresses.add(fixedAddressesItem);
		return this;
	}

	/**
	 * Fixed addresses to assign (from the subnet defined by \"subnetId\" if
	 * provided). Exactly one of \"fixedAddresses\", \"numDynamicAddresses\" or
	 * \"ipAddressRange\" shall be present.
	 *
	 * @return fixedAddresses
	 **/
	@ApiModelProperty(value = "Fixed addresses to assign (from the subnet defined by \"subnetId\" if provided). Exactly one of \"fixedAddresses\", \"numDynamicAddresses\" or \"ipAddressRange\" shall be present. ")

	public List<String> getFixedAddresses() {
		return fixedAddresses;
	}

	public void setFixedAddresses(final List<String> fixedAddresses) {
		this.fixedAddresses = fixedAddresses;
	}

	public IpOverEthernetAddressDataIpAddresses numDynamicAddresses(final Integer _numDynamicAddresses) {
		this.numDynamicAddresses = _numDynamicAddresses;
		return this;
	}

	/**
	 * Number of dynamic addresses to assign (from the subnet defined by
	 * \"subnetId\" if provided). Exactly one of \"fixedAddresses\",
	 * \"numDynamicAddresses\" or \"ipAddressRange\" shall be present.
	 *
	 * @return numDynamicAddresses
	 **/
	@ApiModelProperty(value = "Number of dynamic addresses to assign (from the subnet defined by \"subnetId\" if provided). Exactly one of \"fixedAddresses\", \"numDynamicAddresses\" or \"ipAddressRange\" shall be present. ")

	public Integer getNumDynamicAddresses() {
		return numDynamicAddresses;
	}

	public void setNumDynamicAddresses(final Integer numDynamicAddresses) {
		this.numDynamicAddresses = numDynamicAddresses;
	}

	public IpOverEthernetAddressDataIpAddresses addressRange(final IpOverEthernetAddressDataAddressRange _addressRange) {
		this.addressRange = _addressRange;
		return this;
	}

	/**
	 * Get addressRange
	 *
	 * @return addressRange
	 **/
	@ApiModelProperty(value = "")

	@Valid

	public IpOverEthernetAddressDataAddressRange getAddressRange() {
		return addressRange;
	}

	public void setAddressRange(final IpOverEthernetAddressDataAddressRange addressRange) {
		this.addressRange = addressRange;
	}

	public IpOverEthernetAddressDataIpAddresses subnetId(final String _subnetId) {
		this.subnetId = _subnetId;
		return this;
	}

	/**
	 * Subnet defined by the identifier of the subnet resource in the VIM. In case
	 * this attribute is present, IP addresses from that subnet will be assigned;
	 * otherwise, IP addresses not bound to a subnet will be assigned.
	 *
	 * @return subnetId
	 **/
	@ApiModelProperty(value = "Subnet defined by the identifier of the subnet resource in the VIM. In case this attribute is present, IP addresses from that subnet will be assigned; otherwise, IP addresses not bound to a subnet will be assigned. ")

	public String getSubnetId() {
		return subnetId;
	}

	public void setSubnetId(final String subnetId) {
		this.subnetId = subnetId;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final IpOverEthernetAddressDataIpAddresses ipOverEthernetAddressDataIpAddresses = (IpOverEthernetAddressDataIpAddresses) o;
		return Objects.equals(this.type, ipOverEthernetAddressDataIpAddresses.type) &&
				Objects.equals(this.fixedAddresses, ipOverEthernetAddressDataIpAddresses.fixedAddresses) &&
				Objects.equals(this.numDynamicAddresses, ipOverEthernetAddressDataIpAddresses.numDynamicAddresses) &&
				Objects.equals(this.addressRange, ipOverEthernetAddressDataIpAddresses.addressRange) &&
				Objects.equals(this.subnetId, ipOverEthernetAddressDataIpAddresses.subnetId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(type, fixedAddresses, numDynamicAddresses, addressRange, subnetId);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class IpOverEthernetAddressDataIpAddresses {\n");

		sb.append("    type: ").append(toIndentedString(type)).append("\n");
		sb.append("    fixedAddresses: ").append(toIndentedString(fixedAddresses)).append("\n");
		sb.append("    numDynamicAddresses: ").append(toIndentedString(numDynamicAddresses)).append("\n");
		sb.append("    addressRange: ").append(toIndentedString(addressRange)).append("\n");
		sb.append("    subnetId: ").append(toIndentedString(subnetId)).append("\n");
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
