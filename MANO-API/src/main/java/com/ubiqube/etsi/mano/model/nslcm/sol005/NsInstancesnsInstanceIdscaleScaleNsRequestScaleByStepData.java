package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
  * This type describes the information to scale a VNF instance by steps.  The NFVO shall then invoke the Scale VNF operation towards the appropriate VNFM. 
 **/
@ApiModel(description="This type describes the information to scale a VNF instance by steps.  The NFVO shall then invoke the Scale VNF operation towards the appropriate VNFM. ")
public class NsInstancesnsInstanceIdscaleScaleNsRequestScaleByStepData  {
  
  @ApiModelProperty(required = true, value = "Identifier of the VNF Virtual Link Descriptor (VLD) in the VNFD. ")
 /**
   * Identifier of the VNF Virtual Link Descriptor (VLD) in the VNFD. 
  **/
  private String aspectId = null;

  @ApiModelProperty(value = "Number of scaling steps. It shall be a positive number. Defaults to 1. The VNF provider defines in the VNFD whether or not a particular VNF supports performing more than one step at a time. Such a property in the VNFD applies for all instances of a particular VNF. ")
 /**
   * Number of scaling steps. It shall be a positive number. Defaults to 1. The VNF provider defines in the VNFD whether or not a particular VNF supports performing more than one step at a time. Such a property in the VNFD applies for all instances of a particular VNF. 
  **/
  private Integer numberOfSteps = null;

  @ApiModelProperty(value = "This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  ")
 /**
   * This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  
  **/
  private Object additionalParams = null;
 /**
   * Identifier of the VNF Virtual Link Descriptor (VLD) in the VNFD. 
   * @return aspectId
  **/
  @JsonProperty("aspectId")
  @NotNull
  public String getAspectId() {
    return aspectId;
  }

  public void setAspectId(String aspectId) {
    this.aspectId = aspectId;
  }

  public NsInstancesnsInstanceIdscaleScaleNsRequestScaleByStepData aspectId(String aspectId) {
    this.aspectId = aspectId;
    return this;
  }

 /**
   * Number of scaling steps. It shall be a positive number. Defaults to 1. The VNF provider defines in the VNFD whether or not a particular VNF supports performing more than one step at a time. Such a property in the VNFD applies for all instances of a particular VNF. 
   * @return numberOfSteps
  **/
  @JsonProperty("numberOfSteps")
  public Integer getNumberOfSteps() {
    return numberOfSteps;
  }

  public void setNumberOfSteps(Integer numberOfSteps) {
    this.numberOfSteps = numberOfSteps;
  }

  public NsInstancesnsInstanceIdscaleScaleNsRequestScaleByStepData numberOfSteps(Integer numberOfSteps) {
    this.numberOfSteps = numberOfSteps;
    return this;
  }

 /**
   * This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  
   * @return additionalParams
  **/
  @JsonProperty("additionalParams")
  public Object getAdditionalParams() {
    return additionalParams;
  }

  public void setAdditionalParams(Object additionalParams) {
    this.additionalParams = additionalParams;
  }

  public NsInstancesnsInstanceIdscaleScaleNsRequestScaleByStepData additionalParams(Object additionalParams) {
    this.additionalParams = additionalParams;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesnsInstanceIdscaleScaleNsRequestScaleByStepData {\n");
    
    sb.append("    aspectId: ").append(toIndentedString(aspectId)).append("\n");
    sb.append("    numberOfSteps: ").append(toIndentedString(numberOfSteps)).append("\n");
    sb.append("    additionalParams: ").append(toIndentedString(additionalParams)).append("\n");
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

