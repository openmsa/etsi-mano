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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type specifies existing VNF instances to be moved from one NS instance (source) to another NS instance (destination). The NS instance defined in the Update NS operation indicates the source NS instance and the destination NS instance is specified in this data type (referred to targetNsInstanceId). It shall comply with the provisions defined in Table 6.5.3.35-1. 
 */
@ApiModel(description = "This type specifies existing VNF instances to be moved from one NS instance (source) to another NS instance (destination). The NS instance defined in the Update NS operation indicates the source NS instance and the destination NS instance is specified in this data type (referred to targetNsInstanceId). It shall comply with the provisions defined in Table 6.5.3.35-1. ")
@Validated

public class MoveVnfInstanceData   {
  @JsonProperty("targetNsInstanceId")
  private String targetNsInstanceId = null;

  @JsonProperty("vnfInstanceId")
  @Valid
  private List<String> vnfInstanceId = null;

  public MoveVnfInstanceData targetNsInstanceId(String targetNsInstanceId) {
    this.targetNsInstanceId = targetNsInstanceId;
    return this;
  }

  /**
   * Specify the target NS instance where the VNF instances are moved to. 
   * @return targetNsInstanceId
  **/
  @ApiModelProperty(required = true, value = "Specify the target NS instance where the VNF instances are moved to. ")
  @NotNull


  public String getTargetNsInstanceId() {
    return targetNsInstanceId;
  }

  public void setTargetNsInstanceId(String targetNsInstanceId) {
    this.targetNsInstanceId = targetNsInstanceId;
  }

  public MoveVnfInstanceData vnfInstanceId(List<String> vnfInstanceId) {
    this.vnfInstanceId = vnfInstanceId;
    return this;
  }

  public MoveVnfInstanceData addVnfInstanceIdItem(String vnfInstanceIdItem) {
    if (this.vnfInstanceId == null) {
      this.vnfInstanceId = new ArrayList<>();
    }
    this.vnfInstanceId.add(vnfInstanceIdItem);
    return this;
  }

  /**
   * Specify the VNF instance that is moved. 
   * @return vnfInstanceId
  **/
  @ApiModelProperty(value = "Specify the VNF instance that is moved. ")


  public List<String> getVnfInstanceId() {
    return vnfInstanceId;
  }

  public void setVnfInstanceId(List<String> vnfInstanceId) {
    this.vnfInstanceId = vnfInstanceId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MoveVnfInstanceData moveVnfInstanceData = (MoveVnfInstanceData) o;
    return Objects.equals(this.targetNsInstanceId, moveVnfInstanceData.targetNsInstanceId) &&
        Objects.equals(this.vnfInstanceId, moveVnfInstanceData.vnfInstanceId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(targetNsInstanceId, vnfInstanceId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MoveVnfInstanceData {\n");
    
    sb.append("    targetNsInstanceId: ").append(toIndentedString(targetNsInstanceId)).append("\n");
    sb.append("    vnfInstanceId: ").append(toIndentedString(vnfInstanceId)).append("\n");
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

