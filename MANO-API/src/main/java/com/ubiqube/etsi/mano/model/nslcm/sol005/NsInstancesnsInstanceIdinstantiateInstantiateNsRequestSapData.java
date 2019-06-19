package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
  * This type represents the information related to a SAP of a NS.  It shall comply with the provisions defined in Table 6.5.3.10-1. 
 **/
@ApiModel(description="This type represents the information related to a SAP of a NS.  It shall comply with the provisions defined in Table 6.5.3.10-1. ")
public class NsInstancesnsInstanceIdinstantiateInstantiateNsRequestSapData  {
  
  @ApiModelProperty(required = true, value = "An identifier that is unique within a NS descriptor. Representation: string of variable length. ")
 /**
   * An identifier that is unique within a NS descriptor. Representation: string of variable length. 
  **/
  private String sapdId = null;

  @ApiModelProperty(required = true, value = "Human readable name for the SAP. ")
 /**
   * Human readable name for the SAP. 
  **/
  private String sapName = null;

  @ApiModelProperty(required = true, value = "Human readable description for the SAP. ")
 /**
   * Human readable description for the SAP. 
  **/
  private String description = null;

  @ApiModelProperty(value = "Parameters for configuring the network protocols on the SAP. ")
  @Valid
 /**
   * Parameters for configuring the network protocols on the SAP. 
  **/
  private List<NsInstancesNsInstanceCpInfoCpProtocolData> sapProtocolData = null;
 /**
   * An identifier that is unique within a NS descriptor. Representation: string of variable length. 
   * @return sapdId
  **/
  @JsonProperty("sapdId")
  @NotNull
  public String getSapdId() {
    return sapdId;
  }

  public void setSapdId(String sapdId) {
    this.sapdId = sapdId;
  }

  public NsInstancesnsInstanceIdinstantiateInstantiateNsRequestSapData sapdId(String sapdId) {
    this.sapdId = sapdId;
    return this;
  }

 /**
   * Human readable name for the SAP. 
   * @return sapName
  **/
  @JsonProperty("sapName")
  @NotNull
  public String getSapName() {
    return sapName;
  }

  public void setSapName(String sapName) {
    this.sapName = sapName;
  }

  public NsInstancesnsInstanceIdinstantiateInstantiateNsRequestSapData sapName(String sapName) {
    this.sapName = sapName;
    return this;
  }

 /**
   * Human readable description for the SAP. 
   * @return description
  **/
  @JsonProperty("description")
  @NotNull
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public NsInstancesnsInstanceIdinstantiateInstantiateNsRequestSapData description(String description) {
    this.description = description;
    return this;
  }

 /**
   * Parameters for configuring the network protocols on the SAP. 
   * @return sapProtocolData
  **/
  @JsonProperty("sapProtocolData")
  public List<NsInstancesNsInstanceCpInfoCpProtocolData> getSapProtocolData() {
    return sapProtocolData;
  }

  public void setSapProtocolData(List<NsInstancesNsInstanceCpInfoCpProtocolData> sapProtocolData) {
    this.sapProtocolData = sapProtocolData;
  }

  public NsInstancesnsInstanceIdinstantiateInstantiateNsRequestSapData sapProtocolData(List<NsInstancesNsInstanceCpInfoCpProtocolData> sapProtocolData) {
    this.sapProtocolData = sapProtocolData;
    return this;
  }

  public NsInstancesnsInstanceIdinstantiateInstantiateNsRequestSapData addSapProtocolDataItem(NsInstancesNsInstanceCpInfoCpProtocolData sapProtocolDataItem) {
    this.sapProtocolData.add(sapProtocolDataItem);
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesnsInstanceIdinstantiateInstantiateNsRequestSapData {\n");
    
    sb.append("    sapdId: ").append(toIndentedString(sapdId)).append("\n");
    sb.append("    sapName: ").append(toIndentedString(sapName)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    sapProtocolData: ").append(toIndentedString(sapProtocolData)).append("\n");
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

