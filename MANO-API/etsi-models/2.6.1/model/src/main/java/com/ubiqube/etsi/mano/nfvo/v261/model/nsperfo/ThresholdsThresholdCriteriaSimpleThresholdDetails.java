/**
 * This copy of Woodstox XML processor is licensed under the
 * Apache (Software) License, version 2.0 ("the License").
 * See the License for details about distribution rights, and the
 * specific rights regarding derivate works.
 *
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/
 *
 * A copy is also included in the downloadable source code package
 * containing Woodstox, in file "ASL2.0", under the same directory
 * as this file.
 */
package com.ubiqube.etsi.mano.nfvo.v261.model.nsperfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
  * Details of a simple threshold. Shall be present if thresholdType=\"SIMPLE\". 
 **/
@ApiModel(description="Details of a simple threshold. Shall be present if thresholdType=\"SIMPLE\". ")
public class ThresholdsThresholdCriteriaSimpleThresholdDetails  {
  
  @ApiModelProperty(required = true, value = "The threshold value. Shall be represented as a floating point number.                ")
 /**
   * The threshold value. Shall be represented as a floating point number.                
  **/
  private Integer thresholdValue = null;

  @ApiModelProperty(required = true, value = "The hysteresis of the threshold. Shall be represented as a non-negative floating point number. A notification with crossing direction \"UP\" will be generated if the measured value reaches or exceeds \"thresholdValue\" + \"hysteresis\". A notification with crossing direction \"DOWN\" will be generated if the measured value reaches or undercuts \"thresholdValue\" - \"hysteresis\". The hysteresis is defined to prevent storms of threshold crossing notifications. When processing a request to create a threshold, implementations should enforce a suitable minimum value for this attribute (e.g. override the value or reject the request). ")
 /**
   * The hysteresis of the threshold. Shall be represented as a non-negative floating point number. A notification with crossing direction \"UP\" will be generated if the measured value reaches or exceeds \"thresholdValue\" + \"hysteresis\". A notification with crossing direction \"DOWN\" will be generated if the measured value reaches or undercuts \"thresholdValue\" - \"hysteresis\". The hysteresis is defined to prevent storms of threshold crossing notifications. When processing a request to create a threshold, implementations should enforce a suitable minimum value for this attribute (e.g. override the value or reject the request). 
  **/
  private Integer hysteresis = null;
 /**
   * The threshold value. Shall be represented as a floating point number.                
   * @return thresholdValue
  **/
  @JsonProperty("thresholdValue")
  @NotNull
  public Integer getThresholdValue() {
    return thresholdValue;
  }

  public void setThresholdValue(Integer thresholdValue) {
    this.thresholdValue = thresholdValue;
  }

  public ThresholdsThresholdCriteriaSimpleThresholdDetails thresholdValue(Integer thresholdValue) {
    this.thresholdValue = thresholdValue;
    return this;
  }

 /**
   * The hysteresis of the threshold. Shall be represented as a non-negative floating point number. A notification with crossing direction \&quot;UP\&quot; will be generated if the measured value reaches or exceeds \&quot;thresholdValue\&quot; + \&quot;hysteresis\&quot;. A notification with crossing direction \&quot;DOWN\&quot; will be generated if the measured value reaches or undercuts \&quot;thresholdValue\&quot; - \&quot;hysteresis\&quot;. The hysteresis is defined to prevent storms of threshold crossing notifications. When processing a request to create a threshold, implementations should enforce a suitable minimum value for this attribute (e.g. override the value or reject the request). 
   * @return hysteresis
  **/
  @JsonProperty("hysteresis")
  @NotNull
  public Integer getHysteresis() {
    return hysteresis;
  }

  public void setHysteresis(Integer hysteresis) {
    this.hysteresis = hysteresis;
  }

  public ThresholdsThresholdCriteriaSimpleThresholdDetails hysteresis(Integer hysteresis) {
    this.hysteresis = hysteresis;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ThresholdsThresholdCriteriaSimpleThresholdDetails {\n");
    
    sb.append("    thresholdValue: ").append(toIndentedString(thresholdValue)).append("\n");
    sb.append("    hysteresis: ").append(toIndentedString(hysteresis)).append("\n");
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

