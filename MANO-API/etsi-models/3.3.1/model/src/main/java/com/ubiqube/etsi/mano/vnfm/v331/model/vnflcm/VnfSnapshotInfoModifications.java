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
 * This type represents attribute modifications that were performed on an \&quot;Individual VNF snapshot\&quot;  resource. The attributes that can be included consist of those requested to be modified explicitly  in the \&quot;VnfSnapshotInfoModificationRequest\&quot; data structure, and additional attributes of the  \&quot;VnfSnapshotInfo\&quot; data structure that were modified implicitly. The \&quot;VnfSnapshotInfoModifications\&quot;  data type shall comply with the provisions defined in table 5.5.2.25-1. 
 */
@Schema(description = "This type represents attribute modifications that were performed on an \"Individual VNF snapshot\"  resource. The attributes that can be included consist of those requested to be modified explicitly  in the \"VnfSnapshotInfoModificationRequest\" data structure, and additional attributes of the  \"VnfSnapshotInfo\" data structure that were modified implicitly. The \"VnfSnapshotInfoModifications\"  data type shall comply with the provisions defined in table 5.5.2.25-1. ")
@Validated


public class VnfSnapshotInfoModifications   {
  @JsonProperty("vnfSnapshotPkgId")
  private String vnfSnapshotPkgId = null;

  @JsonProperty("vnfSnapshot")
  private VnfSnapshot vnfSnapshot = null;

  public VnfSnapshotInfoModifications vnfSnapshotPkgId(String vnfSnapshotPkgId) {
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

  public VnfSnapshotInfoModifications vnfSnapshot(VnfSnapshot vnfSnapshot) {
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
    VnfSnapshotInfoModifications vnfSnapshotInfoModifications = (VnfSnapshotInfoModifications) o;
    return Objects.equals(this.vnfSnapshotPkgId, vnfSnapshotInfoModifications.vnfSnapshotPkgId) &&
        Objects.equals(this.vnfSnapshot, vnfSnapshotInfoModifications.vnfSnapshot);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vnfSnapshotPkgId, vnfSnapshot);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfSnapshotInfoModifications {\n");
    
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
