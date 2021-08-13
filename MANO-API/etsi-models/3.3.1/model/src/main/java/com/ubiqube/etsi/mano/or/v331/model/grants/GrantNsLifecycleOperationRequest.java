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
package com.ubiqube.etsi.mano.or.v331.model.grants;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Map;
import com.ubiqube.etsi.mano.or.v331.model.grants.NsLcmOperation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents request parameters for the \&quot;grant NS lifecycle\&quot; operation. It shall comply with the provisions defined in table 7.6.2.2-1.
 */
@Schema(description = "This type represents request parameters for the \"grant NS lifecycle\" operation. It shall comply with the provisions defined in table 7.6.2.2-1.")
@Validated


public class GrantNsLifecycleOperationRequest   {
  @JsonProperty("nsInstanceId")
  private String nsInstanceId = null;

  @JsonProperty("nsdId")
  private String nsdId = null;

  @JsonProperty("nsLcmOpOccId")
  private String nsLcmOpOccId = null;

  @JsonProperty("lifecycleOperation")
  private NsLcmOperation lifecycleOperation = null;

  @JsonProperty("additionalParams")
  private Map<String, String> additionalParams = null;

  public GrantNsLifecycleOperationRequest nsInstanceId(String nsInstanceId) {
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

  public GrantNsLifecycleOperationRequest nsdId(String nsdId) {
    this.nsdId = nsdId;
    return this;
  }

  /**
   * Get nsdId
   * @return nsdId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getNsdId() {
    return nsdId;
  }

  public void setNsdId(String nsdId) {
    this.nsdId = nsdId;
  }

  public GrantNsLifecycleOperationRequest nsLcmOpOccId(String nsLcmOpOccId) {
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

  public GrantNsLifecycleOperationRequest lifecycleOperation(NsLcmOperation lifecycleOperation) {
    this.lifecycleOperation = lifecycleOperation;
    return this;
  }

  /**
   * Get lifecycleOperation
   * @return lifecycleOperation
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public NsLcmOperation getLifecycleOperation() {
    return lifecycleOperation;
  }

  public void setLifecycleOperation(NsLcmOperation lifecycleOperation) {
    this.lifecycleOperation = lifecycleOperation;
  }

  public GrantNsLifecycleOperationRequest additionalParams(Map<String, String> additionalParams) {
    this.additionalParams = additionalParams;
    return this;
  }

  /**
   * Get additionalParams
   * @return additionalParams
   **/
  @Schema(description = "")
  
    @Valid
    public Map<String, String> getAdditionalParams() {
    return additionalParams;
  }

  public void setAdditionalParams(Map<String, String> additionalParams) {
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
    GrantNsLifecycleOperationRequest grantNsLifecycleOperationRequest = (GrantNsLifecycleOperationRequest) o;
    return Objects.equals(this.nsInstanceId, grantNsLifecycleOperationRequest.nsInstanceId) &&
        Objects.equals(this.nsdId, grantNsLifecycleOperationRequest.nsdId) &&
        Objects.equals(this.nsLcmOpOccId, grantNsLifecycleOperationRequest.nsLcmOpOccId) &&
        Objects.equals(this.lifecycleOperation, grantNsLifecycleOperationRequest.lifecycleOperation) &&
        Objects.equals(this.additionalParams, grantNsLifecycleOperationRequest.additionalParams);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nsInstanceId, nsdId, nsLcmOpOccId, lifecycleOperation, additionalParams);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GrantNsLifecycleOperationRequest {\n");
    
    sb.append("    nsInstanceId: ").append(toIndentedString(nsInstanceId)).append("\n");
    sb.append("    nsdId: ").append(toIndentedString(nsdId)).append("\n");
    sb.append("    nsLcmOpOccId: ").append(toIndentedString(nsLcmOpOccId)).append("\n");
    sb.append("    lifecycleOperation: ").append(toIndentedString(lifecycleOperation)).append("\n");
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
