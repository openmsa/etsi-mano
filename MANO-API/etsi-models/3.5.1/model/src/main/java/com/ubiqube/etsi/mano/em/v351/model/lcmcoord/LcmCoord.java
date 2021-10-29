package com.ubiqube.etsi.mano.em.v351.model.lcmcoord;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.em.v351.model.lcmcoord.KeyValuePairs;
import com.ubiqube.etsi.mano.em.v351.model.lcmcoord.LcmCoordLinks;
import com.ubiqube.etsi.mano.em.v351.model.lcmcoord.LcmCoordResultType;
import com.ubiqube.etsi.mano.em.v351.model.lcmcoord.LcmOperationForCoordType;
import com.ubiqube.etsi.mano.em.v351.model.lcmcoord.ProblemDetails;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents an LCM coordination result. 
 */
@Schema(description = "This type represents an LCM coordination result. ")
@Validated


public class LcmCoord   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("coordinationResult")
  private LcmCoordResultType coordinationResult = null;

  @JsonProperty("vnfInstanceId")
  private String vnfInstanceId = null;

  @JsonProperty("vnfLcmOpOccId")
  private String vnfLcmOpOccId = null;

  @JsonProperty("lcmOperationType")
  private LcmOperationForCoordType lcmOperationType = null;

  @JsonProperty("coordinationActionName")
  private String coordinationActionName = null;

  @JsonProperty("outputParams")
  private KeyValuePairs outputParams = null;

  @JsonProperty("warnings")
  private String warnings = null;

  @JsonProperty("error")
  private ProblemDetails error = null;

  @JsonProperty("_links")
  private LcmCoordLinks _links = null;

  public LcmCoord id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public LcmCoord coordinationResult(LcmCoordResultType coordinationResult) {
    this.coordinationResult = coordinationResult;
    return this;
  }

  /**
   * Get coordinationResult
   * @return coordinationResult
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public LcmCoordResultType getCoordinationResult() {
    return coordinationResult;
  }

  public void setCoordinationResult(LcmCoordResultType coordinationResult) {
    this.coordinationResult = coordinationResult;
  }

  public LcmCoord vnfInstanceId(String vnfInstanceId) {
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

  public LcmCoord vnfLcmOpOccId(String vnfLcmOpOccId) {
    this.vnfLcmOpOccId = vnfLcmOpOccId;
    return this;
  }

  /**
   * Get vnfLcmOpOccId
   * @return vnfLcmOpOccId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getVnfLcmOpOccId() {
    return vnfLcmOpOccId;
  }

  public void setVnfLcmOpOccId(String vnfLcmOpOccId) {
    this.vnfLcmOpOccId = vnfLcmOpOccId;
  }

  public LcmCoord lcmOperationType(LcmOperationForCoordType lcmOperationType) {
    this.lcmOperationType = lcmOperationType;
    return this;
  }

  /**
   * Get lcmOperationType
   * @return lcmOperationType
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public LcmOperationForCoordType getLcmOperationType() {
    return lcmOperationType;
  }

  public void setLcmOperationType(LcmOperationForCoordType lcmOperationType) {
    this.lcmOperationType = lcmOperationType;
  }

  public LcmCoord coordinationActionName(String coordinationActionName) {
    this.coordinationActionName = coordinationActionName;
    return this;
  }

  /**
   * Indicates the actual LCM coordination action. The coordination actions that a VNF supports are declared in the VNFD. 
   * @return coordinationActionName
   **/
  @Schema(required = true, description = "Indicates the actual LCM coordination action. The coordination actions that a VNF supports are declared in the VNFD. ")
      @NotNull

    public String getCoordinationActionName() {
    return coordinationActionName;
  }

  public void setCoordinationActionName(String coordinationActionName) {
    this.coordinationActionName = coordinationActionName;
  }

  public LcmCoord outputParams(KeyValuePairs outputParams) {
    this.outputParams = outputParams;
    return this;
  }

  /**
   * Get outputParams
   * @return outputParams
   **/
  @Schema(description = "")
  
    @Valid
    public KeyValuePairs getOutputParams() {
    return outputParams;
  }

  public void setOutputParams(KeyValuePairs outputParams) {
    this.outputParams = outputParams;
  }

  public LcmCoord warnings(String warnings) {
    this.warnings = warnings;
    return this;
  }

  /**
   * Warning messages that were generated while the operation was executing. 
   * @return warnings
   **/
  @Schema(description = "Warning messages that were generated while the operation was executing. ")
  
    public String getWarnings() {
    return warnings;
  }

  public void setWarnings(String warnings) {
    this.warnings = warnings;
  }

  public LcmCoord error(ProblemDetails error) {
    this.error = error;
    return this;
  }

  /**
   * Get error
   * @return error
   **/
  @Schema(description = "")
  
    @Valid
    public ProblemDetails getError() {
    return error;
  }

  public void setError(ProblemDetails error) {
    this.error = error;
  }

  public LcmCoord _links(LcmCoordLinks _links) {
    this._links = _links;
    return this;
  }

  /**
   * Get _links
   * @return _links
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public LcmCoordLinks getLinks() {
    return _links;
  }

  public void setLinks(LcmCoordLinks _links) {
    this._links = _links;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LcmCoord lcmCoord = (LcmCoord) o;
    return Objects.equals(this.id, lcmCoord.id) &&
        Objects.equals(this.coordinationResult, lcmCoord.coordinationResult) &&
        Objects.equals(this.vnfInstanceId, lcmCoord.vnfInstanceId) &&
        Objects.equals(this.vnfLcmOpOccId, lcmCoord.vnfLcmOpOccId) &&
        Objects.equals(this.lcmOperationType, lcmCoord.lcmOperationType) &&
        Objects.equals(this.coordinationActionName, lcmCoord.coordinationActionName) &&
        Objects.equals(this.outputParams, lcmCoord.outputParams) &&
        Objects.equals(this.warnings, lcmCoord.warnings) &&
        Objects.equals(this.error, lcmCoord.error) &&
        Objects.equals(this._links, lcmCoord._links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, coordinationResult, vnfInstanceId, vnfLcmOpOccId, lcmOperationType, coordinationActionName, outputParams, warnings, error, _links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LcmCoord {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    coordinationResult: ").append(toIndentedString(coordinationResult)).append("\n");
    sb.append("    vnfInstanceId: ").append(toIndentedString(vnfInstanceId)).append("\n");
    sb.append("    vnfLcmOpOccId: ").append(toIndentedString(vnfLcmOpOccId)).append("\n");
    sb.append("    lcmOperationType: ").append(toIndentedString(lcmOperationType)).append("\n");
    sb.append("    coordinationActionName: ").append(toIndentedString(coordinationActionName)).append("\n");
    sb.append("    outputParams: ").append(toIndentedString(outputParams)).append("\n");
    sb.append("    warnings: ").append(toIndentedString(warnings)).append("\n");
    sb.append("    error: ").append(toIndentedString(error)).append("\n");
    sb.append("    _links: ").append(toIndentedString(_links)).append("\n");
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