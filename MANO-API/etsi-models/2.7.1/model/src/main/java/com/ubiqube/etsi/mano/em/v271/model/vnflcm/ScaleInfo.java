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
package com.ubiqube.etsi.mano.em.v271.model.vnflcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ScaleInfo
 */
@Validated

public class ScaleInfo   {
  @JsonProperty("aspectId")
  private String aspectId = null;

  @JsonProperty("scaleLevel")
  private Integer scaleLevel = null;

  public ScaleInfo aspectId(String aspectId) {
    this.aspectId = aspectId;
    return this;
  }

  /**
   * Identifier of the scaling aspect. 
   * @return aspectId
  **/
  @ApiModelProperty(required = true, value = "Identifier of the scaling aspect. ")
  @NotNull


  public String getAspectId() {
    return aspectId;
  }

  public void setAspectId(String aspectId) {
    this.aspectId = aspectId;
  }

  public ScaleInfo scaleLevel(Integer scaleLevel) {
    this.scaleLevel = scaleLevel;
    return this;
  }

  /**
   * Indicates the scale level. The minimum value shall be 0 and the maximum value shall be <= maxScaleLevel as described in the VNFD. 
   * @return scaleLevel
  **/
  @ApiModelProperty(required = true, value = "Indicates the scale level. The minimum value shall be 0 and the maximum value shall be <= maxScaleLevel as described in the VNFD. ")
  @NotNull


  public Integer getScaleLevel() {
    return scaleLevel;
  }

  public void setScaleLevel(Integer scaleLevel) {
    this.scaleLevel = scaleLevel;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ScaleInfo scaleInfo = (ScaleInfo) o;
    return Objects.equals(this.aspectId, scaleInfo.aspectId) &&
        Objects.equals(this.scaleLevel, scaleInfo.scaleLevel);
  }

  @Override
  public int hashCode() {
    return Objects.hash(aspectId, scaleLevel);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ScaleInfo {\n");
    
    sb.append("    aspectId: ").append(toIndentedString(aspectId)).append("\n");
    sb.append("    scaleLevel: ").append(toIndentedString(scaleLevel)).append("\n");
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

