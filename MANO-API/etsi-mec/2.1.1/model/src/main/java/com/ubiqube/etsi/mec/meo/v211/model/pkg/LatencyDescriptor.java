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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * LatencyDescriptor
 */
@Validated
public class LatencyDescriptor   {
  @JsonProperty("maxLatency")
  private Integer maxLatency = null;

  public LatencyDescriptor maxLatency(Integer maxLatency) {
    this.maxLatency = maxLatency;
    return this;
  }

  /**
   * The value of the maximum latency in nano seconds tolerated by the MEC application. See note.
   * @return maxLatency
  **/
  @ApiModelProperty(required = true, value = "The value of the maximum latency in nano seconds tolerated by the MEC application. See note.")
      @NotNull

    public Integer getMaxLatency() {
    return maxLatency;
  }

  public void setMaxLatency(Integer maxLatency) {
    this.maxLatency = maxLatency;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LatencyDescriptor latencyDescriptor = (LatencyDescriptor) o;
    return Objects.equals(this.maxLatency, latencyDescriptor.maxLatency);
  }

  @Override
  public int hashCode() {
    return Objects.hash(maxLatency);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LatencyDescriptor {\n");
    
    sb.append("    maxLatency: ").append(toIndentedString(maxLatency)).append("\n");
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
