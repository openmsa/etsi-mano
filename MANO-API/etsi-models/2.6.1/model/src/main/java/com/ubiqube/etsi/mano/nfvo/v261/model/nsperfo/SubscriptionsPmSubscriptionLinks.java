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

/**
  * Links to resources related to this resource. 
 **/
@Schema(description="Links to resources related to this resource. ")
public class SubscriptionsPmSubscriptionLinks  {
  
  @Schema(required = true, description = "")
  @Valid
  private PmJobsPmJobReportsLinksSelf self = null;
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

  public SubscriptionsPmSubscriptionLinks self(PmJobsPmJobReportsLinksSelf self) {
    this.self = self;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubscriptionsPmSubscriptionLinks {\n");
    
    sb.append("    self: ").append(toIndentedString(self)).append("\n");
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

