package com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim.ChangeOperationalStateEnumType;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim.StopEnumType;
import com.fasterxml.jackson.annotation.JsonCreator;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * A change of operational state. Shall be present if the state change request refers to the operational state.  NOTE: In the present document version, a request shall only include an  operational state change (attribute \&quot;operationalStateChange\&quot;) or an  administrative state change request (attribute \&quot;administrativeStateChange\&quot;),  but not both. 
 */
@ApiModel(description = "A change of operational state. Shall be present if the state change request refers to the operational state.  NOTE: In the present document version, a request shall only include an  operational state change (attribute \"operationalStateChange\") or an  administrative state change request (attribute \"administrativeStateChange\"),  but not both. ")
@Validated
public class ChangeStateRequestOperationalStateChange   {
  @JsonProperty("operationalStateAction")
  private ChangeOperationalStateEnumType operationalStateAction = null;

  @JsonProperty("stopType")
  private StopEnumType stopType = null;

  @JsonProperty("gracefulStopTimeout")
  private Integer gracefulStopTimeout = null;

  public ChangeStateRequestOperationalStateChange operationalStateAction(ChangeOperationalStateEnumType operationalStateAction) {
    this.operationalStateAction = operationalStateAction;
    return this;
  }

  /**
   * Get operationalStateAction
   * @return operationalStateAction
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public ChangeOperationalStateEnumType getOperationalStateAction() {
    return operationalStateAction;
  }

  public void setOperationalStateAction(ChangeOperationalStateEnumType operationalStateAction) {
    this.operationalStateAction = operationalStateAction;
  }

  public ChangeStateRequestOperationalStateChange stopType(StopEnumType stopType) {
    this.stopType = stopType;
    return this;
  }

  /**
   * Get stopType
   * @return stopType
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public StopEnumType getStopType() {
    return stopType;
  }

  public void setStopType(StopEnumType stopType) {
    this.stopType = stopType;
  }

  public ChangeStateRequestOperationalStateChange gracefulStopTimeout(Integer gracefulStopTimeout) {
    this.gracefulStopTimeout = gracefulStopTimeout;
    return this;
  }

  /**
   * The time internal (in seconds) to wait for the entity to be taken out  of service during graceful stop.  NOTE: The \"stopType\" shall only be provided when the \"operationalStateAction\"  attribute is equal to \"STOP\" or \"RESTART\".  The \"gracefulStopTimeout\" shall  be absent when the \"stopType\" attribute is equal to \"FORCEFUL\", and may  be provided otherwise. 
   * @return gracefulStopTimeout
  **/
  @ApiModelProperty(value = "The time internal (in seconds) to wait for the entity to be taken out  of service during graceful stop.  NOTE: The \"stopType\" shall only be provided when the \"operationalStateAction\"  attribute is equal to \"STOP\" or \"RESTART\".  The \"gracefulStopTimeout\" shall  be absent when the \"stopType\" attribute is equal to \"FORCEFUL\", and may  be provided otherwise. ")
  
    public Integer getGracefulStopTimeout() {
    return gracefulStopTimeout;
  }

  public void setGracefulStopTimeout(Integer gracefulStopTimeout) {
    this.gracefulStopTimeout = gracefulStopTimeout;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ChangeStateRequestOperationalStateChange changeStateRequestOperationalStateChange = (ChangeStateRequestOperationalStateChange) o;
    return Objects.equals(this.operationalStateAction, changeStateRequestOperationalStateChange.operationalStateAction) &&
        Objects.equals(this.stopType, changeStateRequestOperationalStateChange.stopType) &&
        Objects.equals(this.gracefulStopTimeout, changeStateRequestOperationalStateChange.gracefulStopTimeout);
  }

  @Override
  public int hashCode() {
    return Objects.hash(operationalStateAction, stopType, gracefulStopTimeout);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ChangeStateRequestOperationalStateChange {\n");
    
    sb.append("    operationalStateAction: ").append(toIndentedString(operationalStateAction)).append("\n");
    sb.append("    stopType: ").append(toIndentedString(stopType)).append("\n");
    sb.append("    gracefulStopTimeout: ").append(toIndentedString(gracefulStopTimeout)).append("\n");
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
