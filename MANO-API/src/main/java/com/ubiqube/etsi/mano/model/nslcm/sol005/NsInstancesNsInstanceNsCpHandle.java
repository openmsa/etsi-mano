package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
  * This type represents an identifier of the CP or SAP instance.  It shall comply with the provisions defined in Table 6.5.3.56-1. 
 **/
@ApiModel(description="This type represents an identifier of the CP or SAP instance.  It shall comply with the provisions defined in Table 6.5.3.56-1. ")
public class NsInstancesNsInstanceNsCpHandle  {
  
  @ApiModelProperty(value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String vnfInstanceId = null;

  @ApiModelProperty(value = "An identifier that is unique for the respective type within a VNF instance, but may not be globally unique. ")
 /**
   * An identifier that is unique for the respective type within a VNF instance, but may not be globally unique. 
  **/
  private String vnfExtCpInstanceId = null;

  @ApiModelProperty(value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String pnfInfoId = null;

  @ApiModelProperty(value = "Identifier of the CP in the scope of the PNF. ")
 /**
   * Identifier of the CP in the scope of the PNF. 
  **/
  private String pnfExtCpInstanceId = null;

  @ApiModelProperty(value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String nsInstanceId = null;

  @ApiModelProperty(value = "An identifier that is unique with respect to a NS.  Representation: string of variable length. ")
 /**
   * An identifier that is unique with respect to a NS.  Representation: string of variable length. 
  **/
  private String nsSapInstanceId = null;
 /**
   * An identifier with the intention of being globally unique. 
   * @return vnfInstanceId
  **/
  @JsonProperty("vnfInstanceId")
  public String getVnfInstanceId() {
    return vnfInstanceId;
  }

  public void setVnfInstanceId(String vnfInstanceId) {
    this.vnfInstanceId = vnfInstanceId;
  }

  public NsInstancesNsInstanceNsCpHandle vnfInstanceId(String vnfInstanceId) {
    this.vnfInstanceId = vnfInstanceId;
    return this;
  }

 /**
   * An identifier that is unique for the respective type within a VNF instance, but may not be globally unique. 
   * @return vnfExtCpInstanceId
  **/
  @JsonProperty("vnfExtCpInstanceId")
  public String getVnfExtCpInstanceId() {
    return vnfExtCpInstanceId;
  }

  public void setVnfExtCpInstanceId(String vnfExtCpInstanceId) {
    this.vnfExtCpInstanceId = vnfExtCpInstanceId;
  }

  public NsInstancesNsInstanceNsCpHandle vnfExtCpInstanceId(String vnfExtCpInstanceId) {
    this.vnfExtCpInstanceId = vnfExtCpInstanceId;
    return this;
  }

 /**
   * An identifier with the intention of being globally unique. 
   * @return pnfInfoId
  **/
  @JsonProperty("pnfInfoId")
  public String getPnfInfoId() {
    return pnfInfoId;
  }

  public void setPnfInfoId(String pnfInfoId) {
    this.pnfInfoId = pnfInfoId;
  }

  public NsInstancesNsInstanceNsCpHandle pnfInfoId(String pnfInfoId) {
    this.pnfInfoId = pnfInfoId;
    return this;
  }

 /**
   * Identifier of the CP in the scope of the PNF. 
   * @return pnfExtCpInstanceId
  **/
  @JsonProperty("pnfExtCpInstanceId")
  public String getPnfExtCpInstanceId() {
    return pnfExtCpInstanceId;
  }

  public void setPnfExtCpInstanceId(String pnfExtCpInstanceId) {
    this.pnfExtCpInstanceId = pnfExtCpInstanceId;
  }

  public NsInstancesNsInstanceNsCpHandle pnfExtCpInstanceId(String pnfExtCpInstanceId) {
    this.pnfExtCpInstanceId = pnfExtCpInstanceId;
    return this;
  }

 /**
   * An identifier with the intention of being globally unique. 
   * @return nsInstanceId
  **/
  @JsonProperty("nsInstanceId")
  public String getNsInstanceId() {
    return nsInstanceId;
  }

  public void setNsInstanceId(String nsInstanceId) {
    this.nsInstanceId = nsInstanceId;
  }

  public NsInstancesNsInstanceNsCpHandle nsInstanceId(String nsInstanceId) {
    this.nsInstanceId = nsInstanceId;
    return this;
  }

 /**
   * An identifier that is unique with respect to a NS.  Representation: string of variable length. 
   * @return nsSapInstanceId
  **/
  @JsonProperty("nsSapInstanceId")
  public String getNsSapInstanceId() {
    return nsSapInstanceId;
  }

  public void setNsSapInstanceId(String nsSapInstanceId) {
    this.nsSapInstanceId = nsSapInstanceId;
  }

  public NsInstancesNsInstanceNsCpHandle nsSapInstanceId(String nsSapInstanceId) {
    this.nsSapInstanceId = nsSapInstanceId;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesNsInstanceNsCpHandle {\n");
    
    sb.append("    vnfInstanceId: ").append(toIndentedString(vnfInstanceId)).append("\n");
    sb.append("    vnfExtCpInstanceId: ").append(toIndentedString(vnfExtCpInstanceId)).append("\n");
    sb.append("    pnfInfoId: ").append(toIndentedString(pnfInfoId)).append("\n");
    sb.append("    pnfExtCpInstanceId: ").append(toIndentedString(pnfExtCpInstanceId)).append("\n");
    sb.append("    nsInstanceId: ").append(toIndentedString(nsInstanceId)).append("\n");
    sb.append("    nsSapInstanceId: ").append(toIndentedString(nsSapInstanceId)).append("\n");
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

