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
package com.ubiqube.etsi.mano.vnfm.v331.model.grant;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type provides information regarding a resource zone group. A resource zone group is a group of one or more related resource zones which can be used in resource placement constraints. To fulfil such constraint, the NFVO may decide to place a resource into any zone that belongs to a particular group. NOTE: A resource zone group can be used to support overflow from one resource zone into another, in case a particular deployment supports only non-elastic resource zones. 
 */
@Schema(description = "This type provides information regarding a resource zone group. A resource zone group is a group of one or more related resource zones which can be used in resource placement constraints. To fulfil such constraint, the NFVO may decide to place a resource into any zone that belongs to a particular group. NOTE: A resource zone group can be used to support overflow from one resource zone into another, in case a particular deployment supports only non-elastic resource zones. ")
@Validated


public class ZoneGroupInfo   {
  @JsonProperty("zoneId")
  @Valid
  private List<String> zoneId = new ArrayList<>();

  public ZoneGroupInfo zoneId(List<String> zoneId) {
    this.zoneId = zoneId;
    return this;
  }

  public ZoneGroupInfo addZoneIdItem(String zoneIdItem) {
    this.zoneId.add(zoneIdItem);
    return this;
  }

  /**
   * References of identifiers of \"ZoneInfo\" structures, each of which provides information about a resource zone that belongs to this group. 
   * @return zoneId
   **/
  @Schema(required = true, description = "References of identifiers of \"ZoneInfo\" structures, each of which provides information about a resource zone that belongs to this group. ")
      @NotNull

    public List<String> getZoneId() {
    return zoneId;
  }

  public void setZoneId(List<String> zoneId) {
    this.zoneId = zoneId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ZoneGroupInfo zoneGroupInfo = (ZoneGroupInfo) o;
    return Objects.equals(this.zoneId, zoneGroupInfo.zoneId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(zoneId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ZoneGroupInfo {\n");
    
    sb.append("    zoneId: ").append(toIndentedString(zoneId)).append("\n");
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
