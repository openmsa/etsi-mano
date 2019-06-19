package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
  * This type represents the information about a PNF that is part of an NS instance.  It shall comply with the provisions defined in Table 6.5.3.13-1. 
 **/
@ApiModel(description="This type represents the information about a PNF that is part of an NS instance.  It shall comply with the provisions defined in Table 6.5.3.13-1. ")
public class NsInstancesNsInstancePnfInfo  {
  
  @ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String pnfId = null;

  @ApiModelProperty(value = "Name of the PNF. ")
 /**
   * Name of the PNF. 
  **/
  private String pnfName = null;

  @ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String pnfdId = null;

  @ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String pnfdInfoId = null;

  @ApiModelProperty(required = true, value = "An identifier that is unique within a NS descriptor. Representation: string of variable length. ")
 /**
   * An identifier that is unique within a NS descriptor. Representation: string of variable length. 
  **/
  private String pnfProfileId = null;

  @ApiModelProperty(value = "")
  @Valid
  private NsInstancesNsInstanceCpInfo cpInfo = null;
 /**
   * An identifier with the intention of being globally unique. 
   * @return pnfId
  **/
  @JsonProperty("pnfId")
  @NotNull
  public String getPnfId() {
    return pnfId;
  }

  public void setPnfId(String pnfId) {
    this.pnfId = pnfId;
  }

  public NsInstancesNsInstancePnfInfo pnfId(String pnfId) {
    this.pnfId = pnfId;
    return this;
  }

 /**
   * Name of the PNF. 
   * @return pnfName
  **/
  @JsonProperty("pnfName")
  public String getPnfName() {
    return pnfName;
  }

  public void setPnfName(String pnfName) {
    this.pnfName = pnfName;
  }

  public NsInstancesNsInstancePnfInfo pnfName(String pnfName) {
    this.pnfName = pnfName;
    return this;
  }

 /**
   * An identifier with the intention of being globally unique. 
   * @return pnfdId
  **/
  @JsonProperty("pnfdId")
  @NotNull
  public String getPnfdId() {
    return pnfdId;
  }

  public void setPnfdId(String pnfdId) {
    this.pnfdId = pnfdId;
  }

  public NsInstancesNsInstancePnfInfo pnfdId(String pnfdId) {
    this.pnfdId = pnfdId;
    return this;
  }

 /**
   * An identifier with the intention of being globally unique. 
   * @return pnfdInfoId
  **/
  @JsonProperty("pnfdInfoId")
  @NotNull
  public String getPnfdInfoId() {
    return pnfdInfoId;
  }

  public void setPnfdInfoId(String pnfdInfoId) {
    this.pnfdInfoId = pnfdInfoId;
  }

  public NsInstancesNsInstancePnfInfo pnfdInfoId(String pnfdInfoId) {
    this.pnfdInfoId = pnfdInfoId;
    return this;
  }

 /**
   * An identifier that is unique within a NS descriptor. Representation: string of variable length. 
   * @return pnfProfileId
  **/
  @JsonProperty("pnfProfileId")
  @NotNull
  public String getPnfProfileId() {
    return pnfProfileId;
  }

  public void setPnfProfileId(String pnfProfileId) {
    this.pnfProfileId = pnfProfileId;
  }

  public NsInstancesNsInstancePnfInfo pnfProfileId(String pnfProfileId) {
    this.pnfProfileId = pnfProfileId;
    return this;
  }

 /**
   * Get cpInfo
   * @return cpInfo
  **/
  @JsonProperty("cpInfo")
  public NsInstancesNsInstanceCpInfo getCpInfo() {
    return cpInfo;
  }

  public void setCpInfo(NsInstancesNsInstanceCpInfo cpInfo) {
    this.cpInfo = cpInfo;
  }

  public NsInstancesNsInstancePnfInfo cpInfo(NsInstancesNsInstanceCpInfo cpInfo) {
    this.cpInfo = cpInfo;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesNsInstancePnfInfo {\n");
    
    sb.append("    pnfId: ").append(toIndentedString(pnfId)).append("\n");
    sb.append("    pnfName: ").append(toIndentedString(pnfName)).append("\n");
    sb.append("    pnfdId: ").append(toIndentedString(pnfdId)).append("\n");
    sb.append("    pnfdInfoId: ").append(toIndentedString(pnfdInfoId)).append("\n");
    sb.append("    pnfProfileId: ").append(toIndentedString(pnfProfileId)).append("\n");
    sb.append("    cpInfo: ").append(toIndentedString(cpInfo)).append("\n");
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

