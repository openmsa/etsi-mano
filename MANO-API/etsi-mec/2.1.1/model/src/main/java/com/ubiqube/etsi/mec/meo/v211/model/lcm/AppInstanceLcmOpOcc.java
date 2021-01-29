/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.etsi.mec.meo.v211.model.lcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.AppInstanceLcmOpOccLinks;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.LcmOperation;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.OperationParams;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.OperationState;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.TimeStamp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * &#x27;This data type represents an application lifecycle management operation occurrence&#x27;
 */
@ApiModel(description = "'This data type represents an application lifecycle management operation occurrence'")
@Validated
public class AppInstanceLcmOpOcc   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("operationState")
  private OperationState operationState = null;

  @JsonProperty("stateEnteredTime")
  private TimeStamp stateEnteredTime = null;

  @JsonProperty("startTime")
  private TimeStamp startTime = null;

  @JsonProperty("lcmOperation")
  private LcmOperation lcmOperation = null;

  @JsonProperty("operationParams")
  private OperationParams operationParams = null;

  @JsonProperty("_links")
  private AppInstanceLcmOpOccLinks _links = null;

  public AppInstanceLcmOpOcc id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public AppInstanceLcmOpOcc operationState(OperationState operationState) {
    this.operationState = operationState;
    return this;
  }

  /**
   * Get operationState
   * @return operationState
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public OperationState getOperationState() {
    return operationState;
  }

  public void setOperationState(OperationState operationState) {
    this.operationState = operationState;
  }

  public AppInstanceLcmOpOcc stateEnteredTime(TimeStamp stateEnteredTime) {
    this.stateEnteredTime = stateEnteredTime;
    return this;
  }

  /**
   * Get stateEnteredTime
   * @return stateEnteredTime
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public TimeStamp getStateEnteredTime() {
    return stateEnteredTime;
  }

  public void setStateEnteredTime(TimeStamp stateEnteredTime) {
    this.stateEnteredTime = stateEnteredTime;
  }

  public AppInstanceLcmOpOcc startTime(TimeStamp startTime) {
    this.startTime = startTime;
    return this;
  }

  /**
   * Get startTime
   * @return startTime
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public TimeStamp getStartTime() {
    return startTime;
  }

  public void setStartTime(TimeStamp startTime) {
    this.startTime = startTime;
  }

  public AppInstanceLcmOpOcc lcmOperation(LcmOperation lcmOperation) {
    this.lcmOperation = lcmOperation;
    return this;
  }

  /**
   * Get lcmOperation
   * @return lcmOperation
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public LcmOperation getLcmOperation() {
    return lcmOperation;
  }

  public void setLcmOperation(LcmOperation lcmOperation) {
    this.lcmOperation = lcmOperation;
  }

  public AppInstanceLcmOpOcc operationParams(OperationParams operationParams) {
    this.operationParams = operationParams;
    return this;
  }

  /**
   * Get operationParams
   * @return operationParams
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public OperationParams getOperationParams() {
    return operationParams;
  }

  public void setOperationParams(OperationParams operationParams) {
    this.operationParams = operationParams;
  }

  public AppInstanceLcmOpOcc _links(AppInstanceLcmOpOccLinks _links) {
    this._links = _links;
    return this;
  }

  /**
   * Get _links
   * @return _links
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public AppInstanceLcmOpOccLinks getLinks() {
    return _links;
  }

  public void setLinks(AppInstanceLcmOpOccLinks _links) {
    this._links = _links;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AppInstanceLcmOpOcc appInstanceLcmOpOcc = (AppInstanceLcmOpOcc) o;
    return Objects.equals(this.id, appInstanceLcmOpOcc.id) &&
        Objects.equals(this.operationState, appInstanceLcmOpOcc.operationState) &&
        Objects.equals(this.stateEnteredTime, appInstanceLcmOpOcc.stateEnteredTime) &&
        Objects.equals(this.startTime, appInstanceLcmOpOcc.startTime) &&
        Objects.equals(this.lcmOperation, appInstanceLcmOpOcc.lcmOperation) &&
        Objects.equals(this.operationParams, appInstanceLcmOpOcc.operationParams) &&
        Objects.equals(this._links, appInstanceLcmOpOcc._links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, operationState, stateEnteredTime, startTime, lcmOperation, operationParams, _links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AppInstanceLcmOpOcc {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    operationState: ").append(toIndentedString(operationState)).append("\n");
    sb.append("    stateEnteredTime: ").append(toIndentedString(stateEnteredTime)).append("\n");
    sb.append("    startTime: ").append(toIndentedString(startTime)).append("\n");
    sb.append("    lcmOperation: ").append(toIndentedString(lcmOperation)).append("\n");
    sb.append("    operationParams: ").append(toIndentedString(operationParams)).append("\n");
    sb.append("    _links: ").append(toIndentedString(_links)).append("\n");
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
