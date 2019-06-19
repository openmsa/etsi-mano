package com.ubiqube.etsi.mano.model.nsperfo.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public class InlineResponse2001PerformanceReportEntries  {
  
  @ApiModelProperty(required = true, value = "Defines the object type for which performance information is reported (i.e. NS type). The string value shall be set to the nsdId of the NS instance to which the performance information relates. ")
 /**
   * Defines the object type for which performance information is reported (i.e. NS type). The string value shall be set to the nsdId of the NS instance to which the performance information relates. 
  **/
  private String objectType = null;

  @ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String objectInstanceId = null;

  @ApiModelProperty(required = true, value = "Name of the metric collected. ")
 /**
   * Name of the metric collected. 
  **/
  private String performanceMetric = null;

  @ApiModelProperty(value = "List of performance values with associated timestamp. ")
  @Valid
 /**
   * List of performance values with associated timestamp. 
  **/
  private List<InlineResponse2001PerformanceReportPerformanceValues> performanceValues = null;
 /**
   * Defines the object type for which performance information is reported (i.e. NS type). The string value shall be set to the nsdId of the NS instance to which the performance information relates. 
   * @return objectType
  **/
  @JsonProperty("objectType")
  @NotNull
  public String getObjectType() {
    return objectType;
  }

  public void setObjectType(String objectType) {
    this.objectType = objectType;
  }

  public InlineResponse2001PerformanceReportEntries objectType(String objectType) {
    this.objectType = objectType;
    return this;
  }

 /**
   * An identifier with the intention of being globally unique. 
   * @return objectInstanceId
  **/
  @JsonProperty("objectInstanceId")
  @NotNull
  public String getObjectInstanceId() {
    return objectInstanceId;
  }

  public void setObjectInstanceId(String objectInstanceId) {
    this.objectInstanceId = objectInstanceId;
  }

  public InlineResponse2001PerformanceReportEntries objectInstanceId(String objectInstanceId) {
    this.objectInstanceId = objectInstanceId;
    return this;
  }

 /**
   * Name of the metric collected. 
   * @return performanceMetric
  **/
  @JsonProperty("performanceMetric")
  @NotNull
  public String getPerformanceMetric() {
    return performanceMetric;
  }

  public void setPerformanceMetric(String performanceMetric) {
    this.performanceMetric = performanceMetric;
  }

  public InlineResponse2001PerformanceReportEntries performanceMetric(String performanceMetric) {
    this.performanceMetric = performanceMetric;
    return this;
  }

 /**
   * List of performance values with associated timestamp. 
   * @return performanceValues
  **/
  @JsonProperty("performanceValues")
  public List<InlineResponse2001PerformanceReportPerformanceValues> getPerformanceValues() {
    return performanceValues;
  }

  public void setPerformanceValues(List<InlineResponse2001PerformanceReportPerformanceValues> performanceValues) {
    this.performanceValues = performanceValues;
  }

  public InlineResponse2001PerformanceReportEntries performanceValues(List<InlineResponse2001PerformanceReportPerformanceValues> performanceValues) {
    this.performanceValues = performanceValues;
    return this;
  }

  public InlineResponse2001PerformanceReportEntries addPerformanceValuesItem(InlineResponse2001PerformanceReportPerformanceValues performanceValuesItem) {
    this.performanceValues.add(performanceValuesItem);
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineResponse2001PerformanceReportEntries {\n");
    
    sb.append("    objectType: ").append(toIndentedString(objectType)).append("\n");
    sb.append("    objectInstanceId: ").append(toIndentedString(objectInstanceId)).append("\n");
    sb.append("    performanceMetric: ").append(toIndentedString(performanceMetric)).append("\n");
    sb.append("    performanceValues: ").append(toIndentedString(performanceValues)).append("\n");
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

