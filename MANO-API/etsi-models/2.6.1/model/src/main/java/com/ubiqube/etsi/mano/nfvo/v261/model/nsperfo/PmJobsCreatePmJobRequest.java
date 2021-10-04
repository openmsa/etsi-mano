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
  * This type represents a request to create a PM job.  It shall comply with the provisions defined in Table 7.5.2.6-1. 
 **/
@Schema(description="This type represents a request to create a PM job.  It shall comply with the provisions defined in Table 7.5.2.6-1. ")
public class PmJobsCreatePmJobRequest  {
  
  @Schema(required = true, description = "Identifiers of the NS instances for which performance information is requested to be collected. ")
 /**
   * Identifiers of the NS instances for which performance information is requested to be collected. 
  **/
  private List<String> objectInstanceIds = new ArrayList<String>();

  @Schema(required = true, description = "")
  @Valid
  private PmJobsPmJobCriteria criteria = null;
 /**
   * Identifiers of the NS instances for which performance information is requested to be collected. 
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

  public PmJobsCreatePmJobRequest objectInstanceIds(List<String> objectInstanceIds) {
    this.objectInstanceIds = objectInstanceIds;
    return this;
  }

  public PmJobsCreatePmJobRequest addObjectInstanceIdsItem(String objectInstanceIdsItem) {
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

  public PmJobsCreatePmJobRequest criteria(PmJobsPmJobCriteria criteria) {
    this.criteria = criteria;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PmJobsCreatePmJobRequest {\n");
    
    sb.append("    objectInstanceIds: ").append(toIndentedString(objectInstanceIds)).append("\n");
    sb.append("    criteria: ").append(toIndentedString(criteria)).append("\n");
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

