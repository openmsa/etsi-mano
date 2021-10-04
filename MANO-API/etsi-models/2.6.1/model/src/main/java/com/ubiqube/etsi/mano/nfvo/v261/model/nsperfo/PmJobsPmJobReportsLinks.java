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
import java.util.List;

/**
  * Links for this resource. 
 **/
@Schema(description="Links for this resource. ")
public class PmJobsPmJobReportsLinks  {
  
  @Schema(required = true, description = "")
  @Valid
  private PmJobsPmJobReportsLinksSelf self = null;

  @Schema(description = "Links to resources representing the NS instances for which performance information is collected. Shall be present if the NS instance information is accessible as a resource. ")
  @Valid
 /**
   * Links to resources representing the NS instances for which performance information is collected. Shall be present if the NS instance information is accessible as a resource. 
  **/
  private List<PmJobsPmJobReportsLinksSelf> objects = null;
 /**
   * Get self
   * @return self
  **/
  @JsonProperty("self")
  @NotNull
  public PmJobsPmJobReportsLinksSelf getSelf() {
    return self;
  }

  public void setSelf(PmJobsPmJobReportsLinksSelf self) {
    this.self = self;
  }

  public PmJobsPmJobReportsLinks self(PmJobsPmJobReportsLinksSelf self) {
    this.self = self;
    return this;
  }

 /**
   * Links to resources representing the NS instances for which performance information is collected. Shall be present if the NS instance information is accessible as a resource. 
   * @return objects
  **/
  @JsonProperty("objects")
  public List<PmJobsPmJobReportsLinksSelf> getObjects() {
    return objects;
  }

  public void setObjects(List<PmJobsPmJobReportsLinksSelf> objects) {
    this.objects = objects;
  }

  public PmJobsPmJobReportsLinks objects(List<PmJobsPmJobReportsLinksSelf> objects) {
    this.objects = objects;
    return this;
  }

  public PmJobsPmJobReportsLinks addObjectsItem(PmJobsPmJobReportsLinksSelf objectsItem) {
    this.objects.add(objectsItem);
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PmJobsPmJobReportsLinks {\n");
    
    sb.append("    self: ").append(toIndentedString(self)).append("\n");
    sb.append("    objects: ").append(toIndentedString(objects)).append("\n");
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

