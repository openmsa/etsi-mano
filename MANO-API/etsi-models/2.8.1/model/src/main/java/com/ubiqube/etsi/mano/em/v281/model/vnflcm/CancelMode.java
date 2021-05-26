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
package com.ubiqube.etsi.mano.em.v281.model.vnflcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.em.v281.model.vnflcm.CancelModeType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents a parameter to select the mode of cancelling an ongoing VNF LCM operation occurrence. 
 */
@ApiModel(description = "This type represents a parameter to select the mode of cancelling an ongoing VNF LCM operation occurrence. ")
@Validated

public class CancelMode   {
  @JsonProperty("cancelMode")
  private CancelModeType cancelMode = null;

  public CancelMode cancelMode(CancelModeType cancelMode) {
    this.cancelMode = cancelMode;
    return this;
  }

  /**
   * Cancellation mode to apply. 
   * @return cancelMode
  **/
  @ApiModelProperty(required = true, value = "Cancellation mode to apply. ")
  @NotNull

  @Valid

  public CancelModeType getCancelMode() {
    return cancelMode;
  }

  public void setCancelMode(CancelModeType cancelMode) {
    this.cancelMode = cancelMode;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CancelMode cancelMode = (CancelMode) o;
    return Objects.equals(this.cancelMode, cancelMode.cancelMode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cancelMode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CancelMode {\n");
    
    sb.append("    cancelMode: ").append(toIndentedString(cancelMode)).append("\n");
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

