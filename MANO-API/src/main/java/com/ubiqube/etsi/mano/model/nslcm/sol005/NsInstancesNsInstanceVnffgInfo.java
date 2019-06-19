package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
  * Information on the VNFFG(s) of the NS instance. 
 **/
@ApiModel(description="Information on the VNFFG(s) of the NS instance. ")
public class NsInstancesNsInstanceVnffgInfo  {
  
  @ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String id = null;

  @ApiModelProperty(required = true, value = "An identifier that is unique within a NS descriptor. Representation: string of variable length. ")
 /**
   * An identifier that is unique within a NS descriptor. Representation: string of variable length. 
  **/
  private String vnffgdId = null;

  @ApiModelProperty(required = true, value = "Identifier(s) of the constituent VNF instance(s) of this VNFFG instance. ")
 /**
   * Identifier(s) of the constituent VNF instance(s) of this VNFFG instance. 
  **/
  private List<String> vnfInstanceId = new ArrayList<String>();

  @ApiModelProperty(value = "Identifier(s) of the constituent PNF instance(s) of this VNFFG instance. ")
 /**
   * Identifier(s) of the constituent PNF instance(s) of this VNFFG instance. 
  **/
  private List<String> pnfdInfoId = null;

  @ApiModelProperty(value = "Identifier(s) of the constituent VL instance(s) of this VNFFG instance. ")
 /**
   * Identifier(s) of the constituent VL instance(s) of this VNFFG instance. 
  **/
  private List<String> nsVirtualLinkInfoId = null;

  @ApiModelProperty(value = "Identifiers of the CP instances attached to the constituent VNFs and PNFs or the SAP instances of the VNFFG. See note. ")
  @Valid
 /**
   * Identifiers of the CP instances attached to the constituent VNFs and PNFs or the SAP instances of the VNFFG. See note. 
  **/
  private List<NsInstancesNsInstanceNsCpHandle> nsCpHandle = null;
 /**
   * An identifier with the intention of being globally unique. 
   * @return id
  **/
  @JsonProperty("id")
  @NotNull
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public NsInstancesNsInstanceVnffgInfo id(String id) {
    this.id = id;
    return this;
  }

 /**
   * An identifier that is unique within a NS descriptor. Representation: string of variable length. 
   * @return vnffgdId
  **/
  @JsonProperty("vnffgdId")
  @NotNull
  public String getVnffgdId() {
    return vnffgdId;
  }

  public void setVnffgdId(String vnffgdId) {
    this.vnffgdId = vnffgdId;
  }

  public NsInstancesNsInstanceVnffgInfo vnffgdId(String vnffgdId) {
    this.vnffgdId = vnffgdId;
    return this;
  }

 /**
   * Identifier(s) of the constituent VNF instance(s) of this VNFFG instance. 
   * @return vnfInstanceId
  **/
  @JsonProperty("vnfInstanceId")
  @NotNull
  public List<String> getVnfInstanceId() {
    return vnfInstanceId;
  }

  public void setVnfInstanceId(List<String> vnfInstanceId) {
    this.vnfInstanceId = vnfInstanceId;
  }

  public NsInstancesNsInstanceVnffgInfo vnfInstanceId(List<String> vnfInstanceId) {
    this.vnfInstanceId = vnfInstanceId;
    return this;
  }

  public NsInstancesNsInstanceVnffgInfo addVnfInstanceIdItem(String vnfInstanceIdItem) {
    this.vnfInstanceId.add(vnfInstanceIdItem);
    return this;
  }

 /**
   * Identifier(s) of the constituent PNF instance(s) of this VNFFG instance. 
   * @return pnfdInfoId
  **/
  @JsonProperty("pnfdInfoId")
  public List<String> getPnfdInfoId() {
    return pnfdInfoId;
  }

  public void setPnfdInfoId(List<String> pnfdInfoId) {
    this.pnfdInfoId = pnfdInfoId;
  }

  public NsInstancesNsInstanceVnffgInfo pnfdInfoId(List<String> pnfdInfoId) {
    this.pnfdInfoId = pnfdInfoId;
    return this;
  }

  public NsInstancesNsInstanceVnffgInfo addPnfdInfoIdItem(String pnfdInfoIdItem) {
    this.pnfdInfoId.add(pnfdInfoIdItem);
    return this;
  }

 /**
   * Identifier(s) of the constituent VL instance(s) of this VNFFG instance. 
   * @return nsVirtualLinkInfoId
  **/
  @JsonProperty("nsVirtualLinkInfoId")
  public List<String> getNsVirtualLinkInfoId() {
    return nsVirtualLinkInfoId;
  }

  public void setNsVirtualLinkInfoId(List<String> nsVirtualLinkInfoId) {
    this.nsVirtualLinkInfoId = nsVirtualLinkInfoId;
  }

  public NsInstancesNsInstanceVnffgInfo nsVirtualLinkInfoId(List<String> nsVirtualLinkInfoId) {
    this.nsVirtualLinkInfoId = nsVirtualLinkInfoId;
    return this;
  }

  public NsInstancesNsInstanceVnffgInfo addNsVirtualLinkInfoIdItem(String nsVirtualLinkInfoIdItem) {
    this.nsVirtualLinkInfoId.add(nsVirtualLinkInfoIdItem);
    return this;
  }

 /**
   * Identifiers of the CP instances attached to the constituent VNFs and PNFs or the SAP instances of the VNFFG. See note. 
   * @return nsCpHandle
  **/
  @JsonProperty("nsCpHandle")
  public List<NsInstancesNsInstanceNsCpHandle> getNsCpHandle() {
    return nsCpHandle;
  }

  public void setNsCpHandle(List<NsInstancesNsInstanceNsCpHandle> nsCpHandle) {
    this.nsCpHandle = nsCpHandle;
  }

  public NsInstancesNsInstanceVnffgInfo nsCpHandle(List<NsInstancesNsInstanceNsCpHandle> nsCpHandle) {
    this.nsCpHandle = nsCpHandle;
    return this;
  }

  public NsInstancesNsInstanceVnffgInfo addNsCpHandleItem(NsInstancesNsInstanceNsCpHandle nsCpHandleItem) {
    this.nsCpHandle.add(nsCpHandleItem);
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesNsInstanceVnffgInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    vnffgdId: ").append(toIndentedString(vnffgdId)).append("\n");
    sb.append("    vnfInstanceId: ").append(toIndentedString(vnfInstanceId)).append("\n");
    sb.append("    pnfdInfoId: ").append(toIndentedString(pnfdInfoId)).append("\n");
    sb.append("    nsVirtualLinkInfoId: ").append(toIndentedString(nsVirtualLinkInfoId)).append("\n");
    sb.append("    nsCpHandle: ").append(toIndentedString(nsCpHandle)).append("\n");
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

