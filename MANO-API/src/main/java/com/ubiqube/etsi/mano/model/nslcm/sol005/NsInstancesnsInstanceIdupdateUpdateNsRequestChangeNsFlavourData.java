package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
  * This type specifies an existing NS instance for which the DF needs to be changed.  This specifies the new DF, the instantiationLevel of the new DF that may be used and  the additional parameters as input for the flavour change.  It shall comply with the provisions defined in Table 6.5.3.39-1. 
 **/
@ApiModel(description="This type specifies an existing NS instance for which the DF needs to be changed.  This specifies the new DF, the instantiationLevel of the new DF that may be used and  the additional parameters as input for the flavour change.  It shall comply with the provisions defined in Table 6.5.3.39-1. ")
public class NsInstancesnsInstanceIdupdateUpdateNsRequestChangeNsFlavourData  {
  
  @ApiModelProperty(required = true, value = "An identifier that is unique within a NS descriptor. Representation: string of variable length. ")
 /**
   * An identifier that is unique within a NS descriptor. Representation: string of variable length. 
  **/
  private String newNsFlavourId = null;

  @ApiModelProperty(value = "An identifier that is unique within a NS descriptor. Representation: string of variable length. ")
 /**
   * An identifier that is unique within a NS descriptor. Representation: string of variable length. 
  **/
  private String instantiationLevelId = null;
 /**
   * An identifier that is unique within a NS descriptor. Representation: string of variable length. 
   * @return newNsFlavourId
  **/
  @JsonProperty("newNsFlavourId")
  @NotNull
  public String getNewNsFlavourId() {
    return newNsFlavourId;
  }

  public void setNewNsFlavourId(String newNsFlavourId) {
    this.newNsFlavourId = newNsFlavourId;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestChangeNsFlavourData newNsFlavourId(String newNsFlavourId) {
    this.newNsFlavourId = newNsFlavourId;
    return this;
  }

 /**
   * An identifier that is unique within a NS descriptor. Representation: string of variable length. 
   * @return instantiationLevelId
  **/
  @JsonProperty("instantiationLevelId")
  public String getInstantiationLevelId() {
    return instantiationLevelId;
  }

  public void setInstantiationLevelId(String instantiationLevelId) {
    this.instantiationLevelId = instantiationLevelId;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestChangeNsFlavourData instantiationLevelId(String instantiationLevelId) {
    this.instantiationLevelId = instantiationLevelId;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesnsInstanceIdupdateUpdateNsRequestChangeNsFlavourData {\n");
    
    sb.append("    newNsFlavourId: ").append(toIndentedString(newNsFlavourId)).append("\n");
    sb.append("    instantiationLevelId: ").append(toIndentedString(instantiationLevelId)).append("\n");
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

