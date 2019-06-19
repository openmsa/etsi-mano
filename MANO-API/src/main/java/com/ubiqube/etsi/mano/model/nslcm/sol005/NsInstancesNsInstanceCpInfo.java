package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
  * This type represents the information about the external CP of the PNF.  It shall comply with the provisions defined in Table 6.5.3.17-1. 
 **/
@ApiModel(description="This type represents the information about the external CP of the PNF.  It shall comply with the provisions defined in Table 6.5.3.17-1. ")
public class NsInstancesNsInstanceCpInfo  {
  
  @ApiModelProperty(required = true, value = "Identifier of the CP in the scope of the PNF. ")
 /**
   * Identifier of the CP in the scope of the PNF. 
  **/
  private String cpInstanceId = null;

  @ApiModelProperty(required = true, value = "An identifier that is unique within a NS descriptor. Representation: string of variable length. ")
 /**
   * An identifier that is unique within a NS descriptor. Representation: string of variable length. 
  **/
  private String cpdId = null;

  @ApiModelProperty(value = "Parameters for configuring the network protocols on the CP. ")
  @Valid
 /**
   * Parameters for configuring the network protocols on the CP. 
  **/
  private List<NsInstancesNsInstanceCpInfoCpProtocolData> cpProtocolData = null;
 /**
   * Identifier of the CP in the scope of the PNF. 
   * @return cpInstanceId
  **/
  @JsonProperty("cpInstanceId")
  @NotNull
  public String getCpInstanceId() {
    return cpInstanceId;
  }

  public void setCpInstanceId(String cpInstanceId) {
    this.cpInstanceId = cpInstanceId;
  }

  public NsInstancesNsInstanceCpInfo cpInstanceId(String cpInstanceId) {
    this.cpInstanceId = cpInstanceId;
    return this;
  }

 /**
   * An identifier that is unique within a NS descriptor. Representation: string of variable length. 
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

  public NsInstancesNsInstanceCpInfo cpdId(String cpdId) {
    this.cpdId = cpdId;
    return this;
  }

 /**
   * Parameters for configuring the network protocols on the CP. 
   * @return cpProtocolData
  **/
  @JsonProperty("cpProtocolData")
  public List<NsInstancesNsInstanceCpInfoCpProtocolData> getCpProtocolData() {
    return cpProtocolData;
  }

  public void setCpProtocolData(List<NsInstancesNsInstanceCpInfoCpProtocolData> cpProtocolData) {
    this.cpProtocolData = cpProtocolData;
  }

  public NsInstancesNsInstanceCpInfo cpProtocolData(List<NsInstancesNsInstanceCpInfoCpProtocolData> cpProtocolData) {
    this.cpProtocolData = cpProtocolData;
    return this;
  }

  public NsInstancesNsInstanceCpInfo addCpProtocolDataItem(NsInstancesNsInstanceCpInfoCpProtocolData cpProtocolDataItem) {
    this.cpProtocolData.add(cpProtocolDataItem);
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesNsInstanceCpInfo {\n");
    
    sb.append("    cpInstanceId: ").append(toIndentedString(cpInstanceId)).append("\n");
    sb.append("    cpdId: ").append(toIndentedString(cpdId)).append("\n");
    sb.append("    cpProtocolData: ").append(toIndentedString(cpProtocolData)).append("\n");
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

