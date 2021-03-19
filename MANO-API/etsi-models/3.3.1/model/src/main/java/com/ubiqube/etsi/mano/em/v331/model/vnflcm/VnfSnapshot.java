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
import com.ubiqube.etsi.mano.em.v331.model.vnflcm.VnfInstance;
import com.ubiqube.etsi.mano.em.v331.model.vnflcm.VnfcSnapshotInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents a VNF snapshot. 
 */
@Schema(description = "This type represents a VNF snapshot. ")
@Validated


public class VnfSnapshot   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("vnfInstanceId")
  private String vnfInstanceId = null;

  @JsonProperty("creationStartedAt")
  private OffsetDateTime creationStartedAt = null;

  @JsonProperty("creationFinishedAt")
  private OffsetDateTime creationFinishedAt = null;

  @JsonProperty("vnfdId")
  private String vnfdId = null;

  @JsonProperty("vnfInstance")
  private VnfInstance vnfInstance = null;

  @JsonProperty("vnfcSnapshots")
  @Valid
  private List<VnfcSnapshotInfo> vnfcSnapshots = new ArrayList<>();

  @JsonProperty("userDefinedData")
  private KeyValuePairs userDefinedData = null;

  public VnfSnapshot id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public VnfSnapshot vnfInstanceId(String vnfInstanceId) {
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

  public VnfSnapshot creationStartedAt(OffsetDateTime creationStartedAt) {
    this.creationStartedAt = creationStartedAt;
    return this;
  }

  /**
   * Get creationStartedAt
   * @return creationStartedAt
   **/
  @Schema(description = "")
  
    @Valid
    public OffsetDateTime getCreationStartedAt() {
    return creationStartedAt;
  }

  public void setCreationStartedAt(OffsetDateTime creationStartedAt) {
    this.creationStartedAt = creationStartedAt;
  }

  public VnfSnapshot creationFinishedAt(OffsetDateTime creationFinishedAt) {
    this.creationFinishedAt = creationFinishedAt;
    return this;
  }

  /**
   * Get creationFinishedAt
   * @return creationFinishedAt
   **/
  @Schema(description = "")
  
    @Valid
    public OffsetDateTime getCreationFinishedAt() {
    return creationFinishedAt;
  }

  public void setCreationFinishedAt(OffsetDateTime creationFinishedAt) {
    this.creationFinishedAt = creationFinishedAt;
  }

  public VnfSnapshot vnfdId(String vnfdId) {
    this.vnfdId = vnfdId;
    return this;
  }

  /**
   * Get vnfdId
   * @return vnfdId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getVnfdId() {
    return vnfdId;
  }

  public void setVnfdId(String vnfdId) {
    this.vnfdId = vnfdId;
  }

  public VnfSnapshot vnfInstance(VnfInstance vnfInstance) {
    this.vnfInstance = vnfInstance;
    return this;
  }

  /**
   * Get vnfInstance
   * @return vnfInstance
   **/
  @Schema(description = "")
  
    @Valid
    public VnfInstance getVnfInstance() {
    return vnfInstance;
  }

  public void setVnfInstance(VnfInstance vnfInstance) {
    this.vnfInstance = vnfInstance;
  }

  public VnfSnapshot vnfcSnapshots(List<VnfcSnapshotInfo> vnfcSnapshots) {
    this.vnfcSnapshots = vnfcSnapshots;
    return this;
  }

  public VnfSnapshot addVnfcSnapshotsItem(VnfcSnapshotInfo vnfcSnapshotsItem) {
    this.vnfcSnapshots.add(vnfcSnapshotsItem);
    return this;
  }

  /**
   * Information about VNFC snapshots constituting this VNF snapshot. 
   * @return vnfcSnapshots
   **/
  @Schema(required = true, description = "Information about VNFC snapshots constituting this VNF snapshot. ")
      @NotNull
    @Valid
    public List<VnfcSnapshotInfo> getVnfcSnapshots() {
    return vnfcSnapshots;
  }

  public void setVnfcSnapshots(List<VnfcSnapshotInfo> vnfcSnapshots) {
    this.vnfcSnapshots = vnfcSnapshots;
  }

  public VnfSnapshot userDefinedData(KeyValuePairs userDefinedData) {
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
    VnfSnapshot vnfSnapshot = (VnfSnapshot) o;
    return Objects.equals(this.id, vnfSnapshot.id) &&
        Objects.equals(this.vnfInstanceId, vnfSnapshot.vnfInstanceId) &&
        Objects.equals(this.creationStartedAt, vnfSnapshot.creationStartedAt) &&
        Objects.equals(this.creationFinishedAt, vnfSnapshot.creationFinishedAt) &&
        Objects.equals(this.vnfdId, vnfSnapshot.vnfdId) &&
        Objects.equals(this.vnfInstance, vnfSnapshot.vnfInstance) &&
        Objects.equals(this.vnfcSnapshots, vnfSnapshot.vnfcSnapshots) &&
        Objects.equals(this.userDefinedData, vnfSnapshot.userDefinedData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, vnfInstanceId, creationStartedAt, creationFinishedAt, vnfdId, vnfInstance, vnfcSnapshots, userDefinedData);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfSnapshot {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    vnfInstanceId: ").append(toIndentedString(vnfInstanceId)).append("\n");
    sb.append("    creationStartedAt: ").append(toIndentedString(creationStartedAt)).append("\n");
    sb.append("    creationFinishedAt: ").append(toIndentedString(creationFinishedAt)).append("\n");
    sb.append("    vnfdId: ").append(toIndentedString(vnfdId)).append("\n");
    sb.append("    vnfInstance: ").append(toIndentedString(vnfInstance)).append("\n");
    sb.append("    vnfcSnapshots: ").append(toIndentedString(vnfcSnapshots)).append("\n");
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
