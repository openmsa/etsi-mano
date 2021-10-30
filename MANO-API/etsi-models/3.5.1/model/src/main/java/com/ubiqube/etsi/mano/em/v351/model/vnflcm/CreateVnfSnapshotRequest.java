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
package com.ubiqube.etsi.mano.em.v351.model.vnflcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.em.v351.model.vnflcm.KeyValuePairs;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents request parameters for the \&quot;Create VNF Snapshot\&quot; operation. 
 */
@Schema(description = "This type represents request parameters for the \"Create VNF Snapshot\" operation. ")
@Validated


public class CreateVnfSnapshotRequest   {
  @JsonProperty("vnfSnapshotInfoId")
  private String vnfSnapshotInfoId = null;

  @JsonProperty("vnfcInstanceId")
  private String vnfcInstanceId = null;

  @JsonProperty("additionalParams")
  private KeyValuePairs additionalParams = null;

  @JsonProperty("userDefinedData")
  private KeyValuePairs userDefinedData = null;

  public CreateVnfSnapshotRequest vnfSnapshotInfoId(String vnfSnapshotInfoId) {
    this.vnfSnapshotInfoId = vnfSnapshotInfoId;
    return this;
  }

  /**
   * Get vnfSnapshotInfoId
   * @return vnfSnapshotInfoId
   **/
  @Schema(description = "")
  
    public String getVnfSnapshotInfoId() {
    return vnfSnapshotInfoId;
  }

  public void setVnfSnapshotInfoId(String vnfSnapshotInfoId) {
    this.vnfSnapshotInfoId = vnfSnapshotInfoId;
  }

  public CreateVnfSnapshotRequest vnfcInstanceId(String vnfcInstanceId) {
    this.vnfcInstanceId = vnfcInstanceId;
    return this;
  }

  /**
   * Get vnfcInstanceId
   * @return vnfcInstanceId
   **/
  @Schema(description = "")
  
    public String getVnfcInstanceId() {
    return vnfcInstanceId;
  }

  public void setVnfcInstanceId(String vnfcInstanceId) {
    this.vnfcInstanceId = vnfcInstanceId;
  }

  public CreateVnfSnapshotRequest additionalParams(KeyValuePairs additionalParams) {
    this.additionalParams = additionalParams;
    return this;
  }

  /**
   * Get additionalParams
   * @return additionalParams
   **/
  @Schema(description = "")
  
    @Valid
    public KeyValuePairs getAdditionalParams() {
    return additionalParams;
  }

  public void setAdditionalParams(KeyValuePairs additionalParams) {
    this.additionalParams = additionalParams;
  }

  public CreateVnfSnapshotRequest userDefinedData(KeyValuePairs userDefinedData) {
    this.userDefinedData = userDefinedData;
    return this;
  }

  /**
   * Get userDefinedData
   * @return userDefinedData
   **/
  @Schema(description = "")
  
    @Valid
    public KeyValuePairs getUserDefinedData() {
    return userDefinedData;
  }

  public void setUserDefinedData(KeyValuePairs userDefinedData) {
    this.userDefinedData = userDefinedData;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateVnfSnapshotRequest createVnfSnapshotRequest = (CreateVnfSnapshotRequest) o;
    return Objects.equals(this.vnfSnapshotInfoId, createVnfSnapshotRequest.vnfSnapshotInfoId) &&
        Objects.equals(this.vnfcInstanceId, createVnfSnapshotRequest.vnfcInstanceId) &&
        Objects.equals(this.additionalParams, createVnfSnapshotRequest.additionalParams) &&
        Objects.equals(this.userDefinedData, createVnfSnapshotRequest.userDefinedData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vnfSnapshotInfoId, vnfcInstanceId, additionalParams, userDefinedData);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateVnfSnapshotRequest {\n");
    
    sb.append("    vnfSnapshotInfoId: ").append(toIndentedString(vnfSnapshotInfoId)).append("\n");
    sb.append("    vnfcInstanceId: ").append(toIndentedString(vnfcInstanceId)).append("\n");
    sb.append("    additionalParams: ").append(toIndentedString(additionalParams)).append("\n");
    sb.append("    userDefinedData: ").append(toIndentedString(userDefinedData)).append("\n");
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
