package com.ubiqube.etsi.mano.nfvo.v331.model.nsperfo;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v331.model.nsperfo.PmJobReportsLinks;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Information about available reports collected by this PM job. 
 */
@Schema(description = "Information about available reports collected by this PM job. ")
@Validated


public class PmJobReports   {
  @JsonProperty("href")
  private String href = null;

  @JsonProperty("readyTime")
  private OffsetDateTime readyTime = null;

  @JsonProperty("expiryTime")
  private OffsetDateTime expiryTime = null;

  @JsonProperty("fileSize")
  private Integer fileSize = null;

  @JsonProperty("_links")
  private PmJobReportsLinks _links = null;

  public PmJobReports href(String href) {
    this.href = href;
    return this;
  }

  /**
   * Get href
   * @return href
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getHref() {
    return href;
  }

  public void setHref(String href) {
    this.href = href;
  }

  public PmJobReports readyTime(OffsetDateTime readyTime) {
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

  public PmJobReports expiryTime(OffsetDateTime expiryTime) {
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

  public PmJobReports fileSize(Integer fileSize) {
    this.fileSize = fileSize;
    return this;
  }

  /**
   * The size of the report file in bytes, if known. 
   * @return fileSize
   **/
  @Schema(description = "The size of the report file in bytes, if known. ")
  
    public Integer getFileSize() {
    return fileSize;
  }

  public void setFileSize(Integer fileSize) {
    this.fileSize = fileSize;
  }

  public PmJobReports _links(PmJobReportsLinks _links) {
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
    public PmJobReportsLinks getLinks() {
    return _links;
  }

  public void setLinks(PmJobReportsLinks _links) {
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
    PmJobReports pmJobReports = (PmJobReports) o;
    return Objects.equals(this.href, pmJobReports.href) &&
        Objects.equals(this.readyTime, pmJobReports.readyTime) &&
        Objects.equals(this.expiryTime, pmJobReports.expiryTime) &&
        Objects.equals(this.fileSize, pmJobReports.fileSize) &&
        Objects.equals(this._links, pmJobReports._links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(href, readyTime, expiryTime, fileSize, _links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PmJobReports {\n");
    
    sb.append("    href: ").append(toIndentedString(href)).append("\n");
    sb.append("    readyTime: ").append(toIndentedString(readyTime)).append("\n");
    sb.append("    expiryTime: ").append(toIndentedString(expiryTime)).append("\n");
    sb.append("    fileSize: ").append(toIndentedString(fileSize)).append("\n");
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
