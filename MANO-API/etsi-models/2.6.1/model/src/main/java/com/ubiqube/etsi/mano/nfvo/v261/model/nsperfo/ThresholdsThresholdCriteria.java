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

package com.ubiqube.etsi.mano.nfvo.v261.model.nsperfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
  * This type represents criteria that define a threshold. 
 **/
@Schema(description="This type represents criteria that define a threshold. ")
public class ThresholdsThresholdCriteria  {
  
  @Schema(required = true, description = "Defines the performance metric associated with the threshold, as specified in ETSI GS NFV-IFA 027). ")
 /**
   * Defines the performance metric associated with the threshold, as specified in ETSI GS NFV-IFA 027). 
  **/
  private String performanceMetric = null;


@XmlType(name="ThresholdTypeEnum")
@XmlEnum(String.class)
public enum ThresholdTypeEnum {

@XmlEnumValue("SIMPLE") SIMPLE(String.valueOf("SIMPLE"));


    private String value;

    ThresholdTypeEnum (String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static ThresholdTypeEnum fromValue(String v) {
        for (ThresholdTypeEnum b : ThresholdTypeEnum.values()) {
            if (String.valueOf(b.value).equals(v)) {
                return b;
            }
        }
        return null;
    }
}

  @Schema(required = true, description = "Type of threshold. This attribute determines which other attributes are present in the data structure. Permitted values: * SIMPLE: Single-valued static threshold In the present document, simple thresholds are defined. The definition of additional threshold types is left for future specification. ")
 /**
   * Type of threshold. This attribute determines which other attributes are present in the data structure. Permitted values: * SIMPLE: Single-valued static threshold In the present document, simple thresholds are defined. The definition of additional threshold types is left for future specification. 
  **/
  private ThresholdTypeEnum thresholdType = null;

  @Schema(description = "")
  @Valid
  private ThresholdsThresholdCriteriaSimpleThresholdDetails simpleThresholdDetails = null;
 /**
   * Defines the performance metric associated with the threshold, as specified in ETSI GS NFV-IFA 027). 
   * @return performanceMetric
  **/
  @JsonProperty("performanceMetric")
  @NotNull
  public String getPerformanceMetric() {
    return performanceMetric;
  }

  public void setPerformanceMetric(String performanceMetric) {
    this.performanceMetric = performanceMetric;
  }

  public ThresholdsThresholdCriteria performanceMetric(String performanceMetric) {
    this.performanceMetric = performanceMetric;
    return this;
  }

 /**
   * Type of threshold. This attribute determines which other attributes are present in the data structure. Permitted values: * SIMPLE: Single-valued static threshold In the present document, simple thresholds are defined. The definition of additional threshold types is left for future specification. 
   * @return thresholdType
  **/
  @JsonProperty("thresholdType")
  @NotNull
  public String getThresholdType() {
    if (thresholdType == null) {
      return null;
    }
    return thresholdType.value();
  }

  public void setThresholdType(ThresholdTypeEnum thresholdType) {
    this.thresholdType = thresholdType;
  }

  public ThresholdsThresholdCriteria thresholdType(ThresholdTypeEnum thresholdType) {
    this.thresholdType = thresholdType;
    return this;
  }

 /**
   * Get simpleThresholdDetails
   * @return simpleThresholdDetails
  **/
  @JsonProperty("simpleThresholdDetails")
  public ThresholdsThresholdCriteriaSimpleThresholdDetails getSimpleThresholdDetails() {
    return simpleThresholdDetails;
  }

  public void setSimpleThresholdDetails(ThresholdsThresholdCriteriaSimpleThresholdDetails simpleThresholdDetails) {
    this.simpleThresholdDetails = simpleThresholdDetails;
  }

  public ThresholdsThresholdCriteria simpleThresholdDetails(ThresholdsThresholdCriteriaSimpleThresholdDetails simpleThresholdDetails) {
    this.simpleThresholdDetails = simpleThresholdDetails;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ThresholdsThresholdCriteria {\n");
    
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
  private static String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

