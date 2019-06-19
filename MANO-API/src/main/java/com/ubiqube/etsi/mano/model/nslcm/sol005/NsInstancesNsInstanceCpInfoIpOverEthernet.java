package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.util.List;

/**
  * This type represents network address data for IP over Ethernet. 
 **/
@ApiModel(description="This type represents network address data for IP over Ethernet. ")
public class NsInstancesNsInstanceCpInfoIpOverEthernet  {
  
  @ApiModelProperty(value = "A MAC address. Representation: string that consists of groups of two hexadecimal digits, separated by hyphens or colons. ")
 /**
   * A MAC address. Representation: string that consists of groups of two hexadecimal digits, separated by hyphens or colons. 
  **/
  private String macAddress = null;

  @ApiModelProperty(value = "List of IP addresses to assign to the CP instance. Each entry represents IP address data for fixed or dynamic IP address assignment per subnet. If this attribute is not present, no IP address shall be assigned. ")
  @Valid
 /**
   * List of IP addresses to assign to the CP instance. Each entry represents IP address data for fixed or dynamic IP address assignment per subnet. If this attribute is not present, no IP address shall be assigned. 
  **/
  private List<NsInstancesNsInstanceCpInfoIpOverEthernetIpAddresses> ipAddresses = null;
 /**
   * A MAC address. Representation: string that consists of groups of two hexadecimal digits, separated by hyphens or colons. 
   * @return macAddress
  **/
  @JsonProperty("macAddress")
  public String getMacAddress() {
    return macAddress;
  }

  public void setMacAddress(String macAddress) {
    this.macAddress = macAddress;
  }

  public NsInstancesNsInstanceCpInfoIpOverEthernet macAddress(String macAddress) {
    this.macAddress = macAddress;
    return this;
  }

 /**
   * List of IP addresses to assign to the CP instance. Each entry represents IP address data for fixed or dynamic IP address assignment per subnet. If this attribute is not present, no IP address shall be assigned. 
   * @return ipAddresses
  **/
  @JsonProperty("ipAddresses")
  public List<NsInstancesNsInstanceCpInfoIpOverEthernetIpAddresses> getIpAddresses() {
    return ipAddresses;
  }

  public void setIpAddresses(List<NsInstancesNsInstanceCpInfoIpOverEthernetIpAddresses> ipAddresses) {
    this.ipAddresses = ipAddresses;
  }

  public NsInstancesNsInstanceCpInfoIpOverEthernet ipAddresses(List<NsInstancesNsInstanceCpInfoIpOverEthernetIpAddresses> ipAddresses) {
    this.ipAddresses = ipAddresses;
    return this;
  }

  public NsInstancesNsInstanceCpInfoIpOverEthernet addIpAddressesItem(NsInstancesNsInstanceCpInfoIpOverEthernetIpAddresses ipAddressesItem) {
    this.ipAddresses.add(ipAddressesItem);
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesNsInstanceCpInfoIpOverEthernet {\n");
    
    sb.append("    macAddress: ").append(toIndentedString(macAddress)).append("\n");
    sb.append("    ipAddresses: ").append(toIndentedString(ipAddresses)).append("\n");
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

