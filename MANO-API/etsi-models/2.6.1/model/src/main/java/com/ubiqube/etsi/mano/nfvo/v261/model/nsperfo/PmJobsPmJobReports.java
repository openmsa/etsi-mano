/**
 * This copy of Woodstox XML processor is licensed under the
 * Apache (Software) License, version 2.0 ("the License").
 * See the License for details about distribution rights, and the
 * specific rights regarding derivate works.
 *
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/
 *
 * A copy is also included in the downloadable source code package
 * containing Woodstox, in file "ASL2.0", under the same directory
 * as this file.
 */
package com.ubiqube.etsi.mano.nfvo.v261.model.nsperfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
  * Information about available reports collected by this PM job. 
 **/
@ApiModel(description="Information about available reports collected by this PM job. ")
public class PmJobsPmJobReports  {
  
  @ApiModelProperty(required = true, value = "String formatted according to IETF RFC 3986. ")
 /**
   * String formatted according to IETF RFC 3986. 
  **/
  private String href = null;

  @ApiModelProperty(required = true, value = "Date-time stamp.  Representation: String formatted according to IETF RFC 3339. ")
 /**
   * Date-time stamp.  Representation: String formatted according to IETF RFC 3339. 
  **/
  private Date readyTime = null;

  @ApiModelProperty(value = "Date-time stamp.  Representation: String formatted according to IETF RFC 3339. ")
 /**
   * Date-time stamp.  Representation: String formatted according to IETF RFC 3339. 
  **/
  private Date expiryTime = null;

  @ApiModelProperty(value = "The size of the report file in bytes, if known. ")
 /**
   * The size of the report file in bytes, if known. 
  **/
  private Integer fileSize = null;

  @ApiModelProperty(required = true, value = "")
  @Valid
  private PmJobsPmJobReportsLinks links = null;
 /**
   * String formatted according to IETF RFC 3986. 
   * @return href
  **/
  @JsonProperty("href")
  @NotNull
  public String getHref() {
    return href;
  }

  public void setHref(String href) {
    this.href = href;
  }

  public PmJobsPmJobReports href(String href) {
    this.href = href;
    return this;
  }

 /**
   * Date-time stamp.  Representation: String formatted according to IETF RFC 3339. 
   * @return readyTime
  **/
  @JsonProperty("readyTime")
  @NotNull
  public Date getReadyTime() {
    return readyTime;
  }

  public void setReadyTime(Date readyTime) {
    this.readyTime = readyTime;
  }

  public PmJobsPmJobReports readyTime(Date readyTime) {
    this.readyTime = readyTime;
    return this;
  }

 /**
   * Date-time stamp.  Representation: String formatted according to IETF RFC 3339. 
   * @return expiryTime
  **/
  @JsonProperty("expiryTime")
  public Date getExpiryTime() {
    return expiryTime;
  }

  public void setExpiryTime(Date expiryTime) {
    this.expiryTime = expiryTime;
  }

  public PmJobsPmJobReports expiryTime(Date expiryTime) {
    this.expiryTime = expiryTime;
    return this;
  }

 /**
   * The size of the report file in bytes, if known. 
   * @return fileSize
  **/
  @JsonProperty("fileSize")
  public Integer getFileSize() {
    return fileSize;
  }

  public void setFileSize(Integer fileSize) {
    this.fileSize = fileSize;
  }

  public PmJobsPmJobReports fileSize(Integer fileSize) {
    this.fileSize = fileSize;
    return this;
  }

 /**
   * Get links
   * @return links
  **/
  @JsonProperty("_links")
  @NotNull
  public PmJobsPmJobReportsLinks getLinks() {
    return links;
  }

  public void setLinks(PmJobsPmJobReportsLinks links) {
    this.links = links;
  }

  public PmJobsPmJobReports links(PmJobsPmJobReportsLinks links) {
    this.links = links;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PmJobsPmJobReports {\n");
    
    sb.append("    href: ").append(toIndentedString(href)).append("\n");
    sb.append("    readyTime: ").append(toIndentedString(readyTime)).append("\n");
    sb.append("    expiryTime: ").append(toIndentedString(expiryTime)).append("\n");
    sb.append("    fileSize: ").append(toIndentedString(fileSize)).append("\n");
    sb.append("    links: ").append(toIndentedString(links)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private static String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

