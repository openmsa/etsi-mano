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
package com.ubiqube.etsi.mec.meo.v211.model.pkg;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * &#x27;The data type represents the operational state for an application package resource&#x27;
 */
@ApiModel(description = "'The data type represents the operational state for an application package resource'")
@Validated
public class AppPkgInfoModifications   {
  /**
   * Gets or Sets operationState
   */
  public enum OperationStateEnum {
    DISABLED("DISABLED"),
    
    ENABLED("ENABLED");

    private String value;

    OperationStateEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static OperationStateEnum fromValue(String text) {
      for (OperationStateEnum b : OperationStateEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("operationState")
  private OperationStateEnum operationState = null;

  public AppPkgInfoModifications operationState(OperationStateEnum operationState) {
    this.operationState = operationState;
    return this;
  }

  /**
   * Get operationState
   * @return operationState
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public OperationStateEnum getOperationState() {
    return operationState;
  }

  public void setOperationState(OperationStateEnum operationState) {
    this.operationState = operationState;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AppPkgInfoModifications appPkgInfoModifications = (AppPkgInfoModifications) o;
    return Objects.equals(this.operationState, appPkgInfoModifications.operationState);
  }

  @Override
  public int hashCode() {
    return Objects.hash(operationState);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AppPkgInfoModifications {\n");
    
    sb.append("    operationState: ").append(toIndentedString(operationState)).append("\n");
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
