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

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
  * This type represents collection criteria for PM jobs.  It shall comply with the provisions defined in Table 7.5.3.3-1. 
 **/
@ApiModel(description="This type represents collection criteria for PM jobs.  It shall comply with the provisions defined in Table 7.5.3.3-1. ")
public class PmJobsPmJobCriteria  {
  
  @ApiModelProperty(value = "This defines the types of performance metrics for the specified object instances. At least one of the two attributes (performance metric or group) shall be present. ")
 /**
   * This defines the types of performance metrics for the specified object instances. At least one of the two attributes (performance metric or group) shall be present. 
  **/
  private List<String> performanceMetric = null;

  @ApiModelProperty(value = "Group of performance metrics. A metric group is a pre-defined list of metrics, known to the producer that it can decompose to individual metrics. At least one of the two attributes (performance metric or group) shall be present. ")
 /**
   * Group of performance metrics. A metric group is a pre-defined list of metrics, known to the producer that it can decompose to individual metrics. At least one of the two attributes (performance metric or group) shall be present. 
  **/
  private List<String> performanceMetricGroup = null;

  @ApiModelProperty(required = true, value = "Specifies the periodicity at which the producer will collect performance information. The unit shall be seconds. At the end of each reportingPeriod, the producer will inform the consumer about availability of the performance data collected for each completed collection period during this reportingPeriod. The reportingPeriod should be equal to or a multiple of the collectionPeriod. In the latter case, the performance data for the collection periods within one reporting period are reported together.     In particular when choosing short collection and reporting periods, the number of PM jobs that can be supported depends on the capability of the producing entity. ")
 /**
   * Specifies the periodicity at which the producer will collect performance information. The unit shall be seconds. At the end of each reportingPeriod, the producer will inform the consumer about availability of the performance data collected for each completed collection period during this reportingPeriod. The reportingPeriod should be equal to or a multiple of the collectionPeriod. In the latter case, the performance data for the collection periods within one reporting period are reported together.     In particular when choosing short collection and reporting periods, the number of PM jobs that can be supported depends on the capability of the producing entity. 
  **/
  private Integer collectionPeriod = null;

  @ApiModelProperty(required = true, value = "Specifies the periodicity at which the producer will report to the consumer. about performance information. The unit shall be seconds. At the end of each reportingPeriod, the producer will inform the consumer about availability of the performance data collected for each completed collection period during this reportingPeriod. The reportingPeriod should be equal to or a multiple of the collectionPeriod. In the latter case, the performance data for the collection periods within one reporting period are reported together.     In particular when choosing short collection and reporting periods, the number of PM jobs that can be supported depends on the capability of the producing entity.           ")
 /**
   * Specifies the periodicity at which the producer will report to the consumer. about performance information. The unit shall be seconds. At the end of each reportingPeriod, the producer will inform the consumer about availability of the performance data collected for each completed collection period during this reportingPeriod. The reportingPeriod should be equal to or a multiple of the collectionPeriod. In the latter case, the performance data for the collection periods within one reporting period are reported together.     In particular when choosing short collection and reporting periods, the number of PM jobs that can be supported depends on the capability of the producing entity.           
  **/
  private Integer reportingPeriod = null;

  @ApiModelProperty(value = "Date-time stamp.  Representation: String formatted according to IETF RFC 3339. ")
 /**
   * Date-time stamp.  Representation: String formatted according to IETF RFC 3339. 
  **/
  private Date reportingBoundary = null;
 /**
   * This defines the types of performance metrics for the specified object instances. At least one of the two attributes (performance metric or group) shall be present. 
   * @return performanceMetric
  **/
  @JsonProperty("performanceMetric")
  public List<String> getPerformanceMetric() {
    return performanceMetric;
  }

  public void setPerformanceMetric(List<String> performanceMetric) {
    this.performanceMetric = performanceMetric;
  }

  public PmJobsPmJobCriteria performanceMetric(List<String> performanceMetric) {
    this.performanceMetric = performanceMetric;
    return this;
  }

