package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
  * This type represents information about a network address that has been assigned.  It shall comply with the provisions defined in Table 6.5.3.18-1. 
 **/
@ApiModel(description="This type represents information about a network address that has been assigned.  It shall comply with the provisions defined in Table 6.5.3.18-1. ")
public class NsInstancesNsInstanceInstantiatedVnfInfoIpOverEthernet  {
  
  @ApiModelProperty(required = true, value = "A MAC address. Representation: string that consists of groups of two hexadecimal digits, separated by hyphens or colons. ")
 /**
   * A MAC address. Representation: string that consists of groups of two hexadecimal digits, separated by hyphens or colons. 
  **/
  private String macAddress = null;

  @ApiModelProperty(required = true, value = "Addresses assigned to the CP instance. Each entry represents IP addresses assigned by fixed or dynamic IP address assignment per subnet. ")
  @Valid
 /**
   * Addresses assigned to the CP instance. Each entry represents IP addresses assigned by fixed or dynamic IP address assignment per subnet. 
  **/
  private List<NsInstancesNsInstanceInstantiatedVnfInfoIpOverEthernetIpAddresses> ipAddresses = new ArrayList<NsInstancesNsInstanceInstantiatedVnfInfoIpOverEthernetIpAddresses>();


@XmlType(name="TypeEnum")
@XmlEnum(String.class)
public enum TypeEnum {

@XmlEnumValue("PV4") PV4(String.valueOf("PV4")), @XmlEnumValue("PV6") PV6(String.valueOf("PV6"));


    private String value;

    TypeEnum (String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static TypeEnum fromValue(String v) {
        for (TypeEnum b : TypeEnum.values()) {
            if (String.valueOf(b.value).equals(v)) {
                return b;
            }
        }
        return null;
    }
}

  @ApiModelProperty(value = "The type of the IP addresses ")
 /**
   * The type of the IP addresses 
  **/
  private TypeEnum type = null;

  @ApiModelProperty(required = true, value = "An IPV4 or IPV6 address. Representation: In case of an IPV4 address, string that consists of four decimal integers separated by dots, each integer ranging from 0 to 255. In case of an IPV6 address, string that  consists of groups of zero to four hexadecimal digits, separated by colons. ")
 /**
   * An IPV4 or IPV6 address. Representation: In case of an IPV4 address, string that consists of four decimal integers separated by dots, each integer ranging from 0 to 255. In case of an IPV6 address, string that  consists of groups of zero to four hexadecimal digits, separated by colons. 
  **/
  private String addresses = null;

  @ApiModelProperty(value = "Indicates whether this set of addresses was assigned dynamically (true) or based on address information provided as input from the API consumer (false). Shall be present if \"addresses\" is present and shall be absent otherwise. ")
 /**
   * Indicates whether this set of addresses was assigned dynamically (true) or based on address information provided as input from the API consumer (false). Shall be present if \"addresses\" is present and shall be absent otherwise. 
  **/
  private Boolean isDynamic = null;

  @ApiModelProperty(required = true, value = "")
  @Valid
  private NsInstancesNsInstanceInstantiatedVnfInfoIpOverEthernetAddressRange1 addressRange = null;

  @ApiModelProperty(value = "An IPV4 or IPV6 address. Representation: In case of an IPV4 address, string that consists of four decimal integers separated by dots, each integer ranging from 0 to 255. In case of an IPV6 address, string that  consists of groups of zero to four hexadecimal digits, separated by colons. ")
 /**
   * An IPV4 or IPV6 address. Representation: In case of an IPV4 address, string that consists of four decimal integers separated by dots, each integer ranging from 0 to 255. In case of an IPV6 address, string that  consists of groups of zero to four hexadecimal digits, separated by colons. 
  **/
  private String minAddress = null;

  @ApiModelProperty(value = "An IPV4 or IPV6 address. Representation: In case of an IPV4 address, string that consists of four decimal integers separated by dots, each integer ranging from 0 to 255. In case of an IPV6 address, string that  consists of groups of zero to four hexadecimal digits, separated by colons. ")
 /**
   * An IPV4 or IPV6 address. Representation: In case of an IPV4 address, string that consists of four decimal integers separated by dots, each integer ranging from 0 to 255. In case of an IPV6 address, string that  consists of groups of zero to four hexadecimal digits, separated by colons. 
  **/
  private String maxAddress = null;

  @ApiModelProperty(required = true, value = "An identifier maintained by the VIM or other resource provider. It is expected to be unique within the VIM instance. ")
 /**
   * An identifier maintained by the VIM or other resource provider. It is expected to be unique within the VIM instance. 
  **/
  private String subnetId = null;
 /**
   * A MAC address. Representation: string that consists of groups of two hexadecimal digits, separated by hyphens or colons. 
   * @return macAddress
  **/
  @JsonProperty("macAddress")
  @NotNull
  public String getMacAddress() {
    return macAddress;
  }

