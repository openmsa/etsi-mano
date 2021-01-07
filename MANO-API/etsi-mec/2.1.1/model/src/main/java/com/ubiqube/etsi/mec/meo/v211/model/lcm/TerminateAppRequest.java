package com.ubiqube.etsi.mec.meo.v211.model.lcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.TerminationType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * TerminateAppRequest
 */
@Validated
public class TerminateAppRequest   {
  @JsonProperty("gracefulTerminationTimeout")
  private Integer gracefulTerminationTimeout = null;

  @JsonProperty("terminationType")
  private TerminationType terminationType = null;

  public TerminateAppRequest gracefulTerminationTimeout(Integer gracefulTerminationTimeout) {
    this.gracefulTerminationTimeout = gracefulTerminationTimeout;
    return this;
  }

  /**
   * This attribute is only applicable in case of graceful termination. It defines the time to wait for the application instance to be taken out of service before shutting down the application and releasing the resources.  The unit is seconds. If not given and the \"terminationType\" attribute is set to \"GRACEFUL\", it is expected to wait for the successful taking out of service of the application, no matter how long it takes, before shutting down the application and releasing the resources.
   * @return gracefulTerminationTimeout
  **/
  @ApiModelProperty(value = "This attribute is only applicable in case of graceful termination. It defines the time to wait for the application instance to be taken out of service before shutting down the application and releasing the resources.  The unit is seconds. If not given and the \"terminationType\" attribute is set to \"GRACEFUL\", it is expected to wait for the successful taking out of service of the application, no matter how long it takes, before shutting down the application and releasing the resources.")
  
    public Integer getGracefulTerminationTimeout() {
    return gracefulTerminationTimeout;
  }

  public void setGracefulTerminationTimeout(Integer gracefulTerminationTimeout) {
    this.gracefulTerminationTimeout = gracefulTerminationTimeout;
  }

  public TerminateAppRequest terminationType(TerminationType terminationType) {
    this.terminationType = terminationType;
    return this;
  }

  /**
   * Get terminationType
   * @return terminationType
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public TerminationType getTerminationType() {
    return terminationType;
  }

  public void setTerminationType(TerminationType terminationType) {
    this.terminationType = terminationType;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TerminateAppRequest terminateAppRequest = (TerminateAppRequest) o;
    return Objects.equals(this.gracefulTerminationTimeout, terminateAppRequest.gracefulTerminationTimeout) &&
        Objects.equals(this.terminationType, terminateAppRequest.terminationType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(gracefulTerminationTimeout, terminationType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TerminateAppRequest {\n");
    
    sb.append("    gracefulTerminationTimeout: ").append(toIndentedString(gracefulTerminationTimeout)).append("\n");
    sb.append("    terminationType: ").append(toIndentedString(terminationType)).append("\n");
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
