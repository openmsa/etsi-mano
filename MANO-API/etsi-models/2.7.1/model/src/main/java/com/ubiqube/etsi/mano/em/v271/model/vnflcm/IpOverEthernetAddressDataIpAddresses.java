package com.ubiqube.etsi.mano.em.v271.model.vnflcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.em.v271.model.vnflcm.IpOverEthernetAddressDataAddressRange;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * IpOverEthernetAddressDataIpAddresses
 */
@Validated

public class IpOverEthernetAddressDataIpAddresses   {
  /**
   * The type of the IP addresses. Permitted values: IPV4, IPV6. 
   */
  public enum TypeEnum {
    IPV4("IPV4"),
    
    IPV6("IPV6");

    private String value;

    TypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TypeEnum fromValue(String text) {
      for (TypeEnum b : TypeEnum.values()) {
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

  public IpOverEthernetAddressDataIpAddresses type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * The type of the IP addresses. Permitted values: IPV4, IPV6. 
   * @return type
  **/
  @ApiModelProperty(required = true, value = "The type of the IP addresses. Permitted values: IPV4, IPV6. ")
  @NotNull


  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public IpOverEthernetAddressDataIpAddresses fixedAddresses(List<String> fixedAddresses) {
    this.fixedAddresses = fixedAddresses;
    return this;
  }

  public IpOverEthernetAddressDataIpAddresses addFixedAddressesItem(String fixedAddressesItem) {
    if (this.fixedAddresses == null) {
      this.fixedAddresses = new ArrayList<>();
    }
    this.fixedAddresses.add(fixedAddressesItem);
    return this;
  }

  /**
   * Fixed addresses to assign (from the subnet defined by \"subnetId\" if provided). Exactly one of \"fixedAddresses\", \"numDynamicAddresses\" or \"ipAddressRange\" shall be present. 
   * @return fixedAddresses
  **/
  @ApiModelProperty(value = "Fixed addresses to assign (from the subnet defined by \"subnetId\" if provided). Exactly one of \"fixedAddresses\", \"numDynamicAddresses\" or \"ipAddressRange\" shall be present. ")


  public List<String> getFixedAddresses() {
    return fixedAddresses;
  }

  public void setFixedAddresses(List<String> fixedAddresses) {
    this.fixedAddresses = fixedAddresses;
  }

  public IpOverEthernetAddressDataIpAddresses numDynamicAddresses(Integer numDynamicAddresses) {
    this.numDynamicAddresses = numDynamicAddresses;
    return this;
  }

  /**
   * Number of dynamic addresses to assign (from the subnet defined by \"subnetId\" if provided). Exactly one of \"fixedAddresses\", \"numDynamicAddresses\" or \"ipAddressRange\" shall be present. 
   * @return numDynamicAddresses
  **/
  @ApiModelProperty(value = "Number of dynamic addresses to assign (from the subnet defined by \"subnetId\" if provided). Exactly one of \"fixedAddresses\", \"numDynamicAddresses\" or \"ipAddressRange\" shall be present. ")


  public Integer getNumDynamicAddresses() {
    return numDynamicAddresses;
  }

  public void setNumDynamicAddresses(Integer numDynamicAddresses) {
    this.numDynamicAddresses = numDynamicAddresses;
  }

  public IpOverEthernetAddressDataIpAddresses addressRange(IpOverEthernetAddressDataAddressRange addressRange) {
    this.addressRange = addressRange;
    return this;
  }

  /**
   * Get addressRange
   * @return addressRange
  **/
  @ApiModelProperty(value = "")

  @Valid

  public IpOverEthernetAddressDataAddressRange getAddressRange() {
    return addressRange;
  }

  public void setAddressRange(IpOverEthernetAddressDataAddressRange addressRange) {
    this.addressRange = addressRange;
  }

  public IpOverEthernetAddressDataIpAddresses subnetId(String subnetId) {
    this.subnetId = subnetId;
    return this;
  }

  /**
   * Subnet defined by the identifier of the subnet resource in the VIM. In case this attribute is present, IP addresses from that subnet will be assigned; otherwise, IP addresses not bound to a subnet will be assigned. 
   * @return subnetId
  **/
  @ApiModelProperty(value = "Subnet defined by the identifier of the subnet resource in the VIM. In case this attribute is present, IP addresses from that subnet will be assigned; otherwise, IP addresses not bound to a subnet will be assigned. ")


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
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IpOverEthernetAddressDataIpAddresses ipOverEthernetAddressDataIpAddresses = (IpOverEthernetAddressDataIpAddresses) o;
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
    StringBuilder sb = new StringBuilder();
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
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