  public void setMacAddress(String macAddress) {
    this.macAddress = macAddress;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfoIpOverEthernet macAddress(String macAddress) {
    this.macAddress = macAddress;
    return this;
  }

 /**
   * Addresses assigned to the CP instance. Each entry represents IP addresses assigned by fixed or dynamic IP address assignment per subnet. 
   * @return ipAddresses
  **/
  @JsonProperty("ipAddresses")
  @NotNull
  public List<NsInstancesNsInstanceInstantiatedVnfInfoIpOverEthernetIpAddresses> getIpAddresses() {
    return ipAddresses;
  }

  public void setIpAddresses(List<NsInstancesNsInstanceInstantiatedVnfInfoIpOverEthernetIpAddresses> ipAddresses) {
    this.ipAddresses = ipAddresses;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfoIpOverEthernet ipAddresses(List<NsInstancesNsInstanceInstantiatedVnfInfoIpOverEthernetIpAddresses> ipAddresses) {
    this.ipAddresses = ipAddresses;
    return this;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfoIpOverEthernet addIpAddressesItem(NsInstancesNsInstanceInstantiatedVnfInfoIpOverEthernetIpAddresses ipAddressesItem) {
    this.ipAddresses.add(ipAddressesItem);
    return this;
  }

 /**
   * The type of the IP addresses 
   * @return type
  **/
  @JsonProperty("type")
  public String getType() {
    if (type == null) {
      return null;
    }
    return type.value();
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfoIpOverEthernet type(TypeEnum type) {
    this.type = type;
    return this;
  }

 /**
   * An IPV4 or IPV6 address. Representation: In case of an IPV4 address, string that consists of four decimal integers separated by dots, each integer ranging from 0 to 255. In case of an IPV6 address, string that  consists of groups of zero to four hexadecimal digits, separated by colons. 
   * @return addresses
  **/
  @JsonProperty("addresses")
  @NotNull
  public String getAddresses() {
    return addresses;
  }

  public void setAddresses(String addresses) {
    this.addresses = addresses;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfoIpOverEthernet addresses(String addresses) {
    this.addresses = addresses;
    return this;
  }

 /**
   * Indicates whether this set of addresses was assigned dynamically (true) or based on address information provided as input from the API consumer (false). Shall be present if \&quot;addresses\&quot; is present and shall be absent otherwise. 
   * @return isDynamic
  **/
  @JsonProperty("isDynamic")
  public Boolean isIsDynamic() {
    return isDynamic;
  }

  public void setIsDynamic(Boolean isDynamic) {
    this.isDynamic = isDynamic;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfoIpOverEthernet isDynamic(Boolean isDynamic) {
    this.isDynamic = isDynamic;
    return this;
  }

 /**
   * Get addressRange
   * @return addressRange
  **/
  @JsonProperty("addressRange")
  @NotNull
  public NsInstancesNsInstanceInstantiatedVnfInfoIpOverEthernetAddressRange1 getAddressRange() {
    return addressRange;
  }

  public void setAddressRange(NsInstancesNsInstanceInstantiatedVnfInfoIpOverEthernetAddressRange1 addressRange) {
    this.addressRange = addressRange;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfoIpOverEthernet addressRange(NsInstancesNsInstanceInstantiatedVnfInfoIpOverEthernetAddressRange1 addressRange) {
    this.addressRange = addressRange;
    return this;
  }

 /**
   * An IPV4 or IPV6 address. Representation: In case of an IPV4 address, string that consists of four decimal integers separated by dots, each integer ranging from 0 to 255. In case of an IPV6 address, string that  consists of groups of zero to four hexadecimal digits, separated by colons. 
   * @return minAddress
  **/
  @JsonProperty("minAddress")
  public String getMinAddress() {
    return minAddress;
  }

  public void setMinAddress(String minAddress) {
    this.minAddress = minAddress;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfoIpOverEthernet minAddress(String minAddress) {
    this.minAddress = minAddress;
    return this;
  }

 /**
   * An IPV4 or IPV6 address. Representation: In case of an IPV4 address, string that consists of four decimal integers separated by dots, each integer ranging from 0 to 255. In case of an IPV6 address, string that  consists of groups of zero to four hexadecimal digits, separated by colons. 
   * @return maxAddress
  **/
  @JsonProperty("maxAddress")
  public String getMaxAddress() {
    return maxAddress;
  }

  public void setMaxAddress(String maxAddress) {
    this.maxAddress = maxAddress;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfoIpOverEthernet maxAddress(String maxAddress) {
    this.maxAddress = maxAddress;
    return this;
  }

 /**
   * An identifier maintained by the VIM or other resource provider. It is expected to be unique within the VIM instance. 
   * @return subnetId
  **/
  @JsonProperty("subnetId")
  @NotNull
  public String getSubnetId() {
    return subnetId;
  }

  public void setSubnetId(String subnetId) {
    this.subnetId = subnetId;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfoIpOverEthernet subnetId(String subnetId) {
    this.subnetId = subnetId;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesNsInstanceInstantiatedVnfInfoIpOverEthernet {\n");
    
    sb.append("    macAddress: ").append(toIndentedString(macAddress)).append("\n");
    sb.append("    ipAddresses: ").append(toIndentedString(ipAddresses)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    addresses: ").append(toIndentedString(addresses)).append("\n");
    sb.append("    isDynamic: ").append(toIndentedString(isDynamic)).append("\n");
    sb.append("    addressRange: ").append(toIndentedString(addressRange)).append("\n");
    sb.append("    minAddress: ").append(toIndentedString(minAddress)).append("\n");
    sb.append("    maxAddress: ").append(toIndentedString(maxAddress)).append("\n");
    sb.append("    subnetId: ").append(toIndentedString(subnetId)).append("\n");
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

