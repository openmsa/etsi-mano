package com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanopm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents collection criteria for PM jobs. 
 */
@Schema(description = "This type represents collection criteria for PM jobs. ")
@Validated


public class PmJobCriteria   {
  @JsonProperty("performanceMetric")
  @Valid
  private List<String> performanceMetric = null;

  @JsonProperty("performanceMetricGroup")
  @Valid
  private List<String> performanceMetricGroup = null;

  @JsonProperty("collectionPeriod")
  private Integer collectionPeriod = null;

  @JsonProperty("reportingPeriod")
  private Integer reportingPeriod = null;

  @JsonProperty("reportingBoundary")
  private OffsetDateTime reportingBoundary = null;

  public PmJobCriteria performanceMetric(List<String> performanceMetric) {
    this.performanceMetric = performanceMetric;
    return this;
  }

  public PmJobCriteria addPerformanceMetricItem(String performanceMetricItem) {
    if (this.performanceMetric == null) {
      this.performanceMetric = new ArrayList<>();
    }
    this.performanceMetric.add(performanceMetricItem);
    return this;
  }

  /**
   * This defines the types of performance metrics for the specified measured  object(s). This attribute’s value shall contain the related \"Measurement Name\"  values as defined in clause 8.4 of ETSI GS NFV-IFA 031.  At least one of the two attributes (performance metric or group) shall be present. 
   * @return performanceMetric
   **/
  @Schema(description = "This defines the types of performance metrics for the specified measured  object(s). This attribute’s value shall contain the related \"Measurement Name\"  values as defined in clause 8.4 of ETSI GS NFV-IFA 031.  At least one of the two attributes (performance metric or group) shall be present. ")
  
    public List<String> getPerformanceMetric() {
    return performanceMetric;
  }

  public void setPerformanceMetric(List<String> performanceMetric) {
    this.performanceMetric = performanceMetric;
  }

  public PmJobCriteria performanceMetricGroup(List<String> performanceMetricGroup) {
    this.performanceMetricGroup = performanceMetricGroup;
    return this;
  }

  public PmJobCriteria addPerformanceMetricGroupItem(String performanceMetricGroupItem) {
    if (this.performanceMetricGroup == null) {
      this.performanceMetricGroup = new ArrayList<>();
    }
    this.performanceMetricGroup.add(performanceMetricGroupItem);
    return this;
  }

  /**
   * Group of performance metrics. A metric group is a pre-defined list of metrics, known to the producer that it  can decompose to individual metrics. This attribute’s value shall contain the  related \"Measurement Group\" values as defined in clause 8.4 of ETSI GS  NFV-IFA 031. At least one of the two attributes (performance metric or group) shall be present. 
   * @return performanceMetricGroup
   **/
  @Schema(description = "Group of performance metrics. A metric group is a pre-defined list of metrics, known to the producer that it  can decompose to individual metrics. This attribute’s value shall contain the  related \"Measurement Group\" values as defined in clause 8.4 of ETSI GS  NFV-IFA 031. At least one of the two attributes (performance metric or group) shall be present. ")
  
    public List<String> getPerformanceMetricGroup() {
    return performanceMetricGroup;
  }

  public void setPerformanceMetricGroup(List<String> performanceMetricGroup) {
    this.performanceMetricGroup = performanceMetricGroup;
  }

  public PmJobCriteria collectionPeriod(Integer collectionPeriod) {
    this.collectionPeriod = collectionPeriod;
    return this;
  }

  /**
   * Specifies the periodicity at which the producer will collect performance  information. The unit shall be seconds.  At the end of each reportingPeriod, the producer will inform the API consumer  about availability of the performance data collected for each completed  collection period during this reportingPeriod. The reportingPeriod should  be equal to or a multiple of the collectionPeriod. In the latter case, the  performance data for the collection periods within one reporting period  are reported together.  In particular when choosing short collection and reporting periods, the  number of PM jobs that can be supported depends on the capability of the  producing entity. 
   * @return collectionPeriod
   **/
  @Schema(required = true, description = "Specifies the periodicity at which the producer will collect performance  information. The unit shall be seconds.  At the end of each reportingPeriod, the producer will inform the API consumer  about availability of the performance data collected for each completed  collection period during this reportingPeriod. The reportingPeriod should  be equal to or a multiple of the collectionPeriod. In the latter case, the  performance data for the collection periods within one reporting period  are reported together.  In particular when choosing short collection and reporting periods, the  number of PM jobs that can be supported depends on the capability of the  producing entity. ")
      @NotNull

    public Integer getCollectionPeriod() {
    return collectionPeriod;
  }

  public void setCollectionPeriod(Integer collectionPeriod) {
    this.collectionPeriod = collectionPeriod;
  }

  public PmJobCriteria reportingPeriod(Integer reportingPeriod) {
    this.reportingPeriod = reportingPeriod;
    return this;
  }

  /**
   * Specifies the periodicity at which the producer will report to the API consumer  about performance information. The unit shall be seconds. At the end of each reportingPeriod, the producer will inform the API consumer  about availability of the performance data collected for each completed  collection period during this reportingPeriod. The reportingPeriod should  be equal to or a multiple of the collectionPeriod. In the latter case, the  performance data for the collection periods within one reporting period  are reported together.  In particular when choosing short collection and reporting periods, the  number of PM jobs that can be supported depends on the capability of the  producing entity. 
   * @return reportingPeriod
   **/
  @Schema(required = true, description = "Specifies the periodicity at which the producer will report to the API consumer  about performance information. The unit shall be seconds. At the end of each reportingPeriod, the producer will inform the API consumer  about availability of the performance data collected for each completed  collection period during this reportingPeriod. The reportingPeriod should  be equal to or a multiple of the collectionPeriod. In the latter case, the  performance data for the collection periods within one reporting period  are reported together.  In particular when choosing short collection and reporting periods, the  number of PM jobs that can be supported depends on the capability of the  producing entity. ")
      @NotNull

    public Integer getReportingPeriod() {
    return reportingPeriod;
  }

  public void setReportingPeriod(Integer reportingPeriod) {
    this.reportingPeriod = reportingPeriod;
  }

  public PmJobCriteria reportingBoundary(OffsetDateTime reportingBoundary) {
    this.reportingBoundary = reportingBoundary;
    return this;
  }

  /**
   * Get reportingBoundary
   * @return reportingBoundary
   **/
  @Schema(description = "")
  
    @Valid
    public OffsetDateTime getReportingBoundary() {
    return reportingBoundary;
  }

  public void setReportingBoundary(OffsetDateTime reportingBoundary) {
    this.reportingBoundary = reportingBoundary;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PmJobCriteria pmJobCriteria = (PmJobCriteria) o;
    return Objects.equals(this.performanceMetric, pmJobCriteria.performanceMetric) &&
        Objects.equals(this.performanceMetricGroup, pmJobCriteria.performanceMetricGroup) &&
        Objects.equals(this.collectionPeriod, pmJobCriteria.collectionPeriod) &&
        Objects.equals(this.reportingPeriod, pmJobCriteria.reportingPeriod) &&
        Objects.equals(this.reportingBoundary, pmJobCriteria.reportingBoundary);
  }

  @Override
  public int hashCode() {
    return Objects.hash(performanceMetric, performanceMetricGroup, collectionPeriod, reportingPeriod, reportingBoundary);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PmJobCriteria {\n");
    
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
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
