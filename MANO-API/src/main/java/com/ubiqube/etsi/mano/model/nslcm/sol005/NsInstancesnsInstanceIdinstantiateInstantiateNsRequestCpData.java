package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
  * This type represents the configuration data on the external CP of the PNF.  It shall comply with the provisions defined in Table 6.5.3.16-1. 
 **/
@ApiModel(description="This type represents the configuration data on the external CP of the PNF.  It shall comply with the provisions defined in Table 6.5.3.16-1. ")
public class NsInstancesnsInstanceIdinstantiateInstantiateNsRequestCpData  {
  
  @ApiModelProperty(value = "Identifier of the CP in the scope of the PNF. ")
 /**
   * Identifier of the CP in the scope of the PNF. 
  **/
  private String cpInstanceI16 = null;

  @ApiModelProperty(value = "An identifier that is unique within a NS descriptor. Representation: string of variable length. ")
 /**
   * An identifier that is unique within a NS descriptor. Representation: string of variable length. 
  **/
  private String cpdId = null;

  @ApiModelProperty(required = true, value = "Address assigned for this CP. ")
  @Valid
 /**
   * Address assigned for this CP. 
  **/
  private List<NsInstancesNsInstanceCpInfoCpProtocolData> cpProtocolData = new ArrayList<NsInstancesNsInstanceCpInfoCpProtocolData>();
 /**
   * Identifier of the CP in the scope of the PNF. 
   * @return cpInstanceI16
  **/
  @JsonProperty("cpInstanceI16")
  public String getCpInstanceI16() {
    return cpInstanceI16;
  }

  public void setCpInstanceI16(String cpInstanceI16) {
    this.cpInstanceI16 = cpInstanceI16;
  }

  public NsInstancesnsInstanceIdinstantiateInstantiateNsRequestCpData cpInstanceI16(String cpInstanceI16) {
    this.cpInstanceI16 = cpInstanceI16;
    return this;
  }

 /**
   * An identifier that is unique within a NS descriptor. Representation: string of variable length. 
   * @return cpdId
  **/
  @JsonProperty("cpdId")
  public String getCpdId() {
    return cpdId;
  }

  public void setCpdId(String cpdId) {
    this.cpdId = cpdId;
  }

  public NsInstancesnsInstanceIdinstantiateInstantiateNsRequestCpData cpdId(String cpdId) {
    this.cpdId = cpdId;
    return this;
  }

 /**
   * Address assigned for this CP. 
   * @return cpProtocolData
  **/
  @JsonProperty("cpProtocolData")
  @NotNull
  public List<NsInstancesNsInstanceCpInfoCpProtocolData> getCpProtocolData() {
    return cpProtocolData;
  }

  public void setCpProtocolData(List<NsInstancesNsInstanceCpInfoCpProtocolData> cpProtocolData) {
    this.cpProtocolData = cpProtocolData;
  }

  public NsInstancesnsInstanceIdinstantiateInstantiateNsRequestCpData cpProtocolData(List<NsInstancesNsInstanceCpInfoCpProtocolData> cpProtocolData) {
    this.cpProtocolData = cpProtocolData;
    return this;
  }

  public NsInstancesnsInstanceIdinstantiateInstantiateNsRequestCpData addCpProtocolDataItem(NsInstancesNsInstanceCpInfoCpProtocolData cpProtocolDataItem) {
    this.cpProtocolData.add(cpProtocolDataItem);
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesnsInstanceIdinstantiateInstantiateNsRequestCpData {\n");
    
    sb.append("    cpInstanceI16: ").append(toIndentedString(cpInstanceI16)).append("\n");
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

