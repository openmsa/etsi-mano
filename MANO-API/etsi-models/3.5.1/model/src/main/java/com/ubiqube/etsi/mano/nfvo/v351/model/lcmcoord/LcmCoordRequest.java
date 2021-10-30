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
import com.ubiqube.etsi.mano.nfvo.v351.model.lcmcoord.LcmCoordRequestLinks;
import com.ubiqube.etsi.mano.nfvo.v351.model.lcmcoord.LcmOperationForCoordType;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents an LCM coordination request.  It shall comply with the provisions defined in table 12.5.2.2-1.  NOTE: How to determine the supported coordination actions is outside        the scope of the present version of this document. 
 */
@Schema(description = "This type represents an LCM coordination request.  It shall comply with the provisions defined in table 12.5.2.2-1.  NOTE: How to determine the supported coordination actions is outside        the scope of the present version of this document. ")
@Validated


public class LcmCoordRequest   {
  @JsonProperty("nsInstanceId")
  private String nsInstanceId = null;

  @JsonProperty("nsLcmOpOccId")
  private String nsLcmOpOccId = null;

  @JsonProperty("lcmOperationType")
  private LcmOperationForCoordType lcmOperationType = null;

  @JsonProperty("coordinationActionName")
  private String coordinationActionName = null;

  @JsonProperty("inputParams")
  private KeyValuePairs inputParams = null;

  @JsonProperty("_links")
  private LcmCoordRequestLinks _links = null;

  public LcmCoordRequest nsInstanceId(String nsInstanceId) {
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

  public LcmCoordRequest nsLcmOpOccId(String nsLcmOpOccId) {
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
    return Objects.equals(this.nsInstanceId, lcmCoordRequest.nsInstanceId) &&
        Objects.equals(this.nsLcmOpOccId, lcmCoordRequest.nsLcmOpOccId) &&
        Objects.equals(this.lcmOperationType, lcmCoordRequest.lcmOperationType) &&
        Objects.equals(this.coordinationActionName, lcmCoordRequest.coordinationActionName) &&
        Objects.equals(this.inputParams, lcmCoordRequest.inputParams) &&
        Objects.equals(this._links, lcmCoordRequest._links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nsInstanceId, nsLcmOpOccId, lcmOperationType, coordinationActionName, inputParams, _links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LcmCoordRequest {\n");
    
    sb.append("    nsInstanceId: ").append(toIndentedString(nsInstanceId)).append("\n");
    sb.append("    nsLcmOpOccId: ").append(toIndentedString(nsLcmOpOccId)).append("\n");
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
