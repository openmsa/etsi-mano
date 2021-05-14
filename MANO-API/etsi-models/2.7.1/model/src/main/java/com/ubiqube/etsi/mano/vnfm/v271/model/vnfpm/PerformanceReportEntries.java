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
package com.ubiqube.etsi.mano.vnfm.v271.model.vnfpm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.vnfm.v271.model.vnfpm.PerformanceReportPerformanceValues;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * PerformanceReportEntries
 */
@Validated

public class PerformanceReportEntries   {
  @JsonProperty("objectType")
  private String objectType = null;

  @JsonProperty("objectInstanceId")
  private String objectInstanceId = null;

  @JsonProperty("subObjectInstanceId")
  private String subObjectInstanceId = null;

  @JsonProperty("performanceMetric")
  private String performanceMetric = null;

  @JsonProperty("performanceValues")
  @Valid
  private List<PerformanceReportPerformanceValues> performanceValues = null;

  public PerformanceReportEntries objectType(String objectType) {
    this.objectType = objectType;
    return this;
  }

  /**
   * Type of the measured object. The applicable measured object type for a measurement is defined in clause 7.2 of ETSI GS NFV-IFA 027. 
   * @return objectType
  **/
  @ApiModelProperty(required = true, value = "Type of the measured object. The applicable measured object type for a measurement is defined in clause 7.2 of ETSI GS NFV-IFA 027. ")
  @NotNull


  public String getObjectType() {
    return objectType;
  }

  public void setObjectType(String objectType) {
    this.objectType = objectType;
  }

  public PerformanceReportEntries objectInstanceId(String objectInstanceId) {
    this.objectInstanceId = objectInstanceId;
    return this;
  }

  /**
   * Identifier of the measured object instance for which the performance metric is reported 
   * @return objectInstanceId
  **/
  @ApiModelProperty(required = true, value = "Identifier of the measured object instance for which the performance metric is reported ")
  @NotNull


  public String getObjectInstanceId() {
    return objectInstanceId;
  }

  public void setObjectInstanceId(String objectInstanceId) {
    this.objectInstanceId = objectInstanceId;
  }

  public PerformanceReportEntries subObjectInstanceId(String subObjectInstanceId) {
    this.subObjectInstanceId = subObjectInstanceId;
    return this;
  }

  /**
   * Identifier of the sub-object instance of the measured object (i.e. of the measured object instance) for which the performance metric is reported. Shall be present if this is required in clause 6.2 of ETSI GS NFV-IFA 027 for the related measured object type. The sub-object allows to structure the measured object but is not to be confused with sub-counters which allow to structure the measurement value. EXAMPLE:   Measured object:  VnfInstanceXYZ   Sub-object:       VnfcInstance1   Measurement:      vCPU_utilization   Sub-counters:     vCPU utilization of each of the vCPUs of VnfcInstance1                     (vCPU_utilization.vCPU1, vCPU_utilization.vCPU2, etc.). 
   * @return subObjectInstanceId
  **/
  @ApiModelProperty(value = "Identifier of the sub-object instance of the measured object (i.e. of the measured object instance) for which the performance metric is reported. Shall be present if this is required in clause 6.2 of ETSI GS NFV-IFA 027 for the related measured object type. The sub-object allows to structure the measured object but is not to be confused with sub-counters which allow to structure the measurement value. EXAMPLE:   Measured object:  VnfInstanceXYZ   Sub-object:       VnfcInstance1   Measurement:      vCPU_utilization   Sub-counters:     vCPU utilization of each of the vCPUs of VnfcInstance1                     (vCPU_utilization.vCPU1, vCPU_utilization.vCPU2, etc.). ")


  public String getSubObjectInstanceId() {
    return subObjectInstanceId;
  }

  public void setSubObjectInstanceId(String subObjectInstanceId) {
    this.subObjectInstanceId = subObjectInstanceId;
  }

  public PerformanceReportEntries performanceMetric(String performanceMetric) {
    this.performanceMetric = performanceMetric;
    return this;
  }

  /**
   * Name of the metric collected. This attribute shall contain the related \"Measurement Name\" value as defined in clause 7.2 of ETSI GS NFV-IFA 027. 
   * @return performanceMetric
  **/
  @ApiModelProperty(required = true, value = "Name of the metric collected. This attribute shall contain the related \"Measurement Name\" value as defined in clause 7.2 of ETSI GS NFV-IFA 027. ")
  @NotNull


  public String getPerformanceMetric() {
    return performanceMetric;
  }

  public void setPerformanceMetric(String performanceMetric) {
    this.performanceMetric = performanceMetric;
  }

  public PerformanceReportEntries performanceValues(List<PerformanceReportPerformanceValues> performanceValues) {
    this.performanceValues = performanceValues;
    return this;
  }

  public PerformanceReportEntries addPerformanceValuesItem(PerformanceReportPerformanceValues performanceValuesItem) {
    if (this.performanceValues == null) {
      this.performanceValues = new ArrayList<>();
    }
    this.performanceValues.add(performanceValuesItem);
    return this;
  }

  /**
   * List of performance values with associated timestamp. 
   * @return performanceValues
  **/
  @ApiModelProperty(value = "List of performance values with associated timestamp. ")

  @Valid

  public List<PerformanceReportPerformanceValues> getPerformanceValues() {
    return performanceValues;
  }

  public void setPerformanceValues(List<PerformanceReportPerformanceValues> performanceValues) {
    this.performanceValues = performanceValues;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PerformanceReportEntries performanceReportEntries = (PerformanceReportEntries) o;
    return Objects.equals(this.objectType, performanceReportEntries.objectType) &&
        Objects.equals(this.objectInstanceId, performanceReportEntries.objectInstanceId) &&
        Objects.equals(this.subObjectInstanceId, performanceReportEntries.subObjectInstanceId) &&
        Objects.equals(this.performanceMetric, performanceReportEntries.performanceMetric) &&
        Objects.equals(this.performanceValues, performanceReportEntries.performanceValues);
  }

  @Override
  public int hashCode() {
    return Objects.hash(objectType, objectInstanceId, subObjectInstanceId, performanceMetric, performanceValues);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PerformanceReportEntries {\n");
    
    sb.append("    objectType: ").append(toIndentedString(objectType)).append("\n");
    sb.append("    objectInstanceId: ").append(toIndentedString(objectInstanceId)).append("\n");
    sb.append("    subObjectInstanceId: ").append(toIndentedString(subObjectInstanceId)).append("\n");
    sb.append("    performanceMetric: ").append(toIndentedString(performanceMetric)).append("\n");
    sb.append("    performanceValues: ").append(toIndentedString(performanceValues)).append("\n");
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

