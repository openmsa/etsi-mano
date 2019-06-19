package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
  * The NfpRule data type is an expression of the conditions that shall be met  in order for the NFP to be applicable to the packet. The condition acts as a flow classifier and  it is met only if all the values expressed in the condition are matched by those in the packet. It shall comply with the provisions defined in Table 6.5.3.40-1. 
 **/
@ApiModel(description="The NfpRule data type is an expression of the conditions that shall be met  in order for the NFP to be applicable to the packet. The condition acts as a flow classifier and  it is met only if all the values expressed in the condition are matched by those in the packet. It shall comply with the provisions defined in Table 6.5.3.40-1. ")
public class NsInstancesnsInstanceIdupdateUpdateNsRequestNfpRule  {
  
  @ApiModelProperty(value = "A MAC address. Representation: string that consists of groups of two hexadecimal digits, separated by hyphens or colons. ")
 /**
   * A MAC address. Representation: string that consists of groups of two hexadecimal digits, separated by hyphens or colons. 
  **/
  private String etherDestinationAddress = null;

  @ApiModelProperty(value = "A MAC address. Representation: string that consists of groups of two hexadecimal digits, separated by hyphens or colons. ")
 /**
   * A MAC address. Representation: string that consists of groups of two hexadecimal digits, separated by hyphens or colons. 
  **/
  private String etherSourceAddress = null;


@XmlType(name="EtherTypeEnum")
@XmlEnum(String.class)
public enum EtherTypeEnum {

@XmlEnumValue("IPV4") IPV4(String.valueOf("IPV4")), @XmlEnumValue("IPV6") IPV6(String.valueOf("IPV6"));


    private String value;

    EtherTypeEnum (String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static EtherTypeEnum fromValue(String v) {
        for (EtherTypeEnum b : EtherTypeEnum.values()) {
            if (String.valueOf(b.value).equals(v)) {
                return b;
            }
        }
        return null;
    }
}

  @ApiModelProperty(value = "Human readable description for the VNFFG. ")
 /**
   * Human readable description for the VNFFG. 
  **/
  private EtherTypeEnum etherType = null;

  @ApiModelProperty(value = "Indicates a VLAN identifier in an IEEE 802.1Q-2014  tag [6] Multiple tags can be included for QinQ stacking. See note. ")
 /**
   * Indicates a VLAN identifier in an IEEE 802.1Q-2014  tag [6] Multiple tags can be included for QinQ stacking. See note. 
  **/
  private List<String> vlanTag = null;


@XmlType(name="ProtocolEnum")
@XmlEnum(String.class)
public enum ProtocolEnum {

@XmlEnumValue("TCP") TCP(String.valueOf("TCP")), @XmlEnumValue("UDP") UDP(String.valueOf("UDP")), @XmlEnumValue("ICMP") ICMP(String.valueOf("ICMP"));


    private String value;

    ProtocolEnum (String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static ProtocolEnum fromValue(String v) {
        for (ProtocolEnum b : ProtocolEnum.values()) {
            if (String.valueOf(b.value).equals(v)) {
                return b;
            }
        }
        return null;
    }
}

  @ApiModelProperty(value = "Indicates the L4 protocol, For IPv4 [7] this corresponds to the field called \"Protocol\" to identify the next level protocol. For IPv6 [28] this corresponds to the field is called the \"Next Header\" field. Permitted values: Any keyword defined in the IANA protocol registry [1], e.g.: TCP UDP ICMP ")
 /**
   * Indicates the L4 protocol, For IPv4 [7] this corresponds to the field called \"Protocol\" to identify the next level protocol. For IPv6 [28] this corresponds to the field is called the \"Next Header\" field. Permitted values: Any keyword defined in the IANA protocol registry [1], e.g.: TCP UDP ICMP 
  **/
  private ProtocolEnum protocol = null;

  @ApiModelProperty(value = "For IPv4 [7] a string of \"0\" and \"1\" digits that corresponds to the 6-bit Differentiated Services Code Point (DSCP) field of the IP header. For IPv6 [28] a string of \"0\" and \"1\" digits that corresponds to the 6 differentiated services bits of the traffic class header field ")
 /**
   * For IPv4 [7] a string of \"0\" and \"1\" digits that corresponds to the 6-bit Differentiated Services Code Point (DSCP) field of the IP header. For IPv6 [28] a string of \"0\" and \"1\" digits that corresponds to the 6 differentiated services bits of the traffic class header field 
  **/
  private String dscp = null;

  @ApiModelProperty(value = "")
  @Valid
  private NsInstancesnsInstanceIdupdateUpdateNsRequestNfpRuleSourcePortRange sourcePortRange = null;

