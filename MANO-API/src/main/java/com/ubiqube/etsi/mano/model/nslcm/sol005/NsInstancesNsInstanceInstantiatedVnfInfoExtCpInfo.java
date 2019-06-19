package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public class NsInstancesNsInstanceInstantiatedVnfInfoExtCpInfo  {
  
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

  @ApiModelProperty(value = "Network protocol information for this CP. ")
  @Valid
 /**
   * Network protocol information for this CP. 
  **/
  private List<NsInstancesNsInstanceInstantiatedVnfInfoCpProtocolInfo> cpProtocolInfo = null;

  @ApiModelProperty(value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String extLinkPortId = null;
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

  public NsInstancesNsInstanceInstantiatedVnfInfoExtCpInfo id(String id) {
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

  public NsInstancesNsInstanceInstantiatedVnfInfoExtCpInfo cpdId(String cpdId) {
    this.cpdId = cpdId;
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

  public NsInstancesNsInstanceInstantiatedVnfInfoExtCpInfo cpProtocolInfo(List<NsInstancesNsInstanceInstantiatedVnfInfoCpProtocolInfo> cpProtocolInfo) {
    this.cpProtocolInfo = cpProtocolInfo;
    return this;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfoExtCpInfo addCpProtocolInfoItem(NsInstancesNsInstanceInstantiatedVnfInfoCpProtocolInfo cpProtocolInfoItem) {
    this.cpProtocolInfo.add(cpProtocolInfoItem);
    return this;
  }

 /**
   * An identifier with the intention of being globally unique. 
   * @return extLinkPortId
  **/
  @JsonProperty("extLinkPortId")
  public String getExtLinkPortId() {
    return extLinkPortId;
  }

  public void setExtLinkPortId(String extLinkPortId) {
    this.extLinkPortId = extLinkPortId;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfoExtCpInfo extLinkPortId(String extLinkPortId) {
    this.extLinkPortId = extLinkPortId;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesNsInstanceInstantiatedVnfInfoExtCpInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    cpdId: ").append(toIndentedString(cpdId)).append("\n");
    sb.append("    cpProtocolInfo: ").append(toIndentedString(cpProtocolInfo)).append("\n");
    sb.append("    extLinkPortId: ").append(toIndentedString(extLinkPortId)).append("\n");
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

