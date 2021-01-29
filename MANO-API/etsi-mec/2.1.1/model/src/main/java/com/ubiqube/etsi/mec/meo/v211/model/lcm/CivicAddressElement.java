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
package com.ubiqube.etsi.mec.meo.v211.model.lcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * &#x27;The civic address.&#x27;
 */
@ApiModel(description = "'The civic address.'")
@Validated
public class CivicAddressElement   {
  @JsonProperty("caType")
  private Integer caType = null;

  @JsonProperty("caValue")
  private String caValue = null;

  public CivicAddressElement caType(Integer caType) {
    this.caType = caType;
    return this;
  }

  /**
   * Get caType
   * @return caType
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public Integer getCaType() {
    return caType;
  }

  public void setCaType(Integer caType) {
    this.caType = caType;
  }

  public CivicAddressElement caValue(String caValue) {
    this.caValue = caValue;
    return this;
  }

  /**
   * Get caValue
   * @return caValue
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getCaValue() {
    return caValue;
  }

  public void setCaValue(String caValue) {
    this.caValue = caValue;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CivicAddressElement civicAddressElement = (CivicAddressElement) o;
    return Objects.equals(this.caType, civicAddressElement.caType) &&
        Objects.equals(this.caValue, civicAddressElement.caValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(caType, caValue);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CivicAddressElement {\n");
    
    sb.append("    caType: ").append(toIndentedString(caType)).append("\n");
    sb.append("    caValue: ").append(toIndentedString(caValue)).append("\n");
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
