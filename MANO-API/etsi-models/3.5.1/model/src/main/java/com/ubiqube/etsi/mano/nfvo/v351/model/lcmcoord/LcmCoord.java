/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.etsi.mano.nfvo.v351.model.lcmcoord;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v351.model.lcmcoord.KeyValuePairs;
import com.ubiqube.etsi.mano.nfvo.v351.model.lcmcoord.LcmCoordLinks;
import com.ubiqube.etsi.mano.nfvo.v351.model.lcmcoord.LcmCoordResultType;
import com.ubiqube.etsi.mano.nfvo.v351.model.lcmcoord.LcmOperationForCoordType;
import com.ubiqube.etsi.mano.nfvo.v351.model.lcmcoord.ProblemDetails;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents an LCM coordination result. It shall comply with the provisions  defined in table 12.5.2.3-1.  NOTE: How to determine the supported coordination actions is outside the scope of the        present version of this document. 
 */
@Schema(description = "This type represents an LCM coordination result. It shall comply with the provisions  defined in table 12.5.2.3-1.  NOTE: How to determine the supported coordination actions is outside the scope of the        present version of this document. ")
@Validated


public class LcmCoord   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("coordinationResult")
  private LcmCoordResultType coordinationResult = null;

  @JsonProperty("nsInstanceId")
  private String nsInstanceId = null;

  @JsonProperty("nsLcmOpOccId")
  private String nsLcmOpOccId = null;

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

  public LcmCoord nsInstanceId(String nsInstanceId) {
    this.nsInstanceId = nsInstanceId;
    return this;
  }

  /**
   * Get nsInstanceId
   * @return nsInstanceId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getNsInstanceId() {
    return nsInstanceId;
  }

  public void setNsInstanceId(String nsInstanceId) {
    this.nsInstanceId = nsInstanceId;
  }

  public LcmCoord nsLcmOpOccId(String nsLcmOpOccId) {
    this.nsLcmOpOccId = nsLcmOpOccId;
    return this;
  }

  /**
   * Get nsLcmOpOccId
   * @return nsLcmOpOccId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getNsLcmOpOccId() {
    return nsLcmOpOccId;
  }

  public void setNsLcmOpOccId(String nsLcmOpOccId) {
    this.nsLcmOpOccId = nsLcmOpOccId;
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
   * Get warnings
   * @return warnings
   **/
  @Schema(description = "")
  
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
        Objects.equals(this.nsInstanceId, lcmCoord.nsInstanceId) &&
        Objects.equals(this.nsLcmOpOccId, lcmCoord.nsLcmOpOccId) &&
        Objects.equals(this.lcmOperationType, lcmCoord.lcmOperationType) &&
        Objects.equals(this.coordinationActionName, lcmCoord.coordinationActionName) &&
        Objects.equals(this.outputParams, lcmCoord.outputParams) &&
        Objects.equals(this.warnings, lcmCoord.warnings) &&
        Objects.equals(this.error, lcmCoord.error) &&
        Objects.equals(this._links, lcmCoord._links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, coordinationResult, nsInstanceId, nsLcmOpOccId, lcmOperationType, coordinationActionName, outputParams, warnings, error, _links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LcmCoord {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    coordinationResult: ").append(toIndentedString(coordinationResult)).append("\n");
    sb.append("    nsInstanceId: ").append(toIndentedString(nsInstanceId)).append("\n");
    sb.append("    nsLcmOpOccId: ").append(toIndentedString(nsLcmOpOccId)).append("\n");
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
