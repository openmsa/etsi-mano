package com.ubiqube.etsi.mano.nfvo.v331.model.vnfsnapshotpkgm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.nfvo.v331.model.vnfsnapshotpkgm.ProblemDetails;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Failure details associated to current error state of the VNF snapshot package state.  If \&quot;state\&quot; is \&quot;ERROR\&quot; or \&quot;ERROR_EXTRACTING\&quot;, this attribute shall be present unless it  has been requested to be excluded via an attribute selector. 
 */
@Schema(description = "Failure details associated to current error state of the VNF snapshot package state.  If \"state\" is \"ERROR\" or \"ERROR_EXTRACTING\", this attribute shall be present unless it  has been requested to be excluded via an attribute selector. ")
@Validated


public class VnfSnapshotPkgInfoFailureDetails   {
  /**
   * Type of error, when the failure happened (building, upload, processing, extracting). Permitted values:  - BUILD_ERROR - UPLOAD_ERROR - PROCESS_ERROR - CANCELLED - EXTRACTION_ERROR 
   */
  public enum ErrorTypeEnum {
    BUILD_ERROR("BUILD_ERROR"),
    
    UPLOAD_ERROR("UPLOAD_ERROR"),
    
    PROCESS_ERROR("PROCESS_ERROR"),
    
    CANCELLED("CANCELLED"),
    
    EXTRACTION_ERROR("EXTRACTION_ERROR");

    private String value;

    ErrorTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static ErrorTypeEnum fromValue(String text) {
      for (ErrorTypeEnum b : ErrorTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("errorType")
  private ErrorTypeEnum errorType = null;

  @JsonProperty("details")
  private ProblemDetails details = null;

  public VnfSnapshotPkgInfoFailureDetails errorType(ErrorTypeEnum errorType) {
    this.errorType = errorType;
    return this;
  }

  /**
   * Type of error, when the failure happened (building, upload, processing, extracting). Permitted values:  - BUILD_ERROR - UPLOAD_ERROR - PROCESS_ERROR - CANCELLED - EXTRACTION_ERROR 
   * @return errorType
   **/
  @Schema(required = true, description = "Type of error, when the failure happened (building, upload, processing, extracting). Permitted values:  - BUILD_ERROR - UPLOAD_ERROR - PROCESS_ERROR - CANCELLED - EXTRACTION_ERROR ")
      @NotNull

    public ErrorTypeEnum getErrorType() {
    return errorType;
  }

  public void setErrorType(ErrorTypeEnum errorType) {
    this.errorType = errorType;
  }

  public VnfSnapshotPkgInfoFailureDetails details(ProblemDetails details) {
    this.details = details;
    return this;
  }

  /**
   * Get details
   * @return details
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public ProblemDetails getDetails() {
    return details;
  }

  public void setDetails(ProblemDetails details) {
    this.details = details;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VnfSnapshotPkgInfoFailureDetails vnfSnapshotPkgInfoFailureDetails = (VnfSnapshotPkgInfoFailureDetails) o;
    return Objects.equals(this.errorType, vnfSnapshotPkgInfoFailureDetails.errorType) &&
        Objects.equals(this.details, vnfSnapshotPkgInfoFailureDetails.details);
  }

  @Override
  public int hashCode() {
    return Objects.hash(errorType, details);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfSnapshotPkgInfoFailureDetails {\n");
    
    sb.append("    errorType: ").append(toIndentedString(errorType)).append("\n");
    sb.append("    details: ").append(toIndentedString(details)).append("\n");
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
