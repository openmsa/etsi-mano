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
package com.ubiqube.etsi.mano.nfvo.v351.model.vnfsnapshotpkgm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents the request parameters for cancelling an ongoing operation related  to the content of a VNF snapshot package. It shall comply with the provisions defined in  table 11.5.2.8-1. 
 */
@Schema(description = "This type represents the request parameters for cancelling an ongoing operation related  to the content of a VNF snapshot package. It shall comply with the provisions defined in  table 11.5.2.8-1. ")
@Validated


public class CancelVnfSnapshotPkgOperationRequest   {
  @JsonProperty("cause")
  private String cause = null;

  public CancelVnfSnapshotPkgOperationRequest cause(String cause) {
    this.cause = cause;
    return this;
  }

  /**
   * Description about the reason for cancelling the operation. If this attribute is present,  the NFVO shall add such information into the \"failureDetails\" of the representation of  the \"Individual VNF snapshot package\" resource. 
   * @return cause
   **/
  @Schema(description = "Description about the reason for cancelling the operation. If this attribute is present,  the NFVO shall add such information into the \"failureDetails\" of the representation of  the \"Individual VNF snapshot package\" resource. ")
  
    public String getCause() {
    return cause;
  }

  public void setCause(String cause) {
    this.cause = cause;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CancelVnfSnapshotPkgOperationRequest cancelVnfSnapshotPkgOperationRequest = (CancelVnfSnapshotPkgOperationRequest) o;
    return Objects.equals(this.cause, cancelVnfSnapshotPkgOperationRequest.cause);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cause);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CancelVnfSnapshotPkgOperationRequest {\n");
    
    sb.append("    cause: ").append(toIndentedString(cause)).append("\n");
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
