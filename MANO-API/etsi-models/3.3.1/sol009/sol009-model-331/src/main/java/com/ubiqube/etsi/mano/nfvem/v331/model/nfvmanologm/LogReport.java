package com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanologm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanologm.LogReportFileLocationInfo;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanologm.LogReportLinks;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanologm.LogReportSecurityAndIntegrityInfo;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanologm.ManoManagedObjectReference;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents a log report, which provides information about a compiled log and how to obtain it. It shall comply with the provisions defined in table 8.6.2.7-1. 
 */
@Schema(description = "This type represents a log report, which provides information about a compiled log and how to obtain it. It shall comply with the provisions defined in table 8.6.2.7-1. ")
@Validated


public class LogReport   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("objectInstanceId")
  private ManoManagedObjectReference objectInstanceId = null;

  /**
   * The trigger for the compilation of the log file. Permitted values: - ON_DEMAND: created based on explicit request by a client. - AUTOMATIC: created according to the logging job compilation configuration.
   */
  public enum CompilationTriggerEnum {
    ON_DEMAND("ON_DEMAND"),
    
    AUTOMATIC("AUTOMATIC");

    private String value;

    CompilationTriggerEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static CompilationTriggerEnum fromValue(String text) {
      for (CompilationTriggerEnum b : CompilationTriggerEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("compilationTrigger")
  private CompilationTriggerEnum compilationTrigger = null;

  @JsonProperty("readyTime")
  private OffsetDateTime readyTime = null;

  @JsonProperty("expiryTime")
  private OffsetDateTime expiryTime = null;

  @JsonProperty("fileSize")
  private Integer fileSize = null;

  @JsonProperty("fileFormat")
  private String fileFormat = null;

  @JsonProperty("fileLocationInfo")
  private LogReportFileLocationInfo fileLocationInfo = null;

  @JsonProperty("securityAndIntegrityInfo")
  private LogReportSecurityAndIntegrityInfo securityAndIntegrityInfo = null;

  @JsonProperty("_links")
  private LogReportLinks _links = null;

  public LogReport id(String id) {
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

  public LogReport objectInstanceId(ManoManagedObjectReference objectInstanceId) {
    this.objectInstanceId = objectInstanceId;
    return this;
  }

  /**
   * Get objectInstanceId
   * @return objectInstanceId
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public ManoManagedObjectReference getObjectInstanceId() {
    return objectInstanceId;
  }

  public void setObjectInstanceId(ManoManagedObjectReference objectInstanceId) {
    this.objectInstanceId = objectInstanceId;
  }

  public LogReport compilationTrigger(CompilationTriggerEnum compilationTrigger) {
    this.compilationTrigger = compilationTrigger;
    return this;
  }

  /**
   * The trigger for the compilation of the log file. Permitted values: - ON_DEMAND: created based on explicit request by a client. - AUTOMATIC: created according to the logging job compilation configuration.
   * @return compilationTrigger
   **/
  @Schema(required = true, description = "The trigger for the compilation of the log file. Permitted values: - ON_DEMAND: created based on explicit request by a client. - AUTOMATIC: created according to the logging job compilation configuration.")
      @NotNull

    public CompilationTriggerEnum getCompilationTrigger() {
    return compilationTrigger;
  }

  public void setCompilationTrigger(CompilationTriggerEnum compilationTrigger) {
    this.compilationTrigger = compilationTrigger;
  }

  public LogReport readyTime(OffsetDateTime readyTime) {
    this.readyTime = readyTime;
    return this;
  }

  /**
   * Get readyTime
   * @return readyTime
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public OffsetDateTime getReadyTime() {
    return readyTime;
  }

  public void setReadyTime(OffsetDateTime readyTime) {
    this.readyTime = readyTime;
  }

  public LogReport expiryTime(OffsetDateTime expiryTime) {
    this.expiryTime = expiryTime;
    return this;
  }

  /**
   * Get expiryTime
   * @return expiryTime
   **/
  @Schema(description = "")
  
    @Valid
    public OffsetDateTime getExpiryTime() {
    return expiryTime;
  }

  public void setExpiryTime(OffsetDateTime expiryTime) {
    this.expiryTime = expiryTime;
  }

  public LogReport fileSize(Integer fileSize) {
    this.fileSize = fileSize;
    return this;
  }

  /**
   * The size of the compiled log file in bytes, if known.
   * @return fileSize
   **/
  @Schema(description = "The size of the compiled log file in bytes, if known.")
  
    public Integer getFileSize() {
    return fileSize;
  }

  public void setFileSize(Integer fileSize) {
    this.fileSize = fileSize;
  }

  public LogReport fileFormat(String fileFormat) {
    this.fileFormat = fileFormat;
    return this;
  }

  /**
   * The encoding used by the file.
   * @return fileFormat
   **/
  @Schema(required = true, description = "The encoding used by the file.")
      @NotNull

    public String getFileFormat() {
    return fileFormat;
  }

  public void setFileFormat(String fileFormat) {
    this.fileFormat = fileFormat;
  }

  public LogReport fileLocationInfo(LogReportFileLocationInfo fileLocationInfo) {
    this.fileLocationInfo = fileLocationInfo;
    return this;
  }

  /**
   * Get fileLocationInfo
   * @return fileLocationInfo
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public LogReportFileLocationInfo getFileLocationInfo() {
    return fileLocationInfo;
  }

  public void setFileLocationInfo(LogReportFileLocationInfo fileLocationInfo) {
    this.fileLocationInfo = fileLocationInfo;
  }

  public LogReport securityAndIntegrityInfo(LogReportSecurityAndIntegrityInfo securityAndIntegrityInfo) {
    this.securityAndIntegrityInfo = securityAndIntegrityInfo;
    return this;
  }

  /**
   * Get securityAndIntegrityInfo
   * @return securityAndIntegrityInfo
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public LogReportSecurityAndIntegrityInfo getSecurityAndIntegrityInfo() {
    return securityAndIntegrityInfo;
  }

  public void setSecurityAndIntegrityInfo(LogReportSecurityAndIntegrityInfo securityAndIntegrityInfo) {
    this.securityAndIntegrityInfo = securityAndIntegrityInfo;
  }

  public LogReport _links(LogReportLinks _links) {
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
    LogReport logReport = (LogReport) o;
    return Objects.equals(this.id, logReport.id) &&
        Objects.equals(this.objectInstanceId, logReport.objectInstanceId) &&
        Objects.equals(this.compilationTrigger, logReport.compilationTrigger) &&
        Objects.equals(this.readyTime, logReport.readyTime) &&
        Objects.equals(this.expiryTime, logReport.expiryTime) &&
        Objects.equals(this.fileSize, logReport.fileSize) &&
        Objects.equals(this.fileFormat, logReport.fileFormat) &&
        Objects.equals(this.fileLocationInfo, logReport.fileLocationInfo) &&
        Objects.equals(this.securityAndIntegrityInfo, logReport.securityAndIntegrityInfo) &&
        Objects.equals(this._links, logReport._links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, objectInstanceId, compilationTrigger, readyTime, expiryTime, fileSize, fileFormat, fileLocationInfo, securityAndIntegrityInfo, _links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LogReport {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    objectInstanceId: ").append(toIndentedString(objectInstanceId)).append("\n");
    sb.append("    compilationTrigger: ").append(toIndentedString(compilationTrigger)).append("\n");
    sb.append("    readyTime: ").append(toIndentedString(readyTime)).append("\n");
    sb.append("    expiryTime: ").append(toIndentedString(expiryTime)).append("\n");
    sb.append("    fileSize: ").append(toIndentedString(fileSize)).append("\n");
    sb.append("    fileFormat: ").append(toIndentedString(fileFormat)).append("\n");
    sb.append("    fileLocationInfo: ").append(toIndentedString(fileLocationInfo)).append("\n");
    sb.append("    securityAndIntegrityInfo: ").append(toIndentedString(securityAndIntegrityInfo)).append("\n");
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
