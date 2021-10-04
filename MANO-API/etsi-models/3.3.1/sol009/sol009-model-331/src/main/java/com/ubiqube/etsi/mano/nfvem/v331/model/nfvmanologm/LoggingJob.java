package com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanologm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanologm.LogReportLinks;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanologm.LoggingJobConfig;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanologm.LoggingJobCriteria;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanologm.LoggingJobLogReports;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanologm.ManoManagedObjectReference;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents a logging job. It shall comply with the provisions defined in table 8.6.2.6-1.
 */
@Schema(description = "This type represents a logging job. It shall comply with the provisions defined in table 8.6.2.6-1.")
@Validated


public class LoggingJob   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("objectInstanceIds")
  @Valid
  private List<ManoManagedObjectReference> objectInstanceIds = new ArrayList<>();

  @JsonProperty("jobCriteria")
  private LoggingJobCriteria jobCriteria = null;

  @JsonProperty("jobConfig")
  private LoggingJobConfig jobConfig = null;

  @JsonProperty("logReports")
  @Valid
  private List<LoggingJobLogReports> logReports = null;

  @JsonProperty("_links")
  private LogReportLinks _links = null;

  public LoggingJob id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public LoggingJob objectInstanceIds(List<ManoManagedObjectReference> objectInstanceIds) {
    this.objectInstanceIds = objectInstanceIds;
    return this;
  }

  public LoggingJob addObjectInstanceIdsItem(ManoManagedObjectReference objectInstanceIdsItem) {
    this.objectInstanceIds.add(objectInstanceIdsItem);
    return this;
  }

  /**
   * Identifiers of the object instance for which logging information is collected. This attribute shall contain the identifier of the instance of the object that is logged according to their type.
   * @return objectInstanceIds
   **/
  @Schema(required = true, description = "Identifiers of the object instance for which logging information is collected. This attribute shall contain the identifier of the instance of the object that is logged according to their type.")
      @NotNull
    @Valid
    public List<ManoManagedObjectReference> getObjectInstanceIds() {
    return objectInstanceIds;
  }

  public void setObjectInstanceIds(List<ManoManagedObjectReference> objectInstanceIds) {
    this.objectInstanceIds = objectInstanceIds;
  }

  public LoggingJob jobCriteria(LoggingJobCriteria jobCriteria) {
    this.jobCriteria = jobCriteria;
    return this;
  }

  /**
   * Get jobCriteria
   * @return jobCriteria
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public LoggingJobCriteria getJobCriteria() {
    return jobCriteria;
  }

  public void setJobCriteria(LoggingJobCriteria jobCriteria) {
    this.jobCriteria = jobCriteria;
  }

  public LoggingJob jobConfig(LoggingJobConfig jobConfig) {
    this.jobConfig = jobConfig;
    return this;
  }

  /**
   * Get jobConfig
   * @return jobConfig
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public LoggingJobConfig getJobConfig() {
    return jobConfig;
  }

  public void setJobConfig(LoggingJobConfig jobConfig) {
    this.jobConfig = jobConfig;
  }

  public LoggingJob logReports(List<LoggingJobLogReports> logReports) {
    this.logReports = logReports;
    return this;
  }

  public LoggingJob addLogReportsItem(LoggingJobLogReports logReportsItem) {
    if (this.logReports == null) {
      this.logReports = new ArrayList<>();
    }
    this.logReports.add(logReportsItem);
    return this;
  }

  /**
   * Information about available log reports created by the logging job.
   * @return logReports
   **/
  @Schema(description = "Information about available log reports created by the logging job.")
      @Valid
    public List<LoggingJobLogReports> getLogReports() {
    return logReports;
  }

  public void setLogReports(List<LoggingJobLogReports> logReports) {
    this.logReports = logReports;
  }

  public LoggingJob _links(LogReportLinks _links) {
    this._links = _links;
    return this;
  }

  /**
   * Get _links
   * @return _links
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public LogReportLinks getLinks() {
    return _links;
  }

  public void setLinks(LogReportLinks _links) {
    this._links = _links;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LoggingJob loggingJob = (LoggingJob) o;
    return Objects.equals(this.id, loggingJob.id) &&
        Objects.equals(this.objectInstanceIds, loggingJob.objectInstanceIds) &&
        Objects.equals(this.jobCriteria, loggingJob.jobCriteria) &&
        Objects.equals(this.jobConfig, loggingJob.jobConfig) &&
        Objects.equals(this.logReports, loggingJob.logReports) &&
        Objects.equals(this._links, loggingJob._links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, objectInstanceIds, jobCriteria, jobConfig, logReports, _links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LoggingJob {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    objectInstanceIds: ").append(toIndentedString(objectInstanceIds)).append("\n");
    sb.append("    jobCriteria: ").append(toIndentedString(jobCriteria)).append("\n");
    sb.append("    jobConfig: ").append(toIndentedString(jobConfig)).append("\n");
    sb.append("    logReports: ").append(toIndentedString(logReports)).append("\n");
    sb.append("    _links: ").append(toIndentedString(_links)).append("\n");
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
