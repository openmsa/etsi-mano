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

package com.ubiqube.etsi.mano.nfvo.v271.model.nsperfo;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v271.model.nsperfo.DateTime2;
import com.ubiqube.etsi.mano.nfvo.v271.model.nsperfo.PmJobReportsLinks;
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
  private DateTime2 readyTime = null;

  @JsonProperty("expiryTime")
  private DateTime2 expiryTime = null;

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
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getHref() {
    return href;
  }

  public void setHref(String href) {
    this.href = href;
  }

  public PmJobReports readyTime(DateTime2 readyTime) {
    this.readyTime = readyTime;
    return this;
  }

  /**
   * Get readyTime
   * @return readyTime
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public DateTime2 getReadyTime() {
    return readyTime;
  }

  public void setReadyTime(DateTime2 readyTime) {
    this.readyTime = readyTime;
  }

  public PmJobReports expiryTime(DateTime2 expiryTime) {
    this.expiryTime = expiryTime;
    return this;
  }

  /**
   * Get expiryTime
   * @return expiryTime
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public DateTime2 getExpiryTime() {
    return expiryTime;
  }

  public void setExpiryTime(DateTime2 expiryTime) {
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
  @ApiModelProperty(value = "The size of the report file in bytes, if known. ")
  
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
  @ApiModelProperty(required = true, value = "")
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
