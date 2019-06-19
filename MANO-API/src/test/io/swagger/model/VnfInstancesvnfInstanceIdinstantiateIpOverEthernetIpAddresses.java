package io.swagger.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;

public class VnfInstancesvnfInstanceIdinstantiateIpOverEthernetIpAddresses {

	public enum TypeEnum {

		IPV4(String.valueOf("IPV4")), IPV6(String.valueOf("IPV6"));

		private final String value;

		TypeEnum(String v) {
			value = v;
		}

		public String value() {
			return value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static TypeEnum fromValue(String v) {
			for (final TypeEnum b : TypeEnum.values()) {
				if (String.valueOf(b.value).equals(v)) {
					return b;
				}
			}
			return null;
		}
	}

	private @Valid TypeEnum type = null;
	private @Valid List<String> fixedAddresses = new ArrayList<String>();
	private @Valid Integer numDynamicAddresses = null;
	private @Valid VnfInstancesvnfInstanceIdinstantiateIpOverEthernetAddressRange addressRange = null;
	private @Valid String subnetId = null;

	/**
	 * The type of the IP addresses. Permitted values: IPV4, IPV6.
	 **/
	public VnfInstancesvnfInstanceIdinstantiateIpOverEthernetIpAddresses type(TypeEnum type) {
		this.type = type;
		return this;
	}

	@ApiModelProperty(required = true, value = "The type of the IP addresses. Permitted values: IPV4, IPV6. ")
	@JsonProperty("type")
	@NotNull
	public TypeEnum getType() {
		return type;
	}

	public void setType(TypeEnum type) {
		this.type = type;
	}

	/**
	 * Fixed addresses to assign (from the subnet defined by \&quot;subnetId\&quot;
	 * if provided). Exactly one of \&quot;fixedAddresses\&quot;,
	 * \&quot;numDynamicAddresses\&quot; or \&quot;ipAddressRange\&quot; shall be
	 * present.
	 **/
	public VnfInstancesvnfInstanceIdinstantiateIpOverEthernetIpAddresses fixedAddresses(List<String> fixedAddresses) {
		this.fixedAddresses = fixedAddresses;
		return this;
	}

	@ApiModelProperty(value = "Fixed addresses to assign (from the subnet defined by \"subnetId\" if provided). Exactly one of \"fixedAddresses\", \"numDynamicAddresses\" or \"ipAddressRange\" shall be present. ")
	@JsonProperty("fixedAddresses")
	public List<String> getFixedAddresses() {
		return fixedAddresses;
	}

	public void setFixedAddresses(List<String> fixedAddresses) {
		this.fixedAddresses = fixedAddresses;
	}

	/**
	 * Number of dynamic addresses to assign (from the subnet defined by
	 * \&quot;subnetId\&quot; if provided). Exactly one of
	 * \&quot;fixedAddresses\&quot;, \&quot;numDynamicAddresses\&quot; or
	 * \&quot;ipAddressRange\&quot; shall be present.
	 **/
	public VnfInstancesvnfInstanceIdinstantiateIpOverEthernetIpAddresses numDynamicAddresses(Integer numDynamicAddresses) {
		this.numDynamicAddresses = numDynamicAddresses;
		return this;
	}

	@ApiModelProperty(value = "Number of dynamic addresses to assign (from the subnet defined by \"subnetId\" if provided). Exactly one of \"fixedAddresses\", \"numDynamicAddresses\" or \"ipAddressRange\" shall be present. ")
	@JsonProperty("numDynamicAddresses")
	public Integer getNumDynamicAddresses() {
		return numDynamicAddresses;
	}

	public void setNumDynamicAddresses(Integer numDynamicAddresses) {
		this.numDynamicAddresses = numDynamicAddresses;
	}

	/**
	 **/
	public VnfInstancesvnfInstanceIdinstantiateIpOverEthernetIpAddresses addressRange(VnfInstancesvnfInstanceIdinstantiateIpOverEthernetAddressRange addressRange) {
		this.addressRange = addressRange;
		return this;
	}

	@ApiModelProperty(value = "")
	@JsonProperty("addressRange")
	public VnfInstancesvnfInstanceIdinstantiateIpOverEthernetAddressRange getAddressRange() {
		return addressRange;
	}

	public void setAddressRange(VnfInstancesvnfInstanceIdinstantiateIpOverEthernetAddressRange addressRange) {
		this.addressRange = addressRange;
	}

	/**
	 * An identifier maintained by the VIM or other resource provider. It is
	 * expected to be unique within the VIM instance.
	 **/
	public VnfInstancesvnfInstanceIdinstantiateIpOverEthernetIpAddresses subnetId(String subnetId) {
		this.subnetId = subnetId;
		return this;
	}

	@ApiModelProperty(value = "An identifier maintained by the VIM or other resource provider. It is expected to be unique within the VIM instance. ")
	@JsonProperty("subnetId")
	public String getSubnetId() {
		return subnetId;
	}

	public void setSubnetId(String subnetId) {
		this.subnetId = subnetId;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final VnfInstancesvnfInstanceIdinstantiateIpOverEthernetIpAddresses vnfInstancesvnfInstanceIdinstantiateIpOverEthernetIpAddresses = (VnfInstancesvnfInstanceIdinstantiateIpOverEthernetIpAddresses) o;
		return Objects.equals(type, vnfInstancesvnfInstanceIdinstantiateIpOverEthernetIpAddresses.type) &&
				Objects.equals(fixedAddresses, vnfInstancesvnfInstanceIdinstantiateIpOverEthernetIpAddresses.fixedAddresses) &&
				Objects.equals(numDynamicAddresses, vnfInstancesvnfInstanceIdinstantiateIpOverEthernetIpAddresses.numDynamicAddresses) &&
				Objects.equals(addressRange, vnfInstancesvnfInstanceIdinstantiateIpOverEthernetIpAddresses.addressRange) &&
				Objects.equals(subnetId, vnfInstancesvnfInstanceIdinstantiateIpOverEthernetIpAddresses.subnetId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(type, fixedAddresses, numDynamicAddresses, addressRange, subnetId);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfInstancesvnfInstanceIdinstantiateIpOverEthernetIpAddresses {\n");

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
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
