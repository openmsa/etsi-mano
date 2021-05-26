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
package com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm.VnfSnapshot;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents request parameters for the creation of an \&quot;Individual VNF snapshot\&quot; resource which can be populated with content obtained by invoking the \&quot;Create VNF snapshot\&quot; LCM operation or extracted from a VNF snapshot package. It shall comply with the provisions defined in table 5.5.2.20-1. 
 */
@Schema(description = "This type represents request parameters for the creation of an \"Individual VNF snapshot\" resource which can be populated with content obtained by invoking the \"Create VNF snapshot\" LCM operation or extracted from a VNF snapshot package. It shall comply with the provisions defined in table 5.5.2.20-1. ")
@Validated


public class CreateVnfSnapshotInfoRequest   {
  @JsonProperty("vnfSnapshotPkgId")
  private String vnfSnapshotPkgId = null;

  @JsonProperty("vnfSnapshot")
  private VnfSnapshot vnfSnapshot = null;

  public CreateVnfSnapshotInfoRequest vnfSnapshotPkgId(String vnfSnapshotPkgId) {
    this.vnfSnapshotPkgId = vnfSnapshotPkgId;
    return this;
  }

  /**
   * Get vnfSnapshotPkgId
   * @return vnfSnapshotPkgId
   **/
  @Schema(description = "")
  
    public String getVnfSnapshotPkgId() {
    return vnfSnapshotPkgId;
  }

  public void setVnfSnapshotPkgId(String vnfSnapshotPkgId) {
    this.vnfSnapshotPkgId = vnfSnapshotPkgId;
  }

  public CreateVnfSnapshotInfoRequest vnfSnapshot(VnfSnapshot vnfSnapshot) {
    this.vnfSnapshot = vnfSnapshot;
    return this;
  }

  /**
   * Get vnfSnapshot
   * @return vnfSnapshot
   **/
  @Schema(description = "")
  
    @Valid
    public VnfSnapshot getVnfSnapshot() {
    return vnfSnapshot;
  }

  public void setVnfSnapshot(VnfSnapshot vnfSnapshot) {
    this.vnfSnapshot = vnfSnapshot;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateVnfSnapshotInfoRequest createVnfSnapshotInfoRequest = (CreateVnfSnapshotInfoRequest) o;
    return Objects.equals(this.vnfSnapshotPkgId, createVnfSnapshotInfoRequest.vnfSnapshotPkgId) &&
        Objects.equals(this.vnfSnapshot, createVnfSnapshotInfoRequest.vnfSnapshot);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vnfSnapshotPkgId, vnfSnapshot);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateVnfSnapshotInfoRequest {\n");
    
    sb.append("    vnfSnapshotPkgId: ").append(toIndentedString(vnfSnapshotPkgId)).append("\n");
    sb.append("    vnfSnapshot: ").append(toIndentedString(vnfSnapshot)).append("\n");
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
