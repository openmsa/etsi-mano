package com.ubiqube.etsi.mano.em.v261.model.vnfconfig;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.em.v261.model.vnfconfig.CpAddressAddress;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents configuration parameters of a CP instance address.    *  NOTE 1: Either \&quot;address\&quot; or \&quot;useDynamicAddress\&quot; shall be present.    *  NOTE 2: At least one of \&quot;macAddress\&quot; and \&quot;ipAddress\&quot; shall be present. 
 */
@ApiModel(description = "This type represents configuration parameters of a CP instance address.    *  NOTE 1: Either \"address\" or \"useDynamicAddress\" shall be present.    *  NOTE 2: At least one of \"macAddress\" and \"ipAddress\" shall be present. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-12-11T19:16:20.445+01:00")

public class CpAddress   {
  @JsonProperty("address")
  private CpAddressAddress address = null;

  @JsonProperty("useDynamicAddress")
  private Boolean useDynamicAddress = null;

  @JsonProperty("port")
  private Integer port = null;

  public CpAddress address(CpAddressAddress address) {
    this.address = address;
    return this;
  }

  /**
   * Get address
   * @return address
  **/
  @ApiModelProperty(value = "")

  @Valid

  public CpAddressAddress getAddress() {
    return address;
  }

  public void setAddress(CpAddressAddress address) {
    this.address = address;
  }

  public CpAddress useDynamicAddress(Boolean useDynamicAddress) {
    this.useDynamicAddress = useDynamicAddress;
    return this;
  }

  /**
   * Set to true if an address shall be assigned dynamically. Otherwise set to false. The default value shall be false. See NOTE 1. 
   * @return useDynamicAddress
  **/
  @ApiModelProperty(value = "Set to true if an address shall be assigned dynamically. Otherwise set to false. The default value shall be false. See NOTE 1. ")


  public Boolean isUseDynamicAddress() {
    return useDynamicAddress;
  }

  public void setUseDynamicAddress(Boolean useDynamicAddress) {
    this.useDynamicAddress = useDynamicAddress;
  }

  public CpAddress port(Integer port) {
    this.port = port;
    return this;
  }

  /**
   * The port assigned to the CP instance (e.g. IP port number, Ethernet port number, etc.). 
   * @return port
  **/
  @ApiModelProperty(value = "The port assigned to the CP instance (e.g. IP port number, Ethernet port number, etc.). ")


  public Integer getPort() {
    return port;
  }

  public void setPort(Integer port) {
    this.port = port;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CpAddress cpAddress = (CpAddress) o;
    return Objects.equals(this.address, cpAddress.address) &&
        Objects.equals(this.useDynamicAddress, cpAddress.useDynamicAddress) &&
        Objects.equals(this.port, cpAddress.port);
  }

  @Override
  public int hashCode() {
    return Objects.hash(address, useDynamicAddress, port);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CpAddress {\n");
    
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    useDynamicAddress: ").append(toIndentedString(useDynamicAddress)).append("\n");
    sb.append("    port: ").append(toIndentedString(port)).append("\n");
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

