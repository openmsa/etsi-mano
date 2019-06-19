package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public class NsInstancesNsInstanceInstantiatedVnfInfoVnfcCpInfo  {
  
  @ApiModelProperty(required = true, value = "An identifier that is unique for the respective type within a VNF instance, but may not be globally unique. ")
 /**
   * An identifier that is unique for the respective type within a VNF instance, but may not be globally unique. 
  **/
  private String id = null;

  @ApiModelProperty(required = true, value = "Identifier of the VNF Virtual Link Descriptor (VLD) in the VNFD. ")
 /**
   * Identifier of the VNF Virtual Link Descriptor (VLD) in the VNFD. 
  **/
  private String cpdId = null;

  @ApiModelProperty(value = "An identifier that is unique for the respective type within a VNF instance, but may not be globally unique. ")
 /**
   * An identifier that is unique for the respective type within a VNF instance, but may not be globally unique. 
  **/
  private String vnfExtCpId = null;

  @ApiModelProperty(value = "Network protocol information for this CP. ")
  @Valid
 /**
   * Network protocol information for this CP. 
  **/
  private List<NsInstancesNsInstanceInstantiatedVnfInfoCpProtocolInfo> cpProtocolInfo = null;

  @ApiModelProperty(value = "An identifier that is unique for the respective type within a VNF instance, but may not be globally unique. ")
 /**
   * An identifier that is unique for the respective type within a VNF instance, but may not be globally unique. 
  **/
  private String vnfLinkPortId = null;
 /**
   * An identifier that is unique for the respective type within a VNF instance, but may not be globally unique. 
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

  public NsInstancesNsInstanceInstantiatedVnfInfoVnfcCpInfo id(String id) {
    this.id = id;
    return this;
  }

 /**
   * Identifier of the VNF Virtual Link Descriptor (VLD) in the VNFD. 
   * @return cpdId
  **/
  @JsonProperty("cpdId")
  @NotNull
  public String getCpdId() {
    return cpdId;
  }

  public void setCpdId(String cpdId) {
    this.cpdId = cpdId;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfoVnfcCpInfo cpdId(String cpdId) {
    this.cpdId = cpdId;
    return this;
  }

 /**
   * An identifier that is unique for the respective type within a VNF instance, but may not be globally unique. 
   * @return vnfExtCpId
  **/
  @JsonProperty("vnfExtCpId")
  public String getVnfExtCpId() {
    return vnfExtCpId;
  }

  public void setVnfExtCpId(String vnfExtCpId) {
    this.vnfExtCpId = vnfExtCpId;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfoVnfcCpInfo vnfExtCpId(String vnfExtCpId) {
    this.vnfExtCpId = vnfExtCpId;
    return this;
  }

 /**
   * Network protocol information for this CP. 
   * @return cpProtocolInfo
  **/
  @JsonProperty("cpProtocolInfo")
  public List<NsInstancesNsInstanceInstantiatedVnfInfoCpProtocolInfo> getCpProtocolInfo() {
    return cpProtocolInfo;
  }

  public void setCpProtocolInfo(List<NsInstancesNsInstanceInstantiatedVnfInfoCpProtocolInfo> cpProtocolInfo) {
    this.cpProtocolInfo = cpProtocolInfo;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfoVnfcCpInfo cpProtocolInfo(List<NsInstancesNsInstanceInstantiatedVnfInfoCpProtocolInfo> cpProtocolInfo) {
    this.cpProtocolInfo = cpProtocolInfo;
    return this;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfoVnfcCpInfo addCpProtocolInfoItem(NsInstancesNsInstanceInstantiatedVnfInfoCpProtocolInfo cpProtocolInfoItem) {
    this.cpProtocolInfo.add(cpProtocolInfoItem);
    return this;
  }

 /**
   * An identifier that is unique for the respective type within a VNF instance, but may not be globally unique. 
   * @return vnfLinkPortId
  **/
  @JsonProperty("vnfLinkPortId")
  public String getVnfLinkPortId() {
    return vnfLinkPortId;
  }

  public void setVnfLinkPortId(String vnfLinkPortId) {
    this.vnfLinkPortId = vnfLinkPortId;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfoVnfcCpInfo vnfLinkPortId(String vnfLinkPortId) {
    this.vnfLinkPortId = vnfLinkPortId;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesNsInstanceInstantiatedVnfInfoVnfcCpInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    cpdId: ").append(toIndentedString(cpdId)).append("\n");
    sb.append("    vnfExtCpId: ").append(toIndentedString(vnfExtCpId)).append("\n");
    sb.append("    cpProtocolInfo: ").append(toIndentedString(cpProtocolInfo)).append("\n");
    sb.append("    vnfLinkPortId: ").append(toIndentedString(vnfLinkPortId)).append("\n");
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

