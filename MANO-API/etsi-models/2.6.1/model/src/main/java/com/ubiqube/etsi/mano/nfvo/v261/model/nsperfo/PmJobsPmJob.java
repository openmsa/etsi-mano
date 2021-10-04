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

package com.ubiqube.etsi.mano.nfvo.v261.model.nsperfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
  * This type represents a PM job. 
 **/
@Schema(description="This type represents a PM job. ")
public class PmJobsPmJob  {
  
  @Schema(required = true, description = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String id = null;

  @Schema(required = true, description = "Identifiers of the NS instances for which performance information is collected. ")
 /**
   * Identifiers of the NS instances for which performance information is collected. 
  **/
  private List<String> objectInstanceIds = new ArrayList<String>();

  @Schema(required = true, description = "")
  @Valid
  private PmJobsPmJobCriteria criteria = null;

  @Schema(description = "")
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

