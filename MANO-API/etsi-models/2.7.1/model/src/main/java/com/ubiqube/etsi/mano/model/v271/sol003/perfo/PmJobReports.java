/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.etsi.mano.model.v271.sol003.perfo;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Information about available reports collected by this PM job. 
 */
@ApiModel(description = "Information about available reports collected by this PM job. ")
@Validated


public class PmJobReports   {
  @JsonProperty("href")
  private String href = null;

  @JsonProperty("readyTime")
  private String readyTime = null;

  @JsonProperty("expiryTime")
  private String expiryTime = null;

  @JsonProperty("fileSize")
  private Integer fileSize = null;

  public PmJobReports href(String href) {
    this.href = href;
    return this;
  }

  /**
   * The URI where the report can be obtained. 
   * @return href
  **/
  @ApiModelProperty(required = true, value = "The URI where the report can be obtained. ")
  @NotNull


  public String getHref() {
    return href;
  }

  public void setHref(String href) {
    this.href = href;
  }

  public PmJobReports readyTime(String readyTime) {
    this.readyTime = readyTime;
    return this;
  }

  /**
   * The time when the report was made available. 
   * @return readyTime
  **/
  @ApiModelProperty(required = true, value = "The time when the report was made available. ")
  @NotNull


  public String getReadyTime() {
    return readyTime;
  }

  public void setReadyTime(String readyTime) {
    this.readyTime = readyTime;
  }

  public PmJobReports expiryTime(String expiryTime) {
    this.expiryTime = expiryTime;
    return this;
  }

  /**
   * The time when the report will expire. 
   * @return expiryTime
  **/
  @ApiModelProperty(value = "The time when the report will expire. ")


  public String getExpiryTime() {
    return expiryTime;
  }

  public void setExpiryTime(String expiryTime) {
    this.expiryTime = expiryTime;
  }

  public PmJobReports fileSize(Integer fileSize) {
    this.fileSize = fileSize;
    return this;
  }

  /**
   * The size of the report file in bytes, if known. 
   * minimum: 0
   * maximum: 1024
   * @return fileSize
  **/
  @ApiModelProperty(value = "The size of the report file in bytes, if known. ")

@Min(0) @Max(1024) 
  public Integer getFileSize() {
    return fileSize;
  }

  public void setFileSize(Integer fileSize) {
    this.fileSize = fileSize;
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
        Objects.equals(this.fileSize, pmJobReports.fileSize);
  }

  @Override
  public int hashCode() {
    return Objects.hash(href, readyTime, expiryTime, fileSize);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PmJobReports {\n");
    
    sb.append("    href: ").append(toIndentedString(href)).append("\n");
    sb.append("    readyTime: ").append(toIndentedString(readyTime)).append("\n");
    sb.append("    expiryTime: ").append(toIndentedString(expiryTime)).append("\n");
    sb.append("    fileSize: ").append(toIndentedString(fileSize)).append("\n");
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

