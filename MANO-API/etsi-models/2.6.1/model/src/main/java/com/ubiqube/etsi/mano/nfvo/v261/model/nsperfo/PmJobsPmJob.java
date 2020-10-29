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
import java.util.ArrayList;
import java.util.List;

/**
  * This type represents a PM job. 
 **/
@ApiModel(description="This type represents a PM job. ")
public class PmJobsPmJob  {
  
  @ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String id = null;

  @ApiModelProperty(required = true, value = "Identifiers of the NS instances for which performance information is collected. ")
 /**
   * Identifiers of the NS instances for which performance information is collected. 
  **/
  private List<String> objectInstanceIds = new ArrayList<String>();

  @ApiModelProperty(required = true, value = "")
  @Valid
  private PmJobsPmJobCriteria criteria = null;

  @ApiModelProperty(value = "")
  @Valid
  private PmJobsPmJobReports reports = null;
 /**
   * An identifier with the intention of being globally unique. 
   * @return id
  **/
  @JsonProperty("id")
  @NotNull
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public PmJobsPmJob id(String id) {
    this.id = id;
    return this;
  }

 /**
   * Identifiers of the NS instances for which performance information is collected. 
   * @return objectInstanceIds
  **/
  @JsonProperty("objectInstanceIds")
  @NotNull
  public List<String> getObjectInstanceIds() {
    return objectInstanceIds;
  }

  public void setObjectInstanceIds(List<String> objectInstanceIds) {
    this.objectInstanceIds = objectInstanceIds;
  }

  public PmJobsPmJob objectInstanceIds(List<String> objectInstanceIds) {
    this.objectInstanceIds = objectInstanceIds;
    return this;
  }

  public PmJobsPmJob addObjectInstanceIdsItem(String objectInstanceIdsItem) {
    this.objectInstanceIds.add(objectInstanceIdsItem);
    return this;
  }

 /**
   * Get criteria
   * @return criteria
  **/
  @JsonProperty("criteria")
  @NotNull
  public PmJobsPmJobCriteria getCriteria() {
    return criteria;
  }

  public void setCriteria(PmJobsPmJobCriteria criteria) {
    this.criteria = criteria;
  }

  public PmJobsPmJob criteria(PmJobsPmJobCriteria criteria) {
    this.criteria = criteria;
    return this;
  }

 /**
   * Get reports
   * @return reports
  **/
  @JsonProperty("reports")
  public PmJobsPmJobReports getReports() {
    return reports;
  }

  public void setReports(PmJobsPmJobReports reports) {
    this.reports = reports;
  }

  public PmJobsPmJob reports(PmJobsPmJobReports reports) {
    this.reports = reports;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PmJobsPmJob {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    objectInstanceIds: ").append(toIndentedString(objectInstanceIds)).append("\n");
    sb.append("    criteria: ").append(toIndentedString(criteria)).append("\n");
    sb.append("    reports: ").append(toIndentedString(reports)).append("\n");
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

