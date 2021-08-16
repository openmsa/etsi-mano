package com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanologm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanologm.Link;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * LoggingJobLogReports
 */
@Validated


public class LoggingJobLogReports   {
  @JsonProperty("logReportId")
  private String logReportId = null;

  @JsonProperty("logReportLoc")
  private Link logReportLoc = null;

  public LoggingJobLogReports logReportId(String logReportId) {
    this.logReportId = logReportId;
    return this;
  }

  /**
   * Get logReportId
   * @return logReportId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getLogReportId() {
    return logReportId;
  }

  public void setLogReportId(String logReportId) {
    this.logReportId = logReportId;
  }

  public LoggingJobLogReports logReportLoc(Link logReportLoc) {
    this.logReportLoc = logReportLoc;
    return this;
  }

  /**
   * Get logReportLoc
   * @return logReportLoc
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public Link getLogReportLoc() {
    return logReportLoc;
  }

  public void setLogReportLoc(Link logReportLoc) {
    this.logReportLoc = logReportLoc;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LoggingJobLogReports loggingJobLogReports = (LoggingJobLogReports) o;
    return Objects.equals(this.logReportId, loggingJobLogReports.logReportId) &&
        Objects.equals(this.logReportLoc, loggingJobLogReports.logReportLoc);
  }

  @Override
  public int hashCode() {
    return Objects.hash(logReportId, logReportLoc);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LoggingJobLogReports {\n");
    
    sb.append("    logReportId: ").append(toIndentedString(logReportId)).append("\n");
    sb.append("    logReportLoc: ").append(toIndentedString(logReportLoc)).append("\n");
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
