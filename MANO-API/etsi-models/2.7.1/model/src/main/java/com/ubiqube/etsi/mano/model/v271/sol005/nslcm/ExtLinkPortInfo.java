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
package com.ubiqube.etsi.mano.model.v271.sol005.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.model.v271.sol005.nslcm.ResourceHandle;
import com.fasterxml.jackson.annotation.JsonCreator;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents information about a link port of an external VL, i.e. a port providing connectivity for the VNF to an NS VL. 
 */
@ApiModel(description = "This type represents information about a link port of an external VL, i.e. a port providing connectivity for the VNF to an NS VL. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-11-09T10:14:43.989+01:00")

public class ExtLinkPortInfo   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("resourceHandle")
  private ResourceHandle resourceHandle = null;

  @JsonProperty("cpInstanceId")
  private String cpInstanceId = null;

  public ExtLinkPortInfo id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Identifier of this link port as provided by the entity that has created the link port. 
   * @return id
  **/
  @ApiModelProperty(required = true, value = "Identifier of this link port as provided by the entity that has created the link port. ")
  @NotNull


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ExtLinkPortInfo resourceHandle(ResourceHandle resourceHandle) {
    this.resourceHandle = resourceHandle;
    return this;
  }

  /**
   * Reference to the virtualised resource realizing this link port. 
   * @return resourceHandle
  **/
  @ApiModelProperty(required = true, value = "Reference to the virtualised resource realizing this link port. ")
  @NotNull

  @Valid

  public ResourceHandle getResourceHandle() {
    return resourceHandle;
  }

  public void setResourceHandle(ResourceHandle resourceHandle) {
    this.resourceHandle = resourceHandle;
  }

  public ExtLinkPortInfo cpInstanceId(String cpInstanceId) {
    this.cpInstanceId = cpInstanceId;
    return this;
  }

  /**
   * Identifier of the external CP of the VNF connected to this link port. There shall be at most one link port associated with any external connection point instance. The value refers to an \"extCpInfo\" item in the VnfInstance. 
   * @return cpInstanceId
  **/
  @ApiModelProperty(value = "Identifier of the external CP of the VNF connected to this link port. There shall be at most one link port associated with any external connection point instance. The value refers to an \"extCpInfo\" item in the VnfInstance. ")


  public String getCpInstanceId() {
    return cpInstanceId;
  }

  public void setCpInstanceId(String cpInstanceId) {
    this.cpInstanceId = cpInstanceId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ExtLinkPortInfo extLinkPortInfo = (ExtLinkPortInfo) o;
    return Objects.equals(this.id, extLinkPortInfo.id) &&
        Objects.equals(this.resourceHandle, extLinkPortInfo.resourceHandle) &&
        Objects.equals(this.cpInstanceId, extLinkPortInfo.cpInstanceId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, resourceHandle, cpInstanceId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ExtLinkPortInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    resourceHandle: ").append(toIndentedString(resourceHandle)).append("\n");
    sb.append("    cpInstanceId: ").append(toIndentedString(cpInstanceId)).append("\n");
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

