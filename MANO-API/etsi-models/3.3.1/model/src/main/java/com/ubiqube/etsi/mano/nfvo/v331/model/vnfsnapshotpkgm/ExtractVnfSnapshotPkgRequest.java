package com.ubiqube.etsi.mano.nfvo.v331.model.vnfsnapshotpkgm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents the request parameters for extracting the content of a VNF snapshot package. It shall comply with the provisions defined in table 11.5.2.7-1. 
 */
@Schema(description = "This type represents the request parameters for extracting the content of a VNF snapshot package. It shall comply with the provisions defined in table 11.5.2.7-1. ")
@Validated


public class ExtractVnfSnapshotPkgRequest  implements OneOfExtractVnfSnapshotPkgRequest {
  @JsonProperty("vnfSnapshotInfoId")
  private String vnfSnapshotInfoId = null;

  @JsonProperty("vnfInstanceId")
  private String vnfInstanceId = null;

  public ExtractVnfSnapshotPkgRequest vnfSnapshotInfoId(String vnfSnapshotInfoId) {
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

  public ExtractVnfSnapshotPkgRequest vnfInstanceId(String vnfInstanceId) {
    this.vnfInstanceId = vnfInstanceId;
    return this;
  }

  /**
   * Get vnfInstanceId
   * @return vnfInstanceId
   **/
  @Schema(description = "")
  
    public String getVnfInstanceId() {
    return vnfInstanceId;
  }

  public void setVnfInstanceId(String vnfInstanceId) {
    this.vnfInstanceId = vnfInstanceId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ExtractVnfSnapshotPkgRequest extractVnfSnapshotPkgRequest = (ExtractVnfSnapshotPkgRequest) o;
    return Objects.equals(this.vnfSnapshotInfoId, extractVnfSnapshotPkgRequest.vnfSnapshotInfoId) &&
        Objects.equals(this.vnfInstanceId, extractVnfSnapshotPkgRequest.vnfInstanceId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vnfSnapshotInfoId, vnfInstanceId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ExtractVnfSnapshotPkgRequest {\n");
    
    sb.append("    vnfSnapshotInfoId: ").append(toIndentedString(vnfSnapshotInfoId)).append("\n");
    sb.append("    vnfInstanceId: ").append(toIndentedString(vnfInstanceId)).append("\n");
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
