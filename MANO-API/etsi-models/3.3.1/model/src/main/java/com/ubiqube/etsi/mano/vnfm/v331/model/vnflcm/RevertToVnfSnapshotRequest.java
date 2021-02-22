package com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm.KeyValuePairs;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents request parameters for the \&quot;Revert to VNF Snapshot\&quot; operation. 
 */
@Schema(description = "This type represents request parameters for the \"Revert to VNF Snapshot\" operation. ")
@Validated


public class RevertToVnfSnapshotRequest   {
  @JsonProperty("vnfSnapshotInfoId")
  private String vnfSnapshotInfoId = null;

  @JsonProperty("additionalParams")
  private KeyValuePairs additionalParams = null;

  public RevertToVnfSnapshotRequest vnfSnapshotInfoId(String vnfSnapshotInfoId) {
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

  public RevertToVnfSnapshotRequest additionalParams(KeyValuePairs additionalParams) {
    this.additionalParams = additionalParams;
    return this;
  }

  /**
   * Get additionalParams
   * @return additionalParams
   **/
  @Schema(description = "")
  
    @Valid
    public KeyValuePairs getAdditionalParams() {
    return additionalParams;
  }

  public void setAdditionalParams(KeyValuePairs additionalParams) {
    this.additionalParams = additionalParams;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RevertToVnfSnapshotRequest revertToVnfSnapshotRequest = (RevertToVnfSnapshotRequest) o;
    return Objects.equals(this.vnfSnapshotInfoId, revertToVnfSnapshotRequest.vnfSnapshotInfoId) &&
        Objects.equals(this.additionalParams, revertToVnfSnapshotRequest.additionalParams);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vnfSnapshotInfoId, additionalParams);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RevertToVnfSnapshotRequest {\n");
    
    sb.append("    vnfSnapshotInfoId: ").append(toIndentedString(vnfSnapshotInfoId)).append("\n");
    sb.append("    additionalParams: ").append(toIndentedString(additionalParams)).append("\n");
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
