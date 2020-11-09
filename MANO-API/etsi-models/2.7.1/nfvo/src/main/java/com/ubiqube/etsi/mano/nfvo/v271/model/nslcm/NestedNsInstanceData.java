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

package com.ubiqube.etsi.mano.nfvo.v271.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type specifies an existing nested NS instance to be used in the NS instance  and if needed, the NsProfile to use for this nested NS instance.  It shall comply with the provisions defined in Table 6.5.3.19a-1. 
 */
@ApiModel(description = "This type specifies an existing nested NS instance to be used in the NS instance  and if needed, the NsProfile to use for this nested NS instance.  It shall comply with the provisions defined in Table 6.5.3.19a-1. ")
@Validated
public class NestedNsInstanceData   {
  @JsonProperty("nestedNsInstanceId")
  private String nestedNsInstanceId = null;

  @JsonProperty("nsProfileId")
  private String nsProfileId = null;

  public NestedNsInstanceData nestedNsInstanceId(String nestedNsInstanceId) {
    this.nestedNsInstanceId = nestedNsInstanceId;
    return this;
  }

  /**
   * Get nestedNsInstanceId
   * @return nestedNsInstanceId
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getNestedNsInstanceId() {
    return nestedNsInstanceId;
  }

  public void setNestedNsInstanceId(String nestedNsInstanceId) {
    this.nestedNsInstanceId = nestedNsInstanceId;
  }

  public NestedNsInstanceData nsProfileId(String nsProfileId) {
    this.nsProfileId = nsProfileId;
    return this;
  }

  /**
   * Get nsProfileId
   * @return nsProfileId
  **/
  @ApiModelProperty(value = "")
  
    public String getNsProfileId() {
    return nsProfileId;
  }

  public void setNsProfileId(String nsProfileId) {
    this.nsProfileId = nsProfileId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NestedNsInstanceData nestedNsInstanceData = (NestedNsInstanceData) o;
    return Objects.equals(this.nestedNsInstanceId, nestedNsInstanceData.nestedNsInstanceId) &&
        Objects.equals(this.nsProfileId, nestedNsInstanceData.nsProfileId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nestedNsInstanceId, nsProfileId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NestedNsInstanceData {\n");
    
    sb.append("    nestedNsInstanceId: ").append(toIndentedString(nestedNsInstanceId)).append("\n");
    sb.append("    nsProfileId: ").append(toIndentedString(nsProfileId)).append("\n");
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
