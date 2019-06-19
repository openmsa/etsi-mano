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
  * This type represents network protocol data.  
 **/
@ApiModel(description="This type represents network protocol data.  ")
public class NsInstancesNsInstanceCpInfoCpProtocolData  {
  

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

  @ApiModelProperty(required = true, value = "Identifier of layer(s) and protocol(s). Permitted values: IP_OVER_ETHERNET. ")
 /**
   * Identifier of layer(s) and protocol(s). Permitted values: IP_OVER_ETHERNET. 
  **/
  private LayerProtocolEnum layerProtocol = null;

  @ApiModelProperty(value = "")
  @Valid
  private NsInstancesNsInstanceCpInfoIpOverEthernet ipOverEthernet = null;
 /**
   * Identifier of layer(s) and protocol(s). Permitted values: IP_OVER_ETHERNET. 
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

  public NsInstancesNsInstanceCpInfoCpProtocolData layerProtocol(LayerProtocolEnum layerProtocol) {
    this.layerProtocol = layerProtocol;
    return this;
  }

 /**
   * Get ipOverEthernet
   * @return ipOverEthernet
  **/
  @JsonProperty("ipOverEthernet")
  public NsInstancesNsInstanceCpInfoIpOverEthernet getIpOverEthernet() {
    return ipOverEthernet;
  }

  public void setIpOverEthernet(NsInstancesNsInstanceCpInfoIpOverEthernet ipOverEthernet) {
    this.ipOverEthernet = ipOverEthernet;
  }

  public NsInstancesNsInstanceCpInfoCpProtocolData ipOverEthernet(NsInstancesNsInstanceCpInfoIpOverEthernet ipOverEthernet) {
    this.ipOverEthernet = ipOverEthernet;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesNsInstanceCpInfoCpProtocolData {\n");
    
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

