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
 * TimeStamp
 */
@Validated
public class TimeStamp   {
  @JsonProperty("nanoSeconds")
  private Integer nanoSeconds = null;

  @JsonProperty("seconds")
  private Integer seconds = null;

  public TimeStamp nanoSeconds(Integer nanoSeconds) {
    this.nanoSeconds = nanoSeconds;
    return this;
  }

  /**
   * The nanoseconds part of the Time. Time is defined as Unix-time since January 1, 1970, 00:00:00 UTC.
   * @return nanoSeconds
  **/
  @ApiModelProperty(required = true, value = "The nanoseconds part of the Time. Time is defined as Unix-time since January 1, 1970, 00:00:00 UTC.")
      @NotNull

    public Integer getNanoSeconds() {
    return nanoSeconds;
  }

  public void setNanoSeconds(Integer nanoSeconds) {
    this.nanoSeconds = nanoSeconds;
  }

  public TimeStamp seconds(Integer seconds) {
    this.seconds = seconds;
    return this;
  }

  /**
   * The seconds part of the Time. Time is defined as Unix-time since January 1, 1970, 00:00:00 UTC.
   * @return seconds
  **/
  @ApiModelProperty(required = true, value = "The seconds part of the Time. Time is defined as Unix-time since January 1, 1970, 00:00:00 UTC.")
      @NotNull

    public Integer getSeconds() {
    return seconds;
  }

  public void setSeconds(Integer seconds) {
    this.seconds = seconds;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TimeStamp timeStamp = (TimeStamp) o;
    return Objects.equals(this.nanoSeconds, timeStamp.nanoSeconds) &&
        Objects.equals(this.seconds, timeStamp.seconds);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nanoSeconds, seconds);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TimeStamp {\n");
    
    sb.append("    nanoSeconds: ").append(toIndentedString(nanoSeconds)).append("\n");
    sb.append("    seconds: ").append(toIndentedString(seconds)).append("\n");
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
