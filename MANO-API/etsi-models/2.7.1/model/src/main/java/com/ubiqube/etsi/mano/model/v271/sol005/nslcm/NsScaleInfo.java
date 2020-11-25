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

package com.ubiqube.etsi.mano.model.v271.sol005.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents the target NS Scale level for each NS scaling aspect of the current deployment flavor. 
 */
@ApiModel(description = "This type represents the target NS Scale level for each NS scaling aspect of the current deployment flavor. ")
@Validated
public class NsScaleInfo   {
  @JsonProperty("nsScalingAspectId")
  private String nsScalingAspectId = null;

  @JsonProperty("nsScaleLevelId")
  private String nsScaleLevelId = null;

  public NsScaleInfo nsScalingAspectId(String nsScalingAspectId) {
    this.nsScalingAspectId = nsScalingAspectId;
    return this;
  }

  /**
   * Get nsScalingAspectId
   * @return nsScalingAspectId
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getNsScalingAspectId() {
    return nsScalingAspectId;
  }

  public void setNsScalingAspectId(String nsScalingAspectId) {
    this.nsScalingAspectId = nsScalingAspectId;
  }

  public NsScaleInfo nsScaleLevelId(String nsScaleLevelId) {
    this.nsScaleLevelId = nsScaleLevelId;
    return this;
  }

  /**
   * Get nsScaleLevelId
   * @return nsScaleLevelId
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getNsScaleLevelId() {
    return nsScaleLevelId;
  }

  public void setNsScaleLevelId(String nsScaleLevelId) {
    this.nsScaleLevelId = nsScaleLevelId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NsScaleInfo nsScaleInfo = (NsScaleInfo) o;
    return Objects.equals(this.nsScalingAspectId, nsScaleInfo.nsScalingAspectId) &&
        Objects.equals(this.nsScaleLevelId, nsScaleInfo.nsScaleLevelId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nsScalingAspectId, nsScaleLevelId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsScaleInfo {\n");
    
    sb.append("    nsScalingAspectId: ").append(toIndentedString(nsScalingAspectId)).append("\n");
    sb.append("    nsScaleLevelId: ").append(toIndentedString(nsScaleLevelId)).append("\n");
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
