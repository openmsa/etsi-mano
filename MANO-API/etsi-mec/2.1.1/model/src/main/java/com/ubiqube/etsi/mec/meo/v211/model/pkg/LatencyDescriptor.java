package com.ubiqube.etsi.mec.meo.v211.model.pkg;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * LatencyDescriptor
 */
@Validated
public class LatencyDescriptor   {
  @JsonProperty("maxLatency")
  private Integer maxLatency = null;

  public LatencyDescriptor maxLatency(Integer maxLatency) {
    this.maxLatency = maxLatency;
    return this;
  }

  /**
   * The value of the maximum latency in nano seconds tolerated by the MEC application. See note.
   * @return maxLatency
  **/
  @ApiModelProperty(required = true, value = "The value of the maximum latency in nano seconds tolerated by the MEC application. See note.")
      @NotNull

    public Integer getMaxLatency() {
    return maxLatency;
  }

  public void setMaxLatency(Integer maxLatency) {
    this.maxLatency = maxLatency;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LatencyDescriptor latencyDescriptor = (LatencyDescriptor) o;
    return Objects.equals(this.maxLatency, latencyDescriptor.maxLatency);
  }

  @Override
  public int hashCode() {
    return Objects.hash(maxLatency);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LatencyDescriptor {\n");
    
    sb.append("    maxLatency: ").append(toIndentedString(maxLatency)).append("\n");
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
