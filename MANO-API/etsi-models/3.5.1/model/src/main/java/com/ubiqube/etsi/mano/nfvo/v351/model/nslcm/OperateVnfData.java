package com.ubiqube.etsi.mano.nfvo.v351.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.KeyValuePairs;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.OperationalStates;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.StopType;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents a VNF instance for which the operational state  needs to be changed and the requested new state. NOTE: The \&quot;stopType\&quot; and \&quot;gracefulStopTimeout\&quot; attributes shall be absent, when the \&quot;changeStateTo\&quot; attribute is equal to \&quot;STARTED\&quot;. The \&quot;gracefulStopTimeout\&quot; attribute shall be present, when the \&quot;changeStateTo\&quot; attribute is equal to \&quot;STOPPED\&quot; and the \&quot;stopType\&quot; attribute is equal to \&quot;GRACEFUL\&quot;. The \&quot;gracefulStopTimeout\&quot; attribute shall be absent, when the \&quot;changeStateTo\&quot; attribute is equal to \&quot;STOPPED\&quot; and the \&quot;stopType\&quot; attribute is equal to \&quot;FORCEFUL\&quot;. The request shall be treated as if the \&quot;stopType\&quot; attribute was set to \&quot;FORCEFUL\&quot;, when the \&quot;changeStateTo\&quot; attribute is equal to \&quot;STOPPED\&quot; and the \&quot;stopType\&quot; attribute is absent. 
 */
@Schema(description = "This type represents a VNF instance for which the operational state  needs to be changed and the requested new state. NOTE: The \"stopType\" and \"gracefulStopTimeout\" attributes shall be absent, when the \"changeStateTo\" attribute is equal to \"STARTED\". The \"gracefulStopTimeout\" attribute shall be present, when the \"changeStateTo\" attribute is equal to \"STOPPED\" and the \"stopType\" attribute is equal to \"GRACEFUL\". The \"gracefulStopTimeout\" attribute shall be absent, when the \"changeStateTo\" attribute is equal to \"STOPPED\" and the \"stopType\" attribute is equal to \"FORCEFUL\". The request shall be treated as if the \"stopType\" attribute was set to \"FORCEFUL\", when the \"changeStateTo\" attribute is equal to \"STOPPED\" and the \"stopType\" attribute is absent. ")
@Validated


public class OperateVnfData   {
  @JsonProperty("vnfInstanceId")
  private String vnfInstanceId = null;

  @JsonProperty("changeStateTo")
  private OperationalStates changeStateTo = null;

  @JsonProperty("stopType")
  private StopType stopType = null;

  @JsonProperty("gracefulStopTimeout")
  private Integer gracefulStopTimeout = null;

  @JsonProperty("additionalParam")
  private KeyValuePairs additionalParam = null;

  public OperateVnfData vnfInstanceId(String vnfInstanceId) {
    this.vnfInstanceId = vnfInstanceId;
    return this;
  }

  /**
   * Get vnfInstanceId
   * @return vnfInstanceId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getVnfInstanceId() {
    return vnfInstanceId;
  }

  public void setVnfInstanceId(String vnfInstanceId) {
    this.vnfInstanceId = vnfInstanceId;
  }

  public OperateVnfData changeStateTo(OperationalStates changeStateTo) {
    this.changeStateTo = changeStateTo;
    return this;
  }

  /**
   * Get changeStateTo
   * @return changeStateTo
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public OperationalStates getChangeStateTo() {
    return changeStateTo;
  }

  public void setChangeStateTo(OperationalStates changeStateTo) {
    this.changeStateTo = changeStateTo;
  }

  public OperateVnfData stopType(StopType stopType) {
    this.stopType = stopType;
    return this;
  }

  /**
   * Get stopType
   * @return stopType
   **/
  @Schema(description = "")
  
    @Valid
    public StopType getStopType() {
    return stopType;
  }

  public void setStopType(StopType stopType) {
    this.stopType = stopType;
  }

  public OperateVnfData gracefulStopTimeout(Integer gracefulStopTimeout) {
    this.gracefulStopTimeout = gracefulStopTimeout;
    return this;
  }

  /**
   * The time interval (in seconds) to wait for the VNF to be taken out of service during graceful stop, before stopping the VNF. See note. 
   * @return gracefulStopTimeout
   **/
  @Schema(description = "The time interval (in seconds) to wait for the VNF to be taken out of service during graceful stop, before stopping the VNF. See note. ")
  
    public Integer getGracefulStopTimeout() {
    return gracefulStopTimeout;
  }

  public void setGracefulStopTimeout(Integer gracefulStopTimeout) {
    this.gracefulStopTimeout = gracefulStopTimeout;
  }

  public OperateVnfData additionalParam(KeyValuePairs additionalParam) {
    this.additionalParam = additionalParam;
    return this;
  }

  /**
   * Get additionalParam
   * @return additionalParam
   **/
  @Schema(description = "")
  
    @Valid
    public KeyValuePairs getAdditionalParam() {
    return additionalParam;
  }

  public void setAdditionalParam(KeyValuePairs additionalParam) {
    this.additionalParam = additionalParam;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OperateVnfData operateVnfData = (OperateVnfData) o;
    return Objects.equals(this.vnfInstanceId, operateVnfData.vnfInstanceId) &&
        Objects.equals(this.changeStateTo, operateVnfData.changeStateTo) &&
        Objects.equals(this.stopType, operateVnfData.stopType) &&
        Objects.equals(this.gracefulStopTimeout, operateVnfData.gracefulStopTimeout) &&
        Objects.equals(this.additionalParam, operateVnfData.additionalParam);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vnfInstanceId, changeStateTo, stopType, gracefulStopTimeout, additionalParam);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OperateVnfData {\n");
    
    sb.append("    vnfInstanceId: ").append(toIndentedString(vnfInstanceId)).append("\n");
    sb.append("    changeStateTo: ").append(toIndentedString(changeStateTo)).append("\n");
    sb.append("    stopType: ").append(toIndentedString(stopType)).append("\n");
    sb.append("    gracefulStopTimeout: ").append(toIndentedString(gracefulStopTimeout)).append("\n");
    sb.append("    additionalParam: ").append(toIndentedString(additionalParam)).append("\n");
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
