package com.ubiqube.etsi.mec.meo.v211.model.lcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.ChangeStateTo;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.StopType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * OperateAppRequest
 */
@Validated
public class OperateAppRequest   {
  @JsonProperty("changeStateTo")
  private ChangeStateTo changeStateTo = null;

  @JsonProperty("gracefulStopTimeout")
  private Integer gracefulStopTimeout = null;

  @JsonProperty("stopType")
  private StopType stopType = null;

  public OperateAppRequest changeStateTo(ChangeStateTo changeStateTo) {
    this.changeStateTo = changeStateTo;
    return this;
  }

  /**
   * Get changeStateTo
   * @return changeStateTo
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public ChangeStateTo getChangeStateTo() {
    return changeStateTo;
  }

  public void setChangeStateTo(ChangeStateTo changeStateTo) {
    this.changeStateTo = changeStateTo;
  }

  public OperateAppRequest gracefulStopTimeout(Integer gracefulStopTimeout) {
    this.gracefulStopTimeout = gracefulStopTimeout;
    return this;
  }

  /**
   * The time interval (in seconds) to wait for the application instance to be taken out of service during graceful stop, before stopping the application. See note 1 and note 2.
   * @return gracefulStopTimeout
  **/
  @ApiModelProperty(value = "The time interval (in seconds) to wait for the application instance to be taken out of service during graceful stop, before stopping the application. See note 1 and note 2.")
  
    public Integer getGracefulStopTimeout() {
    return gracefulStopTimeout;
  }

  public void setGracefulStopTimeout(Integer gracefulStopTimeout) {
    this.gracefulStopTimeout = gracefulStopTimeout;
  }

  public OperateAppRequest stopType(StopType stopType) {
    this.stopType = stopType;
    return this;
  }

  /**
   * Get stopType
   * @return stopType
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public StopType getStopType() {
    return stopType;
  }

  public void setStopType(StopType stopType) {
    this.stopType = stopType;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OperateAppRequest operateAppRequest = (OperateAppRequest) o;
    return Objects.equals(this.changeStateTo, operateAppRequest.changeStateTo) &&
        Objects.equals(this.gracefulStopTimeout, operateAppRequest.gracefulStopTimeout) &&
        Objects.equals(this.stopType, operateAppRequest.stopType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(changeStateTo, gracefulStopTimeout, stopType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OperateAppRequest {\n");
    
    sb.append("    changeStateTo: ").append(toIndentedString(changeStateTo)).append("\n");
    sb.append("    gracefulStopTimeout: ").append(toIndentedString(gracefulStopTimeout)).append("\n");
    sb.append("    stopType: ").append(toIndentedString(stopType)).append("\n");
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
