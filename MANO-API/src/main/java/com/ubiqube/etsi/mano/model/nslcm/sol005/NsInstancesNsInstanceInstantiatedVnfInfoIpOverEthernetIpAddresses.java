package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

public class NsInstancesNsInstanceInstantiatedVnfInfoIpOverEthernetIpAddresses  {
  

@XmlType(name="TypeEnum")
@XmlEnum(String.class)
public enum TypeEnum {

@XmlEnumValue("IPV4") IPV4(String.valueOf("IPV4")), @XmlEnumValue("IPV6") IPV6(String.valueOf("IPV6"));


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

  @ApiModelProperty(required = true, value = "The type of the IP addresses. Permitted values: IPV4, IPV6. ")
 /**
   * The type of the IP addresses. Permitted values: IPV4, IPV6. 
  **/
  private TypeEnum type = null;

  @ApiModelProperty(value = "Fixed addresses assigned (from the subnet defined by \"subnetId\" if provided). ")
 /**
   * Fixed addresses assigned (from the subnet defined by \"subnetId\" if provided). 
  **/
  private List<String> addresses = null;

  @ApiModelProperty(value = "Indicates whether this set of addresses was assigned dynamically (true) or based on address information provided as input from the API consumer (false). Shall be present if \"addresses\" is present and shall be absent otherwise. ")
 /**
   * Indicates whether this set of addresses was assigned dynamically (true) or based on address information provided as input from the API consumer (false). Shall be present if \"addresses\" is present and shall be absent otherwise. 
  **/
  private Boolean isDynamic = null;

  @ApiModelProperty(value = "")
  @Valid
  private NsInstancesNsInstanceInstantiatedVnfInfoIpOverEthernetAddressRange addressRange = null;

  @ApiModelProperty(value = "An identifier maintained by the VIM or other resource provider. It is expected to be unique within the VIM instance. ")
 /**
   * An identifier maintained by the VIM or other resource provider. It is expected to be unique within the VIM instance. 
  **/
  private String subnetId = null;
 /**
   * The type of the IP addresses. Permitted values: IPV4, IPV6. 
   * @return type
  **/
  @JsonProperty("type")
  @NotNull
  public String getType() {
    if (type == null) {
      return null;
    }
    return type.value();
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfoIpOverEthernetIpAddresses type(TypeEnum type) {
    this.type = type;
    return this;
  }

 /**
   * Fixed addresses assigned (from the subnet defined by \&quot;subnetId\&quot; if provided). 
   * @return addresses
  **/
  @JsonProperty("addresses")
  public List<String> getAddresses() {
    return addresses;
  }

  public void setAddresses(List<String> addresses) {
    this.addresses = addresses;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfoIpOverEthernetIpAddresses addresses(List<String> addresses) {
    this.addresses = addresses;
    return this;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfoIpOverEthernetIpAddresses addAddressesItem(String addressesItem) {
    this.addresses.add(addressesItem);
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

  public NsInstancesNsInstanceInstantiatedVnfInfoIpOverEthernetIpAddresses isDynamic(Boolean isDynamic) {
    this.isDynamic = isDynamic;
    return this;
  }

 /**
   * Get addressRange
   * @return addressRange
  **/
  @JsonProperty("addressRange")
  public NsInstancesNsInstanceInstantiatedVnfInfoIpOverEthernetAddressRange getAddressRange() {
    return addressRange;
  }

  public void setAddressRange(NsInstancesNsInstanceInstantiatedVnfInfoIpOverEthernetAddressRange addressRange) {
    this.addressRange = addressRange;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfoIpOverEthernetIpAddresses addressRange(NsInstancesNsInstanceInstantiatedVnfInfoIpOverEthernetAddressRange addressRange) {
    this.addressRange = addressRange;
    return this;
  }

 /**
   * An identifier maintained by the VIM or other resource provider. It is expected to be unique within the VIM instance. 
   * @return subnetId
  **/
  @JsonProperty("subnetId")
  public String getSubnetId() {
    return subnetId;
  }

  public void setSubnetId(String subnetId) {
    this.subnetId = subnetId;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfoIpOverEthernetIpAddresses subnetId(String subnetId) {
    this.subnetId = subnetId;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesNsInstanceInstantiatedVnfInfoIpOverEthernetIpAddresses {\n");
    
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    addresses: ").append(toIndentedString(addresses)).append("\n");
    sb.append("    isDynamic: ").append(toIndentedString(isDynamic)).append("\n");
    sb.append("    addressRange: ").append(toIndentedString(addressRange)).append("\n");
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

