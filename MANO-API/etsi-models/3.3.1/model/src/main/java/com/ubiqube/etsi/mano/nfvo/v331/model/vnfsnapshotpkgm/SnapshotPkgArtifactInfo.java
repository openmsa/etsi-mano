package com.ubiqube.etsi.mano.nfvo.v331.model.vnfsnapshotpkgm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v331.model.vnfsnapshotpkgm.Checksum;
import com.ubiqube.etsi.mano.nfvo.v331.model.vnfsnapshotpkgm.KeyValuePairs;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents an artifact other than a software image which is contained in a VNF  snapshot package. It shall comply with provisions defined in table 11.5.3.3-1. 
 */
@Schema(description = "This type represents an artifact other than a software image which is contained in a VNF  snapshot package. It shall comply with provisions defined in table 11.5.3.3-1. ")
@Validated


public class SnapshotPkgArtifactInfo   {
  @JsonProperty("artifactPath")
  private String artifactPath = null;

  @JsonProperty("artifactUri")
  private String artifactUri = null;

  @JsonProperty("checksum")
  private Checksum checksum = null;

  @JsonProperty("isEncrypted")
  private Boolean isEncrypted = null;

  @JsonProperty("metadata")
  private KeyValuePairs metadata = null;

  public SnapshotPkgArtifactInfo artifactPath(String artifactPath) {
    this.artifactPath = artifactPath;
    return this;
  }

  /**
   * Path which identifies the artifact and also allows to access a copy of the artifact. For an artifact contained as a file in the VNF snapshot package, this attribute shall be  present, and the value of this attribute shall start with the name of the first segment in  the path in the package, i.e. it shall not be prefixed by path separator characters such  as \".\" and \"/\". EXAMPLE: foo/bar/m%40ster.sh For an external artifact represented as a URI in the manifest file, this attribute shall  be present if the artifact has been downloaded by the NFVO or the artifact has been  processed after building the VNF snapshot package and shall be absent otherwise. If present,  it shall contain the artifactPath under which the artifact can be obtained using the  \"Individual artifact in a VNF snapshot package\" resource defined in clause 11.4.10. It is  the responsibility of the NFVO to synthesize this path in a manner that avoids any  collision of the synthesized artifact path with the paths and names of artifacts included  in the snapshot package. 
   * @return artifactPath
   **/
  @Schema(description = "Path which identifies the artifact and also allows to access a copy of the artifact. For an artifact contained as a file in the VNF snapshot package, this attribute shall be  present, and the value of this attribute shall start with the name of the first segment in  the path in the package, i.e. it shall not be prefixed by path separator characters such  as \".\" and \"/\". EXAMPLE: foo/bar/m%40ster.sh For an external artifact represented as a URI in the manifest file, this attribute shall  be present if the artifact has been downloaded by the NFVO or the artifact has been  processed after building the VNF snapshot package and shall be absent otherwise. If present,  it shall contain the artifactPath under which the artifact can be obtained using the  \"Individual artifact in a VNF snapshot package\" resource defined in clause 11.4.10. It is  the responsibility of the NFVO to synthesize this path in a manner that avoids any  collision of the synthesized artifact path with the paths and names of artifacts included  in the snapshot package. ")
  
    public String getArtifactPath() {
    return artifactPath;
  }

  public void setArtifactPath(String artifactPath) {
    this.artifactPath = artifactPath;
  }

  public SnapshotPkgArtifactInfo artifactUri(String artifactUri) {
    this.artifactUri = artifactUri;
    return this;
  }

  /**
   * Get artifactUri
   * @return artifactUri
   **/
  @Schema(description = "")
  
    public String getArtifactUri() {
    return artifactUri;
  }

  public void setArtifactUri(String artifactUri) {
    this.artifactUri = artifactUri;
  }

  public SnapshotPkgArtifactInfo checksum(Checksum checksum) {
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

  public SnapshotPkgArtifactInfo isEncrypted(Boolean isEncrypted) {
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

  public SnapshotPkgArtifactInfo metadata(KeyValuePairs metadata) {
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
    SnapshotPkgArtifactInfo snapshotPkgArtifactInfo = (SnapshotPkgArtifactInfo) o;
    return Objects.equals(this.artifactPath, snapshotPkgArtifactInfo.artifactPath) &&
        Objects.equals(this.artifactUri, snapshotPkgArtifactInfo.artifactUri) &&
        Objects.equals(this.checksum, snapshotPkgArtifactInfo.checksum) &&
        Objects.equals(this.isEncrypted, snapshotPkgArtifactInfo.isEncrypted) &&
        Objects.equals(this.metadata, snapshotPkgArtifactInfo.metadata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(artifactPath, artifactUri, checksum, isEncrypted, metadata);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SnapshotPkgArtifactInfo {\n");
    
    sb.append("    artifactPath: ").append(toIndentedString(artifactPath)).append("\n");
    sb.append("    artifactUri: ").append(toIndentedString(artifactUri)).append("\n");
    sb.append("    checksum: ").append(toIndentedString(checksum)).append("\n");
    sb.append("    isEncrypted: ").append(toIndentedString(isEncrypted)).append("\n");
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
