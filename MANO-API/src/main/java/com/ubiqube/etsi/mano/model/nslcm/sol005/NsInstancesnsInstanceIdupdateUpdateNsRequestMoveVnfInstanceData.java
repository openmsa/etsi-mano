package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
  * This type specifies existing VNF instances to be moved from one NS instance (source) to another NS instance (destination). The NS instance defined in the Update NS operation indicates the source NS instance and the destination NS instance is specified in this data type (referred to targetNsInstanceId).  It shall comply with the provisions defined in Table 6.5.3.35-1. 
 **/
@ApiModel(description="This type specifies existing VNF instances to be moved from one NS instance (source) to another NS instance (destination). The NS instance defined in the Update NS operation indicates the source NS instance and the destination NS instance is specified in this data type (referred to targetNsInstanceId).  It shall comply with the provisions defined in Table 6.5.3.35-1. ")
public class NsInstancesnsInstanceIdupdateUpdateNsRequestMoveVnfInstanceData  {
  
  @ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String targetNsInstanceId = null;

  @ApiModelProperty(value = "Specify the VNF instance that is moved. ")
 /**
   * Specify the VNF instance that is moved. 
  **/
  private List<String> vnfInstanceId = null;
 /**
   * An identifier with the intention of being globally unique. 
   * @return targetNsInstanceId
  **/
  @JsonProperty("targetNsInstanceId")
  @NotNull
  public String getTargetNsInstanceId() {
    return targetNsInstanceId;
  }

  public void setTargetNsInstanceId(String targetNsInstanceId) {
    this.targetNsInstanceId = targetNsInstanceId;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestMoveVnfInstanceData targetNsInstanceId(String targetNsInstanceId) {
    this.targetNsInstanceId = targetNsInstanceId;
    return this;
  }

 /**
   * Specify the VNF instance that is moved. 
   * @return vnfInstanceId
  **/
  @JsonProperty("vnfInstanceId")
  public List<String> getVnfInstanceId() {
    return vnfInstanceId;
  }

  public void setVnfInstanceId(List<String> vnfInstanceId) {
    this.vnfInstanceId = vnfInstanceId;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestMoveVnfInstanceData vnfInstanceId(List<String> vnfInstanceId) {
    this.vnfInstanceId = vnfInstanceId;
    return this;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestMoveVnfInstanceData addVnfInstanceIdItem(String vnfInstanceIdItem) {
    this.vnfInstanceId.add(vnfInstanceIdItem);
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesnsInstanceIdupdateUpdateNsRequestMoveVnfInstanceData {\n");
    
    sb.append("    targetNsInstanceId: ").append(toIndentedString(targetNsInstanceId)).append("\n");
    sb.append("    vnfInstanceId: ").append(toIndentedString(vnfInstanceId)).append("\n");
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

