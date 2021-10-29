package com.ubiqube.etsi.mano.em.v351.model.vnffm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.em.v351.model.vnffm.PerceivedSeverityType;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents the escalated value of the perceived severity for an alarm. 
 */
@Schema(description = "This type represents the escalated value of the perceived severity for an alarm. ")
@Validated


public class PerceivedSeverityRequest   {
  @JsonProperty("proposedPerceivedSeverity")
  private PerceivedSeverityType proposedPerceivedSeverity = null;

  public PerceivedSeverityRequest proposedPerceivedSeverity(PerceivedSeverityType proposedPerceivedSeverity) {
    this.proposedPerceivedSeverity = proposedPerceivedSeverity;
    return this;
  }

  /**
   * Get proposedPerceivedSeverity
   * @return proposedPerceivedSeverity
   **/
  @Schema(required = true, description = "")
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
