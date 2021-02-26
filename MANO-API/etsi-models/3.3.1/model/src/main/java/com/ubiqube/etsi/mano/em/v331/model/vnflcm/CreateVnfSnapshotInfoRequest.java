package com.ubiqube.etsi.mano.em.v331.model.vnflcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateVnfSnapshotInfoRequest createVnfSnapshotInfoRequest = (CreateVnfSnapshotInfoRequest) o;
    return Objects.equals(this.vnfSnapshotPkgId, createVnfSnapshotInfoRequest.vnfSnapshotPkgId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vnfSnapshotPkgId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateVnfSnapshotInfoRequest {\n");
    
    sb.append("    vnfSnapshotPkgId: ").append(toIndentedString(vnfSnapshotPkgId)).append("\n");
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
