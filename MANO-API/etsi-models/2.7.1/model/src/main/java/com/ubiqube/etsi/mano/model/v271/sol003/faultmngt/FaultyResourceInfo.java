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
package com.ubiqube.etsi.mano.model.v271.sol003.faultmngt;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.model.v271.sol003.faultmngt.FaultyResourceType;
import com.ubiqube.etsi.mano.model.v271.sol003.faultmngt.ResourceHandle;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents the faulty virtual resources that have a negative impact on a VNF. 
 */
@ApiModel(description = "This type represents the faulty virtual resources that have a negative impact on a VNF. ")
@Validated


public class FaultyResourceInfo   {
  @JsonProperty("faultyResource")
  private ResourceHandle faultyResource = null;

  @JsonProperty("faultyResourceType")
  private FaultyResourceType faultyResourceType = null;

  public FaultyResourceInfo faultyResource(ResourceHandle faultyResource) {
    this.faultyResource = faultyResource;
    return this;
  }

  /**
   * Information that identifies the faulty resource instance and its managing entity. 
   * @return faultyResource
  **/
  @ApiModelProperty(required = true, value = "Information that identifies the faulty resource instance and its managing entity. ")
  @NotNull

  @Valid

  public ResourceHandle getFaultyResource() {
    return faultyResource;
  }

  public void setFaultyResource(ResourceHandle faultyResource) {
    this.faultyResource = faultyResource;
  }

  public FaultyResourceInfo faultyResourceType(FaultyResourceType faultyResourceType) {
    this.faultyResourceType = faultyResourceType;
    return this;
  }

  /**
   * Type of the faulty resource. 
   * @return faultyResourceType
  **/
  @ApiModelProperty(required = true, value = "Type of the faulty resource. ")
  @NotNull

  @Valid

  public FaultyResourceType getFaultyResourceType() {
    return faultyResourceType;
  }

  public void setFaultyResourceType(FaultyResourceType faultyResourceType) {
    this.faultyResourceType = faultyResourceType;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FaultyResourceInfo faultyResourceInfo = (FaultyResourceInfo) o;
    return Objects.equals(this.faultyResource, faultyResourceInfo.faultyResource) &&
        Objects.equals(this.faultyResourceType, faultyResourceInfo.faultyResourceType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(faultyResource, faultyResourceType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FaultyResourceInfo {\n");
    
    sb.append("    faultyResource: ").append(toIndentedString(faultyResource)).append("\n");
    sb.append("    faultyResourceType: ").append(toIndentedString(faultyResourceType)).append("\n");
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

