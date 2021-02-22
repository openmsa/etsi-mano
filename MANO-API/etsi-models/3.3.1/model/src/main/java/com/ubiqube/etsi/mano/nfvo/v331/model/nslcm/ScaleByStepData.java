package com.ubiqube.etsi.mano.nfvo.v331.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.KeyValuePairs;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type describes the information to scale a VNF instance by steps.  The NFVO shall then invoke the Scale VNF operation towards the appropriate VNFM. 
 */
@Schema(description = "This type describes the information to scale a VNF instance by steps.  The NFVO shall then invoke the Scale VNF operation towards the appropriate VNFM. ")
@Validated


public class ScaleByStepData   {
  @JsonProperty("aspectId")
  private String aspectId = null;

  @JsonProperty("numberOfSteps")
  private Integer numberOfSteps = 1;

  @JsonProperty("additionalParams")
  private KeyValuePairs additionalParams = null;

  public ScaleByStepData aspectId(String aspectId) {
    this.aspectId = aspectId;
    return this;
  }

  /**
   * Get aspectId
   * @return aspectId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getAspectId() {
    return aspectId;
  }

  public void setAspectId(String aspectId) {
    this.aspectId = aspectId;
  }

  public ScaleByStepData numberOfSteps(Integer numberOfSteps) {
    this.numberOfSteps = numberOfSteps;
    return this;
  }

  /**
   * Number of scaling steps. It shall be a positive number. Defaults to 1. The VNF provider defines in the VNFD whether or not a particular VNF supports performing more than one step at a time. Such a property in the VNFD applies for all instances of a particular VNF. 
   * @return numberOfSteps
   **/
  @Schema(description = "Number of scaling steps. It shall be a positive number. Defaults to 1. The VNF provider defines in the VNFD whether or not a particular VNF supports performing more than one step at a time. Such a property in the VNFD applies for all instances of a particular VNF. ")
  
    public Integer getNumberOfSteps() {
    return numberOfSteps;
  }

  public void setNumberOfSteps(Integer numberOfSteps) {
    this.numberOfSteps = numberOfSteps;
  }

  public ScaleByStepData additionalParams(KeyValuePairs additionalParams) {
    this.additionalParams = additionalParams;
    return this;
  }

  /**
   * Get additionalParams
   * @return additionalParams
   **/
  @Schema(description = "")
  
    @Valid
    public KeyValuePairs getAdditionalParams() {
    return additionalParams;
  }

  public void setAdditionalParams(KeyValuePairs additionalParams) {
    this.additionalParams = additionalParams;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ScaleByStepData scaleByStepData = (ScaleByStepData) o;
    return Objects.equals(this.aspectId, scaleByStepData.aspectId) &&
        Objects.equals(this.numberOfSteps, scaleByStepData.numberOfSteps) &&
        Objects.equals(this.additionalParams, scaleByStepData.additionalParams);
  }

  @Override
  public int hashCode() {
    return Objects.hash(aspectId, numberOfSteps, additionalParams);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ScaleByStepData {\n");
    
    sb.append("    aspectId: ").append(toIndentedString(aspectId)).append("\n");
    sb.append("    numberOfSteps: ").append(toIndentedString(numberOfSteps)).append("\n");
    sb.append("    additionalParams: ").append(toIndentedString(additionalParams)).append("\n");
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
