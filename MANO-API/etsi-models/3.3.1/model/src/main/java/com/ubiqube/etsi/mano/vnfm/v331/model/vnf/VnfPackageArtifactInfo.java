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
package com.ubiqube.etsi.mano.vnfm.v331.model.vnf;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnf.KeyValuePairs;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents an artifact other than a software image which is contained in or external to a VNF package. 
 */
@Schema(description = "This type represents an artifact other than a software image which is contained in or external to a VNF package. ")
@Validated


public class VnfPackageArtifactInfo   {
  @JsonProperty("artifactPath")
  private String artifactPath = null;

  @JsonProperty("artifactURI")
  @Valid
  private List<String> artifactURI = null;

  @JsonProperty("checksum")
  private String checksum = null;

  @JsonProperty("isEncrypted")
  private Boolean isEncrypted = null;

  @JsonProperty("nonManoArtifactSetId")
  private String nonManoArtifactSetId = null;

  /**
   * Marks specific types of artifacts as defined in the VNF package. If none of the specific classes listed below applies, the attribute shall not be present. Valid values: - HISTORY: a history artifact as per clause 4.3.3 in ETSI GS NFV-SOL 004 - TESTING: a testing artifact as per clause 4.3.4 in ETSI GS NFV-SOL 004 - LICENSE: a license artifact as per clause 4.3.5 in ETSI GS NFV-SOL 004 
   */
  public enum ArtifactClassificationEnum {
    HISTORY("HISTORY"),
    
    TESTING("TESTING"),
    
    LICENSE("LICENSE");

    private String value;

    ArtifactClassificationEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static ArtifactClassificationEnum fromValue(String text) {
      for (ArtifactClassificationEnum b : ArtifactClassificationEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("artifactClassification")
  private ArtifactClassificationEnum artifactClassification = null;

  @JsonProperty("metadata")
  private KeyValuePairs metadata = null;

  public VnfPackageArtifactInfo artifactPath(String artifactPath) {
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

  public VnfPackageArtifactInfo artifactURI(List<String> artifactURI) {
    this.artifactURI = artifactURI;
    return this;
  }

  public VnfPackageArtifactInfo addArtifactURIItem(String artifactURIItem) {
    if (this.artifactURI == null) {
      this.artifactURI = new ArrayList<>();
    }
    this.artifactURI.add(artifactURIItem);
    return this;
  }

  /**
   * URI of the artifact as defined in the VNF package manifest. Shall be present if the artifact is external to the package and shall be absent otherwise. EXAMPLE: https://example.com/m%40ster.sh 
   * @return artifactURI
   **/
  @Schema(description = "URI of the artifact as defined in the VNF package manifest. Shall be present if the artifact is external to the package and shall be absent otherwise. EXAMPLE: https://example.com/m%40ster.sh ")
  
    public List<String> getArtifactURI() {
    return artifactURI;
  }

  public void setArtifactURI(List<String> artifactURI) {
    this.artifactURI = artifactURI;
  }

  public VnfPackageArtifactInfo checksum(String checksum) {
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

  public VnfPackageArtifactInfo isEncrypted(Boolean isEncrypted) {
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

  public VnfPackageArtifactInfo nonManoArtifactSetId(String nonManoArtifactSetId) {
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

  public VnfPackageArtifactInfo artifactClassification(ArtifactClassificationEnum artifactClassification) {
    this.artifactClassification = artifactClassification;
    return this;
  }

  /**
   * Marks specific types of artifacts as defined in the VNF package. If none of the specific classes listed below applies, the attribute shall not be present. Valid values: - HISTORY: a history artifact as per clause 4.3.3 in ETSI GS NFV-SOL 004 - TESTING: a testing artifact as per clause 4.3.4 in ETSI GS NFV-SOL 004 - LICENSE: a license artifact as per clause 4.3.5 in ETSI GS NFV-SOL 004 
   * @return artifactClassification
   **/
  @Schema(description = "Marks specific types of artifacts as defined in the VNF package. If none of the specific classes listed below applies, the attribute shall not be present. Valid values: - HISTORY: a history artifact as per clause 4.3.3 in ETSI GS NFV-SOL 004 - TESTING: a testing artifact as per clause 4.3.4 in ETSI GS NFV-SOL 004 - LICENSE: a license artifact as per clause 4.3.5 in ETSI GS NFV-SOL 004 ")
  
    public ArtifactClassificationEnum getArtifactClassification() {
    return artifactClassification;
  }

  public void setArtifactClassification(ArtifactClassificationEnum artifactClassification) {
    this.artifactClassification = artifactClassification;
  }

  public VnfPackageArtifactInfo metadata(KeyValuePairs metadata) {
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
    VnfPackageArtifactInfo vnfPackageArtifactInfo = (VnfPackageArtifactInfo) o;
    return Objects.equals(this.artifactPath, vnfPackageArtifactInfo.artifactPath) &&
        Objects.equals(this.artifactURI, vnfPackageArtifactInfo.artifactURI) &&
        Objects.equals(this.checksum, vnfPackageArtifactInfo.checksum) &&
        Objects.equals(this.isEncrypted, vnfPackageArtifactInfo.isEncrypted) &&
        Objects.equals(this.nonManoArtifactSetId, vnfPackageArtifactInfo.nonManoArtifactSetId) &&
        Objects.equals(this.artifactClassification, vnfPackageArtifactInfo.artifactClassification) &&
        Objects.equals(this.metadata, vnfPackageArtifactInfo.metadata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(artifactPath, artifactURI, checksum, isEncrypted, nonManoArtifactSetId, artifactClassification, metadata);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfPackageArtifactInfo {\n");
    
    sb.append("    artifactPath: ").append(toIndentedString(artifactPath)).append("\n");
    sb.append("    artifactURI: ").append(toIndentedString(artifactURI)).append("\n");
    sb.append("    checksum: ").append(toIndentedString(checksum)).append("\n");
    sb.append("    isEncrypted: ").append(toIndentedString(isEncrypted)).append("\n");
    sb.append("    nonManoArtifactSetId: ").append(toIndentedString(nonManoArtifactSetId)).append("\n");
    sb.append("    artifactClassification: ").append(toIndentedString(artifactClassification)).append("\n");
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
