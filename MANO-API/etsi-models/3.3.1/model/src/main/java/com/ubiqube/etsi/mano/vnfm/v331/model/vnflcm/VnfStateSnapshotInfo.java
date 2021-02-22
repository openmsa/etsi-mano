package com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents information about VNF-specific state snapshot data. 
 */
@Schema(description = "This type represents information about VNF-specific state snapshot data. ")
@Validated


public class VnfStateSnapshotInfo   {
  @JsonProperty("checksum")
  private Object checksum = null;

  @JsonProperty("isEncrypted")
  private Object isEncrypted = null;

  @JsonProperty("metadata")
  private Object metadata = null;

  public VnfStateSnapshotInfo checksum(Object checksum) {
    this.checksum = checksum;
    return this;
  }

  /**
   * Checksum of the VNF state snapshot file. Hash algorithms applicable to VNF snapshot package artifacts are defined in ETSI GS NFV-SOL 010. $ref: \"../definitions/SOL002SOL003_def.yaml#/definitions/Checksum\" 
   * @return checksum
   **/
  @Schema(required = true, description = "Checksum of the VNF state snapshot file. Hash algorithms applicable to VNF snapshot package artifacts are defined in ETSI GS NFV-SOL 010. $ref: \"../definitions/SOL002SOL003_def.yaml#/definitions/Checksum\" ")
      @NotNull

    public Object getChecksum() {
    return checksum;
  }

  public void setChecksum(Object checksum) {
    this.checksum = checksum;
  }

  public VnfStateSnapshotInfo isEncrypted(Object isEncrypted) {
    this.isEncrypted = isEncrypted;
    return this;
  }

  /**
   * Reflects whether the VNF state snapshot content is encrypted (true) or not (false). $ref: \"../definitions/SOL002SOL003_def.yaml#/definitions/Boolean\" 
   * @return isEncrypted
   **/
  @Schema(required = true, description = "Reflects whether the VNF state snapshot content is encrypted (true) or not (false). $ref: \"../definitions/SOL002SOL003_def.yaml#/definitions/Boolean\" ")
      @NotNull

    public Object getIsEncrypted() {
    return isEncrypted;
  }

  public void setIsEncrypted(Object isEncrypted) {
    this.isEncrypted = isEncrypted;
  }

  public VnfStateSnapshotInfo metadata(Object metadata) {
    this.metadata = metadata;
    return this;
  }

  /**
   * The metadata with additional information such as content type, size, creation date, etc. $ref: \"../definitions/SOL002SOL003_def.yaml#/definitions/KeyValuePairs\" 
   * @return metadata
   **/
  @Schema(description = "The metadata with additional information such as content type, size, creation date, etc. $ref: \"../definitions/SOL002SOL003_def.yaml#/definitions/KeyValuePairs\" ")
  
    public Object getMetadata() {
    return metadata;
  }

  public void setMetadata(Object metadata) {
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
    VnfStateSnapshotInfo vnfStateSnapshotInfo = (VnfStateSnapshotInfo) o;
    return Objects.equals(this.checksum, vnfStateSnapshotInfo.checksum) &&
        Objects.equals(this.isEncrypted, vnfStateSnapshotInfo.isEncrypted) &&
        Objects.equals(this.metadata, vnfStateSnapshotInfo.metadata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(checksum, isEncrypted, metadata);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfStateSnapshotInfo {\n");
    
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
