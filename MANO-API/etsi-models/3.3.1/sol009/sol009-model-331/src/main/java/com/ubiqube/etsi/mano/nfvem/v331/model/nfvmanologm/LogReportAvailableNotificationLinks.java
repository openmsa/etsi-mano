package com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanologm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanologm.NotificationLink;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Links to resources related to this notification.
 */
@Schema(description = "Links to resources related to this notification.")
@Validated


public class LogReportAvailableNotificationLinks   {
  @JsonProperty("subscription")
  private NotificationLink subscription = null;

  @JsonProperty("objectInstance")
  private NotificationLink objectInstance = null;

  @JsonProperty("LoggingJob")
  private NotificationLink loggingJob = null;

  @JsonProperty("logReports")
  @Valid
  private List<NotificationLink> logReports = new ArrayList<>();

  public LogReportAvailableNotificationLinks subscription(NotificationLink subscription) {
    this.subscription = subscription;
    return this;
  }

  /**
   * Get subscription
   * @return subscription
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public NotificationLink getSubscription() {
    return subscription;
  }

  public void setSubscription(NotificationLink subscription) {
    this.subscription = subscription;
  }

  public LogReportAvailableNotificationLinks objectInstance(NotificationLink objectInstance) {
    this.objectInstance = objectInstance;
    return this;
  }

  /**
   * Get objectInstance
   * @return objectInstance
   **/
  @Schema(description = "")
  
    @Valid
    public NotificationLink getObjectInstance() {
    return objectInstance;
  }

  public void setObjectInstance(NotificationLink objectInstance) {
    this.objectInstance = objectInstance;
  }

  public LogReportAvailableNotificationLinks loggingJob(NotificationLink loggingJob) {
    this.loggingJob = loggingJob;
    return this;
  }

  /**
   * Get loggingJob
   * @return loggingJob
   **/
  @Schema(description = "")
  
    @Valid
    public NotificationLink getLoggingJob() {
    return loggingJob;
  }

  public void setLoggingJob(NotificationLink loggingJob) {
    this.loggingJob = loggingJob;
  }

  public LogReportAvailableNotificationLinks logReports(List<NotificationLink> logReports) {
    this.logReports = logReports;
    return this;
  }

  public LogReportAvailableNotificationLinks addLogReportsItem(NotificationLink logReportsItem) {
    this.logReports.add(logReportsItem);
    return this;
  }

  /**
   * Link from which the available log report can be obtained. Due to the relationship of the logging job compilation and the logging information availability reporting, more than one logReport notification link can be provided.
   * @return logReports
   **/
  @Schema(required = true, description = "Link from which the available log report can be obtained. Due to the relationship of the logging job compilation and the logging information availability reporting, more than one logReport notification link can be provided.")
      @NotNull
    @Valid
    public List<NotificationLink> getLogReports() {
    return logReports;
  }

  public void setLogReports(List<NotificationLink> logReports) {
    this.logReports = logReports;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LogReportAvailableNotificationLinks logReportAvailableNotificationLinks = (LogReportAvailableNotificationLinks) o;
    return Objects.equals(this.subscription, logReportAvailableNotificationLinks.subscription) &&
        Objects.equals(this.objectInstance, logReportAvailableNotificationLinks.objectInstance) &&
        Objects.equals(this.loggingJob, logReportAvailableNotificationLinks.loggingJob) &&
        Objects.equals(this.logReports, logReportAvailableNotificationLinks.logReports);
  }

  @Override
  public int hashCode() {
    return Objects.hash(subscription, objectInstance, loggingJob, logReports);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LogReportAvailableNotificationLinks {\n");
    
    sb.append("    subscription: ").append(toIndentedString(subscription)).append("\n");
    sb.append("    objectInstance: ").append(toIndentedString(objectInstance)).append("\n");
    sb.append("    loggingJob: ").append(toIndentedString(loggingJob)).append("\n");
    sb.append("    logReports: ").append(toIndentedString(logReports)).append("\n");
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