  @ApiModelProperty(value = "")
  @Valid
  private NsInstancesnsInstanceIdupdateUpdateNsRequestNfpRuleSourcePortRange destinationPortRange = null;

  @ApiModelProperty(value = "An IPV4 or IPV6 address range in CIDR format. For IPV4 address range, refer to IETF RFC 4632 [12]. For IPV6 address range, refer to IETF RFC 4291 [11]. ")
 /**
   * An IPV4 or IPV6 address range in CIDR format. For IPV4 address range, refer to IETF RFC 4632 [12]. For IPV6 address range, refer to IETF RFC 4291 [11]. 
  **/
  private String sourceIpAddressPrefix = null;

  @ApiModelProperty(value = "An IPV4 or IPV6 address range in CIDR format. For IPV4 address range, refer to IETF RFC 4632 [12]. For IPV6 address range, refer to IETF RFC 4291 [11]. ")
 /**
   * An IPV4 or IPV6 address range in CIDR format. For IPV4 address range, refer to IETF RFC 4632 [12]. For IPV6 address range, refer to IETF RFC 4291 [11]. 
  **/
  private String destinationIpAddressPrefix = null;

  @ApiModelProperty(value = "Indicates values of specific bits in a frame. ")
  @Valid
 /**
   * Indicates values of specific bits in a frame. 
  **/
  private List<NsInstancesnsInstanceIdupdateUpdateNsRequestNfpRuleExtendedCriteria> extendedCriteria = null;
 /**
   * A MAC address. Representation: string that consists of groups of two hexadecimal digits, separated by hyphens or colons. 
   * @return etherDestinationAddress
  **/
  @JsonProperty("etherDestinationAddress")
  public String getEtherDestinationAddress() {
    return etherDestinationAddress;
  }

