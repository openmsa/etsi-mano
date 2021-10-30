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
package com.ubiqube.etsi.mano.nfvo.v351.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * The PortRange data type provides the lower and upper bounds of a range of Internet ports. It shall comply with the provisions defined in Table 6.5.3.42-1. 
 */
@Schema(description = "The PortRange data type provides the lower and upper bounds of a range of Internet ports. It shall comply with the provisions defined in Table 6.5.3.42-1. ")
@Validated


public class PortRange   {
  @JsonProperty("lowerPort")
  private Integer lowerPort = null;

  @JsonProperty("upperPort")
  private Integer upperPort = null;

  public PortRange lowerPort(Integer lowerPort) {
    this.lowerPort = lowerPort;
    return this;
  }

  /**
   * Identifies the lower bound of the port range. upperPort Integer 
   * minimum: 0
   * @return lowerPort
   **/
  @Schema(required = true, description = "Identifies the lower bound of the port range. upperPort Integer ")
      @NotNull

  @Min(0)  public Integer getLowerPort() {
    return lowerPort;
  }

  public void setLowerPort(Integer lowerPort) {
    this.lowerPort = lowerPort;
  }

  public PortRange upperPort(Integer upperPort) {
    this.upperPort = upperPort;
    return this;
  }

  /**
   * Identifies the upper bound of the port range. 
   * minimum: 0
   * @return upperPort
   **/
  @Schema(required = true, description = "Identifies the upper bound of the port range. ")
      @NotNull

  @Min(0)  public Integer getUpperPort() {
    return upperPort;
  }

  public void setUpperPort(Integer upperPort) {
    this.upperPort = upperPort;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PortRange portRange = (PortRange) o;
    return Objects.equals(this.lowerPort, portRange.lowerPort) &&
        Objects.equals(this.upperPort, portRange.upperPort);
  }

  @Override
  public int hashCode() {
    return Objects.hash(lowerPort, upperPort);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PortRange {\n");
    
    sb.append("    lowerPort: ").append(toIndentedString(lowerPort)).append("\n");
    sb.append("    upperPort: ").append(toIndentedString(upperPort)).append("\n");
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
