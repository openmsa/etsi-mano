package com.ubiqube.etsi.mano.nfvo.v351.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type specifies the identifier of information of an available VNF Snapshot to be deleted and the identifier of the related VNF instance of the NS instance. It shall comply with the provisions defined in Table 6.5.3.74-1. 
 */
@Schema(description = "This type specifies the identifier of information of an available VNF Snapshot to be deleted and the identifier of the related VNF instance of the NS instance. It shall comply with the provisions defined in Table 6.5.3.74-1. ")
@Validated


public class DeleteVnfSnapshotData   {
  @JsonProperty("vnfInstanceId")
  private String vnfInstanceId = null;

  @JsonProperty("vnfSnapshotInfoId")
  private String vnfSnapshotInfoId = null;

  public DeleteVnfSnapshotData vnfInstanceId(String vnfInstanceId) {
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

  public DeleteVnfSnapshotData vnfSnapshotInfoId(String vnfSnapshotInfoId) {
    this.vnfSnapshotInfoId = vnfSnapshotInfoId;
    return this;
  }

  /**
   * Get vnfSnapshotInfoId
   * @return vnfSnapshotInfoId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getVnfSnapshotInfoId() {
    return vnfSnapshotInfoId;
  }

  public void setVnfSnapshotInfoId(String vnfSnapshotInfoId) {
    this.vnfSnapshotInfoId = vnfSnapshotInfoId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DeleteVnfSnapshotData deleteVnfSnapshotData = (DeleteVnfSnapshotData) o;
    return Objects.equals(this.vnfInstanceId, deleteVnfSnapshotData.vnfInstanceId) &&
        Objects.equals(this.vnfSnapshotInfoId, deleteVnfSnapshotData.vnfSnapshotInfoId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vnfInstanceId, vnfSnapshotInfoId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DeleteVnfSnapshotData {\n");
    
    sb.append("    vnfInstanceId: ").append(toIndentedString(vnfInstanceId)).append("\n");
    sb.append("    vnfSnapshotInfoId: ").append(toIndentedString(vnfSnapshotInfoId)).append("\n");
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
