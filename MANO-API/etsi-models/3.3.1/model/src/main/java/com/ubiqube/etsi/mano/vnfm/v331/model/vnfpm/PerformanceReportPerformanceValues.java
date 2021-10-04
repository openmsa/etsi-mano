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
package com.ubiqube.etsi.mano.vnfm.v331.model.vnfpm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Map;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * PerformanceReportPerformanceValues
 */
@Validated


public class PerformanceReportPerformanceValues   {
  @JsonProperty("timeStamp")
  private OffsetDateTime timeStamp = null;

  @JsonProperty("value")
  private Object value = null;

  @JsonProperty("context")
  private Map<String, String> context = null;

  public PerformanceReportPerformanceValues timeStamp(OffsetDateTime timeStamp) {
    this.timeStamp = timeStamp;
    return this;
  }

  /**
   * Get timeStamp
   * @return timeStamp
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public OffsetDateTime getTimeStamp() {
    return timeStamp;
  }

  public void setTimeStamp(OffsetDateTime timeStamp) {
    this.timeStamp = timeStamp;
  }

  public PerformanceReportPerformanceValues value(Object value) {
    this.value = value;
    return this;
  }

  /**
   * Value of the metric collected. The type of this attribute shall correspond to the related \"Measurement Unit\" as defined in clause 7.2. of ETSI GS NFV-IFA 027. 
   * @return value
   **/
  @Schema(required = true, description = "Value of the metric collected. The type of this attribute shall correspond to the related \"Measurement Unit\" as defined in clause 7.2. of ETSI GS NFV-IFA 027. ")
      @NotNull

    public Object getValue() {
    return value;
  }

  public void setValue(Object value) {
    this.value = value;
  }

  public PerformanceReportPerformanceValues context(Map<String, String> context) {
    this.context = context;
    return this;
  }

  /**
   * Get context
   * @return context
   **/
  @Schema(description = "")
  
    @Valid
    public Map<String, String> getContext() {
    return context;
  }

  public void setContext(Map<String, String> context) {
    this.context = context;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PerformanceReportPerformanceValues performanceReportPerformanceValues = (PerformanceReportPerformanceValues) o;
    return Objects.equals(this.timeStamp, performanceReportPerformanceValues.timeStamp) &&
        Objects.equals(this.value, performanceReportPerformanceValues.value) &&
        Objects.equals(this.context, performanceReportPerformanceValues.context);
  }

  @Override
  public int hashCode() {
    return Objects.hash(timeStamp, value, context);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PerformanceReportPerformanceValues {\n");
    
    sb.append("    timeStamp: ").append(toIndentedString(timeStamp)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
    sb.append("    context: ").append(toIndentedString(context)).append("\n");
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
