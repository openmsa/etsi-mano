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
package com.ubiqube.etsi.mano.nfvo.v281.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v281.model.nslcm.KeyValuePairs;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents the information to heal a VNF that is part of an NS.  The NFVO shall then invoke the HealVNF operation towards the appropriate VNFM.  It shall comply with the provisions defined in Table 6.5.3.44-1. 
 */
@ApiModel(description = "This type represents the information to heal a VNF that is part of an NS.  The NFVO shall then invoke the HealVNF operation towards the appropriate VNFM.  It shall comply with the provisions defined in Table 6.5.3.44-1. ")
@Validated

public class HealVnfData   {
  @JsonProperty("vnfInstanceId")
  private String vnfInstanceId = null;

  @JsonProperty("cause")
  private String cause = null;

  @JsonProperty("additionalParams")
  private KeyValuePairs additionalParams = null;

  public HealVnfData vnfInstanceId(String vnfInstanceId) {
    this.vnfInstanceId = vnfInstanceId;
    return this;
  }

  /**
   * Identifies the VNF instance, part of the NS, requiring a healing action. 
   * @return vnfInstanceId
  **/
  @ApiModelProperty(required = true, value = "Identifies the VNF instance, part of the NS, requiring a healing action. ")
  @NotNull


  public String getVnfInstanceId() {
    return vnfInstanceId;
  }

  public void setVnfInstanceId(String vnfInstanceId) {
    this.vnfInstanceId = vnfInstanceId;
  }

  public HealVnfData cause(String cause) {
    this.cause = cause;
    return this;
  }

  /**
   * Indicates the reason why a healing procedure is required. 
   * @return cause
  **/
  @ApiModelProperty(value = "Indicates the reason why a healing procedure is required. ")


  public String getCause() {
    return cause;
  }

  public void setCause(String cause) {
    this.cause = cause;
  }

  public HealVnfData additionalParams(KeyValuePairs additionalParams) {
    this.additionalParams = additionalParams;
    return this;
  }

  /**
   * Additional parameters passed by the NFVO as input to the healing process, specific to the VNF being healed. EXAMPLE: Input parameters to VNF-specific healing procedures. 
   * @return additionalParams
  **/
  @ApiModelProperty(value = "Additional parameters passed by the NFVO as input to the healing process, specific to the VNF being healed. EXAMPLE: Input parameters to VNF-specific healing procedures. ")

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
    HealVnfData healVnfData = (HealVnfData) o;
    return Objects.equals(this.vnfInstanceId, healVnfData.vnfInstanceId) &&
        Objects.equals(this.cause, healVnfData.cause) &&
        Objects.equals(this.additionalParams, healVnfData.additionalParams);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vnfInstanceId, cause, additionalParams);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class HealVnfData {\n");
    
    sb.append("    vnfInstanceId: ").append(toIndentedString(vnfInstanceId)).append("\n");
    sb.append("    cause: ").append(toIndentedString(cause)).append("\n");
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

