package com.ubiqube.etsi.mano.nfvo.v331.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.KeyValuePairs;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents the information that are needed for VNF snapshot creation. When the NFVO invokes the Create VNF snapshot operation, a set of these parameters are then passed by the NFVO to the VNFM. It shall comply with the provisions defined in Table 6.5.3.76-1. 
 */
@Schema(description = "This type represents the information that are needed for VNF snapshot creation. When the NFVO invokes the Create VNF snapshot operation, a set of these parameters are then passed by the NFVO to the VNFM. It shall comply with the provisions defined in Table 6.5.3.76-1. ")
@Validated


public class CreateVnfSnapshotData   {
  @JsonProperty("vnfInstanceId")
  private String vnfInstanceId = null;

  @JsonProperty("additionalParams")
  private KeyValuePairs additionalParams = null;

  @JsonProperty("userDefinedData")
  private KeyValuePairs userDefinedData = null;

  public CreateVnfSnapshotData vnfInstanceId(String vnfInstanceId) {
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

  public CreateVnfSnapshotData additionalParams(KeyValuePairs additionalParams) {
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

  public CreateVnfSnapshotData userDefinedData(KeyValuePairs userDefinedData) {
    this.userDefinedData = userDefinedData;
    return this;
  }

  /**
   * Get userDefinedData
   * @return userDefinedData
   **/
  @Schema(description = "")
  
    @Valid
    public KeyValuePairs getUserDefinedData() {
    return userDefinedData;
  }

  public void setUserDefinedData(KeyValuePairs userDefinedData) {
    this.userDefinedData = userDefinedData;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateVnfSnapshotData createVnfSnapshotData = (CreateVnfSnapshotData) o;
    return Objects.equals(this.vnfInstanceId, createVnfSnapshotData.vnfInstanceId) &&
        Objects.equals(this.additionalParams, createVnfSnapshotData.additionalParams) &&
        Objects.equals(this.userDefinedData, createVnfSnapshotData.userDefinedData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vnfInstanceId, additionalParams, userDefinedData);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateVnfSnapshotData {\n");
    
    sb.append("    vnfInstanceId: ").append(toIndentedString(vnfInstanceId)).append("\n");
    sb.append("    additionalParams: ").append(toIndentedString(additionalParams)).append("\n");
    sb.append("    userDefinedData: ").append(toIndentedString(userDefinedData)).append("\n");
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
