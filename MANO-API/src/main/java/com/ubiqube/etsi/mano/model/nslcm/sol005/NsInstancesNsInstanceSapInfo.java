package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
  * This type represents an SAP instance. It shall comply with the provisions defined in Table 6.5.3.67-1. 
 **/
@ApiModel(description="This type represents an SAP instance. It shall comply with the provisions defined in Table 6.5.3.67-1. ")
public class NsInstancesNsInstanceSapInfo  {
  
  @ApiModelProperty(required = true, value = "An identifier that is unique with respect to a NS.  Representation: string of variable length. ")
 /**
   * An identifier that is unique with respect to a NS.  Representation: string of variable length. 
  **/
  private String id = null;

  @ApiModelProperty(required = true, value = "An identifier that is unique within a NS descriptor. Representation: string of variable length. ")
 /**
   * An identifier that is unique within a NS descriptor. Representation: string of variable length. 
  **/
  private String sapdId = null;

  @ApiModelProperty(required = true, value = "Human readable name for the SAP instance. ")
 /**
   * Human readable name for the SAP instance. 
  **/
  private String sapName = null;

  @ApiModelProperty(value = "Human readable description for the SAP instance. ")
 /**
   * Human readable description for the SAP instance. 
  **/
  private String description = null;

  @ApiModelProperty(required = true, value = "Network protocol information for this SAP. ")
  @Valid
 /**
   * Network protocol information for this SAP. 
  **/
  private List<NsInstancesNsInstanceInstantiatedVnfInfoCpProtocolInfo> sapProtocolInfo = new ArrayList<NsInstancesNsInstanceInstantiatedVnfInfoCpProtocolInfo>();
 /**
   * An identifier that is unique with respect to a NS.  Representation: string of variable length. 
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

  public NsInstancesNsInstanceSapInfo id(String id) {
    this.id = id;
    return this;
  }

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

  public NsInstancesNsInstanceSapInfo sapdId(String sapdId) {
    this.sapdId = sapdId;
    return this;
  }

 /**
   * Human readable name for the SAP instance. 
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

  public NsInstancesNsInstanceSapInfo sapName(String sapName) {
    this.sapName = sapName;
    return this;
  }

 /**
   * Human readable description for the SAP instance. 
   * @return description
  **/
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public NsInstancesNsInstanceSapInfo description(String description) {
    this.description = description;
    return this;
  }

 /**
   * Network protocol information for this SAP. 
   * @return sapProtocolInfo
  **/
  @JsonProperty("sapProtocolInfo")
  @NotNull
  public List<NsInstancesNsInstanceInstantiatedVnfInfoCpProtocolInfo> getSapProtocolInfo() {
    return sapProtocolInfo;
  }

  public void setSapProtocolInfo(List<NsInstancesNsInstanceInstantiatedVnfInfoCpProtocolInfo> sapProtocolInfo) {
    this.sapProtocolInfo = sapProtocolInfo;
  }

  public NsInstancesNsInstanceSapInfo sapProtocolInfo(List<NsInstancesNsInstanceInstantiatedVnfInfoCpProtocolInfo> sapProtocolInfo) {
    this.sapProtocolInfo = sapProtocolInfo;
    return this;
  }

  public NsInstancesNsInstanceSapInfo addSapProtocolInfoItem(NsInstancesNsInstanceInstantiatedVnfInfoCpProtocolInfo sapProtocolInfoItem) {
    this.sapProtocolInfo.add(sapProtocolInfoItem);
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesNsInstanceSapInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    sapdId: ").append(toIndentedString(sapdId)).append("\n");
    sb.append("    sapName: ").append(toIndentedString(sapName)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    sapProtocolInfo: ").append(toIndentedString(sapProtocolInfo)).append("\n");
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

