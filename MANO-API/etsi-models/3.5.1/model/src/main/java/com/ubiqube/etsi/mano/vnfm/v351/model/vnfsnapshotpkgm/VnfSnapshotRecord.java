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
package com.ubiqube.etsi.mano.vnfm.v351.model.vnfsnapshotpkgm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents the VNF snapshot record which is contained in a VNF snapshot package. 
 */
@Schema(description = "This type represents the VNF snapshot record which is contained in a VNF snapshot package. ")
@Validated


public class VnfSnapshotRecord   {
  @JsonProperty("recordPath")
  private String recordPath = null;

  @JsonProperty("checksum")
  private String checksum = null;

  @JsonProperty("isEncrypted")
  private Boolean isEncrypted = null;

  public VnfSnapshotRecord recordPath(String recordPath) {
    this.recordPath = recordPath;
    return this;
  }

  /**
   * Get recordPath
   * @return recordPath
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getRecordPath() {
    return recordPath;
  }

  public void setRecordPath(String recordPath) {
    this.recordPath = recordPath;
  }

  public VnfSnapshotRecord checksum(String checksum) {
    this.checksum = checksum;
    return this;
  }

  /**
   * Get checksum
   * @return checksum
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getChecksum() {
    return checksum;
  }

  public void setChecksum(String checksum) {
    this.checksum = checksum;
  }

  public VnfSnapshotRecord isEncrypted(Boolean isEncrypted) {
    this.isEncrypted = isEncrypted;
    return this;
  }

  /**
   * Get isEncrypted
   * @return isEncrypted
   **/
  @Schema(required = true, description = "")
      @NotNull

    public Boolean getIsEncrypted() {
    return isEncrypted;
  }

  public void setIsEncrypted(Boolean isEncrypted) {
    this.isEncrypted = isEncrypted;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VnfSnapshotRecord vnfSnapshotRecord = (VnfSnapshotRecord) o;
    return Objects.equals(this.recordPath, vnfSnapshotRecord.recordPath) &&
        Objects.equals(this.checksum, vnfSnapshotRecord.checksum) &&
        Objects.equals(this.isEncrypted, vnfSnapshotRecord.isEncrypted);
  }

  @Override
  public int hashCode() {
    return Objects.hash(recordPath, checksum, isEncrypted);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfSnapshotRecord {\n");
    
    sb.append("    recordPath: ").append(toIndentedString(recordPath)).append("\n");
    sb.append("    checksum: ").append(toIndentedString(checksum)).append("\n");
    sb.append("    isEncrypted: ").append(toIndentedString(isEncrypted)).append("\n");
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
