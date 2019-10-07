package com.ubiqube.etsi.mano.model.nslcm.sol005;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * The NfpRule data type is an expression of the conditions that shall be met in
 * order for the NFP to be applicable to the packet. The condition acts as a
 * flow classifier and it is met only if all the values expressed in the
 * condition are matched by those in the packet. It shall comply with the
 * provisions defined in Table 6.5.3.40-1.
 */
@ApiModel(description = "The NfpRule data type is an expression of the conditions that shall be met in order for the NFP to be applicable to the packet. The condition acts as a flow classifier and it is met only if all the values expressed in the condition are matched by those in the packet. It shall comply with the provisions defined in Table 6.5.3.40-1. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-07T10:02:43.347+02:00")

public class NfpRule {
	@JsonProperty("etherDestinationAddress")
	private String etherDestinationAddress = null;

	@JsonProperty("etherSourceAddress")
	private String etherSourceAddress = null;

	/**
	 * Human readable description for the VNFFG.
	 */
	public enum EtherTypeEnum {
		IPV4("IPV4"),

		IPV6("IPV6");

		private final String value;

		EtherTypeEnum(final String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static EtherTypeEnum fromValue(final String text) {
			for (final EtherTypeEnum b : EtherTypeEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	@JsonProperty("etherType")
	private EtherTypeEnum etherType = null;

	@JsonProperty("vlanTag")
	@Valid
	private List<String> vlanTag = null;

	/**
	 * Indicates the L4 protocol, For IPv4 [7] this corresponds to the field called
	 * \"Protocol\" to identify the next level protocol. For IPv6 [28] this
	 * corresponds to the field is called the \"Next Header\" field. Permitted
	 * values: Any keyword defined in the IANA protocol registry [1], e.g.: TCP UDP
	 * ICMP
	 */
	public enum ProtocolEnum {
		TCP("TCP"),

		UDP("UDP"),

		ICMP("ICMP");

		private final String value;

		ProtocolEnum(final String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static ProtocolEnum fromValue(final String text) {
			for (final ProtocolEnum b : ProtocolEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	@JsonProperty("protocol")
	private ProtocolEnum protocol = null;

	@JsonProperty("dscp")
	private String dscp = null;

	@JsonProperty("sourcePortRange")
	private PortRange sourcePortRange = null;

	@JsonProperty("destinationPortRange")
	private PortRange destinationPortRange = null;

	@JsonProperty("sourceIpAddressPrefix")
	private String sourceIpAddressPrefix = null;

	@JsonProperty("destinationIpAddressPrefix")
	private String destinationIpAddressPrefix = null;

	@JsonProperty("extendedCriteria")
	@Valid
	private List<Mask> extendedCriteria = null;

	public NfpRule etherDestinationAddress(final String etherDestinationAddress) {
		this.etherDestinationAddress = etherDestinationAddress;
		return this;
	}

	/**
	 * Indicates a destination Mac address.
	 * 
	 * @return etherDestinationAddress
	 **/
	@ApiModelProperty(value = "Indicates a destination Mac address. ")

	public String getEtherDestinationAddress() {
		return etherDestinationAddress;
	}

	public void setEtherDestinationAddress(final String etherDestinationAddress) {
		this.etherDestinationAddress = etherDestinationAddress;
	}

	public NfpRule etherSourceAddress(final String etherSourceAddress) {
		this.etherSourceAddress = etherSourceAddress;
		return this;
	}

	/**
	 * Indicates a source Mac address.
	 * 
	 * @return etherSourceAddress
	 **/
	@ApiModelProperty(value = "Indicates a source Mac address. ")

	public String getEtherSourceAddress() {
		return etherSourceAddress;
	}

	public void setEtherSourceAddress(final String etherSourceAddress) {
		this.etherSourceAddress = etherSourceAddress;
	}

	public NfpRule etherType(final EtherTypeEnum etherType) {
		this.etherType = etherType;
		return this;
	}

	/**
	 * Human readable description for the VNFFG.
	 * 
	 * @return etherType
	 **/
	@ApiModelProperty(value = "Human readable description for the VNFFG. ")

	public EtherTypeEnum getEtherType() {
		return etherType;
	}

	public void setEtherType(final EtherTypeEnum etherType) {
		this.etherType = etherType;
	}

	public NfpRule vlanTag(final List<String> vlanTag) {
		this.vlanTag = vlanTag;
		return this;
	}

	public NfpRule addVlanTagItem(final String vlanTagItem) {
		if (this.vlanTag == null) {
			this.vlanTag = new ArrayList<>();
		}
		this.vlanTag.add(vlanTagItem);
		return this;
	}

	/**
	 * Indicates a VLAN identifier in an IEEE 802.1Q-2018 tag [6] Multiple tags can
	 * be included for QinQ stacking. See note.
	 * 
	 * @return vlanTag
	 **/
	@ApiModelProperty(value = "Indicates a VLAN identifier in an IEEE 802.1Q-2018 tag [6] Multiple tags can be included for QinQ stacking. See note. ")

	public List<String> getVlanTag() {
		return vlanTag;
	}

	public void setVlanTag(final List<String> vlanTag) {
		this.vlanTag = vlanTag;
	}

	public NfpRule protocol(final ProtocolEnum protocol) {
		this.protocol = protocol;
		return this;
	}

	/**
	 * Indicates the L4 protocol, For IPv4 [7] this corresponds to the field called
	 * \"Protocol\" to identify the next level protocol. For IPv6 [28] this
	 * corresponds to the field is called the \"Next Header\" field. Permitted
	 * values: Any keyword defined in the IANA protocol registry [1], e.g.: TCP UDP
	 * ICMP
	 * 
	 * @return protocol
	 **/
	@ApiModelProperty(value = "Indicates the L4 protocol, For IPv4 [7] this corresponds to the field called \"Protocol\" to identify the next level protocol. For IPv6 [28] this corresponds to the field is called the \"Next Header\" field. Permitted values: Any keyword defined in the IANA protocol registry [1], e.g.: TCP UDP ICMP ")

	public ProtocolEnum getProtocol() {
		return protocol;
	}

	public void setProtocol(final ProtocolEnum protocol) {
		this.protocol = protocol;
	}

	public NfpRule dscp(final String dscp) {
		this.dscp = dscp;
		return this;
	}

	/**
	 * For IPv4 [7] a string of \"0\" and \"1\" digits that corresponds to the 6-bit
	 * Differentiated Services Code Point (DSCP) field of the IP header. For IPv6
	 * [28] a string of \"0\" and \"1\" digits that corresponds to the 6
	 * differentiated services bits of the traffic class header field
	 * 
	 * @return dscp
	 **/
	@ApiModelProperty(value = "For IPv4 [7] a string of \"0\" and \"1\" digits that corresponds to the 6-bit Differentiated Services Code Point (DSCP) field of the IP header. For IPv6 [28] a string of \"0\" and \"1\" digits that corresponds to the 6 differentiated services bits of the traffic class header field ")

	public String getDscp() {
		return dscp;
	}

	public void setDscp(final String dscp) {
		this.dscp = dscp;
	}

	public NfpRule sourcePortRange(final PortRange sourcePortRange) {
		this.sourcePortRange = sourcePortRange;
		return this;
	}

	/**
	 * Indicates a range of source ports
	 * 
	 * @return sourcePortRange
	 **/
	@ApiModelProperty(value = "Indicates a range of source ports ")

	@Valid

	public PortRange getSourcePortRange() {
		return sourcePortRange;
	}

	public void setSourcePortRange(final PortRange sourcePortRange) {
		this.sourcePortRange = sourcePortRange;
	}

	public NfpRule destinationPortRange(final PortRange destinationPortRange) {
		this.destinationPortRange = destinationPortRange;
		return this;
	}

	/**
	 * Indicates a range of destination ports.
	 * 
	 * @return destinationPortRange
	 **/
	@ApiModelProperty(value = "Indicates a range of destination ports. ")

	@Valid

	public PortRange getDestinationPortRange() {
		return destinationPortRange;
	}

	public void setDestinationPortRange(final PortRange destinationPortRange) {
		this.destinationPortRange = destinationPortRange;
	}

	public NfpRule sourceIpAddressPrefix(final String sourceIpAddressPrefix) {
		this.sourceIpAddressPrefix = sourceIpAddressPrefix;
		return this;
	}

	/**
	 * Indicates the source IP address range in CIDR format.
	 * 
	 * @return sourceIpAddressPrefix
	 **/
	@ApiModelProperty(value = "Indicates the source IP address range in CIDR format. ")

	public String getSourceIpAddressPrefix() {
		return sourceIpAddressPrefix;
	}

	public void setSourceIpAddressPrefix(final String sourceIpAddressPrefix) {
		this.sourceIpAddressPrefix = sourceIpAddressPrefix;
	}

	public NfpRule destinationIpAddressPrefix(final String destinationIpAddressPrefix) {
		this.destinationIpAddressPrefix = destinationIpAddressPrefix;
		return this;
	}

	/**
	 * Indicates the destination IP address range in CIDR format.
	 * 
	 * @return destinationIpAddressPrefix
	 **/
	@ApiModelProperty(value = "Indicates the destination IP address range in CIDR format. ")

	public String getDestinationIpAddressPrefix() {
		return destinationIpAddressPrefix;
	}

	public void setDestinationIpAddressPrefix(final String destinationIpAddressPrefix) {
		this.destinationIpAddressPrefix = destinationIpAddressPrefix;
	}

	public NfpRule extendedCriteria(final List<Mask> extendedCriteria) {
		this.extendedCriteria = extendedCriteria;
		return this;
	}

	public NfpRule addExtendedCriteriaItem(final Mask extendedCriteriaItem) {
		if (this.extendedCriteria == null) {
			this.extendedCriteria = new ArrayList<>();
		}
		this.extendedCriteria.add(extendedCriteriaItem);
		return this;
	}

	/**
	 * Indicates values of specific bits in a frame.
	 * 
	 * @return extendedCriteria
	 **/
	@ApiModelProperty(value = "Indicates values of specific bits in a frame. ")

	@Valid

	public List<Mask> getExtendedCriteria() {
		return extendedCriteria;
	}

	public void setExtendedCriteria(final List<Mask> extendedCriteria) {
		this.extendedCriteria = extendedCriteria;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final NfpRule nfpRule = (NfpRule) o;
		return Objects.equals(this.etherDestinationAddress, nfpRule.etherDestinationAddress) &&
				Objects.equals(this.etherSourceAddress, nfpRule.etherSourceAddress) &&
				Objects.equals(this.etherType, nfpRule.etherType) &&
				Objects.equals(this.vlanTag, nfpRule.vlanTag) &&
				Objects.equals(this.protocol, nfpRule.protocol) &&
				Objects.equals(this.dscp, nfpRule.dscp) &&
				Objects.equals(this.sourcePortRange, nfpRule.sourcePortRange) &&
				Objects.equals(this.destinationPortRange, nfpRule.destinationPortRange) &&
				Objects.equals(this.sourceIpAddressPrefix, nfpRule.sourceIpAddressPrefix) &&
				Objects.equals(this.destinationIpAddressPrefix, nfpRule.destinationIpAddressPrefix) &&
				Objects.equals(this.extendedCriteria, nfpRule.extendedCriteria);
	}

	@Override
	public int hashCode() {
		return Objects.hash(etherDestinationAddress, etherSourceAddress, etherType, vlanTag, protocol, dscp, sourcePortRange, destinationPortRange, sourceIpAddressPrefix, destinationIpAddressPrefix, extendedCriteria);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class NfpRule {\n");

		sb.append("    etherDestinationAddress: ").append(toIndentedString(etherDestinationAddress)).append("\n");
		sb.append("    etherSourceAddress: ").append(toIndentedString(etherSourceAddress)).append("\n");
		sb.append("    etherType: ").append(toIndentedString(etherType)).append("\n");
		sb.append("    vlanTag: ").append(toIndentedString(vlanTag)).append("\n");
		sb.append("    protocol: ").append(toIndentedString(protocol)).append("\n");
		sb.append("    dscp: ").append(toIndentedString(dscp)).append("\n");
		sb.append("    sourcePortRange: ").append(toIndentedString(sourcePortRange)).append("\n");
		sb.append("    destinationPortRange: ").append(toIndentedString(destinationPortRange)).append("\n");
		sb.append("    sourceIpAddressPrefix: ").append(toIndentedString(sourceIpAddressPrefix)).append("\n");
		sb.append("    destinationIpAddressPrefix: ").append(toIndentedString(destinationIpAddressPrefix)).append("\n");
		sb.append("    extendedCriteria: ").append(toIndentedString(extendedCriteria)).append("\n");
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