  public void setEtherDestinationAddress(String etherDestinationAddress) {
    this.etherDestinationAddress = etherDestinationAddress;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestNfpRule etherDestinationAddress(String etherDestinationAddress) {
    this.etherDestinationAddress = etherDestinationAddress;
    return this;
  }

 /**
   * A MAC address. Representation: string that consists of groups of two hexadecimal digits, separated by hyphens or colons. 
   * @return etherSourceAddress
  **/
  @JsonProperty("etherSourceAddress")
  public String getEtherSourceAddress() {
    return etherSourceAddress;
  }

  public void setEtherSourceAddress(String etherSourceAddress) {
    this.etherSourceAddress = etherSourceAddress;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestNfpRule etherSourceAddress(String etherSourceAddress) {
    this.etherSourceAddress = etherSourceAddress;
    return this;
  }

 /**
   * Human readable description for the VNFFG. 
   * @return etherType
  **/
  @JsonProperty("etherType")
  public String getEtherType() {
    if (etherType == null) {
      return null;
    }
    return etherType.value();
  }

  public void setEtherType(EtherTypeEnum etherType) {
    this.etherType = etherType;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestNfpRule etherType(EtherTypeEnum etherType) {
    this.etherType = etherType;
    return this;
  }

 /**
   * Indicates a VLAN identifier in an IEEE 802.1Q-2014  tag [6] Multiple tags can be included for QinQ stacking. See note. 
   * @return vlanTag
  **/
  @JsonProperty("vlanTag")
  public List<String> getVlanTag() {
    return vlanTag;
  }

  public void setVlanTag(List<String> vlanTag) {
    this.vlanTag = vlanTag;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestNfpRule vlanTag(List<String> vlanTag) {
    this.vlanTag = vlanTag;
    return this;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestNfpRule addVlanTagItem(String vlanTagItem) {
    this.vlanTag.add(vlanTagItem);
    return this;
  }

 /**
   * Indicates the L4 protocol, For IPv4 [7] this corresponds to the field called \&quot;Protocol\&quot; to identify the next level protocol. For IPv6 [28] this corresponds to the field is called the \&quot;Next Header\&quot; field. Permitted values: Any keyword defined in the IANA protocol registry [1], e.g.: TCP UDP ICMP 
   * @return protocol
  **/
  @JsonProperty("protocol")
  public String getProtocol() {
    if (protocol == null) {
      return null;
    }
    return protocol.value();
  }

  public void setProtocol(ProtocolEnum protocol) {
    this.protocol = protocol;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestNfpRule protocol(ProtocolEnum protocol) {
    this.protocol = protocol;
    return this;
  }

 /**
   * For IPv4 [7] a string of \&quot;0\&quot; and \&quot;1\&quot; digits that corresponds to the 6-bit Differentiated Services Code Point (DSCP) field of the IP header. For IPv6 [28] a string of \&quot;0\&quot; and \&quot;1\&quot; digits that corresponds to the 6 differentiated services bits of the traffic class header field 
   * @return dscp
  **/
  @JsonProperty("dscp")
  public String getDscp() {
    return dscp;
  }

  public void setDscp(String dscp) {
    this.dscp = dscp;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestNfpRule dscp(String dscp) {
    this.dscp = dscp;
    return this;
  }

 /**
   * Get sourcePortRange
   * @return sourcePortRange
  **/
  @JsonProperty("sourcePortRange")
  public NsInstancesnsInstanceIdupdateUpdateNsRequestNfpRuleSourcePortRange getSourcePortRange() {
    return sourcePortRange;
  }

  public void setSourcePortRange(NsInstancesnsInstanceIdupdateUpdateNsRequestNfpRuleSourcePortRange sourcePortRange) {
    this.sourcePortRange = sourcePortRange;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestNfpRule sourcePortRange(NsInstancesnsInstanceIdupdateUpdateNsRequestNfpRuleSourcePortRange sourcePortRange) {
    this.sourcePortRange = sourcePortRange;
    return this;
  }

 /**
   * Get destinationPortRange
   * @return destinationPortRange
  **/
  @JsonProperty("destinationPortRange")
  public NsInstancesnsInstanceIdupdateUpdateNsRequestNfpRuleSourcePortRange getDestinationPortRange() {
    return destinationPortRange;
  }

  public void setDestinationPortRange(NsInstancesnsInstanceIdupdateUpdateNsRequestNfpRuleSourcePortRange destinationPortRange) {
    this.destinationPortRange = destinationPortRange;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestNfpRule destinationPortRange(NsInstancesnsInstanceIdupdateUpdateNsRequestNfpRuleSourcePortRange destinationPortRange) {
    this.destinationPortRange = destinationPortRange;
    return this;
  }

 /**
   * An IPV4 or IPV6 address range in CIDR format. For IPV4 address range, refer to IETF RFC 4632 [12]. For IPV6 address range, refer to IETF RFC 4291 [11]. 
   * @return sourceIpAddressPrefix
  **/
  @JsonProperty("sourceIpAddressPrefix")
  public String getSourceIpAddressPrefix() {
    return sourceIpAddressPrefix;
  }

  public void setSourceIpAddressPrefix(String sourceIpAddressPrefix) {
    this.sourceIpAddressPrefix = sourceIpAddressPrefix;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestNfpRule sourceIpAddressPrefix(String sourceIpAddressPrefix) {
    this.sourceIpAddressPrefix = sourceIpAddressPrefix;
    return this;
  }

 /**
   * An IPV4 or IPV6 address range in CIDR format. For IPV4 address range, refer to IETF RFC 4632 [12]. For IPV6 address range, refer to IETF RFC 4291 [11]. 
   * @return destinationIpAddressPrefix
  **/
  @JsonProperty("destinationIpAddressPrefix")
  public String getDestinationIpAddressPrefix() {
    return destinationIpAddressPrefix;
  }

  public void setDestinationIpAddressPrefix(String destinationIpAddressPrefix) {
    this.destinationIpAddressPrefix = destinationIpAddressPrefix;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestNfpRule destinationIpAddressPrefix(String destinationIpAddressPrefix) {
    this.destinationIpAddressPrefix = destinationIpAddressPrefix;
    return this;
  }

 /**
   * Indicates values of specific bits in a frame. 
   * @return extendedCriteria
  **/
  @JsonProperty("extendedCriteria")
  public List<NsInstancesnsInstanceIdupdateUpdateNsRequestNfpRuleExtendedCriteria> getExtendedCriteria() {
    return extendedCriteria;
  }

  public void setExtendedCriteria(List<NsInstancesnsInstanceIdupdateUpdateNsRequestNfpRuleExtendedCriteria> extendedCriteria) {
    this.extendedCriteria = extendedCriteria;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestNfpRule extendedCriteria(List<NsInstancesnsInstanceIdupdateUpdateNsRequestNfpRuleExtendedCriteria> extendedCriteria) {
    this.extendedCriteria = extendedCriteria;
    return this;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestNfpRule addExtendedCriteriaItem(NsInstancesnsInstanceIdupdateUpdateNsRequestNfpRuleExtendedCriteria extendedCriteriaItem) {
    this.extendedCriteria.add(extendedCriteriaItem);
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesnsInstanceIdupdateUpdateNsRequestNfpRule {\n");
    
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
  private static String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

