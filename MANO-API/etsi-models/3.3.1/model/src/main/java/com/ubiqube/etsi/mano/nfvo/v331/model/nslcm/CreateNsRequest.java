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
package com.ubiqube.etsi.mano.nfvo.v331.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CreateNsRequest
 */
@Validated


public class CreateNsRequest   {
  @JsonProperty("nsdId")
  private String nsdId = null;

  @JsonProperty("nsName")
  private String nsName = null;

  @JsonProperty("nsDescription")
  private String nsDescription = null;

  public CreateNsRequest nsdId(String nsdId) {
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

  public CreateNsRequest nsName(String nsName) {
    this.nsName = nsName;
    return this;
  }

  /**
   * Human-readable name of the NS instance to be created. 
   * @return nsName
   **/
  @Schema(required = true, description = "Human-readable name of the NS instance to be created. ")
      @NotNull

    public String getNsName() {
    return nsName;
  }

  public void setNsName(String nsName) {
    this.nsName = nsName;
  }

  public CreateNsRequest nsDescription(String nsDescription) {
    this.nsDescription = nsDescription;
    return this;
  }

  /**
   * Human-readable description of the NS instance to be created. 
   * @return nsDescription
   **/
  @Schema(required = true, description = "Human-readable description of the NS instance to be created. ")
      @NotNull

    public String getNsDescription() {
    return nsDescription;
  }

  public void setNsDescription(String nsDescription) {
    this.nsDescription = nsDescription;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateNsRequest createNsRequest = (CreateNsRequest) o;
    return Objects.equals(this.nsdId, createNsRequest.nsdId) &&
        Objects.equals(this.nsName, createNsRequest.nsName) &&
        Objects.equals(this.nsDescription, createNsRequest.nsDescription);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nsdId, nsName, nsDescription);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateNsRequest {\n");
    
    sb.append("    nsdId: ").append(toIndentedString(nsdId)).append("\n");
    sb.append("    nsName: ").append(toIndentedString(nsName)).append("\n");
    sb.append("    nsDescription: ").append(toIndentedString(nsDescription)).append("\n");
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
