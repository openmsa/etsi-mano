package com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanologm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanologm.LoggingJobConfig;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanologm.LoggingJobCriteria;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanologm.ManoManagedObjectReference;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Information on application context created by the MEC system
 */
@Schema(description = "Information on application context created by the MEC system")
@Validated


public class CreateLoggingJobRequest   {
  @JsonProperty("objectInstanceIds")
  @Valid
  private List<ManoManagedObjectReference> objectInstanceIds = new ArrayList<>();

  @JsonProperty("jobCriteria")
  private LoggingJobCriteria jobCriteria = null;

  @JsonProperty("jobConfig")
  private LoggingJobConfig jobConfig = null;

  public CreateLoggingJobRequest objectInstanceIds(List<ManoManagedObjectReference> objectInstanceIds) {
    this.objectInstanceIds = objectInstanceIds;
    return this;
  }

  public CreateLoggingJobRequest addObjectInstanceIdsItem(ManoManagedObjectReference objectInstanceIdsItem) {
    this.objectInstanceIds.add(objectInstanceIdsItem);
    return this;
  }

  /**
   * Identifiers of the object instance for which logging information is requested to be collected. This attribute shall contain the identifier of the instance of the object to be logged according to their type. If more than one identifier is provided, values shall all refer to object instances of the same type, for which the same criteria is then applicable.
   * @return objectInstanceIds
   **/
  @Schema(required = true, description = "Identifiers of the object instance for which logging information is requested to be collected. This attribute shall contain the identifier of the instance of the object to be logged according to their type. If more than one identifier is provided, values shall all refer to object instances of the same type, for which the same criteria is then applicable.")
      @NotNull
    @Valid
  @Size(min=1)   public List<ManoManagedObjectReference> getObjectInstanceIds() {
    return objectInstanceIds;
  }

  public void setObjectInstanceIds(List<ManoManagedObjectReference> objectInstanceIds) {
    this.objectInstanceIds = objectInstanceIds;
  }

  public CreateLoggingJobRequest jobCriteria(LoggingJobCriteria jobCriteria) {
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

  public CreateLoggingJobRequest jobConfig(LoggingJobConfig jobConfig) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateLoggingJobRequest createLoggingJobRequest = (CreateLoggingJobRequest) o;
    return Objects.equals(this.objectInstanceIds, createLoggingJobRequest.objectInstanceIds) &&
        Objects.equals(this.jobCriteria, createLoggingJobRequest.jobCriteria) &&
        Objects.equals(this.jobConfig, createLoggingJobRequest.jobConfig);
  }

  @Override
  public int hashCode() {
    return Objects.hash(objectInstanceIds, jobCriteria, jobConfig);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateLoggingJobRequest {\n");
    
    sb.append("    objectInstanceIds: ").append(toIndentedString(objectInstanceIds)).append("\n");
    sb.append("    jobCriteria: ").append(toIndentedString(jobCriteria)).append("\n");
    sb.append("    jobConfig: ").append(toIndentedString(jobConfig)).append("\n");
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
