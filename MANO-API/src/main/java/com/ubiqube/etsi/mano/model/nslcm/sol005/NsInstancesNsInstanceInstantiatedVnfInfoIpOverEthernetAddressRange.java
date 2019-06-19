package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
  * An IP address range used, e.g., in case of egress connections. Exactly one of \"addresses\" or \"addressRange\" shall be present. 
 **/
@ApiModel(description="An IP address range used, e.g., in case of egress connections. Exactly one of \"addresses\" or \"addressRange\" shall be present. ")
public class NsInstancesNsInstanceInstantiatedVnfInfoIpOverEthernetAddressRange  {
  
  @ApiModelProperty(required = true, value = "An IPV4 or IPV6 address. Representation: In case of an IPV4 address, string that consists of four decimal integers separated by dots, each integer ranging from 0 to 255. In case of an IPV6 address, string that  consists of groups of zero to four hexadecimal digits, separated by colons. ")
 /**
   * An IPV4 or IPV6 address. Representation: In case of an IPV4 address, string that consists of four decimal integers separated by dots, each integer ranging from 0 to 255. In case of an IPV6 address, string that  consists of groups of zero to four hexadecimal digits, separated by colons. 
  **/
  private String minAddress = null;

  @ApiModelProperty(required = true, value = "An IPV4 or IPV6 address. Representation: In case of an IPV4 address, string that consists of four decimal integers separated by dots, each integer ranging from 0 to 255. In case of an IPV6 address, string that  consists of groups of zero to four hexadecimal digits, separated by colons. ")
 /**
   * An IPV4 or IPV6 address. Representation: In case of an IPV4 address, string that consists of four decimal integers separated by dots, each integer ranging from 0 to 255. In case of an IPV6 address, string that  consists of groups of zero to four hexadecimal digits, separated by colons. 
  **/
  private String maxAddress = null;
 /**
   * An IPV4 or IPV6 address. Representation: In case of an IPV4 address, string that consists of four decimal integers separated by dots, each integer ranging from 0 to 255. In case of an IPV6 address, string that  consists of groups of zero to four hexadecimal digits, separated by colons. 
   * @return minAddress
  **/
  @JsonProperty("minAddress")
  @NotNull
  public String getMinAddress() {
    return minAddress;
  }

  public void setMinAddress(String minAddress) {
    this.minAddress = minAddress;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfoIpOverEthernetAddressRange minAddress(String minAddress) {
    this.minAddress = minAddress;
    return this;
  }

 /**
   * An IPV4 or IPV6 address. Representation: In case of an IPV4 address, string that consists of four decimal integers separated by dots, each integer ranging from 0 to 255. In case of an IPV6 address, string that  consists of groups of zero to four hexadecimal digits, separated by colons. 
   * @return maxAddress
  **/
  @JsonProperty("maxAddress")
  @NotNull
  public String getMaxAddress() {
    return maxAddress;
  }

  public void setMaxAddress(String maxAddress) {
    this.maxAddress = maxAddress;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfoIpOverEthernetAddressRange maxAddress(String maxAddress) {
    this.maxAddress = maxAddress;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesNsInstanceInstantiatedVnfInfoIpOverEthernetAddressRange {\n");
    
    sb.append("    minAddress: ").append(toIndentedString(minAddress)).append("\n");
    sb.append("    maxAddress: ").append(toIndentedString(maxAddress)).append("\n");
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

