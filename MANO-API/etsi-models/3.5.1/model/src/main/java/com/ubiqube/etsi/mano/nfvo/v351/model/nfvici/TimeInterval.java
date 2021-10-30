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
package com.ubiqube.etsi.mano.nfvo.v351.model.nfvici;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type defines the format of a time interval. The type shall comply with the provisions defined in table 10.5.2.7-1. NOTE: When only the startTime is present, there is no time interval being defined, and therefore the provided timing information refers to a specific point in time. 
 */
@Schema(description = "This type defines the format of a time interval. The type shall comply with the provisions defined in table 10.5.2.7-1. NOTE: When only the startTime is present, there is no time interval being defined, and therefore the provided timing information refers to a specific point in time. ")
@Validated


public class TimeInterval   {
  @JsonProperty("aTime")
  private OffsetDateTime aTime = null;

  @JsonProperty("bTime")
  private OffsetDateTime bTime = null;

  public TimeInterval aTime(OffsetDateTime aTime) {
    this.aTime = aTime;
    return this;
  }

  /**
   * Get aTime
   * @return aTime
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public OffsetDateTime getATime() {
    return aTime;
  }

  public void setATime(OffsetDateTime aTime) {
    this.aTime = aTime;
  }

  public TimeInterval bTime(OffsetDateTime bTime) {
    this.bTime = bTime;
    return this;
  }

  /**
   * Get bTime
   * @return bTime
   **/
  @Schema(description = "")
  
    @Valid
    public OffsetDateTime getBTime() {
    return bTime;
  }

  public void setBTime(OffsetDateTime bTime) {
    this.bTime = bTime;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TimeInterval timeInterval = (TimeInterval) o;
    return Objects.equals(this.aTime, timeInterval.aTime) &&
        Objects.equals(this.bTime, timeInterval.bTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(aTime, bTime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TimeInterval {\n");
    
    sb.append("    aTime: ").append(toIndentedString(aTime)).append("\n");
    sb.append("    bTime: ").append(toIndentedString(bTime)).append("\n");
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
