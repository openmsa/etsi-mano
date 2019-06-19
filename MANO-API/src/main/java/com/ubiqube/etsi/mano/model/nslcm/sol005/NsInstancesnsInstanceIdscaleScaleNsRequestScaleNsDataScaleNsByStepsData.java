package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
  * This type represents the information used to scale an NS instance by one or more scaling steps, with respect to a particular NS scaling aspect. Performing a scaling step means increasing/decreasing the capacity of an NS instance in a discrete manner, i.e. moving from one NS scale level to another. The NS scaling aspects and their corresponding NS scale levels applicable to the NS instance are declared in the NSD. 
 **/
@ApiModel(description="This type represents the information used to scale an NS instance by one or more scaling steps, with respect to a particular NS scaling aspect. Performing a scaling step means increasing/decreasing the capacity of an NS instance in a discrete manner, i.e. moving from one NS scale level to another. The NS scaling aspects and their corresponding NS scale levels applicable to the NS instance are declared in the NSD. ")
public class NsInstancesnsInstanceIdscaleScaleNsRequestScaleNsDataScaleNsByStepsData  {
  

@XmlType(name="ScalingDirectionEnum")
@XmlEnum(String.class)
public enum ScalingDirectionEnum {

@XmlEnumValue("SCALE_IN") IN(String.valueOf("SCALE_IN")), @XmlEnumValue("SCALE_OUT") OUT(String.valueOf("SCALE_OUT"));


    private String value;

    ScalingDirectionEnum (String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static ScalingDirectionEnum fromValue(String v) {
        for (ScalingDirectionEnum b : ScalingDirectionEnum.values()) {
            if (String.valueOf(b.value).equals(v)) {
                return b;
            }
        }
        return null;
    }
}

  @ApiModelProperty(required = true, value = "The scaling direction. Possible values are: - SCALE_IN - SCALE_OUT. ")
 /**
   * The scaling direction. Possible values are: - SCALE_IN - SCALE_OUT. 
  **/
  private ScalingDirectionEnum scalingDirection = null;

  @ApiModelProperty(required = true, value = "An identifier that is unique within a NS descriptor. Representation: string of variable length. ")
 /**
   * An identifier that is unique within a NS descriptor. Representation: string of variable length. 
  **/
  private String aspectId = null;

  @ApiModelProperty(value = "The number of scaling steps to be performed. Defaults to 1. ")
 /**
   * The number of scaling steps to be performed. Defaults to 1. 
  **/
  private Integer numberOfSteps = null;
 /**
   * The scaling direction. Possible values are: - SCALE_IN - SCALE_OUT. 
   * @return scalingDirection
  **/
  @JsonProperty("scalingDirection")
  @NotNull
  public String getScalingDirection() {
    if (scalingDirection == null) {
      return null;
    }
    return scalingDirection.value();
  }

  public void setScalingDirection(ScalingDirectionEnum scalingDirection) {
    this.scalingDirection = scalingDirection;
  }

  public NsInstancesnsInstanceIdscaleScaleNsRequestScaleNsDataScaleNsByStepsData scalingDirection(ScalingDirectionEnum scalingDirection) {
    this.scalingDirection = scalingDirection;
    return this;
  }

 /**
   * An identifier that is unique within a NS descriptor. Representation: string of variable length. 
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

  public NsInstancesnsInstanceIdscaleScaleNsRequestScaleNsDataScaleNsByStepsData aspectId(String aspectId) {
    this.aspectId = aspectId;
    return this;
  }

 /**
   * The number of scaling steps to be performed. Defaults to 1. 
   * @return numberOfSteps
  **/
  @JsonProperty("numberOfSteps")
  public Integer getNumberOfSteps() {
    return numberOfSteps;
  }

  public void setNumberOfSteps(Integer numberOfSteps) {
    this.numberOfSteps = numberOfSteps;
  }

  public NsInstancesnsInstanceIdscaleScaleNsRequestScaleNsDataScaleNsByStepsData numberOfSteps(Integer numberOfSteps) {
    this.numberOfSteps = numberOfSteps;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesnsInstanceIdscaleScaleNsRequestScaleNsDataScaleNsByStepsData {\n");
    
    sb.append("    scalingDirection: ").append(toIndentedString(scalingDirection)).append("\n");
    sb.append("    aspectId: ").append(toIndentedString(aspectId)).append("\n");
    sb.append("    numberOfSteps: ").append(toIndentedString(numberOfSteps)).append("\n");
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

