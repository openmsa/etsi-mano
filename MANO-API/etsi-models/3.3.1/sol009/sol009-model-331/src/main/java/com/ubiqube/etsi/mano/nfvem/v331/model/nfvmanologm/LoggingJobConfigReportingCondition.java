package com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanologm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Specifies the condition under which the producer will report to the consumer about the compiled log data.
 */
@Schema(description = "Specifies the condition under which the producer will report to the consumer about the compiled log data.")
@Validated


public class LoggingJobConfigReportingCondition   {
  /**
   * Specifies the type of reporting condition. Permitted values: - REPORTING_ON_COMPILATION: the producer shall notify the consumer once the compilation of the                 collected logging data into a file is completed and a new log report is available. - NO_REPORTING: no reporting is requested (the consumer can query the logging jobs to know about the                 availability of new log reports).
   */
  public enum ReportingTypeEnum {
    REPORTING_ON_COMPILATION("REPORTING_ON_COMPILATION"),
    
    NO_REPORTING("NO_REPORTING");

    private String value;

    ReportingTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static ReportingTypeEnum fromValue(String text) {
      for (ReportingTypeEnum b : ReportingTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("reportingType")
  private ReportingTypeEnum reportingType = null;

  @JsonProperty("minimumReportingPeriod")
  private Integer minimumReportingPeriod = null;

  public LoggingJobConfigReportingCondition reportingType(ReportingTypeEnum reportingType) {
    this.reportingType = reportingType;
    return this;
  }

  /**
   * Specifies the type of reporting condition. Permitted values: - REPORTING_ON_COMPILATION: the producer shall notify the consumer once the compilation of the                 collected logging data into a file is completed and a new log report is available. - NO_REPORTING: no reporting is requested (the consumer can query the logging jobs to know about the                 availability of new log reports).
   * @return reportingType
   **/
  @Schema(required = true, description = "Specifies the type of reporting condition. Permitted values: - REPORTING_ON_COMPILATION: the producer shall notify the consumer once the compilation of the                 collected logging data into a file is completed and a new log report is available. - NO_REPORTING: no reporting is requested (the consumer can query the logging jobs to know about the                 availability of new log reports).")
      @NotNull

    public ReportingTypeEnum getReportingType() {
    return reportingType;
  }

  public void setReportingType(ReportingTypeEnum reportingType) {
    this.reportingType = reportingType;
  }

  public LoggingJobConfigReportingCondition minimumReportingPeriod(Integer minimumReportingPeriod) {
    this.minimumReportingPeriod = minimumReportingPeriod;
    return this;
  }

  /**
   * Specifies the minimum periodicity at which the producer will report to the consumer about the collected log information, in seconds.
   * @return minimumReportingPeriod
   **/
  @Schema(description = "Specifies the minimum periodicity at which the producer will report to the consumer about the collected log information, in seconds.")
  
    public Integer getMinimumReportingPeriod() {
    return minimumReportingPeriod;
  }

  public void setMinimumReportingPeriod(Integer minimumReportingPeriod) {
    this.minimumReportingPeriod = minimumReportingPeriod;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LoggingJobConfigReportingCondition loggingJobConfigReportingCondition = (LoggingJobConfigReportingCondition) o;
    return Objects.equals(this.reportingType, loggingJobConfigReportingCondition.reportingType) &&
        Objects.equals(this.minimumReportingPeriod, loggingJobConfigReportingCondition.minimumReportingPeriod);
  }

  @Override
  public int hashCode() {
    return Objects.hash(reportingType, minimumReportingPeriod);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LoggingJobConfigReportingCondition {\n");
    
    sb.append("    reportingType: ").append(toIndentedString(reportingType)).append("\n");
    sb.append("    minimumReportingPeriod: ").append(toIndentedString(minimumReportingPeriod)).append("\n");
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
