package com.ubiqube.etsi.mano.em.v351.model.lcmcoord;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.em.v351.model.lcmcoord.KeyValuePairs;
import com.ubiqube.etsi.mano.em.v351.model.lcmcoord.LcmCoordRequestLinks;
import com.ubiqube.etsi.mano.em.v351.model.lcmcoord.LcmOperationForCoordType;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * LcmCoordRequest
 */
@Validated


public class LcmCoordRequest   {
  @JsonProperty("vnfInstanceId")
  private String vnfInstanceId = null;

  @JsonProperty("vnfLcmOpOccId")
  private String vnfLcmOpOccId = null;

  @JsonProperty("lcmOperationType")
  private LcmOperationForCoordType lcmOperationType = null;

  @JsonProperty("coordinationActionName")
  private String coordinationActionName = null;

  @JsonProperty("inputParams")
  private KeyValuePairs inputParams = null;

  @JsonProperty("_links")
  private LcmCoordRequestLinks _links = null;

  public LcmCoordRequest vnfInstanceId(String vnfInstanceId) {
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

  public LcmCoordRequest vnfLcmOpOccId(String vnfLcmOpOccId) {
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

  public LcmCoordRequest lcmOperationType(LcmOperationForCoordType lcmOperationType) {
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

  public LcmCoordRequest coordinationActionName(String coordinationActionName) {
    this.coordinationActionName = coordinationActionName;
    return this;
  }

  /**
   * Get coordinationActionName
   * @return coordinationActionName
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getCoordinationActionName() {
    return coordinationActionName;
  }

  public void setCoordinationActionName(String coordinationActionName) {
    this.coordinationActionName = coordinationActionName;
  }

  public LcmCoordRequest inputParams(KeyValuePairs inputParams) {
    this.inputParams = inputParams;
    return this;
  }

  /**
   * Get inputParams
   * @return inputParams
   **/
  @Schema(description = "")
  
    @Valid
    public KeyValuePairs getInputParams() {
    return inputParams;
  }

  public void setInputParams(KeyValuePairs inputParams) {
    this.inputParams = inputParams;
  }

  public LcmCoordRequest _links(LcmCoordRequestLinks _links) {
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
    public LcmCoordRequestLinks getLinks() {
    return _links;
  }

  public void setLinks(LcmCoordRequestLinks _links) {
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
    LcmCoordRequest lcmCoordRequest = (LcmCoordRequest) o;
    return Objects.equals(this.vnfInstanceId, lcmCoordRequest.vnfInstanceId) &&
        Objects.equals(this.vnfLcmOpOccId, lcmCoordRequest.vnfLcmOpOccId) &&
        Objects.equals(this.lcmOperationType, lcmCoordRequest.lcmOperationType) &&
        Objects.equals(this.coordinationActionName, lcmCoordRequest.coordinationActionName) &&
        Objects.equals(this.inputParams, lcmCoordRequest.inputParams) &&
        Objects.equals(this._links, lcmCoordRequest._links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vnfInstanceId, vnfLcmOpOccId, lcmOperationType, coordinationActionName, inputParams, _links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LcmCoordRequest {\n");
    
    sb.append("    vnfInstanceId: ").append(toIndentedString(vnfInstanceId)).append("\n");
    sb.append("    vnfLcmOpOccId: ").append(toIndentedString(vnfLcmOpOccId)).append("\n");
    sb.append("    lcmOperationType: ").append(toIndentedString(lcmOperationType)).append("\n");
    sb.append("    coordinationActionName: ").append(toIndentedString(coordinationActionName)).append("\n");
    sb.append("    inputParams: ").append(toIndentedString(inputParams)).append("\n");
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
