package com.ubiqube.etsi.mano.model.nsperfo.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;

public class PmJobsPmJobIdReportsReportIdGetResponse {
  
  @ApiModelProperty(value = "")
  @Valid
  private InlineResponse2001PerformanceReport performanceReport = null;
 /**
   * Get performanceReport
   * @return performanceReport
  **/
  @JsonProperty("PerformanceReport")
  public InlineResponse2001PerformanceReport getPerformanceReport() {
    return performanceReport;
  }

  public void setPerformanceReport(InlineResponse2001PerformanceReport performanceReport) {
    this.performanceReport = performanceReport;
  }

  public PmJobsPmJobIdReportsReportIdGetResponse performanceReport(InlineResponse2001PerformanceReport performanceReport) {
    this.performanceReport = performanceReport;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PmJobsPmJobIdReportsReportIdGetResponse {\n");
    
    sb.append("    performanceReport: ").append(toIndentedString(performanceReport)).append("\n");
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

