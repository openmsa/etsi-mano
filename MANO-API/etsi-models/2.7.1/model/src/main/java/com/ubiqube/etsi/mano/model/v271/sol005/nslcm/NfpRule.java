/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.ubiqube.etsi.mano.model.v271.sol005.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.model.v271.sol005.nslcm.Mask;
import com.ubiqube.etsi.mano.model.v271.sol005.nslcm.PortRange;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * The NfpRule data type is an expression of the conditions that shall be met in order for the NFP to be applicable to the packet. The condition acts as a flow classifier and it is met only if all the values expressed in the condition are matched by those in the packet. It shall comply with the provisions defined in Table 6.5.3.40-1. 
 */
@ApiModel(description = "The NfpRule data type is an expression of the conditions that shall be met in order for the NFP to be applicable to the packet. The condition acts as a flow classifier and it is met only if all the values expressed in the condition are matched by those in the packet. It shall comply with the provisions defined in Table 6.5.3.40-1. ")
@Validated
public class NfpRule   {
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

    private String value;

    EtherTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static EtherTypeEnum fromValue(String text) {
      for (EtherTypeEnum b : EtherTypeEnum.values()) {
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
   * Indicates the L4 protocol, For IPv4 [7] this corresponds to the field called \"Protocol\" to identify the next level protocol. For IPv6 [28] this corresponds to the field is called the \"Next Header\" field. Permitted values: Any keyword defined in the IANA protocol registry [1], e.g.: TCP UDP ICMP 
   */
  public enum ProtocolEnum {
    TCP("TCP"),
    
    UDP("UDP"),
    
    ICMP("ICMP");

    private String value;

    ProtocolEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static ProtocolEnum fromValue(String text) {
      for (ProtocolEnum b : ProtocolEnum.values()) {
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

  public NfpRule etherDestinationAddress(String etherDestinationAddress) {
    this.etherDestinationAddress = etherDestinationAddress;
    return this;
  }

  /**
   * Get etherDestinationAddress
   * @return etherDestinationAddress
  **/
  @ApiModelProperty(value = "")
  
    public String getEtherDestinationAddress() {
    return etherDestinationAddress;
  }

  public void setEtherDestinationAddress(String etherDestinationAddress) {
    this.etherDestinationAddress = etherDestinationAddress;
  }

  public NfpRule etherSourceAddress(String etherSourceAddress) {
    this.etherSourceAddress = etherSourceAddress;
    return this;
  }

  /**
   * Get etherSourceAddress
   * @return etherSourceAddress
  **/
  @ApiModelProperty(value = "")
  
    public String getEtherSourceAddress() {
    return etherSourceAddress;
  }

  public void setEtherSourceAddress(String etherSourceAddress) {
    this.etherSourceAddress = etherSourceAddress;
  }

  public NfpRule etherType(EtherTypeEnum etherType) {
    this.etherType = etherType;
    return this;
  }

  /**
   * Human readable description for the VNFFG. 
   * @return etherType
  **/
  @ApiModelProperty(value = "Human readable description for the VNFFG. ")
  
    public EtherTypeEnum getEtherType() {
    return etherType;
  }

  public void setEtherType(EtherTypeEnum etherType) {
    this.etherType = etherType;
  }

  public NfpRule vlanTag(List<String> vlanTag) {
    this.vlanTag = vlanTag;
    return this;
  }

  public NfpRule addVlanTagItem(String vlanTagItem) {
    if (this.vlanTag == null) {
      this.vlanTag = new ArrayList<>();
    }
    this.vlanTag.add(vlanTagItem);
    return this;
  }

  /**
   * Indicates a VLAN identifier in an IEEE 802.1Q-2018 tag [6] Multiple tags can be included for QinQ stacking. See note. 
   * @return vlanTag
  **/
  @ApiModelProperty(value = "Indicates a VLAN identifier in an IEEE 802.1Q-2018 tag [6] Multiple tags can be included for QinQ stacking. See note. ")
  
    public List<String> getVlanTag() {
    return vlanTag;
  }

  public void setVlanTag(List<String> vlanTag) {
    this.vlanTag = vlanTag;
  }

  public NfpRule protocol(ProtocolEnum protocol) {
    this.protocol = protocol;
    return this;
  }

  /**
   * Indicates the L4 protocol, For IPv4 [7] this corresponds to the field called \"Protocol\" to identify the next level protocol. For IPv6 [28] this corresponds to the field is called the \"Next Header\" field. Permitted values: Any keyword defined in the IANA protocol registry [1], e.g.: TCP UDP ICMP 
   * @return protocol
  **/
  @ApiModelProperty(value = "Indicates the L4 protocol, For IPv4 [7] this corresponds to the field called \"Protocol\" to identify the next level protocol. For IPv6 [28] this corresponds to the field is called the \"Next Header\" field. Permitted values: Any keyword defined in the IANA protocol registry [1], e.g.: TCP UDP ICMP ")
  
    public ProtocolEnum getProtocol() {
    return protocol;
  }

  public void setProtocol(ProtocolEnum protocol) {
    this.protocol = protocol;
  }

  public NfpRule dscp(String dscp) {
    this.dscp = dscp;
    return this;
  }

  /**
   * For IPv4 [7] a string of \"0\" and \"1\" digits that corresponds to the 6-bit Differentiated Services Code Point (DSCP) field of the IP header. For IPv6 [28] a string of \"0\" and \"1\" digits that corresponds to the 6 differentiated services bits of the traffic class header field 
   * @return dscp
  **/
  @ApiModelProperty(value = "For IPv4 [7] a string of \"0\" and \"1\" digits that corresponds to the 6-bit Differentiated Services Code Point (DSCP) field of the IP header. For IPv6 [28] a string of \"0\" and \"1\" digits that corresponds to the 6 differentiated services bits of the traffic class header field ")
  
    public String getDscp() {
    return dscp;
  }

  public void setDscp(String dscp) {
    this.dscp = dscp;
  }

  public NfpRule sourcePortRange(PortRange sourcePortRange) {
    this.sourcePortRange = sourcePortRange;
    return this;
  }

  /**
   * Get sourcePortRange
   * @return sourcePortRange
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public PortRange getSourcePortRange() {
    return sourcePortRange;
  }

  public void setSourcePortRange(PortRange sourcePortRange) {
    this.sourcePortRange = sourcePortRange;
  }

  public NfpRule destinationPortRange(PortRange destinationPortRange) {
    this.destinationPortRange = destinationPortRange;
    return this;
  }

  /**
   * Get destinationPortRange
   * @return destinationPortRange
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public PortRange getDestinationPortRange() {
    return destinationPortRange;
  }

  public void setDestinationPortRange(PortRange destinationPortRange) {
    this.destinationPortRange = destinationPortRange;
  }

  public NfpRule sourceIpAddressPrefix(String sourceIpAddressPrefix) {
    this.sourceIpAddressPrefix = sourceIpAddressPrefix;
    return this;
  }

  /**
   * Get sourceIpAddressPrefix
   * @return sourceIpAddressPrefix
  **/
  @ApiModelProperty(value = "")
  
    public String getSourceIpAddressPrefix() {
    return sourceIpAddressPrefix;
  }

  public void setSourceIpAddressPrefix(String sourceIpAddressPrefix) {
    this.sourceIpAddressPrefix = sourceIpAddressPrefix;
  }

  public NfpRule destinationIpAddressPrefix(String destinationIpAddressPrefix) {
    this.destinationIpAddressPrefix = destinationIpAddressPrefix;
    return this;
  }

  /**
   * Get destinationIpAddressPrefix
   * @return destinationIpAddressPrefix
  **/
  @ApiModelProperty(value = "")
  
    public String getDestinationIpAddressPrefix() {
    return destinationIpAddressPrefix;
  }

  public void setDestinationIpAddressPrefix(String destinationIpAddressPrefix) {
    this.destinationIpAddressPrefix = destinationIpAddressPrefix;
  }

  public NfpRule extendedCriteria(List<Mask> extendedCriteria) {
    this.extendedCriteria = extendedCriteria;
    return this;
  }

  public NfpRule addExtendedCriteriaItem(Mask extendedCriteriaItem) {
    if (this.extendedCriteria == null) {
      this.extendedCriteria = new ArrayList<>();
    }
    this.extendedCriteria.add(extendedCriteriaItem);
    return this;
  }

  /**
   * Indicates values of specific bits in a frame. 
   * @return extendedCriteria
  **/
  @ApiModelProperty(value = "Indicates values of specific bits in a frame. ")
      @Valid
    public List<Mask> getExtendedCriteria() {
    return extendedCriteria;
  }

  public void setExtendedCriteria(List<Mask> extendedCriteria) {
    this.extendedCriteria = extendedCriteria;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NfpRule nfpRule = (NfpRule) o;
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
    StringBuilder sb = new StringBuilder();
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
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
