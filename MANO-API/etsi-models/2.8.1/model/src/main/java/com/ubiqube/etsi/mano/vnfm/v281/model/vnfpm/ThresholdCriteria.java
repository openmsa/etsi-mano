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
package com.ubiqube.etsi.mano.vnfm.v281.model.vnfpm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.vnfm.v281.model.vnfpm.ThresholdCriteriaSimpleThresholdDetails;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents criteria that define a threshold. 
 */
@ApiModel(description = "This type represents criteria that define a threshold. ")
@Validated

public class ThresholdCriteria   {
  @JsonProperty("performanceMetric")
  private String performanceMetric = null;

  /**
   * Type of threshold. This attribute determines which other attributes are present in the data structure. Permitted values: * SIMPLE: Single-valued static threshold In the present document, simple thresholds are defined. The definition of additional threshold types is left for future specification. 
   */
  public enum ThresholdTypeEnum {
    SIMPLE("SIMPLE");

    private String value;

    ThresholdTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static ThresholdTypeEnum fromValue(String text) {
      for (ThresholdTypeEnum b : ThresholdTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("thresholdType")
  private ThresholdTypeEnum thresholdType = null;

  @JsonProperty("simpleThresholdDetails")
  private ThresholdCriteriaSimpleThresholdDetails simpleThresholdDetails = null;

  public ThresholdCriteria performanceMetric(String performanceMetric) {
    this.performanceMetric = performanceMetric;
    return this;
  }

  /**
   * Defines the performance metric associated with the threshold. Valid values are specified as \"Measurement Name\" values in clause 7.2 of ETSI GS NFV-IFA 027. 
   * @return performanceMetric
  **/
  @ApiModelProperty(required = true, value = "Defines the performance metric associated with the threshold. Valid values are specified as \"Measurement Name\" values in clause 7.2 of ETSI GS NFV-IFA 027. ")
  @NotNull


  public String getPerformanceMetric() {
    return performanceMetric;
  }

  public void setPerformanceMetric(String performanceMetric) {
    this.performanceMetric = performanceMetric;
  }

  public ThresholdCriteria thresholdType(ThresholdTypeEnum thresholdType) {
    this.thresholdType = thresholdType;
    return this;
  }

  /**
   * Type of threshold. This attribute determines which other attributes are present in the data structure. Permitted values: * SIMPLE: Single-valued static threshold In the present document, simple thresholds are defined. The definition of additional threshold types is left for future specification. 
   * @return thresholdType
  **/
  @ApiModelProperty(required = true, value = "Type of threshold. This attribute determines which other attributes are present in the data structure. Permitted values: * SIMPLE: Single-valued static threshold In the present document, simple thresholds are defined. The definition of additional threshold types is left for future specification. ")
  @NotNull


  public ThresholdTypeEnum getThresholdType() {
    return thresholdType;
  }

  public void setThresholdType(ThresholdTypeEnum thresholdType) {
    this.thresholdType = thresholdType;
  }

  public ThresholdCriteria simpleThresholdDetails(ThresholdCriteriaSimpleThresholdDetails simpleThresholdDetails) {
    this.simpleThresholdDetails = simpleThresholdDetails;
    return this;
  }

  /**
   * Get simpleThresholdDetails
   * @return simpleThresholdDetails
  **/
  @ApiModelProperty(value = "")

  @Valid

  public ThresholdCriteriaSimpleThresholdDetails getSimpleThresholdDetails() {
    return simpleThresholdDetails;
  }

  public void setSimpleThresholdDetails(ThresholdCriteriaSimpleThresholdDetails simpleThresholdDetails) {
    this.simpleThresholdDetails = simpleThresholdDetails;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ThresholdCriteria thresholdCriteria = (ThresholdCriteria) o;
    return Objects.equals(this.performanceMetric, thresholdCriteria.performanceMetric) &&
        Objects.equals(this.thresholdType, thresholdCriteria.thresholdType) &&
        Objects.equals(this.simpleThresholdDetails, thresholdCriteria.simpleThresholdDetails);
  }

  @Override
  public int hashCode() {
    return Objects.hash(performanceMetric, thresholdType, simpleThresholdDetails);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ThresholdCriteria {\n");
    
    sb.append("    performanceMetric: ").append(toIndentedString(performanceMetric)).append("\n");
    sb.append("    thresholdType: ").append(toIndentedString(thresholdType)).append("\n");
    sb.append("    simpleThresholdDetails: ").append(toIndentedString(simpleThresholdDetails)).append("\n");
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

