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
package com.ubiqube.etsi.mano.vnfm.v351.model.vnflcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * VnfLcmOpOccRejectedLcmCoordinations
 */
@Validated


public class VnfLcmOpOccRejectedLcmCoordinations   {
  @JsonProperty("coordinationActionName")
  private String coordinationActionName = null;

  @JsonProperty("rejectionTime")
  private OffsetDateTime rejectionTime = null;

  @JsonProperty("delay")
  private OffsetDateTime delay = null;

  /**
   * The endpoint type used by this coordination action. Valid values: • MGMT: coordination with other operation supporting management systems (e.g. EM) • VNF: coordination with the VNF instance 
   */
  public enum EndpointTypeEnum {
    MGMT("MGMT"),
    
    VNF("VNF");

    private String value;

    EndpointTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static EndpointTypeEnum fromValue(String text) {
      for (EndpointTypeEnum b : EndpointTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("endpointType")
  private EndpointTypeEnum endpointType = null;

  public VnfLcmOpOccRejectedLcmCoordinations coordinationActionName(String coordinationActionName) {
    this.coordinationActionName = coordinationActionName;
    return this;
  }

  /**
   * Get coordinationActionName
   * @return coordinationActionName
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getCoordinationActionName() {
    return coordinationActionName;
  }

  public void setCoordinationActionName(String coordinationActionName) {
    this.coordinationActionName = coordinationActionName;
  }

  public VnfLcmOpOccRejectedLcmCoordinations rejectionTime(OffsetDateTime rejectionTime) {
    this.rejectionTime = rejectionTime;
    return this;
  }

  /**
   * Get rejectionTime
   * @return rejectionTime
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public OffsetDateTime getRejectionTime() {
    return rejectionTime;
  }

  public void setRejectionTime(OffsetDateTime rejectionTime) {
    this.rejectionTime = rejectionTime;
  }

  public VnfLcmOpOccRejectedLcmCoordinations delay(OffsetDateTime delay) {
    this.delay = delay;
    return this;
  }

  /**
   * Get delay
   * @return delay
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public OffsetDateTime getDelay() {
    return delay;
  }

  public void setDelay(OffsetDateTime delay) {
    this.delay = delay;
  }

  public VnfLcmOpOccRejectedLcmCoordinations endpointType(EndpointTypeEnum endpointType) {
    this.endpointType = endpointType;
    return this;
  }

  /**
   * The endpoint type used by this coordination action. Valid values: • MGMT: coordination with other operation supporting management systems (e.g. EM) • VNF: coordination with the VNF instance 
   * @return endpointType
   **/
  @Schema(required = true, description = "The endpoint type used by this coordination action. Valid values: • MGMT: coordination with other operation supporting management systems (e.g. EM) • VNF: coordination with the VNF instance ")
      @NotNull

    public EndpointTypeEnum getEndpointType() {
    return endpointType;
  }

  public void setEndpointType(EndpointTypeEnum endpointType) {
    this.endpointType = endpointType;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VnfLcmOpOccRejectedLcmCoordinations vnfLcmOpOccRejectedLcmCoordinations = (VnfLcmOpOccRejectedLcmCoordinations) o;
    return Objects.equals(this.coordinationActionName, vnfLcmOpOccRejectedLcmCoordinations.coordinationActionName) &&
        Objects.equals(this.rejectionTime, vnfLcmOpOccRejectedLcmCoordinations.rejectionTime) &&
        Objects.equals(this.delay, vnfLcmOpOccRejectedLcmCoordinations.delay) &&
        Objects.equals(this.endpointType, vnfLcmOpOccRejectedLcmCoordinations.endpointType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(coordinationActionName, rejectionTime, delay, endpointType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfLcmOpOccRejectedLcmCoordinations {\n");
    
    sb.append("    coordinationActionName: ").append(toIndentedString(coordinationActionName)).append("\n");
    sb.append("    rejectionTime: ").append(toIndentedString(rejectionTime)).append("\n");
    sb.append("    delay: ").append(toIndentedString(delay)).append("\n");
    sb.append("    endpointType: ").append(toIndentedString(endpointType)).append("\n");
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
