package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

public class NsInstancesNsInstanceCpInfoIpOverEthernetIpAddresses  {
  

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

  @ApiModelProperty(value = "Fixed addresses to assign (from the subnet defined by \"subnetId\" if provided). Exactly one of \"fixedAddresses\", \"numDynamicAddresses\" or \"ipAddressRange\" shall be present. ")
 /**
   * Fixed addresses to assign (from the subnet defined by \"subnetId\" if provided). Exactly one of \"fixedAddresses\", \"numDynamicAddresses\" or \"ipAddressRange\" shall be present. 
  **/
  private List<String> fixedAddresses = null;

  @ApiModelProperty(value = "Number of dynamic addresses to assign (from the subnet defined by \"subnetId\" if provided). Exactly one of \"fixedAddresses\", \"numDynamicAddresses\" or \"ipAddressRange\" shall be present. ")
 /**
   * Number of dynamic addresses to assign (from the subnet defined by \"subnetId\" if provided). Exactly one of \"fixedAddresses\", \"numDynamicAddresses\" or \"ipAddressRange\" shall be present. 
  **/
  private Integer numDynamicAddresses = null;

  @ApiModelProperty(value = "")
  @Valid
  private NsInstancesNsInstanceCpInfoIpOverEthernetAddressRange addressRange = null;

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

  public NsInstancesNsInstanceCpInfoIpOverEthernetIpAddresses type(TypeEnum type) {
    this.type = type;
    return this;
  }

 /**
   * Fixed addresses to assign (from the subnet defined by \&quot;subnetId\&quot; if provided). Exactly one of \&quot;fixedAddresses\&quot;, \&quot;numDynamicAddresses\&quot; or \&quot;ipAddressRange\&quot; shall be present. 
   * @return fixedAddresses
  **/
  @JsonProperty("fixedAddresses")
  public List<String> getFixedAddresses() {
    return fixedAddresses;
  }

  public void setFixedAddresses(List<String> fixedAddresses) {
    this.fixedAddresses = fixedAddresses;
  }

  public NsInstancesNsInstanceCpInfoIpOverEthernetIpAddresses fixedAddresses(List<String> fixedAddresses) {
    this.fixedAddresses = fixedAddresses;
    return this;
  }

  public NsInstancesNsInstanceCpInfoIpOverEthernetIpAddresses addFixedAddressesItem(String fixedAddressesItem) {
    this.fixedAddresses.add(fixedAddressesItem);
    return this;
  }

 /**
   * Number of dynamic addresses to assign (from the subnet defined by \&quot;subnetId\&quot; if provided). Exactly one of \&quot;fixedAddresses\&quot;, \&quot;numDynamicAddresses\&quot; or \&quot;ipAddressRange\&quot; shall be present. 
   * @return numDynamicAddresses
  **/
  @JsonProperty("numDynamicAddresses")
  public Integer getNumDynamicAddresses() {
    return numDynamicAddresses;
  }

  public void setNumDynamicAddresses(Integer numDynamicAddresses) {
    this.numDynamicAddresses = numDynamicAddresses;
  }

  public NsInstancesNsInstanceCpInfoIpOverEthernetIpAddresses numDynamicAddresses(Integer numDynamicAddresses) {
    this.numDynamicAddresses = numDynamicAddresses;
    return this;
  }

 /**
   * Get addressRange
   * @return addressRange
  **/
  @JsonProperty("addressRange")
  public NsInstancesNsInstanceCpInfoIpOverEthernetAddressRange getAddressRange() {
    return addressRange;
  }

  public void setAddressRange(NsInstancesNsInstanceCpInfoIpOverEthernetAddressRange addressRange) {
    this.addressRange = addressRange;
  }

  public NsInstancesNsInstanceCpInfoIpOverEthernetIpAddresses addressRange(NsInstancesNsInstanceCpInfoIpOverEthernetAddressRange addressRange) {
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

  public NsInstancesNsInstanceCpInfoIpOverEthernetIpAddresses subnetId(String subnetId) {
    this.subnetId = subnetId;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesNsInstanceCpInfoIpOverEthernetIpAddresses {\n");
    
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
  private static String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

