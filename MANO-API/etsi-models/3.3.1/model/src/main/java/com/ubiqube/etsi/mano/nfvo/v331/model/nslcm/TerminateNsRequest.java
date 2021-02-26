package com.ubiqube.etsi.mano.nfvo.v331.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents a NS termination request.  It shall comply with the provisions defined in Table 6.5.2.15-1. 
 */
@Schema(description = "This type represents a NS termination request.  It shall comply with the provisions defined in Table 6.5.2.15-1. ")
@Validated


public class TerminateNsRequest   {
  @JsonProperty("terminationTime")
  private OffsetDateTime terminationTime = null;

  public TerminateNsRequest terminationTime(OffsetDateTime terminationTime) {
    this.terminationTime = terminationTime;
    return this;
  }

  /**
   * Get terminationTime
   * @return terminationTime
   **/
  @Schema(description = "")
  
    @Valid
    public OffsetDateTime getTerminationTime() {
    return terminationTime;
  }

  public void setTerminationTime(OffsetDateTime terminationTime) {
    this.terminationTime = terminationTime;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TerminateNsRequest terminateNsRequest = (TerminateNsRequest) o;
    return Objects.equals(this.terminationTime, terminateNsRequest.terminationTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(terminationTime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TerminateNsRequest {\n");
    
    sb.append("    terminationTime: ").append(toIndentedString(terminationTime)).append("\n");
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
