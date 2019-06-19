package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
  * This type represents configuration information for external CPs created from a CPD. 
 **/
@ApiModel(description="This type represents configuration information for external CPs created from a CPD. ")
public class NsInstancesnsInstanceIdupdateUpdateNsRequestExtCps  {
  
  @ApiModelProperty(required = true, value = "Identifier of the VNF Virtual Link Descriptor (VLD) in the VNFD. ")
 /**
   * Identifier of the VNF Virtual Link Descriptor (VLD) in the VNFD. 
  **/
  private String cpdId = null;

  @ApiModelProperty(value = "List of instance data that need to be configured on the CP instances created from the respective CPD. ")
  @Valid
 /**
   * List of instance data that need to be configured on the CP instances created from the respective CPD. 
  **/
  private List<NsInstancesnsInstanceIdupdateUpdateNsRequestCpConfig> cpConfig = null;
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

  public NsInstancesnsInstanceIdupdateUpdateNsRequestExtCps cpdId(String cpdId) {
    this.cpdId = cpdId;
    return this;
  }

 /**
   * List of instance data that need to be configured on the CP instances created from the respective CPD. 
   * @return cpConfig
  **/
  @JsonProperty("cpConfig")
  public List<NsInstancesnsInstanceIdupdateUpdateNsRequestCpConfig> getCpConfig() {
    return cpConfig;
  }

  public void setCpConfig(List<NsInstancesnsInstanceIdupdateUpdateNsRequestCpConfig> cpConfig) {
    this.cpConfig = cpConfig;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestExtCps cpConfig(List<NsInstancesnsInstanceIdupdateUpdateNsRequestCpConfig> cpConfig) {
    this.cpConfig = cpConfig;
    return this;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestExtCps addCpConfigItem(NsInstancesnsInstanceIdupdateUpdateNsRequestCpConfig cpConfigItem) {
    this.cpConfig.add(cpConfigItem);
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesnsInstanceIdupdateUpdateNsRequestExtCps {\n");
    
    sb.append("    cpdId: ").append(toIndentedString(cpdId)).append("\n");
    sb.append("    cpConfig: ").append(toIndentedString(cpConfig)).append("\n");
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

