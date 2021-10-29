package com.ubiqube.etsi.mano.nfvo.v351.model.nfvici;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.nfvo.v351.model.nfvici.CapacityThresholdCriteriaCapacityMetric;
import com.ubiqube.etsi.mano.nfvo.v351.model.nfvici.CapacityThresholdCriteriaSimpleThresholdDetails;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents criteria that define a capacity threshold. It shall comply with the provisions defined in table 10.5.3.3-1. NOTE 1: In the present document, simple thresholds are defined. The definition of additional threshold types is left for future specification. NOTE 2: The hysteresis is defined to prevent storms of threshold crossing notifications. When processing a request to create a threshold, implementations should enforce a suitable minimum value for this attribute (e.g. override the value or reject the request). 
 */
@Schema(description = "This type represents criteria that define a capacity threshold. It shall comply with the provisions defined in table 10.5.3.3-1. NOTE 1: In the present document, simple thresholds are defined. The definition of additional threshold types is left for future specification. NOTE 2: The hysteresis is defined to prevent storms of threshold crossing notifications. When processing a request to create a threshold, implementations should enforce a suitable minimum value for this attribute (e.g. override the value or reject the request). ")
@Validated


public class CapacityThresholdCriteria   {
  @JsonProperty("capacityMetric")
  private CapacityThresholdCriteriaCapacityMetric capacityMetric = null;

  /**
   * Type of capacity threshold. This attribute determines which other attributes are present in the data structure. Permitted values: - SIMPLE: Single-valued static threshold. See note 1. 
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
  @Valid
  private List<CapacityThresholdCriteriaSimpleThresholdDetails> simpleThresholdDetails = null;

  public CapacityThresholdCriteria capacityMetric(CapacityThresholdCriteriaCapacityMetric capacityMetric) {
    this.capacityMetric = capacityMetric;
    return this;
  }

  /**
   * Get capacityMetric
   * @return capacityMetric
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public CapacityThresholdCriteriaCapacityMetric getCapacityMetric() {
    return capacityMetric;
  }

  public void setCapacityMetric(CapacityThresholdCriteriaCapacityMetric capacityMetric) {
    this.capacityMetric = capacityMetric;
  }

  public CapacityThresholdCriteria thresholdType(ThresholdTypeEnum thresholdType) {
    this.thresholdType = thresholdType;
    return this;
  }

  /**
   * Type of capacity threshold. This attribute determines which other attributes are present in the data structure. Permitted values: - SIMPLE: Single-valued static threshold. See note 1. 
   * @return thresholdType
   **/
  @Schema(required = true, description = "Type of capacity threshold. This attribute determines which other attributes are present in the data structure. Permitted values: - SIMPLE: Single-valued static threshold. See note 1. ")
      @NotNull

    public ThresholdTypeEnum getThresholdType() {
    return thresholdType;
  }

  public void setThresholdType(ThresholdTypeEnum thresholdType) {
    this.thresholdType = thresholdType;
  }

  public CapacityThresholdCriteria simpleThresholdDetails(List<CapacityThresholdCriteriaSimpleThresholdDetails> simpleThresholdDetails) {
    this.simpleThresholdDetails = simpleThresholdDetails;
    return this;
  }

  public CapacityThresholdCriteria addSimpleThresholdDetailsItem(CapacityThresholdCriteriaSimpleThresholdDetails simpleThresholdDetailsItem) {
    if (this.simpleThresholdDetails == null) {
      this.simpleThresholdDetails = new ArrayList<>();
    }
    this.simpleThresholdDetails.add(simpleThresholdDetailsItem);
    return this;
  }

  /**
   * Details of a simple threshold. Shall be present if thresholdType=\"SIMPLE\". 
   * @return simpleThresholdDetails
   **/
  @Schema(description = "Details of a simple threshold. Shall be present if thresholdType=\"SIMPLE\". ")
      @Valid
    public List<CapacityThresholdCriteriaSimpleThresholdDetails> getSimpleThresholdDetails() {
    return simpleThresholdDetails;
  }

  public void setSimpleThresholdDetails(List<CapacityThresholdCriteriaSimpleThresholdDetails> simpleThresholdDetails) {
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
    CapacityThresholdCriteria capacityThresholdCriteria = (CapacityThresholdCriteria) o;
    return Objects.equals(this.capacityMetric, capacityThresholdCriteria.capacityMetric) &&
        Objects.equals(this.thresholdType, capacityThresholdCriteria.thresholdType) &&
        Objects.equals(this.simpleThresholdDetails, capacityThresholdCriteria.simpleThresholdDetails);
  }

  @Override
  public int hashCode() {
    return Objects.hash(capacityMetric, thresholdType, simpleThresholdDetails);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CapacityThresholdCriteria {\n");
    
    sb.append("    capacityMetric: ").append(toIndentedString(capacityMetric)).append("\n");
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
