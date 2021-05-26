package com.ubiqube.etsi.mano.em.v261.model.faultmngt;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.em.v261.model.faultmngt.PerceivedSeverityType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents the escalated value of the perceived severity for an alarm. 
 */
@ApiModel(description = "This type represents the escalated value of the perceived severity for an alarm. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-12-15T08:29:53.588+01:00")

public class PerceivedSeverityRequest   {
  @JsonProperty("proposedPerceivedSeverity")
  private PerceivedSeverityType proposedPerceivedSeverity = null;

  public PerceivedSeverityRequest proposedPerceivedSeverity(PerceivedSeverityType proposedPerceivedSeverity) {
    this.proposedPerceivedSeverity = proposedPerceivedSeverity;
    return this;
  }

  /**
   * Indicates the proposed escalated perceived severity for an alarm. 
   * @return proposedPerceivedSeverity
  **/
  @ApiModelProperty(required = true, value = "Indicates the proposed escalated perceived severity for an alarm. ")
  @NotNull

  @Valid

  public PerceivedSeverityType getProposedPerceivedSeverity() {
    return proposedPerceivedSeverity;
  }

  public void setProposedPerceivedSeverity(PerceivedSeverityType proposedPerceivedSeverity) {
    this.proposedPerceivedSeverity = proposedPerceivedSeverity;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PerceivedSeverityRequest perceivedSeverityRequest = (PerceivedSeverityRequest) o;
    return Objects.equals(this.proposedPerceivedSeverity, perceivedSeverityRequest.proposedPerceivedSeverity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(proposedPerceivedSeverity);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PerceivedSeverityRequest {\n");
    
    sb.append("    proposedPerceivedSeverity: ").append(toIndentedString(proposedPerceivedSeverity)).append("\n");
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