  public PmJobsPmJobCriteria addPerformanceMetricItem(String performanceMetricItem) {
    this.performanceMetric.add(performanceMetricItem);
    return this;
  }

 /**
   * Group of performance metrics. A metric group is a pre-defined list of metrics, known to the producer that it can decompose to individual metrics. At least one of the two attributes (performance metric or group) shall be present. 
   * @return performanceMetricGroup
  **/
  @JsonProperty("performanceMetricGroup")
  public List<String> getPerformanceMetricGroup() {
    return performanceMetricGroup;
  }

  public void setPerformanceMetricGroup(List<String> performanceMetricGroup) {
    this.performanceMetricGroup = performanceMetricGroup;
  }

  public PmJobsPmJobCriteria performanceMetricGroup(List<String> performanceMetricGroup) {
    this.performanceMetricGroup = performanceMetricGroup;
    return this;
  }

  public PmJobsPmJobCriteria addPerformanceMetricGroupItem(String performanceMetricGroupItem) {
    this.performanceMetricGroup.add(performanceMetricGroupItem);
    return this;
  }

 /**
   * Specifies the periodicity at which the producer will collect performance information. The unit shall be seconds. At the end of each reportingPeriod, the producer will inform the consumer about availability of the performance data collected for each completed collection period during this reportingPeriod. The reportingPeriod should be equal to or a multiple of the collectionPeriod. In the latter case, the performance data for the collection periods within one reporting period are reported together.     In particular when choosing short collection and reporting periods, the number of PM jobs that can be supported depends on the capability of the producing entity. 
   * minimum: 0
   * @return collectionPeriod
  **/
  @JsonProperty("collectionPeriod")
  @NotNull
 @Min(0)  public Integer getCollectionPeriod() {
    return collectionPeriod;
  }

  public void setCollectionPeriod(Integer collectionPeriod) {
    this.collectionPeriod = collectionPeriod;
  }

  public PmJobsPmJobCriteria collectionPeriod(Integer collectionPeriod) {
    this.collectionPeriod = collectionPeriod;
    return this;
  }

 /**
   * Specifies the periodicity at which the producer will report to the consumer. about performance information. The unit shall be seconds. At the end of each reportingPeriod, the producer will inform the consumer about availability of the performance data collected for each completed collection period during this reportingPeriod. The reportingPeriod should be equal to or a multiple of the collectionPeriod. In the latter case, the performance data for the collection periods within one reporting period are reported together.     In particular when choosing short collection and reporting periods, the number of PM jobs that can be supported depends on the capability of the producing entity.           
   * minimum: 0
   * @return reportingPeriod
  **/
  @JsonProperty("reportingPeriod")
  @NotNull
 @Min(0)  public Integer getReportingPeriod() {
    return reportingPeriod;
  }

  public void setReportingPeriod(Integer reportingPeriod) {
    this.reportingPeriod = reportingPeriod;
  }

  public PmJobsPmJobCriteria reportingPeriod(Integer reportingPeriod) {
    this.reportingPeriod = reportingPeriod;
    return this;
  }

 /**
   * Date-time stamp.  Representation: String formatted according to IETF RFC 3339. 
   * @return reportingBoundary
  **/
  @JsonProperty("reportingBoundary")
  public Date getReportingBoundary() {
    return reportingBoundary;
  }

  public void setReportingBoundary(Date reportingBoundary) {
    this.reportingBoundary = reportingBoundary;
  }

  public PmJobsPmJobCriteria reportingBoundary(Date reportingBoundary) {
    this.reportingBoundary = reportingBoundary;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PmJobsPmJobCriteria {\n");
    
    sb.append("    performanceMetric: ").append(toIndentedString(performanceMetric)).append("\n");
    sb.append("    performanceMetricGroup: ").append(toIndentedString(performanceMetricGroup)).append("\n");
    sb.append("    collectionPeriod: ").append(toIndentedString(collectionPeriod)).append("\n");
    sb.append("    reportingPeriod: ").append(toIndentedString(reportingPeriod)).append("\n");
    sb.append("    reportingBoundary: ").append(toIndentedString(reportingBoundary)).append("\n");
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

