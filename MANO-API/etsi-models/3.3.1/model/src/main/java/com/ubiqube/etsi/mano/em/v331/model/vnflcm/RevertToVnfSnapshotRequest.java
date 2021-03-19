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
package com.ubiqube.etsi.mano.em.v331.model.vnflcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.em.v331.model.vnflcm.KeyValuePairs;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents request parameters for the \&quot;Revert-to VNF Snapshot\&quot; operation. 
 */
@Schema(description = "This type represents request parameters for the \"Revert-to VNF Snapshot\" operation. ")
@Validated


public class RevertToVnfSnapshotRequest   {
  @JsonProperty("vnfSnapshotInfoId")
  private String vnfSnapshotInfoId = null;

  @JsonProperty("vnfcInstanceId")
  private String vnfcInstanceId = null;

  @JsonProperty("vnfcSnapshotInfoId")
  private String vnfcSnapshotInfoId = null;

  @JsonProperty("additionalParams")
  private KeyValuePairs additionalParams = null;

  public RevertToVnfSnapshotRequest vnfSnapshotInfoId(String vnfSnapshotInfoId) {
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

  public RevertToVnfSnapshotRequest vnfcInstanceId(String vnfcInstanceId) {
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

  public RevertToVnfSnapshotRequest vnfcSnapshotInfoId(String vnfcSnapshotInfoId) {
    this.vnfcSnapshotInfoId = vnfcSnapshotInfoId;
    return this;
  }

  /**
   * Get vnfcSnapshotInfoId
   * @return vnfcSnapshotInfoId
   **/
  @Schema(description = "")
  
    public String getVnfcSnapshotInfoId() {
    return vnfcSnapshotInfoId;
  }

  public void setVnfcSnapshotInfoId(String vnfcSnapshotInfoId) {
    this.vnfcSnapshotInfoId = vnfcSnapshotInfoId;
  }

  public RevertToVnfSnapshotRequest additionalParams(KeyValuePairs additionalParams) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RevertToVnfSnapshotRequest revertToVnfSnapshotRequest = (RevertToVnfSnapshotRequest) o;
    return Objects.equals(this.vnfSnapshotInfoId, revertToVnfSnapshotRequest.vnfSnapshotInfoId) &&
        Objects.equals(this.vnfcInstanceId, revertToVnfSnapshotRequest.vnfcInstanceId) &&
        Objects.equals(this.vnfcSnapshotInfoId, revertToVnfSnapshotRequest.vnfcSnapshotInfoId) &&
        Objects.equals(this.additionalParams, revertToVnfSnapshotRequest.additionalParams);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vnfSnapshotInfoId, vnfcInstanceId, vnfcSnapshotInfoId, additionalParams);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RevertToVnfSnapshotRequest {\n");
    
    sb.append("    vnfSnapshotInfoId: ").append(toIndentedString(vnfSnapshotInfoId)).append("\n");
    sb.append("    vnfcInstanceId: ").append(toIndentedString(vnfcInstanceId)).append("\n");
    sb.append("    vnfcSnapshotInfoId: ").append(toIndentedString(vnfcSnapshotInfoId)).append("\n");
    sb.append("    additionalParams: ").append(toIndentedString(additionalParams)).append("\n");
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
