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
package com.ubiqube.etsi.mano.nfvo.v331.model.nsd;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v331.model.nsd.Checksum;
import com.ubiqube.etsi.mano.nfvo.v331.model.nsd.KeyValuePairs;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents an artifact contained in a PNFD archive. It shall comply with provisions defined in Table 5.5.3.6-1. 
 */
@Schema(description = "This type represents an artifact contained in a PNFD archive. It shall comply with provisions defined in Table 5.5.3.6-1. ")
@Validated


public class PnfdArchiveArtifactInfo   {
  @JsonProperty("artifactPath")
  private String artifactPath = null;

  @JsonProperty("checksum")
  private Checksum checksum = null;

  @JsonProperty("nonManoArtifactSetId")
  private String nonManoArtifactSetId = null;

  @JsonProperty("metadata")
  private KeyValuePairs metadata = null;

  public PnfdArchiveArtifactInfo artifactPath(String artifactPath) {
    this.artifactPath = artifactPath;
    return this;
  }

  /**
   * Get artifactPath
   * @return artifactPath
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getArtifactPath() {
    return artifactPath;
  }

  public void setArtifactPath(String artifactPath) {
    this.artifactPath = artifactPath;
  }

  public PnfdArchiveArtifactInfo checksum(Checksum checksum) {
    this.checksum = checksum;
    return this;
  }

  /**
   * Get checksum
   * @return checksum
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public Checksum getChecksum() {
    return checksum;
  }

  public void setChecksum(Checksum checksum) {
    this.checksum = checksum;
  }

  public PnfdArchiveArtifactInfo nonManoArtifactSetId(String nonManoArtifactSetId) {
    this.nonManoArtifactSetId = nonManoArtifactSetId;
    return this;
  }

  /**
   * Get nonManoArtifactSetId
   * @return nonManoArtifactSetId
   **/
  @Schema(description = "")
  
    public String getNonManoArtifactSetId() {
    return nonManoArtifactSetId;
  }

  public void setNonManoArtifactSetId(String nonManoArtifactSetId) {
    this.nonManoArtifactSetId = nonManoArtifactSetId;
  }

  public PnfdArchiveArtifactInfo metadata(KeyValuePairs metadata) {
    this.metadata = metadata;
    return this;
  }

  /**
   * Get metadata
   * @return metadata
   **/
  @Schema(description = "")
  
    @Valid
    public KeyValuePairs getMetadata() {
    return metadata;
  }

  public void setMetadata(KeyValuePairs metadata) {
    this.metadata = metadata;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PnfdArchiveArtifactInfo pnfdArchiveArtifactInfo = (PnfdArchiveArtifactInfo) o;
    return Objects.equals(this.artifactPath, pnfdArchiveArtifactInfo.artifactPath) &&
        Objects.equals(this.checksum, pnfdArchiveArtifactInfo.checksum) &&
        Objects.equals(this.nonManoArtifactSetId, pnfdArchiveArtifactInfo.nonManoArtifactSetId) &&
        Objects.equals(this.metadata, pnfdArchiveArtifactInfo.metadata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(artifactPath, checksum, nonManoArtifactSetId, metadata);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PnfdArchiveArtifactInfo {\n");
    
    sb.append("    artifactPath: ").append(toIndentedString(artifactPath)).append("\n");
    sb.append("    checksum: ").append(toIndentedString(checksum)).append("\n");
    sb.append("    nonManoArtifactSetId: ").append(toIndentedString(nonManoArtifactSetId)).append("\n");
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
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
