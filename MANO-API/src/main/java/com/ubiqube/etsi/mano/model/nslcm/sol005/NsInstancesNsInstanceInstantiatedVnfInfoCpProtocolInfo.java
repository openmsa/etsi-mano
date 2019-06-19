package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
  * This type describes the protocol layer(s) that a CP or SAP uses together with protocol-related information, like addresses. It shall comply with the provisions defined in Table 6.5.3.58-1. 
 **/
@ApiModel(description="This type describes the protocol layer(s) that a CP or SAP uses together with protocol-related information, like addresses. It shall comply with the provisions defined in Table 6.5.3.58-1. ")
public class NsInstancesNsInstanceInstantiatedVnfInfoCpProtocolInfo  {
  

@XmlType(name="LayerProtocolEnum")
@XmlEnum(String.class)
public enum LayerProtocolEnum {

@XmlEnumValue("IP_OVER_ETHERNET") IP_OVER_ETHERNET(String.valueOf("IP_OVER_ETHERNET"));


    private String value;

    LayerProtocolEnum (String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static LayerProtocolEnum fromValue(String v) {
        for (LayerProtocolEnum b : LayerProtocolEnum.values()) {
            if (String.valueOf(b.value).equals(v)) {
                return b;
            }
        }
        return null;
    }
}

  @ApiModelProperty(required = true, value = "The identifier of layer(s) and protocol(s) associated to the network address information. Permitted values: IP_OVER_ETHERNET See note. ")
 /**
   * The identifier of layer(s) and protocol(s) associated to the network address information. Permitted values: IP_OVER_ETHERNET See note. 
  **/
  private LayerProtocolEnum layerProtocol = null;

  @ApiModelProperty(required = true, value = "")
  @Valid
  private NsInstancesNsInstanceInstantiatedVnfInfoIpOverEthernet ipOverEthernet = null;
 /**
   * The identifier of layer(s) and protocol(s) associated to the network address information. Permitted values: IP_OVER_ETHERNET See note. 
   * @return layerProtocol
  **/
  @JsonProperty("layerProtocol")
  @NotNull
  public String getLayerProtocol() {
    if (layerProtocol == null) {
      return null;
    }
    return layerProtocol.value();
  }

  public void setLayerProtocol(LayerProtocolEnum layerProtocol) {
    this.layerProtocol = layerProtocol;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfoCpProtocolInfo layerProtocol(LayerProtocolEnum layerProtocol) {
    this.layerProtocol = layerProtocol;
    return this;
  }

 /**
   * Get ipOverEthernet
   * @return ipOverEthernet
  **/
  @JsonProperty("ipOverEthernet")
  @NotNull
  public NsInstancesNsInstanceInstantiatedVnfInfoIpOverEthernet getIpOverEthernet() {
    return ipOverEthernet;
  }

  public void setIpOverEthernet(NsInstancesNsInstanceInstantiatedVnfInfoIpOverEthernet ipOverEthernet) {
    this.ipOverEthernet = ipOverEthernet;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfoCpProtocolInfo ipOverEthernet(NsInstancesNsInstanceInstantiatedVnfInfoIpOverEthernet ipOverEthernet) {
    this.ipOverEthernet = ipOverEthernet;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesNsInstanceInstantiatedVnfInfoCpProtocolInfo {\n");
    
    sb.append("    layerProtocol: ").append(toIndentedString(layerProtocol)).append("\n");
    sb.append("    ipOverEthernet: ").append(toIndentedString(ipOverEthernet)).append("\n");
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

