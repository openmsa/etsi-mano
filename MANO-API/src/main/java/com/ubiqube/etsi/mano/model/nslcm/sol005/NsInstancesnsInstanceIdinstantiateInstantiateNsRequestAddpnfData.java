package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
  * This type specifies an PNF to be added to the NS instance and the PNF Profile  to use for this PNF. It shall comply with the provisions defined in Table 6.5.3.14-1. 
 **/
@ApiModel(description="This type specifies an PNF to be added to the NS instance and the PNF Profile  to use for this PNF. It shall comply with the provisions defined in Table 6.5.3.14-1. ")
public class NsInstancesnsInstanceIdinstantiateInstantiateNsRequestAddpnfData  {
  
  @ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String pnfId = null;

  @ApiModelProperty(required = true, value = "Name of the PNF ")
 /**
   * Name of the PNF 
  **/
  private String pnfName = null;

  @ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String pnfdId = null;

  @ApiModelProperty(required = true, value = "An identifier that is unique within a NS descriptor. Representation: string of variable length. ")
 /**
   * An identifier that is unique within a NS descriptor. Representation: string of variable length. 
  **/
  private String pnfProfileId = null;

  @ApiModelProperty(value = "Address assigned for the PNF external CP(s). ")
  @Valid
 /**
   * Address assigned for the PNF external CP(s). 
  **/
  private List<NsInstancesnsInstanceIdinstantiateInstantiateNsRequestCpData> cpData = null;
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

  public NsInstancesnsInstanceIdinstantiateInstantiateNsRequestAddpnfData pnfId(String pnfId) {
    this.pnfId = pnfId;
    return this;
  }

 /**
   * Name of the PNF 
   * @return pnfName
  **/
  @JsonProperty("pnfName")
  @NotNull
  public String getPnfName() {
    return pnfName;
  }

  public void setPnfName(String pnfName) {
    this.pnfName = pnfName;
  }

  public NsInstancesnsInstanceIdinstantiateInstantiateNsRequestAddpnfData pnfName(String pnfName) {
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

  public NsInstancesnsInstanceIdinstantiateInstantiateNsRequestAddpnfData pnfdId(String pnfdId) {
    this.pnfdId = pnfdId;
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

  public NsInstancesnsInstanceIdinstantiateInstantiateNsRequestAddpnfData pnfProfileId(String pnfProfileId) {
    this.pnfProfileId = pnfProfileId;
    return this;
  }

 /**
   * Address assigned for the PNF external CP(s). 
   * @return cpData
  **/
  @JsonProperty("cpData")
  public List<NsInstancesnsInstanceIdinstantiateInstantiateNsRequestCpData> getCpData() {
    return cpData;
  }

  public void setCpData(List<NsInstancesnsInstanceIdinstantiateInstantiateNsRequestCpData> cpData) {
    this.cpData = cpData;
  }

  public NsInstancesnsInstanceIdinstantiateInstantiateNsRequestAddpnfData cpData(List<NsInstancesnsInstanceIdinstantiateInstantiateNsRequestCpData> cpData) {
    this.cpData = cpData;
    return this;
  }

  public NsInstancesnsInstanceIdinstantiateInstantiateNsRequestAddpnfData addCpDataItem(NsInstancesnsInstanceIdinstantiateInstantiateNsRequestCpData cpDataItem) {
    this.cpData.add(cpDataItem);
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesnsInstanceIdinstantiateInstantiateNsRequestAddpnfData {\n");
    
    sb.append("    pnfId: ").append(toIndentedString(pnfId)).append("\n");
    sb.append("    pnfName: ").append(toIndentedString(pnfName)).append("\n");
    sb.append("    pnfdId: ").append(toIndentedString(pnfdId)).append("\n");
    sb.append("    pnfProfileId: ").append(toIndentedString(pnfProfileId)).append("\n");
    sb.append("    cpData: ").append(toIndentedString(cpData)).append("\n");
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

